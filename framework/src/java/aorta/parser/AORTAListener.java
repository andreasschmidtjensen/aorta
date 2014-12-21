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
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AORTAParser}.
 */
public interface AORTAListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AORTAParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(@NotNull AORTAParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(@NotNull AORTAParser.StructContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#listItem}.
	 * @param ctx the parse tree
	 */
	void enterListItem(@NotNull AORTAParser.ListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#listItem}.
	 * @param ctx the parse tree
	 */
	void exitListItem(@NotNull AORTAParser.ListItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(@NotNull AORTAParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(@NotNull AORTAParser.VarContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#formulas}.
	 * @param ctx the parse tree
	 */
	void enterFormulas(@NotNull AORTAParser.FormulasContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#formulas}.
	 * @param ctx the parse tree
	 */
	void exitFormulas(@NotNull AORTAParser.FormulasContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#ifRule}.
	 * @param ctx the parse tree
	 */
	void enterIfRule(@NotNull AORTAParser.IfRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#ifRule}.
	 * @param ctx the parse tree
	 */
	void exitIfRule(@NotNull AORTAParser.IfRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(@NotNull AORTAParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(@NotNull AORTAParser.RulesContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#prolog2}.
	 * @param ctx the parse tree
	 */
	void enterProlog2(@NotNull AORTAParser.Prolog2Context ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#prolog2}.
	 * @param ctx the parse tree
	 */
	void exitProlog2(@NotNull AORTAParser.Prolog2Context ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull AORTAParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull AORTAParser.ListContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#prolog}.
	 * @param ctx the parse tree
	 */
	void enterProlog(@NotNull AORTAParser.PrologContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#prolog}.
	 * @param ctx the parse tree
	 */
	void exitProlog(@NotNull AORTAParser.PrologContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(@NotNull AORTAParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(@NotNull AORTAParser.ArgsContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#listContents}.
	 * @param ctx the parse tree
	 */
	void enterListContents(@NotNull AORTAParser.ListContentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#listContents}.
	 * @param ctx the parse tree
	 */
	void exitListContents(@NotNull AORTAParser.ListContentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull AORTAParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull AORTAParser.NumberContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#illForce}.
	 * @param ctx the parse tree
	 */
	void enterIllForce(@NotNull AORTAParser.IllForceContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#illForce}.
	 * @param ctx the parse tree
	 */
	void exitIllForce(@NotNull AORTAParser.IllForceContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#aortaAgent}.
	 * @param ctx the parse tree
	 */
	void enterAortaAgent(@NotNull AORTAParser.AortaAgentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#aortaAgent}.
	 * @param ctx the parse tree
	 */
	void exitAortaAgent(@NotNull AORTAParser.AortaAgentContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull AORTAParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull AORTAParser.FormulaContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(@NotNull AORTAParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(@NotNull AORTAParser.ActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull AORTAParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull AORTAParser.TermContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#actRule}.
	 * @param ctx the parse tree
	 */
	void enterActRule(@NotNull AORTAParser.ActRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#actRule}.
	 * @param ctx the parse tree
	 */
	void exitActRule(@NotNull AORTAParser.ActRuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull AORTAParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull AORTAParser.AtomContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(@NotNull AORTAParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(@NotNull AORTAParser.OptionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#termBuilder}.
	 * @param ctx the parse tree
	 */
	void enterTermBuilder(@NotNull AORTAParser.TermBuilderContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#termBuilder}.
	 * @param ctx the parse tree
	 */
	void exitTermBuilder(@NotNull AORTAParser.TermBuilderContext ctx);
}