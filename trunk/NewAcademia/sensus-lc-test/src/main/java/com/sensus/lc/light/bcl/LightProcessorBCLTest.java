package com.sensus.lc.light.bcl;

import static com.sensus.lc.base.TestBaseUtil.createLightWithEcoModeBaseline;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.GetDataFromLightEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.tenant.model.Tenant;

/**
 * Unit test for LightProcessorBCL.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/light/lightprocessorbclimpltest.xml"})
public class LightProcessorBCLTest extends AbstractTestBaseBusiness
{

	/** The light processor bcl. */
	private ILightProcessorBCL lightProcessorBCL;

	/** The calculate comm fail page size. */
	private Integer calculateCommFailPageSize;

	/** The Constant VALID_MODEL_NUMBER. */
	private static final String VALID_MODEL_NUMBER = "539449037C2310";

	/** The Constant TRANSACTION_ID. */
	private static final String TRANSACTION_ID = "1";

	/** The Constant PERCENTAGE. */
	private static final Integer PERCENTAGE = 100;

	/**
	 * Gets the light processor bcl.
	 * 
	 * @return the lightProcessorBCL
	 */
	public ILightProcessorBCL getLightProcessorBCL()
	{
		return lightProcessorBCL;
	}

	/**
	 * Sets the light processor bcl.
	 * 
	 * @param lightProcessorBCL the lightProcessorBCL to set
	 */
	@Resource(name = "lightProcessorBCLTarget")
	public void setLightProcessorBCL(ILightProcessorBCL lightProcessorBCL)
	{
		this.lightProcessorBCL = lightProcessorBCL;
	}

	public Integer getCalculateCommFailPageSize()
	{
		return calculateCommFailPageSize;
	}

	public void setCalculateCommFailPageSize(Integer calculateCommFailPageSize)
	{
		this.calculateCommFailPageSize = calculateCommFailPageSize;
	}

	/**
	 * Test process light status notification.
	 */
	@Test
	public void testProcessLightStatusNotification()
	{
		resetMocksData(getLightProcessorBCL());

		// Success Situation
		setSituation(getLightProcessorBCL(), SituationsEnum.SUCCESS, INotificationHistoryDAC.class,
				"fetchNotificationHistoryByRequest");

		UserContext userContext = TestBaseUtil.createUserContext();
		Light light = TestBaseUtil.createLight();
		light.setLastNotificationHistory(TestBaseUtil.createNotificationHistory(userContext, light));

		LightStatusNotificationRequest request = new LightStatusNotificationRequest(userContext);
		request.setTransactionId(TRANSACTION_ID);
		request.setMessageDate(LCDateUtil.getNewDateUTC());
		request.setLight(light);
		InternalResultsResponse<NotificationHistory> response =
				getLightProcessorBCL().processLightStatusNotification(request);

		TestBaseUtil.assertResponse(response);

		// Success situation - with FORCED STATUS
		setSituation(getLightProcessorBCL(), SituationsEnum.SUCCESS, INotificationHistoryDAC.class,
				"fetchNotificationHistoryByRequest");

		request = new LightStatusNotificationRequest(userContext);
		request.setTransactionId(TRANSACTION_ID);
		request.setMessageDate(LCDateUtil.getNewDateUTC());
		request.setLight(light);
		request.setForced(Boolean.TRUE);
		response = getLightProcessorBCL().processLightStatusNotification(request);

		TestBaseUtil.assertResponse(response);

		// Success situation - with light DIMMABLE
		setSituation(getLightProcessorBCL(), SituationsEnum.SUCCESS, INotificationHistoryDAC.class,
				"fetchNotificationHistoryByRequest");

		request = new LightStatusNotificationRequest(userContext);
		request.setTransactionId(TRANSACTION_ID);
		request.setMessageDate(LCDateUtil.getNewDateUTC());
		request.setLight(light);
		request.getLight().getConfiguration().setDimmable(Boolean.TRUE);
		request.getLight().getConfiguration().setModelNumber(VALID_MODEL_NUMBER);
		request.setForced(Boolean.TRUE);
		response = getLightProcessorBCL().processLightStatusNotification(request);

		TestBaseUtil.assertResponse(response);

		// Success Situation without notification history
		setSituation(getLightProcessorBCL(), SituationsEnum.VALIDATION, INotificationHistoryDAC.class,
				"fetchNotificationHistoryByRequest");

		response = getLightProcessorBCL().processLightStatusNotification(request);
		TestBaseUtil.assertResponse(response);

		// Success situation with operational Data
		request = new LightStatusNotificationRequest(userContext);
		request.setTransactionId(TRANSACTION_ID);
		request.setMessageDate(LCDateUtil.getNewDateUTC());
		request.setLight(light);
		OperationalData operationalData =
				TestBaseUtil.createOperationalData(TestBaseUtil.createNotificationHistory(userContext, light));
		List<OperationalData> listOperationalData = new ArrayList<>();
		listOperationalData.add(operationalData);
		request.setOperationalData(listOperationalData);
		response = getLightProcessorBCL().processLightStatusNotification(request);

		TestBaseUtil.assertResponse(response);

		// Error situation - problems during insertNotificationHistory()
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class,
				"insertNotificationHistory");

		request = new LightStatusNotificationRequest(userContext);
		request.setTransactionId(TRANSACTION_ID);
		request.setMessageDate(LCDateUtil.getNewDateUTC());
		request.setLight(light);
		request.setForced(Boolean.TRUE);
		response = getLightProcessorBCL().processLightStatusNotification(request);
		assertTrue(response.isInError());
		resetMocksData(getLightProcessorBCL());

		// Error situation - problems during update the light information
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"updateLight");

		request = new LightStatusNotificationRequest(userContext);
		request.setTransactionId(TRANSACTION_ID);
		request.setMessageDate(LCDateUtil.getNewDateUTC());
		request.setLight(light);
		request.setForced(Boolean.TRUE);
		response = getLightProcessorBCL().processLightStatusNotification(request);
		assertTrue(response.isInError());

	}

	/**
	 * Test process alarm warning notification.
	 */
	@Test
	public void testProcessAlarmWarningNotification()
	{

		// Success Situation
		AlarmNotificationRequest alarmNotificationRequest = new AlarmNotificationRequest();

		List<AlertSubTypeEnum> alertSubTypes = new ArrayList<AlertSubTypeEnum>();
		alertSubTypes.add(AlertSubTypeEnum.LAMP_FAILURE);
		alarmNotificationRequest.setAlertSubTypes(alertSubTypes);

		alarmNotificationRequest.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		alarmNotificationRequest.setLight(TestBaseUtil.createLight());
		alarmNotificationRequest.setTenant(TestBaseUtil.createTenant());
		alarmNotificationRequest.setUserContext(TestBaseUtil.createUserContext());

		InternalResultsResponse<NotificationHistory> response =
				getLightProcessorBCL().processAlarmWarningNotification(alarmNotificationRequest);

		TestBaseUtil.assertResponse(response);

		// Success Situation without notification history
		setSituation(getLightProcessorBCL(), SituationsEnum.VALIDATION, INotificationHistoryDAC.class,
				"fetchNotificationHistoryByRequest");

		response = getLightProcessorBCL().processAlarmWarningNotification(alarmNotificationRequest);

		TestBaseUtil.assertResponse(response);

		// Success situation - with a last notification history
		resetMocksData(getLightProcessorBCL());
		Light light = TestBaseUtil.createLight();
		UserContext userContext = TestBaseUtil.createUserContext();

		alarmNotificationRequest = new AlarmNotificationRequest();

		alertSubTypes = new ArrayList<AlertSubTypeEnum>();
		alertSubTypes.add(AlertSubTypeEnum.LAMP_FAILURE);
		alarmNotificationRequest.setAlertSubTypes(alertSubTypes);

		alarmNotificationRequest.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		alarmNotificationRequest.setLight(TestBaseUtil.createLight());
		alarmNotificationRequest.setTenant(TestBaseUtil.createTenant());
		alarmNotificationRequest.setUserContext(TestBaseUtil.createUserContext());
		alarmNotificationRequest.getLight().setLastNotificationHistory(
				TestBaseUtil.createNotificationHistory(userContext, light));
		alarmNotificationRequest.getLight().getLastNotificationHistory()
				.setNotificationType(NotificationTypeEnum.ALARM);

		response = getLightProcessorBCL().processAlarmWarningNotification(alarmNotificationRequest);

		TestBaseUtil.assertResponse(response);

		// Coverage situation - with a last notification history
		resetMocksData(getLightProcessorBCL());
		light = TestBaseUtil.createLight();
		userContext = TestBaseUtil.createUserContext();

		alarmNotificationRequest = new AlarmNotificationRequest();

		alertSubTypes = new ArrayList<AlertSubTypeEnum>();
		alertSubTypes.add(AlertSubTypeEnum.LAMP_FAILURE);
		alarmNotificationRequest.setAlertSubTypes(alertSubTypes);

		alarmNotificationRequest.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		alarmNotificationRequest.setLight(TestBaseUtil.createLight());
		alarmNotificationRequest.setTenant(TestBaseUtil.createTenant());
		alarmNotificationRequest.setUserContext(TestBaseUtil.createUserContext());
		alarmNotificationRequest.getLight().setLastNotificationHistory(
				TestBaseUtil.createNotificationHistory(userContext, light));
		alarmNotificationRequest.getLight().getLastNotificationHistory()
				.setNotificationType(NotificationTypeEnum.ALARM);

		List<AlertClassification> alertClassificationList = new ArrayList<AlertClassification>();
		alertClassificationList.add(TestBaseUtil.createAlertClassification(
				alarmNotificationRequest.getUserContext(),
				alarmNotificationRequest.getLight().getLastNotificationHistory().getId(),
				AlertSubTypeEnum.BOARD_FAILURE));

		alarmNotificationRequest
				.getLight()
				.getLastNotificationHistory()
				.setAlertClassifications(alertClassificationList);

		response = getLightProcessorBCL().processAlarmWarningNotification(alarmNotificationRequest);

		TestBaseUtil.assertResponse(response);

		// Success situation - update current and voltage
		resetMocksData(getLightProcessorBCL());
		alarmNotificationRequest = new AlarmNotificationRequest();

		alertSubTypes = new ArrayList<AlertSubTypeEnum>();
		alertSubTypes.add(AlertSubTypeEnum.LAMP_FAILURE);
		alarmNotificationRequest.setAlertSubTypes(alertSubTypes);

		alarmNotificationRequest.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		alarmNotificationRequest.setLight(TestBaseUtil.createLight());
		alarmNotificationRequest.setTenant(TestBaseUtil.createTenant());
		alarmNotificationRequest.setUserContext(TestBaseUtil.createUserContext());
		alarmNotificationRequest.getLight().setLastNotificationHistory(
				TestBaseUtil.createNotificationHistory(userContext, light));

		response = getLightProcessorBCL().processAlarmWarningNotification(alarmNotificationRequest);

		TestBaseUtil.assertResponse(response);

		// Error situation - no data from fetchNotificationHistoryAlertbyID()
		resetMocksData(getLightProcessorBCL());
		setTestControl(getLightProcessorBCL(), INotificationHistoryDAC.class, "NO_RESULTS");

		resetMocksData(getLightProcessorBCL());
		alarmNotificationRequest = new AlarmNotificationRequest();

		alertSubTypes = new ArrayList<AlertSubTypeEnum>();
		alertSubTypes.add(AlertSubTypeEnum.LAMP_FAILURE);
		alarmNotificationRequest.setAlertSubTypes(alertSubTypes);

		alarmNotificationRequest.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		alarmNotificationRequest.setLight(TestBaseUtil.createLight());
		alarmNotificationRequest.setTenant(TestBaseUtil.createTenant());
		alarmNotificationRequest.setUserContext(TestBaseUtil.createUserContext());
		alarmNotificationRequest.getLight().setLastNotificationHistory(
				TestBaseUtil.createNotificationHistory(userContext, light));
		alarmNotificationRequest.getLight().getLastNotificationHistory()
				.setNotificationType(NotificationTypeEnum.ALARM);

		response = getLightProcessorBCL().processAlarmWarningNotification(alarmNotificationRequest);

		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test process light setup notification.
	 */
	@Test
	public void testProcessLightSetupNotification()
	{
		// Success Situation
		LightSetupNotificationRequest request = new LightSetupNotificationRequest();
		request.setLight(TestBaseUtil.createLight());

		InternalResponse response = getLightProcessorBCL().processLightSetupNotification(request);
		TestBaseUtil.assertResponse(response);

		// Validation situation
		request.getLight().setLastNotificationHistory(
				TestBaseUtil.createNotificationHistory(request.getUserContext(), request.getLight()));
		request.getLight().getLastNotificationHistory().setNotificationType(NotificationTypeEnum.SETUP);
		response = getLightProcessorBCL().processLightSetupNotification(request);
		TestBaseUtil.assertResponse(response);

		// Error Situation
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class,
				"insertNotificationHistory");

		response = getLightProcessorBCL().processLightSetupNotification(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test process light binding notification.
	 */
	@Test
	public void testProcessLightBindingNotification()
	{
		// Success situation
		LightStatusNotificationRequest request = new LightStatusNotificationRequest();
		request.setLight(TestBaseUtil.createLight());

		InternalResponse response = getLightProcessorBCL().processLightBindingNotification(request);
		TestBaseUtil.assertResponse(response);

		request.getLight().getRadio().getLocation().setLatitude(null);

		response = getLightProcessorBCL().processLightBindingNotification(request);
		TestBaseUtil.assertResponse(response);

		// Error situation

		setSituation(getLightProcessorBCL(), SituationsEnum.VALIDATION, ILightDAC.class,
				"fetchById");

		response = getLightProcessorBCL().processLightBindingNotification(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test process channel setup audit notification.
	 */
	@Test
	public void testProcessChannelSetupAuditNotification()
	{
		resetMocksData(getLightProcessorBCL());

		// Success situation
		ChannelSetupAuditRequest request = new ChannelSetupAuditRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		Light light = TestBaseUtil.createLight();
		light.setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);
		request.setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);

		request.setLight(light);
		InternalResponse response = getLightProcessorBCL().processChannelSetupAuditNotification(request);

		TestBaseUtil.assertResponse(response);

		// Error situation - during the exclusion of light references
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightBCL.class,
				"deleteLightReferences");

		request = new ChannelSetupAuditRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		light = TestBaseUtil.createLight();
		light.setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);
		request.setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);

		request.setLight(light);
		response = getLightProcessorBCL().processChannelSetupAuditNotification(request);
		assertTrue(response.isInError());

		// Error situation - during the update light
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"updateLight");

		request = new ChannelSetupAuditRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		light = TestBaseUtil.createLight();
		light.setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);
		request.setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);

		request.setLight(light);
		response = getLightProcessorBCL().processChannelSetupAuditNotification(request);
		assertTrue(response.isInError());
	}

	/**
	 * Test process set light intensity notification.
	 */
	@Test
	public void testProcessSetLightIntensityNotification()
	{
		// Success situation
		LightIntensityRequest request = new LightIntensityRequest(TestBaseUtil.createUserContext());
		List<Light> lights = new ArrayList<Light>();
		lights.add(TestBaseUtil.createLight());
		request.setLightList(lights);

		InternalResponse response = getLightProcessorBCL().processSetLightIntensityNotification(request);
		TestBaseUtil.assertResponse(response);

		// Success situation - with BLINK STATUS = SLOW
		request = new LightIntensityRequest(TestBaseUtil.createUserContext());
		lights = new ArrayList<Light>();
		lights.add(TestBaseUtil.createLight());
		request.setLightList(lights);
		request.getLightList().get(0).setBlinkStatus(BlinkStatusEnum.SLOW);

		response = getLightProcessorBCL().processSetLightIntensityNotification(request);
		TestBaseUtil.assertResponse(response);

		// Error Situation - errors during the insert light
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class,
				"insertNotificationHistory");

		request = new LightIntensityRequest(TestBaseUtil.createUserContext());
		lights = new ArrayList<Light>();
		lights.add(TestBaseUtil.createLight());
		request.setLightList(lights);
		request.getLightList().get(0).setBlinkStatus(BlinkStatusEnum.SLOW);

		response = getLightProcessorBCL().processSetLightIntensityNotification(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test process update light status notification.
	 */
	@Test
	public void testProcessUpdateLightStatusNotification()
	{
		resetMocksData(getLightProcessorBCL());

		// Success Situation
		UpdateLightNotificationRequest request = new UpdateLightNotificationRequest();

		request.getLightList().add(TestBaseUtil.createLight());
		request.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResponse response = getLightProcessorBCL().processUpdateLightStatusNotification(request);
		TestBaseUtil.assertResponse(response);

		// Success Situation protected light
		request = new UpdateLightNotificationRequest();
		Light light = TestBaseUtil.createLight();
		light.setProtect(true);
		request.getLightList().add(light);
		request.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		request.setUserContext(TestBaseUtil.createUserContext());

		response = getLightProcessorBCL().processUpdateLightStatusNotification(request);
		TestBaseUtil.assertResponse(response);

		// Error situation - during the insert light
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class,
				"insertNotificationHistory");

		request = new UpdateLightNotificationRequest();
		light = TestBaseUtil.createLight();
		request.getLightList().add(light);
		request.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		request.setUserContext(TestBaseUtil.createUserContext());

		response = getLightProcessorBCL().processUpdateLightStatusNotification(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test process apply smartpoint properties notification.
	 */
	@Test
	public void testProcessApplySmartpointPropertiesNotification()
	{
		// Success situation
		resetMocksData(getLightProcessorBCL());

		ProcessLightsRequest request = new ProcessLightsRequest();
		request.getLights().add(createLightWithEcoModeBaseline());
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResponse response = getLightProcessorBCL().processApplySmartpointPropertiesNotification(request);
		TestBaseUtil.assertResponse(response);

		// Error situation - problems during the notification history insert
		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightNotificationHistoryBCL.class,
				"insertNotificationHistory");

		request = new ProcessLightsRequest();
		request.getLights().add(TestBaseUtil.createLight());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightProcessorBCL().processApplySmartpointPropertiesNotification(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test process clear alarm notification.
	 */
	@Test
	public void testProcessClearAlarmNotification()
	{
		// Success situation
		AlarmNotificationRequest request = new AlarmNotificationRequest();
		request.setLight(TestBaseUtil.createLight());

		List<AlertSubTypeEnum> alertSubTypes = new ArrayList<AlertSubTypeEnum>();
		alertSubTypes.add(AlertSubTypeEnum.LAMP_FAILURE);
		request.setAlertSubTypes(alertSubTypes);

		InternalResultsResponse<NotificationHistory> response =
				getLightProcessorBCL().processClearAlarmNotification(request);

		TestBaseUtil.assertResponse(response);

		// Error situation
		request = new AlarmNotificationRequest();
		request.setLight(TestBaseUtil.createLight());

		response = getLightProcessorBCL().processClearAlarmNotification(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test initiate update light intensity.
	 */
	@Test
	public void testInitiateUpdateLightIntensity()
	{
		// Success situation
		LightRequest request = new LightRequest();
		ActionCriteria actionCriteria = new ActionCriteria();
		actionCriteria.setBlinkStatus(BlinkStatusEnum.SLOW);
		actionCriteria.setGetDataFromLightEnum(GetDataFromLightEnum.ALL);
		actionCriteria.setOverridePerDate(new Date());
		actionCriteria.setIsClearOverride(true);
		actionCriteria.setPercentage(PERCENTAGE);
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setActionCriteria(actionCriteria);

		InternalResultsResponse<com.sensus.lc.process.model.Process> response =
				getLightProcessorBCL().initiateUpdateLightIntensity(request);

		TestBaseUtil.assertResponse(response);

		// Error situation
		request.getActionCriteria().setBlinkStatus(BlinkStatusEnum.NONE);
		request.getActionCriteria().setIsClearOverride(true);
		request.getActionCriteria().setOverride(OverrideEnum.SCHEDULED);

		response = getLightProcessorBCL().initiateUpdateLightIntensity(request);
		TestBaseUtil.assertResponse(response);

		// Error situation
		request.getActionCriteria().setBlinkStatus(BlinkStatusEnum.NONE);
		request.getActionCriteria().setIsClearOverride(false);
		actionCriteria.setPercentage(PERCENTAGE);

		response = getLightProcessorBCL().initiateUpdateLightIntensity(request);
		TestBaseUtil.assertResponse(response);

		// Error situation
		request.getActionCriteria().setBlinkStatus(BlinkStatusEnum.NONE);
		request.getActionCriteria().setIsClearOverride(false);
		actionCriteria.setPercentage(new Integer(0));

		response = getLightProcessorBCL().initiateUpdateLightIntensity(request);
		TestBaseUtil.assertResponse(response);

	}

	/**
	 * Test initiate delete alert.
	 */
	@Test
	public void testInitiateDeleteAlert()
	{
		// Success situation
		LightRequest request = new LightRequest();

		AlertCriteria alertCriteria = new AlertCriteria();
		request.setAlertCriteria(alertCriteria);

		request.setLightCriteria(new LightCriteria());

		InternalResultsResponse<com.sensus.lc.process.model.Process> response =
				getLightProcessorBCL().initiateDeleteAlert(request);

		TestBaseUtil.assertResponse(response);

		// Error situation
		request = new LightRequest();

		alertCriteria = new AlertCriteria();
		alertCriteria.setAlertSubtypeList(Arrays.asList(AlertSubTypeEnum.BOARD_FAILURE));
		request.setAlertCriteria(alertCriteria);

		request.setLightCriteria(new LightCriteria());

		response = getLightProcessorBCL().initiateDeleteAlert(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test initiate upsert light pole id.
	 */
	@Test
	public void testInitiateUpsertLightPoleId()
	{
		LightMaintenanceRequest request = new LightMaintenanceRequest(TestBaseUtil.createLight());

		InternalResultsResponse<com.sensus.lc.process.model.Process> response =
				getLightProcessorBCL().initiateUpsertLightPoleId(request);

		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test initiate update light status.
	 */
	@Test
	public void testInitiateUpdateLightStatus()
	{
		// Success situation
		LightMaintenanceRequest request = new LightMaintenanceRequest(TestBaseUtil.createLight());
		InternalResultsResponse<Process> response =
				getLightProcessorBCL().initiateUpdateLightStatus(request);
		TestBaseUtil.assertResponse(response);

		// Error situation
		request = TestBaseUtil
				.createLightMaintenanceRequest(TestBaseUtil.createUserContext(), TestBaseUtil.createLight());
		request.getLight().setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);
		response =
				getLightProcessorBCL().initiateUpdateLightStatus(request);
		TestBaseUtil.assertResponse(response);

		// Error situation
		request = TestBaseUtil
				.createLightMaintenanceRequest(TestBaseUtil.createUserContext(), TestBaseUtil.createLight());
		request.getLight().setLifeCycleState(LifeCycleStateEnum.MAINTENANCE);
		response =
				getLightProcessorBCL().initiateUpdateLightStatus(request);
		TestBaseUtil.assertResponse(response);
	}

	@Test
	public void testInsertConsumptionToLightInCommunicationFailure()
	{
		UserContext userContext = createUserContext();
		userContext.<Tenant> getTenant();

		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"fetchLightsInCommunicationFailure");
		getLightProcessorBCL().insertConsumptionToLightInCommunicationFailure(userContext);

	}

	@Test
	public void testInitiateUpsertLightLatLng()
	{
		LightMaintenanceRequest request =
				TestBaseUtil
						.createLightMaintenanceRequest(TestBaseUtil.createUserContext(), TestBaseUtil.createLight());
		InternalResultsResponse<Process> response = getLightProcessorBCL().initiateUpsertLightLatLng(request);
		TestBaseUtil.assertResponse(response);
	}

	@Test
	public void testVerifyCommunicationFailure()
	{
		UserContext userContext = createUserContext();
		userContext.<Tenant> getTenant();

		setSituation(getLightProcessorBCL(), SituationsEnum.ERROR, ILightDAC.class,
				"fetchLightsToAddCommunicationFailure");
		getLightProcessorBCL().verifyCommunicationFailure(userContext);
	}
}
