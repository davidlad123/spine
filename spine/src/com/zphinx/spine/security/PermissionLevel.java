/**
 * PermissionLevel.java
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

import java.io.Serializable;

import com.zphinx.spine.exceptions.SpineException;

/**
 * PermissionLevel represents an object which mimics the default unix octal ACL system defined by the presence of Owner,group and public access rights.
 * <p>
 * It allows flags for setting the values Read,Write, and Execute flags and also provides a constructor which allows the client programmer to directly specify the octal numerical equivalent of a resources access rights. i.e calling :
 * </p>
 * <p>
 * <code>PermissionLevel pl = new PermissionLevel({7,5,5});</code><br>
 * Will create a new permissionLevel allowing the owner read,write, execute access and group, public access rights of read and execute.
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class PermissionLevel implements Serializable {

    /**
     * The Serial version UID of this object
     */
    private static final long serialVersionUID = 2273795713049325911L;

    /**
     * The owners default permision string
     */
    private boolean[] ownerPermission = null;

    /**
     * The owners default permision string
     */

    private boolean[] groupPermission = null;

    /**
     * The public's default permision string
     */
    private boolean[] publicPermission = null;

    /**
     * Default constructor which initializes the permission level of this object to it's unix equivalent of 755 as defined by:
     * <ul>
     * <li>Owner - Read,Write,Execute</li>
     * <li>Group - Read,NoWrite,Execute</li>
     * <li>public - Read,NoWrite,Execute</li>
     * </ul>
     */
    public PermissionLevel() {
        super();
        this.ownerPermission = new boolean[3];
        this.ownerPermission[0] = true;
        this.ownerPermission[1] = true;
        this.ownerPermission[2] = true;

        this.groupPermission = new boolean[3];
        this.groupPermission[0] = true;
        this.groupPermission[1] = false;
        this.groupPermission[2] = true;

        this.publicPermission = new boolean[3];
        this.publicPermission[0] = true;
        this.publicPermission[1] = false;
        this.publicPermission[2] = true;

    }

    /**
     * Constructor which uses the numerical(octal) equivalent of the unit access control rights system to assign permission flags to this PermissionLevel. These are defined as:
     * <ul>
     * <li>7 - Read,Write,Execute</li>
     * <li>6 - Read,Write</li>
     * <li>5 - Read,Execute</li>
     * <li>4 - Read</li>
     * <li>3 - Write,Execute</li>
     * <li>2 - Write</li>
     * <li>1 - Execute</li>
     * <li>0 - No Flag set</li>
     * </ul>
     * <p>
     * Any of these octal numbers can be set as a member of the int array passed to this constructor. The values are then transposed to a boolean array which is determined respectively and is symbolized using a boolean array to represent<br/> <code>[Read,Write,Execute]</code> i.e<br/> <code>[true,false,true]</code> will yield
     * <code>[Read,NoWrite,Execute]</code> or <code>5</code> in octal notation
     * </p>
     * 
     * @param permit The array of int flags which define the access rights.
     * @throws SpineException A spine exception is thrown should there be an error creating a permission level
     */
    public PermissionLevel(int[] permit) throws SpineException {
        if(permit.length == 3){

            this.ownerPermission = new boolean[3];
            this.publicPermission = new boolean[3];
            this.groupPermission = new boolean[3];
            for (int i = 0; i < permit.length; i++){
                if(i == 0){
                    this.ownerPermission = createBooleanArray(permit[i]);
                }
                else if(i == 1){
                    this.groupPermission = createBooleanArray(permit[i]);
                }
                else if(i == 2){
                    this.publicPermission = createBooleanArray(permit[i]);
                }
            }

        }
        else{
            throw new SpineException("Error: The public permission contains less than or more than the required number of octals!");
        }
    }

    /**
     * Creates a boolean array which maps an octal value to its permission based boolean array
     * 
     * @param octal The octal value used to creat the boolean array
     * @return A boolean array which symbolizes the octal value parameter
     */
    private boolean[] createBooleanArray(int octal) {
        boolean[] someArray = new boolean[3];
        switch(octal){

            case 7:
                someArray[0] = true;
                someArray[1] = true;
                someArray[2] = true;
                break;

            case 6:
                someArray[0] = true;
                someArray[1] = true;
                someArray[2] = false;
                break;

            case 5:
                someArray[0] = true;
                someArray[1] = false;
                someArray[2] = true;
                break;

            case 4:
                someArray[0] = true;
                someArray[1] = false;
                someArray[2] = false;
                break;

            case 3:
                someArray[0] = false;
                someArray[1] = true;
                someArray[2] = true;
                break;

            case 2:
                someArray[0] = false;
                someArray[1] = true;
                someArray[2] = false;
                break;

            case 1:
                someArray[0] = false;
                someArray[1] = false;
                someArray[2] = true;
                break;

            default:
                someArray[0] = false;
                someArray[1] = false;
                someArray[2] = false;
                break;

        }
        return someArray;
    }

    /**
     * Constructor allowing the client programmer to specify the contents of the permissionLevel using boolean arrays. The contents of the arrays are of the format:
     * <p>
     * [ReadFlag,WriteFlag,ExecuteFlag] eg if a boolean array is set as [true,false,false] for a public permission set, then this permissionLevel object will have a unix equivalent of readOnly or [Read,NoWrite,NoExecute].
     * </p>
     * 
     * @param ownerPermission The owner's permission boolean array
     * @param groupPermission The group's permission boolean array
     * @param publicPermission The public's permission boolean array
     * @throws SpineException A spine exception is thrown should there be an error creating a permission level
     */
    public PermissionLevel(boolean[] ownerPermission, boolean[] groupPermission, boolean[] publicPermission) throws SpineException {
        super();
        if(checkArray(groupPermission)){
            this.groupPermission = groupPermission;
        }
        else{
            throw new SpineException("Error: The group permission contains an illegal character");
        }
        if(checkArray(ownerPermission)){
            this.ownerPermission = ownerPermission;
        }
        else{
            throw new SpineException("Error: The owner permission contains an illegal character");
        }
        if(checkArray(publicPermission)){
            this.publicPermission = publicPermission;
        }
        else{
            throw new SpineException("Error: The public permission contains an illegal character");
        }

    }

    /**
     * Checks if a boolean array can be used as a permission value
     * 
     * @param s The boolean array which is to be checked
     * @return True if this array conforms
     */
    private boolean checkArray(boolean[] s) {
        boolean ok = false;
        if(s.length == 3){
            ok = true;
        }
        return ok;
    }

    /**
     * Gets the group permissions for this object
     * 
     * @return Returns the groupPermission.
     */
    public boolean[] getGroupPermission() {
        return groupPermission;
    }

    /**
     * Gets the owner permissions for this object
     * 
     * @return Returns the ownerPermission.
     */
    public boolean[] getOwnerPermission() {
        return ownerPermission;
    }

    /**
     * Gets the public permissions for this object
     * 
     * @return Returns the publicPermission.
     */
    public boolean[] getPublicPermission() {
        return publicPermission;
    }

    /**
     * Gets the octal representation of this permissionLevel
     * 
     * @return An octal indicating the permissionLevel assigned to this role
     */
    public int[] getPermissionOctal() {
        int[] ret = new int[3];

        ret[0] = checkLevel(this.ownerPermission);
        ret[1] = checkLevel(this.groupPermission);
        ret[2] = checkLevel(this.publicPermission);

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer("");
        int[] i = this.getPermissionOctal();
        s.append(i[0]);
        s.append(i[1]);
        s.append(i[2]);
        return s.toString();
    }

    /**
     * Checks the octal level of this permissionLevel
     * 
     * @param someArray The boolean array containing the values to be checked
     * @return An octal indicating the permissionLevel assigned to this role
     */
    private int checkLevel(boolean[] someArray) {
        int i = 0;
        if(someArray[0] && someArray[1] && someArray[2]){
            i = 7;
        }
        if(someArray[0] && someArray[1] && !someArray[2]){
            i = 6;
        }
        if(someArray[0] && !someArray[1] && someArray[2]){
            i = 5;
        }
        if(someArray[0] && !someArray[1] && !someArray[2]){
            i = 4;
        }
        if(!someArray[0] && someArray[1] && someArray[2]){
            i = 3;
        }
        if(!someArray[0] && someArray[1] && !someArray[2]){
            i = 2;
        }
        if(someArray[0] && someArray[1] && !someArray[2]){
            i = 1;
        }
        if(!someArray[0] && !someArray[1] && !someArray[2]){
            i = 0;
        }

        return i;
    }

    /**
     * Gets the group read,write and execute flags for this permission object
     * 
     * @param i An int denoting location of the flag to get from the boolean array representing the groups permission
     * @return Returns the groupPermission.
     */
    public boolean getGroupPermission(int i) {
        return groupPermission[i];
    }

    /**
     * Gets the owner's read,write and execute flags for this permission object
     * 
     * @param i An int denoting location of the flag to get from the boolean array representing the owner's permission
     * @return Returns the ownerPermission.
     */
    public boolean getOwnerPermission(int i) {
        return ownerPermission[i];
    }

    /**
     * Gets the public read,write and execute flags for this permission object
     * 
     * @param i An int denoting location of the flag to get from the boolean array representing the public permission
     * @return Returns the publicPermission.
     */
    public boolean getPublicPermission(int i) {
        return publicPermission[i];
    }

    /**
     * Sets the read,write and execute flags for the groupPermission in the order shown i.e [R,W,X].at the stated index
     * 
     * @param index The index of the group permission to set
     * @param value The value of the group permission to set either true or false
     */
    public void setGroupPermission(int index, String value) {
        this.groupPermission[index] = new Boolean(value).booleanValue();
    }

    /**
     * Sets the read,write and execute flags for the ownerPermission in the order shown i.e [R,W,X].at the stated index
     * 
     * @param index The index of the owner permission to set
     * @param value The value of the owner permission to set, either true or false
     */
    public void setOwnerPermission(int index, String value) {
        this.ownerPermission[index] = new Boolean(value).booleanValue();
    }

    /**
     * Sets the read,write and execute flags for the publicPermission in the order shown i.e [R,W,X].at the stated index
     * 
     * @param index The index of the public permission to set
     * @param value The value to set (true or false)
     */
    public void setPublicPermission(int index, String value) {
        this.publicPermission[index] = new Boolean(value).booleanValue();
    }

    /**
     * Sets the group permissions for this object
     * 
     * @param groupPermission The groupPermission to set.
     */
    public void setGroupPermission(boolean[] groupPermission) {
        this.groupPermission = groupPermission;
    }

    /**
     * Sets the owner permissions for this object
     * 
     * @param ownerPermission The ownerPermission to set.
     */
    public void setOwnerPermission(boolean[] ownerPermission) {
        this.ownerPermission = ownerPermission;
    }

    /**
     * Sets the public permissions for this object
     * 
     * @param publicPermission The publicPermission to set.
     */
    public void setPublicPermission(boolean[] publicPermission) {
        this.publicPermission = publicPermission;
    }

}
