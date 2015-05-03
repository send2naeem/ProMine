/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.stanford.nlp.util.Pair;
import static forms.FormCategoryManager.jTextAreaCategoryProcessingOutput;
import forms.Form_domain_expert;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.tuple.ImmutablePair;
import pro.miner.classess.Entity;
import pro.miner.classess.Global;
import pro.miner.classess.WiktionarySearcher;

/**
 *
 * @author Administrator
 */
public class Utility {

    public static void ShowOutputInCategoryManager(String text) {
        jTextAreaCategoryProcessingOutput.append("\n");
        jTextAreaCategoryProcessingOutput.append(text);
    }

    public static void ShowError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE, null);
    }

    public static boolean hasTextFiles(File file) {
        if (file.isDirectory()) {
            String[] files = file.list(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return (name.toLowerCase().endsWith(".txt"));
                }
            });

            return (files.length > 0);
        }
        return true;
    }

    private static ArrayList<String> getSynonymsWordnet(String _word) {
        ArrayList<String> _list = new ArrayList();

        File f = new File("WordNet\\2.1\\dict");
        System.setProperty("wordnet.database.dir", f.toString());
        WordNetDatabase database = WordNetDatabase.getFileInstance();
        Synset[] synsets = database.getSynsets(_word);

        if (synsets.length > 0) {
            HashSet hs = new HashSet();
            for (Synset synset : synsets) {
                String[] wordForms = synset.getWordForms();
                _list.addAll(Arrays.asList(wordForms));
            }

            hs.addAll(_list);
            _list.clear();
            _list.addAll(hs);
        }

        return _list;
    }

    private static ArrayList<String> getSynonymsWiktionary(String _word) throws MalformedURLException, IOException {
        ArrayList<String> _list = new ArrayList();
        try {

            URL url = new URL("http://www.igrec.ca/project-files/wikparser/wikparser.php?word=" + _word + "&query=syn&lang=en");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                boolean isEmpty = output.contains("No listed synonyms.")
                        || output.contains("ERROR: The Wiktionary API did not return a page for that word.");

                if (isEmpty) {
                    conn.disconnect();
                    return _list;
                } else {
                    String[] arr = output.trim().split(" ");
                    for (String s : arr) {
                        if (s.length() > 2) {
                            _list.add(s);
                        }
                    }
                }
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return _list;
    }

    public static ArrayList<String> getDictionarySynonyms(String _word) throws IOException, Exception {
        ArrayList<String> _list = new ArrayList();
        WiktionarySearcher wikiSearcher = new WiktionarySearcher(_word, "api");

        _list = wikiSearcher.getCompositeList();

        return _list;
    }

    public static ArrayList<String> getWikiText(String _word) throws MalformedURLException, IOException {
        ArrayList<String> _list = new ArrayList();
        try {

            URL url = new URL("http://en.wiktionary.org/w/api.php"
                    + "?action=parse"
                    + "&prop=wikitext"
                    + "&page=" + _word
                    + "&format=xmlfm");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output, wikiText = "";
            while ((output = br.readLine()) != null) {
                wikiText += output + "\n";
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return _list;
    }

    public static void computeCategoryMatching() throws Exception {
        String key = Form_domain_expert.wordsField.getText().toLowerCase();
        for (Entity e : Global.entities) {
            for (int i = 0; i < Global.categories.size();i++) {
                Category c  = Global.categories.get(i);
                if (c.exists(e.word)) {
                    c.append(e.word);
                    e.categories.add(i);
                }
            }
        }

        // for word in multiple categories

    }

    public static void generateCategoryDataCSV(String word) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output files/" + word + ".csv")));

        int count = 0;
        ArrayList<String> miscList = new ArrayList();

        // calculate miscelleneous list
        for (Entity e : Global.entities) {
            if (e.categories.isEmpty()) {
                miscList.add(e.word);
            }
        }

        count = miscList.size();

        // calculate maximum count
        for (Category c : Global.categories) {
            int itemCount = c._relatedWords.size();
            count = itemCount > count ? itemCount : count;
            writer.write(c._name + ",,");
        }
        writer.write("Miscellineous");

        writer.newLine();

        for (int c = 0; c < count; c++) {
            for (Category cat : Global.categories) {
                String oldWord = (c <= (cat._OriginalList.size() - 1)) ? cat._OriginalList.get(c)._name : "";
                String newWord = (c <= (cat._NewList.size() - 1)) ? cat._NewList.get(c)._name : "";

                // write
                writer.write(oldWord + "," + newWord + ",");
            }

            String miscWord = (c <= (miscList.size() - 1)) ? miscList.get(c) : "";

            // write  misc word
            writer.write(miscWord + ",");
            writer.newLine();
        }
//        for (int i = -1; i < Global.categories.size(); i++) {
//            if (i > -1) {
//                writer.newLine();
//
//                Category c = Global.categories.get(i);
//
//                for (Entity e : Global.entities) {
//                    writer.write((e.categories.contains(c._name) ? "Yes" : "No") + ",");
//                }
//
//                writer.write(c._name);
//            } else {
//                for (Entity e : Global.entities) {
//                    writer.write(e.word + ",");
//                }
//
//                writer.write("CATEGORY");
//            }
//        }
//
//        writer.newLine();
//        for (Entity e : Global.entities) {
//            writer.write((e.categories.isEmpty() ? "Yes" : "No") + ",");
//        }
//        writer.write("Miscellaneous");

        writer.close();
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
}
