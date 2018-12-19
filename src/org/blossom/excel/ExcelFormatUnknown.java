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
 * Exception is raised when the format of the excel document is unknown.
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class ExcelFormatUnknown extends ExcelException {
    
    /**
     * Construct an ExcelException, the message is "File format unknown" 
    */
    public ExcelFormatUnknown() {
        super("File format unknown.");
    }
    
}
