/*
* generated by Xtext
*/
package eu.scape_project;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class SimulatorUiInjectorProvider implements IInjectorProvider {
	
	public Injector getInjector() {
		return eu.scape_project.ui.internal.SimulatorActivator.getInstance().getInjector("eu.scape_project.Simulator");
	}
	
}