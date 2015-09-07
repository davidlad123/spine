/**
 * SimpleDelegate.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests.impl.delegates;

import com.zphinx.spine.core.AbstractBusinessDelegate;
import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * SimpleProcessorTestDelegate
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Nov 29, 2007 11:16:09 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SimpleProcessorTestDelegate extends AbstractBusinessDelegate {

    /**
     * Public Constructor
     */
    public SimpleProcessorTestDelegate() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.core.AbstractBusinessDelegate#run()
     */
    @Override
    protected ResultObject run() {
        DataAccessObject dt = this.getDataProxy().getDataAccessObject();
        return (ResultObject) dt.fetchData((DataTransferObject) this.getCommand().getObj());
    }

}
