/**
 * DataBaseProxy.java
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

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import com.zphinx.spine.Universal;
import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.data.AbstractDataProxy;
import com.zphinx.spine.vo.DAOInput;

/**
 * DataBaseProxy acts as a proxy object to DataAccessObjects driven by Databases. it initiates and closes the necessary connections used by the DataAccessObject leaving the dao free to perform it's predefined functionality without the overhead of opening and closing connections
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          Created 14-Jan-2005 02:43:26 <br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DataBaseProxy extends AbstractDataProxy {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(DataBaseProxy.class.getName());

    /**
     * Initialize a Data Abstract storage object. Open the datastore and fetch the correct type of persistence
     * 
     * @param obj1 Arbitary object to pass to this method
     * @param className A String representing the type of DAO to create.
     * @param daoCons The DaoConstructor passed to this object
     * @return A suitable DataAccessObject object if the operation succeeds
     * @exception Throws a SpineApplicationException if unable to load a the appropriate DataAbstract Object
     */

    protected DataAccessObject open(Object obj1, String className, DAOInput daoCons) {

        AbstractDataBaseDAO da = null;
        try{

            DataSource dataSource = (DataSource) obj1;
            if(log.isDebugEnabled()){
                log.debug("The dataSource is: " + dataSource + " the object class is: " + className);
            }
            if(dataSource != null){
                Connection myConnection = dataSource.getConnection();
                if(myConnection != null){
                    if(daoCons != null){
                        if(daoCons instanceof DataBaseHelper){
                            DataBaseHelper dab = (DataBaseHelper) daoCons;
                            dab.setConnection(myConnection);
                        }
                        da = (AbstractDataBaseDAO) createDataAccessImpl(className, daoCons);
                    }
                    else{
                        da = (AbstractDataBaseDAO) createDataAccessImpl(className, null);
                    }
                    da.setConnection(myConnection);
                    if(log.isDebugEnabled()){
                        log.debug("Successfully created a DAO: " + da);
                    }
                }

            }
            else{
                if(log.isDebugEnabled()){
                    log.debug("Error form DataBaseProxy: The dataSource returned null");
                }

            }

        }
        catch (ClassCastException e){
            if(log.isDebugEnabled()){
                log.debug("Class cast error from DataBaseProxy: " + e.getMessage());
            }

        }
        catch (SQLException e){
            if(log.isDebugEnabled()){
                log.debug("SQL error from DataBaseProxy: " + e.getMessage());
            }

        }
        catch (Exception e){
            if(log.isDebugEnabled()){
                log.debug("Error from DataBaseProxy: " + e.getMessage());
            }
        }
        return da;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.data.AbstractDataProxy#close()
     */
    public void close(DataAccessObject dao) {
        dao.close();
        if(dao != null) dao = null;
    }

}