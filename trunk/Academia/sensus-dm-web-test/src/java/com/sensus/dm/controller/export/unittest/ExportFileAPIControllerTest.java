package com.sensus.dm.controller.export.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.deviceoperation.unittest.DeviceReadingBCFMockImpl;
import com.sensus.dm.controller.electricmeter.unittest.ElectricMeterBCFMockImpl;
import com.sensus.dm.controller.export.ExportFileAPIController;
import com.sensus.dm.controller.gasmeter.unittest.GasMeterBCFMockImpl;
import com.sensus.dm.controller.group.unittest.GroupBCFMockImpl;
import com.sensus.dm.controller.schedule.unittest.ScheduleBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;
import com.sensus.dm.controller.watermeter.unittest.WaterMeterBCFMockImpl;

/**
 * The Class ExportFileAPIControllerTest.
 */
public class ExportFileAPIControllerTest extends AbstractTestBase
{

	/** The Constant GENERATE_GROUP_CSV. */
	private static final String GENERATE_GROUP_CSV = "/api/export/generateGroupCSV";

	/** The Constant INSERT_CSV_PROCESS. */
	private static final String INSERT_CSV_PROCESS = "/api/export/insertCsvProcess";

	/** The Constant GENERATE_SUMMARY_CSV. */
	private static final String GENERATE_SUMMARY_CSV = "/api/export/generateSummaryCSV";

	/** The Constant GENERATE_COMMUNICATION_SUMMARY_CSV. */
	private static final String GENERATE_COMMUNICATION_SUMMARY_CSV = "/api/export/generateComunicationSummaryCSV";

	/** The Constant GENERATE_DEMAND_RESPONSE_SUMMARY_CSV. */
	private static final String GENERATE_DEMAND_RESPONSE_SUMMARY_CSV = "/api/export/generateDemandResponseCSV";

	/** The Constant GENERATE_IMPORT_HAN_SUMMARY_CSV. */
	private static final String GENERATE_IMPORT_HAN_SUMMARY_CSV = "/api/export/generateImportHanSummaryCSV";

	/** The Constant GENERATE_TODAY_CSV. */
	private static final String GENERATE_TODAY_CSV = "/api/export/generateTodayCSV";

	/** The Constant GENERATE_HISTORY_CSV. */
	private static final String GENERATE_HISTORY_CSV = "/api/export/generateHistoryCSV";

	/** The Constant GENERATE_DEVICE_HISTORY_CSV. */
	private static final String GENERATE_DEVICE_HISTORY_CSV = "/api/export/generateDeviceHistoryCSV";

	/** The Constant GENERATE_DEVICES_CSV. */
	private static final String GENERATE_DEVICES_CSV = "/api/export/generateDevicesCSV";

	/** The Constant GENERATE_WATER_DEVICES_CSV. */
	private static final String GENERATE_WATER_DEVICES_CSV = "/api/export/generateWaterDevicesCSV";

	/** The Constant GENERATE_GAS_DEVICES_CSV. */
	private static final String GENERATE_GAS_DEVICES_CSV = "/api/export/generateGasDevicesCSV";

	/** The Constant GENERATE_DEMAND_READ_DETAIL_CSV. */
	private static final String GENERATE_DEMAND_READ_DETAIL_CSV = "/api/export/generateDemandReadDetailCSV";

	/** The Constant GENERATE_FILE_CSV_READ_DATA. */
	private static final String GENERATE_FILE_CSV_READ_DATA = "/api/export/generateFileCsvReadData";

	/** The Constant GENERATE_SCHEDULE_CSV. */
	private static final String GENERATE_SCHEDULE_CSV = "/api/export/generateScheduleCSV";

	/** The Constant GENERATE_SCHEDULE_DEVICE_CSV. */
	private static final String GENERATE_SCHEDULE_DEVICE_CSV = "/api/export/generateScheduleDeviceCSV";

	/** The Constant GENERATE_FILE_CSV_INTERVAL_READ. */
	private static final String GENERATE_FILE_CSV_INTERVAL_READ = "/api/export/generateFileCsvIntervalRead";

	/** The Constant GENERATE_FILE_CSV_SNAPSHOT. */
	private static final String GENERATE_FILE_CSV_SNAPSHOT = "/api/export/generateFileCsvSnapshot";

	/** The Constant GENERATE_FILE_CSV_TOU_READ. */
	private static final String GENERATE_FILE_CSV_TOU_READ = "/api/export/generateFileCsvTouRead";

	/** The Constant GENERATE_DEMAND_READ_CSV. */
	private static final String GENERATE_DEMAND_READ_CSV = "/api/export/generateDemandReadCSV";

	/**
	 * Gets the process csv mock.
	 * 
	 * @return the process csv mock
	 */
	private ProcessCsvBCFMockImpl getProcessCsvMock()
	{
		return (ProcessCsvBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getProcessCsvBCF();
	}

	/**
	 * Gets the group mock.
	 * 
	 * @return the group mock
	 */
	private GroupBCFMockImpl getGroupMock()
	{
		return (GroupBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getGroupBCF();
	}

	/**
	 * Gets the schedule mock.
	 * 
	 * @return the schedule mock
	 */
	private ScheduleBCFMockImpl getScheduleMock()
	{
		return (ScheduleBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getScheduleBCF();
	}

	/**
	 * Gets the device reading mock.
	 * 
	 * @return the device reading mock
	 */
	private DeviceReadingBCFMockImpl getDeviceReadingMock()
	{
		return (DeviceReadingBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getDeviceReadingBCF();
	}

	/**
	 * Gets the electric meter mock.
	 * 
	 * @return the electric meter mock
	 */
	private ElectricMeterBCFMockImpl getElectricMeterMock()
	{
		return (ElectricMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getElectricMeterBCF();
	}

	/**
	 * Gets the water meter mock.
	 * 
	 * @return the water meter mock
	 */
	private WaterMeterBCFMockImpl getWaterMeterMock()
	{
		return (WaterMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getWaterMeterBCF();
	}

	/**
	 * Gets the gas meter mock.
	 * 
	 * @return the gas meter mock
	 */
	private GasMeterBCFMockImpl getGasMeterMock()
	{
		return (GasMeterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getGasMeterBCF();
	}

	/**
	 * Generate groups csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateGroupsCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"sortExpressions\":[{\"field\":\"NAME\",\"direction\":\"Ascending\"}],"
				+ "\"deviceSearch\":{\"groupTypes\":null,\"searchText\":null},\"processId\":1361,\"fileName\":"
				+ "\"C:/AppLogs/csv/rod2013-02-05 06.28.07.068.csv\"}");

		// Success situation
		getGroupMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_GROUP_CSV).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getGroupMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_GROUP_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGroupMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_GROUP_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate devices csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateDevicesCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"sortExpressions\":[{\"field\":\"lifecycle_state\","
				+ "\"direction\":\"Ascending\"}],\"deviceSearch\":{\"searchText\":null},\"groups\":null,\"tags\":null,"
				+ "\"deviceType\":\"ELECTRIC_METER\",\"listColumn\":[\"DEVICE_ID\",\"NETWORK_ADDRESS\",\"DESCRIPTION\","
				+ "\"DEVICE_TYPE\",\"INSTALL_DATE\",\"LIFECYCLE_STATE\"],\"fileName\":\"/AppLogs/csv/rod2013-02-0506.28.07.068.csv\"}");

		// Success situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_DEVICES_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_DEVICES_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_DEVICES_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate water devices csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateWaterDevicesCSV() throws Exception
	{
		setData("{\"timeZone\":\"-4\",\"dateFormat\":\"dd/MM/yyyy\",\"sortExpressions\":[{\"field\":\"COALESCE(alarm, \'ZZZ\')\","
				+ "\"direction\":\"Ascending\"},{\"field\":\"alarm_time\",\"direction\":\"Ascending\"}],\"deviceSearch\":{\"waterMeterSearch\":{"
				+ "\"waterMeter\":{\"radio\":{},\"configuration\":{\"firmwareMeter\":null}},\"waterGasMeterStatusEnumList\":null,"
				+ "\"alarmEnumList\":null},\"groups\":null,\"tags\":null,\"processId\":null,\"startDate\":null,\"endDate\":null},"
				+ "\"listColumn\":[\"DEVICE_ID\",\"NETWORK_ADDRESS\",\"LAST_HEARD\",\"STATUS\",\"INSTALL_DATE\",\"ALARM\"],"
				+ "\"deviceType\":\"WATER_METER\",\"processId\":4417,\"fileName\":\"/AppLogs/csv/rod2013-02-0506.28.07.068.csv\"}");

		// Success situation
		getWaterMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_WATER_DEVICES_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getWaterMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_WATER_DEVICES_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getWaterMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_WATER_DEVICES_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate gas devices csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateGasDevicesCSV() throws Exception
	{
		setData("{\"timeZone\":\"-4\",\"dateFormat\":\"dd/MM/yyyy\",\"sortExpressions\":[{\"field\":\"COALESCE(alarm, 'ZZZ')\","
				+ "\"direction\":\"Ascending\"},{\"field\":\"alarm_time\",\"direction\":\"Ascending\"}],"
				+ "\"listColumn\":[\"DEVICE_ID\",\"NETWORK_ADDRESS\",\"LAST_HEARD\",\"STATUS\",\"INSTALL_DATE\",\"ALARM\"],"
				+ "\"deviceType\":\"GAS_METER\",\"processId\":4410,\"fileName\":\"/AppLogs/csv/rod2013-02-0506.28.07.068.csv\"}");

		// Success situation
		getGasMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_GAS_DEVICES_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getGasMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_GAS_DEVICES_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getGasMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_GAS_DEVICES_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate demand read detail csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateDemandReadDetailCSV() throws Exception
	{
		setData("{\"timeZone\":\"-4\",\"dateFormat\":\"dd/MM/yyyy\",\"device\":{\"deviceId\":\"1001M\",\"radio\":"
				+ "{\"flexNetId\":1001,\"customerId\":\"ACME\",\"location\":{\"modelAction\":\"NONE\",\"createUser\":null,"
				+ "\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"address\":\"121 ACME Street\",\"city\":"
				+ "\"ACME City\",\"country\":\"USA\",\"latitude\":32.7984,\"longitude\":-116.7897,\"state\":\"CA\",\"zip\":\"92014\","
				+ "\"timeZoneInfo\":{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,"
				+ "\"modifyDate\":null,\"timeZone\":null,\"displayName\":\"America/New_York\",\"displayNameGMT\":null,"
				+ "\"displayNameShort\":null}}},\"deviceType\":\"ELECTRIC_METER\"},\"initialDate\":\"2012-12-31T20:00:00.000Z\","
				+ "\"endDate\":\"2013-12-30T20:00:00.000Z\",\"processId\":4406,\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.27.07.412.csv\"}");

		// Success situation
		getElectricMeterMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_DEMAND_READ_DETAIL_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getElectricMeterMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_DEMAND_READ_DETAIL_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getElectricMeterMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_DEMAND_READ_DETAIL_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csv read data.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVReadData() throws Exception
	{
		setData("{\"timeZone\":\"-4\",\"dateFormat\":\"dd/MM/yyyy\",\"isMonitored\":true,\"device\":"
				+ "{\"deviceId\":\"234561274M\",\"radio\":{\"flexNetId\":234561274,\"customerId\":\"ACME\",\"location\":"
				+ "{\"modelAction\":\"NONE\",\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,"
				+ "\"address\":\"121 ACME Street\",\"city\":\"ACME City\",\"country\":\"USA\",\"latitude\":31.1479,"
				+ "\"longitude\":-115.2589,\"state\":\"CA\",\"zip\":\"92014\",\"timeZoneInfo\":{\"modelAction\":\"NONE\","
				+ "\"createUser\":null,\"modifyUser\":null,\"createDate\":null,\"modifyDate\":null,\"timeZone\":null,"
				+ "\"displayName\":\"America/New_York\",\"displayNameGMT\":null,\"displayNameShort\":null}}},"
				+ "\"deviceType\":\"WATER_METER\"},\"initialDate\":\"2015-04-01T03:00:00.000Z\",\"endDate\":\"2015-04-01T03:00:00.000Z\","
				+ "\"processId\":4409,\"fileName\":\"/AppLogs/csv/rod2013-02-0506.28.07.068.csv\"}");

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_FILE_CSV_READ_DATA).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_FILE_CSV_READ_DATA).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_FILE_CSV_READ_DATA).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csv demand read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVDemandRead() throws Exception
	{
		setData("{}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_DEMAND_READ_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_DEMAND_READ_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_DEMAND_READ_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate schedule csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateScheduleCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy hh:mm a\",\"sortExpressions\":[{\"field\":\"start_time\","
				+ "\"direction\":\"Ascending\"}],\"baseSearch\":{\"searchText\":null,\"users\":null,\"startDate\":"
				+ "\"2013-02-06T04:59:59.471Z\",\"endDate\":\"2013-02-05T05:00:00.471Z\",\"processCategories\":null},"
				+ "\"frequencies\":null,\"scheduleStatusEnums\":null,\"processId\":1374,\"fileName\":\"/AppLogs/csv/rod2013-02-0506.28.07.068.csv\"}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_SCHEDULE_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_SCHEDULE_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_SCHEDULE_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate schedule device csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateScheduleDeviceCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"schedule\":{\"id\":3},\"processId\":1176," +
				"\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.21.24.903.csv\"}");

		// Success situation
		getScheduleMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_SCHEDULE_DEVICE_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getScheduleMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_SCHEDULE_DEVICE_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getScheduleMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_SCHEDULE_DEVICE_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate summary file csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateSummaryFileCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"processes\":[{\"id\":983}],\"processItemStatusEnum\":1,"
				+ "\"processId\":1178,\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.23.27.320.csv\"}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_SUMMARY_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csv communication summary.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVCommunicationSummary() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"processes\":[{\"id\":981}],\"processId\":1179," +
				"\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.24.13.759.csv\"}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_COMMUNICATION_SUMMARY_CSV)
				.andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_COMMUNICATION_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_COMMUNICATION_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csv demand response summary.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVDemandResponseSummary() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"processes\":[{\"id\":961}],\"processId\":1180," +
				"\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.24.59.607.csv\"}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_DEMAND_RESPONSE_SUMMARY_CSV).andExpect(
				jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_DEMAND_RESPONSE_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_DEMAND_RESPONSE_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csv import han summary.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVImportHanSummary() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"processes\":[{\"id\":961}],\"processId\":1180," +
				"\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.24.59.607.csv\"}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_IMPORT_HAN_SUMMARY_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_IMPORT_HAN_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_IMPORT_HAN_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate today csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateTodayCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"sortExpressions\":[{\"field\":\"start_time\"," +
				"\"direction\":\"Descending\"}],\"processSearch\":{\"startDate\":\"2013-02-05T05:00:00.311Z\"," +
				"\"endDate\":\"2013-02-06T04:59:59.000Z\"},\"isToday\":true,\"processId\":1182,\"fileName\":" +
				"\"C:/AppLogs/csv/qat12013-02-05 15.27.07.412.csv\"}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_TODAY_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_TODAY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_TODAY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate event history csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateEventHistoryCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"sortExpressions\":[{\"field\":\"start_time\"," +
				"\"direction\":\"Descending\"}],\"processSearch\":{\"searchText\":null,\"users\":null,\"startDate\":" +
				"\"2013-02-04T05:00:00.000Z\",\"endDate\":\"2013-02-06T04:59:59.000Z\",\"processCategories\":null}," +
				"\"processId\":1183,\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.27.07.412.csv\"}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_HISTORY_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_HISTORY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_HISTORY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate device history csv.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateDeviceHistoryCSV() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"sortExpressions\":[{\"field\":\"start_time\"," +
				"\"direction\":\"Descending\"}],\"processSearch\":{\"searchText\":null,\"users\":null,\"startDate\":" +
				"\"2012-02-06T05:00:00.000Z\",\"endDate\":\"2013-02-06T04:59:59.000Z\",\"processCategories\":[{\"name" +
				"\":\"sensus.epm.process.category.group\"}],\"searchType\":null},\"devices\":[{}],\"processId\":1184," +
				"\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.27.07.412.csv\"}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_DEVICE_HISTORY_CSV).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_DEVICE_HISTORY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_DEVICE_HISTORY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csv interval read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVIntervalRead() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"isMonitored\":true," +
				"\"initialDate\":\"2013-02-05T02:00:00.000Z\",\"endDate\":\"2013-02-05T02:00:00.000Z\"," +
				"\"processId\":1186,\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.29.54.128.csv\"}");

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_FILE_CSV_INTERVAL_READ).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_FILE_CSV_INTERVAL_READ).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_FILE_CSV_INTERVAL_READ).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csvtou read.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVTOURead() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"isMonitored\":true," +
				"\"initialDate\":\"2013-02-05T02:00:00.000Z\",\"endDate\":\"2013-02-05T02:00:00.000Z\"," +
				"\"processId\":1185,\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.29.21.725.csv\"}");

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_FILE_CSV_TOU_READ).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_FILE_CSV_TOU_READ).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_FILE_CSV_TOU_READ).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Generate file csv snapshot.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void generateFileCSVSnapshot() throws Exception
	{
		setData("{\"timeZone\":\"-5\",\"dateFormat\":\"MM/dd/yyyy\",\"isMonitored\":true," +
				"\"initialDate\":\"2013-02-05T02:00:00.000Z\",\"endDate\":\"2013-02-05T02:00:00.000Z\"," +
				"\"processId\":1185,\"fileName\":\"C:/AppLogs/csv/qat12013-02-05 15.29.21.725.csv\"}");

		// Success situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_FILE_CSV_SNAPSHOT).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_FILE_CSV_SNAPSHOT).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getDeviceReadingMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_FILE_CSV_SNAPSHOT).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert csv process.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertCsvProcess() throws Exception
	{
		setData("{}");

		// Success situation
		getProcessCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_CSV_PROCESS).andExpect(jsonPath("$.operationSuccess", comparesEqualTo(true)));

		// Failure situation
		getProcessCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_CSV_PROCESS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_CSV_PROCESS).andExpect(
				jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
