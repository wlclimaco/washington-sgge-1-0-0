package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import org.junit.Test;

import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.CodeValueEnum;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CodeValueDACTest.
 */
public class CodeValueDACTest extends AbstractTestBaseDAC
{

	/**
	 * Test fetch code value by type naics.
	 */
	@Test
	public void testFetchCodeValueByTypeNAICS()
	{
		// Send NAICS
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.NAICS);

		InternalResultsResponse<CodeValue> response = getCodeValueDAC().fetchAllCodeValueByType(request);
		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch code value by type sic.
	 */
	@Test
	public void testFetchCodeValueByTypeSIC()
	{
		// Send SIC
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.SIC);
		InternalResultsResponse<CodeValue> response = getCodeValueDAC().fetchAllCodeValueByType(request);
		CommonTestRoutines.assertResponse(response);
	}

}
