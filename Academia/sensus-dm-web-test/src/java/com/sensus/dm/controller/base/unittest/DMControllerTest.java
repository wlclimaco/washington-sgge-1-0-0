package com.sensus.dm.controller.base.unittest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.dm.controller.base.DMController;
import com.sensus.dm.controller.settings.unittest.SettingsBCFMockImpl;
import com.sensus.dm.controller.unittest.util.AbstractTestBase;
import com.sensus.dm.controller.unittest.util.ModeEnum;

public class DMControllerTest extends AbstractTestBase
{

	private static final String SERVICE = "/service";
	private static final String ELECTRIC = "/setService?service=ELECTRIC";
	private static final String WATER = "/setService?service=WATER";
	private static final String GAS = "/setService?service=GAS";

	private SettingsBCFMockImpl getSettingsMock()
	{
		return (SettingsBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(DMController.class).getSettingsBCF();
	}

	@Test
	public void service() throws Exception
	{
		// Success Situation
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTestGetPage(SERVICE).andExpect(status().isOk());
	}

	@Test
	public void electric() throws Exception
	{

		// Success Situation
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTestGetPage(ELECTRIC).andExpect(status().isOk());
	}

	@Test
	public void water() throws Exception
	{

		// Success Situation
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTestGetPage(WATER).andExpect(status().isOk());
	}

	@Test
	public void gas() throws Exception
	{

		// Success Situation
		getSettingsMock().setMode(ModeEnum.MODE_SUCCESS);
		performTestGetPage(GAS).andExpect(status().isOk());
	}
}
