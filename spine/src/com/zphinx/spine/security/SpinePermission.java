/**
 * SpinePermission.java
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

package com.zphinx.spine.security;

import java.security.BasicPermission;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.message.Messages;
import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * SpinePermission declares a set of rules for a Spine managed object.
 * <p>
 * It possesses unix like ACL attributes i.e the permission can be read write or execute. It also defines access control on the premise of a "belongs to" relationship.
 * </p>
 * <p>
 * Since a principal can belong to one of three primary roles i.e
 * <ul type=disc>
 * <li><code>GROUP</code>
 * <li><code>ADMINISTRATOR</code>
 * <li><code>USER</code>
 * </ul>
 * </p>
 * <p>
 * In this manner, A group login is a superuser who allows its listed administrators to manipulate information usually belonging to or relating to users registered with that group.
 * </p>
 * <p>
 * An administrator can administer several groups of registered users, as defined by the groupNames registered to it. A user can belong to several groups, there by making it possible for several administrators to amend data belonging to that user. The group superuser can administer adminstrators registered to it, but can only edit data relating or
 * belonging to a user in it's primary group and sub groups.
 * </p>
 * <p>
 * Developers using this object can call its {@link SpinePermission#checkGuard(Object)} and {@link SpinePermission#getPermit(String)} methods to find out if a Member has access to an object or they can query the permission for read write or execute permissions.
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpinePermission extends BasicPermission implements Cloneable {

    /**
     * The literal name of the superuser of this system
     */
    private static final String ADMIN = "admin";

    /**
     * The serial version uid of this object
     */
    private static final long serialVersionUID = 3448322162557165654L;

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(SpinePermission.class.getName());

    /**
     * An int representing the Administrator
     */

    public static final int PERMISSION_ADMINISTRATOR = 1;

    /**
     * An int representing the User
     */

    public static final int PERMISSION_USER = 2;

    /**
     * An int representing the Groups
     */

    public static final int PERMISSION_GROUP = 3;

    /**
     * An int representing an ordinary user
     */

    public static final int PERMISSION_PUBLIC_USER = 4;

    /**
     * The full name of the member object
     */
    private static final String USER = "User";

    /**
     * The full name of the member object
     */
    private static final String GROUP = "Group";

    /**
     * The full name of the member object
     */
    private static final String APPLICATION = "Application";

    /**
     * The full name of the member object
     */
    private static final String ADMINISTRATOR = "Administrator";

    /**
     * The permissionLevels preset for this object
     */
    private PermissionLevel permissionLevel = null;

    /**
     * The idTag token.
     */
    private String idTag = null;

    /**
     * The Adminisitrators of this object.
     */

    private List<StringAttributeBean> adminNames = null;

    /**
     * The Groups which have group access to this object.
     */

    private List<StringAttributeBean> groupNames = null;

    /**
     * The type of the object which owns this permission ... this must be set at initialization
     */
    private String ownerObjectType = null;

    /**
     * Defualt Constructor - Accepts a string name argument used to identify this permission
     * 
     * @param name The identifier of this object
     */
    public SpinePermission(final String name) {
        super(name);
    }

    /**
     * A constructor which uses the properties of the input permission as it's default properties.
     * 
     * @param name A string used to identify this permission
     * @param permission A permission from which this permission inherits it's properties
     */
    public SpinePermission(final String name, final SpinePermission permission) {
        super(name);
        setIdTag(permission.getIdTag());
        setAdminNames(createListClone(permission.getAdminNames()));
        setGroupNames(createListClone(permission.getGroupNames()));
        this.permissionLevel = copyPermissionLevel(permission);
    }

    /**
     * A constructor which is used to preset the name,permissionLevel,admin names, and group names of this permission.
     * 
     * @param name A string used to identify this permission
     * @param pLevel The permission level to assign to the newly create permission
     * @param adminNames The names|ids of the administrators of the owner of this permission
     * @param groupNames The names|ids of the groups of the owner of this permission
     */
    public SpinePermission(final String name, final PermissionLevel pLevel, final List<StringAttributeBean> adminNames, final List<StringAttributeBean> groupNames) {
        super(name);
        this.permissionLevel = pLevel;
        this.adminNames = adminNames;
        this.groupNames = groupNames;

    }

    /**
     * Constructor which assigns the names, action and id of this permission. The action parameter is currently unused.
     * 
     * @param name A string used to identify this permission
     * @param action Currently passed to the suoer class unused
     * @param id The idTag to assign to this permission
     */
    public SpinePermission(final String name, final String action, final String id) {
        super(name, action);
        setIdTag(id);
    }

    /**
     * Gets the type of SpinePermission Object we represent
     * 
     * @return An int, the SpinePermission flag of this member
     */
    private int getPermissionType() {
        int permitGroup;
        if(this.getOwnerObjectType().equalsIgnoreCase(GROUP) || this.getOwnerObjectType().equalsIgnoreCase(APPLICATION)){
            permitGroup = PERMISSION_GROUP;

        }
        else if(this.getOwnerObjectType().equalsIgnoreCase(ADMINISTRATOR)){
            permitGroup = PERMISSION_ADMINISTRATOR;

        }
        else if(this.getOwnerObjectType().equalsIgnoreCase(USER)){
            permitGroup = PERMISSION_USER;
        }
        else{
            permitGroup = PERMISSION_PUBLIC_USER;
        }

        return permitGroup;

    }

    /**
     * Calculate if the member whose id is given as the permitId has possible access to this resource
     * 
     * @param permitId An id used to check if this administrator or group has access to the permissions owner object
     * @return true if we have permission to perform this activity
     */
    public boolean getPermit(final String permitId) {
        final int permitGroup = getPermissionType();
        boolean proFlag = false;
        List<StringAttributeBean> theList = null;

        switch(permitGroup){

            case PERMISSION_ADMINISTRATOR:
                // case does checkGroupNames
                theList = getGroupNames();
                proFlag = checkGroupId(permitId, theList);
                break;

            case PERMISSION_USER:
                // case does checkGroupNames
                theList = getGroupNames();
                proFlag = checkGroupId(permitId, theList);
                break;

            case PERMISSION_GROUP:
                // case does checkAdminNames
                theList = getAdminNames();
                proFlag = checkGroupId(permitId, theList);
                break;
            default:
                break;

        }

        return proFlag;

    }

    /**
     * Checks these groupIds against the permitId
     * 
     * @param permitId The string to search for
     * @param theList The List which may contain a StringAttributeBean whose value is equal to the permitID
     * @return True if a StringAttributeBean whose value is the permitId exists in this collection
     */
    private boolean checkGroupId(final String permitId, final List<StringAttributeBean> theList) {
        boolean proFlag = false;
        StringAttributeBean gn;
        if(log.isDebugEnabled()){
            log.debug("The groups in permission are: " + theList); //$NON-NLS-1$
        }
        for (int j = 0; j < theList.size(); j++){
            gn = theList.get(j);
            if(gn.getValue().equals(permitId)){
                proFlag = true;
                break;

            }

        }
        return proFlag;
    }

    /**
     * Sets the list of administrator StringAttributeBeans in this permission
     * 
     * @param adminNames The StringAttributeBeans ArrayList of Administrators
     */
    public void setAdminNames(final List<StringAttributeBean> adminNames) {
        this.adminNames = adminNames;

    }

    /**
     * Get The ArrayList of StringAttributeBeans containing the names and Ids of Administrators of this member
     * 
     * @return ArrayList The StringAttributeBeans ArrayList of Administrators
     */
    public List<StringAttributeBean> getAdminNames() {
        return this.adminNames;

    }

    /**
     * Sets the ArrayList of group StringAttributeBeans in this permission
     * 
     * @param groupNames The StringAttributeBeans ArrayList of Groups
     */
    public void setGroupNames(final List<StringAttributeBean> groupNames) {
        this.groupNames = groupNames;

    }

    /**
     * Get The ArrayList of StringAttributeBeans containing the names and Ids of Groups of this member
     * 
     * @return ArrayList The StringAttributeBeans ArrayList of Groups.
     */
    public List<StringAttributeBean> getGroupNames() {
        return this.groupNames;

    }

    /**
     * Gets the readFlag for this SpinePermission
     * 
     * @param permission The permission of the principal making this request
     * @return boolean The new readFlag of this SpinePermission.
     */
    public boolean getReadFlag(final SpinePermission permission) {
        return checkAccess(permission, 1);
    }

    /**
     * Gets the writeFlag of this SpinePermission with respect to the principal making the call.
     * 
     * @param permission The permission of the principal making this request
     * @return boolean The writeFlag of this SpinePermission.
     */
    public boolean getWriteFlag(final SpinePermission permission) {
        return checkAccess(permission, 2);
    }

    /**
     * Gets the executeFlag for this SpinePermission
     * 
     * @param permission The permission of the principal making this request
     * @return boolean The new executeFlag of this SpinePermission.
     */

    public boolean getExecuteFlag(final SpinePermission permission) {

        return checkAccess(permission, 3);
    }

    /**
     * Checks the access type we require
     * 
     * @param accessType The type of access we require i.e "R","W","X"
     * @return Return true if the owner of SpinePermission can access the guarded object which owns this permission.
     */
    private boolean checkAccess(final SpinePermission permission, final int accessType) {

        boolean result = false;
        final PermissionLevel perLev = this.getPermissionLevel();

        final int i = permission.getPermissionType();

        // Check for public permission
        // check if this object provides public access ... this could be a bug
        // as a group member may not have that sort of access
        boolean[] s = perLev.getPublicPermission();
        result = s[accessType - 1];

        if(result){
            // public access available
        }
        // check if this object is owned by the user
        else if(getIdTag().equals(permission.getIdTag())){
            s = perLev.getOwnerPermission();
            result = s[accessType - 1];

        }
        // check if the user is the superuser of the system
        // re[place permission.startsWith
        else if((permission.getOwnerObjectType().equalsIgnoreCase(GROUP)) && (Universal.getGroupLabel(permission.getIdTag()).equalsIgnoreCase(ADMIN))){
            result = true;

        }

        // run other checks
        else{
            switch(i){
                case SpinePermission.PERMISSION_ADMINISTRATOR:
                    // the user is an administrator... check if he/she has group access
                    if(checkGroupNames(permission)){
                        // grant access if this object is owned by a user or the
                        // public
                        if((getPermissionType() == SpinePermission.PERMISSION_USER) || (getPermissionType() == SpinePermission.PERMISSION_PUBLIC_USER)){
                            result = true;

                        }
                        else{
                            // grant access if this object grants group access
                            s = perLev.getGroupPermission();
                            result = s[accessType - 1];

                        }
                    }
                    break;

                case SpinePermission.PERMISSION_USER:
                    // the user is a user... check if he has group access for this
                    // activity if and only if we have a matching group
                    if(checkGroupNames(permission)){

                        s = perLev.getGroupPermission();
                        result = s[accessType - 1];
                    }
                    break;

                case SpinePermission.PERMISSION_GROUP:

                    // check if one of these administrators belongs
                    if(checkAdminNames(permission)){
                        result = true;
                    }

                    break;
                default:
                    break;
            }
        }
        return result;
    }

    /**
     * Get the new idTag of this SpinePermission.
     * 
     * @return A String representing the idTag of this permission
     */
    public String getIdTag() {
        return (this.idTag);
    }

    /**
     * Set the idTag of this SpinePermission.
     * 
     * @param idTag The idTag of this SpinePermission.
     */
    public void setIdTag(final String idTag) {
        this.idTag = idTag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.security.Permission#getActions()
     */
    public String getActions() {
        return super.getActions();
    }

    /**
     * Gets the permissionLevel of this object
     * 
     * @return Returns the permissionLevel.
     */
    public PermissionLevel getPermissionLevel() {
        if(this.permissionLevel == null){
            this.permissionLevel = new PermissionLevel();
        }
        return this.permissionLevel;
    }

    /**
     * Checks the user can modify the permissionLevel before modification
     * 
     * @param permissionLevel The permissionLevel to set.
     */
    public void setPermissionLevel(final PermissionLevel permissionLevel, final SpinePermission permission) {
        checkGuard(permission);
        this.permissionLevel = permissionLevel;
    }

    /**
     * Sets the permissionLevel of this object,this method must not be called directly if we need to check the callers ability to modify permissionLevel
     * 
     * @param permissionLevel The permissionLevel to set.
     */
    public void setPermissionLevel(final PermissionLevel permissionLevel) {

        this.permissionLevel = permissionLevel;
    }

    /**
     * Checks if the specified member permission object can access this resource, this will throw a SecurityException if a SpinePermission is queried.
     * 
     * @param obj The object which we check against
     */
    public void checkGuard(final Object obj) throws SecurityException {

        // throw new SecurityException(Messages.getString("SpinePermission.9"));
        SpinePermission permission = null;
        if(obj instanceof MemberPermission){
            permission = (SpinePermission) obj;
            // there must be a check to see if this user can amend permission
            // levels
            boolean permit = false;
            final int pType = permission.getPermissionType();
            switch(pType){
                case SpinePermission.PERMISSION_ADMINISTRATOR:
                    permit = checkGroupNames(permission);
                    break;

                case SpinePermission.PERMISSION_GROUP:
                    permit = checkAdminNames(permission);
                    break;

                case SpinePermission.PERMISSION_USER:
                    permit = checkGroupNames(permission);
                    break;

                default:
                    break;

            }
            if(!permit) throw new SecurityException(Messages.getString("SpinePermission.9")); //$NON-NLS-1$
        }
        else{
            super.checkGuard(obj);
        }
    }

    /**
     * Checks that at least one of the groups in permission can access this object's owner resource
     * 
     * @param permission The member who requires access permission
     * @return A boolean indicating if this permission is valid
     */
    private boolean checkGroupNames(final SpinePermission permission) {
        boolean permit = false;
        final List<StringAttributeBean> theList = getGroupNames();
        if(theList != null){
            for (int j = 0; j < theList.size(); j++){
                final StringAttributeBean gn = theList.get(j);
                final List<StringAttributeBean> memberList = permission.getGroupNames();

                for (int k = 0; k < memberList.size(); k++){
                    final StringAttributeBean lvb = memberList.get(k);
                    if(lvb.getValue().equals(gn.getValue())){
                        permit = true;
                        break;
                    }
                }
                if(permit){
                    break;
                }

            }
        }
        return permit;
    }

    /**
     * Checks that at least one of the administrators in permission can access this object's owner resource
     * 
     * @param permission The member requiring permission to modify this permission owners resource
     * @return A boolean indicating if this permission is valid
     */
    private boolean checkAdminNames(final SpinePermission permission) {
        boolean permit = false;
        List<StringAttributeBean> theList;

        // replace startswith G with
        // if(permission.getOwnerObjectType().equals("com.zphinx.spine.members.Group")
        if((permission.getOwnerObjectType().equalsIgnoreCase(GROUP)) && (Universal.getGroupLabel(permission.getIdTag()) != null) && (Universal.getGroupLabel(permission.getIdTag()).equalsIgnoreCase(ADMIN))){
            permit = true;
        }
        else{
            theList = getGroupNames();
            if(theList != null){
                for (int j = 0; j < theList.size(); j++){
                    final StringAttributeBean gn = theList.get(j);
                    if(gn.getValue().equals(permission.getIdTag())){
                        permit = true;
                        break;
                    }

                }
            }
            if(!permit){

                final List<StringAttributeBean> memberList = permission.getAdminNames();
                if(memberList != null){
                    for (int k = 0; k < memberList.size(); k++){
                        final StringAttributeBean lvb = memberList.get(k);
                        if(lvb.getValue().equals(this.getIdTag())){
                            permit = true;
                            break;
                        }

                    }
                }

            }

        }

        return permit;
    }

    /**
     * Performs a deep clone of this object.Safely replicates all object attributes without imposing lose references.
     * 
     * @return A SpinePermission object
     */

    public Object clone() throws CloneNotSupportedException {
        SpinePermission sp = (SpinePermission) super.clone();
        if(sp == null) sp = new SpinePermission(getName(), "", this.idTag);
        sp.setAdminNames(createListClone(this.adminNames));
        sp.setGroupNames(createListClone(this.groupNames));
        sp.setPermissionLevel(this.copyPermissionLevel(this));
        return sp;
    }

    /**
     * Clones a List Object by creating a copy of the contents of the list.. this is specific to a List holding a collection of StringAttribute beans
     * 
     * @param aList The list object to
     * @return A clone of the original list
     */
    private List<StringAttributeBean> createListClone(final List<StringAttributeBean> aList) {

        final List<StringAttributeBean> clonedList = new ArrayList<StringAttributeBean>();
        if(aList != null){
            for (int i = 0; i < aList.size(); i++){
                final StringAttributeBean sb = aList.get(i);
                clonedList.add(new StringAttributeBean(sb.getName(), sb.getValue()));
            }
        }
        return clonedList;

    }

    /**
     * Copies the permissionLevel in this permissionLevel to a new PermissionLevel object
     * 
     * @param permission The permission of the object which contains the permissionLevel
     * @return A newly created permissionLevel
     */
    private PermissionLevel copyPermissionLevel(final SpinePermission permission) {

        PermissionLevel pNew = null;
        try{
            final PermissionLevel pOld = permission.permissionLevel;
            pNew = new PermissionLevel(pOld.getPermissionOctal());
        }
        catch (final SpineException e){
            e.printStackTrace();
        }
        return pNew;
    }

    /**
     * Gets the full class name of the object which owns this permission
     * 
     * @return the ownerObjectType
     */
    public String getOwnerObjectType() {
        return ownerObjectType;
    }

    /**
     * Sets the full class name of the object which owns this permission
     * 
     * @param ownerObjectType the ownerObjectType to set
     */
    public void setOwnerObjectType(final String ownerObjectType) {
        this.ownerObjectType = ownerObjectType;
    }
}
