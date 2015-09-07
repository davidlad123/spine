/**
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
 */
package com.zphinx.spine.examples;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.zphinx.spine.Universal;
import com.zphinx.spine.start.ApplicationConfigurator;
import com.zphinx.spine.start.helpers.DataSourceServiceLocator;
import com.zphinx.spine.start.helpers.impl.ConfigReader;

/**
 * Serves as a base class for all examples, preloads all the example configurations
 * 
 * @author David Ladapo
 * @version $Revision: 1.9 $ $Date: 2008/06/15 01:47:09 $
 *          
 *          <p>
 *          copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class ExamplePrimer {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger logger = Universal.getLogger(ExamplePrimer.class.getName());

    /**
     * 
     */
    private static final String ROLE_CONFIG_FILE = "roleConfigFile"; //$NON-NLS-1$

    /**
     * 
     */
    private static final String CONFIG_FILE = "configFile"; //$NON-NLS-1$

    /**
     * 
     */
    private static final String LOG_FILE = "logFile"; //$NON-NLS-1$

  
    /**
     * The DataSource associated with this object
     */
    protected static DataSource dataSource = null;

    /**
     * The connection associated with this object
     */
    private Connection connection = null;

    /**
     * @throws Exception
     */
    public ExamplePrimer() throws Exception {
        load();
    }

    /**
     * Run this example
     */

    protected abstract void run();

    /**
     * Initialize our variables
     * 
     * @throws Exception
     */
    public void load() throws Exception {
        Map<String, String> configMap = null;

     
        File file = new File("./");
        String abs = file.getCanonicalPath()+File.separator; 
        String fileName = abs + Messages.getString("ExamplePrimer.spineConfigFile");
        ConfigReader confReader = new ConfigReader();
        configMap = confReader.createConfig(fileName);
        configMap.put(LOG_FILE,abs+configMap.get(LOG_FILE));
        configMap.put(CONFIG_FILE,abs+configMap.get(CONFIG_FILE));
        configMap.put(ROLE_CONFIG_FILE,abs+configMap.get(ROLE_CONFIG_FILE));
        
        ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
        ap.configure(configMap);
        initDB();

    }

    /**
     * Initializes the database connection
     */
    protected void initDB() throws Exception {
        try{

            
            dataSource = DataSourceServiceLocator.getInstance().getDataSource();
            this.connection = (Connection) dataSource.getConnection();

        }
        catch (Exception e){
            logger.debug("Exception: " + e.getMessage()); //$NON-NLS-1$
        }
    }

    /*
     * Closes the database connection
     */
    protected void close() throws Exception {

        if(getConnection() != null){
            try{
                getConnection().close();
            }
            catch (SQLException e){
                logger.debug("SQL error closing: " + e.getMessage()); //$NON-NLS-1$
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
        ExamplePrimer.dataSource = dataSource;
    }

}
