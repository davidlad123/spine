/**
 * Universal.java
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * A universal class used for providing base application settings and common tasks.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class Universal implements UniversalConstants {

    private static final int LONG_LIST_SIZE = 1000;

    /**
     * A list used to store our groups
     */

    private static List groupsList = null;

    /**
     * A repository of uniqueIds
     */
    private static List<Long> longList = null;

    /**
     * An object used for synchronization
     */

    private static Object uniqueId = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oLogger = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oHash = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oRandom = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oDateFromString = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oGroupValue = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oCalenderDate = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oGroupLabel = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oCalenderDateString = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oDateTime = new Object();

    /**
     * An object used for synchronization
     */

    private static Object oRealDate = new Object();

    /**
     * The value (in seconds) to renew our UserListBeans within the system
     */

    public static final int SESSION_COUNTER = 1800;

    /**
     * An int representing the default size of the random number to generate
     */
    private static final int DEFAULT_RANDOM_NUMBER = 9;

    /**
     * Public Constructor
     */
    private Universal() {
        super();
    }

    /**
     * Gets the log4j logger instance for use by this system
     * 
     * @param instance A string representing the class we are working with
     * @return The Object which denotes our logger
     */

    public static Logger getLogger(String instance) {
        Logger log = null;
        synchronized (oLogger){
            try{

                log = Logger.getLogger(instance);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return log;

    }

    /**
     * Gets a uniqueId which can be used for Ids
     * 
     * @return a long number useful for ids
     */
    public static long getUniqueId() {
        long l = 0;

        if(longList == null){
            longList = new ArrayList<Long>(LONG_LIST_SIZE);
        }
        synchronized (uniqueId){
            long theTime = System.currentTimeMillis();
            // we are about to send the last id
            if(longList.size() == 1){
                l = longList.remove(0).longValue();
                if(l >= theTime){
                    generateList(l + 1);
                }
                else{
                    generateList(theTime);
                }
            }
            else if(longList.size() == 0){
                l = generateList(System.currentTimeMillis());
            }
            else{
                l = longList.remove(0).longValue();
            }

        }
        return l;
    }

    /**
     * Generates a list of Unique IDs
     * 
     * @return a unique Id
     */
    private static long generateList(long currentTime) {

        for (int i = 0; i < 100; i++){
            Long listId = new Long(currentTime + i);
            longList.add(listId);
        }
        return longList.remove(0).longValue();
    }

    /**
     * Gets a random character identifier from (A-Z,a-z,0-9)
     * 
     * @param size The number of random characters to generate
     * @return A random string of 9 letters (A..Z,a..z,0..9)
     */
    public static String getRandom(int size) {
        String rand = "";
        synchronized (oRandom){
            if(size < 1) size = Universal.DEFAULT_RANDOM_NUMBER;
            int j;
            char[] seedArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
            Random ranStr = new Random();
            for (int i = 0; i < size; i++){
                j = ranStr.nextInt(36);
                rand += seedArray[j];
            }
        }
        return rand;
    }

    /**
     * Generates an int useful for hashcodes using a simple numerical replacement of the alphabet eg <code>L = 12</code>
     * 
     * @param s The string whose int representation is to be generated
     * @return An int which represents the addition of all the alphabets in the string using the numerical equivalent eg <code>L = 12</code>
     */

    public static int generateHashCode(final String s) {
        int hash = 0;
        synchronized (oHash){

            char[] seedArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

            for (int i = 0; i < s.length(); i++){
                boolean b = false;
                char c = s.charAt(i);
                try{
                    int z = Integer.parseInt(String.valueOf(c));
                    hash += z;
                    b = true;
                }
                catch (NumberFormatException e){
                    // we do not handle this
                }
                if(!b){
                    for (int k = 0; k < seedArray.length; k++){
                        if(Character.toUpperCase(c) == seedArray[k]){
                            hash += k;
                            break;
                        }
                    }
                }
            }
        }
        return hash;
    }

    /**
     * Gets a string to insert for date values
     * 
     * @param date The Date object representing this date
     * @return The sql string representation of this date
     */

    public static String getRealDate(Date date) {
        String outString = "";
        synchronized (oRealDate){
            String tString = "";
            String dString = "";
            try{

                if(date == null) date = new Date();
                dString = createSQLDate(date.getTime());
                tString = createSQLTime(date.getTime());

            }
            catch (Exception e){
                getLogger("com.zphinx.spine.Universal").debug("There has been an error creating a string from date: " + e.getMessage());
            }
            outString = dString + " " + tString;
        }
        return outString;

    }

    /**
     * Creates an SQL time in string format. The format is <code>hh:mm:ss</code>
     * 
     * @param time The time in milliseconds from the epoch
     * @return The string in hh:mm:ss format
     */
    public static String createSQLTime(long time) {
        synchronized (oDateTime){
            Time t = new Time(time);
            return t.toString();
        }
    }

    /**
     * Creates the java.sql string representation of a date in <code>yyyy-mm-dd</code>
     * 
     * @param date The date in milliseconds from the epoch
     * @return The date in yyyy-mm-dd format
     */
    public static String createSQLDate(long date) {
        synchronized (oDateTime){
            java.sql.Date d = new java.sql.Date(date);
            return d.toString();
        }
    }

    /**
     * Gets a Date object from an sql date string in format <code>yyyy-mm-dd</code>
     * 
     * @param s The String containing the date usually the type obtained from a db
     * @return A date object suitable for use by this framework
     */
    public static Date createSQLDateFromString(String s) {
        synchronized (oDateTime){
            java.sql.Date asd = java.sql.Date.valueOf(s);
            return asd;
        }

    }

    /**
     * Gets a date from string values usually of the form <code>YYYY-MM-DD hh:mm:ss</code>
     * 
     * @param totalString The full string from an sql database
     * @return The java.util.Date representation of the input String
     */

    public static synchronized Date getDateFromString(String totalString) {
        Date date = null;
        synchronized (oDateFromString){
            try{
                if((totalString != null) && (totalString.trim().length() > 6) && (!(totalString.trim().startsWith("0000-00-00")))){
                    String dateString = totalString.substring(0, totalString.indexOf(" "));
                    String timeString = totalString.substring(totalString.indexOf(" ") + 1, totalString.length());
                    String a = dateString.substring(0, dateString.indexOf("-"));
                    String b = dateString.substring(dateString.indexOf("-") + 1, dateString.lastIndexOf("-"));
                    String c = dateString.substring(dateString.lastIndexOf("-") + 1, dateString.length());

                    String d = timeString.substring(0, timeString.indexOf(":"));
                    String e = timeString.substring(timeString.indexOf(":") + 1, timeString.lastIndexOf(":"));
                    String f = timeString.substring(timeString.lastIndexOf(":") + 1, timeString.length());
                    Integer it = new Integer((int) Float.parseFloat(f));
                    int fInt = it.intValue();
                    Calendar endar = Calendar.getInstance();
                    endar.set(Integer.parseInt(a), Integer.parseInt(b) - 1, Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e), fInt);
                    date = endar.getTime();
                }
                else{

                    date = new Date();
                }

            }
            catch (Exception e){
                getLogger("com.zphinx.spine.Universal").debug("There has been an error creating a date: " + e.getMessage());
            }
        }
        return date;

    }

    /**
     * Gets a String that can be displayed to the user by a calender for a date. The format of this date is <code>DD/MM/YYYY</code>
     * 
     * @param date The Date to create a string
     * @return The date in a format used by a Calender application
     */
    public static String getCalenderDateString(Date date) {
        String s = null;
        synchronized (oCalenderDateString){
            Calendar calendar = new GregorianCalendar();
            if(date != null){
                calendar.setTime(date);
                s = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
            }
        }
        return s;

    }

    /**
     * Parses the string returned from a Calender application as a Date, usually of the form <code>DD/MM/YYYY</code>
     * 
     * @param dateString The string returned from widget
     * @return The Date represented by this dateString
     */
    public static Date getCalenderDate(String dateString) {
        Date aDate = null;
        synchronized (oCalenderDate){
            if((dateString.trim().length() > 0) && (dateString.indexOf("/") != -1)){
                int day = Integer.parseInt(dateString.substring(0, dateString.indexOf("/")));
                int month = Integer.parseInt(dateString.substring(dateString.indexOf("/") + 1, dateString.lastIndexOf("/")));
                int year = Integer.parseInt(dateString.substring(dateString.lastIndexOf("/") + 1, dateString.length()));
                Calendar calendar = new GregorianCalendar(year, month, day);
                aDate = calendar.getTime();
            }
        }
        return aDate;

    }

    /**
     * Gets all the groups in this system. This must be initialized at startup by a suitable configuration object
     * 
     * @return An ArrayList {@link StringAttributeBean}s of group names
     */

    public static synchronized List getGroupsList() {

        return groupsList;
    }

    /**
     * Stores the groups list
     * 
     * @param groupsListExt an ArrayList of {@link StringAttributeBean}s representing the names of individual groups
     */
    public static void setGroupsList(List groupsListExt) {
        groupsList = groupsListExt;
    }

    /**
     * Gets the label associated with this groupId
     * 
     * @param gId A String representing the id of individual group
     * @return An appropriate label for the group
     */

    public static String getGroupLabel(String gId) {
        String outString = null;
        synchronized (oGroupLabel){
            StringAttributeBean lbv = null;
            for (int i = 0; i < groupsList.size(); i++){
                lbv = (StringAttributeBean) groupsList.get(i);
                if(lbv.getValue().equals(gId)){
                    outString = lbv.getName();
                    break;
                }

            }
        }
        return outString;

    }

    /**
     * Gets the id of the named group
     * 
     * @param gLabel A String representing the name of individual group
     * @return An id for the group
     */

    public static String getGroupValue(String gLabel) {

        String outString = null;
        synchronized (oGroupValue){
            StringAttributeBean lbv = null;
            for (int i = 0; i < groupsList.size(); i++){
                lbv = (StringAttributeBean) groupsList.get(i);
                if(lbv.getName().equals(gLabel)){
                    outString = lbv.getValue();
                    break;
                }

            }
        }
        return outString;

    }

}
