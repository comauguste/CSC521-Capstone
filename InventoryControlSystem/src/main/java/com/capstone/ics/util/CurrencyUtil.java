/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Auguste C
 */
public class CurrencyUtil {

    public static String convertBigDecimalToString(BigDecimal tempDecimal) {
        tempDecimal = tempDecimal.stripTrailingZeros();
        String tempString = tempDecimal.toPlainString();
        return tempString;
    }

    public static BigDecimal convertStringToBigDecimal(String value) {
        // Create a DecimalFormat that fits my requirements
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
       
        BigDecimal bigDecimal = BigDecimal.ZERO;
        try {
            // parse the string
            bigDecimal = (BigDecimal) decimalFormat.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(CurrencyUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bigDecimal;
    }
    
}
