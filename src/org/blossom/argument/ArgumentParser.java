/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.argument;

import java.util.Properties;

/**
 *
 * @author Luca Boldrin 26/10/2018 
 */
public class ArgumentParser {
    
    /**
     * 
     * @param args The array of parameter arguments, the format must be 
     *      -[param_name]=[param_value]
     * es :
     *      -locale=en
     *      -debug=true
     * @return a Property object that map the argument name to argument value
     */
    public static Properties parse(String args[]) throws ArgumentMalformedException {
        Properties p = new Properties();
        int len = args.length;
        int i=0;
        int pos=0;
        String key="",value="";
        for (String tmp : args) {
            if (tmp.startsWith("--")) {
                pos = tmp.indexOf('=');
                if (pos == -1)
                    throw new ArgumentMalformedException("argument ["+tmp+"] not valid");
                else {
                    key = tmp.substring(2, pos);
                    value= tmp.substring(pos+1);
                    p.setProperty(key, value);
                }
            }
        }
        return p;
        /*
        old format
        -l it
        -d
        
        if (len > 1) {
            for (i=0;i<len;i++) {
                value = "";
                tmp = args[i];
                if (tmp.startsWith("-")) {
                    key = tmp.substring(1);
                    if (i < (len -1) ) {
                        tmp = args[i+1];
                        if (! tmp.startsWith("-")) {
                            value = tmp;
                        }
                    }
                    p.setProperty(key, value);
                }
            }
        }
        */
    }
    
}
