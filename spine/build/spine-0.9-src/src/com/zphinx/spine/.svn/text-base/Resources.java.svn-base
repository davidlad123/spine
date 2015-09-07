/**
 * Resources.java
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

package com.zphinx.spine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import com.zphinx.spine.vo.LabelIntBean;
import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * Resources allows access to a collection of language/locale,country,state,account and message information.
 * <p>
 * This object serves as a means of creating lists and maps of norminal language,country and state information with this information been
 * obtained from <code>com.zphinx.spine.resources.ResourceProperties</code>
 * </p>
 * @author David Ladapo
 * @version 1.0
 *          <p>
 *          Created 19-Jan-2005 02:38:37 <br>
 *          Copyright &copy; Zphinx Software Solutions
 *          </p>
 */
public class Resources {
    /**
     * 
     */
    private static final String DOT_STRING = ".";

    /**
     * 
     */
    private static final String MESSAGE = "message";

    /**
     * 
     */
    private static final String SUBJECT = "subject";

    /**
     * 
     */
    private static final String ACCOUNT = "account";

    /**
     * 
     */
    private static final String ZERO_LENGTH_STRING = "";

    /**
     * 
     */
    private static final String OTHER = "other";

    /**
     * 
     */
    private static final String LOCALE = "locale.";

    /**
     * 
     */
    private static final String COUNTRY = "country.";

    /**
     * 
     */
    private static final String DEFAULT_COUNTRY = "default.country";

    /**
     * The name and location of our resource bundle properties file
     */

    private static final String BUNDLE_NAME = "com.zphinx.spine.resources.ResourceProperties";//$NON-NLS-1$

    /**
     * The PropertyResourceBundle in use
     */
    private static final PropertyResourceBundle RESOURCE_BUNDLE = (PropertyResourceBundle) PropertyResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * A list used to store our countries
     */

    private static ArrayList<StringAttributeBean> countryList = null;

    /**
     * A list used to store our account types
     */

    private static ArrayList accountsList = null;

    /**
     * A list used to store subject types used in messages
     */

    private static ArrayList subjectsList = null;

    /**
     * A list used to store message types used in messages
     */

    private static ArrayList messagesList = null;

    /**
     * A list used to store our states
     */

    private static ArrayList<StringAttributeBean> stateList = null;

    /**
     * The list containing all the Locales in the world
     */
    private static ArrayList localeList = null;

    /**
     * A handle to this object
     */
    private static Resources resource = null;

    /**
     * Public Constructor
     */
    private Resources() {
        super();
        countryList = getCountryList();
        stateList = getStateList();
        localeList = getLocaleList();
        accountsList = getAccountsList();
        subjectsList = getSubjectsList();
        messagesList = getMessagesList();
    }

    /**
     * Gets the locale sensitive string defined by this key
     * 
     * @param key The key to the object been fetched
     * @return The locale sensitive string
     */
    public static String getString(String key) {
        // TODO Auto-generated method stub
        try{
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e){
            return '!' + key + '!';
        }
    }

    /**
     * Gets the resource bundle
     * @return Returns the RESOURCE_BUNDLE.
     */
    public static PropertyResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }

    /**
     * Gets the {@link StringAttributeBean} of the country to use, this represented as <code>CountryName|countryCode</code>
     * 
     * @param countryId The id by which the country is known in the properties file
     * @return The StringAttributeBean of countries in use
     */
    public synchronized StringAttributeBean getCountry(String countryId) {

        StringAttributeBean lab = null;
        if(countryList != null){
            if((countryId != null) && (!countryId.equals(DEFAULT_COUNTRY))){
                for (int i = 0; i < countryList.size(); i++){
                    lab = (StringAttributeBean) countryList.get(i);

                    if(lab.getValue().equals(countryId)){
                        break;
                    }
                }
            }
            else{
                String def = Resources.getString(DEFAULT_COUNTRY).trim();
                for (int i = 0; i < countryList.size(); i++){
                    lab = (StringAttributeBean) countryList.get(i);
                    if((lab.getName() != null) && lab.getName().equals(def)){
                        break;
                    }
                }

            }

        }
        return lab;

    }

    /**
     * Gets a {@link StringAttributeBean} containing the state amd it's state code.
     * 
     * @param stateId The key by which the state is known in the properties file
     * @return The state in whose id is represented by stateId
     */
    public synchronized StringAttributeBean getState(String stateId) {
        StringAttributeBean lab = null;
        if(stateList != null){
            if(stateId != null){
                for (int i = 0; i < stateList.size(); i++){
                    lab = (StringAttributeBean) stateList.get(i);

                    if(lab.getValue().equals(stateId)){
                        break;
                    }
                }
            }
            else{
                lab = getState(OTHER);
            }

        }
        return lab;

    }

    /**
     * Gets the list of {@link StringAttributeBean}s containing the country names with the associated ids.The id is usually represented in the properties file as <code>country.countryId=countryName</code>.
     * <p>
     * The returned {@link StringAttributeBean} is of the format<code> countryName|countryId </code>
     * </p>
     * 
     * @return Returns the countryList.
     */
    public synchronized ArrayList<StringAttributeBean> getCountryList() {
        if(countryList == null){
            countryList = new ArrayList<StringAttributeBean>();
            PropertyResourceBundle resourceBundle = Resources.getResourceBundle();
            Enumeration keyEnum = resourceBundle.getKeys();
            while (keyEnum.hasMoreElements()){
                String key = (String) keyEnum.nextElement();
                if(key.startsWith(COUNTRY)){
                    String value = Resources.getString(key);
                    String countryId = key.substring(key.indexOf(DOT_STRING) + 1, key.length());
                    countryList.add(new StringAttributeBean(value, countryId));

                }
            }
            countryList.trimToSize();
            if(countryList.size() > 1){
                countryList = sortList(countryList);
            }

            for (int i = 0; i < countryList.size(); i++){
                StringAttributeBean lab = (StringAttributeBean) countryList.get(i);
                if((lab.getName() != null) && lab.getName().equals((Resources.getString(DEFAULT_COUNTRY)).trim())){
                    countryList.add(0, new StringAttributeBean(ZERO_LENGTH_STRING, ZERO_LENGTH_STRING));
                    countryList.remove(lab);
                    countryList.add(1, lab);
                    break;
                }
            }

        }

        return countryList;
    }

    /**
     * Gets the static instance of this object
     * 
     * @return A static instance of this object
     */
    public static Resources getInstance() {
        if(resource == null){
            resource = new Resources();
        }
        return resource;
    }

    /**
     * Gets the list of {@link StringAttributeBean} containing the state names with the associated ids.The id is usually represented in the properties file as <code>state.stateId=State Name</code>.
     * <p>
     * The returned {@link StringAttributeBean} is of the format<code> stateName|stateId </code>
     * </p>
     * 
     * @return Returns the stateList.
     */
    public ArrayList<StringAttributeBean> getStateList() {

        if(stateList == null){
            stateList = new ArrayList<StringAttributeBean>();
            PropertyResourceBundle resourceBundle = Resources.getResourceBundle();
            Enumeration keyEnum = resourceBundle.getKeys();
            while (keyEnum.hasMoreElements()){
                String key = (String) keyEnum.nextElement();
                if(key.startsWith("state.")){
                    String value = Resources.getString(key);
                    String stateId = key.substring(key.indexOf(DOT_STRING) + 1, key.length());
                    stateList.add(new StringAttributeBean(value, stateId));

                }
            }
            stateList.trimToSize();
            if(stateList.size() > 1){
                // call sort algorithm
                stateList = sortList(stateList);
            }
            for (int i = 0; i < stateList.size(); i++){
                StringAttributeBean lab = (StringAttributeBean) stateList.get(i);
                if((lab.getName() != null) && lab.getValue().equals(OTHER)){
                    stateList.add(0, new StringAttributeBean(ZERO_LENGTH_STRING, ZERO_LENGTH_STRING));
                    stateList.remove(lab);
                    stateList.add(1, lab);
                    break;
                }
            }

        }

        return stateList;

    }

    /**
     * Alphabetically sort the contents of this list
     * 
     * @param aList The ArrayList of {@link StringAttributeBean}s to be sorted alphabetically according to name variable of the bean
     * @return A sorted list
     */
    protected synchronized ArrayList<StringAttributeBean> sortList(ArrayList aList) {
        ArrayList sorterList = new ArrayList(aList.size());
        for (int i = 0; i < aList.size(); i++){
            sorterList.add(((StringAttributeBean) aList.get(i)).getName());
        }
        String[] sorterArray = getSortArray(sorterList);

        Arrays.sort(sorterArray);
        ArrayList sortedList = new ArrayList();
        for (int k = 0; k < sorterArray.length; k++){
            String label = sorterArray[k];

            for (int j = 0; j < aList.size(); j++){
                StringAttributeBean lab = (StringAttributeBean) aList.get(j);
                if(((label != null) && (lab != null)) && ((lab.getName() != null) && (lab.getValue() != null)) && (label.trim().equals(lab.getName().trim()))){
                    sortedList.add(lab);
                    break;

                }

            }

        }
        if(sortedList.size() > 0) sortedList.trimToSize();
        return sortedList;

    }

    /**
     * Get the Array of strings contained in this sorterList
     * 
     * @param sorterList The list containing the values to be rendered in the String Array.
     * @return A sorted array of Strings
     */
    private String[] getSortArray(ArrayList sorterList) {
        String[] sorterArray = new String[sorterList.size()];
        for (int z = 0; z < sorterList.size(); z++){
            sorterArray[z] = (String) sorterList.get(z);
        }
        return sorterArray;
    }

    /**
     * Gets the list of locales which are defined in the resource properties file
     * 
     * @return Returns the localeList.
     */
    public ArrayList getLocaleList() {
        if(localeList == null){
            localeList = new ArrayList();
            PropertyResourceBundle resourceBundle = Resources.getResourceBundle();
            Enumeration keyEnum = resourceBundle.getKeys();
            while (keyEnum.hasMoreElements()){
                String key = (String) keyEnum.nextElement();
                if(key.startsWith(LOCALE)){
                    String value = Resources.getString(key);
                    String stateId = key.substring(key.indexOf(DOT_STRING) + 1, key.length());
                    localeList.add(new StringAttributeBean(value, stateId));

                }
            }
            localeList.trimToSize();
            if(localeList.size() > 1){
                // call sort algorithm
                localeList = sortList(localeList);
            }
            for (int i = 0; i < localeList.size(); i++){
                StringAttributeBean lab = (StringAttributeBean) localeList.get(i);
                if((lab.getName() != null) && lab.getValue().equals(OTHER)){
                    localeList.add(0, new StringAttributeBean(ZERO_LENGTH_STRING, ZERO_LENGTH_STRING));
                    localeList.remove(lab);
                    localeList.add(1, lab);
                    break;
                }
            }

        }

        return localeList;
    }

    /**
     * Gets a {@link StringAttributeBean} containing the Locale and it's locale code.
     * 
     * @param localeId The id or language code of this locale
     * @return A {@link StringAttributeBean} representing the Locale in question
     */
    public synchronized StringAttributeBean getLocale(String localeId) {
        StringAttributeBean lab = null;
        if(localeList != null){
            if(localeId != null){
                for (int i = 0; i < localeList.size(); i++){
                    lab = (StringAttributeBean) localeList.get(i);

                    if(lab.getValue().equals(localeId)){
                        break;
                    }
                }
            }
            else{
                lab = getLocale(OTHER);
            }

        }
        return lab;

    }

    /**
     * Gets the ArrayList of {@link LabelIntBean}s which contains references to the account types available in the application
     * 
     * @return An ArrayList of account types of configured
     */
    public ArrayList getAccountsList() {
        if(accountsList == null){
            accountsList = (ArrayList) parseNumericList(ACCOUNT);
        }
        return accountsList;
    }

    /**
     * Gets the ArrayList of {@link LabelIntBean} which hold references to subject titles
     * 
     * @return An ArrayList of subject titles
     */
    public ArrayList getSubjectsList() {
        if(subjectsList == null){
            subjectsList = (ArrayList) parseNumericList(SUBJECT);
        }
        return subjectsList;
    }

    /**
     * Gets the ArrayList of {@link LabelIntBean} which hold references to message types
     * 
     * @return An ArrayList of message types
     */
    public ArrayList getMessagesList() {

        if(messagesList == null){
            messagesList = (ArrayList) parseNumericList(MESSAGE);
        }
        return messagesList;
    }

    /**
     * Gets a list based on the key string from the resources file
     * 
     * @param keyString The key to use for searching the resources file
     * @return A sorted List containing the sorted constants
     * @throws NumberFormatException if an exception occurs
     */
    private synchronized List parseNumericList(String keyString) throws NumberFormatException {
        ArrayList dList = new ArrayList();
        PropertyResourceBundle resourceBundle = Resources.getResourceBundle();
        Enumeration keyEnum = resourceBundle.getKeys();

        while (keyEnum.hasMoreElements()){
            String key = (String) keyEnum.nextElement();
            if(key.startsWith(keyString + DOT_STRING)){
                String value = Resources.getString(key);
                String label = key.substring(key.indexOf(DOT_STRING) + 1, key.length());
                int labelInt = Integer.parseInt(label);
                // ["1",1]
                LabelIntBean dBean = new LabelIntBean(value, labelInt);
                dList.add(dBean);
                // System.out.print("The Dbean is: " + dBean);

            }
        }
        dList.trimToSize();
        if(dList.size() > 1){
            // call sort algorithm
            dList = sortIntArray(dList);
        }
      //  System.out.println("The arrayList is: " + dList);

        return dList;
    }

    /**
     * Sorts an ArrayList of {@link LabelIntBean} incrementally
     * 
     * @param dList The list of LabelIntBeans
     */
    private ArrayList sortIntArray(ArrayList dList) {
        int size = dList.size();
        int[] intArr = new int[size];
        ArrayList<LabelIntBean> returnList = null;
        if(size > 0){
            returnList = new ArrayList<LabelIntBean>(size);
            for (int i = 0; i < size; i++){
                intArr[i] = ((LabelIntBean) dList.get(i)).getIntValue();
            }
            Arrays.sort(intArr);

            for (int k = 0; k < intArr.length; k++){
                for (int x = 0; x < size; x++){
                    LabelIntBean aBean = (LabelIntBean) dList.get(x);
                    int aInt = aBean.getIntValue();
                    if(aInt == intArr[k]){
                        returnList.add(aBean);
                        break;
                    }
                }
            }
        }
        return returnList;
    }

    /**
     * Gets a {@link LabelIntBean} containing the Account Type and it's account code.
     * 
     * @param accountId The id or code of this AccountType
     * @return A {@link LabelIntBean} representing the AccountType in question
     */
    public LabelIntBean getAccount(int accountId) {
        return getIntBean(accountId, accountsList);
    }

    /**
     * Gets a {@link LabelIntBean} containing the Message Type and it's message code.
     * 
     * @param messageId The id or code of this MessageType
     * @return A {@link LabelIntBean} representing the MessageType in question
     */
    public LabelIntBean getMessage(int messageId) {
        return getIntBean(messageId, messagesList);
    }

    /**
     * Gets a {@link LabelIntBean} whose intValue is equal to the given intValue
     * 
     * @param intValue The intValue used for tthe Search search
     * @param aList The ArrayList to search
     * @return An instance of the {@link LabelIntBean} whose intValue is equal to the given intValue
     */
    public synchronized LabelIntBean getIntBean(int intValue, ArrayList aList) {
        LabelIntBean lab = null;
        if(aList != null){
            if(intValue >= 0){
                for (int i = 0; i < aList.size(); i++){
                    lab = (LabelIntBean) aList.get(i);

                    if(lab.getIntValue() == intValue){
                        break;
                    }
                }
            }
        }
        return lab;
    }

}