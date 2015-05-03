/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Farooq
 */
public class Corpus {

    public static ArrayList<String> words = new ArrayList<String>();
    
    public static void fillPreProcessedWordList(File filePath) throws IOException {
    
        boolean fileFound = false;
        String line = "";
        BufferedReader fileReader = null;
        
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            fileFound = (fileReader != null) ? true : false;
        } catch (Exception e) {
        }

        if (fileFound) {
            while ((line = fileReader.readLine()) != null) {
                Global.file_text += line;  
            }
            
            List myList = new ArrayList();
            Collections.addAll(myList, Global.file_text.split(" "));            
            words.addAll(myList);
        } else {
            System.out.println("Glossary file not found. Please load a valid corpus file and try again.");
        }
    }

    public static void fillWordList(File filePath) throws IOException {
        boolean fileFound = false;
        String line = "";
        BufferedReader fileReader = null;

        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            fileFound = (fileReader != null) ? true : false;
        } catch (Exception e) {
        }

        if (fileFound) {
            while ((line = fileReader.readLine()) != null) {

                if (line.trim().length() > 0) {                                       
                    
                    /* REMOVAL OF STOP WORDS :: GENERAL */
                    line = removeStopWordsFromLine(line);

                    /* REMOVAL OF STOP WORDS :: WORDNET */
                    List<String> tempList = removeWordNetStopWords(line);

                    /* TODO : PERFORM STEMMING HERE IF NEEDED */

                    /* ADDITION OF SAFE WORDS TO CORPUS DICTIONARY */
                    words.addAll(tempList);
                    
                    Global.file_text += StringUtils.join(tempList, " ") + " ";                    
                }
            }
        } else {
            System.out.println("Glossary file not found. Please load a valid corpus file and try again.");
        }
    }

    private static String removeStopWordsFromLine(String inputLine) {
        inputLine = inputLine.toLowerCase()
                .replace("also", " ").replace("most", " ").replace("be", " ")
                .replace("more", " ").replace("each", " ").replace("have", " ")
                .replace("may", " ").replace("has", " ").replace("other", " ")
                .replace("the", " ").replace("more", " ").replace("that", " ");
                //.replace("ing", " ");

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
}
