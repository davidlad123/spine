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
 * ObjectSecurityExample shows how spine security can be utilised in your application to enforce security for objects which extend the SpineBean class
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ObjectSecurityExample extends SecurityExamplePrimer {

    /**
     * The log instance for this object
     */
    private static final transient Logger logger = Universal.getLogger(ObjectSecurityExample.class.getName());

    /**
     * Public Constructor
     * 
     * @throws Exception
     */
    public ObjectSecurityExample() throws Exception {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.examples.ExamplePrimer#run()
     */
    protected void run() {
        exampleAdminNoAccessSpineBean();
        exampleUserAccessSpineBean();
        exampleUserNoAccessSpineBean();
        exampleGroupAccessSpineBean();
        exampleGroupNoAccessSpineBean();
    }

    /**
     * @param args
     */

    public static void main(String[] args) {

        try{
            ObjectSecurityExample sde = new ObjectSecurityExample();
            sde.load();
            sde.run();
        }
        catch (Exception e){
            logger.debug("Error: " + e.getMessage());
        }
    }

    /**
     * Test method to example that Administrator has no access to user
     */
    public void exampleAdminNoAccessSpineBean() {
        logger.debug("Running example for method:..................................... exampleAdminNoAccessSpineBean() ................");
        SpinePermission permission = noAccessAdmin.getPermission();
        SpinePermission mp = exampleBean1.getPermission();
        try{

            checkPermission755(permission, mp);

            // ---------------------------------------------------------------------------------------

            checkPermissionLevel700(permission);

            // ----------------------------------------------------------------------------------------------------

            checkPermissionLevel775();

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
        }

    }

    /**
     * Test method to example that Administrator has access to user
     */
    public void exampleGroupAccessSpineBean() {
        logger.debug("Running example for method:..................................... exampleGroupAccessSpineBean() ................");
        SpinePermission permission = accessGroup.getPermission();

        try{
            logger.debug("............................[checking 755 on bean  ] ....................");
            checkReadWriteExecute(permission, exampleBean1.getPermission(), "Group", new boolean[] { true, true, true });

            logger.debug("............................[ checking 775 on bean ] ....................");
            checkReadWriteExecute(permission, exampleBean3.getPermission(), "Group", new boolean[] { true, true, true });

            logger.debug("............................[ checking 700 on bean ] ....................");
            checkReadWriteExecute(permission, exampleBean2.getPermission(), "Group", new boolean[] { true, true, true });

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessGroup has no permission :" + e.getMessage() + " ] .............. ");
        }
    }

    /**
     * Test method to example that Administrator has no access to user
     */
    public void exampleGroupNoAccessSpineBean() {
        logger.debug("Running example for method:..................................... exampleGroupNoAccessSpineBean()................");
        SpinePermission permission = noAccessGroup.getPermission();
        SpinePermission mp = exampleBean1.getPermission();
        try{

            checkPermission755(permission, mp);

            // ---------------------------------------------------------------------------------------

            checkPermissionLevel700(permission);

            // ----------------------------------------------------------------------------------------------------

            checkPermissionLevel775();

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
        }

    }

    /**
     * Test method to example that Administrator has access to user
     */
    public void exampleUserAccessSpineBean() {
        logger.debug("Running example for method:..................................... exampleUserAccessSpineBean()................");

        SpinePermission permission = exampleUser3.getPermission();
        try{
            logger.debug("............................[checking 755 on bean using a group member ] ....................");
            checkReadWriteExecute(exampleUser3.getPermission(), exampleBean1.getPermission(), "User", new boolean[] { true, false, true });

            logger.debug("............................[ checking 775 on bean using a group member] ....................");
            checkReadWriteExecute(exampleUser3.getPermission(), exampleBean3.getPermission(), "User", new boolean[] { true, true, true });

            logger.debug("............................[ checking 700 on bean using a group member] ....................");
            checkReadWriteExecute(exampleUser3.getPermission(), exampleBean2.getPermission(), "User", new boolean[] { false, false, false });

            logger.debug("............................[ checking 700 on bean using the member] ....................");
            checkReadWriteExecute(exampleUser1.getPermission(), exampleBean2.getPermission(), "User", new boolean[] { true, true, true });

            logger.debug("............................[ checking 700 on bean using a public member] ....................");
            checkReadWriteExecute(exampleUser2.getPermission(), exampleBean2.getPermission(), "User", new boolean[] { false, false, false });

            logger.debug("............................[ checking 755 on bean using a public member] ....................");
            checkReadWriteExecute(exampleUser2.getPermission(), exampleBean1.getPermission(), "User", new boolean[] { true, false, true });

            logger.debug("............................[ checking 777 on bean using a public member] ....................");
            checkReadWriteExecute(exampleUser2.getPermission(), exampleBean4.getPermission(), "User", new boolean[] { true, true, true });

            logger.debug("............................[ checking 775 on bean using a public member] ....................");
            checkReadWriteExecute(exampleUser2.getPermission(), exampleBean3.getPermission(), "User", new boolean[] { true, false, true });

            // checkPermissionLevel700(permission);

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
        }
    }

    /**
     * Test method to example that Administrator has no access to user
     */
    public void exampleUserNoAccessSpineBean() {
        logger.debug("Running example for method:..................................... exampleUserNoAccessSpineBean() ................");

        SpinePermission permission = exampleUser2.getPermission();
        SpinePermission mp = exampleBean1.getPermission();
        try{

            checkPermission755(permission, mp);

            // ---------------------------------------------------------------------------------------

            checkPermissionLevel700(permission);

            // ----------------------------------------------------------------------------------------------------

            checkPermissionLevel775();

        }
        catch (Exception e){
            logger.debug("............................[  Failure:   The accessUser has no permission :" + e.getMessage() + " ] .............. ");
        }

    }

    /**
     * @param permission
     * @param mp
     */
    private void checkPermission755(SpinePermission permission, SpinePermission mp) {
        logger.debug("............................[checking 755 on bean  ] ....................");

        boolean b = mp.getExecuteFlag(permission);

        if(b){
            logger.debug("............................[  The noAccessUser has execute permission....!!! ] .............. ");
        }
        b = mp.getReadFlag(permission);
        if(b){
            logger.debug("............................[  The noAccessUser has read permission....!!! ] .............. ");
        }

        b = mp.getWriteFlag(permission);
        if(!b){
            logger.debug("............................[  The noAccessUser has no write permission....!!! ] .............. ");
        }
    }

    /**
     * @param permission
     */
    private void checkPermissionLevel700(SpinePermission permission) {

        boolean b;
        logger.debug("............................[ checking 700 on bean ] ....................");
        SpinePermission mp = exampleBean2.getPermission();
        b = mp.getExecuteFlag(permission);
        if(!b){
            logger.debug("............................[  The noAccessUser has no execute permission....!!! ] .............. ");
        }

        b = mp.getReadFlag(permission);
        if(!b){
            logger.debug("............................[  The noAccessUser has no read permission....!!! ] .............. ");
        }

        b = mp.getWriteFlag(permission);
        if(!b){
            logger.debug("............................[  The noAccessUser has no write permission....!!! ] .............. ");
        }
    }

    /**
     * @param permission
     */
    private void checkPermissionLevel775() {

        boolean b;
        SpinePermission mp = exampleBean3.getPermission();
        SpinePermission permission = exampleUser4.getPermission();

        logger.debug("............................[ checking 775 on bean with user who has only public access ] ....................");
        b = mp.getExecuteFlag(permission);
        if(b){
            logger.debug("............................[  The noAccessUser has execute permission....!!! ] .............. ");
        }

        b = mp.getReadFlag(permission);

        if(b){
            logger.debug("............................[  The noAccessUser has read permission....!!! ] .............. ");
        }

        b = mp.getWriteFlag(permission);

        if(!b){
            logger.debug("............................[  The noAccessUser has no write permission....!!! ] .............. ");
        }

    }

    /**
     * Test the read write execute permission using the stated access rights
     * 
     * @param permission
     * @param mp
     * @param s
     * @param rights
     */
    private void checkReadWriteExecute(SpinePermission permission, SpinePermission mp, String s, boolean[] rights) {
        writeResults(s, rights[0], mp.getReadFlag(permission), "read");
        writeResults(s, rights[1], mp.getWriteFlag(permission), "write");
        writeResults(s, rights[2], mp.getExecuteFlag(permission), "execute");

    }

    /**
     * Writes the result of the example
     * 
     * @param s The string representing the type of principal making the call
     * @param rights The expected access rights of the principal
     * @param b The boolean result of the example.
     */
    private void writeResults(String s, boolean rights, boolean b, String permit) {
        if(rights){
            logger.debug("............................[  The access" + s + " has " + permit + " permission....!!! ] .............. ");
        }
        else{
            logger.debug("............................[  The access" + s + " has no " + permit + " permission....!!! ] .............. ");
        }
    }

}
