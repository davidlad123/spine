/**
 * ViewProcessor.java
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

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.AbstractBusinessDelegate;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.message.DisplayError;
import com.zphinx.spine.message.ErrorMessages;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.vo.CommandComponent;
import com.zphinx.spine.vo.DAOInput;
import com.zphinx.spine.vo.DataTransferAssembler;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * The ViewProcessor abstracts the view process from any MVC framework in use by the spine framework. It ensures that processes to be invoked in the system are delegated to it and serves as a interface to Spine for controller classes.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Jul 1, 2007 2:32:19 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 * @see DefaultViewProcessor
 * @see MultiViewProcessor
 */
public abstract class ViewProcessor {

    /**
     * 
     */
    protected static final String DATABASE_DATASOURCE = "database.datasource";

    /**
     * 
     */

    protected static final String GENERAL_EXCEPTION = "general.exception";

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(ViewProcessor.class.getName());

    /**
     * The list Object used by this object to persist messages in its result object
     */
    protected ArrayList messages = null;

    /**
     * The new className to set
     */
    protected String[] newClass = null;

    /**
     * The name of the package we wish to use
     */
    private String delegateFullName = null;
    
    /**
     * The name(alias) of this processor
     */
    private String processorAliasName = null;

    /**
     * Default Constructor - instantiates a messages array list
     * 
     */
    public ViewProcessor() {
        super();
        messages = new ArrayList();
    }

    /**
     * Calls the used methods of this View processor and sends the DataTransferAssembler to the business layer
     * 
     * @param dto The DataTransferObject used to create a DataTransferAssembler
     * @param obj The object used by the proxy, usually a dataSource if the proxy type is database
     * 
     * @return An instance of the resultObject obtained from the bussiness layer
     */
    public ResultObject processData(final DataTransferObject dto, final Object obj) {
        return processData(dto, obj,null, -1);
    }

    /**
     * Calls the used methods of this View processor and sends the DataTransferAssembler to the business layer
     * 
     * @param dto The DataTransferObject used to create a DataTransferAssembler
     * @param obj The object used to initialize this transaction, usually a dataSource when a database proxy is in use
     * @param daoConstructor The construct object used to instantiate a dao
     * @param operation The operation to call within the dto
     * @return An instance of the resultObject obtained from the bussiness layer
     */
    public ResultObject processData(final DataTransferObject dto, final Object obj, final DAOInput daoConstructor, final int operation) {
        ResultObject resultObject = new ResultObject();
        final CommandComponent cc = createAssembler(dto, obj, daoConstructor, operation);
        resultObject = delegateFactoryAndRun(cc);
        return resultObject;
    }

    /**
     * <p>
     * Creates a DataTransferAssembler which will be used to ferry objects to and from the back end using the command design pattern.
     * </p>
     * <p>
     * This implementation adds a data object <code>( eg a dataSource )</code> and a dto to a new instance of a CommandComponent and adds a DAOInput object for use by the data access object invoked by the system.
     * </p>
     * <p>
     * client developers wishing to add an additional operation should override this method in their implementation
     * </p>
     * 
     * @param dto The DataTransferObject sent tro this object
     * @param obj The object used to initialize this transaction, usually a dataSource when a database proxy is in use
     * @param daoConstructor The construct object used to instantiate a dao
     * @param operation The operation to call within the dto
     * @return An instance of a command object
     */
    protected CommandComponent createAssembler(final DataTransferObject dto, final Object obj, final DAOInput daoConstructor, final int operation) {
        final CommandComponent cc = new CommandComponent();
        cc.setObj(dto);
        cc.setDataObject(obj);
        if(daoConstructor != null){
            cc.setDaoConstructor(daoConstructor);
        }
        if(operation >= 0){
            cc.setOperation(operation);
        }
        return cc;
    }

    /**
     * Calls the business delegates and runs the process from business layer to the other layers of the application.
     * 
     * @param command The DataTransferAssembler used in this transaction
     * 
     * @return The result object obtained from the transaction
     */
    private ResultObject delegateFactoryAndRun(final DataTransferAssembler command) {

        ResultObject result = null;
        createParams(command);
        
        try{
            result = (ResultObject) getDelegate(command, true);
        }
        catch (final SpineException e){
            messages.add(new DisplayError(ErrorMessages.ERROR_EXCEPTION, GENERAL_EXCEPTION, new String[] { e.getMessage() }));
            result = new ResultObject();
            result.setMessagesList(messages);

        }
        return result;
    }

    /**
     * Uses the returned data to determine which navigational element to return to the user.
     * 
     * @param result The result object obtained from the processData method
     * @return An int specifying the navigation to return to user
     */
    public abstract int processNavigation(ResultObject result);

    /**
     * Create the parameters needed to initialize a AbstractBusinessDelegate
     * 
     * @param command The CommandComponent needed used by this Action
     * 
     */
    private void createParams(final DataTransferAssembler command) {

        final SpineConfiguration sc = SpineConfiguration.getInstance(3);
        String[] className = null;
        if(newClass == null){
            className = sc.getDelegateString(processorAliasName, SpineConfiguration.CONFIG_SUB_CLASS);
        }
        else{ 
            className = newClass;
        }
        final String daoClass = sc.getDAOClass(processorAliasName, className[0]);
        final int daoType = sc.getDAOType(processorAliasName, className[0]);
        setDelegateFullName(className[0]);
        command.setDataObjectClass(daoClass);
        command.setDataType(daoType);
        if(log.isDebugEnabled()){
            log.debug("The new delegateFullName is: " + className[0]);
            log.debug("The new data object is: " + daoClass);
            log.debug("The new dataType is: " + daoType);
        }
        resetPackageName(className);

    }

    /**
     * Gets the appropriate AbstractBusinessDelegate, initializes it and may call its run method if the boolean is set to true
     * 
     * @param command The DataTransferAssembler used to configure the Delegate
     * @param b A boolean dictating if we should call the delegates run method
     * @return The required AbstractBusinessDelegate Object
     * @throws SpineException
     */
    protected Object getDelegate(final DataTransferAssembler command, final boolean b) throws SpineException {

        Object obj = null;
        final AbstractBusinessDelegate delegate = createDelegate();
        if(delegate != null){
            if(!b){
                obj = delegate.init(command);
            }
            else{
                obj = delegate.initAndRun(command);
            }
        }
        return obj;
    }

    /**
     * Creates and initializes the appropriate business delegate
     * 
     * @return an instance of the business delegate to be used by this object
     */
    private AbstractBusinessDelegate createDelegate() {
        AbstractBusinessDelegate delegate = null;
        try{
            delegate = (AbstractBusinessDelegate) Class.forName(delegateFullName).newInstance();
            if(delegate != null) delegate.setProcessorName(this.getProcessorAliasName());
        }
        catch (final InstantiationException e){
            log.error("Error instantiating object: " + e);

        }
        catch (final IllegalAccessException e){
            log.error("Access denied accessing object: " + e);
        }
        catch (final ClassNotFoundException e){
            log.error("Class not found: " + e);
        }
        return delegate;
    }

    /**
     * Reset the delegate names used by this Action
     * 
     * @param className The array of simple Delegate names used by this action
     */

    protected void resetPackageName(final String[] className) {
        int len = 0;

        if(className.length > 1){
            len = className.length - 1;
            if(len > 0){
                newClass = new String[len];
                for (int k = 1; k < className.length; k++){
                    newClass[k - 1] = className[k];
                }
            }
            else{
                resetDelegateName();
            }
        }
        else{
            resetDelegateName();
        }

    }

    /**
     * Resets the delegate in use. Sub classes which utilise multiple delegates should call this method on execution failure to clear all buffers #bug 0011
     */
    protected void resetDelegateName() {
        newClass = null;
    }

    /**
     * Sets the delegateFullName where the delegate resides
     * 
     * @param className The delegateFullName to set.
     */
    protected void setDelegateFullName(final String className) {
        this.delegateFullName = className;
    }

    /**
     * Creates and and initializes a suitable AbstractBusinessDelegate
     * 
     * @param command The CommandComponent used to configure the Delegate
     * @return The required AbstractBusinessDelegate Object
     * @throws SpineException
     */
    protected AbstractBusinessDelegate delegateFactory(final CommandComponent command) throws SpineException {
        createParams(command);
        return (AbstractBusinessDelegate) getDelegate(command, false);
    }

    /**
     * 
     *Gets the processorAliasName
     * @return the processorAliasName
     */
    public String getProcessorAliasName() {
        return processorAliasName;
    }

    /**
     *
     * Sets the processorAliasName
     * @param processorAliasName the processorAliasName to set
     */
    public void setProcessorAliasName(String processorAliasName) {
        this.processorAliasName = processorAliasName;
    }

}
