package com.sensus.dm.elec.settings.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.settings.bcl.ISettingsBCL;
import com.sensus.dm.elec.settings.model.response.InquiryUserResponse;

/**
 * The Class SettingsBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/elec/settings/settingsbcfimpltest.xml"})
public class SettingsBCFImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant FETCH_SYSTEM_SETTINGS. */
	private static final String FETCH_SYSTEM_SETTINGS = "fetchSystemSettings";

	/** The Constant FETCH_ALL_USERS. */
	private static final String FETCH_ALL_USERS = "fetchAllUsers";

	/** The Constant FETCH_USER_SETTINGS. */
	private static final String FETCH_USER_SETTINGS = "fetchUserSettings";

	/** The Constant UPSERT_SYSTEM_SETTINGS. */
	private static final String UPSERT_SYSTEM_SETTINGS = "upsertSystemSettings";

	/** The Constant UPSERT_USER_SETTINGS. */
	private static final String UPSERT_USER_SETTINGS = "upsertUserSettings";

	/** The Constant INSERT_USER. */
	private static final String INSERT_USER = "insertUser";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_SETTINGS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_SETTINGS_BCF_EXCEPTION_MSG = "sensus.dm.elec.settingsbcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The settings bcf. */
	private ISettingsBCF settingsBCF; // injected by Spring through setter

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF the new settings bcf
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests Settings:

	/**
	 * Sets the custom search area.
	 */
	public void setSettingsArea()
	{
		setArea(getSettingsBCF(), EPMAreaEnum.SETTINGS);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setSettingsArea();
	}

	/**
	 * Removes the custom search area.
	 */
	@After
	public void resetMocksToActionArea()
	{
		resetMocksData(getSettingsBCF());
		setSettingsArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch system settings.
	 */
	@Test
	public void testFetchSystemSettings()
	{
		// Exception
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class,
				FETCH_SYSTEM_SETTINGS);

		PropertyResponse response = getSettingsBCF().fetchSystemSettings(new PropertyRequest());

		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);

		resetMocksToActionArea();

		// Success
		setSituation(getSettingsBCF(), SituationsEnum.SUCCESS, ISettingsBCL.class,
				FETCH_SYSTEM_SETTINGS);
		response = getSettingsBCF().fetchSystemSettings(new PropertyRequest(TestBaseUtil
				.createUserContext()));
		// no parameter is required for this method to work
		assertEquals(true, response.isOperationSuccess());
		resetMocksToActionArea();

	}

	/**
	 * test fetch all users.
	 */

	@Test
	public void testfetchAllUsers()
	{
		// exception
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class,
				FETCH_ALL_USERS);

		InquiryUserResponse response = getSettingsBCF().fetchAllUsers(new PropertyRequest());

		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);

		resetMocksToActionArea();

		// success
		setSituation(getSettingsBCF(), SituationsEnum.SUCCESS, ISettingsBCL.class,
				FETCH_ALL_USERS);
		response = getSettingsBCF().fetchAllUsers(new PropertyRequest());
		// no parameter is required for this method to work
		assertEquals(true, response.isOperationSuccess());

		resetMocksToActionArea();
	}

	/**
	 * 
	 * Test fetch user settings.
	 */
	@Test
	public void testFetchUserSettings()
	{
		// Exception
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class,
				FETCH_USER_SETTINGS);

		PropertyResponse response = getSettingsBCF().fetchUserSettings(new PropertyRequest(TestBaseUtil
				.createUserContext()));

		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);

		resetMocksToActionArea();

		// Failure tenant request required
		response =
				getSettingsBCF().fetchUserSettings(
						new PropertyRequest());
		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Success
		setSituation(getSettingsBCF(), SituationsEnum.SUCCESS, ISettingsBCL.class,
				FETCH_USER_SETTINGS);
		response = getSettingsBCF().fetchUserSettings(new PropertyRequest(TestBaseUtil
				.createUserContext()));
		// no parameter is required for this method to work
		assertEquals(true, response.isOperationSuccess());
		resetMocksToActionArea();
	}

	/**
	 * Test upsert settings.
	 */
	@Test
	public void testUpsertSystemSettings()
	{
		// Exception
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class,
				UPSERT_SYSTEM_SETTINGS);

		PropertyRequest propertyRequest = new PropertyRequest(TestBaseUtil
				.createUserContext());

		propertyRequest.setProperties(new ArrayList<Property>());

		Property property = new Property(USER);
		property.setPropertyValue(USER);
		property.setPropertyName(USER);

		propertyRequest.getProperties().add(property);

		PropertyResponse response = getSettingsBCF().upsertSystemSettings(propertyRequest);

		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);

		resetMocksToActionArea();

		// Failure tenant request required
		response = getSettingsBCF().upsertSystemSettings(new PropertyRequest());
		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Success
		setSituation(getSettingsBCF(), SituationsEnum.SUCCESS, ISettingsBCL.class,
				UPSERT_SYSTEM_SETTINGS);
		response =
				getSettingsBCF().upsertSystemSettings(propertyRequest);
		// no parameter is required for this method to work
		assertEquals(true, response.isOperationSuccess());
		resetMocksToActionArea();

	}

	/**
	 * Test upsert settings.
	 */
	@Test
	public void testUpsertUserSettings()
	{
		// Exception
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class,
				UPSERT_USER_SETTINGS);

		PropertyRequest propertyRequest = new PropertyRequest(TestBaseUtil
				.createUserContext());

		propertyRequest.setProperties(new ArrayList<Property>());

		Property property = new Property(USER);
		property.setPropertyValue(USER);
		property.setPropertyName(USER);

		propertyRequest.getProperties().add(property);

		PropertyResponse response = getSettingsBCF().upsertUserSettings(propertyRequest);

		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);

		resetMocksToActionArea();

		// Failure tenant request required
		response = getSettingsBCF().upsertUserSettings(new PropertyRequest());
		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Success
		setSituation(getSettingsBCF(), SituationsEnum.SUCCESS, ISettingsBCL.class,
				UPSERT_USER_SETTINGS);
		response =
				getSettingsBCF().upsertUserSettings(propertyRequest);
		// no parameter is required for this method to work
		assertEquals(true, response.isOperationSuccess());
		resetMocksToActionArea();

	}

	/**
	 * Test insert user.
	 */
	@Test
	public void testInsertUser()
	{

		// Failure tenant request required
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class,
				INSERT_USER);

		PropertyResponse response = getSettingsBCF().insertUser(new PropertyRequest(TestBaseUtil
				.createUserContext()));

		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SETTINGS_BCF_EXCEPTION_MSG);

		resetMocksToActionArea();

		// Exception
		response = getSettingsBCF().insertUser(new PropertyRequest());
		// no parameter is required for this method to work
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Success
		setSituation(getSettingsBCF(), SituationsEnum.SUCCESS, ISettingsBCL.class,
				INSERT_USER);
		response = getSettingsBCF().insertUser(new PropertyRequest(TestBaseUtil
				.createUserContext()));
		// no parameter is required for this method to work
		assertEquals(true, response.isOperationSuccess());
		resetMocksToActionArea();
	}

}
