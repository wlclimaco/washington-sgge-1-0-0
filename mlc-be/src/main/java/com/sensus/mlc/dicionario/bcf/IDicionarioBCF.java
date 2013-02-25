package com.sensus.mlc.dicionario.bcf;

import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;
import com.sensus.mlc.dicionario.model.response.TelaResponse;
import com.sensus.mlc.dicionario.model.response.InquiryTelaResponse;



// TODO: Auto-generated Javadoc
/**
 * The Interface IGroupBCF.
 */
public interface IDicionarioBCF
{

	/**
	 * Insert dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the group response
	 */
	public TelaResponse insertTela(TelaRequest dicionarioRequest);

	/**
	 * Update dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the group response
	 */
	public TelaResponse updateTela(TelaRequest dicionarioRequest);

	/**
	 * Delete dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the Dicionario response
	 */
	public TelaResponse deleteTela(TelaRequest dicionarioRequest);

	/**
	 * Fetch all dicionarios.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the inquiry dicionario response
	 */
	public InquiryTelaResponse fetchAllTelas(InquiryTelaRequest inquiryDicionarioRequest);

	/**
	 * Fetch dicionario by id.
	 *
	 * @param TelaRequest the Dicionario request
	 * @return the Dicionario response
	 */
	public TelaResponse fetchTelaById(TelaRequest dicionarioRequest);
	


}
