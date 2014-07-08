// Generated from /Users/asj/Dropbox/code/phd/AORTA/Aorta/src/java/aorta/parser/AORTA.g4 by ANTLR 4.1

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
import aorta.reasoning.action.act.*;
import aorta.reasoning.action.coord.*;
import aorta.reasoning.action.opt.*;
import aorta.reasoning.coordination.*;
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
	 * Enter a parse tree produced by {@link AORTAParser#opts}.
	 * @param ctx the parse tree
	 */
	void enterOpts(@NotNull AORTAParser.OptsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#opts}.
	 * @param ctx the parse tree
	 */
	void exitOpts(@NotNull AORTAParser.OptsContext ctx);

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
	 * Enter a parse tree produced by {@link AORTAParser#acts}.
	 * @param ctx the parse tree
	 */
	void enterActs(@NotNull AORTAParser.ActsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#acts}.
	 * @param ctx the parse tree
	 */
	void exitActs(@NotNull AORTAParser.ActsContext ctx);

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
	 * Enter a parse tree produced by {@link AORTAParser#change}.
	 * @param ctx the parse tree
	 */
	void enterChange(@NotNull AORTAParser.ChangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#change}.
	 * @param ctx the parse tree
	 */
	void exitChange(@NotNull AORTAParser.ChangeContext ctx);

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
	 * Enter a parse tree produced by {@link AORTAParser#coord}.
	 * @param ctx the parse tree
	 */
	void enterCoord(@NotNull AORTAParser.CoordContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#coord}.
	 * @param ctx the parse tree
	 */
	void exitCoord(@NotNull AORTAParser.CoordContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#optAction}.
	 * @param ctx the parse tree
	 */
	void enterOptAction(@NotNull AORTAParser.OptActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#optAction}.
	 * @param ctx the parse tree
	 */
	void exitOptAction(@NotNull AORTAParser.OptActionContext ctx);

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
	 * Enter a parse tree produced by {@link AORTAParser#actionRules}.
	 * @param ctx the parse tree
	 */
	void enterActionRules(@NotNull AORTAParser.ActionRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#actionRules}.
	 * @param ctx the parse tree
	 */
	void exitActionRules(@NotNull AORTAParser.ActionRulesContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#opt}.
	 * @param ctx the parse tree
	 */
	void enterOpt(@NotNull AORTAParser.OptContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#opt}.
	 * @param ctx the parse tree
	 */
	void exitOpt(@NotNull AORTAParser.OptContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#coordinationRules}.
	 * @param ctx the parse tree
	 */
	void enterCoordinationRules(@NotNull AORTAParser.CoordinationRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#coordinationRules}.
	 * @param ctx the parse tree
	 */
	void exitCoordinationRules(@NotNull AORTAParser.CoordinationRulesContext ctx);

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

	/**
	 * Enter a parse tree produced by {@link AORTAParser#strategy}.
	 * @param ctx the parse tree
	 */
	void enterStrategy(@NotNull AORTAParser.StrategyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#strategy}.
	 * @param ctx the parse tree
	 */
	void exitStrategy(@NotNull AORTAParser.StrategyContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(@NotNull AORTAParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(@NotNull AORTAParser.InitContext ctx);

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
	 * Enter a parse tree produced by {@link AORTAParser#coords}.
	 * @param ctx the parse tree
	 */
	void enterCoords(@NotNull AORTAParser.CoordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#coords}.
	 * @param ctx the parse tree
	 */
	void exitCoords(@NotNull AORTAParser.CoordsContext ctx);

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
	 * Enter a parse tree produced by {@link AORTAParser#optionRules}.
	 * @param ctx the parse tree
	 */
	void enterOptionRules(@NotNull AORTAParser.OptionRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#optionRules}.
	 * @param ctx the parse tree
	 */
	void exitOptionRules(@NotNull AORTAParser.OptionRulesContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#actAction}.
	 * @param ctx the parse tree
	 */
	void enterActAction(@NotNull AORTAParser.ActActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#actAction}.
	 * @param ctx the parse tree
	 */
	void exitActAction(@NotNull AORTAParser.ActActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#act}.
	 * @param ctx the parse tree
	 */
	void enterAct(@NotNull AORTAParser.ActContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#act}.
	 * @param ctx the parse tree
	 */
	void exitAct(@NotNull AORTAParser.ActContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#changes}.
	 * @param ctx the parse tree
	 */
	void enterChanges(@NotNull AORTAParser.ChangesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#changes}.
	 * @param ctx the parse tree
	 */
	void exitChanges(@NotNull AORTAParser.ChangesContext ctx);

	/**
	 * Enter a parse tree produced by {@link AORTAParser#sendAction}.
	 * @param ctx the parse tree
	 */
	void enterSendAction(@NotNull AORTAParser.SendActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AORTAParser#sendAction}.
	 * @param ctx the parse tree
	 */
	void exitSendAction(@NotNull AORTAParser.SendActionContext ctx);

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
}