package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.CodeValueEnum;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class IndustryClassificationDACTest.
 */
public class IndustryClassificationDACTest extends AbstractTestBaseDAC
{

	/** The Constant SIZE_NEEDS_TO_BE_0. */
	private static final String SIZE_NEEDS_TO_BE_0 = "Size needs to be 0";

	/** The industry classification dac. */
	private IIndustryClassificationDAC industryClassificationDAC;

	/**
	 * Gets the industry classification dac.
	 *
	 * @return the industry classification dac
	 */
	@Override
	public IIndustryClassificationDAC getIndustryClassificationDAC()
	{
		return industryClassificationDAC;
	}

	/**
	 * Sets the industry classification dac.
	 *
	 * @param industryClassificationDAC the industry classification dac
	 */
	@Override
	@Resource
	public void setIndustryClassificationDAC(IIndustryClassificationDAC industryClassificationDAC)
	{
		this.industryClassificationDAC = industryClassificationDAC;
	}

	/**
	 * Test fetch code value by code naics.
	 */
	@Test
	public void testFetchCodeValueByCodeNAICS()
	{
		// Send good NAICS
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.NAICS);
		request.setCode("111150");
		InternalResultsResponse<CodeValue> response = getIndustryClassificationDAC().fetchCodeValueByCode(request);
		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch code value by code sic.
	 */
	@Test
	public void testFetchCodeValueByCodeSIC()
	{
		CodeValueRequest request = new CodeValueRequest();
		// Send good SIC
		request.setCodeValueType(CodeValueEnum.SIC);
		request.setCode("2621");
		InternalResultsResponse<CodeValue> response = getIndustryClassificationDAC().fetchCodeValueByCode(request);
		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch code value by code with invalid sic.
	 */
	@Test
	public void testFetchCodeValueByCodeWithInvalidSIC()
	{
		// Send bad SIC
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.SIC);
		request.setCode("9999");
		InternalResultsResponse<CodeValue> response = getIndustryClassificationDAC().fetchCodeValueByCode(request);
		Assert.assertTrue(SIZE_NEEDS_TO_BE_0, response.getResultsList().size() == 0);
	}

	/**
	 * Test fetch code value by code with invalid naics.
	 */
	@Test
	public void testFetchCodeValueByCodeWithInvalidNAICS()
	{
		// Send bad NAICS
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.NAICS);
		request.setCode("999999");
		InternalResultsResponse<CodeValue> response = getIndustryClassificationDAC().fetchCodeValueByCode(request);
		Assert.assertTrue(SIZE_NEEDS_TO_BE_0, response.getResultsList().size() == 0);

	}
}
