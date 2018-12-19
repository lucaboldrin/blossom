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
package org.blossom.excel.msexcel;

import java.io.File;
import org.blossom.excel.ExcelException;
import org.blossom.excel.ExcelFormatNotSupported;
import org.blossom.excel.Sheet;
import org.blossom.excel.Workbook;


/**
 * Concrete class of Workbook interface, it manages the format MS Excel Binary Format (*.xls)
 * actually is not implemented, any attempt to open a MSExcel file format will raise an ExcelFormatNotSupported Exception
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class MSExcelWorkbook implements Workbook {

    /**
     * Open the file, after this initializazion method the data is ready to be read
     * <b>It is highly reccomended to not call this method directly, use ExcelFactory to create and open a Workbook</b>.
     * @param WorkbookFile the file to open
     * @throws ExcelException if file don't exists the IOException is incapsulated in an ExcelException
     * @see org.lucaboldrin.excel.Workbook#open(java.io.File) 
     */
    @Override
    public void open(File WorkbookFile) throws ExcelException {
        throw new ExcelFormatNotSupported("MS Excel"); 
    }

    /**
     * Close the workbook, if it is not opened doesn't throw exceptions
     * @throws ExcelException if problem closing the file, the IOException is incapsulated in an ExcelException
     * @see org.lucaboldrin.excel.Workbook#close() 
     */
    @Override
    public void close() throws ExcelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Return the Sheet object that represent a single spreadsheet in the excel document.
     * @param name  the name of the sheet, i.e. "sheet1"
     * @return the Sheet object, if sheet don't exists, the value returned is null
     * @throws ExcelException if workbook is not opened or if parameter name is null
     * @see org.lucaboldrin.excel.Workbook#getSheet(java.lang.String) 
     */
    @Override
    public Sheet getSheet(String name) throws ExcelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Return the number of sheets in the excel document.
     * @return the number of the sheets
     */
    @Override
    public int getSheetCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Return the name of sheet
     * @param Index the integer index of the sheet
     * @return the name of sheet
     * @throws ExcelException if index is out of the bounds
     * @see org.lucaboldrin.excel.Workbook#getSheetName(int) 
     */
    @Override
    public String getSheetName(int Index) throws ExcelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Return the name of sheet
     * @param Index the integer index of the sheet
     * @return the name of sheet
     * @throws ExcelException if index is out of the bounds
     * @see org.lucaboldrin.excel.Workbook#getSheet(int) 
     */
    @Override
    public Sheet getSheet(int Index) throws ExcelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
