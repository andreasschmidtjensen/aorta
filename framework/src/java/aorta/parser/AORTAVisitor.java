// Generated from /Users/asj/Dropbox/code/phd/aorta/framework/src/java/aorta/parser/AORTA.g4 by ANTLR 4.1
package aorta.parser;

import alice.tuprolog.Number;
import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import alice.tuprolog.Prolog;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.Var;

import aorta.kr.*;
import aorta.kr.language.*;
import aorta.parser.helper.*;
import aorta.reasoning.*;
import aorta.reasoning.action.*;
import aorta.reasoning.fml.*;

import java.io.IOException;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AORTAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AORTAVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AORTAParser#struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct(@NotNull AORTAParser.StructContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#listItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListItem(@NotNull AORTAParser.ListItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(@NotNull AORTAParser.VarContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#formulas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormulas(@NotNull AORTAParser.FormulasContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#ifRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfRule(@NotNull AORTAParser.IfRuleContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(@NotNull AORTAParser.RulesContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#prolog2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProlog2(@NotNull AORTAParser.Prolog2Context ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(@NotNull AORTAParser.ListContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#prolog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProlog(@NotNull AORTAParser.PrologContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(@NotNull AORTAParser.ArgsContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListContents(@NotNull AORTAParser.ListContentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull AORTAParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#illForce}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIllForce(@NotNull AORTAParser.IllForceContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#aortaAgent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAortaAgent(@NotNull AORTAParser.AortaAgentContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(@NotNull AORTAParser.FormulaContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(@NotNull AORTAParser.ActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(@NotNull AORTAParser.TermContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#actRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActRule(@NotNull AORTAParser.ActRuleContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(@NotNull AORTAParser.AtomContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(@NotNull AORTAParser.OptionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AORTAParser#termBuilder}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermBuilder(@NotNull AORTAParser.TermBuilderContext ctx);
}