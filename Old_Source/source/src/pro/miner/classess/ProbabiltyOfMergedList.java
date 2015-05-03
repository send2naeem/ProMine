/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import javax.swing.DefaultListModel;
import static pro.miner.classess.MergeListFreqCounter.list1_list2_freq_model;
import static pro.miner.classess.MergeListFreqCounter.mergeListFreq;
import forms.Form_domain_expert;

/**
 *
 * @author Devil Knight
 */
public class ProbabiltyOfMergedList implements Runnable {

    public static DefaultListModel mergeProbModel = new DefaultListModel();

    @Override
    public void run() {
//        int totalFreq = 0;
//        String freq;
//        int size = Form_domain_expert.list1_list2_freq.getModel().getSize();
//        for (int i = 0; i < size; i++) {
//            freq = Form_domain_expert.list1_list2_freq.getModel().getElementAt(i).toString();
//            totalFreq += Integer.parseInt(freq);
//        }
//        int prob;
//        double p;
//        for (int i = 0; i < size; i++) {
//            freq = Form_domain_expert.list1_list2_freq.getModel().getElementAt(i).toString();
//            prob = Integer.parseInt(freq);
//            p = (double) prob / totalFreq;
//            p = Math.floor(p * 10000000) / 10000000.0;
//            mergeProbModel.addElement(p);
//        }
        
        mergeProbModel.clear();
        Form_domain_expert.mergeListProb.removeAll();
        
        Form_domain_expert.pbofwordnet.setIndeterminate(true);
        for (int i = 0; i < Form_domain_expert.list1_list2.getModel().getSize(); i++) {
             String word = Form_domain_expert.list1_list2.getModel().getElementAt(i).toString();             
             mergeProbModel.addElement(Double.parseDouble(MergeListFreqCounter.mergeListProb.get(word).toString()));
        }

        //DomainExpertForm.mergeTotalFreqArea.setText(totalFreq + "");
        Form_domain_expert.mergeListProb.setModel(mergeProbModel);
        
        Form_domain_expert.pbofwordnet.setIndeterminate(false);
    }
}
