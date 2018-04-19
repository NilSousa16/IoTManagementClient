package org.apache.servicemix.examples.cxf.send;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicemix.examples.cxf.info.DeviceInfo;
import org.apache.servicemix.examples.cxf.info.GatewayInfo;
import org.apache.servicemix.examples.cxf.model.Actuator;
import org.apache.servicemix.examples.cxf.model.Device;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.model.Sensor;
import org.apache.servicemix.examples.cxf.service.DeviceService;

/**
 * Class responsible for verifying changes to devices information and requesting
 * submission.
 *
 * @author Nilson Rodrigues Sousa
 */
public class ControlSendDeviceInformation {

	/* Responsible for saving the last registered status of the devices */
	private static List<Device> listDevices;

	/* Responsible for requesting information to the server */
	private DeviceService deviceService;

	/* Responsible for capturing device information */
	private DeviceInfo deviceInfo = null;

	/**
	 * Constructs the deviceService and deviceInfo objects
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public ControlSendDeviceInformation() {
		deviceService = new DeviceService();
		deviceInfo = new DeviceInfo();
	}

	/**
	 * Method that compares device information
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public void compareDevice() {

		/*
		 * Conditional that verifies that the gateway has already been properly
		 * registered on the server
		 */
		if (ControlSendGatewayInformation.storaged) {

			/* Used to work the retrieved information */
			List<Device> listCapt = new ArrayList<Device>();

			/* Used to store information retrieved from devices */
			List<Device> listInfo = new ArrayList<Device>();

			/*
			 * Returns a list of existing devices and their sensors and
			 * actuators
			 */
			listInfo = deviceInfo.getListDevice();

			if (listInfo != null && !(listInfo.isEmpty())) {

				/*
				 * Populate the listCapt to be able to evaluate changes in
				 * device information - Routine made by errors of previous
				 * references
				 */
				for (Device d : deviceInfo.getListDevice()) {
					Device dev = new Device();

					dev.setName(d.getName());
					dev.setManufacturer(d.getManufacturer());
					dev.setVersion(d.getVersion());
					dev.setLocation(d.getLocation());
					dev.setDescriptionSemantic(d.getDescriptionSemantic());

					for (Sensor s : d.getListSensor()) {
						Sensor sensor = new Sensor();

						sensor.setName(s.getName());
						sensor.setManufacturer(s.getManufacturer());
						sensor.setVersion(s.getVersion());
						sensor.setLocation(s.getLocation());
						sensor.setDescriptionSemantic(s.getDescriptionSemantic());
						sensor.getListActuator().addAll(s.getListActuator());
						sensor.getListSensor().addAll(s.getListSensor());

						dev.getListSensor().add(sensor);
					}

					for (Actuator a : d.getListActuator()) {
						Actuator actuator = new Actuator();

						actuator.setName(a.getName());
						actuator.setManufacturer(a.getManufacturer());
						actuator.setVersion(a.getVersion());
						actuator.setLocation(a.getLocation());
						actuator.setDescriptionSemantic(a.getDescriptionSemantic());
						actuator.getListActuator().addAll(a.getListActuator());
						actuator.getListSensor().addAll(a.getListSensor());

						dev.getListActuator().add(actuator);
					}

					listCapt.add(dev);

				}

				/* Conditional that evaluates if listDevices is empty */
				if (listDevices != null || listDevices.isEmpty()) {
					listDevices.addAll(listCapt);

					GatewayInfo gatewayInfo = new GatewayInfo();

					Gateway gatewaySend = new Gateway();
					gatewaySend.setMac(gatewayInfo.getMac());
					gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
					gatewaySend.getListDevice().addAll(listDevices);

					/* Sending device information for the first time */
					deviceService.sendListDeviceConnected(gatewaySend);
				} else {
					/* List retrieved from the system to perform comparison */
					List<Device> listDevicesInstance = listCapt;

					/* List of disconnected devices */
					List<Device> listDevicesDisconnected = this.compareToDevices(listDevices, listDevicesInstance);

					/* List of disconnected devices */
					List<Device> listDevicesConnected = this.compareToDevices(listDevicesInstance, listDevices);

					/*
					 * List with devices that have new sensors or actuators
					 * connected
					 */
					List<Device> listInformationAlteredConnected = this
							.compareToInformationAlteredSensorActuator(listDevicesInstance, listDevices);

					/*
					 * List with devices that have sensors or actuators
					 * disconnected
					 */
					List<Device> listInformationAlteredDisconnected = this
							.compareToInformationAlteredSensorActuator(listDevices, listDevicesInstance);

					/*
					 * Condition that evaluates whether there is information
					 * from disconnected devices for sending
					 */
					if (!(listDevicesDisconnected.isEmpty() || listDevicesDisconnected == null)) {
						this.infoDeviceDisconnected(listDevicesDisconnected);
					}

					/*
					 * Condition that evaluates whether there is information
					 * from connected devices for sending
					 */
					if (!(listDevicesConnected.isEmpty() || listDevicesConnected == null)) {
						this.infoDeviceConnected(listDevicesConnected);
					}

					/*
					 * Condition evaluating whether device information exists
					 * with sensors and new actuators
					 */
					if (!(listInformationAlteredConnected.isEmpty() || listInformationAlteredConnected == null)) {
						this.infoDeviceAlteredConnected(listInformationAlteredConnected);
					}

					/*
					 * Condition that evaluates whether there is information
					 * from devices with sensors and actuators disconnected
					 */
					if (!(listInformationAlteredDisconnected.isEmpty() || listInformationAlteredDisconnected == null)) {
						this.infoDeviceAlteredConnected(listInformationAlteredDisconnected);
					}

				}
			}
		}
	}

	/**
	 * Method responsible for requesting deletion of disconnected devices
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param disconnected
	 *            List<Device> - List devices disconnected
	 */
	public void infoDeviceDisconnected(List<Device> disconnected) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		/* Removing devices disconnected from the local information list */
		for (Device deviceDisc : disconnected) {
			for (Device device : listDevices) {
				if (device.getName().equals(deviceDisc.getName()) && device.getVersion().equals(deviceDisc.getVersion())
						&& device.getManufacturer().equals(deviceDisc.getManufacturer())) {
					listDevices.remove(device);
					break;
				}
			}
		}

		/* Mounting information to be sent */
		gatewaySend.setMac(gatewayInfo.getMac());
		gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
		gatewaySend.getListDevice().addAll(disconnected);

		/* Sending information */
		deviceService.sendListDeviceDisconnected(gatewaySend);

	}

	/**
	 * Method responsible for requesting inclusion of connected devices
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param connected
	 *            List<Devices> - List devices connected
	 */
	public void infoDeviceConnected(List<Device> connected) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		/* Adding devices connected from the local information list */
		listDevices.addAll(connected);

		/* Mounting information to be sent */
		gatewaySend.setMac(gatewayInfo.getMac());
		gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
		gatewaySend.getListDevice().addAll(connected);

		/* Sending information */
		deviceService.sendListDeviceConnected(gatewaySend);

	}

	/**
	 * Method responsible for requesting modification caused by new sensors or
	 * actuators connected to the device
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param altered
	 *            List<Device> - List of devices with new sensors or actuators
	 *            connected
	 */
	public void infoDeviceAlteredConnected(List<Device> altered) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		/* Update information locale */
		for (Device deviceAlter : altered) {
			for (Device device : listDevices) {
				if (device.getName().equals(deviceAlter.getName())
						&& device.getVersion().equals(deviceAlter.getVersion())
						&& device.getManufacturer().equals(deviceAlter.getManufacturer())) {

					/* Adding new sensors to local list devices */
					for (Sensor newSensor : deviceAlter.getListSensor()) {
						device.getListSensor().add(newSensor);
					}

					/* Adding new actuator to local list devices */
					for (Actuator newActuador : deviceAlter.getListActuator()) {
						device.getListActuator().add(newActuador);
					}
					break;
				}
			}
		}

		/* Mounting information to be sent */
		gatewaySend.setMac(gatewayInfo.getMac());
		gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
		gatewaySend.getListDevice().addAll(altered);

		/* Sending information */
		deviceService.sendDeviceInformationAlteredConnected(gatewaySend);

	}

	/**
	 * Method responsible for requesting modification caused by sensors or
	 * actuators disconnected from the device
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param altered
	 *            List<Device> - List device altered
	 */
	public void infoDeviceAlteredDisconnected(List<Device> altered) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		/* Update information locale */
		for (Device deviceAlter : altered) {
			for (Device device : listDevices) {
				if (device.getName().equals(deviceAlter.getName())
						&& device.getVersion().equals(deviceAlter.getVersion())
						&& device.getManufacturer().equals(deviceAlter.getManufacturer())) {

					/* Adding new sensors to local list devices */
					for (Sensor newSensor : deviceAlter.getListSensor()) {
						device.getListSensor().remove(newSensor);
					}

					/* Adding new actuator to local list devices */
					for (Actuator newActuador : deviceAlter.getListActuator()) {
						device.getListActuator().remove(newActuador);
					}
					break;
				}
			}
		}

		/* Mounting information to be sent */
		gatewaySend.setMac(gatewayInfo.getMac());
		gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
		gatewaySend.getListDevice().addAll(altered);

		/* Sending information */
		deviceService.sendDeviceInformationAlteredConnected(gatewaySend);

	}

	/**
	 * Auxiliary method for comparing two list of devices. Used to find new
	 * devices and disconnected devices
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param l1
	 *            List<Device>, l2 List<Device>
	 * @return List<Device> - Returns elements of the first list that does not
	 *         exist in the second - new elements
	 */
	public List<Device> compareToDevices(List<Device> l1, List<Device> l2) {
		List<Device> result = new ArrayList<Device>();

		result.addAll(l1);

		result.removeAll(l2);

		return result;
	}

	/**
	 * Auxiliary method for comparing sensors and actuators of two device lists
	 * by returning devices (with sensors and actuators) existing in the first
	 * list and not existing in the second
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param l1
	 *            List<Device>, l2 List<Device>
	 * @return List<Device> - Returns only the devices in the first list that
	 *         have sensors or actuators that are not in the second list
	 */
	public List<Device> compareToInformationAlteredSensorActuator(List<Device> l1, List<Device> l2) {
		List<Device> result = new ArrayList<Device>();
		boolean exist;

		/*
		 * Iteration of the list of devices that will be verified the existence
		 * of sensors and actuators
		 */
		for (Device d1 : l1) {
			/* Result list */
			Device deviceResult = new Device();
			/*
			 * Indicates the existence of sensors or actuators that should be
			 * returned
			 */
			exist = false;

			/* Iteration of the list that will be used for comparison */
			for (Device d2 : l2) {
				/* Conditional to locate the corresponding device */
				if (d1.getName().equals(d2.getName()) && d1.getVersion().equals(d2.getVersion())
						&& d1.getManufacturer().equals(d2.getManufacturer())) {

					/*
					 * Composition of device information that can be added to
					 * the return list
					 */
					deviceResult.setName(d1.getName());
					deviceResult.setManufacturer(d1.getManufacturer());
					deviceResult.setVersion(d1.getVersion());
					deviceResult.setLocation(d1.getLocation());
					deviceResult.setDescriptionSemantic(d1.getDescriptionSemantic());
					deviceResult.getListSensor().addAll(d1.getListSensor());
					deviceResult.getListActuator().addAll(d1.getListActuator());

					/*
					 * Stores information from existing sensors and actuators in
					 * the first list and does not exist on the second
					 */
					deviceResult.getListSensor().removeAll(d2.getListSensor());
					deviceResult.getListActuator().removeAll(d2.getListActuator());

					/* Conditional that verifies the existence of result */
					if (!(deviceResult.getListSensor().isEmpty())) {
						exist = true;
					}

					if (!(deviceResult.getListActuator().isEmpty())) {
						exist = true;
					}
					break;
				}
			}
			/*
			 * Assigns the device that has information from sensors or actuators
			 * to be returned
			 */
			if (exist) {
				result.add(deviceResult);
			}
		}

		return result;
	}

}
