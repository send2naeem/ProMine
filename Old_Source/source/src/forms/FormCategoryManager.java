/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import core.Category;
import core.Category.CategoryListModel;
import core.Category.Word;
import core.Threads.CategoryWorker;
import core.Utility;
import java.awt.JobAttributes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import pro.miner.classess.Global;

/**
 *
 * @author Administrator
 */
public class FormCategoryManager extends javax.swing.JFrame {

    /**
     * Creates new form CategoryManager
     */
    public FormCategoryManager() {
        initComponents();

        jListCategoryFiles.setModel(new DefaultListModel());
        jListCategoryContent.setModel(new DefaultListModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCategoryFolderPath = new javax.swing.JTextField();
        btnChooseCategoryDirectory = new javax.swing.JButton();
        btnProcessCategories = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCategoryFiles = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListCategoryContent = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaCategoryProcessingOutput = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jProgressBarCategoryManager = new javax.swing.JProgressBar();
        jCheckBoxEnableCategoryMatching = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setName("frameCategoryManager"); // NOI18N
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setLabelFor(this);
        jLabel1.setText("Manage Ontology Categories");
        jLabel1.setToolTipText("Manage Ontology Categories");

        jLabel2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel2.setText("Select directory to import category files");

        btnChooseCategoryDirectory.setText("Browse");
        btnChooseCategoryDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseCategoryDirectoryActionPerformed(evt);
            }
        });

        btnProcessCategories.setText("Process Categories");
        btnProcessCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessCategoriesActionPerformed(evt);
            }
        });

        jListCategoryFiles.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListCategoryFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListCategoryFilesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListCategoryFiles);

        jListCategoryContent.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListCategoryContent);

        jTextAreaCategoryProcessingOutput.setColumns(20);
        jTextAreaCategoryProcessingOutput.setRows(5);
        jScrollPane3.setViewportView(jTextAreaCategoryProcessingOutput);

        jLabel3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel3.setText("Words");

        jLabel4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel4.setText("Categories");

        jLabel5.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel5.setText("Processing Output");

        jCheckBoxEnableCategoryMatching.setText("Enable Matching");
        jCheckBoxEnableCategoryMatching.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEnableCategoryMatchingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnProcessCategories)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jCheckBoxEnableCategoryMatching)
                                        .addGap(12, 12, 12))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCategoryFolderPath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnChooseCategoryDirectory))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jProgressBarCategoryManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCategoryFolderPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseCategoryDirectory))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarCategoryManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jCheckBoxEnableCategoryMatching)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProcessCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseCategoryDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseCategoryDirectoryActionPerformed
        // TODO add your handling code here:        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        switch (fileChooser.showOpenDialog(this)) {
            case JFileChooser.APPROVE_OPTION: {
                File file = fileChooser.getSelectedFile();
                this.jTextFieldCategoryFolderPath.setText(file.getAbsolutePath());
                if (Utility.hasTextFiles(file)) {
                    try {
                        Category.seed(file.listFiles());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FormCategoryManager.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(FormCategoryManager.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    DefaultListModel model = new DefaultListModel();
                    for (Category cat : Global.categories) {
                        model.addElement(cat._name);
                    }
                    jListCategoryFiles.setModel(model);
                } else {
                    Utility.ShowError("Error", "Directory does not contain required text file. Please select a directory with '.txt' files.");
                }
                break;
            }
            default: {
                break;
            }
        }
    }//GEN-LAST:event_btnChooseCategoryDirectoryActionPerformed

    private void jListCategoryFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListCategoryFilesMouseClicked
        // TODO add your handling code here:
        Category cat = null;
        for (Category c : Global.categories) {
            if(c._name.toLowerCase().equals(jListCategoryFiles.getSelectedValue().toString().toLowerCase())){
                cat = c;
                break;
            }
        }
        DefaultListModel wordModel = new DefaultListModel();
        for(Word word : cat._relatedWords)
            wordModel.addElement(word._name);
        jListCategoryContent.setModel(wordModel);
    }//GEN-LAST:event_jListCategoryFilesMouseClicked

    private void btnProcessCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessCategoriesActionPerformed
        //Global.categoryManageEnabled = true;
        // TODO add your handling code here:
        new CategoryWorker().execute();
        btnProcessCategories.setEnabled(false);
        // start application main flow
        //new Form_stage_one().setVisible(true);
    }//GEN-LAST:event_btnProcessCategoriesActionPerformed

    private void jCheckBoxEnableCategoryMatchingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEnableCategoryMatchingActionPerformed
        // TODO add your handling code here:
        Global.categoryManageEnabled = jCheckBoxEnableCategoryMatching.isSelected();
    }//GEN-LAST:event_jCheckBoxEnableCategoryMatchingActionPerformed
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseCategoryDirectory;
    public static javax.swing.JButton btnProcessCategories;
    private javax.swing.JCheckBox jCheckBoxEnableCategoryMatching;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jListCategoryContent;
    private javax.swing.JList jListCategoryFiles;
    public static javax.swing.JProgressBar jProgressBarCategoryManager;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTextArea jTextAreaCategoryProcessingOutput;
    private javax.swing.JTextField jTextFieldCategoryFolderPath;
    // End of variables declaration//GEN-END:variables
}
