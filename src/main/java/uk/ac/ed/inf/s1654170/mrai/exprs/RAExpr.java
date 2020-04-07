package uk.ac.ed.inf.s1654170.mrai.exprs;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.parser.BuildExpr;
import uk.ac.ed.inf.s1654170.mrai.parser.ParsingExceptionListener;
import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraLexer;
import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraParser;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public abstract class RAExpr {

	private static final String getName(int i) {
		return RelationalAlgebraLexer.VOCABULARY.getLiteralName(i).replace("'", "");
	}

	enum Type {
		BASE		(""),
		PROJECT		(getName(RelationalAlgebraLexer.PROJECTION)),
		SELECT		(getName(RelationalAlgebraLexer.SELECT)),
		PRODUCT		(getName(RelationalAlgebraLexer.PRODUCT)),
		UNION		(getName(RelationalAlgebraLexer.UNION)),
		UNION_MAX	(getName(RelationalAlgebraLexer.UNION_MAX)),
		INTERSECT	(getName(RelationalAlgebraLexer.INTERSECTION)),
		DIFFERENCE	(getName(RelationalAlgebraLexer.DIFFERENCE)),
		ELIMINATE	(getName(RelationalAlgebraLexer.ELIMINATE)),
		RENAME		(getName(RelationalAlgebraLexer.RENAMING));

		private final String connective;

		Type(String connective) {
			this.connective = connective;
		}

		public String getConnective() {
			return connective;
		}
	}

	private Type type;

	protected RAExpr(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public abstract Signature signature(Schema s) throws SchemaException;

	public boolean validate(Schema schema) throws SchemaException {
		try {
			Signature sign = signature(schema);
			if (sign != null) {
				return true;
			} else {
				return false;
			}
		} catch (SchemaException e) {
			//return false;
			throw new SchemaException(e.getMessage());
		}
	}

	public abstract Table executeValid(Database db);

	public Table execute(Database db) throws SchemaException {
		validate(db.getSchema());
		return executeValid(db);
	}
	
	public static RAExpr parse(String s) {
		CharStream charStream = CharStreams.fromString(s);

		RelationalAlgebraLexer tl = new RelationalAlgebraLexer(charStream);

		CommonTokenStream commonTokenStream = new CommonTokenStream(tl);
		RelationalAlgebraParser tp = new RelationalAlgebraParser(commonTokenStream);
		tp.removeErrorListeners();
		tp.addErrorListener(ParsingExceptionListener.INSTANCE);

		ParseTree parseTree = tp.start();
		BuildExpr buildExpr = new BuildExpr();

		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(buildExpr, parseTree);

		return buildExpr.getExpr();
	}
}
