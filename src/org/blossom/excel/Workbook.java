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

import java.io.File;

/**
 * Represent the entire Excel document, it is the entry point to read the
 * data stored in.
 * @author Luca Boldrin
 * @version %I%, %G%
 * @since 1.8
 */
public interface Workbook {
    /**
     * Open the file, after this initialization method the data is ready to be read
     * <b>It is highly recommended to not call this method directly, use ExcelFactory to create and open a Workbook</b>.
     * @param WorkbookFile the file to open
     * @throws ExcelException if file don't exists the IOException is encapsulated in an ExcelException
     * @see ExcelFactory
     */
    public void open(File WorkbookFile) throws ExcelException;
    
    /**
     * Close the workbook, if it is not opened doesn't throw exceptions
     * @throws ExcelException if problem closing the file, the IOException is incapsulated in an ExcelException
     */
    public void close() throws ExcelException;
    
    /**
     * Return the Sheet object that represent a single spreadsheet in the excel document.
     * @param name  the name of the sheet, i.e. "sheet1"
     * @return the Sheet object, if sheet don't exists, the value returned is null
     * @throws ExcelException if workbook is not opened or if parameter name is null
     */
    public Sheet getSheet(String name) throws ExcelException;
    
    /**
     * Return the Sheet object that represent a single spreadsheet in the excel document.
     * @param Index the index of the sheet starting from 1
     * @return the Sheet object, it should be never null
     * @throws ExcelException if index is out of the bounds or if an IOException occured
     * @see #getSheetCount
     */
    public Sheet getSheet(int Index) throws ExcelException;
    
    /**
     * Return the number of sheets in the excel document.
     * @return the number of the sheets
     */
    public int getSheetCount();
    
    /**
     * Return the name of sheet
     * @param Index the integer index of the sheet
     * @return the name of sheet
     * @throws ExcelException if index is out of the bounds
     */
    public String getSheetName(int Index) throws ExcelException;
    
    
}
