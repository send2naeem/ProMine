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
import static pro.miner.classess.RemoveStopWord.stopWordsofwordnet;

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
            int fileLineNumber = 0;
            while ((line = fileReader.readLine()) != null) {

                if (line.trim().length() > 0) {

                    /* REMOVAL OF STOP WORDS :: GENERAL */
                    ArrayList<String> afterStopWordsRemoved = removeStopWordsFromLine(line);

                    /* REMOVAL OF STOP WORDS :: WORDNET */
                    ArrayList<String> afterWordNetStopWordsRemoved = removeWordNetStopWords(afterStopWordsRemoved);

                    /* TODO : PERFORM STEMMING HERE IF NEEDED */
                    ArrayList<String> afterUptoTwoWordsRemoval = removeUptoTwoCharWords(afterWordNetStopWordsRemoved);

                    /* ADDITION OF SAFE WORDS TO CORPUS DICTIONARY */
                    words.addAll(afterUptoTwoWordsRemoval);
                                      
                    Global.file_text += StringUtils.join(afterUptoTwoWordsRemoval, " ") + " ";

                    fileLineNumber++;
                    System.out.println("Reading Line Number: " + fileLineNumber);
                }
            }
        } else {
            System.out.println("Glossary file not found. Please load a valid corpus file and try again.");
        }
    }

    private static ArrayList<String> removeStopWordsFromLine(String inputLine) {
        String temp = '"' + "";
        inputLine = inputLine.toLowerCase()
                .replace("-", "")
                .replace(temp, " ")
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
                .replace(".", " . ")
                .replace("(", " ")
                .replace(")", " ")
                .replace(",", " , ")
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

        ArrayList<String> wordsList = new ArrayList<>();
        wordsList.addAll(Arrays.asList(inputLine.split(" ")));

        ArrayList<String> processedWordsList = new ArrayList();

        try {

            for (int i = 0; i < wordsList.size(); i++) {
                // get the item as string
                if (!Arrays.asList(RemoveStopWord.stopWordsSelfIdentified).contains(wordsList.get(i).replace(wordsList.get(i), " " + wordsList.get(i) + " "))) {
                    processedWordsList.add(wordsList.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while removing wordnet data");
            System.out.println(e.getMessage());
        }

        return processedWordsList;
    }

    private static ArrayList<String> removeWordNetStopWords(ArrayList<String> inputLine) {
        ArrayList<String> processedWordsList = new ArrayList();
        try {

            for (int i = 0; i < inputLine.size(); i++) {
                // get the item as string
                if (!Arrays.asList(RemoveStopWord.stopWordsofwordnet).contains(inputLine.get(i).replace(inputLine.get(i), " " + inputLine.get(i) + " "))) {
                    processedWordsList.add(inputLine.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while removing wordnet data");
            System.out.println(e.getMessage());
        }
        return processedWordsList;
    }
    
    private static ArrayList<String> removeUptoTwoCharWords(ArrayList<String> inputLine) {
        
        ArrayList<String> processedWordsList = new ArrayList();
        
        try {

            for (int i = 0; i < inputLine.size(); i++) {
                if(inputLine.get(i).length() > 2 || inputLine.get(i).equals(",") || inputLine.get(i).equals(".")){
                    processedWordsList.add(inputLine.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while removing wordnet data");
            System.out.println(e.getMessage());
        }
        
        
        return processedWordsList;
    }
}
