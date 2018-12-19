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
 * Represent a row in a spreadsheet of an excel document, it contains multiple cells
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public interface Row {
    
    /**
     * Return the Cell object
     * @param ColumnIndex the column index
     * @throws ExcelException if ColumnIndex if out the bounds
     * @return the Cell object
     */
    public Cell getCell(int ColumnIndex) throws ExcelException;
    
    /**
     * Return the Cell object
     * @param ColumnName the column in alphabetic value, example "A" or "BD"
     * @return the Cell object
     */
    public Cell getCell(String ColumnName);
    
    /**
     * Return the number of cells stored in this Row object
     * @return the count of cells
     */
    public int getCellCount();
    
}
