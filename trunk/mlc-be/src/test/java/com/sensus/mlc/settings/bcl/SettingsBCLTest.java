package com.sensus.mlc.settings.bcl;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.dac.ISettingsDAC;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.tenant.dac.ITenantDAC;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Class SettingsBCLImplTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/settings/settingsbclimpltest.xml"})
public class SettingsBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant FETCH_SYSTEM_SETTINGS. */
	private static final String FETCH_SYSTEM_SETTINGS = "fetchSystemSettings";

	/** The Constant POLE. */
	private static final String POLE = "POLE";

	/** The Constant FLEXNET. */
	private static final String FLEXNET = "FLEXNET";

	/** The Constant CITY. */
	private static final String CITY = "CITY";

	/** The Constant ARBITRARY_USER_ID_99. */
	private static final Integer ARBITRARY_USER_ID_99 = 99;
	/** The settings bcl. */
	private ISettingsBCL settingsBCL;

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
	 * @param settingsBCL the new settings bcl
	 */
	@Resource(name = "settingsBCLTarget")
	public void setSettingsBCL(ISettingsBCL settingsBCL)
	{
		this.settingsBCL = settingsBCL;
	}

	/**
	 * Sets the settings area.
	 */
	public void setSettingsArea()
	{
		setArea(getSettingsBCL(), LCAreaEnum.SETTINGS);
	}

	/**
	 * Removes the settings area.
	 */
	@After
	public void removeSettingsArea()
	{
		setArea(getSettingsBCL(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Reset mocks to settings area.
	 */
	@After
	public void resetMocksToSettingsArea()
	{
		resetMocksData(getSettingsBCL());
		setSettingsArea();
	}

	/**
	 * Test upsert settings.
	 */
	@Test
	public void testUpsertSettings()
	{
		// Success
		SettingsRequest request = TestBaseUtil.createSettingRequest();
		request.setSettings(TestBaseUtil.createSettings());
		InternalResponse response = getSettingsBCL().upsertSettings(request);
		assertResponse(response);

		// Success
		request.setNewPassword("newPassword");
		request.setActualPassword("actualPassword");
		response = getSettingsBCL().upsertSettings(request);
		assertResponse(response);

		resetMocksToSettingsArea();

		// Error
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, "upsertSettings");
		response = getSettingsBCL().upsertSettings(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch user settings.
	 */
	@Test
	public void testFetchUserSettings()
	{
		// Success without ResultsList
		LightingControlRequest request = TestBaseUtil.createLightingControlRequest();
		request.getUserContext().setId(ARBITRARY_USER_ID_99);
		InternalResultsResponse<Setting> response = getSettingsBCL().fetchUserSettings(request);
		assertResultResponse(response);

		// Success with ResultsList
		request = TestBaseUtil.createLightingControlRequest();
		request.getUserContext().setId(1);
		response = getSettingsBCL().fetchUserSettings(request);
		assertResultResponse(response);

		resetMocksToSettingsArea();

		// Error
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, FETCH_SYSTEM_SETTINGS);
		response = getSettingsBCL().fetchUserSettings(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch system settings.
	 */
	@Test
	public void testFetchSystemSettings()
	{
		// Success without ResultsList
		LightingControlRequest request = TestBaseUtil.createLightingControlRequest();
		request.getUserContext().setId(ARBITRARY_USER_ID_99);
		InternalResultsResponse<Setting> response = getSettingsBCL().fetchSystemSettings(request);
		assertResultResponse(response);

		// Success with ResultsList
		request = TestBaseUtil.createLightingControlRequest();
		request.getUserContext().setId(1);
		response = getSettingsBCL().fetchSystemSettings(request);
		assertResultResponse(response);

		resetMocksToSettingsArea();

		// Error
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, FETCH_SYSTEM_SETTINGS);
		response = getSettingsBCL().fetchSystemSettings(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch tenant by server name.
	 */
	@Test
	public void testFetchTenantByServerName()
	{
		// Success
		TenantRequest tenantRequest = TestBaseUtil.createTenantRequest();
		tenantRequest.setServerName("serverName");
		InternalResultsResponse<Tenant> response = getSettingsBCL().fetchTenantByServerName(tenantRequest);
		assertResultResponse(response);

		resetMocksToSettingsArea();

		// Error
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ITenantDAC.class, "fetchTenantByServerName");
		response = getSettingsBCL().fetchTenantByServerName(tenantRequest);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update system settings.
	 */
	@Test
	public void testUpdateSystemSettings()
	{
		// Success
		SettingsRequest request = TestBaseUtil.createSettingRequest();
		request.setTenant(TestBaseUtil.createTenant());
		request.setSettings(TestBaseUtil.createSettings());
		InternalResponse response = getSettingsBCL().updateSystemSettings(request);
		assertResponse(response);

		resetMocksToSettingsArea();

		// Error
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, "updateSystemSettings");
		response = getSettingsBCL().updateSystemSettings(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test insert user column filter.
	 */
	@Test
	public void testInsertUserColumnFilter()
	{
		// Without ListColumn and Filters
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();
		InternalResponse response = getSettingsBCL().insertUserColumnFilter(request);
		assertNull(response);

		resetMocksToSettingsArea();

		// Success with ListColumn
		request = TestBaseUtil.createColumnFilterRequest();

		List<Column> listColumn = new ArrayList<Column>();
		listColumn.add(setColumn(SmartPointColumnEnum.CITY_NAME, CITY, 0));
		listColumn.add(setColumn(SmartPointColumnEnum.RNI_ID, FLEXNET, 1));
		listColumn.add(setColumn(SmartPointColumnEnum.POLE_ID, POLE, 2));
		request.setListColumn(listColumn);
		response = getSettingsBCL().insertUserColumnFilter(request);
		assertResponse(response);

		resetMocksToSettingsArea();

		// Success with Filters
		request = TestBaseUtil.createColumnFilterRequest();

		List<Filter> listFilter = new ArrayList<Filter>();
		listFilter.add(new Filter());
		request.setFilters(listFilter);

		request.setListColumn(listColumn);
		response = getSettingsBCL().insertUserColumnFilter(request);
		assertResponse(response);

		resetMocksToSettingsArea();

		// Error with ListColumn
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, "deleteUserColumns");
		request = TestBaseUtil.createColumnFilterRequest();

		listColumn = new ArrayList<Column>();
		listColumn.add(setColumn(SmartPointColumnEnum.CITY_NAME, CITY, 0));
		listColumn.add(setColumn(SmartPointColumnEnum.RNI_ID, FLEXNET, 1));
		listColumn.add(setColumn(SmartPointColumnEnum.POLE_ID, POLE, 2));
		request.setListColumn(listColumn);

		response = getSettingsBCL().insertUserColumnFilter(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Error with ListColumn
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, "insertUserColumns");
		response = getSettingsBCL().insertUserColumnFilter(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Error with Filters
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, "deleteUserFilters");
		request = TestBaseUtil.createColumnFilterRequest();

		listFilter = new ArrayList<Filter>();
		listFilter.add(new Filter());
		request.setFilters(listFilter);

		response = getSettingsBCL().insertUserColumnFilter(request);
		this.assertMessages(response, ERROR_CODE);

		resetMocksToSettingsArea();

		// Error with Filters
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, "insertUserFilters");
		response = getSettingsBCL().insertUserColumnFilter(request);
		this.assertMessages(response, ERROR_CODE);
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
