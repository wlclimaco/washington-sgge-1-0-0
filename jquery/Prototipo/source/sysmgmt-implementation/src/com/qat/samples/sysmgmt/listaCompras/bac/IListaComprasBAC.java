package com.qat.samples.sysmgmt.listaCompras.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IListaComprasBAC. (Business Area Component - BAC)
 */
public interface IListaComprasBAC
{

	/**
	 * Insert procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertListaCompras(ListaCompras procedure);

	/**
	 * Update procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateListaCompras(ListaCompras procedure);

	/**
	 * Delete procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteListaCompras(ListaCompras procedure);

	/**
	 * Refresh procedures.
	 * 
	 * @param refreshNumber the value of the number of procedures you want refreshed
	 * 
	 */
	public void refreshListaComprass(Integer refreshNumber);

	/**
	 * Fetch procedure by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ListaCompras> fetchListaComprasById(FetchByIdRequest request);

	/**
	 * Fetch all procedures.
	 * 
	 * @return the internal results response< procedure>
	 */
	public InternalResultsResponse<ListaCompras> fetchAllListaComprass();

	/**
	 * Fetch procedures by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ListaCompras> fetchListaComprassByRequest(PagedInquiryRequest request);

	// ===================================

	public InternalResponse insertListaComprasItens(ListaCompras procedure);

	/**
	 * Update procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateListaComprasItens(ListaCompras procedure);

	/**
	 * Delete procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteListaComprasItens(ListaCompras procedure);

	/**
	 * Refresh procedures.
	 * 
	 * @param refreshNumber the value of the number of procedures you want refreshed
	 * 
	 */
	public void refreshListaComprasItenss(Integer refreshNumber);

	/**
	 * Fetch procedure by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ListaCompras> fetchListaComprasItensById(FetchByIdRequest request);

	/**
	 * Fetch all procedures.
	 * 
	 * @return the internal results response< procedure>
	 */
	public InternalResultsResponse<ListaCompras> fetchAllListaComprasItenss();

	/**
	 * Fetch procedures by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ListaCompras> fetchListaComprasItenssByRequest(PagedInquiryRequest request);

}
