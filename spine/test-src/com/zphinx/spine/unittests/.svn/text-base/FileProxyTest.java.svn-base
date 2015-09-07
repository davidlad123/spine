/**
 * FileProxyTest.java
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

package com.zphinx.spine.unittests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.zphinx.spine.data.AbstractDataProxy;
import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.data.impl.AbstractFileDAO;
import com.zphinx.spine.data.impl.FileProxy;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.start.ApplicationConfigurator;
import com.zphinx.spine.unittests.impl.dao.DaoTestObject;
import com.zphinx.spine.vo.DaoConstructor;

/**
 * FileProxyTest is used to test the File Proxy
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class FileProxyTest extends TestCase {

    Object obj = null;

    DaoConstructor daos = null;

    String className = null;

    DataAccessObject dao = null;

    FileProxy filePro = null;

    /**
     * @param args
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(FileProxyTest.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        // daos = (DaoConstructor) new RoleConstructor("Groups");

        Map configMap = new HashMap();
        configMap.put("logFile", "/home/rogue/workspace/Securesite/login-manager/WEB-INF/log4j.props");
        configMap.put("configFile", "/home/rogue/workspace/spine/spine.xml");

        configMap.put("watchTime", "6000");
        configMap.put("siteMessages", "com.zphinx.spine.resources.ConfigurationResources,com.zphinx.spine.resources.ResourceProperties,com.zphinx.spine.resources.UtilMessages");
        configMap.put("defaultMessageClass", "com.zphinx.spine.resources.ConfigurationResources");
        ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
        // System.out.println("The dataSOurce is: "+getDataSource());
        ap.configure(configMap);

        String[] obj1 = new String[2];
        obj1[0] = "/home/rogue/workspace/spine/groups.xml"; // pathstring
        obj1[1] = "false";// boolean representation
        className = "com.zphinx.spine.unittests.impl.dao.DaoTestObject";
        obj = (Object) obj1;
        filePro = (FileProxy) AbstractDataProxy.getInstance(1);

    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for FileProxyTest.
     * 
     * @param name
     */
    public FileProxyTest(String name) {
        super(name);

    }

    /**
     * @throws SpineApplicationException
     */
    public final void testInit() throws SpineApplicationException {
        System.out.println("The file proxy is: " + filePro);
        assertEquals("We have an instance.....", true, filePro.init(obj, className, daos));

    }

    /**
     * @throws SpineApplicationException
     * @throws IOException
     */
    public final void testOpen() throws SpineApplicationException, IOException {
        filePro.init(obj, className, daos);
        boolean b = false;
        dao = filePro.getDataAccessObject();
        AbstractFileDAO fi = (AbstractFileDAO) dao;
        if(fi.getFileBytes().length > 5){
            b = true;
            ByteArrayOutputStream fd = new ByteArrayOutputStream(fi.getFileBytes().length);
            fd.write(fi.getFileBytes());
            System.out.println(fd.toString());
        }
        assertEquals("We have opened a connection!!", true, b);
        filePro.close(dao);
    }

    /**
     * @throws SpineApplicationException
     */
    public final void testClose() throws SpineApplicationException {
        filePro.init(obj, className, daos);
        boolean b = false;
        dao = filePro.getDataAccessObject();
        filePro.close(dao);
        if(dao instanceof DaoTestObject) b = true;
        assertEquals("Have we closed a connection ?", true, b);
    }

    /**
     * @throws Exception
     */
    public final void testCreateDataAccessImpl() throws Exception {
        boolean b = filePro.init(obj, className, daos);
        if(b){
            String s = filePro.getDataAccessObject().getClass().getName();
            String s1 = ((AbstractFileDAO) new DaoTestObject(daos)).getClass().getName();
            assertEquals("We have created an object!!", s, s1);
        }
        else
            fail("Unable to run the init method!!");
    }

}
