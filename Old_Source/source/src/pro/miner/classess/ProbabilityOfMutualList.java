/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import javax.swing.DefaultListModel;

/**
 *
 * @author Devil Knight
 */
public class ProbabilityOfMutualList implements Runnable {

    public static DefaultListModel mutualProbModel = new DefaultListModel();

    @Override
    public void run() {
//        int totalFreq = 0;
//        String freq;
//        int size = DomainExpertForm.mutualFreqList.getModel().getSize();
//        for (int i = 0; i < size; i++) {
//            freq = DomainExpertForm.mutualFreqList.getModel().getElementAt(i).toString();
//            totalFreq += Integer.parseInt(freq);
//        }
//        int prob;
//        double p;
//        for (int i = 0; i < size; i++) {
//            freq = DomainExpertForm.mutualFreqList.getModel().getElementAt(i).toString();
//            prob = Integer.parseInt(freq);
//            if (prob > 0) {
//                p = (double) prob / totalFreq;
//                p = Math.floor(p * 10000000) / 10000000.0;
//                mutualProbModel.addElement(p);
//            } else {
//                mutualProbModel.addElement(0);
//            }
//        }
//        DomainExpertForm.mutualTotalFreqArea.setText(totalFreq + "");
//        DomainExpertForm.mutualProbList.setModel(mutualProbModel);
//        
//        System.out.println("keysize of list1_list2==="+list1_list2.getModel().getSize());
//        System.out.println("keysize of freqbox==="+list1_list2_freq.getModel().getSize());
//        System.out.println("keysize of prob box==="+mergeListProb.getModel().getSize());
//        ////////////
//        //System.out.println("simsize of mutualOccurList==="+mutualOccurList.getModel().getSize());
//        System.out.println("simsize of mutualFreqList==="+mutualFreqList.getModel().getSize());
//        System.out.println("simsize of mutualProbList==="+mutualProbList.getModel().getSize());
        

    }
}