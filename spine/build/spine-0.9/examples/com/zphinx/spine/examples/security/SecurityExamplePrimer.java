/**
* SecurityExamplePrimer.java
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
 
package com.zphinx.spine.examples.security;

import java.util.ArrayList;
import java.util.Iterator;

import com.zphinx.spine.Universal;
import com.zphinx.spine.examples.ExamplePrimer;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.members.Administrator;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.members.User;
import com.zphinx.spine.security.PermissionLevel;
import com.zphinx.spine.security.SpinePermission;
import com.zphinx.spine.unittests.impl.beans.SpineBean1;
import com.zphinx.spine.utils.ActiveGroups;
import com.zphinx.spine.utils.PersistentGroups;
import com.zphinx.spine.vo.dto.SpineBean;
import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * 
 * SecurityExamplePrimer serves as a super class for security examples
 * @author David Ladapo
 * @version  $1.0
 * 
 * <p>Created: Mar 9, 2008 2:34:51 AM<br>
 * Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/
public abstract class SecurityExamplePrimer extends ExamplePrimer {


    // create 2 administrators

    protected Administrator accessAdmin = null;

    protected Administrator noAccessAdmin = null;

    // create 2 test Groups
    protected Group accessGroup = null;

    protected Group noAccessGroup = null;

    // create 3 users

    protected User exampleUser1 = null;

    protected User exampleUser2 = null;

    protected User exampleUser3 = null;

    protected User exampleUser4 = null;

    // create 3 SpineBeans

    protected SpineBean exampleBean1 = null;

    protected SpineBean exampleBean2 = null;

    protected SpineBean exampleBean3 = null;

    protected SpineBean exampleBean4 = null;

    
    /**
     * Public Constructor
     * @throws Exception 
     */
    public SecurityExamplePrimer() throws Exception {
        
        ArrayList aList = new ArrayList();
        ActiveGroups ag = PersistentGroups.getMap();
        Iterator i = ag.keySet().iterator();

        while (i.hasNext()){
            String key = (String) i.next();
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

        exampleUser1 = CreateUser(group1);
        exampleUser2 = CreateUser(group2);
        exampleUser3 = CreateUser(group4);
        exampleUser4 = CreateUser(group5);

        // create 3 SpineBeans

        exampleBean1 = CreateBean(exampleUser1, new int[] { 7, 5, 5 });

        exampleBean2 = CreateBean(exampleUser1, new int[] { 7, 0, 0 });

        exampleBean3 = CreateBean(exampleUser1, new int[] { 7, 7, 5 });

        exampleBean4 = CreateBean(exampleUser1, new int[] { 7, 7, 7 });

    }

    
    
    
    /**
     * Creates a spinebean and associates it with a user
     * @param user
     * @param octal
     * @return A new SpineBean
     */
    protected SpineBean CreateBean(User user, int[] octal) {
        SpineBean aBean = new SpineBean1();
        SpinePermission permission = user.getPermission();
        // employ cloning of permissions
        return setLevels(octal, aBean, permission);
    }

    /**
     * Sets permission level
     * @param octal
     * @param aBean
     * @param permission
     */
    protected SpineBean setLevels(int[] octal, SpineBean aBean, SpinePermission permission) {
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
    protected User CreateUser(Group[] groups) {
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
    protected Administrator CreateAdmin(Group[] groups) {
        ArrayList groupNames = new ArrayList();
        Administrator ad = new Administrator();
        ad.setUserName(Universal.getRandom(6));
        for (int i = 0; i < groups.length; i++){
            groups[i].setAdministrator(new StringAttributeBean(ad.getUserName(), ad.getId()+""));
            groupNames.add(groups[i].getGroupName());

        }
        ad.setGroupNames(groupNames);
        return ad;
    }


}
