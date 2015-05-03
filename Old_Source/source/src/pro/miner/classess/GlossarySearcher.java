/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import edu.mit.jwi.item.POS;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import static pro.miner.classess.RemoveStopWord.afterstopwordlist;
import static pro.miner.classess.RemoveStopWord.stopWordsofwordnet;
import forms.Form_domain_expert;
import static forms.Form_domain_expert.pbofkeyword;
import static forms.Form_domain_expert.pbofwordnet;
import static forms.Form_domain_expert.wordsField;
import static forms.Form_stage_one.stem;
import forms.Form_text_mining;

/**
 *
 * @author Devil Knight
 */
public class GlossarySearcher extends SwingWorker {

    public static String fileText = null;
    public static DefaultListModel similarWordsOfKeywordsModel = new DefaultListModel();
    public static File file;
    public static StringBuilder alltext = new StringBuilder();
    public static String glossaryPath = "";
    public static ArrayList<String> tempList2 = new ArrayList<>();

    @Override
    public String doInBackground() {
        try {
            
            Form_domain_expert.mergeButton.setEnabled(false);
            
            if (Form_text_mining.includeAgrovocDictionary) {
                Form_domain_expert.similarWordsOfAgrovoc.setEnabled(true);
                AgrovocSearcher.execute();
            }
            
            /* SEARCH FOR SIMILAR WORDS OF KEYWORD */
            new SimilarWordSearcher().execute();

            /* SEARCH FOR SIMILAR WORDS OF RELATED WORDS*/
            new RelatedWordSearcher().execute();

        } catch (IllegalArgumentException e) {
            pbofkeyword.setIndeterminate(false);
        } catch (Exception ex) {
            pbofkeyword.setIndeterminate(false);
        }

        Form_text_mining.checktransform.setSelected(true);
        return null;
    }
}
