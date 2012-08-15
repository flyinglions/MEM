package org.flying.lions;

import java.io.IOException;
import java.util.Scanner;

public class RuleReader {
	
		//
		private String absaRule = "<ABSA,;> <accName,,> <TransactionType,,> <Date,;> <Location,,> <\"R\"amount,,> * <\"R\"balance,.> * * *";
	   	private String confFileName = "ABSARules.ini";
	    private String[] tokenedRules = null;

	    public void iniRules(){
	        //File locationFile = new File("");
	        //String fileLocation = locationFile.getCanonicalPath().toString() + "\\" + confFileName;

	        //File toReadFile = new File(fileLocation);
	        //Scanner lineScanner = new Scanner(toReadFile);
	        Scanner lineScanner = new Scanner(absaRule);
	        lineScanner.useDelimiter(" ");

	        int size = this.rulesSize();
	        tokenedRules = new String[size];

	        for (int a = 0; a < size; a++) {
	            tokenedRules[a] = lineScanner.next();
	        }
	    }

	    private int rulesSize(){
	        int result = 0;

	        //File locationFile = new File("");
	        //String fileLocation = locationFile.getCanonicalPath().toString() + "\\" + confFileName;

	        //File toReadFile = new File(fileLocation);
	        //Scanner lineScanner = new Scanner(toReadFile);
	        Scanner lineScanner = new Scanner(absaRule);
	        lineScanner.useDelimiter(" ");

	        while (lineScanner.hasNext()) {
	            result += 1;
	            lineScanner.next();
	        }
	        return result;
	    }

	    public String getConfFileName() {
	        return confFileName;
	    }

	    public String[] getTokenedRules() {
	        return tokenedRules;
	    }

}
