/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import javax.swing.DefaultListModel;
import static pro.miner.classess.FrequencyCountMergeList.list1_list2_freq_model;
import forms.Form_domain_expert;
import forms.Form_text_mining;

/**
 *
 * @author Devil Knight
 */
public class MergeLists implements Runnable {

    public static DefaultListModel listMergeModel = new DefaultListModel();

    @Override
    public void run() {
//        listMergeModel.removeAllElements();
        int total = 0;
        LinkedHashSet<String> uniqueMergedList = new LinkedHashSet<String>();
        ArrayList<String> merging = new ArrayList<String>();

        if (Form_text_mining.includeAgrovocDictionary) {
            uniqueMergedList.addAll(AgrovocSearcher.agrovocList);
        }
        
        // as filter being applied to similar words so we have to get items from the updated list model
        int similar_words_size = Form_domain_expert.similarWordsList.getModel().getSize();
        for(int i=0;i< similar_words_size;i++)
          uniqueMergedList.add(Form_domain_expert.similarWordsList.getModel().getElementAt(i).toString());
        //uniqueMergedList.addAll(SimilarWordSearcher.similarWords);
        
        uniqueMergedList.addAll(RelatedWordSearcher.relatedWords);

        ArrayList<String> temp = new ArrayList();
        for (String s : uniqueMergedList) {
            String[] arr = s.split(" ");
            String inverse = arr.length > 1 ? arr[1] + " " + arr[0] : arr[0];
            boolean areBothSliceWordsSame = arr.length > 1 ? arr[1].toLowerCase().equals(arr[0].toLowerCase()) : false;

            if (!temp.contains(s) && !temp.contains(inverse) && !areBothSliceWordsSame) {
                temp.add(s);
            } else if (temp.contains(inverse)) {
                if (arr[0].equals(Form_domain_expert.wordsField.getText())) {
                    temp.remove(inverse);
                    temp.add(s);
                }
            }
        }

        uniqueMergedList.clear();
        uniqueMergedList.addAll(temp);

        for (String s : uniqueMergedList) {
            listMergeModel.addElement(s);
        }

        Form_domain_expert.list1_list2.setModel(listMergeModel);

//        //Gettigng List 1 data
//        for (int i = 0; i < Form_domain_expert.similarWordsList.getModel().getSize(); i++) {
//            merging.add(Form_domain_expert.similarWordsList.getModel().getElementAt(i).toString());
//        }
//        //Getting List 2 data
//        for (int i = 0; i < Form_domain_expert.similarWordsOfRelatedWordsList.getModel().getSize(); i++) {
//            merging.add(Form_domain_expert.similarWordsOfRelatedWordsList.getModel().getElementAt(i).toString());
//        }
//        
//        //Getting Agrovoc data
////        for(String str: AgrovocSearcher.agrovocList){
////            merging.add(str);
////        }
//        for (int i = 0; i < Form_domain_expert.similarWordsOfAgrovoc.getModel().getSize(); i++) {
//            merging.add(Form_domain_expert.similarWordsOfAgrovoc.getModel().getElementAt(i).toString());
//        }
//
//        for (int i = 0; i < Form_domain_expert.relatedList.getModel().getSize(); i++) {
//            merging.add(Form_domain_expert.relatedList.getModel().getElementAt(i).toString());
//        }
//
//        // Remove Similar Words
//        Collections.sort(merging);
//        Collections.reverse(merging);
//        HashSet hs = new HashSet();
//        hs.addAll(merging);
//        merging.clear();
//        merging.addAll(hs);
//
//        String freq = "";
//        int size = merging.size();
//        for (int i = 0; i < size; i++) {
//            freq = FrequencyCountCallerMerge.frequencyCounter(merging.get(i));
//            
//            // Agrovoc Sample have zero frequency and they were being eliminated
////            if (freq.equals("0")) {
////            } else {
//                listMergeModel.addElement(merging.get(i));
//                list1_list2_freq_model.addElement(freq);
////            }
//            FrequencyCountCallerMerge.counter = 0;
//            freq = "";
//        }
//
//
////        for (String m : merging) {
////            listMergeModel.addElement(m);
////        }
//
//        for (int i = 0; i < Form_domain_expert.relatedList.getModel().getSize(); i++) {
//            String word = Form_domain_expert.relatedList.getModel().getElementAt(i).toString();
//            int frequency = 1;
//            listMergeModel.addElement(word);
//            list1_list2_freq_model.addElement(frequency);
//        }
//        Form_domain_expert.list1_list2.setModel(listMergeModel);
//
//        merging.clear();
//        new Thread(new MutualOccuranceSearcher()).start();
    }
}
