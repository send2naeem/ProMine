/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import org.apache.axis.client.Service;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import forms.Form_domain_expert;

//source: http://www.torsten-horn.de/techdocs/java-soap-axis.htm#WebService-Client-mit-WSDL2Java s. UniversellerClient
//logging stuff, see http://logback.qos.ch/manual/configuration.html
public class AgrovocSearcher {

    public static String wsMethod = "getConceptByKeyword";
    public static String ontologyName = "Agrovoc";
    //TXT | URI-TXT | SKOS
    public static String format = "URI-TXT";
    public static ArrayList<String> agrovocList = new ArrayList<String>();

    public static void execute() {
        //Agrovoc Part
        int size = Form_domain_expert.relatedList.getModel().getSize();
        ArrayList<String> related = new ArrayList<String>();

        for (int i = 0; i < size; i++) {
            System.out.println("AGROVOC SEARCHER STARTING");
            related.add(Form_domain_expert.relatedList.getModel().getElementAt(i).toString().replace("_", " "));
        }
        try {
            Form_domain_expert.pbofwordnet.setIndeterminate(true);
            searhArray(related.toArray());
            Form_domain_expert.pbofwordnet.setIndeterminate(false);

            DefaultListModel model = new DefaultListModel();
            try {
                for (Object s : AgrovocSearcher.agrovocList) {
                    model.addElement(s.toString());
                }

                // assign to combined list
                Form_domain_expert.similarWordsOfAgrovoc.removeAll();
                Form_domain_expert.similarWordsOfAgrovoc.setModel(model);
            } catch (Exception exception) {
                Form_domain_expert.similarWordsOfAgrovoc.removeAll();
                Form_domain_expert.similarWordsOfAgrovoc.setModel(model);
            }
        } catch (Exception ex) {
            Logger.getLogger(Form_domain_expert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void searhArray(Object[] words) throws Exception {
        agrovocList.removeAll(agrovocList);
        for (Object word : words) {
            search(word.toString());
        }
    }

    private static void search(String keyWord) throws Exception {

        String searchTerm = keyWord;
        //Contains | Exact Match | Starts With | Ends With | Exact Word
        String searchMode = "Exact Word";
        //All | de | en | fr ...
        String lang = "en";

        org.fao.aims.aos.SKOSWSService service = new org.fao.aims.aos.SKOSWSService();

        QName portQName = new QName("http://aos.aims.fao.org", "SKOSWS");

        String req = "<simpleSearchByMode2  xmlns=\"http://aos.aims.fao.org\"><searchString>" + keyWord + "</searchString><searchmode>" + searchMode + "</searchmode><separator>" + "---" + "</separator></simpleSearchByMode2>";

        try { // Call Web Service Operation

            Dispatch<Source> sourceDispatch = null;
            sourceDispatch = service.createDispatch(portQName, Source.class, javax.xml.ws.Service.Mode.PAYLOAD);
            Source result = sourceDispatch.invoke(new StreamSource(new StringReader(req)));

            String test = "";

            test = getResultString(result);

            String[] tokens = test.split("---");


            for (int i = 1; i < tokens.length; i = i + 2) {
                int nextToken = i + 1;
                if (nextToken < tokens.length) {
                    if (tokens[nextToken].equals("en")) {
                        String str = tokens[i];
                        str = str.replace("(", "").replace(")", "");
                        agrovocList.add(str);
                    }
                }
            }



        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
    }

    private static String getResultString(Source input) throws TransformerConfigurationException, TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult xmlOutput = new StreamResult(new StringWriter());
        transformer.transform(input, xmlOutput);
        return xmlOutput.getWriter().toString();
    }

    private static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    private static boolean isNumeric(String value) {
        try {
            double d = Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasWord(String s) {
        boolean found = false;
        for (int i = 0; i < agrovocList.size(); i++) {
            String word = agrovocList.get(i);
//            if (word.toLowerCase().contains(s.toLowerCase())) {
            if (word.toLowerCase() == s.toLowerCase()) {
                found = true;
            }
        }
        return found;
    }
}