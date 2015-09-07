/**
 * SpineConfiguration.java
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

package com.zphinx.spine.start;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.zphinx.spine.vo.DelegateBean;
import com.zphinx.spine.vo.LabelIntBean;
import com.zphinx.spine.vo.ManagedDaoBean;
import com.zphinx.spine.vo.MultiDelegateBean;
import com.zphinx.spine.vo.ProxyConfig;

/**
 * SpineConfiguration maintains the properties available to configure ViewProcessors,BusinessDelegates and their associated DataAccessObjects.<br/>
 * <p>
 * These properties are defined in the spine.xml file used to configure this application and they provide inversion of control(IOC) capabilites to the spine application framework by allowing the user change, delegates,processors,proxies and dao's associated with a process call.
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0:
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpineConfiguration {

    /**
     * The map of DelegateConfiguration objects registered with this system
     */
    private static Map processorMaps = null;

    /**
     * The only instance of this object
     */
    private static SpineConfiguration spineConfig = null;

    /**
     * The object used to acquire a lock
     */
    private static Object objLock = null;

    /**
     * Another object used to acquire a lock
     */
    private static Object objLock2 = null;

    /**
     * An int used to represent a packageName
     */
    public static final int CONFIG_PACKAGE_NAME = 1;

    /**
     * An int used to represent a subClass
     */
    public static final int CONFIG_SUB_CLASS = 2;

    /**
     * The ProxyConfig associated with this configuration
     */
    private static ProxyConfig proxyConfig;

    /**
     * Private Constructor
     */
    private SpineConfiguration(final int i) {
        super();
        if(processorMaps == null){
            if(i > 0){

                processorMaps = Collections.synchronizedMap(new HashMap(i));
            }
            else{
                processorMaps = Collections.synchronizedMap(new HashMap());
            }
        }
        else if(i == -1){
            processorMaps = null;
        }
        objLock = new Object();
        objLock2 = new Object();

    }

    /**
     * Copies the contents of map m to this object
     * 
     * @param m The map to copy into this map
     */
    public void putAll(final Map m) {

        processorMaps.putAll(m);
    }

    /**
     * Gets the string array which denotes the full name of the sub class of the delegates used by this configuration.
     * 
     * @param s The identifier for the DelegateConfiguration
     * @param j An int representing the kind of strings to get from the DelegateBean
     * @return The array of Strings which denotes the ids of the delegates contained in this DelegateConfiguration
     */
    public String[] getDelegateString(final String s, final int j) {
        synchronized (objLock){
            String[] outPut = null;
            if(processorMaps.containsKey(s)){
                final DelegateConfiguration ac = (DelegateConfiguration) processorMaps.get(s);
                final DelegateBean[] dBean = ac.getDataBean();
                outPut = new String[dBean.length];
                for (int i = 0; i < dBean.length; i++){
                    switch(j){
                        case CONFIG_SUB_CLASS:
                            outPut[i] = dBean[i].getSubClass();
                        default:
                            break;
                    }
                }
            }
            return outPut;
        }
    }

    /**
     * Gets the DAO class of the key associated the delegateName
     * 
     * @param key The identifier for the DelegateConfiguration
     * @param delegateName The name identifier of the Delegate sub class
     * @return The name of the DAO class
     */
    public String getDAOClass(final String key, final String delegateName) {
        synchronized (objLock2){
            String output = null;
            final LabelIntBean dao = getDAO(key, delegateName);
            if(dao != null){
                output = dao.getLabel();
            }
            return output;
        }

    }

    /**
     * Gets the LabelIntBean associated with the delegateName
     * 
     * @param key The identifier for the DelegateConfiguration
     * @param delegateName The name identifier of the Delegate sub class
     * @return The LabelIntBean associated with this delegateName
     */
    private LabelIntBean getDAO(final String key, final String delegateName) {
        LabelIntBean dao = null;
        synchronized (objLock){
            if(processorMaps.containsKey(key)){
                final DelegateConfiguration ac = (DelegateConfiguration) processorMaps.get(key);
                final DelegateBean[] dBean = ac.getDataBean();

                for (final DelegateBean element : dBean){
                    if(element.getSubClass().equals(delegateName)){
                        dao = element.getDaoBean();
                        break;
                    }
                }

            }

            return dao;
        }
    }

    /**
     * Gets the ManagedDaoBean[] associated with the delegateName
     * 
     * @param key The identifier for the DelegateConfiguration
     * @param delegateName The name identifier of the Delegate sub class
     * @return The LabelIntBean associated with this delegateName
     */
    public ManagedDaoBean[] getManagedBean(final String key, final String delegateName) {
        ManagedDaoBean[] managedBean = null;
        synchronized (objLock){
            if(processorMaps.containsKey(key)){
                final DelegateConfiguration ac = (DelegateConfiguration) processorMaps.get(key);
                final DelegateBean[] dBean = ac.getDataBean();

                for (final DelegateBean element : dBean){
                    if(element.getSubClass().equals(delegateName) || element.getSubClass().endsWith(delegateName)){
                        final MultiDelegateBean ddBean = (MultiDelegateBean) element;
                        managedBean = ddBean.getManagedBean();
                        break;
                    }
                }

            }

            return managedBean;
        }
    }

    /**
     * Gets the type of the LabelIntBean associated the delegateName
     * 
     * @param key The identifier for the DelegateConfiguration
     * @param delegateName The name identifier of the Delegate sub class
     * @return The type of the DataAccessObject to create
     */
    public int getDAOType(final String key, final String delegateName) {
        synchronized (objLock2){
            int k = 0;
            final LabelIntBean dao = getDAO(key, delegateName);
            if(dao != null){
                k = dao.getIntValue();
            }
            return k;
        }

    }

    /**
     * Gets the DelegateConfiguration object identified by this key
     * 
     * @param key The key which identifies the DelegateConfiguration
     * @return The DelegateConfiguration object associated with this key
     */
    public DelegateConfiguration get(final String key) {
        synchronized (objLock){
            DelegateConfiguration ac = null;
            if(processorMaps.containsKey(key)){
                ac = (DelegateConfiguration) processorMaps.get(key);
            }
            return ac;
        }
    }

    /**
     * Add a DelegateConfiguration object to this collection
     * 
     * @param key The key which identifies this object
     * @param delegateConfig DelegateConfiguration object to register with this System
     */
    public void put(final String key, final DelegateConfiguration delegateConfig) {
        processorMaps.put(key, delegateConfig);
    }

    /**
     * Gets the only instance of this object
     * 
     * @param i An int representing the size of the Map contained in this object, set to zero to create a non specified size.
     * @return An instance of this object
     */
    public static SpineConfiguration getInstance(final int i) {
        if(spineConfig == null){

            spineConfig = new SpineConfiguration(i);
        }
        return spineConfig;

    }

    /**
     * Gets the builder instance in use by this configuration
     * 
     * @param key The key to the processor class that owns this configuration
     * @param delegateName The name of the delegate containing the builder instance.
     * @return The name of the builder in use by this system
     */

    public String getBuilder(final String key, final String delegateName) {
        String builderName = null;
        synchronized (objLock){

            if(processorMaps.containsKey(key)){
                final DelegateConfiguration ac = (DelegateConfiguration) processorMaps.get(key);
                final DelegateBean[] dBean = ac.getDataBean();

                for (final DelegateBean element : dBean){
                    if((element.getSubClass().equals(delegateName)) || (element.getSubClass().endsWith(delegateName))){
                        final MultiDelegateBean ddBean = (MultiDelegateBean) element;
                        builderName = ddBean.getBuilderName();
                        break;
                    }
                }

            }

            return builderName;
        }

    }

    /**
     * Gets the ProxyConfig Object
     * 
     * @return the ProxyConfig Object
     */
    public ProxyConfig getProxyConfiguration() {

        return proxyConfig;
    }

    /**
     * Sets the ProxyConfig Object
     * 
     * @param pConfig the ProxyConfig Object to set
     */
    public void setProxyConfiguration(final ProxyConfig pConfig) {
        proxyConfig = pConfig;

    }
}