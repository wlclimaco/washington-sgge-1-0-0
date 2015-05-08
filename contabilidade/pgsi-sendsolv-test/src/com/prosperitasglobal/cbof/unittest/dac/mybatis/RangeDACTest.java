package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import org.junit.Test;

import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.cbof.model.RangeEnum;
import com.prosperitasglobal.cbof.model.request.RangeRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class RangeDACTest.
 */
public class RangeDACTest extends AbstractTestBaseDAC
{

	/**
	 * Test fetch all range.
	 */
	@Test
	public void testFetchAllRange()
	{
		// fetch all
		RangeRequest request = new RangeRequest();
		request.setRangeType(RangeEnum.NUMBER_OF_EMPLOYEES);
		InternalResultsResponse<Range> response = getRangeDAC().fetchAllRanges(request);
		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch range by id.
	 */
	@Test
	public void testFetchRangeById()
	{
		RangeRequest request = new RangeRequest();
		request.setRangeType(RangeEnum.NUMBER_OF_EMPLOYEES);
		InternalResultsResponse<Range> response = getRangeDAC().fetchRangeById(request);
		CommonTestRoutines.assertResponse(response);

	}

}
