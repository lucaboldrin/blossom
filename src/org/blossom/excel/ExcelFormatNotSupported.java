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
 * Exception is raised when the format or the excel document in not supported
 * @author Luca Boldrin
 * @version %I%, %G%
 */
public class ExcelFormatNotSupported extends ExcelException {
    
    /**
     * Construct an ExcelException specifying only the file format
     * @param FileFormat the string detail of file format
     */
    public ExcelFormatNotSupported(String FileFormat) {
        super(FileFormat+" format not supported.");
    }
}
