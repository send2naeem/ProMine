/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Devil Knight
 */

public class FrequencyCountCallerMerge {

    public static int counter = 0;

    public static String frequencyCounter(String key) {        
        counter = 0;

        Pattern p = Pattern.compile(key);
        Matcher m = p.matcher(GlossarySearcher.alltext);

        while (m.find()) {
            counter++;
        }

        return counter + "";
    }
}
