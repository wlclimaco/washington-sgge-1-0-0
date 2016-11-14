package com.qat.samples.sysmgmt.bar.Vendas;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
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
	public NFNota fetchNotaFiscalSaidaById(FetchByIdRequest request);

	/**
* Insert notafiscalsaida.
*
* @param notafiscalsaida the notafiscalsaida
*
* @return the internal response
*/
	public InternalResponse insertNotaFiscalSaida(NFNota notafiscalsaida);

	/**
* Update notafiscalsaida.
*
* @param notafiscalsaida the notafiscalsaida
*
* @return the internal response
*/
	public InternalResponse updateNotaFiscalSaida(NFNota notafiscalsaida);

	/**
* Delete notafiscalsaida.
*
* @param notafiscalsaida the notafiscalsaida
*
* @return the internal response
*/
	public InternalResponse deleteNotaFiscalSaidaById(NFNota notafiscalsaida);

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
	public InternalResultsResponse<NFNota> fetchAllNotaFiscalSaidas(NFNota  notafiscalsaida);

	/**
* Fetch notafiscalsaidas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> fetchNotaFiscalSaidasByRequest(NFNotaInquiryRequest request);

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


}
