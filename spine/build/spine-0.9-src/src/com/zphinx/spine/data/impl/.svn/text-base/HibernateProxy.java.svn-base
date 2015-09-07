/**
 * HibernateProxy.java
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

package com.zphinx.spine.data.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.zphinx.spine.Universal;
import com.zphinx.spine.data.AbstractDataProxy;
import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.vo.DAOInput;

/**
 * HibernateProxy represents a proxy class which is used to call Hibernate DataAccess objects.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: May 11, 2008 2:53:12 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class HibernateProxy extends AbstractDataProxy {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(HibernateProxy.class.getName());

    /**
     * Public Constructor
     */
    public HibernateProxy() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.AbstractDataProxy#close(com.zphinx.spine.data.DataAccessObject)
     */
    public void close(DataAccessObject dao) throws SpineApplicationException {
        try{
            if(dao != null) dao.close();
            HibernateSessionFactory.closeSession();
        }
        catch (HibernateException e){
            throw new SpineApplicationException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.AbstractDataProxy#open(java.lang.Object, java.lang.String, com.zphinx.spine.vo.DAOInput)
     */
    protected DataAccessObject open(Object obj, String className, DAOInput daoCons) throws SpineApplicationException {

        AbstractHibernateDAO ahDAO = null;
        Session session = null;
        try{

            if(obj == null) {
                HibernateSessionFactory hsf = HibernateSessionFactory.getInstance();
                session = hsf.currentSession();
            }
            else
                session = (Session) obj;
            if(log.isDebugEnabled()){
                log.debug("The session is: " + session + " the object class is: " + className);
            }
            if(session != null){
                ahDAO = (AbstractHibernateDAO) createDataAccessImpl(className, daoCons);
                ahDAO.setSession(session);
                if(log.isDebugEnabled()){
                    log.debug("Successfully created a DAO: " + ahDAO);
                }
            }
            else{
                if(log.isDebugEnabled()){
                    log.debug("Error form HibernateProxy: The session returned null");
                }

            }

        }
        catch (ClassCastException e){
            if(log.isDebugEnabled()){
                log.debug("Class cast error from HibernateProxy: " + e.getMessage());
            }

        }
        catch (Exception e){
            if(log.isDebugEnabled()){
                log.debug("Error from HibernateProxy: " + e.getMessage());
            }
        }
        return ahDAO;
    }

}
