/**
 * ApplicationConfigurator.java
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

package com.zphinx.spine.start;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.plugin.SpinePlugin;
import com.zphinx.spine.start.helpers.DataSourceBuilder;
import com.zphinx.spine.start.helpers.DataSourceServiceLocator;
import com.zphinx.spine.start.helpers.PluginServiceLocator;
import com.zphinx.spine.start.helpers.impl.ConfigReader;
import com.zphinx.spine.start.helpers.impl.ConfigurationHelper;
import com.zphinx.spine.start.helpers.impl.RoleConfigHelper;
import com.zphinx.spine.utils.ActiveGroups;
import com.zphinx.spine.utils.PersistentGroups;
import com.zphinx.spine.vo.ProxyConfig;

/**
 * ApplicationConfigurator is used by this application to run initializers at configuration time.It will use a map usually obtained from the spine-init.xml file
 * to initialize the framework.
 * <p>
 * Client developers can add a plugin via the initialization file, which must have a concrete implementation that can be used to provide application functionality.This plugin is avialable throughout the
 * duration of the application and can by queried by the {@linkplain PluginServiceLocator#getPlugin(String)} or {@linkplain PluginServiceLocator#getPlugins()} method of this object.  
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 18, 2007 10:43:45 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ApplicationConfigurator {

    /**
     * 
     */
    private static final String PLUGIN_NAME = "pluginName";

    /**
     * 
     */
    private static final String DASH = "-";

    /**
     * The identifier for the dataSource node
     */
    private static final String DATA_SOURCE = "dataSource";

    /**
     * The identifier for the plugin node
     */
    private static final String PLUGIN = "plugin";

    /**
     * The name of the proxy config node
     */
    private static final String PROXY_CONFIG = "proxyConfig";

    /**
     * The key used to store the datasource Object
     */
    public static final String CONFIG_DATASOURCE = "config.datasource";

    /**
     * The key used to store the watchTime value
     */
    public static final String WATCH_TIME = "watchTime";

    /**
     * The key used to store the path to the configuration file
     */
    public static final String CONFIG_FILE = "configFile";

    /**
     * The key used to store the path to the role configuration file
     */
    public static final String ROLE_CONFIG_FILE = "roleConfigFile";

    /**
     * The key used to store the path to the logfile
     */
    public static final String LOG_FILE = "logFile";

    /**
     * The default value of the refresh time used by log4J
     */
    private static long watchDefault = 6000;

    /**
     * 
     */
    private static final String XML = ".xml";

    

    /**
     * The static instance of this object
     */
    private static ApplicationConfigurator appCon = null;

    /**
     * Constructor
     */
    private ApplicationConfigurator() {
        super();
       

    }

    /**
     * Configures the application by running all initialization objects
     * 
     * @param map The map containing the configuration parameters, the keys in this map are listed above as constants
     */
    public void configure(final Map map) {

        String logFile = null;
        String configFile = null;
        String roleConfigFile = null;
        long watchTime = 0;

        Map plugsMap = new HashMap();
        List cfList = new ArrayList();
        Iterator it = map.keySet().iterator();
        int x = 0;
        while (it.hasNext()){
            String key = (String) it.next();
            Object value = map.get(key);
            if(value instanceof Map && key.startsWith(DATA_SOURCE)){
                x = key.indexOf(DASH);
                createDataSource((Map) value, key.substring(x + 1, key.length()));
            }

            else if(value instanceof Map && key.startsWith(PLUGIN)){
                x = key.indexOf(DASH);
                plugsMap.put(key.substring(x + 1, key.length()), value);
            }
            else if(key.equalsIgnoreCase(LOG_FILE)){
                logFile = (String) value;
            }
            else if(key.equalsIgnoreCase(CONFIG_FILE)){
                configFile = (String) value;
            }
            else if(key.equalsIgnoreCase(ROLE_CONFIG_FILE)){
                roleConfigFile = (String) value;
            }

            else if(key.equalsIgnoreCase(WATCH_TIME)){
                String watchTimeString = (String) value;
                if(watchTimeString != null){
                    try{
                        watchTime = Long.parseLong(watchTimeString);
                    }
                    catch (final NumberFormatException e){
                        watchTime = watchDefault;
                        System.out.println("Watch time set to default: " + watchDefault);
                    }
                }
            }

        }

        // this is applicable for sitemessages and logFile
        if((logFile != null) && (watchTime > 0)){
            // configure log4j
            configureLog4J(logFile, watchTime);
        }

        // Add dependency injection configuration
        processConfigurationFile(configFile);
        // Add base roles
        processBaseRoles(roleConfigFile);

        // process plugins
        processPlugin(plugsMap);

    }

    /**
     * Processes the configuration file spine.xml
     * 
     * @param configFile The configurationFile path
     */
    private void processConfigurationFile(String configFile) {
        if(configFile != null){
            final ConfigurationHelper ch = new ConfigurationHelper();
            final HashMap configHash = ch.createConfig(configFile);
            if(configHash != null){
                final ProxyConfig proxyConfig = (ProxyConfig) configHash.remove(PROXY_CONFIG);
                final SpineConfiguration spineConfiguration = SpineConfiguration.getInstance(configHash.size());
                spineConfiguration.putAll(configHash);
                spineConfiguration.setProxyConfiguration(proxyConfig);
                ch.resetHelper();
            }
        }
    }

    /**
     * Processes the Role data
     * 
     * @param roleConfigFile
     */
    private void processBaseRoles(String roleConfigFile) {
        if(roleConfigFile != null){
            final RoleConfigHelper rch = new RoleConfigHelper();
            final HashMap roleHash = rch.createConfig(roleConfigFile);
            final ActiveGroups sg = PersistentGroups.getMap();
            final Iterator itm = roleHash.keySet().iterator();
            while (itm.hasNext()){
                final Group group = (Group) roleHash.get(itm.next());
                sg.addGroup(group);
            }
            try{
                Universal.setGroupsList(sg.subsidizedGroupsList());
                PersistentGroups.setMap(sg);

            }
            catch (SpineException e){
                System.err.println("Error in persistentGroups: " + e.getMessage());
            }
        }
    }

    /**
     * Configures the log4J instance within the application
     */
    private boolean configureLog4J(String logFile, long watchTime) {
        boolean b = false;
        if(logFile != null){
            if(logFile.endsWith(XML)){
                if(watchTime > 0){
                    DOMConfigurator.configureAndWatch(logFile, watchTime);
                }
                else{
                    DOMConfigurator.configureAndWatch(logFile);
                }
                b = true;
            }
            else{

                if(watchTime > 0){
                    PropertyConfigurator.configureAndWatch(logFile, watchTime);
                }
                else{
                    PropertyConfigurator.configureAndWatch(logFile);
                }
                b = true;
            }
            if(!b){
                System.err.println("Please specify the correct location of you log4j properties/xml file!!");

            }
        }
        return b;
    }

    /**
     * Processes the Plugins
     * 
     * @param plugsMap A Map containing all the registered plugin
     */
    private void processPlugin(Map plugsMap) {
        Iterator plugsIt = plugsMap.keySet().iterator();
        while (plugsIt.hasNext()){
            String plugKey = (String) plugsIt.next();
            Map plugMap = (Map) plugsMap.get(plugKey);
            try{
                createPlugin(plugMap, plugKey);
            }
            catch (Throwable e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the Plugin and saves it in the pluginMap
     * 
     * @param map The map of nodes retrieved from XML
     * @param key The named identifier of this plugin
     */
    private void createPlugin(Map map, String key) throws Throwable {
        SpinePlugin plugin = null;
        String pluginName = (String) map.get(PLUGIN_NAME);
        try{
            plugin = (SpinePlugin) Class.forName(pluginName).newInstance();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){

            e.printStackTrace();
        }
        catch (ClassNotFoundException e){

            e.printStackTrace();
        }
        if(plugin != null){
            plugin.setName(key);
            plugin.process(map);
            PluginServiceLocator.getInstance().addPlugin(key, plugin);
        }
    }

    /**
     * Creates a dataSource using the specified values.The map of dataSources is saved in the DataSourceServiceLocator whose default dataSOurce will be added to this object if a dataSOurce is not defined for the this configurator
     * 
     * @param map The map of the dataSource to be created
     * @param key The key by which the dataSOurce is known to the system
     * @param value The
     */
    private void createDataSource(final Map map, String key) {
        DataSourceServiceLocator dssl = DataSourceServiceLocator.getInstance();
        DataSourceBuilder dsb = dssl.initiateBuilder(map);
        if(dsb != null){
            dssl.createDataSource(key, map, dsb);
        }

    }

    /**
     * Configures the application and initializes the datasource objects
     * 
     * @param map The map containing the configuration parameters, the keys in this map are listed above as constants
     * @param dataSource the dataSource to set
     */
    public void configure(final Map map, final DataSource dataSource) {
        this.setDataSource(dataSource);
        configure(map);
    }

    /**
     * Configures the application using the xml path at the stated location and initializes the datasource objects
     * 
     * @param fileName The full path to the xml file containing the configuration parameters, the keys in this file are listed above as constants except where the key value is a node list
     * @param dataSource the dataSource to set
     */
    public void configure(final String fileName, final DataSource dataSource) {
        this.setDataSource(dataSource);
        final ConfigReader config = new ConfigReader();
        final Map map = config.createConfig(fileName);
        configure(map);
    }

    /**
     * Gets the static instance of this object
     * 
     * @return the static instance of this object
     */
    public static ApplicationConfigurator getInstance() {
        if(appCon == null){
            appCon = new ApplicationConfigurator();
        }
        return appCon;
    }

    /**
     * Sets the dataSource
     * 
     * @param dataSource the dataSource to set
     */
    public void setDataSource(final DataSource dataSource) {

        DataSourceServiceLocator.getInstance().setDataSource(dataSource);
    }

}
