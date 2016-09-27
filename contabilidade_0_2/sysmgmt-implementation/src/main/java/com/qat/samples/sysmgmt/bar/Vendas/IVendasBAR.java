package com.qat.samples.sysmgmt.bar.Vendas;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.ConhecimentoTransporte;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

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
	public NotaFiscalSaida fetchNotaFiscalSaidaById(FetchByIdRequest request);

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
	public Orcamento fetchOrcamentoById(FetchByIdRequest request);

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
	public OrdemServico fetchOrdemServicoById(FetchByIdRequest request);

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



	/**
	 * Fetch conhecimentotransporte by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public ConhecimentoTransporte fetchConhecimentoTransporteById(FetchByIdRequest request);

	/**
* Insert conhecimentotransporte.
*
* @param conhecimentotransporte the conhecimentotransporte
*
* @return the internal response
*/
	public InternalResponse insertConhecimentoTransporte(ConhecimentoTransporte conhecimentotransporte);

	/**
* Update conhecimentotransporte.
*
* @param conhecimentotransporte the conhecimentotransporte
*
* @return the internal response
*/
	public InternalResponse updateConhecimentoTransporte(ConhecimentoTransporte conhecimentotransporte);

	/**
* Delete conhecimentotransporte.
*
* @param conhecimentotransporte the conhecimentotransporte
*
* @return the internal response
*/
	public InternalResponse deleteConhecimentoTransporteById(ConhecimentoTransporte conhecimentotransporte);

	/**
* Delete all conhecimentotransportes.
*
* @return the internal response
*/
	public InternalResponse deleteAllConhecimentoTransportes();

	/**
* Fetch all conhecimentotransportes.
*
* @return the list< conhecimentotransporte>
*/
	public InternalResultsResponse<ConhecimentoTransporte> fetchAllConhecimentoTransportes(ConhecimentoTransporte  conhecimentotransporte);

	/**
* Fetch conhecimentotransportes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ConhecimentoTransporte> fetchConhecimentoTransportesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch notafiscalitens by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NotaFiscalItens fetchNotaFiscalItensById(FetchByIdRequest request);

	/**
* Insert notafiscalitens.
*
* @param notafiscalitens the notafiscalitens
*
* @return the internal response
*/
	public InternalResponse insertNotaFiscalItens(NotaFiscalItens notafiscalitens);

	/**
* Update notafiscalitens.
*
* @param notafiscalitens the notafiscalitens
*
* @return the internal response
*/
	public InternalResponse updateNotaFiscalItens(NotaFiscalItens notafiscalitens);

	/**
* Delete notafiscalitens.
*
* @param notafiscalitens the notafiscalitens
*
* @return the internal response
*/
	public InternalResponse deleteNotaFiscalItensById(NotaFiscalItens notafiscalitens);

	/**
* Delete all notafiscalitenss.
*
* @return the internal response
*/
	public InternalResponse deleteAllNotaFiscalItenss();

	/**
* Fetch all notafiscalitenss.
*
* @return the list< notafiscalitens>
*/
	public InternalResultsResponse<NotaFiscalItens> fetchAllNotaFiscalItenss(NotaFiscalItens  notafiscalitens);

	/**
* Fetch notafiscalitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalItens> fetchNotaFiscalItenssByRequest(PagedInquiryRequest request);

}
