package com.sensus.dm.common.process.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.dm.common.process.bcl.IProcessTypeBCL;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class ProcessTypeBCFImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/process/processtypebcfimpltest.xml"})
public class ProcessTypeBCFImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant FETCH_ALL_PROCESS_CATEGORY. */
	private static final String FETCH_ALL_PROCESS_CATEGORY = "fetchAllProcessCategory";

	/** The Constant SHOULD_BRING_THE_ID. */
	private static final String SHOULD_BRING_THE_ID = "should bring the ID";

	/** The Constant SHOULD_BRING_ONE_PROCESS. */
	private static final String SHOULD_BRING_TWO_PROCESS_CATEGORY = "should bring two process category ";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The process type bcf. */
	private IProcessTypeBCF processTypeBCF;

	/**
	 * Gets the process type bcf.
	 * 
	 * @return the process type bcf
	 */
	public IProcessTypeBCF getProcessTypeBCF()
	{
		return processTypeBCF;
	}

	/**
	 * Sets the process type bcf.
	 * 
	 * @param processTypeBCF the new process type bcf
	 */
	@Resource
	public void setProcessTypeBCF(IProcessTypeBCF processTypeBCF)
	{
		this.processTypeBCF = processTypeBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:

	/**
	 * Sets the process area.
	 */
	public void setProcessTypeArea()
	{
		setArea(getProcessTypeBCF(), EPMAreaEnum.PROCESS);
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
		resetMocksData(getProcessTypeBCF());
		setProcessTypeArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch all process category.
	 */
	@Test
	public void testFetchAllProcessCategory()
	{
		// Validation Situation - user context required
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = getProcessTypeBCF().fetchAllProcessCategory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Success Situation
		request = TestBaseUtil.createProcessRequest();
		response = getProcessTypeBCF().fetchAllProcessCategory(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_TWO_PROCESS_CATEGORY, 2, response.getProcessCategories().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getProcessCategories().get(0).getId());

		// Error situation
		setSituation(getProcessTypeBCF(), SituationsEnum.ERROR, IProcessTypeBCL.class,
				FETCH_ALL_PROCESS_CATEGORY);
		response = getProcessTypeBCF().fetchAllProcessCategory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToProcessArea();

		// Exception situation
		setSituation(getProcessTypeBCF(), SituationsEnum.EXCEPTION, IProcessTypeBCL.class,
				FETCH_ALL_PROCESS_CATEGORY);
		response = getProcessTypeBCF().fetchAllProcessCategory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
	}
}
