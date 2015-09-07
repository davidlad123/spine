/**
 * FileProxy.java
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.data.AbstractDataProxy;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.vo.DAOInput;

/**
 * FileProxy serves as a proxy for DataAccessObjects which require a file or a properties file for data persistence.
 * <p>
 * It automatically retrieves the file at a known file path and loads it into an {@linkplain AbstractFileDAO} either as a byte[] array or as a properties object which can then be used directly by the client programmer.
 * </p>
 * 
 * @author David Ladapo
 * @version 1.0
 *        
 *          <p>
 *          copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class FileProxy extends AbstractDataProxy {
    /**
     * 
     */
    private static final String TRUE = "true";

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(FileProxy.class.getName());

    /**
     * Open the datastore and fetch the correct type of persistence.
     * <p>
     * This method will initialize the file referenced at the stated path, load the file and save it in object format either as a properties file or as a byte[] array in {@linkplain AbstractFileDAO}.<br>
     * <br>
     * This can latter be retrieved by the client programmer who simply makes a call to {@linkplain AbstractFileDAO#getFileBytes()} or {@linkplain AbstractFileDAO#getProperties()} to retrieve the constituents of the file.
     * </p>
     * <p>
     * The developer does not need to write the object to the file system but must ensure that if changes are made to the object form of the file, these changes must be represented in the object form which this proxy will persist automatically.
     * </p>
     * 
     * @param obj1 Arbitary object to pass to this method
     * @param className A String representing the type of DAO to create.
     * @param daoCons The DaoConstructor passed to this object
     * @return A suitable DataAccessObject object if the operation succeeds
     * @exception Throws a SpineApplicationException if unable to load a the appropriate DataAbstract Object
     * @see com.zphinx.spine.data.AbstractDataProxy#open(java.lang.Object, java.lang.String, com.zphinx.spine.vo.DAOInput)
     */

    protected DataAccessObject open(Object obj1, String className, DAOInput daoCons) {
        String[] p = (String[]) obj1;
        String pas = p[0];
        AbstractFileDAO fileAbs = null;

        if((p[0] != null) && (p[1] != null) && (p[0].length() > 0) && (p[1].length() > 0)){

            try{
                fileAbs = (AbstractFileDAO) createDataAccessImpl(className, daoCons);
                if(p[1].equalsIgnoreCase(TRUE)){
                    fileAbs.setPropsFile(true);
                    fileAbs.setProperties(new Properties());
                }
                fileAbs.setPath(pas);
                FileInputStream fil = new FileInputStream(fileAbs.getPath());
                if(fileAbs.isPropsFile()){
                    fileAbs.getProperties().load(fil);
                    fil.close();
                }
                else{

                    BufferedInputStream by = new BufferedInputStream(fil);
                    int i = by.available();
                    byte[] b = new byte[i];
                    i = by.read(b, 0, i);
                    fileAbs.setFileBytes(b);
                    by.close();
                    fil.close();

                }
            }
            catch (java.io.IOException e){
                if(log.isDebugEnabled()){
                    log.debug("IOException from open: " + e.getMessage());
                }
            }
            catch (Exception e){
                if(log.isDebugEnabled()){
                    log.debug("Exception from open: " + e.getMessage());
                }
            }
        }

        return fileAbs;
    }

    /**
     * Close and dispose of all opened resources.
     * <p>
     * Persists the contents of the file or properties file back to the file system. Any changes made to the object obtained from {@linkplain AbstractFileDAO#getProperties()} or {@linkplain AbstractFileDAO#getFileBytes()} will be automatically persisted by this method.
     * </p>
     * 
     * @param dao The DataAccessObject associated with this proxy
     * @throws SpineApplicationException
     * @see com.zphinx.spine.data.AbstractDataProxy#close(com.zphinx.spine.data.DataAccessObject)
     */
    public void close(DataAccessObject dao) {

        AbstractFileDAO fileAbs = (AbstractFileDAO) dao;

        try{
            if(fileAbs.isWriteFlag()){
                FileOutputStream fos = new FileOutputStream(fileAbs.getPath());
                if(fileAbs.isPropsFile()){
                    fileAbs.getProperties().store(fos, "Saved To System by FileProxy !!");
                    fos.close();
                }
                else{
                    BufferedOutputStream bout = new BufferedOutputStream(fos);
                    bout.write(fileAbs.getFileBytes(), 0, fileAbs.getFileBytes().length);
                    bout.close();
                    fos.close();
                }
            }

        }
        catch (java.io.IOException e){
            if(log.isDebugEnabled()){
                log.debug("IOException from close: " + e.getMessage());
            }
        }
        catch (Exception e){
            if(log.isDebugEnabled()){
                log.debug("Exception from close: " + e.getMessage());
            }
        }
        fileAbs.close();
        if(fileAbs != null) fileAbs = null;
        if(dao != null){
            dao.close();
            dao = null;
        }

    }

}