/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import org.apache.commons.lang3.StringUtils;
import static pro.miner.classess.GlossarySearcher.file;
import static pro.miner.classess.MergeListFreqCounter.countFreq;
import static pro.miner.classess.MergeListFreqCounter.countWordFreq;
import static pro.miner.classess.MergeListFreqCounter.list1_list2_freq_model;
import static pro.miner.classess.MergeListFreqCounter.mergeListFreq;
import static pro.miner.classess.MergeListFreqCounter.mergeListProb;
import forms.Form_domain_expert;

/**
 *
 * @author Farooq
 */
public class EntropyOfSynonyms extends SwingWorker {

    public static HashMap<String, String> EntropyOfSimilarWord = new HashMap<String, String>();
    public static DefaultListModel entropy_list_model = new DefaultListModel();
    public static double total_entropy = 0;
    public static int word_count_in_corpus = 0;

    @Override
    protected String doInBackground() throws Exception {
        countEntropy();
        return "";
    }
    
    public static void countEntropy() {
        Form_domain_expert.pbofwordnet.setIndeterminate(true);

        entropy_list_model.clear();
        Form_domain_expert.entropyList.removeAll();

        for (int i = 0; i < Form_domain_expert.relatedList.getModel().getSize(); i++) {

            try {

                //Get the word from list
                String word = Form_domain_expert.relatedList.getModel().getElementAt(i).toString();

                //Compute probability of the word
                fillEntropy(word);

                double prob = Double.parseDouble(EntropyOfSimilarWord.get(word).toString());
                double log_prob = Math.log(prob);
                double entropy = prob > 0 ? prob * log_prob : 0;

                //total_entropy += entropy;
                //System.out.println("=========================");
                //System.out.println("=========================Entropy  Prob [" + word + "]: " + prob);
                //System.out.println("=========================Entropy  Prob Log[" + word + "]: " + log_prob);
                System.out.println("=========================Entropy [" + word + "]: " + entropy);
                //System.out.println("=========================");

                total_entropy = total_entropy + entropy;

                entropy_list_model.addElement(entropy);
            } catch (Exception e) {
                System.out.println("Exception occured while computing entroy, exception: " + e.getMessage());
            }
        }

        Form_domain_expert.pbofwordnet.setIndeterminate(false);
        Form_domain_expert.entropyList.setModel(entropy_list_model);

//        for (int i = 0; i < Form_domain_expert.entropyList.getModel().getSize(); i++) {
//            String entropy_item = Form_domain_expert.entropyList.getModel().getElementAt(i).toString();
//            double entropy_double_item = Double.parseDouble(entropy_item);
//            total_entropy += entropy_double_item;
//        }
//        System.out.println("Total Entropy : " + total_entropy);
    }

    private static double fillEntropy(String input_word) {
        double prob = 0;
        double count_of_word = 0;
        //double total_words = 0;
        try {
            Form_domain_expert.pbofwordnet.setIndeterminate(true);
            //BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

//            while ((line = br.readLine()) != null) {
//                if (!line.isEmpty()) {
//                    /* REMOVAL OF STOP WORDS :: GENERAL */
//                    line = removeStopWordsFromLine(line);
//
//                    /* REMOVAL OF STOP WORDS :: WORDNET */
//                    List<String> tempList = removeWordNetStopWords(line);
//                    
//                    line = StringUtils.join(tempList, " ");
//                    
//                    String[] word_array = input_word.split("\\s");
//                    count_of_word += countWordFreq(StringUtils.join(word_array, "(\\s+)"), line);
//                    total_words += CountWords(line);
//                }
//            }
//
//            br.close();
            //System.out.println("Count :" + count_of_word);
            line = Global.file_text;

            String[] word_array = input_word.split("\\s");
            count_of_word = countWordFreq(StringUtils.join(word_array, "(\\s+)"), line);
            if (word_count_in_corpus == 0) {
                word_count_in_corpus = CountWords(line);
            }

            //Get the probability of word in corpus file
            prob = count_of_word / word_count_in_corpus;

            EntropyOfSimilarWord.put(input_word, Double.toString(prob));
            Form_domain_expert.pbofwordnet.setIndeterminate(false);
            return prob;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int countWordFreq(String word, String line) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(line);
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        return counter;
    }

    private static int CountWords(String in) {
        String trim = in.trim();
        if (trim.isEmpty()) {
            return 0;
        }
        return trim.split("\\s+").length; //separate string around spaces
    }

    private static String removeStopWordsFromLine(String inputLine) {
        inputLine = inputLine.toLowerCase()
                .replace("also", " ").replace("most", " ").replace("be", " ")
                .replace("more", " ").replace("each", " ").replace("have", " ")
                .replace("may", " ").replace("has", " ").replace("other", " ")
                .replace("the", " ").replace("more", " ").replace("that", " ");

        inputLine = inputLine.toLowerCase()
                .replace("@", " ")
                .replace("[", " ")
                .replace("�", " ")
                .replace("=", " ")
                .replace("+", " ")
                .replace("?", " ")
                .replace("&", " ")
                .replace("%", " ")
                .replace("$", " ")
                .replace(" ", " ")
                .replace("<", " ")
                .replace(">", " ")
                .replace("]", " ")
                .replace("*", " ")
                .replace("_", " ")
                .replace("{", " ")
                .replace("}", " ")
                .replace("#", " ")
                .replace(":", " ")
                .replace("•", " ")
                .replace("\n", " ")
                .replace("\u0092", " ")
                .replace("'", " ")
                .replace("•", " ")
                .replace("--", " ") // replaced double hipehn first in order to save single hiphen words
                .replace("’", " ")
                .replace("\n", " ")
                //.replace(".", " ")
                .replace("(", " ")
                .replace(")", " ")
                //.replace(",", " ")
                .replace(";", " ")
                .replace("“", " ")
                .replace("\\", " ")
                .replace("/", " ")
                .replace("”", " ")
                .replace("–", "") // in order to get words like "multi-national" => "multinational"
                .replace("1", " ")
                .replace("2", " ")
                .replace("3", " ")
                .replace("4", " ")
                .replace("5", " ")
                .replace("6", " ")
                .replace("7", " ")
                .replace("8", " ")
                .replace("9", " ")
                .replace("0", " ");

        inputLine = inputLine.trim().replaceAll("\\s+", " ");

        return inputLine;
    }

    private static List<String> removeWordNetStopWords(String inputLine) {
        List<String> tempList = new ArrayList<String>();
        try {
            for (int i = 0; i < Arrays.asList(inputLine.split(" ")).size(); i++) {
                String word = Arrays.asList(inputLine.split(" ")).get(i).toString();
                if (!Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(word)) {
                    tempList.add(word);
                }
            }
        } catch (Exception e) {
        }

        return tempList;
    }

    
}
