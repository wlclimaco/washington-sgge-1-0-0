package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.InquiryTitularesRequest;
import com.sensus.mlc.gestao.model.request.TitularesRequest;
import com.sensus.mlc.gestao.model.response.InquiryTitularesResponse;
import com.sensus.mlc.gestao.model.response.TitularesResponse;


/**
 * The Interface ITitularesBCF.
 *
 * @author Washington.Costa
 */
public interface ITitularesBCF
{

	/**
	 * Insert titulares.
	 *
	 * @param titularesRequest the titulares request
	 * @return the titulares response
	 */
	public TitularesResponse insertTitulares(TitularesRequest titularesRequest);

	/**
	 * Update titulares.
	 *
	 * @param titularesRequest the titulares request
	 * @return the titulares response
	 */
	public TitularesResponse updateTitulares(TitularesRequest titularesRequest);

	/**
	 * Delete titulares.
	 *
	 * @param titularesRequest the titulares request
	 * @return the titulares response
	 */
	public TitularesResponse deleteTitulares(TitularesRequest titularesRequest);

	/**
	 * Fetch all titulares.
	 *
	 * @param inquiryTitularesRequest the inquiryTitulares request
	 * @return the inquiry titulares response
	 */
	public InquiryTitularesResponse fetchAllTitulares(InquiryTitularesRequest inquiryTitularesRequest);

	/**
	 * Fetch titulares by id.
	 *
	 * @param TitularesRequest the titulares request
	 * @return the titulares response
	 */
	public TitularesResponse fetchTitularesById(TitularesRequest titularesRequest);

}
