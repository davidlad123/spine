/**
 * ConfigReaderTest.java
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

package com.zphinx.spine.unittests;

import java.util.Map;

import javax.sql.DataSource;

import com.zphinx.spine.exceptions.SpineMessageException;
import com.zphinx.spine.message.MessageConfig;
import com.zphinx.spine.start.ApplicationConfigurator;
import com.zphinx.spine.start.helpers.DataSourceServiceLocator;
import com.zphinx.spine.start.helpers.impl.ConfigReader;

/**
 * ConfigReaderTest
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigReaderTest extends DataResources {

    /**
     * Public Constructor
     */
    public ConfigReaderTest(final String name) {
        super(name);
    }

    /**
     * @throws Exception
     */
    public void testConfigReader() throws Exception {
        String fileName = "resources/spine-init.xml";
        final ConfigReader config = new ConfigReader();
        final Map map = config.createConfig(fileName);
        System.out.println("The map contains: " + map);
        assertNotNull(map);

    }

    /**
     * 
     * @throws Exception
     */
    public void testConfigLoaded() throws Exception{
        String fileName = "resources/spine-init.xml";
        final ConfigReader config = new ConfigReader();
        final Map map = config.createConfig(fileName);
        ApplicationConfigurator.getInstance().configure(map);
        DataSourceServiceLocator ddsl = DataSourceServiceLocator.getInstance();
        DataSource ds = ddsl.getDataSource();
        assertNotNull(ds);
        System.out.println("The datasource is: "+ds);
        DataSource dse = ddsl.getDataSource("Mysql");
        assertNotNull(dse);
        System.out.println("The datasource is: "+dse);
        DataSource dsed = ddsl.getDataSource("default");
        assertNotNull(dsed);
        System.out.println("The datasource is: "+dsed);

    }
    
    /**
     * @throws Exception
     */
    public void testCommonConfig() throws Exception {

        System.out.println("The config is: " + MessageConfig.getResourcesList());
    }

}
