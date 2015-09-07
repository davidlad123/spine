/**
 * RoleConfigurationEngine
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
 */
package com.zphinx.spine.plugin;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.exceptions.SpineException;
import com.zphinx.spine.start.ConfigurationEngine;
import com.zphinx.spine.start.helpers.DataSourceServiceLocator;
import com.zphinx.spine.utils.ActiveGroups;
import com.zphinx.spine.utils.PersistentGroups;

/**
 * Serves as a configurationEngine used for populating the default groups map
 * 
 * @author David Ladapo
 * @version $Revision: 1.00
 *          <p>
 *          Date: Created on Mar 7, 2008
 *          </p>
 *          <p>
 *          Copyright &copy; Zphinx Software Solutions
 *          </p>
 */
public class RoleConfigurationEngine implements ConfigurationEngine {

	/**
	 * The <code>Log</code> instance for this application.
	 */

	private static Logger log = Universal
			.getLogger(RoleConfigurationEngine.class.getName());

	/**
	 * 
	 */
	public RoleConfigurationEngine() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zphinx.spine.start.ConfigurationEngine#process(java.util.Map,
	 *      javax.sql.DataSource)
	 */
	@Override
	public void process(final Map map) {
		try {
            final DataSource dataSource = DataSourceServiceLocator.getInstance().getDataSource("default");
			if (dataSource != null) {
				// the dao used to fetch a map of groups data from the db
				final GroupsDAO groupD = new GroupsDAO(dataSource);

				final ActiveGroups sg = PersistentGroups.getMap();
				final ActiveGroups ag = (ActiveGroups) groupD.fetchData(sg);
				PersistentGroups.setMap(ag);
				if (groupD.getErrors() != null) {
					log.debug("Error:.................................... "
							+ groupD.getErrors());
				}
				groupD.close();
			} else {
				log
						.debug("Error:............................. Missing datasource!!");
			}
		} catch (final SpineException e) {
			
			e.printStackTrace();
		}

	}

}
