/**
 * ResultObject.java
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

package com.zphinx.spine.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.zphinx.spine.core.viewprocessors.ViewProcessor;
import com.zphinx.spine.message.DisplayError;
import com.zphinx.spine.message.DisplayMessage;
import com.zphinx.spine.message.DisplayMessages;

/**
 * ResultObject acts as a DataTransferAssembler used to send the output of data transactions accross layers for onward processing
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          created 10-Jan-2005 18:33:01
 *          </p>
 *          <p>
 *          copyright Zphinx Software Solutions
 *          </p>
 */
public class ResultObject extends CommandComponent {

    /**
     * The key which used to persist messages in this object
     */
    public static final String MESSAGES = "messages";

    /**
     * The key which used to persist errors in this object
     */
    public static final String ERRORS = "errors";

    /**
     * 
     */
    private static final long serialVersionUID = -646096206645126454L;

    /**
     * The String which represents our major reason for failure, not cumulative
     */
    private String errors = null;

    /**
     * A flag which denotes failure or success.True if there was an error during processing
     */
    private boolean errorFlag = false;

    /**
     * A flag indicating the presence of a non error message
     */
    private boolean messageFlag = false;

    /**
     * The string version of this messages
     */
    private String messages = null;

    /**
     * The displayMessages object used to save both errors and messages
     */
    private List displayMessagesList = null;

    /**
     * The array of DisplayMessage objects
     */
    private DisplayMessage[] displayMessage = null;

    /**
     * The array of DisplayError objects
     */
    private DisplayError[] displayError = null;

    /**
     * The DisplayMessages object associated with this object
     */
    private DisplayMessages displayMessages = null;

    /**
     * Constructor initializes a displayMessages object using the default locale,client developers are advised to use {@link #setMessagesList(List)} to set the messages. {@link ViewProcessor}
     */
    public ResultObject() {
        super();
        displayMessages = new DisplayMessages();

    }

    /**
     * Gets the errors saved in this object
     * 
     * @return Returns the errors.
     */
    public String getErrors() {
        return errors;
    }

    /**
     * Sets the errors saved in this object,and sets the errorFlag to true the errors must be a String with length greater than 1, and cannot be a blank String
     * 
     * @deprecated Kept here for backwards compartibility
     * @param errorString The errors to set.
     */
    public void setErrors(String errorString) {
        if((errorString != null) && (errorString.trim().length() > 1)){
            this.errors = errorString;
            this.errorFlag = true;
        }
    }

    /**
     * Sets additional messages into this result object. Contains backwards compartibility mode code
     * 
     * @param displayMessagesList The DisplayMessages object which contains our messages
     */
    public void setMessagesList(List displayMessagesList) {
        this.displayMessagesList = displayMessagesList;
        // backwards compart
        Iterator it = displayMessagesList.iterator();
        ArrayList errorList = new ArrayList();
        ArrayList messageList = new ArrayList();
        while (it.hasNext()){
            Object o = it.next();
            if(o instanceof DisplayError){
                errorList.add(o);
            }
            else if(o instanceof DisplayMessage){
                messageList.add(o);
            }
        }
        if(!messageList.isEmpty()){
            this.messageFlag = true;
            this.messages = getMessageStringFromList(messageList);
            this.displayMessage = (DisplayMessage[]) messageList.toArray(new DisplayMessage[] {});

        }
        if(!errorList.isEmpty()){
            this.errorFlag = true;
            this.errors = getMessageStringFromList(errorList);
            this.displayError = (DisplayError[]) errorList.toArray(new DisplayError[] {});
        }
    }

    /**
     * Gets the string representation of all the messages in the given list
     * 
     * @param aList The list containing our messages
     * @return A String representing the messages in this collection
     */

    private String getMessageStringFromList(List aList) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < aList.size(); i++){
            String s = ((DisplayMessage) aList.get(i)).getMessage();
            sb.append(s + "\r");
        }
        return sb.toString();
    }

    /**
     * Gets the flag indicating failure or success.True if there was an error during processing
     * 
     * @return Returns the errorFlag.
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }

    /**
     * Gets the array of DisplayMessage objects in this result
     * 
     * @return the displayMessages
     */
    public DisplayMessage[] getDisplayMessage() {
        return displayMessage;
    }

    /**
     * Gets the DisplayMessages object associated with this object, using the default locale for this jvm
     * 
     * @return The DisplayMessages object associated with this object
     */
    public DisplayMessages getDisplayMessages() {
        return getDisplayMessages(Locale.getDefault());
    }

    /**
     * Gets the DisplayMessages object associated with this object using the stated locale
     * 
     * @param locale The Locale for which we wish to create messages/errors
     * @return The DisplayMessages object associated with this object
     */
    public DisplayMessages getDisplayMessages(Locale locale) {
        Iterator it = this.displayMessagesList.iterator();
        while (it.hasNext()){
            Object o = it.next();
            if(o instanceof DisplayError){
                displayMessages.add(ERRORS, (DisplayError) o, locale);
            }
            else if(o instanceof DisplayMessage){
                displayMessages.add(MESSAGES, (DisplayMessage) o, locale);
            }
        }
        return displayMessages;
    }

    /**
     * Gets the array of DisplayError objects
     * 
     * @return the displayError
     */
    public DisplayError[] getDisplayError() {
        return displayError;
    }

    /**
     * Gets a flag indicating the presence of messages in this object
     * 
     * @return the messageFlag
     */
    public boolean isMessageFlag() {
        return messageFlag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer s = new StringBuffer("ResultObject contains:¬\n\r");
        s.append("getObj: " + this.getObj());
        s.append("\n\r");
        s.append("getDataObjectClass: " + this.getDataObjectClass());
        s.append("\n\r");
        s.append("Messages: " + this.messages);
        s.append("\n\r");
        s.append("Errors: " + this.errors);
        return s.toString();
    }

}