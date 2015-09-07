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

import org.apache.commons.i18n.MessageManager;
import org.apache.commons.i18n.MessageNotFoundException;

import com.zphinx.spine.resources.ConfigMessageProvider;

/**
 * SpineMessageException is the base spine exception that is used by this framework to create exceptions which accept locale sensitive message keys which allow parametric replacement of strings.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 1, 2007 14:31:14 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpineMessageException extends SpineException {

    /**
     * 
     */
    private static final long serialVersionUID = 681472864365504381L;

    /**
     * The message key used to fetch messages from the properties file
     */
    private String message = null;

    /**
     * The locale used to fetch messages
     */
    private Locale locale = null;

    /**
     * THe array of objects to be used for parametric replacement within the properties files
     */
    private Object[] arguments = null;

    /**
     * The resource from which to fetch Messages
     */
    private static final String MESSAGE_RESOURCES = "com.zphinx.spine.resources.SpineMessages";

    /**
     * The id given to the resource
     */
    private static final String RESOURCE = "resource";

    /**
     * Public Constructor
     * 
     * @param message
     */
    public SpineMessageException(String message) {
        super.fillInStackTrace();
        initialize();
        this.message = message;
    }

    /**
     * Public Constructor
     * 
     * @param message
     * @param arguments
     */
    public SpineMessageException(String message, Object[] arguments) {
        super.fillInStackTrace();
        initialize();
        this.message = message;
        this.arguments = arguments;
    }

    /**
     * Public Constructor
     * 
     * @param message
     * @param arguments
     * @param cause
     */
    public SpineMessageException(String message, Object[] arguments, Throwable cause) {
        super.fillInStackTrace();
        initialize();
        this.message = message;
        this.arguments = arguments;
    }

    /**
     * Gets the message for this exception from the associated properties files
     * 
     * @return the message to get for this exception
     */
    public String getMessage() {
        String s = "";
        try{
            s = MessageManager.getText(RESOURCE, message, arguments, locale, message);
        }
        catch (RuntimeException t){
            s = t.getMessage();
        }
        catch (Exception e){
            s = e.getMessage();
        }
        catch (Throwable t){
            s = t.getMessage();
        }
        return s;
    }

    /**
     * Gets the locale sensitive message
     * 
     * @return the message the locale sensitive message
     */
    public String getLocalizedMessage() {
        return getMessage();
    }

    /**
     * Gets the message for this exception from the associated properties files
     * 
     * @param local The locale for which to retrieve this message
     * @return the message to get for this exception
     */
    public String getMessage(Locale local) {
        return MessageManager.getText(RESOURCE, message, arguments, local, message);
    }

    /**
     * Gets the locale sensitive message
     * 
     * @param local The locale for which to retrieve this message
     * @return the message the locale sensitive message
     */
    public String getLocalizedMessage(Locale local) {
        return getMessage(local);
    }

    /**
     * Initialize this object
     * 
     * @throws MessageNotFoundException
     */
    private void initialize() throws MessageNotFoundException {

        ConfigMessageProvider rbm = new ConfigMessageProvider(MESSAGE_RESOURCES);
        MessageManager.addMessageProvider(RESOURCE, rbm);
        this.locale = Locale.getDefault();
    }

}
