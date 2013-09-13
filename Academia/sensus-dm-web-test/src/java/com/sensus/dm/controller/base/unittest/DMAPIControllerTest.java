package com.sensus.dm.controller.base.unittest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.base.DMAPIController;
import com.sensus.dm.controller.device.unittest.DeviceBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.controller.unittest.util.TenantBCFMockImpl;

public class DMAPIControllerTest extends AbstractTestBase
{

	private static final String FILL_SETTINGS = "/fillSettings";

	private static final String FETCHMESSAGES = "/fetchmessages";

	private static final String FETCH_SERVICES_BY_DEVICE_TYPE = "/fetchServicesByDeviceType";

	private TenantBCFMockImpl getTenantMock()
	{
		return (TenantBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DMAPIController.class).getTenantBCF();
	}

	private DeviceBCFMockImpl getDeviceMock()
	{
		return (DeviceBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DMAPIController.class).getDeviceBCF();
	}

	@Test
	public void fillSettings() throws Exception
	{
		setData("");
		// Success Situation
		getTenantMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FILL_SETTINGS).andExpect(status().isOk());
	}

	@Test
	public void fetchMessages() throws Exception
	{
		setData("{\"localeLanguage\":\"en-US\"}");
		// Success Situation
		performTestGet(FETCHMESSAGES).andExpect(status().isOk());
	}

	@Test
	public void fetchDeviceTypeParameters() throws Exception
	{
		setData("");
		// Success Situation
		getDeviceMock().setMode(ModeEnum.MODE_SUCCESS);
		performTest(FETCH_SERVICES_BY_DEVICE_TYPE).andExpect(status().isOk());

		getDeviceMock().setMode(ModeEnum.MODE_EXCEPTION);
		performTest(FETCH_SERVICES_BY_DEVICE_TYPE).andExpect(status().isOk());
	}

}
