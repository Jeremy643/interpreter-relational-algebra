package uk.ac.ed.inf.s1654170.mrai.evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uk.ac.ed.inf.s1654170.mrai.exprs.Base;
import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.exprs.Union;
import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.instance.TableOperations;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;

public class Evaluate {
	
	private static Database db;
	private static ArrayList<String> fileName = new ArrayList<>();
	
	private static void readData() throws IOException {
		String dirPath = System.getProperty("user.dir");
		dirPath += "\\src\\main\\java\\uk\\ac\\ed\\inf\\s1654170\\mrai\\evaluation";
		File folder = new File(dirPath);
		File[] listOfFiles = folder.listFiles();
		
		//ArrayList<String> fileName = new ArrayList<>();
		ArrayList<String> attributes = new ArrayList<>();
		ArrayList<String> attributeTypes = new ArrayList<>();
		
		for (File file : listOfFiles) {
			if (file.getName().equals("Evaluate.java")) {
				continue;
			} else {
				fileName.add(file.getName().replace(".csv", ""));
				String path = dirPath + "\\" + file.getName();
				BufferedReader csvReader = new BufferedReader(new FileReader(path));
				for (int i = 0; i < 2; i++) {
					if (i == 0) {
						attributes.add(csvReader.readLine());
					} else {
						attributeTypes.add(csvReader.readLine());
					}
				}
				csvReader.close();
			}
		}
		
		Schema sch = new Schema(fileName, attributes, attributeTypes);
		
		
		db = new Database(sch);
		
		for (File file : listOfFiles) {
			String name = file.getName().replace(".csv", "");
			
			if (name.equals("Evaluate.java")) {
				continue;
			} else {
				Table table = new Table();
				List<Column.Type> types = sch.getSignature(name).getTypes();
				
				String path = dirPath + "\\" + file.getName();
				BufferedReader csvReader = new BufferedReader(new FileReader(path));
				
				int x = 0;
				String row;
				while((row = csvReader.readLine()) != null) {
					if (x == 0 || x == 1) {
						x++;
					} else {
						String[] val = row.split(",");
						Record r = Record.valueOf(types, val);
						table.add(r);
					}
				}
				
				if (!name.substring(0, 1).equals("_")) {
					TableOperations.sortRecords(table);
				}
				
				try {
					db.add(name, table);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				csvReader.close();
			}
		}
	}
	
	private static void pickChoice() {
		boolean cont = true;
		
		Scanner scanner = new Scanner(System.in);
		do {
			// Ask user to pick an evaluation option
			System.out.println("Pick one of the following!");
			System.out.println("(1) Evaluate validation method.");
			System.out.println("(2) Evaluate table operations.");
			System.out.print("Enter: ");
			String choice = scanner.nextLine().replaceAll(" ", "");

			switch(choice) {
			case "1":
				evaluateValidation();
				break;
			case "2":
				evaluateTableOperations();
				break;
			default:
				System.out.println("Wrong input. Enter either 1 or 2!");
				break;
			}
			
			System.out.println();
			
			// Ask user if they want to stop or pick another option
			System.out.println("Do you wish to pick another option?[y/n]");
			System.out.print("Enter: ");
			String carryOn = scanner.nextLine().replaceAll(" ", "").toLowerCase();
			
			switch(carryOn) {
			case "y":
				System.out.println();
				break;
			case "n":
				System.out.println("Exit...");
				cont = false;
				break;
			default:
				System.out.println("Your input was incorrect! Exit it is then!");
				cont = false;
				break;
			}
		} while (cont == true);
		scanner.close();
	}
	
	private static void evaluateValidation() {
		// Evaluate the validation method on correct and incorrect input
		System.out.println();
		System.out.println("Evaluating the validation method.");
		System.out.println();
		
		for (String name : fileName) {	
			if (!name.substring(0, 1).equals("_")) {
				System.out.println(String.format("%s: %s", name, db.getSchema().getSignature(name)));
			}
		}
		
		System.out.println();
		
		RAExpr correctUnion = new Union(new Base("Students"), new Base("SportStudents"));
		System.out.println(correctUnion.toString());
		System.out.println("Expected: true");
		System.out.println("Actual: " + correctUnion.validate(db.getSchema()));
		
		System.out.println();
		
		RAExpr incorrectUnion = new Union(new Base("Students"), new Base("Teachers"));
		System.out.println(incorrectUnion.toString());
		System.out.println("Expected: false");
		System.out.println("Actual: " + incorrectUnion.validate(db.getSchema()));
	}
	
	private static void evaluateTableOperations() {
		System.out.println();
		System.out.println("Evaluating the different table operations.");
		System.out.println();
		
		for (String name : fileName) {
			if (!name.substring(0, 1).equals("_")) {
				System.out.println(String.format("%s: %s", name, db.getTable(name)));
			}
		}
		
		System.out.println();
		
		RAExpr correctUnion = new Union(new Base("Students"), new Base("SportStudents"));
		System.out.println(correctUnion.toString());
		System.out.println("Expected: " + db.getTable("_StudentsUnionSportStudents"));
		System.out.println("Actual: " + correctUnion.execute(db));
	}
	
	public static void main(String[] args) throws IOException {
		readData();
		
		pickChoice();
	}
}
