/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import static pro.miner.classess.GetRelatedWordFromGlossary.relatedGlossary;
import forms.Form_domain_expert;
import static forms.Form_domain_expert.pbofwordnet;

/**
 *
 * @author Devil Knight
 */
public class RelatedWordGlossarySearcher extends SwingWorker{

    
    
    public String doInBackground() {
        System.out.println("In RelatedWordGlossarySercher.java");
        try{
        pbofwordnet.setIndeterminate(true);
        int size = Form_domain_expert.relatedList.getModel().getSize();
        ArrayList<String> related = new ArrayList<String>();
        
        for (int i = 0; i < size; i++) {
            System.out.println("THIS IS RELATED WORDS SEARCHER");
            related.add(Form_domain_expert.relatedList.getModel().getElementAt(i).toString().replace("_", " "));
        }
        for (int i = 0; i < related.size(); i++) {
//            System.out.println("get related word from glossary for word : [" + related.get(i) + "]");
            GetRelatedWordFromGlossary.getRelatedwordsFromGlossary(related.get(i));
        }
         pbofwordnet.setIndeterminate(false);
        Form_domain_expert.similarWordsOfRelatedWordsList.setModel(GetRelatedWordFromGlossary.relatedGlossary);
       
        if (relatedGlossary.isEmpty()) {
                throw new IllegalArgumentException("No Related Words found for Wordnet related words");
            }
        
        }catch(IllegalArgumentException e){
                        pbofwordnet.setIndeterminate(false);
                        JOptionPane.showMessageDialog(null,
                    "Exception\n" + e.getMessage() + "\nClass:RelatedWordGlossarySearcher",
                    "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e){
            pbofwordnet.setIndeterminate(false);
                        JOptionPane.showMessageDialog(null,
                    "Exception\n" + e.getMessage() + "\nClass:RelatedWordGlossarySearcher",
                    "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }
}
