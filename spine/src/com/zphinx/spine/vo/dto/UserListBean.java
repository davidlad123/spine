/**
 *
 * UserListBean.java
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

/**
 * A UserListBean provides additional properties for use by Member objects of the spine framework
 * 
 * @author David Ladapo
 * @version $Revision: 1.4 Date: Wed Aug 18 02:00:31 BST 2004
 *          <p>
 *          copyright &copy; Zphinx Software Solutions
 *          </p>
 */

public class UserListBean extends StringAttributeBean {

    /**
     * 
     */
    private static final long serialVersionUID = -442515535610536182L;

    /**
     * The time this UserListBean was created
     */
    private long time = 0;

    /**
     * The time zone offset of this bean in minutes
     */

    private String timeZoneOffset = null;

    /**
     * The probable id of this time zone
     */
    private String timeZoneId = null;

    /**
     * The last user of this object
     */
    private String lastUser;

    /**
     * Constructor which defines this objects name and value and also sets its creation time to the time property
     * 
     * @param name The name for this bean instance
     * @param value The value to associate with this object
     */

    public UserListBean(String name, String value) {
        super(name, value);
        this.setTime();
    }

    /**
     * Set the time this object was created in milliseconds
     */
    public void setTime() {
        this.time = System.currentTimeMillis();

    }

    /**
     * Gets the time this UserListBean was created.
     * 
     * @return long A long representing the time in milliseconds when this UserListBean was created.
     */
    public long getTime() {
        return this.time;

    }

    /**
     * Sets the timezone offset of this object
     * 
     * @param timeZoneOffset The timeZoneOffset to set.
     */
    public void setTimeZoneOffset(String timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    /**
     * Gets the timezone offset of this object
     * 
     * @return Returns the timeZoneOffset.
     */
    public String getTimeZoneOffset() {
        return timeZoneOffset;
    }

    /**
     * Gets a string describing this object
     * 
     * @return String A String description of this object
     */

    public String toString() {
        return getName() + ":" + getValue() + ":" + getTime();
    }

    /**
     * Gets the timezone id of this object
     * 
     * @return Returns the timeZoneId.
     */
    public String getTimeZoneId() {
        return timeZoneId;
    }

    /**
     * Sets the timezone id of this object
     * 
     * @param timeZoneId The timeZoneId to set.
     */
    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    /**
     * Sets the last User used by this UserListBean
     * 
     * @param lastUser The last user of this object
     */

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;

    }

    /**
     * Gets the last User used by this UserListBean
     * 
     * @return Returns the lastUser.
     */
    public String getLastUser() {
        return lastUser;
    }
}
