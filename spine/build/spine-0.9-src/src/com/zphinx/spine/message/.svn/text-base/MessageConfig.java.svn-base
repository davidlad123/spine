/**
 * MessageConfig.java
 * 
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
 ***/

package com.zphinx.spine.message;

import java.util.ArrayList;
import java.util.ListIterator;

import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * MessageConfig contains static messages used by spine for configuration purposes. It also serves as a static resource where different message properties files are referenced.
 * <p>
 * It is called at initialization to persist message properties files which it saves in an arraylist of {@link StringAttributeBean}s 
 * obtained from {@link #getResourcesList()}
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 30-Sep-2005 13:09:00<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MessageConfig {

    /**
     * The prefix used to stamp any message resource file referenced by this object
     */
    public static final String PROVIDER = "provider-";

    /**
     * The prefix used by this object to denote it's alias in the system
     */
    private static String modulePrefix = "";

    /**
     * This is the default value of the messages file which may be changed at initialization
     */
    private static String messageResourceFile = "com.zphinx.spine.resources.ConfigurationResources";

    /**
     * The name of the properties file
     */
    private static String siteMessages = null;

    /**
     * The collection of properties files
     */
    private static ArrayList resourcesList = null;

    /**
     * Private Constructor
     */
    private MessageConfig() {
        super();
    }

    /**
     * Gets the default or the last saved message properties file used by this frameworkwhich is saved by {@link #setSiteMessages(String)}
     * 
     * @return Returns the siteMessages.
     */
    public static String getSiteMessages() {
        return siteMessages;
    }

    /**
     * Sets this modules prefix
     * 
     * @param prefix The alias of this object
     */
    public static void setModulePrefix(String prefix) {
        modulePrefix = prefix;
    }

    /**
     * Adds a message properties file to this object.The last saved message properties file can be retrieved by {@link #getSiteMessages()}
     * 
     * @param siteMessages The siteMessages to set.
     */
    public static void setSiteMessages(String siteMessages) {
        MessageConfig.siteMessages = siteMessages;
        saveResource(siteMessages);
    }

    /**
     * Saves a site message in the resource list
     * 
     * @param siteMessages The site message to save
     */
    private static void saveResource(String siteMessages) {
        int i = getResourcesList().size();
        ListIterator it = getResourcesList().listIterator();
        boolean b = false;
        while (it.hasNext()){
            StringAttributeBean sat = (StringAttributeBean) it.next();
            if(sat.getValue().equals(siteMessages)){
                b = true;
                break;
            }
        }
        if(!b){
            getResourcesList().add(new StringAttributeBean(PROVIDER + i, siteMessages));
        }

    }

    /**
     * Gets the arrayList of resource properties files in use by this framework.
     * 
     * @return Returns the arrayList of resource properties files in use by this framework.
     */
    public static ArrayList<StringAttributeBean> getResourcesList() {
        if(resourcesList == null){
            resourcesList = new ArrayList();
        }
        return resourcesList;
    }

    /**
     * Sets the resources list, an array of {@link StringAttributeBean}s
     * 
     * @param resourcesList The resourcesList to set.
     */
    public static void setResourcesList(ArrayList resourcesList) {
        MessageConfig.resourcesList = resourcesList;
    }

    /**
     * Gets a simple prefix used by this object to denote it's alias in the system
     * 
     * @return the modulePrefix
     */
    public static String getModulePrefix() {
        return modulePrefix;
    } 

    /**
     * Gets this object's associated resource file normally <code>com.zphinx.spine.resources.ConfigurationResources</code>
     * 
     * @return the messageResourceFile
     */
    public static String getMessageResourceFile() {
        return messageResourceFile;
    }

    /**
     * Sets this object's associated resource file
     * 
     * @param messageResourceFile the messageResourceFile to set
     */
    public static void setMessageResourceFile(String messageResourceFile) {
        MessageConfig.messageResourceFile = messageResourceFile;
    }

}
