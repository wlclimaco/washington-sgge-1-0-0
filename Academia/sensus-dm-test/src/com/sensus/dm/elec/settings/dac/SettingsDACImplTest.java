package com.sensus.dm.elec.settings.dac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class SettingsDACImplTest.
 * 
 * @author QAT Global.
 */
public class SettingsDACImplTest extends AbstractTestBaseDAC
{
	// -------------------------------------------------------------------------
	// Logs.

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(SettingsDACImplTest.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant SHOULD_BE_SUCCESS. */
	private static final String SHOULD_BE_SUCCESS = "Status should be OperationSuccess";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The Settings dac. */

	private ISettingsDAC settingsDAC; // injected by Spring through setter

	/**
	 * Gets the settings dac.
	 * 
	 * @return the settings dac
	 */
	public ISettingsDAC getSettingsDAC()
	{
		return settingsDAC;
	}

	/**
	 * Sets the settings dac.
	 * 
	 * @param settingsDAC the new settings dac
	 */
	@Resource
	public void setSettingsDAC(ISettingsDAC settingsDAC)
	{
		this.settingsDAC = settingsDAC;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all users.
	 */
	@Test
	public void testFetchAllUsers()
	{
		insertUser();

		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		InternalResultsResponse<UserContext> response = getSettingsDAC().fetchAllUser(request);
		for (UserContext userContext : response.getResultsList())
		{
			LOG.info("User ID: [" + userContext.getUserId() + "]");
		}
		assertNotNull(response);
		assertEquals(SHOULD_BE_SUCCESS, Status.OperationSuccess, response.getStatus());

	}

	/**
	 * Test insert user.
	 */
	@Test
	public void testInsertUser()
	{
		insertUser();
	}

	/**
	 * Insert user.
	 */
	private void insertUser()
	{
		// success
		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		InternalResponse response = getSettingsDAC().insertUser(request);
		assertNotNull(response);
		assertEquals(SHOULD_BE_SUCCESS, Status.OperationSuccess, response.getStatus());
	}

}
