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
 * ConfigurationTest tests the round robin action of invoking a process within spine
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Oct 18, 2007 12:19:54 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigurationTest extends ApplicationConfigurationTest {

    /**
     * 
     */
    private static Logger logger = Universal.getLogger(ConfigurationTest.class.getName());

    /**
     * 
     */
    DelegateConfiguration dbp = null;

    /**
     * Public Constructor
     * 
     * @param name
     */
    public ConfigurationTest(String name) {
        super(name);

    }

    /**
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();
        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        dbp = sc.get("TestMultiDelegateProcessor");

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(ConfigurationTest.class);

    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorAvailable() throws Exception {
        String processorClass = dbp.getProcessorClass();
        logger.debug("\n\rThe processor is : " + processorClass + "\n\r");
        assertEquals("com.zphinx.spine.core.viewprocessors.DefaultViewProcessor", processorClass);

    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorInitialization() throws Exception {
        DefaultViewProcessor pro = initializeProcessor("TestMultiDelegateProcessor");
        assertNotNull(pro);

    }

    /**
     * Gets an instance of the Processor to test
     * 
     * @return
     */
    private DefaultViewProcessor initializeProcessor(String s){
        
        DefaultViewProcessor pro = (DefaultViewProcessor) ViewProcessorFactory.getInstance().createProcessor(s);
        // DefaultViewProcessor pro = (DefaultViewProcessor) Class.forName(processorClass).newInstance();
        logger.debug("The name of the object created is: " + pro + "\n\r");
        return pro;
    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorInvocationData() throws Exception {
        DefaultViewProcessor pro = initializeProcessor("TestMultiDelegateProcessor");
        ResultObject res = invokeProcessDB(pro);
        ResultObject res1 = invokeProcessDB1(pro);
        logger.debug("Retrieved : " + res1);
        testProcessorNavigation(res1, pro);
        assertNotNull(res);
        assertNotNull(res.getObj());
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB(DefaultViewProcessor pro) {
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
    private ResultObject invokeProcessDB1(DefaultViewProcessor pro) {
        DataTransportBean dtb = new DataTransportBean("adtb2", new Object());
        dtb.setId("wtwyeuiqk");
        return pro.processData(dtb, getDataSource());
    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorNavigation(ResultObject res, DefaultViewProcessor pro) throws Exception {
        int i = pro.processNavigation(res);
        logger.debug("\n\rThe int returned is: " + i + "\n\r");
        assertEquals(1, i);

    }
}
