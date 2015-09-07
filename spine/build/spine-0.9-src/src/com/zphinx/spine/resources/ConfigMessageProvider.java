/**
 * ConfigMessageProvider.java
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

package com.zphinx.spine.resources;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.i18n.MessageNotFoundException;
import org.apache.commons.i18n.ResourceBundleMessageProvider;

/**
 * ConfigMessageProvider serves as the default MessageProvider used within this application
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 27, 2007 3:10:38 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigMessageProvider extends ResourceBundleMessageProvider {

    /**
     * The logger used by this class
     */
    private static Logger logger = Logger.getLogger(ConfigMessageProvider.class.getName());

    /**
     * 
     */
    static final String MESSAGE_NOT_FOUND = "noMessageEntriesFound";

    /**
     * The name of the resource file associated with this provider
     */
    private String baseName = null;

    /**
     * Public Constructor
     */
    public ConfigMessageProvider(String baseName) {
        super(baseName);
        this.baseName = baseName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.commons.i18n.MessageProvider#getEntries(java.lang.String, java.util.Locale)
     */
    public Map getEntries(String id, Locale locale) throws MessageNotFoundException {

        Map entries = null;
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
        Enumeration keys = resourceBundle.getKeys();
        entries = new HashMap();
        while (keys.hasMoreElements()){
            String key = (String) keys.nextElement();
            entries.put(key, resourceBundle.getString(key));

        }
        if(entries.size() < 1){ throw new MessageNotFoundException(MessageFormat.format(ConfigResources.getString(MESSAGE_NOT_FOUND), (Object[]) new String[] { id })); }
        return entries;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.commons.i18n.MessageProvider#getText(java.lang.String, java.lang.String, java.util.Locale)
     */
    public String getText(String id, String entry, Locale locale) {
        String returnString = "";
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
            returnString = resourceBundle.getObject(entry).toString();
        }
        catch (MissingResourceException e){
            String s = MessageFormat.format(ConfigResources.getString(MESSAGE_NOT_FOUND), (Object[]) new String[] { entry });
            logger.log(Level.WARNING, s);
            returnString = entry;
        }
        return returnString;
    }

}
