/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devil Knight
 */
public class MutualOccuranceSearcher implements Runnable {

    public static String moText = "";

    @Override
    public void run() {

        moText = "";
        BufferedReader br;
        try {
            FileReader fr = new FileReader(GlossarySearcher.glossaryPath);
            br = new BufferedReader(fr);
            String line = "";

            while ((line = br.readLine()) != null) {
                line = line.toLowerCase().replace(":", " ").replace(";", " ").replace("/", " ").replace("\"", "").replace("(", " ").replace(".", " ").replace(",", " ").replace("-", "").replace(")", " ").replace("%", " ");
                if (line.isEmpty()) {
                } else {
                    moText += line.trim().replaceAll("\\s+", " ") + " ";
                }
            }
            br.close();
//            System.out.println("Mutual text = " + moText);
            MutualOccuranceList.mutualOccuranceListSetter();

        } catch (Exception ex) {
            Logger.getLogger(MutualOccuranceSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
