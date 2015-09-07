/**
 * ExampleFileDAO.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.examples.dao;

import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.data.impl.AbstractFileDAO;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * ExampleFileDAO:- An example file dao
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 14, 2007 10:52:21 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ExampleFileDAO extends AbstractFileDAO {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(ExampleFileDAO.class.getName());

    /**
     * Public Constructor
     */
    public ExampleFileDAO() {
       super();
       log.debug("Constructing "+ExampleFileDAO.class.getSimpleName()+" instance . .. . ");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAbstract#fetchData(com.zphinx.spine.vo.dto.DataTransferObject)
     */
    @Override
    public Object fetchData(DataTransferObject obj) {

        ResultObject res = null;
        if(obj != null){
            log.debug("Running fetchdata . .");
            log.debug("\n\rThe object is: " + this.getClass().getSimpleName());
            if(this.isPropsFile()) {
                Properties props = this.getProperties();
                log.debug(props);
                props.put("New_Property", new Date().toString());
                props.put("DTO_SessionID",obj.getSessionId());
                this.setProperties(props);
            }
            res = new ResultObject();
            res.setObj(obj);
        }
        else{
            log.debug("The object is: null");
        }
        return res;
    }

    @Override
    public boolean close() {
       
        return true;
    }

}
