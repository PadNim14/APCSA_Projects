/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {

    private ArrayList inputFile = new ArrayList();
    // private ArrayList outputFile = new ArrayList();
    private String input = "C:\\Users\\19padmanabhann\\Documents\\NetBeansProjects\\Anagrams\\dict.txt";
    private Scanner file;

    /**
     * Used for reading the dictionary file.
     */
    public void readFile() {
        try {
            BufferedReader f = new BufferedReader(new FileReader(input));
            ArrayList mod = new ArrayList();
            String line = f.readLine();
            while (!(line == null)) {
                inputFile.add(line);
                line = f.readLine();
            }

//            System.out.println(inputFile);
//            System.out.println(inputFile);
        } catch (Exception e) {
            System.out.println("Cannot read file.");
            System.out.println(inputFile);
        }
    }

    /**
     * Opens the dictionary file using a try-catch block
     */
    public void openFile() {

        try {
            file = new Scanner(new File(input));

        } catch (Exception e) {
            System.out.println("Could not find file");
        }

    }

    /**
     * Goes through the dictionary to determine a word is in the dictionary. A
     * boolean method
     *
     * @param str
     * @return
     */
    public boolean fileReader(String str) {
        try {

            BufferedReader f = new BufferedReader(new FileReader(input));
            String sam = "y";
            while (!(sam == null)) {
                sam = f.readLine();
                if (sam.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
//            System.out.println("invalid read file path");
        }
        return false;
    }
//

    /**
     * Based on the boolean method, it will return an ArrayList of Strings that
     *
     * @param str
     * @return
     */
    public ArrayList<String> wordList(String str) {
        ArrayList<String> mod = removeDup(str);
        ArrayList<String> upd = new ArrayList<>();
        for (int i = 0; i < mod.size(); i++) {
            if (fileReader(mod.get(i)) == true) {
                upd.add(mod.get(i));
            }
        }
        return upd;
    }

    public void alphaDict(ArrayList<String> str) {
        for (int i = 0; i < str.size(); i++) {
            for (int j = 1; j < str.size(); j++) {
                if (str.get(i).compareTo(str.get(j)) > 0) {
                    Collections.sort(str);
                }
            }
        }
        System.out.println("ABC Order for Dictionary Words: " + str);
    }

    /**
     * A method to alphabetize strings using the sort method (Yes, I did the
     * shortcut :))
     *
     * @param str
     */
    public void swap(ArrayList<String> str) {
        for (int i = 0; i < str.size(); i++) {
            for (int j = 1; j < str.size(); j++) {
                if (str.get(i).compareTo(str.get(j)) > 0) {
                    Collections.sort(str);
                }
            }
        }
        System.out.println("ABC Order: " + str);
    }

    /**
     * Returns all possible permutations of a certain word.
     *
     * @param str
     * @return
     */
    public ArrayList<String> getPermutations(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str.length() <= 1) {
            result.add(str);
            return result;
        }
        for (int i = 0; i < str.length(); i++) {
            String perm = str.substring(0, i) + str.substring(i + 1);
            // System.out.println(result.add(perm));
            ArrayList<String> shortPerm = getPermutations(perm);
            for (String s : shortPerm) {
                result.add(str.charAt(i) + s);
            }
        }
        return result;

    }

    /**
     * Removes duplicate words of permutations
     *
     * @param str
     * @return
     */
    public ArrayList<String> removeDup(String str) {
        ArrayList<String> org = getPermutations(str);
        ArrayList<String> mod = new ArrayList<>();
        for (String rpt : org) {
            if (!mod.contains(rpt)) {
                mod.add(rpt);
            }
        }
        return mod;
    }

//	
    /**
     * Runner for my class
     *
     * @param args
     */
    public static void main(String[] args) {
        Main bob = new Main();
        String word = JOptionPane
                .showInputDialog("Enter a word and I will give you permutations.");

        String dict = JOptionPane.showInputDialog("Enter your dictionary file.");
        System.out.println("Your word is "+ word+".");
        bob.openFile();
        bob.readFile();
        System.out.println(bob.fileReader(word));
//        bob.findWord(word);
//        bob.addWord(dict, word);
//        System.out.println(bob.checkWord(dict, word));
        System.out.println("Existing Words: " + bob.wordList(word));
        bob.alphaDict(bob.wordList(word));
//		System.out.println(bob.checkWord(word));
//		System.out.println(bob.checkWord(word));
        System.out.println("All of the permutations: " + bob.getPermutations(word));
        System.out.println("No repeat list: " + bob.removeDup(word));
        bob.swap(bob.getPermutations(word));
        System.out.print("No repeat swap ");
        bob.swap(bob.removeDup(word));
		// System.out.println(bob.alphaPerms(bob.getPermutations(word)));
        // System.out.println(bob.removeDup(bob.getPermutations(word)));

        // bob.getPermutations(str)
    }
}
