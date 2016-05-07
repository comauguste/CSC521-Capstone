/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.util;

import java.awt.BorderLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Auguste C
 */
public class Validator {

    private final Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Validator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validateEmail(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

    /**
     * @param phoneNumber
     * @return true if the phone number is correct
     */
    public boolean isPhoneNumberCorrect(String phoneNumber) {

        Pattern pattern = Pattern
                .compile("((\\+[1-9]{3,4}|0[1-9]{4}|00[1-9]{3})\\-?)?\\d{8,20}");
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            return true;
        }

        return false;
    }
    
    public boolean isCurrencyCorrect(String currency) {

        Pattern pattern = Pattern
                .compile("^\\d+\\.\\d{2}");
        Matcher matcher = pattern.matcher(currency);

        if (matcher.matches()) {
            return true;
        }

        return false;
    }

//    public static void main(String[] args) {
//        
//        Validator test = new Validator();
//        System.out.println("23.234,00: "+test.isCurrencyCorrect("23,234.00"));   
//        System.out.println("234.00: "+test.isCurrencyCorrect("234.00")); 
//        System.out.println("-234,00: "+test.isCurrencyCorrect("-234,00")); 
//        System.out.println("aassad: "+test.isCurrencyCorrect("aassad")); 
//        
//    }
}
