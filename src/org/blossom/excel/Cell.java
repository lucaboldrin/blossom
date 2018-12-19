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
 * Cell represent the single cell in a spreadsheet, it is immutable.
 * @author Luca Boldrin
 * @version %I%, %G%
 */

public interface Cell {
    
    /**
     * Return the string coordinates in excel format in alphabetic columns and numeric rows, example "AB12".
     * @return the string coordinates
     */
    public String getRif();
    
    /**
     * Return the column in alphabetic value, example "A" or "AR"
     * @return the string value of column
     */
    public String getColumn();
    
    /**
     * Return the numeric value of the row in string format, example "23"
     * @return the numeric value of the row
     */
    public String getRow();
    
    /**
     * Return the contents of the cell in string format.
     * @return the contents of the cell 
     */
    public String getText();
    
    /**
     * Return the contents of the cell in integer format
     * @return the integer value of the cell content
     * @throws NumberFormatException if the value in not an integer
     */
    public int getInt() throws NumberFormatException;
    
    /**
     * Return the contents of the cell in double format
     * @return the double value of the cell content 
     * @throws NumberFormatException if the value is not a double
     */
    public double getDouble() throws NumberFormatException;
    
    
}
