package org.flying.lions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.File;

import android.os.Environment;


public class RuleReader {

    private String confFileName = "ABSARules.ini";
    private String[] tokenedRules = null;

    public RuleReader(String newConfName) {
        confFileName = newConfName;
    }

    RuleReader() {
        
    }

    public void iniRules() throws IOException {
     
        File sdcard = Environment.getExternalStorageDirectory();

      //Get the text file
        File file = new File(sdcard + "/MEM/ORI",confFileName);
    	
        Scanner sc = new Scanner(file);
        
        String tempString = sc.nextLine();
        
        
        sc.close();
        
        tokenedRules = tempString.split(" ");
    }



    public String getConfFileName() {
        return confFileName;
    }

    public String[] getTokenedRules() {
        return tokenedRules;
    }
}
