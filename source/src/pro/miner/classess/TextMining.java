package pro.miner.classess;

import java.io.BufferedReader;
import forms.Form_text_mining;
import static forms.Form_text_mining.checkfile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import forms.Form_domain_expert;
import static forms.Form_text_mining.checktok;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Devil Knight
 */
public class TextMining extends SwingWorker {

    public static boolean isFile = false;
    public static boolean isFolder = false;
    public static File file;

    public static String methodIIFileText = "";

    @Override
    public String doInBackground() throws Exception {
        mining();
        return "";
    }

    public void mining() {

        //Open Dialogue Box to Get Folder
        Form_text_mining.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = Form_text_mining.fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = Form_text_mining.fileChooser.getSelectedFile();

            try {
                isFolder = (file.listFiles().length > 0 && !file.getName().endsWith(".txt"));
            } catch (Exception e) {
            }

            try {
                isFile = (file.listFiles().length == 0 && file.getName().endsWith(".txt"));
            } catch (Exception e) {
            }

            Form_text_mining.progressBarTextMining.setIndeterminate(true);
            if (isFolder) {
                File[] listOfFiles = file.listFiles();

                //Tree Making
                DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Contexts");
                DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
                Form_text_mining.contextstree.setModel(treeModel);
                
                DefaultMutableTreeNode ntemp;
                String node;

                for (int i = 0; i < listOfFiles.length; i++) {
                    node = listOfFiles[i].getName();
                    ntemp = new DefaultMutableTreeNode(node);
                    rootNode.add(ntemp);
                }
            } else {

                //set name of the file to global variable for output save
                Global.CurrentContextFile = file.getName();

                String line = "";
                BufferedReader reader = null;

                DefaultListModel modelListOfWords = new DefaultListModel();
                DefaultListModel freqModelListOfWords = new DefaultListModel();

                try {
                    Form_text_mining.listofwords.removeAll();

                    // Tokenize words from file and populate it into the list.
                    reader = new BufferedReader(new FileReader(file));

                    if (reader != null) {
                        System.out.println("FILE READ :: START");
                        while ((line = reader.readLine()) != null) {
                            line = line.trim();
                            methodIIFileText += line + " ";
                        }

                        //check tokenization
                        checktok.setSelected(true);

                        System.out.println("FILE READ :: COMPLETED");

                        //Start Thread for Remove Stop Words and Set remaining in the List
                        RemoveStopWord r = new RemoveStopWord();
                        r.remove();

                        System.out.println("PROCESS IMPORT :: COMPLETE");
                        Form_text_mining.checkStem.setSelected(true);
                        Form_text_mining.checkstop.setSelected(true);
                        Form_text_mining.checkFreqCount.setSelected(true);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TextMining.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TextMining.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Setting Folder Path in Bar
            Form_text_mining.folderPath.setText(file.toString());
            checkfile.setSelected(true);
            Form_text_mining.progressBarTextMining.setIndeterminate(false);
        }
    }
}
