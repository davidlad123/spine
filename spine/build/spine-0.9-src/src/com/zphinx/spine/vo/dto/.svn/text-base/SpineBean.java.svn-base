/**
 * SpineBean.java
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

package com.zphinx.spine.vo.dto;

import java.util.Date;
import java.util.Locale;

import com.zphinx.spine.Universal;
import com.zphinx.spine.members.Administrator;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.members.User;
import com.zphinx.spine.security.SpinePermission;

/**
 * <p>
 * SpineBean contains the base properties of all classes that can be managed by Spine.
 * </p>
 * <p>
 * A SpineBean contains base properties which can be used by client programmers to define subclasses and provides features which are needed by spine to identify objects which it can manipulate.
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 30-Apr-2005 23:10:38<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class SpineBean implements DataTransferObject {

    /**
     * The date this object was created
     */
    private Date creationDate = null;

    /**
     * The date this object was last modified
     */
    private Date modifiedDate = null;

    /**
     * The serial version uid of this object
     */
    private static final long serialVersionUID = 2386166294462675683L;

    /**
     * The id of this object
     */

    private long id = 0;
    
    /**
     * The name of this object
     */

    private String name = null;

    /**
     * The description of this object
     */

    private String description = null;

    /**
     * The SpinePermission Object associated with this user
     */
    private SpinePermission permission = null;

    /**
     * This beans unique ID
     */
    private String sessionId = null;

/**
 * The Locale where this object was created
 */
    private Locale locale = null;
    /**
     * Public Constructor
     */
    public SpineBean() {
        super();
        this.creationDate = new Date();
        this.sessionId = Universal.getRandom(10);
    }

    /**
     * Gets the id of this object
     * 
     * @return Returns the id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of this object
     * 
     * @param id The id to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the description of this object
     * 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this object
     * 
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the name of this object
     * 
     * @return Returns the name.
     */
    public String getName() {
        if(name == null){
            name = this.getClass().getSimpleName();
        }
        return name;
    }

    /**
     * Sets the name of this object
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets a clone of the SpinePermission for this object.The SpinePermission can be reset if necessary but a clone is always returned so that external operations does
     *  not affect the security state of this object.
     * 
     * @return SpinePermission A SpinePermission for this member object
     * @see SpinePermission
     */
    public SpinePermission getPermission() {
        SpinePermission perm = null;
        if(permission == null){
            PermissionFactory pf = new PermissionFactory();
            if(getName() == null){
                setName(this.getClass().getSimpleName());
            }
            this.permission = pf.getPermission(getName(), " ", getId());

        }
        try{
            perm = (SpinePermission) this.permission.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return perm;
    }

    /**
     * Set the permission object associated with this user
     * 
     * @param permission The permission object associated with this user
     */
    public void setPermission(SpinePermission permission) {
        this.permission = permission;
    }

    /**
     * A permission Factory to create a permission for use by this object, called by implementations of SpineBean to retrieve a suitable SpinePrmission
     * 
     * @author David Ladapo
     * @version $Revision: 1.1 $ $Date: 2008/06/12 22:35:40 $
     */

    public class PermissionFactory {

        /*
         * Create a default permission object of this type of member @return SpinePermission the default permission object for this member
         */
        public SpinePermission getPermission(String name, String actions, long id) {

            SpinePermission permission = new SpinePermission(name, actions, id+"");
            setPermission(permission);
            return permission;

        }
    }

    /**
     * Gets this objects creation date, normally created in the constructor of this object
     * 
     * @return Returns the creationDate.
     */
    public long getCreationDate() {
        return creationDate.getTime();
    }

    /**
     * Sets this objects creation date
     * 
     * @param longDate The creationDate to set.
     */
    public void setCreationDate(long longDate) {

        this.creationDate = new Date(longDate);
    }

    /**
     * Gets this objects last modified date
     * 
     * @return Returns the modifiedDate.
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets this objects last modified date
     * 
     * @param modifiedDate The modifiedDate to set.
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.vo.dto.DataTransferObject#getSessionId()
     */

    public String getSessionId() {

        return sessionId;
    }

    /**
     * Sets this beans sessionId
     * 
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

	/*
	 * (non-Javadoc)
	 * @see com.zphinx.spine.vo.dto.DataTransferObject#getLocale()
	 */
	public Locale getLocale() {
		if(this.locale == null){
			this.locale = Locale.getDefault();
		}
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}


}
