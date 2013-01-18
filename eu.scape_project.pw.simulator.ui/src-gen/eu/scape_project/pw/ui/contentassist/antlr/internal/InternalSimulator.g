/*
* generated by Xtext
*/
grammar InternalSimulator;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package eu.scape_project.pw.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package eu.scape_project.pw.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import eu.scape_project.pw.services.SimulatorGrammarAccess;

}

@parser::members {
 
 	private SimulatorGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(SimulatorGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }
    
    @Override
    protected String getValueForTokenName(String tokenName) {
    	return tokenName;
    }

}




// Entry rule entryRuleSimulation
entryRuleSimulation 
:
{ before(grammarAccess.getSimulationRule()); }
	 ruleSimulation
{ after(grammarAccess.getSimulationRule()); } 
	 EOF 
;

// Rule Simulation
ruleSimulation
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getSimulationAccess().getGroup()); }
(rule__Simulation__Group__0)
{ after(grammarAccess.getSimulationAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__Simulation__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Simulation__Group__0__Impl
	rule__Simulation__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Simulation__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSimulationAccess().getSimulationKeyword_0()); }

	'Simulation' 

{ after(grammarAccess.getSimulationAccess().getSimulationKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Simulation__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Simulation__Group__1__Impl
	rule__Simulation__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Simulation__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSimulationAccess().getNameAssignment_1()); }
(rule__Simulation__NameAssignment_1)
{ after(grammarAccess.getSimulationAccess().getNameAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Simulation__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Simulation__Group__2__Impl
	rule__Simulation__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Simulation__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSimulationAccess().getLeftCurlyBracketKeyword_2()); }

	'{' 

{ after(grammarAccess.getSimulationAccess().getLeftCurlyBracketKeyword_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Simulation__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Simulation__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Simulation__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSimulationAccess().getRightCurlyBracketKeyword_3()); }

	'}' 

{ after(grammarAccess.getSimulationAccess().getRightCurlyBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}











rule__Simulation__NameAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSimulationAccess().getNameSTRINGTerminalRuleCall_1_0()); }
	RULE_STRING{ after(grammarAccess.getSimulationAccess().getNameSTRINGTerminalRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;

