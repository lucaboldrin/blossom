/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.logging;

import java.util.EventListener;

/**
 *
 * @author 37226125
 */
public interface LoggerListener extends EventListener {
    
    public void setFilter(LoggerFilter Filter);
    public LoggerFilter getFilter();
    public void fireNewEvent(LoggerEvent Entry);
    
}
