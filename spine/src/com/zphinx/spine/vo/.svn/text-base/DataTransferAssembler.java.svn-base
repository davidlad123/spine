/**
 * DataTransferAssembler.java
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

import java.io.Serializable;

/**
 * DataTransferAssembler contains the relevant methods needed by spine when defining a DataTransferAssembler. Two DataTransferAssembler's are define within spine, they are:
 * <ul type="disc">
 * <li>CommandComponent</li>
 * <li>ResultObject</li>
 * </ul>
 * 
 * @author David Ladapo
 * @version $1.0
 * @see CommandComponent
 * @see ResultObject
 *      <p>
 *      
 *      Copyright &copy;Zphinx Software Solutions
 *      </p>
 */
public interface DataTransferAssembler extends Serializable {

    /**
     * Return the object used to store data passed to the persistence layer
     * 
     * @return Returns the obj.
     */
    public abstract Object getObj();

    /**
     * Set the object used to store data passed to the persistence layer
     * 
     * @param obj The obj to set.
     */
    public abstract void setObj(Object obj);

    /**
     * Get the object needed to initiate a data connection e.g DataSource for databases
     * 
     * @return Returns the dataObject.
     */
    public abstract Object getDataObject();

    /**
     * Set the object needed to initiate a data connection e.g DataSource for databases
     * 
     * @param dataObject The dataObject to set.
     */
    public abstract void setDataObject(Object dataObject);

    /**
     * Gets the int which represents which type of persistence layer this object will be passed to i.e the index of the DataProxy in use.
     * 
     * @return Returns the dataType.
     */
    public abstract int getDataType();

    /**
     * Sets the int which represents which type of persistence layer this object will be passed to i.e the index of the DataProxy in use.
     * 
     * @param dataType The dataType to set.
     */
    public abstract void setDataType(int dataType);

    /**
     * Gets the name of the DataAccessObject to use
     * 
     * @return The name of the DataAccessObject to use
     */
    public abstract String getDataObjectClass();

    /**
     * Sets the name of the DataAccessObject to use
     * 
     * @param dataObjectClass The dataObjectClass to set.
     */
    public abstract void setDataObjectClass(String dataObjectClass);

    /**
     * Gets the DaoConstructor object associated with this object
     * 
     * @return Returns the daoConstructor.
     */
    public abstract DAOInput getDaoConstructor();

    /**
     * Sets the DaoConstructor object associated with this object
     * 
     * @param daoConstructor The daoConstructor to set.
     */
    public abstract void setDaoConstructor(DAOInput daoConstructor);

    /**
     * Used to package additinal instructions to the DAO
     * 
     * @return An int representing an opeartion to perform
     */
    public abstract int getOperation();

    /**
     * Used to package additional instructions to the DAO
     * 
     * @param i The int representing the additional instruction
     */
    public abstract void setOperation(int i);

}