package com.sensus.dm.controller.importfile.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.action.unittest.ActionBCFMockImpl;
import com.sensus.dm.controller.importfile.ImportActionController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ImportGroupAPIControllerTest.
 */
public class ImportFileAPIControllerTest extends AbstractTestBase
{

	/** The Constant API_DEVICEOP_APPLY. */
	private static final String API_DEVICEOP_APPLY = "/api/importFile/upload/action/importFile";

	/** The Constant MESSAGE_INFO_CODE. */
	private static final String MESSAGE_INFO_CODE = "$.messageInfoList[0].code";

	/** The Constant OPERATION_SUCCESS. */
	private static final String OPERATION_SUCCESS = "$.operationSuccess";

	private ActionBCFMockImpl getImportFileAPIControllerMock()
	{
		return (ActionBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ImportActionController.class)
				.getActionBCF();
	}

	@Test
	public void importHanDeviceFromAction() throws Exception
	{

		// ====================================sensus.dm.action.connect.han.device====================================

		// setData("{\"parameters\":{\"installCode\":\"1\",\"macAddress\":\"11:11:11:11:11:11:11:11\",\"manufacture\":\"Energate\",\"model\":\"TH-Z100\",\"customerId\":\"Acme\",\"subDeviceType\":\"THERMOSTAT\",\"deviceId\":\"1001M\",\"clientEndPointId\":null,\"flexNetId\":\"1001\",\"actionType\":{\"id\":null},\"actionTypeName\":\"sensus.dm.action.connect.han.device\",\"actionTime\":\"2013-04-24T20:35:03.201Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"deviceType\":\"ELECTRIC_METER\"}]}}");
		setData("{\"parameters\":{\"installCode\":\"1001\",\"macAddress\":\"11:11:11:11:11:11:11:11\",\"manufacture\":\"Energate\",\"model\":\"EG-FZ100\",\"customerId\":null,\"deviceId\":\"1001\",\"clientEndPointId\":null,\"flexNetId\":null,\"actionType\":{\"id\":null},\"actionTypeName\":\"sensus.dm.action.import.han.device\",\"actionTime\":\"2013-09-09T16:10:39.383Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"lifecycleStateEnum\":\"INSTALLED\"}]}}");
		// Success Situation
		// setData("{}");
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.import.han.device====================================

		setData("{\"parameters\":{\"actionTypeName\":\"sensus.dm.action.import.han.device\",\"onDemand\":true,\"isMonitored\":true,\"actionTime\":\"2013-01-25T17:54:24.450Z\"}}");

		// Success Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		setData("{\"parameters\":{\"isRetry\":true,\"actionTypeName\":\"sensus.dm.action.import.han.device\",\"onDemand\":true,\"isMonitored\":true,\"actionTime\":\"2013-01-25T17:54:24.450Z\",\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"deviceType\":\"ELECTRIC_METER\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}}");

		// Success Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// ====================================sensus.dm.action.remote.disconnect======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":3},\"actionTypeName\":\"sensus.dm.action.remote.disconnect\",\"actionTime\":\"2013-04-24T20:47:37.847Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"1001M\",\"deviceType\":\"ELECTRIC_METER\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]} ");

		// Success Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.delete.han.device======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":1},\"actionTypeName\":\"sensus.dm.action.delete.han.device\",\"onDemand\":true,\"isMonitored\":true,\"actionTime\":\"2013-01-25T17:54:24.450Z\",\"securityTokenType\":\"zigbee_install_code\",\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":{\"type\":\"Meter\",\"id\":1},\"customerId\":\"ACME\",\"baseFlexNetId\":\"\",\"deviceId\":\"1010M\",\"deviceType\":\"ELECTRIC_METER\"}]}}");

		// Success Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.disconnect.han.device======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":1},\"actionTypeName\":\"sensus.dm.action.disconnect.han.device\",\"onDemand\":true,\"isMonitored\":true,\"actionTime\":\"2013-01-25T17:54:24.450Z\",\"securityTokenType\":\"zigbee_install_code\",\"devices\":[{\"flexNetId\":\"1001\",\"deviceType\":{\"type\":\"Meter\",\"id\":1},\"customerId\":\"ACME\",\"baseFlexNetId\":\"\",\"deviceId\":\"1010M\",\"deviceType\":\"ELECTRIC_METER\"}]}}");

		// Success Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

		// ====================================sensus.dm.action.get.connection.status======================================

		setData("{\"parameters\":{\"actionType\":{\"id\":12},\"actionTypeName\":\"sensus.dm.action.get.connection.status\",\"actionTime\":\"2013-04-25T17:34:40.706Z\",\"onDemand\":true,\"isMonitored\":true,\"devices\":[{\"flexNetId\":\"10000001\",\"deviceType\":\"ELECTRIC_METER\",\"customerId\":\"ACME\",\"electricMeterFlexNetId\":\"\",\"specificDeviceTypeEnumValue\":1,\"deviceId\":\"10000001M\",\"deviceType\":\"ELECTRIC_METER\"}]},\"sortExpressions\":[{\"field\":\"flexnet_id\",\"direction\":\"Ascending\"}]}");

		// Success Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(OPERATION_SUCCESS, equalTo(Boolean.TRUE)));

		// Failure Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(API_DEVICEOP_APPLY).andExpect(
				jsonPath(MESSAGE_INFO_CODE, containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty Situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EMPTY);

		// Exception situation
		getImportFileAPIControllerMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(API_DEVICEOP_APPLY).andExpect(jsonPath(MESSAGE_INFO_CODE,
				containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));

	}

}
