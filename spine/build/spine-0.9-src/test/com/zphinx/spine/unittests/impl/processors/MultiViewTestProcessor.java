/**
 * MultiViewTestProcessor.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests.impl.processors;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.AbstractBuilder;
import com.zphinx.spine.core.viewprocessors.MultiViewProcessor;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.vo.dto.DataTransferObject;
import com.zphinx.spine.vo.dto.SpineBean;

/**
 * MultiViewTestProcessor
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Jan 2, 2008 1:23:24 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultiViewTestProcessor extends MultiViewProcessor {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(MultiViewTestProcessor.class.getName());

    /**
     * Public Constructor
     */
    public MultiViewTestProcessor() {

    }

    /**
     * Creates a DataTransferObject
     * 
     * @param alias The identifier for the DelegateConfiguration
     * @param delegateName The name identifier of the Delegate sub class
     * @param pageIndex The int which is associated with the SpineBean type to create
     * @return A DataTransferObject
     */
    public DataTransferObject createDTO(String alias, String delegateName, int pageIndex) {
        SpineBean sb = null;
        try{
            AbstractBuilder builder = (AbstractBuilder) this.findBuilder(delegateName);
            sb = builder.createBeanFromIndex(pageIndex);
        }
        catch (SpineException e){
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        return sb;
    }

}
