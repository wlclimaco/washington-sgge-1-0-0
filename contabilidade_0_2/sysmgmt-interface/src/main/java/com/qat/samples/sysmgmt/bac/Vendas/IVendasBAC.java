package com.qat.samples.sysmgmt.bac.Vendas;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
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
	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	/**
* Update notafiscalsaida.
*
* @param request the notafiscalsaida maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	/**
* Delete notafiscalsaida.
*
* @param request the notafiscalsaida maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalSaida> deleteNotaFiscalSaida(NotaFiscalSaidaMaintenanceRequest request);

	/**
* Refresh notafiscalsaidas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<NotaFiscalSaida> refreshNotaFiscalSaidas(RefreshRequest request);

	/**
* Fetch notafiscalsaida by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request);

	/**
* Fetch all notafiscalsaidas.
*
* @return the internal results response< notafiscalsaida>
*/
	public InternalResultsResponse<NotaFiscalSaida> fetchAllNotaFiscalSaidas(NotaFiscalSaida  notafiscalsaida);

	/**
* Fetch notafiscalsaidas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidasByRequest(NotaFiscalInquiryRequest request);


//===================================### ORCAMENTO ####======================================
	/**

	/**
	 * Insert orcamento.
	 *
* @param request the orcamento maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Orcamento> insertOrcamento(OrcamentoMaintenanceRequest request);

	/**
* Update orcamento.
*
* @param request the orcamento maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Orcamento> updateOrcamento(OrcamentoMaintenanceRequest request);

	/**
* Delete orcamento.
*
* @param request the orcamento maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Orcamento> deleteOrcamento(OrcamentoMaintenanceRequest request);

	/**
* Refresh orcamentos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Orcamento> refreshOrcamentos(RefreshRequest request);

	/**
* Fetch orcamento by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	/**
* Fetch all orcamentos.
*
* @return the internal results response< orcamento>
*/
	public InternalResultsResponse<Orcamento> fetchAllOrcamentos(Orcamento  orcamento);

	/**
* Fetch orcamentos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Orcamento> fetchOrcamentosByRequest(OrcamentoInquiryRequest request);


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
