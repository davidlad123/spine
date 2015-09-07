/**
 * RoleConfigHelper.java
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

import org.w3c.dom.Node;

import com.zphinx.spine.Universal;
import com.zphinx.spine.members.Application;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.members.Member;
import com.zphinx.spine.start.helpers.AbstractConfigHelper;
import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * RoleConfigHelper reads an xml file of role data from the filesystem, this is used for initialization purposes.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class RoleConfigHelper extends AbstractConfigHelper {

    /**
     * 
     */
    private static final String LAST_IP = "lastIp";

    /**
     * 
     */
    private static final String LAST_LOGIN = "lastLogin";

    /**
     * 
     */
    private static final String URL = "url";

    /**
     * 
     */
    private static final String CREATED_DATE = "createdDate";

    /**
     * 
     */
    private static final String LAST_MODIFIED_DATE = "lastModifiedDate";

    /**
     * 
     */
    private static final String EMAIL_ADDRESS = "emailAddress";

    /**
     * 
     */
    private static final String GROUP_NAME = "groupName";

    /**
     * 
     */
    private static final String LAST_NAME = "lastName";

    /**
     * 
     */
    private static final String FIRST_NAME = "firstName";

    /**
     * 
     */
    private static final String USER_NAME = "userName";

    /**
     * 
     */
    private static final String APPLICATION = "application";

    /**
     * 
     */
    private static final String GROUP = "group";

    /**
     * A member object to use for iterations
     */
    private Member member = null;

    /**
     * A string used for the id of the member object
     */
    private String attrib = null;

    /**
     * Public Constructor
     */
    public RoleConfigHelper() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.start.AbstractConfigHelper#readNodeTypes(org.w3c.dom.Node, int)
     */
    @Override
    protected void readNodeTypes(Node node, int type) throws NumberFormatException, Throwable {
        switch(type){
            case Node.ELEMENT_NODE: {
                String elementName = node.getNodeName();

                String data = getNodeData(node.getFirstChild());
                if(elementName.equals(GROUP) || elementName.equals(APPLICATION)){

                    if(elementName.equals(GROUP)){
                        member = new Group();
                    }
                    else{
                        member = new Application();
                    }
                    attrib = member.getId() + "";
                }
                else if(elementName.equals(USER_NAME)){
                    member.setUserName(data);
                }
                else if(elementName.equals(FIRST_NAME)){
                    member.setFirstName(data);
                }
                else if(elementName.equals(LAST_NAME)){
                    member.setLastName(data);
                }
                else if(elementName.equals(GROUP_NAME)){
                    member.setGroupName(new StringAttributeBean(data, attrib));
                }
                else if(elementName.equals(EMAIL_ADDRESS)){
                    member.setEmailAddress(data);
                }
                else if(elementName.equals(LAST_MODIFIED_DATE)){
                    member.setModifiedDate(Universal.getDateFromString(data));
                }
                else if(elementName.equals(CREATED_DATE)){
                    member.setCreationDate(Long.parseLong(data));
                }
                else if(elementName.equals(URL)){
                    member.setUrl(data);
                }
                else if(elementName.equals(LAST_LOGIN)){
                    member.setLastLogin(Universal.getDateFromString(data));
                }
                else if(elementName.equals(LAST_IP)){
                    member.setLastIp(data);
                }
                if((member != null) && (!hash.containsValue(member))){
                    hash.put(attrib, member);
                }

                break;
            }
            default:
                break;
        }
        loopChildNodes(node);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.start.AbstractConfigHelper#resetHelper()
     */
    @Override
    public void resetHelper() {
        this.hash = null;
        this.member = null;
    }

}
