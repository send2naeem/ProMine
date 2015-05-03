/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.miner.classess;

import core.Category;
import java.util.ArrayList;

/**
 *
 * @author Farooq
 */
public class Global {
    public static String file_text = "";
    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static boolean categoriesProcessed = false;
    public static boolean categoryManageEnabled = false;
    public static String CurrentContextFile = "";
    public static boolean glossaryProcessed = false;
}
