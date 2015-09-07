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
import com.zphinx.spine.core.viewprocessors.ViewProcessorFactory;
import com.zphinx.spine.start.DelegateConfiguration;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.unittests.impl.processors.MultiViewTestProcessor;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * MultiViewConfigurationTest tests the use of a multiple view processor instance used by the spine framework
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Oct 18, 2007 12:19:54 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultiViewConfigurationTest extends ApplicationConfigurationTest {

    /**
     * 
     */
    private static Logger logger = Universal.getLogger(MultiViewConfigurationTest.class.getName());

    /**
     * 
     */
    DelegateConfiguration dbp = null;

    /**
     * Public Constructor
     * 
     * @param name
     */
    public MultiViewConfigurationTest(String name) {
        super(name);

    }

    /**
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();
        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        dbp = sc.get("TestMultiViewProcessor");

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(MultiViewConfigurationTest.class);

    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorAvailable() throws Exception {
        String processorClass = dbp.getProcessorClass();
        logger.debug("\n\rThe processor is : " + processorClass + "\n\r");
        assertEquals("com.zphinx.spine.unittests.impl.processors.MultiViewTestProcessor", processorClass);

    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorInitialization() throws Exception {
        MultiViewTestProcessor pro = initializeProcessor("TestMultiViewProcessor");
        assertNotNull(pro);

    }

    /**
     * Gets an instance of the Processor to test
     * 
     * @return
     */
    private MultiViewTestProcessor initializeProcessor(String s) {
        
        MultiViewTestProcessor pro = (MultiViewTestProcessor) ViewProcessorFactory.getInstance().createProcessor(s);
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
        MultiViewTestProcessor pro = initializeProcessor("TestMultiViewProcessor");
        ResultObject res = invokeProcessDB(pro);
        ResultObject res1 = invokeProcessDB1(pro);
        ResultObject res2 = invokeProcessDB2(pro);
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
    private ResultObject invokeProcessDB(MultiViewTestProcessor pro) {
        String delegateName = "com.zphinx.spine.unittests.impl.delegates.MultiViewTestDelegate";
        DataTransferObject dto = pro.createDTO("TestMultiViewProcessor", delegateName, 1);
        String aPath = "/home/rogue/workspace/spine/src/com/zphinx/spine/resources/ConfigurationResources.properties";
        return pro.processData(dto, new String[] { aPath, "true" }, null, 1, 1);
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB1(MultiViewTestProcessor pro) {
        String delegateName = "com.zphinx.spine.unittests.impl.delegates.MultiViewTestDelegate";
        DataTransferObject dto = pro.createDTO("TestMultiViewProcessor", delegateName, 2);
        return pro.processData(dto, getDataSource(), null, 2, 2);
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB2(MultiViewTestProcessor pro) {
        String delegateName = "com.zphinx.spine.unittests.impl.delegates.MultiViewTestDelegate";
        DataTransferObject dto = pro.createDTO("TestMultiViewProcessor", delegateName, 3);
        return pro.processData(dto, getDataSource(),null, 3, 3);
    }

    /**
     * Tests processor available
     * 
     * @throws Exception
     */
    public void testProcessorNavigation(ResultObject res, MultiViewTestProcessor pro) throws Exception {
        int i = pro.processNavigation(res);
        logger.debug("\n\rThe int returned is: " + i + "\n\r");
        assertEquals(1, i);

    }
}
