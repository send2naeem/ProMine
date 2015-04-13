///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package pro.miner.classess;
//
///**
// *
// * @author Farooq
// */
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
// 
//import org.apache.commons.lang3.StringUtils;
////import org.creativecommons.learn.IndexFieldName;
////import org.fao.gilw.aims.webservices.AgrovocWSProxy;
////import org.mortbay.log.Log;
// 
///**
// * This class provides an interface to Agrovoc.
// * @author paulproteus
// *
// */
//public class Test {
//    public final static String AGROVOC_FIELD = IndexFieldName.makeCompleteFieldNameWithProvenance("http://search.agshare.org/#agrovoc", "http://purl.org/dc/terms/subject");
// 
//    public static Collection<Integer> parseRTList(String rtList) {
//        ArrayList<Integer> ret = new ArrayList<Integer>();
//        String[] pieces = StringUtils.split(rtList, ",");
//        for (String piece: pieces) {
//            if (piece.startsWith("[RT")) {
//                continue; // this is the header of the list
//            }
//            ret.add(Integer.parseInt(piece.trim().replace("]", "")));
//        }
//        return ret;
//    }
// 
//    public static Set<String> getRelatedTermsFromAgrovoc(String string) {
//        HashSet<String> relatedTermStrings = new HashSet<String>();
//        AgrovocWSProxy x = new AgrovocWSProxy();
//        
//        // First, get the term code
//        String code = null;
//        try {
//            code = x.getTermcodeByTerm(string);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        System.out.println(code);
// 
//        // Second, get related terms
//        String[] conceptInfo = null;
//        try {
//            conceptInfo = x.getConceptInfoByTermcode(code);
//        } catch (Exception e) {
//            // well, that sucks. the web services gave us an error.
//            //Log.info("Web services gave us an error.");
//            return relatedTermStrings;
//        }
//        
//        System.out.println(conceptInfo);
//        
//        Collection<Integer> relatedTermNumbers = new ArrayList<Integer>();
// 
//        // find the "Related" section
//        for (String maybeRelated: conceptInfo) {
//            if (maybeRelated.startsWith("[RT")) {
//                relatedTermNumbers = parseRTList(maybeRelated);
//                break;
//            }
//        }
//        
//        // Turn those into strings
//        for (int relatedTermNumber: relatedTermNumbers) {
//            String term = Test.termNumberToString(x, relatedTermNumber);
//            if (term == null) {
//                continue; // shucks we didn't get its name
//            } else {
//                relatedTermStrings.add(term);
//            }
//        }
//        
//        return relatedTermStrings;
//    }
// 
//    private static String termNumberToString(AgrovocWSProxy proxy, int termNumber) {
//        ArrayList<String> ret = new ArrayList<String>();
//        String termName = "";
// 
//        try {
//            termName = proxy.getTermByLanguage(termNumber, "en");
//            return termName;
//        } catch (Exception e) {
//            //Log.warn("Yargh, we hit an exception while turning term numbers into strings.");
//            return null;
//        } 
//    }
// 
// 
//}