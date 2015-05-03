/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BoundedRangeModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import pro.miner.classess.AgrovocSearcher;
import pro.miner.classess.EntropyOfSynonyms;
import pro.miner.classess.FrequencyCountMergeList;
import pro.miner.classess.GetRelatedWordFromGlossary;
import pro.miner.classess.Global;
import pro.miner.classess.GlossarySearcher;
import pro.miner.classess.MergeListFreqCounter;
import pro.miner.classess.MergeLists;
import pro.miner.classess.MutualFreqCountCaller;
import pro.miner.classess.MutualOccuranceList;
import pro.miner.classess.NewInfoGain;
import pro.miner.classess.ProbabilityOfMutualList;
import pro.miner.classess.ProbabiltyOfMergedList;
import pro.miner.classess.RelatedWordSearcher;
import pro.miner.classess.SimilarWordSearcher;
import static pro.miner.classess.SimilarWordSearcher.similarWords;
import pro.miner.classess.SimilarityMeasureCalculator;
import pro.miner.classess.listWordSelection;

/**
 *
 * @author Devil Knight
 */
public class Form_domain_expert extends javax.swing.JFrame {

    private Form_final_list finalList;
    private BoundedRangeModel model;
    private BoundedRangeModel model2;
    private BoundedRangeModel model3;

//    public static DefaultListModel 
    /**
     * Creates new form DomainExpertForm
     */
    public Form_domain_expert() {
        EntropyOfSynonyms.total_entropy = 0;
        AgrovocSearcher.agrovocList.clear();
        SimilarWordSearcher.similarWords.clear();
        RelatedWordSearcher.relatedWords.clear();
        
        initComponents();
        setFieldValues();
//        mutualFreqButton.setEnabled(false);
        mergeFreqButton.setEnabled(false);
        final_List.setEnabled(false);
        SavedList.setEnabled(false);
        setVisible(true);
        setResizable(false);
        //Global.file_text = "";
        
        Global.entities.clear();
        
        // set RADIO BUTTONS for Information Gain
        jRadioButtonEntropyNegative.setSelected(true);
        jRadioButtonEntropyPositive.setSelected(false);
        
        jRadioButtonProbNegative.setSelected(true);
        jRadioButtonProbPositive.setSelected(false);
        
               
    }
    public static DefaultListModel synonymModel = new DefaultListModel();
    public static DefaultListModel relatedModel = new DefaultListModel();
    
    public void setFieldValues() {
        //Finding Probability Of the Selected Word
        int totalProb = 0;
        for (int i = 0; i < Form_text_mining.freqlist.getModel().getSize(); i++) {
            totalProb += Integer.parseInt(Form_text_mining.freqlist.getModel().getElementAt(i).toString());
        }
        int selectFreq = Integer.parseInt(listWordSelection.selectedWordFrequency);
        double prob = (double) selectFreq / totalProb;
        prob = Math.floor(prob * 10000000) / 10000000.0;
        probArea.setText(prob + "");
        
        //------------------------------------------------
        for (int i = 0; i < Form_text_mining.relatedWordsListWordNet.getModel().getSize(); i++) {
            synonymModel.addElement(Form_text_mining.relatedWordsListWordNet.getModel().getElementAt(i).toString().replace("_", " "));
            System.out.println(synonymModel.getElementAt(i));
        }

        for (int i = 0; i < Form_text_mining.relatedWordsListWiktionary.getModel().getSize(); i++) {
            synonymModel.addElement(Form_text_mining.relatedWordsListWiktionary.getModel().getElementAt(i).toString().replace("_", " "));
            System.out.println(synonymModel.getElementAt(i));
        }
        relatedList.setModel(synonymModel);
        //------------------------------------------------
        for (int i = 0; i < Form_text_mining.derivedWordsListWiktionary.getModel().getSize(); i++) {
            relatedModel.addElement(Form_text_mining.derivedWordsListWiktionary.getModel().getElementAt(i).toString().replace("_", " "));
            System.out.println(relatedModel.getElementAt(i));
        }

        for (int i = 0; i < Form_text_mining.agrovocWords.getModel().getSize(); i++) {
            relatedModel.addElement(Form_text_mining.agrovocWords.getModel().getElementAt(i).toString().replace("_", " "));
            System.out.println(relatedModel.getElementAt(i));
        }
        derivedList.setModel(relatedModel);
        //------------------------------------------------
        
        wordsField.setText(listWordSelection.selectedWord); 
        freqArea.setText(listWordSelection.selectedWordFrequency);
        weightArea.setText(listWordSelection.selectedWordWeight);

        String keywordprob = probArea.getText();
        double prob3 = Double.parseDouble(keywordprob);
        double prob4 = Math.log(prob3) / Math.log(2);
        if (prob3 > 0) {
            double p2 = -(prob3 * prob4);
            p2 = Math.floor(p2 * 10000000) / 10000000.0;
            entropyArea.setText("" + p2);
        } else {
            entropyArea.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        glossaryFileChooser = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        generalInfo = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxSimilarWordsFilter = new javax.swing.JComboBox();
        infoGainThreshold = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        entropyArea = new javax.swing.JTextField();
        weightArea = new javax.swing.JTextField();
        jRadioButtonProbNegative = new javax.swing.JRadioButton();
        jRadioButtonProbPositive = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        probArea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jRadioButtonEntropyNegative = new javax.swing.JRadioButton();
        freqArea = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        wordsField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jRadioButtonEntropyPositive = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        detailedInfo = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        similarityMeasureList = new javax.swing.JList();
        jScrollPane11 = new javax.swing.JScrollPane();
        infoGainList = new javax.swing.JList();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        entropyList = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        mergeListProb = new javax.swing.JList();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        list1_list2_freq = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        list1_list2 = new javax.swing.JList();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        similarWordsOfRelatedWordsList = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        similarWordsList = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        relatedList = new javax.swing.JList();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        derivedList = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();
        searchGlossaryButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        mergeButton = new javax.swing.JButton();
        contextualMiningButton = new javax.swing.JButton();
        mergeFreqButton = new javax.swing.JButton();
        mergeTotalFreqButton = new javax.swing.JButton();
        mergeClear = new javax.swing.JButton();
        infoGainClear = new javax.swing.JButton();
        btnCountSimilarityMeasure = new javax.swing.JButton();
        btnClearSimilarityMeasure = new javax.swing.JButton();
        CountInfoGain = new javax.swing.JButton();
        entropyClear = new javax.swing.JButton();
        CountEntropy = new javax.swing.JButton();
        pbofkeyword = new javax.swing.JProgressBar();
        pbofwordnet = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        final_List = new javax.swing.JMenuItem();
        SavedList = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pro Miner - Text Mining - Domain Expert");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(154, 180, 209));
        mainPanel.setPreferredSize(new java.awt.Dimension(1106, 600));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel13.setText("Similar Words");

        jComboBoxSimilarWordsFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        jComboBoxSimilarWordsFilter.setSelectedIndex(9);
        jComboBoxSimilarWordsFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSimilarWordsFilterActionPerformed(evt);
            }
        });

        infoGainThreshold.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        infoGainThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoGainThresholdActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel6.setText("Information Gain");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel5.setText("Entropy:");

        entropyArea.setEditable(false);
        entropyArea.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        entropyArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entropyAreaActionPerformed(evt);
            }
        });

        weightArea.setEditable(false);
        weightArea.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N

        jRadioButtonProbNegative.setBackground(new java.awt.Color(154, 180, 209));
        buttonGroup2.add(jRadioButtonProbNegative);
        jRadioButtonProbNegative.setText("-ve");

        jRadioButtonProbPositive.setBackground(new java.awt.Color(154, 180, 209));
        buttonGroup2.add(jRadioButtonProbPositive);
        jRadioButtonProbPositive.setText("+ve");

        jLabel12.setText("Probability");

        probArea.setEditable(false);
        probArea.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel3.setText("Probability:");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel4.setText("Weight:");

        jRadioButtonEntropyNegative.setBackground(new java.awt.Color(154, 180, 209));
        buttonGroup1.add(jRadioButtonEntropyNegative);
        jRadioButtonEntropyNegative.setText("-ve");

        freqArea.setEditable(false);
        freqArea.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel2.setText("Frequency:");

        wordsField.setEditable(false);
        wordsField.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        wordsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordsFieldActionPerformed(evt);
            }
        });

        jLabel9.setText("Entropy");

        jRadioButtonEntropyPositive.setBackground(new java.awt.Color(154, 180, 209));
        buttonGroup1.add(jRadioButtonEntropyPositive);
        jRadioButtonEntropyPositive.setText("+ve");

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel1.setText("Word:");

        javax.swing.GroupLayout generalInfoLayout = new javax.swing.GroupLayout(generalInfo);
        generalInfo.setLayout(generalInfoLayout);
        generalInfoLayout.setHorizontalGroup(
            generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalInfoLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonEntropyPositive)
                        .addGap(6, 6, 6)
                        .addComponent(jRadioButtonEntropyNegative))
                    .addGroup(generalInfoLayout.createSequentialGroup()
                        .addComponent(wordsField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(freqArea, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalInfoLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonProbPositive)
                                .addGap(6, 6, 6)
                                .addComponent(jRadioButtonProbNegative))
                            .addGroup(generalInfoLayout.createSequentialGroup()
                                .addComponent(probArea, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(weightArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(entropyArea, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(generalInfoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(118, 118, 118)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(infoGainThreshold)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxSimilarWordsFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        generalInfoLayout.setVerticalGroup(
            generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalInfoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalInfoLayout.createSequentialGroup()
                        .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wordsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(freqArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(probArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weightArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entropyArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(infoGainThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSimilarWordsFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(generalInfoLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButtonEntropyPositive)
                                .addComponent(jRadioButtonEntropyNegative)
                                .addComponent(jLabel9))
                            .addGroup(generalInfoLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(generalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jRadioButtonProbPositive)
                                    .addComponent(jRadioButtonProbNegative)))))))
        );

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Similarity Measure");

        similarityMeasureList.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        similarityMeasureList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane13.setViewportView(similarityMeasureList);

        infoGainList.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        infoGainList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane11.setViewportView(infoGainList);

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("<html><p align=\"center\">Information Gain</p></html>");

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("<html><p align=\"center\">Entropy (-ve)</p></html>");

        entropyList.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        entropyList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane10.setViewportView(entropyList);

        mergeListProb.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        mergeListProb.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(mergeListProb);

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel11.setText("Probabilty");

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel10.setText("Frequency");

        list1_list2_freq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        list1_list2_freq.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(list1_list2_freq);

        list1_list2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        list1_list2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(list1_list2);

        jLabel19.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Merged List");

        similarWordsOfRelatedWordsList.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        similarWordsOfRelatedWordsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(similarWordsOfRelatedWordsList);

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("<html><p align=\"center\">Related Words</p></html>");

        similarWordsList.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        similarWordsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(similarWordsList);

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("<html><p align=\"center\">Similar Words</p></html>");

        relatedList.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        relatedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(relatedList);

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("<html><p align=\"center\">Synonyms</p></html>");

        jScrollPane7.setViewportView(derivedList);

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Derived Words");

        searchGlossaryButton.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        searchGlossaryButton.setText("Search Glossary");
        searchGlossaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchGlossaryButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Apply All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mergeButton.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        mergeButton.setText("Merge List");
        mergeButton.setEnabled(false);
        mergeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeButtonActionPerformed(evt);
            }
        });

        contextualMiningButton.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        contextualMiningButton.setText("Contextual Mining");
        contextualMiningButton.setEnabled(false);
        contextualMiningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contextualMiningButtonActionPerformed(evt);
            }
        });

        mergeFreqButton.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        mergeFreqButton.setText("Count Frequency");
        mergeFreqButton.setEnabled(false);
        mergeFreqButton.setOpaque(false);
        mergeFreqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeFreqButtonActionPerformed(evt);
            }
        });

        mergeTotalFreqButton.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        mergeTotalFreqButton.setText("∑f");
        mergeTotalFreqButton.setEnabled(false);
        mergeTotalFreqButton.setOpaque(false);
        mergeTotalFreqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeTotalFreqButtonActionPerformed(evt);
            }
        });

        mergeClear.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        mergeClear.setText("C");
        mergeClear.setEnabled(false);
        mergeClear.setOpaque(false);
        mergeClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeClearActionPerformed(evt);
            }
        });

        infoGainClear.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        infoGainClear.setText("C");
        infoGainClear.setEnabled(false);
        infoGainClear.setOpaque(false);
        infoGainClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoGainClearActionPerformed(evt);
            }
        });

        btnCountSimilarityMeasure.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        btnCountSimilarityMeasure.setText("Count");
        btnCountSimilarityMeasure.setEnabled(false);
        btnCountSimilarityMeasure.setOpaque(false);
        btnCountSimilarityMeasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountSimilarityMeasureActionPerformed(evt);
            }
        });

        btnClearSimilarityMeasure.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        btnClearSimilarityMeasure.setText("C");
        btnClearSimilarityMeasure.setEnabled(false);
        btnClearSimilarityMeasure.setOpaque(false);
        btnClearSimilarityMeasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSimilarityMeasureActionPerformed(evt);
            }
        });

        CountInfoGain.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        CountInfoGain.setText("Count");
        CountInfoGain.setEnabled(false);
        CountInfoGain.setOpaque(false);
        CountInfoGain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountInfoGainActionPerformed(evt);
            }
        });

        entropyClear.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        entropyClear.setText("C");
        entropyClear.setEnabled(false);
        entropyClear.setOpaque(false);
        entropyClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entropyClearActionPerformed(evt);
            }
        });

        CountEntropy.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        CountEntropy.setText("Count");
        CountEntropy.setEnabled(false);
        CountEntropy.setOpaque(false);
        CountEntropy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountEntropyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout detailedInfoLayout = new javax.swing.GroupLayout(detailedInfo);
        detailedInfo.setLayout(detailedInfoLayout);
        detailedInfoLayout.setHorizontalGroup(
            detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailedInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailedInfoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailedInfoLayout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(detailedInfoLayout.createSequentialGroup()
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchGlossaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addComponent(mergeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contextualMiningButton)
                                .addGap(27, 27, 27)
                                .addComponent(mergeFreqButton)
                                .addGap(22, 22, 22)
                                .addComponent(mergeTotalFreqButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mergeClear)
                                .addGap(18, 18, 18)
                                .addComponent(CountEntropy, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(entropyClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CountInfoGain, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(infoGainClear)
                                .addGap(34, 34, 34)
                                .addComponent(btnCountSimilarityMeasure, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(detailedInfoLayout.createSequentialGroup()
                                .addComponent(pbofkeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pbofwordnet, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearSimilarityMeasure)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        detailedInfoLayout.setVerticalGroup(
            detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailedInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailedInfoLayout.createSequentialGroup()
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(detailedInfoLayout.createSequentialGroup()
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane7)))
                    .addGroup(detailedInfoLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mergeClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mergeTotalFreqButton)
                        .addComponent(mergeFreqButton))
                    .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(contextualMiningButton)
                        .addComponent(mergeButton))
                    .addComponent(searchGlossaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(detailedInfoLayout.createSequentialGroup()
                        .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCountSimilarityMeasure)
                                .addComponent(btnClearSimilarityMeasure))
                            .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CountEntropy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(entropyClear)
                                .addComponent(CountInfoGain)
                                .addComponent(infoGainClear)))
                        .addGap(26, 26, 26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailedInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(pbofkeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pbofwordnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailedInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(detailedInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("List");

        final_List.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        final_List.setText("Final List");
        final_List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                final_ListActionPerformed(evt);
            }
        });
        jMenu2.add(final_List);

        SavedList.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        SavedList.setText("Saved Record");
        SavedList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavedListActionPerformed(evt);
            }
        });
        jMenu2.add(SavedList);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        mergeButton.setEnabled(true);
        searchGlossaryButton.setEnabled(true);
        mergeFreqButton.setEnabled(true);
//        mutualFreqButton.setEnabled(true);
        GlossarySearcher.alltext.delete(0, GlossarySearcher.alltext.length());
        relatedModel.removeAllElements();
        GlossarySearcher.similarWordsOfKeywordsModel.removeAllElements();
        GetRelatedWordFromGlossary.relatedGlossary.removeAllElements();
        MergeLists.listMergeModel.removeAllElements();
        FrequencyCountMergeList.list1_list2_freq_model.removeAllElements();
//        TempFreqList.tempModel1.removeAllElements();
//        TempFreqList.tempModel2.removeAllElements();
        MutualOccuranceList.mutualWords.removeAllElements();
        MutualFreqCountCaller.moFreq.removeAllElements();
        ProbabiltyOfMergedList.mergeProbModel.removeAllElements();
        ProbabilityOfMutualList.mutualProbModel.removeAllElements();
    }//GEN-LAST:event_formWindowClosing
    public static Form_final_list finallist = null;    

    private void final_ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_final_ListActionPerformed
        // TODO add your handling code here:
        //if (finallist == null) {
            finallist = new Form_final_list();
            finallist.setLocationRelativeTo(null);
            finallist.setVisible(true);
//        } else if (finallist != null) {
//            finallist.setLocationRelativeTo(null);
//            finallist.setVisible(true);
//        } else {
//            finallist.toFront();
//        }
    }//GEN-LAST:event_final_ListActionPerformed

    private void SavedListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavedListActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_SavedListActionPerformed

    private void jComboBoxSimilarWordsFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSimilarWordsFilterActionPerformed
        // TODO add your handling code here:
        String item = this.jComboBoxSimilarWordsFilter.getSelectedItem().toString();
        Double percent = Double.parseDouble(item);

        double recordsToLoad = SimilarWordSearcher.similarWords.size() * (percent/100);

        /* DATABIND SIMILAR WORDS LIST */
        DefaultListModel similarWordsOfKeywordsModel = new DefaultListModel();

        for(int i=0; i< recordsToLoad; i++){
            similarWordsOfKeywordsModel.addElement(SimilarWordSearcher.similarWords.get(i));
        }

        similarWordsList.setModel(similarWordsOfKeywordsModel);
    }//GEN-LAST:event_jComboBoxSimilarWordsFilterActionPerformed

    private void btnCountSimilarityMeasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountSimilarityMeasureActionPerformed
        // TODO add your handling code here:
        new Thread(new SimilarityMeasureCalculator()).start();
    }//GEN-LAST:event_btnCountSimilarityMeasureActionPerformed

    private void btnClearSimilarityMeasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSimilarityMeasureActionPerformed
        // TODO add your handling code here:
        similarityMeasureList.removeAll();
    }//GEN-LAST:event_btnClearSimilarityMeasureActionPerformed

    private void infoGainThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoGainThresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoGainThresholdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //SimilarityMeasureCalculator.findPaths("boy", "girl");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void infoGainClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoGainClearActionPerformed
        // TODO add your handling code here:
        infoGainClear.setEnabled(false);
        CountInfoGain.setEnabled(true);
        DefaultListModel infoGainListClear = (DefaultListModel) infoGainList.getModel();
        infoGainListClear.removeAllElements();
    }//GEN-LAST:event_infoGainClearActionPerformed

    private void CountInfoGainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CountInfoGainActionPerformed
        // TODO add your handling code here:
        CountInfoGain.setEnabled(false);
        infoGainClear.setEnabled(true);
        btnCountSimilarityMeasure.setEnabled(true);
        new Thread(new NewInfoGain()).start();
        final_List.setEnabled(true);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("" + listWordSelection.selectedWord + ".txt"));
            SavedList.setEnabled(true);
        } catch (IOException e) {
        }
        jScrollPane11.getVerticalScrollBar().setModel(model3);
        infoGainThreshold.setEditable(true);

    }//GEN-LAST:event_CountInfoGainActionPerformed

    private void entropyClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entropyClearActionPerformed
        // TODO add your handling code here:
        entropyClear.setEnabled(false);
        CountEntropy.setEnabled(true);
        DefaultListModel entropyListClear = (DefaultListModel) entropyList.getModel();
        entropyListClear.removeAllElements();
    }//GEN-LAST:event_entropyClearActionPerformed

    private void CountEntropyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CountEntropyActionPerformed
        // TODO add your handling code here:
        CountEntropy.setEnabled(false);
        entropyClear.setEnabled(true);
        //new Thread(new EntropySimilarWords()).start();
        new EntropyOfSynonyms().execute();
        CountInfoGain.setEnabled(true);
        jScrollPane10.getVerticalScrollBar().setModel(model3);
    }//GEN-LAST:event_CountEntropyActionPerformed

    private void mergeClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeClearActionPerformed
        // TODO add your handling code here:
        mergeClear.setEnabled(false);
        mergeTotalFreqButton.setEnabled(true);
        DefaultListModel mergeListProbClear = (DefaultListModel) mergeListProb.getModel();
        mergeListProbClear.removeAllElements();
        //        DefaultListModel list1_list2_freqClear = (DefaultListModel) list1_list2_freq.getModel();
        //        list1_list2_freqClear.removeAllElements();
        //mergeTotalFreqArea.setText("");
    }//GEN-LAST:event_mergeClearActionPerformed

    private void mergeTotalFreqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeTotalFreqButtonActionPerformed
        // TODO add your handling code here:
        mergeTotalFreqButton.setEnabled(false);
        //mergeTotalFreqArea.setVisible(true);
        mergeClear.setEnabled(true);
        jScrollPane6.getVerticalScrollBar().setModel(model);
        new Thread(new ProbabiltyOfMergedList()).start();
        CountEntropy.setEnabled(true);
    }//GEN-LAST:event_mergeTotalFreqButtonActionPerformed

    private void mergeFreqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeFreqButtonActionPerformed
        // TODO add your handling code here:
        mergeFreqButton.setEnabled(false);
        mergeTotalFreqButton.setEnabled(true);
        jScrollPane5.getVerticalScrollBar().setModel(model);
        //new Thread(new FrequencyCountMergeList()).start();
        new MergeListFreqCounter().execute();
    }//GEN-LAST:event_mergeFreqButtonActionPerformed

    private void mergeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeButtonActionPerformed
        // TODO add your handling code here:
        mergeButton.setEnabled(false);
        contextualMiningButton.setEnabled(true);
        new Thread(new MergeLists()).start();
    }//GEN-LAST:event_mergeButtonActionPerformed

    private void contextualMiningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contextualMiningButtonActionPerformed
        // TODO add your handling code here:
        contextualMiningButton.setEnabled(false);
        mergeFreqButton.setEnabled(true);
        model = jScrollPane2.getVerticalScrollBar().getModel();
    }//GEN-LAST:event_contextualMiningButtonActionPerformed

    private void searchGlossaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchGlossaryButtonActionPerformed

        GlossarySearcher.alltext.delete(0, GlossarySearcher.alltext.length());

        searchGlossaryButton.setEnabled(false);
        mergeButton.setEnabled(true);

        System.out.println("Step : Executing Glossary Search");
        new GlossarySearcher().execute();
        System.out.println("Step : Gloassary Search Finished");
    }//GEN-LAST:event_searchGlossaryButtonActionPerformed

    private void entropyAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entropyAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entropyAreaActionPerformed

    private void wordsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wordsFieldActionPerformed

    public static void main(String[] abc) {
        new Form_domain_expert();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CountEntropy;
    private javax.swing.JButton CountInfoGain;
    private javax.swing.JMenuItem SavedList;
    private javax.swing.JButton btnClearSimilarityMeasure;
    private javax.swing.JButton btnCountSimilarityMeasure;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JButton contextualMiningButton;
    public static javax.swing.JList derivedList;
    public static javax.swing.JPanel detailedInfo;
    public static javax.swing.JTextField entropyArea;
    private javax.swing.JButton entropyClear;
    public static javax.swing.JList entropyList;
    private javax.swing.JMenuItem final_List;
    public static javax.swing.JTextField freqArea;
    public static javax.swing.JPanel generalInfo;
    public static javax.swing.JFileChooser glossaryFileChooser;
    private javax.swing.JButton infoGainClear;
    public static javax.swing.JList infoGainList;
    public static javax.swing.JTextField infoGainThreshold;
    private javax.swing.JButton jButton1;
    public javax.swing.JComboBox jComboBoxSimilarWordsFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JRadioButton jRadioButtonEntropyNegative;
    public static javax.swing.JRadioButton jRadioButtonEntropyPositive;
    public static javax.swing.JRadioButton jRadioButtonProbNegative;
    public static javax.swing.JRadioButton jRadioButtonProbPositive;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    public static javax.swing.JList list1_list2;
    public static javax.swing.JList list1_list2_freq;
    public static javax.swing.JPanel mainPanel;
    public static javax.swing.JButton mergeButton;
    public static javax.swing.JButton mergeClear;
    public static javax.swing.JButton mergeFreqButton;
    public static javax.swing.JList mergeListProb;
    public static javax.swing.JButton mergeTotalFreqButton;
    public static javax.swing.JProgressBar pbofkeyword;
    public static javax.swing.JProgressBar pbofwordnet;
    public static javax.swing.JTextField probArea;
    public static javax.swing.JList relatedList;
    public static javax.swing.JButton searchGlossaryButton;
    public static javax.swing.JList similarWordsList;
    public static javax.swing.JList similarWordsOfRelatedWordsList;
    public static javax.swing.JList similarityMeasureList;
    public static javax.swing.JTextField weightArea;
    public static javax.swing.JTextField wordsField;
    // End of variables declaration//GEN-END:variables
}
