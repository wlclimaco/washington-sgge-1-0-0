package com.sensus.dm.common.process.dac;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class ProcessDACImplTest.
 * 
 * @author QAT Global
 */
@Transactional
public class ProcessTypeDACImplTest extends AbstractTestBaseDAC
{

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(ProcessTypeDACImplTest.class);

	/** The process default. */
	private DMProcess processDefault;

	/**
	 * Gets the process default.
	 * 
	 * @return the process default
	 */
	public DMProcess getProcessDefault()
	{
		return processDefault;
	}

	/**
	 * Sets the process default.
	 * 
	 * @param processDefault the new process default
	 */
	public void setProcessDefault(DMProcess processDefault)
	{
		this.processDefault = processDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		setCacheStatementScope(getProcessTypeDAC());
		insertProcess(TestBaseUtil.createProcess(), ServiceTypeEnum.WATER);
		setProcessDefault(insertProcess(TestBaseUtil.createProcess()));
	}

	/**
	 * Test fetch all process category.
	 */
	@Test
	public void testFetchAllProcessCategory()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		InternalResultsResponse<ProcessCategory> response =
				getProcessTypeDAC().fetchAllProcessCategory(request);
		TestBaseUtil.assertResultResponse(response);
		for (ProcessCategory p : response.getResultsList())
		{
			LOG.debug(p.getName());
		}
	}

	/**
	 * Test fetch process type by description.
	 */
	@Test
	public void testFetchProcessTypeByDescription()
	{
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		processRequest.addProcessAsFirstElement(getProcessDefault());

		InternalResultsResponse<ProcessType> response =
				getProcessTypeDAC().fetchProcessTypeByDescription(processRequest);
		TestBaseUtil.assertResultResponse(response);
	}

}
