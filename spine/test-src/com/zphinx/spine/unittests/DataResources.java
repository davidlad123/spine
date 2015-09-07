/**
 * DataResources.java
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

package com.zphinx.spine.unittests;

import java.sql.SQLException;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zphinx.spine.Universal;

/**
 * DataResources is used to test database DAOs. Test classes should extend this class if they wish grab a database connection
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class DataResources extends TestCase {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(DataResources.class.getName()); //$NON-NLS-1$  

    /**
     * The database url
     */
    private static final String URL = "jdbc:mysql://192.168.1.5:3306/securesite";

    /**
     * The db test username
     */
    private static final String TESTUSER = "testuser";

    /**
     * The password
     */
    private static final String PASSWORD = "minerva";

    /**
     * The DataSource associated with this object
     */
    protected static DataSource dataSource = null;

    /**
     * The location of the log4j config file
     */
    private static String logFile = "log4j.props";

    /**
     * The watch time to set for the log4j instance
     */
    private static long watchTime = 6000;

    /**
     * The connection associated with this object
     */
    private Connection connection = null;

    /**
     * Public Constructor
     */
    public DataResources(String name) {
        super(name);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        try{

            InitHelper.configureLog4J(logFile, watchTime);
            MysqlDataSource source = (MysqlDataSource) Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource").newInstance();
            source.setDatabaseName("securesite");
            source.setPort(3306);

            source.setPassword(PASSWORD);

            source.setUrl(URL);

            source.setUser(TESTUSER);
            dataSource = source;
            this.connection = (Connection) dataSource.getConnection();
            // (Connection) DriverManager.getConnection(URL, TESTUSER, PASSWORD);

        }
        catch (Exception e){
            log.debug("Exception: " + e.getMessage());
        }
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        if(getConnection() != null){
            try{
                getConnection().close();
            }
            catch (SQLException e){
                log.debug("SQL error closing: " + e.getMessage()); //$NON-NLS-1$
            }
        }
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the dataSource
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource the dataSource to set
     */
    public static void setDataSource(DataSource dataSource) {
        DataResources.dataSource = dataSource;
    }

}
