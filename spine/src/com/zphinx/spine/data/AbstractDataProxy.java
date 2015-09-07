/**
 *
 * AbstractDataProxy.java
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.vo.DAOInput;
import com.zphinx.spine.vo.LabelIntBean;
import com.zphinx.spine.vo.ProxyConfig;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * A skeleton class used to select a suitable data persistence object to use to process the request.Presently it provides definitions for 6 types of persistence systems i.e:
 * <ul>
 * <li>PROPERTIES FILE</li>
 * <li>DATABASE</li>
 * <li>EJB</li>
 * <li>LDAP</li>
 * <li>JAVASPACE</li>
 * <li>HIBERNATE</li>
 * </ul>
 * based data storage or data mapping software.
 * 
 * @author David Ladapo (davidl@zphinx.com)
 * @version 1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */

public abstract class AbstractDataProxy {

    private static Object someObject = new Object();

    /**
     * The constant representing a properties persistence system
     */
    public static final int DATA_PROPERTIES_FILE = 1;

    /**
     * The constant representing a database persistence system
     */
    public static final int DATA_DATABASE = 2;

    /**
     * The constant representing a EJB based persistence system
     */

    public static final int DATA_EJB = 3;

    /**
     * The constant representing a JavaSpace/Jini persistence system
     */

    public static final int DATA_JAVASPACE = 4;

    /**
     * The constant representing an LDAP persistence system
     */

    public static final int DATA_LDAP = 5;

    /**
     * The constant representing an Hibernate driven persistence system
     */
    public static final int DATA_HIBERNATE = 6;

    /**
     * The int which represents the type of persistence system in use
     */

    private int storeType = DATA_DATABASE;

    /**
     * The currently active Data Access object
     */
    private DataAccessObject dao = null;

    /**
     * An arbitary Object used for synchronization
     */
    private Object synchExecute = new Object();

    /**
     * An arbitary Object used for synchronization
     */
    private Object synchInit = new Object();

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(AbstractDataProxy.class.getName());

    /**
     * Initializes all the objects needed by this Abstract Factory, opens the necessary resource and throws an exception when it cannot find a persistence object
     * 
     * @param obj1 A Suitable Object
     * @param className A String representing the type of DAO to create.
     * @param daoCons The DaoConstructor passed to this object
     * @throws SpineApplicationException
     */

    public final boolean init(Object obj1, String className, DAOInput daoCons) throws SpineApplicationException {
        synchronized (synchInit){
            boolean returnFlag = false;
            this.dao = open(obj1, className, daoCons);
            if(dao != null){
                returnFlag = true;
            }
            return returnFlag;
        }
    }

    /**
     * Open the datastore and fetch the correct type of persistence
     * 
     * @param obj Arbitary object to pass to this method
     * @param s A String representing the type of DAO to create.
     * @param daoCons The DaoConstructor passed to this object
     * @return A suitable DataAccessObject object if the operation succeeds
     * @exception Throws a SpineApplicationException if unable to load a the appropriate DataAbstract Object
     */
    protected abstract DataAccessObject open(Object obj, String s, DAOInput daoCons) throws SpineApplicationException;

    /**
     * Execute a process in the data storage object and return a suitable result object
     * 
     * @param dao The DataAccessObject associated with this proxy
     * @param obj The object to be parsed by the DAO
     * @return A Object containing the output of this data process
     * @throws SpineApplicationException
     */
    public final Object execute(DataAccessObject dao, DataTransferObject obj) {
        synchronized (synchExecute){
            return dao.fetchData(obj);
        }

    }

    /**
     * Set the type of persistence system in use by this system. This can be anyone of the 6 types defined in this abstract class or a client programmer implementation
     * 
     * @param i An int representing the storage type
     */
    private void setStoreType(int i) {
        if(i > 0) this.storeType = i;
    }

    /**
     * Gets the type of persistence system in use by this system. Presently properties and database.BMP EJB implementation under development
     * 
     * @return int The concatenation of all the generated errors
     */
    protected int getStoreType() {
        return this.storeType;
    }

    /**
     * The method which returns a handle to the relevant subclass
     * 
     * @param i An int which represents the type of proxy we wish to create
     * @return AbstractDataProxy the object which implements this interface
     */
    public static final AbstractDataProxy getInstance(int i) {
        synchronized (someObject){
            return createProxy(i);
        }
    }

    /**
     * Creates a AbstractDataProxy subclass determined by the int parameter. Use an int value higher than 10 for a non spine defined sub class.
     * 
     * @param proxyIndex The int which determines the proxy to instantiate
     * @return An instantiated AbstractDataProxy
     */
    private static AbstractDataProxy createProxy(int proxyIndex) {
        AbstractDataProxy abstractDataProxy = null;
        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        ProxyConfig config = sc.getProxyConfiguration();
        List aList = config.getProxyList();
        ListIterator lt = aList.listIterator();
        if(proxyIndex <= 0) proxyIndex = 2;
        while (lt.hasNext()){
            LabelIntBean lib = (LabelIntBean) lt.next();
            if(lib.getIntValue() == proxyIndex){
                abstractDataProxy = (AbstractDataProxy) instantiateProxy(lib.getLabel());
                break;
            }
        }
        abstractDataProxy.setStoreType(proxyIndex);
        return abstractDataProxy;
    }

    /**
     * Creates a proxy using the string provided
     * 
     * @param s The string used to create the proxy
     * @return A new proxy object
     */
    private static Object instantiateProxy(String s) {
        Object obj = null;
        try{
            obj = Class.forName(s).newInstance();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Close and dispose of all opened resources
     * 
     * @param dao The DataAccessObject associated with this proxy
     * @throws SpineApplicationException
     */
    public abstract void close(DataAccessObject dao) throws SpineApplicationException;

    /**
     * Gets the currently active DataAccessObject associated with this proxy
     * 
     * @return The currently active DataAccessObject associated with this proxy
     */
    public DataAccessObject getDataAccessObject() {
        return dao;
    }

    /**
     * Creates a new Data Access Object which accepts an object as it's constructor
     * 
     * @param constructor The constructor required by the object implementing the Data Access Object
     * @param arguments The arguements used to create the constructor object
     * @return The object created by this instaiation method
     */
    private Object createObject(Constructor constructor, Object[] arguments) {
        Object object = null;
        try{
            object = constructor.newInstance(arguments);
            if(log.isDebugEnabled()){
                log.debug("Object: " + object.toString());
            }

        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        catch (InvocationTargetException e){
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Create an object which implements a DataAccessObject suitable for performing our data operations
     * 
     * @param className A string representing the type of DataAccessObject we wish to create
     * @param daoCons The DaoConstructor object used by the DAO to instantiate it's type
     * @return The object which represents the Data Access Object we wish to create
     */
    protected Object createDataAccessImpl(String className, DAOInput daoCons) {
        Object obj = null;
        Class objectDefinition;
        try{
            objectDefinition = Class.forName(className);
            if(daoCons != null){
                Class[] argsClass = new Class[] { DAOInput.class };
                Object[] intArgs = new Object[] { daoCons };
                Constructor intArgsConstructor = objectDefinition.getConstructor(argsClass);
                obj = createObject(intArgsConstructor, intArgs);
            }
            else{
                try{
                    obj = objectDefinition.newInstance();
                }
                catch (InstantiationException e){
                    e.printStackTrace();
                }
                catch (IllegalAccessException e){
                    e.printStackTrace();

                }
            }

        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        return obj;

    }

}