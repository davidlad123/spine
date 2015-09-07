/**
 * MemberAccount.java
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

import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.vo.AccountTypeBean;

/**
 * MemberAccount defines common variables and methods used to create differing account types
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 15-Feb-2005 12:16:25<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public interface MemberAccount {
    /**
     * One day account type
     */
    public static final int ACCOUNT_ONE_DAY = 0;

    /**
     * 3 day account type
     */
    public static final int ACCOUNT_THREE_DAYS = 1;

    /**
     * 1 week account type
     */
    public static final int ACCOUNT_ONE_WEEK = 2;

    /**
     * 30 day account type
     */
    public static final int ACCOUNT_30_DAYS = 3;

    /**
     * 1 year account type
     */
    public static final int ACCOUNT_ONE_YEAR = 4;

    /**
     * A variable account type
     */
    public static final int ACCOUNT_VARIABLE = 5;

    /**
     * Unlimited account type
     */
    public static final int ACCOUNT_NO_EXPIRE = 6;

    /**
     * Gets the expiration dtae of this account
     * 
     * @return Returns the accountExpirationDate.
     */
    public Date getAccountExpirationDate();

    /**
     * Sets the expiration dtae of this account
     * 
     * @param accountExpirationDate The accountExpirationDate to set.
     * @throws SpineApplicationException
     */
    public void setAccountExpirationDate(Date accountExpirationDate) throws SpineApplicationException;

    /**
     * Gets the id of this account
     * 
     * @return Returns the accountId.
     */
    public long getId();

    /**
     * Sets the id of this account
     * 
     * @param accountId The accountId to set.
     */
    public void setId(long accountId);

    /**
     * Gets the type of account we are creating
     * 
     * @return Returns the accountType.
     */
    public AccountTypeBean getAccountType();

    /**
     * Sets the type of account we are creating
     * 
     * @param accountType The accountType to set.
     */
    public void setAccountType(AccountTypeBean accountType);

    /**
     * Returns true if this object has been fully initialized
     * 
     * @return Returns the initialized.
     */
    public boolean isInitialized();

    /**
     * Amend the properties of this account with the given parameters
     * 
     * @param accountType The type of account details object we are creating
     * @param expire The time in milliseconds when this account will expire
     * @throws SpineApplicationException
     */
    public void changeDetails(AccountTypeBean accountType, long expire) throws SpineApplicationException;

    /**
     * Checks for changes in the users accountType
     * 
     * @return True if the accountType is been changed/amended
     */
    public boolean isAccountTypeChanged();

}
