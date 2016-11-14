package com.qat.samples.sysmgmt.bac.Vendas;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IVendasBAC. (Business Area Component - BAC)
 */
public interface IVendasBAC
{



//===================================### NOTAFISCALSAIDA ####======================================
	/**

	/**
	 * Insert notafiscalsaida.
	 *
* @param request the notafiscalsaida maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	/**
* Update notafiscalsaida.
*
* @param request the notafiscalsaida maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	/**
* Delete notafiscalsaida.
*
* @param request the notafiscalsaida maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	/**
* Refresh notafiscalsaidas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<NFNota> refreshNotaFiscalSaidas(RefreshRequest request);

	/**
* Fetch notafiscalsaida by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> fetchNotaFiscalSaidaById(FetchByIdRequest request);

	/**
* Fetch all notafiscalsaidas.
*
* @return the internal results response< notafiscalsaida>
*/
	public InternalResultsResponse<NFNota> fetchAllNotaFiscalSaidas(NFNota  notafiscalsaida);

	/**
* Fetch notafiscalsaidas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> fetchNotaFiscalSaidasByRequest(NFNotaInquiryRequest request);


//===================================### ORDEMSERVICO ####======================================
	/**

	/**
	 * Insert ordemservico.
	 *
* @param request the ordemservico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Update ordemservico.
*
* @param request the ordemservico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Delete ordemservico.
*
* @param request the ordemservico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> deleteOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
* Refresh ordemservicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<OrdemServico> refreshOrdemServicos(RefreshRequest request);

	/**
* Fetch ordemservico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	/**
* Fetch all ordemservicos.
*
* @return the internal results response< ordemservico>
*/
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico  ordemservico);

	/**
* Fetch ordemservicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request);

}
