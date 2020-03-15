package uk.ac.ed.inf.s1654170.mrai.evaluation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;

public class GetDataHelper {
	
	public static Database readData(boolean ordered, boolean bags) throws IOException {
		Database db;
		ArrayList<String> fileName = new ArrayList<>();
		
		File dirPath = new File(System.getProperty("user.dir"));
		File folder = new File(dirPath, "src/main/java/uk/ac/ed/inf/s1654170/mrai/evaluation");
		File[] listOfFiles = folder.listFiles();
		
		ArrayList<String> attributes = new ArrayList<>();
		ArrayList<String> attributeTypes = new ArrayList<>();
		
		Map<String,List<Record>> tables = new HashMap<>();
		for (File file : listOfFiles) {
			if (!file.getName().endsWith(".csv")) {
				//if file not csv then ignore
				continue;
			}
			String name = file.getName().replace(".csv", "");
			fileName.add(name);
			
			Reader in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			
			int index = 0;
			List<Record> tableRecords = new ArrayList<>();
			List<Type> types = new ArrayList<>();
			for (CSVRecord record : records) {
				int size = record.size();
				switch (index) {
					case 0:
						String attr = "";
						for (int i = 0; i < size; i++) {
							if (i == size-1) {
								attr += record.get(i);
							} else {
								attr += record.get(i) + ",";
							}
						}
						attributes.add(attr);
						break;
					case 1:
						String type = "";
						for (int i = 0; i < size; i++) {
							if (i == size-1) {
								type += record.get(i);
							} else {
								type += record.get(i) + ",";
							}
							types.add(Type.valueOf(record.get(i)));
						}
						attributeTypes.add(type);
						break;
					default:
						String[] values = new String[size];
						for (int i = 0; i < size; i++) {
							values[i] = record.get(i);
						}
						Record r = Record.valueOf(types, values);
						tableRecords.add(r);
						break;
				}
				tables.put(name, tableRecords);
				index++;
			}
		}
		
		Schema sch = new Schema(fileName, attributes, attributeTypes, ordered);
		
		db = new Database(sch);
		for (String name : fileName) {
			Table table = new Table(sch.getSignature(name), bags);
			table.addAll(tables.get(name));
			System.out.println(table);
			try {
				db.add(name, table);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		return db;
	}
}
