/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.logging;

/**
 *
 * @author Luca Boldrin <luca.boldrin@telecomitalia.it>
 */
public interface LoggerFilter {
    
    public boolean accept(LoggerEvent Entry);
    
}
