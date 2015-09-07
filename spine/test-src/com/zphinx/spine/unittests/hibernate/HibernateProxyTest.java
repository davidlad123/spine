/**
 * HibernateProxyTest.java
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

package com.zphinx.spine.unittests.hibernate;

import junit.framework.TestCase;

import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.data.DataProxyFactory;
import com.zphinx.spine.data.impl.AbstractHibernateDAO;
import com.zphinx.spine.data.impl.HibernateProxy;
import com.zphinx.spine.unittests.InitHelper;
import com.zphinx.spine.vo.DAOInput;

/**
 * HibernateProxyTest test the HibernateProxy used by the Spine Framework
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class HibernateProxyTest extends TestCase {

    /**
     * 
     */
    private HibernateProxy hibProx = null;

    /**
     * 
     */

    String className = null;

    /**
     * 
     */

    Object obj = null;

    /**
     * 
     */

    DAOInput daos = null;

    /**
     * 
     */

    DataAccessObject dao = null;

    /**
     * The location of the log4j config file
     */
    private static String logFile = "log4j.props";

    /**
     * The watch time to set for the log4j instance
     */
    private static long watchTime = 6000;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        InitHelper.configureLog4J(logFile, watchTime);
        hibProx = (HibernateProxy) DataProxyFactory.getInstance(6);
        className = "com.zphinx.spine.unittests.impl.dao.HibernateTestDAO";
        // obj = (Object) obj1;

    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.zphinx.spine.data.impl.HibernateProxy#open(java.lang.Object, java.lang.String, com.zphinx.spine.vo.DAOInput)}.
     */
    public final void testOpen() throws Exception {

        boolean b = hibProx.init(obj, className, daos);
        if(b){
            dao = hibProx.getDataAccessObject();
            AbstractHibernateDAO fi = (AbstractHibernateDAO) dao;
            assertNotNull("We have an Hibernate DAO", true);
            hibProx.close(dao);
        }
        else
            fail("Unable to initialise DAO!!");
    }

    /**
     * Test method for {@link com.zphinx.spine.data.impl.HibernateProxy#close(com.zphinx.spine.data.DataAccessObject)}.
     */
    public final void testClose() throws Exception {
        boolean b = hibProx.init(obj, className, daos);
        if(b){
            dao = hibProx.getDataAccessObject();
            hibProx.close(dao);
        }
        else
            fail("Unable to initialise DAO!!");
    }

}
