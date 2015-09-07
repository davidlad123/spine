/**
 * AccountSecrets.java
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

import java.io.Serializable;
import java.util.Date;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;

/**
 * AccountSecrets encapsulates various secret words preset by the user which can be reset by either the user or an administrator. This implementation uses the following secret words:
 * <ul type="disc">
 * <li>mothersMaidenName </li>
 * <li>lastSchoolAttended </li>
 * <li>specialDate </li>
 * <li>specialWord </li>
 * <li>specialPlace </li>
 * <li>password </li>
 * <li>password reminder </li>
 * <li>pin number </li>
 * </ul>
 * All the secret words apart from the pin number default to a zero length string while the pin number defaults to <code>0000</code>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class AccountSecrets implements AccountSecretsConfig, Serializable {

    /**
     * The id of this object
     */
    private long id = 0;

    /**
     * The serialVersionUID of this object
     */
    private static final long serialVersionUID = -7167341586809355154L;

    /**
     * Users mother's maiden name
     */
    private String mothersMaidenName = "";

    /**
     * Users last attended school
     */
    private String lastSchoolAttended = "";

    /**
     * A date special to the user
     */
    private Date specialDate;

    /**
     * A word special to the user
     */

    private String specialWord = "";

    /**
     * A place special to ther user
     */

    private String specialPlace = "";

    /**
     * The password.
     */
    private String password1 = "";

    /**
     * The password to compare with.
     */
    private String password2 = "";

    /**
     * The password reminder
     */
    private String passwordReminder = "";

    /**
     * The pin number for this account
     */
    private String pinNumber = "0000";

    /**
     * Public Constructor
     */
    public AccountSecrets() {
        super();
        this.id = Universal.getUniqueId();
    }

    /**
     * gets the last school attended
     * 
     * @return The lastSchoolAttended
     */
    public String getLastSchoolAttended() {
        return lastSchoolAttended;
    }

    /**
     * Sets the last school attended
     * 
     * @param lastSchoolAttended the lastSchoolAttended to set
     */
    public void setLastSchoolAttended(String lastSchoolAttended) {
        this.lastSchoolAttended = lastSchoolAttended;
    }

    /**
     * Gets the mothers maiden name
     * 
     * @return the mothersMaidenName
     */
    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    /**
     * Sets the mothers maiden name
     * 
     * @param mothersMaidenName the mothersMaidenName to set
     */
    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    /**
     * Gets the special date
     * 
     * @return the specialDate
     */
    public Date getSpecialDate() {
        if(specialDate == null){
            specialDate = new Date();
        }
        return specialDate;
    }

    /**
     * Sets the special date
     * 
     * @param specialDate the specialDate to set
     */
    public void setSpecialDate(Date specialDate) {
        this.specialDate = specialDate;
    }

    /**
     * Gets the special place
     * 
     * @return the specialPlace
     */
    public String getSpecialPlace() {
        return specialPlace;
    }

    /**
     * Sets the special place
     * 
     * @param specialPlace the specialPlace to set
     */
    public void setSpecialPlace(String specialPlace) {
        this.specialPlace = specialPlace;
    }

    /**
     * Gets the special word
     * 
     * @return the specialWord
     */
    public String getSpecialWord() {
        return specialWord;
    }

    /**
     * Sets the special word
     * 
     * @param specialWord the specialWord to set
     */
    public void setSpecialWord(String specialWord) {
        this.specialWord = specialWord;
    }

    /**
     * Return the password of this member.
     * 
     * @return String The password of this member.
     */
    public String getPassword1() {
        return (this.password1);
    }

    /**
     * Set the new password of this member.
     * 
     * @param password1 The new password of this member.
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * Gets the comparative/old password of this member.
     * 
     * @return String The password of this member.
     */
    public String getPassword2() {
        return (this.password2);
    }

    /**
     * Sets the comparative/old password of this member.
     * 
     * @param password2 The new password of this member.
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * Gets the password reminder
     * 
     * @return The password reminder
     */
    public String getPasswordReminder() {
        return passwordReminder;
    }

    /**
     * Sets the password reminder
     * 
     * @param passwordReminder the passwordReminder to set
     */
    public void setPasswordReminder(String passwordReminder) {
        this.passwordReminder = passwordReminder;
    }

    /**
     * Sets the pin number for this user
     * 
     * @param pinNumber The pin number to set
     * @throws SpineException If there are more than 4 numbers or the pinnumber cannot be parsed into a number
     */
    public void setPinNumber(String pinNumber) throws SpineException {
        char[] cha = pinNumber.trim().toCharArray();
        if(cha.length > 4){ throw new SpineException(); }
        for (int i = 0; i < cha.length; i++){
            try{
                Integer.parseInt(String.valueOf(cha[i]));
            }
            catch (NumberFormatException e){
                throw new SpineException(e.getMessage());
            }

        }
        this.pinNumber = pinNumber;

    }

    /**
     * Gets the pin number for this user
     * 
     * @return the pinNumber
     */
    public String getPinNumber() {
        return this.pinNumber;
    }

    /**
     * Gets the special date as a String
     * 
     * @return Returns the specialDate.
     */
    public String getSpecialDateString() {
        return Universal.getCalenderDateString(this.specialDate);

    }

    /**
     * Sets the specialDate using a String representation
     * 
     * @param specialDateString
     */
    public void setSpecialDateString(String specialDateString) {

        this.specialDate = Universal.getCalenderDate(specialDateString);

    }

    /**
     * Gets the id
     * 
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id
     * 
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

}
