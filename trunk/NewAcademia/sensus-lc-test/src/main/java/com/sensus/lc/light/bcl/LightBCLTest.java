package com.sensus.lc.light.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createLightRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.query.SearchDate;
import com.sensus.common.query.SearchDate.DateSearchType;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.MLCSortExpression;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.LightPropertyForSearchEnum;
import com.sensus.lc.light.model.LightTextSearch;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.GeoCodeCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.criteria.OperationalDataCriteria;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.LCActionCategoryEnum;
import com.sensus.lc.process.model.ProcessFilter;

/**
 * The Class LightBCLTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/light/lightbclimpletest.xml"})
public class LightBCLTest extends AbstractTestBaseBusiness
{
	/** Attributes. */
	private static final String LC_ACTION_DESCRIPTION = "lc_action_description";

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant PERCENT. */
	private static final Integer PERCENT = 20;

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant RESET_MIN_MAX_FAILED. */
	private static final String RESET_MIN_MAX_FAILED =
			"sensus.mlc.lightbclimpl.update.min.max.failed";

	/** The Constant LANGUAGE. */
	private static final String LANGUAGE = "en_US";

	/** BCL Attributes. */
	private ILightBCL lightBCL;

	/** The notification history bcl. */
	private ILightNotificationHistoryBCL notificationHistoryBCL;

	/**
	 * Gets the light bcl.
	 * 
	 * @return the light bcl
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * Sets the light bcl.
	 * 
	 * @param lightBCL the new light bcl
	 */
	@Resource(name = "lightBCLTarget")
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * Gets the notification history bcl.
	 * 
	 * @return the notification history bcl
	 */
	public ILightNotificationHistoryBCL getNotificationHistoryBCL()
	{
		return notificationHistoryBCL;
	}

	/**
	 * Sets the notification history bcl.
	 * 
	 * @param notificationHistoryBCL the new notification history bcl
	 */
	@Resource(name = "lightNotificationHistoryBCLTarget")
	public void setNotificationHistoryBCL(ILightNotificationHistoryBCL notificationHistoryBCL)
	{
		this.notificationHistoryBCL = notificationHistoryBCL;
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setLightArea();
	}

	/**
	 * Sets the smart point area.
	 */
	public void setLightArea()
	{
		setArea(getLightBCL(), LCAreaEnum.LIGHT);
	}

	/**
	 * Reset mocks to light area.
	 */
	@After
	public void resetMocksToLightArea()
	{
		resetMocksData(getLightBCL());
		setLightArea();
	}

	/**
	 * Test fetch by id.
	 */
	@Test
	public void testFetchById()
	{
		// Test success: light by id
		FetchByIdRequest request = new FetchByIdRequest(ONE);
		InternalResultsResponse<Light> response = getLightBCL().fetchById(request);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test fetch light intensity by light.
	 */
	@Test
	public void testFetchLightIntensityByLight()
	{
		LightRequest request = createLightRequest(TestBaseUtil.createUserContext());
		request.setActionCriteria(new ActionCriteria());
		request.getActionCriteria().setPercentage(PERCENT);
		IntensityEnum intensityEnum = getLightBCL().fetchLightIntensityByLight(request);
		assertNotNull(intensityEnum);

		// Intensity LEVEL = 0
		request = createLightRequest(TestBaseUtil.createUserContext());
		request.setActionCriteria(new ActionCriteria());
		request.getActionCriteria().setPercentage(0);
		intensityEnum = getLightBCL().fetchLightIntensityByLight(request);
		assertNotNull(intensityEnum);
		assertEquals(IntensityEnum.LEVEL_0, intensityEnum);

		// Fetch by Light ID
		request = createLightRequest(TestBaseUtil.createUserContext());
		request.setActionCriteria(new ActionCriteria());
		request.setLightCriteria(new LightCriteria());
		request.getLightCriteria().setLightIdList(Arrays.asList(ONE));
		request.getActionCriteria().setPercentage(TEN);
		intensityEnum = getLightBCL().fetchLightIntensityByLight(request);
		assertNotNull(intensityEnum);

		// Error situation
		setSituation(getLightBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"fetchById");

		request = createLightRequest(TestBaseUtil.createUserContext());
		request.setActionCriteria(new ActionCriteria());
		intensityEnum = getLightBCL().fetchLightIntensityByLight(request);
		assertNotNull(intensityEnum);
		assertEquals(IntensityEnum.LEVEL_0, intensityEnum);

	}

	/**
	 * Test fetch light notification history.
	 */
	@Test
	public void testFetchLightNotificationHistory()
	{
		// Success Situation
		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();
		GroupCriteria groupCriteria = new GroupCriteria();
		groupCriteria.getGroupIdList().add(ONE);
		notificationHistoryRequest.setGroupCriteria(groupCriteria);

		// create notification criteria
		NotificationHistoryCriteria notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setLightId(new Integer(TEN));
		notificationHistoryCriteria.setTenantId(ONE);

		// insert process filter
		ProcessFilter processFilter = new ProcessFilter();
		processFilter.setStartDate(new Date());
		processFilter.setEndDate(new Date());
		processFilter.setActionCategoryList(Arrays.asList(LCActionCategoryEnum.MANAGE_LIGHTS));
		LightTextSearch lightTextSearch = new LightTextSearch();
		lightTextSearch.setSearchText("GROUP");
		processFilter.setLightTextSearch(lightTextSearch);
		processFilter.setUserIds(Arrays.asList(ONE, TWO, FIVE, TEN));
		notificationHistoryCriteria.setProcessFilter(processFilter);

		// create MLCSortExpression
		SortExpression sortExpression = new SortExpression();
		sortExpression.setField("name");
		sortExpression.setDirection(Direction.Ascending);
		notificationHistoryRequest.addSortExpressions(sortExpression);

		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		InternalResultsResponse<LightHistory> response =
				getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);

		// coverage situation - without user id, search text and action category list.
		processFilter.setUserIds(null);
		processFilter.setActionCategoryList(null);
		processFilter.setLightTextSearch(null);
		notificationHistoryCriteria.setProcessFilter(processFilter);
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);

		// coverage situation - order by
		sortExpression = new SortExpression();
		sortExpression.setField("name");
		sortExpression.setDirection(Direction.Ascending);
		notificationHistoryRequest.addSortExpressions(sortExpression);
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);

		// coverage situation - order by
		sortExpression = new MLCSortExpression();
		sortExpression.setField(LC_ACTION_DESCRIPTION);
		notificationHistoryRequest.addSortExpressions(sortExpression);
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);

		// coverage situation - search text
		lightTextSearch = new LightTextSearch();
		lightTextSearch.setSearchText(String.valueOf(ONE));
		processFilter.setLightTextSearch(lightTextSearch);
		processFilter.getLightTextSearch().setLightProperty(LightPropertyForSearchEnum.EVENT_ID);
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);

		// coverage situation - search text
		lightTextSearch = new LightTextSearch();
		lightTextSearch.setSearchText(String.valueOf(TWO));
		processFilter.setLightTextSearch(lightTextSearch);
		processFilter.getLightTextSearch().setLightProperty(LightPropertyForSearchEnum.ACTION_ID);
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);

		// coverage situation - translate message
		lightTextSearch = new LightTextSearch();
		lightTextSearch.setSearchText(String.valueOf(TWO));
		processFilter.setLightTextSearch(lightTextSearch);
		processFilter.getLightTextSearch().setLightProperty(LightPropertyForSearchEnum.ACTION_ID);
		notificationHistoryRequest.setUserContext(TestBaseUtil.createUserContext());
		notificationHistoryRequest.getUserContext().setLocaleString(LANGUAGE);
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);

		// Validation situation
		notificationHistoryRequest = new NotificationHistoryRequest();
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);

		// Error situation
		setSituation(getNotificationHistoryBCL(), SituationsEnum.ERROR, INotificationHistoryDAC.class,
				"fetchLightNotificationHistory");
		notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		response = getNotificationHistoryBCL().fetchLightNotificationHistory(notificationHistoryRequest);

	}

	/**
	 * Test fetch light history header.
	 */
	@Test
	public void testFetchLightHistoryHeader()
	{
		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();
		GroupCriteria groupCriteria = new GroupCriteria();
		groupCriteria.getGroupIdList().add(ONE);
		notificationHistoryRequest.setGroupCriteria(groupCriteria);
		InternalResultsResponse<HashMap<String, Integer>> response =
				getNotificationHistoryBCL().fetchLightHistoryHeader(notificationHistoryRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test reset min max.
	 */
	@Test
	public void testResetMinMax()
	{
		// Test Success
		LightRequest request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		request.setOperationalDataCriteria(new OperationalDataCriteria());
		InternalResponse response = getLightBCL().resetMinMaxValue(request);
		TestBaseUtil.assertResponse(response);

		// Test Success - Without light to do Reset Min/Max
		setTestControl(getLightBCL(), ILightDAC.class, "NO_RESULTS");

		request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		request.setOperationalDataCriteria(new OperationalDataCriteria());
		response = getLightBCL().resetMinMaxValue(request);
		TestBaseUtil.assertResponse(response);

		// Error Situation
		setTestControl(getLightBCL(), ILightDAC.class, "");

		setSituation(getLightBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);
		response = getLightBCL().resetMinMaxValue(request);
		assertTrue(response.isInError());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Error Situation
		setSituation(getLightBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"resetMinMaxValue");
		response = getLightBCL().resetMinMaxValue(request);
		assertTrue(response.isInError());
		assertMessages(response, RESET_MIN_MAX_FAILED);
	}

	/**
	 * Test update light mass.
	 */
	@Test
	public void testUpdateLightMass()
	{
		UserContext userContext = TestBaseUtil.createUserContext();

		// Success Situation - Update Light Protected
		LightMassUpdateRequest request = new LightMassUpdateRequest();
		request.setLight(TestBaseUtil.createLight(userContext));
		request.getLight().setProtect(Boolean.TRUE);
		InternalResponse response = getLightBCL().updateLightMass(request);
		TestBaseUtil.assertResponse(response);

		// Success Situation - Nothing to update
		request = new LightMassUpdateRequest();
		request.setLight(TestBaseUtil.createLight(userContext));
		response = getLightBCL().updateLightMass(request);
		TestBaseUtil.assertResponse(response);

		// Error Situation
		setSituation(getLightBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"updateLightBase");
		request = new LightMassUpdateRequest();
		request.setLight(TestBaseUtil.createLight(userContext));
		request.getLight().setProtect(Boolean.TRUE);
		response = getLightBCL().updateLightMass(request);
		assertTrue(response.isInError());

	}

	/**
	 * Test update light base.
	 */
	@Test
	public void testUpdateLightBase()
	{
		UserContext userContext = TestBaseUtil.createUserContext();

		// Success situation
		LightMaintenanceRequest request = new LightMaintenanceRequest();
		request.setLight(TestBaseUtil.createLight(userContext));
		InternalResponse response = getLightBCL().updateLightBase(request);
		TestBaseUtil.assertResponse(response);

		// Success situation (update Light Protected)
		request = new LightMaintenanceRequest();
		request.setLight(TestBaseUtil.createLight(userContext));
		request.getLight().setProtect(Boolean.TRUE);
		response = getLightBCL().updateLightBase(request);
		TestBaseUtil.assertResponse(response);

		// Error Situation
		setSituation(getLightBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"updateLight");

		request = new LightMaintenanceRequest();
		request.setLight(TestBaseUtil.createLight(userContext));
		response = getLightBCL().updateLightBase(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch all to map by request.
	 */
	@Test
	public void testFetchAllToMapByRequest()
	{
		UserContext userContext = TestBaseUtil.createUserContext();

		// Success situation
		LightRequest request = new LightRequest();
		request.setUserContext(userContext);

		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setGeoCodeCriteria(new GeoCodeCriteria());

		request.setLightCriteria(lightCriteria);

		InternalResultsResponse<GeocodeLightInfo> response = getLightBCL().fetchAllToMapByRequest(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test update configuration.
	 */
	@Test
	public void testUpdateConfiguration()
	{
		// Success situation
		ConfigurationMaintenanceRequest request = new ConfigurationMaintenanceRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setLightId(ONE);

		InternalResponse response = getLightBCL().updateConfiguration(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test update schedule.
	 */
	@Test
	public void testUpdateSchedule()
	{
		// Success situation
		ScheduleMaintenanceRequest request = new ScheduleMaintenanceRequest();
		request.setLightId(ONE);
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResponse response = getLightBCL().updateSchedule(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test update last operational data.
	 */
	@Test
	public void testUpdateLastOperationalData()
	{
		// Success situation
		LastOperationalDataMaintenanceRequest request = new LastOperationalDataMaintenanceRequest();
		request.setLightId(ONE);
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResponse response = getLightBCL().updateLastOperationalData(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test delete light references.
	 */
	@Test
	public void testDeleteLightReferences()
	{
		// Success situation
		LightDeleteRequest request = new LightDeleteRequest(TestBaseUtil.createLight());
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResponse response = getLightBCL().deleteLightReferences(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert light.
	 */
	@Test
	public void testInsertLight()
	{
		// Success situation
		LightMaintenanceRequest request = new LightMaintenanceRequest(TestBaseUtil.createLight());
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResponse response = getLightBCL().insertLight(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch map bounds by request.
	 */
	@Test
	public void testFetchMapBoundsByRequest()
	{
		// Success situation
		LightRequest request = new LightRequest();
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResultsResponse<GeocodeLightInfo> response = getLightBCL().fetchMapBoundsByRequest(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch notification history by request.
	 */
	@Test
	public void testFetchNotificationHistoryByRequest()
	{
		// Success situation
		NotificationHistoryRequest request = new NotificationHistoryRequest();
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryBCL().fetchNotificationHistoryByRequest(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test fetch notification history alert by id.
	 */
	@Test
	public void testFetchNotificationHistoryAlertById()
	{
		// Success situation
		NotificationHistoryRequest request = new NotificationHistoryRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());
		request.getNotificationHistoryCriteria().setNotificationHistoryId(TEN);

		InternalResultsResponse<AlertClassification> response =
				getNotificationHistoryBCL().fetchNotificationHistoryAlertById(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch notification history by id.
	 */
	@Test
	public void testFetchNotificationHistoryById()
	{
		// Success situation
		NotificationHistoryRequest request = new NotificationHistoryRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setNotificationHistoryCriteria(new NotificationHistoryCriteria());
		request.getNotificationHistoryCriteria().setNotificationHistoryId(TEN);

		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryBCL().fetchNotificationHistoryById(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test insert notification history alert classification.
	 */
	@Test
	public void testInsertNotificationHistoryAlertClassification()
	{
		UserContext userContext = TestBaseUtil.createUserContext();

		// Success situation
		AlertClassificationMaintenanceRequest request = new AlertClassificationMaintenanceRequest();
		request.setAlertClassification(TestBaseUtil.createAlertClassification(userContext, TEN,
				AlertSubTypeEnum.BOARD_FAILURE));

		InternalResultsResponse<AlertClassification> response =
				getNotificationHistoryBCL().insertNotificationHistoryAlertClassification(request);
		TestBaseUtil.assertResponse(response);
	}

	@Test
	public void testUpdateNotificationHistory()
	{
		UserContext userContext = TestBaseUtil.createUserContext();
		Light light = TestBaseUtil.createLight();

		// Success situation
		NotificationHistoryMaintenanceRequest request = new NotificationHistoryMaintenanceRequest();
		request.setNotificationHistory(TestBaseUtil.createNotificationHistory(userContext, light));

		InternalResponse response = getNotificationHistoryBCL().updateNotificationHistory(request);
		TestBaseUtil.assertResponse(response);

	}

	@Test
	public void testInsertNotificationHistory()
	{
		UserContext userContext = TestBaseUtil.createUserContext();
		Light light = TestBaseUtil.createLight();

		// Success situation
		NotificationHistoryMaintenanceRequest request = new NotificationHistoryMaintenanceRequest();
		request.setNotificationHistory(TestBaseUtil.createNotificationHistory(userContext, light));

		AlertClassification alertClassification =
				TestBaseUtil.createAlertClassification(userContext, request.getNotificationHistory().getId(),
						AlertSubTypeEnum.BROWN_OUT_DETECTED);

		request.getNotificationHistory().setAlertClassifications(Arrays.asList(alertClassification));

		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryBCL().insertNotificationHistory(request);
		TestBaseUtil.assertResponse(response);
	}

	@Test
	public void testFetchAttributeChanges()
	{
		// Sucess situations
		Date date = SensusDateUtil.getMinDate();
		SearchDate searchDate = new SearchDate(date, DateSearchType.GREATER_THAN);
		LightRequest request = createLightRequest();
		request.setLightCriteria(new LightCriteria());
		request.getLightCriteria().setModifyDate(searchDate);
		InternalResultsResponse<BigInteger> response = getLightBCL().fetchAttributeChanges(request);
		assertResultResponse(response);
	}

}
