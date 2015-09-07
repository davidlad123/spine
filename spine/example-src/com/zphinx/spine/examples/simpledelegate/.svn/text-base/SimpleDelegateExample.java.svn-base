/**
 * SimpleDelegateExample.java
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

package com.zphinx.spine.examples.simpledelegate;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.core.viewprocessors.DefaultViewProcessor;
import com.zphinx.spine.core.viewprocessors.ViewProcessorFactory;
import com.zphinx.spine.examples.ExamplePrimer;
import com.zphinx.spine.vo.ResultObject;
import com.zphinx.spine.vo.dto.DataTransportBean;

/**
 * SimpleDelegateExample demonstrates the use of the simple delegate configuration used by spine
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SimpleDelegateExample extends ExamplePrimer {

    /**
     * The log instance for this object
     */
    private static final transient Logger logger = Universal.getLogger(SimpleDelegateExample.class.getName());

    /**
     * Public Constructor
     * 
     * @throws Exception
     */
    public SimpleDelegateExample() throws Exception {
        super();
    }

    @Override
    protected void run() {

        // get a viewProcessor
        DefaultViewProcessor viewProcessor = (DefaultViewProcessor) ViewProcessorFactory.getInstance().createProcessor("simpleProcessor");
        // create a dto
        DataTransportBean dtb = new DataTransportBean("a_data_bean", new Object());
        dtb.setId("123425663671");
        String aPath = "groups.xml";
        // run processor.processData
        ResultObject res = viewProcessor.processData(dtb, new String[] { aPath, "true" });
        logger.debug(res);
       // logger.debug(res.getDisplayMessages().getAllMessageString());
       // logger.debug(res.getDisplayMessages().getAllErrorString());
    }

    /**
     * Invokes this example
     * 
     * @param args
     */

    public static void main(String[] args) {

        try{
            SimpleDelegateExample sde = new SimpleDelegateExample();
            sde.run();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

}
