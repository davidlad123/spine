/**
 *
 * ActiveGroups.java
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

package com.zphinx.spine.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.vo.dto.DataTransferObject;
import com.zphinx.spine.vo.dto.StringAttributeBean;
import com.zphinx.spine.vo.dto.UserListBean;

/**
 * An ActiveGroups object encapsulates the logic behind the persistent group system which is used by
 *  the spine framework to capture the properties of Groups registered within the system.
 * 
 * @see java.util.HashMap
 * @see com.zphinx.spine.members.Group
 * @author David Ladapo
 * @version $Revision: 1.1 $ $Date: 2008/06/12 22:35:52 $
 *          <p>
 *          copyright &copy; Zphinx Software Solutions
 *          </p>
 */

public class ActiveGroups extends ConcurrentHashMap implements DataTransferObject {

    /**
     * 
     */
    private static final String UNIQUE = "unique";

    /**
     * 
     */
    private static final String SITE_USERS = "siteUsers";

    /**
     * 
     */
    private static final long serialVersionUID = -3016414389779326423L;

    /**
     * int representing the time to elapse before performing cleanup of this objects
     */

    private static int duration = Universal.SESSION_COUNTER;

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(ActiveGroups.class.getName());

    /**
     * Public Constructor, set ups up a default group of non logged in users
     */

    public ActiveGroups() {
        Group siteUsers = new Group();
        siteUsers.setId(new Long(1));
        siteUsers.setGroupName(new StringAttributeBean(SITE_USERS, 1+""));
        put(new Long(1), siteUsers);

    }

    /**
     * Gets the groups as a list of groups
     * 
     * @return A list of groups
     */
    public List getGroupsList() {
        return new ArrayList(this.values());
    }

    /**
     * Gets the groups as a list of groups
     * 
     * @return A list of groups
     */
    public List subsidizedGroupsList() {
        Iterator i = this.values().iterator();
        List aList = new ArrayList();
        while (i.hasNext()){
            Group g = (Group) i.next();
            aList.add(g.getGroupName());
        }

        return aList;
    }

    /**
     * Method to call when user joins a group i.e logs into a group.
     * 
     * @param groupId The group whose id to join
     * @param lvb The UserListBean representing this User
     * @return int The total number of users currently logged into this group
     */

    public int joinGroup(long groupId, UserListBean lvb) {
        int outInt = 0;
        synchronized (this){
            Group group = (Group) get(new Long(groupId));
            if(group != null){
                ArrayList uList = group.getUserList();
                ArrayList dList = getUniqueList();
                if(!uList.contains(lvb)){
                    uList.add(lvb);
                    String s = lvb.getValue();
                    if(!dList.contains(s)) dList.add(s);
                    group.setUserList(uList);
                    put(new Long(groupId), group);
                    put(UNIQUE, dList);
                }
                outInt = uList.size();
            }
        }
        return outInt;
    }

    /**
     * Method to call when user updates his session within a group.
     * 
     * @param groupId The group whose id to User belongs to
     * @param lvb The UserListBean representing this User
     * @return int The total number of users currently logged into this group
     */

    public int updateGroup(long groupId, UserListBean lvb) {
        int outInt = 0;
        synchronized (this){
            Group group = (Group) get(new Long(groupId) );
            if(group != null){
                ArrayList uList = group.getUserList();
                if(uList.contains(lvb)){
                    int a = uList.indexOf(lvb);
                    lvb.setTime();
                    uList.set(a, lvb);

                }
                else{
                    uList.add(lvb);
                    String s = lvb.getValue();
                    ArrayList dList = getUniqueList();
                    if(!dList.contains(s)) dList.add(s);
                    put(UNIQUE, dList);

                }
                outInt = uList.size();
                group.setUserList(uList);
                put(new Long(groupId), group);

            }
        }
        return outInt;
    }

    /**
     * Method to call when user logs out of the system
     * 
     * @param groupId The group whose id  this user belongs
     * @param lvb The LabelValueBean representing this User
     * @return int The total number of users currently logged into this group
     */

    public int exitGroup(long groupId, UserListBean lvb) {
        int outInt = 0;
        synchronized (this){
            Group group = (Group) get(new Long(groupId));
            if(group != null){
                ArrayList uList = group.getUserList();
                ArrayList dList = getUniqueList();
                if(uList.contains(lvb)){
                    uList.remove(lvb);
                    String s = lvb.getValue();
                    if(dList.contains(s)) dList.remove(s);
                    group.setUserList(uList);
                    put(new Long(groupId), group);
                    put(UNIQUE, dList);
                }
                outInt = uList.size();
            }
        }

        return outInt;
    }

    /**
     * Get the number of users currently logged into a group
     * 
     * @param groupId The group whose size we need
     * @return int The total number of users currently logged into this group
     */

    public int getGroupSize(long groupId) {
        int outInt = 0;
        synchronized (this){
            Group group = (Group) get(new Long(groupId));
            if(group != null){
                outInt = group.getUserList().size();
            }
        }
        return outInt;
    }

    /**
     * Add a new Group object to this map
     * 
     * @param group The group to add to this HashMap
     */

    public void addGroup(Group group) {
        put(new Long(group.getId()), group);

    }

    /**
     * remove a Group object from this map
     * 
     * @param groupId The group id of the group to remove
     * @return Group The group we are removing
     */

    public Group removeGroup(long groupId) {
        return ((Group) remove(new Long(groupId)));

    }

    /**
     * Get a unique list of users of this system
     * 
     * @return ArrayList The unique list of user id's
     */

    public ArrayList getUniqueList() {
        ArrayList dList = null;
        synchronized (this){
            if(get(UNIQUE) == null){
                dList = new ArrayList();
                put(UNIQUE, dList);
            }
            else{
                dList = (ArrayList) get(UNIQUE);
            }

        }
        return dList;

    }

    /**
     * The id of the member to remove from all groups
     * 
     * @param id The id of member to remove from the group
     * @return True if this operation is successfull
     */

    public boolean exitGroup(long id) {
        int groupRemovedCounter = 0;
        boolean removed = false;
        if(getUniqueList().contains(new Long(id))){
            getUniqueList().remove(new Long(id));
        }
        Enumeration e = this.elements();
        while (e.hasMoreElements()){
            Group group = (Group) e.nextElement();
            ArrayList userList = group.getUserList();
            for (int i = 0; i < userList.size(); i++){
                UserListBean ulb = (UserListBean) userList.get(i);
                if(ulb.getValue().equals(id)){
                    exitGroup(group.getId(), ulb);
                    removed = true;
                    groupRemovedCounter += 1;
                    break;
                }
            }

        }
        log.debug("Successfully removed userlistBean from.." + groupRemovedCounter + " groups");
        return removed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.vo.dto.DataTransferObject#getSessionId()
     */
    public String getSessionId() {

        return Integer.toString(hashCode());
    }


	/*
	 * (non-Javadoc)
	 * @see com.zphinx.spine.vo.dto.DataTransferObject#getLocale()
	 */
	public Locale getLocale() {
			return Locale.getDefault();
	}



}
