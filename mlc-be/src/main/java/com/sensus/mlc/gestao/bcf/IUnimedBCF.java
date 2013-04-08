package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.InquiryUnimedRequest;
import com.sensus.mlc.gestao.model.request.UnimedRequest;
import com.sensus.mlc.gestao.model.response.InquiryUnimedResponse;
import com.sensus.mlc.gestao.model.response.UnimedResponse;


/**
 * The Interface IUnimedBCF.
 *
 * @author Washington.Costa
 */
public interface IUnimedBCF
{

	/**
	 * Insert unimed.
	 *
	 * @param unimedRequest the unimed request
	 * @return the unimed response
	 */
	public UnimedResponse insertUnimed(UnimedRequest unimedRequest);

	/**
	 * Update unimed.
	 *
	 * @param unimedRequest the unimed request
	 * @return the unimed response
	 */
	public UnimedResponse updateUnimed(UnimedRequest unimedRequest);

	/**
	 * Delete unimed.
	 *
	 * @param unimedRequest the unimed request
	 * @return the unimed response
	 */
	public UnimedResponse deleteUnimed(UnimedRequest unimedRequest);

	/**
	 * Fetch all unimed.
	 *
	 * @param inquiryUnimedRequest the inquiryUnimed request
	 * @return the inquiry unimed response
	 */
	public InquiryUnimedResponse fetchAllUnimed(InquiryUnimedRequest inquiryUnimedRequest);

	/**
	 * Fetch unimed by id.
	 *
	 * @param UnimedRequest the unimed request
	 * @return the unimed response
	 */
	public UnimedResponse fetchUnimedById(UnimedRequest unimedRequest);

}
