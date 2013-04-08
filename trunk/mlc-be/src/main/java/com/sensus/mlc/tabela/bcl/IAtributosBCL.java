package com.sensus.mlc.tabela.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tabela.model.Atributos;
import com.sensus.mlc.tabela.model.request.AtributosRequest;
import com.sensus.mlc.tabela.model.request.InquiryAtributosRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - QAT Brazil.
 *
 */
public interface IAtributosBCL
{

	/**
	 * Insert atributos.
	 *
	 * @param atributosRequest the atributos request
	 * @return the atributos response
	 */
	public InternalResultsResponse<Atributos> insertAtributos(AtributosRequest atributosRequest);

	/**
	 * Update atributos.
	 *
	 * @param atributosRequest the atributos request
	 * @return the atributos response
	 */
	public InternalResultsResponse<Atributos> updateAtributos(AtributosRequest atributosRequest);

	/**
	 * Delete atributos.
	 *
	 * @param atributosRequest the atributos request
	 * @return the atributos response
	 */
	public InternalResponse deleteAtributos(AtributosRequest atributosRequest);

	/**
	 * Fetch all atributos.
	 *
	 * @param inquiryatributosRequest the inquiryatributos request
	 * @return the inquiry atributos response
	 */
	public InternalResultsResponse<Atributos> fetchAllAtributos(InquiryAtributosRequest inquiryatributosRequest);

	/**
	 * Fetch atributos by id.
	 *
	 * @param inquiryatributosRequest the inquiryatributos request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Atributos> fetchAtributosById(AtributosRequest atributosRequest);

	/**
	 * Fetch all atributos types.
	 *
	 * @param request the request
	 * @return the atributos response
	 */
	public InternalResultsResponse<Atributos> fetchAllAtributosTypes(InquiryAtributosRequest inquiryatributosRequest);

	/**
	 * Fetch all atributos filial.
	 *
	 * @param request the request
	 * @return the atributos response
	 */
	public InternalResultsResponse<Atributos> fetchAllAtributosFilial(AtributosRequest atributosRequest);

}
