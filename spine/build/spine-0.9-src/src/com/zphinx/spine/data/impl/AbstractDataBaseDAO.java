/**
 * AbstractDataBaseDAO.java
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

package com.zphinx.spine.data.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.data.AbstractDataProxy;
import com.zphinx.spine.data.DataAbstract;
import com.zphinx.spine.data.DataPack;

/**
 * AbstractDataBaseDAO is the abstract implementation of DataAbstract which uses a database for its persistence layer.<br>
 * DataAccessObjects which use a database should extend this class
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 17-Feb-2005 23:02:41<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class AbstractDataBaseDAO extends DataAbstract implements DataBaseHelper {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(AbstractDataBaseDAO.class.getName());

   
    /**
     * An sql Connection Object
     */
    private Connection connection = null;

    /**
     * Public Constructor
     */
    public AbstractDataBaseDAO() {
        super();
        setStoreType(AbstractDataProxy.DATA_DATABASE);

    }


    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.impl.DataBaseHelper#setConnection(java.sql.Connection)
     */
    public void setConnection(Connection myConnection) {
        this.connection = myConnection;

    }

    /**
     * Gets a Connection object
     * @return Returns the connection.
     */
    protected Connection getConnection() {
        return connection;
    }

    /**
     * Creates a DataPack
     * 
     * @param i The size of the dataPack to create
     * @param tableName The name of the table to access
     * @param setClause The setClause within the prepared statement 
     * @return A DataPack for use by a prepared statement
     */
    protected DataPack createDataPack(int i, String tableName, String setClause) {
        String[] aColumn = { "?" };
        DataPack data = new DataPack(i);
        data.setTableName(tableName);
        data.setColumnNames(aColumn);
        data.setSetClause(setClause);
        return data;
    }

    /**
     * Closes the database connection
     */

    public boolean close() {
        if(getConnection() != null){
            try{
                getConnection().close();
            }
            catch (SQLException e){
                log.debug("SQL error closing: " + e.getMessage()); //$NON-NLS-1$
            }
        }
        return true;
    }

}
