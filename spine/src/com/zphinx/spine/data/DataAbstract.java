/**
 * DataAbstract.java
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

package com.zphinx.spine.data;

import com.zphinx.spine.data.impl.AbstractDataBaseDAO;
import com.zphinx.spine.data.impl.AbstractFileDAO;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * DataAbstract is an implementation of the DataAccessObject which contains base implementations of some the methods in a DataAccessObject.
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 * @see AbstractDataBaseDAO
 * @see AbstractFileDAO
 */
public abstract class DataAbstract implements DataAccessObject {

    /**
     * The type of data store we represent
     */
    private int storeType = 0;

    /**
     * The cumulative errors associated with a call to this data object
     */
    private String errors = null;

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAccessObject#fetchData(java.lang.Object)
     */
    public abstract Object fetchData(DataTransferObject obj);

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAccessObject#getErrors()
     */
    public String getErrors() {
        return errors;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAccessObject#setErrors(java.lang.String)
     */
    public void setErrors(String error) {
        if(this.errors != null){
            this.errors += error + "\n\r";
        }
        else{
            this.errors = error + "\n\r";
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAccessObject#getStoreType()
     */
    public final int getStoreType() {
        return storeType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.DataAccessObject#close()
     */
    public abstract boolean close();

    /**
     * Sets the type of data storage system to use with this object
     * 
     * @param storeType The storeType to set.
     */
    protected void setStoreType(int storeType) {
        this.storeType = storeType;
    }
}
