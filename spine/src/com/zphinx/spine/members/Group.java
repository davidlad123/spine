/**
 *
 * Group.java
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

 **/

package com.zphinx.spine.members;

import java.util.ArrayList;

import com.zphinx.spine.Universal;
import com.zphinx.spine.security.SpinePermission;
import com.zphinx.spine.vo.dto.StringAttributeBean;
import com.zphinx.spine.vo.dto.UserListBean;

/**
 * The Object which represents a named group within the spine framework.
 * 
 * @see Member
 * @see MemberActions
 * @author David Ladapo (davidl@zphinx.com)
 * @version $Revision: 1.17 $ $Date: 2008/06/15 01:47:09 $
 *          <p>
 *          created Sun Oct 5 14:29:10 BST 2003 <br>
 *          copyright &copy; Zphinx Software Solutions
 *          </p>
 */

public class Group extends Member implements MemberActions {

    /**
     * The default name of this group
     */
    private static final String GROUP = "Group";

    /**
     * 
     */
    private static final long serialVersionUID = 4574685338889019539L;

    /**
     * The currently active Administrator
     */
    private StringAttributeBean administrator = null;

    /**
     * A Collection of UserListBeans
     */
    private ArrayList userList = null;

    /**
     * Constructor for group object
     */

    public Group() {
        super();
        setName(GROUP);
        setId(Universal.getUniqueId());
    }

    /**
     * Get the currently active Administrator
     * 
     * @return Administrator An Administrator object expected to be active in this group
     */
    public StringAttributeBean getAdministrator() {
        return this.administrator;

    }

    /**
     * Add an administrator to this group.This administrator is also the active Administrator for this group
     * 
     * @param administrator An Administrator object to add to this group.
     */
    public void setAdministrator(StringAttributeBean administrator) {
        if(administrator != null){
            if(!getAdministrators().contains(administrator)){
                getAdministrators().add(administrator);
            }
        }
        this.administrator = administrator;

    }

    /**
     * Implementation of getPermission in Member,returns the SpinePermission Object associated with this Object. Uses MemberPermissionFactory.getMemberPermission to create the appropriate SpinePermission
     * 
     * @return SpinePermission The SpinePermission object associated with this group
     */

    public SpinePermission getPermission() {
        synchronized (this){
            MemberPermissionFactory pf = new MemberPermissionFactory();
            SpinePermission permission = pf.getMemberPermission();
            ArrayList adminN = getAdministrators();
            permission.setAdminNames(adminN);
            return permission;

        }

    }

    /**
     * Get the Users this group contains.This are persisted as a collection of UserListBeans
     * 
     * @return ArrayList A collection of UserListBeans
     */
    public ArrayList getUserList() {
        if(this.userList == null){
            this.userList = new ArrayList();
        }
        return this.userList;
    }

    /**
     * Sets the list of users in this group
     * 
     * @param userList an array of UserListBeans representing the names of individual groups
     */
    public void setUserList(ArrayList userList) {
        this.userList = userList;
    }

    /**
     * Adds a user to the userList
     * 
     * @param userListBean The UserListBean to add
     */
    public void add(UserListBean userListBean) {
        userList.add(userListBean);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.members.Member#setGroupName(com.zphinx.spine.vo.StringAttributeBean)
     */
    public void setGroupName(StringAttributeBean groupName) {
        if(groupName != null){
            super.setGroupName(groupName);
            setName(groupName.getName());
        }
    }

}