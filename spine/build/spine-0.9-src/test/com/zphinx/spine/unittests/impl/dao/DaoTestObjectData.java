/**
 * DaoTestObjectData.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests.impl.dao;

import com.zphinx.spine.data.impl.AbstractDataBaseDAO;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * DaoTestObjectData is a test dao useful for proving access to data bases
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 5, 2007 2:25:40 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DaoTestObjectData extends AbstractDataBaseDAO {

    /**
     * Public Constructor
     */
    public DaoTestObjectData() {
        System.out.println("Initailising DataAccessObject with database datastore . .. ");
    }

    @Override
    public Object fetchData(DataTransferObject obj) {
        ResultObject res = null;
        if(obj != null){
            System.out.println("Running fetchdata . .");
            System.out.println("\n\rThe object is: " + obj);
            System.out.println("\n\rThe connection is: " + this.getConnection() + "\n\r");
            res = new ResultObject();
            res.setObj(obj);
        }
        else{
            System.out.println("The object is: null");
        }
        return res;
    }

}
