package eu.scape_project.pw.simulator.engine.recorder;

import eu.scape_project.pw.simulator.engine.model.ISimulationProperties;
import eu.scape_project.pw.simulator.engine.model.state.ISimulationState;

public interface IRecorder {

	void startSimulation(ISimulationProperties properties);
	
	void startRun(ISimulationState state, int run);
	
	void stopRun(ISimulationState state, int run);
	
	void stopSimulation(ISimulationProperties properties);
	
	void record(ISimulationState state);
	
	void dump();
}
