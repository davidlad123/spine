/**
 * AllTests.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.unittests;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * 
 * 
 * AllTests serves a testsuite for all the tests in this package
 * @author David Ladapo
 * @version  $1.0
 * 
 * <p>Created: May 8, 2008 1:33:27 PM<br>
 * Copyright &copy;Zphinx Software Solutions</p>
 * 
 *
 */
public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.zphinx.spine.unittests");
        // $JUnit-BEGIN$
        suite.addTestSuite(ConfigReaderTest.class);
        suite.addTestSuite(ApplicationConfigurationTest.class);
        suite.addTestSuite(ApplicationConfigurationTest2.class);
        suite.addTestSuite(ConfigResourcesTest.class);
        suite.addTestSuite(ConfigurationProcessorTest.class);
        suite.addTestSuite(ConfigurationTest.class);
        suite.addTestSuite(FileProxyTest.class);
        suite.addTestSuite(MessageExceptionTest.class);
        suite.addTestSuite(DisplayMessagesTest.class);
        suite.addTestSuite(MultiViewConfigurationTest.class);
     
        suite.addTestSuite(TestSecurity.class);

        // $JUnit-END$
        return suite;
    }

}
