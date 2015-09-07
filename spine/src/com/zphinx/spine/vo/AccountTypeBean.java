/**
 * AccountTypeBean.java
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

package com.zphinx.spine.vo;

import java.io.Serializable;

import com.zphinx.spine.members.MemberAccount;

/**
 * AccountTypeBean stores AccountTypes by referencing both the previous and the present value of the AccountType held by a user of this system.<br/> The types of the account are derived from MemberAccount.
 * 
 * @author David Ladapo
 * @version $1.0
 * @see MemberAccount
 *      <p>
 *      
 *      Copyright &copy;Zphinx Software Solutions
 *      </p>
 */
public class AccountTypeBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1999029191404184145L;

    /**
     * The previous value of the AccountType for a user
     */
    private int previousType = 0;

    /**
     * The present value of the AccountType for a user
     */
    private int presentType = 0;

    /**
     * Public Constructor
     */
    public AccountTypeBean() {
        super();
    }

    /**
     * Constructor which accepts the present type of the account as a parameter
     * 
     * @param presentType The present type of the account
     */
    public AccountTypeBean(int presentType) {
        super();
        this.previousType = presentType;
        this.presentType = presentType;
    }

    /**
     * Constructor which accepts the present and previous values of this account type
     * 
     * @param previousType The previous type of this account normally in specified in MemberAccount
     * @param presentType The present type of the account normally in specified in MemberAccount
     */
    public AccountTypeBean(int previousType, int presentType) {
        super();
        this.previousType = previousType;
        this.presentType = presentType;
    }

    /**
     * Gets the presentType of this account
     * 
     * @return the presentType
     */
    public int getPresentType() {
        return presentType;
    }

    /**
     * Sets the presentType of this account
     * 
     * @param presentType the presentType to set
     */
    public void setPresentType(int presentType) {
        this.presentType = presentType;
    }

    /**
     * Gets the previousType of this account
     * 
     * @return the previousType
     */
    public int getPreviousType() {
        return previousType;
    }

    /**
     * Sets the previousType of this account
     * 
     * @param previousType the previousType to set
     */
    public void setPreviousType(int previousType) {
        this.previousType = previousType;
    }

}
