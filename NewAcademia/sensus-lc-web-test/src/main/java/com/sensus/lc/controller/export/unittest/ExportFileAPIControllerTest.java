package com.sensus.lc.controller.export.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.lc.controller.analytics.unittest.AnalyticsBCFMockImpl;
import com.sensus.lc.controller.ecomode.unittest.EcoModeBCFMockImpl;
import com.sensus.lc.controller.export.ExportFileAPIController;
import com.sensus.lc.controller.light.unittest.LightCSVBCFMockImpl;
import com.sensus.lc.controller.process.unittest.ProcessBCFMockImpl;
import com.sensus.lc.controller.util.AbstractTestBase;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.controller.util.enums.TestMessageEnum;

public class ExportFileAPIControllerTest extends AbstractTestBase
{
	private static final String GENERATE_LIGHT_CSV = "/api/export/generateLightCSV";

	private static final String INSERT_CSV_PROCESS = "/api/export/insertCsvProcess";

	private static final String INSERT_CSV_ANALYTICS_PROCESS = "/api/export/generateAnalytics";

	public static final String GENERATE_PROCESS_CSV = "/api/export/generateProcessCSV";

	public static final String GENERATE_LIGHT_HISTORY_CSV = "/api/export/generateLightHistoryCSV";

	public static final String GENERATE_ECO_MODE_CSV = "/api/export/generateEcoModeCSV";

	public static final String GENERATE_SUMMARY_CSV = "/api/export/generateSummaryCSV";

	public static final String GENERATE_ANALYTICS_CSV = "/api/export/generateAnalytics";

	@Test
	public void generateLightCSV() throws Exception
	{
		// set request object
		setData("{\"lightRequest\":{\"lightCriteria\":{\"lifeCycleStateList\":[\"ACTIVE\"],\"ecomodeFilter\":[]},\"groupCriteria\":{},\"alertCriteria\":{\"alertTypeList\":[],\"alertSubtypeList\":[]},\"addressCriteria\":{},\"processCriteria\":{},\"scheduleCriteria\":{\"lightIdList\":null,\"lightSchedule\":[]},\"configurationCriteria\":{},\"tagCriteria\":{},\"startRow\":0,\"endRow\":0,\"pageSize\":0,\"sortExpressions\":[{\"field\":\"ast.label_key\",\"direction\":\"Descending\"}]},\"csvColumns\":[\"POLE_ID\",\"FLEXNET_ID\",\"LAMP_TYPE_WATTAGE_DIMMABLE\",\"DATE_ADDED\",\"CITY\",\"MAP_IT\",\"PROTECTED\",\"ECOMODE\",\"LIFECYCLE_STATE\",\"ALERTS\"],\"fileName\":\"/opt/flexnet-slc/csvtempsuperuser2013_08_22_14_19_17_742_BRT.csv\",\"processId\":29}");

		// Success situation
		getLightCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_LIGHT_CSV).andExpect(jsonPath("$.fileName", notNullValue()));

		// Failure situation
		getLightCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_LIGHT_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightCsvMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(GENERATE_LIGHT_CSV).andExpect(jsonPath("$.fileName", nullValue()));

		// Exception situation
		getLightCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_LIGHT_CSV).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	@Test
	public void generateProcessCSV() throws Exception
	{
		// set request object
		setData("{\"inquiryProcessRequest\":{\"startRow\":0,\"endRow\":0,\"pageSize\":null,\"datePattern\":\"mm/dd/yy\",\"timezone\":\"US/Eastern\",\"processFilter\":{\"startDate\":null,\"endDate\":null,\"userIds\":[]},\"listColumn\":[\"Action\",\"Description\",\"light\",\"lightFailed\",\"CreateUser\",\"StartTime\",\"status\"],\"sortExpressions\":[{\"field\":\"start_datetime\",\"direction\":\"Descending\"}]},\"timezone\":\"US/Eastern\",\"fileName\":\"/opt/flexnet-slc/csvtempsuperuser2013_08_22_14_20_16_436_BRT.csv\",\"processId\":30}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_PROCESS_CSV).andExpect(jsonPath("$.fileName", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_PROCESS_CSV)
				.andExpect(jsonPath("$.messageInfoList[0].code",
						containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getProcessMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(GENERATE_PROCESS_CSV).andExpect(jsonPath("$.fileName", nullValue()));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_PROCESS_CSV).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void generateLightHistoryCSV() throws Exception
	{
		// set request object
		setData("{\"notificationHistoryRequest\":{\"notificationHistoryCriteria\":{\"notificationHistoryId\":null,\"notificationType\":null,\"lightId\":\"9\",\"processFilter\":{\"startDate\":null,\"endDate\":null,\"lightTextSearch\":{},\"userIds\":null}},\"pageSize\":0,\"sortExpressions\":[{\"field\":\"start_datetime\",\"direction\":\"Descending\"}]},\"timezone\":\"America/New_York\",\"fileName\":\"/opt/flexnet-slc/csvtempsuperuser2013_08_22_14_18_30_959_BRT.csv\",\"processId\":28}");

		// Success situation
		getLightCsvMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_LIGHT_HISTORY_CSV).andExpect(jsonPath("$.fileName", notNullValue()));

		// Failure situation
		getLightCsvMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_LIGHT_HISTORY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getLightCsvMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(GENERATE_LIGHT_HISTORY_CSV).andExpect(jsonPath("$.fileName", nullValue()));

		// Exception situation
		getLightCsvMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_LIGHT_HISTORY_CSV).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void generateEcoModeCSV() throws Exception
	{
		// set request object
		setData("{\"inquiryEcoModeRequest\":{\"datePattern\":\"mm/dd/yy\",\"timezone\":\"US/Eastern\",\"pageSize\":\"25\",\"selectionPaginationIds\":[\"9\"],\"initialDate\":\"2013-07-22-00-00-00-000\",\"endDate\":\"2013-08-22-23-59-59-999\",\"sortExpressions\":[{\"field\":\"CONSUMPTION_DAY\",\"direction\":\"Descending\"}]},\"timezone\":\"US/Eastern\",\"fileName\":\"/opt/flexnet-slc/csvtempsuperuser2013_08_22_14_17_16_708_BRT.csv\",\"processId\":27}");

		// Success situation
		getEcomodeMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_ECO_MODE_CSV).andExpect(jsonPath("$.fileName", notNullValue()));

		// Failure situation
		getEcomodeMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_ECO_MODE_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getEcomodeMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(GENERATE_ECO_MODE_CSV).andExpect(jsonPath("$.fileName", nullValue()));

		// Exception situation
		getEcomodeMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_ECO_MODE_CSV).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void generateSummaryCSV() throws Exception
	{
		// set request object
		setData("{\"processList\":[{\"id\":2396,\"processItems\":[{\"processItemStatusEnum\":\"MLCFAILURE\"}]}],\"fileName\":\"/opt/flexnet-slc/csvtempsuperuser2013_08_21_13_42_19_629_BRT.csv\",\"processId\":2420}");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(GENERATE_SUMMARY_CSV).andExpect(jsonPath("$.processes", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(GENERATE_SUMMARY_CSV).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(GENERATE_SUMMARY_CSV).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void insertCsvProcess() throws Exception
	{
		// set request object
		setData("");

		// Success situation
		getProcessMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_CSV_PROCESS).andExpect(jsonPath("$.processes", notNullValue()));

		// Failure situation
		getProcessMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_CSV_PROCESS).andExpect(
				jsonPath("$.messageInfoList[0].code", containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getProcessMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_CSV_PROCESS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	@Test
	public void generateLightCSVAnalytics() throws Exception
	{
		// set request object
		setData("{\"inquiryAnalyticsRequest\":{\"group\":{\"id\":\"0\"},\"startRow\":0,\"endRow\":0,\"pageSize\":null,\"initialDate\":\"2013-08-16-04-00-00-000\",\"endDate\":\"2013-08-23-03-59-59-000\",\"analyticsTypeEnum\":\"LIGHTING_ALARM\",\"analyticsDateTypeEnum\":\"ONE_WEEK\",\"sortExpressions\":[{\"field\":\"total\",\"direction\":\"Ascending\"}]},\"fileName\":\"/opt/flexnet-slc/csvtempsuperuser2013_08_23_12_18_01_989_BRT.csv\",\"processId\":32}");

		// Success situation
		getAnalyticsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_CSV_ANALYTICS_PROCESS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

		// Failure situation
		getAnalyticsMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_CSV_ANALYTICS_PROCESS)
				.andExpect(
						jsonPath("$.messageInfoList[0].code",
								containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Exception situation
		getAnalyticsMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_CSV_ANALYTICS_PROCESS).andExpect(jsonPath("$.messageInfoList[0].code",
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

	private ProcessBCFMockImpl getProcessMock()
	{
		return (ProcessBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getProcessBCF();
	}

	private AnalyticsBCFMockImpl getAnalyticsMock()
	{
		return (AnalyticsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getAnalyticsBCF();
	}

	private EcoModeBCFMockImpl getEcomodeMock()
	{
		return (EcoModeBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getEcoModeBCF();
	}

	private LightCSVBCFMockImpl getLightCsvMock()
	{
		return (LightCSVBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ExportFileAPIController.class).getLightCSVBCF();
	}

}
