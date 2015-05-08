package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.cbof.model.request.RangeRequest;
import com.prosperitasglobal.cbof.model.response.RangeResponse;

/**
 * The Interface IRangeBAI.
 */
public interface IRangeBAI
{
	/**
	 * Fetch all range.
	 *
	 * @param request the request
	 * @return the range response
	 */
	public RangeResponse fetchAllRange(RangeRequest request);

	/**
	 * Fetch range by id.
	 *
	 * @param request the request
	 * @return the range response
	 */
	public RangeResponse fetchRangeById(RangeRequest request);

}
