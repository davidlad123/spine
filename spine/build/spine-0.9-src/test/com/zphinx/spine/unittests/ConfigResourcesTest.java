/**
 * ConfigResourcesTest.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
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
 *          Created: Sep 26, 2007 5:37:54 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigResourcesTest extends TestCase {
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
