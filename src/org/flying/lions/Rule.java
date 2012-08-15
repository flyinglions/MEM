package org.flying.lions;

public class Rule {
	private int ruleNumber = 0;
    private String playString = "";

    Rule(int toDo) {
        ruleNumber = toDo;
    }

    /**
     * Rule list:
     * 1 - Skip till next token := *
     * 2 - Token is till ',' := <Value,,>
     * 3 - Token is till '' := <Value,; >
     * 4 - Token is till '.' := <Value,.>
     * 5 - Token is from end of  last" till ',' := <"R"Value,,>
     * 6 - Token is from end of  last" till ' ' := <"R"Value,,>     * 
     */
    public String doRule(String getToken, int Start) {
        playString = new String(getToken);
        playString = this.removeTo(Start);
        //System.out.println(playString + "::" + Start + "\n");
        if (ruleNumber == 0) {
            //System.out.append("Init Rule");
            return "";
        }
        switch (ruleNumber) {
            case 1: {
                return skipToken().trim();
            }
            case 2: {
                return getToBreak().trim();
            }
            case 3: {
                return getToSpace().trim();
            }
            case 4: {
                return getToPoint().trim();
            }
            case 5: {
                return getFromHashToBreak().trim();
            }
            case 6: {
                return getFromHashToPoint().trim();
            }
        }
        return "";
    }

    private String skipToken() {
        return "*";
    }

    private String getToBreak() {
        String returnString = "";

        int breakCon = playString.indexOf(",") + 1;
        returnString = playString.substring(0, breakCon);
        SMSHandler.setStart(breakCon);
        returnString = removeFinalBreaks(returnString);
        return returnString;
    }

    private String getToSpace() {
        String returnString = "";

        int breakCon = playString.indexOf(" ");
        returnString = playString.substring(0, breakCon);
        SMSHandler.setStart(breakCon);
        returnString = removeFinalBreaks(returnString);
        return returnString;
    }

    private String getToPoint() {
        String returnString = "";

        int breakCon = playString.indexOf(".");
        returnString = playString.substring(0, breakCon);
        SMSHandler.setStart(breakCon);
        returnString = removeFinalBreaks(returnString);
        return returnString;
    }

    private String getFromHashToBreak() {
        String returnString = "";
        int breakFirst = playString.indexOf("R-") + 1;
        int breakLast = playString.indexOf(",");
        int prev = breakLast;

        if (breakFirst == 0) {
            breakFirst = playString.indexOf("R") + 1;
        }

        if (breakFirst < 0) {
            breakFirst = 0;
        }

        if (breakFirst > breakLast) {
            playString = this.removeTo(breakLast + 1).trim();
            breakFirst = playString.indexOf("R") + 1;
            breakLast = playString.indexOf(" ") - 1;
            if (breakFirst < 0) {
                breakFirst = 0;
            }
            SMSHandler.setStart(breakLast + prev);
        } else {
            SMSHandler.setStart(breakLast);
        }
        //      System.out.println(playString + ": " + breakLast);

        returnString = playString.substring(breakFirst, breakLast);

        return returnString;
    }

    private String getFromHashToPoint() {
        String returnString = "";
        //System.out.println(playString);
        int breakFirst = playString.indexOf("R") + 1;
        int breakLast = playString.indexOf(".") + 3;
        if (breakFirst < 0) {
            breakFirst = 0;
        }
        SMSHandler.setStart(breakLast);
        returnString = playString.substring(breakFirst, breakLast);

        return returnString;
    }

    @Override
    public String toString() {
        return "Rule Nr: " + ruleNumber;
    }

    private String removeTo(int where) {
        return playString.substring(where).trim();
    }

    private String removeFinalBreaks(String toTrim) {
        int leng = toTrim.length();
        if (toTrim.endsWith(",") || toTrim.endsWith(":")) {
            toTrim = toTrim.substring(0, leng - 1);
        }
        return toTrim;
    }

}
