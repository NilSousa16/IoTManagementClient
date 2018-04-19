package org.apache.servicemix.examples.cxf.info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.karaf.shell.support.ShellUtil;
import org.apache.servicemix.examples.cxf.interfaces.ServiceInfoInterface;
import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.Service;
import org.apache.servicemix.examples.cxf.util.ObjectClassMatcher;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * Class responsible for discovering the services installed at the gateway
 *
 * @author Nilson Rodrigues Sousa
 */
public class ServiceInfo implements ServiceInfoInterface {

	private String objectClass;

	private boolean showAll;

	/* Recover instance of bundle context */
	private BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

	/*
	 * Stores the list of services that will be evaluated for sending to the
	 * server
	 */
	protected static List<Service> listService = new ArrayList<Service>();

	/**
	 * Method returns information about the services
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Service> - Services existing gateway
	 */
	public List<Service> getListService() {

		try {
			listService = new ArrayList<Service>();

			List<ServiceReference<?>> serviceRefs = new ArrayList<ServiceReference<?>>();
			Bundle[] bundles = bundleContext.getBundles();
			for (Bundle bundle : bundles) {
				ServiceReference<?>[] services = bundle.getRegisteredServices();
				if (services != null) {
					for (ServiceReference<?> ref : services) {
						String[] objectClasses = (String[]) ref.getProperty(Constants.OBJECTCLASS);
						if (objectClass == null
								|| ObjectClassMatcher.matchesAtLeastOneName(objectClasses, objectClass)) {
							serviceRefs.add(ref);
						}
					}
				}
			}

			Collections.sort(serviceRefs, new ServiceClassComparator());

			/* Used to limit the number of services */
			int cont = 0;

			/* Instruction for popular listService */
			for (ServiceReference<?> serviceRef : serviceRefs) {

				/* Service limiter */
				if (cont > 15) {
					break;
				}
				
				cont++;

				if (showAll || !isCommand((String[]) serviceRef.getProperty(Constants.OBJECTCLASS))) {

					Service service = new Service();

					String[] objectClass = (String[]) serviceRef.getProperty(Constants.OBJECTCLASS);

					/*
					 * Capture the name of the service in the current iteration
					 */
					service.setNameService(ShellUtil.getValueString(objectClass));

					/* Capture the id of the service in the current iteration */
					for (String key : serviceRef.getPropertyKeys()) {
						if (!Constants.OBJECTCLASS.equals(key)) {
							if (key.equals("service.id")) {
								service.setId(Long.parseLong(ShellUtil.getValueString(serviceRef.getProperty(key))));
							}
						}
					}

					/* Captures the service bundleProvide information */
					Bundler bundler = new Bundler();

					bundler.setName(formatOutput(ShellUtil.getBundleName(serviceRef.getBundle())));
					bundler.setVersion(String.valueOf(serviceRef.getBundle().getVersion()));

					service.setBundlerProvide(bundler);

					/*
					 * Captures the information of the bundles that use use the
					 * service
					 */
					Bundle[] usingBundles = serviceRef.getUsingBundles();
					if (usingBundles != null) {
						for (Bundle bundle : usingBundles) {
							Bundler addBundler = new Bundler();

							addBundler.setName(formatOutput(ShellUtil.getBundleName(bundle)));
							addBundler.setVersion(String.valueOf(bundle.getVersion()));

							service.setBundlerProvide(bundler);

							service.getListUsesBundles().add(addBundler);

							// service.getListUsesBundles().add(formatOutput(ShellUtil.getBundleName(bundle)));
						}
					}
					listService.add(service);
				}
			}

			return listService;
		} catch (Exception e) {
			System.out.println("Failure in capture information service.");
			return null;
		}
	}

	/**
	 * Karaf implementation method
	 * 
	 * @param string
	 *            String - String to be formatted
	 * @author Apache Sofware Foundation
	 * @return String - Formatted String
	 */
	private String formatOutput(String string) {
		String stringFormated = "";

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '(') {
				break;
			}
			stringFormated += string.charAt(i);
		}
		stringFormated = stringFormated.substring(0, stringFormated.length() - 1);
		return stringFormated;
	}

	/**
	 * Karaf implementation method
	 * 
	 * @param objectClasses
	 *            String[] - List strings
	 * @author Apache Sofware Foundation
	 * @return boolean - Command
	 */
	private boolean isCommand(String[] objectClasses) {
		for (String objectClass : objectClasses) {
			if (objectClass.equals("org.apache.felix.service.command.Function")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Karaf implementation method
	 * 
	 * @author Apache Sofware Foundation
	 * @return ServiceClassComparator - Comparison result
	 */
	public final class ServiceClassComparator implements Comparator<ServiceReference<?>> {
		@Override
		public int compare(ServiceReference<?> o1, ServiceReference<?> o2) {
			String class1 = getObjectClass(o1);
			String class2 = getObjectClass(o2);
			return class1.compareTo(class2);
		}

		private String getObjectClass(ServiceReference<?> o1) {
			Object value = o1.getProperty(Constants.OBJECTCLASS);
			if (value == null || !(value instanceof String[])) {
				return "";
			}
			String[] values = (String[]) value;
			return values.length == 0 ? "" : values[0];
		}
	}

}
