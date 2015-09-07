/**
 * ProcessorTest.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
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
 *          Created: Oct 18, 2007 12:19:54 PM<br>
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
