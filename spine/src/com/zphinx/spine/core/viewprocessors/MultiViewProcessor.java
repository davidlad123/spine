/**
 * MultiViewProcessor.java
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

package com.zphinx.spine.core.viewprocessors;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.AbstractBuilder;
import com.zphinx.spine.core.AbstractBusinessDelegate;
import com.zphinx.spine.data.impl.DataBaseProxy;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.exceptions.SpineRuntimeException;
import com.zphinx.spine.message.DisplayError;
import com.zphinx.spine.message.ErrorMessages;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.vo.CommandComponent;
import com.zphinx.spine.vo.DAOInput;
import com.zphinx.spine.vo.DataTransferAssembler;
import com.zphinx.spine.vo.ManagedDaoBean;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;
import com.zphinx.spine.vo.dto.SpineBean;

/**
 * MultiViewProcessor processes invocations which require multiple delegates and {@link DataTransferObject}s while using a single {@link ViewProcessor}.
 * <p>
 * It allows the programmer access to more than one delegate or process flow from the same {@link ViewProcessor} whilst possessing the ability to use an optional builder to create and run generic methods in each of the generated {@link SpineBean}s.
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultiViewProcessor extends ViewProcessor {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(MultiViewProcessor.class.getName());

    /**
     * Processes the navigation rules for this processor
     * 
     * @param result The resultObject instance to be parsed for information
     */

    public int processNavigation(ResultObject result) {

        final Object obj = (result == null) ? null : result.getObj();
        int i = 0;
        if((obj != null) && (!result.isErrorFlag())){
            i = 1;
        }
        return i;
    }

    /**
     * Calls the methods of this ViewProcessor,creates and sends the {@link DataTransferAssembler} to the business layer
     * 
     * @param dto The {@link DataTransferObject} used to create a {@link DataTransferAssembler}
     * @param obj The object used to initialize this transaction, usually a dataSource when a database proxy is in use
     * @param daoConstructor The construct object used to instantiate a dao
     * @param operation The operation to call within the dto
     * @param pageIndex The int representing the position of the DAO defination in spine.xml
     * @return An instance of the {@link ResultObject} obtained from the bussiness layer
     */

    public ResultObject processData(final DataTransferObject dto, final Object obj, final DAOInput daoConstructor, final int operation, final int pageIndex) {
        ResultObject resultObject = new ResultObject();
        final CommandComponent cc = createAssembler(dto, obj, daoConstructor, operation);
        resultObject = delegateFactoryAndRun(cc, pageIndex);
        return resultObject;
    }

    /**
     * Processes the invocation to the business tier, throws a runtime exception asking the user to call {@link #processData(DataTransferObject, Object, DAOInput, int, int)}
     * 
     * @param dto The {@link DataTransferObject} used to create a {@link DataTransferAssembler}
     * @param obj The object used to initialize this transaction, usually a dataSource when a {@link DataBaseProxy} is in use
     * @param daoConstructor The construct object used to instantiate a dao
     * @param operation The operation to call within the dto
     * @deprecated
     * @return An instance of the {@link ResultObject} obtained from the bussiness layer
     */
    public ResultObject processData(final DataTransferObject dto, final Object obj, final DAOInput daoConstructor, final int operation) {
        throw new SpineRuntimeException("Please use method processData(DataTransferObject, Object, DAOInput, int, int).. ");
    }

    /**
     * Creates and initializes a suitable {@link AbstractBusinessDelegate} then calls the run method of the instantiated Business Delegate.
     * 
     * @param command The {@link DataTransferAssembler} used by this processor
     * @param pageIndex The int representing the position of the DAO defination in spine.xml
     * @return A {@link DataTransferAssembler} in the form of a {@link ResultObject}
     */
    public ResultObject delegateFactoryAndRun(final CommandComponent command, final int pageIndex) {
        ResultObject result = null;
        createParams(command, pageIndex);
        try{
            result = (ResultObject) getDelegate(command, true);
        }
        catch (final SpineException e){
            result = new ResultObject();
            result.addMessageOrError(new DisplayError(ErrorMessages.ERROR_EXCEPTION, GENERAL_EXCEPTION, new String[] { e.getMessage() }));

        }
        return result;
    }

    /**
     * Creates and initializes a suitable {@link AbstractBusinessDelegate}
     * 
     * @param command The {@link DataTransferAssembler} used by this processor
     * @param pageIndex The int representing the position of the DAO defination in spine.xml
     * @return The required {@link AbstractBusinessDelegate} Object
     * @throws SpineException
     */
    public AbstractBusinessDelegate delegateFactory(final CommandComponent command, final int pageIndex) throws SpineException {
        createParams(command, pageIndex);
        return (AbstractBusinessDelegate) getDelegate(command, false);
    }

    /**
     * Create the parameters needed to initialize a {@link AbstractBusinessDelegate}
     * 
     * @param command The {@link DataTransferAssembler} used by this processor
     * @param pageIndex The int representing the position of the DAO defination in spine.xml
     */
    private void createParams(final DataTransferAssembler command, final int pageIndex) {
        final SpineConfiguration sc = SpineConfiguration.getInstance(0);
        String[] className = null;
        if(newClass == null){
            className = sc.getDelegateString(this.getProcessorAliasName(), SpineConfiguration.CONFIG_SUB_CLASS);
        }
        else{
            className = newClass;
        }
        setDelegateFullName(className[0]);

        // delegate set, find pageIndex etc
        final ManagedDaoBean[] managedBeans = sc.getManagedBean(this.getProcessorAliasName(), className[0]);

        for (final ManagedDaoBean mBean : managedBeans){
            if(mBean.getPageIndex() == pageIndex){
                final String daoClass = mBean.getLabel();
                final int daoType = mBean.getIntValue();
                command.setDataObjectClass(daoClass);
                command.setDataType(daoType);
                if(log.isDebugEnabled()){
                    log.debug("The new data object is: " + daoClass);
                    log.debug("The new dataType is: " + daoType);
                }
                break;
            }
        }

        if(log.isDebugEnabled()){
            log.debug("The new delegateFullName is: " + className[0]);

        }
        resetPackageName(className);

    }

    /**
     * Gets the builder for the named BusinessDelegate i.e the delegateSubClass
     * 
     * @param delegateSubClass The full class name of the delegate subclass defined as subClass in configuration
     * @return The {@link AbstractBuilder} Object which was defined in configuration
     */
    protected Object findBuilder(final String delegateSubClass) {
        final SpineConfiguration sc = SpineConfiguration.getInstance(0);
        final String builderName = sc.getBuilder(this.getProcessorAliasName(), delegateSubClass);
        AbstractBuilder builder = null;
        try{
            builder = (AbstractBuilder) Class.forName(builderName).newInstance();
            builder.setDefaults(delegateSubClass, this.getProcessorAliasName());
        }
        catch (final InstantiationException e){
            e.printStackTrace();
        }
        catch (final IllegalAccessException e){
            e.printStackTrace();
        }
        catch (final ClassNotFoundException e){
            e.printStackTrace();
        }

        return builder;
    }

}
