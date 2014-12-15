// Generated from /Users/asj/Dropbox/code/phd/aorta/metamodel/src/java/aorta/kr/language/parser/MetamodelParser.g4 by ANTLR 4.1
package aorta.kr.language.parser;

	import alice.tuprolog.Number;
	import alice.tuprolog.Struct;
	import alice.tuprolog.Prolog;
	import alice.tuprolog.Term;
	import alice.tuprolog.Theory;
	import alice.tuprolog.Var;

	import aorta.kr.language.model.*;
	
	import java.util.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MetamodelParser}.
 */
public interface MetamodelParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MetamodelParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(@NotNull MetamodelParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(@NotNull MetamodelParser.StructContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#metamodel}.
	 * @param ctx the parse tree
	 */
	void enterMetamodel(@NotNull MetamodelParser.MetamodelContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#metamodel}.
	 * @param ctx the parse tree
	 */
	void exitMetamodel(@NotNull MetamodelParser.MetamodelContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#role}.
	 * @param ctx the parse tree
	 */
	void enterRole(@NotNull MetamodelParser.RoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#role}.
	 * @param ctx the parse tree
	 */
	void exitRole(@NotNull MetamodelParser.RoleContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull MetamodelParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull MetamodelParser.StringContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#roles}.
	 * @param ctx the parse tree
	 */
	void enterRoles(@NotNull MetamodelParser.RolesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#roles}.
	 * @param ctx the parse tree
	 */
	void exitRoles(@NotNull MetamodelParser.RolesContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#obligations}.
	 * @param ctx the parse tree
	 */
	void enterObligations(@NotNull MetamodelParser.ObligationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#obligations}.
	 * @param ctx the parse tree
	 */
	void exitObligations(@NotNull MetamodelParser.ObligationsContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(@NotNull MetamodelParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(@NotNull MetamodelParser.RulesContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#prolog2}.
	 * @param ctx the parse tree
	 */
	void enterProlog2(@NotNull MetamodelParser.Prolog2Context ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#prolog2}.
	 * @param ctx the parse tree
	 */
	void exitProlog2(@NotNull MetamodelParser.Prolog2Context ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#objectiveList}.
	 * @param ctx the parse tree
	 */
	void enterObjectiveList(@NotNull MetamodelParser.ObjectiveListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#objectiveList}.
	 * @param ctx the parse tree
	 */
	void exitObjectiveList(@NotNull MetamodelParser.ObjectiveListContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#objective}.
	 * @param ctx the parse tree
	 */
	void enterObjective(@NotNull MetamodelParser.ObjectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#objective}.
	 * @param ctx the parse tree
	 */
	void exitObjective(@NotNull MetamodelParser.ObjectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#listContents}.
	 * @param ctx the parse tree
	 */
	void enterListContents(@NotNull MetamodelParser.ListContentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#listContents}.
	 * @param ctx the parse tree
	 */
	void exitListContents(@NotNull MetamodelParser.ListContentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull MetamodelParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull MetamodelParser.NumberContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull MetamodelParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull MetamodelParser.TermContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#listItem}.
	 * @param ctx the parse tree
	 */
	void enterListItem(@NotNull MetamodelParser.ListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#listItem}.
	 * @param ctx the parse tree
	 */
	void exitListItem(@NotNull MetamodelParser.ListItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#dependency}.
	 * @param ctx the parse tree
	 */
	void enterDependency(@NotNull MetamodelParser.DependencyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#dependency}.
	 * @param ctx the parse tree
	 */
	void exitDependency(@NotNull MetamodelParser.DependencyContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(@NotNull MetamodelParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(@NotNull MetamodelParser.VarContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull MetamodelParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull MetamodelParser.ListContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#prolog}.
	 * @param ctx the parse tree
	 */
	void enterProlog(@NotNull MetamodelParser.PrologContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#prolog}.
	 * @param ctx the parse tree
	 */
	void exitProlog(@NotNull MetamodelParser.PrologContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#dependencies}.
	 * @param ctx the parse tree
	 */
	void enterDependencies(@NotNull MetamodelParser.DependenciesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#dependencies}.
	 * @param ctx the parse tree
	 */
	void exitDependencies(@NotNull MetamodelParser.DependenciesContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(@NotNull MetamodelParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(@NotNull MetamodelParser.ArgsContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#krrule}.
	 * @param ctx the parse tree
	 */
	void enterKrrule(@NotNull MetamodelParser.KrruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#krrule}.
	 * @param ctx the parse tree
	 */
	void exitKrrule(@NotNull MetamodelParser.KrruleContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#obligation}.
	 * @param ctx the parse tree
	 */
	void enterObligation(@NotNull MetamodelParser.ObligationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#obligation}.
	 * @param ctx the parse tree
	 */
	void exitObligation(@NotNull MetamodelParser.ObligationContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#objectives}.
	 * @param ctx the parse tree
	 */
	void enterObjectives(@NotNull MetamodelParser.ObjectivesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#objectives}.
	 * @param ctx the parse tree
	 */
	void exitObjectives(@NotNull MetamodelParser.ObjectivesContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull MetamodelParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull MetamodelParser.AtomContext ctx);

	/**
	 * Enter a parse tree produced by {@link MetamodelParser#termBuilder}.
	 * @param ctx the parse tree
	 */
	void enterTermBuilder(@NotNull MetamodelParser.TermBuilderContext ctx);
	/**
	 * Exit a parse tree produced by {@link MetamodelParser#termBuilder}.
	 * @param ctx the parse tree
	 */
	void exitTermBuilder(@NotNull MetamodelParser.TermBuilderContext ctx);
}