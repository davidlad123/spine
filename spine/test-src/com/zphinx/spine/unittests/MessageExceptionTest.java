/**
 * MessageExceptionTest.java
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

package com.zphinx.spine.unittests;

import java.util.ArrayList;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineMessageException;
import com.zphinx.spine.message.DisplayError;
import com.zphinx.spine.message.DisplayMessage;
import com.zphinx.spine.message.DisplayMessages;
import com.zphinx.spine.message.MessageConfig;
import com.zphinx.spine.resources.ConfigResources;

/**
 * MessageExceptionTest is used to test exceptions which accept a locale instance
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MessageExceptionTest extends TestCase {

    /**
     * 
     */
    private ConfigResources cr = null;

    private DisplayMessages dm = null;

    private ArrayList displayMessageList = null;

    private ArrayList displayErrorList = null;

    /**
     * 
     */
    private static Logger logger = Universal.getLogger(DisplayMessagesTest.class.getName());

    /**
     * Public Constructor
     * 
     * @param arg0
     */
    public MessageExceptionTest(String arg0) {
        super(arg0);
    }

    /**
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();
        // mro = MessageReadingObserver.getInstance(getDataSource());
        MessageConfig.setMessageResourceFile("com.zphinx.spine.resources.ConfigurationResources");
        MessageConfig.setSiteMessages("com.zphinx.spine.resources.UtilMessages");
        MessageConfig.setSiteMessages("com.zphinx.spine.resources.ConfigurationResources");
        cr = ConfigResources.getInstance(Locale.ENGLISH);
        dm = new DisplayMessages(Locale.ENGLISH);

        displayMessageList = new ArrayList();
        displayErrorList = new ArrayList();

        for (int i = 0; i < 3; i++){
            DisplayMessage ds = new DisplayMessage("test.key." + (i + 1), getPresentObject(i, null));
            displayMessageList.add(ds);
        }

        for (int j = 0; j < 3; j++){
            DisplayError de = new DisplayError(j, "test.key." + (j + 1), getPresentObject(j, null));
            displayErrorList.add(de);
        }

    }

    /**
     * Gets the object to use for an iteration
     * 
     * @param i The index of the iteration
     * @param arguments The object Array to be created
     * @return The Object Array
     */
    private Object[] getPresentObject(int i, Object[] arguments) {
        if(i == 0){
            arguments = new Object[] {};
        }
        else if(i == 1){
            arguments = new Object[] { "showing value for key " + i + ": " };
        }
        else if(i == 2){
            arguments = new Object[] { "showing value for key " + i + ": ", ": added another parameter" };
        }
        return arguments;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(MessageExceptionTest.class);
    }

    /**
     * @throws Exception
     */
    public void testConstructors1() throws Exception {
        try{
            cons1();
        }
        catch (SpineMessageException e){
            logger.debug("The error is: " + e.getMessage());
        }
    }

    /**
     * @throws Exception
     */
    public void testConstructors2() throws Exception {

        try{
            cons2();
        }
        catch (SpineMessageException e){
            logger.debug("The error is: " + e.getMessage());
        }
    }

    /**
     * @throws Exception
     */
    public void testConstructors3() throws Exception {

        try{
            cons3();
        }
        catch (SpineMessageException e){
            logger.debug("The error is: " + e.getMessage());
        }
    }

    /**
     * @throws Exception
     */
    public void testPrintStackTrace() throws Exception {
        try{
            checkException();
        }
        catch (SpineMessageException e){
            e.printStackTrace();
        }

    }

    /**
     * @throws Exception
     */
    public void testFillStackTrace() throws Exception {
        try{
            checkException();
        }
        catch (SpineMessageException e){
            e.fillInStackTrace();
        }

    }

    /**
     * @throws Exception
     */
    public void testGetMessage() throws Exception {
        try{
            checkException();
        }
        catch (SpineMessageException e){
            logger.debug("The error is: " + e.getMessage(Locale.FRENCH));
            logger.debug("The error is: " + e.getMessage());
        }
    }

    /**
     * @throws Exception
     */
    public void testGetLocalizedMessage() throws Exception {
        try{
            checkException();
        }
        catch (SpineMessageException e){
            logger.debug("The error is: " + e.getLocalizedMessage(Locale.FRENCH));
            logger.debug("The error is: " + e.getLocalizedMessage());
        }

    }

    /**
     * @throws Exception
     */
    public void testThrowsClause() throws Exception {
        checkGetMessage();

    }

    /**
     * 
     */
    private void checkGetMessage() {
        try{
            checkException();
        }
        catch (SpineMessageException e){
            logger.debug("The error is: " + e.getMessage());
        }
    }

    /**
     * @throws Exception
     */
    public void testMissingResource() throws Exception {
        try{
            checkException2();
        }
        catch (SpineMessageException e){
            logger.debug("The error is: " + e.getMessage());
        }
    }

    /**
     * 
     *
     */

    private void checkException() throws SpineMessageException {
        try{
            int i = Integer.parseInt("sajs=jjsjs.skjsk");
        }
        catch (NumberFormatException e){
            // throw new SpineMessageException("test.key.2",new String[]{e.getMessage()});
            throw new SpineMessageException("test.key.1");
        }

    }

    /**
     * 
     *
     */

    private void checkException2() throws SpineMessageException {
        try{
            int i = Integer.parseInt("sajs=jjsjs.skjsk");
        }
        catch (NumberFormatException e){
            // throw new SpineMessageException("test.key.2",new String[]{e.getMessage()});
            throw new SpineMessageException(e.getMessage());
        }

    }

    /**
     * @throws SpineMessageException
     */
    private void cons1() throws SpineMessageException {
        try{
            int i = Integer.parseInt("sconsone.df");
        }
        catch (NumberFormatException e){
            throw new SpineMessageException("test.key.2", new String[] { e.getMessage() });

        }

    }

    /**
     * @throws SpineMessageException
     */
    private void cons2() throws SpineMessageException {
        try{
            int i = Integer.parseInt("sconstwo.df");
        }
        catch (NumberFormatException e){
            throw new SpineMessageException("test.key.2", new String[] { e.getMessage() });

        }

    }

    /**
     * @throws SpineMessageException
     */
    private void cons3() throws SpineMessageException {
        try{
            int i = Integer.parseInt("sconsthree.df");
        }
        catch (NumberFormatException e){
            throw new SpineMessageException("test.key.1");

        }
    }

}