/**
 * UniversalConstants.java
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

package com.zphinx.spine;

import com.zphinx.spine.resources.ConfigResources;

/**
 * UniversalConstants serves as a resource for constants used throughout the spine framework
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public interface UniversalConstants {

    /**
     * 
     */
    public static final String ACCOUNT_SECRETS_TYPE = "accountSecrets.type";

    /**
     * An int representing a reset statement
     */
    public static final int RESET = 1;

    /**
     * An int representing a create statement
     */
    public static final int CREATE = 1;

    /**
     * An int representing a save/insert statement
     */
    public static final int SAVE = 2;

    /**
     * An int representing a edit/select statement
     */
    public static final int EDIT = 3;

    /**
     * An int representing a view/select statement
     */
    public static final int VIEW = 4;

    /**
     * An int representing a delete/drop statement
     */
    public static final int DELETE = 5;

    /**
     * An int representing a clear parameters statement
     */
    public static final int CLEAR = 6;

    /**
     * An int representing a update statement
     */
    public static final int SAVE_UPDATE = 7;

    /**
     * The type of account secrets to use
     */
    public static final String ACCOUNT_SECRETS = ConfigResources.getString(ACCOUNT_SECRETS_TYPE);

    /**
     * An int representing the default value for the AccountDetails on creation
     */
    public static final int ACCOUNT_DETAILS_DEFAULT = 1;

    /**
     * The member is in the public domain
     */
    public static final String ZONE_PUBLIC = "public";

    /**
     * The member is in the logged in or global domain
     */
    public static final String ZONE_USER = "world";

    /**
     * The member is in the role/group domain
     */
    public static final String ZONE_GROUP = "group";

    /**
     * The member is in the admin domain
     */
    public static final String ZONE_ADMIN = "admin";


}
