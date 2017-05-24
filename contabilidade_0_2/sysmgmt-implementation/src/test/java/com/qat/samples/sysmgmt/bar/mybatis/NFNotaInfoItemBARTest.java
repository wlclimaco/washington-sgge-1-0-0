/** create by system gera-java version 1.0.0 04/12/2016 11:43 : 35*/
package com.qat.samples.sysmgmt.bar.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Nfe.INFNotaInfoItemBAR;
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
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = { "classpath:conf/unittest-base-context.xml", "classpath:conf/bartest-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class NFNotaInfoItemBARTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger LOG = LoggerFactory.getLogger(NFNotaInfoItemBARTest.class);
	private INFNotaInfoItemBAR nfnotainfoitemBAR; // injected by Spring through
													// @Resource

	@Resource
	public void setNFNotaInfoItemBAR(INFNotaInfoItemBAR nfnotainfoitemBAR) {
		this.nfnotainfoitemBAR = nfnotainfoitemBAR;
	}

	public INFNotaInfoItemBAR getNFNotaInfoItemBAR() {
		return nfnotainfoitemBAR;
	}

	// ===================================### NFNOTAINFOITEM
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItem() {
		NFNotaInfoItem nfnotainfoitem = Objects.insertNFNotaInfoItem(4, TabelaEnum.NFNOTAINFOITEM,
				PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItem nfnotainfoitemResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemById(request);
		Assert.assertEquals(nfnotainfoitemResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItem(nfnotainfoitem);
		nfnotainfoitemResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemById(request);
		Assert.assertEquals(nfnotainfoitem.getId(), nfnotainfoitemResponse.getId());
		nfnotainfoitem = Objects.insertNFNotaInfoItem(4, TabelaEnum.NFNOTAINFOITEM,
				PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemById(nfnotainfoitem);
		nfnotainfoitemResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemById(request);
		Assert.assertEquals(nfnotainfoitemResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItems() {
		NFNotaInfoItem nfnotainfoitem = new NFNotaInfoItem();
		List<NFNotaInfoItem> response = getNFNotaInfoItemBAR().fetchAllNFNotaInfoItems(nfnotainfoitem).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItems() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItems();
		NFNotaInfoItem nfnotainfoitem = new NFNotaInfoItem();
		List<NFNotaInfoItem> response = getNFNotaInfoItemBAR().fetchAllNFNotaInfoItems(new NFNotaInfoItem())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItem() {
		NFNotaInfoItem nfnotainfoitem = Objects.insertNFNotaInfoItem(1000, TabelaEnum.NFNOTAINFOITEM,
				PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItem nfnotainfoitemResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemById(request);
		Assert.assertEquals(nfnotainfoitemResponse.getInformacoesAdicionais(), "informacoesAdicionais_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItem(nfnotainfoitem);
		nfnotainfoitemResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemById(request);
		Assert.assertEquals(nfnotainfoitemResponse.getInformacoesAdicionais(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItem> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItem> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItems();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItem> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMPRODUTO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProduto() {
		NFNotaInfoItemProduto nfnotainfoitemproduto = Objects.insertNFNotaInfoItemProduto(4, TabelaEnum.NFNOTAINFOITEMPRODUTO,
				PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemProduto nfnotainfoitemprodutoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemProduto(nfnotainfoitemproduto);
		nfnotainfoitemprodutoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoById(request);
		Assert.assertEquals(nfnotainfoitemproduto.getId(), nfnotainfoitemprodutoResponse.getId());
		nfnotainfoitemproduto = Objects.insertNFNotaInfoItemProduto(4, TabelaEnum.NFNOTAINFOITEMPRODUTO,
				PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemProdutoById(nfnotainfoitemproduto);
		nfnotainfoitemprodutoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutos() {
		NFNotaInfoItemProduto nfnotainfoitemproduto = new NFNotaInfoItemProduto();
		List<NFNotaInfoItemProduto> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutos(nfnotainfoitemproduto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutos();
		NFNotaInfoItemProduto nfnotainfoitemproduto = new NFNotaInfoItemProduto();
		List<NFNotaInfoItemProduto> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutos(new NFNotaInfoItemProduto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProduto() {
		NFNotaInfoItemProduto nfnotainfoitemproduto = Objects.insertNFNotaInfoItemProduto(1000, TabelaEnum.NFNOTAINFOITEMPRODUTO,
				PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProduto nfnotainfoitemprodutoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoResponse.getCfop(), "cfop_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemProduto(nfnotainfoitemproduto);
		nfnotainfoitemprodutoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoResponse.getCfop(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProduto> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProduto> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProduto> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProdutoDeclaracaoImportacao() {
		NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao = Objects.insertNFNotaInfoItemProdutoDeclaracaoImportacao(
				1004, TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoResponse, null);
		getNFNotaInfoItemBAR()
				.insertNFNotaInfoItemProdutoDeclaracaoImportacao(nfnotainfoitemprodutodeclaracaoimportacao);
		nfnotainfoitemprodutodeclaracaoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacaoResponse.getId());
		nfnotainfoitemprodutodeclaracaoimportacao = Objects.insertNFNotaInfoItemProdutoDeclaracaoImportacao(
				1004, TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO, PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR()
				.deleteNFNotaInfoItemProdutoDeclaracaoImportacaoById(nfnotainfoitemprodutodeclaracaoimportacao);
		nfnotainfoitemprodutodeclaracaoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutoDeclaracaoImportacaos() {
		NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao = new NFNotaInfoItemProdutoDeclaracaoImportacao();
		List<NFNotaInfoItemProdutoDeclaracaoImportacao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaos(nfnotainfoitemprodutodeclaracaoimportacao)
				.getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutoDeclaracaoImportacaos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaos();
		NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao = new NFNotaInfoItemProdutoDeclaracaoImportacao();
		List<NFNotaInfoItemProdutoDeclaracaoImportacao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaos(new NFNotaInfoItemProdutoDeclaracaoImportacao())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProdutoDeclaracaoImportacao() {
		NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacao = Objects.insertNFNotaInfoItemProdutoDeclaracaoImportacao(
				1000, TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProdutoDeclaracaoImportacao nfnotainfoitemprodutodeclaracaoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoResponse.getCnpj(), "cnpj_0");
		getNFNotaInfoItemBAR()
				.updateNFNotaInfoItemProdutoDeclaracaoImportacao(nfnotainfoitemprodutodeclaracaoimportacao);
		nfnotainfoitemprodutodeclaracaoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoResponse.getCnpj(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacao> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao() {
		NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao = Objects.insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(
				1004, TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse, null);
		getNFNotaInfoItemBAR()
				.insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(nfnotainfoitemprodutodeclaracaoimportacaoadicao);
		nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoadicao.getId(),
				nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(
				nfnotainfoitemprodutodeclaracaoimportacaoadicao);
		nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos() {
		NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao = new NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao();
		List<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos(
						nfnotainfoitemprodutodeclaracaoimportacaoadicao)
				.getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos();
		NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao = new NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao();
		List<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos(
						new NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao() {
		NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicao = Objects.insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(
				1000, TabelaEnum.NFNOTAINFOITEMPRODUTODECLARACAOIMPORTACAOADICAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse.getCodigoFabricante(), "codigoFabricante_0");
		getNFNotaInfoItemBAR()
				.updateNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao(nfnotainfoitemprodutodeclaracaoimportacaoadicao);
		nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaoById(request);
		Assert.assertEquals(nfnotainfoitemprodutodeclaracaoimportacaoadicaoResponse.getCodigoFabricante(),
				"NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoDeclaracaoImportacaoAdicaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMDETALHEEXPORTACAO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemDetalheExportacao() {
		NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao = Objects.insertNFNotaInfoItemDetalheExportacao(10010,
				TabelaEnum.NFNOTAINFOITEMDETALHEEXPORTACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(10010);
		NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaoById(request);
		Assert.assertEquals(nfnotainfoitemdetalheexportacaoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemDetalheExportacao(nfnotainfoitemdetalheexportacao);
		nfnotainfoitemdetalheexportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaoById(request);
		Assert.assertEquals(nfnotainfoitemdetalheexportacao.getId(), nfnotainfoitemdetalheexportacaoResponse.getId());
		nfnotainfoitemdetalheexportacao = Objects.insertNFNotaInfoItemDetalheExportacao(10010,
				TabelaEnum.NFNOTAINFOITEMDETALHEEXPORTACAO, PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemDetalheExportacaoById(nfnotainfoitemdetalheexportacao);
		nfnotainfoitemdetalheexportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaoById(request);
		Assert.assertEquals(nfnotainfoitemdetalheexportacaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemDetalheExportacaos() {
		NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao = new NFNotaInfoItemDetalheExportacao();
		List<NFNotaInfoItemDetalheExportacao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemDetalheExportacaos(nfnotainfoitemdetalheexportacao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemDetalheExportacaos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemDetalheExportacaos();
		NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao = new NFNotaInfoItemDetalheExportacao();
		List<NFNotaInfoItemDetalheExportacao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemDetalheExportacaos(new NFNotaInfoItemDetalheExportacao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemDetalheExportacao() {
		NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacao = Objects.insertNFNotaInfoItemDetalheExportacao(1000,
				TabelaEnum.NFNOTAINFOITEMDETALHEEXPORTACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemDetalheExportacao nfnotainfoitemdetalheexportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaoById(request);
		Assert.assertEquals(nfnotainfoitemdetalheexportacaoResponse.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemDetalheExportacao(nfnotainfoitemdetalheexportacao);
		nfnotainfoitemdetalheexportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaoById(request);
		Assert.assertEquals(nfnotainfoitemdetalheexportacaoResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemDetalheExportacaosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemDetalheExportacao> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemDetalheExportacaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemDetalheExportacao> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemDetalheExportacaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemDetalheExportacao> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemDetalheExportacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMEXPORTACAOINDIRETA
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemExportacaoIndireta() {
		NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta = Objects.insertNFNotaInfoItemExportacaoIndireta(1004,
				TabelaEnum.NFNOTAINFOITEMEXPORTACAOINDIRETA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindiretaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretaById(request);
		Assert.assertEquals(nfnotainfoitemexportacaoindiretaResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemExportacaoIndireta(nfnotainfoitemexportacaoindireta);
		nfnotainfoitemexportacaoindiretaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretaById(request);
		Assert.assertEquals(nfnotainfoitemexportacaoindireta.getId(), nfnotainfoitemexportacaoindiretaResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemExportacaoIndiretaById(nfnotainfoitemexportacaoindireta);
		nfnotainfoitemexportacaoindiretaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretaById(request);
		Assert.assertEquals(nfnotainfoitemexportacaoindiretaResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemExportacaoIndiretas() {
		NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta = new NFNotaInfoItemExportacaoIndireta();
		List<NFNotaInfoItemExportacaoIndireta> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemExportacaoIndiretas(nfnotainfoitemexportacaoindireta).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemExportacaoIndiretas() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemExportacaoIndiretas();
		NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta = new NFNotaInfoItemExportacaoIndireta();
		List<NFNotaInfoItemExportacaoIndireta> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemExportacaoIndiretas(new NFNotaInfoItemExportacaoIndireta()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemExportacaoIndireta() {
		NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindireta = Objects.insertNFNotaInfoItemExportacaoIndireta(1000,
				TabelaEnum.NFNOTAINFOITEMEXPORTACAOINDIRETA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemExportacaoIndireta nfnotainfoitemexportacaoindiretaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretaById(request);
		Assert.assertEquals(nfnotainfoitemexportacaoindiretaResponse.getChaveAcessoNFe(), "chaveAcessoNFe_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemExportacaoIndireta(nfnotainfoitemexportacaoindireta);
		nfnotainfoitemexportacaoindiretaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretaById(request);
		Assert.assertEquals(nfnotainfoitemexportacaoindiretaResponse.getChaveAcessoNFe(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemExportacaoIndiretasByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretasByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemExportacaoIndiretasByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemExportacaoIndiretas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemExportacaoIndireta> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemExportacaoIndiretasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMPRODUTOVEICULO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProdutoVeiculo() {
		NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo = Objects.insertNFNotaInfoItemProdutoVeiculo(4,
				TabelaEnum.NFNOTAINFOITEMPRODUTOVEICULO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoVeiculoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoveiculoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemProdutoVeiculo(nfnotainfoitemprodutoveiculo);
		nfnotainfoitemprodutoveiculoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoVeiculoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoveiculo.getId(), nfnotainfoitemprodutoveiculoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemProdutoVeiculoById(nfnotainfoitemprodutoveiculo);
		nfnotainfoitemprodutoveiculoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoVeiculoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoveiculoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutoVeiculos() {
		NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo = new NFNotaInfoItemProdutoVeiculo();
		List<NFNotaInfoItemProdutoVeiculo> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoVeiculos(nfnotainfoitemprodutoveiculo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutoVeiculos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoVeiculos();
		NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo = new NFNotaInfoItemProdutoVeiculo();
		List<NFNotaInfoItemProdutoVeiculo> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoVeiculos(new NFNotaInfoItemProdutoVeiculo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProdutoVeiculo() {
		NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculo = Objects.insertNFNotaInfoItemProdutoVeiculo(1000,
				TabelaEnum.NFNOTAINFOITEMPRODUTOVEICULO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProdutoVeiculo nfnotainfoitemprodutoveiculoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoVeiculoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoveiculoResponse.getCapacidadeMaximaTracao(), "capacidadeMaximaTracao_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemProdutoVeiculo(nfnotainfoitemprodutoveiculo);
		nfnotainfoitemprodutoveiculoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoVeiculoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoveiculoResponse.getCapacidadeMaximaTracao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutoVeiculosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoVeiculosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoVeiculosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoVeiculosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoVeiculos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProdutoVeiculo> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoVeiculosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMPRODUTOMEDICAMENTO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProdutoMedicamento() {
		NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento = Objects.insertNFNotaInfoItemProdutoMedicamento(1004,
				TabelaEnum.NFNOTAINFOITEMPRODUTOMEDICAMENTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutomedicamentoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemProdutoMedicamento(nfnotainfoitemprodutomedicamento);
		nfnotainfoitemprodutomedicamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutomedicamento.getId(), nfnotainfoitemprodutomedicamentoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemProdutoMedicamentoById(nfnotainfoitemprodutomedicamento);
		nfnotainfoitemprodutomedicamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutomedicamentoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutoMedicamentos() {
		NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento = new NFNotaInfoItemProdutoMedicamento();
		List<NFNotaInfoItemProdutoMedicamento> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoMedicamentos(nfnotainfoitemprodutomedicamento).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutoMedicamentos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoMedicamentos();
		NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento = new NFNotaInfoItemProdutoMedicamento();
		List<NFNotaInfoItemProdutoMedicamento> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoMedicamentos(new NFNotaInfoItemProdutoMedicamento()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProdutoMedicamento() {
		NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento = Objects.insertNFNotaInfoItemProdutoMedicamento(1000,
				TabelaEnum.NFNOTAINFOITEMPRODUTOMEDICAMENTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutomedicamentoResponse.getQuantidade(), "quantidade_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemProdutoMedicamento(nfnotainfoitemprodutomedicamento);
		nfnotainfoitemprodutomedicamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutomedicamentoResponse.getQuantidade(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutoMedicamentosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoMedicamentosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoMedicamentos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProdutoMedicamento> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoMedicamentosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMPRODUTOARMAMENTO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProdutoArmamento() {
		NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento = Objects.insertNFNotaInfoItemProdutoArmamento(4,
				TabelaEnum.NFNOTAINFOITEMPRODUTOARMAMENTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoarmamentoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemProdutoArmamento(nfnotainfoitemprodutoarmamento);
		nfnotainfoitemprodutoarmamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoarmamento.getId(), nfnotainfoitemprodutoarmamentoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemProdutoArmamentoById(nfnotainfoitemprodutoarmamento);
		nfnotainfoitemprodutoarmamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoarmamentoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutoArmamentos() {
		NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento = new NFNotaInfoItemProdutoArmamento();
		List<NFNotaInfoItemProdutoArmamento> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoArmamentos(nfnotainfoitemprodutoarmamento).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutoArmamentos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoArmamentos();
		NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento = new NFNotaInfoItemProdutoArmamento();
		List<NFNotaInfoItemProdutoArmamento> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoArmamentos(new NFNotaInfoItemProdutoArmamento()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProdutoArmamento() {
		NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamento = Objects.insertNFNotaInfoItemProdutoArmamento(1000,
				TabelaEnum.NFNOTAINFOITEMPRODUTOARMAMENTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProdutoArmamento nfnotainfoitemprodutoarmamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoarmamentoResponse.getDescricao(), "descricao_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemProdutoArmamento(nfnotainfoitemprodutoarmamento);
		nfnotainfoitemprodutoarmamentoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentoById(request);
		Assert.assertEquals(nfnotainfoitemprodutoarmamentoResponse.getDescricao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutoArmamentosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProdutoArmamento> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoArmamentosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProdutoArmamento> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoArmamentos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProdutoArmamento> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoArmamentosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMPRODUTOCOMBUSTIVEL
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProdutoCombustivel() {
		NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel = Objects.insertNFNotaInfoItemProdutoCombustivel(4,
				TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVEL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemProdutoCombustivel(nfnotainfoitemprodutocombustivel);
		nfnotainfoitemprodutocombustivelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivel.getId(), nfnotainfoitemprodutocombustivelResponse.getId());
		nfnotainfoitemprodutocombustivel = Objects.insertNFNotaInfoItemProdutoCombustivel(4,
				TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVEL, PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemProdutoCombustivelById(nfnotainfoitemprodutocombustivel);
		nfnotainfoitemprodutocombustivelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutoCombustivels() {
		NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel = new NFNotaInfoItemProdutoCombustivel();
		List<NFNotaInfoItemProdutoCombustivel> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoCombustivels(nfnotainfoitemprodutocombustivel).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutoCombustivels() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoCombustivels();
		NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel = new NFNotaInfoItemProdutoCombustivel();
		List<NFNotaInfoItemProdutoCombustivel> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoCombustivels(new NFNotaInfoItemProdutoCombustivel()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProdutoCombustivel() {
		NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivel = Objects.insertNFNotaInfoItemProdutoCombustivel(1000,
				TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVEL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProdutoCombustivel nfnotainfoitemprodutocombustivelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelResponse.getCodigoAutorizacaoCOFIF(), "codigoAutorizacaoCOFIF_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemProdutoCombustivel(nfnotainfoitemprodutocombustivel);
		nfnotainfoitemprodutocombustivelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelResponse.getCodigoAutorizacaoCOFIF(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutoCombustivelsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoCombustivelsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoCombustivels();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivel> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemProdutoCombustivelCIDE() {
		NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide = Objects.insertNFNotaInfoItemProdutoCombustivelCIDE(
				4, TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcideResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelcideResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemProdutoCombustivelCIDE(nfnotainfoitemprodutocombustivelcide);
		nfnotainfoitemprodutocombustivelcideResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelcide.getId(),
				nfnotainfoitemprodutocombustivelcideResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemProdutoCombustivelCIDEById(nfnotainfoitemprodutocombustivelcide);
		nfnotainfoitemprodutocombustivelcideResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelcideResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemProdutoCombustivelCIDEs() {
		NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide = new NFNotaInfoItemProdutoCombustivelCIDE();
		List<NFNotaInfoItemProdutoCombustivelCIDE> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoCombustivelCIDEs(nfnotainfoitemprodutocombustivelcide).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemProdutoCombustivelCIDEs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoCombustivelCIDEs();
		NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide = new NFNotaInfoItemProdutoCombustivelCIDE();
		List<NFNotaInfoItemProdutoCombustivelCIDE> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemProdutoCombustivelCIDEs(new NFNotaInfoItemProdutoCombustivelCIDE())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemProdutoCombustivelCIDE() {
		NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcide = Objects.insertNFNotaInfoItemProdutoCombustivelCIDE(
				1000, TabelaEnum.NFNOTAINFOITEMPRODUTOCOMBUSTIVELCIDE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemProdutoCombustivelCIDE nfnotainfoitemprodutocombustivelcideResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelcideResponse.getQuantidadeBCCIDE(), "quantidadeBCCIDE_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemProdutoCombustivelCIDE(nfnotainfoitemprodutocombustivelcide);
		nfnotainfoitemprodutocombustivelcideResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEById(request);
		Assert.assertEquals(nfnotainfoitemprodutocombustivelcideResponse.getQuantidadeBCCIDE(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemProdutoCombustivelCIDEs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemProdutoCombustivelCIDE> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemProdutoCombustivelCIDEsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFIMPOSTODEVOLVIDO
	// ####======================================

	@Test
	public void testDeleteNFImpostoDevolvido1() {
		NFImpostoDevolvido nfimpostodevolvido = Objects.insertNFImpostoDevolvido(4, TabelaEnum.NFIMPOSTODEVOLVIDO,
				PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFImpostoDevolvido nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse, null);
		getNFNotaInfoItemBAR().insertNFImpostoDevolvido(nfimpostodevolvido);
		nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvido.getId(), nfimpostodevolvidoResponse.getId());
		nfimpostodevolvido = Objects.insertNFImpostoDevolvido(4, TabelaEnum.NFIMPOSTODEVOLVIDO,
				PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFImpostoDevolvidoById(nfimpostodevolvido);
		nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse, null);
	}

	@Test
	public void testFetchAllNFImpostoDevolvidos1() {
		NFImpostoDevolvido nfimpostodevolvido = new NFImpostoDevolvido();
		List<NFImpostoDevolvido> response = getNFNotaInfoItemBAR().fetchAllNFImpostoDevolvidos(nfimpostodevolvido)
				.getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFImpostoDevolvidos1() {
		getNFNotaInfoItemBAR().deleteAllNFImpostoDevolvidos();
		NFImpostoDevolvido nfimpostodevolvido = new NFImpostoDevolvido();
		List<NFImpostoDevolvido> response = getNFNotaInfoItemBAR().fetchAllNFImpostoDevolvidos(new NFImpostoDevolvido())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFImpostoDevolvido1() {
		NFImpostoDevolvido nfimpostodevolvido = Objects.insertNFImpostoDevolvido(1000, TabelaEnum.NFIMPOSTODEVOLVIDO,
				PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFImpostoDevolvido nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse.getPercentualDevolucao(), "percentualDevolucao_0");
		getNFNotaInfoItemBAR().updateNFImpostoDevolvido(nfimpostodevolvido);
		nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse.getPercentualDevolucao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFImpostoDevolvidosByRequest1() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchNFImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFImpostoDevolvido> response2 = getNFNotaInfoItemBAR()
				.fetchNFImpostoDevolvidosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFImpostoDevolvidos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFImpostoDevolvido> response3 = getNFNotaInfoItemBAR()
				.fetchNFImpostoDevolvidosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFINFORMACAOIMPOSTODEVOLVIDO
	// ####======================================

	@Test
	public void testDeleteNFInformacaoImpostoDevolvido() {
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = Objects.insertNFInformacaoImpostoDevolvido(4,
				TabelaEnum.NFINFORMACAOIMPOSTODEVOLVIDO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse, null);
		getNFNotaInfoItemBAR().insertNFInformacaoImpostoDevolvido(nfinformacaoimpostodevolvido);
		nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvido.getId(), nfinformacaoimpostodevolvidoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFInformacaoImpostoDevolvidoById(nfinformacaoimpostodevolvido);
		nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse, null);
	}

	@Test
	public void testFetchAllNFInformacaoImpostoDevolvidos() {
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = new NFInformacaoImpostoDevolvido();
		List<NFInformacaoImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchAllNFInformacaoImpostoDevolvidos(nfinformacaoimpostodevolvido).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFInformacaoImpostoDevolvidos() {
		getNFNotaInfoItemBAR().deleteAllNFInformacaoImpostoDevolvidos();
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = new NFInformacaoImpostoDevolvido();
		List<NFInformacaoImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchAllNFInformacaoImpostoDevolvidos(new NFInformacaoImpostoDevolvido()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFInformacaoImpostoDevolvido() {
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = Objects.insertNFInformacaoImpostoDevolvido(1000,
				TabelaEnum.NFINFORMACAOIMPOSTODEVOLVIDO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse.getValorIPIDevolvido(), "valorIPIDevolvido_0");
		getNFNotaInfoItemBAR().updateNFInformacaoImpostoDevolvido(nfinformacaoimpostodevolvido);
		nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse.getValorIPIDevolvido(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFInformacaoImpostoDevolvidosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response2 = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFInformacaoImpostoDevolvidos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response3 = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImposto() {
		NFNotaInfoItemImposto nfnotainfoitemimposto = Objects.insertNFNotaInfoItemImposto(4, TabelaEnum.NFNOTAINFOITEMIMPOSTO,
				PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImposto nfnotainfoitemimpostoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImposto(nfnotainfoitemimposto);
		nfnotainfoitemimpostoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoById(request);
		Assert.assertEquals(nfnotainfoitemimposto.getId(), nfnotainfoitemimpostoResponse.getId());
		nfnotainfoitemimposto = Objects.insertNFNotaInfoItemImposto(4, TabelaEnum.NFNOTAINFOITEMIMPOSTO,
				PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoById(nfnotainfoitemimposto);
		nfnotainfoitemimpostoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostos() {
		NFNotaInfoItemImposto nfnotainfoitemimposto = new NFNotaInfoItemImposto();
		List<NFNotaInfoItemImposto> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostos(nfnotainfoitemimposto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostos();
		NFNotaInfoItemImposto nfnotainfoitemimposto = new NFNotaInfoItemImposto();
		List<NFNotaInfoItemImposto> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostos(new NFNotaInfoItemImposto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImposto() {
		NFNotaInfoItemImposto nfnotainfoitemimposto = Objects.insertNFNotaInfoItemImposto(1000, TabelaEnum.NFNOTAINFOITEMIMPOSTO,
				PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImposto nfnotainfoitemimpostoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoResponse.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImposto(nfnotainfoitemimposto);
		nfnotainfoitemimpostoResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImposto> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImposto> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImposto> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS() {
		NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms = Objects.insertNFNotaInfoItemImpostoICMS(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicmsResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS(nfnotainfoitemimpostoicms);
		nfnotainfoitemimpostoicmsResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms.getId(), nfnotainfoitemimpostoicmsResponse.getId());
		nfnotainfoitemimpostoicms = Objects.insertNFNotaInfoItemImpostoICMS(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS, PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSById(nfnotainfoitemimpostoicms);
		nfnotainfoitemimpostoicmsResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSs() {
		NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms = new NFNotaInfoItemImpostoICMS();
		List<NFNotaInfoItemImpostoICMS> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSs(nfnotainfoitemimpostoicms).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSs();
		NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms = new NFNotaInfoItemImpostoICMS();
		List<NFNotaInfoItemImpostoICMS> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSs(new NFNotaInfoItemImpostoICMS()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS() {
		NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicms = Objects.insertNFNotaInfoItemImpostoICMS(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS nfnotainfoitemimpostoicmsResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsResponse.getModifyUser(), "rod");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS(nfnotainfoitemimpostoicms);
		nfnotainfoitemimpostoicmsResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsResponse.getModifyUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS00
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS00() {
		NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00 = Objects.insertNFNotaInfoItemImpostoICMS00(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS00, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS00ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms00Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS00(nfnotainfoitemimpostoicms00);
		nfnotainfoitemimpostoicms00Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS00ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms00.getId(), nfnotainfoitemimpostoicms00Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS00ById(nfnotainfoitemimpostoicms00);
		nfnotainfoitemimpostoicms00Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS00ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms00Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS00s() {
		NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00 = new NFNotaInfoItemImpostoICMS00();
		List<NFNotaInfoItemImpostoICMS00> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS00s(nfnotainfoitemimpostoicms00).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS00s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS00s();
		NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00 = new NFNotaInfoItemImpostoICMS00();
		List<NFNotaInfoItemImpostoICMS00> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS00s(new NFNotaInfoItemImpostoICMS00()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS00() {
		NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00 = Objects.insertNFNotaInfoItemImpostoICMS00(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS00, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS00 nfnotainfoitemimpostoicms00Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS00ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms00Response.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS00(nfnotainfoitemimpostoicms00);
		nfnotainfoitemimpostoicms00Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS00ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms00Response.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS00sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS00> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS00sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS00sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS00> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS00sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS00s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS00> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS00sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS10
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS10() {
		NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10 = Objects.insertNFNotaInfoItemImpostoICMS10(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS10, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS10ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms10Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS10(nfnotainfoitemimpostoicms10);
		nfnotainfoitemimpostoicms10Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS10ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms10.getId(), nfnotainfoitemimpostoicms10Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS10ById(nfnotainfoitemimpostoicms10);
		nfnotainfoitemimpostoicms10Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS10ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms10Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS10s() {
		NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10 = new NFNotaInfoItemImpostoICMS10();
		List<NFNotaInfoItemImpostoICMS10> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS10s(nfnotainfoitemimpostoicms10).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS10s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS10s();
		NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10 = new NFNotaInfoItemImpostoICMS10();
		List<NFNotaInfoItemImpostoICMS10> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS10s(new NFNotaInfoItemImpostoICMS10()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS10() {
		NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10 = Objects.insertNFNotaInfoItemImpostoICMS10(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS10, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS10 nfnotainfoitemimpostoicms10Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS10ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms10Response.getValorBaseCalculo(), "valorBaseCalculo_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS10(nfnotainfoitemimpostoicms10);
		nfnotainfoitemimpostoicms10Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS10ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms10Response.getValorBaseCalculo(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS10sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS10> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS10sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS10sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS10> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS10sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS10s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS10> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS10sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS20
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS20() {
		NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20 = Objects.insertNFNotaInfoItemImpostoICMS20(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS20, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS20ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms20Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS20(nfnotainfoitemimpostoicms20);
		nfnotainfoitemimpostoicms20Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS20ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms20.getId(), nfnotainfoitemimpostoicms20Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS20ById(nfnotainfoitemimpostoicms20);
		nfnotainfoitemimpostoicms20Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS20ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms20Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS20s() {
		NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20 = new NFNotaInfoItemImpostoICMS20();
		List<NFNotaInfoItemImpostoICMS20> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS20s(nfnotainfoitemimpostoicms20).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS20s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS20s();
		NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20 = new NFNotaInfoItemImpostoICMS20();
		List<NFNotaInfoItemImpostoICMS20> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS20s(new NFNotaInfoItemImpostoICMS20()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS20() {
		NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20 = Objects.insertNFNotaInfoItemImpostoICMS20(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS20, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS20 nfnotainfoitemimpostoicms20Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS20ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms20Response.getValorBCICMS(), "valorBCICMS_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS20(nfnotainfoitemimpostoicms20);
		nfnotainfoitemimpostoicms20Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS20ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms20Response.getValorBCICMS(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS20sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS20> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS20sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS20sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS20> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS20sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS20s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS20> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS20sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS30
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS30() {
		NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30 = Objects.insertNFNotaInfoItemImpostoICMS30(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS30, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS30ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms30Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS30(nfnotainfoitemimpostoicms30);
		nfnotainfoitemimpostoicms30Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS30ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms30.getId(), nfnotainfoitemimpostoicms30Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS30ById(nfnotainfoitemimpostoicms30);
		nfnotainfoitemimpostoicms30Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS30ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms30Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS30s() {
		NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30 = new NFNotaInfoItemImpostoICMS30();
		List<NFNotaInfoItemImpostoICMS30> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS30s(nfnotainfoitemimpostoicms30).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS30s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS30s();
		NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30 = new NFNotaInfoItemImpostoICMS30();
		List<NFNotaInfoItemImpostoICMS30> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS30s(new NFNotaInfoItemImpostoICMS30()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS30() {
		NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30 = Objects.insertNFNotaInfoItemImpostoICMS30(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS30, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS30 nfnotainfoitemimpostoicms30Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS30ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms30Response.getPercentualAliquotaImpostoICMSST(), "percentualAliquotaImpostoICMSST_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS30(nfnotainfoitemimpostoicms30);
		nfnotainfoitemimpostoicms30Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS30ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms30Response.getPercentualAliquotaImpostoICMSST(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS30sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS30> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS30sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS30sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS30> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS30sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS30s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS30> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS30sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS40
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS40() {
		NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40 = Objects.insertNFNotaInfoItemImpostoICMS40(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS40, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS40ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms40Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS40(nfnotainfoitemimpostoicms40);
		nfnotainfoitemimpostoicms40Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS40ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms40.getId(), nfnotainfoitemimpostoicms40Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS40ById(nfnotainfoitemimpostoicms40);
		nfnotainfoitemimpostoicms40Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS40ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms40Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS40s() {
		NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40 = new NFNotaInfoItemImpostoICMS40();
		List<NFNotaInfoItemImpostoICMS40> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS40s(nfnotainfoitemimpostoicms40).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS40s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS40s();
		NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40 = new NFNotaInfoItemImpostoICMS40();
		List<NFNotaInfoItemImpostoICMS40> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS40s(new NFNotaInfoItemImpostoICMS40()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS40() {
		NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40 = Objects.insertNFNotaInfoItemImpostoICMS40(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS40, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS40 nfnotainfoitemimpostoicms40Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS40ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms40Response.getValorICMSDesoneracao(), "valorICMSDesoneracao_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS40(nfnotainfoitemimpostoicms40);
		nfnotainfoitemimpostoicms40Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS40ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms40Response.getValorICMSDesoneracao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS40sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS40> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS40sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS40sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS40> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS40sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS40s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS40> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS40sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS51
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS51() {
		NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51 = Objects.insertNFNotaInfoItemImpostoICMS51(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS51, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS51ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms51Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS51(nfnotainfoitemimpostoicms51);
		nfnotainfoitemimpostoicms51Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS51ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms51.getId(), nfnotainfoitemimpostoicms51Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS51ById(nfnotainfoitemimpostoicms51);
		nfnotainfoitemimpostoicms51Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS51ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms51Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS51s() {
		NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51 = new NFNotaInfoItemImpostoICMS51();
		List<NFNotaInfoItemImpostoICMS51> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS51s(nfnotainfoitemimpostoicms51).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS51s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS51s();
		NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51 = new NFNotaInfoItemImpostoICMS51();
		List<NFNotaInfoItemImpostoICMS51> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS51s(new NFNotaInfoItemImpostoICMS51()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS51() {
		NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51 = Objects.insertNFNotaInfoItemImpostoICMS51(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS51, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS51 nfnotainfoitemimpostoicms51Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS51ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms51Response.getPercentualDiferimento(), "percentualDiferimento_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS51(nfnotainfoitemimpostoicms51);
		nfnotainfoitemimpostoicms51Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS51ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms51Response.getPercentualDiferimento(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS51sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS51> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS51sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS51sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS51> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS51sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS51s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS51> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS51sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS60
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS60() {
		NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60 = Objects.insertNFNotaInfoItemImpostoICMS60(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS60, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS60ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms60Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS60(nfnotainfoitemimpostoicms60);
		nfnotainfoitemimpostoicms60Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS60ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms60.getId(), nfnotainfoitemimpostoicms60Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS60ById(nfnotainfoitemimpostoicms60);
		nfnotainfoitemimpostoicms60Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS60ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms60Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS60s() {
		NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60 = new NFNotaInfoItemImpostoICMS60();
		List<NFNotaInfoItemImpostoICMS60> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS60s(nfnotainfoitemimpostoicms60).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS60s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS60s();
		NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60 = new NFNotaInfoItemImpostoICMS60();
		List<NFNotaInfoItemImpostoICMS60> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS60s(new NFNotaInfoItemImpostoICMS60()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS60() {
		NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60 = Objects.insertNFNotaInfoItemImpostoICMS60(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS60, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS60 nfnotainfoitemimpostoicms60Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS60ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms60Response.getValorICMSSTRetido(), "valorICMSSTRetido_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS60(nfnotainfoitemimpostoicms60);
		nfnotainfoitemimpostoicms60Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS60ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms60Response.getValorICMSSTRetido(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS60sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS60> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS60sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS60sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS60> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS60sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS60s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS60> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS60sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS70
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS70() {
		NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70 = Objects.insertNFNotaInfoItemImpostoICMS70(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS70, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS70ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms70Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS70(nfnotainfoitemimpostoicms70);
		nfnotainfoitemimpostoicms70Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS70ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms70.getId(), nfnotainfoitemimpostoicms70Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS70ById(nfnotainfoitemimpostoicms70);
		nfnotainfoitemimpostoicms70Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS70ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms70Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS70s() {
		NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70 = new NFNotaInfoItemImpostoICMS70();
		List<NFNotaInfoItemImpostoICMS70> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS70s(nfnotainfoitemimpostoicms70).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS70s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS70s();
		NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70 = new NFNotaInfoItemImpostoICMS70();
		List<NFNotaInfoItemImpostoICMS70> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS70s(new NFNotaInfoItemImpostoICMS70()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS70() {
		NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70 = Objects.insertNFNotaInfoItemImpostoICMS70(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS70, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS70 nfnotainfoitemimpostoicms70Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS70ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms70Response.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS70(nfnotainfoitemimpostoicms70);
		nfnotainfoitemimpostoicms70Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS70ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms70Response.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS70sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS70> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS70sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS70sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS70> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS70sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS70s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS70> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS70sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMS90
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMS90() {
		NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90 = Objects.insertNFNotaInfoItemImpostoICMS90(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS90, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS90ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms90Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMS90(nfnotainfoitemimpostoicms90);
		nfnotainfoitemimpostoicms90Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS90ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms90.getId(), nfnotainfoitemimpostoicms90Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMS90ById(nfnotainfoitemimpostoicms90);
		nfnotainfoitemimpostoicms90Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS90ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms90Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMS90s() {
		NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90 = new NFNotaInfoItemImpostoICMS90();
		List<NFNotaInfoItemImpostoICMS90> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS90s(nfnotainfoitemimpostoicms90).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMS90s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS90s();
		NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90 = new NFNotaInfoItemImpostoICMS90();
		List<NFNotaInfoItemImpostoICMS90> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMS90s(new NFNotaInfoItemImpostoICMS90()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMS90() {
		NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90 = Objects.insertNFNotaInfoItemImpostoICMS90(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMS90, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMS90 nfnotainfoitemimpostoicms90Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS90ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms90Response.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMS90(nfnotainfoitemimpostoicms90);
		nfnotainfoitemimpostoicms90Response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS90ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicms90Response.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMS90sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS90> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS90sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMS90sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS90> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS90sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMS90s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMS90> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMS90sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOICMSPARTILHADO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSPartilhado() {
		NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado = Objects.insertNFNotaInfoItemImpostoICMSPartilhado(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSPARTILHADO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmspartilhadoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSPartilhado(nfnotainfoitemimpostoicmspartilhado);
		nfnotainfoitemimpostoicmspartilhadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmspartilhado.getId(),
				nfnotainfoitemimpostoicmspartilhadoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSPartilhadoById(nfnotainfoitemimpostoicmspartilhado);
		nfnotainfoitemimpostoicmspartilhadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmspartilhadoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSPartilhados() {
		NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado = new NFNotaInfoItemImpostoICMSPartilhado();
		List<NFNotaInfoItemImpostoICMSPartilhado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSPartilhados(nfnotainfoitemimpostoicmspartilhado).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSPartilhados() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSPartilhados();
		NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado = new NFNotaInfoItemImpostoICMSPartilhado();
		List<NFNotaInfoItemImpostoICMSPartilhado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSPartilhados(new NFNotaInfoItemImpostoICMSPartilhado())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSPartilhado() {
		NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhado = Objects.insertNFNotaInfoItemImpostoICMSPartilhado(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSPARTILHADO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSPartilhado nfnotainfoitemimpostoicmspartilhadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmspartilhadoResponse.getPercentualReducaoBCICMSST(), "percentualReducaoBCICMSST_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSPartilhado(nfnotainfoitemimpostoicmspartilhado);
		nfnotainfoitemimpostoicmspartilhadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmspartilhadoResponse.getPercentualReducaoBCICMSST(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSPartilhadosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSPartilhados();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSPartilhado> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSPartilhadosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSST
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSST() {
		NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst = Objects.insertNFNotaInfoItemImpostoICMSST(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSST, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsstResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsstResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSST(nfnotainfoitemimpostoicmsst);
		nfnotainfoitemimpostoicmsstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsst.getId(), nfnotainfoitemimpostoicmsstResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSSTById(nfnotainfoitemimpostoicmsst);
		nfnotainfoitemimpostoicmsstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsstResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSSTs() {
		NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst = new NFNotaInfoItemImpostoICMSST();
		List<NFNotaInfoItemImpostoICMSST> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSTs(nfnotainfoitemimpostoicmsst).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSSTs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSTs();
		NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst = new NFNotaInfoItemImpostoICMSST();
		List<NFNotaInfoItemImpostoICMSST> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSTs(new NFNotaInfoItemImpostoICMSST()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSST() {
		NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsst = Objects.insertNFNotaInfoItemImpostoICMSST(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSST, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSST nfnotainfoitemimpostoicmsstResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsstResponse.getValorBCICMSSTRetidoUFRemetente(), "valorBCICMSSTRetidoUFRemetente_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSST(nfnotainfoitemimpostoicmsst);
		nfnotainfoitemimpostoicmsstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsstResponse.getValorBCICMSSTRetidoUFRemetente(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSSTsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSST> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSTsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSTsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSST> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSTsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSTs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSST> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSTsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN101
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSSN101() {
		NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101 = Objects.insertNFNotaInfoItemImpostoICMSSN101(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN101, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn101Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSSN101(nfnotainfoitemimpostoicmssn101);
		nfnotainfoitemimpostoicmssn101Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn101.getId(), nfnotainfoitemimpostoicmssn101Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSSN101ById(nfnotainfoitemimpostoicmssn101);
		nfnotainfoitemimpostoicmssn101Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn101Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSSN101s() {
		NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101 = new NFNotaInfoItemImpostoICMSSN101();
		List<NFNotaInfoItemImpostoICMSSN101> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN101s(nfnotainfoitemimpostoicmssn101).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSSN101s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN101s();
		NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101 = new NFNotaInfoItemImpostoICMSSN101();
		List<NFNotaInfoItemImpostoICMSSN101> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN101s(new NFNotaInfoItemImpostoICMSSN101()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSSN101() {
		NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101 = Objects.insertNFNotaInfoItemImpostoICMSSN101(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN101, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSSN101 nfnotainfoitemimpostoicmssn101Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn101Response.getPercentualAliquotaAplicavelCalculoCreditoSN(), "percentualAliquotaAplicavelCalculoCreditoSN_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSSN101(nfnotainfoitemimpostoicmssn101);
		nfnotainfoitemimpostoicmssn101Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn101Response.getPercentualAliquotaAplicavelCalculoCreditoSN(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSSN101sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSN101sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN101s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN101> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN101sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN102
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSSN102() {
		NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102 = Objects.insertNFNotaInfoItemImpostoICMSSN102(1004,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN102, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn102Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSSN102(nfnotainfoitemimpostoicmssn102);
		nfnotainfoitemimpostoicmssn102Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn102.getId(), nfnotainfoitemimpostoicmssn102Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSSN102ById(nfnotainfoitemimpostoicmssn102);
		nfnotainfoitemimpostoicmssn102Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn102Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSSN102s() {
		NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102 = new NFNotaInfoItemImpostoICMSSN102();
		List<NFNotaInfoItemImpostoICMSSN102> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN102s(nfnotainfoitemimpostoicmssn102).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSSN102s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN102s();
		NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102 = new NFNotaInfoItemImpostoICMSSN102();
		List<NFNotaInfoItemImpostoICMSSN102> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN102s(new NFNotaInfoItemImpostoICMSSN102()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSSN102() {
		NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102 = Objects.insertNFNotaInfoItemImpostoICMSSN102(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN102, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSSN102 nfnotainfoitemimpostoicmssn102Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn102Response.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSSN102(nfnotainfoitemimpostoicmssn102);
		nfnotainfoitemimpostoicmssn102Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn102Response.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSSN102sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSN102sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN102s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN102> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN102sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN201
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSSN201() {
		NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201 = Objects.insertNFNotaInfoItemImpostoICMSSN201(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN201, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn201Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSSN201(nfnotainfoitemimpostoicmssn201);
		nfnotainfoitemimpostoicmssn201Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn201.getId(), nfnotainfoitemimpostoicmssn201Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSSN201ById(nfnotainfoitemimpostoicmssn201);
		nfnotainfoitemimpostoicmssn201Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn201Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSSN201s() {
		NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201 = new NFNotaInfoItemImpostoICMSSN201();
		List<NFNotaInfoItemImpostoICMSSN201> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN201s(nfnotainfoitemimpostoicmssn201).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSSN201s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN201s();
		NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201 = new NFNotaInfoItemImpostoICMSSN201();
		List<NFNotaInfoItemImpostoICMSSN201> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN201s(new NFNotaInfoItemImpostoICMSSN201()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSSN201() {
		NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201 = Objects.insertNFNotaInfoItemImpostoICMSSN201(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN201, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSSN201 nfnotainfoitemimpostoicmssn201Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn201Response.getPercentualAliquotaAplicavelCalculoCreditoSN(), "percentualAliquotaAplicavelCalculoCreditoSN_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSSN201(nfnotainfoitemimpostoicmssn201);
		nfnotainfoitemimpostoicmssn201Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn201Response.getPercentualAliquotaAplicavelCalculoCreditoSN(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSSN201sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSN201sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN201s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN201> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN201sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN202
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSSN202() {
		NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202 = Objects.insertNFNotaInfoItemImpostoICMSSN202(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN202, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn202Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSSN202(nfnotainfoitemimpostoicmssn202);
		nfnotainfoitemimpostoicmssn202Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn202.getId(), nfnotainfoitemimpostoicmssn202Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSSN202ById(nfnotainfoitemimpostoicmssn202);
		nfnotainfoitemimpostoicmssn202Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn202Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSSN202s() {
		NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202 = new NFNotaInfoItemImpostoICMSSN202();
		List<NFNotaInfoItemImpostoICMSSN202> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN202s(nfnotainfoitemimpostoicmssn202).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSSN202s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN202s();
		NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202 = new NFNotaInfoItemImpostoICMSSN202();
		List<NFNotaInfoItemImpostoICMSSN202> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN202s(new NFNotaInfoItemImpostoICMSSN202()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSSN202() {
		NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202 = Objects.insertNFNotaInfoItemImpostoICMSSN202(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN202, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSSN202 nfnotainfoitemimpostoicmssn202Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn202Response.getPercentualAliquotaImpostoICMSST(), "percentualAliquotaImpostoICMSST_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSSN202(nfnotainfoitemimpostoicmssn202);
		nfnotainfoitemimpostoicmssn202Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn202Response.getPercentualAliquotaImpostoICMSST(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSSN202sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSN202sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN202s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN202> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN202sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN500
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSSN500() {
		NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500 = Objects.insertNFNotaInfoItemImpostoICMSSN500(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN500, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn500Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSSN500(nfnotainfoitemimpostoicmssn500);
		nfnotainfoitemimpostoicmssn500Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn500.getId(), nfnotainfoitemimpostoicmssn500Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSSN500ById(nfnotainfoitemimpostoicmssn500);
		nfnotainfoitemimpostoicmssn500Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn500Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSSN500s() {
		NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500 = new NFNotaInfoItemImpostoICMSSN500();
		List<NFNotaInfoItemImpostoICMSSN500> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN500s(nfnotainfoitemimpostoicmssn500).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSSN500s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN500s();
		NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500 = new NFNotaInfoItemImpostoICMSSN500();
		List<NFNotaInfoItemImpostoICMSSN500> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN500s(new NFNotaInfoItemImpostoICMSSN500()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSSN500() {
		NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500 = Objects.insertNFNotaInfoItemImpostoICMSSN500(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN500, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSSN500 nfnotainfoitemimpostoicmssn500Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn500Response.getValorBCICMSSTRetido(), "valorBCICMSSTRetido_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSSN500(nfnotainfoitemimpostoicmssn500);
		nfnotainfoitemimpostoicmssn500Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn500Response.getValorBCICMSSTRetido(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSSN500sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSN500sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN500s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN500> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN500sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSSN900
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSSN900() {
		NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900 = Objects.insertNFNotaInfoItemImpostoICMSSN900(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN900, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn900Response, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSSN900(nfnotainfoitemimpostoicmssn900);
		nfnotainfoitemimpostoicmssn900Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn900.getId(), nfnotainfoitemimpostoicmssn900Response.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSSN900ById(nfnotainfoitemimpostoicmssn900);
		nfnotainfoitemimpostoicmssn900Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn900Response, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSSN900s() {
		NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900 = new NFNotaInfoItemImpostoICMSSN900();
		List<NFNotaInfoItemImpostoICMSSN900> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN900s(nfnotainfoitemimpostoicmssn900).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSSN900s() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN900s();
		NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900 = new NFNotaInfoItemImpostoICMSSN900();
		List<NFNotaInfoItemImpostoICMSSN900> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSSN900s(new NFNotaInfoItemImpostoICMSSN900()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSSN900() {
		NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900 = Objects.insertNFNotaInfoItemImpostoICMSSN900(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSSN900, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSSN900 nfnotainfoitemimpostoicmssn900Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn900Response.getOrigem(), "origem_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSSN900(nfnotainfoitemimpostoicmssn900);
		nfnotainfoitemimpostoicmssn900Response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900ById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmssn900Response.getOrigem(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSSN900sByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSSN900sByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900sByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSSN900s();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSSN900> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSSN900sByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOIPI
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoIPI() {
		NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi = Objects.insertNFNotaInfoItemImpostoIPI(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOIPI, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipiResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPIById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipiResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoIPI(nfnotainfoitemimpostoipi);
		nfnotainfoitemimpostoipiResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoIPIById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipi.getId(), nfnotainfoitemimpostoipiResponse.getId());
		nfnotainfoitemimpostoipi = Objects.insertNFNotaInfoItemImpostoIPI(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOIPI, PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoIPIById(nfnotainfoitemimpostoipi);
		nfnotainfoitemimpostoipiResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoIPIById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipiResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoIPIs() {
		NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi = new NFNotaInfoItemImpostoIPI();
		List<NFNotaInfoItemImpostoIPI> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoIPIs(nfnotainfoitemimpostoipi).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoIPIs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoIPIs();
		NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi = new NFNotaInfoItemImpostoIPI();
		List<NFNotaInfoItemImpostoIPI> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoIPIs(new NFNotaInfoItemImpostoIPI()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoIPI() {
		NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipi = Objects.insertNFNotaInfoItemImpostoIPI(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOIPI, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoIPI nfnotainfoitemimpostoipiResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPIById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipiResponse.getClasseEnquadramento(), "classeEnquadramento_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoIPI(nfnotainfoitemimpostoipi);
		nfnotainfoitemimpostoipiResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoIPIById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipiResponse.getClasseEnquadramento(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoIPIsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoIPI> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPIsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoIPIsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoIPI> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPIsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoIPIs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoIPI> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPIsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOIPITRIBUTADO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoIPITributado() {
		NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado = Objects.insertNFNotaInfoItemImpostoIPITributado(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOIPITRIBUTADO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipitributadoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoIPITributado(nfnotainfoitemimpostoipitributado);
		nfnotainfoitemimpostoipitributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipitributado.getId(),
				nfnotainfoitemimpostoipitributadoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoIPITributadoById(nfnotainfoitemimpostoipitributado);
		nfnotainfoitemimpostoipitributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipitributadoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoIPITributados() {
		NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado = new NFNotaInfoItemImpostoIPITributado();
		List<NFNotaInfoItemImpostoIPITributado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoIPITributados(nfnotainfoitemimpostoipitributado).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoIPITributados() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoIPITributados();
		NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado = new NFNotaInfoItemImpostoIPITributado();
		List<NFNotaInfoItemImpostoIPITributado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoIPITributados(new NFNotaInfoItemImpostoIPITributado()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoIPITributado() {
		NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributado = Objects.insertNFNotaInfoItemImpostoIPITributado(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOIPITRIBUTADO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoIPITributado nfnotainfoitemimpostoipitributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipitributadoResponse.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoIPITributado(nfnotainfoitemimpostoipitributado);
		nfnotainfoitemimpostoipitributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipitributadoResponse.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoIPITributadosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoIPITributadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoIPITributados();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoIPITributado> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPITributadosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoIPINaoTributado() {
		NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado = Objects.insertNFNotaInfoItemImpostoIPINaoTributado(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipinaotributadoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoIPINaoTributado(nfnotainfoitemimpostoipinaotributado);
		nfnotainfoitemimpostoipinaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipinaotributado.getId(),
				nfnotainfoitemimpostoipinaotributadoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoIPINaoTributadoById(nfnotainfoitemimpostoipinaotributado);
		nfnotainfoitemimpostoipinaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipinaotributadoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoIPINaoTributados() {
		NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado = new NFNotaInfoItemImpostoIPINaoTributado();
		List<NFNotaInfoItemImpostoIPINaoTributado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoIPINaoTributados(nfnotainfoitemimpostoipinaotributado).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoIPINaoTributados() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoIPINaoTributados();
		NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado = new NFNotaInfoItemImpostoIPINaoTributado();
		List<NFNotaInfoItemImpostoIPINaoTributado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoIPINaoTributados(new NFNotaInfoItemImpostoIPINaoTributado())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoIPINaoTributado() {
		NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributado = Objects.insertNFNotaInfoItemImpostoIPINaoTributado(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOIPINAOTRIBUTADO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoIPINaoTributado nfnotainfoitemimpostoipinaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipinaotributadoResponse.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoIPINaoTributado(nfnotainfoitemimpostoipinaotributado);
		nfnotainfoitemimpostoipinaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoipinaotributadoResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoIPINaoTributadosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoIPINaoTributados();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoIPINaoTributado> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoIPINaoTributadosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOIMPORTACAO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoImportacao() {
		NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao = Objects.insertNFNotaInfoItemImpostoImportacao(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOIMPORTACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoimportacaoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoImportacao(nfnotainfoitemimpostoimportacao);
		nfnotainfoitemimpostoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoimportacao.getId(), nfnotainfoitemimpostoimportacaoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoImportacaoById(nfnotainfoitemimpostoimportacao);
		nfnotainfoitemimpostoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoimportacaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoImportacaos() {
		NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao = new NFNotaInfoItemImpostoImportacao();
		List<NFNotaInfoItemImpostoImportacao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoImportacaos(nfnotainfoitemimpostoimportacao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoImportacaos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoImportacaos();
		NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao = new NFNotaInfoItemImpostoImportacao();
		List<NFNotaInfoItemImpostoImportacao> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoImportacaos(new NFNotaInfoItemImpostoImportacao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoImportacao() {
		NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacao = Objects.insertNFNotaInfoItemImpostoImportacao(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOIMPORTACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoImportacao nfnotainfoitemimpostoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoimportacaoResponse.getValorBaseCalculo(), "valorBaseCalculo_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoImportacao(nfnotainfoitemimpostoimportacao);
		nfnotainfoitemimpostoimportacaoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoimportacaoResponse.getValorBaseCalculo(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoImportacaosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoImportacao> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoImportacaosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoImportacao> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoImportacaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoImportacao> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoImportacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOISSQN
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoISSQN() {
		NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn = Objects.insertNFNotaInfoItemImpostoISSQN(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOISSQN, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqnResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoISSQNById(request);
		Assert.assertEquals(nfnotainfoitemimpostoissqnResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoISSQN(nfnotainfoitemimpostoissqn);
		nfnotainfoitemimpostoissqnResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoISSQNById(request);
		Assert.assertEquals(nfnotainfoitemimpostoissqn.getId(), nfnotainfoitemimpostoissqnResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoISSQNById(nfnotainfoitemimpostoissqn);
		nfnotainfoitemimpostoissqnResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoISSQNById(request);
		Assert.assertEquals(nfnotainfoitemimpostoissqnResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoISSQNs() {
		NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn = new NFNotaInfoItemImpostoISSQN();
		List<NFNotaInfoItemImpostoISSQN> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoISSQNs(nfnotainfoitemimpostoissqn).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoISSQNs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoISSQNs();
		NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn = new NFNotaInfoItemImpostoISSQN();
		List<NFNotaInfoItemImpostoISSQN> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoISSQNs(new NFNotaInfoItemImpostoISSQN()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoISSQN() {
		NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqn = Objects.insertNFNotaInfoItemImpostoISSQN(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOISSQN, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoISSQN nfnotainfoitemimpostoissqnResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoISSQNById(request);
		Assert.assertEquals(nfnotainfoitemimpostoissqnResponse.getCodigoMunicipioIncidenciaImposto(), "codigoMunicipioIncidenciaImposto_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoISSQN(nfnotainfoitemimpostoissqn);
		nfnotainfoitemimpostoissqnResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoISSQNById(request);
		Assert.assertEquals(nfnotainfoitemimpostoissqnResponse.getCodigoMunicipioIncidenciaImposto(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoISSQNsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoISSQN> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoISSQNsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoISSQNsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoISSQN> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoISSQNsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoISSQNs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoISSQN> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoISSQNsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOPIS
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoPIS() {
		NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis = Objects.insertNFNotaInfoItemImpostoPIS(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOPIS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopisResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoPIS(nfnotainfoitemimpostopis);
		nfnotainfoitemimpostopisResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISById(request);
		Assert.assertEquals(nfnotainfoitemimpostopis.getId(), nfnotainfoitemimpostopisResponse.getId());
		nfnotainfoitemimpostopis = Objects.insertNFNotaInfoItemImpostoPIS(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOPIS, PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoPISById(nfnotainfoitemimpostopis);
		nfnotainfoitemimpostopisResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoPISs() {
		NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis = new NFNotaInfoItemImpostoPIS();
		List<NFNotaInfoItemImpostoPIS> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISs(nfnotainfoitemimpostopis).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoPISs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISs();
		NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis = new NFNotaInfoItemImpostoPIS();
		List<NFNotaInfoItemImpostoPIS> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISs(new NFNotaInfoItemImpostoPIS()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoPIS() {
		NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopis = Objects.insertNFNotaInfoItemImpostoPIS(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOPIS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoPIS nfnotainfoitemimpostopisResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisResponse.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoPIS(nfnotainfoitemimpostopis);
		nfnotainfoitemimpostopisResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoPISsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoPIS> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoPIS> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoPIS> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOPISALIQUOTA
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoPISAliquota() {
		NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota = Objects.insertNFNotaInfoItemImpostoPISAliquota(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOPISALIQUOTA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisaliquotaResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoPISAliquota(nfnotainfoitemimpostopisaliquota);
		nfnotainfoitemimpostopisaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisaliquota.getId(), nfnotainfoitemimpostopisaliquotaResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoPISAliquotaById(nfnotainfoitemimpostopisaliquota);
		nfnotainfoitemimpostopisaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisaliquotaResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoPISAliquotas() {
		NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota = new NFNotaInfoItemImpostoPISAliquota();
		List<NFNotaInfoItemImpostoPISAliquota> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISAliquotas(nfnotainfoitemimpostopisaliquota).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoPISAliquotas() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISAliquotas();
		NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota = new NFNotaInfoItemImpostoPISAliquota();
		List<NFNotaInfoItemImpostoPISAliquota> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISAliquotas(new NFNotaInfoItemImpostoPISAliquota()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoPISAliquota() {
		NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquota = Objects.insertNFNotaInfoItemImpostoPISAliquota(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOPISALIQUOTA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoPISAliquota nfnotainfoitemimpostopisaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisaliquotaResponse.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoPISAliquota(nfnotainfoitemimpostopisaliquota);
		nfnotainfoitemimpostopisaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisaliquotaResponse.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoPISAliquotasByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotasByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISAliquotasByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISAliquotas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoPISAliquota> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISAliquotasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOPISQUANTIDADE
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoPISQuantidade() {
		NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade = Objects.insertNFNotaInfoItemImpostoPISQuantidade(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOPISQUANTIDADE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisquantidadeResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoPISQuantidade(nfnotainfoitemimpostopisquantidade);
		nfnotainfoitemimpostopisquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisquantidade.getId(),
				nfnotainfoitemimpostopisquantidadeResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoPISQuantidadeById(nfnotainfoitemimpostopisquantidade);
		nfnotainfoitemimpostopisquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisquantidadeResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoPISQuantidades() {
		NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade = new NFNotaInfoItemImpostoPISQuantidade();
		List<NFNotaInfoItemImpostoPISQuantidade> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISQuantidades(nfnotainfoitemimpostopisquantidade).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoPISQuantidades() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISQuantidades();
		NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade = new NFNotaInfoItemImpostoPISQuantidade();
		List<NFNotaInfoItemImpostoPISQuantidade> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISQuantidades(new NFNotaInfoItemImpostoPISQuantidade()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoPISQuantidade() {
		NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidade = Objects.insertNFNotaInfoItemImpostoPISQuantidade(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOPISQUANTIDADE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoPISQuantidade nfnotainfoitemimpostopisquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisquantidadeResponse.getQuantidadeVendida(), "quantidadeVendida_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoPISQuantidade(nfnotainfoitemimpostopisquantidade);
		nfnotainfoitemimpostopisquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisquantidadeResponse.getQuantidadeVendida(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoPISQuantidadesByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISQuantidades();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoPISQuantidade> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISQuantidadesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoPISNaoTributado() {
		NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado = Objects.insertNFNotaInfoItemImpostoPISNaoTributado(
				1004, TabelaEnum.NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisnaotributadoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoPISNaoTributado(nfnotainfoitemimpostopisnaotributado);
		nfnotainfoitemimpostopisnaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisnaotributado.getId(),
				nfnotainfoitemimpostopisnaotributadoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoPISNaoTributadoById(nfnotainfoitemimpostopisnaotributado);
		nfnotainfoitemimpostopisnaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisnaotributadoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoPISNaoTributados() {
		NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado = new NFNotaInfoItemImpostoPISNaoTributado();
		List<NFNotaInfoItemImpostoPISNaoTributado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISNaoTributados(nfnotainfoitemimpostopisnaotributado).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoPISNaoTributados() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISNaoTributados();
		NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado = new NFNotaInfoItemImpostoPISNaoTributado();
		List<NFNotaInfoItemImpostoPISNaoTributado> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISNaoTributados(new NFNotaInfoItemImpostoPISNaoTributado())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoPISNaoTributado() {
		NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributado = Objects.insertNFNotaInfoItemImpostoPISNaoTributado(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOPISNAOTRIBUTADO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoPISNaoTributado nfnotainfoitemimpostopisnaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisnaotributadoResponse.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoPISNaoTributado(nfnotainfoitemimpostopisnaotributado);
		nfnotainfoitemimpostopisnaotributadoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadoById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisnaotributadoResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoPISNaoTributadosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISNaoTributados();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoPISNaoTributado> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISNaoTributadosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoPISOutrasOperacoes() {
		NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes = Objects.insertNFNotaInfoItemImpostoPISOutrasOperacoes(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisoutrasoperacoesResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoPISOutrasOperacoes(nfnotainfoitemimpostopisoutrasoperacoes);
		nfnotainfoitemimpostopisoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisoutrasoperacoes.getId(),
				nfnotainfoitemimpostopisoutrasoperacoesResponse.getId());
		getNFNotaInfoItemBAR()
				.deleteNFNotaInfoItemImpostoPISOutrasOperacoesById(nfnotainfoitemimpostopisoutrasoperacoes);
		nfnotainfoitemimpostopisoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisoutrasoperacoesResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoPISOutrasOperacoess() {
		NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes = new NFNotaInfoItemImpostoPISOutrasOperacoes();
		List<NFNotaInfoItemImpostoPISOutrasOperacoes> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISOutrasOperacoess(nfnotainfoitemimpostopisoutrasoperacoes)
				.getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoPISOutrasOperacoess() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISOutrasOperacoess();
		NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes = new NFNotaInfoItemImpostoPISOutrasOperacoes();
		List<NFNotaInfoItemImpostoPISOutrasOperacoes> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISOutrasOperacoess(new NFNotaInfoItemImpostoPISOutrasOperacoes())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoPISOutrasOperacoes() {
		NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoes = Objects.insertNFNotaInfoItemImpostoPISOutrasOperacoes(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOPISOUTRASOPERACOES, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoPISOutrasOperacoes nfnotainfoitemimpostopisoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisoutrasoperacoesResponse.getQuantidadeVendida(), "quantidadeVendida_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoPISOutrasOperacoes(nfnotainfoitemimpostopisoutrasoperacoes);
		nfnotainfoitemimpostopisoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisoutrasoperacoesResponse.getQuantidadeVendida(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISOutrasOperacoess();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoPISOutrasOperacoes> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISOutrasOperacoessByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOPISST
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoPISST() {
		NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst = Objects.insertNFNotaInfoItemImpostoPISST(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOPISST, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisstResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisstResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoPISST(nfnotainfoitemimpostopisst);
		nfnotainfoitemimpostopisstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisst.getId(), nfnotainfoitemimpostopisstResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoPISSTById(nfnotainfoitemimpostopisst);
		nfnotainfoitemimpostopisstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisstResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoPISSTs() {
		NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst = new NFNotaInfoItemImpostoPISST();
		List<NFNotaInfoItemImpostoPISST> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISSTs(nfnotainfoitemimpostopisst).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoPISSTs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISSTs();
		NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst = new NFNotaInfoItemImpostoPISST();
		List<NFNotaInfoItemImpostoPISST> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoPISSTs(new NFNotaInfoItemImpostoPISST()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoPISST() {
		NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisst = Objects.insertNFNotaInfoItemImpostoPISST(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOPISST, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoPISST nfnotainfoitemimpostopisstResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisstResponse.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoPISST(nfnotainfoitemimpostopisst);
		nfnotainfoitemimpostopisstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostopisstResponse.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoPISSTsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoPISST> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISSTsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoPISSTsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoPISST> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISSTsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoPISSTs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoPISST> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoPISSTsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOCOFINS
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoCOFINS() {
		NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins = Objects.insertNFNotaInfoItemImpostoCOFINS(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofinsResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoCOFINS(nfnotainfoitemimpostocofins);
		nfnotainfoitemimpostocofinsResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofins.getId(), nfnotainfoitemimpostocofinsResponse.getId());
		nfnotainfoitemimpostocofins = Objects.insertNFNotaInfoItemImpostoCOFINS(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINS, PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoCOFINSById(nfnotainfoitemimpostocofins);
		nfnotainfoitemimpostocofinsResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoCOFINSs() {
		NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins = new NFNotaInfoItemImpostoCOFINS();
		List<NFNotaInfoItemImpostoCOFINS> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSs(nfnotainfoitemimpostocofins).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoCOFINSs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSs();
		NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins = new NFNotaInfoItemImpostoCOFINS();
		List<NFNotaInfoItemImpostoCOFINS> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSs(new NFNotaInfoItemImpostoCOFINS()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoCOFINS() {
		NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofins = Objects.insertNFNotaInfoItemImpostoCOFINS(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoCOFINS nfnotainfoitemimpostocofinsResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsResponse.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoCOFINS(nfnotainfoitemimpostocofins);
		nfnotainfoitemimpostocofinsResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoCOFINSsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINS> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoCOFINSAliquota() {
		NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota = Objects.insertNFNotaInfoItemImpostoCOFINSAliquota(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsaliquotaResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoCOFINSAliquota(nfnotainfoitemimpostocofinsaliquota);
		nfnotainfoitemimpostocofinsaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsaliquota.getId(),
				nfnotainfoitemimpostocofinsaliquotaResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoCOFINSAliquotaById(nfnotainfoitemimpostocofinsaliquota);
		nfnotainfoitemimpostocofinsaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsaliquotaResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoCOFINSAliquotas() {
		NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota = new NFNotaInfoItemImpostoCOFINSAliquota();
		List<NFNotaInfoItemImpostoCOFINSAliquota> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSAliquotas(nfnotainfoitemimpostocofinsaliquota).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoCOFINSAliquotas() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSAliquotas();
		NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota = new NFNotaInfoItemImpostoCOFINSAliquota();
		List<NFNotaInfoItemImpostoCOFINSAliquota> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSAliquotas(new NFNotaInfoItemImpostoCOFINSAliquota())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoCOFINSAliquota() {
		NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquota = Objects.insertNFNotaInfoItemImpostoCOFINSAliquota(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSALIQUOTA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoCOFINSAliquota nfnotainfoitemimpostocofinsaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsaliquotaResponse.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoCOFINSAliquota(nfnotainfoitemimpostocofinsaliquota);
		nfnotainfoitemimpostocofinsaliquotaResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotaById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsaliquotaResponse.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSAliquotas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSAliquota> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSAliquotasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoCOFINSQuantidade() {
		NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade = Objects.insertNFNotaInfoItemImpostoCOFINSQuantidade(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsquantidadeResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoCOFINSQuantidade(nfnotainfoitemimpostocofinsquantidade);
		nfnotainfoitemimpostocofinsquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsquantidade.getId(),
				nfnotainfoitemimpostocofinsquantidadeResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoCOFINSQuantidadeById(nfnotainfoitemimpostocofinsquantidade);
		nfnotainfoitemimpostocofinsquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsquantidadeResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoCOFINSQuantidades() {
		NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade = new NFNotaInfoItemImpostoCOFINSQuantidade();
		List<NFNotaInfoItemImpostoCOFINSQuantidade> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSQuantidades(nfnotainfoitemimpostocofinsquantidade).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoCOFINSQuantidades() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSQuantidades();
		NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade = new NFNotaInfoItemImpostoCOFINSQuantidade();
		List<NFNotaInfoItemImpostoCOFINSQuantidade> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSQuantidades(new NFNotaInfoItemImpostoCOFINSQuantidade())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoCOFINSQuantidade() {
		NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidade = Objects.insertNFNotaInfoItemImpostoCOFINSQuantidade(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSQUANTIDADE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoCOFINSQuantidade nfnotainfoitemimpostocofinsquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsquantidadeResponse.getValorAliquota(), "valorAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoCOFINSQuantidade(nfnotainfoitemimpostocofinsquantidade);
		nfnotainfoitemimpostocofinsquantidadeResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadeById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsquantidadeResponse.getValorAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSQuantidades();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSQuantidade> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSQuantidadesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoCOFINSNaoTributavel() {
		NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel = Objects.insertNFNotaInfoItemImpostoCOFINSNaoTributavel(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsnaotributavelResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoCOFINSNaoTributavel(nfnotainfoitemimpostocofinsnaotributavel);
		nfnotainfoitemimpostocofinsnaotributavelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsnaotributavel.getId(),
				nfnotainfoitemimpostocofinsnaotributavelResponse.getId());
		getNFNotaInfoItemBAR()
				.deleteNFNotaInfoItemImpostoCOFINSNaoTributavelById(nfnotainfoitemimpostocofinsnaotributavel);
		nfnotainfoitemimpostocofinsnaotributavelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsnaotributavelResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoCOFINSNaoTributavels() {
		NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel = new NFNotaInfoItemImpostoCOFINSNaoTributavel();
		List<NFNotaInfoItemImpostoCOFINSNaoTributavel> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSNaoTributavels(nfnotainfoitemimpostocofinsnaotributavel)
				.getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoCOFINSNaoTributavels() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSNaoTributavels();
		NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel = new NFNotaInfoItemImpostoCOFINSNaoTributavel();
		List<NFNotaInfoItemImpostoCOFINSNaoTributavel> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSNaoTributavels(new NFNotaInfoItemImpostoCOFINSNaoTributavel())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoCOFINSNaoTributavel() {
		NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavel = Objects.insertNFNotaInfoItemImpostoCOFINSNaoTributavel(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSNAOTRIBUTAVEL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoCOFINSNaoTributavel nfnotainfoitemimpostocofinsnaotributavelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsnaotributavelResponse.getCreateUser(), "system");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoCOFINSNaoTributavel(nfnotainfoitemimpostocofinsnaotributavel);
		nfnotainfoitemimpostocofinsnaotributavelResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsnaotributavelResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSNaoTributavels();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSNaoTributavel> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSNaoTributavelsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================###
	// NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoCOFINSOutrasOperacoes() {
		NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes = Objects.insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsoutrasoperacoesResponse, null);
		getNFNotaInfoItemBAR()
				.insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(nfnotainfoitemimpostocofinsoutrasoperacoes);
		nfnotainfoitemimpostocofinsoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsoutrasoperacoes.getId(),
				nfnotainfoitemimpostocofinsoutrasoperacoesResponse.getId());
		getNFNotaInfoItemBAR()
				.deleteNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(nfnotainfoitemimpostocofinsoutrasoperacoes);
		nfnotainfoitemimpostocofinsoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsoutrasoperacoesResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess() {
		NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes = new NFNotaInfoItemImpostoCOFINSOutrasOperacoes();
		List<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess(nfnotainfoitemimpostocofinsoutrasoperacoes)
				.getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess();
		NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes = new NFNotaInfoItemImpostoCOFINSOutrasOperacoes();
		List<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess(new NFNotaInfoItemImpostoCOFINSOutrasOperacoes())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoCOFINSOutrasOperacoes() {
		NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoes = Objects.insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSOUTRASOPERACOES, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoCOFINSOutrasOperacoes nfnotainfoitemimpostocofinsoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsoutrasoperacoesResponse.getQuantidadeVendida(), "quantidadeVendida_0");
		getNFNotaInfoItemBAR()
				.updateNFNotaInfoItemImpostoCOFINSOutrasOperacoes(nfnotainfoitemimpostocofinsoutrasoperacoes);
		nfnotainfoitemimpostocofinsoutrasoperacoesResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoesById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsoutrasoperacoesResponse.getQuantidadeVendida(),
				"NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSOutrasOperacoess();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSOutrasOperacoes> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSOutrasOperacoessByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOCOFINSST
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoCOFINSST() {
		NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst = Objects.insertNFNotaInfoItemImpostoCOFINSST(4,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSST, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsstResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsstResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoCOFINSST(nfnotainfoitemimpostocofinsst);
		nfnotainfoitemimpostocofinsstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsst.getId(), nfnotainfoitemimpostocofinsstResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoCOFINSSTById(nfnotainfoitemimpostocofinsst);
		nfnotainfoitemimpostocofinsstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsstResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoCOFINSSTs() {
		NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst = new NFNotaInfoItemImpostoCOFINSST();
		List<NFNotaInfoItemImpostoCOFINSST> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSSTs(nfnotainfoitemimpostocofinsst).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoCOFINSSTs() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSSTs();
		NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst = new NFNotaInfoItemImpostoCOFINSST();
		List<NFNotaInfoItemImpostoCOFINSST> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoCOFINSSTs(new NFNotaInfoItemImpostoCOFINSST()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoCOFINSST() {
		NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsst = Objects.insertNFNotaInfoItemImpostoCOFINSST(1000,
				TabelaEnum.NFNOTAINFOITEMIMPOSTOCOFINSST, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoCOFINSST nfnotainfoitemimpostocofinsstResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsstResponse.getPercentualAliquota(), "percentualAliquota_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoCOFINSST(nfnotainfoitemimpostocofinsst);
		nfnotainfoitemimpostocofinsstResponse = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSSTById(request);
		Assert.assertEquals(nfnotainfoitemimpostocofinsstResponse.getPercentualAliquota(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoCOFINSSTsByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoCOFINSSTs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoCOFINSST> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoCOFINSSTsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFNOTAINFOITEMIMPOSTOICMSUFDESTINO
	// ####======================================

	@Test
	public void testDeleteNFNotaInfoItemImpostoICMSUFDestino() {
		NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino = Objects.insertNFNotaInfoItemImpostoICMSUFDestino(
				4, TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSUFDESTINO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestinoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsufdestinoResponse, null);
		getNFNotaInfoItemBAR().insertNFNotaInfoItemImpostoICMSUFDestino(nfnotainfoitemimpostoicmsufdestino);
		nfnotainfoitemimpostoicmsufdestinoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsufdestino.getId(),
				nfnotainfoitemimpostoicmsufdestinoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFNotaInfoItemImpostoICMSUFDestinoById(nfnotainfoitemimpostoicmsufdestino);
		nfnotainfoitemimpostoicmsufdestinoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsufdestinoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoItemImpostoICMSUFDestinos() {
		NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino = new NFNotaInfoItemImpostoICMSUFDestino();
		List<NFNotaInfoItemImpostoICMSUFDestino> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSUFDestinos(nfnotainfoitemimpostoicmsufdestino).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoItemImpostoICMSUFDestinos() {
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSUFDestinos();
		NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino = new NFNotaInfoItemImpostoICMSUFDestino();
		List<NFNotaInfoItemImpostoICMSUFDestino> response = getNFNotaInfoItemBAR()
				.fetchAllNFNotaInfoItemImpostoICMSUFDestinos(new NFNotaInfoItemImpostoICMSUFDestino()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoItemImpostoICMSUFDestino() {
		NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestino = Objects.insertNFNotaInfoItemImpostoICMSUFDestino(
				1000, TabelaEnum.NFNOTAINFOITEMIMPOSTOICMSUFDESTINO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNotaInfoItemImpostoICMSUFDestino nfnotainfoitemimpostoicmsufdestinoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsufdestinoResponse.getPercentualInterestadual(), "percentualInterestadual_0");
		getNFNotaInfoItemBAR().updateNFNotaInfoItemImpostoICMSUFDestino(nfnotainfoitemimpostoicmsufdestino);
		nfnotainfoitemimpostoicmsufdestinoResponse = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinoById(request);
		Assert.assertEquals(nfnotainfoitemimpostoicmsufdestinoResponse.getPercentualInterestadual(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoItemImpostoICMSUFDestinosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> response = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> response2 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFNotaInfoItemImpostoICMSUFDestinos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoItemImpostoICMSUFDestino> response3 = getNFNotaInfoItemBAR()
				.fetchNFNotaInfoItemImpostoICMSUFDestinosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFIMPOSTODEVOLVIDO
	// ####======================================

	@Test
	public void testDeleteNFImpostoDevolvido() {
		NFImpostoDevolvido nfimpostodevolvido = Objects.insertNFImpostoDevolvido(4000, TabelaEnum.NFIMPOSTODEVOLVIDO,
				PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4000);
		NFImpostoDevolvido nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse, null);
		getNFNotaInfoItemBAR().insertNFImpostoDevolvido(nfimpostodevolvido);
		nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvido.getId(), nfimpostodevolvidoResponse.getId());
		nfimpostodevolvido = Objects.insertNFImpostoDevolvido(4000, TabelaEnum.NFIMPOSTODEVOLVIDO,
				PersistenceActionEnum.DELETE);
		getNFNotaInfoItemBAR().deleteNFImpostoDevolvidoById(nfimpostodevolvido);
		nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse, null);
	}

	@Test
	public void testFetchAllNFImpostoDevolvidos() {
		NFImpostoDevolvido nfimpostodevolvido = new NFImpostoDevolvido();
		List<NFImpostoDevolvido> response = getNFNotaInfoItemBAR().fetchAllNFImpostoDevolvidos(nfimpostodevolvido)
				.getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFImpostoDevolvidos() {
		getNFNotaInfoItemBAR().deleteAllNFImpostoDevolvidos();
		NFImpostoDevolvido nfimpostodevolvido = new NFImpostoDevolvido();
		List<NFImpostoDevolvido> response = getNFNotaInfoItemBAR().fetchAllNFImpostoDevolvidos(new NFImpostoDevolvido())
				.getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFImpostoDevolvido() {
		NFImpostoDevolvido nfimpostodevolvido = Objects.insertNFImpostoDevolvido(1000, TabelaEnum.NFIMPOSTODEVOLVIDO,
				PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFImpostoDevolvido nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse.getPercentualDevolucao(), "percentualDevolucao_0");
		getNFNotaInfoItemBAR().updateNFImpostoDevolvido(nfimpostodevolvido);
		nfimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidoById(request);
		Assert.assertEquals(nfimpostodevolvidoResponse.getPercentualDevolucao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFImpostoDevolvidosByRequest() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchNFImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFImpostoDevolvido> response2 = getNFNotaInfoItemBAR()
				.fetchNFImpostoDevolvidosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFImpostoDevolvidos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFImpostoDevolvido> response3 = getNFNotaInfoItemBAR()
				.fetchNFImpostoDevolvidosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	// ===================================### NFINFORMACAOIMPOSTODEVOLVIDO
	// ####======================================

	@Test
	public void testDeleteNFInformacaoImpostoDevolvido1() {
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = Objects.insertNFInformacaoImpostoDevolvido(4,
				TabelaEnum.NFINFORMACAOIMPOSTODEVOLVIDO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse, null);
		getNFNotaInfoItemBAR().insertNFInformacaoImpostoDevolvido(nfinformacaoimpostodevolvido);
		nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvido.getId(), nfinformacaoimpostodevolvidoResponse.getId());
		getNFNotaInfoItemBAR().deleteNFInformacaoImpostoDevolvidoById(nfinformacaoimpostodevolvido);
		nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse, null);
	}

	@Test
	public void testFetchAllNFInformacaoImpostoDevolvidos1() {
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = new NFInformacaoImpostoDevolvido();
		List<NFInformacaoImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchAllNFInformacaoImpostoDevolvidos(nfinformacaoimpostodevolvido).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFInformacaoImpostoDevolvidos1() {
		getNFNotaInfoItemBAR().deleteAllNFInformacaoImpostoDevolvidos();
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = new NFInformacaoImpostoDevolvido();
		List<NFInformacaoImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchAllNFInformacaoImpostoDevolvidos(new NFInformacaoImpostoDevolvido()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFInformacaoImpostoDevolvido1() {
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvido = Objects.insertNFInformacaoImpostoDevolvido(1000,
				TabelaEnum.NFINFORMACAOIMPOSTODEVOLVIDO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFInformacaoImpostoDevolvido nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse.getValorIPIDevolvido(), "valorIPIDevolvido_0");
		getNFNotaInfoItemBAR().updateNFInformacaoImpostoDevolvido(nfinformacaoimpostodevolvido);
		nfinformacaoimpostodevolvidoResponse = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidoById(request);
		Assert.assertEquals(nfinformacaoimpostodevolvidoResponse.getValorIPIDevolvido(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFInformacaoImpostoDevolvidosByRequest1() throws Exception {
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFNotaInfoItemBAR().fetchNFInformacaoImpostoDevolvidosByRequest(request);
		// Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response2 = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFNotaInfoItemBAR().deleteAllNFInformacaoImpostoDevolvidos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFInformacaoImpostoDevolvido> response3 = getNFNotaInfoItemBAR()
				.fetchNFInformacaoImpostoDevolvidosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup() {
		executeSqlScript("conf/insertNFNotaInfoItem.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProduto.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProdutoDeclaracaoImportacao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProdutoDeclaracaoImportacaoAdicao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemDetalheExportacao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemExportacaoIndireta.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProdutoVeiculo.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProdutoMedicamento.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProdutoArmamento.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProdutoCombustivel.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemProdutoCombustivelCIDE.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImposto.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS00.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS10.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS20.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS30.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS40.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS51.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS60.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS70.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMS90.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSPartilhado.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSST.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSSN101.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSSN102.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSSN201.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSSN202.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSSN500.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSSN900.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoIPI.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoIPITributado.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoIPINaoTributado.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoImportacao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoISSQN.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoPIS.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoPISAliquota.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoPISQuantidade.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoPISNaoTributado.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoPISOutrasOperacoes.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoPISST.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoCOFINS.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoCOFINSAliquota.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoCOFINSQuantidade.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoCOFINSNaoTributavel.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoCOFINSOutrasOperacoes.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoCOFINSST.sql", false);
		executeSqlScript("conf/insertNFNotaInfoItemImpostoICMSUFDestino.sql", false);
		executeSqlScript("conf/insertNFImpostoDevolvido.sql", false);
		executeSqlScript("conf/insertNFInformacaoImpostoDevolvido.sql", false);
	}

}
