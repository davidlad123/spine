/**
 * DataPack.java
 * Copyright (C) 2008  Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING WITH ZPHINX SOFTWARE SOLUTIONS 
 * AND/OR OTHER PARTIES WHO PROVIDE THIS SOFTWARE "AS IS" WITHOUT WARRANTY
 * OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
 * IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
 * ALL NECESSARY SERVICING, REPAIR OR CORRECTION.
 *
 * IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
 * WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS
 * THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY
 * GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
 * USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
 * DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD
 * PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),
 * EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGES.
 *
 * For further information, please go to http://spine.zphinx.co.uk/

 * 
 **/

package com.zphinx.spine.data;

/**
 * DataPack contains the properties needed to create an sql prepared statement
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DataPack {

    /**
     * The amount of data to fetch
     */
    private int dataCount = 0;

    /**
     * The names of the columns in the table to fetch
     */
    private String[] columnNames = null;

    /**
     * The data to be fed into the swl
     */
    private Object[] columnData = null;

    /**
     * The name of the table to use
     */
    private String tableName = null;

    /**
     * The where clause in the sql
     */
    private String whereClause = null;

    /**
     * The set clause in the sql
     */
    private String setClause = null;

    /**
     * The type of sql statement to call
     */
    private int sqlType = 1;

    /**
     * Public Constructor
     */
    public DataPack(int i) {
        super();
        this.sqlType = i;
        this.dataCount = i;
    }

    /**
     * Gets the data for the column in a DataBase
     * 
     * @return Returns the columnData.
     */
    public Object[] getColumnData() {
        return columnData;
    }

    /**
     * Sets the data for the column in a DataBase
     * 
     * @param columnData The columnData to set.
     */
    public void setColumnData(Object[] columnData) {
        this.columnData = columnData;
    }

    /**
     * Gets the names of the columns in the database
     * 
     * @return Returns the columnNames.
     */
    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     * Sets the names of the columns in the database
     * 
     * @param columnNames The columnNames to set.
     */
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    /**
     * Gets the DataCount
     * 
     * @return Returns the dataCount.
     */
    public int getDataCount() {
        return dataCount;
    }

    /**
     * Sets the DataCount
     * 
     * @param dataCount The dataCount to set.
     */
    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    /**
     * Gets the table name
     * 
     * @return Returns the tableName.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the table name
     * 
     * @param tableName The tableName to set.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets the where clause
     * 
     * @return Returns the whereClause.
     */
    public String getWhereClause() {
        return whereClause;
    }

    /**
     * Sets the where clause
     * 
     * @param whereClause The whereClause to set.
     */
    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    /**
     * Gets the sql type
     * 
     * @return Returns the sqlType.
     */
    public int getSqlType() {
        return sqlType;
    }

    /**
     * Gets the Set clause of the prepared statement
     * 
     * @return Returns the setClause.
     */
    public String getSetClause() {
        return setClause;
    }

    /**
     * Sets the Set clause of the prepared statement
     * 
     * @param setClause The setClause to set.
     */
    public void setSetClause(String setClause) {
        this.setClause = setClause;
    }
}