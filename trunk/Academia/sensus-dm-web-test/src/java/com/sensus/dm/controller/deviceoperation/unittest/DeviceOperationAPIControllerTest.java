package com.sensus.dm.controller.deviceoperation.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.deviceoperation.DeviceOperationAPIController;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.schedule.unittest.ScheduleBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class DeviceOperationAPIControllerTest.
 */
public class DeviceOperationAPIControllerTest extends AbstractTestBase
{

	/** The Constant API_DEVICEOP_APPLY. */
	private static final String API_DEVICEOP_APPLY = "/api/deviceop/apply";

	/** The Constant FETCH_METER_LOAD_PROFILE. */
	private static final String FETCH_METER_LOAD_PROFILE = "/api/deviceop/fetchMeterLoadProfile";

	/** The Constant FETCH_INTERVAL_READ. */
	private static final String FETCH_INTERVAL_READ = "/api/deviceop/fetchIntervalRead";

	/** The Constant FETCH_SNAPSHOT. */
	private static final String FETCH_SNAPSHOT = "/api/deviceop/fetchSnapshot";

	/** The Constant FETCH_TOU_READ. */
	private static final String FETCH_TOU_READ = "/api/deviceop/fetchTouRead";

	/** The Constant FETCH_METER_DEMAND_READS. */
	private static final String FETCH_METER_DEMAND_READS = "/api/deviceop/fetchMeterDemandReads";

	/** The Constant FETCH_DATA_READ. */
	private static final String FETCH_DATA_READ = "/api/deviceop/fetchDataRead";

	/** The Constant MESSAGE_INFO_CODE. */
	private static final String MESSAGE_INFO_CODE = "$.messageInfoList[0].code";

	/** The Constant OPERATION_SUCCESS. */
	private static final String OPERATION_SUCCESS = "$.operationSuccess";

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceOperationAPIController.class)
				.getScheduleBCF();
	}

	/**
	 * Gets the device reading mock.
	 * 
	 * @return the device reading mock
	 */
	private DeviceReadingBCFMockImpl getDeviceReadingMock()
	{
		return (DeviceReadingBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceOperationAPIController.class)
				.getDeviceReadingBCF();
	}

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DeviceOperationAPIController.class)
				.getElectricMeterBCF();
	}

	/**
	 * Apply.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void apply() throws Exception
	{
		// ====================================sensus.dm.action.demand.response====================================

		setData("{\"parameters\":{\"demandResponseDuration\":1439,\"enrollmentCode\":\"1\",\"heating\":1,\"cooling\":1,\"dutyCycleRate\":1,\"loadAdjustment\":1,\"criticalityLevel\":3,\"randomizeStart\":true,\"randomizeEnd\":true,\"dateTimeString\":\"02/28/2014 4:25pm\",\"deviceClasses\":[\"IndustrialLoads\",\"SimpleMiscLoads\",\"ExteriorLighting\"],\"actionType\":{\"id\":4},\"actionTypeName\":\"sensus.dm.action.demand.response\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"lifecycleStateEnum\":\"INSTALLED\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.setup.demand.response====================================

		setData("{\"parameters\":{\"actionType\":{\"id\":7},\"actionTypeName\":\"sensus.dm.action.setup.demand.response\",\"actionTime\":\"2013-08-13T13:03:56.318Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"50410022\",\"deviceType\":\"LCM\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"50410022\",\"specificDeviceTypeEnumValue\":2,\"deviceId\":\"2198\",\"lifecycleStateEnum\":\"\"}],\"enrollmentCode\":\"1\",\"startMinutes\":2,\"endMinutes\":3},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);

		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// Success Situation with Relay List
		setData("{\"parameters\":{\"demandResponseDuration\":1000,\"enrollmentCode\":\"0\",\"heating\":null,\"cooling\":null,\"dutyCycleRate\":null,\"loadAdjustment\":null,\"criticalityLevel\":1,\"randomizeStart\":false,\"randomizeEnd\":false,\"dateTimeString\":\"04/25/2013 3:56pm\",\"deviceClasses\":[],\"actionType\":{\"id\":4},\"actionTypeName\":\"sensus.dm.action.setup.demand.response\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"10000001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"10000001M\",\"deviceType\":\"ELECTRIC_METER\"}],\"lcmRelaysList\":[{\"relay\":1,\"tamperDetectTimeout\":10}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// ====================================sensus.dm.action.send.han.text.message====================================

		setData("{\"parameters\":{\"textMessage\":\"Test for JUnits.\",\"durationHANTextMessage\":15,\"dateTimeString\":\"04/25/2020 2:41pm\",\"actionType\":{\"id\":6},\"actionTypeName\":\"sensus.dm.action.send.han.text.message\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"2153943262073699\",\"deviceType\":\"HAN_DEVICE\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"46722565\",\"specificDeviceTypeEnumValue\":0,\"deviceId\":\"PCT 9363\",\"deviceType\":\"HAN_DEVICE\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]} ");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.remote.connect======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":2},\"actionTypeName\":\"sensus.dm.action.remote.connect\",\"actionTime\":\"2013-04-24T20:45:32.919Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"deviceType\":\"ELECTRIC_METER\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.remote.disconnect======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":3},\"actionTypeName\":\"sensus.dm.action.remote.disconnect\",\"actionTime\":\"2013-04-24T20:47:37.847Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"deviceType\":\"ELECTRIC_METER\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]} ");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.remote.arm.connect======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":17},\"actionTypeName\":\"sensus.dm.action.remote.arm.connect\",\"actionTime\":\"2013-04-24T20:48:58.959Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"deviceType\":\"ELECTRIC_METER\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.delete.han.device======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":1},\"actionTypeName\":\"sensus.dm.action.delete.han.device\",\"onDemand\":true,\"isMonitored\":true,\"actionTime\":\"2013-01-25T17:54:24.450Z\",\"securityTokenType\":\"zigbee_install_code\",\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":{\"type\":\"Meter\",\"id\":1},\"customerId\":\"ACME\",\"baseFlexNetId\":\"\",\"deviceId\":\"1010M\",\"deviceType\":\"ELECTRIC_METER\"}]}}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.demand.read======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":19},\"actionTypeName\":\"sensus.dm.action.demand.read\",\"actionTime\":\"2013-04-25T17:24:54.635Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"16012024\",\"deviceType\":\"WATER_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":47,\"deviceId\":\"16012024\",\"deviceType\":\"WATER_METER\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.get.demand.response.event.status======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":8},\"actionTypeName\":\"sensus.dm.action.get.demand.response.event.status\",\"actionTime\":\"2013-04-25T18:47:09.513Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"2153943262073701\",\"deviceType\":\"LCM\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"0\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"LCM 9365\",\"deviceType\":\"LCM\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.get.tamper.detect.timer====================================

		setData("{\"parameters\":{\"actionTypeName\":\"sensus.dm.action.get.tamper.detect.timer\",\"onDemand\":true,\"isMonitored\":true,\"lcmRelaysList\":[{\"relay\":1},{\"relay\":2}],\"devices\":[{\"flexNetId\":\"50410022\",\"deviceType\":\"LCM\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"50410022\",\"specificDeviceTypeEnumValue\":2,\"deviceId\":\"2198\",\"lifecycleStateEnum\":\"\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]} ");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.set.tamper.detect.timer====================================

		setData("{\"parameters\":{\"actionType\":{\"id\":23},\"actionTypeName\":\"sensus.dm.action.set.tamper.detect.timer\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"2153943262073699\",\"deviceType\":\"HAN_DEVICE\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"46722565\",\"specificDeviceTypeEnumValue\":0,\"deviceId\":\"PCT 9363\",\"deviceType\":\"HAN_DEVICE\"}],\"lcmRelaysList\":[{\"relay\":1,\"enrollmentCode\":10,\"deviceClass\":\"HVAC_COMPRESSOR\",\"startMinutes\":10,\"endMinutes\":10}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]} ");

		// Success Situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getScheduleMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ============================================================================================================
	}

	/**
	 * Fetch meter load profile.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchMeterLoadProfile() throws Exception
	{
		setData("{\"endDate\":\"2014-04-11T23:00:00.000Z\"}");
		// Success Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_METER_LOAD_PROFILE).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_METER_LOAD_PROFILE).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_METER_LOAD_PROFILE).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch interval read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchIntervalRead() throws Exception
	{

		setData("{\"endDate\":\"2014-04-11T23:00:00.000Z\"}");
		// Success Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_INTERVAL_READ).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_INTERVAL_READ).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_INTERVAL_READ).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch meter demand reads.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchMeterDemandReads() throws Exception
	{
		setData("{}");
		// Success Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_METER_DEMAND_READS).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_METER_DEMAND_READS).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_METER_DEMAND_READS).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch snapshot.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchSnapshot() throws Exception
	{
		setData("{\"endDate\":\"2014-04-11T23:00:00.000Z\"}");
		// Success Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_SNAPSHOT).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_SNAPSHOT).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_SNAPSHOT).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch tou.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchTou() throws Exception
	{

		setData("{\"endDate\":\"2014-04-11T23:00:00.000Z\"}");
		// Success Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_TOU_READ).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_TOU_READ).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_TOU_READ).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Fetch data read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void fetchDataRead() throws Exception
	{

		setData("{\"timeZone\":\"-4\",\"dateFormat\":\"MM/dd/yyyy\",\"isMonitored\":true,\"device\":{\"deviceId\":\"B100926220128\",\"radio\":{\"flexNetId\":13573176,\"customerId\":\"ACME\",\"location\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"address\":null,\"city\":null,\"country\":null,\"latitude\":0,\"longitude\":0,\"state\":null,\"zip\":null,\"timeZoneInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"timeZone\":null,\"displayName\":\"America/New_York\",\"displayNameGMT\":null,\"displayNameShort\":null}}},\"deviceType\":\"WATER_METER\"},\"initialDate\":\"2013-04-25T03:00:00.000Z\",\"endDate\":\"2013-04-25T03:00:00.000Z\"}");
		// Success Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_DATA_READ).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(FETCH_DATA_READ).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_DATA_READ).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
