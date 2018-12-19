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


import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStream;
import org.blossom.excel.ExcelException;
import org.blossom.excel.Sheet;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class OOXmlSheet implements Sheet {
    
    private final OOXmlWorkbook wb;
    private final int index;
    private final InputSource is;
    private XMLReader reader;
    private DefaultHandler parser;
    private OOXmlRow row;
    
    protected OOXmlSheet(OOXmlWorkbook Wb, int Index, InputStream Source) {
        this.wb = Wb;
        this.index = Index;
        this.is = new InputSource(Source);       
        this.row = null;
    }
    
    public void export(int StartRow, int EndRow, String[] ColumnList, PrintWriter OutputFile, String Delimiter) throws ExcelException {
        this.parser = new OOXmlSheet.ColumnExporter(StartRow,EndRow,ColumnList,OutputFile,Delimiter);
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(parser); 
            reader.parse(is);           
        } catch(SAXException | IOException ex) {
            throw new ExcelException(ex);
        }         
    }
    
    public void export(String StartRow, String EndRow, String ColumnList, PrintWriter OutputFile, String Delimiter) throws ExcelException {
        try {
            export(Integer.parseInt(StartRow), Integer.parseInt(EndRow), ColumnList.split(","),OutputFile,Delimiter);
        } catch(NumberFormatException ex) {
            throw new ExcelException("Parameter StartRow/EndRow must be integers");
        }
    }
    
    public OOXmlRow getRow(int RowIndex) throws ExcelException {
        this.row = new OOXmlRow(wb);
        this.parser = new OOXmlSheet.RowParser(RowIndex);
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(parser); 
            reader.parse(is);
        } catch(SAXException | IOException ex) {
            throw new ExcelException(ex);
        }
        return row;
    }
        
    private class RowParser extends DefaultHandler {
        
        private int rowIndex;
        private boolean rowFound;
        String value;
        String rif;
        String type;
        
        public RowParser(int RowIndex) {
            this.rowIndex = RowIndex;
            type = "";
        }
        
        public void setRowIndex(int RowIndex) {
            this.rowIndex = RowIndex;
        }
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("row"))
                    rowFound = Integer.parseInt(attributes.getValue("r"))==rowIndex;
            if (rowFound && qName.equals("c")) {
                rif = attributes.getValue("r");
                type = attributes.getValue("t");
                if (type == null)
                    type = "";
            }
        }
        
        @Override
        public void endElement(String uri, String localName, String qName) {
            if (rowFound && qName.equals("c")) {
                if (type.equals("s"))
                    row.add(new OOXmlCell(rif,wb.getStringValue(Integer.valueOf(value))));
                else
                    row.add(new OOXmlCell(rif,value));
            }                
        }
        
        @Override
        public void characters(char[] ch, int start, int length) {
            value = new String(ch, start, length);
        }            

    }
    
    private class ColumnExporter extends DefaultHandler {
        
        private final int startRow;
        private final int endRow;
        private int currentRow;
        private String[] columnList;
        private String currentCol;
        private PrintWriter out;
        private int rowIndex;
        private String delimiter;
        private boolean rowFound;
        private boolean colFound;
        private boolean lastColumn;
        private String colRif;
        String value;
        String rif;
        String type;
        private int columnCount;
        
        public ColumnExporter(int StartRow, int EndRow, String[] ColumnList, PrintWriter Output,String Delimiter) {
            startRow = StartRow;
            endRow = EndRow;
            columnList = ColumnList;
            out = Output;
            delimiter = Delimiter;
            columnCount = columnList.length;
        }
        
        /**
         * @return the startRow
         */
        public int getStartRow() {
            return startRow;
        }

        /**
         * @return the endRow
         */
        public int getEndRow() {
            return endRow;
        }

        /**
         * @return the columnList
         */
        public String[] getColumns() {
            return columnList;
        }

        /**
         * @return the output file
         */
        public PrintWriter getOutputFile() {
            return out;
        }
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("row")) {
                    currentRow = Integer.parseInt(attributes.getValue("r"));
                    rowFound = (currentRow >= startRow) && (currentRow <=endRow);
            }
            if (rowFound && qName.equals("c")) {
                rif = attributes.getValue("r");
                currentCol = getColumnByRif(decodeCol(rif));
                colFound = currentCol != null;
                if (colFound) 
                    lastColumn = currentCol.equals(columnList[columnList.length-1]);
                type = attributes.getValue("t");
                if (type == null)
                    type = "";
            }
            value = "";
        }
        
        @Override
        public void endElement(String uri, String localName, String qName) {
            if (rowFound && colFound && qName.equals("c") && (!value.equals(""))) {
                if (type.equals("s"))
                    value = wb.getStringValue(Integer.valueOf(value));
                out.print(value);
                if (lastColumn)
                    out.println();
                else
                    out.print(delimiter);
            }
        }
        
        @Override
        public void characters(char[] ch, int start, int length) {
            value = new String(ch, start, length);
        }
        
        private String decodeCol(String Rif) {
            StringBuilder buf = new StringBuilder();
            int len = Rif.length();
            for (int i=0;i<len;i++) {
                if (Character.getType(Rif.charAt(i)) == Character.UPPERCASE_LETTER)
                    buf.append(Rif.charAt(i));
            }
            return buf.toString();
        }

        private String getColumnByRif(String Rif) {
            for (String col: columnList) {
                if (col.equals(Rif))
                    return col;
            }
            return null;
        }
       
    }
}
