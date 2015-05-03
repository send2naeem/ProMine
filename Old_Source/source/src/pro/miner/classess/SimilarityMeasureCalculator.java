/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import forms.Form_domain_expert;
import pro.minor.wordnet.similarity.DepthFinder;
import pro.minor.wordnet.similarity.PathFinder;

/**
 *
 * @author Administrator
 */
public class SimilarityMeasureCalculator implements Runnable {

    public static ArrayList<Double> similarityMeasureList = new ArrayList<>();
    public static DefaultListModel similarityMeasureModel = new DefaultListModel();

    private static ISynset getWordNetSynset(String _word, IDictionary dict) {
        IIndexWord idxWord = dict.getIndexWord(_word, POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);
        return word.getSynset();
    }

    @Override
    public void run() {
        Form_domain_expert.pbofkeyword.setIndeterminate(true);
        double constant = 1;
        // iterate over the merged list
        for (Entity e : Global.entities) {

            try {
                String word = e.word;

                double similarity_measure = 0; /* Depth * log (path_length) * constant * information_gain */

                double information_gain = e.information_gain;

                String[] arr = word.split(" ");
                
                double depth_1 = DepthFinder.findSynsetDepth(arr[0], 2);
                double depth_2 = DepthFinder.findSynsetDepth(arr[1], 2);
                
                double path = PathFinder.findPathLength(arr[0], arr[1]);
                               
                
                double depth = (depth_1 > depth_2)  ? (depth_1 - depth_2) : (depth_2 - depth_1);
                //depth_1 = depth_1 > 0 ? depth_1 : 1;

                similarity_measure = depth * constant * Math.log(path) * information_gain;

                similarityMeasureList.add(similarity_measure);               
                e.similarity_measure = similarity_measure;
            } catch (Exception ex) {
            }
        }
        
        for (int iterator = 0; iterator < similarityMeasureList.size(); iterator++) {
            similarityMeasureModel.add(iterator, similarityMeasureList.get(iterator));
        }                
        
        Form_domain_expert.pbofkeyword.setIndeterminate(false);
        Form_domain_expert.similarityMeasureList.setModel(similarityMeasureModel);
    }
}
