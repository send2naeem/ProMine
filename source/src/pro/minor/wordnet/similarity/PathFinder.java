package pro.minor.wordnet.similarity;

//package edu.sussex.nlp.jws;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.*;
import java.net.*;
import edu.mit.jwi.Dictionary;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathFinder {

    /**
     * PathFinder finds the shortest path in WordNet between two synsets (other
     * methods to be implemented see: the equivalent Perl module). In theory,
     * you can have any combination of synsets / parts of speech as we are using
     * all the Pointer types and thus, somewhere in the space we should find a
     * connection
     *
     * David Hope, 2008, University Of Sussex
     *
     */
    private IDictionary dict = null;

    public PathFinder(IDictionary dict) {
        System.out.println("... PathFinder");

        this.dict = dict;
    }

    public double getShortestPath(ISynset a, ISynset z) {
        double sp = Double.MAX_VALUE;;
        HashSet<ISynsetID> A = new HashSet<ISynsetID>();
        A.add(a.getID());
        TreeMap<Integer, HashSet<ISynsetID>> AA = new TreeMap<Integer, HashSet<ISynsetID>>();
        getHypernyms(0, A, AA);

        HashSet<ISynsetID> Z = new HashSet<ISynsetID>();
        Z.add(z.getID());
        TreeMap<Integer, HashSet<ISynsetID>> ZZ = new TreeMap<Integer, HashSet<ISynsetID>>();
        getHypernyms(0, Z, ZZ);

        for (Integer i : AA.keySet()) {
            HashSet<ISynsetID> setA = AA.get(i);
            for (Integer j : ZZ.keySet()) {
                HashSet<ISynsetID> setZ = ZZ.get(j);
                HashSet<ISynsetID> join = new HashSet<ISynsetID>();
                join.addAll(setA);
                join.retainAll(setZ);
                if (!join.isEmpty()) {
                    if ((i + j) < sp) {
                        sp = (i + j);
                    }
                }
            }
        }
        return (sp + 1.0);
    }

    public void getHypernyms(int pathlength, HashSet<ISynsetID> synsets, TreeMap<Integer, HashSet<ISynsetID>> paths) {
        pathlength++;
        HashSet<ISynsetID> hypernyms = new HashSet<ISynsetID>();
        for (ISynsetID s : synsets) {
            ISynset synset = dict.getSynset(s);
            hypernyms.addAll(synset.getRelatedSynsets(Pointer.HYPERNYM)); 					// get the <hypernyms> if there are any
            hypernyms.addAll(synset.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE));	// get the <hypernyms> (instances) if there are any
        }
        if (!hypernyms.isEmpty()) {
            paths.put(pathlength, hypernyms);
            getHypernyms(pathlength, hypernyms, paths);
        }
        return;
    }

    private static ISynset getWordNetSynset(String _word, IDictionary dict) {
        IIndexWord idxWord = dict.getIndexWord(_word, POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);
        return word.getSynset();
    }

    public static double findPathLength(String first_word, String second_word) {
        double _pathLength = 0D;
        String wnhome = "WordNet\\" + 2.1 + "\\dict";
        String icfile = "WordNet\\" + 2.1 + "\\WordNet-InfoContent-" + 2.1 + "\\ic-semcor.dat";
        URL url = null;
        try {
            url = new URL("file", null, wnhome);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            return _pathLength;
        }
        IDictionary dict = new Dictionary(url);
        try {
            dict.open();
        } catch (IOException ex) {
            Logger.getLogger(PathFinder.class.getName()).log(Level.SEVERE, null, ex);
        }

        PathFinder pathfinder = new PathFinder(dict);
        ISynset synset1 = getWordNetSynset(first_word, dict);
        ISynset synset2 = getWordNetSynset(second_word, dict);

        _pathLength = pathfinder.getShortestPath(synset1, synset2);

        return _pathLength;
    }
}
