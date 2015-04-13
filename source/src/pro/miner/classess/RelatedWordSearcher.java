/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import forms.Form_domain_expert;

/**
 *
 * @author Farooq
 */
public class RelatedWordSearcher extends SwingWorker {

    public static ArrayList<String> relatedWords = new ArrayList<String>();

    private static void findRelatedWords() {        
        ArrayList<String> corpusList = Corpus.words;
        WordNetDatabase dbWordNet = WordNetDatabase.getFileInstance();

        for (int k = 0; k < Form_domain_expert.relatedList.getModel().getSize(); k++) {
            
            String keyword = Form_domain_expert.relatedList.getModel().getElementAt(k).toString().trim().toLowerCase();
            
            for (int i = 0; i < corpusList.size(); i++) {
                boolean hasPrevWord = (i - 1) > 0;
                boolean hasNextWord = (i + 1 < corpusList.size());

                String currentWord = corpusList.get(i).toString().trim().toLowerCase();
                String prevWord = hasPrevWord ? corpusList.get(i - 1).toString() : "";
                String nextWord = hasNextWord ? corpusList.get(i + 1).toString() : "";

                if (currentWord.equals(keyword)) {
                    String stemmed = "";

                    /*  CHECK PREVIOUS WORD */
                    if (hasPrevWord && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(prevWord.toLowerCase())) {
                        stemmed = dbWordNet.getSynsets(prevWord.toLowerCase(), SynsetType.NOUN).toString();
                        stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                        if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(prevWord.toLowerCase()) && prevWord.length() >= 3) {
                            if (!relatedWords.contains(prevWord + " " + keyword)) {
                                relatedWords.add(prevWord + " " + keyword);
                            }
                        }
                    }

                    /*  CHECK NEXT WORD */
                    if (hasNextWord && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(nextWord.toLowerCase())) {
                        stemmed = dbWordNet.getSynsets(nextWord.toLowerCase(), SynsetType.NOUN).toString();
                        stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                        if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(nextWord.toLowerCase()) && nextWord.length() >= 3) {
                            if (!relatedWords.contains(keyword + " " + nextWord)) {
                                relatedWords.add(keyword + " " + nextWord);
                            }
                        }
                    }
                }
            }
        }

        /* DATABIND RELATED WORDS LIST */
        DefaultListModel relatedWordsKeywordsModel = new DefaultListModel();

        for (String s : relatedWords) {
            relatedWordsKeywordsModel.addElement(s);
        }

        Form_domain_expert.similarWordsOfRelatedWordsList.setModel(relatedWordsKeywordsModel);
    }

    @Override
    public String doInBackground() throws Exception {
        Form_domain_expert.pbofwordnet.setIndeterminate(true);
        System.out.println(" START : SEARCH SIMILAR WORDS FOR RELATED WORDS ");
        findRelatedWords();
        System.out.println(" END : SEARCH SIMILAR WORDS FOR RELATED WORDS ");
        Form_domain_expert.pbofwordnet.setIndeterminate(false);
        
        Form_domain_expert.mergeButton.setEnabled(true);
        return "";
    }
}
