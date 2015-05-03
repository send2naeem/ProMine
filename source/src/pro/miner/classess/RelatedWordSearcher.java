/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import forms.Form_domain_expert;
import static pro.miner.classess.SimilarWordSearcher.similarWords;

/**
 *
 * @author Farooq
 */
public class RelatedWordSearcher extends SwingWorker {

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

    public static ArrayList<String> relatedWords = new ArrayList<String>();

    private static void findRelatedWordsFromSynonyms() {
        //ArrayList<String> corpusList = Corpus.words;
        //WordNetDatabase dbWordNet = WordNetDatabase.getFileInstance();

        for (int k = 0; k < Form_domain_expert.relatedList.getModel().getSize(); k++) {
            String keyword = Form_domain_expert.relatedList.getModel().getElementAt(k).toString().trim().toLowerCase();
            for (int i = 0; i < Corpus.words.size(); i++) {
                String currentWord = Corpus.words.get(i).trim().toLowerCase();
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
                    if (!relatedWords.contains(keyword)) {
                        relatedWords.add(keyword);
                    }

                    if (prevFirstWordIsNoun) {
                        if (!relatedWords.contains(prevFirstWord + " " + keyword)) {
                            relatedWords.add(prevFirstWord + " " + keyword);
                        }
                    }//End if

                    if (nextFirstWordIsNoun) {
                        if (!relatedWords.contains(keyword + " " + nextFirstWord)) {
                            relatedWords.add(keyword + " " + nextFirstWord);
                        }
                    }

                    if (prevFirstWordIsNoun && nextFirstWordIsNoun) {
                        if (!relatedWords.contains(prevFirstWord + " " + keyword + " " + nextFirstWord)) {
                            relatedWords.add(prevFirstWord + " " + keyword + " " + nextFirstWord);
                        }
                    }

                    if (prevFirstWordIsNoun && prevSecondWordIsNoun) {
                        if (!relatedWords.contains(prevSecondWord + " " + prevFirstWord + " " + keyword)) {
                            relatedWords.add(prevSecondWord + " " + prevFirstWord + " " + keyword);
                        }
                    }

                    if (nextFirstWordIsNoun && nextSecondWordIsNoun) {
                        if (!relatedWords.contains(keyword + " " + nextFirstWord + " " + nextSecondWord)) {
                            relatedWords.add(keyword + " " + nextFirstWord + " " + nextSecondWord);
                        }
                    }
                    //--------------------------------------------------------------

                    /*
                     boolean hasPrevWord = (i - 1) > 0;
                     boolean hasNextWord = (i + 1 < Corpus.words.size());

                     String prevWord = hasPrevWord ? Corpus.words.get(i - 1) : "";
                     String nextWord = hasNextWord ? Corpus.words.get(i + 1) : "";

                     String stemmed = "";

                     /*  CHECK PREVIOUS WORD *
                     if (hasPrevWord && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(prevWord.toLowerCase())) {
                     stemmed = dbWordNet.getSynsets(prevWord.toLowerCase(), SynsetType.NOUN).toString();
                     stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                     if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(prevWord.toLowerCase()) && prevWord.length() >= 3) {
                     if (!relatedWords.contains(prevWord + " " + keyword)) {
                     relatedWords.add(prevWord + " " + keyword);
                     }
                     }
                     }

                     /*  CHECK NEXT WORD *
                     if (hasNextWord && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(nextWord.toLowerCase())) {
                     stemmed = dbWordNet.getSynsets(nextWord.toLowerCase(), SynsetType.NOUN).toString();
                     stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                     if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(nextWord.toLowerCase()) && nextWord.length() >= 3) {
                     if (!relatedWords.contains(keyword + " " + nextWord)) {
                     relatedWords.add(keyword + " " + nextWord);
                     }
                     }
                     }
                     */
                }
            }
        }
    }

    private static void findRelatedWordsFromWiktionaryDerivedandArovoc() {

        for (int k = 0; k < Form_domain_expert.derivedList.getModel().getSize(); k++) {
            String keyword = Form_domain_expert.derivedList.getModel().getElementAt(k).toString().trim().toLowerCase();
            for (int i = 0; i < Corpus.words.size(); i++) {
                String currentWord = Corpus.words.get(i).trim().toLowerCase();
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
                    if (!relatedWords.contains(keyword)) {
                        relatedWords.add(keyword);
                    }

                    if (prevFirstWordIsNoun) {
                        if (!relatedWords.contains(prevFirstWord + " " + keyword)) {
                            relatedWords.add(prevFirstWord + " " + keyword);
                        }
                    }//End if

                    if (nextFirstWordIsNoun) {
                        if (!relatedWords.contains(keyword + " " + nextFirstWord)) {
                            relatedWords.add(keyword + " " + nextFirstWord);
                        }
                    }

                    if (prevFirstWordIsNoun && nextFirstWordIsNoun) {
                        if (!relatedWords.contains(prevFirstWord + " " + keyword + " " + nextFirstWord)) {
                            relatedWords.add(prevFirstWord + " " + keyword + " " + nextFirstWord);
                        }
                    }

                    if (prevFirstWordIsNoun && prevSecondWordIsNoun) {
                        if (!relatedWords.contains(prevSecondWord + " " + prevFirstWord + " " + keyword)) {
                            relatedWords.add(prevSecondWord + " " + prevFirstWord + " " + keyword);
                        }
                    }

                    if (nextFirstWordIsNoun && nextSecondWordIsNoun) {
                        if (!relatedWords.contains(keyword + " " + nextFirstWord + " " + nextSecondWord)) {
                            relatedWords.add(keyword + " " + nextFirstWord + " " + nextSecondWord);
                        }
                    }
                    //--------------------------------------------------------------

                    /*
                     boolean hasPrevWord = (i - 1) > 0;
                     boolean hasNextWord = (i + 1 < Corpus.words.size());

                     String prevWord = hasPrevWord ? Corpus.words.get(i - 1) : "";
                     String nextWord = hasNextWord ? Corpus.words.get(i + 1) : "";

                     String stemmed = "";

                     /*  CHECK PREVIOUS WORD *
                     if (hasPrevWord && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(prevWord.toLowerCase())) {
                     stemmed = dbWordNet.getSynsets(prevWord.toLowerCase(), SynsetType.NOUN).toString();
                     stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                     if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(prevWord.toLowerCase()) && prevWord.length() >= 3) {
                     if (!relatedWords.contains(prevWord + " " + keyword)) {
                     relatedWords.add(prevWord + " " + keyword);
                     }
                     }
                     }

                     /*  CHECK NEXT WORD *
                     if (hasNextWord && !Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(nextWord.toLowerCase())) {
                     stemmed = dbWordNet.getSynsets(nextWord.toLowerCase(), SynsetType.NOUN).toString();
                     stemmed = stemmed.replace("]", "").replace("[", "").replace(",", "");
                     if (stemmed.trim().length() > 0 && Tagger.IsValidNoun(nextWord.toLowerCase()) && nextWord.length() >= 3) {
                     if (!relatedWords.contains(keyword + " " + nextWord)) {
                     relatedWords.add(keyword + " " + nextWord);
                     }
                     }
                     }
                     */
                }
            }
        }
    }

    @Override
    public String doInBackground() throws Exception {
        Form_domain_expert.pbofwordnet.setIndeterminate(true);
        System.out.println(" START : SEARCH SIMILAR WORDS FOR RELATED WORDS ");

        findRelatedWordsFromSynonyms();
        findRelatedWordsFromWiktionaryDerivedandArovoc();

        /* DATABIND RELATED WORDS LIST */
        DefaultListModel relatedWordsKeywordsModel = new DefaultListModel();

        for (String s : relatedWords) {
            relatedWordsKeywordsModel.addElement(s);
        }

        Form_domain_expert.similarWordsOfRelatedWordsList.setModel(relatedWordsKeywordsModel);

        System.out.println(" END : SEARCH SIMILAR WORDS FOR RELATED WORDS ");
        Form_domain_expert.pbofwordnet.setIndeterminate(false);

        Form_domain_expert.mergeButton.setEnabled(true);
        return "";
    }
}
