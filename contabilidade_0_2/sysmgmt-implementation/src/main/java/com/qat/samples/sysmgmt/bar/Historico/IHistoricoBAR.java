package com.qat.samples.sysmgmt.bar.Historico;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface HistoricoBAR.. (Data Access Component - DAC)
 */
public interface IHistoricoBAR
{

	/**
	 * Fetch historico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Historico fetchHistoricoById(FetchByIdRequest request);

	/**
* Insert historico.
*
* @param historico the historico
*
* @return the internal response
*/
	public InternalResponse insertHistorico(Historico historico);

	/**
* Update historico.
*
* @param historico the historico
*
* @return the internal response
*/
	public InternalResponse updateHistorico(Historico historico);

	/**
* Delete historico.
*
* @param historico the historico
*
* @return the internal response
*/
	public InternalResponse deleteHistoricoById(Historico historico);

	/**
* Delete all historicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllHistoricos();

	/**
* Fetch all historicos.
*
* @return the list< historico>
*/
	public InternalResultsResponse<Historico> fetchAllHistoricos(Historico  historico);

	/**
* Fetch historicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Historico> fetchHistoricosByRequest(HistoricoInquiryRequest request);

	/**
	 * Fetch historicoitens by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public HistoricoItens fetchHistoricoItensById(FetchByIdRequest request);

	/**
* Insert historicoitens.
*
* @param historicoitens the historicoitens
*
* @return the internal response
*/
	public InternalResponse insertHistoricoItens(HistoricoItens historicoitens);

	/**
* Update historicoitens.
*
* @param historicoitens the historicoitens
*
* @return the internal response
*/
	public InternalResponse updateHistoricoItens(HistoricoItens historicoitens);

	/**
* Delete historicoitens.
*
* @param historicoitens the historicoitens
*
* @return the internal response
*/
	public InternalResponse deleteHistoricoItensById(HistoricoItens historicoitens);

	/**
* Delete all historicoitenss.
*
* @return the internal response
*/
	public InternalResponse deleteAllHistoricoItenss();

	/**
* Fetch all historicoitenss.
*
* @return the list< historicoitens>
*/
	public InternalResultsResponse<HistoricoItens> fetchAllHistoricoItenss(HistoricoItens  historicoitens);

	/**
* Fetch historicoitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<HistoricoItens> fetchHistoricoItenssByRequest(PagedInquiryRequest request);

}
