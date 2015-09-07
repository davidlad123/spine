/**
 * MultiDelegateTestDelegate.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests.impl.delegates;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.AbstractBusinessDelegate;
import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * MultiDelegateTestDelegate
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 16, 2007 12:54:02 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultiDelegateTestDelegate extends AbstractBusinessDelegate {

    /**
     * The log instance for this object
     */
    private static final transient Logger log = Universal.getLogger("com.zphinx.spine.unittests.impl.delegates.MultiDelegateTestDelegate");

    /**
     * Public Constructor
     */
    public MultiDelegateTestDelegate() {
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
            res = (ResultObject) this.processDAO();
            log.debug("Obtained an object from dao..." + res);
            log.debug("Running first delegate in collection...");
        }
        catch (SpineException e){
            e.printStackTrace();
        }
        return res;
    }

}
