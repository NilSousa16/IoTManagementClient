package org.apache.servicemix.examples.cxf.info;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicemix.examples.cxf.model.Actuator;

/**
 * Class responsible capture information about the actuator
 *
 * @author Nilson Rodrigues Sousa
 */
public class ActuatorInfo {

	/**
	 * Method that returns information from an actuator - PROVISIONAL
	 * IMPLEMENTATION
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Actuator - actuator information
	 */
	public Actuator getActuatorInfo() {
		Actuator actuador = new Actuator();

		actuador.setName("ActuatorBalluff");
		actuador.setManufacturer("Balluff");
		actuador.setVersion("3.1");
		actuador.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		actuador.setAction("Open_Close");

		return actuador;
	}

	/**
	 * Method returns a list of actuators connected to the gateway devices -
	 * PROVISIONAL IMPLEMENTATION
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Actuator> - List of existing actuators
	 */
	public List<Actuator> getListActuator() {

		List<Actuator> listActuator = new ArrayList<Actuator>();

		Actuator actuator = new Actuator();

		actuator.setName("ActuatorBalluff");
		actuator.setManufacturer("Balluff");
		actuator.setVersion("2.2");
		actuator.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		actuator.setAction("Open_Close");

		listActuator.add(actuator);

		actuator = new Actuator();

		actuator.setName("ActuatorSMC");
		actuator.setManufacturer("SMC");
		actuator.setVersion("9.2");
		actuator.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		actuator.setAction("Decreases_Increases");

		listActuator.add(actuator);

		return listActuator;
	}

}
