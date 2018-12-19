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

import java.io.PrintWriter;

/**
 * Sheet is the basic interface for all objects that represent a worksheet or spreadsheet in a workbook
 * A sheet object contains multiple rows and can export the cell contents in a text file.
 * @author Luca Boldrin
 * @version %I%, %G%
 * @since 1.8
 */
public interface Sheet {
    
    /**
     * Returns the object that can be used to access to rows and can export the data stored in it
     * @param RowIndex the index of the row, if index is out of the bounds an ExcelException is raised
     * @return The Row object that contains the cells
     * @throws ExcelException if an IOException is raised, the exception is encapsulated in a ExcelException
     * @version %I%, %G%
     * @since 1.8
     */
    public Row getRow(int RowIndex) throws ExcelException;
    
    /**
     * Perform the export of the rows in a text file, delimited by the Delimiter param, each rows is terminated with a println
     * @param StartRow the first row to export
     * @param EndRow the last row to export
     * @param ColumnList the list of column to export i.e. { "A", "B", "AR" }
     * @param OutputFile the file where the values are written
     * @param Delimiter the field delimiter, normally is "," or ";"
     * @throws ExcelException if an IOException is raised, the exception is encapsulated in a ExcelException
     * @version %I%, %G%
     * @since 1.8

     */
    public void export(int StartRow, int EndRow, String[] ColumnList, PrintWriter OutputFile, String Delimiter) throws ExcelException;
    
   /**
    * Perform the export of the rows in a text file, delimited by the Delimiter param, each rows is terminated with a println
    * @param StartRow the first row to export, the value is parsed into integer, if the value isn't valid an ExcelException is thrown
    * @param EndRow the last row to export, the value is parsed into integer, if the value isn't valid an ExcelException is thrown
    * @param ColumnList the list of columns separated by a comma ",", i.e. "A,B,AR"
    * @param OutputFile the file where the values are written
    * @param Delimiter the field delimiter, normally is "," or ";"
    * @throws ExcelException if an IOException s raised, the exception is encapsulated in a ExcelException
    * @version %I%, %G%
    * @since 1.8
    */
    public void export(String StartRow, String EndRow, String ColumnList, PrintWriter OutputFile, String Delimiter) throws ExcelException;
    
    
}
