/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.stanford.nlp.util.StringUtils;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import forms.Form_domain_expert;
import static forms.Form_domain_expert.wordsField;

/**
 *
 * @author Farooq
 */
public class SimilarWordSearcher extends SwingWorker {

    public static ArrayList<String> similarWords = new ArrayList<String>();

    private static void findSimilarWords() {
        String keyword = wordsField.getText().trim().toLowerCase();
        ArrayList<String> corpusList = Corpus.words;

        WordNetDatabase dbWordNet = WordNetDatabase.getFileInstance();

        for (int i = 0; i < corpusList.size(); i++) {
            boolean hasPrevWord = (i - 1) > 0;
            boolean hasNextWord = (i + 1 < corpusList.size());

            String currentWord = corpusList.get(i).toString().trim().toLowerCase();
            String prevWord = hasPrevWord ? corpusList.get(i - 1).toString() : "";
            String nextWord = hasNextWord ? corpusList.get(i + 1).toString() : "";

            if (currentWord.equals(keyword)) {
                String stemmed = "";

                /*  CHECK PREVIOUS WORD */
                if (hasPrevWord) {
                    stemmed = dbWordNet.getSynsets(prevWord.toLowerCase(), SynsetType.NOUN).toString();
                    stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                    if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(prevWord.toLowerCase()) && prevWord.length() >= 3) {
                        if (!similarWords.contains(prevWord + " " + keyword)) {
                            similarWords.add(prevWord + " " + keyword);
                        }
                    }
                }

                /*  CHECK NEXT WORD */
                if (hasNextWord) {
                    stemmed = dbWordNet.getSynsets(nextWord.toLowerCase(), SynsetType.NOUN).toString();
                    stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                    if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(nextWord.toLowerCase()) && nextWord.length() >= 3) {
                        if (!similarWords.contains(keyword + " " + nextWord)) {
                            similarWords.add(keyword + " " + nextWord);
                        }
                    }
                }
            }
        }
        
        /* DATABIND SIMILAR WORDS LIST */
        DefaultListModel similarWordsOfKeywordsModel = new DefaultListModel();
        
        for(String s : similarWords){
            similarWordsOfKeywordsModel.addElement(s);
        }
        
        Form_domain_expert.similarWordsList.setModel(similarWordsOfKeywordsModel);
    }

    @Override
    public String doInBackground() throws Exception {
        Form_domain_expert.pbofkeyword.setIndeterminate(true);
        System.out.println(" START : SEARCH SIMILAR WORDS OF KEYWORD ");
        findSimilarWords();
        System.out.println(" END : SEARCH SIMILAR WORDS OF KEYWORD ");
        Form_domain_expert.pbofkeyword.setIndeterminate(false);
        return "";
    }
}
