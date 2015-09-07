/**
 * SpineMessageException.java
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

package com.zphinx.spine.exceptions;

import java.util.Locale;

import com.zphinx.spine.message.SpineMessageManager;
import com.zphinx.spine.plugin.MessagePlugin;
import com.zphinx.spine.resources.ConfigResources;

/**
 *<p> SpineMessageException is the base spine exception that is used by this framework to create exceptions which accept locale
 * sensitive message keys which allow parametric replacement of strings.</p>
 * <p>This exception requires (for speed purposes) that an exceptionMessageClass property be declared in the spine-init.xml file. This property is a property of the {@linkplain MessagePlugin} and associates this
 * exception with a properties file, which should contain your error messages. </p>
 * 
 * <p>The exception will still find you message by it's defined key if it is not in the default exceptionMessageClass, but it does this by seacrhing through all the properties files, and  Locales in the application.</p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpineMessageException extends SpineException {

    /**
     * The serialVer id of this object
     */
    private static final long serialVersionUID = 681472864365504381L;

    /**
     * The Locale used to fetch messages
     */
    private Locale locale = null;
    
    /**
     * The message key used to fetch messages from the properties file
     */
    private String message = null;

    /**
     * The array of objects to be used for parametric replacement within the properties files
     */
    private Object[] arguments = null;

    /**
     * The id given to the resource
     */
    private static final String RESOURCE = "ExceptionMessages";

    /**
     * Creates a SpineMessageException with a message key
     * 
     * @param message The message key used to initialise this exception
     */
    public SpineMessageException(String message) {
        initialize(message,Locale.getDefault());
        locale = Locale.getDefault();
    }

    /**
     * 
     * Creates a SpineMessageException with a message key at the specified locale
     * @param message The message key used to initialise this exception
     * @param locale The locale within which this message key's property should be displayed 
     */
    public SpineMessageException(String message,Locale locale) {
        initialize(message,locale);
        this.locale = locale;
    }
    
    
    /**
     * Creates a SpineMessageException with a message key with the associated parametric replacements
     * 
     * @param message The message key used to initialise this exception
     * @param arguments The parametric replacement objects used to obtain messages from the specified properties file
     */
    public SpineMessageException(String message, Object[] arguments) {
        initialize(message,Locale.getDefault());
        this.arguments = arguments;
        locale = Locale.getDefault();
    }

    /**
     * 
     * Creates a SpineMessageException with a message key with the associated parametric replacements at the specified locale
     * @param message The message key used to initialise this exception
     * @param arguments The parametric replacement objects used to obtain messages from the specified properties file
     * @param locale The locale within which this message key's property should be displayed
     */
    public SpineMessageException(String message, Object[] arguments,Locale locale) {
        initialize(message,locale);
        this.arguments = arguments;
        this.locale = locale;
    }

    
    /**
     * Gets the message for this exception from the associated properties files
     * 
     * @return the message to get for this exception
     */
    public String getMessage() {
        return searchMessages(this.locale);
    }

    /**
     * Gets the locale sensitive message
     * 
     * @return the message the locale sensitive message
     */
    public String getLocalizedMessage() {
        return getMessage(this.locale);
    }

    /**
     * Gets the message for this exception from the associated properties files
     * 
     * @param locale The locale for which to retrieve this message
     * @return the message to get for this exception
     */
    public String getMessage(Locale locale) {

        return searchMessages(locale);
    }

    /**
     * Searches the messages for the given message and arguments
     * 
     * @param locale The given locale for this search
     * @return The string retrieved from the Message manager.
     */
    private String searchMessages(Locale locale) {
        String s = "";
        try{
            s = SpineMessageManager.searchMessages(RESOURCE, message, message, arguments, locale, message);

        }

        catch (Throwable t){
            s = t.getMessage();
        }
        return s;
    }

    /**
     * Gets the locale sensitive message
     * 
     * @param locale The locale for which to retrieve this message
     * @return the message the locale sensitive message
     */
    public String getLocalizedMessage(Locale locale) {
        return getMessage(locale);
    }

   /**
    * Initialize this object
    * @param message The message key used to retrieve messages from the resource bundle
    */
    private void initialize(String message,Locale locale) {
      ConfigResources.getInstance(locale);
        super.fillInStackTrace();
        this.message = message;

    }

}
