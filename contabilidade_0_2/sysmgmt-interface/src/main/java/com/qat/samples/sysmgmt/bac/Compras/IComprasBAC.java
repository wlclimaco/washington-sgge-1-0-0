package com.qat.samples.sysmgmt.bac.Compras;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cotacao.model.Cotacao;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoInquiryRequest;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.PedidoCompras;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IComprasBAC. (Business Area Component - BAC)
 */
public interface IComprasBAC
{



//===================================### NOTAFISCALENTRADA ####======================================
	/**

	/**
	 * Insert notafiscalentrada.
	 *
* @param request the notafiscalentrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	/**
* Update notafiscalentrada.
*
* @param request the notafiscalentrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	/**
* Delete notafiscalentrada.
*
* @param request the notafiscalentrada maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalEntrada> deleteNotaFiscalEntrada(NotaFiscalEntradaMaintenanceRequest request);

	/**
* Refresh notafiscalentradas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<NotaFiscalEntrada> refreshNotaFiscalEntradas(RefreshRequest request);

	/**
* Fetch notafiscalentrada by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request);

	/**
* Fetch all notafiscalentradas.
*
* @return the internal results response< notafiscalentrada>
*/
	public InternalResultsResponse<NotaFiscalEntrada> fetchAllNotaFiscalEntradas(NotaFiscalEntrada  notafiscalentrada);

	/**
* Fetch notafiscalentradas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradasByRequest(NotaFiscalInquiryRequest request);


//===================================### PEDIDOCOMPRAS ####======================================
	/**

	/**
	 * Insert pedidocompras.
	 *
* @param request the pedidocompras maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoComprasMaintenanceRequest request);

	/**
* Update pedidocompras.
*
* @param request the pedidocompras maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoComprasMaintenanceRequest request);

	/**
* Delete pedidocompras.
*
* @param request the pedidocompras maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<PedidoCompras> deletePedidoCompras(PedidoComprasMaintenanceRequest request);

	/**
* Refresh pedidocomprass.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<PedidoCompras> refreshPedidoComprass(RefreshRequest request);

	/**
* Fetch pedidocompras by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request);

	/**
* Fetch all pedidocomprass.
*
* @return the internal results response< pedidocompras>
*/
	public InternalResultsResponse<PedidoCompras> fetchAllPedidoComprass(PedidoCompras  pedidocompras);

	/**
* Fetch pedidocomprass by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprassByRequest(PedidoComprasInquiryRequest request);


//===================================### COTACAO ####======================================
	/**

	/**
	 * Insert cotacao.
	 *
* @param request the cotacao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cotacao> insertCotacao(CotacaoMaintenanceRequest request);

	/**
* Update cotacao.
*
* @param request the cotacao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cotacao> updateCotacao(CotacaoMaintenanceRequest request);

	/**
* Delete cotacao.
*
* @param request the cotacao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cotacao> deleteCotacao(CotacaoMaintenanceRequest request);

	/**
* Refresh cotacaos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Cotacao> refreshCotacaos(RefreshRequest request);

	/**
* Fetch cotacao by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cotacao> fetchCotacaoById(FetchByIdRequest request);

	/**
* Fetch all cotacaos.
*
* @return the internal results response< cotacao>
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
