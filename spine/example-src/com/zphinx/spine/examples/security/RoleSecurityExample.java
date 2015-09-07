/**
 * RoleSecurityExample.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * Copyright &copy;Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE ZPHINX SOFTWARE SOLUTIONS 
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

package com.zphinx.spine.examples.security;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.security.SpinePermission;

/**
 * RoleSecurityExample shows how spine security can be utilised in your application to enforce security for roles and principals
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class RoleSecurityExample extends SecurityExamplePrimer {

    /**
     * The log instance for this object
     */
    private static final transient Logger logger = Universal.getLogger(RoleSecurityExample.class.getName());

    /**
     * Public Constructor
     * 
     * @throws Exception
     */
    public RoleSecurityExample() throws Exception {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.examples.ExamplePrimer#run()
     */
    protected void run() {
        exampleAdminAccessUser();
        exampleAdminNoAccessUser();
        exampleGroupAccessAdmin();
        exampleGroupNoAccessAdmin();
        exampleGroupAccessUser();
        exampleGroupNoAccessUser();
        exampleUserNoAccessUser();

    }

    /**
     * @param args
     */

    public static void main(String[] args) {

        try{
            RoleSecurityExample sde = new RoleSecurityExample();
            sde.run();
        }
        catch (Exception e){
            logger.debug("Error: " + e.getMessage());
        }
    }

    /**
     * Example to prove that Administrator has access to user
     */
    public void exampleAdminAccessUser() {
        logger.debug("Running example for method:..................................... exampleAdminAccessUser()................");
        SpinePermission mp = exampleUser1.getPermission();
        try{
            mp.checkGuard(accessAdmin.getPermission());
            logger.debug("............................[  The accessAdmin has permission!!! ] .............. ");

        }
        catch (Exception e){
            logger.debug("............................[  The accessAdmin did not have permission!!! ] .............. ");
        }

    }

    /**
     * Example to show that Administrator has no access to user
     */
    public void exampleAdminNoAccessUser() {
        logger.debug("Running example for method:..................................... exampleAdminNoAccessUser() ................");
        SpinePermission mp = exampleUser2.getPermission();
        try{
            mp.checkGuard(noAccessAdmin.getPermission());
            logger.debug("............................[  The noAccessAdmin has permission!!! ] .............. ");
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessAdmin has no permission!!! ] .............. ");
        }

    }

    /**
     * Example to show that Administrator has access to user
     */

    public void exampleGroupAccessUser() {
        logger.debug("Running example for method:..................................... exampleGroupAccessUser() ................");
        SpinePermission mp = exampleUser1.getPermission();
        try{
            mp.checkGuard(accessGroup.getPermission());
            logger.debug("............................[  The accessGroup has permission!!! ] .............. ");

        }
        catch (Exception e){
            logger.debug("............................[  " + e.getMessage() + " ] .............. ");
        }

    }

    /**
     * Example to show that Administrator has no access to user
     */
    public void exampleGroupNoAccessUser() {
        logger.debug("Running example for method:..................................... exampleGroupNoAccessUser() ................");
        SpinePermission mp = exampleUser2.getPermission();
        try{
            mp.checkGuard(noAccessGroup.getPermission());
            logger.debug("............................[  The noAccessGroup has permission!!! ] .............. ");
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessGroup has no permission: " + e.getMessage() + " ] .............. ");
        }

    }

    /**
     * Example to show that Administrator has access to user
     */
    public void exampleGroupAccessAdmin() {
        logger.debug("Running example for method:..................................... exampleGroupAccessAdmin() ................");
        SpinePermission mp = accessAdmin.getPermission();
        try{
            mp.checkGuard(accessGroup.getPermission());
            logger.debug("............................[  The accessGroup has permission!!! ] .............. ");

        }
        catch (Exception e){
            logger.debug("............................[  The accessGroup did not have permission :" + e.getMessage() + " ] .............. ");
        }
    }

    /**
     * Example to show that Administrator has no access to user
     */
    public void exampleUserNoAccessUser() {
        logger.debug("Running example for method:..................................... exampleUserNoAccessUser() ................");
        SpinePermission mp = exampleUser2.getPermission();
        try{
            mp.checkGuard(exampleUser1.getPermission());
            logger.debug("............................[ failure : The noAccessUser has permission...!!! ] .............. ");
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessUser has no permission : " + e.getMessage() + " ] .............. ");
        }

    }

    /**
     * Example to show that Administrator has no access to user
     */
    public void exampleGroupNoAccessAdmin() {
        logger.debug("Running example for method:..................................... exampleGroupNoAccessAdmin() ................");
        SpinePermission mp = accessAdmin.getPermission();
        try{
            mp.checkGuard(noAccessGroup.getPermission());
            logger.debug("............................[ Failure:  The noAccessGroup has permission....!!! ] .............. ");
        }
        catch (Exception e){
            logger.debug("............................[  The noAccessGroup has no permission :" + e.getMessage() + " ] .............. ");
        }

    }

}
