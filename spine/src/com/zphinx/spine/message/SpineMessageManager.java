/**
 * SpineMessageManager.java
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

package com.zphinx.spine.message;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.i18n.MessageManager;
import org.apache.commons.i18n.MessageNotFoundException;
import org.apache.commons.i18n.MessageProvider;

/**
 * <p>
 * SpineMessageManager is a minimal implementation of the apache I18N MessageManager object. It provides the base methods needed by spine and adds extra functionality to the MessageManager Object.
 * </p>
 * <p>
 * It also contains bug fixes for errors found in MessageManager
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpineMessageManager {

    static final String INTERNAL_MESSAGE_NOT_FOUND = "Internal I18n error: Message not found";

    static final String MESSAGE_NOT_FOUND = "messageNotFound";

    static final String NO_MESSAGE_ENTRIES_FOUND = "noMessageEntriesFound";

    static final String MESSAGE_ENTRY_NOT_FOUND = "messageEntryNotFound";

    static final String RESOURCE_BUNDLE_NOT_FOUND = "resourceBundleNotFound";

    private static Map messageProviders = new LinkedHashMap();

    /**
     * Public Constructor
     */
    private SpineMessageManager() {
        super();
    }

    /**
     * Add a custom <code>{@link MessageProvider}</code> to the <code>MessageManager</code>. It will be incorporated in later calls of the {@link MessageManager#getText(String,String,Object[],Locale) getText} or {@link #getEntries(String,Locale) getEntries}methods.
     * 
     * @param providerId Id of the provider used for uninstallation and qualified naming.
     * @param messageProvider The <code>MessageProvider</code> to be added.
     */
    public static void addMessageProvider(String providerId, MessageProvider messageProvider) {
        messageProviders.put(providerId, messageProvider);
    }

    /**
     * Remove custom <code>{@link MessageProvider}</code> from the <code>MessageManager</code>. Used for tearing down unit tests.
     * 
     * @param providerId The ID of the provider to remove.
     */
    public static void removeMessageProvider(String providerId) {
        messageProviders.remove(providerId);
    }

  

    /**
     * Tries to find the desired entry in the named message provider.
     * 
     * @param providerId The name of the message provider (i.e. source) to use for the message
     * @param id The identifier that will be used to retrieve the message bundle
     * @param entry The desired message entry
     * @param arguments The dynamic parts of the message that will be evaluated using the standard java text formatting abilities.
     * @param locale The locale in which the message will be printed
     * @exception MessageNotFoundException Will be thrown if the requested message provider cannot be found or no message bundle can be found for the given id or if the desired message entry is missing in the retrieved bundle
     * @return The localized text
     */
    public static String getText(String providerId, String id, String entry, Object[] arguments, Locale locale) throws MessageNotFoundException {
        String s = "";
        MessageProvider provider = (MessageProvider) messageProviders.get(providerId);
        if(provider == null) throw new MessageNotFoundException("Provider '" + providerId + "' not installed");
        String text = provider.getText(id, entry, locale);
        if(text != null){
            s = (arguments != null && arguments.length > 0) ? MessageFormat.format(text, arguments) : text;
        }
        else{
            throw new MessageNotFoundException(MessageFormat.format(Messages.getString(MESSAGE_ENTRY_NOT_FOUND), new Object[] { id, entry }));
        }
        return s;
    }

    /**
     * Attempts to find the requested message from the registered message provider in order to find the given entry in the requested message bundle.
     * 
     * @param providerId The name of the message provider (i.e. source) to use for the message
     * @param id The identifier that will be used to retrieve the message bundle
     * @param entry The desired message entry
     * @param arguments The dynamic parts of the message that will be evaluated using the standard java text formatting abilities.
     * @param locale The locale in which the message will be printed
     * @param defaultText If no message bundle or message entry could be found for the specified parameters, the default text will be returned.
     * @return The localized text or the default text if the message could not be found
     */
    public static String getText(String providerId, String id, String entry, Object[] arguments, Locale locale, String defaultText) {
        String s = "";
        try{
            s = getText(providerId, id, entry, arguments, locale);
        }
        catch (MessageNotFoundException e){
            s = (arguments != null && arguments.length > 0) ? MessageFormat.format(defaultText, arguments) : defaultText;
        }
        return s;
        
    }

    /**
     * Returns a map containing all available message entries for the given locale. The map contains keys of type {@link String}containing the keys of the available message entries and values of type {@link String} containing the localized message entries. 
     * available within all the message providers.
     * @param id The key to search for in this manager
     * @param locale The locale in which the entries are expected to be located
     * @return A map of all the keys in all the providers registeerd with this manager
     * @throws MessageNotFoundException
     */
    public static Map getEntries(String id, Locale locale) throws MessageNotFoundException {
        if(messageProviders.isEmpty()) throw new MessageNotFoundException("No MessageProvider registered");
        Map map = new HashMap();
        for (Iterator i = messageProviders.values().iterator(); i.hasNext();){
            try{
                map.putAll(((MessageProvider) i.next()).getEntries(id, locale));
            }
            catch (MessageNotFoundException e){

            }
        }
        return map;
    
    }

    
    /**
     * Returns a map containing all available message entries in the stated provider, for the given locale.  The map contains keys of type {@link String}containing the keys of the available message entries and values of type {@link String} containing the localized message entries. 
     *  @param providerId The name of the message provider (i.e. source) to use for the message
     * @param id The identifier that will be used to retrieve the message bundle
     * @param locale The locale in which the message will be printed
     * @return A map of all the entries in the MessageProvider
     * @throws MessageNotFoundException
     */
    public static Map getEntries(String providerId, String id, Locale locale) throws MessageNotFoundException {
        MessageProvider provider = (MessageProvider) messageProviders.get(providerId);
        if(provider == null) throw new MessageNotFoundException("Provider '" + providerId + "' not installed");
        return provider.getEntries(id, locale);
    }
    
    /**
     * This method first looks in the provider given for the occurence of the entry, then recursively searchs the other providers in the system for the key. The entry key is returned if non is found.
     * @param providerId The name of the message provider (i.e. source) to use for the message
     * @param id The identifier that will be used to retrieve the message bundle
     * @param entry The desired message entry
     * @param arguments The dynamic parts of the message that will be evaluated using the standard java text formatting abilities.
     * @param locale The locale in which the message will be printed
     * @param defaultText If no message bundle or message entry could be found for the specified parameters, the default text will be returned.
     * @return The localized text or the default text if the message could not be found
     * @throws Throwable if an error occurs
     */

    public static String searchMessages(String providerId, String id, String entry, Object[] arguments, Locale locale, String defaultText) throws Throwable {
        String s = "";
        try{
            s = getText(providerId, id, entry, arguments, locale, entry);
            if(s.equals(entry)){

                for (Iterator i = messageProviders.keySet().iterator(); i.hasNext();){
                    String newProviderId = (String) i.next();
                    if(!newProviderId.equals(providerId)){
                        s = getText(newProviderId, id, entry, arguments, locale, entry);
                        if(!s.equals(entry)){
                            break;
                        }
                    }
                }
            }
        }
        catch (MessageNotFoundException e){
            throw new Throwable(e);
        }
        return s;
    }
}
