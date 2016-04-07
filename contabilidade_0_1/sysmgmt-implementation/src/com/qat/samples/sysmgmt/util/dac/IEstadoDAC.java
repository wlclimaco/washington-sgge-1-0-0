package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.estado.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;

/**
 * The Interface IEstadoDAC.
 */
public interface IEstadoDAC
{

	/**
	 * Update estado.
	 *
	 * @param estado the estado
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateEstado(Estado estado, InternalResultsResponse<?> response);

	/**
	 * Insert estado.
	 *
	 * @param estado the estado
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEstado(Estado estado, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business estado.
	 *
	 * @param estado the estado
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteEstado(Estado estado, InternalResultsResponse<?> response);

	/**
	 * Fetch estado by id.
	 *
	 * @param id the id
	 * @return the internal results response< estado>
	 */
	public InternalResultsResponse<Estado> fetchEstadoById(Integer id);

	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request);

}
