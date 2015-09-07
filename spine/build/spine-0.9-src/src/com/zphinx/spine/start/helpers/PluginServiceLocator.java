/**
* PluginServiceLocator.java
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
* For further information, please go to http://www.zphinx.co.uk/spine/
*
**/
 
package com.zphinx.spine.start.helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.zphinx.spine.plugin.SpinePlugin;

/**
 * 
 * PluginServiceLocator serves as a singleton used to keep track of the plugins registered with the Spine framework
 * @author David Ladapo
 * @version  $1.0
 * 
 * <p>Created: May 26, 2008 3:56:30 AM<br>
 * Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/
public class PluginServiceLocator {

    
    /**
     * The static instance of this Object
     */
    private static PluginServiceLocator psl  = null;
   
    /**
     * A map to store the plugins associated with this framework
     */
    private static Map pluginMap = null;
    /**
     * Private Constructor
     */
    private PluginServiceLocator() {
        super();
        pluginMap = Collections.synchronizedMap(new HashMap());
    }


    /**
     * Gets the static instance of this object
     * 
     * @return the static instance of this object
     */
    public static PluginServiceLocator getInstance() {
        if(psl == null){
            psl = new PluginServiceLocator();
        }
        return psl;
    }

    /**
     * Gets the Map containing all the registered plugins
     * @return A Map containing all the registered plugins
     */
    public Map getPlugins() {
        return pluginMap;
    }
    
    /**
     * Gets the SpinePlugin which was registered with this object by the specified key
     * @param key The key by which the plugin is registered.
     * @return The SpinePlugin which was registered with this key
     */
    public SpinePlugin getPlugin(String key) {
        
        return (SpinePlugin) pluginMap.get(key);
    }
    
    /**
     * Adds a plugin to this service locator
     * @param key The key by which the plugin will be known to the system
     * @param plugin The plugin to add.
     */
    public void addPlugin(String key,SpinePlugin plugin){
        
        pluginMap.put(key, plugin);
    }
}
