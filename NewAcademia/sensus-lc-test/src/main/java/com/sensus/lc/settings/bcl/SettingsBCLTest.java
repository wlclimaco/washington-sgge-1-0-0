package com.sensus.lc.settings.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.settings.dac.ISettingsDAC;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.tenant.dac.ITenantDAC;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.user.bcf.IUserBCF;

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

		// Error situation - error in the method "changePassword"
		setSituation(getSettingsBCL(), SituationsEnum.ERROR, IUserBCF.class,
				"changePassword");

		request.setNewPassword("newPassword");
		request.setActualPassword("actualPassword");
		response = getSettingsBCL().upsertSettings(request);
		assertTrue(response.isInError());

	}

	/**
	 * Test fetch user settings.
	 */
	@Test
	public void testFetchUserSettings()
	{
		// Success without ResultsList
		LightingControlRequest request = TestBaseUtil.createLightingControlRequest();
		request.setUserId(ARBITRARY_USER_ID_99);
		InternalResultsResponse<Setting> response = getSettingsBCL().fetchUserSettings(request);
		assertResultResponse(response);

		// Success with ResultsList
		request = TestBaseUtil.createLightingControlRequest();
		request.setUserId(new Integer(1));
		response = getSettingsBCL().fetchUserSettings(request);
		assertResultResponse(response);

		resetMocksToSettingsArea();

		// Error
		request.setUserId(ARBITRARY_USER_ID_99);
		setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, FETCH_SYSTEM_SETTINGS);
		response = getSettingsBCL().fetchUserSettings(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch system settings.
	 */
	@Test
	public void testFetchSystemSettings()
	{
		// Success with ResultsList
		LightingControlRequest request = TestBaseUtil.createLightingControlRequest();
		request.getUserContext().setId(ARBITRARY_USER_ID_99);
		InternalResultsResponse<Setting> response = getSettingsBCL().fetchSystemSettings(request);
		assertResultResponse(response);

		// Success without ResultsList (return the Default Settings)
		setTestControl(getSettingsBCL(), ISettingsDAC.class, "NO_RESULTS");

		request = TestBaseUtil.createLightingControlRequest();
		request.getUserContext().setId(1);
		response = getSettingsBCL().fetchSystemSettings(request);
		assertResultResponse(response);

		resetMocksToSettingsArea();

		// Error
		setTestControl(getSettingsBCL(), ISettingsDAC.class, "");
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
	 * Test upsert system settings.
	 */
	@Test
	public void testUpsertSystemSettings()
	{
		// Success
		SettingsRequest request = TestBaseUtil.createSettingRequest();
		request.setTenant(TestBaseUtil.createTenant());
		request.setSettings(TestBaseUtil.createSettings());
		InternalResponse response = getSettingsBCL().upsertSystemSettings(request);
		assertResponse(response);

		resetMocksToSettingsArea();

		// Error
		this.setSituation(getSettingsBCL(), SituationsEnum.ERROR, ISettingsDAC.class, "upsertSystemSettings");
		response = getSettingsBCL().upsertSystemSettings(request);
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
		listColumn.add(setColumn(LightColumnEnum.CITY, CITY, 0));
		listColumn.add(setColumn(LightColumnEnum.FLEXNET_ID, FLEXNET, 1));
		listColumn.add(setColumn(LightColumnEnum.POLE_ID, POLE, 2));
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
		listColumn.add(setColumn(LightColumnEnum.CITY, CITY, 0));
		listColumn.add(setColumn(LightColumnEnum.FLEXNET_ID, FLEXNET, 1));
		listColumn.add(setColumn(LightColumnEnum.POLE_ID, POLE, 2));
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
	private Column setColumn(LightColumnEnum propertyEnum, String text, int displayOrder)
	{
		Column column = new Column();
		column.setColumnEnum(propertyEnum);
		column.setFieldName(text);
		column.setOrdered(true);
		column.setDisplayOrder(displayOrder);

		return column;
	}
}
