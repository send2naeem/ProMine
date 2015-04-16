/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.stanford.nlp.util.StringUtils;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import forms.Form_domain_expert;
import static forms.Form_domain_expert.wordsField;
import java.util.Arrays;

/**
 *
 * @author Farooq
 */
public class SimilarWordSearcher extends SwingWorker {

    private static Synset[] GetStemmedWord(String _word) {
        WordNetDatabase dbWordNet = WordNetDatabase.getFileInstance();
        
        Synset[] synsets;
        synsets = dbWordNet.getSynsets(_word.toLowerCase(), SynsetType.NOUN);
        return synsets;
        
        //String stemmed;
        //stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
        //stemmed = stemmed.trim();
        //return stemmed;
    }

    private static boolean IsWordNoun(String _word) {
        return Tagger.IsValidNoun(_word.toLowerCase()) && _word.length() >= 3;
    }

    public static ArrayList<String> similarWords = new ArrayList<>();

    private static void findSimilarWords() {
        String keyword = wordsField.getText().trim().toLowerCase();

        for (int i = 0; i < Corpus.words.size(); i++) {

            String currentWord = Corpus.words.get(i).trim().toLowerCase();

            //Check the current word either match with the keyword
            if (currentWord.equals(keyword)) {

                boolean hasPrevFirstWord = (i - 1) > 0;
                boolean hasNextFirstWord = (i + 1 < Corpus.words.size());
                boolean hasPrevSecondWord = (i - 2) > 0;
                boolean hasNextSecondWord = (i + 2 < Corpus.words.size());

                //Get the previous first and second words
                String prevFirstWord = hasPrevFirstWord ? Corpus.words.get(i - 1) : "";
                String prevSecondWord = hasPrevSecondWord ? Corpus.words.get(i - 2) : "";
                //Get the next first and second words
                String nextFirstWord = hasNextFirstWord ? Corpus.words.get(i + 1) : "";
                String nextSecondWord = hasNextSecondWord ? Corpus.words.get(i + 2) : "";

                //String prevFirstStemmedWord = "";
                //String prevSecondStemmedWord = "";
                //String nextFirstStemmedWord = "";
                //String nextSecondStemmedWord = "";

                boolean prevFirstWordIsNoun = false;
                boolean prevSecondWordIsNoun = false;
                boolean nextFirstWordIsNoun = false;
                boolean nextSecondWordIsNoun = false;

                if (!"".equals(prevFirstWord) && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(prevFirstWord.toLowerCase())) {
                    //prevFirstStemmedWord = GetStemmedWord(prevFirstWord);
                    //if (!"".equals(prevFirstStemmedWord)) {
                        prevFirstWordIsNoun = IsWordNoun(prevFirstWord);
                    //}
                }//End if

                if (!"".equals(prevSecondWord) && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(prevSecondWord.toLowerCase())) {
                    //prevSecondStemmedWord = GetStemmedWord(prevSecondWord);
                    //if (!"".equals(prevSecondStemmedWord)) {
                        prevSecondWordIsNoun = IsWordNoun(prevSecondWord);
                    //}
                }//End if

                if (!"".equals(nextFirstWord) && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(nextFirstWord.toLowerCase())) {
                    //nextFirstStemmedWord = GetStemmedWord(nextFirstWord);
                    //if (!"".equals(nextFirstStemmedWord)) {
                        nextFirstWordIsNoun = IsWordNoun(nextFirstWord);
                    //}
                }//End if

                if (!"".equals(nextSecondWord) && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(nextSecondWord.toLowerCase())) {
                    //nextSecondStemmedWord = GetStemmedWord(nextSecondWord);
                    //if (!"".equals(nextSecondStemmedWord)) {
                        nextSecondWordIsNoun = IsWordNoun(nextSecondWord);
                    //}
                }//End if

                //--------------------------------------------------------------
                if(!similarWords.contains(keyword)){
                    similarWords.add(keyword);
                }
                
                if (prevFirstWordIsNoun) {
                    if (!similarWords.contains(prevFirstWord + " " + keyword)) {
                        similarWords.add(prevFirstWord + " " + keyword);
                    }
                }//End if

                if (nextFirstWordIsNoun) {
                    if (!similarWords.contains(keyword + " " + nextFirstWord)) {
                        similarWords.add(keyword + " " + nextFirstWord);
                    }
                }
                
                if(prevFirstWordIsNoun && nextFirstWordIsNoun){
                     if (!similarWords.contains(prevFirstWord + " " + keyword + " " + nextFirstWord)) {
                        similarWords.add(prevFirstWord + " " + keyword + " " + nextFirstWord);
                    }
                }
                
                if(prevFirstWordIsNoun && prevSecondWordIsNoun){
                    if (!similarWords.contains(prevSecondWord + " " + prevFirstWord  + " " + keyword)) {
                        similarWords.add(prevSecondWord + " " + prevFirstWord  + " " + keyword);
                    }
                }
                
                if(nextFirstWordIsNoun && nextSecondWordIsNoun){
                    if (!similarWords.contains(keyword + " " + nextFirstWord + " " + nextSecondWord)) {
                        similarWords.add(keyword + " " + nextFirstWord + " " + nextSecondWord);
                    }
                }
                //--------------------------------------------------------------
                
                /*
                 String stemmed = "";

                 /*  CHECK PREVIOUS WORD *
                 if (hasPrevFirstWord) {
                 stemmed = dbWordNet.getSynsets(prevFirstWord.toLowerCase(), SynsetType.NOUN).toString();
                 stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                 if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(prevFirstWord.toLowerCase()) && prevFirstWord.length() >= 3) {
                 if (!similarWords.contains(prevFirstWord + " " + keyword)) {
                 similarWords.add(prevFirstWord + " " + keyword);
                 }
                 }
                 }

                 /*  CHECK NEXT WORD *
                 if (hasNextFirstWord) {
                 stemmed = dbWordNet.getSynsets(nextFirstWord.toLowerCase(), SynsetType.NOUN).toString();
                 stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                 if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(nextFirstWord.toLowerCase()) && nextFirstWord.length() >= 3) {
                 if (!similarWords.contains(keyword + " " + nextFirstWord)) {
                 similarWords.add(keyword + " " + nextFirstWord);
                 }
                 }
                 }
                 */
            }
        }

        /* DATABIND SIMILAR WORDS LIST */
        DefaultListModel similarWordsOfKeywordsModel = new DefaultListModel();

        for (String s : similarWords) {
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
