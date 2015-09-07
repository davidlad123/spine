/**
 * MultiViewProcessorExample.java
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

package com.zphinx.spine.examples.multiview;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.viewprocessors.ViewProcessorFactory;
import com.zphinx.spine.examples.ExamplePrimer;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransferObject;

/**
 * MultiViewProcessorExample is an example which demonstrates the use of a MultiViewProcessor
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MultiViewProcessorExample extends ExamplePrimer {

    /**
     * 
     */
    private static final String MULTI_VIEW_PROCESSOR = "MultiViewProcessor";

    private final static String DELEGATE_NAME = "com.zphinx.spine.examples.multiview.MultiViewExampleDelegate";

    private final static String PATH = "groups.xml";

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger logger = Universal.getLogger(ExtendedMultiViewProcessor.class.getName());

    /**
     * Public Constructor
     * 
     * @throws Exception
     */
    public MultiViewProcessorExample() throws Exception {
        super();
    }

    @Override
    public void run() {
        ExtendedMultiViewProcessor pro = (ExtendedMultiViewProcessor) ViewProcessorFactory.getInstance().createProcessor(MULTI_VIEW_PROCESSOR);
        ResultObject res = invokeProcessDB(pro);
        ResultObject res1 = invokeProcessDB1(pro);
        ResultObject res2 = invokeProcessDB2(pro);
        logger.debug(res2);

    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB(ExtendedMultiViewProcessor pro) {

        DataTransferObject dto = pro.createDTO(DELEGATE_NAME, 1);

        return pro.processData(dto, new String[] { PATH, "true" }, null, 1, 1);
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB1(ExtendedMultiViewProcessor pro) {

        DataTransferObject dto = pro.createDTO(DELEGATE_NAME, 2);
        return pro.processData(dto, getDataSource(), null, 2, 2);
    }

    /**
     * Invokes the process from view to data layer for a database backed object
     * 
     * @return
     */
    private ResultObject invokeProcessDB2(ExtendedMultiViewProcessor pro) {

        DataTransferObject dto = pro.createDTO(DELEGATE_NAME, 3);
        return pro.processData(dto, getDataSource(), null, 3, 3);
    }

    /**
     * @param args
     */

    public static void main(String[] args) {

        try{
            MultiViewProcessorExample sde = new MultiViewProcessorExample();
            sde.run();
        }
        catch (Exception e){
            logger.debug("Error: " + e.getMessage());
        }
    }
}
