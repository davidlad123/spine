/**
 *
 * User.java
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

import java.util.Date;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.security.SpinePermission;
import com.zphinx.spine.vo.AccountTypeBean;

/**
 * A User object represents a registered user within the spine framework. It stores all the details of a user within the spine framework and provides implementation methods for the relevant activities taken by this user.
 * 
 * @see Member
 * @see Administrator
 * @see PrincipalActions
 * @author David Ladapo
 * @version $Revision: 1.1 $ $Date: 2008/06/12 22:35:41 $
 *          <p>
 *          Created Sun Oct 5 14:29:10 BST 2003<br>
 *          Copyright &copy; Zphinx software solutions
 *          </p>
 */

public class User extends Member implements PrincipalActions {

    /**
     * The name for the account details object
     */
    protected static final String ACCOUNT_DETAILS = "Account Details";

  

    /**
     * The serial version uid of this object
     */
    private static final long serialVersionUID = -4969486510695826911L;

    /**
     * The account associated with this member
     */
    private AccountDetails accountDetails = null;

    /**
     * Constructor for object
     */
    public User() {
        super();
        setId(Universal.getUniqueId());
        setName(USER);
        this.accountDetails = new AccountDetails();

        accountDetails.setId(Universal.getUniqueId());
        accountDetails.setName(ACCOUNT_DETAILS);
        accountDetails.setActivationCode(null);
        try{
            accountDetails.changeDetails(new AccountTypeBean(AccountDetails.ACCOUNT_THREE_DAYS), 0);

        }
        catch (SpineApplicationException e){
            e.printStackTrace();
        }
        accountDetails.setAccountCreationDate(new Date());
        accountDetails.setModifiedDate(new Date());

    }

    /**
     * Get the type of member object
     * 
     * @return Member Return this object
     */
    public Member getPrincipal() {
        return this;
    }

    /**
     * Implementation of getPermission in Member, returns the SpinePermission Object associated with this Object.Uses MemberPermissionFactory.getMemberPermission to create the appropriate SpinePermission
     * 
     * @return MemberPermission The SpinePermission object associated with this user
     */

    public SpinePermission getPermission() {

        MemberPermissionFactory pf = new MemberPermissionFactory();
        return pf.getMemberPermission();

    }

    /**
     * Gets this Users account details
     * 
     * @return Returns the accountDetails.
     */
    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    /**
     * Sets this Users account details
     * 
     * @param accountDetails The accountDetails to set.
     */
    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    /**
     * Sets the userName into the name field of the identifier
     */
    public void setUserName(String s) {
        super.setUserName(s);
        setName(s);
    }

    
}
