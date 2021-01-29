package in.co.sunrays.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates input data
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class DataValidator {

    /**
     * Checks if value is Null
     *
     * @param val
     * @return
     */
	
	
	public static boolean isMobileNo(String s) 
	{ 
	      
	    // The given argument to compile() method  
	    // is regular expression. With the help of  
	    // regular expression we can validate mobile 
	    // number.  
	    // 1) Begins with 0 or 91 
	    // 2) Then contains 7 or 8 or 9. 
	    // 3) Then contains 9 digits 
	    Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
	  
	    // Pattern class contains matcher() method 
	    // to find matching between given number  
	    // and regular expression 
	    Matcher m = p.matcher(s); 
	    return (m.find() && m.group().equals(s)); 
	} 
	  
    public static boolean isNull(String val) {
        if (val == null || val.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if value is NOT Null
     *
     * @param val
     * @return
     */
    public static boolean isNotNull(String val) {
        return !isNull(val);
    }

    /**
     * Checks if value is an Integer
     *
     * @param val
     * @return
     */

    public static boolean isInteger(String val) {

        if (isNotNull(val)) {
            try {
                int i = Integer.parseInt(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Long
     *
     * @param val
     * @return
     */
    public static boolean isLong(String val) {
        if (isNotNull(val)) {
            try {
                long i = Long.parseLong(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is valid Email ID
     *
     * @param val
     * @return
     */
    public static boolean isEmail(String val) {

        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (isNotNull(val)) {
            try {
                return val.matches(emailreg);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Date
     *
     * @param val
     * @return
     */
    public static boolean isDate(String val) {

        Date d = null;
        if (isNotNull(val)) {
            d = DataUtility.getDate(val);
        }
        return d != null;
    }

    /**
     * Test above methods
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Not Null 2" + isNotNull("ABC"));
        System.out.println("Not Null 3" + isNotNull(null));
        System.out.println("Not Null 4" + isNull("123"));

        System.out.println("Is Int " + isInteger(null));
        System.out.println("Is Int " + isInteger("ABC1"));
        System.out.println("Is Int " + isInteger("123"));
        System.out.println("Is Int " + isNotNull("123"));
    }

}
