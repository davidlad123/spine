/**
 * DefaultDataSourceBuilder.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * Copyright &copy;Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE ZPHINX SOFTWARE SOLUTIONS 
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
 * For further information, please go to http://www.zphinx.co.uk/spine/
 *
 **/

package com.zphinx.spine.start.helpers.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import com.zphinx.spine.start.helpers.DataSourceBuilder;

/**
 * DefaultDataSourceBuilder is a DataSourceBuilder that attempts to initialize a dataSOurce based on the properties specified by a user in configuration. It requires the user to use the tag names as expected properties of the DataSource to be initialized, otherwise it will fail.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DefaultDataSourceBuilder implements DataSourceBuilder {

    /**
     * 
     */
    private static final String SET = "set";

    /**
     * 
     */
    private static final String DEFAULT = "default";

    /**
     * 
     */
    private static final String KEY = "key";

    /**
     * 
     */
    private static final String DATA_SOURCE_BUILDER = "dataSourceBuilder";

    /**
     * 
     */
    private static final String SOURCE_CLASS = "sourceClass";

    /**
     * Public Constructor
     */
    public DefaultDataSourceBuilder() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.start.helpers.DataSourceBuilder#createDataSource(java.util.Map)
     */
    public DataSource createDataSource(Map map) {
        map.remove(DATA_SOURCE_BUILDER);
        map.remove(KEY);
        map.remove(DEFAULT);
        return initializeDataSource((String) map.remove(SOURCE_CLASS), map);
    }

    /**
     * Creates an initializes a dataSource
     * 
     * @param sourceClass The string representing the dataSource to initialize
     * @param map The map containing the names of the dataSOurce properties
     * @return An initialized dataSource
     */
    private DataSource initializeDataSource(String sourceClass, Map map) {
        Object object = null;
        try{
            object = Class.forName(sourceClass).newInstance();
            Class dataSourceClass = object.getClass();
            Class subClass = dataSourceClass.asSubclass(Class.forName(sourceClass));

            Iterator it = map.keySet().iterator();
            while (it.hasNext()){
                String key = (String) it.next();
                Object value = map.get(key);
                try{
                    invokeMethod(subClass, key, value, object);

                }
                catch (SecurityException e){

                    e.printStackTrace();
                }

            }
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
        return (DataSource) object;
    }

    /**
     * Iterates though all the given parameter types to see if we have the stated parameter
     * 
     * @param subClass The Subclass of the DataSource implementation
     * @param key The key by which this property is known in configuration
     * @param value The value of the stated key
     * @return A suitable method
     * @throws IllegalAccessException
     */
    private void invokeMethod(Class subClass, String key, Object value, Object object) throws IllegalAccessException {
        Method method = null;
        Class[][] allTypes = new Class[][] { new Class[] { String.class }, new Class[] { int.class }, new Class[] { long.class }, new Class[] { float.class }, new Class[] { double.class } };
        key.trim();
        String keyString = key.substring(0, 1).toUpperCase() + key.substring(1);

        for (int i = 0; i < allTypes.length; i++){
            try{
                method = subClass.getMethod(SET + keyString, allTypes[i]);
            }
            catch (SecurityException e){
                // since we are performing a trial and error algo... we do nothing with the exceptions
                // e.printStackTrace();
            }
            catch (NoSuchMethodException e){
                // since we are performing a trial and error algo... we do nothing with the exceptions
            }
            if(method != null){
                try{
                    switch(i){

                        case 1:
                            value = new Integer((String) value);
                            break;
                        case 2:
                            value = new Long((String) value);
                            break;
                        case 3:
                            value = new Float((String) value);
                            break;
                        case 4:
                            value = new Double((String) value);
                        default:
                            break;
                    }
                    method.invoke(object, new Object[] { value });
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }
                catch (IllegalArgumentException e){
                    e.printStackTrace();
                }
                catch (InvocationTargetException e){
                    e.printStackTrace();
                }

                break;
            }
        }

    }

}
