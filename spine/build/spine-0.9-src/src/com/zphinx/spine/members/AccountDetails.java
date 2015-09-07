/**
 * AccountDetails.java
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

import java.util.Date;
import com.zphinx.spine.Universal;
import com.zphinx.spine.UniversalConstants;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.vo.AccountTypeBean;
import com.zphinx.spine.vo.dto.SpineBean;

/**
 * AccountDetails represents the member's account details persisted within the system. Every Member/Principal can possess one of 6 account types namely
 * <UL TYPE=DISC>
 * <li>ACCOUNT_NO_EXPIRE: Account can never expire
 * <li>ACCOUNT_THREE_DAYS: A three day account
 * <li>ACCOUNT_ONE_WEEK: A one week account
 * <li>ACCOUNT_30_DAYS: A thirty day account
 * <li>ACCOUNT_ONE_YEAR: A one year account
 * <li>ACCOUNT_VARIABLE: A variable account period
 * </UL>
 * Max Caravaggio (mc@zphinx.com)
 * 
 * @version $1.0
 *          <p>
 *          Created: 14-Feb-2005 14:15:38 <br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class AccountDetails extends SpineBean implements MemberAccount {

    /**
     * The serial version uid of this object
     */
    private static final long serialVersionUID = 3737087758864381964L;

    /**
     * A flag to indicate how this detail was created
     */
    private boolean initialized = false;

    /**
     * The type of the account in question, can be any of the named types
     */
    private AccountTypeBean accountType = null;

    /**
     * The date this account will expire
     */
    private Date accountExpirationDate = null;

    /**
     * The random code which recognizes this user as having registered for activation
     */
    private String activationCode = null;

    /**
     * Default constructor - sets the account expiration period to three 3 days
     */
    public AccountDetails() {
        super();
        // preset the Account details to: AccountDetails.ACCOUNT_THREE_DAYS
        this.accountType = new AccountTypeBean(UniversalConstants.ACCOUNT_DETAILS_DEFAULT);
        try{
            initialize(Universal.getUniqueId(), this.accountType, 0);
        }
        catch (SpineApplicationException e){

            e.printStackTrace();
        }

    }

    /**
     * Constructor - Allows the developer to manually set the activation code and sets the default account expiration period to three days
     * @param activationCode The code to be associated with the activation of this account.
     */
    public AccountDetails(String activationCode) {
        super();
        setAccountCreationDate(new Date());
        this.activationCode = activationCode;
        this.accountType = new AccountTypeBean(ACCOUNT_VARIABLE);
        long accountId = Universal.getUniqueId();
        try{
            initialize(accountId, accountType, 0);
        }
        catch (SpineApplicationException e){
            e.printStackTrace();
        }

    }

    /**
     * Constructor which presets the account type and sets the default account expiration period to three days
     * 
     * @param accountId The account owners id
     * @param accountType The type of account details object we are creating
     * @throws SpineApplicationException Throws an application exception if an error occurs while initializing this AccountDetails object
     */
    public AccountDetails(long accountId, AccountTypeBean accountType) throws SpineApplicationException {
        initialize(accountId, accountType, 0);
    }

    /**
     * Constructor which presets the expiration time to a time defined above by the rules:
     * <UL TYPE=DISC>
     * <li>ACCOUNT_NO_EXPIRE: Account can never expire
     * <li>ACCOUNT_THREE_DAYS: A three day account
     * <li>ACCOUNT_ONE_WEEK: A one week account
     * <li>ACCOUNT_30_DAYS: A thirty day account
     * <li>ACCOUNT_ONE_YEAR: A one year account
     * <li>ACCOUNT_VARIABLE: A variable account period
     * </UL>
     * 
     * @param accountId The account owners id
     * @param expire The time in milliseconds when this account will expire
     * @throws SpineApplicationException
     */
    public AccountDetails(long accountId, long expire) throws SpineApplicationException {
       super();
        this.accountType = new AccountTypeBean(ACCOUNT_VARIABLE);
        initialize(accountId, accountType, expire);
    }

    /**
     * Initializes this object with the given parameters
     * 
     * @param accountId The account owners id
     * @param accountType The type of account details object we are creating
     * @param expire The time in milliseconds when this account will expire
     * @throws SpineApplicationException
     */
    private void initialize(long accountId, AccountTypeBean accountType, long expire) throws SpineApplicationException {

        setId(accountId);
        this.accountType = accountType;
        setAccountCreationDate(new Date());
        createExpirationDate(accountType, expire);

    }

    /**
     * Gets the expiration date of this account
     * 
     * @return Returns the accountExpirationDate.
     */
    public Date getAccountExpirationDate() {
        return this.accountExpirationDate;
    }

    /**
     * Sets the expiration date of this account
     * 
     * @param accountExpirationDate The accountExpirationDate to set.
     * @throws SpineApplicationException
     */
    public void setAccountExpirationDate(Date accountExpirationDate) throws SpineApplicationException {
        this.accountExpirationDate = accountExpirationDate;
        this.activationCode = Universal.getRandom(24);

    }

    /**
     * Returns a string representation of the expiration date
     * 
     * @return A string representing the expiration date
     */
    public long getExpiration() {
        long l = 0;
        if(this.accountExpirationDate != null){
            l = accountExpirationDate.getTime();
        }
        return l;

    }

    /**
     * Sets the expiration date of this account
     * 
     * @param expiration The accountExpirationDate to set.
     * @throws SpineApplicationException
     */
    public void setExpiration(long expiration) throws SpineApplicationException {

        setAccountExpirationDate(new Date(expiration));

    }

    /**
     * Gets the type of account we created
     * 
     * @return Returns the accountType.
     */
    public AccountTypeBean getAccountType() {
        return this.accountType;
    }

    /**
     * Sets the type of account we are creating
     * 
     * @param accountTypeBean The accountTypeBean to set.
     */
    public void setAccountType(AccountTypeBean accountTypeBean) {
        this.accountType = accountTypeBean;
        try{
            createExpirationDate(accountType, 0);
        }
        catch (SpineApplicationException e){

            e.printStackTrace();
        }
    }

    /**
     * Creates the account expiration date from the accountType int, will set an account expriry period to 90 days if the account type is variable and an account expiry of 0 or 1 is inputted
     * 
     * @param accountType The account type in use by this user
     * @throws SpineApplicationException
     */
    private void createExpirationDate(AccountTypeBean accountType, long expire) throws SpineApplicationException {
        Date date = null;
        long today = System.currentTimeMillis();
        long oneDay = (60 * 60 * 24 * 1000);
        if((accountType.getPresentType() == ACCOUNT_VARIABLE) && (expire < 1)){
            expire = oneDay;
        }
        else if((expire < 1) && (accountType.getPresentType() >= 0)){
            switch(accountType.getPresentType()){
                case ACCOUNT_NO_EXPIRE:
                    date = new Date(today + (oneDay * 25 * 365));
                    break;
                case ACCOUNT_THREE_DAYS:
                    date = new Date(today + (oneDay * 3));
                    break;
                case ACCOUNT_ONE_WEEK:
                    date = new Date(today + (oneDay * 7));
                    break;
                case ACCOUNT_30_DAYS:
                    date = new Date(today + (oneDay * 30));
                    break;
                case ACCOUNT_ONE_YEAR:
                    date = new Date(today + (oneDay * 365));
                    break;
                case ACCOUNT_VARIABLE:
                    date = new Date(today + expire);
                case ACCOUNT_ONE_DAY:
                    date = new Date(today + oneDay);
                default:
                    break;

            }
        }
        else{
            date = new Date(today + expire);
        }

        setAccountExpirationDate(date);
    }

    /**
     * Returns true if this object has been fully initialized
     * 
     * @return Returns the initialized.
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Sets this object to the initialized state
     * 
     * @param constructorExpiry A boolean indicating if this object has been initialized
     */
    public void setInitialized(boolean constructorExpiry) {
        this.initialized = constructorExpiry;
    }

    /**
     * Amend the properties of this object with the given parameters
     * 
     * @param accountType The type of account details object we are creating
     * @param expire The time in milliseconds when this account will expire
     * @throws SpineApplicationException
     */
    public void changeDetails(AccountTypeBean accountType, long expire) throws SpineApplicationException {
        this.accountType = accountType;
        createExpirationDate(accountType, expire);

    }

    /**
     * Gets the activation code associated with this account
     * 
     * @return Returns the activationCode.
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * Sets the activation code for this account
     * 
     * @param activationCode The activationCode to set.
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * Gets the date this account was created
     * 
     * @return Returns the accountCreationDate.
     */
    public Date getAccountCreationDate() {
        return new Date(getCreationDate());
    }

    /**
     * Sets the date this account was created
     * 
     * @param accountCreationDate The accountCreationDate to set.
     */
    public void setAccountCreationDate(Date accountCreationDate) {
        setCreationDate(accountCreationDate.getTime());
    }

    /**
     * Restores the expiration date to the parameter date.
     * 
     * @param date The expiration date to set
     */
    public void restoreDate(Date date) {
        this.accountExpirationDate = date;
    }

    /**
     * Checks for changes in the users accountType
     * 
     * @return True if the accountType is been changed/amended
     */
    public boolean isAccountTypeChanged() {
        return (this.accountType.getPreviousType() == this.accountType.getPresentType());
    }

}