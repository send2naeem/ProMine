/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.Threads;

import core.Category;
import core.Utility;
import forms.FormCategoryManager;
import static forms.FormCategoryManager.jProgressBarCategoryManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import pro.miner.classess.Global;

/**
 *
 * @author Administrator
 */
public class CategoryWorker extends SwingWorker {

    @Override
    protected Object doInBackground() throws Exception {
        jProgressBarCategoryManager.setIndeterminate(true);
        for(Category category: Global.categories){
            if(category._hasFileProcessed && !category._hasWordsProcessed){                
                try {                    
                    Utility.ShowOutputInCategoryManager("Processing : " + category._name + " ...");
                    category.processWords();
                    Utility.ShowOutputInCategoryManager("Processing : " + category._name + " :  -----Completed-----");                    
                } catch (Exception ex) {
                    Logger.getLogger(FormCategoryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Global.categoriesProcessed = true;
        jProgressBarCategoryManager.setIndeterminate(false);
        //FormCategoryManager.btnProcessCategories.setEnabled(true);
        return null;
    }
}
