/**
 * DataTransportBean.java
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

package com.zphinx.spine.vo.dto;

import java.util.Locale;

import javax.management.Attribute;

/**
 * DataTransportBean extends Attribute to provide a String id for the java Attribute object
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: 20-Feb-2005 03:24:01<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DataTransportBean extends Attribute implements DataTransferObject {

    /**
     * 
     */
    private static final long serialVersionUID = -2167242338062658889L;

    /**
     * The String identifier of the object
     */
    private String id = null;

    /**
     * The locale where this object was created
     */
    private Locale locale = null;
    /**
     * Default Constructor
     * 
     * @param name The name of this object
     * @param value The object returned as this objects value
     */
    public DataTransportBean(String name, Object value) {
        super(name, value);

    }

    /**
     * Constructor which accepts an id
     * 
     * @param name The name of this object
     * @param value The object returned as this objects value
     * @param id The String identifier of the object
     */
    public DataTransportBean(String name, Object value, String id) {
        super(name, value);
        this.id = id;
    }

    /**
     * Gets the id of this Object
     * 
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of this Object
     * 
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zphinx.spine.vo.dto.DataTransferObject#getSessionId()
     */

    public String getSessionId() {

        return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer s = new StringBuffer("DataTransportBean: Name=[" + this.getName() + "], Value=[" + getValue() + "], Id=[" + id + "]");
        return s.toString();
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
