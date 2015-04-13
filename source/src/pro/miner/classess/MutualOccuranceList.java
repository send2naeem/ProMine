/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import javax.swing.DefaultListModel;
import forms.Form_domain_expert;

/**
 *
 * @author Devil Knight
 */
public class MutualOccuranceList {
    
    public static DefaultListModel mutualWords = new DefaultListModel();
    public static void mutualOccuranceListSetter(){
//      mutualWords.removeAllElements();
        String wordIsSelected = listWordSelection.selectedWord;
        System.out.println("MutualList WordToReplace : " + wordIsSelected);
        for(int i = 0;i<MergeLists.listMergeModel.getSize();i++){
            mutualWords.addElement(MergeLists.listMergeModel.getElementAt(i).toString().replace(wordIsSelected+" ", "").replace(" "+wordIsSelected, ""));
            System.out.println("MutualList Word Replaced");
        }
        System.out.println("In Mutual List Setter Done");
        //DomainExpertForm.mutualOccurList.setModel(mutualWords);
    }
    
}
