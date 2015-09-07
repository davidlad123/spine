/**
 * SimpleDelegateExample.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
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
 * SimpleDelegateExample demonstrates the use of the simple delegate
 * configuration used by spine
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Feb 24, 2008 3:08:17 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SimpleDelegateExample extends ExamplePrimer {

	/**
	 * The log instance for this object
	 */
	private static final transient Logger logger = Universal
			.getLogger(SimpleDelegateExample.class.getName());

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
		DefaultViewProcessor viewProcessor = (DefaultViewProcessor) ViewProcessorFactory
				.getInstance().createProcessor("simpleProcessor");
		// create a dto
		DataTransportBean dtb = new DataTransportBean("a_data_bean",
				new Object());
		dtb.setId("123425663673");
		String aPath = "groups.xml";
		// run processor.processData
		ResultObject res = viewProcessor.processData(dtb, new String[] { aPath,
				"true" });
		logger.debug(res);

	}

	/**
	 * Invokes this example
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		try {
			SimpleDelegateExample sde = new SimpleDelegateExample();
			sde.run();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
