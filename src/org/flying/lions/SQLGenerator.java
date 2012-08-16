package org.flying.lions;

import java.io.FileWriter;
import java.io.IOException;

public class SQLGenerator {
	
	private float prevSaldo = 0;
    private String newLine = System.getProperty("line.separator");    
    
    public void buildSQL(String [] realValue) throws IOException{
        
              this.writeToFile(this.buildInsert(realValue),this.buildRecon(realValue[7],realValue[5],realValue[5]));  
    }
    
    private String buildInsert(String[] realValue){
        String SQLStatement = "INSERT INTO sms values(";
        SQLStatement += realValue[3] +",'" + realValue[2] + "'," + this.removeMinus(realValue[5])+","+ realValue[7]+","+realValue[4] +")";
        System.out.println(SQLStatement);
        return SQLStatement;

    }
    
    private String buildRecon(String currBal,String diff,String Loc){
        String SQLStatement = "INSERT INTO Reconciliation values('";
        SQLStatement += this.getCategories(Loc) +"','"+this.getExspensesIncome(diff) + "'," + this.getRecon(currBal,diff)+ ")";
        System.out.println(SQLStatement);
        return SQLStatement;
    }
    
    private String removeMinus(String inValue){
        if(inValue.startsWith("-"))
            inValue = inValue.substring(1);
        return inValue;        
    }
    
    private String getExspensesIncome(String inCurrancy){
         if(inCurrancy.contains("-"))
            return "Expense";
        else
            return "Income";
    }
    
    private String getCategories(String Loc){
        if(Loc.contains("SPAR") || Loc.contains("CHECKERS"))
            return "Food";
        else
            return "";
    }

    private String getRecon(String stringVal,String diff) {
        stringVal = stringVal.replaceAll(",", "");
        diff = diff.replaceAll(",", "");
        float currBal = Float.parseFloat(stringVal);
        float currDiff = Float.parseFloat(diff);
        float recon = 0;       
        
        if(prevSaldo == 0){
            prevSaldo = currBal;
        return "0.00";
        }
         float newBal = 0;         

         newBal = prevSaldo + currDiff;
         
        if(currBal == newBal){
            prevSaldo = currBal;
            return "0.00";
        }else{
           recon = newBal - currBal; 
           prevSaldo = currBal;
        }
        recon = (float) (Math.round(recon*100.00)/100.00);
        String reconString = String.valueOf(recon);
        return reconString;
    }
    
    private void writeToFile(String Insert,String recon) throws IOException{
	      /*File locationFile = new File("");
	      String fileLocation = locationFile.getCanonicalPath().toString() + "\\SQLStatements.txt";
          FileWriter fstream = new FileWriter(fileLocation,true);
          BufferedWriter out = new BufferedWriter(fstream);*/
    	 FileWriter fileWriter;
	   	 fileWriter = new FileWriter("/mnt/sdcard/SQLStatements.txt", true);
	   	 fileWriter.append(Insert + "\r\n");
	     fileWriter.append(recon + "\r\n");
	   	 fileWriter.flush();
	   	 fileWriter.close();    	
    	
          //out.write(Insert + newLine);
          //out.write(recon + newLine);
          //out.close();
    }	

}
