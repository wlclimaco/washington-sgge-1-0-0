package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface INfStatusDAC.
 */
public interface INfStatusDAC
{

	/**
	 * Update nf status.
	 *
	 * @param nfStatus the nf status
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateNfStatus(NfStatus nfStatus, InternalResultsResponse<?> response);

	/**
	 * Insert nf status.
	 *
	 * @param nfStatus the nf status
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertNfStatus(NfStatus nfStatus, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete nf status.
	 *
	 * @param nfStatus the nf status
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteNfStatus(NfStatus nfStatus, InternalResultsResponse<?> response);

	/**
	 * Fetch nf status by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NfStatus> fetchNfStatusByRequest(PagedInquiryRequest request);

}
