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
import org.blossom.excel.msexcel.MSExcelWorkbook;
import org.blossom.excel.ods.ODSWorkbook;
import org.blossom.excel.ooxml.OOXmlWorkbook;

/**
 * Factory used to get the correct implementation Workbook for the format of the excel document
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class ExcelFactory {
    
    private static ExcelFactory factory;
    
    /**
     * This constructor is not meant for public use
     */
    private ExcelFactory() {
        
    }
    
    /**
     * Get the instance of this factory
     * @return The factory needed to open the excel document
     */
    public static ExcelFactory getInstance() {
        if (factory == null)
            factory = new ExcelFactory();
        return factory;
    }
    
    
    /**
     * Open an excel document, it return the correct implementation of the Workbook interface depending of the format fo the excel document
     * @param WorkbookFile the file to open
     * @return the Workbook object
     * @throws ExcelException if WorkbookFile is null or WorkbookFile doesn't exists or format is unknown
     */
    public Workbook open(File WorkbookFile) throws ExcelException {
        Workbook wb = null;
        String name = WorkbookFile.getName();
        if (! WorkbookFile.exists())
            throw new ExcelException("File "+WorkbookFile.getName()+" does not exists.");
        if (name.endsWith("*.xlsx")) {
            wb = new OOXmlWorkbook();
            wb.open(WorkbookFile);
        } else if (name.endsWith("*.ods")) {
            wb = new ODSWorkbook();
            wb.open(WorkbookFile);
        } else if (name.endsWith("*.xls")) {
            wb = new MSExcelWorkbook();
            wb.open(WorkbookFile);
        } else {
            throw new ExcelFormatUnknown();
        }
        return wb;
    }
    /**
     * Open an excel document, it return the correct implementation of the Workbook interface depending of the format of the excel document
     * @param WorkbookPath the String path to open
     * @return the Workbook object
     * @throws ExcelException if WorkbookFile is null or WorkbookFile doesn't exists or format is unknown
     */
    public Workbook open(String WorkbookPath) throws ExcelException {
        if (WorkbookPath == null)
            throw new ExcelException("The specified path is null.");
        return open(new File(WorkbookPath));
    }
    
}
