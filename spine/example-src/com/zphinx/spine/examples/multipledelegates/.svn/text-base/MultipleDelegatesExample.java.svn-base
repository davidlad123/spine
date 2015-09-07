/**
 * MultipleDelegatesExample.java
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

package com.zphinx.spine.examples.multipledelegates;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.viewprocessors.DefaultViewProcessor;
import com.zphinx.spine.core.viewprocessors.ViewProcessorFactory;
import com.zphinx.spine.examples.ExamplePrimer;
import com.zphinx.spine.members.Member;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransportBean;

/**
 * MultipleDelegatesExample is an example of using the spine framework in multiple delegates mode
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultipleDelegatesExample extends ExamplePrimer {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger logger = Universal.getLogger(MultipleDelegatesExample.class.getName());

    /**
     * 
     */
    private static final String MULTI_DELEGATE_PROCESSOR = "MultiDelegateProcessor";

    /**
     * 
     */
    private static final String TESTGROUPS_XML = "groups.xml";

    /**
     * Public Constructor
     * 
     * @throws Exception
     */
    public MultipleDelegatesExample() throws Exception {
        super();

    }

    /**
     * Processes this example
     */
    protected void run() {

        // get a viewProcessor
        DefaultViewProcessor pro = (DefaultViewProcessor) ViewProcessorFactory.getInstance().createProcessor(MULTI_DELEGATE_PROCESSOR);

        ResultObject res = invokeProcess1(pro);
        ResultObject res1 = invokeProcess2(pro, (Member) res.getObj());
        
        ResultObject res2 = invokeProcess3(pro, (Member) res.getObj());
        logger.debug(res);
        logger.debug(res1);
        logger.debug(res2);
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcess1(DefaultViewProcessor pro) {
        DataTransportBean dtb = new DataTransportBean("myName", "password");
        dtb.setId(Universal.getRandom(6));
        //setSessionId();
        String aPath = TESTGROUPS_XML;
        return pro.processData(dtb, new String[] { aPath, "true" });
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcess2(DefaultViewProcessor pro, Member user) {
        DataTransportBean dtb = new DataTransportBean(user.getName(), "");
        dtb.setId(user.getId()+"");
        
        return pro.processData(dtb, getDataSource());
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcess3(DefaultViewProcessor pro, Member user) {
        DataTransportBean dtb = new DataTransportBean(user.getName(), "");
        dtb.setId(user.getId()+"");
        return pro.processData(dtb, getDataSource());
    }

    /**
     * Runs this example
     * 
     * @param args
     */

    public static void main(String[] args) {

        try{
            MultipleDelegatesExample sde = new MultipleDelegatesExample();
            sde.run();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

}
