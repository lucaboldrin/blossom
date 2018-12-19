/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.argument;

/**
 *
 * @author Luca Boldrin <luca.boldrin@telecomitalia.it>
 */
public class ArgumentMalformedException extends Exception {
    
    public ArgumentMalformedException() {
        super();
    }
    
    public ArgumentMalformedException(String message) {
        super(message);
    }
    
    public ArgumentMalformedException(String message, Throwable cause) {
        super(message,cause);
    }
    
    public ArgumentMalformedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);
    }
    
    public ArgumentMalformedException(Throwable cause) {
        super(cause);
    }
    
}
