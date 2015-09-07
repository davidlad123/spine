/**
* InitHelper.java
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
 
package com.zphinx.spine.unittests;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 
 * InitHelper serves as a singleton to help initialize test classes
 * @author David Ladapo
 * @version  $1.0
 * 
 * <p>Created: May 21, 2008 2:29:17 AM<br>
 * Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/
public final class InitHelper {

    /**
     * Public Constructor
     */
    private InitHelper() {
        super();
    }

    /**
       * Configures the log4J instance within the application 
     * @param logFile
     * @param watchTime
     * @return
     */
    public static boolean configureLog4J(String logFile, long watchTime) {
         boolean b = false;
        if(logFile != null){
            if(logFile.endsWith(".xml")){
                if(watchTime > 0){
                    DOMConfigurator.configureAndWatch(logFile, watchTime);
                }
                else{
                    DOMConfigurator.configureAndWatch(logFile);
                }
                b = true;
            }
            else{

                if(watchTime > 0){
                    PropertyConfigurator.configureAndWatch(logFile, watchTime);
                }
                else{
                    PropertyConfigurator.configureAndWatch(logFile);
                }
                b = true;
            }
            if(!b){
                System.err.println("Please specify the correct location of you log4j properties/xml file!!");

            }
        }
        return b;
    }
    
}
