/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Keep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ClaraU
 */
public class FileManager {

    File dir;
    BufferedReader bf;
    PrintWriter pw;
    String string;

    public FileManager() {
        System.out.println("paso");
        string = "C:\\Users\\ClaraU\\Documents";//System.getProperty("user.dir"); //Folder used for all the files
    }

    public void UserPassword(String user, String password) {
        String saveUser = "C:\\Users\\ClaraU\\Documents" + "\\saveUser.txt";
        System.out.println(saveUser);
        dir = new File(saveUser);
        if (!dir.exists()) {
            try {
                dir.createNewFile(); //Create a new file
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            pw = new PrintWriter(new FileWriter(dir, true));
            pw.append(user + "\n");
            pw.append(password + "\n");
            pw.append("\n");
            pw.close();

        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void FixedVariables(int id, String name, int age, boolean gender, double weight, double height) {
        String saveFixedVar = "C:\\Users\\ClaraU\\Documents" + "\\saveFixed.txt";
        System.out.println(saveFixedVar);
        dir = new File(saveFixedVar);
        if (!dir.exists()) {
            try {
                dir.createNewFile(); //Create a new file
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            pw = new PrintWriter(new FileWriter(dir, true));
            pw.append(id + "\n"); ///ID???
            pw.append(name + "\n");
            pw.append(age + "\n");
            pw.append(gender + "\n");
            pw.append(weight + "\n");
            pw.append(height + "\n");
            pw.append("\n");
            pw.close();

        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangingVariables(int flex_ang, int turn_ang, ArrayList<Integer> bitalino) {
        String saveChangingVar = "C:\\Users\\ClaraU\\Documents" + "\\saveFixed.txt";
        System.out.println(saveChangingVar);
        dir = new File(saveChangingVar);
        if (!dir.exists()) {
            try {
                dir.createNewFile(); //Create a new file
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            pw = new PrintWriter(new FileWriter(dir, true));

            pw.append(flex_ang + "\n");
            pw.append(turn_ang + "\n");
            pw.append(bitalino + "\n");
            pw.append("\n");
            pw.close();

        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
