/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blossom.logging;

import java.time.LocalDateTime;
import java.util.EventObject;

/**
 *
 * @author Luca Boldrin <luca.boldrin@telecomitalia.it>
 */
public class LoggerEvent extends EventObject {
    
    private final Level level;
    private final LocalDateTime logTime;
    private final String message;

    public LoggerEvent(Object source, Level level, LocalDateTime logTime, String message) {
        super(source);
        this.level = level;
        this.logTime = logTime;
        this.message = message;
    }

    /**
     * @return the level
     * @see org.blossom.logging.Level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return the logTime
     */
    public LocalDateTime getLogTime() {
        return logTime;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
}
