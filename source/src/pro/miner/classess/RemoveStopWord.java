/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import static forms.Form_text_mining.checkstop;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import pro.miner.classess.StartStemmer;
import static pro.miner.classess.StartStemmer.allstemmedtext;
//import pro.miner.classess.Stemmer;
import static pro.miner.classess.TreeSelection.fileText;

/**
 *
 * @author Devil Knight
 */
public class RemoveStopWord {
    
    public static String[] stopWordsSelfIdentified = {
        " also "," most "," each "," be "," more "," have "," may "," has "," other "," the "," that " 
    };
    
    public static String[] stopWordsofwordnet = {
         " without "," see "," unless "," due "," also "," must "," might "," like ",
         "]","[","}","{","<",">","?","\"  ","\\","/",")","(",
         " will "," may "," can "," much "," every "," the "," in "," other "," this "," the "," many "," any "," an "," or "," for "," in "," an "," an  "," is "," a "," about "," above "," after "," again "," against "," all "," am "," an "," and "," any "," are "," aren’t "," as "," at "," be "," because "," been "," before "," being "," below "," between "," both "," but "," by "," can’t "," cannot "," could " ,
         " couldn’t "," did "," didn’t "," do "," does "," doesn’t "," doing "," don’t "," down "," during "," each "," few "," for "," from "," further "," had "," hadn’t "," has "," hasn’t "," have "," haven’t "," having " ,
         " he "," he’d "," he’ll "," he’s "," her "," here "," here’s "," hers "," herself "," him "," himself "," his "," how "," how’s "," ing "," i  ","  i "," i’d "," i’ll "," i’m "," i’ve "," if "," in "," into "," is " ,
         " isn’t "," it "," it’s "," its "," itself "," let’s "," me "," more "," most "," mustn’t "," my "," myself "," no "," nor "," not "," of "," off "," on "," once "," only "," ought "," our "," ours "," ourselves " ,
         " out "," over "," own "," same "," shan’t "," she "," she’d "," she’ll "," she’s "," should "," shouldn’t "," so "," some "," such "," than " ,
         " that "," that’s "," their "," theirs "," them "," themselves "," then "," there "," there’s "," these "," they "," they’d "," they’ll "," they’re "," they’ve " ,
         " this "," those "," through "," to "," too "," under "," until "," up "," very "," was "," wasn’t "," we "," we’d "," we’ll "," we’re "," we’ve " ,
         " were "," weren’t "," what "," what’s "," when "," when’s "," where "," where’s "," which "," while "," who "," who’s "," whom " ,
         " why "," why’s "," with "," won’t "," would "," wouldn’t "," you "," you’d "," you’ll "," you’re "," you’ve "," your "," yours "," yourself "," yourselves " ,
         " Without "," See "," Unless "," Due "," Also "," Must "," Might "," Like "," Will "," May "," Can "," Much "," Every "," The "," In "," Other "," This "," The "," Many "," Any "," An "," Or "," For "," In "," An "," An  "," Is "," A "," About "," Above "," After "," Again "," Against "," All "," Am "," An "," And "," Any "," Are "," Aren’t "," As "," At "," Be "," Because "," Been "," Before "," Being "," Below "," Between "," Both "," But "," By "," Can’t "," Cannot "," Could " ,
         " Couldn’t "," Did "," Didn’t "," Do "," Does "," Doesn’t "," Doing "," Don’t "," Down "," During "," Each "," Few "," For "," From "," Further "," Had "," Hadn’t "," Has "," Hasn’t "," Have "," Haven’t "," Having " ,
         " He "," He’d "," He’ll "," He’s "," Her "," Here "," Here’s "," Hers "," Herself "," Him "," Himself "," His "," How "," How’s "," I  ","  I "," I’d "," I’ll "," I’m "," I’ve "," If "," In "," Into "," Is " ,
         " Isn’t "," It "," It’s "," Its "," Itself "," Let’s "," Me "," More "," Most "," Mustn’t "," My "," Myself "," No "," Nor "," Not "," Of "," Off "," On "," Once "," Only "," Ought "," Our "," Ours "," Ourselves " ,
         " Out "," Over "," Own "," Same "," Shan’t "," She "," She’d "," She’ll "," She’s "," Should "," Shouldn’t "," So "," Some "," Such "," Than " ,
         " That "," That’s "," Their "," Theirs "," Them "," Themselves "," Then "," There "," There’s "," These "," They "," They’d "," They’ll "," They’re "," They’ve " ,
         " This "," Those "," Through "," To "," Too "," Under "," Until "," Up "," Very "," Was "," Wasn’t "," We "," We’d "," We’ll "," We’re "," We’ve " ,
         " Were "," Weren’t "," What "," What’s "," When "," When’s "," Where "," Where’s "," Which "," While "," Who "," Who’s "," Whom " ,
         " Why "," Why’s "," With "," Won’t "," Would "," Wouldn’t "," You "," example "," You’d "," You’ll "," You’re "," You’ve "," Your "," Yours "," Yourself "," Yourselves "
    };

    public static ArrayList<String> afterstopwordlist = new ArrayList<String>();
    public static ArrayList<String> stemlist = new ArrayList<String>();
    public static ArrayList<String> wordsList = new ArrayList<>();
    public static ArrayList<String> removed = new ArrayList<>();

    public void remove() {
//        String wordList= TreeSelection.fileText;
        allstemmedtext = "";
        wordsList.clear();
        afterstopwordlist.clear();
        stemlist.clear();
        
        String[] words = TextMining.isFolder ? fileText.split(" ") : TextMining.methodIIFileText.split(" ");
        /*for (String w : words) {
         wordsList.add(w);
         } Next Line is replacement of this code */
        wordsList.addAll(Arrays.asList(words));

        System.out.println("PROCESS REMOVE STOP WORDS RUNNING");
        for (int i = 0; i < wordsList.size(); i++) {
            // get the item as string
            for (int j = 0; j < stopWordsofwordnet.length; j++) {
                if (stopWordsofwordnet[j].contains(wordsList.get(i))) {
                    removed.add(wordsList.get(i));
                    wordsList.remove(i);
                }
            }
        }
        System.out.println("PROCESS REMOVE STOP WORDS :: COMPLETE");

//        for (int i = 0; i < wordsList.size(); i++) {
//            // get the item as string
//            for (int j = 0; j < stopWordsofwordnet.length; j++) {
//                if (stopWordsofwordnet[j].contains(wordsList.get(i))) {
//                    removed.add(wordsList.get(i));
//                    wordsList.remove(i);
//                }
//            }
//        }
//
//        for (int i = 0; i < wordsList.size(); i++) {
//            // get the item as string
//            for (int j = 0; j < stopWordsofwordnet.length; j++) {
//                if (stopWordsofwordnet[j].contains(wordsList.get(i))) {
//                    removed.add(wordsList.get(i));
//                    wordsList.remove(i);
//                }
//            }
//        }

        for (String s : wordsList) {            
            afterstopwordlist.add(s);
        }

        StartStemmer st = new StartStemmer();
        st.Starter();

    }
}
