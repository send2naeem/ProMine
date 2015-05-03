/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import forms.Form_text_mining;

/**
 *
 * @author Devil Knight
 */
public class listWordSelection {

    public static String selectedWord = null;
    public static String selectedWordFrequency = null;
    public static String selectedWordWeight = null;

    public void select() {
        Form_text_mining.searchWord.setEnabled(true);
        selectedWord = (String) Form_text_mining.listofwords.getSelectedValue();
        int index = Form_text_mining.listofwords.getSelectedIndex();
        if (index >= 0) {
            //if (TextMining.isFolder) {
                selectedWordFrequency = Form_text_mining.freqlist.getModel().getElementAt(index) + "";
                float weight = (int) Form_text_mining.freqlist.getModel().getElementAt(index);
                selectedWordWeight = weight / 10 + "";
            //}
            
            Form_text_mining.selectedGlossaryFilePath.setText(selectedWord);
            Form_text_mining.domain.setEnabled(false);
        }
        System.out.println("Selected Word:" + selectedWord);
        Form_text_mining.checkwordselect.setSelected(true);
//        new WordNetSearcher().searcher();
    }
}
