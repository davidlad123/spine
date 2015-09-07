/**
 * DaoTestObject.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests.impl.dao;

import com.zphinx.spine.data.impl.AbstractFileDAO;
import com.zphinx.spine.vo.DAOInput;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * DaoTestObject
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Nov 29, 2007 11:25:54 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DaoTestObject extends AbstractFileDAO {

    /**
     * Public Constructor
     */
    public DaoTestObject() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Public Constructor
     */
    public DaoTestObject(DAOInput daos) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean close() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object fetchData(DataTransferObject obj) {
        ResultObject res = null;
        if(obj != null){
            System.out.println("The object is: " + obj);
            res = new ResultObject();
            res.setObj(obj);
        }
        else{
            System.out.println("The object is: null");
        }
        return res;
    }

}
