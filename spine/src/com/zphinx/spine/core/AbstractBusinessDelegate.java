/**
 * AbstractBusinessDelegate.java
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

import java.util.Locale;

import com.zphinx.spine.data.AbstractDataProxy;
import com.zphinx.spine.data.DataAccessObject;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.start.SpineConfiguration;
import com.zphinx.spine.vo.DataTransferAssembler;
import com.zphinx.spine.vo.ManagedDaoBean;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * Core class used by the business tier to access business information and process activities.
 * <p>
 * It forms the core of the business layer and client programmers are advised to write all processing and business rules within the sub classes of this object to make their code more transparent to the view.
 * </p>
 * 
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          created 10-Jan-2005 16:03:12
 *          </p>
 *          <p>
 *          copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class AbstractBusinessDelegate {

    private static final Object initObject = new Object();

    private static final Object initThisObject = new Object();

    private static final Object initAndRunObject = new Object();

    private String processorName = null;

    /**
     * The DataTransferAssembler which contains this BusinessDelegates requisite objects
     */
    private transient DataTransferAssembler command = null;

    /**
     * The Proxy which creates and manages the data access object
     */
    private transient AbstractDataProxy abstractDataProxy = null;

    /**
     * A boolean indicating that this object is initialised
     */
    private boolean isInit = false;

    /**
     * Initializes this AbstractBusinessDelegate,invokes the preconfigured proxy and prepares it for further processing.
     * <p>
     * This method should be overidden if the use of a DataAccessObject is not necessary.
     * 
     * @param command The CommandComponent used by this AbstractBusinessDelegate
     * @throws SpineException Wrap errors in the message driven SpineException
     */
    protected void initialize(final DataTransferAssembler command) throws SpineException {
        // initialize objects
        synchronized (initObject){
            this.command = command;
            AbstractDataProxy newProxy = AbstractDataProxy.getInstance(command.getDataType());
            //
            try{
                isInit = (newProxy.init(command.getDataObject(), command.getDataObjectClass(), command.getDaoConstructor()));
                // close any previous proxies if they have not been closed!!
                if(this.getDataProxy() != null){
                    this.getDataProxy().close(this.abstractDataProxy.getDataAccessObject());
                }
                this.abstractDataProxy = newProxy;
            }
            catch (SpineApplicationException e1){

                throw new SpineException(e1.getMessage(), e1);
            }

        }

    }

    /**
     * Processes the call to {@link DataAccessObject#fetchData(DataTransferObject)} and returns the Object which is the result of this invocation.
     * 
     * @return The object returned from the DAO
     * @throws SpineException
     */
    protected Object processDAO() throws SpineException {
        try{
            DataAccessObject dao = this.getDataProxy().getDataAccessObject();
            DataTransferObject dto  = (DataTransferObject) this.getCommand().getObj();
            return dao.fetchData(dto);
        }
        catch (Exception e1){

            throw new SpineException(e1.getMessage(), e1);
        }
    }

    /**
     * Initializes this AbstractBusinessDelegate and returns a copy to the user
     * 
     * @param command The CommandComponent used by this AbstractBusinessDelegate
     * @throws SpineException
     */

    public final AbstractBusinessDelegate init(final DataTransferAssembler command) throws SpineException {
        synchronized (initThisObject){
            initialize(command);
        }
        return this;
    }

    /**
     * Performs initialization and runs the AbstractBusinessDelegate methods
     * <p>
     * <ol>
     * <li>{@link AbstractBusinessDelegate#preProcessBusinessRules(DataTransferAssembler)}</li>
     * <li>{@link AbstractBusinessDelegate#run()}</li>
     * <li>{@link AbstractBusinessDelegate#postProcessBusinessRules(ResultObject, DataTransferAssembler)}</li>
     * </ol>
     * in the order shown.
     * </p>
     * If calling run directly, you must directly invoke the methods {@linkplain AbstractBusinessDelegate#preProcessBusinessRules(DataTransferAssembler)} and {@linkplain AbstractBusinessDelegate#postProcessBusinessRules(ResultObject, DataTransferAssembler)} yourself. You must also ensure that you close the DataProxy to clean up resources by
     * invoking {@linkplain AbstractDataProxy#close(DataAccessObject)}.
     * 
     * @param command The CommandComponent used by this AbstractBusinessDelegate
     * @return Returns the ResultObject which contains the components needed by the Action.
     * @throws SpineException Wrap errors in the SpineException
     */
    public final ResultObject initAndRun(final DataTransferAssembler command) throws SpineException {
        ResultObject results1 = null;
        synchronized (initAndRunObject){
            initialize(command);
            if(isInit && preProcessBusinessRules(command)){
                results1 = run();
                this.getDataProxy().close(this.abstractDataProxy.getDataAccessObject());
                if(!results1.isErrorFlag()) results1 = postProcessBusinessRules(results1, command);
            }
        }
        return results1;
    }

    /**
     * Run all business rules associated with this delegate after processing in the data tier.
     * 
     * @param results1 The ResultObject obtained from the {@link AbstractBusinessDelegate#run()} method
     * @param command1 The CommandComponent used by this AbstractBusinessDelegate
     * @return The result object containing the results of this invocation
     * @throws SpineException Wrap errors in the message driven SpineException
     */
    protected ResultObject postProcessBusinessRules(ResultObject results1, DataTransferAssembler command1) throws SpineException {
       
        return results1;
    }

    /**
     * Run all business rules associated with this delegate before processing in the backend
     * 
     * @param command The command object associated with this delegate
     * @return true if all business rules are met
     * @throws SpineException Wrap errors in the message driven SpineException
     */
    protected boolean preProcessBusinessRules(DataTransferAssembler command) throws SpineException {

        return true;
    }

    /**
     * Performs the activities required by this delegate, then calls the close method of AbstractDataProxy
     * 
     * @return Returns the ResultObject which contains the components obtained from the process flow.
     */
    protected abstract ResultObject run();

    /**
     * Gets the DataTransferAssembler which configured this AbstractBusinessDelegate
     * 
     * @return Returns the command.
     */
    protected DataTransferAssembler getCommand() {
        return command;
    }

    /**
     * Gets the AbstractDataProxy in use by this delegate
     * 
     * @return Returns the abstractDataProxy.
     */
    protected AbstractDataProxy getDataProxy() {
        return abstractDataProxy;
    }

    /**
     * Gets the builder for this BusinessDelegate as defined in the configuration file
     * 
     * @return The AbstractBuider Object which was defined in configuration
     */
    protected Object findBuilder() throws SpineException {
        String delegateSubClass = this.getClass().getSimpleName();
        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        String builderName = sc.getBuilder(processorName, delegateSubClass);
        AbstractBuilder builder = null;
        try{
            builder = (AbstractBuilder) Class.forName(builderName).newInstance();
            builder.setDefaults(delegateSubClass, processorName);
        }
        catch (InstantiationException e){
            throw new SpineException(e.getMessage());
        }
        catch (IllegalAccessException e){
            throw new SpineException(e.getMessage());
        }
        catch (ClassNotFoundException e){
            throw new SpineException(e.getMessage());
        }

        return builder;
    }

    /**
     * <p>
     * Re initalizes this BusinessDelegate to use a new DataProxy and DataAccessObject where the processor is a MultiView Processor.
     * </p>
     * <p>
     * This method will not work for a simpl processor instance
     * </p>
     * 
     * @param pageIndex The pageIndex of the DAO instance in configuration
     * @return An initialized DataAccessObject which can be used to retrieve or persist data.
     * @throws SpineException
     */
    protected final DataAccessObject initializeNewDAO(int pageIndex) throws SpineException {
        synchronized (initThisObject){
            final SpineConfiguration sc = SpineConfiguration.getInstance(1);
            String[] className = null;
            String delegateFullName = this.getClass().getName();
            final ManagedDaoBean[] managedBeans = sc.getManagedBean(this.processorName, delegateFullName);
            boolean success = false;
            for (final ManagedDaoBean mBean : managedBeans){
                if(mBean.getPageIndex() == pageIndex){
                    final String daoClass = mBean.getLabel();
                    final int daoType = mBean.getIntValue();
                    if((daoClass != null) && (daoClass.trim().length() > 0) && (daoType > 0)){
                        command.setDataObjectClass(daoClass);
                        command.setDataType(daoType);
                        success = true;
                    }

                    break;
                }
            }
            initialize(command);
            return this.getDataProxy().getDataAccessObject();
        }

    }

    /**
     * Sets the processorName
     * 
     * @param processorName the processorName to set
     */
    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    /**
     * Gets the current users Locale from the DataTransferObject passed to this BusinessDelegate, otherwise returns the default Localein which this framework is been invoked
     * @return The current users Locale
     */
    protected Locale getUserLocale() {
        Locale locale = Locale.getDefault();
        DataTransferObject dto = (DataTransferObject) this.getCommand().getObj();
        if(dto != null) locale =  dto.getLocale();
        return locale;
    }

}