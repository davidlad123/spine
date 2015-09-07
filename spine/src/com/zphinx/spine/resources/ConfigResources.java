/**
 * ConfigResources.java
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

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

import org.apache.commons.i18n.MessageManager;
import org.apache.commons.i18n.MessageNotFoundException;
import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.message.MessageConfig;
import com.zphinx.spine.message.SpineMessageManager;
import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * ConfigResources allows access to various configuration parameters and provides a default means to access the default properties file used for configuration.
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          
 *          Copyright &copy; Zphinx Software Solutions
 *          </p>
 */
public class ConfigResources {

    /**
     * The name for the default message id used by the MessageProvider 
     */
    private static final String MESSAGE_RESOURCES_ID = "MessageResources";

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(ConfigResources.class.getName());

    /**
     * The name and location of our resource bundle properties file
     */

    private static final String BUNDLE_NAME = "com.zphinx.spine.resources.ConfigurationResources";//$NON-NLS-1$

    /**
     * The id for the message resource
     */
    private static final String RESOURCE_ID = "ConfigResource";

    /**
     * The id for the exception messages resource
     */
    private static final String EXCEPTION_MESSAGES_ID = "ExceptionMessages";

    /**
     * The static instance of this Object
     */
    private static ConfigResources configResources = null;

    /**
     * The PropertyResourceBundle in use
     */

    private static PropertyResourceBundle RESOURCE_BUNDLE = null;

    /**
     * The locale used to fetch our resource bundle
     */
    private Locale locale = null;

    /**
     * <p>Creates message providers from the resources file stored in @{linkplain MessageConfig}. It will also create other messageProviders from the id/resourceFile obtained in MessageConfig.getResourcesList()
     *  and add them to the registered message providers for this system.</p>
     * 
     * @param locale The default locale for this resource retrieval engine
     */
    private ConfigResources(Locale locale) {
        
        if(locale == null) locale = Locale.getDefault();
        
        RESOURCE_BUNDLE = (PropertyResourceBundle) PropertyResourceBundle.getBundle(BUNDLE_NAME);
        ConfigMessageProvider rbm = new ConfigMessageProvider(BUNDLE_NAME);
        SpineMessageManager.addMessageProvider(RESOURCE_ID, rbm);
        
        //add default message file
       
        ConfigMessageProvider rbmp1 = new ConfigMessageProvider(MessageConfig.getMessageResourceFile());
        SpineMessageManager.addMessageProvider(MESSAGE_RESOURCES_ID, rbmp1);

        //add default exception Message file
        
        ConfigMessageProvider rbmpe = new ConfigMessageProvider(MessageConfig.getExceptionMessageFile());
        SpineMessageManager.addMessageProvider(EXCEPTION_MESSAGES_ID, rbmpe);

        
        
        // Add resourceArray implementation
        List list = MessageConfig.getResourcesList();
        Iterator it = list.iterator();
        while (it.hasNext()){
            StringAttributeBean sab = (StringAttributeBean) it.next();
            ConfigMessageProvider rbmp2 = new ConfigMessageProvider(sab.getValue());
            SpineMessageManager.addMessageProvider(sab.getName(), rbmp2);
        }

        this.locale = locale;

    }

    /**
     * Gets the locale sensitive string defined by this key
     * 
     * @param key The key to the object been fetched
     * @return The locale sensitive string
     */
    public static String getString(String key) {

        try{
            if(RESOURCE_BUNDLE == null){
                RESOURCE_BUNDLE = (PropertyResourceBundle) PropertyResourceBundle.getBundle(BUNDLE_NAME);
            }
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e){
            return '!' + key + '!';
        }
    }

    /**
     * Gets the PropertyResourceBundle associated with this object
     * 
     * @return Returns the RESOURCE_BUNDLE.
     */
    public PropertyResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }

    /**
     * Gets the static instance of this object.
     * 
     * @return The static instance of this object
     */
    public static ConfigResources getInstance(Locale loca) {
        return initialize(loca);

    }

    /**
     * Runs the initialization process if this object is null or un-initialized
     * 
     * @param loca The locale used to initialize this object
     * @return The static instance of this object
     */
    private static ConfigResources initialize(Locale loca) {
        if(configResources == null){
            configResources = new ConfigResources(loca);
        }
        return configResources;
    }

    /**
     * Gets the static instance of this object.
     * 
     * @return The static instance of this object
     */
    public static ConfigResources getInstance() {
        return initialize(Locale.getDefault());
    }

    /**
     * Iterates over the registered message provider to get the given message key.
     * 
     * @param key The desired message key
     * @param arguments The dynamic parts of the message that will be evaluated.
     * @param locale The locale in which the message will be printed
     * @return The localized text
     */
    public String getString(String key, Object[] arguments, Locale locale) {
        String s = "";
        try{
            s = SpineMessageManager.getText(RESOURCE_ID, key,key, arguments, checkLocale(locale), key);
        }
        catch (MessageNotFoundException e){
            log.debug("Message Not Found Error: " + e.getMessage());
            s = key;
        }
        catch (Exception e){
            log.debug("Error: " + e.getMessage());
            s = key;
        }
        return s;
    }

    /**
     * Checks that this locale is not null
     * 
     * @param locale2 The locale to check
     * @return The default locale for this object or the locale that was sent to this method if not null
     */

    private Locale checkLocale(Locale locale2) {
        if(locale2 == null){
            locale2 = this.locale;
        }
        return locale2;
    }

    /**
     * Iterates over the registered message provider to get the given message key.
     * 
     * @param key The desired message key
     * @param arguments The dynamic parts of the message that will be evaluated.
     */
    public String getString(String key, Object[] arguments) {
        return getString(key, arguments, this.locale);
    }

    /**
     * Tries to find the desired entry in the named message provider.
     * 
     * @param providerId The name of the message provider (i.e. source) to use for the message
     * @param key The desired message key
     * @param arguments The dynamic parts of the message that will be evaluated using the standard java text formatting abilities.
     * @param locale The locale in which the message will be printed
     * @return The localized text
     */
    public String getString(String providerId, String key, Object[] arguments, Locale locale) {
        String s = "";
        try{
            s = SpineMessageManager.searchMessages(providerId, key, key, arguments, checkLocale(locale), key);
          
        }
        catch (Throwable t){
            s = t.getMessage();
        }
        return s;
    }

    /**
     * Tries to find the desired entry in the named message provider.
     * 
     * @param providerId The name of the message provider (i.e. source) to use for the message
     * @param key The desired message key
     * @param arguments The dynamic parts of the message that will be evaluated using the standard java text formatting abilities.
     * @return The localized text
     */
    public String getString(String providerId, String key, Object[] arguments) {
        String s = "";
        try{
            s = SpineMessageManager.searchMessages(providerId, key, key, arguments, locale,key);

        }

        catch (Throwable t){
            s = t.getMessage();
        }
        return s;
    }
}