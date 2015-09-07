/**
 * 
 *
 **/
package com.zphinx.spine.plugin;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.start.helpers.impl.ConfigReader;
import com.zphinx.spine.start.ApplicationConfigurator;

/**
 * <p>StrutsInitPlugIn handles initialisation of data used by spine when integrated
 * with struts. Please note that this plugin will require the following defined in your struts-config.xml file
 * </p>
 * <ul>
 * <li>the struts-config.xml file to have a dataSource defined as <code>dataSource.default</code></li>
 * <li>The path to the start_config.xml file used to initialise spine. This path must have its name declared as <code>startConfigFile</code></li>
 * 
 * </ul>
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Date: Created on Mar 7, 2008
 *          </p>
 *          <p>
 *          Copyright &copy; Zphinx Software Solutions
 *          </p>
 */
public class StrutsInitPlugIn implements PlugIn {

	/**
	 * The <code>Log</code> instance for this application.
	 */

	private static Logger log = Universal.getLogger(StrutsInitPlugIn.class
			.getName());

    
    private static String START_CONFIG_FILE = "startConfigFile";
	
    /**
	 * Constructor
	 */
	public StrutsInitPlugIn() {
		super();
	}

	/**
	 * Gracefully shut down, releasing any resources that were allocated at
	 * initialisation.
	 * 
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy() {
		if (log.isDebugEnabled()) {
			log.debug("Destroying StrutsInitPlugIn");
		}

	}

	/**
	 * Initializes this module using the Spine ApplicationConfigurator
	 * file.Requires adding struts.jar to your classpath.<br>
	 * N:B Struts.jar is not distributed with this application
	 * 
	 * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
	 *      org.apache.struts.config.ModuleConfig)
	 * @param actionServlet
	 *            The ActionServlet instance running this plugin
	 * @param module
	 *            The ModuleConfig associated with this module
	 */
	public void init(ActionServlet actionServlet, ModuleConfig module)
			throws ServletException {

		try {

			// Identify the current module
			final ServletContext context = actionServlet.getServletConfig()
					.getServletContext();

			PlugInConfig plug = null;
			PlugInConfig plugs[] = module.findPlugInConfigs();
			for (int i = 0; i < plugs.length; i++) {
				if (plugs[i].getClassName().equals(this.getClass().getName())) {
					plug = plugs[i];
					break;
				}
			}
			if (plug != null) {
				final Map map = plug.getProperties();
				final DataSource dataSource = (DataSource) context
						.getAttribute("dataSource.default" + module.getPrefix());
				System.out.println("The datasource is: " + dataSource);
                ConfigReader confReader = new ConfigReader();
                if(map.get(START_CONFIG_FILE) != null) {
                    String path = (String) map.get(START_CONFIG_FILE);
                    path = context.getRealPath(path);
                    Map m = confReader.createConfig(path);
                    ApplicationConfigurator.getInstance(
                            new RoleConfigurationEngine()).configure(m,
                            dataSource);
                }
				
			}
		}

		catch (final Exception e) {
			e.printStackTrace();
		}

	}

}