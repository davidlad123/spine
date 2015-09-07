/**
 * MysqlDataSourceBuilder.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * Copyright &copy;Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE ZPHINX SOFTWARE SOLUTIONS 
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

package com.zphinx.spine.start.helpers.impl;

import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zphinx.spine.Universal;
import com.zphinx.spine.start.helpers.DataSourceBuilder;

/**
 * MysqlDataSourceBuilder is the DataSourceBuilder used to create a Mysql
 * DataSource by the spine system
 * 
 * @author David Ladapo
 * @version $1.0
 *          <p>
 *          Created: Apr 25, 2008 11:23:09 AM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 */
public class MysqlDataSourceBuilder implements DataSourceBuilder {

	/**
	 * 
	 */
	private static final String LOGIN_TIMEOUT = "loginTimeout";

	/**
	 * 
	 */
	private static final String PROFILE_SQL = "profileSql";

	/**
	 * The <code>Log</code> instance for this application.
	 */

	private static Logger log = Universal
			.getLogger(MysqlDataSourceBuilder.class.getName());

	/**
	 * 
	 */
	private static final String PORT = "port";

	/**
	 * 
	 */
	private static final String SOURCE_CLASS = "sourceClass";

	/**
	 * 
	 */
	private static final String URL = "url";

	/**
	 * 
	 */
	private static final String SERVER_NAME = "serverName";

	/**
	 * 
	 */
	private static final String DATABASE_NAME = "databaseName";

	/**
	 * 
	 */
	private static final String PASSWORD = "password";

	/**
	 * 
	 */
	private static final String USER_NAME = "userName";

	/**
	 * Public Constructor
	 */
	public MysqlDataSourceBuilder() {
		super();
	}

	/**
	 * Creates a Mysql dataSource using the specified input properties
	 * 
	 * @param sourceClass
	 *            The name of the class which represents the dataSource
	 * @param userName
	 *            The userName used to create the database
	 * @param password
	 *            The password used to access the database
	 * @param databaseName
	 *            The name of the dataBase instance
	 * @param url
	 *            The Url of the database
	 * @param port
	 *            The port at which the database resides
	 * @param server
	 *            The name of the server hosting the database
	 * @param profileSql
	 *            The profile sql to run after initialization
	 * @param loginTimeout
	 *            The timeout value for user logon to the database instance
	 * @return The DataSource instance
	 */
	private DataSource createMysqlDataSource(String sourceClass,
			String userName, String password, String databaseName, String url,
			int port, String server, String profileSql, int loginTimeout) {
		MysqlDataSource source = null;
		try {

			source = (MysqlDataSource) Class.forName(sourceClass).newInstance();
			source.setDatabaseName(databaseName);
			source.setPort(port);
			source.setPassword(password);
			source.setUrl(url);
			source.setUser(userName);
			source.setServerName(server);
			source.setLoginTimeout(loginTimeout);
			source.setProfileSql(profileSql);

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zphinx.spine.start.helpers.DataSourceBuilder#createDataSource(java.util.Map)
	 */
	public DataSource createDataSource(Map map) {
		Iterator it = map.keySet().iterator();
		String userName = null;
		String password = null;
		String databaseName = null;
		String url = null;
		String sourceClass = null;
		String server = null;
		int port = 3306;
		int loginTimeout = 15;
		String profileSql = null;
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = (Object) map.get(key);

			if (key.equalsIgnoreCase(USER_NAME)) {
				userName = (String) value;
			} else if (key.equalsIgnoreCase(PASSWORD)) {
				password = (String) value;
			} else if (key.equalsIgnoreCase(DATABASE_NAME)) {
				databaseName = (String) value;
			} else if (key.equalsIgnoreCase(URL)) {
				url = (String) value;
			} else if (key.equalsIgnoreCase(PORT)) {
				try {
					port = Integer.parseInt((String) value);
				} catch (NumberFormatException e) {
					log
							.debug("Unable to set port address, setting port to default: "
									+ port);
				}
			} else if (key.equalsIgnoreCase(LOGIN_TIMEOUT)) {
				try {
					loginTimeout = Integer.parseInt((String) value);
				} catch (NumberFormatException e) {
					log
							.debug("Unable to set login timeout, setting timeout to default: "
									+ loginTimeout);
				}
			} else if (key.equalsIgnoreCase(SERVER_NAME)) {
				server = (String) value;
			} else if (key.equalsIgnoreCase(PROFILE_SQL)) {
				profileSql = (String) value;
			} else if (key.equalsIgnoreCase(SOURCE_CLASS)) {
				sourceClass = (String) value;
			}
		}

		return this.createMysqlDataSource(sourceClass, userName, password,
				databaseName, url, port, server, profileSql, loginTimeout);
	}

}
