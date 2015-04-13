/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;


import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import static pro.miner.classess.WordNetSearcher.relatedWordsListModel;

/**
 *
 * @author Devil Knight
 */
public class ABC {

    public void juni() {
        String wordForm = listWordSelection.selectedWord;
        //  Get the synsets containing the wrod form
        File f = new File("WordNet\\2.1\\dict");
        System.setProperty("wordnet.database.dir", f.toString());
        WordNetDatabase database = WordNetDatabase.getFileInstance();
        
        Synset[] synsets = database.getSynsets(wordForm);
        //  Display the word forms and definitions for synsets retrieved
        if (synsets.length > 0) {
            ArrayList<String> al = new ArrayList<String>();
            // add elements to al, including duplicates
            HashSet hs = new HashSet();

            for (int i = 0; i < synsets.length; i++) {

                String[] wordForms = synsets[i].getWordForms();
                for (int j = 0; j < wordForms.length; j++) {
                    al.add(wordForms[j]);
                }

            }

            hs.addAll(al);
            al.clear();
            al.addAll(hs);

            for (int i = 0; i < al.size(); i++) {

                relatedWordsListModel.addElement(al.get(i));
            }
        } else {
            System.err.println("No synsets exist that contain "
                    + "the word form '" + wordForm + "'");
        }


    }
}
