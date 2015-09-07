/**
 * Identity.java
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

package com.zphinx.spine.members;

import com.zphinx.spine.vo.dto.SpineBean;

/**
 * Identity represents the base identity details of a principal/role of the spine framework.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Nov 15, 2006 1:42:18 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class Identity extends SpineBean {

    /**
     * A string denoting the application object
     */
  
    protected static final String APPLICATION = "Application";

    /**
     * A string denoting the group object
     */
  
    protected static final String GROUP = "Group";

    /**
     * A string denoting the user object
     */
    protected static final String USER = "User";

    /**
     * A string denoting the administrator object
     */
  
    protected static final String ADMINISTRATOR = "Administrator";
    
   
   
 
    /**
     * 
     */
    private static final long serialVersionUID = -1148868718212204862L;

    /**
     * The emailAddress.
     */
    private String emailAddress = null;

    /**
     * The full name.
     */
    private String firstName = null;

    /**
     * The last name.
     */
    private String lastName = null;

    /**
     * The username.
     */
    private String userName = null;

    /**
     * Return the first name of this member.
     * 
     * @return String The first name of this member.
     */
    public String getFirstName() {
        return (this.firstName);
    }

    /**
     * Set the first name of this member.
     * 
     * @param firstName The new first name of this member.
     */
    public void setFirstName(String firstName) {

        if((firstName != null) && (firstName.trim().length() > 1)){
            String l = firstName.substring(0, 1);
            String y = firstName.substring(1);
            this.firstName = l.toUpperCase() + y;
        }
        else{
            this.firstName = null;
        }
    }

    /**
     * Gets the last name of this member.
     * 
     * @return the last name of this member
     */
    public String getLastName() {

        return (this.lastName);
    }

    /**
     * Sets the last name of this member.
     * 
     * @param lastName The new last name of this member.
     */
    public void setLastName(String lastName) {
        if((lastName != null) && (lastName.trim().length() > 1)){
            String l = lastName.substring(0, 1);
            String y = lastName.substring(1);
            this.lastName = l.toUpperCase() + y;
        }
        else
            this.lastName = lastName;

    }

    /**
     * Gets the user name of this member.
     * 
     * @return The user name of this member.
     */
    public String getUserName() {
        return (this.userName);
    }

    /**
     * Sets the user name of this member.
     * 
     * @param userName The new user name of this member.
     */
    public void setUserName(String userName) {
        this.userName = userName;
        setName(userName);
    }

    /**
     * Gets the email address of this member..
     * 
     * @return The email address of this member.
     */
    public String getEmailAddress() {
        return (this.emailAddress);
    }

    /**
     * Sets the new email address of this member.
     * 
     * @param emailAddress The new email address of this member.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

   

}
