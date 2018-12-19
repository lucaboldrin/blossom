/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.logging;

/**
 *
 * @author 37226125
 */
public enum Level {
    DEBUG(0,"Debug"),
    INFO(1,"Info"),
    WARNING(2,"Warning"),
    MINOR(3,"Minor"),
    MAJOR(4,"Major"),
    CRITICAL(5,"Critical");
    
    private final int severity;
    private final String name;
    
    Level(int Severity, String Name) {
        this.severity = Severity;
        this.name = Name;
    }
    
    public static Level forName(String name) {
        Level ltype;
        switch(name.toUpperCase()) {
            case "DEBUG" :  ltype = DEBUG;
                            break;
            case "INFO" :   ltype=INFO;
                            break;
            case "WARNING" :    ltype=WARNING;
                                break;
            case "MINOR" :  ltype=MINOR;
                            break;
            case "MAJOR" :  ltype=MAJOR;
                            break;
            case "CRITICAL" :   ltype=CRITICAL;
                                break;
            default :   ltype=null;
        }
        return ltype;
    }
    
    public int compare(Level Level1, Level Level2) {
        if (Level1.severity == Level2.severity)
            return 0;
        if (Level1.severity < Level2.severity)
            return -1;
        else
            return 1;
        
    }
    
    @Override
    public String toString() {
        return name;
    }
}
