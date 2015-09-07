/**
 * LogonDAO.java
 * Copyright (C) 2008  Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING WITH ZPHINX SOFTWARE SOLUTIONS 
 * AND/OR OTHER PARTIES WHO PROVIDE THIS SOFTWARE "AS IS" WITHOUT WARRANTY
 * OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
 * IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
 * ALL NECESSARY SERVICING, REPAIR OR CORRECTION.
 *
 * IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
 * WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS
 * THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY
 * GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
 * USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
 * DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD
 * PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),
 * EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGES.
 *
 * For further information, please go to http://spine.zphinx.co.uk/
 * 
 **/

package com.zphinx.spine.examples.dao;

import java.util.Date;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.data.impl.AbstractFileDAO;
import com.zphinx.spine.members.Member;
import com.zphinx.spine.members.User;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;
import com.zphinx.spine.vo.dto.DataTransportBean;

/**
 * LogonDAO:- An example database dao
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class LogonDAO extends AbstractFileDAO {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(LogonDAO.class.getName());

    /**
     * Public Constructor
     */
    public LogonDAO() {
        super();
        log.debug("Constructing " + LogonDAO.class.getSimpleName() + " instance . .. . ");
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
     
            DataTransportBean dtb = (DataTransportBean) obj;
            String password = (String) dtb.getValue();
            String name = dtb.getName();
            //find user in data store
            Member member = findUser(name,password);
            res = new ResultObject();
            res.setObj(member);
        }
        else{
            log.debug("The input object is: null");
        }
        return res;
    }

    /**
     * Loose implementation, does not read a database,creates a user using the supplied credentials
     * @param name
     * @param password
     * @return
     */
    private Member findUser(String name, String password) {
        
       User user = new User();
       user.setId(Universal.getUniqueId());
       user.setName(name);
       user.getAccountSecrets().setPassword1(password);
       user.getAccountSecrets().setPassword2(password);
       user.setModifiedDate(new Date());
       return user;
    }

    /* (non-Javadoc)
     * @see com.zphinx.spine.data.DataAbstract#close()
     */
    public boolean close() {
      
        return true;
    }

}
