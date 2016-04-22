package com.qat.samples.sysmgmt.bar.Vendas;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface VendasBAR.. (Data Access Component - DAC)
 */
public interface IVendasBAR
{

	/**
	 * Fetch notafiscalsaida by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request);

	/**
* Insert notafiscalsaida.
*
* @param notafiscalsaida the notafiscalsaida
*
* @return the internal response
*/
	public InternalResponse insertNotaFiscalSaida(NotaFiscalSaida notafiscalsaida);

	/**
* Update notafiscalsaida.
*
* @param notafiscalsaida the notafiscalsaida
*
* @return the internal response
*/
	public InternalResponse updateNotaFiscalSaida(NotaFiscalSaida notafiscalsaida);

	/**
* Delete notafiscalsaida.
*
* @param notafiscalsaida the notafiscalsaida
*
* @return the internal response
*/
	public InternalResponse deleteNotaFiscalSaidaById(NotaFiscalSaida notafiscalsaida);

	/**
* Delete all notafiscalsaidas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNotaFiscalSaidas();

	/**
* Fetch all notafiscalsaidas.
*
* @return the list< notafiscalsaida>
*/
	public InternalResultsResponse<NotaFiscalSaida> fetchAllNotaFiscalSaidas(NotaFiscalSaida  notafiscalsaida);

	/**
* Fetch notafiscalsaidas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidasByRequest(NotaFiscalInquiryRequest request);

	/**
	 * Fetch orcamento by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	/**
* Insert orcamento.
*
* @param orcamento the orcamento
*
* @return the internal response
*/
	public InternalResponse insertOrcamento(Orcamento orcamento);

	/**
* Update orcamento.
*
* @param orcamento the orcamento
*
* @return the internal response
*/
	public InternalResponse updateOrcamento(Orcamento orcamento);

	/**
* Delete orcamento.
*
* @param orcamento the orcamento
*
* @return the internal response
*/
	public InternalResponse deleteOrcamentoById(Orcamento orcamento);

	/**
* Delete all orcamentos.
*
* @return the internal response
*/
	public InternalResponse deleteAllOrcamentos();

	/**
* Fetch all orcamentos.
*
* @return the list< orcamento>
*/
	public InternalResultsResponse<Orcamento> fetchAllOrcamentos(Orcamento  orcamento);

	/**
* Fetch orcamentos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Orcamento> fetchOrcamentosByRequest(OrcamentoInquiryRequest request);

	/**
	 * Fetch ordemservico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	/**
* Insert ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse insertOrdemServico(OrdemServico ordemservico);

	/**
* Update ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse updateOrdemServico(OrdemServico ordemservico);

	/**
* Delete ordemservico.
*
* @param ordemservico the ordemservico
*
* @return the internal response
*/
	public InternalResponse deleteOrdemServicoById(OrdemServico ordemservico);

	/**
* Delete all ordemservicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllOrdemServicos();

	/**
* Fetch all ordemservicos.
*
* @return the list< ordemservico>
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
