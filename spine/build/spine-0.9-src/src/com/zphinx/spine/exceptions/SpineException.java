/**
 * SpineException.java
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

/**
 * SpineException represents the common exception thrown by classes within the spine framework.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Nov 15, 2007 2:39:26 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpineException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -4697367739638892236L;

    /**
     * Default Constructor - takes no arguments
     */
    public SpineException() {
       super();
    }

    /**
     * Constructor with string parameter, denoting the default message to print
     * 
     * @param message The message to print
     */
    public SpineException(String message) {
        super(message);
       
    }

    /**
     * Constructor with cause parameter which denotes the cause of the exception which may contain more error information
     * 
     * @param cause The cause of the exception defined in a throwable object
     */
    public SpineException(Throwable cause) {
        super(cause);
       
    }

    /**
     * Constructor with string and cause parameter, denoting the default message to print and the cause of the exception which may contain more error information
     * 
     * @param message The message to print
     * @param cause The cause of the exception defined in a throwable object
     */
    public SpineException(String message, Throwable cause) {
        super(message, cause);
       
    }

}
