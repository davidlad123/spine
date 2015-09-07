/**
 * ConfigResourcesTest.java
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
import org.apache.log4j.Logger;

import junit.framework.TestCase;

import com.zphinx.spine.Universal;
import com.zphinx.spine.message.MessageConfig;
import com.zphinx.spine.resources.ConfigResources;

/**
 * ConfigResourcesTest is used to test the retrieval of localized messages available to the application from ConfigResources
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigResourcesTest extends DataResources {
    /**
     * 
     */
    ConfigResources cr = null;

    /**
     * 
     */
    ArrayList mEnglish = null;

    /**
     * 
     */
    private static Logger logger = Universal.getLogger(ConfigResourcesTest.class.getName());

    /**
     * Public Constructor
     * 
     * @param name
     */
    public ConfigResourcesTest(String name) {
        super(name);

    }

    /**
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();
        // mro = MessageReadingObserver.getInstance(getDataSource());
        MessageConfig.setMessageResourceFile("com.zphinx.spine.resources.ConfigurationResources");
        MessageConfig.setSiteMessages("com.zphinx.spine.resources.UtilMessages");
        MessageConfig.setMessageResourceFile("com.zphinx.spine.resources.SpineMessages");
        cr = ConfigResources.getInstance(Locale.ENGLISH);
        mEnglish = new ArrayList(3);
        mEnglish.add("test.key.1");
        mEnglish.add("test.key.2");
        mEnglish.add("test.key.3");

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(ConfigResourcesTest.class);
    }

    /**
     * Tests with a known provider
     * 
     * @throws Exception
     */
    public void testGetTextProviderKnownKeyExist() throws Exception {
        logger.debug("Running testGetTextProviderKnownKeyExist . . . . ");
        String providerId = "provider-1";
        for (int i = 0; i < mEnglish.size(); i++){
            String key = ((String) mEnglish.get(i));
            Object[] arguments = getPresentObject(i, null);
            String s = cr.getString(providerId, key, arguments, Locale.ENGLISH);
            printTestResults(s);

        }

    }

    /**
     * Tests with a known provider
     * 
     * @throws Exception
     */
    public void testGetTextProviderNotAvailable() throws Exception {
        logger.debug("Running testGetTextProviderNotAvailable . . . . ");
        String providerId = "provider-11";
        for (int i = 0; i < mEnglish.size(); i++){
            String key = ((String) mEnglish.get(i));
            Object[] arguments = getPresentObject(i, null);
            String s = cr.getString(providerId, key, arguments, Locale.ENGLISH);
            printTestResults(s);

        }
    }

    /**
     * Tests with a known provider
     * 
     * @throws Exception
     */
    public void testGetTextProviderKnownKeyNotExist() throws Exception {
        logger.debug("Running testGetTextProviderKnownKeyNotExist.... ..  .");
        String providerId = "provider-1";
        String key = "kererekeke";
        Object[] arguments = getPresentObject(2, null);
        String s = cr.getString(providerId, key, arguments, Locale.ENGLISH);
        printTestResults(s);
        key = "kererekekejikeke";
        arguments = getPresentObject(3, null);
        s = cr.getString(providerId, key, arguments, Locale.ENGLISH);
        printTestResults(s);

    }

    /**
     * Print the test results and runs all comparative tests
     * 
     * @param s The string obtained from the get string method calls
     */
    private void printTestResults(String s) {

        int l = s.trim().length();
        logger.debug("The text returned is: " + s + "\n\r");
        assertNotNull("Get Text returned: " + s, s);
        assertTrue("The lenght of the string is: " + l, l > 0);
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
     * Tests with an unknown provider
     * 
     * @throws Exception
     */
    public void testGetTextProviderUnknown() throws Exception {
        logger.debug("Running testGetTextProviderUnknown . . . . . . ");
        simpleExtract(Locale.ENGLISH);

    }

    /**
     * Tests with a known locale
     * 
     * @throws Exception
     */
    public void testGetTextWithLocale() throws Exception {
        logger.debug("Running testGetTextWithLocale . . . . . .");
        simpleExtract(Locale.FRENCH);

    }

    /**
     * Runs the test for text with or without Locale
     * 
     * @param locale
     */
    private void simpleExtract(Locale locale) {
        for (int i = 0; i < mEnglish.size(); i++){
            String key = ((String) mEnglish.get(i));
            Object[] arguments = getPresentObject(i, null);
            String s = cr.getString(key, arguments, locale);
            printTestResults(s);

        }
    }

    /**
     * Tests with a known locale
     * 
     * @throws Exception
     */
    public void testGetTextNoLocale() throws Exception {
        logger.debug("Running testGetTextNoLocale . . . . . .. ");
        simpleExtract(null);

    }

}
