/**
 * SpineBean2.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * <p>Copyright &copy;Zphinx Software Solutions</p>
 * 
 **/

package com.zphinx.spine.examples.beans;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.vo.dto.SpineBean;

/**
 * SpineBean2:- An example SpineBean
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Dec 14, 2007 11:05:16 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class SpineBean2 extends SpineBean {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(SpineBean2.class.getName());
    /**
     * 
     */
    private static final long serialVersionUID = -3971455306930857665L;

    /**
     * Public Constructor
     */
    public SpineBean2() {
        log.debug("Constructing spinebean2 instance . .. . ");
    }

}
