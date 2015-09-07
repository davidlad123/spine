/**
 * CommandComponent.java
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

import com.zphinx.spine.vo.dto.DTOWrapper;

/**
 * CommandComponent is a DataTransferAssember for objects needed to initialize the BusinessDelegates used in the business layer. It assembles all the objects needed by the system to communicate between different layers and mimics the command design pattern.
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          created 10-Jan-2005 13:47:49
 *          </p>
 *          <p>
 *          copyright &copy; Zphinx Software Solutions
 *          </p>
 */
public class CommandComponent implements DataTransferAssembler {

    /**
     * 
     */
    private static final long serialVersionUID = 4686318633150086478L;

    /**
     * The DataObject to use to acquire the source of Data
     */
    private Object DataObject = null;

    /**
     * The name of the DataAccessObject Class used for for this operation
     */
    private String dataObjectClass = null;

    /**
     * A relevant object used to store data sent to the persistence layer
     */
    private Object obj = null;

    /**
     * An int specifying the type of persistence layer to use
     */
    private int dataType = 0;

    /**
     * A value object with properties used to initialize a Data Access Object
     */
    private DAOInput daoConstructor = null;

    /**
     * Additional operation to be undertaken by the object using this assembler
     */
    private int operation = 0;

    /**
     * Public constructor
     */
    public CommandComponent() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#getObj()
     */
    public Object getObj() {
        Object o = obj;
        if(obj instanceof DTOWrapper){
            o = ((DTOWrapper) obj).getObject();
        }
        return o;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#setObj(java.lang.Object)
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#getDataObject()
     */
    public Object getDataObject() {
        return DataObject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#setDataObject(java.lang.Object)
     */
    public void setDataObject(Object dataObject) {
        DataObject = dataObject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#getDataType()
     */
    public int getDataType() {
        return dataType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#setDataType(int)
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#getDataObjectClass()
     */
    public String getDataObjectClass() {

        return dataObjectClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.utils.DataTransferAssembler#setDataObjectClass(java.lang.String)
     */
    public void setDataObjectClass(String dataObjectClass) {
        this.dataObjectClass = dataObjectClass;
    }

    /**
     * Gets the DaoConstructor object associated with this object
     * 
     * @return Returns the daoConstructor.
     */
    public DAOInput getDaoConstructor() {
        return daoConstructor;
    }

    /**
     * Sets the DaoConstructor object associated with this object
     * 
     * @param daoConstructor The daoConstructor to set.
     */
    public void setDaoConstructor(DAOInput daoConstructor) {
        this.daoConstructor = daoConstructor;
    }

    /**
     * Gets the present operation been run
     */
    public int getOperation() {

        return operation;
    }

    /**
     * Sets the present operation been run
     * 
     * @param i The value of the operation been run in the several layers where this component been transported
     */
    public void setOperation(int i) {
        this.operation = i;

    }
}