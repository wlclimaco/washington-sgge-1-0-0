package com.sensus.mlc.settings.dac;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createLightingControlRequest;
import static com.sensus.mlc.base.TestBaseUtil.createSettingRequest;
import static com.sensus.mlc.base.TestBaseUtil.createSettings;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.user.model.User;

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
		request.getUserContext().setUserId(null);
		InternalResultsResponse<Setting> response = getSettingsDAC().upsertSettings(request);
		assertResultResponse(response);
	}

	/**
	 * Test update system settings.
	 */
	@Test
	public void testUpdateSystemSettings()
	{
		// TODO uncomment after bufixes for 1.1.0/1.2.0 allowed
		//		SettingsRequest request = createSettingRequest();
		//		request.setSettings(createSettings());
		//		InternalResultsResponse<Setting> response = getSettingsDAC().updateSystemSettings(request);
		//		assertResultResponse(response);
	}

	/**
	 * Test fetch user settings.
	 */
	@Test
	public void testFetchUserSettings()
	{
		insertSettings(getUserDefault().getId());

		LightingControlRequest request = createLightingControlRequest();
		request.getUserContext().setId(getUserDefault().getId());

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
		column.setColumnEnum(SmartPointColumnEnum.CITY_NAME);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.FIRMWARE_VERSION);
		columns.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.LAMP_TYPE);
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
		filter.setFilterEnum(SmartPointFilterEnum.CONFIGURATION);
		filter.setDisplayOrder(0);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.GROUPS);
		filter.setDisplayOrder(0);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.ADDRESS);
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

	/**
	 * Test delete old data.
	 */
	@Test
	public void testDeleteOldData()
	{
		Tenant tenant = TestBaseUtil.createTenant();
		getSettingsDAC().deleteOldData(tenant);
	}
}
