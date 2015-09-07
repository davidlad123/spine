/**
 * ConfigReader.java
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
 * For further information, please go to http://spine.zphinx.co.uk/
 *
 **/

package com.zphinx.spine.start.helpers.impl;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.zphinx.spine.start.helpers.AbstractConfigHelper;

/**
 * ConfigReader helps to read and parse the incomming start configuration file loaded at startup
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Mar 24, 2008 11:10:32 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ConfigReader extends AbstractConfigHelper {

    /**
     * 
     */
    private static final String PROPERTY = "property";

    /**
     * 
     */
    private static final String VALUE = "value";

    /**
     * 
     */
    private static final String NAME = "name";

    /**
     * 
     */
    private static final String FALSE = "false";

    /**
     * 
     */
    private static final String DEFAULT = "default";

    /**
     * 
     */
    private static final String TRUE = "true";

    /**
     * 
     */
    private static final String DATA_SOURCE = "dataSource";

    /**
     * 
     */
    private static final String KEY = "key";

    /**
     * 
     */

    private static final String PLUGIN = "plugin";

    /**
     * The top Node for the DataSource tag
     */
    private Node sourceNode = null;

    /**
     * The key by which the dataSource configuration is known to the system
     */
    private String sourceKey = null;

    /**
     * The map containing child nodes of the DataSource tag
     */
    private Map sourceMap = null;

    /**
     * The top Node for the plugin tag
     */
    private Node pluginNode = null;

    /**
     * The key by which the plugin configuration is known to the system
     */
    private String pluginKey = null;

    /**
     * The map containing child nodes of the plugin tag
     */
    private Map pluginMap = null;

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.start.AbstractConfigHelper#readNodeTypes(org.w3c.dom.Node, int)
     */
    protected void readNodeTypes(final Node node, final int type) throws NumberFormatException, Throwable {

        switch(type){

            case Node.ELEMENT_NODE: {
                String elementName = node.getNodeName();
                if(elementName.equalsIgnoreCase(DATA_SOURCE)){
                    sourceMap = new HashMap();
                    sourceNode = node;
                    sourceKey = saveDefaultKey(sourceMap, sourceNode, DATA_SOURCE);

                }
                else if(elementName.equalsIgnoreCase(PLUGIN)){
                    pluginMap = new HashMap();
                    pluginNode = node;
                    pluginKey = saveDefaultKey(pluginMap, pluginNode, PLUGIN);

                }
                else{
                    final String data = getNodeData(node.getFirstChild());
                    if((elementName != null) && (elementName.trim().length() > 0) ){
                        if(node.getParentNode().equals(sourceNode)){
                            if(elementName.equalsIgnoreCase(PROPERTY)){
                                extractProperty(sourceMap, node);
                            }
                            else if((data != null) && (data.trim().length() > 0)){
                                sourceMap.put(elementName, data);
                            }
                        }
                        else if(node.getParentNode().equals(pluginNode)){
                            if(elementName.equalsIgnoreCase(PROPERTY)){
                                extractProperty(pluginMap, node);
                            }
                            else if((data != null) && (data.trim().length() > 0)){

                                pluginMap.put(elementName, data);
                            }
                        }
                        else  if((data != null) && (data.trim().length() > 0)){
                            hash.put(elementName, data);
                        }

                    }
                }

                break;
            }
            default:
                break;
        }

        if((sourceNode != null) && (sourceKey != null) && (sourceMap != null) && (node.equals(sourceNode.getLastChild()))){
            hash.put(DATA_SOURCE + "-" + sourceKey, sourceMap);
        }
        else if((pluginNode != null) && (pluginKey != null) && (pluginMap != null) && (node.equals(pluginNode.getLastChild()))){
            hash.put(PLUGIN + "-" + pluginKey, pluginMap);
        }
        loopChildNodes(node);

    }

    /**
     * Inserts the contents of the node attributes as a mapped name value pair
     * 
     * @param map The map to insert the property
     * @param node The node containing the data to be inserted
     */
    private void extractProperty(Map map,Node node) {
        try{
            NamedNodeMap nnm = node.getAttributes();
            Node nameNode = nnm.getNamedItem(NAME);
            Node valueNode = nnm.getNamedItem(VALUE);
            String propName = getNodeData(nameNode);
            String propValue = getNodeData(valueNode);
            map.put(propName, propValue);
        }
        catch (Throwable t){
            t.printStackTrace();
        }
    }

    /**
     * Saves the defaultKey in the specified Map
     * 
     * @param node The present node in consideration
     * @return The String which represents the key to the given node
     * @throws Exception
     */
    private String saveDefaultKey(Map map, Node node, String errorTerm) throws Exception {
        String defaultKey = DEFAULT;
        NamedNodeMap nnm = node.getAttributes();
        Node atNode = nnm.getNamedItem(KEY);
        if(atNode == null) throw new Exception("A " + errorTerm + " must have a defined key attribute!!");
        String someKey = getNodeData(atNode);
        Node defNode = nnm.getNamedItem(defaultKey);
        map.put(KEY, someKey);
        if(defNode != null){
            String defaultValue = getNodeData(defNode);
            if((defaultValue == null) || (defaultValue.trim().length() == 0)){
                defaultValue = TRUE;
            }
            else if(!defaultValue.equalsIgnoreCase(TRUE)){
                defaultValue = FALSE;
            }
            map.put(defaultKey, defaultValue);
        }
        return someKey;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.start.AbstractConfigHelper#resetHelper()
     */
    public void resetHelper() {
        this.hash = null;

        this.pluginMap = null;
        this.sourceMap = null;

    }

}
