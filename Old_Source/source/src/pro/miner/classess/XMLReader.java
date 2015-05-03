package pro.miner.classess;

import forms.Form_stage_one;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Junaid Hassan
 */
public class XMLReader implements Runnable{

    public static File file;
    public static String alltext;
    @Override
    public void run() {
        int returnopener= Form_stage_one.opener.showOpenDialog(null);
         if (returnopener == JFileChooser.APPROVE_OPTION){
             File f=Form_stage_one.opener.getSelectedFile();
             
             // Setting File Path in Selected File Field in Form
             Form_stage_one.filePath.setText(f.toString());
             
             int returnVal = Form_stage_one.filer.showSaveDialog(null);
             
             if (returnVal == JFileChooser.APPROVE_OPTION){
             
                
                DocumentBuilderFactory builderFactory =DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;
             
             try {
                 
                 builder = builderFactory.newDocumentBuilder();
                org.w3c.dom.Document document = builder.parse(new FileInputStream(f));
            
                XPath xPath =  XPathFactory.newInstance().newXPath();
            
                String expression = "/ADOXML/MODELS/MODEL//INSTANCE[@class='Activity']/ATTRIBUTE[@name='Description' and @type='STRING']";
           
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
           
                XPathFactory factory = XPathFactory.newInstance();
                XPath xpath = factory.newXPath();
                 XPathExpression expr = xpath.compile("/ADOXML/MODELS/MODEL/INSTANCE[@class='Activity']");
                Object result = expr.evaluate(document, XPathConstants.NODESET);
                NodeList nodes = (NodeList) result;
                
                List<String> depart = new ArrayList<String>();
                int i;

                for (i = 0; i < nodes.getLength(); i++) {

                    Node currentItem = nodes.item(i);
    
                    String key = currentItem.getAttributes().getNamedItem("name").getNodeValue();
                    key=key.replace("[","").replace("]","").replace("#","").replace(":", "").replace("•", "").replace("\n", " ")
                        .replace("\u0092", " ").replace("'", "").replace("•", " ").replace("-", " ").replace("’", " ").replace("\n", " ")
                            .replace(".","").replace("(", "").replace(")","").replace(",","").replace(";","").replace("“","")
                            .replace("\\","").replace("/","").replace("”","").replace("–", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "").replace("0", "");
                    depart.add(key);

                }

                ArrayList<String> text = new ArrayList<String>();

                int k;
                for (k = 0; k < nodeList.getLength(); k++) {
  
                    String txt=nodeList.item(k).getTextContent();
   
                    txt=txt.replace("[","").replace("]","").replace("#","").replace(":", "").replace("•", "").replace("\n", " ")
                        .replace("\u0092", " ").replace("'", "").replace("•", " ").replace("-", " ").replace("’", " ").replace("\n", " ")
                            .replace(".","").replace("(", "").replace(")","").replace(",","").replace(";","").replace("“","")
                            .replace("\\","").replace("/","").replace("”","").replace("–", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "").replace("0", "");
                    text.add(txt);
 
                }
                
                for (int j = 0; j < text.size(); j++) {
               
                    //Form_stage_one.txtarea.append(depart.get(j)+"\n"+text.get(j)+"\n\n");
                    FileWriter fw=new FileWriter(Form_stage_one.filer.getSelectedFile().getAbsolutePath()+"\\"+depart.get(j)+".txt");
                    BufferedWriter bw=new BufferedWriter(fw);
                    
                    bw.write(depart.get(j));
                    bw.newLine();
                    bw.write(text.get(j));
                    bw.newLine();
                    bw.newLine();
                    
                    bw.close();
                }
                
                JOptionPane.showMessageDialog(null,
    "Directory Created!\n",
    "File Saved",
    JOptionPane.INFORMATION_MESSAGE);
             }catch(Exception e){
                 JOptionPane.showMessageDialog(null,
    "Please Contact the Application Vendor!\n"+e,
    "File Save Error",
    JOptionPane.ERROR_MESSAGE);
             }
         }
         }
             
         
    }
    
}
