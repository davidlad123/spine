/**
 * AbstractFileDAO.java
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

package com.zphinx.spine.data.impl;

import java.util.Properties;

import com.zphinx.spine.data.DataAbstract;
import com.zphinx.spine.data.AbstractDataProxy;

/**
 * AbstractFileDAO acts as an implementation of a DataAccessObject which uses a file system file for data persistence
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class AbstractFileDAO extends DataAbstract {

    /**
     * The path to the file to use by this object
     */

    private String path = null;

    /**
     * The properties file to use by this object
     */
    private Properties properties = null;

    /**
     * A boolean indicating if we will use a properties file or a plain file
     */
    private boolean propsFile = false;

    /**
     * The byte array created from an inputstream
     */
    private byte[] fileBytes = null;

    /**
     * The flag which indicates if we should persist the File associated with this object
     */
    private boolean writeFlag = false;

    /**
     * Get the output file as a byte[]
     * 
     * @return Returns the fileBytes.
     */
    public byte[] getFileBytes() {
        return fileBytes;
    }

    /**
     * Set the output file as a byte[]
     * 
     * @param fileBytes The fileBytes to set.
     */
    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    /**
     * Gets the path to the file this object uses
     * 
     * @return Returns the path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path to the file this object uses
     * 
     * @param path The path to set.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Get the Properties Object to use with this data class
     * 
     * @return Returns the properties.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Set the Properties Object to use with this data class
     * 
     * @param properties The properties to set.
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Get the boolean which indictates if we initiated a Properties Object.
     * 
     * @return Returns the propsFile.
     */
    public boolean isPropsFile() {
        return propsFile;
    }

    /**
     * Set the boolean which dictates if we initiated a Properties Object.
     * 
     * @param propsFile The propsFile to set.
     */
    public void setPropsFile(boolean propsFile) {
        if(propsFile) setStoreType(AbstractDataProxy.DATA_PROPERTIES_FILE);
        this.propsFile = propsFile;
    }

    /**
     * Get the writeFlag.True if we should persist the contents of the physical file attached to this resource
     * 
     * @return Returns the writeFlag.
     */
    public boolean isWriteFlag() {
        return writeFlag;
    }

    /**
     * Set true if we wish to persist the contents of the physical file attached to this resource
     * 
     * @param writeFlag The writeFlag to set.
     */
    public void setWriteFlag(boolean writeFlag) {
        this.writeFlag = writeFlag;
    }
}