import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import jas.rai.exprs.*;
import jas.rai.conditions.*;

public class BuildExpr extends RelationalAlgebraBaseListener {
	
	private RAExpr expr;
	private Stack<RAExpr> exprPart = new Stack<>();
	private Stack<Condition> conditions = new Stack<>();
	private Stack<Term> terms = new Stack<>();
	private List<String> attributes = new ArrayList<>();
	private Map<String,String> subst = new HashMap<>();
	
	@Override
	public void enterBaseRelation(RelationalAlgebraParser.BaseRelationContext ctx) {
		RAExpr base = new Base(ctx.getChild(0).getText());
		exprPart.push(base);
	}
	
	@Override
	public void enterTerm(RelationalAlgebraParser.TermContext ctx) {
		// Get the terms in the condition(s)
		boolean constant;
		if (ctx.getChild(0).getText().equals("'")) {
			constant = true;
			Term term = new Term(ctx.getChild(1).getText(), constant);
			terms.push(term);
		} else {
			constant = false;
			Term term = new Term(ctx.getChild(0).getText(), constant);
			terms.push(term);
		}
	}
	
	@Override
	public void enterAttributes(RelationalAlgebraParser.AttributesContext ctx) {
		// Get the attributes for projection
		for (int i = 0; i < ctx.getChildCount(); i++) {
			if (ctx.getChild(i).getText().equals(",")) {
				continue;
			} else {
				attributes.add(ctx.getChild(i).getText());
			}
		}
	}
	
	@Override
	public void enterSubst(RelationalAlgebraParser.SubstContext ctx) {
		// Get the substitutions for renaming
		String key = "";
		String value = "";
		for (int i = 0; i < ctx.getChildCount(); i++) {
			if (ctx.getChild(i).getText().equals("->")) {
				continue;
			} else {
				if (i == 0) {
					key = ctx.getChild(i).getText();
				} else {
					value = ctx.getChild(i).getText();
				}
			}
		}
		subst.put(key, value);
	}
	
	@Override
	public void exitUnion(RelationalAlgebraParser.UnionContext ctx) {
		RAExpr right = getSubExprs();
		RAExpr left = getSubExprs();
		
		RAExpr union = new Union(left, right);
		exprPart.push(union);
	}
	
	@Override
	public void exitUnionMax(RelationalAlgebraParser.UnionMaxContext ctx) {
		RAExpr right = getSubExprs();
		RAExpr left = getSubExprs();
		
		RAExpr unionMax = new UnionMax(left, right);
		exprPart.push(unionMax);
	}
	
	@Override
	public void exitProduct(RelationalAlgebraParser.ProductContext ctx) {
		RAExpr right = getSubExprs();
		RAExpr left = getSubExprs();
		
		RAExpr product = new Product(left, right);
		exprPart.push(product);
	}
	
	@Override
	public void exitIntersection(RelationalAlgebraParser.IntersectionContext ctx) {
		RAExpr right = getSubExprs();
		RAExpr left = getSubExprs();
		
		RAExpr intersect = new Intersect(left, right);
		exprPart.push(intersect);
	}
	
	@Override
	public void exitDifference(RelationalAlgebraParser.DifferenceContext ctx) {
		RAExpr right = getSubExprs();
		RAExpr left = getSubExprs();
		
		RAExpr difference = new Difference(left, right);
		exprPart.push(difference);
	}
	
	@Override
	public void exitEliminate(RelationalAlgebraParser.EliminateContext ctx) {
		RAExpr main = getSubExprs();
		
		RAExpr eliminate = new Eliminate(main);
		exprPart.push(eliminate);
	}
	
	@Override
	public void exitProjection(RelationalAlgebraParser.ProjectionContext ctx) {
		RAExpr main = getSubExprs();
		
		RAExpr project = new Project(main, attributes);
		exprPart.push(project);
	}
	
	@Override
	public void exitRenaming(RelationalAlgebraParser.RenamingContext ctx) {
		RAExpr main = getSubExprs();
		
		RAExpr rename = new Rename(main, subst);
		exprPart.push(rename);
	}
	
	@Override
	public void exitSelection(RelationalAlgebraParser.SelectionContext ctx) {
		RAExpr main = getSubExprs();
		Condition condition = getCond();
		
		RAExpr select = new Select(condition, main);
		exprPart.push(select);
	}
	
	@Override
	public void exitEquality(RelationalAlgebraParser.EqualityContext ctx) {
		Term right = getTerms();
		Term left = getTerms();
		
		Condition cond = new Equality(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitInequality(RelationalAlgebraParser.InequalityContext ctx) {
		Term right = getTerms();
		Term left = getTerms();
		
		Condition cond = new Inequality(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitGreater(RelationalAlgebraParser.GreaterContext ctx) {
		Term right = getTerms();
		Term left = getTerms();
		
		Condition cond = new Greater(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitGreaterEqual(RelationalAlgebraParser.GreaterEqualContext ctx) {
		Term right = getTerms();
		Term left = getTerms();
		
		Condition cond = new GreaterEqual(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitLess(RelationalAlgebraParser.LessContext ctx) {
		Term right = getTerms();
		Term left = getTerms();
		
		Condition cond = new Less(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitLessEqual(RelationalAlgebraParser.LessEqualContext ctx) {
		Term right = getTerms();
		Term left = getTerms();
		
		Condition cond = new LessEqual(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitAnd(RelationalAlgebraParser.AndContext ctx) {
		Condition right = getCond();
		Condition left = getCond();
		
		Condition cond = new And(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitOr(RelationalAlgebraParser.OrContext ctx) {
		Condition right = getCond();
		Condition left = getCond();
		
		Condition cond = new Or(left, right);
		conditions.push(cond);
	}
	
	@Override
	public void exitNot(RelationalAlgebraParser.NotContext ctx) {
		Condition main = getCond();
		
		Condition cond = new Not(main);
		conditions.push(cond);
	}
	
	private Term getTerms() {
		return terms.pop();
	}
	
	private Condition getCond() {
		return conditions.pop();
	}
	
	private RAExpr getSubExprs() {
		return exprPart.pop();
	}
	
	public RAExpr getExpr() {
		expr = exprPart.pop();
		return expr;
	}

}
