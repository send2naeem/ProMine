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

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import forms.Form_domain_expert;

/**
 *
 * @author Sehrish
 */
public class InformationGain implements Runnable {

    public static ArrayList<Double> infoGainList;
    public static DefaultListModel InfoGainModel = new DefaultListModel();

    @Override
    public void run() {
        int totalFreq = 0;
//        int size = Form_domain_expert.mutualFreqList.getModel().getSize();
//        Form_domain_expert.pbofwordnet.setIndeterminate(true);
//        System.out.println("Step : Information Gain Started");
//        infoGainList = new ArrayList<Double>();
//
//        String mutualFreq, entropysimilar;
//        String entropy = Form_domain_expert.entropyArea.getText();
//        String keywordweight = Form_domain_expert.weightArea.getText();
//        double prob1, prob2, prob3, prob4, p;
//
//        for (int i = 0; i < size; i++) {
//            System.out.println("Step : Information Gain In Loop");
//            String currentWord = "";
//
//            try {
//                currentWord = AgrovocSearcher.agrovocList.get(i).toString();
//            } catch (Exception exception) {
//            }
//
//            if (currentWord != "") {                
//                if (AgrovocSearcher.hasWord(currentWord)) {
//                    //InfoGainModel.addEzlement(1);
//                    infoGainList.add((double) 1);
//                } else {
//                    mutualFreq = Form_domain_expert.mutualFreqList.getModel().getElementAt(i).toString();
//                    entropysimilar = Form_domain_expert.entropyList.getModel().getElementAt(i).toString();
//                    
//                    prob1 = Double.parseDouble(mutualFreq);
//                    prob2 = Double.parseDouble(entropysimilar);
//                    prob3 = Double.parseDouble(entropy);
//                    prob4 = Double.parseDouble(keywordweight);
//
//                    if ((prob1 * prob2 * prob3 * prob4) > 0) {
//                        p = (prob1 * prob3 * prob2) / (prob4);
//                        p = Math.floor(p * 10000000) / 10000000.0;
//
//                        infoGainList.add(p);
//                    } else {
//                        infoGainList.add((double) 0);
//                    }
//                }
//            } else {
//                System.out.println("Step : Information Gain Except Agrovoc");
//                mutualFreq = Form_domain_expert.mutualFreqList.getModel().getElementAt(i).toString();
//                entropysimilar = Form_domain_expert.entropyList.getModel().getElementAt(i).toString();
//                prob1 = Double.parseDouble(mutualFreq);
//                prob2 = Double.parseDouble(entropysimilar);
//                prob3 = Double.parseDouble(entropy);
//                prob4 = Double.parseDouble(keywordweight);
//
//                if ((prob1 * prob2 * prob3 * prob4) > 0) {
//                    p = (prob1 * prob3 * prob2) / (prob4);
//                    p = Math.floor(p * 10000000) / 10000000.0;
//
//                    infoGainList.add(p);
//                } else {
//                    infoGainList.add((double) 0);
//                }
//            }
//
//            Collections.sort(infoGainList, Collections.reverseOrder());
//            
//            
//            System.out.println("Step : Information Gain Set Model");
//            for (int iterator = 0; iterator < infoGainList.size(); iterator++) {
//                InfoGainModel.add(iterator, infoGainList.get(iterator));
//            }
//            System.out.println("Step : Information Gain Set Model Finished");

//        }

        Form_domain_expert.pbofwordnet.setIndeterminate(false);
        //DomainExpertForm.mutualTotalFreqArea.setText(totalFreq + "");
        Form_domain_expert.infoGainList.setModel(InfoGainModel);

        System.out.println("Information Gain file running");

    }
}