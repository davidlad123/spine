/**
 * DisplayError.java
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
 * DisplayError serves as a nominal error object generated within the spine framework. It use the concept of a message severity based on the following:
 * <ul type=disc>
 * <li>ERROR_SYSTEM represented by an int value of 4</li>
 * <li>ERROR_EXCEPTION represented by an int value of 3</li>
 * <li>ERROR_PROCESS represented by an int value of 2</li>
 * <li>ERROR_WARNING represented by an int value of 1</li>
 * <li>ERROR_NONE represented by an int value of 0</li>
 * </ul>
 * As with displayMessage it accepts both string and an Object[] arguments which it uses to fetch the appropriate string representation of the message key persisted in the DisplayError object
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 8, 2007 12:30:11 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DisplayError extends DisplayMessage implements ErrorMessages {

    /**
     * The severity of this error
     */
    private int severity = 2;

    /**
     * Public Constructor
     */
    public DisplayError() {
        super();
    }

    /**
     * Public Constructor which sets the severity of the error
     * 
     * @param severity The severity of this error can be one of the values defined above
     */
    public DisplayError(int severity) {
        super();
        this.severity = severity;
    }

    /**
     * Constructs a DisplayError with parametric replacements
     * 
     * @param message The string which represents the key by which this message is discovered
     * @param arguments The arguments to use for parametric replacements
     */
    public DisplayError(String message, Object[] arguments) {
        super(message, arguments);
    }

    /**
     * Public Constructor which sets a message and severity of the error
     * 
     * @param severity The severity of this error can be one of those defined above
     * @param message The string which represents the key by which this message is discovered
     */
    public DisplayError(int severity, String message) {
        super();
        this.severity = severity;
        setMessage(message);
    }

    /**
     * Constructs a DisplayError with parametric replacements
     * 
     * @param severity The severity of this error
     * @param message The string which represents the key by which this message is discovered
     * @param arguments The arguments to use for parametric replacements
     */
    public DisplayError(int severity, String message, Object[] arguments) {
        super(message, arguments);
        this.severity = severity;
    }

    /**
     * Gets the severity
     * @return the severity
     */
    public int getSeverity() {
        return severity;
    }

    /**
     * Sets the severity of this error
     * 
     * @param severity the severity to set
     */
    public void setSeverity(int severity) {
        this.severity = severity;
    }

    /**
     * Sets a message and it's severity
     * 
     * @param severity The severity of this error
     * @param message The message or key to be set
     */
    public void setMessage(int severity, String message) {
        this.severity = severity;
        setMessage(message);
    }

    /**
     * Returns the string representation of this object
     */
    public String toString() {

        return severity + "| " + getMessage();
    }
}
