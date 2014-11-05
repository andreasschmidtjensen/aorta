parser grammar MetamodelParser;

@header {
	import alice.tuprolog.Number;
	import alice.tuprolog.Struct;
	import alice.tuprolog.Prolog;
	import alice.tuprolog.Term;
	import alice.tuprolog.Theory;
	import alice.tuprolog.Var;

	import aorta.kr.language.model.*;
	
	import java.util.*;
}

options {   tokenVocab = MetamodelLexer; }

metamodel returns [Metamodel mm] 
	: {
	   List<Dependency> dependencyList = new ArrayList<>();
	   List<Obligation> obligationList = new ArrayList<>();
	   List<Rule> ruleList = new ArrayList<>();
	  }
	roles 
	objectives 
	(dependencies { dependencyList = $dependencies.dependencyList; })? 
	(obligations { obligationList = $obligations.obligationList; })? 
	(rules { ruleList = $rules.ruleList; })? 
	EOF
	{
	$mm = new Metamodel($roles.roleList, $objectives.objList, dependencyList, obligationList, ruleList); 
	};

roles returns [List<Role> roleList] 
	: { $roleList = new ArrayList<>(); }
	ROLES COLON (role { $roleList.add($role.r); })+;
role returns [Role r] : name=ATOM COLON objectiveList FULLSTOP { $r = new Role($name.text, $objectiveList.terms); };
objectiveList returns [List<Term> terms] : 
			{ $terms = new ArrayList<>(); }
		   ( term { $terms.add($term.fml); }
		   | term SEMICOLON ol=objectiveList { $terms.add($term.fml); $terms.addAll($ol.terms); });

objectives returns [List<Objective> objList] 
	: { $objList = new ArrayList<>(); }
	OBJECTIVES COLON (objective { $objList.add($objective.o); })+;
objective returns [Objective o] : prolog FULLSTOP { $o = new Objective($prolog.fml); }
		  | prolog COLON objectiveList FULLSTOP { $o = new Objective($prolog.fml, $objectiveList.terms); };

dependencies returns [List<Dependency> dependencyList] 
	: { $dependencyList = new ArrayList<>(); }
	DEPENDENCIES COLON (dependency { $dependencyList.add($dependency.d); })+;
dependency returns [Dependency d] 
	: dependee=ATOM GT dependant=ATOM COLON prolog FULLSTOP
	  { $d = new Dependency($dependee.text, $dependant.text, $prolog.fml); };

obligations returns [List<Obligation> obligationList] 
	: { $obligationList = new ArrayList<>(); }
	OBLIGATIONS COLON (obligation { $obligationList.add($obligation.o); })+;
obligation returns [Obligation o] 
	: roleName=ATOM COLON obj=prolog LT deadline=prolog PIPE cond=prolog FULLSTOP
	  { $o = new Obligation($roleName.text, $obj.fml, $deadline.fml, $cond.fml); };

rules returns [List<Rule> ruleList] 
	: { $ruleList = new ArrayList<>(); }
	RULES COLON (krrule { $ruleList.add($krrule.r); })+;
krrule returns [Rule r] 
	: struct ENTAILS prolog FULLSTOP
	  { $r = new Rule($struct.fml, $prolog.fml); };

prolog returns [Term fml]: prolog2 { $fml = Term.createTerm($prolog2.text); };
prolog2 returns [Term fml]
	: (COMMA pl=prolog2 { $fml = new Struct(",", $pl.fml); }
	  | SEMICOLON pl=prolog2 { $fml = new Struct(";", $pl.fml); }
	  | START pl=prolog2 END prolog2 { $fml = $pl.fml; }
	  | UNARY_OP pl=prolog2 { $fml = new Struct($UNARY_OP.text, $pl.fml); } 
	  | termBuilder prolog2  { $fml = $termBuilder.fml; }
	  | );
termBuilder returns [Term fml]
	: 
	( t3=term IS t4=term MATH_OP t5=term { $fml = new Struct("is", $t3.fml, new Struct($MATH_OP.text, $t4.fml, $t5.fml)); } 
	| term { $fml = $term.fml; }
	| t1=term BINARY_OP t2=term { $fml = new Struct($BINARY_OP.text, $t1.fml, $t2.fml); } );
term returns [Term fml]
	: ( string {$fml = $string.fml; }
	  | struct {$fml = $struct.fml;}
	  | atom {$fml = $atom.fml;}
	  | var {$fml = $var.fml;}
	  | number {$fml = $number.fml;})
;
string returns [Struct fml]: OPENSTRING STRLIT CLOSESTRING { $fml = new Struct($STRLIT.text); };
atom returns [Struct fml]: ATOM { $fml = new Struct($ATOM.text); };
number returns [Number fml] : NUMBER { String numStr = $NUMBER.text; $fml = new alice.tuprolog.Double(Double.parseDouble(numStr)); };
var returns [Var fml]: VAR { $fml = new Var($VAR.text); };
struct returns [Struct fml]
	: (ATOM START args END { $fml = new Struct($ATOM.text, $args.fml.toArray(new Term[0])); }
	| list  { $fml = $list.fml; });
args returns [List<Term> fml]
	: { $fml = new ArrayList<>(); } 
	(term { $fml.add($term.fml); }
	| term COMMA a=args { $fml.add($term.fml); $fml.addAll($a.fml); } );
list returns [Struct fml]
	: START_BRACKET listContents END_BRACKET { $fml = $listContents.fml; }
	| START_BRACKET t1=term PIPE t2=term END_BRACKET { $fml = new Struct($t1.fml, $t2.fml); };
listContents returns [Struct fml]
	: { $fml = new Struct(); }
	(listItem { $fml.append($listItem.fml); } 
	| listItem COMMA lc=listContents { $fml.append($listItem.fml); $fml.append($lc.fml); });
listItem returns [Term fml]: prolog2 { $fml = $prolog2.fml; };
