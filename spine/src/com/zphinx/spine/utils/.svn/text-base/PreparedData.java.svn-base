/**
 * PreparedData.java
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

package com.zphinx.spine.utils;

import com.zphinx.spine.data.DataPack;

/**
 * PreparedData helps create the appropriate prepared statement to use in an sql query using a prepared statement.It contains methods which are useful for creating snippets of preparedStatement sequel
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class PreparedData {

    /**
     * 
     */
    private static final String COMMA_QUESTOR = ",?";

    /**
     * 
     */
    private static final String QUESTOR = "?";

    /**
     * The static instance of this object
     */
    private static PreparedData prepData = null;

    private Object syncInsert = null;

    private Object syncDelete = null;

    private Object syncSelect = null;

    private Object syncSelectDistinct = null;

    private Object syncUpdate = null;

    private Object synchWhereString = null;

    /**
     * private Constructor
     */
    private PreparedData() {
        super();
        syncInsert = new Object();
        syncDelete = new Object();
        syncSelect = new Object();
        syncSelectDistinct = new Object();
        syncUpdate = new Object();
        synchWhereString = new Object();

    }

    /**
     * Get a string to represent the question marks needed
     * 
     * @param dataCount The int which represents the number of question marks to generate
     * @return The concatenated string of question marks
     */
    private String getQuestionMarks(int dataCount) {
        String s = "";
        for (int i = 0; i < dataCount; i++){
            if(i == 0){
                s += QUESTOR;
            }
            else{
                s += COMMA_QUESTOR;
            }
        }
        return s;
    }

    /**
     * Gets the where statement and conditions
     * 
     * @param dataPack The DataPack object which contains the data to render as an sql string
     * @param sql The sql string we are parsing
     * @return The sql string concatenated with the where statement
     */
    protected String getWhereString(DataPack dataPack, String sql) {
        synchronized (synchWhereString){
            if(dataPack.getWhereClause() != null){
                sql += " WHERE " + dataPack.getWhereClause();
            }
            return sql;
        }
    }

    /**
     * Creates a delete prepared statement
     * 
     * @param dp The DataPack object which contains the data to render as an sql string
     * @return The sql string we are parsing
     */
    public String delete(DataPack dp) {
        synchronized (syncDelete){
            String s = "DELETE ";
            if(dp.getSetClause() != null){
                s += dp.getSetClause();
            }
            s += " FROM " + dp.getTableName();
            s = getWhereString(dp, s);

            return s;
        }

    }

    /**
     * Creates an update prepared statement
     * 
     * @param dp The DataPack object which contains the data to render as an sql string
     * @return The sql prepared statement we are parsing
     */
    public String update(DataPack dp) {
        synchronized (syncUpdate){
            String statement = "UPDATE " + dp.getTableName() + " SET " + dp.getSetClause();
            return getWhereString(dp, statement);
        }
    }

    /**
     * Creates a select prepared statement
     * 
     * @param dp The DataPack object which contains the data to render as an sql string
     * @return The sql prepared statement we are parsing
     */
    public String select(DataPack dp) {
        synchronized (syncSelect){
            String sql = "SELECT " + dp.getSetClause() + " FROM " + dp.getTableName();
            sql = getWhereString(dp, sql);
            return sql;
        }
    }

    /**
     * Creates a select distinct prepared statement
     * 
     * @param dp The DataPack object which contains the data to render as an sql string
     * @return The sql prepared statement we are parsing
     */
    public String selectDistinct(DataPack dp) {
        synchronized (syncSelectDistinct){
            String sql = "SELECT DISTINCT " + dp.getSetClause() + " FROM " + dp.getTableName();
            sql = getWhereString(dp, sql);
            return sql + ";";
        }

    }

    /**
     * Creates an insert prepared statement
     * 
     * @param dp The DataPack object which contains the data to render as an sql string
     * @return The sql prepared statement we are parsing
     */
    public synchronized String insert(DataPack dp) {
        synchronized (syncInsert){
            String sql = "INSERT INTO " + dp.getTableName() + "(" + dp.getSetClause() + ")" + " VALUES(" + getQuestionMarks(dp.getDataCount()) + ")";
            return getWhereString(dp, sql);
        }
    }

    /**
     * Creates a set clause for an sql prepared statement
     * 
     * @param setParams The parameters used to create the set clause
     * @param joiner The String to concatenate with
     * @return The concatenation of set sql statements
     */
    public String createSetClause(String[] setParams, String joiner) {
        String s = "";
        if(joiner == null) joiner = ",";
        if(setParams != null){
            for (int i = 0; i < setParams.length; i++){

                if(i > 0){
                    s += " " + joiner + " " + setParams[i] + "=?";
                }
                else{
                    s += setParams[i] + "=?";
                }
            }
        }
        return s;

    }

    /**
     * Formats the insert string using the given array of column names
     * 
     * @param colNames The array of strings representing the database fields
     * @return The formatted insert string
     */
    public String getInsertString(String[] colNames) {
        String s = "";
        for (int i = 0; i < colNames.length; i++){
            if(i > 0){
                s += "," + colNames[i];
            }
            else{
                s += colNames[i];
            }
        }
        return s;
    }

    /**
     * Formats the select string and concatenates the tableName with each column name
     * 
     * @param colNames The array of strings representing the database fields
     * @param concat The name of the table to concatenate to the column name
     * @return The formatted select string
     */
    public String getSelectString(String[] colNames, String concat) {
        String s = "";
        for (int i = 0; i < colNames.length; i++){
            if(i > 0){
                s += "," + concat + "." + colNames[i];
            }
            else{
                s += concat + "." + colNames[i];
            }
        }
        return s;

    }

    /**
     * Formats the update string using the given column names
     * 
     * @param colNames The array of strings representing the database fields
     * @return The formatted update string
     */
    public String getUpdateString(String[] colNames) {
        String s = "";
        for (int i = 0; i < colNames.length; i++){
            if(i > 0){
                s += "," + colNames[i] + "=?";
            }
            else{
                s += colNames[i] + "=?";
            }
        }
        return s;

    }

    /**
     * Creates a where clause for an sql prepared statement
     * 
     * @param whereParams The logical where clause to set
     * @param setParams The equivalence of the where clause
     * @return The concatenation of where sql statements
     */
    public String createWhereClause(String[] whereParams, String[] setParams, String equalType) {
        String s = "";
        if(equalType == null){
            equalType = "=";
        }
        for (int i = 0; i < setParams.length; i++){

            if(i > 0){
                s += "," + whereParams[i] + equalType + setParams[i];
            }
            else{
                s += whereParams[i] + equalType + setParams[i];
            }
        }

        return s;

    }

    /**
     * Creates a where clause for an sql prepared statement
     * 
     * @param whereParams The logical where clause to set
     * @param setParam The equivalence of the where clause
     * @return The concatenation of where sql statements
     */
    public String createWhereClause(String[] whereParams, String setParam, String equalType) {

        String separator = ",";
        String s = createWhereClauseSep(whereParams, setParam, equalType, separator);

        return s;

    }

    /**
     * Creates a where clause for an sql prepared statement
     * 
     * @param whereParams The array of where clauses
     * @param setParam The parameter used to equate the where clause value
     * @param equalType The type of equality sign used to create the where clause
     * @param separator The type of seperator in use
     * @return A String representing the where clause
     */
    public String createWhereClauseSep(String[] whereParams, String setParam, String equalType, String separator) {
        String s = "";
        if(equalType == null){
            equalType = "=";
        }
        for (int i = 0; i < whereParams.length; i++){

            if(i > 0){
                s += separator + " " + whereParams[i] + " " + equalType + " " + setParam;
            }
            else{
                s += whereParams[i] + " " + equalType + " " + setParam;
            }
        }
        return s;
    }

    /**
     * Gets the only instance of this object
     * 
     * @return The static instance of this object
     */
    public static synchronized PreparedData getInstance() {

        if(prepData == null){

            prepData = new PreparedData();
        }
        return prepData;
    }

}