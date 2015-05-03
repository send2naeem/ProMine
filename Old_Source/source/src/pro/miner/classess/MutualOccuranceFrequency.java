/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import forms.Form_domain_expert;

/**
 *
 * @author Devil Knight
 */
public class MutualOccuranceFrequency {

    public static int counter = 0;

    public static String mutualOccuranceFreq(String s) {
        counter = 0;
        Pattern p = Pattern.compile(" " + s + " " + listWordSelection.selectedWord);
        Matcher m = p.matcher(MutualOccuranceSearcher.moText);

        while (m.find()) {                       
            counter++;
        }

        Pattern p2 = Pattern.compile(listWordSelection.selectedWord + " " + s + " ");
        Matcher m2 = p2.matcher(MutualOccuranceSearcher.moText);

        while (m2.find()) {
            counter++;
        }

        return counter + "";

    }
}
