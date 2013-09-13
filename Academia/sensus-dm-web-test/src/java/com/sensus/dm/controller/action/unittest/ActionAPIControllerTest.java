package com.sensus.dm.controller.action.unittest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.action.ActionAPIController;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TestMessageEnum;

/**
 * The Class ActionAPIControllerTest.
 */
public class ActionAPIControllerTest extends AbstractTestBase
{
	/** The Constant ABORT. */
	private static final String ABORT = "/api/action/abort";

	/** The Constant DELETE_DEVICE_OPT_OUT_LIST. */
	private static final String DELETE_DEVICE_OPT_OUT_LIST = "/api/action/deleteDeviceToOptOutList";

	/** The Constant INSERT_DEVICE_OPT_OUT_LIST. */
	private static final String INSERT_DEVICE_OPT_OUT_LIST = "/api/action/insertDeviceToOptOutList";

	/** The Constant MESSAGE_INFO. */
	private static final String MESSAGE_INFO = "$.messageInfoList";

	/** The Constant MESSAGE_INFO_CODE. */
	private static final String MESSAGE_INFO_CODE = "$.messageInfoList[0].code";

	/** The Constant OPERATION_SUCCESS. */
	private static final String OPERATION_SUCCESS = "$.operationSuccess";

	/**
	 * Gets the action mock.
	 * 
	 * @return the action mock
	 */
	private ActionBCFMockImpl getActionMock()
	{
		return (ActionBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(ActionAPIController.class).getActionBCF();
	}

	/**
	 * Abort.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void abort() throws Exception
	{
		// Success situation
		setData("{\"processID\":1}");

		getActionMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(ABORT)
				.andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(true)))
				.andExpect(jsonPath(MESSAGE_INFO, hasSize(0)));

		// Failure situation
		getActionMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(ABORT)
				.andExpect(jsonPath(MESSAGE_INFO_CODE,
						containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getActionMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(ABORT).andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(true)));

		// Exception situation
		getActionMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(ABORT)
				.andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(false)))
				.andExpect(jsonPath(MESSAGE_INFO_CODE,
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Insert device to schedule opt out list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertDeviceToScheduleOptOutList() throws Exception
	{
		// Success Situation
		setData("{\"action\":{\"id\":1,\"devices\":[{\"radio\":{\"flexNetId\":1001}}]}}");

		getActionMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(INSERT_DEVICE_OPT_OUT_LIST)
				.andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(true)))
				.andExpect(jsonPath(MESSAGE_INFO, hasSize(1)));

		// Failure situation
		getActionMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(INSERT_DEVICE_OPT_OUT_LIST)
				.andExpect(jsonPath(MESSAGE_INFO_CODE,
						containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getActionMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(INSERT_DEVICE_OPT_OUT_LIST).andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(true)));

		// Exception situation
		getActionMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(INSERT_DEVICE_OPT_OUT_LIST)
				.andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(false)))
				.andExpect(jsonPath(MESSAGE_INFO_CODE,
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}

	/**
	 * Delete device to schedule opt out list.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void deleteDeviceToScheduleOptOutList() throws Exception
	{
		// Success Situation
		setData("{\"action\":{\"id\":1,\"devices\":[{\"radio\":{\"flexNetId\":1001}}]}}");

		getActionMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(DELETE_DEVICE_OPT_OUT_LIST)
				.andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(true)))
				.andExpect(jsonPath(MESSAGE_INFO, hasSize(1)));

		// Failure situation
		getActionMock().setMode(ModeEnum.MODE_FAILURE);
		performTest(DELETE_DEVICE_OPT_OUT_LIST)
				.andExpect(jsonPath(MESSAGE_INFO_CODE,
						containsString(TestMessageEnum.MESSAGE_ERROR.getValue())));

		// Empty situation
		getActionMock().setMode(ModeEnum.MODE_EMPTY);
		performTest(DELETE_DEVICE_OPT_OUT_LIST).andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(true)));

		// Exception situation
		getActionMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(DELETE_DEVICE_OPT_OUT_LIST)
				.andExpect(jsonPath(OPERATION_SUCCESS, comparesEqualTo(false)))
				.andExpect(jsonPath(MESSAGE_INFO_CODE,
						containsString(TestMessageEnum.DEFAULT_EXCEPTION_MSG.getValue())));
	}
}
