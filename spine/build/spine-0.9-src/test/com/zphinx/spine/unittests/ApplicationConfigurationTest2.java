/**
 * ApplicationConfigurationTest.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests;

import com.zphinx.spine.message.MessageConfig;
import com.zphinx.spine.start.ApplicationConfigurator;
import com.zphinx.spine.start.DelegateConfiguration;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.utils.PersistentGroups;
import com.zphinx.spine.vo.ProxyConfig;

/**
 * ApplicationConfigurationTest tests the configuration parameters passed to the
 * Spine framework.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Nov 29, 2007 2:14:11 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ApplicationConfigurationTest2 extends DataResources {
	

	/**
	 * Public Constructor
	 */
	public ApplicationConfigurationTest2(final String name) {
		super(name);
	}

	/**
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		super.setUp();
		

		final ApplicationConfigurator ap = ApplicationConfigurator
				.getInstance();
		ap.configure("spine-init.xml", getDataSource());

	}


	/**
	 * Tests configuration Loaded
	 * 
	 * @throws Exception
	 */
	public void testAppConfigLoaded() throws Exception {
		final SpineConfiguration sc = SpineConfiguration.getInstance(0);
		final DelegateConfiguration dc = sc.get("TestMultiViewProcessor");
		System.out.println("The delegate config is: " + dc);
		System.out.println("The delegate is: " + dc.getDataBean()[0]);
	}

	/**
	 * @throws Exception
	 */
	public void testCommonConfig() throws Exception {

		System.out.println("The config is: " + MessageConfig.getResourcesList());
	}

	/**
	 * @throws Exception
	 */
	public void testRoleConfig() throws Exception {

		System.out.println("The role config is: " + PersistentGroups.getMap());
	}

	/**
	 * Test the proxy configuration
	 * 
	 * @throws Exception
	 */
	public void testProxyConfigLoaded() throws Exception {

		final SpineConfiguration sc = SpineConfiguration.getInstance(0);
		final ProxyConfig config = sc.getProxyConfiguration();
		System.out.println("The collection of proxys is: " + config.getProxyList());

	}
    
    
   
}
