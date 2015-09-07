/**
 *
 * GroupsDAO.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * <p>created Tue Oct  7 22:14:45 BST 2003
 * copyright &copy; Zphinx Software Solutions</p>
 **/

package com.zphinx.spine.plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.zphinx.spine.Universal;
import com.zphinx.spine.data.DataPack;
import com.zphinx.spine.data.impl.AbstractDataBaseDAO;
import com.zphinx.spine.members.Application;
import com.zphinx.spine.members.Group;
import com.zphinx.spine.utils.ActiveGroups;
import com.zphinx.spine.utils.PreparedData;
import com.zphinx.spine.vo.dto.DataTransferObject;
import com.zphinx.spine.vo.dto.StringAttributeBean;

/**
 * An object to capture data relating to the groups in this system from the
 * database persistence layer.
 * 
 * @author David Ladapo (davidl@zphinx.com)
 * @version $Revision: 1.00
 *          <p>
 *          created Tue Oct 7 22:14:45 BST 2003 copyright &copy; Zphinx Software
 *          Solutions
 *          </p>
 * @see RoleConfigurationEngine
 */

public class GroupsDAO extends DataBaseAbstract {

	/**
	 * The <code>Log</code> instance for this application.
	 */

	private static Logger log = Universal.getLogger(GroupsDAO.class.getName());

	/**
	 * Constructor for object
	 * 
	 * @param dataSource
	 *            The DataSource Object passed to this Object
	 */
	public GroupsDAO(final DataSource dataSource) {
		super();
		if (dataSource != null) {
			try {
				setConnection(dataSource.getConnection());
				if (log.isDebugEnabled())
					log.debug("Succesfully initialized object");
			} catch (final SQLException e) {
				if (log.isDebugEnabled())
					log.debug("Could not initialize Groups DataAccessObject");
			}

		} else {
			if (log.isDebugEnabled())
				log.debug("Could not initialize Groups DataAccessObject");
		}

	}

	/**
	 * Generic Method to fetch groups data from database.
	 * 
	 * @param obj
	 *            The ActiveGroups object which contains the groups to map
	 * @return Object The object which encapsulates our results
	 * @see com.zphinx.spine.data.DataAbstract#fetchData(com.zphinx.spine.vo.dto.DataTransferObject)
	 */

	public Object fetchData(final DataTransferObject obj) {
		final ActiveGroups gMap = (ActiveGroups) obj;
		final ArrayList arl = new ArrayList();

		final String[] aColumn = { "*" };

		final DataPack dataPack = new DataPack(0);
		dataPack.setTableName("Groups");
		dataPack.setColumnNames(aColumn);
		final PreparedData pc = PreparedData.getInstance();
		dataPack.setSetClause("*");

		try {
			PreparedStatement ps = getConnection().prepareStatement(
					pc.select(dataPack));
			ResultSet results = ps.executeQuery();
			boolean doneFlag = false;

			// String[] groupArray = null;
			doneFlag = getGroupFromResults(gMap, arl, results, doneFlag, false);
			if (!doneFlag) {
				setErrors("..........Error from fetchData: Unable to acquire any groups!!.........");
				if (log.isDebugEnabled())
					log
							.debug("..........Error from fetchData: Unable to acquire any groups!!........");
			} else {

				if (results != null)
					results.close();
				doneFlag = false;
				dataPack.setTableName("Applications");
				ps = getConnection().prepareStatement(pc.select(dataPack));
				results = ps.executeQuery();

				doneFlag = getGroupFromResults(gMap, arl, results, doneFlag,
						true);
				if (!doneFlag) {
					setErrors("..........Error from fetchData: Unable to acquire any groups!!.........");
					if (log.isDebugEnabled())
						log
								.debug("..........Error from fetchData: Unable to acquire any groups!!........");
				}

				arl.trimToSize();
				// Universal.setGroupsList(arl);
			}

			if (ps != null)
				ps.close();

		} catch (final SQLException e) {
			e.printStackTrace();

		}

		catch (final Exception e) {
			setErrors("Error from fetchData: " + e.getMessage());
			if (log.isDebugEnabled())
				log.debug("Error from fetchData: " + e.getMessage());

		}

		return gMap;
	}

	/**
	 * @param gMap
	 * @param arl
	 * @param results
	 * @param doneFlag
	 * @return
	 * @throws SQLException
	 */
	private boolean getGroupFromResults(final ActiveGroups gMap,
			final ArrayList arl, final ResultSet results, boolean doneFlag,
			final boolean appFlag) throws SQLException {
		String rand;
		while (results.next()) {
			doneFlag = true;
			Group group = null;
			if (appFlag) {
				group = new Application();
			} else {
				group = new Group();
			}
			rand = results.getString(1);

			group.setId(rand);
			group.setUserName(results.getString(2));
			group.setFirstName(results.getString(3));
			group.setLastName(results.getString(4));
			group.setGroupName(new StringAttributeBean(results.getString(5),
					rand));
			group.setEmailAddress(results.getString(6));
			// group.setPassword1(results.getString(7));
			group.setCreationDate(Universal.getDateFromString(
					results.getString(8)).getTime());
			group.setModifiedDate(Universal.getDateFromString(results
					.getString(9)));
			group.setUrl(results.getString(10));

			group.setLastLogin(Universal.getDateFromString(results
					.getString(11)));
			group.setLastIp(results.getString(12));

			arl.add(new StringAttributeBean(results.getString(5), rand));

			gMap.addGroup(group);
		}
		return doneFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zphinx.securesite.data.DataAbstract#close()
	 */
	public boolean close() {
		if (getConnection() != null)
			try {
				getConnection().close();
                return true;
			} catch (final SQLException e) {
				log.debug("Error closing GroupsDAO: " + e);

			}
		return false;
	}

}