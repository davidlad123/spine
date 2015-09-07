/**
 *PendingJobsDelegate.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.examples.multipledelegates;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.AbstractBusinessDelegate;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.vo.ResultObject;

/**
 * PendingJobsDelegate is an example delegate for multiple delegates
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 16, 2007 12:54:02 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class PendingJobsDelegate extends AbstractBusinessDelegate {

    /**
     * The log instance for this object
     */
    private static final transient Logger log = Universal.getLogger("com.zphinx.spine.examples.multipledelegates.PendingJobsDelegate");

    /**
     * Public Constructor
     */
    public PendingJobsDelegate() {
        log.debug("initialising..." + this.getClass().getSimpleName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.core.AbstractBusinessDelegate#run()
     */
    @Override
    protected ResultObject run() {
        ResultObject res = null;
        try{
            log.debug("Running first delegate in collection...");
            res = (ResultObject) this.processDAO();
          //  log.debug("Obtained an object from dao..." + res);
            
        }
        catch (SpineException e){
            e.printStackTrace();
        }
        return res;
    }

}
