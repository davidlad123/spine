/**
 * SpineServlet.java
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

package com.zphinx.spine.start;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.zphinx.spine.start.helpers.impl.ConfigReader;

/**
 * SpineServlet is a base servlet used to kick start the spine application core. The application is capable of running without the servlet but must be initialised as a running process in a JVM. The function of this Servlet is primarily to initialize the working spine application core.
 *  <p>
 *  Client developers using other MVC frameworks are advised to integrate spine as part of their application by:
 *  <ol>
 *  <li>Using Spine servlet to initialize the the framework whilst declaring a single config file (normally spine_start_configFile.xml), SpineServlet will initialise the framework if added as an additional Servlet declaration in your web.xml </li>
 *  <li>Providing  a map which must then be passed to the {@link ApplicationConfigurator} instance which will provide the base variables needed by this framework at runtime.<br>This method will however require that
 *  a dataSource is instantiated and passed to the {@link ApplicationConfigurator} at the same time.</li>
 *  <li>{@link ConfigReader} can be used to create such a map, and it will automatically create a map containing all information needed by {@link ApplicationConfigurator} to instantiate the spine framework( DataSources will also be instantiated by {@link ApplicationConfigurator}). </li>
 * <ol>
 *  <p>To use SpineServlet, a start configuration file known as <code>startConfigFile</code> must be added to the init params tag of the declared SpineServlet.</p>
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 17, 2007 5:00:35 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpineServlet extends HttpServlet {

    /**
     * The namne of the configuration file
     */
    private static final String START_CONFIG_FILE = "startConfigFile";
    /**
     * 
     */
    private static final long serialVersionUID = -2113317678992604830L;

    

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        Map configMap = null;
        if(config.getInitParameter(START_CONFIG_FILE) != null){
            String startConfigFile = config.getInitParameter(START_CONFIG_FILE);
            startConfigFile = context.getRealPath(startConfigFile); 
            ConfigReader confReader = new ConfigReader();
            configMap = confReader.createConfig(startConfigFile);
            Iterator i = configMap.keySet().iterator();
            while(i.hasNext()) {
                String key = (String) i.next();
                if(configMap.get(key) instanceof String) {
                    String valueString = (String) configMap.get(key);    
                    validateMapContents(context, configMap,key , valueString);
                }
            }
            
        }
        else{
            Enumeration e = config.getInitParameterNames();

            configMap = new HashMap();
            while (e.hasMoreElements()){
                String parameterName = (String) e.nextElement();
                String parameterValue = config.getInitParameter(parameterName);
                validateMapContents(context, configMap, parameterName, parameterValue);
            }
           
        }
        ApplicationConfigurator ap = ApplicationConfigurator.getInstance();
        ap.configure(configMap,null);
    }

    /**
     * Validates the contents of the map and ensures that the proper file names are entered.
     * <p>This method will search for all keys ending with the word <code>"File"</code> and ensure that the full filename is set in the map
     * by searching for the real path.</p>
     * 
     * The name of the key validated must always end with <code>"File"</code>
     * @param context The servlet context
     * @param configMap The map used to store the configuration parameters
     * @param parameterName The name or key by which we search
     * @param parameterValue The value associated with the key
     */
    private void validateMapContents(ServletContext context, Map configMap, String parameterName, String parameterValue) {
        if(((parameterName != null) && (parameterName.trim().length() > 0)) && ((parameterValue != null) && (parameterValue.trim().length() > 0))){
            System.out.println("The name is: " + parameterName + " value is: " + parameterValue);
            if(parameterName.endsWith("File")){
                parameterValue = context.getRealPath(parameterValue);
            }
            configMap.put(parameterName, parameterValue);
        }
        else{
            System.err.println("Name/Value pair inproper!! name: " + parameterName + " value: " + parameterValue);
        }
    }

}
