/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 *
 * @author Farooq
 */
public class Tagger {

    public static MaxentTagger tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");
    
    public static boolean IsValidNoun(String word) {
        try {            
            if(Tagger.tagger == null)
                Tagger.tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");

            String tagged = tagger.tagString(word);
            tagged = tagged.trim();
            return (tagged.endsWith("_NN") || tagged.endsWith("_NNP"));

        } catch (Exception e) {
            System.out.println("TAGGED WORD EXCEPTION: " + e.getMessage());
        }
        
        return false;

    }
    
    public static String GetNoun(String word) {
        try {            
            if(Tagger.tagger == null)
                Tagger.tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");

            String tagged = tagger.tagString(word);
            tagged = tagged.trim();
            if (tagged.endsWith("_NN") || tagged.endsWith("_NNP"))
            {
                return tagged.split("_")[0];
            }
            else
            {
                return "";
            }

        } catch (Exception e) {
            System.out.println("TAGGED WORD EXCEPTION: " + e.getMessage());
        }
        
        return "";

    }
}
