/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.mit.jwi.item.POS;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static pro.miner.classess.GlossarySearcher.file;
import static pro.miner.classess.RemoveStopWord.stopWordsofwordnet;
import forms.Form_domain_expert;
import static forms.Form_domain_expert.pbofwordnet;
import static forms.Form_stage_one.stem;

/**
 *
 * @author Devil Knight
 */
public class GetRelatedWordFromGlossary {

    public static DefaultListModel relatedGlossary = new DefaultListModel();
    public static ArrayList<String> tempList2 = new ArrayList<>();
    public static ArrayList<String> tempList = new ArrayList<String>();

    public static void getRelatedwordsFromGlossary(String wkey) {
        Stemmers ss = new Stemmers();

        System.out.println("In GetRelatedWordFromGlossary.java");


        try {
            // Reading Glossary File
//            relatedGlossary.removeAllElements();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = br.readLine()) != null) {
                line = line.toLowerCase().replace("@", " ").replace("[", " ").replace("�", " ").replace("=", " ").replace("+", " ").replace("?", " ").replace("&", " ").replace("%", " ").replace("$", " ").replace(" ", " ").replace("<", " ").replace(">", " ").replace("]", " ").replace("*", " ").replace("_", " ").replace("{", " ").replace("}", " ").replace("#", " ").replace(":", " ").replace("•", " ").replace("\n", " ")
                        .replace("\u0092", " ").replace("'", " ").replace("•", " ").replace("-", " ").replace("’", " ").replace("\n", " ")
                        .replace(".", " ").replace("(", " ").replace(")", " ").replace(",", " ").replace(";", " ").replace("“", " ")
                        .replace("\\", " ").replace("/", " ").replace("”", " ").replace("–", " ").replace("1", " ").replace("2", " ").replace("3", " ").replace("4", " ").replace("5", " ").replace("6", " ").replace("7", " ").replace("8", " ").replace("9", " ").replace("0", " ");
                if (line.isEmpty()) {
                    //System.out.println("Line is empty");
                } else {
                    //System.out.println("Line Added to temprary list as array");
                    tempList.addAll(Arrays.asList(line.split(" ")));
                }
            }

            br.close();


            System.out.println("Remove stop words from temprary list");
            //Removing Stop Words
            for (int i = 0; i < tempList.size(); i++) {
                // get the item as string
                //System.out.println("Iterating Temprary List");
                for (int j = 0; j < stopWordsofwordnet.length; j++) {
                    if (stopWordsofwordnet[j].contains(tempList.get(i))) {
                        tempList.remove(i);
                    }
                }
            }


            // Search In Glossary File
            System.out.println("Search in Glossary File");
            String stemmed;
            ArrayList<String> wordsList = new ArrayList<String>();

            //Find Stems
            WordNetDatabase database = WordNetDatabase.getFileInstance();

            for (int index = 0; index < tempList.size(); index++) {
                //System.out.println("Search in Glossary File Loop");
                if ((tempList.get(index)).trim().toLowerCase().equals(wkey.toLowerCase())) {
                    System.out.println("Search in Glossary File if-statement");
                    if (index - 1 > 0) {


                        stemmed = database.getSynsets(tempList.get(index - 1).toLowerCase(), SynsetType.NOUN).toString();

                        //stemmed = stem.findStems(tempList.get(index - 1).toLowerCase(), POS.NOUN).toString();

                        stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                        if (stemmed == "") {
                        } else {
                            String key = tempList.get(index - 1).toLowerCase();
                            if (Tagger.IsValidNoun(key)) {
                                wordsList.add(key + " " + wkey);
                            }
                        }
                    }
                    if (index + 1 < tempList.size()) {

                        stemmed = database.getSynsets(tempList.get(index + 1).toLowerCase(), SynsetType.NOUN).toString();

                        //stemmed = stem.findStems(tempList.get(index - 1).toLowerCase(), POS.NOUN).toString();

                        stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");

                        if (stemmed == "") {
                        } else {
                            String key = tempList.get(index + 1).toLowerCase();
                            if (Tagger.IsValidNoun(key)) {
                                wordsList.add(wkey + " " + key);
                            }
                        }
                    }
                }
            }


            // Remove Similar Words
            Collections.sort(wordsList);
            Collections.reverse(wordsList);
            HashSet hs = new HashSet();
            hs.addAll(wordsList);
            wordsList.clear();
            wordsList.addAll(hs);


            for (int i = 0; i < wordsList.size(); i++) {
                relatedGlossary.addElement(wordsList.get(i));
                System.out.println(wordsList.get(i));
            }

            Form_domain_expert.similarWordsOfRelatedWordsList.setModel(relatedGlossary);


            //wordsList.clear();
            //tempList.clear();

        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,
//                    "Exception\n" + ex + "\nClass:GetRelatedWordFromGlossary",
//                    "Not Found Error",
//                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
