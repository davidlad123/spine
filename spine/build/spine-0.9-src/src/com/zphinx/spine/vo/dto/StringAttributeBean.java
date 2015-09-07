/**
 * StringAttributeBean.java
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

import com.zphinx.spine.Universal;

/**
 * StringAttributeBean defines a bean whose name and value can only be a String by
 * associating its name with its value.
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 6, 2007 12:44:39 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class StringAttributeBean implements DataTransferObject {

	/**
	 * The StringAttribute name.
	 */
	private String name = null;
	/**
	 * The Locale where this object is been used
	 */
	private Locale locale = null;
	/**
	 * The StringAttribute value
	 */
	private String value = null;

	/**
	 * This instance's unique serial ID
	 */
	private static final long serialVersionUID = -91523155188662740L;

	/**
	 * This beans unique ID
	 */
	private String sessionId = null;

	/**
	 * Constructor which sets the name and the value of this object
	 * 
	 * @param name
	 *            The name of the value to be used by this object
	 * @param value
	 *            The string which represents the value of this object
	 */
	public StringAttributeBean(final String name, final String value) {
		if (name == null)
			throw new RuntimeException(new IllegalArgumentException(
					"StringAttributeBean name cannot be null "));
		this.name = name;
		this.value = value;

	}

	/**
	 * @param name
	 *            The name of the value to be used by this object
	 * @param value
	 *            The string which represents the value of this object
	 * @param locale
	 *            The Locale of this object
	 */
	public StringAttributeBean(final String name, final String value,
			final Locale locale) {
		super();
		this.name = name;
		this.locale = locale;
		this.value = value;
	}

	/**
	 * @param name
	 *            The name of the value to be used by this object
	 * @param value
	 *            The string which represents the value of this object
	 * @param locale
	 *            The Locale of this object
	 * @param sessionId
	 *            The sessionId to set for this object
	 */
	public StringAttributeBean(final String name, final String value,
			final Locale locale, final String sessionId) {
		super();
		this.name = name;
		this.locale = locale;
		this.value = value;
		this.sessionId = sessionId;
	}

	/**
	 * Sets the value of this object
	 * 
	 * @param value
	 *            The string which represents the value of this object
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Sets the name of this object
	 * 
	 * @param name
	 *            The name of the value to be used by this object
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this object
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the name of this object
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Compares the current StringAttributeBean Object with another
	 * StringAttributeBean.
	 * 
	 * @param object
	 *            The object that the current StringAttributeBean is to be
	 *            compared with.
	 * @return True if the two StringAttributeBean objects are equal, otherwise
	 *         false.
	 */

	public boolean equals(final Object object) {
		boolean b = false;
		if (object instanceof StringAttributeBean) {
			final StringAttributeBean val = (StringAttributeBean) object;
			if (this.value == null) {
				if (val.getValue() == null) {
					b = name.equals(val.getName());
				} else {
					b = false;
				}
			} else {
				b = ((name.equals(val.getName())) && (value.equals(val
						.getValue())));
			}

		}
		return b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Universal.generateHashCode(this.value)
				+ Universal.generateHashCode(this.name);
	}

	/**
	 * Returns a description for this StringAttributeBean
	 */
	public String toString() {
		return "StringAttributeBean[name: " + name + "][value: " + value + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zphinx.spine.vo.dto.DataTransferObject#getSessionId()
	 */

	public String getSessionId() {
		if (sessionId.equals(null))
			sessionId = this.value;
		return sessionId;
	}

	/**
	 * Sets this beans sessionId
	 * 
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zphinx.spine.vo.dto.DataTransferObject#getLocale()
	 */
	public Locale getLocale() {
		if (this.locale == null) {
			this.locale = Locale.getDefault();
		}
		return locale;
	}

	/**
	 * Sets the Locale of this object
	 * 
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

}
