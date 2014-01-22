package com.sensus.lc.settings.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createLightingControlRequest;
import static com.sensus.lc.base.TestBaseUtil.createSettingRequest;
import static com.sensus.lc.base.TestBaseUtil.createSettings;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.user.model.User;

/**
 * The Class SettingsDACTest.
 */
public class SettingsDACTest extends AbstractTestBaseDAC
{
	private User userDefault;

	public User getUserDefault()
	{
		if (userDefault == null)
		{
			userDefault = insertUser();
		}

		return userDefault;
	}

	public void setUserDefault(User userDefault)
	{
		this.userDefault = userDefault;
	}

	/**
	 * Test update system settings.
	 */
	@Test
	public void testUpsertSettings()
	{
		insertSettings(getUserDefault().getId());

		SettingsRequest request = createSettingRequest();
		request.setSettings(createSettings());
		InternalResultsResponse<Setting> response = getSettingsDAC().upsertSettings(request);
		assertResultResponse(response);
	}

	/**
	 * Test upsert system settings.
	 */
	@Test
	public void testUpsertSystemSettings()
	{
		SettingsRequest request = createSettingRequest();
		request.setSettings(createSettings());
		InternalResultsResponse<Setting> response = getSettingsDAC().upsertSystemSettings(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch user settings.
	 */
	@Test
	public void testFetchUserSettings()
	{
		insertSettings(getUserDefault().getId());

		LightingControlRequest request = createLightingControlRequest();
		request.setUserId(getUserDefault().getId());
		InternalResultsResponse<Setting> response = getSettingsDAC().fetchUserSettings(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch system settings.
	 */
	@Test
	public void testFetchSystemSettings()
	{
		insertSettings(null);

		LightingControlRequest request = createLightingControlRequest();
		request.getUserContext().setUserId(null);

		InternalResultsResponse<Setting> response = getSettingsDAC().fetchSystemSettings(request);
		assertResultResponse(response);
	}

	/**
	 * Test insert user columns.
	 */
	@Test
	public void testInsertUserColumns()
	{
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();

		List<Column> columns = new ArrayList<Column>();

		Column column = new Column();
		column.setColumnEnum(LightColumnEnum.CITY);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.FIRMWARE_VERSION);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(LightColumnEnum.LAMP_TYPE_WATTAGE_DIMMABLE);
		columns.add(column);

		request.setListColumn(columns);

		InternalResponse response = getSettingsDAC().insertUserColumns(request);
		assertResponse(response);
	}

	/**
	 * Test insert user filters.
	 */
	@Test
	public void testInsertUserFilters()
	{
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();

		List<Filter> filters = new ArrayList<Filter>();

		Filter filter = new Filter();
		filter.setFilterEnum(FilterEnum.CONFIGURATION);
		filter.setDisplayOrder(0);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.GROUPS);
		filter.setDisplayOrder(0);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(FilterEnum.ADDRESS);
		filter.setDisplayOrder(0);
		filters.add(filter);

		request.setFilters(filters);

		InternalResponse response = getSettingsDAC().insertUserFilters(request);
		assertResponse(response);
	}

	/**
	 * Test delete user columns.
	 */
	@Test
	public void testDeleteUserColumns()
	{
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();
		request.setUserContext(createUserContext());
		InternalResponse response = getSettingsDAC().deleteUserColumns(request);
		assertResponse(response);
	}

	/**
	 * Test delete user filters.
	 */
	@Test
	public void testDeleteUserFilters()
	{
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();
		request.setUserContext(createUserContext());
		InternalResponse response = getSettingsDAC().deleteUserFilters(request);
		assertResponse(response);
	}

	/**
	 * Test calculate map center.
	 */
	@Test
	public void testCalculateMapCenter()
	{
		Tenant tenant = TestBaseUtil.createTenant();
		getSettingsDAC().calculateMapCenter(tenant);
	}

}
