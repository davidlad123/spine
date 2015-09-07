/**
 * DTOWrapper.java
 * Copyright (C) 2008  Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING WITH ZPHINX SOFTWARE SOLUTIONS 
 *  "AS IS" WITHOUT WARRANTY
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

package com.zphinx.spine.vo.dto;

import java.util.Locale;

/**
 * DTOWrapper serves as a wrapper for java objects. Objects encapsulated within this wrapper are provide 
 * with the means tp morph as a DataTransferObject and automatically possess the capability of DataTransferObjects
 * and can be retrieved using the getObject() method.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Oct 31, 2007 9:28:50 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DTOWrapper implements DataTransferObject {

	/**
	 * The Locale where this object was created or is been used
	 */
	private Locale locale = null;
    /**
     * 
     */
    private static final long serialVersionUID = 6584840823846452914L;

    /**
     * The object saved in this Warpper
     */
    private Object object = null;

    /**
     * This beans unique ID
     */
    private String sessionId = null;

    /**
     * Public Constructor
     * 
     * @param object The object which is wrapped within this object
     */
    public DTOWrapper(Object object) {
        super();
        this.object = object;
    }

    /**
     * Gets the wrapped object
     * 
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.vo.dto.DataTransferObject#getSessionId()
     */

    public String getSessionId() {

        return sessionId;
    }

    /**
     * Sets this beans sessionId
     * 
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

	/*
	 * (non-Javadoc)
	 * @see com.zphinx.spine.vo.dto.DataTransferObject#getLocale()
	 */
	public Locale getLocale() {
		if(this.locale == null){
			this.locale = Locale.getDefault();
		}
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}


    
}
