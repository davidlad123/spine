/**
 * MultipleDelegatesExample.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.examples.multipledelegates;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.viewprocessors.DefaultViewProcessor;
import com.zphinx.spine.core.viewprocessors.ViewProcessorFactory;
import com.zphinx.spine.examples.ExamplePrimer;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransportBean;

/**
 * MultipleDelegatesExample is an example of using the spine framework in multiple delegates mode
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Feb 24, 2008 3:15:38 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultipleDelegatesExample extends ExamplePrimer {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger logger = Universal.getLogger(MultipleDelegatesExample.class.getName());


    /**
     * 
     */
    private static final String MULTI_DELEGATE_PROCESSOR = "MultiDelegateProcessor";
    /**
     * 
     */
    private static final String TESTGROUPS_XML = "groups.xml";
   

   /**
     * Public Constructor
     * @throws Exception
     */
    public MultipleDelegatesExample() throws Exception {
        super();
       
    }

    /**
    * Processes this example
    */
    protected void run() {

        //get a viewProcessor
        DefaultViewProcessor pro = (DefaultViewProcessor) ViewProcessorFactory.getInstance().createProcessor(MULTI_DELEGATE_PROCESSOR);
        
        ResultObject res = invokeProcess1(pro);
        ResultObject res1 = invokeProcess2(pro,(DataTransportBean) res.getObj());
        ResultObject res2 = invokeProcess3(pro,(DataTransportBean) res1.getObj());
        
        logger.debug(res2);   
       }
    
    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcess1(DefaultViewProcessor pro) {
        DataTransportBean dtb = new DataTransportBean("adtb", new Object());
        dtb.setId(Universal.getRandom(6));
        String aPath = TESTGROUPS_XML;
        return pro.processData(dtb, new String[] { aPath, "true" });
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcess2(DefaultViewProcessor pro,DataTransportBean dtb) {
        return pro.processData(dtb, getDataSource());
    }


    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcess3(DefaultViewProcessor pro,DataTransportBean dtb) {
        String aPath = TESTGROUPS_XML;
        return pro.processData(dtb, new String[] { aPath, "true" });
    }

    
    /**
     * Runs this example
     * @param args
     */

    public static void main(String[] args) {
       
        try{
            MultipleDelegatesExample sde = new MultipleDelegatesExample();
            sde.run();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

}
