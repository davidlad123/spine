/**
 * Identifier.java
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

import com.zphinx.spine.exceptions.SpineMessageException;

/**
 * Identifier represents the identification values available to all members of this system.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 19-Feb-2005 00:18:38<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class Identifier extends Identity {

    /**
     * 
     */
    private static final String INVALID_SECRET = "invalid.secret";

    /**
     * 
     */
    private static final long serialVersionUID = 564111233527610124L;

    /**
     * The id of the account secret associated with this Identifier
     */
    private long accountSecretId = 0;
    
    /**
     * Special words and characters used to identify this account holder
     */
    private AccountSecrets accountSecrets = null;

    /**
     * Gets the account secrets of this member
     * 
     * @return the accountSecrets
     */
    public AccountSecrets getAccountSecrets() {
        if(accountSecrets == null){
            this.accountSecrets = new AccountSecrets();
            this.accountSecretId = accountSecrets.getId();
           
        }
        return accountSecrets;
    }

    /**
     * Sets the account secrets of this member. 
     * 
     * @param accountSecrets the accountSecrets to set
     * @throws SpineMessageException 
     */
    public void setAccountSecrets(AccountSecrets accountSecrets) throws SpineMessageException {
        if((accountSecrets != null) && (accountSecrets.getId() == this.accountSecretId)){
            this.accountSecrets = accountSecrets;
        }
        else if(this.accountSecrets == null) {
            this.accountSecrets = accountSecrets;
            this.accountSecretId = accountSecrets.getId();
        }
        else throw new SpineMessageException(INVALID_SECRET);
    }

}
