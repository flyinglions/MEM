package org.flying.lions;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class SMSHandler {
	
	private static final String TAG = "SMSHandler";
	
    private RuleBuilder ruleBuild = new RuleBuilder();
    private static int currentLocation = 0;
    private Rule[] ruleList = null;
    private String[] valueArray = null;
    private String[] realValue = null;
    private int messageSize = 0;
    private String smsString = "";
    private static boolean isValid = true;
    private SQLGenerator SQLGen = new SQLGenerator();

    public void recieveSMS(String inSms) throws IOException{
        smsString = inSms;
        ruleBuild.ruleParser();
        messageSize = ruleBuild.getRuleLeng();
        ruleList = ruleBuild.getRuleList();
        valueArray = ruleBuild.getValueArray();
        realValue = new String[messageSize];        

        for (int y = 0; y < messageSize; y++) {
            try{
                if(isValid)
                    this.parseSMS(y);
                else{
               System.err.println("SMS is not valid: '" + inSms +"'"); 
               currentLocation = 0; 
               isValid = true;
               return;
                }
                
            }catch(Exception e){
               System.err.println("SMS is not valid: '" + inSms +"'"); 
               currentLocation = 0; 
               isValid = true;
               return;
            }
        }
       
        System.out.println("\n\n" + this);
        SQLGen.buildSQL(realValue);
        currentLocation = 0;
        isValid = true;

    }

    private void parseSMS(int where) {
        realValue[where] = ruleList[where].doRule(smsString, currentLocation);
    }

    public static void setStart(int start) {
        start += 1;
        currentLocation += start;
    }
    
    public static void setValid(boolean toChange){
        isValid = toChange;
    }    

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        for (int y = 0; y < messageSize; y++) {
            returnString.append(y).append(": ").append(realValue[y]).append(" -- ").append(valueArray[y]).append("\n");
        }
        return returnString.toString();
    }  
    
    public JSONObject storeJSONObject(){
    	//StringBuilder returnString = new StringBuilder();
    	JSONObject returnObject = new JSONObject();
    	
    	//TODO Change hardcoded assignments
    	try
    	{
    		if(realValue[0].equals("Absa"))
    		{
	    		returnObject.put("bank", realValue[0]);
	    		returnObject.put("accName", realValue[1]);
	    		returnObject.put("transaction", realValue[2]);
	    		returnObject.put("date", realValue[3]);
	    		returnObject.put("description", realValue[4]);
	    		returnObject.put("amount", realValue[5]);
	    		returnObject.put("balance", realValue[7]);
    		}
    		else 
    		{
    			returnObject.put("bank", "NOT BANK SMS");
    			return returnObject;
    		}
    	
    	}
    	
		catch (JSONException e)
		{
	 	   	Log.e(TAG, "JSON exception");
		}
    	
    	return returnObject;
    }

}
