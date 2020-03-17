package evaluation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.ac.ed.inf.s1654170.mrai.schema.Database;

class UnorderedTest {

	private static Database dbBags;
	private static Database dbSets;
	private static boolean unordered = true;
	private static boolean bags = true;

	@BeforeAll
	static void readData() throws IOException {
		dbBags = GetDataHelper.readData(unordered, bags);
		dbSets = GetDataHelper.readData(unordered, !bags);
	}
}
