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
		RULE_start = 0, RULE_raExpr = 1, RULE_base = 2, RULE_attributes = 3, RULE_subst = 4, 
		RULE_condition = 5, RULE_term = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "raExpr", "base", "attributes", "subst", "condition", "term"
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
			setState(14);
			raExpr(0);
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
		public List<RaExprContext> raExpr() {
			return getRuleContexts(RaExprContext.class);
		}
		public RaExprContext raExpr(int i) {
			return getRuleContext(RaExprContext.class,i);
		}
		public TerminalNode INTERSECTION() { return getToken(RelationalAlgebraParser.INTERSECTION, 0); }
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
	public static class ParenthisisedExprContext extends RaExprContext {
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public RaExprContext raExpr() {
			return getRuleContext(RaExprContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_BRACKET, 0); }
		public ParenthisisedExprContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterParenthisisedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitParenthisisedExpr(this);
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
		public RaExprContext raExpr() {
			return getRuleContext(RaExprContext.class,0);
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
	public static class BaseRelationContext extends RaExprContext {
		public BaseContext base() {
			return getRuleContext(BaseContext.class,0);
		}
		public BaseRelationContext(RaExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).enterBaseRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraListener ) ((RelationalAlgebraListener)listener).exitBaseRelation(this);
		}
	}
	public static class EliminateContext extends RaExprContext {
		public TerminalNode ELIMINATE() { return getToken(RelationalAlgebraParser.ELIMINATE, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public RaExprContext raExpr() {
			return getRuleContext(RaExprContext.class,0);
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
	public static class UnionMaxContext extends RaExprContext {
		public List<RaExprContext> raExpr() {
			return getRuleContexts(RaExprContext.class);
		}
		public RaExprContext raExpr(int i) {
			return getRuleContext(RaExprContext.class,i);
		}
		public TerminalNode UNION_MAX() { return getToken(RelationalAlgebraParser.UNION_MAX, 0); }
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
	public static class ProjectionContext extends RaExprContext {
		public TerminalNode PROJECTION() { return getToken(RelationalAlgebraParser.PROJECTION, 0); }
		public TerminalNode LEFT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_SQUARE_BRACKET, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode RIGHT_SQUARE_BRACKET() { return getToken(RelationalAlgebraParser.RIGHT_SQUARE_BRACKET, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(RelationalAlgebraParser.LEFT_BRACKET, 0); }
		public RaExprContext raExpr() {
			return getRuleContext(RaExprContext.class,0);
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
	public static class ProductContext extends RaExprContext {
		public List<RaExprContext> raExpr() {
			return getRuleContexts(RaExprContext.class);
		}
		public RaExprContext raExpr(int i) {
			return getRuleContext(RaExprContext.class,i);
		}
		public TerminalNode PRODUCT() { return getToken(RelationalAlgebraParser.PRODUCT, 0); }
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
	public static class DifferenceContext extends RaExprContext {
		public List<RaExprContext> raExpr() {
			return getRuleContexts(RaExprContext.class);
		}
		public RaExprContext raExpr(int i) {
			return getRuleContext(RaExprContext.class,i);
		}
		public TerminalNode DIFFERENCE() { return getToken(RelationalAlgebraParser.DIFFERENCE, 0); }
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
		public List<RaExprContext> raExpr() {
			return getRuleContexts(RaExprContext.class);
		}
		public RaExprContext raExpr(int i) {
			return getRuleContext(RaExprContext.class,i);
		}
		public TerminalNode UNION() { return getToken(RelationalAlgebraParser.UNION, 0); }
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
		public RaExprContext raExpr() {
			return getRuleContext(RaExprContext.class,0);
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
		return raExpr(0);
	}

	private RaExprContext raExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RaExprContext _localctx = new RaExprContext(_ctx, _parentState);
		RaExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_raExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				{
				_localctx = new BaseRelationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(17);
				base();
				}
				break;
			case LEFT_BRACKET:
				{
				_localctx = new ParenthisisedExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(18);
				match(LEFT_BRACKET);
				setState(19);
				raExpr(0);
				setState(20);
				match(RIGHT_BRACKET);
				}
				break;
			case PROJECTION:
				{
				_localctx = new ProjectionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				match(PROJECTION);
				setState(23);
				match(LEFT_SQUARE_BRACKET);
				setState(24);
				attributes();
				setState(25);
				match(RIGHT_SQUARE_BRACKET);
				setState(26);
				match(LEFT_BRACKET);
				setState(27);
				raExpr(0);
				setState(28);
				match(RIGHT_BRACKET);
				}
				break;
			case RENAMING:
				{
				_localctx = new RenamingContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(30);
				match(RENAMING);
				setState(31);
				match(LEFT_SQUARE_BRACKET);
				setState(32);
				subst();
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(33);
					match(T__0);
					setState(34);
					subst();
					}
					}
					setState(39);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(40);
				match(RIGHT_SQUARE_BRACKET);
				setState(41);
				match(LEFT_BRACKET);
				setState(42);
				raExpr(0);
				setState(43);
				match(RIGHT_BRACKET);
				}
				break;
			case ELIMINATE:
				{
				_localctx = new EliminateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				match(ELIMINATE);
				setState(46);
				match(LEFT_BRACKET);
				setState(47);
				raExpr(0);
				setState(48);
				match(RIGHT_BRACKET);
				}
				break;
			case SELECT:
				{
				_localctx = new SelectionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				match(SELECT);
				setState(51);
				match(LEFT_SQUARE_BRACKET);
				setState(52);
				condition();
				setState(53);
				match(RIGHT_SQUARE_BRACKET);
				setState(54);
				match(LEFT_BRACKET);
				setState(55);
				raExpr(0);
				setState(56);
				match(RIGHT_BRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(77);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(75);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new UnionContext(new RaExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_raExpr);
						setState(60);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(61);
						match(UNION);
						setState(62);
						raExpr(10);
						}
						break;
					case 2:
						{
						_localctx = new UnionMaxContext(new RaExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_raExpr);
						setState(63);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(64);
						match(UNION_MAX);
						setState(65);
						raExpr(9);
						}
						break;
					case 3:
						{
						_localctx = new ProductContext(new RaExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_raExpr);
						setState(66);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(67);
						match(PRODUCT);
						setState(68);
						raExpr(8);
						}
						break;
					case 4:
						{
						_localctx = new IntersectionContext(new RaExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_raExpr);
						setState(69);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(70);
						match(INTERSECTION);
						setState(71);
						raExpr(7);
						}
						break;
					case 5:
						{
						_localctx = new DifferenceContext(new RaExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_raExpr);
						setState(72);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(73);
						match(DIFFERENCE);
						setState(74);
						raExpr(6);
						}
						break;
					}
					} 
				}
				setState(79);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
		enterRule(_localctx, 4, RULE_base);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
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
		enterRule(_localctx, 6, RULE_attributes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(NAME);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(83);
				match(T__0);
				setState(84);
				match(NAME);
				}
				}
				setState(89);
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
		enterRule(_localctx, 8, RULE_subst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(NAME);
			setState(91);
			match(T__1);
			setState(92);
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
		enterRule(_localctx, 10, RULE_condition);
		try {
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new EqualityContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				term();
				setState(95);
				match(EQUALITY);
				setState(96);
				term();
				}
				break;
			case 2:
				_localctx = new InequalityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				term();
				setState(99);
				match(INEQUALITY);
				setState(100);
				term();
				}
				break;
			case 3:
				_localctx = new LessContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				term();
				setState(103);
				match(LESS);
				setState(104);
				term();
				}
				break;
			case 4:
				_localctx = new LessEqualContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(106);
				term();
				setState(107);
				match(LESS_EQUAL);
				setState(108);
				term();
				}
				break;
			case 5:
				_localctx = new GreaterContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(110);
				term();
				setState(111);
				match(GREATER);
				setState(112);
				term();
				}
				break;
			case 6:
				_localctx = new GreaterEqualContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				term();
				setState(115);
				match(GREATER_EQUAL);
				setState(116);
				term();
				}
				break;
			case 7:
				_localctx = new AndContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(118);
				match(LEFT_BRACKET);
				setState(119);
				condition();
				setState(120);
				match(RIGHT_BRACKET);
				setState(121);
				match(AND);
				setState(122);
				match(LEFT_BRACKET);
				setState(123);
				condition();
				setState(124);
				match(RIGHT_BRACKET);
				}
				break;
			case 8:
				_localctx = new OrContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(126);
				match(LEFT_BRACKET);
				setState(127);
				condition();
				setState(128);
				match(RIGHT_BRACKET);
				setState(129);
				match(OR);
				setState(130);
				match(LEFT_BRACKET);
				setState(131);
				condition();
				setState(132);
				match(RIGHT_BRACKET);
				}
				break;
			case 9:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(134);
				match(NOT);
				setState(135);
				match(LEFT_BRACKET);
				setState(136);
				condition();
				setState(137);
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
		enterRule(_localctx, 12, RULE_term);
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(NAME);
				}
				break;
			case SINGLE_QUOTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(SINGLE_QUOTE);
				setState(143);
				match(NAME);
				setState(144);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return raExpr_sempred((RaExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean raExpr_sempred(RaExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u0096\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3&\n"+
		"\3\f\3\16\3)\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3=\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3Q\13\3\3\4\3\4\3\5\3\5\3\5\7\5X"+
		"\n\5\f\5\16\5[\13\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7\u008e\n\7\3\b\3\b\3\b\3\b\5\b\u0094\n\b\3\b\2\3\4\t\2\4\6\b"+
		"\n\f\16\2\2\2\u00a3\2\20\3\2\2\2\4<\3\2\2\2\6R\3\2\2\2\bT\3\2\2\2\n\\"+
		"\3\2\2\2\f\u008d\3\2\2\2\16\u0093\3\2\2\2\20\21\5\4\3\2\21\3\3\2\2\2\22"+
		"\23\b\3\1\2\23=\5\6\4\2\24\25\7\5\2\2\25\26\5\4\3\2\26\27\7\6\2\2\27="+
		"\3\2\2\2\30\31\7\13\2\2\31\32\7\7\2\2\32\33\5\b\5\2\33\34\7\b\2\2\34\35"+
		"\7\5\2\2\35\36\5\4\3\2\36\37\7\6\2\2\37=\3\2\2\2 !\7\f\2\2!\"\7\7\2\2"+
		"\"\'\5\n\6\2#$\7\3\2\2$&\5\n\6\2%#\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2"+
		"\2\2(*\3\2\2\2)\'\3\2\2\2*+\7\b\2\2+,\7\5\2\2,-\5\4\3\2-.\7\6\2\2.=\3"+
		"\2\2\2/\60\7\22\2\2\60\61\7\5\2\2\61\62\5\4\3\2\62\63\7\6\2\2\63=\3\2"+
		"\2\2\64\65\7\23\2\2\65\66\7\7\2\2\66\67\5\f\7\2\678\7\b\2\289\7\5\2\2"+
		"9:\5\4\3\2:;\7\6\2\2;=\3\2\2\2<\22\3\2\2\2<\24\3\2\2\2<\30\3\2\2\2< \3"+
		"\2\2\2</\3\2\2\2<\64\3\2\2\2=O\3\2\2\2>?\f\13\2\2?@\7\16\2\2@N\5\4\3\f"+
		"AB\f\n\2\2BC\7\17\2\2CN\5\4\3\13DE\f\t\2\2EF\7\r\2\2FN\5\4\3\nGH\f\b\2"+
		"\2HI\7\20\2\2IN\5\4\3\tJK\f\7\2\2KL\7\21\2\2LN\5\4\3\bM>\3\2\2\2MA\3\2"+
		"\2\2MD\3\2\2\2MG\3\2\2\2MJ\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\5\3"+
		"\2\2\2QO\3\2\2\2RS\7\n\2\2S\7\3\2\2\2TY\7\n\2\2UV\7\3\2\2VX\7\n\2\2WU"+
		"\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\t\3\2\2\2[Y\3\2\2\2\\]\7\n\2\2"+
		"]^\7\4\2\2^_\7\n\2\2_\13\3\2\2\2`a\5\16\b\2ab\7\26\2\2bc\5\16\b\2c\u008e"+
		"\3\2\2\2de\5\16\b\2ef\7\27\2\2fg\5\16\b\2g\u008e\3\2\2\2hi\5\16\b\2ij"+
		"\7\30\2\2jk\5\16\b\2k\u008e\3\2\2\2lm\5\16\b\2mn\7\31\2\2no\5\16\b\2o"+
		"\u008e\3\2\2\2pq\5\16\b\2qr\7\32\2\2rs\5\16\b\2s\u008e\3\2\2\2tu\5\16"+
		"\b\2uv\7\33\2\2vw\5\16\b\2w\u008e\3\2\2\2xy\7\5\2\2yz\5\f\7\2z{\7\6\2"+
		"\2{|\7\24\2\2|}\7\5\2\2}~\5\f\7\2~\177\7\6\2\2\177\u008e\3\2\2\2\u0080"+
		"\u0081\7\5\2\2\u0081\u0082\5\f\7\2\u0082\u0083\7\6\2\2\u0083\u0084\7\25"+
		"\2\2\u0084\u0085\7\5\2\2\u0085\u0086\5\f\7\2\u0086\u0087\7\6\2\2\u0087"+
		"\u008e\3\2\2\2\u0088\u0089\7\34\2\2\u0089\u008a\7\5\2\2\u008a\u008b\5"+
		"\f\7\2\u008b\u008c\7\6\2\2\u008c\u008e\3\2\2\2\u008d`\3\2\2\2\u008dd\3"+
		"\2\2\2\u008dh\3\2\2\2\u008dl\3\2\2\2\u008dp\3\2\2\2\u008dt\3\2\2\2\u008d"+
		"x\3\2\2\2\u008d\u0080\3\2\2\2\u008d\u0088\3\2\2\2\u008e\r\3\2\2\2\u008f"+
		"\u0094\7\n\2\2\u0090\u0091\7\t\2\2\u0091\u0092\7\n\2\2\u0092\u0094\7\t"+
		"\2\2\u0093\u008f\3\2\2\2\u0093\u0090\3\2\2\2\u0094\17\3\2\2\2\t\'<MOY"+
		"\u008d\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}