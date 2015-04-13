/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pro.miner.classess;

/**
 *
 * @author AD SMAB
 */


import java.util.Scanner;
import javax.swing.DefaultListModel;
import static pro.miner.classess.StartStemmer.wordsmodel;
import static pro.miner.classess.StartStemmer.freqmodel;
 
class BubbleSort {

  public static void desc_sorting() {
    int n, c, f, d, swap;
    String stringswap;
    //Scanner in = new Scanner(System.in);
 
    System.out.println("Input number of integers to sort");
    n = freqmodel.size();
 
    int array[] = new int[n];
    String stringarray[] = new String[n];
 
    System.out.println("Enter " + n + " integers");
 
      
      for (c = 0; c < n; c++) {
//          if(wordsmodel.elementAt(c).toString()=="")
//          {
//              System.out.println("------------------------------"
//                      +"check empty '''': "+c);
//          }
//          else if(wordsmodel.elementAt(c).toString()==" ")
//          {
//              System.out.println("------------------------------"
//                      +"check space '' '': "+c);
//          }
      array[c] = Integer.parseInt(freqmodel.elementAt(c).toString());
      stringarray[c] = wordsmodel.elementAt(c).toString();
      System.out.println("copying array[c] "+array[c]+" : with stringarray[c] "+stringarray[c]);
      System.out.println("size of array[c] "+freqmodel.size()+" : size of stringarray[c] "+wordsmodel.size());
          
    }
    
    for (c = 0; c < ( n - 1 ); c++) {
      for (d = 0; d < n - c - 1; d++) {
        if (array[d] > array[d+1]) /* For descending order use < */
        {
          swap       = array[d];
          array[d]   = array[d+1];
          array[d+1] = swap;
          
          stringswap       = stringarray[d];
          stringarray[d]   = stringarray[d+1];
          stringarray[d+1] = stringswap;
        }
      }
    }
 
    System.out.println("Sorted list of numbers");
 
    freqmodel.clear();
    wordsmodel.clear();
    
    System.out.println("array "+array.length+" : stringarray "+stringarray.length);

    for (f = 2; f <= n; f++) {
        if(stringarray[n-f]!=null)
        {
            freqmodel.addElement(array[n-f]);
            wordsmodel.addElement(stringarray[n-f]);
            System.out.println(f + " - " + stringarray[n-f] + "has frequency" + array[n-f]);
        }
    }
    System.out.println("freqmodel "+freqmodel.size()+" : wordsmodel "+wordsmodel.size());    
  }
}