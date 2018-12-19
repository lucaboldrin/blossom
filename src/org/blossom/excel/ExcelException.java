/*
 * Copyright 2017 Luca Boldrin <luca.boldrin75@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.blossom.excel;

/**
 * Generic exception used in this package, also used to encapsule other exceptions
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class ExcelException extends Exception {
    /**
     * Construct an ExcelException with null message
     */
    public ExcelException() {
        super();
    }
    
    /**
     * Construct an ExcelExceptoion with the specified message
     * @param message the string message 
     */
    public ExcelException(String message) {
        super(message);
    }
    
    /**
     * Construct an ExcelException with the specified message and cause
     * @param message the string message
     * @param cause cause of exception, normally another exception 
     */
    public ExcelException(String message, Throwable cause) {
        super(message,cause);
    }
    
    /**
     * Construct the ExcelException with the specified cause
     * @param cause cause of the exception, normally another exception
     */
    public ExcelException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Construct an ExcelException with the specified message, cause with enableSuppresion enable/disable and writebleStackTrace enable/disable
     * @param message the string message
     * @param cause cause of the exception. normally another exception
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    protected ExcelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
