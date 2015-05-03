package pro.miner.classess;

import forms.Form_text_mining;
import static forms.Form_text_mining.checktok;
import static forms.Form_text_mining.freqlist;
import static forms.Form_text_mining.listofwords;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Devil Knight
 */
public class TreeSelection {

    public static String fileText = null;

    public void SelectTree() {
        String str = null;
        try {

            listWordSelection.selectedWord = "";

            Form_text_mining.selectedGlossaryFilePath.setText("");
            Form_text_mining.meaningArea.setText("");
            if (WordNetSearcher.relatedWordsListModel != null) {
                WordNetSearcher.relatedWordsListModel.removeAllElements();
            }


            StartStemmer.freqmodel.removeAllElements();
            StartStemmer.wordsmodel.removeAllElements();

            str = Form_text_mining.contextstree.getSelectionPath().toString();
            String[] st = str.split(", ");
            str = st[1];
            str = str.replace("]", "");
            //str = str.replace(" .txt", ".txt");
            //str = str.replace("Context", "");
            //System.out.println(str);
            
            //set selected file from tree view to global variable for output save
            Global.CurrentContextFile = str;
            
            // Reading File
            File file = TextMining.file;
            String f = file.toString() + "\\" + str;
            fileText = readFile(f);
            //System.out.println(str);                       
            
            //check tokenization
            checktok.setSelected(true);

            //Start Thread for Remove Stop Words and Set remaining in the List
            RemoveStopWord r = new RemoveStopWord();
            r.remove();
            Form_text_mining.checkStem.setSelected(true);
            Form_text_mining.checkstop.setSelected(true);
            Form_text_mining.checkFreqCount.setSelected(true);
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null,
                    "Select a file for processing\n" + ex + "\nClass:TreeSelection",
                    "File Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
//Read Select File From The Tree

    private String readFile(String file) {

        BufferedReader br = null;
        String allText = "";
        String str = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((str = br.readLine()) != null) {
                allText += str.replace("have", "") + " " + "\n".trim().replaceAll("\\s+", " ");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "File not Found\n" + e + "\nClass:Tree Selection|Method:readFile()",
                    "Read File Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "File not found,Unable to Close File\n" + ex + "\nClass:Tree Selection, Method:readFile()",
                        "Read File Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        //stopword part needing
        
        //again tokenization applying
        for(int i=0; i<5; i++)
        {
            allText = allText.replace("[","").replace("]","").replace("#","").replace(":", "").replace("•", "").replace("\n", "")
                        .replace("\u0092", "").replace("'", "").replace("•", "").replace("�", "").replace("-", "").replace("’", "").replace("\n", "")
                        .replace(".","").replace("(", "").replace(")","").replace(",","").replace(";","").replace("“","").replace("?","")
                        .replace("\\","").replace("/","").replace("”","").replace("–", "").replace("1", "").replace("2", "").replace("3", "")
                            .replace("4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "").replace("0", "");
        }
        System.out.println(allText);
        return allText;
    }
}
