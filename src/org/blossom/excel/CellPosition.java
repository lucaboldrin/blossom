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
 * Utility class to manipulate the cell position
 * the full position is in this form [sheet]:[column][row]
 * example
 *          "1:AZ4"
 *          "sheet1:AR34"
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class CellPosition {
    private String sheet;
    private String column;
    private String row;
    private String rif;
    
    /**
     * Constructor - parse the the string parameter
     * @param Rif   String value of the position, it must be in this format [sheet]:[column][row]
     *              the sheet value is optional, if these is not, the colon sign ":" has to be omitted
     */
    public CellPosition(String Rif) {
        this.rif = Rif;
        decodeRif();
    }
    
    /**
     * Constructor - take the single values separately
     * @param Sheet the sheet value, can be numerical or alphabetic, examples of valid values "1","sheet2"
     * @param Column the column value, must be alphabetic, examples of valid values "A","B","BE","CZ"
     * @param Row the row value, must be numeric, examples of valid values "2","23","234"
     */
    public CellPosition(String Sheet, String Column, String Row) {
        this.sheet = Sheet;
        this.column = Column;
        this.row = Row;
        encodeRif();
    }
    
    /**
     * Return the sheet value, it can be numerical of alphabetic
     * @return the sheet value, null is sheet is omitted in constructor
     */
    public String getSheet() { 
        return sheet;
    }
     
    /**
     * Return the column value
     * @return the column value
     */
    public String getColumn() {
        return column;
    }
    
    
    /**
     * Return the row value
     * @return the row value
     */
    public String getRow() {
        return row;
    }
    
    
    /**
     * Return the complete position string
     * @return the position
     */
    public String getRif() {
        return rif;
    }
    
    /**
     * Return the sheet from a position value
     * @param Rif the string value of position
     * @return the sheet value or null if it is omitted in parameter Rif
     */
    public static String getSheet(String Rif) {
        int s = Rif.indexOf(":");
        if (s == -1) {
            return null;
        } else {
            return Rif.substring(0,s);
        }        
    }
    
    /**
     * Return the column from a position value
     * @param Rif the string value of position
     * @return the column value
     */
    public static String getColumn(String Rif) {
        StringBuilder bufCol = new StringBuilder();
        String tmp;
        int s = Rif.indexOf(":");
        if (s == -1) {
            tmp = Rif;
        } else {
            tmp = Rif.substring(s+1);
        }
        for (int i=0;i<tmp.length();i++) {
            if (Character.getType(tmp.charAt(i)) == Character.UPPERCASE_LETTER)
                bufCol.append(tmp.charAt(i));
        }
        return bufCol.toString();
    }
    
    /**
     * Return the row from a position value
     * @param Rif the string value of position
     * @return the row value
     */
    public static String getRow(String Rif) {
        StringBuilder bufRow = new StringBuilder();
        String tmp;
        int s = Rif.indexOf(":");
        if (s == -1) {
            tmp = Rif;
        } else {
            tmp = Rif.substring(s+1);
        }
        for (int i=0;i<tmp.length();i++) {
            if (Character.getType(tmp.charAt(i)) == Character.DECIMAL_DIGIT_NUMBER)
                bufRow.append(tmp.charAt(i));
        }
        return bufRow.toString();       
    }
    
    
    /**
     * Internal use
     */
    
    private void decodeRif() {
        StringBuilder bufCol = new StringBuilder();
        StringBuilder bufRow = new StringBuilder();
        String tmp;
        int s = rif.indexOf(":");
        if (s == -1) {
            sheet = null;
            tmp = rif;
        } else {
            sheet = rif.substring(0, s);
            tmp = rif.substring(s+1);
        }
        for (int i=0;i<tmp.length();i++) {
            if (Character.getType(tmp.charAt(i)) == Character.UPPERCASE_LETTER)
                bufCol.append(tmp.charAt(i));
            else
                bufRow.append(tmp.charAt(i));
        }
        row = bufRow.toString();
        column = bufCol.toString();
    }
    
    /**
     * Internal use
     */
    private void encodeRif() {
        StringBuilder buf = new StringBuilder();
        if (sheet != null) {
            buf.append(sheet);
            buf.append(":");
        }
        buf.append(column);
        buf.append(row);
        rif = buf.toString();
    }
}
