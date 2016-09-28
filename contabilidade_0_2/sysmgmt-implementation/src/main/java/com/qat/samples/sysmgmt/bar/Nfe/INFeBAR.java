/** create by system gera-java version 1.0.0 28/09/2016 16:26 : 42*/
package com.qat.samples.sysmgmt.bar.Nfe;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nfe.model.NFInfoModelo1Por1AReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFInfoProdutorRuralReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFInfoReferenciada;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfo;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoAvulsa;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCana;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCanaDeducao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCanaFornecimentoDiario;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCartao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCobranca;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoCompra;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoDestinatario;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoDuplicata;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoEmitente;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoExportacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoFatura;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoICMSTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoISSQNTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoIdentificacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoInformacoesAdicionais;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoLocal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoObservacao;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoPagamento;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoProcessoReferenciado;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoReboque;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoRetencaoICMSTransporte;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoRetencoesTributos;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoSuplementar;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTotal;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTransportador;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoTransporte;
import com.qat.samples.sysmgmt.nfe.model.NFNotaInfoVeiculo;
import com.qat.samples.sysmgmt.nfe.model.NFPessoaAutorizadaDownloadNFe;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface NFeBAR.. (Data Access Component - DAC)
 */
public interface INFeBAR
{

	/**
	 * Fetch nfnota by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNota fetchNFNotaById(FetchByIdRequest request);

	/**
* Insert nfnota.
*
* @param nfnota the nfnota
*
* @return the internal response
*/
	public InternalResponse insertNFNota(NFNota nfnota);

	/**
* Update nfnota.
*
* @param nfnota the nfnota
*
* @return the internal response
*/
	public InternalResponse updateNFNota(NFNota nfnota);

	/**
* Delete nfnota.
*
* @param nfnota the nfnota
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaById(NFNota nfnota);

	/**
* Delete all nfnotas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotas();

	/**
* Fetch all nfnotas.
*
* @return the list< nfnota>
*/
	public InternalResultsResponse<NFNota> fetchAllNFNotas(NFNota  nfnota);

	/**
* Fetch nfnotas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> fetchNFNotasByRequest(NFNotaInquiryRequest request);

	/**
	 * Fetch nfnotainfo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfo fetchNFNotaInfoById(FetchByIdRequest request);

	/**
* Insert nfnotainfo.
*
* @param nfnotainfo the nfnotainfo
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfo(NFNotaInfo nfnotainfo);

	/**
* Update nfnotainfo.
*
* @param nfnotainfo the nfnotainfo
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfo(NFNotaInfo nfnotainfo);

	/**
* Delete nfnotainfo.
*
* @param nfnotainfo the nfnotainfo
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoById(NFNotaInfo nfnotainfo);

	/**
* Delete all nfnotainfos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfos();

	/**
* Fetch all nfnotainfos.
*
* @return the list< nfnotainfo>
*/
	public InternalResultsResponse<NFNotaInfo> fetchAllNFNotaInfos(NFNotaInfo  nfnotainfo);

	/**
* Fetch nfnotainfos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfo> fetchNFNotaInfosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoidentificacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoIdentificacao fetchNFNotaInfoIdentificacaoById(FetchByIdRequest request);

	/**
* Insert nfnotainfoidentificacao.
*
* @param nfnotainfoidentificacao the nfnotainfoidentificacao
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoIdentificacao(NFNotaInfoIdentificacao nfnotainfoidentificacao);

	/**
* Update nfnotainfoidentificacao.
*
* @param nfnotainfoidentificacao the nfnotainfoidentificacao
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoIdentificacao(NFNotaInfoIdentificacao nfnotainfoidentificacao);

	/**
* Delete nfnotainfoidentificacao.
*
* @param nfnotainfoidentificacao the nfnotainfoidentificacao
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoIdentificacaoById(NFNotaInfoIdentificacao nfnotainfoidentificacao);

	/**
* Delete all nfnotainfoidentificacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoIdentificacaos();

	/**
* Fetch all nfnotainfoidentificacaos.
*
* @return the list< nfnotainfoidentificacao>
*/
	public InternalResultsResponse<NFNotaInfoIdentificacao> fetchAllNFNotaInfoIdentificacaos(NFNotaInfoIdentificacao  nfnotainfoidentificacao);

	/**
* Fetch nfnotainfoidentificacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoIdentificacao> fetchNFNotaInfoIdentificacaosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfinfomodelo1por1areferenciada by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFInfoModelo1Por1AReferenciada fetchNFInfoModelo1Por1AReferenciadaById(FetchByIdRequest request);

	/**
* Insert nfinfomodelo1por1areferenciada.
*
* @param nfinfomodelo1por1areferenciada the nfinfomodelo1por1areferenciada
*
* @return the internal response
*/
	public InternalResponse insertNFInfoModelo1Por1AReferenciada(NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada);

	/**
* Update nfinfomodelo1por1areferenciada.
*
* @param nfinfomodelo1por1areferenciada the nfinfomodelo1por1areferenciada
*
* @return the internal response
*/
	public InternalResponse updateNFInfoModelo1Por1AReferenciada(NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada);

	/**
* Delete nfinfomodelo1por1areferenciada.
*
* @param nfinfomodelo1por1areferenciada the nfinfomodelo1por1areferenciada
*
* @return the internal response
*/
	public InternalResponse deleteNFInfoModelo1Por1AReferenciadaById(NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada);

	/**
* Delete all nfinfomodelo1por1areferenciadas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFInfoModelo1Por1AReferenciadas();

	/**
* Fetch all nfinfomodelo1por1areferenciadas.
*
* @return the list< nfinfomodelo1por1areferenciada>
*/
	public InternalResultsResponse<NFInfoModelo1Por1AReferenciada> fetchAllNFInfoModelo1Por1AReferenciadas(NFInfoModelo1Por1AReferenciada  nfinfomodelo1por1areferenciada);

	/**
* Fetch nfinfomodelo1por1areferenciadas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFInfoModelo1Por1AReferenciada> fetchNFInfoModelo1Por1AReferenciadasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfinforeferenciada by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFInfoReferenciada fetchNFInfoReferenciadaById(FetchByIdRequest request);

	/**
* Insert nfinforeferenciada.
*
* @param nfinforeferenciada the nfinforeferenciada
*
* @return the internal response
*/
	public InternalResponse insertNFInfoReferenciada(NFInfoReferenciada nfinforeferenciada);

	/**
* Update nfinforeferenciada.
*
* @param nfinforeferenciada the nfinforeferenciada
*
* @return the internal response
*/
	public InternalResponse updateNFInfoReferenciada(NFInfoReferenciada nfinforeferenciada);

	/**
* Delete nfinforeferenciada.
*
* @param nfinforeferenciada the nfinforeferenciada
*
* @return the internal response
*/
	public InternalResponse deleteNFInfoReferenciadaById(NFInfoReferenciada nfinforeferenciada);

	/**
* Delete all nfinforeferenciadas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFInfoReferenciadas();

	/**
* Fetch all nfinforeferenciadas.
*
* @return the list< nfinforeferenciada>
*/
	public InternalResultsResponse<NFInfoReferenciada> fetchAllNFInfoReferenciadas(NFInfoReferenciada  nfinforeferenciada);

	/**
* Fetch nfinforeferenciadas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFInfoReferenciada> fetchNFInfoReferenciadasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfinfoprodutorruralreferenciada by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFInfoProdutorRuralReferenciada fetchNFInfoProdutorRuralReferenciadaById(FetchByIdRequest request);

	/**
* Insert nfinfoprodutorruralreferenciada.
*
* @param nfinfoprodutorruralreferenciada the nfinfoprodutorruralreferenciada
*
* @return the internal response
*/
	public InternalResponse insertNFInfoProdutorRuralReferenciada(NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada);

	/**
* Update nfinfoprodutorruralreferenciada.
*
* @param nfinfoprodutorruralreferenciada the nfinfoprodutorruralreferenciada
*
* @return the internal response
*/
	public InternalResponse updateNFInfoProdutorRuralReferenciada(NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada);

	/**
* Delete nfinfoprodutorruralreferenciada.
*
* @param nfinfoprodutorruralreferenciada the nfinfoprodutorruralreferenciada
*
* @return the internal response
*/
	public InternalResponse deleteNFInfoProdutorRuralReferenciadaById(NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada);

	/**
* Delete all nfinfoprodutorruralreferenciadas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFInfoProdutorRuralReferenciadas();

	/**
* Fetch all nfinfoprodutorruralreferenciadas.
*
* @return the list< nfinfoprodutorruralreferenciada>
*/
	public InternalResultsResponse<NFInfoProdutorRuralReferenciada> fetchAllNFInfoProdutorRuralReferenciadas(NFInfoProdutorRuralReferenciada  nfinfoprodutorruralreferenciada);

	/**
* Fetch nfinfoprodutorruralreferenciadas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFInfoProdutorRuralReferenciada> fetchNFInfoProdutorRuralReferenciadasByRequest(PagedInquiryRequest request);



	/**
* Fetch nfnotainfoemitentes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoEmitente> fetchNFNotaInfoEmitentesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoemitente by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoEmitente fetchNFNotaInfoEmitenteById(FetchByIdRequest request);

	/**
* Insert nfnotainfoemitente.
*
* @param nfnotainfoemitente the nfnotainfoemitente
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoEmitente(NFNotaInfoEmitente nfnotainfoemitente);

	/**
* Update nfnotainfoemitente.
*
* @param nfnotainfoemitente the nfnotainfoemitente
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoEmitente(NFNotaInfoEmitente nfnotainfoemitente);

	/**
* Delete nfnotainfoemitente.
*
* @param nfnotainfoemitente the nfnotainfoemitente
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoEmitenteById(NFNotaInfoEmitente nfnotainfoemitente);

	/**
* Delete all nfnotainfoemitentes.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoEmitentes();

	/**
* Fetch all nfnotainfoemitentes.
*
* @return the list< nfnotainfoemitente>
*/
	public InternalResultsResponse<NFNotaInfoEmitente> fetchAllNFNotaInfoEmitentes(NFNotaInfoEmitente  nfnotainfoemitente);


	/**
	 * Fetch nfnotainfoavulsa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoAvulsa fetchNFNotaInfoAvulsaById(FetchByIdRequest request);

	/**
* Insert nfnotainfoavulsa.
*
* @param nfnotainfoavulsa the nfnotainfoavulsa
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoAvulsa(NFNotaInfoAvulsa nfnotainfoavulsa);

	/**
* Update nfnotainfoavulsa.
*
* @param nfnotainfoavulsa the nfnotainfoavulsa
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoAvulsa(NFNotaInfoAvulsa nfnotainfoavulsa);

	/**
* Delete nfnotainfoavulsa.
*
* @param nfnotainfoavulsa the nfnotainfoavulsa
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoAvulsaById(NFNotaInfoAvulsa nfnotainfoavulsa);

	/**
* Delete all nfnotainfoavulsas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoAvulsas();

	/**
* Fetch all nfnotainfoavulsas.
*
* @return the list< nfnotainfoavulsa>
*/
	public InternalResultsResponse<NFNotaInfoAvulsa> fetchAllNFNotaInfoAvulsas(NFNotaInfoAvulsa  nfnotainfoavulsa);

	/**
* Fetch nfnotainfoavulsas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoAvulsa> fetchNFNotaInfoAvulsasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfodestinatario by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoDestinatario fetchNFNotaInfoDestinatarioById(FetchByIdRequest request);

	/**
* Insert nfnotainfodestinatario.
*
* @param nfnotainfodestinatario the nfnotainfodestinatario
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoDestinatario(NFNotaInfoDestinatario nfnotainfodestinatario);

	/**
* Update nfnotainfodestinatario.
*
* @param nfnotainfodestinatario the nfnotainfodestinatario
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoDestinatario(NFNotaInfoDestinatario nfnotainfodestinatario);

	/**
* Delete nfnotainfodestinatario.
*
* @param nfnotainfodestinatario the nfnotainfodestinatario
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoDestinatarioById(NFNotaInfoDestinatario nfnotainfodestinatario);

	/**
* Delete all nfnotainfodestinatarios.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoDestinatarios();

	/**
* Fetch all nfnotainfodestinatarios.
*
* @return the list< nfnotainfodestinatario>
*/
	public InternalResultsResponse<NFNotaInfoDestinatario> fetchAllNFNotaInfoDestinatarios(NFNotaInfoDestinatario  nfnotainfodestinatario);

	/**
* Fetch nfnotainfodestinatarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoDestinatario> fetchNFNotaInfoDestinatariosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfolocal by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoLocal fetchNFNotaInfoLocalById(FetchByIdRequest request);

	/**
* Insert nfnotainfolocal.
*
* @param nfnotainfolocal the nfnotainfolocal
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoLocal(NFNotaInfoLocal nfnotainfolocal);

	/**
* Update nfnotainfolocal.
*
* @param nfnotainfolocal the nfnotainfolocal
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoLocal(NFNotaInfoLocal nfnotainfolocal);

	/**
* Delete nfnotainfolocal.
*
* @param nfnotainfolocal the nfnotainfolocal
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoLocalById(NFNotaInfoLocal nfnotainfolocal);

	/**
* Delete all nfnotainfolocals.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoLocals();

	/**
* Fetch all nfnotainfolocals.
*
* @return the list< nfnotainfolocal>
*/
	public InternalResultsResponse<NFNotaInfoLocal> fetchAllNFNotaInfoLocals(NFNotaInfoLocal  nfnotainfolocal);

	/**
* Fetch nfnotainfolocals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoLocal> fetchNFNotaInfoLocalsByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfpessoaautorizadadownloadnfe by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFPessoaAutorizadaDownloadNFe fetchNFPessoaAutorizadaDownloadNFeById(FetchByIdRequest request);

	/**
* Insert nfpessoaautorizadadownloadnfe.
*
* @param nfpessoaautorizadadownloadnfe the nfpessoaautorizadadownloadnfe
*
* @return the internal response
*/
	public InternalResponse insertNFPessoaAutorizadaDownloadNFe(NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe);

	/**
* Update nfpessoaautorizadadownloadnfe.
*
* @param nfpessoaautorizadadownloadnfe the nfpessoaautorizadadownloadnfe
*
* @return the internal response
*/
	public InternalResponse updateNFPessoaAutorizadaDownloadNFe(NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe);

	/**
* Delete nfpessoaautorizadadownloadnfe.
*
* @param nfpessoaautorizadadownloadnfe the nfpessoaautorizadadownloadnfe
*
* @return the internal response
*/
	public InternalResponse deleteNFPessoaAutorizadaDownloadNFeById(NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe);

	/**
* Delete all nfpessoaautorizadadownloadnfes.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFPessoaAutorizadaDownloadNFes();

	/**
* Fetch all nfpessoaautorizadadownloadnfes.
*
* @return the list< nfpessoaautorizadadownloadnfe>
*/
	public InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> fetchAllNFPessoaAutorizadaDownloadNFes(NFPessoaAutorizadaDownloadNFe  nfpessoaautorizadadownloadnfe);

	/**
* Fetch nfpessoaautorizadadownloadnfes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> fetchNFPessoaAutorizadaDownloadNFesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfototal by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoTotal fetchNFNotaInfoTotalById(FetchByIdRequest request);

	/**
* Insert nfnotainfototal.
*
* @param nfnotainfototal the nfnotainfototal
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoTotal(NFNotaInfoTotal nfnotainfototal);

	/**
* Update nfnotainfototal.
*
* @param nfnotainfototal the nfnotainfototal
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoTotal(NFNotaInfoTotal nfnotainfototal);

	/**
* Delete nfnotainfototal.
*
* @param nfnotainfototal the nfnotainfototal
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoTotalById(NFNotaInfoTotal nfnotainfototal);

	/**
* Delete all nfnotainfototals.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoTotals();

	/**
* Fetch all nfnotainfototals.
*
* @return the list< nfnotainfototal>
*/
	public InternalResultsResponse<NFNotaInfoTotal> fetchAllNFNotaInfoTotals(NFNotaInfoTotal  nfnotainfototal);

	/**
* Fetch nfnotainfototals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoTotal> fetchNFNotaInfoTotalsByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoicmstotal by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoICMSTotal fetchNFNotaInfoICMSTotalById(FetchByIdRequest request);

	/**
* Insert nfnotainfoicmstotal.
*
* @param nfnotainfoicmstotal the nfnotainfoicmstotal
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoICMSTotal(NFNotaInfoICMSTotal nfnotainfoicmstotal);

	/**
* Update nfnotainfoicmstotal.
*
* @param nfnotainfoicmstotal the nfnotainfoicmstotal
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoICMSTotal(NFNotaInfoICMSTotal nfnotainfoicmstotal);

	/**
* Delete nfnotainfoicmstotal.
*
* @param nfnotainfoicmstotal the nfnotainfoicmstotal
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoICMSTotalById(NFNotaInfoICMSTotal nfnotainfoicmstotal);

	/**
* Delete all nfnotainfoicmstotals.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoICMSTotals();

	/**
* Fetch all nfnotainfoicmstotals.
*
* @return the list< nfnotainfoicmstotal>
*/
	public InternalResultsResponse<NFNotaInfoICMSTotal> fetchAllNFNotaInfoICMSTotals(NFNotaInfoICMSTotal  nfnotainfoicmstotal);

	/**
* Fetch nfnotainfoicmstotals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoICMSTotal> fetchNFNotaInfoICMSTotalsByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoissqntotal by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoISSQNTotal fetchNFNotaInfoISSQNTotalById(FetchByIdRequest request);

	/**
* Insert nfnotainfoissqntotal.
*
* @param nfnotainfoissqntotal the nfnotainfoissqntotal
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoISSQNTotal(NFNotaInfoISSQNTotal nfnotainfoissqntotal);

	/**
* Update nfnotainfoissqntotal.
*
* @param nfnotainfoissqntotal the nfnotainfoissqntotal
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoISSQNTotal(NFNotaInfoISSQNTotal nfnotainfoissqntotal);

	/**
* Delete nfnotainfoissqntotal.
*
* @param nfnotainfoissqntotal the nfnotainfoissqntotal
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoISSQNTotalById(NFNotaInfoISSQNTotal nfnotainfoissqntotal);

	/**
* Delete all nfnotainfoissqntotals.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoISSQNTotals();

	/**
* Fetch all nfnotainfoissqntotals.
*
* @return the list< nfnotainfoissqntotal>
*/
	public InternalResultsResponse<NFNotaInfoISSQNTotal> fetchAllNFNotaInfoISSQNTotals(NFNotaInfoISSQNTotal  nfnotainfoissqntotal);

	/**
* Fetch nfnotainfoissqntotals by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoISSQNTotal> fetchNFNotaInfoISSQNTotalsByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainforetencoestributos by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoRetencoesTributos fetchNFNotaInfoRetencoesTributosById(FetchByIdRequest request);

	/**
* Insert nfnotainforetencoestributos.
*
* @param nfnotainforetencoestributos the nfnotainforetencoestributos
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoRetencoesTributos(NFNotaInfoRetencoesTributos nfnotainforetencoestributos);

	/**
* Update nfnotainforetencoestributos.
*
* @param nfnotainforetencoestributos the nfnotainforetencoestributos
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoRetencoesTributos(NFNotaInfoRetencoesTributos nfnotainforetencoestributos);

	/**
* Delete nfnotainforetencoestributos.
*
* @param nfnotainforetencoestributos the nfnotainforetencoestributos
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoRetencoesTributosById(NFNotaInfoRetencoesTributos nfnotainforetencoestributos);

	/**
* Delete all nfnotainforetencoestributoss.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoRetencoesTributoss();

	/**
* Fetch all nfnotainforetencoestributoss.
*
* @return the list< nfnotainforetencoestributos>
*/
	public InternalResultsResponse<NFNotaInfoRetencoesTributos> fetchAllNFNotaInfoRetencoesTributoss(NFNotaInfoRetencoesTributos  nfnotainforetencoestributos);

	/**
* Fetch nfnotainforetencoestributoss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoRetencoesTributos> fetchNFNotaInfoRetencoesTributossByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfotransporte by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoTransporte fetchNFNotaInfoTransporteById(FetchByIdRequest request);

	/**
* Insert nfnotainfotransporte.
*
* @param nfnotainfotransporte the nfnotainfotransporte
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoTransporte(NFNotaInfoTransporte nfnotainfotransporte);

	/**
* Update nfnotainfotransporte.
*
* @param nfnotainfotransporte the nfnotainfotransporte
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoTransporte(NFNotaInfoTransporte nfnotainfotransporte);

	/**
* Delete nfnotainfotransporte.
*
* @param nfnotainfotransporte the nfnotainfotransporte
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoTransporteById(NFNotaInfoTransporte nfnotainfotransporte);

	/**
* Delete all nfnotainfotransportes.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoTransportes();

	/**
* Fetch all nfnotainfotransportes.
*
* @return the list< nfnotainfotransporte>
*/
	public InternalResultsResponse<NFNotaInfoTransporte> fetchAllNFNotaInfoTransportes(NFNotaInfoTransporte  nfnotainfotransporte);

	/**
* Fetch nfnotainfotransportes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoTransporte> fetchNFNotaInfoTransportesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainforetencaoicmstransporte by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoRetencaoICMSTransporte fetchNFNotaInfoRetencaoICMSTransporteById(FetchByIdRequest request);

	/**
* Insert nfnotainforetencaoicmstransporte.
*
* @param nfnotainforetencaoicmstransporte the nfnotainforetencaoicmstransporte
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoRetencaoICMSTransporte(NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte);

	/**
* Update nfnotainforetencaoicmstransporte.
*
* @param nfnotainforetencaoicmstransporte the nfnotainforetencaoicmstransporte
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoRetencaoICMSTransporte(NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte);

	/**
* Delete nfnotainforetencaoicmstransporte.
*
* @param nfnotainforetencaoicmstransporte the nfnotainforetencaoicmstransporte
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoRetencaoICMSTransporteById(NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte);

	/**
* Delete all nfnotainforetencaoicmstransportes.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoRetencaoICMSTransportes();

	/**
* Fetch all nfnotainforetencaoicmstransportes.
*
* @return the list< nfnotainforetencaoicmstransporte>
*/
	public InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> fetchAllNFNotaInfoRetencaoICMSTransportes(NFNotaInfoRetencaoICMSTransporte  nfnotainforetencaoicmstransporte);

	/**
* Fetch nfnotainforetencaoicmstransportes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> fetchNFNotaInfoRetencaoICMSTransportesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfotransportador by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoTransportador fetchNFNotaInfoTransportadorById(FetchByIdRequest request);

	/**
* Insert nfnotainfotransportador.
*
* @param nfnotainfotransportador the nfnotainfotransportador
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoTransportador(NFNotaInfoTransportador nfnotainfotransportador);

	/**
* Update nfnotainfotransportador.
*
* @param nfnotainfotransportador the nfnotainfotransportador
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoTransportador(NFNotaInfoTransportador nfnotainfotransportador);

	/**
* Delete nfnotainfotransportador.
*
* @param nfnotainfotransportador the nfnotainfotransportador
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoTransportadorById(NFNotaInfoTransportador nfnotainfotransportador);

	/**
* Delete all nfnotainfotransportadors.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoTransportadors();

	/**
* Fetch all nfnotainfotransportadors.
*
* @return the list< nfnotainfotransportador>
*/
	public InternalResultsResponse<NFNotaInfoTransportador> fetchAllNFNotaInfoTransportadors(NFNotaInfoTransportador  nfnotainfotransportador);

	/**
* Fetch nfnotainfotransportadors by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoTransportador> fetchNFNotaInfoTransportadorsByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoveiculo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoVeiculo fetchNFNotaInfoVeiculoById(FetchByIdRequest request);

	/**
* Insert nfnotainfoveiculo.
*
* @param nfnotainfoveiculo the nfnotainfoveiculo
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoVeiculo(NFNotaInfoVeiculo nfnotainfoveiculo);

	/**
* Update nfnotainfoveiculo.
*
* @param nfnotainfoveiculo the nfnotainfoveiculo
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoVeiculo(NFNotaInfoVeiculo nfnotainfoveiculo);

	/**
* Delete nfnotainfoveiculo.
*
* @param nfnotainfoveiculo the nfnotainfoveiculo
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoVeiculoById(NFNotaInfoVeiculo nfnotainfoveiculo);

	/**
* Delete all nfnotainfoveiculos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoVeiculos();

	/**
* Fetch all nfnotainfoveiculos.
*
* @return the list< nfnotainfoveiculo>
*/
	public InternalResultsResponse<NFNotaInfoVeiculo> fetchAllNFNotaInfoVeiculos(NFNotaInfoVeiculo  nfnotainfoveiculo);

	/**
* Fetch nfnotainfoveiculos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoVeiculo> fetchNFNotaInfoVeiculosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainforeboque by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoReboque fetchNFNotaInfoReboqueById(FetchByIdRequest request);

	/**
* Insert nfnotainforeboque.
*
* @param nfnotainforeboque the nfnotainforeboque
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoReboque(NFNotaInfoReboque nfnotainforeboque);

	/**
* Update nfnotainforeboque.
*
* @param nfnotainforeboque the nfnotainforeboque
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoReboque(NFNotaInfoReboque nfnotainforeboque);

	/**
* Delete nfnotainforeboque.
*
* @param nfnotainforeboque the nfnotainforeboque
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoReboqueById(NFNotaInfoReboque nfnotainforeboque);

	/**
* Delete all nfnotainforeboques.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoReboques();

	/**
* Fetch all nfnotainforeboques.
*
* @return the list< nfnotainforeboque>
*/
	public InternalResultsResponse<NFNotaInfoReboque> fetchAllNFNotaInfoReboques(NFNotaInfoReboque  nfnotainforeboque);

	/**
* Fetch nfnotainforeboques by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoReboque> fetchNFNotaInfoReboquesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfocobranca by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoCobranca fetchNFNotaInfoCobrancaById(FetchByIdRequest request);

	/**
* Insert nfnotainfocobranca.
*
* @param nfnotainfocobranca the nfnotainfocobranca
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoCobranca(NFNotaInfoCobranca nfnotainfocobranca);

	/**
* Update nfnotainfocobranca.
*
* @param nfnotainfocobranca the nfnotainfocobranca
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoCobranca(NFNotaInfoCobranca nfnotainfocobranca);

	/**
* Delete nfnotainfocobranca.
*
* @param nfnotainfocobranca the nfnotainfocobranca
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoCobrancaById(NFNotaInfoCobranca nfnotainfocobranca);

	/**
* Delete all nfnotainfocobrancas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoCobrancas();

	/**
* Fetch all nfnotainfocobrancas.
*
* @return the list< nfnotainfocobranca>
*/
	public InternalResultsResponse<NFNotaInfoCobranca> fetchAllNFNotaInfoCobrancas(NFNotaInfoCobranca  nfnotainfocobranca);

	/**
* Fetch nfnotainfocobrancas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoCobranca> fetchNFNotaInfoCobrancasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoduplicata by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoDuplicata fetchNFNotaInfoDuplicataById(FetchByIdRequest request);

	/**
* Insert nfnotainfoduplicata.
*
* @param nfnotainfoduplicata the nfnotainfoduplicata
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoDuplicata(NFNotaInfoDuplicata nfnotainfoduplicata);

	/**
* Update nfnotainfoduplicata.
*
* @param nfnotainfoduplicata the nfnotainfoduplicata
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoDuplicata(NFNotaInfoDuplicata nfnotainfoduplicata);

	/**
* Delete nfnotainfoduplicata.
*
* @param nfnotainfoduplicata the nfnotainfoduplicata
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoDuplicataById(NFNotaInfoDuplicata nfnotainfoduplicata);

	/**
* Delete all nfnotainfoduplicatas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoDuplicatas();

	/**
* Fetch all nfnotainfoduplicatas.
*
* @return the list< nfnotainfoduplicata>
*/
	public InternalResultsResponse<NFNotaInfoDuplicata> fetchAllNFNotaInfoDuplicatas(NFNotaInfoDuplicata  nfnotainfoduplicata);

	/**
* Fetch nfnotainfoduplicatas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoDuplicata> fetchNFNotaInfoDuplicatasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfofatura by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoFatura fetchNFNotaInfoFaturaById(FetchByIdRequest request);

	/**
* Insert nfnotainfofatura.
*
* @param nfnotainfofatura the nfnotainfofatura
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoFatura(NFNotaInfoFatura nfnotainfofatura);

	/**
* Update nfnotainfofatura.
*
* @param nfnotainfofatura the nfnotainfofatura
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoFatura(NFNotaInfoFatura nfnotainfofatura);

	/**
* Delete nfnotainfofatura.
*
* @param nfnotainfofatura the nfnotainfofatura
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoFaturaById(NFNotaInfoFatura nfnotainfofatura);

	/**
* Delete all nfnotainfofaturas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoFaturas();

	/**
* Fetch all nfnotainfofaturas.
*
* @return the list< nfnotainfofatura>
*/
	public InternalResultsResponse<NFNotaInfoFatura> fetchAllNFNotaInfoFaturas(NFNotaInfoFatura  nfnotainfofatura);

	/**
* Fetch nfnotainfofaturas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoFatura> fetchNFNotaInfoFaturasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfocartao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoCartao fetchNFNotaInfoCartaoById(FetchByIdRequest request);

	/**
* Insert nfnotainfocartao.
*
* @param nfnotainfocartao the nfnotainfocartao
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoCartao(NFNotaInfoCartao nfnotainfocartao);

	/**
* Update nfnotainfocartao.
*
* @param nfnotainfocartao the nfnotainfocartao
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoCartao(NFNotaInfoCartao nfnotainfocartao);

	/**
* Delete nfnotainfocartao.
*
* @param nfnotainfocartao the nfnotainfocartao
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoCartaoById(NFNotaInfoCartao nfnotainfocartao);

	/**
* Delete all nfnotainfocartaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoCartaos();

	/**
* Fetch all nfnotainfocartaos.
*
* @return the list< nfnotainfocartao>
*/
	public InternalResultsResponse<NFNotaInfoCartao> fetchAllNFNotaInfoCartaos(NFNotaInfoCartao  nfnotainfocartao);

	/**
* Fetch nfnotainfocartaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoCartao> fetchNFNotaInfoCartaosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfopagamento by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoPagamento fetchNFNotaInfoPagamentoById(FetchByIdRequest request);

	/**
* Insert nfnotainfopagamento.
*
* @param nfnotainfopagamento the nfnotainfopagamento
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoPagamento(NFNotaInfoPagamento nfnotainfopagamento);

	/**
* Update nfnotainfopagamento.
*
* @param nfnotainfopagamento the nfnotainfopagamento
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoPagamento(NFNotaInfoPagamento nfnotainfopagamento);

	/**
* Delete nfnotainfopagamento.
*
* @param nfnotainfopagamento the nfnotainfopagamento
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoPagamentoById(NFNotaInfoPagamento nfnotainfopagamento);

	/**
* Delete all nfnotainfopagamentos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoPagamentos();

	/**
* Fetch all nfnotainfopagamentos.
*
* @return the list< nfnotainfopagamento>
*/
	public InternalResultsResponse<NFNotaInfoPagamento> fetchAllNFNotaInfoPagamentos(NFNotaInfoPagamento  nfnotainfopagamento);

	/**
* Fetch nfnotainfopagamentos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoPagamento> fetchNFNotaInfoPagamentosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoinformacoesadicionais by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoInformacoesAdicionais fetchNFNotaInfoInformacoesAdicionaisById(FetchByIdRequest request);

	/**
* Insert nfnotainfoinformacoesadicionais.
*
* @param nfnotainfoinformacoesadicionais the nfnotainfoinformacoesadicionais
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoInformacoesAdicionais(NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais);

	/**
* Update nfnotainfoinformacoesadicionais.
*
* @param nfnotainfoinformacoesadicionais the nfnotainfoinformacoesadicionais
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoInformacoesAdicionais(NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais);

	/**
* Delete nfnotainfoinformacoesadicionais.
*
* @param nfnotainfoinformacoesadicionais the nfnotainfoinformacoesadicionais
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoInformacoesAdicionaisById(NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais);

	/**
* Delete all nfnotainfoinformacoesadicionaiss.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoInformacoesAdicionaiss();

	/**
* Fetch all nfnotainfoinformacoesadicionaiss.
*
* @return the list< nfnotainfoinformacoesadicionais>
*/
	public InternalResultsResponse<NFNotaInfoInformacoesAdicionais> fetchAllNFNotaInfoInformacoesAdicionaiss(NFNotaInfoInformacoesAdicionais  nfnotainfoinformacoesadicionais);

	/**
* Fetch nfnotainfoinformacoesadicionaiss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoInformacoesAdicionais> fetchNFNotaInfoInformacoesAdicionaissByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoobservacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoObservacao fetchNFNotaInfoObservacaoById(FetchByIdRequest request);

	/**
* Insert nfnotainfoobservacao.
*
* @param nfnotainfoobservacao the nfnotainfoobservacao
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoObservacao(NFNotaInfoObservacao nfnotainfoobservacao);

	/**
* Update nfnotainfoobservacao.
*
* @param nfnotainfoobservacao the nfnotainfoobservacao
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoObservacao(NFNotaInfoObservacao nfnotainfoobservacao);

	/**
* Delete nfnotainfoobservacao.
*
* @param nfnotainfoobservacao the nfnotainfoobservacao
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoObservacaoById(NFNotaInfoObservacao nfnotainfoobservacao);

	/**
* Delete all nfnotainfoobservacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoObservacaos();

	/**
* Fetch all nfnotainfoobservacaos.
*
* @return the list< nfnotainfoobservacao>
*/
	public InternalResultsResponse<NFNotaInfoObservacao> fetchAllNFNotaInfoObservacaos(NFNotaInfoObservacao  nfnotainfoobservacao);

	/**
* Fetch nfnotainfoobservacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoObservacao> fetchNFNotaInfoObservacaosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoprocessoreferenciado by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoProcessoReferenciado fetchNFNotaInfoProcessoReferenciadoById(FetchByIdRequest request);

	/**
* Insert nfnotainfoprocessoreferenciado.
*
* @param nfnotainfoprocessoreferenciado the nfnotainfoprocessoreferenciado
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoProcessoReferenciado(NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado);

	/**
* Update nfnotainfoprocessoreferenciado.
*
* @param nfnotainfoprocessoreferenciado the nfnotainfoprocessoreferenciado
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoProcessoReferenciado(NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado);

	/**
* Delete nfnotainfoprocessoreferenciado.
*
* @param nfnotainfoprocessoreferenciado the nfnotainfoprocessoreferenciado
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoProcessoReferenciadoById(NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado);

	/**
* Delete all nfnotainfoprocessoreferenciados.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoProcessoReferenciados();

	/**
* Fetch all nfnotainfoprocessoreferenciados.
*
* @return the list< nfnotainfoprocessoreferenciado>
*/
	public InternalResultsResponse<NFNotaInfoProcessoReferenciado> fetchAllNFNotaInfoProcessoReferenciados(NFNotaInfoProcessoReferenciado  nfnotainfoprocessoreferenciado);

	/**
* Fetch nfnotainfoprocessoreferenciados by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoProcessoReferenciado> fetchNFNotaInfoProcessoReferenciadosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoexportacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoExportacao fetchNFNotaInfoExportacaoById(FetchByIdRequest request);

	/**
* Insert nfnotainfoexportacao.
*
* @param nfnotainfoexportacao the nfnotainfoexportacao
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoExportacao(NFNotaInfoExportacao nfnotainfoexportacao);

	/**
* Update nfnotainfoexportacao.
*
* @param nfnotainfoexportacao the nfnotainfoexportacao
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoExportacao(NFNotaInfoExportacao nfnotainfoexportacao);

	/**
* Delete nfnotainfoexportacao.
*
* @param nfnotainfoexportacao the nfnotainfoexportacao
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoExportacaoById(NFNotaInfoExportacao nfnotainfoexportacao);

	/**
* Delete all nfnotainfoexportacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoExportacaos();

	/**
* Fetch all nfnotainfoexportacaos.
*
* @return the list< nfnotainfoexportacao>
*/
	public InternalResultsResponse<NFNotaInfoExportacao> fetchAllNFNotaInfoExportacaos(NFNotaInfoExportacao  nfnotainfoexportacao);

	/**
* Fetch nfnotainfoexportacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoExportacao> fetchNFNotaInfoExportacaosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfocompra by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoCompra fetchNFNotaInfoCompraById(FetchByIdRequest request);

	/**
* Insert nfnotainfocompra.
*
* @param nfnotainfocompra the nfnotainfocompra
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoCompra(NFNotaInfoCompra nfnotainfocompra);

	/**
* Update nfnotainfocompra.
*
* @param nfnotainfocompra the nfnotainfocompra
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoCompra(NFNotaInfoCompra nfnotainfocompra);

	/**
* Delete nfnotainfocompra.
*
* @param nfnotainfocompra the nfnotainfocompra
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoCompraById(NFNotaInfoCompra nfnotainfocompra);

	/**
* Delete all nfnotainfocompras.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoCompras();

	/**
* Fetch all nfnotainfocompras.
*
* @return the list< nfnotainfocompra>
*/
	public InternalResultsResponse<NFNotaInfoCompra> fetchAllNFNotaInfoCompras(NFNotaInfoCompra  nfnotainfocompra);

	/**
* Fetch nfnotainfocompras by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoCompra> fetchNFNotaInfoComprasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfocana by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoCana fetchNFNotaInfoCanaById(FetchByIdRequest request);

	/**
* Insert nfnotainfocana.
*
* @param nfnotainfocana the nfnotainfocana
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoCana(NFNotaInfoCana nfnotainfocana);

	/**
* Update nfnotainfocana.
*
* @param nfnotainfocana the nfnotainfocana
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoCana(NFNotaInfoCana nfnotainfocana);

	/**
* Delete nfnotainfocana.
*
* @param nfnotainfocana the nfnotainfocana
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoCanaById(NFNotaInfoCana nfnotainfocana);

	/**
* Delete all nfnotainfocanas.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoCanas();

	/**
* Fetch all nfnotainfocanas.
*
* @return the list< nfnotainfocana>
*/
	public InternalResultsResponse<NFNotaInfoCana> fetchAllNFNotaInfoCanas(NFNotaInfoCana  nfnotainfocana);

	/**
* Fetch nfnotainfocanas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoCana> fetchNFNotaInfoCanasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfocanafornecimentodiario by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoCanaFornecimentoDiario fetchNFNotaInfoCanaFornecimentoDiarioById(FetchByIdRequest request);

	/**
* Insert nfnotainfocanafornecimentodiario.
*
* @param nfnotainfocanafornecimentodiario the nfnotainfocanafornecimentodiario
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoCanaFornecimentoDiario(NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario);

	/**
* Update nfnotainfocanafornecimentodiario.
*
* @param nfnotainfocanafornecimentodiario the nfnotainfocanafornecimentodiario
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoCanaFornecimentoDiario(NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario);

	/**
* Delete nfnotainfocanafornecimentodiario.
*
* @param nfnotainfocanafornecimentodiario the nfnotainfocanafornecimentodiario
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoCanaFornecimentoDiarioById(NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario);

	/**
* Delete all nfnotainfocanafornecimentodiarios.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoCanaFornecimentoDiarios();

	/**
* Fetch all nfnotainfocanafornecimentodiarios.
*
* @return the list< nfnotainfocanafornecimentodiario>
*/
	public InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> fetchAllNFNotaInfoCanaFornecimentoDiarios(NFNotaInfoCanaFornecimentoDiario  nfnotainfocanafornecimentodiario);

	/**
* Fetch nfnotainfocanafornecimentodiarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> fetchNFNotaInfoCanaFornecimentoDiariosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfocanadeducao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoCanaDeducao fetchNFNotaInfoCanaDeducaoById(FetchByIdRequest request);

	/**
* Insert nfnotainfocanadeducao.
*
* @param nfnotainfocanadeducao the nfnotainfocanadeducao
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoCanaDeducao(NFNotaInfoCanaDeducao nfnotainfocanadeducao);

	/**
* Update nfnotainfocanadeducao.
*
* @param nfnotainfocanadeducao the nfnotainfocanadeducao
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoCanaDeducao(NFNotaInfoCanaDeducao nfnotainfocanadeducao);

	/**
* Delete nfnotainfocanadeducao.
*
* @param nfnotainfocanadeducao the nfnotainfocanadeducao
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoCanaDeducaoById(NFNotaInfoCanaDeducao nfnotainfocanadeducao);

	/**
* Delete all nfnotainfocanadeducaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoCanaDeducaos();

	/**
* Fetch all nfnotainfocanadeducaos.
*
* @return the list< nfnotainfocanadeducao>
*/
	public InternalResultsResponse<NFNotaInfoCanaDeducao> fetchAllNFNotaInfoCanaDeducaos(NFNotaInfoCanaDeducao  nfnotainfocanadeducao);

	/**
* Fetch nfnotainfocanadeducaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoCanaDeducao> fetchNFNotaInfoCanaDeducaosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfosuplementar by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public NFNotaInfoSuplementar fetchNFNotaInfoSuplementarById(FetchByIdRequest request);

	/**
* Insert nfnotainfosuplementar.
*
* @param nfnotainfosuplementar the nfnotainfosuplementar
*
* @return the internal response
*/
	public InternalResponse insertNFNotaInfoSuplementar(NFNotaInfoSuplementar nfnotainfosuplementar);

	/**
* Update nfnotainfosuplementar.
*
* @param nfnotainfosuplementar the nfnotainfosuplementar
*
* @return the internal response
*/
	public InternalResponse updateNFNotaInfoSuplementar(NFNotaInfoSuplementar nfnotainfosuplementar);

	/**
* Delete nfnotainfosuplementar.
*
* @param nfnotainfosuplementar the nfnotainfosuplementar
*
* @return the internal response
*/
	public InternalResponse deleteNFNotaInfoSuplementarById(NFNotaInfoSuplementar nfnotainfosuplementar);

	/**
* Delete all nfnotainfosuplementars.
*
* @return the internal response
*/
	public InternalResponse deleteAllNFNotaInfoSuplementars();

	/**
* Fetch all nfnotainfosuplementars.
*
* @return the list< nfnotainfosuplementar>
*/
	public InternalResultsResponse<NFNotaInfoSuplementar> fetchAllNFNotaInfoSuplementars(NFNotaInfoSuplementar  nfnotainfosuplementar);

	/**
* Fetch nfnotainfosuplementars by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNotaInfoSuplementar> fetchNFNotaInfoSuplementarsByRequest(PagedInquiryRequest request);

}
