/**
* AbstractSpinePlugin.java
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
 
package com.zphinx.spine.plugin;

import java.util.Map;


/**
 * 
 * AbstractSpinePlugin is a base plugin useful for creating other plugins, a client developer should extend this class when creating a new plugin 
 * @author David Ladapo
 * @version  $1.0
 * 
 * <p>Created: May 19, 2008 10:39:55 PM<br>
 * Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/
public abstract class AbstractSpinePlugin implements SpinePlugin {

    /**
     * The name given to this plugin by which it will be identified within the framework
     */
    private String name = null;

    /**
     * Public Constructor
     */
    public AbstractSpinePlugin() {
     super();
    }

    /* (non-Javadoc)
     * @see com.zphinx.spine.plugin.SpinePlugin#getName()
     */
    public String getName() {
     
        return this.name ;
    }

    /* (non-Javadoc)
     * @see com.zphinx.spine.start.ConfigurationEngine#process(java.util.Map, javax.sql.DataSource)
     */
    public abstract void process(Map map) throws Throwable;

    /**
     *
     * Sets the name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
