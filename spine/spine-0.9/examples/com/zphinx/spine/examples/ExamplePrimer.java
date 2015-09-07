/**
 * 
 */
package com.zphinx.spine.examples;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zphinx.spine.Universal;
import com.zphinx.spine.start.ApplicationConfigurator;

/**
 * Serves as a base class for all examples, preloads all the example configurations
 * 
 * @author David Ladapo
 */
public abstract class ExamplePrimer {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger logger = Universal.getLogger(ExamplePrimer.class.getName());

    
    /**
     * 
     */
    private static final String MESSAGE_PROPERTY_CLASS = "messagePropertyClass"; 

    /**
     * 
     */
    private static final String SITE_MESSAGES = "siteMessages"; //$NON-NLS-1$

    /**
     * 
     */
    private static final String WATCH_TIME = "watchTime"; //$NON-NLS-1$

    /**
     * 
     */
    private static final String SEARCH_CONFIG_FILE = "searchConfigFile"; //$NON-NLS-1$

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
     * The database url
     */
    private static final String URL = Messages.getString("ExamplePrimer.dburl"); //$NON-NLS-1$

    /**
     * The db test username
     */
    private static final String TESTUSER = Messages.getString("ExamplePrimer.dbUserName"); //$NON-NLS-1$

    /**
     * The password
     */
    private static final String PASSWORD = Messages.getString("ExamplePrimer.dbPassword"); //$NON-NLS-1$

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
        Map<String, String> configMap = new HashMap<String, String>();
        File file = new File(Messages.getString("ExamplePrimer.spineConfigFile")); //$NON-NLS-1$
        String logFile = Messages.getString("ExamplePrimer.log4jFile"); //$NON-NLS-1$
     
        String roleConfigFile = Messages.getString("ExamplePrimer.groupsXmlFile"); //$NON-NLS-1$
        String searchConfigFile = Messages.getString("ExamplePrimer.searchXmlFile"); //$NON-NLS-1$
        String watchTime = Messages.getString("ExamplePrimer.watchTimeConstant"); //$NON-NLS-1$
        String siteMessages = Messages.getString("ExamplePrimer.siteMessageValue"); //$NON-NLS-1$
        String messagePropertyClass = Messages.getString("ExamplePrimer.resourceConfig"); //$NON-NLS-1$
        //initialise database params
        initDB();
        configMap.put(LOG_FILE, logFile);
        configMap.put(CONFIG_FILE, file.getPath());
        configMap.put(ROLE_CONFIG_FILE, roleConfigFile);
        configMap.put(SEARCH_CONFIG_FILE, searchConfigFile);
        configMap.put(WATCH_TIME, watchTime);
        configMap.put(SITE_MESSAGES, siteMessages);
        configMap.put(MESSAGE_PROPERTY_CLASS, messagePropertyClass);
        

        ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
        ap.configure(configMap, getDataSource());

    }

    /**
     * Initializes the database connection
     */
    protected void initDB() throws Exception {
        try{

            MysqlDataSource source = (MysqlDataSource) Class.forName(Messages.getString("ExamplePrimer.mysqlClass")).newInstance(); //$NON-NLS-1$
            source.setDatabaseName(Messages.getString("ExamplePrimer.dbName")); //$NON-NLS-1$
            source.setPort(3306);

            source.setPassword(PASSWORD);

            source.setUrl(URL);

            source.setUser(TESTUSER);
            dataSource = source;
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
