/**
 * PendingJobsDAO.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.examples.dao;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.data.impl.AbstractDataBaseDAO;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * PendingJobsDAO:- An example database dao
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 14, 2007 10:56:24 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class PendingJobsDAO extends AbstractDataBaseDAO {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(PendingJobsDAO.class.getName());

    /**
     * Public Constructor
     */
    public PendingJobsDAO() {
       super();
       log.debug("Constructing "+PendingJobsDAO.class.getSimpleName()+" instance . .. . ");
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
            log.debug("\n\rThe connection is: " + this.getConnection() + "\n\r");
            res = new ResultObject();
            res.setObj(obj);
        }
        else{
            log.debug("The object is: null");
        }
        return res;
    }

}
