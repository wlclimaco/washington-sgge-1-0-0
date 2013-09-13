package com.sensus.dm.elec.settings.bcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.bcl.IPropertyBCL;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class SettingsBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/elec/settings/settingsbclimpltest.xml"})
public class SettingsBCLImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant UPSERT_PROPERTY. */
	private static final String UPSERT_PROPERTY = "upsertProperty";

	/** The Constant FETCH_PROPERTY. */
	private static final String FETCH_PROPERTY = "fetchProperty";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The settings bcl. */

	private ISettingsBCL settingsBCL; // injected by Spring through setter

	/** The property bcl. */

	private IPropertyBCL propertyBCL; // injected by Spring through setter

	/**
	 * Gets the settings bcl.
	 * 
	 * @return the settings bcl
	 */
	public ISettingsBCL getSettingsBCL()
	{
		return settingsBCL;
	}

	/**
	 * Sets the settings bcl.
	 * 
	 * @param settingsBCLValue the new settings bcl
	 */
	@Resource(name = "settingsBCLTarget")
	public void setSettingsBCL(ISettingsBCL settingsBCLValue)
	{
		settingsBCL = settingsBCLValue;
	}

	/**
	 * Gets the property bcl.
	 * 
	 * @return the property bcl
	 */
	public IPropertyBCL getPropertyBCL()
	{
		return propertyBCL;
	}

	/**
	 * Sets the property bcl.
	 * 
	 * @param propertyBCL the new property bcl
	 */
	@Resource
	public void setPropertyBCL(IPropertyBCL propertyBCL)
	{
		this.propertyBCL = propertyBCL;
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
		setArea(getSettingsBCL(), EPMAreaEnum.SETTINGS);
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
		resetMocksData(getSettingsBCL());
		setSettingsArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch user settings.
	 */
	@Test
	public void testFetchUserSettings()
	{
		// Error
		setSituation(getSettingsBCL(), SituationsEnum.ERROR, IPropertyBCL.class,
				FETCH_PROPERTY);

		PropertyRequest request = new PropertyRequest();
		request.setUserContext(new UserContext(STRING_ONE));

		InternalResultsResponse<Property> response = getSettingsBCL().fetchUserSettings(request);
		assertNotNull(response.getResultsList());
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocksToActionArea();

		// Success
		PropertyRequest request2 = new PropertyRequest();
		request2.setUserContext(new UserContext(STRING_ONE));

		InternalResultsResponse<Property> response2 = getSettingsBCL().fetchUserSettings(request);
		assertNotNull(response2.getResultsList());
		assertEquals(Status.OperationSuccess, response2.getStatus());

	}

	/**
	 * Test fetch system settings.
	 */
	@Test
	public void testFetchSystemSettings()
	{
		// Error
		setSituation(getSettingsBCL(), SituationsEnum.ERROR, IPropertyBCL.class,
				FETCH_PROPERTY);

		PropertyRequest request = new PropertyRequest();
		request.setPropertyProviderType(TestBaseUtil.USER_PROVIDER);

		UserContext userContext = new UserContext(USER);
		request.setUserContext(userContext);

		InternalResultsResponse<Property> response = getSettingsBCL().fetchSystemSettings(request);

		assertNotNull(response.getResultsList());
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocksToActionArea();

		setSituation(getSettingsBCL(), SituationsEnum.ERROR, IPropertyBCL.class,
				UPSERT_PROPERTY);

		response = getSettingsBCL().fetchSystemSettings(request);

		assertNotNull(response.getResultsList());
		assertEquals(Status.ExceptionError, response.getStatus());

		resetMocksToActionArea();

		// Success
		PropertyRequest request2 = new PropertyRequest();
		request2.setUserContext(TestBaseUtil.createUserContext());

		InternalResultsResponse<Property> response2 = getSettingsBCL().fetchSystemSettings(request);

		assertNotNull(response2.getResultsList());
		assertEquals(SEVEN, response2.getResultsList().size());
		assertEquals(Status.OperationSuccess, response2.getStatus());
	}

	/**
	 * Test fetch all users.
	 */
	@Test
	public void testFetchAllUsers()
	{

		PropertyRequest request = new PropertyRequest();

		InternalResultsResponse<UserContext> response = getSettingsBCL().fetchAllUsers(request);
		assertNotNull(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());
		assertEquals(ONE, response.getResultsList().size());
		assertEquals(Status.OperationSuccess, response.getStatus());

	}

	/**
	 * Test upsert settings.
	 */
	@Test
	public void testUpsertSystemSettings()
	{
		// Test Success
		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		List<Property> properties = new ArrayList<Property>();

		Property property =
				new Property(STRING_ONE, STRING_ONE, PropertyEnum.LANGUAGE.getValue(), STRING_ONE, STRING_ONE, ONE);
		properties.add(property);

		request.setProperties(properties);

		InternalResponse response = getSettingsBCL().upsertSystemSettings(request);

		// Test Failure
		properties = new ArrayList<Property>();
		property =
				new Property(STRING_ONE, STRING_ONE, PropertyEnum.LANGUAGE.getValue(), STRING_ONE_HUNDRED, STRING_ONE,
						ONE);
		properties.add(property);
		request.setProperties(properties);

		response = getSettingsBCL().upsertSystemSettings(request);

		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test upsert settings.
	 */
	@Test
	public void testUpsertUserSettings()
	{
		// Test Success
		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		List<Property> properties = new ArrayList<Property>();

		Property property =
				new Property(STRING_ONE, STRING_ONE, PropertyEnum.LANGUAGE.getValue(), STRING_ONE, STRING_ONE, ONE);
		properties.add(property);
		request.setProperties(properties);

		InternalResponse response = getSettingsBCL().upsertUserSettings(request);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Test Failure
		properties = new ArrayList<Property>();
		property =
				new Property(STRING_ONE, STRING_ONE, PropertyEnum.LANGUAGE.getValue(), STRING_ONE_HUNDRED, STRING_ONE,
						ONE);
		properties.add(property);
		request.setProperties(properties);

		response = getSettingsBCL().upsertUserSettings(request);
		assertNotNull(response.getMessageInfoList());
	}

	/**
	 * Test insert user.
	 */
	@Test
	public void testInsertUser()
	{
		UserContext userContext = new UserContext();
		userContext.setUserId(USER);
		PropertyRequest request = new PropertyRequest();
		request.setUserContext(userContext);
		InternalResponse internalResponse = getSettingsBCL().insertUser(request);
		assertNotNull(internalResponse);
		assertEquals(Status.OperationSuccess, internalResponse.getStatus());
	}

}
