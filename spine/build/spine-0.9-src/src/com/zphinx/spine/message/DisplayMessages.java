/**
 * DisplayMessages.java
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

package com.zphinx.spine.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.i18n.MessageManager;
import org.apache.commons.i18n.MessageNotFoundException;
import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineApplicationException;
import com.zphinx.spine.resources.ConfigMessageProvider;

/**
 * DisplayMessages is a container for a collection of messages each identified
 * by a unique key. It serves as a repository for both DisplayMessage and
 * DisplayError objects and returns arrays of both types of message objects.
 * <p>
 * This object contains counters which contain the following information:
 * <ul type=disc>
 * <li>errorKeyCounter - The number of unique error keys in this container</li>
 * <li>errorValueCounter - The total number of errors in this object </li>
 * <li>messageKeyCounter - The number of unique message keys in this container</li>
 * <li>messageValueCounter - The total number of messages in this object</li>
 * </ul>
 * </p>
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Sep 7, 2007 4:16:03 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class DisplayMessages implements Serializable {

	/**
	 * The <code>Log</code> instance for this application.
	 */

	private static Logger log = Universal
			.getLogger("com.zphinx.spine.messages.DisplayMessages");

	/**
	 * The resource from which to fetch Messages
	 */
	private static final String MESSAGE_RESOURCES = "com.zphinx.spine.resources.SpineMessages";

	/**
	 * The id given to the resource
	 */
	private static final String RESOURCE = "resource";

	/**
	 * The serializable id of this object
	 */
	private static final long serialVersionUID = 3701352626662600551L;

	/**
	 * The HashMap associated with this instance
	 */
	private HashMap map = null;

	/**
	 * The total number of message keys in this collection
	 */
	private int messageKeyCounter = 0;

	/**
	 * The total number of messages in this collection
	 */
	private int messageValueCounter = 0;

	/**
	 * The total number of error keys in this collection
	 */
	private int errorKeyCounter = 0;

	/**
	 * The locale used to fetch messages
	 */
	private Locale locale = null;

	/**
	 * The total number of errors in this collection
	 */

	private int errorValueCounter = 0;

	/**
	 * Public Constructor
	 */
	public DisplayMessages() {
		super();
		initialize(Locale.getDefault());
	}

	/**
	 * Constructs a DisplayMessages with a given locale
	 * 
	 * @param locale
	 *            The Locale to use for creating messages
	 */
	public DisplayMessages(Locale locale) {
		super();
		if (locale == null) {
			locale = Locale.getDefault();
		}
		initialize(locale);
	}

	/**
	 * Initialize this object
	 * 
	 * @param locale
	 *            The locale to use
	 * @throws MessageNotFoundException
	 */
	private void initialize(final Locale locale)
			throws MessageNotFoundException {
		this.map = new HashMap();
		final ConfigMessageProvider rbm = new ConfigMessageProvider(
				MESSAGE_RESOURCES);
		MessageManager.addMessageProvider(RESOURCE, rbm);
		this.locale = locale;
	}

	/**
	 * Clears all entries in this object
	 */
	public void clear() {
		map.clear();
		this.errorKeyCounter = 0;
		this.errorValueCounter = 0;
		this.messageValueCounter = 0;
		this.messageKeyCounter = 0;
	}

	/**
	 * Checks to see that this key is available within this object
	 * 
	 * @param key
	 *            The key to search for within this object
	 * @return True if this key is available within this object
	 * @see java.util.HashMap#containsKey(java.lang.Object)
	 */
	public boolean containsKey(final String key) {
		return map.containsKey(key);
	}

	/**
	 * Gets all the errors identified by the given key
	 * 
	 * @param key
	 *            The key which identifies the errors
	 * @return The array of DisplayErrors saved under the given key
	 */
	public DisplayError[] getErrors(final String key) {

		DisplayError[] errors = null;
		final List errorsList = (List) map.get(key);
		if (errorsList.get(0) instanceof DisplayError) {
			errors = (DisplayError[]) errorsList.toArray(new DisplayError[] {});
		}
		return errors;

	}

	/**
	 * Gets all the messages identified by the given key
	 * 
	 * @param key
	 *            The key under which the message are saved
	 * @return The array of DisplayErrors saved using the given key
	 */
	public DisplayMessage[] getMessages(final String key) {
		DisplayMessage[] messages = null;
		final List messagesList = (List) map.get(key);
		if (messagesList.get(0) instanceof DisplayMessage) {
			messages = (DisplayMessage[]) messagesList
					.toArray(new DisplayMessage[] {});
		}
		return messages;

	}

	/**
	 * Checks if this object contains any entries
	 * 
	 * @return true if this object contains no entries
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * Checks to see if any messages are stored in this object
	 * 
	 * @return true if this object contains at least one Display message object
	 */
	public boolean containsMessages() {
		return (messageValueCounter > 0);
	}

	/**
	 * Checks to see if any errors are stored in this object
	 * 
	 * @return True if this object contains at least one display error object
	 */
	public boolean containsErrors() {
		return (errorValueCounter > 0);
	}

	/**
	 * Adds a message to this object, by setting a key with which the message
	 * and other messages of the same key will be saved. Both messages and
	 * errors are saved using different keys.
	 * 
	 * @param key
	 *            The key by which this message is saved
	 * @param value
	 *            The message or error object to be saved
	 */
	public void add(final String key, final DisplayMessage value) {
		add(key, value, this.locale);
	}

	/**
	 * Adds a message to this object, by setting a key under which the message
	 * and other messages of the same key will be saved. Both messages and
	 * errors are saved under different keys.
	 * 
	 * @param key
	 *            The key by which this message is saved
	 * @param value
	 *            The message or error object to be saved
	 * @param locale
	 *            The locale in which this message exists
	 */
	public void add(final String key, final DisplayMessage value,
			final Locale locale) {
		// this uses Resource as an id, but our message provide does not use
		// this param .... Extend MessageManager?
		final String s = MessageManager.getText(RESOURCE, value.getMessage(),
				value.getArguments(), locale, value.getMessage());
		value.setArguments(null);
		value.setMessage(s);

		try {
			addStringMessage(key, value, locale);
		} catch (final RuntimeException e) {
			e.printStackTrace();
		} catch (final SpineApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Adds a message to this object
	 * 
	 * @param key
	 *            The key by which this message is saved
	 * @param value
	 *            The message or error object to be saved
	 * @param locale
	 *            The locale in which this message exists
	 * @throws RuntimeException
	 * @throws SpineApplicationException
	 */
	private void addStringMessage(final String key, final DisplayMessage value,
			final Locale locale) throws RuntimeException,
			SpineApplicationException {
		boolean isError = (value instanceof DisplayError);
		if (map.keySet().contains(key)) {

			final List keyedList = (List) map.get(key);
			if ((keyedList.get(0) instanceof DisplayError) && isError) {
				keyedList.add(value);
				errorValueCounter++;
			} else if ((keyedList.get(0) instanceof DisplayError) && !isError)
				throw new SpineApplicationException(
						"Cannot save messages under an error key");
			else if ((keyedList.get(0) instanceof DisplayMessage) && !isError) {
				keyedList.add(value);
				messageValueCounter++;
			} else
				throw new SpineApplicationException(
						"Cannot save errors under a message key");
		} else {
			final ArrayList list = new ArrayList(5);
			list.add(value);
			this.map.put(key, list);
			if (isError) {
				errorKeyCounter++;
				errorValueCounter++;
			} else {
				messageKeyCounter++;
				messageValueCounter++;
			}

		}
	}

	/**
	 * Removes this key and it's value from this object
	 * 
	 * @param key
	 *            The key which we wish to remove
	 * @return True if this object was successfully removed and false if it does
	 *         not exist within the collection
	 */
	public boolean remove(final String key) {
		boolean b = false;
		final Object obj = map.remove(key);
		final List aList = ((List) obj);
		boolean isError = false;
		if (aList.get(0) instanceof DisplayError)
			isError = true;
		if (obj != null) {
			b = true;
			if (isError) {
				errorKeyCounter--;
				errorValueCounter -= aList.size();
			} else {

				messageKeyCounter--;
				messageValueCounter -= aList.size();
			}
		}
		return b;
	}

	/**
	 * Gets the size of this collection
	 * 
	 * @return The size of this collection
	 */
	public int size() {
		return map.size();
	}

	/**
	 * Gets the size of the message keys in this collection
	 * 
	 * @return The size of the message keys in this collection
	 */
	public int messageKeySize() {
		return messageKeyCounter;
	}

	/**
	 * Gets the size of the error keys in this collection
	 * 
	 * @return The size of the error keys in this collection
	 */
	public int errorKeySize() {
		return errorKeyCounter;
	}

	/**
	 * The string representation of this object
	 * 
	 * @return The string representation of this object
	 */
	public String toString() {
		return map.toString();
	}

	/**
	 * Gets a collection of all the keys in this DisplayMessages object
	 * 
	 * @return A Set of all the keys in this DisplayMessages
	 * @see java.util.HashMap#keySet()
	 */
	public Set keySet() {
		return map.keySet();
	}

	/**
	 * Gets all the errors as a string seperated by a carriage return character
	 * 
	 * @return All the errors as a string seperated by a carriage return
	 *         character
	 */
	public String getAllErrorString() {
		String out = null;
		if (this.containsErrors()) {
			out = formatMessageString(true);
		}
		return out;
	}

	/**
	 * Gets all the messages as a string seperated by a carriage return
	 * character
	 * 
	 * @return All the messages as a string seperated by a carriage return
	 *         character
	 */
	public String getAllMessageString() {
		String out = null;
		if (this.containsMessages()) {
			out = formatMessageString(false);
		}
		return out;
	}

	/**
	 * Formats the message to be sent as a string
	 * 
	 * @param isError
	 *            True is we are working with a DisplayError
	 * @return The formatted string to be output to the client code
	 */
	private String formatMessageString(boolean isError) {
		final Iterator it = map.keySet().iterator();
		final StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			final String key = (String) it.next();
			final List keyedList = (List) map.get(key);
			boolean b = false;
			if (!isError) {
				b = !(keyedList.get(0) instanceof DisplayError);
			} else {
				b = (keyedList.get(0) instanceof DisplayError);
			}
			if (b) {
				for (int i = 0; i < keyedList.size(); i++) {
					final String s = ((DisplayMessage) keyedList.get(i))
							.getMessage();
					sb.append(s + "\r");
				}
			}
		}
		return sb.toString();
	}

}
