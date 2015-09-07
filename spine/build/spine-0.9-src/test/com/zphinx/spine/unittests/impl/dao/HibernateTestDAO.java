/**
* HibernateTestDAO.java
* @author David Ladapo (davidl@zphinx.com)
* @version  1.0
* 
* Copyright &copy;Zphinx Software Solutions
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
* APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE ZPHINX SOFTWARE SOLUTIONS 
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
 
package com.zphinx.spine.unittests.impl.dao;

import org.hibernate.Session;

import com.zphinx.spine.data.impl.AbstractHibernateDAO;
import com.zphinx.spine.unittests.impl.beans.Contact;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * 
 * HibernateTestDAO serves as a test implementation of an AbstractHibernateDAO
 * @author David Ladapo
 * @version  $1.0
 * 
 * <p>Created: May 13, 2008 1:09:37 AM<br>
 * Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/
public class HibernateTestDAO extends AbstractHibernateDAO {

    /**
     * Public Constructor
     */
    public HibernateTestDAO() {
        super();
    }

    /* (non-Javadoc)
     * @see com.zphinx.spine.data.impl.AbstractHibernateDAO#close()
     */
    public boolean close() {
        
        return super.close();
    }

    /* (non-Javadoc)
     * @see com.zphinx.spine.data.impl.AbstractHibernateDAO#fetchData(com.zphinx.spine.vo.dto.DataTransferObject)
     */
    public Object fetchData(DataTransferObject obj) {
       Contact contact  = (Contact) obj;
       Session session = null;

       try{
         // This step will read hibernate.cfg.xml and prepare hibernate for use
           System.out.println("Proceeding.....");
           
           System.out.println("openin session..............");
           session = getSession();
           //Create new instance of Contact and set values in it by reading them from form object
            System.out.println("Inserting Record");
           contact.setIdSuffix(3);
           contact.setFirstName("Deepak");
           contact.setLastName("Kumar");
           contact.setEmail("deepak_38@yahoo.com");
           session.save(contact);
           System.out.println("Done");
         
       }
       catch(Exception e){
         System.out.println("Error: "+e.getMessage());
         contact = null;
       }finally{
         // Actual contact insertion will happen at this step
           System.out.println("Running finalizers ..");
         }
 
       return contact;
    }
    

}
