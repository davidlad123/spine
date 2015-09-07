/**
 *
 * MemberPermission.java
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

package com.zphinx.spine.security;

import java.io.Serializable;

/**
 * A MemberPermission object encapsulates the logic behind the permission system which dictates the properties of principals and roles registered within the spine framework.
 * 
 * @see com.zphinx.spine.members.Group
 * @see com.zphinx.spine.members.User
 * @see com.zphinx.spine.members.Administrator
 * @see com.zphinx.spine.members.Application
 * @author David Ladapo
 * @version $Revision: 1.9 $ $Date: 2008/06/12 22:35:56 $
 *          <p>
 *          copyright &copy; Zphinx Software Solutions
 *          </p>
 */

public class MemberPermission extends SpinePermission implements Serializable {

    /**
     * The serial uid version of this object
     */
    private static final long serialVersionUID = 3448322162557165654L;

    /**
     * The Admin flag.
     */
    private boolean isAdmin = false;

    /**
     * The user flag.
     */
    private boolean isUser = false;

    /**
     * The group flag
     */
    private boolean isSuperUser = false;

    /**
     * Default Constructor
     */
    public MemberPermission() {
        super("member");
    }

    /**
     * Public constructor for the MemberPermission object
     * 
     * @param memberType The type of member whose permission is been instantiated
     */
    public MemberPermission(int memberType) {
        super("member");
        switch(memberType){
            case PERMISSION_ADMINISTRATOR:
                this.isAdmin = true;
                break;

            case PERMISSION_USER:
                this.isUser = true;
                break;

            case PERMISSION_GROUP:
                this.isSuperUser = true;
                break;
            default:

                break;
        }

    }

    /**
     * Return the maintenance isAdmin to be taken by this MemberPermission object.
     * 
     * @return boolean The maintenance isAdmin to be taken by this MemberPermission object.
     */
    public boolean isAdmin() {
        return (this.isAdmin);
    }

    /**
     * Return the userFlag of this MemberPermission.
     * 
     * @return boolean The userFlag of this MemberPermission.
     */
    public boolean isUser() {
        return (this.isUser);
    }

    /**
     * Return the group flag of this MemberPermission.
     * 
     * @return boolean The group flag of this MemberPermission.
     */
    public boolean isSuperUser() {
        return (this.isSuperUser);
    }

}
