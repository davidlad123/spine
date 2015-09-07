/**
 * SecurityExampleDelegate.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.examples.security;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.AbstractBusinessDelegate;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * SecurityExampleDelegate si a business delegate utilised in the security examples
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 14, 2007 11:19:43 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SecurityExampleDelegate extends AbstractBusinessDelegate {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger("com.zphinx.spine.unittests.impl.delegates.MultiViewTestDelegate");

    /**
     * Public Constructor
     */
    public SecurityExampleDelegate() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.core.AbstractBusinessDelegate#run()
     */
    @Override
    protected ResultObject run() {
        int i = getCommand().getOperation();
        DataTransferObject dto = (DataTransferObject) getCommand().getObj();
        log.debug("Running operation: " + i + " with object: " + dto.getClass().getSimpleName());
        ResultObject res = null;
        try{
            res = (ResultObject) this.processDAO();
        }
        catch (SpineException e){
            e.printStackTrace();
        }
        log.debug("");
        return res;
    }
}
