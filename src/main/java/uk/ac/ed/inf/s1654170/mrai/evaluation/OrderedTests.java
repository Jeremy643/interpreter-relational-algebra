package uk.ac.ed.inf.s1654170.mrai.evaluation;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.ac.ed.inf.s1654170.mrai.schema.Database;

class OrderedTests {
	
	private static Database dbBags;
	private static Database dbSets;
	
	@BeforeAll
	static void readData() throws IOException {
		dbBags = GetDataHelper.readData(true, true);
		dbSets = GetDataHelper.readData(true, false);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
