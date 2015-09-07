/**
 * ApplicationConfigurationTest.java
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
import com.zphinx.spine.start.helpers.impl.ConfigReader;
import com.zphinx.spine.utils.PersistentGroups;
import com.zphinx.spine.vo.ProxyConfig;

/**
 * ApplicationConfigurationTest tests the configuration parameters passed to the Spine framework.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
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
    private static final String MESSAGE_PROPERTY_CLASS = "defaultMessageClass";

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
        
        File file = new File("./");
        String abs = file.getCanonicalPath()+File.separator; 
        String fileName = abs + "spine-init.xml";
       ConfigReader reader = new ConfigReader();
        Map<String, String> configMap = reader.createConfig(fileName);
                
        ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
        ap.configure(configMap);

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
