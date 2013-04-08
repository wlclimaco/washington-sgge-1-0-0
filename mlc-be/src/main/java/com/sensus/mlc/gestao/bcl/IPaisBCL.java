package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Pais;
import com.sensus.mlc.gestao.model.request.InquiryPaisRequest;
import com.sensus.mlc.gestao.model.request.PaisRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IPaisBCL
{

	/**
	 * Insert pais.
	 *
	 * @param paisRequest the pais request
	 * @return the pais response
	 */
	public InternalResultsResponse<Pais> insertPais(PaisRequest paisRequest);

	/**
	 * Update pais.
	 *
	 * @param paisRequest the pais request
	 * @return the pais response
	 */
	public InternalResultsResponse<Pais> updatePais(PaisRequest paisRequest);

	/**
	 * Delete pais.
	 *
	 * @param paisRequest the pais request
	 * @return the pais response
	 */
	public InternalResponse deletePais(PaisRequest paisRequest);

	/**
	 * Fetch all pais.
	 *
	 * @param inquirypaisRequest the inquirypais request
	 * @return the inquiry pais response
	 */
	public InternalResultsResponse<Pais> fetchAllPais(InquiryPaisRequest inquirypaisRequest);

	/**
	 * Fetch pais by id.
	 *
	 * @param inquirypaisRequest the inquirypais request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Pais> fetchPaisById(PaisRequest paisRequest);

	/**
	 * Fetch all pais types.
	 *
	 * @param request the request
	 * @return the pais response
	 */
	public InternalResultsResponse<Pais> fetchAllPaisTypes(InquiryPaisRequest inquirypaisRequest);

	/**
	 * Fetch all pais filial.
	 *
	 * @param request the request
	 * @return the pais response
	 */
	public InternalResultsResponse<Pais> fetchAllPaisFilial(PaisRequest paisRequest);

}
