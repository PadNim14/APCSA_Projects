/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DNA {
	private final double aMass = 135.128;
	private final double cMass = 111.103;
	private final double gMass = 151.128;
	private final double tMass = 125.107;
	private final double junk = 0;

	// private String nuc;
	private String input = "";
	private String output = "";
	private Scanner file;
	private ArrayList inputFile = new ArrayList();
	private ArrayList outputFile = new ArrayList();

	public void betaGreet() {

		Scanner keyboard = new Scanner(System.in);
		System.out
				.println("Greetings, wise one. This program will compute and analyze the various nucleotide sequences.");
		System.out
				.println("Please enter an input file. (It has to have .txt after the name.");
		input = keyboard.nextLine();
		System.out.println("And please enter an output file as well.");
		output = keyboard.nextLine();
		readFile();
		for (int i = 0; i < inputFile.size(); i++) {
			if(i % 2 == 0){
			outputFile.add("Region Name: " + inputFile.get(i));
			// System.out.println(outputFile);
//			if (i % 2 == 0) {
				String upperCase = (String) inputFile.get(i + 1);
				nucCount(upperCase);
//				massPercentages(upperCase);
				upperCase = upperCase.toUpperCase();
				dataOutput(upperCase);

				outputFile.add(" ");
			}
		}
		writeFile(outputFile);
		System.out.println("Dunzo.");

	}

	public String[] parseDNA(String nuc) {
		String[] parse = new String[nuc.length()];
		for (int i = 1; i < nuc.length() + 1; i++) {
			parse[i - 1] = (nuc.substring(i - 1, i));
		}
		return parse;
	}

	// public void addOutput() {
	// for (int i = 0; i < inputFile.size(); i++) {
	// inputFile.add("Region Name:");
	// String capital = (String) inputFile.get(i);
	// capital = capital.toUpperCase();
	// outputFile.add(" ");
	// }
	// }

	public void dataOutput(String nuc) {
		outputFile.add("Nucleotides: " + nuc);
		outputFile.add("Nuc. Counts: " + (nucCount(nuc)));
		outputFile.add("Total Mass%: " + toString(massPercentages(nuc), nuc));
		outputFile.add("Codons List: " + toString(codonList(nuc)));
		if (isProtein(nuc) == true) {
			outputFile.add("Is Protein?: " + "YES");
		} else {
			outputFile.add("Is Protein?: " + "NO");
		}
	}

	public String toString(double[] dec, String nuc) {
		String finalString = "[" + dec[0];
		for (int i = 1; i < dec.length; i++) {
			finalString = finalString + ", " + dec[i];
		}
		finalString = finalString + "] of " + (totalMass(nuc));
		return finalString;

	}

	public String toString(int[] num) {
		String finalNum = "[" + num[0];
		for (int i = 1; i < num.length - 2; i++) {
			finalNum = finalNum + ", " + num[i];
		}
		finalNum = finalNum + "]";
		return finalNum;
	}

	public String toString(String seq) {
		String finalSeq = "[" + seq.indexOf(0);
		for (int i = 1; i < seq.length(); i++) {
			finalSeq = finalSeq + ", " + seq.indexOf(i);
		}
		finalSeq = finalSeq + "]";
		return finalSeq;
	}
	public String toString(String [] seq){
		String finalSeq = "[" + seq[0];
		for(int i = 1; i < seq.length; i++){
			finalSeq = finalSeq + ", " + seq[i];
		}
		finalSeq = finalSeq + "]";
		return finalSeq;
	}

	public boolean isProtein(String nuc) {
		if (containsDash(nuc) == true) {
			return false;
		} else if (nuc.startsWith("ATG")
				&& (nuc.endsWith("TAA") || nuc.endsWith("TAG") || nuc
						.endsWith("TGA")) && (nuc.length() >= 5)
				&& (massCytosine(nuc) + massGuanine(nuc) >= 30.0)) {
			return true;
		}
		return false;
	}

	public void openFile() {

		try {
			file = new Scanner(new File(
					"D:\\DNA_Eclipse\\DNA_Analysis\\src\\basic.txt"));

		} catch (Exception e) {
			System.out.println("Could not find file");
		}

	}

	// public void readFile() {
	//
	// }

	public void readFile() {
		try {
			BufferedReader f = new BufferedReader(new FileReader(input));
			String line = f.readLine();
			while (!(line == null)) {
				inputFile.add(line);
				line = f.readLine();
			}
		} catch (Exception e) {
			System.out.println("Cannot read file.");
		}
	}

	public void writeFile(ArrayList input) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(output)));
			for (int i = 0; i < input.size() - 1; i++) {
				out.println(input.get(i));
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Not a valid write file path");
		}
	}

	public boolean containsDash(String nuc) {
		String dash = "-";
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.contains(dash)) {
				return true;
			}
		}
		return false;
	}

	public String removeDash(String nuc) {
		String dash = "-";
		for (int i = 0; i < dash.length(); i++) {
			if (nuc.contains(dash)) {
				nuc = nuc.replace(dash, "");
			}
		}
		return nuc;
	}

	public String[] codonList(String nuc) {
		String[] list = new String[nuc.length() + 3 / 3];
		String[] parsed = parseDNA(nuc);
		String codon = "";
		int countNuc = 0;
		int placeLoc = 0;
		int count = 0;
		while (count < parsed.length) {
			if (!(parsed[count].equals("-"))) {
				countNuc++;
				codon = codon + parsed[count];
			}
			if (countNuc == 3) {
				list[placeLoc] = codon;
				placeLoc++;
				countNuc = 0;
				codon = "";
			}
			count++;
		}
		String[] finalList = new String[placeLoc];
		count = 0;
		while (count < placeLoc) {
			finalList[count] = list[count];
			count++;
		}
		return finalList;
	}

	public int countAdenine(String nuc) {
		char a = 'A';
		char lowerA = 'a';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == a || nuc.charAt(i) == lowerA) {
				count++;
			}
		}
		return count;
	}

	public int countCytosine(String nuc) {
		char c = 'C';
		char lowerC = 'c';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == c || nuc.charAt(i) == lowerC) {
				count++;
			}
		}
		return count;
	}

	public int countGuanine(String nuc) {
		char g = 'G';
		char lowerG = 'g';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == g || nuc.charAt(i) == lowerG) {
				count++;
			}
		}
		return count;
	}

	public int countThymine(String nuc) {
		char t = 'T';
		char lowerT = 't';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == t || nuc.charAt(i) == lowerT) {
				count++;
			}
		}
		return count;
	}

	public double countJunk(String nuc) {
		char dash = '-';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == dash) {
				count++;
			}
		}
		return count;
	}

	public double massAdenine(String nuc) {
		char a = 'A';
		char lowerA = 'a';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == a || nuc.charAt(i) == lowerA) {
				count++;
			}
		}
		return count * aMass;
	}

	public double massCytosine(String nuc) {
		char c = 'C';
		char lowerC = 'c';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == c || nuc.charAt(i) == lowerC) {
				count++;
			}
		}
		return count * cMass;
	}

	public double massGuanine(String nuc) {
		char g = 'G';
		char lowerG = 'g';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == g || nuc.charAt(i) == lowerG) {
				count++;
			}
		}
		return count * gMass;
	}

	public double massThymine(String nuc) {
		char t = 'T';
		char lowerT = 't';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == t || nuc.charAt(i) == lowerT) {
				count++;
			}
		}
		return count * tMass;
	}

	public double massJunk(String nuc) {
		char dash = '-';
		int count = 0;
		for (int i = 0; i < nuc.length(); i++) {
			if (nuc.charAt(i) == dash) {
				count++;
			}
		}
		return count * junk;
	}

	public String nucCount(String nuc) {
		int[] count = new int[4];
		for (int i = 0; i < count.length; i++) {
			count[0] = countAdenine(nuc);
			count[1] = countCytosine(nuc);
			count[2] = countGuanine(nuc);
			count[3] = countThymine(nuc);
		}
//		System.out.println(count[3]);
		return "["+count[0] + ", " + count[1] + ", " + count[2] + ", " + count[3]+"]";
		
	}

	public double percentAdenine(String nuc) {
		double percent = (massAdenine(nuc) / totalMass(nuc)) * 100;
		double rounded = Math.round(percent * 10) / 10.0;
		return rounded;
	}

	public double percentCytosine(String nuc) {
		double percent = (massCytosine(nuc) / totalMass(nuc)) * 100;
		double rounded = Math.round(percent * 10) / 10.0;
		return rounded;
	}

	public double percentGuanine(String nuc) {
		double percent = (massGuanine(nuc) / totalMass(nuc)) * 100;
		double rounded = Math.round(percent * 10) / 10.0;
		return rounded;
	}

	public double percentThymine(String nuc) {
		double percent = (massThymine(nuc) / totalMass(nuc)) * 100;
		double rounded = Math.round(percent * 10) / 10.0;
		return rounded;
	}

	public double percentJunk(String nuc) {
		double percent = (massJunk(nuc) / totalMass(nuc)) * 100;
		double rounded = Math.round(percent * 10) / 10.0;
		return rounded;
	}

	public double totalMass(String nuc) {
		double total = massAdenine(nuc) + massCytosine(nuc) + massGuanine(nuc)
				+ massThymine(nuc) + massJunk(nuc);
		double rounded = Math.round(total * 10) / 10.0;
		return rounded;
	}
	public double [] toStringMass(String nuc){
		double [] total = {totalMass(nuc)};
		return total;
	}

	public double[] massPercentages(String nuc) {
		double[] percent = new double[4];
		for (int i = 0; i < percent.length; i++) {
			percent[0] = percentAdenine(nuc);
			percent[1] = percentCytosine(nuc);
			percent[2] = percentGuanine(nuc);
			percent[3] = percentThymine(nuc);
		}
		return percent;
//		 return"[" + percent[0] + "," + " " + percent[1] + ", " + percent[2] + "," + " "+ percent[3] + "] of " + totalMass(nuc);
	}

	// public void removeJunk(){
	// String noJunk = nuc;
	// for(int i = 0; i < noJunk.length(); i++){
	// if(noJunk.contains("-")){
	// noJunk.replace("-", " ");
	// }
	// }
	// System.out.print(noJunk);
	// }
}

