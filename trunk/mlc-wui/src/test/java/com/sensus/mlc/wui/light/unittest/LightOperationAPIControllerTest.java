package com.sensus.mlc.wui.light.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;

import com.sensus.common.util.SensusAppContext;
import com.sensus.mlc.wui.light.LightAPIController;
import com.sensus.mlc.wui.light.LightOperationAPIController;
import com.sensus.mlc.wui.util.AbstractTestBase;
import com.sensus.mlc.wui.util.ModeEnum;
import com.sensus.mlc.wui.util.TestMessageEnum;


public class LightOperationAPIControllerTest extends AbstractTestBase
{

	/* Spring Actions URLs */
	/** The Constant CLEAR_STATUS. */
	public static final String CLEAR_STATUS = "/clearstatus";

	/** The Constant UPSERT_PROPERTY. */
	public static final String UPSERT_PROPERTY = "/upsertproperty";

	/** The Constant FETCH_STATUS. */
	public static final String FETCH_STATUS = "/fetchstatus";

	/** The Constant UPDATE_PROTECTED. */
	public static final String UPDATE_PROTECTED = "/updateprotected";

	/** The Constant UPDATE_STATUS. */
	public static final String UPDATE_STATUS = "/updatestatus";

	/** The Constant UPDATE_RESET. */
	public static final String UPDATE_RESET = "/updatereset";


	private SmartPointUpdaterBCFMockImpl getSmartPointUpdaterMock()
	{
		return (SmartPointUpdaterBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightOperationAPIController.class).getSmartPointUpdaterBCF();
	}

	private SmartPointProcessorBCFMockImpl getSmartPointProcessorMock()
	{
		return (SmartPointProcessorBCFMockImpl)SensusAppContext.getApplicationContext()
				.getBean(LightOperationAPIController.class).getSmartPointProcessorBCF();
	}


}