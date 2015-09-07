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
 *          
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

        final ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
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
