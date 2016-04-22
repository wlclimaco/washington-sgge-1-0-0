package com.qat.samples.sysmgmt.bar.Compras;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cotacao.model.Cotacao;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface ComprasBAR.. (Data Access Component - DAC)
 */
public interface IComprasBAR
{

	/**
	 * Fetch notafiscalentrada by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request);

	/**
* Insert notafiscalentrada.
*
* @param notafiscalentrada the notafiscalentrada
*
* @return the internal response
*/
	public InternalResponse insertNotaFiscalEntrada(NotaFiscalEntrada notafiscalentrada);

	/**
* Update notafiscalentrada.
*
* @param notafiscalentrada the notafiscalentrada
*
* @return the internal response
*/
	public InternalResponse updateNotaFiscalEntrada(NotaFiscalEntrada notafiscalentrada);

	/**
* Delete notafiscalentrada.
*
* @param notafiscalentrada the notafiscalentrada
*
* @return the internal response
*/
	public InternalResponse deleteNotaFiscalEntradaById(NotaFiscalEntrada notafiscalentrada);

	/**
* Delete all notafiscalentradas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNotaFiscalEntradas();

	/**
* Fetch all notafiscalentradas.
*
* @return the list< notafiscalentrada>
*/
	public InternalResultsResponse<NotaFiscalEntrada> fetchAllNotaFiscalEntradas(NotaFiscalEntrada  notafiscalentrada);

	/**
* Fetch notafiscalentradas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradasByRequest(NotaFiscalInquiryRequest request);

	/**
	 * Fetch pedidocompras by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	/**
* Insert pedidocompras.
*
* @param pedidocompras the pedidocompras
*
* @return the internal response
*/
	public InternalResponse insertPedidoCompras(PedidoCompras pedidocompras);

	/**
* Update pedidocompras.
*
* @param pedidocompras the pedidocompras
*
* @return the internal response
*/
	public InternalResponse updatePedidoCompras(PedidoCompras pedidocompras);

	/**
* Delete pedidocompras.
*
* @param pedidocompras the pedidocompras
*
* @return the internal response
*/
	public InternalResponse deletePedidoComprasById(PedidoCompras pedidocompras);

	/**
* Delete all pedidocomprass.
*
* @return the internal response
*/
	public InternalResponse deleteAllPedidoComprass();

	/**
* Fetch all pedidocomprass.
*
* @return the list< pedidocompras>
*/
	public InternalResultsResponse<PedidoCompras> fetchAllPedidoComprass(PedidoCompras  pedidocompras);

	/**
* Fetch pedidocomprass by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprassByRequest(PedidoComprasInquiryRequest request);

	/**
	 * Fetch cotacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cotacao> fetchCotacaoById(FetchByIdRequest request);

	/**
* Insert cotacao.
*
* @param cotacao the cotacao
*
* @return the internal response
*/
	public InternalResponse insertCotacao(Cotacao cotacao);

	/**
* Update cotacao.
*
* @param cotacao the cotacao
*
* @return the internal response
*/
	public InternalResponse updateCotacao(Cotacao cotacao);

	/**
* Delete cotacao.
*
* @param cotacao the cotacao
*
* @return the internal response
*/
	public InternalResponse deleteCotacaoById(Cotacao cotacao);

	/**
* Delete all cotacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllCotacaos();

	/**
* Fetch all cotacaos.
*
* @return the list< cotacao>
*/
	public InternalResultsResponse<Cotacao> fetchAllCotacaos(Cotacao  cotacao);

	/**
* Fetch cotacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cotacao> fetchCotacaosByRequest(CotacaoInquiryRequest request);

}
