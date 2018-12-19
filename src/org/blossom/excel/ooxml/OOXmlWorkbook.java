package org.blossom.excel.ooxml;

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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.blossom.excel.ExcelException;
import org.blossom.excel.Sheet;
import org.blossom.excel.Workbook;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * This class implements the Workbook interface for Office Open Xml format 
 * usually this format has the *.xlsx suffix 
 * for complete description <a href="https://www.iso.org/standard/51463.html">ISO/IEC 29500:2008</a>
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class OOXmlWorkbook implements Workbook {

    private File wbFile;
    private ZipFile zipFile;

    private String[] sharedStrings;
    private String[] sheetNames;
    private int sharedStringCount;

    private static final String SharedStringPath = "xl/sharedStrings.xml";
    private static final String SheetBasePath = "xl/worksheets/sheet%1$s.xml";
    private static final String WorkbookPath = "xl/workbook.xml";

    
    /**
     * Construct an OOXmlWorkbook, 
     * <b>It is highly recommended to create and open excel document with ExcelFactory</b>
     * @see jtools.excel.ExcelFactory
     */
    public OOXmlWorkbook() {
        wbFile = null;
        zipFile = null;
    }

    /**
     * For internal use
     */
    private void init(File WorkbookFile) throws IOException {
        try {
            this.wbFile = WorkbookFile;
            this.zipFile = new ZipFile(wbFile);
        } catch (ZipException e) {
            throw new IOException(e);
        }
        loadSharedStrings();
        loadWorkbook();

    }
    
    /**
     * Close the workbook, if it is not opened doesn't throw exceptions
     * @throws ExcelException if problem closing the file, the IOException is encapsulated in an ExcelException
     * @see org.lucaboldrin.excel.Workbook#close() 
     */
    @Override
    public void close() throws ExcelException {
        try {
            if (zipFile != null)
                zipFile.close();
        } catch (IOException ex) {
            throw new ExcelException(ex);
        }
    }

    /**
     * Open the file, after this initialization method the data is ready to be read
     * <b>It is highly recommended to not call this method directly, use ExcelFactory to create and open a Workbook</b>.
     * @param WorkbookFile the file to open
     * @throws ExcelException if file don't exists the IOException encapsulated in an ExcelException
     * @see org.lucaboldrin.excel.Workbook#open(java.io.File) 
     */    
    @Override
    public void open(File WorkbookFile) throws ExcelException {

        try {
            init(WorkbookFile);
        } catch (IOException ex) {
            throw new ExcelException(ex);
        }
    }

    /**
     * Return the Sheet object that represent a single spreadsheet in the excel document.
     * @param Index the index of the sheet starting from 1
     * @return the Sheet object, it should be never null
     * @throws ExcelException if index is out of the bounds or if an IOException is raised
     * @see org.lucaboldrin.excel.Workbook#getSheet(int) 
     * @see #getSheetCount
     */
    @Override
    public Sheet getSheet(int Index) throws ExcelException {
        if (wbFile == null) {
            throw new ExcelException("File " + wbFile.getName() + " is not opened.");
        }
        if ((Index < 1) || (Index > (sheetNames.length - 1))) {
            throw new ExcelException("Sheet index must be between 1 and " + (sheetNames.length - 1));
        }
        String sheetPath = String.format(SheetBasePath, Index);
        ZipEntry entry = zipFile.getEntry(sheetPath);
        OOXmlSheet sh;
        try {
            sh = new OOXmlSheet(this, Index, zipFile.getInputStream(entry));
        } catch (IOException ex) {
            throw new ExcelException(ex);
        }
        return sh;
    }

    /**
     * Return the Sheet object that represent a single spreadsheet in the excel document.
     * @param name  the name of the sheet, i.e. "sheet1"
     * @return the Sheet object, if sheet don't exists, the value returned is null
     * @throws ExcelException if workbook is not opened or if parameter name is null
     */        
    @Override
    public Sheet getSheet(String name) throws ExcelException {
        int index;
        if (name == null) {
            throw new ExcelException("Sheet name is null");
        }
        try {                                               // try if name is a number es. "1"
            index = Integer.parseInt(name);
            return getSheet(index);
        } catch (NumberFormatException ex) {                 // if no, assumed it's a name es. "sheet1"
            for (int i = 0; i < sheetNames.length; i++) {
                if (sheetNames[i].equals(name)) {
                    return getSheet(i);
                }
            }
        }
        return null;
    }

    /**
     * Return the number of sheets in the excel document.
     * @return the number of the sheets
     */        
    @Override
    public int getSheetCount() {
        return sheetNames.length - 1;
    }

    /**
     * Return the name of sheet
     * @param Index the integer index of the sheet
     * @return the name of sheet
     * @throws ExcelException if index is out of the bounds
     */    
    @Override
    public String getSheetName(int Index) throws ExcelException {
        if ((Index < 1) || (Index > (sheetNames.length - 1))) {
            throw new ExcelException("Sheet index must be between 1 and " + (sheetNames.length - 1));
        }
        return sheetNames[Index];
    }

    /**
     * Return the string value from the SST (Shared String Table)
     * @param index the value index in the table
     * @return the string value
     */  
    public String getStringValue(int index) {
        return sharedStrings[index];
    }

    /**
     * Read all the SST (Shared String Table) in memory, it use a SAX parser
     * @throws IOException if an IOException is raised reading the file
     * @see OOXmlWorkbook#SharedStringParser
     * 
     */    
    private void loadSharedStrings() throws IOException {
        try {
            sharedStringCount = 0;
            XMLReader myReader = XMLReaderFactory.createXMLReader();
            myReader.setContentHandler(new OOXmlWorkbook.SharedStringParser());
            myReader.parse(new InputSource(zipFile.getInputStream(zipFile.getEntry(SharedStringPath))));
        } catch (SAXException e) {
            throw new IOException(e);
        }
    }

    /**
     * Read the sheets information from excel document
     * @throws IOException if an IOException is raised reading the file
     */
    private void loadWorkbook() throws IOException {
        try {
            XMLReader myReader = XMLReaderFactory.createXMLReader();
            myReader.setContentHandler(new OOXmlWorkbook.SheetParser());
            myReader.parse(new InputSource(zipFile.getInputStream(zipFile.getEntry(WorkbookPath))));
        } catch (SAXException e) {
            throw new IOException(e);
        }
    }

    // start service class
    
    /**
     * Service class that read the SST (Shared String Table) from the document
     * a SAX parser is used
     */
    private class SharedStringParser extends DefaultHandler {

        StringBuffer value;
        String t;
        int i;

        public SharedStringParser() {
            value = new StringBuffer();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("sst")) {
                sharedStringCount = Integer.parseInt(attributes.getValue("uniqueCount"));
                sharedStrings = new String[sharedStringCount];
                i = 0;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            switch (qName) {
                case "si":
                    //list.add(value.toString());
                    sharedStrings[i] = value.toString();
                    i++;
                    value = new StringBuffer();
                    break;
                case "t":
                    value.append(t);
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            t = new String(ch, start, length);
        }
    }

    /**
     * Service class that read the sheets from the document
     * a SAX parser is used
     */
    private class SheetParser extends DefaultHandler {

        private ArrayList<String> list;
        int index;
        String name;

        public SheetParser() {
            list = new ArrayList<>();
            list.add("<EMPTY>");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("sheet")) {
                index = Integer.parseInt(attributes.getValue("r:id").substring(3));
                name = attributes.getValue("name");
                list.add(index, name);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("workbook")) {
                sheetNames = list.toArray(new String[1]);
            }
        }
    }
    // end service class
}
