/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Farooq
 */
public class WiktionarySearcher {

    String wikiText = "";
    String nounHeader = "===Noun===";
    String synheader = "====Synonyms====";
    String derivedTermsHeader = "====Derived terms====";
    String nextheader = "====";
    ArrayList<String> synonyms = new ArrayList();
    ArrayList<String> derivedTerms = new ArrayList();
    ArrayList<String> nouns = new ArrayList();

    String mode = ""; // local || api

    /**
     *
     * @param word : Subject Word
     * @param mode : local | api
     * @param extractMode synonyms | derived_terms | nouns
     * @throws java.lang.Exception
     */
    public WiktionarySearcher(String word, String mode) throws Exception {
        this.wikiText = mode.equals("local") ? this.loadFromDatabase(word) : this.loadFromAPI(word);
    }

    public ArrayList<String> getList(String extractMode) throws Exception {
        switch (extractMode) {
            case "synonyms": {
                return this.synonyms;
            }
            case "derived_terms": {
                return this.derivedTerms;
            }
            case "noun": {
                return this.nouns;
            }
            default: {
                throw new Exception("Please specify an extraction mode.");
            }
        }
    }

    public void extractSection(String section) throws Exception {
        String wordInSquareBrackets = "\\[\\[(.*?)\\]\\]";
        String wordInMidBrackets = "\\{\\{(.*?)\\}\\}";
        String subWiki = "";

        switch (section) {
            case "synonyms": {
                int synIndex = this.wikiText.indexOf(this.synheader);

                if (synIndex > -1) {
                    subWiki = this.wikiText.substring(this.wikiText.indexOf(this.synheader) + this.synheader.length());
                    int nextHeaderIndex = subWiki.indexOf(this.nextheader);
                    this.wikiText = nextHeaderIndex > -1 ? subWiki.substring(0, nextHeaderIndex) : subWiki;
                    try {
                        this.synonyms = this.extractMatchedRegex(this.wikiText, wordInSquareBrackets);
                        this.synonyms.addAll(this.extractMatchedRegex(this.wikiText, wordInMidBrackets));
                    } catch (Exception exception) {
                        throw exception;
                    }
                }
                break;
            }
            case "derived_terms": {
                int dervIndex = this.wikiText.indexOf(this.derivedTermsHeader);

                if (dervIndex > -1) {
                    subWiki = this.wikiText.substring(this.wikiText.indexOf(this.derivedTermsHeader) + this.derivedTermsHeader.length());
                    int nextHeaderIndex = subWiki.indexOf(this.nextheader);
                    this.wikiText = nextHeaderIndex > -1 ? subWiki.substring(0, nextHeaderIndex) : subWiki;
                    try {
                        this.derivedTerms = this.extractMatchedRegex(this.wikiText, wordInSquareBrackets);
                        this.derivedTerms.addAll(this.extractMatchedRegex(this.wikiText, wordInMidBrackets));
                    } catch (Exception exception) {
                        throw exception;
                    }
                }
                break;
            }
            case "noun": {
                int nounIndex = this.wikiText.indexOf(this.nounHeader);

                if (nounIndex > -1) {
                    subWiki = this.wikiText.substring(this.wikiText.indexOf(this.nounHeader) + this.nounHeader.length());
                    int nextHeaderIndex = subWiki.indexOf(this.nextheader);
                    this.wikiText = nextHeaderIndex > -1 ? subWiki.substring(0, nextHeaderIndex) : subWiki;
                    try {
                        this.nouns = this.extractMatchedRegex(this.wikiText, wordInSquareBrackets);
                        this.nouns.addAll(this.extractMatchedRegex(this.wikiText, wordInMidBrackets));
                    } catch (Exception exception) {
                        throw exception;
                    }
                }
                break;
            }
            default: {
                throw (new Exception("Please specify extraction mode (synonyms | derived_terms | nouns)"));
            }
        }
    }

//    private String extractMatchedRegexString(String input, String expression) {
//        ArrayList<String> list = extractMatchedRegex(input, expression);
//        return (org.apache.commons.lang3.StringUtils.join(list.getItems(), ","));
//    }
    private ArrayList<String> extractMatchedRegex(String input, String expression) {
        ArrayList<String> matched = new ArrayList();
        Pattern pattern = Pattern.compile(expression, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String s = matcher.group(1);
            if (!s.contains(":")) {
                int hashIndex = s.indexOf("#");
                if (hashIndex > -1) {
                    s = s.substring(0, hashIndex);
                }

                int slashIndex = s.indexOf("|");
                if (slashIndex > -1) {
                    s = s.substring(slashIndex + 1, s.length() - 1);
                }

                s = s.replace("&lt;", "");
                s = s.replace("!--", "");
                s = s.replace("--", "");
                s = s.replace("&gt;", "");
                s = s.replace("&quot;", "");
                s = s.replace("&quot", "");

//                if (s.split(" ").length > 1) {
//                    s = Tagger.GetNoun(s);
//                    if (s.length() > 2) {
//                        matched.add(s);
//                    }
//                } else {
                    
                    if(s.contains("rel-") || s.startsWith("-") || s.isEmpty() || s.contains("<span")){}
                    else
                        matched.add(s);
//                }
            }
        }
        return matched;
    }

    private String loadFromAPI(String _word) throws MalformedURLException, IOException {
        try {
            String urlText = "http://en.wiktionary.org/w/api.php"
                    + "?action=parse"
                    + "&prop=wikitext"
                    + "&page=" + _word.toLowerCase()
                    + "&format=xml";
            URL url = new URL(urlText);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output, wikiText = "";
            while ((output = br.readLine()) != null) {
                wikiText += output + "\n";
            }

            conn.disconnect();
            return wikiText;
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    private String loadFromDatabase(String word) throws Exception {
        String output = "";
        String db_query = "select \n"
                + "    old_text\n"
                + "from\n"
                + "    text\n"
                + "where\n"
                + "    old_id = (select \n"
                + "            rev_text_id\n"
                + "        from\n"
                + "            revision\n"
                + "        where\n"
                + "            rev_page in (select \n"
                + "                    page_id\n"
                + "                from\n"
                + "                    page\n"
                + "                where\n"
                + "                    page_title = '" + word
                + "' and page_namespace = 0))";

        databaseHelper db;

        try {
            db = new databaseHelper();
            db.select("use wiktionary;");

            ResultSet rs = db.select(db_query);

            while (rs.next()) {
                Blob blob = rs.getBlob("old_text");
                byte[] bdata = blob.getBytes(1, (int) blob.length());
                output = output + new String(bdata);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            throw exception;
        }

        return output;
    }

    public static List searchOnlineAPI(String _word) throws MalformedURLException, IOException {
        List Synonyms = new List();
        try {
            
            URL url = new URL("http://www.igrec.ca/project-files/wikparser/wikparser.php?word=" + _word + "&query=syn&lang=en");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                if (output.contains("No listed synonyms.")
                        || output.contains("ERROR: The Wiktionary API did not return a page for that word.")) {
                    return null;
                } else {
                    String[] arr = output.trim().split(" ");
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].length() > 2) {
                            Synonyms.add(arr[i].toString());
                        }
                    }
                }
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return Synonyms;
    }

    public ArrayList<String> getCompositeList() throws Exception {
        HashSet set = new HashSet();
        ArrayList<String> compositeList = new ArrayList();

        this.extractSection("synonyms");
        this.extractSection("derived_terms");
        this.extractSection("noun");

        set.addAll(this.synonyms);
        set.addAll(this.derivedTerms);
        set.addAll(this.nouns);

        compositeList.addAll(set);

        return compositeList;
    }

    public ArrayList<String> getSynonymsWordList() throws Exception {
        HashSet set = new HashSet();
        ArrayList<String> compositeList = new ArrayList();

        this.extractSection("synonyms");
        //this.extractSection("derived_terms");

        set.addAll(this.synonyms);
        //set.addAll(this.derivedTerms);

        compositeList.addAll(set);

        return compositeList;
    }
    
    public ArrayList<String> getSynonymsAndDerivedWordList() throws Exception {
        HashSet set = new HashSet();
        ArrayList<String> compositeList = new ArrayList();

        this.extractSection("synonyms");
        this.extractSection("derived_terms");

        set.addAll(this.synonyms);
        set.addAll(this.derivedTerms);

        compositeList.addAll(set);

        return compositeList;
    }
}
