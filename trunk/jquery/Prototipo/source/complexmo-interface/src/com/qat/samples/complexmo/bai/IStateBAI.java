package com.qat.samples.complexmo.bai;

import com.qat.samples.complexmo.model.request.StateRequest;
import com.qat.samples.complexmo.model.response.StateResponse;

/**
 * The Interface IEntityStateBAI.
 */
public interface IStateBAI
{

	/**
	 * Fetch state by code.
	 * 
	 * @param request the request
	 * @return the state response
	 */
	StateResponse fetchStateByCode(StateRequest request);
}
