package org.apache.servicemix.examples.cxf.info;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.bundle.core.BundleInfo;
import org.apache.karaf.bundle.core.BundleService;
import org.apache.servicemix.examples.cxf.interfaces.BundlerInfoInterface;
import org.apache.servicemix.examples.cxf.model.Bundler;
import org.osgi.framework.Bundle;

/**
 * Class responsible detect gateway and update database with your ip, mac and
 * status
 *
 * @author Nilson Rodrigues Sousa
 */
public class BundlerInfo implements BundlerInfoInterface {

	/* Instance of Karaf recovered context */
	BundleService bundleService;

	String context = "0";

	List<String> ids;

	boolean defaultAllBundles = true;

	/* List of bundles retrieved from Karaf context */
	List<Bundle> listBundle;

	/* List of captured bundles for use */
	protected static List<Bundler> listBundler = new ArrayList<Bundler>();

	/**
	 * Method used by blueprint to instantiate a bundleService
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param bundleService
	 *            BundleService - Karaf bundleService instance
	 */
	public void setBundleService(BundleService bundleService) {
		this.bundleService = bundleService;
	}

	/**
	 * Method returns information about the bundles
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Bundler> - Bundles existing gateway
	 */
	public List<Bundler> getListBundler() {
		try {
			Bundler bundler;

			/* List to store the captured bundles */
			listBundler = new ArrayList<Bundler>();

			/* Returns the system list */
			listBundle = bundleService.selectBundles(context, ids, defaultAllBundles);

			/* Instruction that captures bundle information */
			for (Bundle bundle : listBundle) {

				bundler = new Bundler();
				BundleInfo info = this.bundleService.getInfo(bundle);

				if (info.getStartLevel() >= -1) {

					bundler.setName(info.getName());
					bundler.setVersion(info.getVersion());
					bundler.setStatus(info.getState().toString());
					bundler.setLocation(info.getUpdateLocation());

					listBundler.add(bundler);
				}
			}

			if (!listBundler.isEmpty()) {
				return listBundler;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failure in capture information bundler.");
			return null;
		}

	}

}
