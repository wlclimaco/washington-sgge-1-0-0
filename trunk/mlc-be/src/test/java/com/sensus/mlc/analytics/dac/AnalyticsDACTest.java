package com.sensus.mlc.analytics.dac;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createAnalyticsRequest;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryAnalyticsRequest;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.analytics.csv.AnalyticsGroupCSVDataSource;
import com.sensus.mlc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.mlc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.mlc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.mlc.analytics.model.AnalyticsGroupWarning;
import com.sensus.mlc.analytics.model.DashboardViewTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class AnalyticsDACTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/analytics/analyticsdacimpltest.xml"})
public class AnalyticsDACTest extends AbstractTestBaseDAC
{

	/** The Constant SIZE_N2. */
	private static final int SIZE_N2 = -2;

	/** The Constant SIZE_4. */
	private static final int SIZE_4 = 4;

	/** The Constant SIZE_3. */
	private static final int SIZE_3 = 3;

	/** The Constant ARBITRARY_ASSERT_3. */
	private static final int ARBITRARY_ASSERT_3 = 3;

	/** The Constant ARBITRARY_PARAMETER_300. */
	private static final Integer ARBITRARY_PARAMETER_300 = 300;

	/** The Constant ARBITRARY_PARAMETER_200. */
	private static final Integer ARBITRARY_PARAMETER_200 = 200;

	/** The Constant ARBITRARY_PAGE_SIZE_30. */
	private static final Integer ARBITRARY_PAGE_SIZE_30 = 30;

	/** The Constant OPERATOR. */
	private static final String OPERATOR = "+";

	/** The Constant THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT. */
	private static final String THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT =
			"The user should have access to only two groups previously added to it";

	/** The CARBO n_ credit s_ factor. */
	private static final Double CARBON_CREDITS_FACTOR = 0.000717830556540234;

	/** The BARREL s_ o f_ oi l_ factor. */
	private static final Double BARRELS_OF_OIL_FACTOR = 0.0016705069124424;

	/** The METRI c_ o f_ c o_ factor. */
	private static final Double METRIC_OF_CO_FACTOR = 0.000717830556540234;

	/** The light default. */
	private Light lightDefault;

	/** The analytics dac. */
	private IAnalyticsDAC analyticsDAC;

	/**
	 * Reset data.
	 */
	@After
	public void resetData()
	{
		resetGroupsToUser(createUserContext());
	}

	/**
	 * Gets the light default.
	 * 
	 * @return the light default
	 */
	public Light getLightDefault()
	{
		return lightDefault;
	}

	/**
	 * Sets the light default.
	 * 
	 * @param lightDefault the new light default
	 */
	public void setLightDefault(Light lightDefault)
	{
		this.lightDefault = lightDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		if (getLightDefault() == null)
		{
			setLightDefault(insertLight());
		}
	}

	/**
	 * Gets the analytics dac.
	 * 
	 * @return the analytics dac
	 */
	public IAnalyticsDAC getAnalyticsDAC()
	{
		return analyticsDAC;
	}

	/**
	 * Sets the analytics dac.
	 * 
	 * @param analyticsDAC the new analytics dac
	 */
	@Resource
	public void setAnalyticsDAC(IAnalyticsDAC analyticsDAC)
	{
		this.analyticsDAC = analyticsDAC;
	}

	/**
	 * Test fetch analytics alerts by type.
	 */
	@Test
	public void testFetchAnalyticsAlertsByType()
	{
		AnalyticsRequest request = createAnalyticsRequest();
		InternalResultsResponse<Map<String, Integer>> response =
				getAnalyticsDAC().fetchAnalyticsAlertsByType(request);
		assertResultResponse(response);

		// Add allowed group list for user
		this.setGroupsToUser(request, 1);
		response = getAnalyticsDAC().fetchAnalyticsAlertsByType(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch analytics alarms by status id.
	 */
	@Test
	public void testFetchAnalyticsAlarmsByStatusId()
	{
		LightRequest lightRequest = TestBaseUtil.createLightRequest();

		insertStatusMessage(lightRequest, LightStatusEnum.ALARM, StatusMessageCategoryEnum.ALARM,
				StatusExceptionTypeEnum.LAMP_FAILURE);

		upsertLightProperty(lightRequest);

		AnalyticsRequest analyticsRequest = createAnalyticsRequest();
		analyticsRequest.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.LAMP_FAILURE);

		InternalResultsResponse<Light> response =
				getAnalyticsDAC().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
		assertResultResponse(response);

		// Add allowed group list for user
		resetGroupsToUser(analyticsRequest.getUserContext());
		analyticsRequest.setAllowedGroupIdList(getGroupIdList(2));
		response = getAnalyticsDAC().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
		assertResultResponse(response);
		assertEquals(THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT, 2, response
				.getResultsList().size());
	}

	/**
	 * Test fetch all analytics installed by group.
	 */
	@Test
	public void testFetchAllAnalyticsInstalledByGroup()
	{
		InquiryAnalyticsRequest request = createInquiryAnalyticsRequest();

		List<Integer> groupIdList = getGroupIdList(1);
		request.setGroup(new AnalyticsGroup(groupIdList.get(0)));
		request.setAllowedGroupIdList(groupIdList);
		request.setEndDate(createEndDate().getTime());
		request.setInitialDate(createInitialDate().getTime());
		request.setPageSize(ARBITRARY_PAGE_SIZE_30);
		request.setStartPage(0);
		request.setStartRow(0);

		InternalResultsResponse<AnalyticsGroupTypeLight> response =
				getAnalyticsDAC().fetchAllAnalyticsInstalledByGroup(request);
		assertResultResponse(response);

		// Add allowed group list for user
		resetGroupsToUser(request.getUserContext());
		request.setAllowedGroupIdList(getGroupIdList(2));

		response = getAnalyticsDAC().fetchAllAnalyticsInstalledByGroup(request);
		assertResultResponse(response);

		assertEquals(THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT, 2, response
				.getResultsList().size());

		// Test success with group id 0
		request.setGroup(new AnalyticsGroup(0));
		response =
				getAnalyticsDAC().fetchAllAnalyticsInstalledByGroup(request);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all analytics consumption by group.
	 */
	@Test
	public void testFetchAllAnalyticsConsumptionByGroup()
	{
		InquiryAnalyticsRequest request = createInquiryAnalyticsRequest();

		request.setEndDate(createEndDate().getTime());
		request.setGroup(null);
		request.setInitialDate(createInitialDate().getTime());
		request.setPageSize(ARBITRARY_PAGE_SIZE_30);
		request.setStartRow(0);

		InternalResultsResponse<AnalyticsGroupTypeLight> response =
				getAnalyticsDAC().fetchAllAnalyticsConsumptionByGroup(request);
		assertResultResponse(response);

		List<LightParameter> parameters = new ArrayList<LightParameter>();
		parameters.add(new LightParameter(PropertyEnum.CONSUMPTION, String.valueOf(ARBITRARY_PARAMETER_200)));

		InternalResponse internalResponse =
				getSmartpointDAC().upsertLightProperty(request.getUserContext().getUserId(),
						getLightDefault().getRniId(), parameters);
		assertResponse(internalResponse);

		// Add allowed group list for user
		request.setAllowedGroupIdList(getGroupIdList(1));

		response =
				getAnalyticsDAC().fetchAllAnalyticsConsumptionByGroup(request);
		assertResultResponse(response);

		// Test success with group id 0
		parameters = new ArrayList<LightParameter>();
		parameters.add(new LightParameter(PropertyEnum.CONSUMPTION, String.valueOf(ARBITRARY_PARAMETER_300)));

		internalResponse =
				getSmartpointDAC().upsertLightProperty(request.getUserContext().getUserId(),
						getLightDefault().getRniId(), parameters);
		assertResponse(internalResponse);

		request.setGroup(new AnalyticsGroup(0));
		response =
				getAnalyticsDAC().fetchAllAnalyticsConsumptionByGroup(request);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all analytics alarm by group.
	 */
	@Test
	public void testFetchAllAnalyticsAlarmsByGroup()
	{
		this.insertStatusMessage(TestBaseUtil.createLightRequest(), LightStatusEnum.ALARM,
				StatusMessageCategoryEnum.ALARM, StatusExceptionTypeEnum.LAMP_FAILURE);

		InquiryAnalyticsRequest analyticsRequest = createInquiryAnalyticsRequest();

		InternalResponse internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		List<Integer> groupIdList = getGroupIdList(1);
		analyticsRequest.setGroup(new AnalyticsGroup(groupIdList.get(0)));
		analyticsRequest.setAllowedGroupIdList(groupIdList);
		analyticsRequest.setInitialDate(createInitialDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());
		analyticsRequest.setPageSize(ARBITRARY_PAGE_SIZE_30);
		analyticsRequest.setStartPage(0);
		analyticsRequest.setStartRow(0);

		InternalResultsResponse<AnalyticsGroupAlarm> response =
				getAnalyticsDAC().fetchAllAnalyticsAlarmsByGroup(analyticsRequest);
		assertResultResponse(response);

		resetGroupsToUser(analyticsRequest.getUserContext());

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		analyticsRequest.setAllowedGroupIdList(getGroupIdList(2));

		response = getAnalyticsDAC().fetchAllAnalyticsAlarmsByGroup(analyticsRequest);
		assertResultResponse(response);

		assertEquals(THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT, 2, response
				.getResultsList().size());

		// Test success with group id 0
		analyticsRequest.setGroup(new AnalyticsGroup(0));
		response =
				getAnalyticsDAC().fetchAllAnalyticsAlarmsByGroup(analyticsRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all analytics warning by group.
	 */
	@Test
	public void testFetchAllAnalyticsWarningsByGroup()
	{
		this.insertStatusMessage(TestBaseUtil.createLightRequest(), LightStatusEnum.WARNING,
				StatusMessageCategoryEnum.ALARM, StatusExceptionTypeEnum.POWER_SURGE_DETECTED);

		InquiryAnalyticsRequest analyticsRequest = createInquiryAnalyticsRequest();

		InternalResponse internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		List<Integer> groupIdList = getGroupIdList(1);
		analyticsRequest.setGroup(new AnalyticsGroup(groupIdList.get(0)));
		analyticsRequest.setAllowedGroupIdList(groupIdList);
		analyticsRequest.setInitialDate(createInitialDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());
		analyticsRequest.setPageSize(ARBITRARY_PAGE_SIZE_30);
		analyticsRequest.setStartPage(0);
		analyticsRequest.setStartRow(0);

		InternalResultsResponse<AnalyticsGroupWarning> response =
				getAnalyticsDAC().fetchAllAnalyticsWarningsByGroup(analyticsRequest);
		assertResultResponse(response);

		resetGroupsToUser(analyticsRequest.getUserContext());

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		analyticsRequest.setAllowedGroupIdList(getGroupIdList(2));

		response = getAnalyticsDAC().fetchAllAnalyticsWarningsByGroup(analyticsRequest);
		assertResultResponse(response);
		assertEquals(THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT, 2, response
				.getResultsList().size());

		// Test success with group id 0
		analyticsRequest.setGroup(new AnalyticsGroup(0));
		response =
				getAnalyticsDAC().fetchAllAnalyticsWarningsByGroup(analyticsRequest);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all analytics energy savings by group.
	 */
	@Test
	public void testfetchAllAnalyticsEcoModeGroup()
	{
		// without group
		InquiryAnalyticsRequest request = createInquiryAnalyticsRequest();
		request.setPageSize(ARBITRARY_PAGE_SIZE_30);
		request.setStartRow(0);
		request.setInitialDate(createInitialDate().getTime());
		request.setEndDate(createEndDate().getTime());

		InternalResultsResponse<AnalyticsGroupEcoMode> response =
				getAnalyticsDAC().fetchAllAnalyticsEcoModeGroup(request);
		assertResultResponse(response);

		// filter from group
		request.setAllowedGroupIdList(getGroupIdList(2));

		response = getAnalyticsDAC().fetchAllAnalyticsEcoModeGroup(request);
		assertResultResponse(response);
		assertEquals("The user should have access to only one groups previously added to it", 2, response
				.getResultsList().size());

	}

	/**
	 * Test fetch all analytics carbon credits by group.
	 */
	@Test
	public void testFetchAllAnalyticsCarbonCreditsByGroup()
	{
		// without group
		InquiryAnalyticsRequest request = createInquiryAnalyticsRequest();

		request.setInitialDate(createInitialDate().getTime());
		request.setEndDate(createEndDate().getTime());

		InternalResultsResponse<AnalyticsGroupCarbonCredits> response =
				getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByGroup(request, CARBON_CREDITS_FACTOR,
						BARRELS_OF_OIL_FACTOR, METRIC_OF_CO_FACTOR);
		assertResultResponse(response);

		// Add allowed group list for user
		this.setGroupsToUser(request, 2);

		response = getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByGroup(request, CARBON_CREDITS_FACTOR,
				BARRELS_OF_OIL_FACTOR, METRIC_OF_CO_FACTOR);
		assertResultResponse(response);

		assertEquals(THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT, 2, response
				.getResultsList().size());

		this.setGroupsToUser(request, 1);

		response = getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByGroup(request, CARBON_CREDITS_FACTOR,
				BARRELS_OF_OIL_FACTOR, METRIC_OF_CO_FACTOR);
		assertResultResponse(response);

		assertEquals("The user should have access to only three groups previously added to it.", ARBITRARY_ASSERT_3,
				response
						.getResultsList().size());

		// Test success with group id 0
		request.setGroup(new AnalyticsGroup(0));
		response =
				getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByGroup(request, CARBON_CREDITS_FACTOR,
						BARRELS_OF_OIL_FACTOR, METRIC_OF_CO_FACTOR);
		assertResultResponse(response);

	}

	/**
	 * Test generate file csv.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGenerateFileCSV() throws IOException
	{
		List<AnalyticsGroup> groups = new ArrayList<AnalyticsGroup>();
		for (int i = 0; i < SIZE_3; i++)
		{
			AnalyticsGroup analyticsGroup = new AnalyticsGroup();
			analyticsGroup.setName("Uberaba " + i);
			List<AnalyticsGroupColumns> listAnalyticsGroupColumns = new ArrayList<AnalyticsGroupColumns>();
			for (int j = 0; j < SIZE_4; j++)
			{
				AnalyticsGroupColumns analyticsGroupColumn = new AnalyticsGroupColumns();
				switch (j)
				{
					case 0:
						analyticsGroupColumn.setDescription("Total");
						break;
					case 1:
						analyticsGroupColumn.setDescription("LED");
						break;
					case 2:
						analyticsGroupColumn.setDescription("Induction");
						break;
					case SIZE_3:
						analyticsGroupColumn.setDescription("Other");
						break;
					default:
						break;
				}
				analyticsGroupColumn.setValue(TestBaseUtil.RANDOM.nextDouble());
				listAnalyticsGroupColumns.add(analyticsGroupColumn);
			}
			analyticsGroup.setColumns(listAnalyticsGroupColumns);
			groups.add(analyticsGroup);
		}

		InquiryAnalyticsRequest inquiryAnalyticsRequest = createInquiryAnalyticsRequest();
		inquiryAnalyticsRequest.setFileName(TestBaseUtil.FILE_NAME);
		inquiryAnalyticsRequest.setGroups(groups);

		InternalResponse response = getAnalyticsDAC().generateFileCSV(inquiryAnalyticsRequest);
		assertResponse(response);

		// check dimensions...
		AnalyticsGroupCSVDataSource ds = new AnalyticsGroupCSVDataSource(inquiryAnalyticsRequest);
		TestBaseUtil.checkCSVFileDimensions(groups.size(), ds);
	}

	/**
	 * Test fetch all analytics group.
	 */
	@Test
	public void testFetchAllAnalyticsGroup()
	{
		AnalyticsRequest request = createAnalyticsRequest();

		InternalResultsResponse<AnalyticsGroup> response = getAnalyticsDAC().fetchAllAnalyticsGroup(request);
		assertResultResponse(response);

		// Add allowed group list for user
		this.setGroupsToUser(request, 2);

		response = getAnalyticsDAC().fetchAllAnalyticsGroup(request);
		assertResultResponse(response);

		assertEquals(THE_USER_SHOULD_HAVE_ACCESS_TO_ONLY_TWO_GROUPS_PREVIOUSLY_ADDED_TO_IT, 2, response
				.getResultsList().size());

		this.setGroupsToUser(request, 1);

		response = getAnalyticsDAC().fetchAllAnalyticsGroup(request);
		assertResultResponse(response);

		assertEquals("The user should have access to only three groups previously added to it", ARBITRARY_ASSERT_3,
				response
						.getResultsList().size());

	}

	/**
	 * Test fetch all analytics alarms by date.
	 */
	@Test
	public void testFetchAllAnalyticsAlarmsByDate()
	{

		LightRequest lightRequest = TestBaseUtil.createLightRequest();

		this.insertStatusMessage(lightRequest, LightStatusEnum.ALARM, StatusMessageCategoryEnum.ALARM,
				StatusExceptionTypeEnum.LAMP_FAILURE);

		AnalyticsRequest analyticsRequest = createAnalyticsRequest();

		InternalResponse internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		analyticsRequest.setInitialDate(createInitialDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());
		analyticsRequest.setAllowedGroupIdList(getGroupIdList(1));

		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_MONTH);

		InternalResultsResponse<AnalyticsGroup> internalResultResponse =
				getAnalyticsDAC().fetchAllAnalyticsGroup(analyticsRequest);
		assertResultResponse(internalResultResponse);

		analyticsRequest.setGroup(new AnalyticsGroup(internalResultResponse.getFirstResult().getId()));

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchAllAnalyticsAlarmsByDate(analyticsRequest);
		assertResultResponse(response);

		resetGroupsToUser(analyticsRequest.getUserContext());
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_WEEK);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.LAMP_FAILURE.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		analyticsRequest.setAllowedGroupIdList(getGroupIdList(2));
		response = getAnalyticsDAC().fetchAllAnalyticsAlarmsByDate(analyticsRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all analytics warnings by date.
	 */
	@Test
	public void testFetchAllAnalyticsWarningsByDate()
	{
		this.insertStatusMessage(TestBaseUtil.createLightRequest(), LightStatusEnum.WARNING,
				StatusMessageCategoryEnum.ALARM, StatusExceptionTypeEnum.POWER_SURGE_DETECTED);

		AnalyticsRequest analyticsRequest = createAnalyticsRequest();

		InternalResponse internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		analyticsRequest.setGroup(null);
		analyticsRequest.setInitialDate(createInitialDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());
		analyticsRequest.setAllowedGroupIdList(getGroupIdList(1));
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_MONTH);

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchAllAnalyticsWarningsByDate(analyticsRequest);
		assertResultResponse(response);

		resetGroupsToUser(analyticsRequest.getUserContext());
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_WEEK);

		internalResponse =
				getSmartpointDAC().updateAnalyticsAlarmWarning(
						analyticsRequest.getUserContext().getTenant().getId(),
						getLightDefault().getId(), StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue(),
						analyticsRequest.getUserContext().getUserId(), OPERATOR);
		assertResponse(internalResponse);

		analyticsRequest.setAllowedGroupIdList(getGroupIdList(2));

		response = getAnalyticsDAC().fetchAllAnalyticsWarningsByDate(analyticsRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all analytics consumption by date.
	 */
	@Test
	public void testFetchAllAnalyticsConsumptionByDate()
	{
		AnalyticsRequest analyticsRequest = createAnalyticsRequest();
		analyticsRequest.setInitialDate(createInitialDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());
		analyticsRequest.setGroup(new AnalyticsGroup(2));
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.THREE_MONTHS);

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchAllAnalyticsConsumptionByDate(analyticsRequest);
		assertResultResponse(response);

		// Future date, should bring 0 lights installed
		// Necessary createEndDate twice.
		analyticsRequest.setInitialDate(createEndDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());

		response = getAnalyticsDAC().fetchAllAnalyticsConsumptionByDate(analyticsRequest);
		assertResultResponse(response);

	}

	/**
	 * Test fetch all analytics installed by date.
	 */
	@Test
	public void testFetchAllAnalyticsInstalledByDate()
	{
		AnalyticsRequest analyticsRequest = createAnalyticsRequest();
		analyticsRequest.setInitialDate(createInitialDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_WEEK);

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchAllAnalyticsInstalledByDate(analyticsRequest);
		assertResultResponse(response);

		analyticsRequest.setGroup(new AnalyticsGroup(insertGroup().getId()));
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_MONTH);

		analyticsRequest.setAllowedGroupIdList(getGroupIdList(1));

		response =
				getAnalyticsDAC().fetchAllAnalyticsInstalledByDate(analyticsRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all analytics eco mode by date.
	 */
	@Test
	public void testFetchAllAnalyticsEcoModeByDate()
	{
		AnalyticsRequest analyticsRequest = createAnalyticsRequest();

		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.DAY_OF_MONTH, SIZE_3);

		// two month without group
		date.add(Calendar.MONTH, -1);
		analyticsRequest.setInitialDate(date.getTime());

		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchAllAnalyticsEcoModeByDate(analyticsRequest);
		assertResultResponse(response);

		// three month without group
		date.add(Calendar.MONTH, SIZE_N2);
		analyticsRequest.setInitialDate(date.getTime());

		date.add(Calendar.MONTH, 2);
		analyticsRequest.setEndDate(date.getTime());

		response =
				getAnalyticsDAC().fetchAllAnalyticsEcoModeByDate(analyticsRequest);
		assertResultResponse(response);

		analyticsRequest = createAnalyticsRequest();

		// two month with filter from group
		date.add(Calendar.MONTH, -1);
		analyticsRequest.setInitialDate(date.getTime());

		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());

		analyticsRequest.setGroup(new AnalyticsGroup(insertGroup().getId()));
		response = getAnalyticsDAC().fetchAllAnalyticsEcoModeByDate(analyticsRequest);
		assertResultResponse(response);

		// three month with restriction from group
		date.add(Calendar.MONTH, SIZE_N2);
		analyticsRequest.setInitialDate(date.getTime());

		date.add(Calendar.MONTH, 2);
		analyticsRequest.setEndDate(date.getTime());

		analyticsRequest.setGroup(null);
		analyticsRequest.setAllowedGroupIdList(getGroupIdList(2));

		response = getAnalyticsDAC().fetchAllAnalyticsEcoModeByDate(analyticsRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch all analytics carbon credits by date.
	 */
	@Test
	public void testFetchAllAnalyticsCarbonCreditsByDate()
	{
		Double carbonCreditsFactor = new Double(2);
		Double barrelsOfOilFactor = new Double(SIZE_3);
		Double metricOfCOFactor = new Double(SIZE_4);

		AnalyticsRequest analyticsRequest = createAnalyticsRequest();
		analyticsRequest.setInitialDate(createInitialDate().getTime());
		analyticsRequest.setEndDate(createEndDate().getTime());
		analyticsRequest.setGroup(new AnalyticsGroup(insertGroup().getId()));
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_WEEK);

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByDate(analyticsRequest, carbonCreditsFactor,
						barrelsOfOilFactor, metricOfCOFactor);
		assertResultResponse(response);

		// Add allowed group list for user
		this.setGroupsToUser(analyticsRequest, 2);

		response = getAnalyticsDAC().fetchAllAnalyticsCarbonCreditsByDate(analyticsRequest, carbonCreditsFactor,
				barrelsOfOilFactor, metricOfCOFactor);
		assertResultResponse(response);
	}

	/**
	 * Test fetch dashboard header.
	 */
	@Test
	public void testFetchDashboarHeader()
	{
		AnalyticsRequest request = createAnalyticsRequest();

		request.setInitialDate(createInitialDate().getTime());
		request.setEndDate(createEndDate().getTime());

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchDashboarHeader(request, CARBON_CREDITS_FACTOR);
		assertResultResponse(response);

		// Test success with allowed group
		request.setAllowedGroupIdList(getGroupIdList(1));
		response =
				getAnalyticsDAC().fetchDashboarHeader(request, CARBON_CREDITS_FACTOR);
		assertResultResponse(response);
	}

	/**
	 * Test fetch dashboard resume.
	 */
	@Test
	public void testFetchDashboardResume()
	{
		AnalyticsRequest analyticsRequest = createAnalyticsRequest();
		analyticsRequest.setAllowedGroupIdList(getGroupIdList(2));

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsDAC().fetchDashboardResume(analyticsRequest, CARBON_CREDITS_FACTOR);
		assertResultResponse(response);

		// fetch for today
		analyticsRequest.setDashboardViewTypeEnum(DashboardViewTypeEnum.TODAY);

		response =
				getAnalyticsDAC().fetchDashboardResume(analyticsRequest, CARBON_CREDITS_FACTOR);
		assertResultResponse(response);

		// fetch for week
		analyticsRequest.setDashboardViewTypeEnum(DashboardViewTypeEnum.WEEK);

		response = getAnalyticsDAC().fetchDashboardResume(analyticsRequest, CARBON_CREDITS_FACTOR);
		assertResultResponse(response);

		// fetch for month
		analyticsRequest.setDashboardViewTypeEnum(DashboardViewTypeEnum.MONTH);

		response = getAnalyticsDAC().fetchDashboardResume(analyticsRequest, CARBON_CREDITS_FACTOR);
		assertResultResponse(response);
	}

	/**
	 * Creates the initial date.
	 * 
	 * @return the calendar
	 */
	private Calendar createInitialDate()
	{
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, 0);

		return date;
	}

	/**
	 * Creates the end date.
	 * 
	 * @return the calendar
	 */
	private Calendar createEndDate()
	{
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, 1);

		return date;
	}

	/**
	 * Upsert light property.
	 * 
	 * @param lightRequest the light request
	 */
	private void upsertLightProperty(LightRequest lightRequest)
	{

		List<LightParameter> properties = new ArrayList<LightParameter>();

		LightParameter parameter = new LightParameter();
		parameter.setPropertyEnum(PropertyEnum.POLE_ID);
		parameter.setValue("12");
		properties.add(parameter);

		parameter = new LightParameter();
		parameter.setPropertyEnum(PropertyEnum.LATITUDE);
		parameter.setValue("16");
		properties.add(parameter);

		parameter = new LightParameter();
		parameter.setPropertyEnum(PropertyEnum.LONGITUDE);
		parameter.setValue("17");
		properties.add(parameter);

		// insert Light Property
		getSmartpointDAC().upsertLightProperty(lightRequest.getUserContext().getUserId(),
				lightRequest.getFirstLight().getRniId(), properties);
	}

	/**
	 * Insert status message.
	 * 
	 * @param lightRequest the light request
	 * @param lightStatusEnum the light status enum
	 * @param statusMessageCategoryEnum the status message category enum
	 * @param statusExceptionTypeEnum the status exception type enum
	 */
	private void insertStatusMessage(LightRequest lightRequest, LightStatusEnum lightStatusEnum,
			StatusMessageCategoryEnum statusMessageCategoryEnum, StatusExceptionTypeEnum statusExceptionTypeEnum)
	{
		List<Light> lights = new ArrayList<Light>();
		lights.add(getLightDefault());
		lightRequest.setLights(lights);

		StatusMessage sm = new StatusMessage();
		sm.setLightStatusEnum(lightStatusEnum);
		sm.setDate(LCDateUtil.getNewDateUTC());
		sm.setStatusMessageCategoryEnum(statusMessageCategoryEnum);
		lightRequest.getStatusMessages().add(sm);

		// insert a new Status Message
		Integer statusMessageId =
				getSmartpointDAC().insertStatusMessage(lightRequest.getStatusMessages().get(0),
						lightRequest.getUserContext().getUserId(),
						lightRequest.getTenant().getId(), lightRequest.getFirstLight().getId(), null);

		// insert a new Status Message Status Subtype
		InternalResultsResponse<Integer> response2 =
				getSmartpointDAC().insertStatusMessageStatusSubtype(statusMessageId,
						statusExceptionTypeEnum.getValue(),
						lightRequest.getUserContext().getUserId(), false);
		assertResultResponse(response2);
	}

	/**
	 * Gets the group id list.
	 * 
	 * @param groupCount the group count
	 * @return the group id list
	 */
	private List<Integer> getGroupIdList(Integer groupCount)
	{
		List<Integer> groupIdList = new ArrayList<Integer>();
		Light light = getLightDefault();

		do
		{
			Group group = insertGroup();
			addLightToGroup(light, group);
			groupIdList.add(group.getId());
		} while (groupIdList.size() < groupCount);

		return groupIdList;
	}
}
