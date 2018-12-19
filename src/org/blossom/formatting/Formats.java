/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.formatting;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author 37226125
 */
public class Formats {
    
    public static final String DefaultDatePattern = "dd-MM-yyyy";
    public static final String DefaultTimePattern = "HH:mm:ss";
    public static final String DefaultDateTimePattern = "dd-MM-yyyy HH:mm:ss";
    
    
    public static DateTimeFormatter getDateFormatter(String Pattern) {
        return DateTimeFormatter.ofPattern(Pattern);
    }
    
    public static DateTimeFormatter getDateFormatter() {
        return getDateFormatter(DefaultDatePattern);
    }
    
    public static DateTimeFormatter getTimeFormatter(String Pattern) {
        return DateTimeFormatter.ofPattern(Pattern);
    }
    
    
    public static DateTimeFormatter getTimeFormatter() {
        return getTimeFormatter(DefaultTimePattern);
    }
    
    public static DateTimeFormatter getDateTimeFormatter(String Pattern) {
        return DateTimeFormatter.ofPattern(Pattern);
    }
    
    public static DateTimeFormatter getDateTimeFormatter() {
        return getDateTimeFormatter(DefaultDateTimePattern);
    }
    
    public static DateTimeFormatter getShortDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyyMMdd");
    }
    
}
