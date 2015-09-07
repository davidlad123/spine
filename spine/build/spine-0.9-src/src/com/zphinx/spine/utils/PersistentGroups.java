/**
 * PersistentGroups.java
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
import java.util.Enumeration;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.resources.ConfigResources;
import com.zphinx.spine.vo.dto.UserListBean;

/**
 * A singleton used to access the Groups/Role Data
 * 
 * @see com.zphinx.spine.utils.ActiveGroups
 * @author David Ladapo
 * @version Revision: 1.0
 *          <p>
 *          Created Date: Wed Aug 18 02:00:31 BST 2004
 *          </p>
 *          <p>
 *          copyright &copy;Zphinx Software Solutions
 *          </p>
 */

public class PersistentGroups {

    /**
     * 
     */
    private static final String NO_MAP_GIVEN = "no.map.given";

    /**
     * The ActiveGroups Map of groups
     */
    private static ActiveGroups groupsMap = null;

    /**
     * A boolean indictating if the map is available;
     */
    private static boolean created = false;

    /**
     * An instance of this object
     */
    private static PersistentGroups persistentGroup = null;

    /**
     * A long indicating the last time sessions were flushed
     */
    private static final long COUNTER = Universal.SESSION_COUNTER * 1000;

    /**
     * The rate of execution of the scheduler, set to 5 minutes
     */
    private static final long EXECUTION_PERIOD = 5 * 60 * 1000;

    /**
     * The delay before the timer task starts execution, set to 5 seconds
     */
    private static final long EXECUTION_DELAY = 5000;

    /**
     * A timer to use to time our cleanup operations
     */

    private static Timer timer = null;

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(PersistentGroups.class.getName());

    /**
     * private constructor, initialise all our variables
     */

    private PersistentGroups() {

        if(groupsMap == null){
            groupsMap = new ActiveGroups();
            created = true;
        }

        if(timer == null){
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new ClearStateTask(), EXECUTION_DELAY, EXECUTION_PERIOD);
        }

    }

    /**
     * Set the ActiveGroups object to associate with this object
     * 
     * @param map The ActiveGroups Object to store
     */

    public static synchronized void setMap(ActiveGroups map) throws SpineException {
        if(!created) getInstance();
        if(map != null){
            groupsMap = map;
        }
        // Common.messages.getMessage("no.map.given")
        else
            throw new SpineException(ConfigResources.getString(NO_MAP_GIVEN));

    }

    /**
     * A utility method to return an instance of this object
     */

    protected static void getInstance() {
        if(persistentGroup == null){
            persistentGroup = new PersistentGroups();
        }

    }

    /**
     * Get the activeGroups map
     * 
     * @return ActiveGroups The map of active groups in the system
     */

    public synchronized static ActiveGroups getMap() {
        if(!created) getInstance();
        return groupsMap;
    }

    /**
     * A boolean indicating if we have a populated ActiveGroups object
     * 
     * @return boolean a boolean indicating the presence of an ActiveGroups object
     */

    public synchronized boolean getCreatedFlag() {
        return created;

    }

    /**
     * An inner class which uses TimerTask to schedule the execution of the Task of cleaning up the contents of the ActiveGroups map
     * 
     * @author David Ladapo
     * @version Revision: 1.0
     *          <p>
     *          Date: Wed Aug 18 02:00:31 BST 2004
     *          </p>
     *          <p>
     *          copyright &copy; Zphinx Software Solutions
     *          </p>
     */

    private static class ClearStateTask extends TimerTask {

        /**
         * 
         */
        private static final String UNIQUE = "unique";

        /**
         * Clear out expired and logged out users from the ActiveGroups objects at pre specified periods defined by Common.SESSION_COUNTER
         */

        public void run() {

            try{
                ActiveGroups ag = getMap();
                clearTimeouts(ag, 0);
                setMap(ag);

            }
            catch (IllegalStateException e){
                log.debug("Illegal Exception in thread: " + e.getMessage());
            }
            catch (Exception e){
                log.debug("Exception in thread: " + e.getMessage());
            }

        }

        /**
         * Cleanup the Groups in this system by removing associated UserlistBeans that have expired from the system
         * 
         * @param timeout The time interval to check if user is still valid
         */

        public void clearTimeouts(ActiveGroups activeGroups, int timeout) {
            int duration = 0;
            // synchronized (this){
            if(timeout > 0){
                duration = timeout;
            }

            Set aSet = activeGroups.keySet();
            // Collection objects = values();
            Enumeration keys = activeGroups.keys();
            ArrayList dList = activeGroups.getUniqueList();
            while (keys.hasMoreElements()){

                Object key = keys.nextElement();
                Group g = null;
                Object obj = activeGroups.get(key);
                if((obj != null) && !(obj instanceof java.util.ArrayList)){
                    g = (Group) obj;
                }

                if(g != null){
                    ArrayList userList = g.getUserList();
                    if(userList != null){

                        for (int k = 0; k < userList.size(); k++){
                            UserListBean ulb = (UserListBean) userList.get(k);
                            if(log.isDebugEnabled()){
                                // log.debug("The beans time in millis is:
                                // "+ulb.getLabel()+":"+ulb.getTime() +
                                // COUNTER);
                            }
                            if((ulb.getTime() + COUNTER) <= System.currentTimeMillis()){
                                userList.remove(ulb);
                                String asd = ulb.getValue();
                                if(dList.contains(asd)) dList.remove(asd);

                                if(log.isDebugEnabled()) log.debug("Removed: " + asd + " and " + ulb);

                            }

                        }
                        g.setUserList(userList);
                    }
                    activeGroups.put(new Long(g.getId()), g);
                }
            }
            activeGroups.put(UNIQUE, dList);

        }

    }

}