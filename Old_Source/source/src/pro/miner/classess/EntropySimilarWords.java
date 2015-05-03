/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class EntropySimilarWords implements Runnable {

    public static DefaultListModel EntropyModel = new DefaultListModel();

    @Override
    public void run() {
        System.out.println("Step : Entropy Of Similar Word Started");
        int totalFreq = 0;
//        int size = Form_domain_expert.mutualFreqList.getModel().getSize();
        int size = Form_domain_expert.mergeListProb.getModel().getSize();

        String similarwordsprob;
        double prob1;
        double prob2;
        
        double p;
        double p2;
        
        Form_domain_expert.pbofwordnet.setIndeterminate(true);
        System.out.println("Step : Entropy loop Started");
        for (int i = 0; i < size; i++) {
            
            similarwordsprob = Form_domain_expert.mergeListProb.getModel().getElementAt(i).toString();
            prob1 = Double.parseDouble(similarwordsprob);
            prob2 = Math.log(prob1) / Math.log(2);

            if (prob1 > 0) {
                
                p = -(prob1*prob2);
                
                p = Math.floor (p * 10000000) / 10000000.0;
                
                EntropyModel.addElement(p);
            } else {
                EntropyModel.addElement(0);
            }
        }

        System.out.println("Step : Entropy loop Completed");
        Form_domain_expert.pbofwordnet.setIndeterminate(false);
        
        //DomainExpertForm.mutualTotalFreqArea.setText(totalFreq + "");
        Form_domain_expert.entropyList.setModel(EntropyModel);

        System.out.println("Entropy of Similar Words file running");
                
}
}