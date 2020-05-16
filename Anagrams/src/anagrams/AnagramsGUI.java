/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anagrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 19padmanabhann
 */
public class AnagramsGUI extends javax.swing.JFrame {

    
    private ArrayList inputFile = new ArrayList();
    // private ArrayList outputFile = new ArrayList();
    private String input = "D:\\Nimal Backup Files\\NetBeansProjects\\Anagrams\\dict.txt";
    private Scanner file;

    /**
     * Creates new form AnagramsGUI
     */
    public AnagramsGUI() {
        initComponents();
    }
/**
     * Used for reading the dictionary file and checking whether a word
     * exists in the dictionary file.
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
    /**
     * Alphabetizes the dictionary words given a string.
     * @param s
     * @return 
     */
    public String alphaDict(String s) {
        ArrayList<String> str = wordList(s);
        for (int i = 0; i < str.size(); i++) {
            for (int j = 1; j < str.size(); j++) {
                if (str.get(i).compareTo(str.get(j)) > 0) {
                    Collections.sort(str);
                }
            }
        }
        return "ABC Order for Dictionary Words: " + str;
    }
    /**
     * A method to alphabetize strings using the sort method (Yes, I did the
     * shortcut :))
     *
     * @param str
     */
    public String swap(String s) {
        ArrayList<String> str = getPermutations(s);
        for (int i = 0; i < str.size(); i++) {
            for (int j = 1; j < str.size(); j++) {
                if (str.get(i).compareTo(str.get(j)) > 0) {
                    Collections.sort(str);
                }
            }
        }
        return "ABC Order: " + str;
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
/**
     * Based on the boolean method of fileReader(), it will return an ArrayList of Strings that
     * exist in the dictionary.
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        word = new javax.swing.JTextField();
        dictionary = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        getPermutations = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayPerm = new javax.swing.JTextArea();
        getDictPerms = new javax.swing.JButton();
        getAlpha = new javax.swing.JButton();
        removeDup = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        alphaValid = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AnagramsGUI");

        word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordActionPerformed(evt);
            }
        });

        dictionary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dictionaryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Enter a word.");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Enter a dictionary file.");

        getPermutations.setText("Get Permutations");
        getPermutations.setToolTipText("Returns the permutations of a given word.");
        getPermutations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPermutationsActionPerformed(evt);
            }
        });

        displayPerm.setColumns(20);
        displayPerm.setRows(5);
        jScrollPane2.setViewportView(displayPerm);

        getDictPerms.setText("Actual Perms");
        getDictPerms.setToolTipText("Returns actual words from the anagram");
        getDictPerms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getDictPermsActionPerformed(evt);
            }
        });

        getAlpha.setText("ABC w/All");
        getAlpha.setToolTipText("Shows the alphabetized list of anagrams");
        getAlpha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAlphaActionPerformed(evt);
            }
        });

        removeDup.setText("No Repeats");
        removeDup.setToolTipText("Removes the duplicate anagrams");
        removeDup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDupActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.setToolTipText("Clears the Text Area");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        alphaValid.setText("ABC Valid");
        alphaValid.setToolTipText("Alphabetizes the dictionary anagrams");
        alphaValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alphaValidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(word, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(dictionary, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(getPermutations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(getAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(getDictPerms, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(alphaValid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 37, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeDup, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(455, 455, 455))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(word, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dictionary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getPermutations)
                    .addComponent(getDictPerms)
                    .addComponent(removeDup))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getAlpha)
                    .addComponent(alphaValid)
                    .addComponent(clearButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * When the Get Permutations button is pressed, it adds the word to the word
 * text field and to the jTextArea for display.
 * 
 * @param evt 
 */
    private void getPermutationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getPermutationsActionPerformed
        String str;
//       word.setText("");
//       displayPerm.setText("");

        try {
            str = word.getText();
            displayPerm.append("All anagrams: " + getPermutations(str).toString() + "\n");
//            this.getPermutations(str);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Not a valid word", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        this.getPermutations.setText(getPermutations(str).toString());
    }//GEN-LAST:event_getPermutationsActionPerformed
    /**
     * Reads the dictionary file when the program runs.
     * @param evt 
     */
    private void dictionaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dictionaryActionPerformed
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
            JOptionPane.showMessageDialog(this, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_dictionaryActionPerformed
    /**
     * Similar to the first method, the dictionary anagrams are added to the 
     * jTextArea for display using a try-catch block.
     * @param evt 
     */
    private void getDictPermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDictPermsActionPerformed
        String str;
        try {
            str = word.getText();
            displayPerm.append("Actual words: " + wordList(str).toString() + "\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Not a valid word", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_getDictPermsActionPerformed
    /**
     * When the Clear button is pressed, the word textField and the jTextArea
     * are cleared by setting the text to a blank string.
     * @param evt 
     */
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        word.setText("");
        displayPerm.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed
    /**
     * When the no repeats button is pressed, it will add the anagrams without
     * repeats if a word meets such case (Ex. hello, tell, hiss, manner, etc)
     * @param evt 
     */
    private void removeDupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDupActionPerformed
        String str;
        try {
            str = word.getText();
            displayPerm.append("No repeat: " + removeDup(str).toString() + "\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Not a valid word", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_removeDupActionPerformed
    /**
     * Returns ALL anagrams in ABC order.
     * @param evt 
     */
    private void getAlphaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAlphaActionPerformed
        String str;
        try {
            str = word.getText();
            displayPerm.append(swap(str) + "\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Not a valid word", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_getAlphaActionPerformed
    /**
     * 
     * @param evt 
     */
    private void wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wordActionPerformed
    /**
     * Returns the alphabetized list of dictionary anagrams by using a try-catch
     * block.
     * @param evt 
     */
    private void alphaValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alphaValidActionPerformed
       String str;
        try {
            str = word.getText();
            displayPerm.append(alphaDict(str) + "\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Not a valid word", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_alphaValidActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AnagramsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnagramsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnagramsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnagramsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnagramsGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alphaValid;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField dictionary;
    private javax.swing.JTextArea displayPerm;
    private javax.swing.JButton getAlpha;
    private javax.swing.JButton getDictPerms;
    private javax.swing.JButton getPermutations;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeDup;
    private javax.swing.JTextField word;
    // End of variables declaration//GEN-END:variables
}