// Generated from C:\Dropbox\code\phd\aorta\metamodel\src\java\aorta\kr\language\parser\Metamodel.g4 by ANTLR 4.1
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
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MetamodelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MetamodelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MetamodelParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(@NotNull MetamodelParser.AtomContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#metamodel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetamodel(@NotNull MetamodelParser.MetamodelContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#objective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjective(@NotNull MetamodelParser.ObjectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#termBuilder}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermBuilder(@NotNull MetamodelParser.TermBuilderContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(@NotNull MetamodelParser.ArgsContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(@NotNull MetamodelParser.VarContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListContents(@NotNull MetamodelParser.ListContentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(@NotNull MetamodelParser.ListContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull MetamodelParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#obligations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObligations(@NotNull MetamodelParser.ObligationsContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#prolog2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProlog2(@NotNull MetamodelParser.Prolog2Context ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(@NotNull MetamodelParser.RulesContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#listItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListItem(@NotNull MetamodelParser.ListItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#dependency}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependency(@NotNull MetamodelParser.DependencyContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#prolog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProlog(@NotNull MetamodelParser.PrologContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#obligation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObligation(@NotNull MetamodelParser.ObligationContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#objectives}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectives(@NotNull MetamodelParser.ObjectivesContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(@NotNull MetamodelParser.TermContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#dependencies}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependencies(@NotNull MetamodelParser.DependenciesContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct(@NotNull MetamodelParser.StructContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#roles}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoles(@NotNull MetamodelParser.RolesContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#role}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRole(@NotNull MetamodelParser.RoleContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#objectiveList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectiveList(@NotNull MetamodelParser.ObjectiveListContext ctx);

	/**
	 * Visit a parse tree produced by {@link MetamodelParser#krrule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKrrule(@NotNull MetamodelParser.KrruleContext ctx);
}