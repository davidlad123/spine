/**
 * ProcessorTest.java
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

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.viewprocessors.DefaultViewProcessor;
import com.zphinx.spine.core.viewprocessors.ViewProcessorFactory;
import com.zphinx.spine.start.DelegateConfiguration;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransportBean;

/**
 * ConfigurationProcessorTest tests the configuration and use of ViewProcessors within spine
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigurationProcessorTest extends ApplicationConfigurationTest {

    /**
     * 
     */
    DelegateConfiguration dc = null;

    /**
     * 
     */
    DelegateConfiguration dbp = null;

    /**
     * 
     */
    private static Logger logger = Universal.getLogger(ConfigResourcesTest.class.getName());

    /**
     * Public Constructor
     * 
     * @param name
     */
    public ConfigurationProcessorTest(String name) {
        super(name);

    }

    /**
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();
        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        dc = sc.get("TestProcessor");
        dbp = sc.get("TestProcessorData");

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(ConfigurationProcessorTest.class);

    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorAvailable() throws Exception {

        String processorClass = dc.getProcessorClass();
        logger.debug("\n\rThe processor is : " + processorClass + "\n\r");
        assertEquals("com.zphinx.spine.core.viewprocessors.DefaultViewProcessor", processorClass);

    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorInitialization() throws Exception {
        DefaultViewProcessor pro = initializeProcessor("TestProcessor");
        assertNotNull(pro);

    }

    /**
     * Gets an instance of the Processor to test
     * 
     * @return
     */
    private DefaultViewProcessor initializeProcessor(String proName) {

        DefaultViewProcessor pro = (DefaultViewProcessor) ViewProcessorFactory.getInstance().createProcessor(proName);
        // DefaultViewProcessor pro = (DefaultViewProcessor) Class.forName(processorClass).newInstance();
        logger.debug("The name of the object created is: " + pro + "\n\r");
        return pro;
    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorInvocation() throws Exception {
        DefaultViewProcessor pro = initializeProcessor("TestProcessor");
        ResultObject res = invokeProcess(pro);
        logger.debug("Retrieved : " + res);
        assertNotNull(res);
        assertNotNull(res.getObj());
    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorInvocationData() throws Exception {
        DefaultViewProcessor pro = initializeProcessor("TestProcessorData");
        ResultObject res = invokeProcessDB(pro);
        logger.debug("Retrieved : " + res);
        assertNotNull(res);
        assertNotNull(res.getObj());
    }

    /**
     * Invokes the process from view to data layer for a file properties object
     * 
     * @return
     */
    private ResultObject invokeProcess(DefaultViewProcessor pro) {
        DataTransportBean dtb = new DataTransportBean("adtb", new Object());
        dtb.setId("sdjjdjdjd");
        String aPath = "/home/rogue/workspace/spine/src/com/zphinx/spine/resources/ConfigurationResources.properties";
        return pro.processData(dtb, new String[] { aPath, "true" });
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB(DefaultViewProcessor pro) {
        DataTransportBean dtb = new DataTransportBean("adtb", new Object());
        dtb.setId("sdjjdjdjd");
        return pro.processData(dtb, getDataSource());
    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorNavigation() throws Exception {
        DefaultViewProcessor pro = initializeProcessor("TestProcessor");
        ResultObject res = invokeProcess(pro);
        int i = pro.processNavigation(res);
        logger.debug("\n\rThe int returned is: " + i + "\n\r");
        assertEquals(1, i);

    }
}
