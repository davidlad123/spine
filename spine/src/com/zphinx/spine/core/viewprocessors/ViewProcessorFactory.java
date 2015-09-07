/**
 * ViewProcessorFactory.java
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

package com.zphinx.spine.core.viewprocessors;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.start.DelegateConfiguration;
import com.zphinx.spine.start.SpineConfiguration;

/**
 * ViewProcessorFactory dictates how and when ViewProcessors are created by this system. A process should call this factory to retrieve the ViewProcessor which it needs to use
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class ViewProcessorFactory {

    /**
     * The log instance for this object
     */
    private static final transient Logger log = Universal.getLogger(ViewProcessorFactory.class.getName());

    /**
     * The map used to store ViewProcessors
     */
    private static Hashtable viewProcessorMap = null;

    /**
     * The static instance of this object
     */
    private static ViewProcessorFactory viewFactory = null;

    /**
     * An arbitary object used for synchronization
     */
    private static Object obj = null;

    /**
     * Another arbitary object used for synchronization
     */
    private static Object multiObj = null;

    /**
     * Default Constructor - Initializes this factory by ensuring that the viewProcessorMap is non null.
     */
    private ViewProcessorFactory() {
        super();
        viewProcessorMap = new Hashtable();
        obj = new Object();
        multiObj = new Object();
    }

    /**
     * Gets the only static instance of this object
     * 
     * @return The static instance of this object
     */
    public static ViewProcessorFactory getInstance() {
        if(viewFactory == null){
            viewFactory = new ViewProcessorFactory();
        }
        return viewFactory;

    }

    /**
     * Creates a new processor of the specified name
     * 
     * @param processorConfigName The name of the processor in configuration
     * @return An instance of a processor
     */
    public ViewProcessor createProcessor(String processorConfigName) {

        SpineConfiguration sc = SpineConfiguration.getInstance(0);
        DelegateConfiguration dbp = sc.get(processorConfigName);
        String processorClass = dbp.getProcessorClass();
        ViewProcessor processor = initProcessor(processorClass);
        if(processor != null) processor.setProcessorAliasName(processorConfigName);
        return processor;
    }

    /**
     * Creates a new processor of the specified name
     * 
     * @param processorName The full class name of the processor we wish to create
     * @return An instance of a processor
     */
    private final ViewProcessor initProcessor(String processorName) {

        synchronized (obj){

            ViewProcessor processor = null;

            try{
                processor = (ViewProcessor) Class.forName(processorName).newInstance();

            }
            catch (InstantiationException e){
                log.error("Error instantiating object: " + e);

            }
            catch (IllegalAccessException e){
                log.error("Access denied accessing object: " + e);
            }
            catch (ClassNotFoundException e){
                log.error("Class not found: " + e);
            }
            return processor;
        }

    }

}
