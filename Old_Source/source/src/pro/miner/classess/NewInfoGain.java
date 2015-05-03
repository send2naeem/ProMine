/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import org.apache.commons.lang3.StringUtils;
import static pro.miner.classess.GlossarySearcher.alltext;
import static pro.miner.classess.GlossarySearcher.file;
import static pro.miner.classess.InformationGain.InfoGainModel;
import static pro.miner.classess.InformationGain.infoGainList;
import forms.Form_domain_expert;
import forms.Form_text_mining;

/**
 *
 * @author Farooq
 */
public class NewInfoGain implements Runnable {

    public static ArrayList<Double> infoGainList = new ArrayList<Double>();
    public static DefaultListModel InfoGainModel = new DefaultListModel();
    public static ArrayList<String> synonymsCorpus = new ArrayList<String>();

    @Override
    public void run() {
        int mergedListSize = Form_domain_expert.list1_list2.getModel().getSize();

        synonymsCorpus.clear();
        infoGainList.clear();

        // create synonym corpus list
        for (int i = 0; i < Form_domain_expert.relatedList.getModel().getSize(); i++) {
            //System.out.println("Printing Synonyms : " + Form_domain_expert.relatedList.getModel().getElementAt(i).toString());
            synonymsCorpus.add(Form_domain_expert.relatedList.getModel().getElementAt(i).toString());
        }

        Global.entities.clear();
        for (int i = 0; i < mergedListSize; i++) {
            Entity e = new Entity();
//        for (int i = 0; i < 1; i++) {
            double informationGain = 0;
            double total_entropy = EntropyOfSynonyms.total_entropy;
            double probability_in_merge = Double.parseDouble(Form_domain_expert.mergeListProb.getModel().getElementAt(i).toString());
            
            

            boolean isKeyWordOnLeft = false;
            boolean isKeyWordOnRight = false;

            // find T
            String t = "";
            String compound_word = Form_domain_expert.list1_list2.getModel().getElementAt(i).toString();
            String[] compound_word_arr = compound_word.split(" ");

            e.word = compound_word;
            e.probability = probability_in_merge;
            
            if (compound_word_arr.length >= 2) {
                if (!synonymsCorpus.contains(compound_word_arr[0].toString())) {
                    isKeyWordOnRight = true;
                    t = compound_word_arr[0].toString();
                } else if (!synonymsCorpus.contains(compound_word_arr[1].toString())) {
                    isKeyWordOnLeft = true;
                    t = compound_word_arr[1].toString();
                }
            }

            //System.out.println("Current Word[T] :" + t);
            double prob_t = getProbabilityInCorpus(t);

            // calculate prob of t against each word in corpus
            double sum_t_with_other = 0;
            for (String corpus_word : synonymsCorpus) {
                String comp_word_with_key_word_right = t + " " + corpus_word;
                String comp_word_with_key_word_left = corpus_word + " " + t;

                // prob of merged word : combination
                double prob = 0;
                double log_prob = 0;
                if (isKeyWordOnLeft) {
                    if (MergeListFreqCounter.mergeListProb.containsKey(comp_word_with_key_word_left)) {
                        prob = Double.parseDouble(MergeListFreqCounter.mergeListProb.get(comp_word_with_key_word_left).toString());
                    } else {
                        prob = getProbabilityInCorpus(comp_word_with_key_word_left);
                    }

                    log_prob = Math.log(prob);

                    sum_t_with_other += (prob > 0 ? prob * log_prob : 0);

                    System.out.println("Probability: " + prob);
                    System.out.println("Log Of Probability: " + log_prob);
                    System.out.println("Product : " + prob * log_prob);
                    System.out.println("Sum  : " + sum_t_with_other);
                    //System.out.println("Compund word of Current Word[" + t + "]: " + comp_word_with_key_word_left);
                }

                if (isKeyWordOnRight) {
                    // prob of merged word : combination
                    if (MergeListFreqCounter.mergeListProb.containsKey(comp_word_with_key_word_right)) {
                        prob = Double.parseDouble(MergeListFreqCounter.mergeListProb.get(comp_word_with_key_word_right).toString());
                    } else {
                        prob = getProbabilityInCorpus(comp_word_with_key_word_right);
                    }

                    log_prob = Math.log(prob);

                    sum_t_with_other += (prob > 0 ? prob * log_prob : 0);
                    System.out.println("Probability: " + prob);
                    System.out.println("Log Of Probability: " + log_prob);
                    System.out.println("Product : " + prob * log_prob);
                    System.out.println("Sum  : " + sum_t_with_other);
                    //System.out.println("Compund word of Current Word[" + t + "]: " + comp_word_with_key_word_right);
                }
            }

            boolean is_native_word = (SimilarWordSearcher.similarWords.contains(compound_word) || RelatedWordSearcher.relatedWords.contains(compound_word))
                    && !AgrovocSearcher.agrovocList.contains(compound_word);

            if (Form_text_mining.includeAgrovocDictionary) {
                if (is_native_word) {
                    //informationGain = total_entropy + (prob_t * sum_t_with_other);
                    informationGain = computeInformationGain(total_entropy, (prob_t * sum_t_with_other));

                    System.out.println("Total Entropy: " + total_entropy);
                    System.out.println("probability of t: " + prob_t);
                    System.out.println("sum prob : " + sum_t_with_other);
                    System.out.println("information gain: " + informationGain);
                } else {
                    informationGain = 1;
                }
            } else {
                //informationGain = total_entropy + (prob_t * sum_t_with_other);
                informationGain = computeInformationGain(total_entropy, (prob_t * sum_t_with_other));

                System.out.println("Total Entropy: " + total_entropy);
                System.out.println("probability of t: " + prob_t);
                System.out.println("sum prob : " + sum_t_with_other);
                System.out.println("information gain: " + informationGain);
            }
            
            e.information_gain = informationGain;
            
            infoGainList.add(informationGain);
            
            Global.entities.add(e);
        }

        InfoGainModel.clear();
        Form_domain_expert.infoGainList.removeAll();                
                
        
        for (int iterator = 0; iterator < infoGainList.size(); iterator++) {
            InfoGainModel.add(iterator, infoGainList.get(iterator));
        }

        System.out.println("Step : New Information Gain Set Model Finished ");

        Form_domain_expert.infoGainList.setModel(InfoGainModel);

        Form_domain_expert.pbofwordnet.setIndeterminate(false);                
    }

    private double getProbabilityInCorpus(String input_word) {
        double prob = 0;
        double count_of_word = 0;
        double total_words = 0;
        try {
            Form_domain_expert.pbofwordnet.setIndeterminate(true);
            //BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

//            while ((line = br.readLine()) != null) {
//                if (!line.isEmpty()) {
//                    line = removeStopWordFromLine(line);
//                    String[] word_array = input_word.split("\\s");
//                    count_of_word += countWordFreq(StringUtils.join(word_array, "(\\s+)"), line);
//                    total_words += CountWords(line);
//                }
//            }
//
//            br.close();
            line = Global.file_text;

            String[] word_array = input_word.split("\\s");

            count_of_word += countWordFreq(StringUtils.join(word_array, "(\\s+)"), line);
            total_words += CountWords(line);

            //System.out.println("Count :" + count_of_word);
            prob = count_of_word / total_words;

            //System.out.println("Probability : " + prob);
            Form_domain_expert.pbofwordnet.setIndeterminate(false);

            return prob;
        } catch (Exception e) {
            return 0;
        }
    }

    public int countWordFreq(String word, String line) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(line);
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        return counter;
    }

    int CountWords(String in) {
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
                .replace("0", " ")
                // ROMAN COUNTING
                .replace("I", " ")
                .replace("II", " ")
                .replace("III", " ")
                .replace("IV", " ")
                .replace("V", " ")
                .replace("VI", " ")
                .replace("VII", " ")
                .replace("VIII", " ")
                .replace("IX", " ")
                .replace("X", " ");

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
    
    private double computeInformationGain(double entropy, double probability_compound){
        boolean isEntropyPositive = Form_domain_expert.jRadioButtonEntropyPositive.isSelected();
        boolean isEntropyNegative = Form_domain_expert.jRadioButtonEntropyNegative.isSelected();
        
        boolean isProbPositive = Form_domain_expert.jRadioButtonProbPositive.isSelected();
        boolean isProbNegative = Form_domain_expert.jRadioButtonProbNegative.isSelected();
        
        
        
        double informationGain = 0;
       
        if(isEntropyPositive && isProbNegative){
            informationGain = (entropy) + (probability_compound);
        }
        else if(isEntropyPositive && isProbPositive){
            informationGain = (entropy) - (probability_compound);
        }
        else if(isEntropyNegative && isProbNegative){
            informationGain = - (entropy) - (probability_compound);
        }
        else if(isEntropyNegative && isProbPositive){
            informationGain = -(entropy) + (probability_compound);
        }
        
        return informationGain;
    }
}
