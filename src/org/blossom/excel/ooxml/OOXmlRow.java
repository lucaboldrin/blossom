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


import java.util.ArrayList;
import org.blossom.excel.ExcelException;
import org.blossom.excel.Row;
import org.blossom.excel.Workbook;


/**
 *
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class OOXmlRow implements Row {

    private final ArrayList<OOXmlCell> cells;
    private final Workbook wb;
    
    protected OOXmlRow(OOXmlWorkbook wb) {
        this.wb = wb;
        cells = new ArrayList<>();
    }
    
    @Override
    public OOXmlCell getCell(int Column) throws ExcelException {
        if ((Column < 1) || (Column > cells.size()))
            throw new ExcelException("Column index must be between 1 and " + cells.size());
        return cells.get(Column);
    }
    
    @Override
    public OOXmlCell getCell(String Column) {
        for(OOXmlCell c : cells)
            if (c.getColumn().equals(Column))
                return c;
        return null;
    }
    
    
    @Override
    public int getCellCount() {
        return cells.size();
    }
    
    public void add(OOXmlCell cell) {
        this.cells.add(cell);
    }
    
}
