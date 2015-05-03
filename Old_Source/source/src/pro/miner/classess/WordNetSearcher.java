/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import forms.Form_text_mining;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Devil Knight
 */
public class WordNetSearcher {

//    String path = "C:\\Program Files\\WordNet\\2.1\\dict";
//    String path = MainStage.path;
    URL url;
    IDictionary dict;
    ABC a = new ABC();
    public static DefaultListModel relatedWordsListModel = new DefaultListModel();

    public void searcher() {
        try {

            url = new URL("file", null, "WordNet\\2.1\\dict");


            dict = new Dictionary(url);
            try {
                dict.open();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Dictionary directory does not exist\n" + ex + "\nClass:Meaning Thread", "Dictionary Not Found Error", JOptionPane.ERROR_MESSAGE);

            }

            IIndexWord idxWord = dict.getIndexWord(listWordSelection.selectedWord, POS.NOUN);
            IWordID wordID = idxWord.getWordIDs().get(0);
            IWord word = dict.getWord(wordID);

            Form_text_mining.meaningArea.setText(word.getSynset().getGloss());          
            a.juni();

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            getVerbDefinition();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //TextMiningForm.relatedWordsList.setModel(relatedWordsListModel);

    }

    public void getVerbDefinition() {
        try {
            IIndexWord idxWord = dict.getIndexWord(listWordSelection.selectedWord, POS.VERB);
            IWordID wordID = idxWord.getWordIDs().get(0);
            IWord word = dict.getWord(wordID);

            Form_text_mining.meaningArea.setText(word.getSynset().getGloss());
            //Adding Related Words to List of Realted Words
//            ISynset synset = word.getSynset();
//
//            for (IWord w : synset.getWords()) {
//                relatedWordsListModel.addElement(w.getLemma());
//            }
//            Form_text_mining.relatedWordsList.setModel(relatedWordsListModel);
            a.juni();

        } catch (NullPointerException ex) {
            getAdjectiveDefinition();
        } catch (Exception e) {
        }
    }

    public void getAdjectiveDefinition() {
        try {
            IIndexWord idxWord = dict.getIndexWord(listWordSelection.selectedWord, POS.ADJECTIVE);
            IWordID wordID = idxWord.getWordIDs().get(0);
            IWord word = dict.getWord(wordID);

            Form_text_mining.meaningArea.setText(word.getSynset().getGloss());
            //Adding Related Words to List of Realted Words
//            ISynset synset = word.getSynset();
//            for (IWord w : synset.getWords()) {
//                relatedWordsListModel.addElement(w.getLemma());
//            }
//            Form_text_mining.relatedWordsList.setModel(relatedWordsListModel);
            a.juni();
        } catch (NullPointerException e) {
            getAdverbDefinition();
        } catch (Exception e) {
        }
    }

    public void getAdverbDefinition() {
//        if (listWordSelection.selectedWord != null) {
        try {
            IIndexWord idxWord = dict.getIndexWord(listWordSelection.selectedWord, POS.ADVERB);
            IWordID wordID = idxWord.getWordIDs().get(0);
            IWord word = dict.getWord(wordID);

            Form_text_mining.meaningArea.setText(word.getSynset().getGloss());
            //Adding Related Words to List of Realted Words
//            ISynset synset = word.getSynset();
//
//            for (IWord w : synset.getWords()) {
//                relatedWordsListModel.addElement(w.getLemma());
//            }
//            Form_text_mining.relatedWordsList.setModel(relatedWordsListModel);
            a.juni();
        } catch (NullPointerException e) {
//            JOptionPane.showMessageDialog(null,
//                    "Word Not Found in Dictionary\n" + e + "\nClass:Meaning Thread",
//                    "Not Found Error",
//                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
        }
    }

    public static List<Object> getRelatedWordListModelList() {
        return WordNetSearcher.asList(WordNetSearcher.relatedWordsListModel);
    }

    public static List<Object> asList(final DefaultListModel model) {
        return new AbstractList<Object>() {
            @Override
            public Object get(int index) {
                return model.getElementAt(index);
            }

            @Override
            public int size() {
                return model.getSize();
            }
        };
    }
}
