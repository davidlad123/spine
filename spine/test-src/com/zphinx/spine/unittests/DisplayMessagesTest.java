/**
 * DisplayMessagesTest.java
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
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

import com.zphinx.spine.message.DisplayError;
import com.zphinx.spine.message.DisplayMessage;
import com.zphinx.spine.message.DisplayMessages;
import com.zphinx.spine.message.MessageConfig;
import com.zphinx.spine.resources.ConfigResources;

/**
 * DisplayMessagesTest tests the funtionality embedded in the message collection object DisplayMessages
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DisplayMessagesTest extends TestCase {

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
    private static Logger logger = Logger.getLogger(DisplayMessagesTest.class.getName());

    /**
     * Public Constructor
     * 
     * @param arg0
     */
    public DisplayMessagesTest(String arg0) {
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
        displayMessageList.add(new DisplayMessage("test.key-message.notavail"));

        for (int j = 0; j < 3; j++){
            DisplayError de = new DisplayError(j, "test.key." + (j + 1), getPresentObject(j, null));
            displayErrorList.add(de);
        }
        DisplayError de4 = new DisplayError(3, "test.error-key.notavail");
        displayErrorList.add(de4);
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
        junit.textui.TestRunner.run(DisplayMessagesTest.class);
    }

    /**
     * @throws Exception
     */

    public void testAddMethod() throws Exception {
        logger.log(Level.INFO, "Running testAddMethod. . . ");
        addMessages(Locale.ENGLISH);
        assertTrue(dm.containsMessages());
        assertTrue(dm.containsErrors());
    }

    /**
     * Adds messages to the displayMessages object
     */
    private void addMessages(Locale locale) {
        Iterator itm = displayMessageList.iterator();
        while (itm.hasNext()){
            dm.add("messages", (DisplayMessage) itm.next(), locale);
        }
        Iterator ite = displayErrorList.iterator();
        while (ite.hasNext()){
            dm.add("errors", (DisplayError) ite.next(), locale);
        }
    }

    /**
     * Test the contains methods
     * 
     * @throws Exception
     */

    public void testContains() throws Exception {
        logger.log(Level.INFO, "Running testContains. . . ");
        addMessages(Locale.ENGLISH);
        assertTrue(dm.messageKeySize() > 0);
        assertTrue(dm.errorKeySize() > 0);

    }

    /**
     * Test the number of keys in the Display messages object
     * 
     * @throws Exception
     */
    public void testKeySizes() throws Exception {
        logger.log(Level.INFO, "Running testKeySizes. . . ");
        addMessages(Locale.ENGLISH);
        assertTrue(dm.messageKeySize() == 1);
        assertTrue(dm.errorKeySize() == 1);
    }

    /**
     * Test getErrors
     * 
     * @throws Exception
     */

    public void testGetErrors() throws Exception {
        logger.log(Level.INFO, "Running testGetErrors. . . ");
        addMessages(Locale.ENGLISH);
        DisplayError[] dre = dm.getErrors("errors");
        assertNotNull(dre);
        assertTrue(dre.length == 4);
        for (int k = 0; k < dre.length; k++){
            assertNotNull(dre[k]);
        }

    }

    /**
     * Tests get Messages
     * 
     * @throws Exception
     */

    public void testGetMessages() throws Exception {
        logger.log(Level.INFO, "Running testGetMessages. . . ");
        addMessages(Locale.ENGLISH);
        DisplayMessage[] dme = dm.getMessages("messages");
        assertNotNull(dme);
        assertTrue(dme.length == 4);
        for (int k = 0; k < dme.length; k++){
            assertNotNull(dme[k]);
        }
    }

    /**
     * Test get all strings
     * 
     * @throws Exception
     */
    public void testGetAllStrings() throws Exception {
        logger.log(Level.INFO, "Running testGetAllStrings. . . ");
        addMessages(Locale.ENGLISH);
        String se = dm.getAllErrorString();
        String sm = dm.getAllMessageString();
        System.out.println("The errors are: " + se);
        System.out.println("The messages are: " + sm);
        assertTrue((se != null) && (se.trim().length() > 0));
        assertTrue((sm != null) && (sm.trim().length() > 0));
    }

    /**
     * Test the clear method of the DisplayMessages object
     * 
     * @throws Exception
     */
    public void testClear() throws Exception {
        logger.log(Level.INFO, "Running testClear. . . ");
        addMessages(Locale.ENGLISH);
        dm.clear();
        assertFalse(dm.containsErrors());
        assertFalse(dm.containsMessages());
        assertTrue(dm.errorKeySize() == 0);
        assertTrue(dm.messageKeySize() == 0);
        assertFalse(dm.containsKey("errors"));
        assertFalse(dm.containsKey("messages"));
    }

    /**
     * Test the selection of messages using a different Locale
     * 
     * @throws Exception
     */

    public void testLocaleMessage() throws Exception {
        logger.log(Level.INFO, "Running testLocaleMessage. . . ");
        addMessages(Locale.FRENCH);
        assertTrue(dm.containsMessages());
        assertTrue(dm.containsErrors());
    }

    /**
     * @throws Exception
     */

    public void testRemove() throws Exception {
        logger.log(Level.INFO, "Running testRemove. . . ");
        addMessages(Locale.ENGLISH);
        dm.remove("errors");
        dm.remove("messages");
        assertFalse(dm.containsErrors());
        assertFalse(dm.containsMessages());
        assertTrue(dm.errorKeySize() == 0);
        assertTrue(dm.messageKeySize() == 0);
        assertFalse(dm.containsKey("errors"));
        assertFalse(dm.containsKey("messages"));

    }

}
