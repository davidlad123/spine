/**
 * ApplicationConfigurationTest.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.examples.Messages;
import com.zphinx.spine.message.MessageConfig;
import com.zphinx.spine.start.ApplicationConfigurator;
import com.zphinx.spine.start.DelegateConfiguration;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.utils.PersistentGroups;
import com.zphinx.spine.vo.ProxyConfig;

/**
 * ApplicationConfigurationTest tests the configuration parameters passed to the Spine framework.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Nov 29, 2007 2:14:11 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ApplicationConfigurationTest extends DataResources {
    /**
     * 
     */
    private static Logger logger = Universal.getLogger(ApplicationConfigurationTest.class.getName());


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
     * Public Constructor
     */
    public ApplicationConfigurationTest(String name) {
        super(name);
    }

    /**
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();
        Map<String, String> configMap = new HashMap<String, String>();
        File file = new File(Messages.getString("test.spineTestConfigFile")); //$NON-NLS-1$
        String logFile = Messages.getString("test.log4jFile"); //$NON-NLS-1$
     
        String roleConfigFile = Messages.getString("test.groupsXmlFile"); //$NON-NLS-1$
        String searchConfigFile = Messages.getString("test.searchXmlFile"); //$NON-NLS-1$
        String watchTime = Messages.getString("test.watchTimeConstant"); //$NON-NLS-1$
        String siteMessages = Messages.getString("test.siteMessageValue"); //$NON-NLS-1$
        String messagePropertyClass = Messages.getString("test.resourceConfig"); 
        
        configMap.put(LOG_FILE, logFile);
        configMap.put(CONFIG_FILE, file.getPath());
        configMap.put(ROLE_CONFIG_FILE, roleConfigFile);
        configMap.put(SEARCH_CONFIG_FILE, searchConfigFile);
        configMap.put(WATCH_TIME, watchTime);
        configMap.put(SITE_MESSAGES, siteMessages);
        configMap.put(MESSAGE_PROPERTY_CLASS, messagePropertyClass);
        ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
        ap.configure(configMap,getDataSource());

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(ApplicationConfigurator.class);
    }

    /**
     * Tests configuration Loaded
     * 
     * @throws Exception
     */
    public void testAppConfigLoaded() throws Exception {
        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        DelegateConfiguration dc = sc.get("TestMultiViewProcessor");
        logger.debug("The delegate config is: " + dc);
        logger.debug("The delegate is: " + dc.getDataBean()[0]);
    }

    /**
     * @throws Exception
     */
    public void testCommonConfig() throws Exception {

        logger.debug("The config is: " + MessageConfig.getResourcesList());
    }

    /**
     * @throws Exception
     */
    public void testRoleConfig() throws Exception {

        logger.debug("The role config is: " + PersistentGroups.getMap());
    }

    /**
     * Test the proxy configuration
     * 
     * @throws Exception
     */
    public void testProxyConfigLoaded() throws Exception {

        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        ProxyConfig config = sc.getProxyConfiguration();
        logger.debug("The collection of proxys is: " + config.getProxyList());

    }
}
