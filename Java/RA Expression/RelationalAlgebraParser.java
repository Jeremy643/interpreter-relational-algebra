// Generated from RelationalAlgebra.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RelationalAlgebraParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, LEFT_BRACKET=3, RIGHT_BRACKET=4, LEFT_SQUARE_BRACKET=5, 
		RIGHT_SQUARE_BRACKET=6, SINGLE_QUOTE=7, NAME=8, PROJECTION=9, RENAMING=10, 
		PRODUCT=11, UNION=12, UNION_MAX=13, INTERSECTION=14, DIFFERENCE=15, ELIMINATE=16, 
		SELECT=17, AND=18, OR=19, EQUALITY=20, INEQUALITY=21, LESS=22, LESS_EQUAL=23, 
		GREATER=24, GREATER_EQUAL=25, NOT=26, WS=27;
	public static final int
		RULE_start = 0, RULE_raExpr = 1, RULE_raExprBase = 2, RULE_base = 3, RULE_attributes = 4, 
		RULE_subst = 5, RULE_condition = 6, RULE_term = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "raExpr", "raExprBase", "base", "attributes", "subst", "condition", 
			"term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'->'", "'('", "')'", "'['", "']'", "'''", null, "':PROJECT:'", 
			"':RENAME:'", "':PRODUCT:'", "':UPLUS:'", "':UMAX:'", "':INTERSECT:'", 
			"':DIFF:'", "':ELIM:'", "':SELECT:'", "':AND:'", "':OR:'", "':=:'", "':!=:'", 
			"':<:'", "':<=:'", "':>:'", "':>=:'", "':NOT:'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_SQUARE_BRACKET", 
			"RIGHT_SQUARE_BRACKET", "SINGLE_QUOTE", "NAME", "PROJECTION", "RENAMING", 
			"PRODUCT", "UNION", "UNION_MAX", "INTERSECTION", "DIFFERENCE", "ELIMINATE", 
			"SELECT", "AND", "OR", "EQUALITY", "INEQUALITY", "LESS", "LESS_EQUAL", 
			"GREATER", "GREATER_EQUAL", "NOT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RelationalAlgebra.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RelationalAlgebraParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public RaExprContext raExpr() {
			return getRuleContext(RaExprContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			raExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RaExprContext extends ParserRuleContext {
		public RaExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raExpr; }
	 
		public RaExprContext() { }
		public void copyFrom(RaExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntersectionContext extends RaExprContext {
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public List<RaExprBaseContext> raExprBase() {
			return getRuleContexts(RaExprBaseContext.class);
		}
		public RaExprBaseContext raExprBase(int i) {
			return getRuleContext(RaExprBaseContext.class,i);
		}
		public TerminalNode INTERSECTION() { return getToken(RelationalAlgebraParser.INTERSECTION, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public IntersectionContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterIntersection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitIntersection(this);
		}
	}
	public static class SelectionContext extends RaExprContext {
		public TerminalNode SELECT() { return getToken(RelationalAlgebraParser.SELECT, 0); }
		public TerminalNode LEFT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_SQUARE_BRACKET, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RIGHT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_SQUARE_BRACKET, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public RaExprBaseContext raExprBase() {
			return getRuleContext(RaExprBaseContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public SelectionContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterSelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitSelection(this);
		}
	}
	public static class UnionMaxContext extends RaExprContext {
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public List<RaExprBaseContext> raExprBase() {
			return getRuleContexts(RaExprBaseContext.class);
		}
		public RaExprBaseContext raExprBase(int i) {
			return getRuleContext(RaExprBaseContext.class,i);
		}
		public TerminalNode UNION_MAX() { return getToken(RelationalAlgebraParser.UNION_MAX, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public UnionMaxContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterUnionMax(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitUnionMax(this);
		}
	}
	public static class EliminateContext extends RaExprContext {
		public TerminalNode ELIMINATE() { return getToken(RelationalAlgebraParser.ELIMINATE, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public RaExprBaseContext raExprBase() {
			return getRuleContext(RaExprBaseContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public EliminateContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterEliminate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitEliminate(this);
		}
	}
	public static class ProductContext extends RaExprContext {
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public List<RaExprBaseContext> raExprBase() {
			return getRuleContexts(RaExprBaseContext.class);
		}
		public RaExprBaseContext raExprBase(int i) {
			return getRuleContext(RaExprBaseContext.class,i);
		}
		public TerminalNode PRODUCT() { return getToken(RelationalAlgebraParser.PRODUCT, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public ProductContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitProduct(this);
		}
	}
	public static class ProjectionContext extends RaExprContext {
		public TerminalNode PROJECTION() { return getToken(RelationalAlgebraParser.PROJECTION, 0); }
		public TerminalNode LEFT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_SQUARE_BRACKET, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode RIGHT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_SQUARE_BRACKET, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public RaExprBaseContext raExprBase() {
			return getRuleContext(RaExprBaseContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public ProjectionContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterProjection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitProjection(this);
		}
	}
	public static class DifferenceContext extends RaExprContext {
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public List<RaExprBaseContext> raExprBase() {
			return getRuleContexts(RaExprBaseContext.class);
		}
		public RaExprBaseContext raExprBase(int i) {
			return getRuleContext(RaExprBaseContext.class,i);
		}
		public TerminalNode DIFFERENCE() { return getToken(RelationalAlgebraParser.DIFFERENCE, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public DifferenceContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterDifference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitDifference(this);
		}
	}
	public static class UnionContext extends RaExprContext {
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public List<RaExprBaseContext> raExprBase() {
			return getRuleContexts(RaExprBaseContext.class);
		}
		public RaExprBaseContext raExprBase(int i) {
			return getRuleContext(RaExprBaseContext.class,i);
		}
		public TerminalNode UNION() { return getToken(RelationalAlgebraParser.UNION, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public UnionContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitUnion(this);
		}
	}
	public static class RenamingContext extends RaExprContext {
		public TerminalNode RENAMING() { return getToken(RelationalAlgebraParser.RENAMING, 0); }
		public TerminalNode LEFT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_SQUARE_BRACKET, 0); }
		public List<SubstContext> subst() {
			return getRuleContexts(SubstContext.class);
		}
		public SubstContext subst(int i) {
			return getRuleContext(SubstContext.class,i);
		}
		public TerminalNode RIGHT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_SQUARE_BRACKET, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public RaExprBaseContext raExprBase() {
			return getRuleContext(RaExprBaseContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public RenamingContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterRenaming(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitRenaming(this);
		}
	}

	public final RaExprContext raExpr() throws RecognitionException {
		RaExprContext _localctx = new RaExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_raExpr);
		int _la;
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new UnionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				match(LEFT_BRACKET);
				setState(19);
				raExprBase();
				setState(20);
				match(UNION);
				setState(21);
				raExprBase();
				setState(22);
				match(RIGHT_BRACKET);
				}
				break;
			case 2:
				_localctx = new UnionMaxContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				match(LEFT_BRACKET);
				setState(25);
				raExprBase();
				setState(26);
				match(UNION_MAX);
				setState(27);
				raExprBase();
				setState(28);
				match(RIGHT_BRACKET);
				}
				break;
			case 3:
				_localctx = new ProductContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(30);
				match(LEFT_BRACKET);
				setState(31);
				raExprBase();
				setState(32);
				match(PRODUCT);
				setState(33);
				raExprBase();
				setState(34);
				match(RIGHT_BRACKET);
				}
				break;
			case 4:
				_localctx = new IntersectionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(36);
				match(LEFT_BRACKET);
				setState(37);
				raExprBase();
				setState(38);
				match(INTERSECTION);
				setState(39);
				raExprBase();
				setState(40);
				match(RIGHT_BRACKET);
				}
				break;
			case 5:
				_localctx = new DifferenceContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				match(LEFT_BRACKET);
				setState(43);
				raExprBase();
				setState(44);
				match(DIFFERENCE);
				setState(45);
				raExprBase();
				setState(46);
				match(RIGHT_BRACKET);
				}
				break;
			case 6:
				_localctx = new ProjectionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(48);
				match(PROJECTION);
				setState(49);
				match(LEFT_SQUARE_BRACKET);
				setState(50);
				attributes();
				setState(51);
				match(RIGHT_SQUARE_BRACKET);
				setState(52);
				match(LEFT_BRACKET);
				setState(53);
				raExprBase();
				setState(54);
				match(RIGHT_BRACKET);
				}
				break;
			case 7:
				_localctx = new RenamingContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(56);
				match(RENAMING);
				setState(57);
				match(LEFT_SQUARE_BRACKET);
				setState(58);
				subst();
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(59);
					match(T__0);
					setState(60);
					subst();
					}
					}
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(66);
				match(RIGHT_SQUARE_BRACKET);
				setState(67);
				match(LEFT_BRACKET);
				setState(68);
				raExprBase();
				setState(69);
				match(RIGHT_BRACKET);
				}
				break;
			case 8:
				_localctx = new EliminateContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(71);
				match(ELIMINATE);
				setState(72);
				match(LEFT_BRACKET);
				setState(73);
				raExprBase();
				setState(74);
				match(RIGHT_BRACKET);
				}
				break;
			case 9:
				_localctx = new SelectionContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(76);
				match(SELECT);
				setState(77);
				match(LEFT_SQUARE_BRACKET);
				setState(78);
				condition();
				setState(79);
				match(RIGHT_SQUARE_BRACKET);
				setState(80);
				match(LEFT_BRACKET);
				setState(81);
				raExprBase();
				setState(82);
				match(RIGHT_BRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RaExprBaseContext extends ParserRuleContext {
		public RaExprBaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raExprBase; }
	 
		public RaExprBaseContext() { }
		public void copyFrom(RaExprBaseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RelationalAlgebraExprContext extends RaExprBaseContext {
		public RaExprContext raExpr() {
			return getRuleContext(RaExprContext.class,0);
		}
		public RelationalAlgebraExprContext(RaExprBaseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterRelationalAlgebraExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitRelationalAlgebraExpr(this);
		}
	}
	public static class BaseRelationContext extends RaExprBaseContext {
		public BaseContext base() {
			return getRuleContext(BaseContext.class,0);
		}
		public BaseRelationContext(RaExprBaseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterBaseRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitBaseRelation(this);
		}
	}

	public final RaExprBaseContext raExprBase() throws RecognitionException {
		RaExprBaseContext _localctx = new RaExprBaseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_raExprBase);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				_localctx = new BaseRelationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				base();
				}
				break;
			case LEFT_BRACKET:
			case PROJECTION:
			case RENAMING:
			case ELIMINATE:
			case SELECT:
				_localctx = new RelationalAlgebraExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				raExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(RelationalAlgebraParser.NAME, 0); }
		public BaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitBase(this);
		}
	}

	public final BaseContext base() throws RecognitionException {
		BaseContext _localctx = new BaseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_base);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributesContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(RelationalAlgebraParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(RelationalAlgebraParser.NAME, i);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitAttributes(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attributes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(NAME);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(93);
				match(T__0);
				setState(94);
				match(NAME);
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubstContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(RelationalAlgebraParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(RelationalAlgebraParser.NAME, i);
		}
		public SubstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterSubst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitSubst(this);
		}
	}

	public final SubstContext subst() throws RecognitionException {
		SubstContext _localctx = new SubstContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_subst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(NAME);
			setState(101);
			match(T__1);
			setState(102);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GreaterEqualContext extends ConditionContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode GREATER_EQUAL() { return getToken(RelationalAlgebraParser.GREATER_EQUAL, 0); }
		public GreaterEqualContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterGreaterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitGreaterEqual(this);
		}
	}
	public static class NotContext extends ConditionContext {
		public TerminalNode NOT() { return getToken(RelationalAlgebraParser.NOT, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public NotContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitNot(this);
		}
	}
	public static class OrContext extends ConditionContext {
		public List<TerminalNode> LEFT_BRACKET() { return getTokens(RelationalAlgebraParser.LEFT_BRACKET); }
		public TerminalNode LEFT_BRACKET(int i) {
			return getToken(RelationalAlgebraParser.LEFT_BRACKET, i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> RIGHT_BRACKET() { return getTokens(RelationalAlgebraParser.RIGHT_BRACKET); }
		public TerminalNode RIGHT_BRACKET(int i) {
			return getToken(RelationalAlgebraParser.RIGHT_BRACKET, i);
		}
		public TerminalNode OR() { return getToken(RelationalAlgebraParser.OR, 0); }
		public OrContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitOr(this);
		}
	}
	public static class AndContext extends ConditionContext {
		public List<TerminalNode> LEFT_BRACKET() { return getTokens(RelationalAlgebraParser.LEFT_BRACKET); }
		public TerminalNode LEFT_BRACKET(int i) {
			return getToken(RelationalAlgebraParser.LEFT_BRACKET, i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> RIGHT_BRACKET() { return getTokens(RelationalAlgebraParser.RIGHT_BRACKET); }
		public TerminalNode RIGHT_BRACKET(int i) {
			return getToken(RelationalAlgebraParser.RIGHT_BRACKET, i);
		}
		public TerminalNode AND() { return getToken(RelationalAlgebraParser.AND, 0); }
		public AndContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitAnd(this);
		}
	}
	public static class LessEqualContext extends ConditionContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode LESS_EQUAL() { return getToken(RelationalAlgebraParser.LESS_EQUAL, 0); }
		public LessEqualContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterLessEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitLessEqual(this);
		}
	}
	public static class GreaterContext extends ConditionContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(RelationalAlgebraParser.GREATER, 0); }
		public GreaterContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterGreater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitGreater(this);
		}
	}
	public static class EqualityContext extends ConditionContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode EQUALITY() { return getToken(RelationalAlgebraParser.EQUALITY, 0); }
		public EqualityContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitEquality(this);
		}
	}
	public static class LessContext extends ConditionContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode LESS() { return getToken(RelationalAlgebraParser.LESS, 0); }
		public LessContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterLess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitLess(this);
		}
	}
	public static class InequalityContext extends ConditionContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode INEQUALITY() { return getToken(RelationalAlgebraParser.INEQUALITY, 0); }
		public InequalityContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterInequality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitInequality(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_condition);
		try {
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new EqualityContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				term();
				setState(105);
				match(EQUALITY);
				setState(106);
				term();
				}
				break;
			case 2:
				_localctx = new InequalityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				term();
				setState(109);
				match(INEQUALITY);
				setState(110);
				term();
				}
				break;
			case 3:
				_localctx = new LessContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				term();
				setState(113);
				match(LESS);
				setState(114);
				term();
				}
				break;
			case 4:
				_localctx = new LessEqualContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				term();
				setState(117);
				match(LESS_EQUAL);
				setState(118);
				term();
				}
				break;
			case 5:
				_localctx = new GreaterContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
				term();
				setState(121);
				match(GREATER);
				setState(122);
				term();
				}
				break;
			case 6:
				_localctx = new GreaterEqualContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(124);
				term();
				setState(125);
				match(GREATER_EQUAL);
				setState(126);
				term();
				}
				break;
			case 7:
				_localctx = new AndContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(128);
				match(LEFT_BRACKET);
				setState(129);
				condition();
				setState(130);
				match(RIGHT_BRACKET);
				setState(131);
				match(AND);
				setState(132);
				match(LEFT_BRACKET);
				setState(133);
				condition();
				setState(134);
				match(RIGHT_BRACKET);
				}
				break;
			case 8:
				_localctx = new OrContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(136);
				match(LEFT_BRACKET);
				setState(137);
				condition();
				setState(138);
				match(RIGHT_BRACKET);
				setState(139);
				match(OR);
				setState(140);
				match(LEFT_BRACKET);
				setState(141);
				condition();
				setState(142);
				match(RIGHT_BRACKET);
				}
				break;
			case 9:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(144);
				match(NOT);
				setState(145);
				match(LEFT_BRACKET);
				setState(146);
				condition();
				setState(147);
				match(RIGHT_BRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(RelationalAlgebraParser.NAME, 0); }
		public List<TerminalNode> SINGLE_QUOTE() { return getTokens(RelationalAlgebraParser.SINGLE_QUOTE); }
		public TerminalNode SINGLE_QUOTE(int i) {
			return getToken(RelationalAlgebraParser.SINGLE_QUOTE, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(NAME);
				}
				break;
			case SINGLE_QUOTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(SINGLE_QUOTE);
				setState(153);
				match(NAME);
				setState(154);
				match(SINGLE_QUOTE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00a0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3@\n\3\f\3\16\3C\13\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3W\n\3\3\4\3\4"+
		"\5\4[\n\4\3\5\3\5\3\6\3\6\3\6\7\6b\n\6\f\6\16\6e\13\6\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0098\n\b\3\t\3\t\3\t\3\t"+
		"\5\t\u009e\n\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2\u00ab\2\22\3\2\2\2\4"+
		"V\3\2\2\2\6Z\3\2\2\2\b\\\3\2\2\2\n^\3\2\2\2\ff\3\2\2\2\16\u0097\3\2\2"+
		"\2\20\u009d\3\2\2\2\22\23\5\4\3\2\23\3\3\2\2\2\24\25\7\5\2\2\25\26\5\6"+
		"\4\2\26\27\7\16\2\2\27\30\5\6\4\2\30\31\7\6\2\2\31W\3\2\2\2\32\33\7\5"+
		"\2\2\33\34\5\6\4\2\34\35\7\17\2\2\35\36\5\6\4\2\36\37\7\6\2\2\37W\3\2"+
		"\2\2 !\7\5\2\2!\"\5\6\4\2\"#\7\r\2\2#$\5\6\4\2$%\7\6\2\2%W\3\2\2\2&\'"+
		"\7\5\2\2\'(\5\6\4\2()\7\20\2\2)*\5\6\4\2*+\7\6\2\2+W\3\2\2\2,-\7\5\2\2"+
		"-.\5\6\4\2./\7\21\2\2/\60\5\6\4\2\60\61\7\6\2\2\61W\3\2\2\2\62\63\7\13"+
		"\2\2\63\64\7\7\2\2\64\65\5\n\6\2\65\66\7\b\2\2\66\67\7\5\2\2\678\5\6\4"+
		"\289\7\6\2\29W\3\2\2\2:;\7\f\2\2;<\7\7\2\2<A\5\f\7\2=>\7\3\2\2>@\5\f\7"+
		"\2?=\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\b\2"+
		"\2EF\7\5\2\2FG\5\6\4\2GH\7\6\2\2HW\3\2\2\2IJ\7\22\2\2JK\7\5\2\2KL\5\6"+
		"\4\2LM\7\6\2\2MW\3\2\2\2NO\7\23\2\2OP\7\7\2\2PQ\5\16\b\2QR\7\b\2\2RS\7"+
		"\5\2\2ST\5\6\4\2TU\7\6\2\2UW\3\2\2\2V\24\3\2\2\2V\32\3\2\2\2V \3\2\2\2"+
		"V&\3\2\2\2V,\3\2\2\2V\62\3\2\2\2V:\3\2\2\2VI\3\2\2\2VN\3\2\2\2W\5\3\2"+
		"\2\2X[\5\b\5\2Y[\5\4\3\2ZX\3\2\2\2ZY\3\2\2\2[\7\3\2\2\2\\]\7\n\2\2]\t"+
		"\3\2\2\2^c\7\n\2\2_`\7\3\2\2`b\7\n\2\2a_\3\2\2\2be\3\2\2\2ca\3\2\2\2c"+
		"d\3\2\2\2d\13\3\2\2\2ec\3\2\2\2fg\7\n\2\2gh\7\4\2\2hi\7\n\2\2i\r\3\2\2"+
		"\2jk\5\20\t\2kl\7\26\2\2lm\5\20\t\2m\u0098\3\2\2\2no\5\20\t\2op\7\27\2"+
		"\2pq\5\20\t\2q\u0098\3\2\2\2rs\5\20\t\2st\7\30\2\2tu\5\20\t\2u\u0098\3"+
		"\2\2\2vw\5\20\t\2wx\7\31\2\2xy\5\20\t\2y\u0098\3\2\2\2z{\5\20\t\2{|\7"+
		"\32\2\2|}\5\20\t\2}\u0098\3\2\2\2~\177\5\20\t\2\177\u0080\7\33\2\2\u0080"+
		"\u0081\5\20\t\2\u0081\u0098\3\2\2\2\u0082\u0083\7\5\2\2\u0083\u0084\5"+
		"\16\b\2\u0084\u0085\7\6\2\2\u0085\u0086\7\24\2\2\u0086\u0087\7\5\2\2\u0087"+
		"\u0088\5\16\b\2\u0088\u0089\7\6\2\2\u0089\u0098\3\2\2\2\u008a\u008b\7"+
		"\5\2\2\u008b\u008c\5\16\b\2\u008c\u008d\7\6\2\2\u008d\u008e\7\25\2\2\u008e"+
		"\u008f\7\5\2\2\u008f\u0090\5\16\b\2\u0090\u0091\7\6\2\2\u0091\u0098\3"+
		"\2\2\2\u0092\u0093\7\34\2\2\u0093\u0094\7\5\2\2\u0094\u0095\5\16\b\2\u0095"+
		"\u0096\7\6\2\2\u0096\u0098\3\2\2\2\u0097j\3\2\2\2\u0097n\3\2\2\2\u0097"+
		"r\3\2\2\2\u0097v\3\2\2\2\u0097z\3\2\2\2\u0097~\3\2\2\2\u0097\u0082\3\2"+
		"\2\2\u0097\u008a\3\2\2\2\u0097\u0092\3\2\2\2\u0098\17\3\2\2\2\u0099\u009e"+
		"\7\n\2\2\u009a\u009b\7\t\2\2\u009b\u009c\7\n\2\2\u009c\u009e\7\t\2\2\u009d"+
		"\u0099\3\2\2\2\u009d\u009a\3\2\2\2\u009e\21\3\2\2\2\bAVZc\u0097\u009d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}