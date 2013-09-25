/*
 * generated by Xtext
 */
package eu.scape_project.pw.generator

import com.google.inject.Inject
import eu.scape_project.pw.simulator.ConditionalScheduling
import eu.scape_project.pw.simulator.EExpression
import eu.scape_project.pw.simulator.Event
import eu.scape_project.pw.simulator.Expression
import eu.scape_project.pw.simulator.MExpression
import eu.scape_project.pw.simulator.OExpression
import eu.scape_project.pw.simulator.PExpression
import eu.scape_project.pw.simulator.RExpression
import eu.scape_project.pw.simulator.Simulation
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.xbase.compiler.XbaseCompiler

class SimulatorGenerator implements IGenerator {

	@Inject
	protected XbaseCompiler xbaseCompiler
	
	InitializatorGenerator iGenerator;
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		iGenerator = new InitializatorGenerator()
		for (e : resource.allContents.toIterable.filter(typeof(Simulation))) {
			fsa.generateFile("/simulator/" + e.name + ".java", e.createMain)
		}
		iGenerator.generateInitializator(resource,fsa);
		
		for (e : resource.allContents.toIterable.filter(typeof(Event))) {
			fsa.generateFile("/simulator/" + e.name + ".java", e.compileEvent)
		}
		
		for (e : resource.allContents.toIterable.filter(typeof(ConditionalScheduling))) {
			fsa.generateFile("/simulator/" + e.observes.name + "2" + e.schedule.name + ".java", e.compileConditionalScheduling)
		}
		

	}

	/**
	 * generate main file 
	 */
	def createMain(Simulation s) '''
		
		package simulator;
		import eu.scape_project.*;
		public class «s.name» { 
			public static void main(String[] args) {
				EventProcessor processor = new EventProcessor();
				Initializator initializator = new Initializator();
				processor.setEventContainer(initializator.getEventContainer());
				processor.setEOContainer(initializator.getEOContainer());
				processor.setSimulationState(initializator.getSimulationState());
				processor.startSimulation();
			}
		}
		
	'''
	
	

	
/* 
	def compileConditionalEventSchedulingMain(ConditionalScheduling e) '''
		tmpEvent = new «e.observes.name»2«e.schedule.name»();
		processor.addEventObserver(tmpEvent);
	'''
	*/
	def compileEvent(Event e) '''
		
		package simulator;
		import eu.scape_project.*;
		public class «e.name» extends Event{ 
			 	

			public «e.name»() {
				name = "«e.name»";
			}
	
			@Override
			public void execute(SimulationState state) {
				«compileExpression(e.expression)»
			}
		}
		
	'''	
	
	def compileExpression(Expression e) {
		
		switch e {
			RExpression : compileRExpression(e)
			OExpression : compileOExpression(e)
		}
			
			
	}
	
	def compileRExpression(RExpression r) {
		
		var temp=
			'''
				for (int i=0; i<''' + r.number + '''; i++) {
			'''
		for (e:r.expression) {
			temp = temp + compileExpression(e)
		}
		temp = temp + '''}''' 	
	}
	
	def compileOExpression(OExpression o){
		switch o {
			PExpression : compilePExpression(o)
			MExpression : compileMExpression(o)
			EExpression : compileEExpression(o)
		}
	}
	
	def compilePExpression(PExpression p) {
		var temp ='''state.addStateVariable("'''
		temp = temp + p.leftSide
		temp = temp + '''",'''
		if (iGenerator.getVarType(p.leftSide) == "int"){
			temp = temp + '''((Integer)state.getStateVariable("'''+ p.leftSide +'''")).intValue()'''
		} else if (iGenerator.getVarType(p.leftSide) == "float"){
			temp = temp + '''((Double)state.getStateVariable("'''+ p.leftSide +'''")).doubleValue()'''
		} else if (iGenerator.getVarType(p.leftSide) == "String") {
			temp = temp + '''((String)state.getStateVariable("'''+ p.leftSide +'''"))'''
		}
		temp = temp + '''+''' + p.rightSide.toString + ''');''' + "\n"
		temp
	}
	
	def compileMExpression(MExpression p) {
		var temp ='''state.addStateVariable("'''
		temp = temp + p.leftSide
		temp = temp + '''",'''
		if (iGenerator.getVarType(p.leftSide) == "int"){
			temp = temp + '''((Integer)state.getStateVariable("'''+ p.leftSide +'''")).intValue()'''
		} else if (iGenerator.getVarType(p.leftSide) == "float"){
			temp = temp + '''((Double)state.getStateVariable("'''+ p.leftSide +'''")).doubleValue()'''
		} else if (iGenerator.getVarType(p.leftSide) == "String") {
			temp = temp + '''((String)state.getStateVariable("'''+ p.leftSide +'''"))'''
		}
		temp = temp + '''-''' + p.rightSide.toString + ''');''' + "\n"
		temp
	}
	
	def compileEExpression(EExpression p) {
		var temp ='''state.addStateVariable("'''
		temp = temp + p.leftSide
		temp = temp + '''",'''
		temp = temp + p.rightSide.toString + ''');'''
		temp
	}
	
	def compileConditionalScheduling(ConditionalScheduling e) '''
	
	package simulator;
	import eu.scape_project.*;
	public class «e.observes.name»2«e.schedule.name» extends EventObserver {
		
		public «e.observes.name»2«e.schedule.name» () {
			observedEvent = "«e.observes.name»";
		}
		
		@Override
		public IEvent schedules(SimulationState state) { 
		
		 long currentTime = state.getTime();
		 IEvent tmp = new «e.schedule.name»();
		 tmp.setScheduleTime(currentTime + «e.delay»);
		 return tmp;	
		} 
	} 
	'''
}
