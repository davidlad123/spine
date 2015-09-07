/**
 *
 * GroupUtils.java
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

package com.zphinx.spine.utils;

import java.util.ArrayList;

import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * A GroupUtils object is an utility object to retrieve StringAttributeBeans from the specified groupName
 * and/or groupIds Array and vice versa.
 * 
 * @author David Ladapo
 * @version $Revision: 1.1 $ $Date: 2008/06/12 22:35:52 $
 *          <p>
 *          created Fri Jul 30 18:13:08 BST 2004 copyright Zphinx Software Solutions
 *          </p>
 */

public class GroupUtils {

    /**
     * The instance of this object
     */
    private static GroupUtils groupUtils = null;

    /**
     * Constructor for the GroupUtils object
     */
    private GroupUtils() {
        super();
    }

    /**
     * Creates and returns an Arraylist of StringAttributeBeans from groupNames and Group Ids Array
     * 
     * @param groupNames The array of groupNames
     * @param groupIds The array of group ids
     * @return ArrayList of StringAttributeBeans representing the groups member belongs to
     */

    public synchronized ArrayList getGroupValueList(String[] groupNames, String[] groupIds) {
        ArrayList lbvList = new ArrayList();
        for (int i = 0; i < groupNames.length; i++){
            StringAttributeBean lvb = new StringAttributeBean(groupNames[i], groupIds[i]);
            lbvList.add(lvb);

        }
        lbvList.trimToSize();
        return lbvList;

    }

    /**
     * Creates and returns an Array of groupNames from ArrayList of StringAttributeBeans
     * 
     * @param groupList The ArrayList of StringAttributeBeans
     * @return ArrayList An ArrayList of length 2 comprising {groupNames[],groupIds[]} representing the groups member belongs to
     */

    public synchronized ArrayList getGroupArray(ArrayList groupList) {

        ArrayList outGroup = new ArrayList();
        String[] groupNames = null;
        String[] groupIds = null;
        if(groupList.size() > 0){
            groupNames = new String[groupList.size()];
            groupIds = new String[groupList.size()];
            for (int i = 0; i < groupList.size(); i++){
                StringAttributeBean lbv = (StringAttributeBean) groupList.get(i);
                groupNames[i] = lbv.getName();
                groupIds[i] = lbv.getValue();

            }
            outGroup.add(groupNames);
            outGroup.add(groupIds);
        }
        outGroup.trimToSize();
        return outGroup;

    }

    /**
     * Gets the GroupUtils object
     * 
     * @return The static instance of this object
     */
    public static GroupUtils getInstance() {

        if(groupUtils == null){
            groupUtils = new GroupUtils();
        }
        return groupUtils;

    }

}