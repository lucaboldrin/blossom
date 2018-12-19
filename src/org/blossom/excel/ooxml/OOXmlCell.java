package org.blossom.excel.ooxml;

import org.blossom.excel.Cell;



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


/**
 *
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class OOXmlCell implements Cell{
    
    private final String rif;
    private final String value;
    private String column;
    private String row;
    
    protected OOXmlCell(String Rif, String Value) {
        this.rif = Rif;
        this.value = Value;
        decodeRif();
    }
    
    @Override
    public String getRif() {
        return rif;
    }
    
    @Override
    public String getColumn() {
        return column;
    }
    
    @Override
    public String getRow() {
        return row;
    }
    
    @Override
    public String getText() {
        return value;
    }
    
    @Override
    public int getInt() throws NumberFormatException {
        return Integer.parseInt(value);
    }

    @Override
    public double getDouble() throws NumberFormatException {
        return Double.parseDouble(value);
    }
    
    private void decodeRif() {
        int len = rif.length();
        char ch;
        StringBuilder colBuffer = new StringBuilder();
        StringBuilder rowBuffer = new StringBuilder();
        for (int i=0;i<len;i++) {
            ch = rif.charAt(i);
            if (Character.getType(ch) == Character.DECIMAL_DIGIT_NUMBER) 
                rowBuffer.append(ch);
            else
                colBuffer.append(ch);
        }
        column = colBuffer.toString();
        row = rowBuffer.toString();
    }
}
