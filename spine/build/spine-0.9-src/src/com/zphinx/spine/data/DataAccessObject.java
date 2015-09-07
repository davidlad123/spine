/**
 *
 * DataAccessObject.java
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

 **/

package com.zphinx.spine.data;

import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * An Interface used to define suitable methods available to an object which is used to access
 * a suitable data store.
 * 
 * @author David Ladapo (davidl@zphinx.com)
 * @version 1.0
 *          <p>
 *          Created Sun Aug 22 03:20:27 BST 2004 <br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */

public interface DataAccessObject {

    /**
     * Open a connection to the data storage system and return a suitable object
     * 
     * @param obj A DataTransferObject to parse
     * @return A value object containing data
     */
    public Object fetchData(DataTransferObject obj);

    /**
     * Get the total errors generated by this implementation
     * 
     * @return The concatenation of all the generated errors
     */
    public String getErrors();

    /**
     * Set the errors generated by this implementation
     * 
     * @param errors The error to add to the total errors
     */
    public void setErrors(String errors);

    /**
     * The type of persistence system in use by this system. Presently properties and database.
     * @return The concatenation of all the generated errors
     */
    public int getStoreType();

    /**
     * Call all the actions to be performed before closing the datastore
     * 
     * @return True if the operation succeeds
     */
    public boolean close();

}