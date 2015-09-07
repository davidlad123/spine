/**
* MessagePlugin.java
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

import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import com.zphinx.spine.message.MessageConfig;

/**
 * 
 * MessagePlugin serves as a plugin used to configure messages for the SPine framework
 * @author David Ladapo
 * @version  $1.0
 * 
 * <p>Created: May 21, 2008 2:12:22 AM<br>
 * Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/
public class MessagePlugin extends AbstractSpinePlugin {

    /**
     * The key used to store the Message Properties class name
     */
    public static final String MESSAGE_PROPERTY_CLASS = "messagePropertyClass";
    
    /**
     * The key used to store the default site messages, a comma separated list of properties files which are used by this application framework to store messages
     */
    public static final String SITE_MESSAGES = "siteMessages";

    /**
     * Public Constructor
     */
    public MessagePlugin() {
        super();
    }

   

    /* (non-Javadoc)
     * @see com.zphinx.spine.plugin.SpinePlugin#process(java.util.Map)
     */
    public void process(Map map) throws Throwable {
        String siteMessages = null;
        String messagePropertyClass = null;

        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            String key = (String) it.next();
            Object value = map.get(key);
        if(key.equalsIgnoreCase(SITE_MESSAGES)){
            siteMessages = (String) value;
        }
        else if(key.equalsIgnoreCase(MESSAGE_PROPERTY_CLASS)){
            messagePropertyClass = (String) value;
        }
        }
        // Add messagePropertyClass
        if(messagePropertyClass != null){
            MessageConfig.setMessageResourceFile(messagePropertyClass);
        }
        // Add siteMessages
        if(siteMessages != null){
            storeResources(siteMessages);
        }
    }
    /**
     * Stores the location of the available resource files
     * 
     * @param siteMessages The comma separated list of properties files which are used by this application framework to store messages
     */
    private void storeResources(String siteMessages) {
        if(siteMessages != null){
            StringTokenizer st = new StringTokenizer(siteMessages, ",");
            int counter = -1;
            String[] resourceArray = new String[st.countTokens()];
            while (st.hasMoreElements()){
                counter++;
                String s = (String) st.nextElement();
                resourceArray[counter] = s;
            }
            if(resourceArray.length > 0){
                for (int j = 0; j < resourceArray.length; j++){
                    MessageConfig.setSiteMessages(resourceArray[j]);
                }

            }
        }
    }
}
