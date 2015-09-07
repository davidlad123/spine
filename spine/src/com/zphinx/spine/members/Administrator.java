/**
 *
 * Administrator.java
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

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.vo.AccountTypeBean;

/**
 * The adminstrator of a group or several groups within the spine framework.
 * 
 * @see Member
 * @see User
 * @see PrincipalActions
 * @author David Ladapo (davidl@zphinx.com)
 * @version $Revision: 1.4 Date: Fri Oct 3 02:37:59 BST 2003
 *          <p>
 *          
 *          Copyright &copy; Zphinx Software Solutions
 *          </p>
 */

public class Administrator extends User {

    /**
     * 
     */
    private static final String ADMINISTRATOR = "Administrator";

    /**
     * 
     */
    private static final long serialVersionUID = -6154098109316601667L;

    /**
     * Public constructor for Administrator
     */

    public Administrator() {
        super();
        setId(Universal.getUniqueId());
        setName(ADMINISTRATOR);
        getAccountDetails().setActivationCode(null);
        try{
            this.getAccountDetails().changeDetails(new AccountTypeBean(AccountDetails.ACCOUNT_NO_EXPIRE), 0);

        }
        catch (SpineApplicationException e){
            e.printStackTrace();
        }
    }

}
