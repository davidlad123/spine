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

import java.util.List;

import com.zphinx.spine.core.viewprocessors.ViewProcessor;
import com.zphinx.spine.message.DisplayError;
import com.zphinx.spine.message.DisplayMessage;
import com.zphinx.spine.message.DisplayMessages;
import com.zphinx.spine.message.ErrorMessages;

/**
 * ResultObject acts as a DataTransferAssembler used to send the output of data transactions accross layers for onward processing
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          created 10-Jan-2005 18:33:01
 *          </p>
 *          <p>
 *          copyright &copy; Zphinx Software Solutions
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
     * The DisplayMessages object associated with this object
     */
    private DisplayMessages displayMessages = null;

    /**
     * Constructor initializes a displayMessages object using the default locale,client developers are advised to use {@link #addMessageOrError(DisplayMessage)} to set the messages. {@link ViewProcessor}
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
        return this.displayMessages.getAllErrorString();
    }

    /**
     * Sets the errors saved in this object,and sets the errorFlag to true the errors must be a String with length greater than 1, and cannot be a blank String
     * 
     * 
     * @param errorString The errors to set.
     */
    public void setErrors(String errorString) {
        if((errorString != null) && (errorString.trim().length() > 1)){
           this.displayMessages.add(ERRORS, new DisplayError(ErrorMessages.ERROR_PROCESS,errorString));
          
        }
    }


    /**
     * Gets the flag indicating failure or success.True if there was an error during processing
     * 
     * @return Returns the errorFlag.
     */
    public boolean isErrorFlag() {
        
        return this.displayMessages.containsErrors();
    }

   

    /**
     * Gets the DisplayMessages object associated with this object, using the default locale for this jvm
     * 
     * @return The DisplayMessages object associated with this object
     */
    public DisplayMessages getDisplayMessages() {
        return this.displayMessages;
    }

   
    /**
     * Gets a flag indicating the presence of messages in this object
     * 
     * @return the messageFlag
     */
    public boolean isMessageFlag() {
        return this.displayMessages.containsMessages();
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
        s.append("Messages: " + this.displayMessages.getAllMessageString());
        s.append("\n\r");
        s.append("Errors: " + this.displayMessages.getAllErrorString());
        return s.toString();
    }

    /**
     *
     * Sets the displayMessages
     * @param displayMessages the displayMessages to set
     */
    public void setDisplayMessages(DisplayMessages displayMessages) {
        this.displayMessages = displayMessages;
    }

    /**
     * 
     *Gets the messages
     * @return the messages
     */
    public String getMessages() {
        return this.displayMessages.getAllMessageString();
    }

    /**
     *
     * Sets the messages returned with this Object, accepts a String value 
     * @param messages the messages to set
     */
    public void setMessages(String messages) {
        if((messages != null) && (messages.trim().length() > 1)){
            this.displayMessages.add(ERRORS, new DisplayError(ErrorMessages.ERROR_PROCESS,messages));
           
         }
    }

    

    /**
     *
     * Sets a DisplayMessage or DisplayError into this Object 
     * @param message the message or error object to set
     */
    public void addMessageOrError(DisplayMessage message) {
        if(message != null){
            if(message instanceof DisplayError) {
                this.displayMessages.add(ERRORS, message);    
            }
            else {
                this.displayMessages.add(MESSAGES, message);
            }
            
           
         }
    }

}