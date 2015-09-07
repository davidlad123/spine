/**
 * AbstractHibernateDAO.java
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

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.zphinx.spine.data.DataAbstract;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * AbstractHibernateDAO represents a stub for creating Data Access Objects which use hibernate to load and persist data
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: May 11, 2008 2:50:31 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class AbstractHibernateDAO extends DataAbstract {

    /**
     * An hibernate session associated with this DAO
     */
    private Session session = null;

    /**
     * Public Constructor
     */
    public AbstractHibernateDAO() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAbstract#close()
     */
    public boolean close() {
        boolean b = false;
        if(getSession() != null){
            try{
                session.flush();
                session.close();
                b = true;
            }
            catch (HibernateException e){
                e.printStackTrace();
            }

        }

        return b;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAbstract#fetchData(com.zphinx.spine.vo.dto.DataTransferObject)
     */
    public Object fetchData(DataTransferObject obj) {
        return null;
    }

    /**
     * sets the session
     * 
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;

    }

    /**
     * Gets the session
     * 
     * @return the session
     */
    public Session getSession() {
        return session;
    }

}
