/** create by system gera-java version 1.0.0 03/12/2016 15:59 : 25*/
package com.qat.samples.sysmgmt.bar.Nfe;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nfeItens.model.NFImpostoDevolvido;
import com.qat.samples.sysmgmt.nfeItens.model.NFInformacaoImpostoDevolvido;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItem;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemDetalheExportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemExportacaoIndireta;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImposto;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSAliquota;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSNaoTributavel;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSOutrasOperacoes;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSQuantidade;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoCOFINSST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS00;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS10;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS20;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS30;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS40;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS51;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS60;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS70;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMS90;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSPartilhado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN101;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN102;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN201;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN202;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN500;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSSN900;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoICMSUFDestino;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPI;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPINaoTributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoIPITributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoISSQN;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoImportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPIS;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISAliquota;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISNaoTributado;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISOutrasOperacoes;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISQuantidade;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemImpostoPISST;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProduto;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoArmamento;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoCombustivel;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoCombustivelCIDE;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoDeclaracaoImportacao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoMedicamento;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoVeiculo;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface NFNotaInfoItemBAR.. (Data Access Component - DAC)
 */
public interface INFNotaInfoItemBAR {

	/**
	 * Fetch nfnotainfoitem by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItem fetchNFNotaInfoItemById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitem.
	 * 
	 * @param nfnotainfoitem
	 *            the nfnotainfoitem
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItem(NFNotaInfoItem nfnotainfoitem);

	/**
	 * Update nfnotainfoitem.
	 * 
	 * @param nfnotainfoitem
	 *            the nfnotainfoitem
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItem(NFNotaInfoItem nfnotainfoitem);

	/**
	 * Delete nfnotainfoitem.
	 * 
	 * @param nfnotainfoitem
	 *            the nfnotainfoitem
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemById(NFNotaInfoItem nfnotainfoitem);

	/**
	 * Delete all nfnotainfoitems.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItems();

	/**
	 * Fetch all nfnotainfoitems.
	 * 
	 * @return the list< nfnotainfoitem>
	 */
	public InternalResultsResponse<NFNotaInfoItem> fetchAllNFNotaInfoItems(NFNotaInfoItem nfnotainfoitem);

	/**
	 * Fetch nfnotainfoitems by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItem> fetchNFNotaInfoItemsByRequest(PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemproduto by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProduto fetchNFNotaInfoItemProdutoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemproduto.
	 * 
	 * @param nfnotainfoitemproduto
	 *            the nfnotainfoitemproduto
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProduto(NFNotaInfoItemProduto nfnotainfoitemproduto);

	/**
	 * Update nfnotainfoitemproduto.
	 * 
	 * @param nfnotainfoitemproduto
	 *            the nfnotainfoitemproduto
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProduto(NFNotaInfoItemProduto nfnotainfoitemproduto);

	/**
	 * Delete nfnotainfoitemproduto.
	 * 
	 * @param nfnotainfoitemproduto
	 *            the nfnotainfoitemproduto
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoById(NFNotaInfoItemProduto nfnotainfoitemproduto);

	/**
	 * Delete all nfnotainfoitemprodutos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutos();

	/**
	 * Fetch all nfnotainfoitemprodutos.
	 * 
	 * @return the list< nfnotainfoitemproduto>
	 */
	public InternalResultsResponse<NFNotaInfoItemProduto> fetchAllNFNotaInfoItemProdutos(
			NFNotaInfoItemProduto nfnotainfoitemproduto);
	
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(
			PagedInquiryRequest request);
	/**
	 * Fetch nfnotainfoitemprodutos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemProduto> fetchNFNotaInfoItemProdutosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemprodutodeclaracaoimportacao by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProdutoDeclaracaoImportacao fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(
			FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemprodutodeclaracaoimportacao.
	 * 
	 * @param nfnotainfoitemprodutodeclaracaoimportacao
	 *            the nfnotainfoitemprodutodeclaracaoimportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProdutoDeclaracaoImportacao(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao);

	/**
	 * Update nfnotainfoitemprodutodeclaracaoimportacao.
	 * 
	 * @param nfnotainfoitemprodutodeclaracaoimportacao
	 *            the nfnotainfoitemprodutodeclaracaoimportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProdutoDeclaracaoImportacao(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao);

	/**
	 * Delete nfnotainfoitemprodutodeclaracaoimportacao.
	 * 
	 * @param nfnotainfoitemprodutodeclaracaoimportacao
	 *            the nfnotainfoitemprodutodeclaracaoimportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoDeclaracaoImportacaoById(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao);

	/**
	 * Delete all nfnotainfoitemprodutodeclaracaoimportacaos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaos();

	/**
	 * Fetch all nfnotainfoitemprodutodeclaracaoimportacaos.
	 * 
	 * @return the list< nfnotainfoitemprodutodeclaracaoimportacao>
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaos(
			NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao);

	/**
	 * Fetch nfnotainfoitemprodutodeclaracaoimportacaos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemprodutodeclaracaoimportacaoadicao by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(
			FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemprodutodeclaracaoimportacaoadicao.
	 * 
	 * @param nfnotainfoitemprodutodeclaracaoimportacaoadicao
	 *            the nfnotainfoitemprodutodeclaracaoimportacaoadicao
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao);

	/**
	 * Update nfnotainfoitemprodutodeclaracaoimportacaoadicao.
	 * 
	 * @param nfnotainfoitemprodutodeclaracaoimportacaoadicao
	 *            the nfnotainfoitemprodutodeclaracaoimportacaoadicao
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao);

	/**
	 * Delete nfnotainfoitemprodutodeclaracaoimportacaoadicao.
	 * 
	 * @param nfnotainfoitemprodutodeclaracaoimportacaoadicao
	 *            the nfnotainfoitemprodutodeclaracaoimportacaoadicao
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao);

	/**
	 * Delete all nfnotainfoitemprodutodeclaracaoimportacaoadicaos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos();

	/**
	 * Fetch all nfnotainfoitemprodutodeclaracaoimportacaoadicaos.
	 * 
	 * @return the list< nfnotainfoitemprodutodeclaracaoimportacaoadicao>
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos(
			NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao);

	/**
	 * Fetch nfnotainfoitemprodutodeclaracaoimportacaoadicaos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemdetalheexportacao by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemDetalheExportacao fetchNFNotaInfoItemDetalheExportacaoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemdetalheexportacao.
	 * 
	 * @param nfnotainfoitemdetalheexportacao
	 *            the nfnotainfoitemdetalheexportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemDetalheExportacao(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao);

	/**
	 * Update nfnotainfoitemdetalheexportacao.
	 * 
	 * @param nfnotainfoitemdetalheexportacao
	 *            the nfnotainfoitemdetalheexportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemDetalheExportacao(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao);

	/**
	 * Delete nfnotainfoitemdetalheexportacao.
	 * 
	 * @param nfnotainfoitemdetalheexportacao
	 *            the nfnotainfoitemdetalheexportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemDetalheExportacaoById(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao);

	/**
	 * Delete all nfnotainfoitemdetalheexportacaos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemDetalheExportacaos();

	/**
	 * Fetch all nfnotainfoitemdetalheexportacaos.
	 * 
	 * @return the list< nfnotainfoitemdetalheexportacao>
	 */
	public InternalResultsResponse<NFNotaInfoItemDetalheExportacao> fetchAllNFNotaInfoItemDetalheExportacaos(
			NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao);

	/**
	 * Fetch nfnotainfoitemdetalheexportacaos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemDetalheExportacao> fetchNFNotaInfoItemDetalheExportacaosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemexportacaoindireta by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemExportacaoIndireta fetchNFNotaInfoItemExportacaoIndiretaById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemexportacaoindireta.
	 * 
	 * @param nfnotainfoitemexportacaoindireta
	 *            the nfnotainfoitemexportacaoindireta
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemExportacaoIndireta(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta);

	/**
	 * Update nfnotainfoitemexportacaoindireta.
	 * 
	 * @param nfnotainfoitemexportacaoindireta
	 *            the nfnotainfoitemexportacaoindireta
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemExportacaoIndireta(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta);

	/**
	 * Delete nfnotainfoitemexportacaoindireta.
	 * 
	 * @param nfnotainfoitemexportacaoindireta
	 *            the nfnotainfoitemexportacaoindireta
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemExportacaoIndiretaById(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta);

	/**
	 * Delete all nfnotainfoitemexportacaoindiretas.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemExportacaoIndiretas();

	/**
	 * Fetch all nfnotainfoitemexportacaoindiretas.
	 * 
	 * @return the list< nfnotainfoitemexportacaoindireta>
	 */
	public InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> fetchAllNFNotaInfoItemExportacaoIndiretas(
			NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta);

	/**
	 * Fetch nfnotainfoitemexportacaoindiretas by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> fetchNFNotaInfoItemExportacaoIndiretasByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemprodutoveiculo by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProdutoVeiculo fetchNFNotaInfoItemProdutoVeiculoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemprodutoveiculo.
	 * 
	 * @param nfnotainfoitemprodutoveiculo
	 *            the nfnotainfoitemprodutoveiculo
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProdutoVeiculo(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo);

	/**
	 * Update nfnotainfoitemprodutoveiculo.
	 * 
	 * @param nfnotainfoitemprodutoveiculo
	 *            the nfnotainfoitemprodutoveiculo
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProdutoVeiculo(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo);

	/**
	 * Delete nfnotainfoitemprodutoveiculo.
	 * 
	 * @param nfnotainfoitemprodutoveiculo
	 *            the nfnotainfoitemprodutoveiculo
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoVeiculoById(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo);

	/**
	 * Delete all nfnotainfoitemprodutoveiculos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutoVeiculos();

	/**
	 * Fetch all nfnotainfoitemprodutoveiculos.
	 * 
	 * @return the list< nfnotainfoitemprodutoveiculo>
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> fetchAllNFNotaInfoItemProdutoVeiculos(
			NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo);

	/**
	 * Fetch nfnotainfoitemprodutoveiculos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> fetchNFNotaInfoItemProdutoVeiculosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemprodutomedicamento by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProdutoMedicamento fetchNFNotaInfoItemProdutoMedicamentoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemprodutomedicamento.
	 * 
	 * @param nfnotainfoitemprodutomedicamento
	 *            the nfnotainfoitemprodutomedicamento
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProdutoMedicamento(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento);

	/**
	 * Update nfnotainfoitemprodutomedicamento.
	 * 
	 * @param nfnotainfoitemprodutomedicamento
	 *            the nfnotainfoitemprodutomedicamento
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProdutoMedicamento(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento);

	/**
	 * Delete nfnotainfoitemprodutomedicamento.
	 * 
	 * @param nfnotainfoitemprodutomedicamento
	 *            the nfnotainfoitemprodutomedicamento
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoMedicamentoById(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento);

	/**
	 * Delete all nfnotainfoitemprodutomedicamentos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutoMedicamentos();

	/**
	 * Fetch all nfnotainfoitemprodutomedicamentos.
	 * 
	 * @return the list< nfnotainfoitemprodutomedicamento>
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> fetchAllNFNotaInfoItemProdutoMedicamentos(
			NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento);

	/**
	 * Fetch nfnotainfoitemprodutomedicamentos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> fetchNFNotaInfoItemProdutoMedicamentosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemprodutoarmamento by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProdutoArmamento fetchNFNotaInfoItemProdutoArmamentoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemprodutoarmamento.
	 * 
	 * @param nfnotainfoitemprodutoarmamento
	 *            the nfnotainfoitemprodutoarmamento
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProdutoArmamento(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento);

	/**
	 * Update nfnotainfoitemprodutoarmamento.
	 * 
	 * @param nfnotainfoitemprodutoarmamento
	 *            the nfnotainfoitemprodutoarmamento
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProdutoArmamento(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento);

	/**
	 * Delete nfnotainfoitemprodutoarmamento.
	 * 
	 * @param nfnotainfoitemprodutoarmamento
	 *            the nfnotainfoitemprodutoarmamento
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoArmamentoById(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento);

	/**
	 * Delete all nfnotainfoitemprodutoarmamentos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutoArmamentos();

	/**
	 * Fetch all nfnotainfoitemprodutoarmamentos.
	 * 
	 * @return the list< nfnotainfoitemprodutoarmamento>
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoArmamento> fetchAllNFNotaInfoItemProdutoArmamentos(
			NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento);

	/**
	 * Fetch nfnotainfoitemprodutoarmamentos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoArmamento> fetchNFNotaInfoItemProdutoArmamentosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemprodutocombustivel by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProdutoCombustivel fetchNFNotaInfoItemProdutoCombustivelById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemprodutocombustivel.
	 * 
	 * @param nfnotainfoitemprodutocombustivel
	 *            the nfnotainfoitemprodutocombustivel
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProdutoCombustivel(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel);

	/**
	 * Update nfnotainfoitemprodutocombustivel.
	 * 
	 * @param nfnotainfoitemprodutocombustivel
	 *            the nfnotainfoitemprodutocombustivel
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProdutoCombustivel(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel);

	/**
	 * Delete nfnotainfoitemprodutocombustivel.
	 * 
	 * @param nfnotainfoitemprodutocombustivel
	 *            the nfnotainfoitemprodutocombustivel
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoCombustivelById(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel);

	/**
	 * Delete all nfnotainfoitemprodutocombustivels.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutoCombustivels();

	/**
	 * Fetch all nfnotainfoitemprodutocombustivels.
	 * 
	 * @return the list< nfnotainfoitemprodutocombustivel>
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> fetchAllNFNotaInfoItemProdutoCombustivels(
			NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel);

	/**
	 * Fetch nfnotainfoitemprodutocombustivels by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> fetchNFNotaInfoItemProdutoCombustivelsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemprodutocombustivelcide by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemProdutoCombustivelCIDE fetchNFNotaInfoItemProdutoCombustivelCIDEById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemprodutocombustivelcide.
	 * 
	 * @param nfnotainfoitemprodutocombustivelcide
	 *            the nfnotainfoitemprodutocombustivelcide
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemProdutoCombustivelCIDE(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide);

	/**
	 * Update nfnotainfoitemprodutocombustivelcide.
	 * 
	 * @param nfnotainfoitemprodutocombustivelcide
	 *            the nfnotainfoitemprodutocombustivelcide
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemProdutoCombustivelCIDE(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide);

	/**
	 * Delete nfnotainfoitemprodutocombustivelcide.
	 * 
	 * @param nfnotainfoitemprodutocombustivelcide
	 *            the nfnotainfoitemprodutocombustivelcide
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemProdutoCombustivelCIDEById(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide);

	/**
	 * Delete all nfnotainfoitemprodutocombustivelcides.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemProdutoCombustivelCIDEs();

	/**
	 * Fetch all nfnotainfoitemprodutocombustivelcides.
	 * 
	 * @return the list< nfnotainfoitemprodutocombustivelcide>
	 */
	public InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> fetchAllNFNotaInfoItemProdutoCombustivelCIDEs(
			NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide);

	/**
	 * Fetch nfnotainfoitemprodutocombustivelcides by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	
	/**
	 * Fetch nfimpostodevolvidos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFImpostoDevolvido> fetchNFImpostoDevolvidosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfinformacaoimpostodevolvido by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	
	/**
	 * Fetch nfnotainfoitemimposto by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImposto fetchNFNotaInfoItemImpostoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimposto.
	 * 
	 * @param nfnotainfoitemimposto
	 *            the nfnotainfoitemimposto
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImposto(NFNotaInfoItemImposto nfnotainfoitemimposto);

	/**
	 * Update nfnotainfoitemimposto.
	 * 
	 * @param nfnotainfoitemimposto
	 *            the nfnotainfoitemimposto
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImposto(NFNotaInfoItemImposto nfnotainfoitemimposto);

	/**
	 * Delete nfnotainfoitemimposto.
	 * 
	 * @param nfnotainfoitemimposto
	 *            the nfnotainfoitemimposto
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoById(NFNotaInfoItemImposto nfnotainfoitemimposto);

	/**
	 * Delete all nfnotainfoitemimpostos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostos();

	/**
	 * Fetch all nfnotainfoitemimpostos.
	 * 
	 * @return the list< nfnotainfoitemimposto>
	 */
	public InternalResultsResponse<NFNotaInfoItemImposto> fetchAllNFNotaInfoItemImpostos(
			NFNotaInfoItemImposto nfnotainfoitemimposto);

	/**
	 * Fetch nfnotainfoitemimpostos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImposto> fetchNFNotaInfoItemImpostosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS fetchNFNotaInfoItemImpostoICMSById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms.
	 * 
	 * @param nfnotainfoitemimpostoicms
	 *            the nfnotainfoitemimpostoicms
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS(NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms);

	/**
	 * Update nfnotainfoitemimpostoicms.
	 * 
	 * @param nfnotainfoitemimpostoicms
	 *            the nfnotainfoitemimpostoicms
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS(NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms);

	/**
	 * Delete nfnotainfoitemimpostoicms.
	 * 
	 * @param nfnotainfoitemimpostoicms
	 *            the nfnotainfoitemimpostoicms
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSById(NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms);

	/**
	 * Delete all nfnotainfoitemimpostoicmss.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSs();

	/**
	 * Fetch all nfnotainfoitemimpostoicmss.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS> fetchAllNFNotaInfoItemImpostoICMSs(
			NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms);

	/**
	 * Fetch nfnotainfoitemimpostoicmss by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS> fetchNFNotaInfoItemImpostoICMSsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms00 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS00 fetchNFNotaInfoItemImpostoICMS00ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms00.
	 * 
	 * @param nfnotainfoitemimpostoicms00
	 *            the nfnotainfoitemimpostoicms00
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS00(NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00);

	/**
	 * Update nfnotainfoitemimpostoicms00.
	 * 
	 * @param nfnotainfoitemimpostoicms00
	 *            the nfnotainfoitemimpostoicms00
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS00(NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00);

	/**
	 * Delete nfnotainfoitemimpostoicms00.
	 * 
	 * @param nfnotainfoitemimpostoicms00
	 *            the nfnotainfoitemimpostoicms00
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS00ById(
			NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00);

	/**
	 * Delete all nfnotainfoitemimpostoicms00s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS00s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms00s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms00>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS00> fetchAllNFNotaInfoItemImpostoICMS00s(
			NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00);

	/**
	 * Fetch nfnotainfoitemimpostoicms00s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS00> fetchNFNotaInfoItemImpostoICMS00sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms10 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS10 fetchNFNotaInfoItemImpostoICMS10ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms10.
	 * 
	 * @param nfnotainfoitemimpostoicms10
	 *            the nfnotainfoitemimpostoicms10
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS10(NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10);

	/**
	 * Update nfnotainfoitemimpostoicms10.
	 * 
	 * @param nfnotainfoitemimpostoicms10
	 *            the nfnotainfoitemimpostoicms10
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS10(NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10);

	/**
	 * Delete nfnotainfoitemimpostoicms10.
	 * 
	 * @param nfnotainfoitemimpostoicms10
	 *            the nfnotainfoitemimpostoicms10
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS10ById(
			NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10);

	/**
	 * Delete all nfnotainfoitemimpostoicms10s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS10s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms10s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms10>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS10> fetchAllNFNotaInfoItemImpostoICMS10s(
			NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10);

	/**
	 * Fetch nfnotainfoitemimpostoicms10s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS10> fetchNFNotaInfoItemImpostoICMS10sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms20 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS20 fetchNFNotaInfoItemImpostoICMS20ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms20.
	 * 
	 * @param nfnotainfoitemimpostoicms20
	 *            the nfnotainfoitemimpostoicms20
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS20(NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20);

	/**
	 * Update nfnotainfoitemimpostoicms20.
	 * 
	 * @param nfnotainfoitemimpostoicms20
	 *            the nfnotainfoitemimpostoicms20
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS20(NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20);

	/**
	 * Delete nfnotainfoitemimpostoicms20.
	 * 
	 * @param nfnotainfoitemimpostoicms20
	 *            the nfnotainfoitemimpostoicms20
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS20ById(
			NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20);

	/**
	 * Delete all nfnotainfoitemimpostoicms20s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS20s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms20s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms20>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS20> fetchAllNFNotaInfoItemImpostoICMS20s(
			NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20);

	/**
	 * Fetch nfnotainfoitemimpostoicms20s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS20> fetchNFNotaInfoItemImpostoICMS20sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms30 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS30 fetchNFNotaInfoItemImpostoICMS30ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms30.
	 * 
	 * @param nfnotainfoitemimpostoicms30
	 *            the nfnotainfoitemimpostoicms30
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS30(NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30);

	/**
	 * Update nfnotainfoitemimpostoicms30.
	 * 
	 * @param nfnotainfoitemimpostoicms30
	 *            the nfnotainfoitemimpostoicms30
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS30(NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30);

	/**
	 * Delete nfnotainfoitemimpostoicms30.
	 * 
	 * @param nfnotainfoitemimpostoicms30
	 *            the nfnotainfoitemimpostoicms30
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS30ById(
			NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30);

	/**
	 * Delete all nfnotainfoitemimpostoicms30s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS30s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms30s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms30>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS30> fetchAllNFNotaInfoItemImpostoICMS30s(
			NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30);

	/**
	 * Fetch nfnotainfoitemimpostoicms30s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS30> fetchNFNotaInfoItemImpostoICMS30sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms40 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS40 fetchNFNotaInfoItemImpostoICMS40ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms40.
	 * 
	 * @param nfnotainfoitemimpostoicms40
	 *            the nfnotainfoitemimpostoicms40
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS40(NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40);

	/**
	 * Update nfnotainfoitemimpostoicms40.
	 * 
	 * @param nfnotainfoitemimpostoicms40
	 *            the nfnotainfoitemimpostoicms40
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS40(NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40);

	/**
	 * Delete nfnotainfoitemimpostoicms40.
	 * 
	 * @param nfnotainfoitemimpostoicms40
	 *            the nfnotainfoitemimpostoicms40
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS40ById(
			NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40);

	/**
	 * Delete all nfnotainfoitemimpostoicms40s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS40s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms40s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms40>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS40> fetchAllNFNotaInfoItemImpostoICMS40s(
			NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40);

	/**
	 * Fetch nfnotainfoitemimpostoicms40s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS40> fetchNFNotaInfoItemImpostoICMS40sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms51 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS51 fetchNFNotaInfoItemImpostoICMS51ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms51.
	 * 
	 * @param nfnotainfoitemimpostoicms51
	 *            the nfnotainfoitemimpostoicms51
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS51(NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51);

	/**
	 * Update nfnotainfoitemimpostoicms51.
	 * 
	 * @param nfnotainfoitemimpostoicms51
	 *            the nfnotainfoitemimpostoicms51
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS51(NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51);

	/**
	 * Delete nfnotainfoitemimpostoicms51.
	 * 
	 * @param nfnotainfoitemimpostoicms51
	 *            the nfnotainfoitemimpostoicms51
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS51ById(
			NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51);

	/**
	 * Delete all nfnotainfoitemimpostoicms51s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS51s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms51s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms51>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS51> fetchAllNFNotaInfoItemImpostoICMS51s(
			NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51);

	/**
	 * Fetch nfnotainfoitemimpostoicms51s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS51> fetchNFNotaInfoItemImpostoICMS51sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms60 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS60 fetchNFNotaInfoItemImpostoICMS60ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms60.
	 * 
	 * @param nfnotainfoitemimpostoicms60
	 *            the nfnotainfoitemimpostoicms60
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS60(NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60);

	/**
	 * Update nfnotainfoitemimpostoicms60.
	 * 
	 * @param nfnotainfoitemimpostoicms60
	 *            the nfnotainfoitemimpostoicms60
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS60(NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60);

	/**
	 * Delete nfnotainfoitemimpostoicms60.
	 * 
	 * @param nfnotainfoitemimpostoicms60
	 *            the nfnotainfoitemimpostoicms60
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS60ById(
			NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60);

	/**
	 * Delete all nfnotainfoitemimpostoicms60s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS60s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms60s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms60>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS60> fetchAllNFNotaInfoItemImpostoICMS60s(
			NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60);

	/**
	 * Fetch nfnotainfoitemimpostoicms60s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS60> fetchNFNotaInfoItemImpostoICMS60sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms70 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS70 fetchNFNotaInfoItemImpostoICMS70ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms70.
	 * 
	 * @param nfnotainfoitemimpostoicms70
	 *            the nfnotainfoitemimpostoicms70
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS70(NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70);

	/**
	 * Update nfnotainfoitemimpostoicms70.
	 * 
	 * @param nfnotainfoitemimpostoicms70
	 *            the nfnotainfoitemimpostoicms70
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS70(NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70);

	/**
	 * Delete nfnotainfoitemimpostoicms70.
	 * 
	 * @param nfnotainfoitemimpostoicms70
	 *            the nfnotainfoitemimpostoicms70
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS70ById(
			NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70);

	/**
	 * Delete all nfnotainfoitemimpostoicms70s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS70s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms70s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms70>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS70> fetchAllNFNotaInfoItemImpostoICMS70s(
			NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70);

	/**
	 * Fetch nfnotainfoitemimpostoicms70s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS70> fetchNFNotaInfoItemImpostoICMS70sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicms90 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMS90 fetchNFNotaInfoItemImpostoICMS90ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicms90.
	 * 
	 * @param nfnotainfoitemimpostoicms90
	 *            the nfnotainfoitemimpostoicms90
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMS90(NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90);

	/**
	 * Update nfnotainfoitemimpostoicms90.
	 * 
	 * @param nfnotainfoitemimpostoicms90
	 *            the nfnotainfoitemimpostoicms90
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMS90(NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90);

	/**
	 * Delete nfnotainfoitemimpostoicms90.
	 * 
	 * @param nfnotainfoitemimpostoicms90
	 *            the nfnotainfoitemimpostoicms90
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMS90ById(
			NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90);

	/**
	 * Delete all nfnotainfoitemimpostoicms90s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMS90s();

	/**
	 * Fetch all nfnotainfoitemimpostoicms90s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicms90>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS90> fetchAllNFNotaInfoItemImpostoICMS90s(
			NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90);

	/**
	 * Fetch nfnotainfoitemimpostoicms90s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMS90> fetchNFNotaInfoItemImpostoICMS90sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmspartilhado by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSPartilhado fetchNFNotaInfoItemImpostoICMSPartilhadoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmspartilhado.
	 * 
	 * @param nfnotainfoitemimpostoicmspartilhado
	 *            the nfnotainfoitemimpostoicmspartilhado
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSPartilhado(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado);

	/**
	 * Update nfnotainfoitemimpostoicmspartilhado.
	 * 
	 * @param nfnotainfoitemimpostoicmspartilhado
	 *            the nfnotainfoitemimpostoicmspartilhado
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSPartilhado(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado);

	/**
	 * Delete nfnotainfoitemimpostoicmspartilhado.
	 * 
	 * @param nfnotainfoitemimpostoicmspartilhado
	 *            the nfnotainfoitemimpostoicmspartilhado
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSPartilhadoById(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado);

	/**
	 * Delete all nfnotainfoitemimpostoicmspartilhados.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSPartilhados();

	/**
	 * Fetch all nfnotainfoitemimpostoicmspartilhados.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmspartilhado>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> fetchAllNFNotaInfoItemImpostoICMSPartilhados(
			NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado);

	/**
	 * Fetch nfnotainfoitemimpostoicmspartilhados by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmsst by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSST fetchNFNotaInfoItemImpostoICMSSTById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmsst.
	 * 
	 * @param nfnotainfoitemimpostoicmsst
	 *            the nfnotainfoitemimpostoicmsst
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSST(NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst);

	/**
	 * Update nfnotainfoitemimpostoicmsst.
	 * 
	 * @param nfnotainfoitemimpostoicmsst
	 *            the nfnotainfoitemimpostoicmsst
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSST(NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst);

	/**
	 * Delete nfnotainfoitemimpostoicmsst.
	 * 
	 * @param nfnotainfoitemimpostoicmsst
	 *            the nfnotainfoitemimpostoicmsst
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSTById(
			NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst);

	/**
	 * Delete all nfnotainfoitemimpostoicmssts.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSTs();

	/**
	 * Fetch all nfnotainfoitemimpostoicmssts.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmsst>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSST> fetchAllNFNotaInfoItemImpostoICMSSTs(
			NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst);

	/**
	 * Fetch nfnotainfoitemimpostoicmssts by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSST> fetchNFNotaInfoItemImpostoICMSSTsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn101 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSSN101 fetchNFNotaInfoItemImpostoICMSSN101ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmssn101.
	 * 
	 * @param nfnotainfoitemimpostoicmssn101
	 *            the nfnotainfoitemimpostoicmssn101
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN101(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101);

	/**
	 * Update nfnotainfoitemimpostoicmssn101.
	 * 
	 * @param nfnotainfoitemimpostoicmssn101
	 *            the nfnotainfoitemimpostoicmssn101
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN101(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101);

	/**
	 * Delete nfnotainfoitemimpostoicmssn101.
	 * 
	 * @param nfnotainfoitemimpostoicmssn101
	 *            the nfnotainfoitemimpostoicmssn101
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN101ById(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101);

	/**
	 * Delete all nfnotainfoitemimpostoicmssn101s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN101s();

	/**
	 * Fetch all nfnotainfoitemimpostoicmssn101s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmssn101>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> fetchAllNFNotaInfoItemImpostoICMSSN101s(
			NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn101s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> fetchNFNotaInfoItemImpostoICMSSN101sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn102 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSSN102 fetchNFNotaInfoItemImpostoICMSSN102ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmssn102.
	 * 
	 * @param nfnotainfoitemimpostoicmssn102
	 *            the nfnotainfoitemimpostoicmssn102
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN102(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102);

	/**
	 * Update nfnotainfoitemimpostoicmssn102.
	 * 
	 * @param nfnotainfoitemimpostoicmssn102
	 *            the nfnotainfoitemimpostoicmssn102
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN102(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102);

	/**
	 * Delete nfnotainfoitemimpostoicmssn102.
	 * 
	 * @param nfnotainfoitemimpostoicmssn102
	 *            the nfnotainfoitemimpostoicmssn102
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN102ById(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102);

	/**
	 * Delete all nfnotainfoitemimpostoicmssn102s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN102s();

	/**
	 * Fetch all nfnotainfoitemimpostoicmssn102s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmssn102>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> fetchAllNFNotaInfoItemImpostoICMSSN102s(
			NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn102s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> fetchNFNotaInfoItemImpostoICMSSN102sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn201 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSSN201 fetchNFNotaInfoItemImpostoICMSSN201ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmssn201.
	 * 
	 * @param nfnotainfoitemimpostoicmssn201
	 *            the nfnotainfoitemimpostoicmssn201
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN201(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201);

	/**
	 * Update nfnotainfoitemimpostoicmssn201.
	 * 
	 * @param nfnotainfoitemimpostoicmssn201
	 *            the nfnotainfoitemimpostoicmssn201
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN201(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201);

	/**
	 * Delete nfnotainfoitemimpostoicmssn201.
	 * 
	 * @param nfnotainfoitemimpostoicmssn201
	 *            the nfnotainfoitemimpostoicmssn201
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN201ById(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201);

	/**
	 * Delete all nfnotainfoitemimpostoicmssn201s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN201s();

	/**
	 * Fetch all nfnotainfoitemimpostoicmssn201s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmssn201>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> fetchAllNFNotaInfoItemImpostoICMSSN201s(
			NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn201s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> fetchNFNotaInfoItemImpostoICMSSN201sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn202 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSSN202 fetchNFNotaInfoItemImpostoICMSSN202ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmssn202.
	 * 
	 * @param nfnotainfoitemimpostoicmssn202
	 *            the nfnotainfoitemimpostoicmssn202
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN202(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202);

	/**
	 * Update nfnotainfoitemimpostoicmssn202.
	 * 
	 * @param nfnotainfoitemimpostoicmssn202
	 *            the nfnotainfoitemimpostoicmssn202
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN202(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202);

	/**
	 * Delete nfnotainfoitemimpostoicmssn202.
	 * 
	 * @param nfnotainfoitemimpostoicmssn202
	 *            the nfnotainfoitemimpostoicmssn202
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN202ById(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202);

	/**
	 * Delete all nfnotainfoitemimpostoicmssn202s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN202s();

	/**
	 * Fetch all nfnotainfoitemimpostoicmssn202s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmssn202>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> fetchAllNFNotaInfoItemImpostoICMSSN202s(
			NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn202s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> fetchNFNotaInfoItemImpostoICMSSN202sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn500 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSSN500 fetchNFNotaInfoItemImpostoICMSSN500ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmssn500.
	 * 
	 * @param nfnotainfoitemimpostoicmssn500
	 *            the nfnotainfoitemimpostoicmssn500
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN500(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500);

	/**
	 * Update nfnotainfoitemimpostoicmssn500.
	 * 
	 * @param nfnotainfoitemimpostoicmssn500
	 *            the nfnotainfoitemimpostoicmssn500
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN500(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500);

	/**
	 * Delete nfnotainfoitemimpostoicmssn500.
	 * 
	 * @param nfnotainfoitemimpostoicmssn500
	 *            the nfnotainfoitemimpostoicmssn500
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN500ById(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500);

	/**
	 * Delete all nfnotainfoitemimpostoicmssn500s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN500s();

	/**
	 * Fetch all nfnotainfoitemimpostoicmssn500s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmssn500>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> fetchAllNFNotaInfoItemImpostoICMSSN500s(
			NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn500s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> fetchNFNotaInfoItemImpostoICMSSN500sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn900 by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSSN900 fetchNFNotaInfoItemImpostoICMSSN900ById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmssn900.
	 * 
	 * @param nfnotainfoitemimpostoicmssn900
	 *            the nfnotainfoitemimpostoicmssn900
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSSN900(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900);

	/**
	 * Update nfnotainfoitemimpostoicmssn900.
	 * 
	 * @param nfnotainfoitemimpostoicmssn900
	 *            the nfnotainfoitemimpostoicmssn900
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSSN900(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900);

	/**
	 * Delete nfnotainfoitemimpostoicmssn900.
	 * 
	 * @param nfnotainfoitemimpostoicmssn900
	 *            the nfnotainfoitemimpostoicmssn900
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSSN900ById(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900);

	/**
	 * Delete all nfnotainfoitemimpostoicmssn900s.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSSN900s();

	/**
	 * Fetch all nfnotainfoitemimpostoicmssn900s.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmssn900>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> fetchAllNFNotaInfoItemImpostoICMSSN900s(
			NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900);

	/**
	 * Fetch nfnotainfoitemimpostoicmssn900s by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> fetchNFNotaInfoItemImpostoICMSSN900sByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoipi by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoIPI fetchNFNotaInfoItemImpostoIPIById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoipi.
	 * 
	 * @param nfnotainfoitemimpostoipi
	 *            the nfnotainfoitemimpostoipi
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoIPI(NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi);

	/**
	 * Update nfnotainfoitemimpostoipi.
	 * 
	 * @param nfnotainfoitemimpostoipi
	 *            the nfnotainfoitemimpostoipi
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoIPI(NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi);

	/**
	 * Delete nfnotainfoitemimpostoipi.
	 * 
	 * @param nfnotainfoitemimpostoipi
	 *            the nfnotainfoitemimpostoipi
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoIPIById(NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi);

	/**
	 * Delete all nfnotainfoitemimpostoipis.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoIPIs();

	/**
	 * Fetch all nfnotainfoitemimpostoipis.
	 * 
	 * @return the list< nfnotainfoitemimpostoipi>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoIPI> fetchAllNFNotaInfoItemImpostoIPIs(
			NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi);

	/**
	 * Fetch nfnotainfoitemimpostoipis by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoIPI> fetchNFNotaInfoItemImpostoIPIsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoipitributado by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoIPITributado fetchNFNotaInfoItemImpostoIPITributadoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoipitributado.
	 * 
	 * @param nfnotainfoitemimpostoipitributado
	 *            the nfnotainfoitemimpostoipitributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoIPITributado(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado);

	/**
	 * Update nfnotainfoitemimpostoipitributado.
	 * 
	 * @param nfnotainfoitemimpostoipitributado
	 *            the nfnotainfoitemimpostoipitributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoIPITributado(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado);

	/**
	 * Delete nfnotainfoitemimpostoipitributado.
	 * 
	 * @param nfnotainfoitemimpostoipitributado
	 *            the nfnotainfoitemimpostoipitributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoIPITributadoById(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado);

	/**
	 * Delete all nfnotainfoitemimpostoipitributados.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoIPITributados();

	/**
	 * Fetch all nfnotainfoitemimpostoipitributados.
	 * 
	 * @return the list< nfnotainfoitemimpostoipitributado>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> fetchAllNFNotaInfoItemImpostoIPITributados(
			NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado);

	/**
	 * Fetch nfnotainfoitemimpostoipitributados by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> fetchNFNotaInfoItemImpostoIPITributadosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoipinaotributado by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoIPINaoTributado fetchNFNotaInfoItemImpostoIPINaoTributadoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoipinaotributado.
	 * 
	 * @param nfnotainfoitemimpostoipinaotributado
	 *            the nfnotainfoitemimpostoipinaotributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoIPINaoTributado(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado);

	/**
	 * Update nfnotainfoitemimpostoipinaotributado.
	 * 
	 * @param nfnotainfoitemimpostoipinaotributado
	 *            the nfnotainfoitemimpostoipinaotributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoIPINaoTributado(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado);

	/**
	 * Delete nfnotainfoitemimpostoipinaotributado.
	 * 
	 * @param nfnotainfoitemimpostoipinaotributado
	 *            the nfnotainfoitemimpostoipinaotributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoIPINaoTributadoById(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado);

	/**
	 * Delete all nfnotainfoitemimpostoipinaotributados.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoIPINaoTributados();

	/**
	 * Fetch all nfnotainfoitemimpostoipinaotributados.
	 * 
	 * @return the list< nfnotainfoitemimpostoipinaotributado>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> fetchAllNFNotaInfoItemImpostoIPINaoTributados(
			NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado);

	/**
	 * Fetch nfnotainfoitemimpostoipinaotributados by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoimportacao by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoImportacao fetchNFNotaInfoItemImpostoImportacaoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoimportacao.
	 * 
	 * @param nfnotainfoitemimpostoimportacao
	 *            the nfnotainfoitemimpostoimportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoImportacao(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao);

	/**
	 * Update nfnotainfoitemimpostoimportacao.
	 * 
	 * @param nfnotainfoitemimpostoimportacao
	 *            the nfnotainfoitemimpostoimportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoImportacao(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao);

	/**
	 * Delete nfnotainfoitemimpostoimportacao.
	 * 
	 * @param nfnotainfoitemimpostoimportacao
	 *            the nfnotainfoitemimpostoimportacao
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoImportacaoById(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao);

	/**
	 * Delete all nfnotainfoitemimpostoimportacaos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoImportacaos();

	/**
	 * Fetch all nfnotainfoitemimpostoimportacaos.
	 * 
	 * @return the list< nfnotainfoitemimpostoimportacao>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoImportacao> fetchAllNFNotaInfoItemImpostoImportacaos(
			NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao);

	/**
	 * Fetch nfnotainfoitemimpostoimportacaos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoImportacao> fetchNFNotaInfoItemImpostoImportacaosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoissqn by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoISSQN fetchNFNotaInfoItemImpostoISSQNById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoissqn.
	 * 
	 * @param nfnotainfoitemimpostoissqn
	 *            the nfnotainfoitemimpostoissqn
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoISSQN(NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn);

	/**
	 * Update nfnotainfoitemimpostoissqn.
	 * 
	 * @param nfnotainfoitemimpostoissqn
	 *            the nfnotainfoitemimpostoissqn
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoISSQN(NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn);

	/**
	 * Delete nfnotainfoitemimpostoissqn.
	 * 
	 * @param nfnotainfoitemimpostoissqn
	 *            the nfnotainfoitemimpostoissqn
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoISSQNById(NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn);

	/**
	 * Delete all nfnotainfoitemimpostoissqns.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoISSQNs();

	/**
	 * Fetch all nfnotainfoitemimpostoissqns.
	 * 
	 * @return the list< nfnotainfoitemimpostoissqn>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoISSQN> fetchAllNFNotaInfoItemImpostoISSQNs(
			NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn);

	/**
	 * Fetch nfnotainfoitemimpostoissqns by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoISSQN> fetchNFNotaInfoItemImpostoISSQNsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostopis by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoPIS fetchNFNotaInfoItemImpostoPISById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostopis.
	 * 
	 * @param nfnotainfoitemimpostopis
	 *            the nfnotainfoitemimpostopis
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoPIS(NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis);

	/**
	 * Update nfnotainfoitemimpostopis.
	 * 
	 * @param nfnotainfoitemimpostopis
	 *            the nfnotainfoitemimpostopis
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoPIS(NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis);

	/**
	 * Delete nfnotainfoitemimpostopis.
	 * 
	 * @param nfnotainfoitemimpostopis
	 *            the nfnotainfoitemimpostopis
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoPISById(NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis);

	/**
	 * Delete all nfnotainfoitemimpostopiss.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISs();

	/**
	 * Fetch all nfnotainfoitemimpostopiss.
	 * 
	 * @return the list< nfnotainfoitemimpostopis>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPIS> fetchAllNFNotaInfoItemImpostoPISs(
			NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis);

	/**
	 * Fetch nfnotainfoitemimpostopiss by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPIS> fetchNFNotaInfoItemImpostoPISsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostopisaliquota by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoPISAliquota fetchNFNotaInfoItemImpostoPISAliquotaById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostopisaliquota.
	 * 
	 * @param nfnotainfoitemimpostopisaliquota
	 *            the nfnotainfoitemimpostopisaliquota
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoPISAliquota(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota);

	/**
	 * Update nfnotainfoitemimpostopisaliquota.
	 * 
	 * @param nfnotainfoitemimpostopisaliquota
	 *            the nfnotainfoitemimpostopisaliquota
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoPISAliquota(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota);

	/**
	 * Delete nfnotainfoitemimpostopisaliquota.
	 * 
	 * @param nfnotainfoitemimpostopisaliquota
	 *            the nfnotainfoitemimpostopisaliquota
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoPISAliquotaById(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota);

	/**
	 * Delete all nfnotainfoitemimpostopisaliquotas.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISAliquotas();

	/**
	 * Fetch all nfnotainfoitemimpostopisaliquotas.
	 * 
	 * @return the list< nfnotainfoitemimpostopisaliquota>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> fetchAllNFNotaInfoItemImpostoPISAliquotas(
			NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota);

	/**
	 * Fetch nfnotainfoitemimpostopisaliquotas by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> fetchNFNotaInfoItemImpostoPISAliquotasByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostopisquantidade by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoPISQuantidade fetchNFNotaInfoItemImpostoPISQuantidadeById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostopisquantidade.
	 * 
	 * @param nfnotainfoitemimpostopisquantidade
	 *            the nfnotainfoitemimpostopisquantidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoPISQuantidade(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade);

	/**
	 * Update nfnotainfoitemimpostopisquantidade.
	 * 
	 * @param nfnotainfoitemimpostopisquantidade
	 *            the nfnotainfoitemimpostopisquantidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoPISQuantidade(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade);

	/**
	 * Delete nfnotainfoitemimpostopisquantidade.
	 * 
	 * @param nfnotainfoitemimpostopisquantidade
	 *            the nfnotainfoitemimpostopisquantidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoPISQuantidadeById(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade);

	/**
	 * Delete all nfnotainfoitemimpostopisquantidades.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISQuantidades();

	/**
	 * Fetch all nfnotainfoitemimpostopisquantidades.
	 * 
	 * @return the list< nfnotainfoitemimpostopisquantidade>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> fetchAllNFNotaInfoItemImpostoPISQuantidades(
			NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade);

	/**
	 * Fetch nfnotainfoitemimpostopisquantidades by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostopisnaotributado by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoPISNaoTributado fetchNFNotaInfoItemImpostoPISNaoTributadoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostopisnaotributado.
	 * 
	 * @param nfnotainfoitemimpostopisnaotributado
	 *            the nfnotainfoitemimpostopisnaotributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoPISNaoTributado(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado);

	/**
	 * Update nfnotainfoitemimpostopisnaotributado.
	 * 
	 * @param nfnotainfoitemimpostopisnaotributado
	 *            the nfnotainfoitemimpostopisnaotributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoPISNaoTributado(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado);

	/**
	 * Delete nfnotainfoitemimpostopisnaotributado.
	 * 
	 * @param nfnotainfoitemimpostopisnaotributado
	 *            the nfnotainfoitemimpostopisnaotributado
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoPISNaoTributadoById(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado);

	/**
	 * Delete all nfnotainfoitemimpostopisnaotributados.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISNaoTributados();

	/**
	 * Fetch all nfnotainfoitemimpostopisnaotributados.
	 * 
	 * @return the list< nfnotainfoitemimpostopisnaotributado>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> fetchAllNFNotaInfoItemImpostoPISNaoTributados(
			NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado);

	/**
	 * Fetch nfnotainfoitemimpostopisnaotributados by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostopisoutrasoperacoes by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoPISOutrasOperacoes fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(
			FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostopisoutrasoperacoes.
	 * 
	 * @param nfnotainfoitemimpostopisoutrasoperacoes
	 *            the nfnotainfoitemimpostopisoutrasoperacoes
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoPISOutrasOperacoes(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes);

	/**
	 * Update nfnotainfoitemimpostopisoutrasoperacoes.
	 * 
	 * @param nfnotainfoitemimpostopisoutrasoperacoes
	 *            the nfnotainfoitemimpostopisoutrasoperacoes
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoPISOutrasOperacoes(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes);

	/**
	 * Delete nfnotainfoitemimpostopisoutrasoperacoes.
	 * 
	 * @param nfnotainfoitemimpostopisoutrasoperacoes
	 *            the nfnotainfoitemimpostopisoutrasoperacoes
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoPISOutrasOperacoesById(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes);

	/**
	 * Delete all nfnotainfoitemimpostopisoutrasoperacoess.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISOutrasOperacoess();

	/**
	 * Fetch all nfnotainfoitemimpostopisoutrasoperacoess.
	 * 
	 * @return the list< nfnotainfoitemimpostopisoutrasoperacoes>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> fetchAllNFNotaInfoItemImpostoPISOutrasOperacoess(
			NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes);

	/**
	 * Fetch nfnotainfoitemimpostopisoutrasoperacoess by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostopisst by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoPISST fetchNFNotaInfoItemImpostoPISSTById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostopisst.
	 * 
	 * @param nfnotainfoitemimpostopisst
	 *            the nfnotainfoitemimpostopisst
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoPISST(NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst);

	/**
	 * Update nfnotainfoitemimpostopisst.
	 * 
	 * @param nfnotainfoitemimpostopisst
	 *            the nfnotainfoitemimpostopisst
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoPISST(NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst);

	/**
	 * Delete nfnotainfoitemimpostopisst.
	 * 
	 * @param nfnotainfoitemimpostopisst
	 *            the nfnotainfoitemimpostopisst
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoPISSTById(NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst);

	/**
	 * Delete all nfnotainfoitemimpostopissts.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoPISSTs();

	/**
	 * Fetch all nfnotainfoitemimpostopissts.
	 * 
	 * @return the list< nfnotainfoitemimpostopisst>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISST> fetchAllNFNotaInfoItemImpostoPISSTs(
			NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst);

	/**
	 * Fetch nfnotainfoitemimpostopissts by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoPISST> fetchNFNotaInfoItemImpostoPISSTsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostocofins by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoCOFINS fetchNFNotaInfoItemImpostoCOFINSById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostocofins.
	 * 
	 * @param nfnotainfoitemimpostocofins
	 *            the nfnotainfoitemimpostocofins
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoCOFINS(NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins);

	/**
	 * Update nfnotainfoitemimpostocofins.
	 * 
	 * @param nfnotainfoitemimpostocofins
	 *            the nfnotainfoitemimpostocofins
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoCOFINS(NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins);

	/**
	 * Delete nfnotainfoitemimpostocofins.
	 * 
	 * @param nfnotainfoitemimpostocofins
	 *            the nfnotainfoitemimpostocofins
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSById(
			NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins);

	/**
	 * Delete all nfnotainfoitemimpostocofinss.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSs();

	/**
	 * Fetch all nfnotainfoitemimpostocofinss.
	 * 
	 * @return the list< nfnotainfoitemimpostocofins>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> fetchAllNFNotaInfoItemImpostoCOFINSs(
			NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins);

	/**
	 * Fetch nfnotainfoitemimpostocofinss by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> fetchNFNotaInfoItemImpostoCOFINSsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostocofinsaliquota by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoCOFINSAliquota fetchNFNotaInfoItemImpostoCOFINSAliquotaById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostocofinsaliquota.
	 * 
	 * @param nfnotainfoitemimpostocofinsaliquota
	 *            the nfnotainfoitemimpostocofinsaliquota
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSAliquota(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota);

	/**
	 * Update nfnotainfoitemimpostocofinsaliquota.
	 * 
	 * @param nfnotainfoitemimpostocofinsaliquota
	 *            the nfnotainfoitemimpostocofinsaliquota
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSAliquota(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota);

	/**
	 * Delete nfnotainfoitemimpostocofinsaliquota.
	 * 
	 * @param nfnotainfoitemimpostocofinsaliquota
	 *            the nfnotainfoitemimpostocofinsaliquota
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSAliquotaById(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota);

	/**
	 * Delete all nfnotainfoitemimpostocofinsaliquotas.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSAliquotas();

	/**
	 * Fetch all nfnotainfoitemimpostocofinsaliquotas.
	 * 
	 * @return the list< nfnotainfoitemimpostocofinsaliquota>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> fetchAllNFNotaInfoItemImpostoCOFINSAliquotas(
			NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota);

	/**
	 * Fetch nfnotainfoitemimpostocofinsaliquotas by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostocofinsquantidade by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoCOFINSQuantidade fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(
			FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostocofinsquantidade.
	 * 
	 * @param nfnotainfoitemimpostocofinsquantidade
	 *            the nfnotainfoitemimpostocofinsquantidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSQuantidade(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade);

	/**
	 * Update nfnotainfoitemimpostocofinsquantidade.
	 * 
	 * @param nfnotainfoitemimpostocofinsquantidade
	 *            the nfnotainfoitemimpostocofinsquantidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSQuantidade(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade);

	/**
	 * Delete nfnotainfoitemimpostocofinsquantidade.
	 * 
	 * @param nfnotainfoitemimpostocofinsquantidade
	 *            the nfnotainfoitemimpostocofinsquantidade
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSQuantidadeById(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade);

	/**
	 * Delete all nfnotainfoitemimpostocofinsquantidades.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSQuantidades();

	/**
	 * Fetch all nfnotainfoitemimpostocofinsquantidades.
	 * 
	 * @return the list< nfnotainfoitemimpostocofinsquantidade>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> fetchAllNFNotaInfoItemImpostoCOFINSQuantidades(
			NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade);

	/**
	 * Fetch nfnotainfoitemimpostocofinsquantidades by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostocofinsnaotributavel by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoCOFINSNaoTributavel fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(
			FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostocofinsnaotributavel.
	 * 
	 * @param nfnotainfoitemimpostocofinsnaotributavel
	 *            the nfnotainfoitemimpostocofinsnaotributavel
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSNaoTributavel(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel);

	/**
	 * Update nfnotainfoitemimpostocofinsnaotributavel.
	 * 
	 * @param nfnotainfoitemimpostocofinsnaotributavel
	 *            the nfnotainfoitemimpostocofinsnaotributavel
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSNaoTributavel(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel);

	/**
	 * Delete nfnotainfoitemimpostocofinsnaotributavel.
	 * 
	 * @param nfnotainfoitemimpostocofinsnaotributavel
	 *            the nfnotainfoitemimpostocofinsnaotributavel
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSNaoTributavelById(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel);

	/**
	 * Delete all nfnotainfoitemimpostocofinsnaotributavels.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSNaoTributavels();

	/**
	 * Fetch all nfnotainfoitemimpostocofinsnaotributavels.
	 * 
	 * @return the list< nfnotainfoitemimpostocofinsnaotributavel>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> fetchAllNFNotaInfoItemImpostoCOFINSNaoTributavels(
			NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel);

	/**
	 * Fetch nfnotainfoitemimpostocofinsnaotributavels by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostocofinsoutrasoperacoes by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoCOFINSOutrasOperacoes fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(
			FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostocofinsoutrasoperacoes.
	 * 
	 * @param nfnotainfoitemimpostocofinsoutrasoperacoes
	 *            the nfnotainfoitemimpostocofinsoutrasoperacoes
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes);

	/**
	 * Update nfnotainfoitemimpostocofinsoutrasoperacoes.
	 * 
	 * @param nfnotainfoitemimpostocofinsoutrasoperacoes
	 *            the nfnotainfoitemimpostocofinsoutrasoperacoes
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSOutrasOperacoes(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes);

	/**
	 * Delete nfnotainfoitemimpostocofinsoutrasoperacoes.
	 * 
	 * @param nfnotainfoitemimpostocofinsoutrasoperacoes
	 *            the nfnotainfoitemimpostocofinsoutrasoperacoes
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes);

	/**
	 * Delete all nfnotainfoitemimpostocofinsoutrasoperacoess.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess();

	/**
	 * Fetch all nfnotainfoitemimpostocofinsoutrasoperacoess.
	 * 
	 * @return the list< nfnotainfoitemimpostocofinsoutrasoperacoes>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> fetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess(
			NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes);

	/**
	 * Fetch nfnotainfoitemimpostocofinsoutrasoperacoess by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostocofinsst by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoCOFINSST fetchNFNotaInfoItemImpostoCOFINSSTById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostocofinsst.
	 * 
	 * @param nfnotainfoitemimpostocofinsst
	 *            the nfnotainfoitemimpostocofinsst
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoCOFINSST(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst);

	/**
	 * Update nfnotainfoitemimpostocofinsst.
	 * 
	 * @param nfnotainfoitemimpostocofinsst
	 *            the nfnotainfoitemimpostocofinsst
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoCOFINSST(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst);

	/**
	 * Delete nfnotainfoitemimpostocofinsst.
	 * 
	 * @param nfnotainfoitemimpostocofinsst
	 *            the nfnotainfoitemimpostocofinsst
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoCOFINSSTById(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst);

	/**
	 * Delete all nfnotainfoitemimpostocofinssts.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoCOFINSSTs();

	/**
	 * Fetch all nfnotainfoitemimpostocofinssts.
	 * 
	 * @return the list< nfnotainfoitemimpostocofinsst>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> fetchAllNFNotaInfoItemImpostoCOFINSSTs(
			NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst);

	/**
	 * Fetch nfnotainfoitemimpostocofinssts by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfnotainfoitemimpostoicmsufdestino by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFNotaInfoItemImpostoICMSUFDestino fetchNFNotaInfoItemImpostoICMSUFDestinoById(FetchByIdRequest request);

	/**
	 * Insert nfnotainfoitemimpostoicmsufdestino.
	 * 
	 * @param nfnotainfoitemimpostoicmsufdestino
	 *            the nfnotainfoitemimpostoicmsufdestino
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFNotaInfoItemImpostoICMSUFDestino(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino);

	/**
	 * Update nfnotainfoitemimpostoicmsufdestino.
	 * 
	 * @param nfnotainfoitemimpostoicmsufdestino
	 *            the nfnotainfoitemimpostoicmsufdestino
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFNotaInfoItemImpostoICMSUFDestino(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino);

	/**
	 * Delete nfnotainfoitemimpostoicmsufdestino.
	 * 
	 * @param nfnotainfoitemimpostoicmsufdestino
	 *            the nfnotainfoitemimpostoicmsufdestino
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFNotaInfoItemImpostoICMSUFDestinoById(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino);

	/**
	 * Delete all nfnotainfoitemimpostoicmsufdestinos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFNotaInfoItemImpostoICMSUFDestinos();

	/**
	 * Fetch all nfnotainfoitemimpostoicmsufdestinos.
	 * 
	 * @return the list< nfnotainfoitemimpostoicmsufdestino>
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> fetchAllNFNotaInfoItemImpostoICMSUFDestinos(
			NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino);

	/**
	 * Fetch nfnotainfoitemimpostoicmsufdestinos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(
			PagedInquiryRequest request);

	/**
	 * Fetch nfimpostodevolvido by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFImpostoDevolvido fetchNFImpostoDevolvidoById(FetchByIdRequest request);

	/**
	 * Insert nfimpostodevolvido.
	 * 
	 * @param nfimpostodevolvido
	 *            the nfimpostodevolvido
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFImpostoDevolvido(NFImpostoDevolvido nfimpostodevolvido);

	/**
	 * Update nfimpostodevolvido.
	 * 
	 * @param nfimpostodevolvido
	 *            the nfimpostodevolvido
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFImpostoDevolvido(NFImpostoDevolvido nfimpostodevolvido);

	/**
	 * Delete nfimpostodevolvido.
	 * 
	 * @param nfimpostodevolvido
	 *            the nfimpostodevolvido
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFImpostoDevolvidoById(NFImpostoDevolvido nfimpostodevolvido);

	/**
	 * Delete all nfimpostodevolvidos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFImpostoDevolvidos();

	/**
	 * Fetch all nfimpostodevolvidos.
	 * 
	 * @return the list< nfimpostodevolvido>
	 */
	public InternalResultsResponse<NFImpostoDevolvido> fetchAllNFImpostoDevolvidos(
			NFImpostoDevolvido nfimpostodevolvido);

	/**
	 * Fetch nfinformacaoimpostodevolvido by id.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public NFInformacaoImpostoDevolvido fetchNFInformacaoImpostoDevolvidoById(FetchByIdRequest request);

	/**
	 * Insert nfinformacaoimpostodevolvido.
	 * 
	 * @param nfinformacaoimpostodevolvido
	 *            the nfinformacaoimpostodevolvido
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertNFInformacaoImpostoDevolvido(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido);

	/**
	 * Update nfinformacaoimpostodevolvido.
	 * 
	 * @param nfinformacaoimpostodevolvido
	 *            the nfinformacaoimpostodevolvido
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateNFInformacaoImpostoDevolvido(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido);

	/**
	 * Delete nfinformacaoimpostodevolvido.
	 * 
	 * @param nfinformacaoimpostodevolvido
	 *            the nfinformacaoimpostodevolvido
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteNFInformacaoImpostoDevolvidoById(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido);

	/**
	 * Delete all nfinformacaoimpostodevolvidos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllNFInformacaoImpostoDevolvidos();

	/**
	 * Fetch all nfinformacaoimpostodevolvidos.
	 * 
	 * @return the list< nfinformacaoimpostodevolvido>
	 */
	public InternalResultsResponse<NFInformacaoImpostoDevolvido> fetchAllNFInformacaoImpostoDevolvidos(
			NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido);

	/**
	 * Fetch nfinformacaoimpostodevolvidos by request.
	 * 
	 * @param request
	 *            the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFInformacaoImpostoDevolvido> fetchNFInformacaoImpostoDevolvidosByRequest(
			PagedInquiryRequest request);

}
