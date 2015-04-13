/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pro.miner.classess;

import javax.swing.DefaultListModel;
import forms.Form_domain_expert;

/**
 *
 * @author Sehrish
 */

public class MutualInformation implements Runnable {

    public static DefaultListModel mutualInfoModel = new DefaultListModel();

    @Override
    public void run() {
        int totalFreq = 0;
//        int size = Form_domain_expert.mutualFreqList.getModel().getSize();
//        int size = Form_domain_expert.mergeListProb.getModel().getSize();
//
//        String mutualprob;
//        String mergeprob;
//        String keywordprob;
//        double prob1;
//        double prob2;
//        double prob3;
//        
//        double p;
//        for (int i = 0; i < size; i++) {
//            //mutualprob = Form_domain_expert.mutualProbList.getModel().getElementAt(i).toString();
//            mergeprob = Form_domain_expert.mergeListProb.getModel().getElementAt(i).toString();
//            keywordprob = Form_domain_expert.probArea.getText();
//            //prob1 = Double.parseDouble(mutualprob);
//            prob2 = Double.parseDouble(mergeprob);
//            prob3 = Double.parseDouble(keywordprob);
//            
////            if ((prob1*prob2*prob3) > 0) {
//            if ((prob2*prob3) > 0) {
//                p = prob1/(prob3*prob2);
//                p = Math.log(p)/Math.log(2);
//                p = Math.floor (p * 10000000) / 10000000.0;
//                mutualInfoModel.addElement(p);
//            } else {
//                mutualInfoModel.addElement(0);
//            }
//        }
        //DomainExpertForm.mutualTotalFreqArea.setText(totalFreq + "");
        //DomainExpertForm.mutualInfoList.setModel(mutualInfoModel);        
        
        System.out.println("Mutual information file running");
        
}
}