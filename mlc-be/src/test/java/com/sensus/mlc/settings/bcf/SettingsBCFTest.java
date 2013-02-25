package com.sensus.mlc.settings.bcf;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.bcl.ISettingsBCL;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Class SettingsBCFImplTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/settings/settingsbcfimpltest.xml"})
public class SettingsBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant FETCH_USER_SETTINGS. */
	private static final String FETCH_USER_SETTINGS = "fetchUserSettings";

	/** The Constant NEW_PASSWORD. */
	private static final String NEW_PASSWORD = "newPassword";

	/** The Constant FETCH_SYSTEM_SETTINGS. */
	private static final String FETCH_SYSTEM_SETTINGS = "fetchSystemSettings";

	/** The Constant FETCH_TENANT_BY_SERVER_NAME. */
	private static final String FETCH_TENANT_BY_SERVER_NAME = "fetchTenantByServerName";

	/** The Constant UPDATE_SYSTEM_SETTINGS. */
	private static final String UPDATE_SYSTEM_SETTINGS = "updateSystemSettings";

	/** The Constant UPSERT_SETTINGS. */
	private static final String UPSERT_SETTINGS = "upsertSettings";

	/** The Constant ACTUAL_PASSWORD. */
	private static final String ACTUAL_PASSWORD = "actualPassword";

	/** The Constant INSERT_USER_COLUMN_FILTER. */
	private static final String INSERT_USER_COLUMN_FILTER = "insertUserColumnFilter";

	/** The Constant POLE. */
	private static final String POLE = "POLE";

	/** The Constant FLEXNET. */
	private static final String FLEXNET = "FLEXNET";

	/** The Constant CITY. */
	private static final String CITY = "CITY";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.settingsbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_SETTINGSVALIDATOR_SETTINGS_REQUIRED. */
	private static final String SENSUS_MLC_SETTINGSVALIDATOR_SETTINGS_REQUIRED =
			"sensus.mlc.settingsvalidator.settings.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_VALIDATOR_FILTER_LIST_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_FILTER_LIST_REQUIRED =
			"sensus.mlc.validator.filter.list.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_COLUMNS_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_COLUMNS_REQUIRED =
			"sensus.mlc.validator.column.list.required";

	/** The Constant SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED =
			"sensus.mlc.validator.property.required";

	/** The Constant SENSUS_MLC_SETTINGSVALIDATOR_PROPERTYVALUE_REQUIRED. */
	private static final String SENSUS_MLC_SETTINGSVALIDATOR_PROPERTYVALUE_REQUIRED =
			"sensus.mlc.settingsvalidator.propertyvalue.required";

	/** The Constant SENSUS_MLC_TENANTVALIDATOR_REQUEST_REQUIRED. */
	private static final String SENSUS_MLC_TENANTVALIDATOR_REQUEST_REQUIRED =
			"sensus.mlc.tenantvalidator.request.required";

	/** The settings bcf. */
	private ISettingsBCF settingsBCF;

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
	@Resource(name = "settingsBCFTarget")
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Sets the settings area.
	 */
	public void setSettingsArea()
	{
		setArea(getSettingsBCF(), LCAreaEnum.SETTINGS);
	}

	/**
	 * Removes the settings area.
	 */
	@After
	public void removeSettingsArea()
	{
		setArea(getSettingsBCF(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Reset mocks to settings area.
	 */
	public void resetMocksToSettingsArea()
	{
		resetMocksData(getSettingsBCF());
		setSettingsArea();
	}

	/**
	 * Test upsert settings.
	 */
	@Test
	public void testUpsertSettings()
	{
		// Validation
		SettingsRequest request = new SettingsRequest();
		SettingsResponse response = getSettingsBCF().upsertSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		request = TestBaseUtil.createSettingRequest();
		response = getSettingsBCF().upsertSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SETTINGSVALIDATOR_SETTINGS_REQUIRED);

		// Success
		request = TestBaseUtil.createSettingRequest();
		request.setActualPassword(ACTUAL_PASSWORD);
		request.setNewPassword(NEW_PASSWORD);
		response = getSettingsBCF().upsertSettings(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response);
		assertEquals("Upsert Settings OK ", 0, response.getMessageList().size());

		request = TestBaseUtil.createSettingRequest();
		request.setSettings(TestBaseUtil.createSettings());
		response = getSettingsBCF().upsertSettings(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response);
		assertEquals("Upsert Settings OK", 0, response.getMessageList().size());

		resetMocksToSettingsArea();

		// Exception
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class, UPSERT_SETTINGS);
		response = getSettingsBCF().upsertSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);

		resetMocksToSettingsArea();

		// Error
		setSituation(getSettingsBCF(), SituationsEnum.ERROR, ISettingsBCL.class, UPSERT_SETTINGS);
		response = getSettingsBCF().upsertSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch user settings.
	 */
	@Test
	public void testFetchUserSettings()
	{
		// Validation
		LightingControlRequest request = new LightingControlRequest();
		SettingsResponse response = getSettingsBCF().fetchUserSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		request = TestBaseUtil.createLightingControlRequest();
		response = getSettingsBCF().fetchUserSettings(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSettingsArea();

		// Error situation
		setSituation(getSettingsBCF(), SituationsEnum.ERROR, ISettingsBCL.class, FETCH_USER_SETTINGS);
		request = TestBaseUtil.createLightingControlRequest();
		response = getSettingsBCF().fetchUserSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Exception situation
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class, FETCH_USER_SETTINGS);
		request = TestBaseUtil.createLightingControlRequest();
		response = getSettingsBCF().fetchUserSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Fetch system settings.
	 */
	@Test
	public void fetchSystemSettings()
	{
		// Validation
		LightingControlRequest request = new LightingControlRequest();
		SettingsResponse response = getSettingsBCF().fetchSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		request = TestBaseUtil.createLightingControlRequest();
		response = getSettingsBCF().fetchSystemSettings(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSettingsArea();

		// Error situation
		setSituation(getSettingsBCF(), SituationsEnum.ERROR, ISettingsBCL.class, FETCH_SYSTEM_SETTINGS);
		request = TestBaseUtil.createLightingControlRequest();
		response = getSettingsBCF().fetchSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Exception situation
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class, FETCH_SYSTEM_SETTINGS);
		request = TestBaseUtil.createLightingControlRequest();
		response = getSettingsBCF().fetchSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch tenant by server name.
	 */
	@Test
	public void testFetchTenantByServerName()
	{
		// Validation
		TenantRequest request = null;
		TenantResponse response = getSettingsBCF().fetchTenantByServerName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_TENANTVALIDATOR_REQUEST_REQUIRED);

		request = new TenantRequest();
		response = getSettingsBCF().fetchTenantByServerName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Success situation
		request = TestBaseUtil.createTenantRequest();
		Tenant tenant = TestBaseUtil.createTenant();
		request.setTenant(tenant);
		request.setServerName(tenant.getName());
		response = getSettingsBCF().fetchTenantByServerName(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSettingsArea();

		// Error situation
		setSituation(getSettingsBCF(), SituationsEnum.ERROR, ISettingsBCL.class, FETCH_TENANT_BY_SERVER_NAME);
		response = getSettingsBCF().fetchTenantByServerName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Error situation
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class, FETCH_TENANT_BY_SERVER_NAME);
		response = getSettingsBCF().fetchTenantByServerName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test update system.
	 */
	@Test
	public void testUpdateSystem()
	{
		// Validation
		SettingsRequest request = new SettingsRequest();
		SettingsResponse response = getSettingsBCF().updateSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		request = TestBaseUtil.createSettingRequest();
		response = getSettingsBCF().updateSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SETTINGSVALIDATOR_SETTINGS_REQUIRED);

		request = TestBaseUtil.createSettingRequest();
		Setting setting = TestBaseUtil.createSetting(null, null);
		List<Setting> settings = new ArrayList<Setting>();
		settings.add(setting);
		request.setSettings(settings);
		response = getSettingsBCF().updateSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED,
				SENSUS_MLC_SETTINGSVALIDATOR_PROPERTYVALUE_REQUIRED);

		// Success situation
		request = TestBaseUtil.createSettingRequest();
		request.setActualPassword(ACTUAL_PASSWORD);
		request.setNewPassword(NEW_PASSWORD);
		response = getSettingsBCF().updateSystemSettings(request);
		assertTrue(response.isOperationSuccess());

		request = TestBaseUtil.createSettingRequest();
		settings = TestBaseUtil.createSettings();
		request.setSettings(settings);
		response = getSettingsBCF().updateSystemSettings(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSettingsArea();

		// Error situation
		setSituation(getSettingsBCF(), SituationsEnum.ERROR, ISettingsBCL.class, UPDATE_SYSTEM_SETTINGS);
		request = TestBaseUtil.createSettingRequest();
		settings = TestBaseUtil.createSettings();
		request.setSettings(settings);
		response = getSettingsBCF().updateSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Exception situation
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class, UPDATE_SYSTEM_SETTINGS);
		request = TestBaseUtil.createSettingRequest();
		settings = TestBaseUtil.createSettings();
		request.setSettings(settings);
		response = getSettingsBCF().updateSystemSettings(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test insert user column filter.
	 */
	@Test
	public void testInsertUserColumnFilter()
	{
		// Validation
		ColumnFilterRequest request = new ColumnFilterRequest();
		ColumnFilterResponse response = getSettingsBCF().insertUserColumnFilter(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		request = TestBaseUtil.createColumnFilterRequest();
		response = getSettingsBCF().insertUserColumnFilter(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_FILTER_LIST_REQUIRED,
				SENSUS_MLC_VALIDATOR_COLUMNS_REQUIRED);

		// Success situation
		request = TestBaseUtil.createColumnFilterRequest();
		List<Column> listColumn = new ArrayList<Column>();
		listColumn.add(setColumn(SmartPointColumnEnum.CITY_NAME, CITY, 0));
		listColumn.add(setColumn(SmartPointColumnEnum.RNI_ID, FLEXNET, 1));
		listColumn.add(setColumn(SmartPointColumnEnum.POLE_ID, POLE, 2));
		List<Filter> listFilter = new ArrayList<Filter>();
		Filter filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.GROUPS);
		listFilter.add(filter);
		request.setListColumn(listColumn);
		request.setFilters(listFilter);
		response = getSettingsBCF().insertUserColumnFilter(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSettingsArea();

		// Error situation
		setSituation(getSettingsBCF(), SituationsEnum.ERROR, ISettingsBCL.class, INSERT_USER_COLUMN_FILTER);
		request = TestBaseUtil.createColumnFilterRequest();
		listColumn = new ArrayList<Column>();
		listColumn.add(setColumn(SmartPointColumnEnum.CITY_NAME, CITY, 0));
		listColumn.add(setColumn(SmartPointColumnEnum.RNI_ID, FLEXNET, 1));
		listColumn.add(setColumn(SmartPointColumnEnum.POLE_ID, POLE, 2));
		listFilter = new ArrayList<Filter>();
		filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.GROUPS);
		listFilter.add(filter);
		request.setListColumn(listColumn);
		request.setFilters(listFilter);
		response = getSettingsBCF().insertUserColumnFilter(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Exception situation
		setSituation(getSettingsBCF(), SituationsEnum.EXCEPTION, ISettingsBCL.class, INSERT_USER_COLUMN_FILTER);
		request = TestBaseUtil.createColumnFilterRequest();
		listColumn = new ArrayList<Column>();
		listColumn.add(setColumn(SmartPointColumnEnum.CITY_NAME, CITY, 0));
		listColumn.add(setColumn(SmartPointColumnEnum.RNI_ID, FLEXNET, 1));
		listColumn.add(setColumn(SmartPointColumnEnum.POLE_ID, POLE, 2));
		listFilter = new ArrayList<Filter>();
		filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.GROUPS);
		listFilter.add(filter);
		request.setListColumn(listColumn);
		request.setFilters(listFilter);
		response = getSettingsBCF().insertUserColumnFilter(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test change password.
	 */
	@Test
	public void testChangePassword()
	{
		SettingsRequest request = TestBaseUtil.createSettingRequest();
		request.setActualPassword("koala");
		request.setNewPassword("panda");
		SettingsResponse response = getSettingsBCF().upsertSettings(request);
		assertNotNull(response);
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Sets the column.
	 *
	 * @param propertyEnum the property enum
	 * @param text the text
	 * @param displayOrder the display order
	 * @return the column
	 */
	private Column setColumn(SmartPointColumnEnum propertyEnum, String text, int displayOrder)
	{
		Column column = new Column();
		column.setColumnEnum(propertyEnum);
		column.setFieldName(text);
		column.setOrdered(true);
		column.setDisplayOrder(displayOrder);

		return column;
	}
}
