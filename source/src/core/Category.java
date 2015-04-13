/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import edu.stanford.nlp.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import javax.swing.AbstractListModel;
import pro.miner.classess.Global;

/**
 *
 * @author Administrator
 */
public class Category {

    // Category Name
    public String _name = "";
    // Category Text Glued with ',' to optimize matching with regular expressions
    public String _gluedText = "";
    // Seed File of the category
    public String _fileName = "";
    // Identifier if category file has been processed
    public boolean _hasFileProcessed = false;
    // Identifier if category words has been processed
    public boolean _hasWordsProcessed = false;
    // delimiter string in category files
    public String _delimeter = ",";
    // List to store words against each category
    public ArrayList<Word> _relatedWords = new ArrayList();

    public String _DumpText = "";

    public ArrayList<Word> _OriginalList = new ArrayList();
    public ArrayList<Word> _NewList = new ArrayList();

    public Category(File file) throws FileNotFoundException, Exception {
        if (file.exists()) {
            this._name = file.getName().substring(0, file.getName().indexOf(".txt"));
            this._fileName = file.getPath();
            this._hasFileProcessed = false;
            this._hasWordsProcessed = false;

            this._hasFileProcessed = this.processFile();
        } else {
            throw new FileNotFoundException("Unable to locate category file.");
        }
    }

    private boolean processFile() throws IOException, Exception {
        File catFile = new File(this._fileName);
        if (catFile.exists() && catFile.canRead()) {
            String currentLine, fileText = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(catFile));
            while ((currentLine = bufferedReader.readLine()) != null) {
                fileText += currentLine;
            }
            String[] fileContentArray = fileText.replaceAll(this._delimeter + " ", this._delimeter).split(this._delimeter);

            for (String fileWord : fileContentArray) {
                Word word = new Word(fileWord);
                if (!this._relatedWords.contains(word)) {
                    this._relatedWords.add(word);
                }
            }

            // filter duplicates
            HashSet hashSet = new HashSet();
            hashSet.addAll(_relatedWords);
            _relatedWords.clear();
            _relatedWords.addAll(hashSet);

            // return processed true
            return true;
        }

        // return processed false
        return false;
    }
    
    public int getOccurances(String word) {
        int count = 0;
        for (Word w : this._relatedWords) {
            for (String s : w._relatedWords) {
                if (s.toLowerCase().equals(word.toLowerCase())) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public boolean processWords() throws Exception {
        if (this._hasFileProcessed) {
            for (Word _word : this._relatedWords) {
                this._OriginalList.add(_word);
                Utility.ShowOutputInCategoryManager("Processing : " + _word._name + " ...");
                _word.processRelatedWords();

                if (!_word._DumpText.isEmpty()) {
                    this._DumpText = this._DumpText + ", " + _word._DumpText;
                }

                this._hasWordsProcessed = true;
            }
        }
        // return processed flase
        return false;
    }

    public boolean exists(String _word) throws Exception {
        boolean exists = false;
        String[] parts = _word.split(" ");
        if (parts.length > 1) {
            for (String wrd : parts) {
                exists = exists || this._relatedWords.contains(new Word(wrd));
            }
        } else {
            exists = this._relatedWords.contains(new Word(_word));
        }
        return exists;
    }

    public void append(String word) throws Exception {
        this.appendToList(word);
        this.appendToFile(word);
    }

    void appendToList(String text) throws Exception {
        Word word = new Word(text);
        word._isNew = true;
        word.processRelatedWords();
        //if(!this._relatedWords.contains(word)){
        this._relatedWords.add(word);
        this._NewList.add(word);
        //}
    }

    void appendToFile(String text) throws IOException {
        File catFile = new File(this._fileName);
        if (catFile.exists() && catFile.canWrite()) {
            String fileText = "", buffer = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(new File(this._fileName)))) {
                while ((buffer = reader.readLine()) != null) {
                    fileText = fileText + buffer;
                }
            }

            String word_1 = text;
            String[] arr = text.split(" ");
            String word_2 = arr.length > 1 ? (arr[1] + " " + arr[0]) : arr[0];

            if (!fileText.contains(word_1) && !fileText.contains(word_2)) {
                try (FileWriter writer = new FileWriter(catFile)) {
                    writer.append(fileText + "," + text);
                }
            }
        }
    }

    public static void seed(File[] listFiles) throws FileNotFoundException, Exception {
        for (File file : listFiles) {
            Global.categories.add(new Category(file));
        }
    }

    public class Word {

        public String _name = "";
        public boolean _isNew = false;
        public ArrayList<String> _relatedWords = new ArrayList();

        public String _DumpText = "";

        public Word(String name) throws Exception {
            this._name = name;
            this._isNew = false;
        }

        public void processRelatedWords() throws Exception {
            this._relatedWords = Utility.getDictionarySynonyms(this._name.replace(" ", "_"));
            if (!this._relatedWords.isEmpty()) {
                this._DumpText = _name + ", " + StringUtils.join(this._relatedWords, ", ");
            } else {
                this._DumpText = this._name;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof Word) {
                Word obj = (Word) (o);
                return (this._name.toLowerCase().equals(obj._name.toLowerCase())
                        || this._relatedWords.contains(obj._name));
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 17 * hash + Objects.hashCode(this._name);
            hash = 17 * hash + Objects.hashCode(this._relatedWords);
            return hash;
        }
    }

    public class CategoryListModel extends AbstractListModel {

        ArrayList<Category> _List = new ArrayList();

        public CategoryListModel(ArrayList<Category> list) {
            _List = list;
        }

        @Override
        public int getSize() {
            return _List.size();
        }

        @Override
        public Object getElementAt(int index) {
            return _List.get(index);
        }
    }
}
