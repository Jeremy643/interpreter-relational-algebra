// Generated from RelationalAlgebra.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RelationalAlgebraParser}.
 */
public interface RelationalAlgebraListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(RelationalAlgebraParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(RelationalAlgebraParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Union}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnion(RelationalAlgebraParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Union}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnion(RelationalAlgebraParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnionMax}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnionMax(RelationalAlgebraParser.UnionMaxContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnionMax}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnionMax(RelationalAlgebraParser.UnionMaxContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Product}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterProduct(RelationalAlgebraParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Product}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitProduct(RelationalAlgebraParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Intersection}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterIntersection(RelationalAlgebraParser.IntersectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Intersection}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitIntersection(RelationalAlgebraParser.IntersectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Difference}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterDifference(RelationalAlgebraParser.DifferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Difference}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitDifference(RelationalAlgebraParser.DifferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Projection}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterProjection(RelationalAlgebraParser.ProjectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Projection}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitProjection(RelationalAlgebraParser.ProjectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Renaming}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterRenaming(RelationalAlgebraParser.RenamingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Renaming}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitRenaming(RelationalAlgebraParser.RenamingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Eliminate}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterEliminate(RelationalAlgebraParser.EliminateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Eliminate}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitEliminate(RelationalAlgebraParser.EliminateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Selection}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void enterSelection(RelationalAlgebraParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Selection}
	 * labeled alternative in {@link RelationalAlgebraParser#raExpr}.
	 * @param ctx the parse tree
	 */
	void exitSelection(RelationalAlgebraParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BaseRelation}
	 * labeled alternative in {@link RelationalAlgebraParser#raExprBase}.
	 * @param ctx the parse tree
	 */
	void enterBaseRelation(RelationalAlgebraParser.BaseRelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BaseRelation}
	 * labeled alternative in {@link RelationalAlgebraParser#raExprBase}.
	 * @param ctx the parse tree
	 */
	void exitBaseRelation(RelationalAlgebraParser.BaseRelationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalAlgebraExpr}
	 * labeled alternative in {@link RelationalAlgebraParser#raExprBase}.
	 * @param ctx the parse tree
	 */
	void enterRelationalAlgebraExpr(RelationalAlgebraParser.RelationalAlgebraExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalAlgebraExpr}
	 * labeled alternative in {@link RelationalAlgebraParser#raExprBase}.
	 * @param ctx the parse tree
	 */
	void exitRelationalAlgebraExpr(RelationalAlgebraParser.RelationalAlgebraExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraParser#base}.
	 * @param ctx the parse tree
	 */
	void enterBase(RelationalAlgebraParser.BaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraParser#base}.
	 * @param ctx the parse tree
	 */
	void exitBase(RelationalAlgebraParser.BaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraParser#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(RelationalAlgebraParser.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraParser#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(RelationalAlgebraParser.AttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraParser#subst}.
	 * @param ctx the parse tree
	 */
	void enterSubst(RelationalAlgebraParser.SubstContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraParser#subst}.
	 * @param ctx the parse tree
	 */
	void exitSubst(RelationalAlgebraParser.SubstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterEquality(RelationalAlgebraParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitEquality(RelationalAlgebraParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Inequality}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterInequality(RelationalAlgebraParser.InequalityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Inequality}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitInequality(RelationalAlgebraParser.InequalityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Less}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterLess(RelationalAlgebraParser.LessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Less}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitLess(RelationalAlgebraParser.LessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessEqual}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterLessEqual(RelationalAlgebraParser.LessEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessEqual}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitLessEqual(RelationalAlgebraParser.LessEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Greater}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterGreater(RelationalAlgebraParser.GreaterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Greater}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitGreater(RelationalAlgebraParser.GreaterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterEqual}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterGreaterEqual(RelationalAlgebraParser.GreaterEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterEqual}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitGreaterEqual(RelationalAlgebraParser.GreaterEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterAnd(RelationalAlgebraParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitAnd(RelationalAlgebraParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterOr(RelationalAlgebraParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitOr(RelationalAlgebraParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Not}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterNot(RelationalAlgebraParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link RelationalAlgebraParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitNot(RelationalAlgebraParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(RelationalAlgebraParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(RelationalAlgebraParser.TermContext ctx);
}