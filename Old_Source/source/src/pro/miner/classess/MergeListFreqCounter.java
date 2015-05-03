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
import forms.Form_domain_expert;

/**
 *
 * @author Farooq
 */
public class MergeListFreqCounter extends SwingWorker {

    public static HashMap<String, String> mergeListFreq = new HashMap<String, String>();
    public static HashMap<String, String> mergeListProb = new HashMap<String, String>();
    public static DefaultListModel list1_list2_freq_model = new DefaultListModel();

    public static void countFreq() {
        Form_domain_expert.pbofwordnet.setIndeterminate(true);

        list1_list2_freq_model.removeAllElements();
        Form_domain_expert.list1_list2_freq.removeAll();

        mergeListFreq.clear();
        mergeListProb.clear();

        for (int i = 0; i < Form_domain_expert.list1_list2.getModel().getSize(); i++) {
            String word = Form_domain_expert.list1_list2.getModel().getElementAt(i).toString();
            fillProbAndFreq(word);
            list1_list2_freq_model.addElement(Double.parseDouble(mergeListFreq.get(word).toString()));
        }

        Form_domain_expert.pbofwordnet.setIndeterminate(false);
        Form_domain_expert.list1_list2_freq.setModel(list1_list2_freq_model);
    }

    private static double fillProbAndFreq(String input_word) {
        double prob = 0;
        double count_of_word = 0;
        double total_words = 0;
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
            //line = removeStopWordsFromLine(Global.file_text);

            /* REMOVAL OF STOP WORDS :: WORDNET */
            //List<String> tempList = removeWordNetStopWords(line);
            //line = StringUtils.join(tempList, " ");
            line = Global.file_text;

            String[] word_array = input_word.split("\\s");
            count_of_word += countWordFreq(StringUtils.join(word_array, "(\\s+)"), line);
            total_words += CountWords(line);
            //System.out.println("Count :" + count_of_word);
            System.out.println("Total Frequency:" + total_words);

            prob = count_of_word / total_words;

            mergeListFreq.put(input_word, Double.toString(count_of_word));
            mergeListProb.put(input_word, Double.toString(prob));

            //System.out.println("Probability : " + prob);
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

    @Override
    protected String doInBackground() throws Exception {
        countFreq();
        return "";
    }
}
