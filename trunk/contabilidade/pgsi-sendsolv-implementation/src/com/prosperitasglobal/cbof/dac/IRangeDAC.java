package com.prosperitasglobal.cbof.dac;

import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.cbof.model.request.RangeRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IRangeDAC.
 */
public interface IRangeDAC
{
	/**
	 * Fetch all ranges.
	 *
	 * @param request the request
	 * @return the internal results response< range>
	 */
	public InternalResultsResponse<Range> fetchAllRanges(RangeRequest request);

	/**
	 * Fetch range by id.
	 *
	 * @param request the request
	 * @return the internal results response< range>
	 */
	public InternalResultsResponse<Range> fetchRangeById(RangeRequest request);

}
