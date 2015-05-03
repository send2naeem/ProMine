/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.mit.jwi.item.POS;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import static pro.miner.classess.RemoveStopWord.stemlist;
import static forms.Form_stage_one.stem;
import forms.Form_text_mining;

/**
 *
 * @author Junaid Hassan
 */
public class Stemmer {

    public void Stem(String word) {

        String stemmed = stem.findStems(word, POS.NOUN).toString();
        stemmed = stemmed.replace("[", "").replace("]", "").replace(",", "");

        if (stemmed.endsWith("ing")) {
            removeING(stemmed);
        } else if (word.endsWith("ied")) {
            removeIED(word);
        } else if (word.endsWith("ed")) {
            removeED(word);
        } else if (word.endsWith("ify")) {
            removeIFY(word);
        } else if (stemmed.endsWith("ment")) {
            removeMENT(stemmed);
        } else if (stemmed.contains(" ")) {
            hasSpace(stemmed);
        } else if (stemmed.endsWith("ion")) {
            removeION(stemmed);
        } else if (stemmed.length() == 0) {
            stemlist.add(word);
        } else {
            stemlist.add(stemmed);
        }

    }

    public void removeION(String ion) {
        String removed_ion = ion.replace("ion", "");
        if (removed_ion == null ? ("motivat") == null : removed_ion.equals("motivat")) {
            String removed_sse = removed_ion.replace("motivat", "motivate");
            stemlist.add(removed_sse);
        } else {
            stemlist.add(removed_ion);
        }
    }

    public void removeMENT(String ment) {
        String removed_ment = ment.replace("ment", "");
        stemlist.add(removed_ment);
    }

    public void removeIED(String ied) {

        String removed_ied = stem.findStems(ied, POS.VERB).toString();
        removed_ied = removed_ied.replace("[", "").replace("]", "").replace(",", "");

        if (removed_ied.contains(" ")) {
            hasSpace(removed_ied);
        } else if (removed_ied.endsWith("ify")) {
            removeIFY(removed_ied);
        } else {
            stemlist.add(removed_ied);
        }
    }

    public void removeIFY(String fy) {
        String removed_ify = fy.replace("ify", "");
        if ("ident".equals(removed_ify)) {
            stemlist.add("identify");
        } else if ("simpl".equals(removed_ify)) {
            stemlist.add("simple");
        } else {
            stemlist.add(removed_ify);
        }

    }

    public void removeED(String ed) {

        String removed_ed = stem.findStems(ed, null).toString();
        removed_ed = removed_ed.replace("[", "").replace("]", "").replace(",", "");

        if (removed_ed.contains(" ")) {
            hasSpace(removed_ed);
        } else {
            stemlist.add(removed_ed);
        }
    }

    public void removeING(String ing) {
        String removed_ing = stem.findStems(ing, POS.VERB).toString();
        removed_ing = removed_ing.replace("[", "").replace("]", "").replace(",", "");

        if (removed_ing.contains(" ")) {
            hasSpace(removed_ing);
        } else {
            stemlist.add(removed_ing);
        }
    }

    public void hasSpace(String space) {

        String[] parts = space.split(" ");

        List<String> mylist = new LinkedList<String>();
        Set<String> result = new LinkedHashSet<String>();

        for (String part : parts) {
            mylist.add(part);
        }

        String small = "";

        Collections.sort(mylist);
        Collections.reverse(mylist);

        for (int i = 0; i < mylist.size(); i++) {

            small = mylist.get(i);

            for (int j = i; j < mylist.size(); j++) {

                if (small.contains(mylist.get(j))) {

                    small = mylist.get(j);
                }

            }

            result.add(small);

        }

        for (String string : result) {

            stemlist.add(string);

        }
    }

    public static String[] getUniqueKeys(String[] keys) {
        String[] uniqueKeys = new String[keys.length];

        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;

        for (int i = 1; i < keys.length; i++) {
            for (int j = 0; j <= uniqueKeyIndex; j++) {
                if (keys[i].equals(uniqueKeys[j])) {
                    keyAlreadyExists = true;
                }
            }

            if (!keyAlreadyExists) {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;
            }
            keyAlreadyExists = false;
        }
        return uniqueKeys;
    }
}
