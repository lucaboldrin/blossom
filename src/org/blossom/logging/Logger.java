/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.logging;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author 37226125
 */
public class Logger {
    
    private final ArrayList<LoggerListener> listeners;
    
    
    public Logger() {
        listeners = new ArrayList<>();
    }
    
    public void log(LoggerEvent Event) {
        listeners.stream().forEach((l) -> {
            l.fireNewEvent(Event);
        });    
    }
    
    public void log(Object Source, Level Level, LocalDateTime LogTime, String Message) {
        log(new LoggerEvent(Source,Level,LogTime,Message));
    }
    
    public void log(Object Source, Level Level, String Message) {
        log(Source,Level,LocalDateTime.now(),Message);
    }
    
    public void log(Object Source, String Message) {
        log(Source, Level.INFO,LocalDateTime.now(),Message);
    }
    
    public void addListener(LoggerListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(LoggerListener listener) {
        listeners.remove(listener);
    }
    
    public void clearListeners() {
        listeners.clear();
    }
    
}
