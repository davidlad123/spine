/**
 * AbstractConfigHelper.java
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

package com.zphinx.spine.start.helpers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zphinx.spine.Universal;

/**
 * AbstractConfigHelper serves as a base helper for objects which read data from an xml configuration file
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public abstract class AbstractConfigHelper {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(AbstractConfigHelper.class.getName());

    /**
     * A HashMap used to temporarily store our configuration data
     */
    protected HashMap hash;

    /**
     * Public Constructor
     */
    public AbstractConfigHelper() {
        super();
    }

    /**
     * Create the configuration objects from the configuration Filename given. This must be the full path to the configuration file
     * 
     * @param fileName The full name and path to the configuration file
     * @return The Hashmap created from the contents of the configuration
     */
    public HashMap createConfig(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return runIterator(fileName, factory);
    }

    /**
     * Create the configuration objects from the configuration Filename given. This must be the full path to the configuration file
     * 
     * @param fileName The full name and path to the configuration file
     * @param validating A boolean indicating if this xml file should be validated
     * @param nameSpaceAware A boolean indicating if this xml file should be set to nameSpaceAware
     * @return The Hashmap created from the contents of the configuration
     */
    public HashMap createConfig(String fileName, boolean validating, boolean nameSpaceAware) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        return runIterator(fileName, factory);
    }

    /**
     * Iterates through the xml file and processes the contents into a HashMap
     * 
     * @param fileName The filename of the xml file
     * @param factory The DocumentBuilderFactory used to create
     * @return The Hashmap created from the contents of the configuration
     */
    private HashMap runIterator(String fileName, DocumentBuilderFactory factory) {
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            Element el = document.getDocumentElement();

            Node node = el;
            node = node.getParentNode();
            NodeList nl = node.getChildNodes();
            hash = new HashMap();
            for (int i = 0; i < nl.getLength(); i++){
                try{
                    recurseNodeTree(nl.item(i));
                }
                catch (Throwable e){
                    if(log.isDebugEnabled()){
                        log.debug("The node length is: " + nl.getLength() + " The node number is: " + i);
                        log.debug("The node is: " + nl.item(i).getNodeName());
                        log.debug("The has been an error in create config: " + e.getMessage());
                    }

                }
            }
        }
        catch (SAXException sxe){
            // Error generated during parsing)
            Exception x = sxe;
            if(sxe.getException() != null) x = sxe.getException();
            x.printStackTrace();

        }
        catch (ParserConfigurationException pce){
            // Parser with specified options can't be built
            pce.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return hash;
    }

    /**
     * Reads a node for it's sub nodes and creates our requisite objects
     * 
     * @param node A method to recurse through the xml node
     * @throws Throwable
     */
    protected void recurseNodeTree(Node node) throws Throwable {
        if(node == null){ return; }
        int type = node.getNodeType();
        readNodeTypes(node, type);

    }

    /**
     * Reads the different types of nodes e.g <code>Element node</code>
     * 
     * @param node The node to read
     * @param type The type of node to be read e.g Node.ELEMENT_NODE
     * @throws NumberFormatException
     * @throws Throwable
     */
    protected abstract void readNodeTypes(Node node, int type) throws NumberFormatException, Throwable;

    /**
     * Loops through the child nodes of this node
     * 
     * @param node The node whose children will be read
     * @throws Throwable
     */
    protected void loopChildNodes(Node node) throws Throwable {
        NodeList childNodes = node.getChildNodes();
        if(childNodes != null){
            int length = childNodes.getLength();
            for (int loopIndex = 0; loopIndex < length; loopIndex++){
                recurseNodeTree(childNodes.item(loopIndex));
            }
        }
    }

    /**
     * Resets the helper variables
     */
    public abstract void resetHelper();

    /**
     * Gets the value of the node data
     * 
     * @param node The node to examine
     * @return The String value of this node
     */
    protected String getNodeData(Node node) {
        String data = null;
        if(node != null){
            data = node.getNodeValue();

            if(data != null){
                data = data.trim();
            }
            else{
                data = "";
            }
        }
        return data;
    }

}
