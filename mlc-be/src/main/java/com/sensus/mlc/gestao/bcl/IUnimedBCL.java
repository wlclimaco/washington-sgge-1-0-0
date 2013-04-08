package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Unimed;
import com.sensus.mlc.gestao.model.request.InquiryUnimedRequest;
import com.sensus.mlc.gestao.model.request.UnimedRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IUnimedBCL
{

	/**
	 * Insert unimed.
	 *
	 * @param unimedRequest the unimed request
	 * @return the unimed response
	 */
	public InternalResultsResponse<Unimed> insertUnimed(UnimedRequest unimedRequest);

	/**
	 * Update unimed.
	 *
	 * @param unimedRequest the unimed request
	 * @return the unimed response
	 */
	public InternalResultsResponse<Unimed> updateUnimed(UnimedRequest unimedRequest);

	/**
	 * Delete unimed.
	 *
	 * @param unimedRequest the unimed request
	 * @return the unimed response
	 */
	public InternalResponse deleteUnimed(UnimedRequest unimedRequest);

	/**
	 * Fetch all unimed.
	 *
	 * @param inquiryunimedRequest the inquiryunimed request
	 * @return the inquiry unimed response
	 */
	public InternalResultsResponse<Unimed> fetchAllUnimed(InquiryUnimedRequest inquiryunimedRequest);

	/**
	 * Fetch unimed by id.
	 *
	 * @param inquiryunimedRequest the inquiryunimed request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Unimed> fetchUnimedById(UnimedRequest unimedRequest);

	/**
	 * Fetch all unimed types.
	 *
	 * @param request the request
	 * @return the unimed response
	 */
	public InternalResultsResponse<Unimed> fetchAllUnimedTypes(InquiryUnimedRequest inquiryunimedRequest);

	/**
	 * Fetch all unimed filial.
	 *
	 * @param request the request
	 * @return the unimed response
	 */
	public InternalResultsResponse<Unimed> fetchAllUnimedFilial(UnimedRequest unimedRequest);

}
