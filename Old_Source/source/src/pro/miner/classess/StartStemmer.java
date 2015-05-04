/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import static pro.miner.classess.RemoveStopWord.afterstopwordlist;
import static pro.miner.classess.RemoveStopWord.stemlist;
import pro.minor.utility.Settings;

/**
 *
 * @author Junaid Hassan
 */
public class StartStemmer {

    /*For changing stemmer purpose*/
    Stemmer s = new Stemmer();
    public static String allstemmedtext;
    public static DefaultListModel wordsmodel = new DefaultListModel();
    public static DefaultListModel freqmodel = new DefaultListModel();

    public void Starter() {
        char[] stemmingWord = new char[25];
        char ch;
        
        stemlist = new ArrayList<String>();
        for (int i = 0; i < afterstopwordlist.size(); i++) {            
            /* removed Stemmer On Instructions */
            //s.Stem(afterstopwordlist.get(i));
            
            stemlist.add(afterstopwordlist.get(i));
        }        
        
        System.out.println("RUNNING :: LEMMATIZER");
        ArrayList lemmatizedList = new ArrayList();
        for (String s : stemlist) {
            String validNoun = Tagger.GetNoun(s);  
            if(validNoun.length() > 2)
             lemmatizedList.add(validNoun != "" ? validNoun : s);
        }
        System.out.println("LEMMATIZER COMPLETED");
        
        stemlist = lemmatizedList;
        
        System.out.println("RUNNING :: COUNT FREQUENCY");
        countFrequency();
        System.out.println("COUNT FREQUENCY COMPLETED");
        System.out.println("SORT :: RUNNING");        
        sortWholeList();
        System.out.println("SORT :: COMPLETED");        
    }
    
    public void SaveWords(){
        
    }            

    public void sortWholeList() {
        BubbleSort bblsort = new BubbleSort();
        bblsort.desc_sorting();
    }

    public void countFrequency() {
        for (int i = 0; i < stemlist.size(); i++) {
            allstemmedtext += stemlist.get(i) + " ";
        }
        String[] keys = allstemmedtext.split(" ");
        String[] uniqueKeys;
        int count = 0;
        System.out.println(allstemmedtext);
        uniqueKeys = getUniqueKeys(keys);

        for (String key : uniqueKeys) {
            if (null == key) {
                break;
            }
            for (String s : keys) {
                if (key.equals(s)) {
                    count++;
                }
            }
            System.out.println("Key checking point : " + key);
            System.out.println("Count of [" + key + "] is : " + count);

            if (count >= Settings.text_mining_import_threshold_frequency) {
                wordsmodel.addElement(key);
                freqmodel.addElement(count);
            }

            count = 0;
        }
    }

    private static String[] getUniqueKeys(String[] keys) {
        String[] uniqueKeys = new String[keys.length];

        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;

        for (int i = 1; i < keys.length; i++) {
            for (int j = 0; j <= uniqueKeyIndex; j++) {
                if (keys[i].equals(uniqueKeys[j])) {
                    keyAlreadyExists = true;
                }
            }

            if (!keyAlreadyExists) {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;
            }
            keyAlreadyExists = false;
        }
        return uniqueKeys;
    }
}
