/**
 * AbstractBuilder.java
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

package com.zphinx.spine.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.vo.ManagedDaoBean;
import com.zphinx.spine.vo.dto.SpineBean;

/**
 * AbstractBuilder is a base builder for use by a MultiViewProcessor. It provides base methods for developers who wish to utilize the dependency injected builder from configuration within the MultiViewProcessor or the associated BusinessDelegate.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class AbstractBuilder {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(AbstractBuilder.class.getName());

    /**
     * 
     */
    public static final String RUN_RESET = "reset";

    /**
     * The delegate which this builder associates with
     */
    private String delegateName = null;

    /**
     * The name of the processor which this builder associates with
     */
    private String processorName = null;

    /**
     * An object used for synchronization
     */
    private Object runObj = null;

    /**
     * An object used for synchronization
     */

    private Object createObj = null;

    /**
     * An object used for synchronization
     */

    private Object initObj = null;

    /**
     * Default Constructor
     */
    public AbstractBuilder() {
        runObj = new Object();
        createObj = new Object();
        initObj = new Object();

    }

    /**
     * Public Constructor - Sets the names of the default associates of this Builder
     * 
     * @param delegateName The name of the delegate which is directly associated to this builder
     * @param processorName The name of the processor which invoked this builder
     */
    public AbstractBuilder(String delegateName, String processorName) {
        super();
        this.delegateName = delegateName;
        this.processorName = processorName;
    }

    /**
     * Invokes the relevant method in this object. The method which will be called is named using the prefix to the method e.g <code>reset</code> and the short/simple name of the objectClass. eg if we have a method <code>resetLetter</code>, set the runType to reset and pass in the full name of the SpineBean.
     * 
     * @param sBean The SpineBean whose methods are to be run
     * @param runType The prefix to the method we wish to run
     * @param wrapper An object array used to convey for any other parameters we wish to pass to our method, null if the method expects no parameters.
     * @throws SpineException The exception thrown if this call fails
     */
    public Object runMethod(final SpineBean sBean, final String runType, final Object[] wrapper) throws SpineException {
        synchronized (runObj){
            
            String objectClass = sBean.getClass().getSimpleName();
            Object obj = null;
            Class[] params = null;
            try{

                if(wrapper != null){
                    params = new Class[wrapper.length];
                    for(int i=0;i<wrapper.length;i++) {
                        params[i] = wrapper[i].getClass();    
                    }
                    
                }
                final Method method = sBean.getClass().getDeclaredMethod(runType , params);
                obj = method.invoke(sBean, wrapper);

            }
            catch (final SecurityException e){
                log.debug("SecurityException: " + e.getMessage());
                throw new SpineException(e.getMessage());

            }
            catch (final NoSuchMethodException e){
                log.debug("No such method error: " + e.getMessage());
                throw new SpineException(e.getMessage());
            }
            catch (final IllegalArgumentException e){
                log.debug("IllegalArgumentException: " + e.getMessage());
                throw new SpineException(e.getMessage());
            }
            catch (final IllegalAccessException e){
                log.debug("IllegalAccessException: " + e.getMessage());
                throw new SpineException(e.getMessage());
            }
            catch (final InvocationTargetException e){
                log.debug("No such method error: " + e.getMessage());
                throw new SpineException(e.getMessage());
            }
            return obj;
        }
    }

    /**
     * Resets the SpineBean by reseting Modified date and the bean's description
     * 
     * @param sBean The SpineBean to reset
     * @param wrapper The object passed to this method, is not used in this implementation
     */
    protected void resetSpineBean(final SpineBean sBean, final Object wrapper) {
        sBean.setDescription("");
        sBean.setModifiedDate(new Date());
    }

    /**
     * Resets the SpineBean by reseting Modified date and the bean's description
     * 
     * @param sBean The SpineBean to reset
     */
    public void resetSpineBean(final SpineBean sBean) {
        sBean.setDescription("");
        sBean.setModifiedDate(new Date());
    }

    /**
     * Creates a SpineBean from the given string
     * 
     * @param s The string which represents the SpineBean type to create
     * @return An initialised SpineBean of the type denoted by string s
     * @throws SpineException
     */
    public SpineBean createBean(final String s) throws SpineException {
        synchronized (createObj){
            SpineBean spineBean;
            try{
                spineBean = (SpineBean) Class.forName(s).newInstance();
            }
            catch (final InstantiationException e){
                throw new SpineException(e.getMessage());
            }
            catch (final IllegalAccessException e){
                throw new SpineException(e.getMessage());
            }
            catch (final ClassNotFoundException e){
                throw new SpineException(e.getMessage());
            }
            return spineBean;
        }
    }

    /**
     * Creates a SpineBean from the given string, the businessDelegate's short class name and the pageIndex of the ManagedBean configured for a MultiViewProcessor.
     * 
     * @param alias The identifier for the DelegateConfiguration, normally the ViewProcessor Name
     * @param delegateName The name identifier of the Delegate sub class or it's short name eg <code>RoleDelegate</code>
     * @param pageIndex The int which is associated with the SpineBean type to create
     * @return An initialised SpineBean of the type denoted by string s
     * @throws SpineException
     */
    private SpineBean createBeanFromIndex(final String alias, final String delegateName, final int pageIndex) throws SpineException {

        SpineBean spineBean = null;
        final SpineConfiguration sc = SpineConfiguration.getInstance(0);
        final ManagedDaoBean[] managedBeans = sc.getManagedBean(alias, delegateName);
        for (final ManagedDaoBean element : managedBeans){
            final int pageInt = element.getPageIndex();
            if(pageInt == pageIndex){
                final String s = element.getObjectClass();
                spineBean = createBean(s);
                break;
            }
        }
        return spineBean;

    }

    /**
     * Creates a Spine bean using the stated index and the default values of the delegateName and processorNames
     * 
     * @param pageIndex The int which is associated with the SpineBean type to create
     * @return An initialised SpineBean of the type denoted by string s
     * @throws SpineException
     */
    public SpineBean createBeanFromIndex(final int pageIndex) throws SpineException {
        synchronized (initObj){
            return createBeanFromIndex(this.processorName, this.delegateName, pageIndex);
        }
    }

    /**
     * Sets the names of the default associates of this Builder
     * 
     * @param delegateName The name of the delegate which is directly associated to this builder
     * @param processorName The name of the processor which invoked this builder
     */
    public void setDefaults(String delegateName, String processorName) {
        this.delegateName = delegateName;
        this.processorName = processorName;

    }
}
