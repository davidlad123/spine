/**
 * TestPaginator.java
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.members.Administrator;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.members.User;
import com.zphinx.spine.security.PermissionLevel;
import com.zphinx.spine.security.SpinePermission;
import com.zphinx.spine.start.ApplicationConfigurator;
import com.zphinx.spine.start.helpers.impl.ConfigReader;
import com.zphinx.spine.unittests.impl.beans.SpineBean1;
import com.zphinx.spine.utils.ActiveGroups;
import com.zphinx.spine.utils.PersistentGroups;
import com.zphinx.spine.vo.dto.SpineBean;
import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * TestSecurity serves as a test for the spines integrated security objects
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class TestSecurity extends TestCase {

    /**
     * 
     */
    private static Logger logger = Universal.getLogger(TestSecurity.class.getName());

    // create 2 administrators

    private Administrator accessAdmin = null;

    private Administrator noAccessAdmin = null;

    // create 2 test Groups
    private Group accessGroup = null;

    private Group noAccessGroup = null;

    // create 3 users

    private User testUser1 = null;

    private User testUser2 = null;

    private User testUser3 = null;

    private User testUser4 = null;

    // create 3 SpineBeans

    private SpineBean testBean1 = null;

    private SpineBean testBean2 = null;

    private SpineBean testBean3 = null;

    private SpineBean testBean4 = null;

    /**
     * Public Constructor
     * 
     * @param arg0
     */
    public TestSecurity(String arg0) {
        super(arg0);

    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        configSetup();
        ArrayList aList = new ArrayList();
        ActiveGroups ag = PersistentGroups.getMap();
        Iterator i = ag.keySet().iterator();

        while (i.hasNext()){
            Object key = i.next();
            aList.add(ag.get(key));
        }

        Group[] group1 = new Group[] { (Group) aList.get(0), (Group) aList.get(1) };
        Group[] group2 = new Group[] { (Group) aList.get(2), (Group) aList.get(3) };
        Group[] group3 = new Group[] { (Group) aList.get(6) };
        Group[] group5 = new Group[] { (Group) aList.get(5) };
        Group[] group4 = new Group[] { (Group) aList.get(1), (Group) aList.get(2) };

        accessAdmin = CreateAdmin(group1);
        noAccessAdmin = CreateAdmin(group5);

        accessGroup = (Group) aList.get(1);
        noAccessGroup = (Group) aList.get(4);
        // create 3 users

        testUser1 = CreateUser(group1);
        testUser2 = CreateUser(group2);
        testUser3 = CreateUser(group4);
        testUser4 = CreateUser(group5);

        // create 3 SpineBeans

        testBean1 = CreateBean(testUser1, new int[] { 7, 5, 5 });

        testBean2 = CreateBean(testUser1, new int[] { 7, 0, 0 });

        testBean3 = CreateBean(testUser1, new int[] { 7, 7, 5 });

        testBean4 = CreateBean(testUser1, new int[] { 7, 7, 7 });
    }

    /**
     * @param user
     * @param octal
     * @return A new SpineBean
     */
    private SpineBean CreateBean(User user, int[] octal) {
        SpineBean aBean = new SpineBean1();
        SpinePermission permission = user.getPermission();
        // employ cloning of permissions
        return setLevels(octal, aBean, permission);
    }

    /**
     * @param octal
     * @param aBean
     * @param permission
     */
    private SpineBean setLevels(int[] octal, SpineBean aBean, SpinePermission permission) {
        PermissionLevel permissionLevel;
        try{
            permissionLevel = new PermissionLevel(octal);
            permission.setPermissionLevel(permissionLevel);
            aBean.setPermission(permission);
        }
        catch (SpineException e){
            e.printStackTrace();
        }
        return aBean;
    }

    /**
     * Creates a new User
     * 
     * @param groups An array of groups
     * @return A new User
     */
    private User CreateUser(Group[] groups) {
        ArrayList groupNames = new ArrayList();
        User user = new User();
        for (int i = 0; i < groups.length; i++){
            groupNames.add(groups[i].getGroupName());
        }
        user.setGroupNames(groupNames);
        return user;
    }

    /**
     * Creates an administrator
     * 
     * @param groups An array of groups
     * @return A newly created administrator
     */
    private Administrator CreateAdmin(Group[] groups) {
        ArrayList groupNames = new ArrayList();
        Administrator ad = new Administrator();
        ad.setUserName(Universal.getRandom(6));
        for (int i = 0; i < groups.length; i++){
            groups[i].setAdministrator(new StringAttributeBean(ad.getUserName(), ad.getId() + ""));
            groupNames.add(groups[i].getGroupName());

        }
        ad.setGroupNames(groupNames);
        return ad;
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method to test that Administrator has access to user
     */
    public void testAdminAccessUser() {
        logger.debug("Running test for method:..................................... testAdminAccessUser()................");
        SpinePermission mp = testUser1.getPermission();
        try{
            mp.checkGuard(accessAdmin.getPermission());
            logger.debug("............................[  The accessAdmin has permission!!! ] .............. ");
            assertTrue(true);

        }
        catch (Exception e){
            logger.debug("............................[  The accessAdmin did not have permission!!! ] .............. ");
            assertTrue(false);
        }

    }

    /**
     * Test method to test that Administrator has no access to user
     */
    public void testAdminNoAccessUser() {
        logger.debug("Running test for method:..................................... testAdminNoAccessUser() ................");
        SpinePermission mp = testUser2.getPermission();
        try{
            mp.checkGuard(noAccessAdmin.getPermission());
            logger.debug("............................[  The noAccessAdmin has permission!!! ] .............. ");
            assertTrue(false);
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessAdmin has no permission!!! ] .............. ");
            assertTrue(true);
        }

    }

    /**
     * Test method to test that Administrator has access to user
     */

    public void testGroupAccessUser() {
        logger.debug("Running test for method:..................................... testGroupAccessUser() ................");
        SpinePermission mp = testUser1.getPermission();
        try{
            mp.checkGuard(accessGroup.getPermission());
            logger.debug("............................[  The accessGroup has permission!!! ] .............. ");
            assertTrue(true);

        }
        catch (Exception e){
            logger.debug("............................[  " + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }

    }

    /**
     * Test method to test that Administrator has no access to user
     */
    public void testGroupNoAccessUser() {
        logger.debug("Running test for method:..................................... testGroupNoAccessUser() ................");
        SpinePermission mp = testUser2.getPermission();
        try{
            mp.checkGuard(noAccessGroup.getPermission());
            logger.debug("............................[  The noAccessGroup has permission!!! ] .............. ");
            assertTrue(false);
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessGroup has no permission: " + e.getMessage() + " ] .............. ");
            assertTrue(true);
        }

    }

    /**
     * Test method to test that Administrator has access to user
     */
    public void testGroupAccessAdmin() {
        logger.debug("Running test for method:..................................... testGroupAccessAdmin() ................");
        SpinePermission mp = accessAdmin.getPermission();
        try{
            mp.checkGuard(accessGroup.getPermission());
            logger.debug("............................[  The accessGroup has permission!!! ] .............. ");
            assertTrue(true);

        }
        catch (Exception e){
            logger.debug("............................[  The accessGroup did not have permission :" + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }
    }

    /**
     * Test method to test that Administrator has no access to user
     */
    public void testUserNoAccessUser() {
        logger.debug("Running test for method:..................................... testUserNoAccessUser() ................");
        SpinePermission mp = testUser2.getPermission();
        try{
            mp.checkGuard(testUser1.getPermission());
            logger.debug("............................[ failure : The noAccessUser has permission...!!! ] .............. ");
            assertTrue(false);
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessUser has no permission : " + e.getMessage() + " ] .............. ");
            assertTrue(true);
        }

    }

    /**
     * Test method to test that Administrator has no access to user
     */
    public void testGroupNoAccessAdmin() {
        logger.debug("Running test for method:..................................... testGroupNoAccessAdmin() ................");
        SpinePermission mp = accessAdmin.getPermission();
        try{
            mp.checkGuard(noAccessGroup.getPermission());
            logger.debug("............................[ Failure:  The noAccessGroup has permission....!!! ] .............. ");
            assertTrue(false);
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessAdmin has no permission :" + e.getMessage() + " ] .............. ");
            assertTrue(true);
        }

    }

    /**
     * Test method to test that Administrator has access to user
     */
    public void testAdminAccessSpineBean() {
        logger.debug("Running test for method:..................................... testAdminAccessSpineBean() ................");

        SpinePermission permission = accessAdmin.getPermission();
        // SpinePermission mp = testUser1.getPermission();
        try{
            logger.debug("............................[checking 755 on bean  ] ....................");
            checkReadWriteExecute(permission, testBean1.getPermission(), "Admin", new boolean[] { true, true, true });

            logger.debug("............................[ checking 775 on bean ] ....................");
            checkReadWriteExecute(permission, testBean3.getPermission(), "Admin", new boolean[] { true, true, true });

            logger.debug("............................[ checking 700 on bean ] ....................");
            checkReadWriteExecute(permission, testBean2.getPermission(), "Admin", new boolean[] { true, true, true });

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessAdmin has no permission :" + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }
    }

    /**
     * Test the read write execute permission using the stated access rights
     * 
     * @param permission
     * @param mp
     * @param s
     * @param rights
     */
    private void checkReadWriteExecute(SpinePermission permission, SpinePermission mp, String s, boolean[] rights) {
        writeResults(s, rights[0], mp.getReadFlag(permission), "read");
        writeResults(s, rights[1], mp.getWriteFlag(permission), "write");
        writeResults(s, rights[2], mp.getExecuteFlag(permission), "execute");

    }

    /**
     * Writes the result of the test
     * 
     * @param s The string representing the type of principal making the call
     * @param rights The expected access rights of the principal
     * @param b The boolean result of the test.
     */
    private void writeResults(String s, boolean rights, boolean b, String permit) {
        if(rights){
            assertTrue(b);
            logger.debug("............................[  The access" + s + " has " + permit + " permission....!!! ] .............. ");
        }
        else{
            assertFalse(b);
            logger.debug("............................[  The access" + s + " has no " + permit + " permission....!!! ] .............. ");
        }
    }

    /**
     * Test method to test that Administrator has no access to user
     */
    public void testAdminNoAccessSpineBean() {
        logger.debug("Running test for method:..................................... testAdminNoAccessSpineBean() ................");
        SpinePermission permission = noAccessAdmin.getPermission();
        SpinePermission mp = testBean1.getPermission();
        try{

            checkPermission755(permission, mp);

            // ---------------------------------------------------------------------------------------

            checkPermissionLevel700(permission);

            // ----------------------------------------------------------------------------------------------------

            checkPermissionLevel775();

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }

    }

    /**
     * Test method to test that Administrator has access to user
     */
    public void testGroupAccessSpineBean() {
        logger.debug("Running test for method:..................................... testGroupAccessSpineBean() ................");
        SpinePermission permission = accessGroup.getPermission();

        try{
            logger.debug("............................[checking 755 on bean  ] ....................");
            checkReadWriteExecute(permission, testBean1.getPermission(), "Group", new boolean[] { true, true, true });

            logger.debug("............................[ checking 775 on bean ] ....................");
            checkReadWriteExecute(permission, testBean3.getPermission(), "Group", new boolean[] { true, true, true });

            logger.debug("............................[ checking 700 on bean ] ....................");
            checkReadWriteExecute(permission, testBean2.getPermission(), "Group", new boolean[] { true, true, true });

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessGroup has no permission :" + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }
    }

    /**
     * Test method to test that Administrator has no access to user
     */
    public void testGroupNoAccessSpineBean() {
        logger.debug("Running test for method:..................................... testGroupNoAccessSpineBean()................");
        SpinePermission permission = noAccessGroup.getPermission();
        SpinePermission mp = testBean1.getPermission();
        try{

            checkPermission755(permission, mp);

            // ---------------------------------------------------------------------------------------

            checkPermissionLevel700(permission);

            // ----------------------------------------------------------------------------------------------------

            checkPermissionLevel775();

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }

    }

    /**
     * Test method to test that Administrator has access to user
     */
    public void testUserAccessSpineBean() {
        logger.debug("Running test for method:..................................... testUserAccessSpineBean()................");

        SpinePermission permission = testUser3.getPermission();
        try{
            logger.debug("............................[checking 755 on bean using a group member ] ....................");
            checkReadWriteExecute(testUser3.getPermission(), testBean1.getPermission(), "User", new boolean[] { true, false, true });

            logger.debug("............................[ checking 775 on bean using a group member] ....................");
            checkReadWriteExecute(testUser3.getPermission(), testBean3.getPermission(), "User", new boolean[] { true, true, true });

            logger.debug("............................[ checking 700 on bean using a group member] ....................");
            checkReadWriteExecute(testUser3.getPermission(), testBean2.getPermission(), "User", new boolean[] { false, false, false });

            logger.debug("............................[ checking 700 on bean using the member] ....................");
            checkReadWriteExecute(testUser1.getPermission(), testBean2.getPermission(), "User", new boolean[] { true, true, true });

            logger.debug("............................[ checking 700 on bean using a public member] ....................");
            checkReadWriteExecute(testUser2.getPermission(), testBean2.getPermission(), "User", new boolean[] { false, false, false });

            logger.debug("............................[ checking 755 on bean using a public member] ....................");
            checkReadWriteExecute(testUser2.getPermission(), testBean1.getPermission(), "User", new boolean[] { true, false, true });

            logger.debug("............................[ checking 777 on bean using a public member] ....................");
            checkReadWriteExecute(testUser2.getPermission(), testBean4.getPermission(), "User", new boolean[] { true, true, true });

            logger.debug("............................[ checking 775 on bean using a public member] ....................");
            checkReadWriteExecute(testUser2.getPermission(), testBean3.getPermission(), "User", new boolean[] { true, false, true });

            // checkPermissionLevel700(permission);

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }
    }

    /**
     * Test method to test that Administrator has no access to user
     */
    public void testUserNoAccessSpineBean() {
        logger.debug("Running test for method:..................................... testUserNoAccessSpineBean() ................");

        SpinePermission permission = testUser2.getPermission();
        SpinePermission mp = testBean1.getPermission();
        try{

            checkPermission755(permission, mp);

            // ---------------------------------------------------------------------------------------

            checkPermissionLevel700(permission);

            // ----------------------------------------------------------------------------------------------------

            checkPermissionLevel775();

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
            assertTrue(false);
        }

    }

    /**
     * @param permission
     * @param mp
     */
    private void checkPermission755(SpinePermission permission, SpinePermission mp) {
        logger.debug("............................[checking 755 on bean  ] ....................");

        boolean b = mp.getExecuteFlag(permission);
        assertTrue(b);
        if(b){
            logger.debug("............................[  The noAccessUser has execute permission....!!! ] .............. ");
        }
        b = mp.getReadFlag(permission);
        assertTrue(b);
        if(b){
            logger.debug("............................[  The noAccessUser has read permission....!!! ] .............. ");
        }

        b = mp.getWriteFlag(permission);
        assertFalse(b);
        if(!b){
            logger.debug("............................[  The noAccessUser has no write permission....!!! ] .............. ");
        }
    }

    /**
     * @param permission
     */
    private void checkPermissionLevel700(SpinePermission permission) {

        boolean b;
        logger.debug("............................[ checking 700 on bean ] ....................");
        SpinePermission mp = testBean2.getPermission();
        b = mp.getExecuteFlag(permission);
        assertFalse(b);
        if(!b){
            logger.debug("............................[  The noAccessUser has no execute permission....!!! ] .............. ");
        }

        b = mp.getReadFlag(permission);
        assertFalse(b);
        if(!b){
            logger.debug("............................[  The noAccessUser has no read permission....!!! ] .............. ");
        }

        b = mp.getWriteFlag(permission);
        assertFalse(b);
        if(!b){
            logger.debug("............................[  The noAccessUser has no write permission....!!! ] .............. ");
        }
    }

    /**
     * @param permission
     */
    private void checkPermissionLevel775() {

        boolean b;
        SpinePermission mp = testBean3.getPermission();
        SpinePermission permission = testUser4.getPermission();

        logger.debug("............................[ checking 775 on bean with user who has only public access ] ....................");
        b = mp.getExecuteFlag(permission);
        assertTrue(b);
        if(b){
            logger.debug("............................[  The noAccessUser has execute permission....!!! ] .............. ");
        }

        b = mp.getReadFlag(permission);
        assertTrue(b);
        if(b){
            logger.debug("............................[  The noAccessUser has read permission....!!! ] .............. ");
        }

        b = mp.getWriteFlag(permission);
        assertFalse(b);
        if(!b){
            logger.debug("............................[  The noAccessUser has no write permission....!!! ] .............. ");
        }

    }

    /**
     * Sets up thr configuration to use
     */
    private void configSetup() {

        String filePath = "/home/rogue/workspace/spine/spine-init.xml";
        ConfigReader reader = new ConfigReader();
        Map configMap = reader.createConfig(filePath);
        ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
        // logger.debug("The dataSOurce is: "+getDataSource());
        ap.configure(configMap);

    }

}
