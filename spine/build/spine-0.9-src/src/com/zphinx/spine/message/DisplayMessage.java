/**
 * DisplayMessage.java
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

package com.zphinx.spine.message;

/**
 * DisplayMessage represents a single message generated within the spine framework which can be displayed to the user.
 * It can retrieve messages from a predefined resource bundle which is available system wide.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 8, 2007 12:31:10 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DisplayMessage {

    /**
     * The string displayed to the user
     */
    private String message = "";

    /**
     * The arguments used to fetch the message from a resource
     */
    private Object[] arguments = null;

    /**
     * Default Constructor
     */
    public DisplayMessage() {
        super();
    }

    /**
     * Constructor which accepts a message key
     * 
     * @param message The message key by which this object derives the appropriate message
     */
    public DisplayMessage(String message) {
        super();
        this.message = message;
    }

    /**
     * Constructs a DisplayMessage with parametric replacements
     * 
     * @param message The string which represents the key by which this message is discovered
     * @param arguments The arguments to use for parametric replacements
     */
    public DisplayMessage(String message, Object[] arguments) {
        super();
        this.message = message;
        this.arguments = arguments;
    }

    /**
     * Gets the message key
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message key
     * 
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets a message with parametric replacements
     * 
     * @param message the message to set
     */
    public void setMessage(String message, Object[] arguments) {
        this.message = message;
        this.arguments = arguments;
    }

    /**
     * Returns the value of the message contained in this message
     */
    public String toString() {
        return message;
    }

    /**
     * Gets the arguments for the specified message
     * 
     * @return the arguments
     */
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * Sets the arguments for the specified message
     * 
     * @param arguments the arguments to set
     */
    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

}
