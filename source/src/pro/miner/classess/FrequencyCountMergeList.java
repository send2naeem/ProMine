/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import forms.Form_domain_expert;

/**
 *
 * @author Devil Knight
 */
public class FrequencyCountMergeList implements Runnable {

    public static ArrayList<String> mergeFreq = new ArrayList<String>();
    public static DefaultListModel list1_list2_freq_model = new DefaultListModel();

    @Override
    public void run() {
//        list1_list2_freq_model.removeAllElements();
//        int size = Form_domain_expert.list1_list2.getModel().getSize();
//        String freq = "";
//        for (int i = 0; i < size; i++) {
//            freq = FrequencyCountCallerMerge.frequencyCounter(Form_domain_expert.list1_list2.getModel().getElementAt(i).toString());
//            FrequencyCountCallerMerge.counter = 0;
//
//            list1_list2_freq_model.addElement(freq);
//            freq = "";
//        }

        Form_domain_expert.list1_list2_freq.setModel(list1_list2_freq_model);
//        Form_domain_expert.list1_list2.setModel(MergeLists.listMergeModel);

        //Entering Data in TempList at Bottom 1
        TempFreqList.tempFreqListSetter1();

    }
}
