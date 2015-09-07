/**
 * ConfigurationHelper.java
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

package com.zphinx.spine.start.helpers.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.zphinx.spine.Universal;
import com.zphinx.spine.start.DelegateConfiguration;
import com.zphinx.spine.start.helpers.AbstractConfigHelper;
import com.zphinx.spine.vo.DelegateBean;
import com.zphinx.spine.vo.LabelIntBean;
import com.zphinx.spine.vo.ManagedDaoBean;
import com.zphinx.spine.vo.MultiDelegateBean;
import com.zphinx.spine.vo.ProxyConfig;

/**
 * ConfigurationHelper creates the Hashmap used by the SpineConfiguration helper from a known configuration file
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 14, 2007 3:42:31 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigurationHelper extends AbstractConfigHelper {

    /**
     * 
     */
    private static final String INDEX = "index";

    /**
     * 
     */
    private static final String BUILDER = "builder";

    /**
     * 
     */
    private static final String PAGE_INDEX = "pageIndex";

    /**
     * 
     */
    private static final String PROXY_INDEX = "proxyIndex";

    /**
     * 
     */
    private static final String CLASS_NAME = "className";

    /**
     * 
     */
    private static final String DATA_ACCESS_OBJECT = "dataAccessObject";

    /**
     * 
     */
    private static final String OBJECT_CLASS = "objectClass";

    /**
     * 
     */
    private static final String MANAGED_OBJECT = "managedObject";

    /**
     * 
     */
    private static final String SUBCLASS = "subclass";

    /**
     * 
     */
    private static final String MULTI_DELEGATE = "multiDelegate";

    /**
     * 
     */
    private static final String DELEGATE = "delegate";

    /**
     * 
     */
    private static final String PROCESSOR_CLASS = "processorClass";

    /**
     * 
     */
    private static final String NAME = "name";

    /**
     * 
     */
    private static final String DATA_PROXIES = "dataProxies";

    /**
     * 
     */
    private static final String DATA_PROXY = "dataProxy";

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(ConfigurationHelper.class.getName());

    /**
     * An instance of the DelegateBean
     */
    private DelegateBean delegateBean = null;

    /**
     * An instance of the LabelIntBean
     */
    private LabelIntBean dao = null;

    /**
     * An instance of the DelegateConfiguration
     */
    private DelegateConfiguration dc = null;

    /**
     * A string used as a key
     */
    private String processor = null;

     /**
     * A managedBean instance
     */
    private ManagedDaoBean managedBean = null;

    /**
     * The proxyconfig object used in this configuration
     */
    private ProxyConfig proxyConfig = null;

    /**
     * Public Constructor
     */
    public ConfigurationHelper() {
        super();
    }

    /**
     * Create the configuration objects from the configuration Filename given. This must be the full path to the configuration file
     * 
     * @param fileName The name/path to the configuration file
     */
    public HashMap createConfig(String fileName) {
        hash = super.createConfig(fileName);
        hash.put("proxyConfig", proxyConfig);
        return hash;
    }

    /**
     * Resets the helper variables
     */
    public void resetHelper() {
        // clean up
        this.processor = null;
        this.dao = null;
        this.delegateBean = null;
        this.managedBean = null;
        this.dc = null;
        this.hash = null;
        this.proxyConfig = null;

    }

    @Override
    protected void readNodeTypes(Node node, int type) throws NumberFormatException, Throwable {

        switch(type){

            // handle element nodes
            case Node.ELEMENT_NODE: {
                String elementName = node.getNodeName();

                String attrib = null;
                String data = getNodeData(node.getFirstChild());
                if((!elementName.equals(DATA_PROXY)) && (!elementName.equals(DATA_PROXIES))){
                    if(node.hasAttributes()){
                        Element ele = (Element) node;
                        attrib = ele.getAttribute(NAME);
                        processor = attrib;
                        dc = new DelegateConfiguration();

                    }
                    else if(elementName.equals(PROCESSOR_CLASS)){

                        // Adds the processor class node
                      
                        dc.setProcessorClass(data);
                    }
                    else if(elementName.equals(DELEGATE)){

                        // create delegate object i.e currently processing
                        delegateBean = new DelegateBean();
                    }
                    else if(elementName.equals(MULTI_DELEGATE)){
                        // create delegate object i.e currently processing
                        delegateBean = new MultiDelegateBean();
                    }
                    else if(elementName.equals(SUBCLASS)){
                        // create dao ... currently processing
                        delegateBean.setSubClass(data);
                    }
                    else if(elementName.equals(MANAGED_OBJECT)){
                        // create dao ... currently processing
                        managedBean = new ManagedDaoBean();
                    }
                    else if(elementName.equals(OBJECT_CLASS)){
                        // create dao ... currently processing

                        managedBean.setObjectClass(data);
                    }
                    else if(elementName.equals(DATA_ACCESS_OBJECT)){
                        // create dao ... currently processing
                        if(!(delegateBean instanceof MultiDelegateBean)){
                            dao = new LabelIntBean();
                        }
                        else{

                        }
                    }

                    else if(elementName.equals(CLASS_NAME)){
                        if(!(delegateBean instanceof MultiDelegateBean)){
                            dao.setLabel(data);
                        }
                        else{
                            managedBean.setLabel(data);
                        }
                    }
                    else if(elementName.equals(PROXY_INDEX)){

                        if(!(delegateBean instanceof MultiDelegateBean)){
                            dao.setIntValue(Integer.parseInt(data));
                            delegateBean.setDaoBean(dao);
                            dc.add(delegateBean);
                            delegateBean = null;
                            hash.put(processor, dc);
                        }
                        else{
                            managedBean.setIntValue(Integer.parseInt(data));
                        }
                    }
                    else if(elementName.equals(PAGE_INDEX)){
                        // create dao ... currently processing
                        managedBean.setPageIndex(Integer.parseInt(data));
                        delegateBean.setDaoBean(managedBean);

                    }
                    else if(elementName.equals(BUILDER) && (delegateBean instanceof MultiDelegateBean)){
                        MultiDelegateBean disDel = (MultiDelegateBean) delegateBean;
                        if(elementName.equals(BUILDER)){
                            disDel.setBuilderName(data);
                        }
                        // delegateBean = disDel;
                        dc.add(disDel);
                        // delegateBean = null;
                        hash.put(processor, dc);
                    }

                }
                else{
                    if(elementName.equals(DATA_PROXIES)){
                        // processor = attrib;
                        proxyConfig = new ProxyConfig();

                    }
                    else if(elementName.equals(DATA_PROXY)){

                        if(node.hasAttributes()){
                            Element ele = (Element) node;
                            String proxyName = ele.getAttribute(NAME);
                            String s = ele.getAttribute(INDEX);
                            int intValue = 0;
                            try{
                                intValue = Integer.parseInt(s);
                            }
                            catch (NumberFormatException e){
                                log.debug("Error gettting int value: " + s);
                            }
                            LabelIntBean lib = new LabelIntBean(proxyName, intValue);
                            proxyConfig.addProxy(lib);
                        }
                    }
                }

                break;
            }
            default:
                break;
        }

        // log.debug("The element is: "+elementName);
        loopChildNodes(node);

    }

}
