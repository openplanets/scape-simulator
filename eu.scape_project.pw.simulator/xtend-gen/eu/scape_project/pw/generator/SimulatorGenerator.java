/**
 * generated by Xtext
 */
package eu.scape_project.pw.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import eu.scape_project.pw.generator.InitializatorGenerator;
import eu.scape_project.pw.simulator.ConditionalScheduling;
import eu.scape_project.pw.simulator.EExpression;
import eu.scape_project.pw.simulator.Event;
import eu.scape_project.pw.simulator.Expression;
import eu.scape_project.pw.simulator.MExpression;
import eu.scape_project.pw.simulator.OExpression;
import eu.scape_project.pw.simulator.PExpression;
import eu.scape_project.pw.simulator.RExpression;
import eu.scape_project.pw.simulator.Simulation;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class SimulatorGenerator implements IGenerator {
  @Inject
  protected XbaseCompiler xbaseCompiler;
  
  private InitializatorGenerator iGenerator;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    InitializatorGenerator _initializatorGenerator = new InitializatorGenerator();
    this.iGenerator = _initializatorGenerator;
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<Simulation> _filter = Iterables.<Simulation>filter(_iterable, Simulation.class);
    for (final Simulation e : _filter) {
      String _name = e.getName();
      String _plus = ("/simulator/" + _name);
      String _plus_1 = (_plus + ".java");
      CharSequence _createMain = this.createMain(e);
      fsa.generateFile(_plus_1, _createMain);
    }
    this.iGenerator.generateInitializator(resource, fsa);
    TreeIterator<EObject> _allContents_1 = resource.getAllContents();
    Iterable<EObject> _iterable_1 = IteratorExtensions.<EObject>toIterable(_allContents_1);
    Iterable<Event> _filter_1 = Iterables.<Event>filter(_iterable_1, Event.class);
    for (final Event e_1 : _filter_1) {
      String _name_1 = e_1.getName();
      String _plus_2 = ("/simulator/" + _name_1);
      String _plus_3 = (_plus_2 + ".java");
      CharSequence _compileEvent = this.compileEvent(e_1);
      fsa.generateFile(_plus_3, _compileEvent);
    }
    TreeIterator<EObject> _allContents_2 = resource.getAllContents();
    Iterable<EObject> _iterable_2 = IteratorExtensions.<EObject>toIterable(_allContents_2);
    Iterable<ConditionalScheduling> _filter_2 = Iterables.<ConditionalScheduling>filter(_iterable_2, ConditionalScheduling.class);
    for (final ConditionalScheduling e_2 : _filter_2) {
      Event _observes = e_2.getObserves();
      String _name_2 = _observes.getName();
      String _plus_4 = ("/simulator/" + _name_2);
      String _plus_5 = (_plus_4 + "2");
      Event _schedule = e_2.getSchedule();
      String _name_3 = _schedule.getName();
      String _plus_6 = (_plus_5 + _name_3);
      String _plus_7 = (_plus_6 + ".java");
      CharSequence _compileConditionalScheduling = this.compileConditionalScheduling(e_2);
      fsa.generateFile(_plus_7, _compileConditionalScheduling);
    }
  }
  
  /**
   * generate main file
   */
  public CharSequence createMain(final Simulation s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("package simulator;");
    _builder.newLine();
    _builder.append("import eu.scape_project.*;");
    _builder.newLine();
    _builder.append("public class ");
    String _name = s.getName();
    _builder.append(_name, "");
    _builder.append(" { ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("EventProcessor processor = new EventProcessor();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Initializator initializator = new Initializator();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("processor.setEventContainer(initializator.getEventContainer());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("processor.setEOContainer(initializator.getEOContainer());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("processor.setSimulationState(initializator.getSimulationState());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("processor.startSimulation();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  /**
   * def compileConditionalEventSchedulingMain(ConditionalScheduling e) '''
   * tmpEvent = new «e.observes.name»2«e.schedule.name»();
   * processor.addEventObserver(tmpEvent);
   * '''
   */
  public CharSequence compileEvent(final Event e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("package simulator;");
    _builder.newLine();
    _builder.append("import eu.scape_project.*;");
    _builder.newLine();
    _builder.append("public class ");
    String _name = e.getName();
    _builder.append(_name, "");
    _builder.append(" extends Event{ ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t \t");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_1 = e.getName();
    _builder.append(_name_1, "	");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("name = \"");
    String _name_2 = e.getName();
    _builder.append(_name_2, "		");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void execute(SimulationState state) {");
    _builder.newLine();
    _builder.append("\t\t");
    Expression _expression = e.getExpression();
    String _compileExpression = this.compileExpression(_expression);
    _builder.append(_compileExpression, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public String compileExpression(final Expression e) {
    String _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (e instanceof RExpression) {
        final RExpression _rExpression = (RExpression)e;
        _matched=true;
        String _compileRExpression = this.compileRExpression(_rExpression);
        _switchResult = _compileRExpression;
      }
    }
    if (!_matched) {
      if (e instanceof OExpression) {
        final OExpression _oExpression = (OExpression)e;
        _matched=true;
        String _compileOExpression = this.compileOExpression(_oExpression);
        _switchResult = _compileOExpression;
      }
    }
    return _switchResult;
  }
  
  public String compileRExpression(final RExpression r) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("for (int i=0; i<");
      int _number = r.getNumber();
      String _plus = (_builder.toString() + Integer.valueOf(_number));
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("; i++) {");
      _builder_1.newLine();
      String temp = (_plus + _builder_1);
      EList<Expression> _expression = r.getExpression();
      for (final Expression e : _expression) {
        Object _compileExpression = this.compileExpression(e);
        String _plus_1 = (temp + _compileExpression);
        temp = _plus_1;
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("}");
      String _plus_2 = (temp + _builder_2);
      String _temp = temp = _plus_2;
      _xblockexpression = (_temp);
    }
    return _xblockexpression;
  }
  
  public String compileOExpression(final OExpression o) {
    String _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (o instanceof PExpression) {
        final PExpression _pExpression = (PExpression)o;
        _matched=true;
        String _compilePExpression = this.compilePExpression(_pExpression);
        _switchResult = _compilePExpression;
      }
    }
    if (!_matched) {
      if (o instanceof MExpression) {
        final MExpression _mExpression = (MExpression)o;
        _matched=true;
        String _compileMExpression = this.compileMExpression(_mExpression);
        _switchResult = _compileMExpression;
      }
    }
    if (!_matched) {
      if (o instanceof EExpression) {
        final EExpression _eExpression = (EExpression)o;
        _matched=true;
        String _compileEExpression = this.compileEExpression(_eExpression);
        _switchResult = _compileEExpression;
      }
    }
    return _switchResult;
  }
  
  public String compilePExpression(final PExpression p) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("state.addStateVariable(\"");
      String temp = _builder.toString();
      String _leftSide = p.getLeftSide();
      String _plus = (temp + _leftSide);
      temp = _plus;
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("\",");
      String _plus_1 = (temp + _builder_1);
      temp = _plus_1;
      String _leftSide_1 = p.getLeftSide();
      String _varType = this.iGenerator.getVarType(_leftSide_1);
      boolean _equals = Objects.equal(_varType, "int");
      if (_equals) {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("((Integer)state.getStateVariable(\"");
        String _plus_2 = (temp + _builder_2);
        String _leftSide_2 = p.getLeftSide();
        String _plus_3 = (_plus_2 + _leftSide_2);
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("\")).intValue()");
        String _plus_4 = (_plus_3 + _builder_3);
        temp = _plus_4;
      } else {
        String _leftSide_3 = p.getLeftSide();
        String _varType_1 = this.iGenerator.getVarType(_leftSide_3);
        boolean _equals_1 = Objects.equal(_varType_1, "float");
        if (_equals_1) {
          StringConcatenation _builder_4 = new StringConcatenation();
          _builder_4.append("((Double)state.getStateVariable(\"");
          String _plus_5 = (temp + _builder_4);
          String _leftSide_4 = p.getLeftSide();
          String _plus_6 = (_plus_5 + _leftSide_4);
          StringConcatenation _builder_5 = new StringConcatenation();
          _builder_5.append("\")).doubleValue()");
          String _plus_7 = (_plus_6 + _builder_5);
          temp = _plus_7;
        } else {
          String _leftSide_5 = p.getLeftSide();
          String _varType_2 = this.iGenerator.getVarType(_leftSide_5);
          boolean _equals_2 = Objects.equal(_varType_2, "String");
          if (_equals_2) {
            StringConcatenation _builder_6 = new StringConcatenation();
            _builder_6.append("((String)state.getStateVariable(\"");
            String _plus_8 = (temp + _builder_6);
            String _leftSide_6 = p.getLeftSide();
            String _plus_9 = (_plus_8 + _leftSide_6);
            StringConcatenation _builder_7 = new StringConcatenation();
            _builder_7.append("\"))");
            String _plus_10 = (_plus_9 + _builder_7);
            temp = _plus_10;
          }
        }
      }
      StringConcatenation _builder_8 = new StringConcatenation();
      _builder_8.append("+");
      String _plus_11 = (temp + _builder_8);
      int _rightSide = p.getRightSide();
      String _string = Integer.valueOf(_rightSide).toString();
      String _plus_12 = (_plus_11 + _string);
      StringConcatenation _builder_9 = new StringConcatenation();
      _builder_9.append(");");
      String _plus_13 = (_plus_12 + _builder_9);
      String _plus_14 = (_plus_13 + "\n");
      temp = _plus_14;
      _xblockexpression = (temp);
    }
    return _xblockexpression;
  }
  
  public String compileMExpression(final MExpression p) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("state.addStateVariable(\"");
      String temp = _builder.toString();
      String _leftSide = p.getLeftSide();
      String _plus = (temp + _leftSide);
      temp = _plus;
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("\",");
      String _plus_1 = (temp + _builder_1);
      temp = _plus_1;
      String _leftSide_1 = p.getLeftSide();
      String _varType = this.iGenerator.getVarType(_leftSide_1);
      boolean _equals = Objects.equal(_varType, "int");
      if (_equals) {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("((Integer)state.getStateVariable(\"");
        String _plus_2 = (temp + _builder_2);
        String _leftSide_2 = p.getLeftSide();
        String _plus_3 = (_plus_2 + _leftSide_2);
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("\")).intValue()");
        String _plus_4 = (_plus_3 + _builder_3);
        temp = _plus_4;
      } else {
        String _leftSide_3 = p.getLeftSide();
        String _varType_1 = this.iGenerator.getVarType(_leftSide_3);
        boolean _equals_1 = Objects.equal(_varType_1, "float");
        if (_equals_1) {
          StringConcatenation _builder_4 = new StringConcatenation();
          _builder_4.append("((Double)state.getStateVariable(\"");
          String _plus_5 = (temp + _builder_4);
          String _leftSide_4 = p.getLeftSide();
          String _plus_6 = (_plus_5 + _leftSide_4);
          StringConcatenation _builder_5 = new StringConcatenation();
          _builder_5.append("\")).doubleValue()");
          String _plus_7 = (_plus_6 + _builder_5);
          temp = _plus_7;
        } else {
          String _leftSide_5 = p.getLeftSide();
          String _varType_2 = this.iGenerator.getVarType(_leftSide_5);
          boolean _equals_2 = Objects.equal(_varType_2, "String");
          if (_equals_2) {
            StringConcatenation _builder_6 = new StringConcatenation();
            _builder_6.append("((String)state.getStateVariable(\"");
            String _plus_8 = (temp + _builder_6);
            String _leftSide_6 = p.getLeftSide();
            String _plus_9 = (_plus_8 + _leftSide_6);
            StringConcatenation _builder_7 = new StringConcatenation();
            _builder_7.append("\"))");
            String _plus_10 = (_plus_9 + _builder_7);
            temp = _plus_10;
          }
        }
      }
      StringConcatenation _builder_8 = new StringConcatenation();
      _builder_8.append("-");
      String _plus_11 = (temp + _builder_8);
      int _rightSide = p.getRightSide();
      String _string = Integer.valueOf(_rightSide).toString();
      String _plus_12 = (_plus_11 + _string);
      StringConcatenation _builder_9 = new StringConcatenation();
      _builder_9.append(");");
      String _plus_13 = (_plus_12 + _builder_9);
      String _plus_14 = (_plus_13 + "\n");
      temp = _plus_14;
      _xblockexpression = (temp);
    }
    return _xblockexpression;
  }
  
  public String compileEExpression(final EExpression p) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("state.addStateVariable(\"");
      String temp = _builder.toString();
      String _leftSide = p.getLeftSide();
      String _plus = (temp + _leftSide);
      temp = _plus;
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("\",");
      String _plus_1 = (temp + _builder_1);
      temp = _plus_1;
      int _rightSide = p.getRightSide();
      String _string = Integer.valueOf(_rightSide).toString();
      String _plus_2 = (temp + _string);
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append(");");
      String _plus_3 = (_plus_2 + _builder_2);
      temp = _plus_3;
      _xblockexpression = (temp);
    }
    return _xblockexpression;
  }
  
  public CharSequence compileConditionalScheduling(final ConditionalScheduling e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("package simulator;");
    _builder.newLine();
    _builder.append("import eu.scape_project.*;");
    _builder.newLine();
    _builder.append("public class ");
    Event _observes = e.getObserves();
    String _name = _observes.getName();
    _builder.append(_name, "");
    _builder.append("2");
    Event _schedule = e.getSchedule();
    String _name_1 = _schedule.getName();
    _builder.append(_name_1, "");
    _builder.append(" extends EventObserver {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    Event _observes_1 = e.getObserves();
    String _name_2 = _observes_1.getName();
    _builder.append(_name_2, "	");
    _builder.append("2");
    Event _schedule_1 = e.getSchedule();
    String _name_3 = _schedule_1.getName();
    _builder.append(_name_3, "	");
    _builder.append(" () {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("observedEvent = \"");
    Event _observes_2 = e.getObserves();
    String _name_4 = _observes_2.getName();
    _builder.append(_name_4, "		");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public IEvent schedules(SimulationState state) { ");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("long currentTime = state.getTime();");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("IEvent tmp = new ");
    Event _schedule_2 = e.getSchedule();
    String _name_5 = _schedule_2.getName();
    _builder.append(_name_5, "	 ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("tmp.setScheduleTime(currentTime + ");
    int _delay = e.getDelay();
    _builder.append(_delay, "	 ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("return tmp;\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} ");
    _builder.newLine();
    _builder.append("} ");
    _builder.newLine();
    return _builder;
  }
}
