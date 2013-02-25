package com.sensus.mlc.smartpoint.bcl;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.smartpoint.dac.ISmartPointDAC;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.OperationalDataTypeEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class SmartPointProcessorBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/smartpoint/smartpointbclimpltest.xml"})
public class SmartPointProcessorBCLTest extends AbstractTestBaseBusiness
{
	/** The Constant ARBITRARY_LIGHT_PARAMETER_ID_18. */
	private static final Integer ARBITRARY_LIGHT_PARAMETER_ID_18 = 18;

	/** The Constant ARBITRARY_LIGHT_PARAMETER_ID_10. */
	private static final Integer ARBITRARY_LIGHT_PARAMETER_ID_10 = 10;

	/** The Constant ARBITRARY_LIGHT_PARAMETER_ID_5. */
	private static final Integer ARBITRARY_LIGHT_PARAMETER_ID_5 = 5;

	/** The Constant ARBITRARY_PERCENTAGE_50. */
	private static final Integer ARBITRARY_PERCENTAGE_50 = 50;

	/** The Constant ARBITRARY_PERCENTAGE_100. */
	private static final Integer ARBITRARY_PERCENTAGE_100 = 100;

	/** The Constant ARBITRARY_PERCENTAGE_10. */
	private static final Integer ARBITRARY_PERCENTAGE_10 = 10;

	/** The Constant ARBITRARY_OPERATIONAL_DATA_VALUE_30. */
	private static final Float ARBITRARY_OPERATIONAL_DATA_VALUE_30 = new Float(30);

	/** The Constant ARBITRARY_OPERATIONAL_DATA_VALUE_120. */
	private static final Float ARBITRARY_OPERATIONAL_DATA_VALUE_120 = new Float(120);

	/** The Constant ARBITRARY_OPERATIONAL_DATA_VALUE_200. */
	private static final Float ARBITRARY_OPERATIONAL_DATA_VALUE_200 = new Float(200);

	/** The Constant PECO. */
	private static final String PECO = "PECO";

	/** The Constant UPDATE_LIGHT_STATE. */
	private static final String UPDATE_LIGHT_STATE = "updateLightState";

	/** The Constant ONE. */
	private static final String ONE = "1";

	/** The Constant SENSUS_MLC_SMARTPOINTBCLIMPL_ADD_LIGHT_FAILED. */
	private static final String SENSUS_MLC_SMARTPOINTBCLIMPL_ADD_LIGHT_FAILED =
			"sensus.mlc.smartpointbclimpl.add.light.failed";

	/** The Constant SENSUS_MLC_MLC_ACTION_LIGHT_STATUS_ALREADY_ACTIVE. */
	private static final String SENSUS_MLC_MLC_ACTION_LIGHT_STATUS_ALREADY_ACTIVE =
			"sensus.mlc.mlc_action.light_status.already.active";

	/** The smart point processor bcl. */
	private ISmartPointProcessorBCL smartPointProcessorBCL;

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL;

	/**
	 * Gets the smart point processor bcl.
	 *
	 * @return the smart point processor bcl
	 */
	public ISmartPointProcessorBCL getSmartPointProcessorBCL()
	{
		return smartPointProcessorBCL;
	}

	/**
	 * Sets the smart point processor bcl.
	 *
	 * @param smartPointProcessorBCL the new smart point processor bcl
	 */
	@Resource(name = "smartPointProcessorBCLTarget")
	public void setSmartPointProcessorBCL(ISmartPointProcessorBCL smartPointProcessorBCL)
	{
		this.smartPointProcessorBCL = smartPointProcessorBCL;
	}

	/**
	 * Gets the smart point accessor bcl.
	 *
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		return smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 *
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	@Resource(name = "smartPointAccessorBCLTarget")
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setSmartPointArea();
	}

	/**
	 * Sets the smart point area.
	 */
	public void setSmartPointArea()
	{
		setArea(getSmartPointProcessorBCL(), LCAreaEnum.SMARTPOINT);
	}

	/**
	 * Reset the smart point area.
	 */
	@After
	public void resetMocksToSmartPointArea()
	{
		resetMocksData(getSmartPointProcessorBCL());
		setSmartPointArea();
	}

	/**
	 * Test light status notification.
	 */
	@Test
	public void testProcessLightStatusNotification()
	{
		// Success situation
		LightStatusNotificationRequest request = TestBaseUtil.createLightStatusNotificationRequest();
		Light light = TestBaseUtil.createLight();
		request.setLight(light);

		InternalResponse response = getSmartPointProcessorBCL().processLightStatusNotification(request);
		assertResponse(response);

		light.setLightStateEnum(LightStateEnum.MAINTENANCE);
		request.setLight(light);
		response = getSmartPointProcessorBCL().processLightStatusNotification(request);
		assertResponse(response);

		light.setLightStateEnum(LightStateEnum.ON);
		request.setLight(light);
		light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
		response = getSmartPointProcessorBCL().processLightStatusNotification(request);
		assertResponse(response);

		light.setLightStateEnum(LightStateEnum.UNKNOWN);
		request.setLight(light);
		light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
		response = getSmartPointProcessorBCL().processLightStatusNotification(request);
		assertResponse(response);

		// Error situation
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, UPDATE_LIGHT_STATE);
		response = getSmartPointProcessorBCL().processLightStatusNotification(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test light setup notification.
	 */
	@Test
	public void testProcessLightSetupNotification()
	{
		Light light = TestBaseUtil.createLight();
		light.setLightStateEnum(LightStateEnum.ON);
		light.setCurrentStatusMessage(TestBaseUtil.createStatusMessage());

		// Success situation
		LightSetupNotificationRequest request = TestBaseUtil.createLightSetupNotification();
		request.setLight(light);

		InternalResponse response = getSmartPointProcessorBCL().processLightSetupNotification(request);
		assertResponse(response);

		// Error situation
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, "fetchLightByRniId");
		response = getSmartPointProcessorBCL().processLightSetupNotification(request);
		assertMessages(response, SENSUS_MLC_SMARTPOINTBCLIMPL_ADD_LIGHT_FAILED);
	}

	/**
	 * Test forced light status notification.
	 */
	@Test
	public void testProcessForcedLightStatusNotification()
	{
		// Success situation
		ForcedLightStatusNotificationRequest request = TestBaseUtil.createForcedLightStatusNotificationRequest();
		Light light = TestBaseUtil.createLight();
		light.setLightStateEnum(LightStateEnum.ON);
		request.setLight(light);

		request.setLightParameters(prepareParameters());
		request.setOperationalData(prepareOperationalData());
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);

		InternalResponse response = getSmartPointProcessorBCL().processForcedLightStatusNotification(request);
		assertResponse(response);

		request.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
		response = getSmartPointProcessorBCL().processForcedLightStatusNotification(request);
		assertResponse(response);

		// Error situation
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, "insertOperationalData");
		response = getSmartPointProcessorBCL().processForcedLightStatusNotification(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test process alarm warning notification.
	 */
	@Test
	public void testProcessAlarmWarningNotification()
	{
		// Success situation
		// Status Active
		AlarmNotificationRequest request = TestBaseUtil.createAlarmNotificationRequest();
		Light light = TestBaseUtil.createLight();
		request.setLight(light);
		request.setTenant(getDummyTenant(PECO));
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		InternalResultsResponse<StatusMessage> response =
				getSmartPointProcessorBCL().processAlarmWarningNotification(request);
		assertResultResponse(response);

		// Status Warning
		List<StatusException> statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.BROWN_OUT_DETECTED));
		request.setStatusException(statusExceptions);
		response = getSmartPointProcessorBCL().processAlarmWarningNotification(request);
		assertResultResponse(response);

		// Status Alarm
		statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(new StatusException(StatusExceptionTypeEnum.POWER_FAILURE));
		request.setStatusException(statusExceptions);
		response = getSmartPointProcessorBCL().processAlarmWarningNotification(request);
		assertResultResponse(response);

		// Status Maintenance
		request.setLightStatusEnum(LightStatusEnum.MAINTENANCE);
		response = getSmartPointProcessorBCL().processAlarmWarningNotification(request);
		assertResultResponse(response);

		// Status Deactivated
		request.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
		response = getSmartPointProcessorBCL().processAlarmWarningNotification(request);
		assertResultResponse(response);
	}

	/**
	 * Test process channel setup audit notification.
	 */
	@Test
	public void testProcessChannelSetupAuditNotification()
	{
		// Success situation
		// Test Light Active
		ChannelSetupAuditRequest request = TestBaseUtil.createChannelSetupAuditRequest();
		Light light = TestBaseUtil.createLight();
		request.setLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		InternalResponse response = getSmartPointProcessorBCL().processChannelSetupAuditNotification(request);
		assertResponse(response);

		// Test Light DEACTIVATED
		request.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
		response = getSmartPointProcessorBCL().processChannelSetupAuditNotification(request);
		assertResponse(response);

		// Error situation
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, UPDATE_LIGHT_STATE);
		response = getSmartPointProcessorBCL().processChannelSetupAuditNotification(request);
		assertEquals("Should get Error", response.getStatus(), Status.ExceptionError);
	}

	/**
	 * Test process apply smartpoint properties notification.
	 */
	@Test
	public void testProcessApplySmartpointPropertiesNotification()
	{
		//Test coverage
		ProcessLightsRequest request = TestBaseUtil.createProcessLightsRequest();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class);

		List<Light> lightList = new ArrayList<Light>();

		Light light = TestBaseUtil.createLight();
		lightList.add(light);
		request.setLights(lightList);

		request.getFirstLight().setCurrentStatusMessage(TestBaseUtil.createStatusMessage());

		InternalResponse response =	getSmartPointProcessorBCL().processApplySmartpointPropertiesNotification(request);
		assertEquals("Should get Success.", response.getStatus(), Status.SystemError);

		//Test coverage
		resetMocksToSmartPointArea();
		request = TestBaseUtil.createProcessLightsRequest();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class);

		lightList = new ArrayList<Light>();

		light = TestBaseUtil.createLight();
		lightList.add(light);
		request.setLights(lightList);

		light.getLightLocation().setLatitude(0.00);
		light.getLightLocation().setLongitude(0.00);
		request.getFirstLight().setCurrentStatusMessage(TestBaseUtil.createStatusMessage());

		response =	getSmartPointProcessorBCL().processApplySmartpointPropertiesNotification(request);

		//Test Success
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.SUCCESS, ISmartPointDAC.class);
		light = TestBaseUtil.createLight();
		light.setCurrentStatusMessage(TestBaseUtil.createStatusMessage());
		request.setLights(Arrays.asList(light));

		response =
				getSmartPointProcessorBCL().processApplySmartpointPropertiesNotification(request);
		assertEquals("Should get Success", response.getStatus(), Status.OperationSuccess);
	}

	/**
	 * Test set light intensity notification.
	 */
	@Test
	public void testSetLightIntensityNotification()
	{
		// Success situation
		ProcessLightsRequest request = TestBaseUtil.createProcessLightsRequest();

		Light light = TestBaseUtil.createLight();
		light.setLightStateEnum(LightStateEnum.ON);
		light.setLightBlinkEnum(LightBlinkEnum.NONE);

		request.getLights().add(light);

		InternalResponse response = getSmartPointProcessorBCL().processSetLightIntensityNotification(request);
		assertResponse(response);

		light.setLightBlinkEnum(LightBlinkEnum.FAST);
		request.getLights().add(light);
		response = getSmartPointProcessorBCL().processSetLightIntensityNotification(request);
		assertResponse(response);

		// Error situation
		resetMocksToSmartPointArea();
		light.setLightBlinkEnum(LightBlinkEnum.SLOW);
		request.getLights().add(light);
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, UPDATE_LIGHT_STATE);
		response = getSmartPointProcessorBCL().processSetLightIntensityNotification(request);
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();
		light.setLightBlinkEnum(LightBlinkEnum.SLOW);
		request.getLights().add(light);
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, "upsertLightProperty");
		response = getSmartPointProcessorBCL().processSetLightIntensityNotification(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test process light binding notification.
	 */
	@Test
	public void testProcessLightBindingNotification()
	{
		// Success situation
		LightStatusNotificationRequest request = TestBaseUtil.createLightStatusNotificationRequest();
		request.setLight(TestBaseUtil.createLight());
		request.setAction("NotProtect");

		InternalResponse response = getSmartPointProcessorBCL().processLightBindingNotification(request);
		assertResponse(response);

		response = getSmartPointProcessorBCL().processLightBindingNotification(request);
		assertResponse(response);

		// Error situation
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class);
		request.setTenant(TestBaseUtil.createTenant());
		request.setLight(TestBaseUtil.createLight());
		response = getSmartPointProcessorBCL().processLightBindingNotification(request);
		assertMessages(response, SENSUS_MLC_SMARTPOINTBCLIMPL_ADD_LIGHT_FAILED);

		// Test Coverage
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.VALIDATION, ISmartPointDAC.class);
		request.setLight(TestBaseUtil.createLight());
		response = getSmartPointProcessorBCL().processLightBindingNotification(request);
	}

	/**
	 * Test initiate get light status.
	 */
	@Test
	public void testInitiateGetLightStatus()
	{
		// Success situation
		LightRequest lightRequest = TestBaseUtil.createLightRequest();
		Light light = TestBaseUtil.createLight();

		lightRequest.addLight(light);
		lightRequest.setPercentage(ARBITRARY_PERCENTAGE_10);

		InternalResultsResponse<Process> response =
				getSmartPointProcessorBCL().initiateGetLightStatus(lightRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getSmartPointProcessorBCL().initiateGetLightStatus(lightRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test initiate update light intensity.
	 */
	@Test
	public void testInitiateUpdateLightIntensity()
	{
		// Success situation
		Light light = getDummyLight(1, 1, 1);

		// Set light intensity
		LightRequest lightRequest =
				getDummyLightRequestIntensity(light, ARBITRARY_PERCENTAGE_50, LightBlinkEnum.NONE,
						OverrideEnum.SCHEDULED, null, false);
		InternalResultsResponse<Process> response =
				getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
		assertResultResponse(response);

		// Set light intensity OFF
		lightRequest =
				getDummyLightRequestIntensity(light, 0, LightBlinkEnum.NONE, OverrideEnum.SCHEDULED, null, false);
		response = getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
		assertResultResponse(response);

		// Set light Blink Fast
		lightRequest =
				getDummyLightRequestIntensity(light, ARBITRARY_PERCENTAGE_100, LightBlinkEnum.FAST,
						OverrideEnum.PER_DATE,
						getNewDateUTC(),
						false);
		response = getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
		assertResultResponse(response);

		// Set light Blink Slow
		lightRequest =
				getDummyLightRequestIntensity(light, ARBITRARY_PERCENTAGE_100, LightBlinkEnum.SLOW,
						OverrideEnum.PER_DATE,
						getNewDateUTC(),
						false);
		response = getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
		assertResultResponse(response);

		// Set light intensity 100%
		lightRequest =
				getDummyLightRequestIntensity(light, ARBITRARY_PERCENTAGE_100, LightBlinkEnum.NONE,
						OverrideEnum.PER_DATE,
						getNewDateUTC(),
						false);
		response = getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
		assertResultResponse(response);

		// Clear Override
		lightRequest =
				getDummyLightRequestIntensity(light, null, LightBlinkEnum.NONE, OverrideEnum.NONE, null, true);
		response = getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test initiate delete alert.
	 */
	@Test
	public void testInitiateDeleteAlert()
	{
		// Success situation
		LightRequest lightRequest = TestBaseUtil.createLightRequest();
		Light light = TestBaseUtil.createLight();

		StatusMessage statusMessage = TestBaseUtil.createStatusMessage();

		StatusException statusException = new StatusException();
		statusException.setStatusExceptionTypeEnumValue(StatusExceptionTypeEnum.POWER_FAILURE.getValue());

		List<StatusException> statusExceptions = new ArrayList<StatusException>();
		statusExceptions.add(statusException);
		statusMessage.setStatusExceptions(statusExceptions);

		light.setCurrentStatusMessage(statusMessage);

		lightRequest.addLight(light);

		InternalResultsResponse<Process> response = getSmartPointProcessorBCL().initiateDeleteAlert(lightRequest);
		assertResultResponse(response);

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getSmartPointProcessorBCL().initiateDeleteAlert(lightRequest);
		assertMessages(response, ERROR_CODE, SENSUS_MLC_MLC_ACTION_LIGHT_STATUS_ALREADY_ACTIVE);
	}

	/**
	 * Test initiate upsert light property.
	 */
	@Test
	public void testInitiateUpsertLightProperty()
	{
		// Success situation
		// Update Pole Id / Latitude / Longitude
		Light light = TestBaseUtil.createLight();
		light.setPoleId(ONE);

		LightRequest lightRequest = TestBaseUtil.createLightRequest();
		lightRequest.addLight(light);

		InternalResponse response = getSmartPointProcessorBCL().initiateUpsertLightProperty(lightRequest);
		assertResponse(response);

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getSmartPointProcessorBCL().initiateUpsertLightProperty(lightRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test initiate setup dimming configuration.
	 */
	@Test
	public void testInitiateSetupDimmingConfiguration()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResponse response = getSmartPointProcessorBCL().initiateSetupDimmingConfiguration(request);
		assertResponse(response);

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		response = getSmartPointProcessorBCL().initiateSetupDimmingConfiguration(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test initiate update light status.
	 */
	@Test
	public void testInitiateUpdateLightStatus()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest();
		Light light = TestBaseUtil.createLight();
		request.addLight(light);
		request.setLightStatusEnum(LightStatusEnum.ACTIVE);
		InternalResponse response = getSmartPointProcessorBCL().initiateUpdateLightStatus(request);
		assertResponse(response);

		request.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
		response = getSmartPointProcessorBCL().initiateUpdateLightStatus(request);
		assertResponse(response);

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointProcessorBCL(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class);
		response = getSmartPointProcessorBCL().initiateUpdateLightStatus(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test process clear alarm notification.
	 */
	@Test
	public void testProcessClearAlarmNotification()
	{
		// Success situation
		AlarmNotificationRequest request = TestBaseUtil.createAlarmNotificationRequest();
		request.setLight(TestBaseUtil.createLight());
		InternalResponse response = getSmartPointProcessorBCL().processClearAlarmNotification(request);
		assertResponse(response);

		request = TestBaseUtil.createAlarmNotificationRequest();
		request.setLight(TestBaseUtil.createLight());
		request.setStatusException(Arrays.asList(new StatusException(StatusExceptionTypeEnum.POWER_FAILURE)));
		response = getSmartPointProcessorBCL().processClearAlarmNotification(request);
		assertResponse(response);

		// Error situation
		request.getLight().setId(null);
		response = getSmartPointProcessorBCL().processClearAlarmNotification(request);
		assertEquals("Should get Persistence Error", response.getStatus(), Status.PersistenceError);
	}

	/**
	 * Gets the dummy tenant.
	 *
	 * @param rniCode the rni code
	 * @return the dummy tenant
	 */
	private Tenant getDummyTenant(String rniCode)
	{
		Tenant result = new Tenant();
		result.setRniCode(rniCode);
		result.setId(1);
		return result;
	}

	/**
	 * Prepare parameters.
	 *
	 * @return the list
	 */
	private List<LightParameter> prepareParameters()
	{
		List<LightParameter> params = new ArrayList<LightParameter>();
		params.add(new LightParameter(ARBITRARY_LIGHT_PARAMETER_ID_5, "Value1"));
		params.add(new LightParameter(ARBITRARY_LIGHT_PARAMETER_ID_10, "Value2"));
		params.add(new LightParameter(ARBITRARY_LIGHT_PARAMETER_ID_18, "Value18"));
		return params;
	}

	/**
	 * Prepare operational data.
	 *
	 * @return the list
	 */
	private List<OperationalData> prepareOperationalData()
	{
		List<OperationalData> data = new ArrayList<OperationalData>();

		data.add(new OperationalData(ARBITRARY_OPERATIONAL_DATA_VALUE_200,
				OperationalDataTypeEnum.CUMULATIVECONSUMPTION));
		data.add(new OperationalData(ARBITRARY_OPERATIONAL_DATA_VALUE_120, OperationalDataTypeEnum.VOLTAGE));
		data.add(new OperationalData(ARBITRARY_OPERATIONAL_DATA_VALUE_30, OperationalDataTypeEnum.CURRENT));

		return data;
	}

	/**
	 * Gets the dummy user context.
	 *
	 * @return the dummy user context
	 */
	private UserContext getDummyUserContext()
	{
		UserContext result = new UserContext();
		result.setId(1);
		result.setUserId("QAT");
		return result;
	}

	/**
	 * Gets the dummy light request intensity.
	 *
	 * @param light the light
	 * @param percentage the percentage
	 * @param lightBlinkEnum the light blink enum
	 * @param overrideEnum the override enum
	 * @param overridePerDate the override per date
	 * @param isClearOverride the is clear override
	 * @return the dummy light request intensity
	 */
	private LightRequest getDummyLightRequestIntensity(Light light, Integer percentage, LightBlinkEnum lightBlinkEnum,
			OverrideEnum overrideEnum, Date overridePerDate, Boolean isClearOverride)
	{
		LightRequest lightRequest = new LightRequest(getDummyUserContext());
		lightRequest.addLight(light);
		lightRequest.setTenant(getDummyTenantIntensity(ONE, "ACME"));
		lightRequest.setPercentage(percentage);
		lightRequest.setLightBlinkEnum(lightBlinkEnum);
		lightRequest.setOverrideEnum(overrideEnum);
		lightRequest.setOverridePerDate(overridePerDate);
		lightRequest.setIsClearOverride(isClearOverride);
		return lightRequest;
	}

	/**
	 * Gets the dummy light.
	 *
	 * @param rniId the rni id
	 * @param id the id
	 * @param smartPointId the smart point id
	 * @return the dummy light
	 */
	private Light getDummyLight(Integer rniId, Integer id, Integer smartPointId)
	{
		Light light = new Light();
		light.setId(id);
		light.setSmartPointId(smartPointId);
		light.setRniId(rniId);
		return light;
	}

	/**
	 * Gets the dummy tenant intensity.
	 *
	 * @param rniCode the rni code
	 * @param name the name
	 * @return the dummy tenant intensity
	 */
	private Tenant getDummyTenantIntensity(String rniCode, String name)
	{
		Tenant tenant = new Tenant();
		UserContext userContext = new UserContext();
		userContext.setId(1);
		tenant.setRniCode(rniCode);
		tenant.setName(name);
		tenant.setGatewayURL("http://localhost:8083/gateway-mlc/mlc-gateway-ws/");
		tenant.setId(1);
		return tenant;
	}
}