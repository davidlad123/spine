/**
 * MultiViewProcessorExample.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.examples.multiview;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.viewprocessors.ViewProcessorFactory;
import com.zphinx.spine.examples.ExamplePrimer;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * MultiViewProcessorExample is an example which demonstrates the use of a MultiViewProcessor
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Feb 24, 2008 3:18:35 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultiViewProcessorExample extends ExamplePrimer {

	/**
     * 
     */
    private static final String MULTI_VIEW_PROCESSOR = "MultiViewProcessor";
    private final static String DELEGATE_NAME = "com.zphinx.spine.examples.multiview.MultiViewExampleDelegate";
    private final static String PATH = "groups.xml";
    
      /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger logger = Universal.getLogger(ExtendedMultiViewProcessor.class.getName());

    
    /**
     * Public Constructor
     * @throws Exception 
     */
    public MultiViewProcessorExample() throws Exception {
       super();
    }

	@Override
	public void run() {
        ExtendedMultiViewProcessor pro = (ExtendedMultiViewProcessor) ViewProcessorFactory.getInstance().createProcessor(MULTI_VIEW_PROCESSOR);
		ResultObject res = invokeProcessDB(pro);
        ResultObject res1 = invokeProcessDB1(pro);
        ResultObject res2 = invokeProcessDB2(pro);
        logger.debug(res2);
        
	}
	

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB(ExtendedMultiViewProcessor pro) {
        
        DataTransferObject dto = pro.createDTO( DELEGATE_NAME, 1);
        
        return pro.processData(dto, new String[] { PATH, "true" }, null, 1, 1);
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB1(ExtendedMultiViewProcessor pro) {
        
        DataTransferObject dto = pro.createDTO( DELEGATE_NAME, 2);
        return pro.processData(dto, getDataSource(), null, 2, 2);
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB2(ExtendedMultiViewProcessor pro) {
        
        DataTransferObject dto = pro.createDTO( DELEGATE_NAME, 3);
        return pro.processData(dto, getDataSource(), null, 3, 3);
    }

	
	
	
	
	 /**
     * @param args
     */

    public static void main(String[] args) {
    	
        try{
            MultiViewProcessorExample sde = new MultiViewProcessorExample();
            sde.run();
        }
        catch (Exception e){
            logger.debug("Error: "+e.getMessage());
        }
    }
}
