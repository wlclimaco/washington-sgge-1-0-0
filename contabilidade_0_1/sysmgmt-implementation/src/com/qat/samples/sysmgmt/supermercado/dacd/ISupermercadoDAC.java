package com.qat.samples.sysmgmt.supermercado.dacd;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * The Interface ISupermercadoDAC. (Data Access Component - DAC)
 */
public interface ISupermercadoDAC
{

	/**
	 * Insert supermercado.
	 * 
	 * @param supermercado the supermercado
	 * @return the internal response
	 */
	public InternalResponse insertSupermercado(Supermercado supermercado);

	/**
	 * Update supermercado.
	 * 
	 * @param supermercado the supermercado
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateSupermercado(Supermercado supermercado);

	/**
	 * Delete supermercado.
	 * 
	 * @param supermercado the supermercado
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteSupermercado(Supermercado supermercado);

	/**
	 * Delete all supermercados.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllSupermercados();

	/**
	 * Fetch all supermercados.
	 * 
	 * @return the list< supermercado>
	 */
	public List<Supermercado> fetchAllSupermercados();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Supermercado fetchSupermercadoById(FetchByIdRequest request);

	/**
	 * Fetch supermercados by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Supermercado> fetchSupermercadosByRequest(PagedInquiryRequest request);

}
