/**
 *
 * Member.java
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

package com.zphinx.spine.members;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zphinx.spine.vo.dto.StringAttributeBean;

import com.zphinx.spine.Universal;
import com.zphinx.spine.Resources;
import com.zphinx.spine.security.MemberPermission;
import com.zphinx.spine.security.SpinePermission;

/**
 * A member object is the super object which dictates the properties of Users,Administrators,Groups and Applications registered within the Spine framework.
 * 
 * @see Group
 * @see User
 * @see Administrator
 * @see Application
 * @author David Ladapo
 * @version 1.4 Date: Mon Sep 13 14:58:28 BST 2004
 *          <p>
 *          copyright &copy;Zphinx Software Solutions
 *          </p>
 */

public abstract class Member extends Identifier {

    /**
     * The collection of group id's
     */

    private String[] groupId = null;

    /**
     * The ArrayList of Administrator StringAttributeBeans
     */
    private ArrayList administrators = null;

    /**
     * The address.
     */
    private String address = null;

    /**
     * The state.
     */
    private StringAttributeBean state = new StringAttributeBean(Resources.getString("state.other"), "other");

    /**
     * The country.
     */
    private StringAttributeBean country = Resources.getInstance().getCountry(null);

    /**
     * The postCode.
     */
    private String postCode = null;

    /**
     * The city.
     */
    private String city = null;

    /**
     * The companyName.
     */
    private String companyName = null;

    /**
     * The group name.
     */
    private StringAttributeBean groupName = null;

    /**
     * The group names.
     */
    private ArrayList groupNames = null;

    /**
     * The phone number.
     */
    private String phone = null;

    /**
     * The fax number.
     */
    private String fax = null;

    /**
     * A place holder [bug 2733]
     */
    // private String birthDateString = null;
    /**
     * The url.
     */
    private String url = null;

    /**
     * The last time we logged in.
     */
    private Date lastLogin = null;

    /**
     * The ip we are using.
     */
    private String lastIp = null;

    /**
     * The Activity taken.
     */
    private int activity = Universal.CREATE;

    /**
     * Our gender.
     */
    private String gender = "-";

    /**
     * The members date of birth
     */

    private Date birthDate;

    /**
     * Public Constructor
     */
    public Member() {
        super();

    }

    /**
     * Get the short name for this member object i.e User,Group or Administrator
     * 
     * @return String A short name for this member object
     */
    public String getMemberName() {
        String cln = this.getClass().getName();
        cln = cln.substring((cln.lastIndexOf(".") + 1), cln.length());
        return cln;
    }

    /**
     * Return the ids of the groups this member belongs to.
     * 
     * @return String[] The array of groupIds.
     */

    public String[] getGroupId() {
        if(groupId == null){
            int d = getGroupNames().size();
            if(d > 0){
                groupId = new String[d];
                for (int i = 0; i < d; i++){
                    groupId[i] = ((StringAttributeBean) getGroupNames().get(i)).getValue();
                }
            }
            else{
                groupId = new String[1];
                groupId[0] = getGroupName().getValue();
            }
        }
        return (this.groupId);
    }

    /**
     * Add some more Group ids
     * 
     * @param groupId The array of group ids
     */
    public void setGroupId(String[] groupId) {

        List activeGroupsList = Universal.getGroupsList();
        String idSysGroup = null;
        StringAttributeBean b = null;
        // create an arraylist to store StringAttributeBeans
        ArrayList newArrayList = new ArrayList();
        try{
            if(groupId != null){
                String abbs = this.getClass().getSimpleName();
                if((getId() > 0) && (getMemberName().equals(GROUP) || getMemberName().equals(APPLICATION))){

                    setGroupName(new StringAttributeBean(groupId[0], getId() + ""));
                    // groupId[0] = getId();
                    StringAttributeBean processingBean = getGroupName();

                    for (int k = 0; k < activeGroupsList.size(); k++){
                        b = (StringAttributeBean) activeGroupsList.get(k);
                        if(b.equals(processingBean)){

                            break;
                        }
                        else
                            continue;
                    }
                    if(!newArrayList.contains(processingBean)) newArrayList.add(processingBean);
                }
                else{
                    this.groupId = groupId;
                    // loop tro the grouupIds
                    for (int j = 0; j < groupId.length; j++){

                        // loop tro the groups in the system
                        for (int k = 0; k < activeGroupsList.size(); k++){
                            // group in system
                            b = (StringAttributeBean) activeGroupsList.get(k);
                            idSysGroup = b.getValue();

                            // Check if group exists in System
                            if(idSysGroup.equals(groupId[j])){

                                // add this group in the newArrayList
                                newArrayList.add(b);
                                // set as GroupName if this gropIDs has one
                                // entry usually by group
                                if(groupId.length == 1){
                                    setGroupName(b);
                                }
                                break;
                            }
                            else
                                continue;

                        }
                    }
                }
                setGroupNames(newArrayList);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Return the address of this member.
     * 
     * @return String The address of this member.
     */
    public String getAddress() {
        return (this.address);
    }

    /**
     * Set the address of this member.
     * 
     * @param address The new address of this member.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the group name this member belongs to.
     * 
     * @return StringAttributeBean The group name of this member.
     */
    public StringAttributeBean getGroupName() {
        if(this.groupName == null){
            this.groupName = new StringAttributeBean("", Universal.getUniqueId() + "");

        }
        return this.groupName;
    }

    /**
     * Set the group name this member belongs to.
     * 
     * @param groupName The new groupName
     */
    public void setGroupName(StringAttributeBean groupName) {
        if(groupName != null){
            this.groupName = groupName;
            ArrayList groups = getGroupNames();
            if(!groups.contains(groupName)){
                StringAttributeBean lab = new StringAttributeBean("", "");
                if(groups.contains(lab)) groups.remove(lab);
                groups.add(groupName);
                setGroupNames(groups);
            }
        }

    }

    /**
     * Get the groups this member belongs to.
     * 
     * @return ArrayList A collection of group names
     */
    public ArrayList getGroupNames() {
        if(this.groupNames == null){
            this.groupNames = new ArrayList();
            this.groupNames.trimToSize();
        }

        return this.groupNames;
    }

    /**
     * @param groupNames an array of StringAttributeBeans representing the names of individual groups
     */
    public void setGroupNames(ArrayList groupNames) {
        if(groupNames != null){
            groupNames.trimToSize();
            if(this.groupName == null){
                if(groupNames.size() > 0){
                    this.groupName = (StringAttributeBean) groupNames.get(0);
                }
            }
            this.groupNames = groupNames;
        }
    }

    /**
     * Return the city of origin of this member.
     * 
     * @return String The city of origin of this member.
     */
    public String getCity() {
        return (this.city);
    }

    /**
     * Set the new city of origin of this member..
     * 
     * @param city The new city of origin of this member.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Return the state of origin of this member.
     * 
     * @return StringAttributeBean The state of origin of this member.
     */
    public StringAttributeBean getState() {

        return (this.state);
    }

    /**
     * Set the new state of origin of this member.
     * 
     * @param bean The new state of origin of this member.
     */
    public void setState(StringAttributeBean bean) {
        this.state = bean;
    }

    /**
     * Set the new state of origin of this member.
     * 
     * @param stateId The new state of origin of this member.
     */
    public void setState(String stateId) {
        this.state = Resources.getInstance().getState(stateId);
    }

    /**
     * Return the post/zip code of this member.
     * 
     * @return String The post code / zip code of this member.
     */
    public String getPostCode() {
        return (this.postCode);
    }

    /**
     * Set The new post/zip code of this member.
     * 
     * @param postCode The new post/zip code of this member.
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Return the country of origin of this member.
     * 
     * @return StringAttributeBean The country of origin of this member.
     */
    public StringAttributeBean getCountry() {

        return (this.country);
    }

    /**
     * Set the new country of origin of this member.
     * 
     * @param country The country of origin of this member.
     */
    public void setCountry(StringAttributeBean country) {
        this.country = country;
    }

    /**
     * Set the new country of origin of this member.
     * 
     * @param countryId The country of origin of this member.
     */
    public void setCountry(String countryId) {
        this.country = Resources.getInstance().getCountry(countryId);
    }

    /**
     * Return the companyName of this member.
     * 
     * @return String The companyName of this member.
     */
    public String getCompanyName() {
        return (this.companyName);
    }

    /**
     * Set the new company name of this member.
     * 
     * @param companyName The company name of this member.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Return the phone number of this member.
     * 
     * @return String The phone number of this member.
     */
    public String getPhone() {
        return (this.phone);
    }

    /**
     * Set the new phone number of this member.
     * 
     * @param phone The new phone number of this member.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Return the fax number of this member.
     * 
     * @return String The fax number of this member
     */
    public String getFax() {
        return (this.fax);
    }

    /**
     * Set the new fax number of this member.
     * 
     * @param fax The fax number of this member
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Return the gender of this member.
     * 
     * @return String The gender of this member
     */
    public String getGender() {
        return (this.gender);
    }

    /**
     * Set the new gender of this member.
     * 
     * @param gender The new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Return the url representing the homepage of this member.
     * 
     * @return URL The URL object representing the homepage of this member
     */
    public String getUrl() {
        return (this.url);
    }

    /**
     * Set the new url representing the homepage of this member.
     * 
     * @param url A Url Object representing the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Return the last Ip address this member logged in from.
     * 
     * @return String The last Ip address the member logged in from.
     */
    public String getLastIp() {
        return (this.lastIp);
    }

    /**
     * Set the last Ip address this member logged in from.
     * 
     * @param lastIp The last Ip address this member logged in from.
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    /**
     * Add some Administrators to this object.
     * 
     * @param administrators An array of Administrator StringAttributeBean objects to add to this object.
     */
    public void setAdministrators(ArrayList administrators) {
        if(administrators != null){
            administrators.trimToSize();
            this.administrators = administrators;
        }

    }

    /**
     * Get the Administrators who can administer to this object
     * 
     * @return ArrayList A collection of Administrator StringAttributeBean objects who can administer to this object
     */
    public ArrayList getAdministrators() {
        if(this.administrators == null){
            this.administrators = new ArrayList();
        }
        return this.administrators;
    }

    /**
     * Return the last Login date of this member.
     * 
     * @return Date The last login date
     */
    public Date getLastLogin() {
        return (this.lastLogin);
    }

    /**
     * Set the last Login date of this member.
     * 
     * @param lastLogin The last time this member Logged on to the system.
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Return the maintenance activity to be taken by this member object.
     * 
     * @return int The maintenance activity to be taken by this member object.
     */
    public int getActivity() {
        return (this.activity);
    }

    /**
     * Set the maintenance activity to be taken by this member object.
     * 
     * @param activity The new maintenance activity to be taken by this member object.
     */
    public void setActivity(int activity) {
        this.activity = activity;
    }

    /**
     * Gets this members birth date
     * 
     * @return Returns the birthDate.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Gets this members birth date as a string in the format DD/MM/YYYY
     * 
     * @return Returns the birthDate.
     */
    public String getBirthDateString() {
        return Universal.getCalenderDateString(this.birthDate);
    }

    /**
     * Gets this members birth date
     * 
     * @param birthDate The birthDate to set.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Sets the birthDate String
     * 
     * @param birthDateString
     */
    public void setBirthDateString(String birthDateString) {
        this.birthDate = Universal.getCalenderDate(birthDateString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.vo.SpineBean#getPermission()
     */
    public abstract SpinePermission getPermission();

    /**
     * A permission Factory to create a permission for use by a Member called by implementations of getPermission
     * 
     * @see Group
     * @see User
     * @see Administrator
     * @see Application
     * @author David Ladapo
     * @version $Revision: 1.17 $ $Date: 2008/06/12 22:35:54 $
     */

    protected class MemberPermissionFactory {

        /**
         * Create a default permission object of this type of member
         * 
         * @return The default MemberPermission object for this member
         */
        public MemberPermission getMemberPermission() {
            MemberPermission permission = null;
            int memberIntValue = calcIntValue();
            permission = new MemberPermission(memberIntValue);
            permission.setGroupNames(getGroupNames());
            permission.setIdTag(getId() + "");
            permission.setOwnerObjectType(getMemberName());
            return permission;

        }

        /**
         * Calculates the int value of the Member object.
         * 
         * @return An int used to represent the type of this Member
         */
        private int calcIntValue() {
            int memberIntVal;

            if(getMemberName().equals(ADMINISTRATOR)){
                memberIntVal = 1;
            }
            else if(getMemberName().equals(USER)){
                memberIntVal = 2;
            }
            else if(getMemberName().equals(GROUP)){
                memberIntVal = 3;
            }
            else if(getMemberName().equals(APPLICATION)){
                memberIntVal = 4;
            }
            else{
                memberIntVal = 0;
            }
            return memberIntVal;
        }
    }

}
