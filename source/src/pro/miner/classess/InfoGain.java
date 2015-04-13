/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import org.apache.commons.lang3.StringUtils;
import static pro.miner.classess.GlossarySearcher.file;
import forms.Form_domain_expert;

/**
 *
 * @author Farooq
 */
public class InfoGain implements Runnable {

    public static ArrayList<Double> infoGainList = new ArrayList<Double>();
    public static DefaultListModel InfoGainModel = new DefaultListModel();

    @Override
    public void run() {
        int mergedListSize = Form_domain_expert.list1_list2.getModel().getSize();

        for (int i = 0; i < mergedListSize; i++) {
            double informationGain = 0;
            double entropy = Double.parseDouble(Form_domain_expert.entropyList.getModel().getElementAt(i).toString());
            double probability_in_merge = Double.parseDouble(Form_domain_expert.mergeListProb.getModel().getElementAt(i).toString());

            // calculate probability of CURRENT_WORD in corpus file            
            double prob_of_word_in_corpus = getProbabilityInCorpus(Form_domain_expert.list1_list2.getModel().getElementAt(i).toString());
            double log_prob = Math.log(prob_of_word_in_corpus);

            if (prob_of_word_in_corpus != 0) {
                informationGain = (probability_in_merge * prob_of_word_in_corpus * log_prob) - entropy;
            } else {
                informationGain = 0;
            }

            infoGainList.add(informationGain);
        }

        //Collections.sort(infoGainList, Collections.reverseOrder());
        System.out.println("Step : New Information Gain Set Model: Ieration ");
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
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    line = removeStopWordFromLine(line);
                    String[] word_array = input_word.split("\\s");
                    count_of_word += countWordFreq(StringUtils.join(word_array, "(\\s+)"), line);
                    total_words += CountWords(line);
                }
            }

            br.close();

            System.out.println("Count :" + count_of_word);
            prob = count_of_word / total_words;
            
            System.out.println("Probability : " + prob);
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

    private String removeStopWordFromLine(String input_line) {
        String output_line = "";
        output_line = input_line.replace("also", " ").replace("most", " ").replace("be", " ").replace("more", " ").replace("each", " ").replace("have", " ")
                .replace("may", " ").replace("has", " ").replace("other", " ").replace("the", " ").replace("more", " ").replace("that", " ");
        output_line = input_line.toLowerCase().replace("@", " ").replace("[", " ").replace("�", " ").replace("=", " ").replace("+", " ").replace("?", " ").replace("&", " ").replace("%", " ").replace("$", " ").replace(" ", " ").replace("<", " ").replace(">", " ").replace("]", " ").replace("*", " ").replace("_", " ").replace("{", " ").replace("}", " ").replace("#", " ").replace(":", " ").replace("•", " ").replace("\n", " ")
                .replace("\u0092", " ").replace("'", " ").replace("•", " ").replace("-", " ").replace("’", " ").replace("\n", " ")
                .replace(".", " ").replace("(", " ").replace(")", " ").replace(",", " ").replace(";", " ").replace("“", " ")
                .replace("\\", " ").replace("/", " ").replace("”", " ").replace("–", " ").replace("1", " ").replace("2", " ").replace("3", " ").replace("4", " ").replace("5", " ").replace("6", " ").replace("7", " ").replace("8", " ").replace("9", " ").replace("0", " ")
                .replace("  ", " ").replace("   ", " ").replace("     ", " ");
        return output_line;
    }
}
