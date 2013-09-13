package com.sensus.dm.common.process.bcl;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class ProcessTypeBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/process/processtypebclimpltest.xml"})
public class ProcessTypeBCLImplTest extends AbstractTestBaseBusiness
{

	/** The process bcl. */
	private IProcessTypeBCL processTypeBCL;

	/**
	 * Gets the process type bcl.
	 * 
	 * @return the process type bcl
	 */
	public IProcessTypeBCL getProcessTypeBCL()
	{
		return processTypeBCL;
	}

	/**
	 * Sets the process type bcl.
	 * 
	 * @param processTypeBCL the new process type bcl
	 */
	@Resource(name = "processTypeBCLTarget")
	public void setProcessTypeBCL(IProcessTypeBCL processTypeBCL)
	{
		this.processTypeBCL = processTypeBCL;
	}

	/**
	 * Sets the process type area.
	 */
	public void setProcessTypeArea()
	{
		setArea(getProcessTypeBCL(), EPMAreaEnum.PROCESS);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setProcessTypeArea();
	}

	/**
	 * Removes the process area.
	 */
	@After
	public void resetMocksToProcessArea()
	{
		resetMocksData(getProcessTypeBCL());
		setProcessTypeArea();
	}

	/**
	 * Test fetch all process category.
	 */
	@Test
	public void testFetchAllProcessCategory()
	{
		// Success situation
		InternalResultsResponse<ProcessCategory> response =
				getProcessTypeBCL().fetchAllProcessCategory(TestBaseUtil.createProcessRequest());

		TestBaseUtil.assertResultResponse(response);
	}

}
