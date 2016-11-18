/** create by system gera-java version 1.0.0 29/09/2016 11:35 : 14*/
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
import com.qat.samples.sysmgmt.bar.Nfe.INFeBAR;
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
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class NFeBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(NFeBARTest.class);
private INFeBAR nfeBAR; // injected by Spring through @Resource

@Resource
public void setNFeBAR(INFeBAR nfeBAR)
{
	this.nfeBAR = nfeBAR;
}

public INFeBAR getNFeBAR()
{
	return nfeBAR;
}


//===================================### NFNOTA ####======================================


@Test
	public void testDeleteNFNota()
	{
		NFNota nfnota = Objects.insertNFNota(40002, TabelaEnum.NFNOTA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(40002);
		NFNota nfnotaResponse = getNFeBAR().fetchNFNotaById(request);
		Assert.assertEquals(nfnotaResponse, null);
		getNFeBAR().insertNFNota(nfnota);
		nfnotaResponse = getNFeBAR().fetchNFNotaById(request);
		Assert.assertEquals(nfnota.getId(), nfnotaResponse.getId());
		nfnota = Objects.insertNFNota(4002, TabelaEnum.NFNOTA, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaById(nfnota);
		nfnotaResponse = getNFeBAR().fetchNFNotaById(request);
	//	Assert.assertEquals(nfnotaResponse, null);
	}

	@Test
	public void testFetchAllNFNotas()
	{
	NFNota nfnota = new NFNota();
		List<NFNota> response = getNFeBAR().fetchAllNFNotas(nfnota).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotas()
	{
		getNFeBAR().deleteAllNFNotas();
	NFNota nfnota = new NFNota();
		List<NFNota> response = getNFeBAR().fetchAllNFNotas(new NFNota()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNota()
	{
		NFNota nfnota = Objects.insertNFNota(1001, TabelaEnum.NFNOTA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNota nfnotaResponse = getNFeBAR().fetchNFNotaById(request);
		Assert.assertEquals(nfnotaResponse.getId().toString(), "1001");
		getNFeBAR().updateNFNota(nfnota);
		nfnotaResponse = getNFeBAR().fetchNFNotaById(request);
		Assert.assertEquals(nfnotaResponse.getId().toString(), "1001");
	}

	@Test
	public void testFetchNFNotasByRequest() throws Exception
	{
		// check for valid and precount
		NFNotaInquiryRequest request = new NFNotaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNota> response = getNFeBAR().fetchNFNotasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		NFNotaInquiryRequest request2 = new NFNotaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNota> response2 = getNFeBAR().fetchNFNotasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotas();
		NFNotaInquiryRequest request3 = new NFNotaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNota> response3 = getNFeBAR().fetchNFNotasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFO ####======================================


@Test
	public void testDeleteNFNotaInfo()
	{
		NFNotaInfo nfnotainfo = Objects.insertNFNotaInfo(4000011, TabelaEnum.NFNOTAINFO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4000011);
		NFNotaInfo nfnotainfoResponse = getNFeBAR().fetchNFNotaInfoById(request);
		Assert.assertEquals(nfnotainfoResponse, null);
		getNFeBAR().insertNFNotaInfo(nfnotainfo);
		nfnotainfoResponse = getNFeBAR().fetchNFNotaInfoById(request);
		Assert.assertEquals(nfnotainfo.getId(), nfnotainfoResponse.getId());
		nfnotainfo = Objects.insertNFNotaInfo(4000011, TabelaEnum.NFNOTAINFO, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaInfoById(nfnotainfo);
		nfnotainfoResponse = getNFeBAR().fetchNFNotaInfoById(request);
		Assert.assertEquals(nfnotainfoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfos()
	{
	NFNotaInfo nfnotainfo = new NFNotaInfo();
		List<NFNotaInfo> response = getNFeBAR().fetchAllNFNotaInfos(nfnotainfo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfos()
	{
		getNFeBAR().deleteAllNFNotaInfos();
	NFNotaInfo nfnotainfo = new NFNotaInfo();
		List<NFNotaInfo> response = getNFeBAR().fetchAllNFNotaInfos(new NFNotaInfo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfo()
	{
		NFNotaInfo nfnotainfo = Objects.insertNFNotaInfo(1001, TabelaEnum.NFNOTAINFO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfo nfnotainfoResponse = getNFeBAR().fetchNFNotaInfoById(request);
		Assert.assertEquals(nfnotainfoResponse.getIdentificador(), "identificador_1");
		getNFeBAR().updateNFNotaInfo(nfnotainfo);
		nfnotainfoResponse = getNFeBAR().fetchNFNotaInfoById(request);
		Assert.assertEquals(nfnotainfoResponse.getIdentificador(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfo> response = getNFeBAR().fetchNFNotaInfosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfo> response2 = getNFeBAR().fetchNFNotaInfosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfo> response3 = getNFeBAR().fetchNFNotaInfosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOIDENTIFICACAO ####======================================


@Test
	public void testDeleteNFNotaInfoIdentificacao()
	{
		NFNotaInfoIdentificacao nfnotainfoidentificacao = Objects.insertNFNotaInfoIdentificacao(4, TabelaEnum.NFNOTAINFOIDENTIFICACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoIdentificacao nfnotainfoidentificacaoResponse = getNFeBAR().fetchNFNotaInfoIdentificacaoById(request);
		Assert.assertEquals(nfnotainfoidentificacaoResponse, null);
		getNFeBAR().insertNFNotaInfoIdentificacao(nfnotainfoidentificacao);
		nfnotainfoidentificacaoResponse = getNFeBAR().fetchNFNotaInfoIdentificacaoById(request);
		Assert.assertEquals(nfnotainfoidentificacao.getId(), nfnotainfoidentificacaoResponse.getId());
		nfnotainfoidentificacao = Objects.insertNFNotaInfoIdentificacao(4, TabelaEnum.NFNOTAINFOIDENTIFICACAO, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaInfoIdentificacaoById(nfnotainfoidentificacao);

		nfnotainfoidentificacaoResponse = getNFeBAR().fetchNFNotaInfoIdentificacaoById(request);
		Assert.assertEquals(nfnotainfoidentificacaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoIdentificacaos()
	{
	NFNotaInfoIdentificacao nfnotainfoidentificacao = new NFNotaInfoIdentificacao();
		List<NFNotaInfoIdentificacao> response = getNFeBAR().fetchAllNFNotaInfoIdentificacaos(nfnotainfoidentificacao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoIdentificacaos()
	{
		getNFeBAR().deleteAllNFNotaInfoIdentificacaos();
	NFNotaInfoIdentificacao nfnotainfoidentificacao = new NFNotaInfoIdentificacao();
		List<NFNotaInfoIdentificacao> response = getNFeBAR().fetchAllNFNotaInfoIdentificacaos(new NFNotaInfoIdentificacao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoIdentificacao()
	{
		NFNotaInfoIdentificacao nfnotainfoidentificacao = Objects.insertNFNotaInfoIdentificacao(1001, TabelaEnum.NFNOTAINFOIDENTIFICACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoIdentificacao nfnotainfoidentificacaoResponse = getNFeBAR().fetchNFNotaInfoIdentificacaoById(request);
		Assert.assertEquals(nfnotainfoidentificacaoResponse.getCodigoMunicipio(), "codigoMunicipio_1");
		getNFeBAR().updateNFNotaInfoIdentificacao(nfnotainfoidentificacao);
		nfnotainfoidentificacaoResponse = getNFeBAR().fetchNFNotaInfoIdentificacaoById(request);
		Assert.assertEquals(nfnotainfoidentificacaoResponse.getCodigoMunicipio(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoIdentificacaosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoIdentificacao> response = getNFeBAR().fetchNFNotaInfoIdentificacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoIdentificacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoIdentificacao> response2 = getNFeBAR().fetchNFNotaInfoIdentificacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoIdentificacaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoIdentificacao> response3 = getNFeBAR().fetchNFNotaInfoIdentificacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFINFOMODELO1POR1AREFERENCIADA ####======================================


@Test
	public void testDeleteNFInfoModelo1Por1AReferenciada()
	{
		NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada = Objects.insertNFInfoModelo1Por1AReferenciada(4, TabelaEnum.NFINFOMODELO1POR1AREFERENCIADA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciadaResponse = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadaById(request);
		Assert.assertEquals(nfinfomodelo1por1areferenciadaResponse, null);
		getNFeBAR().insertNFInfoModelo1Por1AReferenciada(nfinfomodelo1por1areferenciada);
		nfinfomodelo1por1areferenciadaResponse = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadaById(request);
		Assert.assertEquals(nfinfomodelo1por1areferenciada.getId(), nfinfomodelo1por1areferenciadaResponse.getId());
		getNFeBAR().deleteNFInfoModelo1Por1AReferenciadaById(nfinfomodelo1por1areferenciada);
		nfinfomodelo1por1areferenciadaResponse = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadaById(request);
		Assert.assertEquals(nfinfomodelo1por1areferenciadaResponse, null);
	}

	@Test
	public void testFetchAllNFInfoModelo1Por1AReferenciadas()
	{
	NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada = new NFInfoModelo1Por1AReferenciada();
		List<NFInfoModelo1Por1AReferenciada> response = getNFeBAR().fetchAllNFInfoModelo1Por1AReferenciadas(nfinfomodelo1por1areferenciada).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFInfoModelo1Por1AReferenciadas()
	{
		getNFeBAR().deleteAllNFInfoModelo1Por1AReferenciadas();
	NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada = new NFInfoModelo1Por1AReferenciada();
		List<NFInfoModelo1Por1AReferenciada> response = getNFeBAR().fetchAllNFInfoModelo1Por1AReferenciadas(new NFInfoModelo1Por1AReferenciada()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFInfoModelo1Por1AReferenciada()
	{
		NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciada = Objects.insertNFInfoModelo1Por1AReferenciada(1001, TabelaEnum.NFINFOMODELO1POR1AREFERENCIADA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFInfoModelo1Por1AReferenciada nfinfomodelo1por1areferenciadaResponse = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadaById(request);
		Assert.assertEquals(nfinfomodelo1por1areferenciadaResponse.getAnomesemissaonfe(), "anoMesEmissaoNFe_1");
		getNFeBAR().updateNFInfoModelo1Por1AReferenciada(nfinfomodelo1por1areferenciada);
		nfinfomodelo1por1areferenciadaResponse = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadaById(request);
		Assert.assertEquals(nfinfomodelo1por1areferenciadaResponse.getAnomesemissaonfe(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFInfoModelo1Por1AReferenciadasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFInfoModelo1Por1AReferenciada> response = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFInfoModelo1Por1AReferenciada> response2 = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFInfoModelo1Por1AReferenciadas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFInfoModelo1Por1AReferenciada> response3 = getNFeBAR().fetchNFInfoModelo1Por1AReferenciadasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFINFOREFERENCIADA ####======================================


@Test
	public void testDeleteNFInfoReferenciada()
	{
		NFInfoReferenciada nfinforeferenciada = Objects.insertNFInfoReferenciada(4, TabelaEnum.NFINFOREFERENCIADA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFInfoReferenciada nfinforeferenciadaResponse = getNFeBAR().fetchNFInfoReferenciadaById(request);
		Assert.assertEquals(nfinforeferenciadaResponse, null);
		getNFeBAR().insertNFInfoReferenciada(nfinforeferenciada);
		nfinforeferenciadaResponse = getNFeBAR().fetchNFInfoReferenciadaById(request);
		Assert.assertEquals(nfinforeferenciada.getId(), nfinforeferenciadaResponse.getId());
		nfinforeferenciada = Objects.insertNFInfoReferenciada(4, TabelaEnum.NFINFOREFERENCIADA, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFInfoReferenciadaById(nfinforeferenciada);

		nfinforeferenciadaResponse = getNFeBAR().fetchNFInfoReferenciadaById(request);
		Assert.assertEquals(nfinforeferenciadaResponse, null);
	}

	@Test
	public void testFetchAllNFInfoReferenciadas()
	{
	NFInfoReferenciada nfinforeferenciada = new NFInfoReferenciada();
		List<NFInfoReferenciada> response = getNFeBAR().fetchAllNFInfoReferenciadas(nfinforeferenciada).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFInfoReferenciadas()
	{
		getNFeBAR().deleteAllNFInfoReferenciadas();
	NFInfoReferenciada nfinforeferenciada = new NFInfoReferenciada();
		List<NFInfoReferenciada> response = getNFeBAR().fetchAllNFInfoReferenciadas(new NFInfoReferenciada()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFInfoReferenciada()
	{
		NFInfoReferenciada nfinforeferenciada = Objects.insertNFInfoReferenciada(1001, TabelaEnum.NFINFOREFERENCIADA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFInfoReferenciada nfinforeferenciadaResponse = getNFeBAR().fetchNFInfoReferenciadaById(request);
		Assert.assertEquals(nfinforeferenciadaResponse.getChaveAcesso(), "chaveAcesso_1");
		getNFeBAR().updateNFInfoReferenciada(nfinforeferenciada);
		nfinforeferenciadaResponse = getNFeBAR().fetchNFInfoReferenciadaById(request);
		Assert.assertEquals(nfinforeferenciadaResponse.getChaveAcesso(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFInfoReferenciadasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFInfoReferenciada> response = getNFeBAR().fetchNFInfoReferenciadasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFInfoReferenciadasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFInfoReferenciada> response2 = getNFeBAR().fetchNFInfoReferenciadasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFInfoReferenciadas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFInfoReferenciada> response3 = getNFeBAR().fetchNFInfoReferenciadasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFINFOPRODUTORRURALREFERENCIADA ####======================================


@Test
	public void testDeleteNFInfoProdutorRuralReferenciada()
	{
		NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada = Objects.insertNFInfoProdutorRuralReferenciada(4, TabelaEnum.NFINFOPRODUTORRURALREFERENCIADA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciadaResponse = getNFeBAR().fetchNFInfoProdutorRuralReferenciadaById(request);
		Assert.assertEquals(nfinfoprodutorruralreferenciadaResponse, null);
		getNFeBAR().insertNFInfoProdutorRuralReferenciada(nfinfoprodutorruralreferenciada);
		nfinfoprodutorruralreferenciadaResponse = getNFeBAR().fetchNFInfoProdutorRuralReferenciadaById(request);
		Assert.assertEquals(nfinfoprodutorruralreferenciada.getId(), nfinfoprodutorruralreferenciadaResponse.getId());
		getNFeBAR().deleteNFInfoProdutorRuralReferenciadaById(nfinfoprodutorruralreferenciada);
		nfinfoprodutorruralreferenciadaResponse = getNFeBAR().fetchNFInfoProdutorRuralReferenciadaById(request);
		Assert.assertEquals(nfinfoprodutorruralreferenciadaResponse, null);
	}

	@Test
	public void testFetchAllNFInfoProdutorRuralReferenciadas()
	{
	NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada = new NFInfoProdutorRuralReferenciada();
		List<NFInfoProdutorRuralReferenciada> response = getNFeBAR().fetchAllNFInfoProdutorRuralReferenciadas(nfinfoprodutorruralreferenciada).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFInfoProdutorRuralReferenciadas()
	{
		getNFeBAR().deleteAllNFInfoProdutorRuralReferenciadas();
	NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada = new NFInfoProdutorRuralReferenciada();
		List<NFInfoProdutorRuralReferenciada> response = getNFeBAR().fetchAllNFInfoProdutorRuralReferenciadas(new NFInfoProdutorRuralReferenciada()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFInfoProdutorRuralReferenciada()
	{
		NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciada = Objects.insertNFInfoProdutorRuralReferenciada(1001, TabelaEnum.NFINFOPRODUTORRURALREFERENCIADA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFInfoProdutorRuralReferenciada nfinfoprodutorruralreferenciadaResponse = getNFeBAR().fetchNFInfoProdutorRuralReferenciadaById(request);
		Assert.assertEquals(nfinfoprodutorruralreferenciadaResponse.getAnoMesEmissao(), "anoMesEmissao_1");
		getNFeBAR().updateNFInfoProdutorRuralReferenciada(nfinfoprodutorruralreferenciada);
		nfinfoprodutorruralreferenciadaResponse = getNFeBAR().fetchNFInfoProdutorRuralReferenciadaById(request);
		Assert.assertEquals(nfinfoprodutorruralreferenciadaResponse.getAnoMesEmissao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFInfoProdutorRuralReferenciadasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFInfoProdutorRuralReferenciada> response = getNFeBAR().fetchNFInfoProdutorRuralReferenciadasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFInfoProdutorRuralReferenciadasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFInfoProdutorRuralReferenciada> response2 = getNFeBAR().fetchNFInfoProdutorRuralReferenciadasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFInfoProdutorRuralReferenciadas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFInfoProdutorRuralReferenciada> response3 = getNFeBAR().fetchNFInfoProdutorRuralReferenciadasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOEMITENTE ####======================================


@Test
	public void testDeleteNFNotaInfoEmitente()
	{
		NFNotaInfoEmitente nfnotainfoemitente = Objects.insertNFNotaInfoEmitente(4, TabelaEnum.NFNOTAINFOEMITENTE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoEmitente nfnotainfoemitenteResponse = getNFeBAR().fetchNFNotaInfoEmitenteById(request);
		Assert.assertEquals(nfnotainfoemitenteResponse, null);
		getNFeBAR().insertNFNotaInfoEmitente(nfnotainfoemitente);
		nfnotainfoemitenteResponse = getNFeBAR().fetchNFNotaInfoEmitenteById(request);
		Assert.assertEquals(nfnotainfoemitente.getId(), nfnotainfoemitenteResponse.getId());
		getNFeBAR().deleteNFNotaInfoEmitenteById(nfnotainfoemitente);
		nfnotainfoemitenteResponse = getNFeBAR().fetchNFNotaInfoEmitenteById(request);
		Assert.assertEquals(nfnotainfoemitenteResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoEmitentes()
	{
	NFNotaInfoEmitente nfnotainfoemitente = new NFNotaInfoEmitente();
		List<NFNotaInfoEmitente> response = getNFeBAR().fetchAllNFNotaInfoEmitentes(nfnotainfoemitente).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoEmitentes()
	{
		getNFeBAR().deleteAllNFNotaInfoEmitentes();
	NFNotaInfoEmitente nfnotainfoemitente = new NFNotaInfoEmitente();
		List<NFNotaInfoEmitente> response = getNFeBAR().fetchAllNFNotaInfoEmitentes(new NFNotaInfoEmitente()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoEmitente()
	{
		NFNotaInfoEmitente nfnotainfoemitente = Objects.insertNFNotaInfoEmitente(1001, TabelaEnum.NFNOTAINFOEMITENTE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoEmitente nfnotainfoemitenteResponse = getNFeBAR().fetchNFNotaInfoEmitenteById(request);
		Assert.assertEquals(nfnotainfoemitenteResponse.getClassificacaonacionalatividadeseconomicas(), "classificacaoNacionalAtividadesEconomicas_1");
		getNFeBAR().updateNFNotaInfoEmitente(nfnotainfoemitente);
		nfnotainfoemitenteResponse = getNFeBAR().fetchNFNotaInfoEmitenteById(request);
		Assert.assertEquals(nfnotainfoemitenteResponse.getClassificacaonacionalatividadeseconomicas(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoEmitentesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoEmitente> response = getNFeBAR().fetchNFNotaInfoEmitentesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoEmitentesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoEmitente> response2 = getNFeBAR().fetchNFNotaInfoEmitentesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoEmitentes();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoEmitente> response3 = getNFeBAR().fetchNFNotaInfoEmitentesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOAVULSA ####======================================


@Test
	public void testDeleteNFNotaInfoAvulsa()
	{
		NFNotaInfoAvulsa nfnotainfoavulsa = Objects.insertNFNotaInfoAvulsa(4, TabelaEnum.NFNOTAINFOAVULSA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoAvulsa nfnotainfoavulsaResponse = getNFeBAR().fetchNFNotaInfoAvulsaById(request);
		Assert.assertEquals(nfnotainfoavulsaResponse, null);
		getNFeBAR().insertNFNotaInfoAvulsa(nfnotainfoavulsa);
		nfnotainfoavulsaResponse = getNFeBAR().fetchNFNotaInfoAvulsaById(request);
		Assert.assertEquals(nfnotainfoavulsa.getId(), nfnotainfoavulsaResponse.getId());
		getNFeBAR().deleteNFNotaInfoAvulsaById(nfnotainfoavulsa);
		nfnotainfoavulsaResponse = getNFeBAR().fetchNFNotaInfoAvulsaById(request);
		Assert.assertEquals(nfnotainfoavulsaResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoAvulsas()
	{
	NFNotaInfoAvulsa nfnotainfoavulsa = new NFNotaInfoAvulsa();
		List<NFNotaInfoAvulsa> response = getNFeBAR().fetchAllNFNotaInfoAvulsas(nfnotainfoavulsa).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoAvulsas()
	{
		getNFeBAR().deleteAllNFNotaInfoAvulsas();
	NFNotaInfoAvulsa nfnotainfoavulsa = new NFNotaInfoAvulsa();
		List<NFNotaInfoAvulsa> response = getNFeBAR().fetchAllNFNotaInfoAvulsas(new NFNotaInfoAvulsa()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoAvulsa()
	{
		NFNotaInfoAvulsa nfnotainfoavulsa = Objects.insertNFNotaInfoAvulsa(1001, TabelaEnum.NFNOTAINFOAVULSA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoAvulsa nfnotainfoavulsaResponse = getNFeBAR().fetchNFNotaInfoAvulsaById(request);
		Assert.assertEquals(nfnotainfoavulsaResponse.getFone(), "fone_1");
		getNFeBAR().updateNFNotaInfoAvulsa(nfnotainfoavulsa);
		nfnotainfoavulsaResponse = getNFeBAR().fetchNFNotaInfoAvulsaById(request);
		Assert.assertEquals(nfnotainfoavulsaResponse.getFone(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoAvulsasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoAvulsa> response = getNFeBAR().fetchNFNotaInfoAvulsasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoAvulsasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoAvulsa> response2 = getNFeBAR().fetchNFNotaInfoAvulsasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoAvulsas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoAvulsa> response3 = getNFeBAR().fetchNFNotaInfoAvulsasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFODESTINATARIO ####======================================


@Test
	public void testDeleteNFNotaInfoDestinatario()
	{
		NFNotaInfoDestinatario nfnotainfodestinatario = Objects.insertNFNotaInfoDestinatario(4, TabelaEnum.NFNOTAINFODESTINATARIO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoDestinatario nfnotainfodestinatarioResponse = getNFeBAR().fetchNFNotaInfoDestinatarioById(request);
		Assert.assertEquals(nfnotainfodestinatarioResponse, null);
		getNFeBAR().insertNFNotaInfoDestinatario(nfnotainfodestinatario);
		nfnotainfodestinatarioResponse = getNFeBAR().fetchNFNotaInfoDestinatarioById(request);
		Assert.assertEquals(nfnotainfodestinatario.getId(), nfnotainfodestinatarioResponse.getId());
		getNFeBAR().deleteNFNotaInfoDestinatarioById(nfnotainfodestinatario);
		nfnotainfodestinatarioResponse = getNFeBAR().fetchNFNotaInfoDestinatarioById(request);
		Assert.assertEquals(nfnotainfodestinatarioResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoDestinatarios()
	{
	NFNotaInfoDestinatario nfnotainfodestinatario = new NFNotaInfoDestinatario();
		List<NFNotaInfoDestinatario> response = getNFeBAR().fetchAllNFNotaInfoDestinatarios(nfnotainfodestinatario).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoDestinatarios()
	{
		getNFeBAR().deleteAllNFNotaInfoDestinatarios();
	NFNotaInfoDestinatario nfnotainfodestinatario = new NFNotaInfoDestinatario();
		List<NFNotaInfoDestinatario> response = getNFeBAR().fetchAllNFNotaInfoDestinatarios(new NFNotaInfoDestinatario()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoDestinatario()
	{
		NFNotaInfoDestinatario nfnotainfodestinatario = Objects.insertNFNotaInfoDestinatario(1001, TabelaEnum.NFNOTAINFODESTINATARIO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoDestinatario nfnotainfodestinatarioResponse = getNFeBAR().fetchNFNotaInfoDestinatarioById(request);
		Assert.assertEquals(nfnotainfodestinatarioResponse.getCnpj(), "cnpj_1");
		getNFeBAR().updateNFNotaInfoDestinatario(nfnotainfodestinatario);
		nfnotainfodestinatarioResponse = getNFeBAR().fetchNFNotaInfoDestinatarioById(request);
		Assert.assertEquals(nfnotainfodestinatarioResponse.getCnpj(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoDestinatariosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoDestinatario> response = getNFeBAR().fetchNFNotaInfoDestinatariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoDestinatariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoDestinatario> response2 = getNFeBAR().fetchNFNotaInfoDestinatariosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoDestinatarios();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoDestinatario> response3 = getNFeBAR().fetchNFNotaInfoDestinatariosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOLOCAL ####======================================


@Test
	public void testDeleteNFNotaInfoLocal()
	{
		NFNotaInfoLocal nfnotainfolocal = Objects.insertNFNotaInfoLocal(4, TabelaEnum.NFNOTAINFOLOCAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoLocal nfnotainfolocalResponse = getNFeBAR().fetchNFNotaInfoLocalById(request);
		Assert.assertEquals(nfnotainfolocalResponse, null);
		getNFeBAR().insertNFNotaInfoLocal(nfnotainfolocal);
		nfnotainfolocalResponse = getNFeBAR().fetchNFNotaInfoLocalById(request);
		Assert.assertEquals(nfnotainfolocal.getId(), nfnotainfolocalResponse.getId());
		getNFeBAR().deleteNFNotaInfoLocalById(nfnotainfolocal);
		nfnotainfolocalResponse = getNFeBAR().fetchNFNotaInfoLocalById(request);
		Assert.assertEquals(nfnotainfolocalResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoLocals()
	{
	NFNotaInfoLocal nfnotainfolocal = new NFNotaInfoLocal();
		List<NFNotaInfoLocal> response = getNFeBAR().fetchAllNFNotaInfoLocals(nfnotainfolocal).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoLocals()
	{
		getNFeBAR().deleteAllNFNotaInfoLocals();
	NFNotaInfoLocal nfnotainfolocal = new NFNotaInfoLocal();
		List<NFNotaInfoLocal> response = getNFeBAR().fetchAllNFNotaInfoLocals(new NFNotaInfoLocal()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoLocal()
	{
		NFNotaInfoLocal nfnotainfolocal = Objects.insertNFNotaInfoLocal(1001, TabelaEnum.NFNOTAINFOLOCAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoLocal nfnotainfolocalResponse = getNFeBAR().fetchNFNotaInfoLocalById(request);
		Assert.assertEquals(nfnotainfolocalResponse.getBairro(), "bairro_1");
		getNFeBAR().updateNFNotaInfoLocal(nfnotainfolocal);
		nfnotainfolocalResponse = getNFeBAR().fetchNFNotaInfoLocalById(request);
		Assert.assertEquals(nfnotainfolocalResponse.getBairro(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoLocalsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoLocal> response = getNFeBAR().fetchNFNotaInfoLocalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoLocalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoLocal> response2 = getNFeBAR().fetchNFNotaInfoLocalsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoLocals();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoLocal> response3 = getNFeBAR().fetchNFNotaInfoLocalsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFPESSOAAUTORIZADADOWNLOADNFE ####======================================


@Test
	public void testDeleteNFPessoaAutorizadaDownloadNFe()
	{
		NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe = Objects.insertNFPessoaAutorizadaDownloadNFe(4, TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfeResponse = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFeById(request);
		Assert.assertEquals(nfpessoaautorizadadownloadnfeResponse, null);
		getNFeBAR().insertNFPessoaAutorizadaDownloadNFe(nfpessoaautorizadadownloadnfe);
		nfpessoaautorizadadownloadnfeResponse = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFeById(request);
		Assert.assertEquals(nfpessoaautorizadadownloadnfe.getId(), nfpessoaautorizadadownloadnfeResponse.getId());
		getNFeBAR().deleteNFPessoaAutorizadaDownloadNFeById(nfpessoaautorizadadownloadnfe);
		nfpessoaautorizadadownloadnfeResponse = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFeById(request);
		Assert.assertEquals(nfpessoaautorizadadownloadnfeResponse, null);
	}

	@Test
	public void testFetchAllNFPessoaAutorizadaDownloadNFes()
	{
	NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe = new NFPessoaAutorizadaDownloadNFe();
		List<NFPessoaAutorizadaDownloadNFe> response = getNFeBAR().fetchAllNFPessoaAutorizadaDownloadNFes(nfpessoaautorizadadownloadnfe).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFPessoaAutorizadaDownloadNFes()
	{
		getNFeBAR().deleteAllNFPessoaAutorizadaDownloadNFes();
	NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe = new NFPessoaAutorizadaDownloadNFe();
		List<NFPessoaAutorizadaDownloadNFe> response = getNFeBAR().fetchAllNFPessoaAutorizadaDownloadNFes(new NFPessoaAutorizadaDownloadNFe()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFPessoaAutorizadaDownloadNFe()
	{
		NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfe = Objects.insertNFPessoaAutorizadaDownloadNFe(1001, TabelaEnum.NFPESSOAAUTORIZADADOWNLOADNFE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFPessoaAutorizadaDownloadNFe nfpessoaautorizadadownloadnfeResponse = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFeById(request);
		Assert.assertEquals(nfpessoaautorizadadownloadnfeResponse.getCnpj(), "cnpj_1");
		getNFeBAR().updateNFPessoaAutorizadaDownloadNFe(nfpessoaautorizadadownloadnfe);
		nfpessoaautorizadadownloadnfeResponse = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFeById(request);
		Assert.assertEquals(nfpessoaautorizadadownloadnfeResponse.getCnpj(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFPessoaAutorizadaDownloadNFesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> response = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> response2 = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFPessoaAutorizadaDownloadNFes();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFPessoaAutorizadaDownloadNFe> response3 = getNFeBAR().fetchNFPessoaAutorizadaDownloadNFesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOTOTAL ####======================================


@Test
	public void testDeleteNFNotaInfoTotal()
	{
		NFNotaInfoTotal nfnotainfototal = Objects.insertNFNotaInfoTotal(4000, TabelaEnum.NFNOTAINFOTOTAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4000);
		NFNotaInfoTotal nfnotainfototalResponse = getNFeBAR().fetchNFNotaInfoTotalById(request);
		Assert.assertEquals(nfnotainfototalResponse, null);
		getNFeBAR().insertNFNotaInfoTotal(nfnotainfototal);
		nfnotainfototalResponse = getNFeBAR().fetchNFNotaInfoTotalById(request);
		Assert.assertEquals(nfnotainfototal.getId(), nfnotainfototalResponse.getId());
		Objects.insertNFNotaInfoTotal(4, TabelaEnum.NFNOTAINFOTOTAL, PersistenceActionEnum.DELETE);
//		getNFeBAR().deleteNFNotaInfoTotalById(nfnotainfototal);
//		nfnotainfototalResponse = getNFeBAR().fetchNFNotaInfoTotalById(request);
//		Assert.assertEquals(nfnotainfototalResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoTotals()
	{
	NFNotaInfoTotal nfnotainfototal = new NFNotaInfoTotal();
		List<NFNotaInfoTotal> response = getNFeBAR().fetchAllNFNotaInfoTotals(nfnotainfototal).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoTotals()
	{
		getNFeBAR().deleteAllNFNotaInfoTotals();
	NFNotaInfoTotal nfnotainfototal = new NFNotaInfoTotal();
		List<NFNotaInfoTotal> response = getNFeBAR().fetchAllNFNotaInfoTotals(new NFNotaInfoTotal()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoTotal()
	{
		NFNotaInfoTotal nfnotainfototal = Objects.insertNFNotaInfoTotal(1001, TabelaEnum.NFNOTAINFOTOTAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoTotal nfnotainfototalResponse = getNFeBAR().fetchNFNotaInfoTotalById(request);
		Assert.assertEquals(nfnotainfototalResponse.getModifyUser(), "rod");
		getNFeBAR().updateNFNotaInfoTotal(nfnotainfototal);
		nfnotainfototalResponse = getNFeBAR().fetchNFNotaInfoTotalById(request);
		Assert.assertEquals(nfnotainfototalResponse.getModifyUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoTotalsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoTotal> response = getNFeBAR().fetchNFNotaInfoTotalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoTotalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoTotal> response2 = getNFeBAR().fetchNFNotaInfoTotalsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoTotals();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoTotal> response3 = getNFeBAR().fetchNFNotaInfoTotalsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOICMSTOTAL ####======================================


@Test
	public void testDeleteNFNotaInfoICMSTotal()
	{
		NFNotaInfoICMSTotal nfnotainfoicmstotal = Objects.insertNFNotaInfoICMSTotal(4, TabelaEnum.NFNOTAINFOICMSTOTAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoICMSTotal nfnotainfoicmstotalResponse = getNFeBAR().fetchNFNotaInfoICMSTotalById(request);
		Assert.assertEquals(nfnotainfoicmstotalResponse, null);
		getNFeBAR().insertNFNotaInfoICMSTotal(nfnotainfoicmstotal);
		nfnotainfoicmstotalResponse = getNFeBAR().fetchNFNotaInfoICMSTotalById(request);
		Assert.assertEquals(nfnotainfoicmstotal.getId(), nfnotainfoicmstotalResponse.getId());
		getNFeBAR().deleteNFNotaInfoICMSTotalById(nfnotainfoicmstotal);
		nfnotainfoicmstotalResponse = getNFeBAR().fetchNFNotaInfoICMSTotalById(request);
		Assert.assertEquals(nfnotainfoicmstotalResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoICMSTotals()
	{
	NFNotaInfoICMSTotal nfnotainfoicmstotal = new NFNotaInfoICMSTotal();
		List<NFNotaInfoICMSTotal> response = getNFeBAR().fetchAllNFNotaInfoICMSTotals(nfnotainfoicmstotal).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoICMSTotals()
	{
		getNFeBAR().deleteAllNFNotaInfoICMSTotals();
	NFNotaInfoICMSTotal nfnotainfoicmstotal = new NFNotaInfoICMSTotal();
		List<NFNotaInfoICMSTotal> response = getNFeBAR().fetchAllNFNotaInfoICMSTotals(new NFNotaInfoICMSTotal()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoICMSTotal()
	{
		NFNotaInfoICMSTotal nfnotainfoicmstotal = Objects.insertNFNotaInfoICMSTotal(1001, TabelaEnum.NFNOTAINFOICMSTOTAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoICMSTotal nfnotainfoicmstotalResponse = getNFeBAR().fetchNFNotaInfoICMSTotalById(request);
		Assert.assertEquals(nfnotainfoicmstotalResponse.getBasecalculoicms(), "baseCalculoICMS_1");
		getNFeBAR().updateNFNotaInfoICMSTotal(nfnotainfoicmstotal);
		nfnotainfoicmstotalResponse = getNFeBAR().fetchNFNotaInfoICMSTotalById(request);
		Assert.assertEquals(nfnotainfoicmstotalResponse.getBasecalculoicms(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoICMSTotalsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoICMSTotal> response = getNFeBAR().fetchNFNotaInfoICMSTotalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoICMSTotalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoICMSTotal> response2 = getNFeBAR().fetchNFNotaInfoICMSTotalsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoICMSTotals();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoICMSTotal> response3 = getNFeBAR().fetchNFNotaInfoICMSTotalsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOISSQNTOTAL ####======================================


@Test
	public void testDeleteNFNotaInfoISSQNTotal()
	{
		NFNotaInfoISSQNTotal nfnotainfoissqntotal = Objects.insertNFNotaInfoISSQNTotal(4, TabelaEnum.NFNOTAINFOISSQNTOTAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoISSQNTotal nfnotainfoissqntotalResponse = getNFeBAR().fetchNFNotaInfoISSQNTotalById(request);
		Assert.assertEquals(nfnotainfoissqntotalResponse, null);
		getNFeBAR().insertNFNotaInfoISSQNTotal(nfnotainfoissqntotal);
		nfnotainfoissqntotalResponse = getNFeBAR().fetchNFNotaInfoISSQNTotalById(request);
		Assert.assertEquals(nfnotainfoissqntotal.getId(), nfnotainfoissqntotalResponse.getId());
		getNFeBAR().deleteNFNotaInfoISSQNTotalById(nfnotainfoissqntotal);
		nfnotainfoissqntotalResponse = getNFeBAR().fetchNFNotaInfoISSQNTotalById(request);
		Assert.assertEquals(nfnotainfoissqntotalResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoISSQNTotals()
	{
	NFNotaInfoISSQNTotal nfnotainfoissqntotal = new NFNotaInfoISSQNTotal();
		List<NFNotaInfoISSQNTotal> response = getNFeBAR().fetchAllNFNotaInfoISSQNTotals(nfnotainfoissqntotal).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoISSQNTotals()
	{
		getNFeBAR().deleteAllNFNotaInfoISSQNTotals();
	NFNotaInfoISSQNTotal nfnotainfoissqntotal = new NFNotaInfoISSQNTotal();
		List<NFNotaInfoISSQNTotal> response = getNFeBAR().fetchAllNFNotaInfoISSQNTotals(new NFNotaInfoISSQNTotal()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoISSQNTotal()
	{
		NFNotaInfoISSQNTotal nfnotainfoissqntotal = Objects.insertNFNotaInfoISSQNTotal(1001, TabelaEnum.NFNOTAINFOISSQNTOTAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoISSQNTotal nfnotainfoissqntotalResponse = getNFeBAR().fetchNFNotaInfoISSQNTotalById(request);
		Assert.assertEquals(nfnotainfoissqntotalResponse.getBaseCalculoISS(), "baseCalculoISS_1");
		getNFeBAR().updateNFNotaInfoISSQNTotal(nfnotainfoissqntotal);
		nfnotainfoissqntotalResponse = getNFeBAR().fetchNFNotaInfoISSQNTotalById(request);
		Assert.assertEquals(nfnotainfoissqntotalResponse.getBaseCalculoISS(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoISSQNTotalsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoISSQNTotal> response = getNFeBAR().fetchNFNotaInfoISSQNTotalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoISSQNTotalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoISSQNTotal> response2 = getNFeBAR().fetchNFNotaInfoISSQNTotalsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoISSQNTotals();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoISSQNTotal> response3 = getNFeBAR().fetchNFNotaInfoISSQNTotalsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFORETENCOESTRIBUTOS ####======================================


@Test
	public void testDeleteNFNotaInfoRetencoesTributos()
	{
		NFNotaInfoRetencoesTributos nfnotainforetencoestributos = Objects.insertNFNotaInfoRetencoesTributos(4, TabelaEnum.NFNOTAINFORETENCOESTRIBUTOS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoRetencoesTributos nfnotainforetencoestributosResponse = getNFeBAR().fetchNFNotaInfoRetencoesTributosById(request);
		Assert.assertEquals(nfnotainforetencoestributosResponse, null);
		getNFeBAR().insertNFNotaInfoRetencoesTributos(nfnotainforetencoestributos);
		nfnotainforetencoestributosResponse = getNFeBAR().fetchNFNotaInfoRetencoesTributosById(request);
		Assert.assertEquals(nfnotainforetencoestributos.getId(), nfnotainforetencoestributosResponse.getId());
		getNFeBAR().deleteNFNotaInfoRetencoesTributosById(nfnotainforetencoestributos);
		nfnotainforetencoestributosResponse = getNFeBAR().fetchNFNotaInfoRetencoesTributosById(request);
		Assert.assertEquals(nfnotainforetencoestributosResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoRetencoesTributoss()
	{
	NFNotaInfoRetencoesTributos nfnotainforetencoestributos = new NFNotaInfoRetencoesTributos();
		List<NFNotaInfoRetencoesTributos> response = getNFeBAR().fetchAllNFNotaInfoRetencoesTributoss(nfnotainforetencoestributos).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoRetencoesTributoss()
	{
		getNFeBAR().deleteAllNFNotaInfoRetencoesTributoss();
	NFNotaInfoRetencoesTributos nfnotainforetencoestributos = new NFNotaInfoRetencoesTributos();
		List<NFNotaInfoRetencoesTributos> response = getNFeBAR().fetchAllNFNotaInfoRetencoesTributoss(new NFNotaInfoRetencoesTributos()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoRetencoesTributos()
	{
		NFNotaInfoRetencoesTributos nfnotainforetencoestributos = Objects.insertNFNotaInfoRetencoesTributos(1001, TabelaEnum.NFNOTAINFORETENCOESTRIBUTOS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoRetencoesTributos nfnotainforetencoestributosResponse = getNFeBAR().fetchNFNotaInfoRetencoesTributosById(request);
		Assert.assertEquals(nfnotainforetencoestributosResponse.getBasecalculoirrf(), "baseCalculoIRRF_1");
		getNFeBAR().updateNFNotaInfoRetencoesTributos(nfnotainforetencoestributos);
		nfnotainforetencoestributosResponse = getNFeBAR().fetchNFNotaInfoRetencoesTributosById(request);
		Assert.assertEquals(nfnotainforetencoestributosResponse.getBasecalculoirrf(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoRetencoesTributossByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoRetencoesTributos> response = getNFeBAR().fetchNFNotaInfoRetencoesTributossByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoRetencoesTributossByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoRetencoesTributos> response2 = getNFeBAR().fetchNFNotaInfoRetencoesTributossByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoRetencoesTributoss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoRetencoesTributos> response3 = getNFeBAR().fetchNFNotaInfoRetencoesTributossByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOTRANSPORTE ####======================================


@Test
	public void testDeleteNFNotaInfoTransporte()
	{
		NFNotaInfoTransporte nfnotainfotransporte = Objects.insertNFNotaInfoTransporte(4, TabelaEnum.NFNOTAINFOTRANSPORTE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoTransporte nfnotainfotransporteResponse = getNFeBAR().fetchNFNotaInfoTransporteById(request);
		Assert.assertEquals(nfnotainfotransporteResponse, null);
		getNFeBAR().insertNFNotaInfoTransporte(nfnotainfotransporte);
		nfnotainfotransporteResponse = getNFeBAR().fetchNFNotaInfoTransporteById(request);
		Assert.assertEquals(nfnotainfotransporte.getId(), nfnotainfotransporteResponse.getId());
		nfnotainfotransporte = Objects.insertNFNotaInfoTransporte(4, TabelaEnum.NFNOTAINFOTRANSPORTE, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaInfoTransporteById(nfnotainfotransporte);
		nfnotainfotransporteResponse = getNFeBAR().fetchNFNotaInfoTransporteById(request);
		Assert.assertEquals(nfnotainfotransporteResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoTransportes()
	{
	NFNotaInfoTransporte nfnotainfotransporte = new NFNotaInfoTransporte();
		List<NFNotaInfoTransporte> response = getNFeBAR().fetchAllNFNotaInfoTransportes(nfnotainfotransporte).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoTransportes()
	{
		getNFeBAR().deleteAllNFNotaInfoTransportes();
	NFNotaInfoTransporte nfnotainfotransporte = new NFNotaInfoTransporte();
		List<NFNotaInfoTransporte> response = getNFeBAR().fetchAllNFNotaInfoTransportes(new NFNotaInfoTransporte()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoTransporte()
	{
		NFNotaInfoTransporte nfnotainfotransporte = Objects.insertNFNotaInfoTransporte(1001, TabelaEnum.NFNOTAINFOTRANSPORTE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoTransporte nfnotainfotransporteResponse = getNFeBAR().fetchNFNotaInfoTransporteById(request);
		Assert.assertEquals(nfnotainfotransporteResponse.getBalsa(), "balsa_1");
		getNFeBAR().updateNFNotaInfoTransporte(nfnotainfotransporte);
		nfnotainfotransporteResponse = getNFeBAR().fetchNFNotaInfoTransporteById(request);
		Assert.assertEquals(nfnotainfotransporteResponse.getBalsa(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoTransportesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoTransporte> response = getNFeBAR().fetchNFNotaInfoTransportesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoTransportesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoTransporte> response2 = getNFeBAR().fetchNFNotaInfoTransportesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoTransportes();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoTransporte> response3 = getNFeBAR().fetchNFNotaInfoTransportesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFORETENCAOICMSTRANSPORTE ####======================================


@Test
	public void testDeleteNFNotaInfoRetencaoICMSTransporte()
	{
		NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte = Objects.insertNFNotaInfoRetencaoICMSTransporte(4, TabelaEnum.NFNOTAINFORETENCAOICMSTRANSPORTE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporteResponse = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransporteById(request);
		Assert.assertEquals(nfnotainforetencaoicmstransporteResponse, null);
		getNFeBAR().insertNFNotaInfoRetencaoICMSTransporte(nfnotainforetencaoicmstransporte);
		nfnotainforetencaoicmstransporteResponse = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransporteById(request);
		Assert.assertEquals(nfnotainforetencaoicmstransporte.getId(), nfnotainforetencaoicmstransporteResponse.getId());
		getNFeBAR().deleteNFNotaInfoRetencaoICMSTransporteById(nfnotainforetencaoicmstransporte);
		nfnotainforetencaoicmstransporteResponse = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransporteById(request);
		Assert.assertEquals(nfnotainforetencaoicmstransporteResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoRetencaoICMSTransportes()
	{
	NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte = new NFNotaInfoRetencaoICMSTransporte();
		List<NFNotaInfoRetencaoICMSTransporte> response = getNFeBAR().fetchAllNFNotaInfoRetencaoICMSTransportes(nfnotainforetencaoicmstransporte).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoRetencaoICMSTransportes()
	{
		getNFeBAR().deleteAllNFNotaInfoRetencaoICMSTransportes();
	NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte = new NFNotaInfoRetencaoICMSTransporte();
		List<NFNotaInfoRetencaoICMSTransporte> response = getNFeBAR().fetchAllNFNotaInfoRetencaoICMSTransportes(new NFNotaInfoRetencaoICMSTransporte()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoRetencaoICMSTransporte()
	{
		NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporte = Objects.insertNFNotaInfoRetencaoICMSTransporte(1001, TabelaEnum.NFNOTAINFORETENCAOICMSTRANSPORTE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoRetencaoICMSTransporte nfnotainforetencaoicmstransporteResponse = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransporteById(request);
		Assert.assertEquals(nfnotainforetencaoicmstransporteResponse.getAliquotaretencao(), "aliquotaRetencao_1");
		getNFeBAR().updateNFNotaInfoRetencaoICMSTransporte(nfnotainforetencaoicmstransporte);
		nfnotainforetencaoicmstransporteResponse = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransporteById(request);
		Assert.assertEquals(nfnotainforetencaoicmstransporteResponse.getAliquotaretencao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoRetencaoICMSTransportesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> response = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransportesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransportesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> response2 = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransportesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoRetencaoICMSTransportes();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoRetencaoICMSTransporte> response3 = getNFeBAR().fetchNFNotaInfoRetencaoICMSTransportesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOTRANSPORTADOR ####======================================


@Test
	public void testDeleteNFNotaInfoTransportador()
	{
		NFNotaInfoTransportador nfnotainfotransportador = Objects.insertNFNotaInfoTransportador(4, TabelaEnum.NFNOTAINFOTRANSPORTADOR, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoTransportador nfnotainfotransportadorResponse = getNFeBAR().fetchNFNotaInfoTransportadorById(request);
		Assert.assertEquals(nfnotainfotransportadorResponse, null);
		getNFeBAR().insertNFNotaInfoTransportador(nfnotainfotransportador);
		nfnotainfotransportadorResponse = getNFeBAR().fetchNFNotaInfoTransportadorById(request);
		Assert.assertEquals(nfnotainfotransportador.getId(), nfnotainfotransportadorResponse.getId());
		getNFeBAR().deleteNFNotaInfoTransportadorById(nfnotainfotransportador);
		nfnotainfotransportadorResponse = getNFeBAR().fetchNFNotaInfoTransportadorById(request);
		Assert.assertEquals(nfnotainfotransportadorResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoTransportadors()
	{
	NFNotaInfoTransportador nfnotainfotransportador = new NFNotaInfoTransportador();
		List<NFNotaInfoTransportador> response = getNFeBAR().fetchAllNFNotaInfoTransportadors(nfnotainfotransportador).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoTransportadors()
	{
		getNFeBAR().deleteAllNFNotaInfoTransportadors();
	NFNotaInfoTransportador nfnotainfotransportador = new NFNotaInfoTransportador();
		List<NFNotaInfoTransportador> response = getNFeBAR().fetchAllNFNotaInfoTransportadors(new NFNotaInfoTransportador()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoTransportador()
	{
		NFNotaInfoTransportador nfnotainfotransportador = Objects.insertNFNotaInfoTransportador(1001, TabelaEnum.NFNOTAINFOTRANSPORTADOR, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoTransportador nfnotainfotransportadorResponse = getNFeBAR().fetchNFNotaInfoTransportadorById(request);
		Assert.assertEquals(nfnotainfotransportadorResponse.getCnpj(), "cnpj_1");
		getNFeBAR().updateNFNotaInfoTransportador(nfnotainfotransportador);
		nfnotainfotransportadorResponse = getNFeBAR().fetchNFNotaInfoTransportadorById(request);
		Assert.assertEquals(nfnotainfotransportadorResponse.getCnpj(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoTransportadorsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoTransportador> response = getNFeBAR().fetchNFNotaInfoTransportadorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoTransportadorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoTransportador> response2 = getNFeBAR().fetchNFNotaInfoTransportadorsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoTransportadors();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoTransportador> response3 = getNFeBAR().fetchNFNotaInfoTransportadorsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOVEICULO ####======================================


@Test
	public void testDeleteNFNotaInfoVeiculo()
	{
		NFNotaInfoVeiculo nfnotainfoveiculo = Objects.insertNFNotaInfoVeiculo(4, TabelaEnum.NFNOTAINFOVEICULO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoVeiculo nfnotainfoveiculoResponse = getNFeBAR().fetchNFNotaInfoVeiculoById(request);
		Assert.assertEquals(nfnotainfoveiculoResponse, null);
		getNFeBAR().insertNFNotaInfoVeiculo(nfnotainfoveiculo);
		nfnotainfoveiculoResponse = getNFeBAR().fetchNFNotaInfoVeiculoById(request);
		Assert.assertEquals(nfnotainfoveiculo.getId(), nfnotainfoveiculoResponse.getId());
		getNFeBAR().deleteNFNotaInfoVeiculoById(nfnotainfoveiculo);
		nfnotainfoveiculoResponse = getNFeBAR().fetchNFNotaInfoVeiculoById(request);
		Assert.assertEquals(nfnotainfoveiculoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoVeiculos()
	{
	NFNotaInfoVeiculo nfnotainfoveiculo = new NFNotaInfoVeiculo();
		List<NFNotaInfoVeiculo> response = getNFeBAR().fetchAllNFNotaInfoVeiculos(nfnotainfoveiculo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoVeiculos()
	{
		getNFeBAR().deleteAllNFNotaInfoVeiculos();
	NFNotaInfoVeiculo nfnotainfoveiculo = new NFNotaInfoVeiculo();
		List<NFNotaInfoVeiculo> response = getNFeBAR().fetchAllNFNotaInfoVeiculos(new NFNotaInfoVeiculo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoVeiculo()
	{
		NFNotaInfoVeiculo nfnotainfoveiculo = Objects.insertNFNotaInfoVeiculo(1001, TabelaEnum.NFNOTAINFOVEICULO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoVeiculo nfnotainfoveiculoResponse = getNFeBAR().fetchNFNotaInfoVeiculoById(request);
		Assert.assertEquals(nfnotainfoveiculoResponse.getPlacaveiculo(), "placaVeiculo_1");
		getNFeBAR().updateNFNotaInfoVeiculo(nfnotainfoveiculo);
		nfnotainfoveiculoResponse = getNFeBAR().fetchNFNotaInfoVeiculoById(request);
		Assert.assertEquals(nfnotainfoveiculoResponse.getPlacaveiculo(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoVeiculosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoVeiculo> response = getNFeBAR().fetchNFNotaInfoVeiculosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoVeiculosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoVeiculo> response2 = getNFeBAR().fetchNFNotaInfoVeiculosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoVeiculos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoVeiculo> response3 = getNFeBAR().fetchNFNotaInfoVeiculosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOREBOQUE ####======================================


@Test
	public void testDeleteNFNotaInfoReboque()
	{
		NFNotaInfoReboque nfnotainforeboque = Objects.insertNFNotaInfoReboque(4, TabelaEnum.NFNOTAINFOREBOQUE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoReboque nfnotainforeboqueResponse = getNFeBAR().fetchNFNotaInfoReboqueById(request);
		Assert.assertEquals(nfnotainforeboqueResponse, null);
		getNFeBAR().insertNFNotaInfoReboque(nfnotainforeboque);
		nfnotainforeboqueResponse = getNFeBAR().fetchNFNotaInfoReboqueById(request);
		Assert.assertEquals(nfnotainforeboque.getId(), nfnotainforeboqueResponse.getId());
		getNFeBAR().deleteNFNotaInfoReboqueById(nfnotainforeboque);
		nfnotainforeboqueResponse = getNFeBAR().fetchNFNotaInfoReboqueById(request);
		Assert.assertEquals(nfnotainforeboqueResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoReboques()
	{
	NFNotaInfoReboque nfnotainforeboque = new NFNotaInfoReboque();
		List<NFNotaInfoReboque> response = getNFeBAR().fetchAllNFNotaInfoReboques(nfnotainforeboque).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoReboques()
	{
		getNFeBAR().deleteAllNFNotaInfoReboques();
	NFNotaInfoReboque nfnotainforeboque = new NFNotaInfoReboque();
		List<NFNotaInfoReboque> response = getNFeBAR().fetchAllNFNotaInfoReboques(new NFNotaInfoReboque()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoReboque()
	{
		NFNotaInfoReboque nfnotainforeboque = Objects.insertNFNotaInfoReboque(1001, TabelaEnum.NFNOTAINFOREBOQUE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoReboque nfnotainforeboqueResponse = getNFeBAR().fetchNFNotaInfoReboqueById(request);
		Assert.assertEquals(nfnotainforeboqueResponse.getPlacaVeiculo(), "placaVeiculo_1");
		getNFeBAR().updateNFNotaInfoReboque(nfnotainforeboque);
		nfnotainforeboqueResponse = getNFeBAR().fetchNFNotaInfoReboqueById(request);
		Assert.assertEquals(nfnotainforeboqueResponse.getPlacaVeiculo(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoReboquesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoReboque> response = getNFeBAR().fetchNFNotaInfoReboquesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoReboquesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoReboque> response2 = getNFeBAR().fetchNFNotaInfoReboquesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoReboques();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoReboque> response3 = getNFeBAR().fetchNFNotaInfoReboquesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOCOBRANCA ####======================================


@Test
	public void testDeleteNFNotaInfoCobranca()
	{
		NFNotaInfoCobranca nfnotainfocobranca = Objects.insertNFNotaInfoCobranca(4, TabelaEnum.NFNOTAINFOCOBRANCA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoCobranca nfnotainfocobrancaResponse = getNFeBAR().fetchNFNotaInfoCobrancaById(request);
		Assert.assertEquals(nfnotainfocobrancaResponse, null);
		getNFeBAR().insertNFNotaInfoCobranca(nfnotainfocobranca);
		nfnotainfocobrancaResponse = getNFeBAR().fetchNFNotaInfoCobrancaById(request);
		Assert.assertEquals(nfnotainfocobranca.getId(), nfnotainfocobrancaResponse.getId());
		nfnotainfocobranca = Objects.insertNFNotaInfoCobranca(4, TabelaEnum.NFNOTAINFOCOBRANCA, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaInfoCobrancaById(nfnotainfocobranca);
		nfnotainfocobrancaResponse = getNFeBAR().fetchNFNotaInfoCobrancaById(request);
		Assert.assertEquals(nfnotainfocobrancaResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoCobrancas()
	{
	NFNotaInfoCobranca nfnotainfocobranca = new NFNotaInfoCobranca();
		List<NFNotaInfoCobranca> response = getNFeBAR().fetchAllNFNotaInfoCobrancas(nfnotainfocobranca).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoCobrancas()
	{
		getNFeBAR().deleteAllNFNotaInfoCobrancas();
	NFNotaInfoCobranca nfnotainfocobranca = new NFNotaInfoCobranca();
		List<NFNotaInfoCobranca> response = getNFeBAR().fetchAllNFNotaInfoCobrancas(new NFNotaInfoCobranca()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoCobranca()
	{
		NFNotaInfoCobranca nfnotainfocobranca = Objects.insertNFNotaInfoCobranca(1001, TabelaEnum.NFNOTAINFOCOBRANCA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoCobranca nfnotainfocobrancaResponse = getNFeBAR().fetchNFNotaInfoCobrancaById(request);
		Assert.assertEquals(nfnotainfocobrancaResponse.getCreateUser(), "system");
		getNFeBAR().updateNFNotaInfoCobranca(nfnotainfocobranca);
		nfnotainfocobrancaResponse = getNFeBAR().fetchNFNotaInfoCobrancaById(request);
		Assert.assertEquals(nfnotainfocobrancaResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoCobrancasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoCobranca> response = getNFeBAR().fetchNFNotaInfoCobrancasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoCobrancasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoCobranca> response2 = getNFeBAR().fetchNFNotaInfoCobrancasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoCobrancas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoCobranca> response3 = getNFeBAR().fetchNFNotaInfoCobrancasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFODUPLICATA ####======================================


@Test
	public void testDeleteNFNotaInfoDuplicata()
	{
		NFNotaInfoDuplicata nfnotainfoduplicata = Objects.insertNFNotaInfoDuplicata(4, TabelaEnum.NFNOTAINFODUPLICATA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoDuplicata nfnotainfoduplicataResponse = getNFeBAR().fetchNFNotaInfoDuplicataById(request);
		Assert.assertEquals(nfnotainfoduplicataResponse, null);
		getNFeBAR().insertNFNotaInfoDuplicata(nfnotainfoduplicata);
		nfnotainfoduplicataResponse = getNFeBAR().fetchNFNotaInfoDuplicataById(request);
		Assert.assertEquals(nfnotainfoduplicata.getId(), nfnotainfoduplicataResponse.getId());
		getNFeBAR().deleteNFNotaInfoDuplicataById(nfnotainfoduplicata);
		nfnotainfoduplicataResponse = getNFeBAR().fetchNFNotaInfoDuplicataById(request);
		Assert.assertEquals(nfnotainfoduplicataResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoDuplicatas()
	{
	NFNotaInfoDuplicata nfnotainfoduplicata = new NFNotaInfoDuplicata();
		List<NFNotaInfoDuplicata> response = getNFeBAR().fetchAllNFNotaInfoDuplicatas(nfnotainfoduplicata).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoDuplicatas()
	{
		getNFeBAR().deleteAllNFNotaInfoDuplicatas();
	NFNotaInfoDuplicata nfnotainfoduplicata = new NFNotaInfoDuplicata();
		List<NFNotaInfoDuplicata> response = getNFeBAR().fetchAllNFNotaInfoDuplicatas(new NFNotaInfoDuplicata()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoDuplicata()
	{
		NFNotaInfoDuplicata nfnotainfoduplicata = Objects.insertNFNotaInfoDuplicata(1001, TabelaEnum.NFNOTAINFODUPLICATA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoDuplicata nfnotainfoduplicataResponse = getNFeBAR().fetchNFNotaInfoDuplicataById(request);
		Assert.assertEquals(nfnotainfoduplicataResponse.getNumeroDuplicata(), "numeroDuplicata_1");
		getNFeBAR().updateNFNotaInfoDuplicata(nfnotainfoduplicata);
		nfnotainfoduplicataResponse = getNFeBAR().fetchNFNotaInfoDuplicataById(request);
		Assert.assertEquals(nfnotainfoduplicataResponse.getNumeroDuplicata(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoDuplicatasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoDuplicata> response = getNFeBAR().fetchNFNotaInfoDuplicatasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoDuplicatasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoDuplicata> response2 = getNFeBAR().fetchNFNotaInfoDuplicatasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoDuplicatas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoDuplicata> response3 = getNFeBAR().fetchNFNotaInfoDuplicatasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOFATURA ####======================================


@Test
	public void testDeleteNFNotaInfoFatura()
	{
		NFNotaInfoFatura nfnotainfofatura = Objects.insertNFNotaInfoFatura(4, TabelaEnum.NFNOTAINFOFATURA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoFatura nfnotainfofaturaResponse = getNFeBAR().fetchNFNotaInfoFaturaById(request);
		Assert.assertEquals(nfnotainfofaturaResponse, null);
		getNFeBAR().insertNFNotaInfoFatura(nfnotainfofatura);
		nfnotainfofaturaResponse = getNFeBAR().fetchNFNotaInfoFaturaById(request);
		Assert.assertEquals(nfnotainfofatura.getId(), nfnotainfofaturaResponse.getId());
		getNFeBAR().deleteNFNotaInfoFaturaById(nfnotainfofatura);
		nfnotainfofaturaResponse = getNFeBAR().fetchNFNotaInfoFaturaById(request);
		Assert.assertEquals(nfnotainfofaturaResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoFaturas()
	{
	NFNotaInfoFatura nfnotainfofatura = new NFNotaInfoFatura();
		List<NFNotaInfoFatura> response = getNFeBAR().fetchAllNFNotaInfoFaturas(nfnotainfofatura).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoFaturas()
	{
		getNFeBAR().deleteAllNFNotaInfoFaturas();
	NFNotaInfoFatura nfnotainfofatura = new NFNotaInfoFatura();
		List<NFNotaInfoFatura> response = getNFeBAR().fetchAllNFNotaInfoFaturas(new NFNotaInfoFatura()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoFatura()
	{
		NFNotaInfoFatura nfnotainfofatura = Objects.insertNFNotaInfoFatura(1001, TabelaEnum.NFNOTAINFOFATURA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoFatura nfnotainfofaturaResponse = getNFeBAR().fetchNFNotaInfoFaturaById(request);
		Assert.assertEquals(nfnotainfofaturaResponse.getNumeroFatura(), "numeroFatura_1");
		getNFeBAR().updateNFNotaInfoFatura(nfnotainfofatura);
		nfnotainfofaturaResponse = getNFeBAR().fetchNFNotaInfoFaturaById(request);
		Assert.assertEquals(nfnotainfofaturaResponse.getNumeroFatura(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoFaturasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoFatura> response = getNFeBAR().fetchNFNotaInfoFaturasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoFaturasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoFatura> response2 = getNFeBAR().fetchNFNotaInfoFaturasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoFaturas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoFatura> response3 = getNFeBAR().fetchNFNotaInfoFaturasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOCARTAO ####======================================


@Test
	public void testDeleteNFNotaInfoCartao()
	{
		NFNotaInfoCartao nfnotainfocartao = Objects.insertNFNotaInfoCartao(4, TabelaEnum.NFNOTAINFOCARTAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoCartao nfnotainfocartaoResponse = getNFeBAR().fetchNFNotaInfoCartaoById(request);
		Assert.assertEquals(nfnotainfocartaoResponse, null);
		getNFeBAR().insertNFNotaInfoCartao(nfnotainfocartao);
		nfnotainfocartaoResponse = getNFeBAR().fetchNFNotaInfoCartaoById(request);
		Assert.assertEquals(nfnotainfocartao.getId(), nfnotainfocartaoResponse.getId());
		getNFeBAR().deleteNFNotaInfoCartaoById(nfnotainfocartao);
		nfnotainfocartaoResponse = getNFeBAR().fetchNFNotaInfoCartaoById(request);
		Assert.assertEquals(nfnotainfocartaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoCartaos()
	{
	NFNotaInfoCartao nfnotainfocartao = new NFNotaInfoCartao();
		List<NFNotaInfoCartao> response = getNFeBAR().fetchAllNFNotaInfoCartaos(nfnotainfocartao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoCartaos()
	{
		getNFeBAR().deleteAllNFNotaInfoCartaos();
	NFNotaInfoCartao nfnotainfocartao = new NFNotaInfoCartao();
		List<NFNotaInfoCartao> response = getNFeBAR().fetchAllNFNotaInfoCartaos(new NFNotaInfoCartao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoCartao()
	{
		NFNotaInfoCartao nfnotainfocartao = Objects.insertNFNotaInfoCartao(1001, TabelaEnum.NFNOTAINFOCARTAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoCartao nfnotainfocartaoResponse = getNFeBAR().fetchNFNotaInfoCartaoById(request);
		Assert.assertEquals(nfnotainfocartaoResponse.getCnpj(), "cnpj_1");
		getNFeBAR().updateNFNotaInfoCartao(nfnotainfocartao);
		nfnotainfocartaoResponse = getNFeBAR().fetchNFNotaInfoCartaoById(request);
		Assert.assertEquals(nfnotainfocartaoResponse.getCnpj(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoCartaosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoCartao> response = getNFeBAR().fetchNFNotaInfoCartaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoCartaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoCartao> response2 = getNFeBAR().fetchNFNotaInfoCartaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoCartaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoCartao> response3 = getNFeBAR().fetchNFNotaInfoCartaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOPAGAMENTO ####======================================


@Test
	public void testDeleteNFNotaInfoPagamento()
	{
		NFNotaInfoPagamento nfnotainfopagamento = Objects.insertNFNotaInfoPagamento(4, TabelaEnum.NFNOTAINFOPAGAMENTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoPagamento nfnotainfopagamentoResponse = getNFeBAR().fetchNFNotaInfoPagamentoById(request);
		Assert.assertEquals(nfnotainfopagamentoResponse, null);
		getNFeBAR().insertNFNotaInfoPagamento(nfnotainfopagamento);
		nfnotainfopagamentoResponse = getNFeBAR().fetchNFNotaInfoPagamentoById(request);
		Assert.assertEquals(nfnotainfopagamento.getId(), nfnotainfopagamentoResponse.getId());
		nfnotainfopagamento = Objects.insertNFNotaInfoPagamento(4, TabelaEnum.NFNOTAINFOPAGAMENTO, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaInfoPagamentoById(nfnotainfopagamento);
		nfnotainfopagamentoResponse = getNFeBAR().fetchNFNotaInfoPagamentoById(request);
		Assert.assertEquals(nfnotainfopagamentoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoPagamentos()
	{
	NFNotaInfoPagamento nfnotainfopagamento = new NFNotaInfoPagamento();
		List<NFNotaInfoPagamento> response = getNFeBAR().fetchAllNFNotaInfoPagamentos(nfnotainfopagamento).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoPagamentos()
	{
		getNFeBAR().deleteAllNFNotaInfoPagamentos();
	NFNotaInfoPagamento nfnotainfopagamento = new NFNotaInfoPagamento();
		List<NFNotaInfoPagamento> response = getNFeBAR().fetchAllNFNotaInfoPagamentos(new NFNotaInfoPagamento()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoPagamento()
	{
		NFNotaInfoPagamento nfnotainfopagamento = Objects.insertNFNotaInfoPagamento(1001, TabelaEnum.NFNOTAINFOPAGAMENTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoPagamento nfnotainfopagamentoResponse = getNFeBAR().fetchNFNotaInfoPagamentoById(request);
		Assert.assertEquals(nfnotainfopagamentoResponse.getValorpagamento(), "valorPagamento_1");
		getNFeBAR().updateNFNotaInfoPagamento(nfnotainfopagamento);
		nfnotainfopagamentoResponse = getNFeBAR().fetchNFNotaInfoPagamentoById(request);
		Assert.assertEquals(nfnotainfopagamentoResponse.getValorpagamento(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoPagamentosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoPagamento> response = getNFeBAR().fetchNFNotaInfoPagamentosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoPagamentosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoPagamento> response2 = getNFeBAR().fetchNFNotaInfoPagamentosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoPagamentos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoPagamento> response3 = getNFeBAR().fetchNFNotaInfoPagamentosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOINFORMACOESADICIONAIS ####======================================


@Test
	public void testDeleteNFNotaInfoInformacoesAdicionais()
	{
		NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais = Objects.insertNFNotaInfoInformacoesAdicionais(4000, TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4000);
		NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionaisResponse = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaisById(request);
		Assert.assertEquals(nfnotainfoinformacoesadicionaisResponse, null);
		getNFeBAR().insertNFNotaInfoInformacoesAdicionais(nfnotainfoinformacoesadicionais);
		nfnotainfoinformacoesadicionaisResponse = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaisById(request);
		Assert.assertEquals(nfnotainfoinformacoesadicionais.getId(), nfnotainfoinformacoesadicionaisResponse.getId());
		nfnotainfoinformacoesadicionais = Objects.insertNFNotaInfoInformacoesAdicionais(4000, TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaInfoInformacoesAdicionaisById(nfnotainfoinformacoesadicionais);
		nfnotainfoinformacoesadicionaisResponse = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaisById(request);
		Assert.assertEquals(nfnotainfoinformacoesadicionaisResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoInformacoesAdicionaiss()
	{
	NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais = new NFNotaInfoInformacoesAdicionais();
		List<NFNotaInfoInformacoesAdicionais> response = getNFeBAR().fetchAllNFNotaInfoInformacoesAdicionaiss(nfnotainfoinformacoesadicionais).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoInformacoesAdicionaiss()
	{
		getNFeBAR().deleteAllNFNotaInfoInformacoesAdicionaiss();
	NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais = new NFNotaInfoInformacoesAdicionais();
		List<NFNotaInfoInformacoesAdicionais> response = getNFeBAR().fetchAllNFNotaInfoInformacoesAdicionaiss(new NFNotaInfoInformacoesAdicionais()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoInformacoesAdicionais()
	{
		NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionais = Objects.insertNFNotaInfoInformacoesAdicionais(1001, TabelaEnum.NFNOTAINFOINFORMACOESADICIONAIS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoInformacoesAdicionais nfnotainfoinformacoesadicionaisResponse = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaisById(request);
		Assert.assertEquals(nfnotainfoinformacoesadicionaisResponse.getInformacoesadicionaisinteressefisco(), "informacoesAdicionaisInteresseFisco_1");
		getNFeBAR().updateNFNotaInfoInformacoesAdicionais(nfnotainfoinformacoesadicionais);
		nfnotainfoinformacoesadicionaisResponse = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaisById(request);
		Assert.assertEquals(nfnotainfoinformacoesadicionaisResponse.getInformacoesadicionaisinteressefisco(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoInformacoesAdicionaissByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoInformacoesAdicionais> response = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaissByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaissByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoInformacoesAdicionais> response2 = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaissByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoInformacoesAdicionaiss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoInformacoesAdicionais> response3 = getNFeBAR().fetchNFNotaInfoInformacoesAdicionaissByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOOBSERVACAO ####======================================


@Test
	public void testDeleteNFNotaInfoObservacao()
	{
		NFNotaInfoObservacao nfnotainfoobservacao = Objects.insertNFNotaInfoObservacao(4, TabelaEnum.NFNOTAINFOOBSERVACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoObservacao nfnotainfoobservacaoResponse = getNFeBAR().fetchNFNotaInfoObservacaoById(request);
		Assert.assertEquals(nfnotainfoobservacaoResponse, null);
		getNFeBAR().insertNFNotaInfoObservacao(nfnotainfoobservacao);
		nfnotainfoobservacaoResponse = getNFeBAR().fetchNFNotaInfoObservacaoById(request);
		Assert.assertEquals(nfnotainfoobservacao.getId(), nfnotainfoobservacaoResponse.getId());
		getNFeBAR().deleteNFNotaInfoObservacaoById(nfnotainfoobservacao);
		nfnotainfoobservacaoResponse = getNFeBAR().fetchNFNotaInfoObservacaoById(request);
		Assert.assertEquals(nfnotainfoobservacaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoObservacaos()
	{
	NFNotaInfoObservacao nfnotainfoobservacao = new NFNotaInfoObservacao();
		List<NFNotaInfoObservacao> response = getNFeBAR().fetchAllNFNotaInfoObservacaos(nfnotainfoobservacao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoObservacaos()
	{
		getNFeBAR().deleteAllNFNotaInfoObservacaos();
	NFNotaInfoObservacao nfnotainfoobservacao = new NFNotaInfoObservacao();
		List<NFNotaInfoObservacao> response = getNFeBAR().fetchAllNFNotaInfoObservacaos(new NFNotaInfoObservacao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoObservacao()
	{
		NFNotaInfoObservacao nfnotainfoobservacao = Objects.insertNFNotaInfoObservacao(1001, TabelaEnum.NFNOTAINFOOBSERVACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoObservacao nfnotainfoobservacaoResponse = getNFeBAR().fetchNFNotaInfoObservacaoById(request);
		Assert.assertEquals(nfnotainfoobservacaoResponse.getConteudocampo(), "conteudoCampo_1");
		getNFeBAR().updateNFNotaInfoObservacao(nfnotainfoobservacao);
		nfnotainfoobservacaoResponse = getNFeBAR().fetchNFNotaInfoObservacaoById(request);
		Assert.assertEquals(nfnotainfoobservacaoResponse.getConteudocampo(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoObservacaosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoObservacao> response = getNFeBAR().fetchNFNotaInfoObservacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoObservacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoObservacao> response2 = getNFeBAR().fetchNFNotaInfoObservacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoObservacaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoObservacao> response3 = getNFeBAR().fetchNFNotaInfoObservacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOPROCESSOREFERENCIADO ####======================================


@Test
	public void testDeleteNFNotaInfoProcessoReferenciado()
	{
		NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado = Objects.insertNFNotaInfoProcessoReferenciado(4, TabelaEnum.NFNOTAINFOPROCESSOREFERENCIADO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciadoResponse = getNFeBAR().fetchNFNotaInfoProcessoReferenciadoById(request);
		Assert.assertEquals(nfnotainfoprocessoreferenciadoResponse, null);
		getNFeBAR().insertNFNotaInfoProcessoReferenciado(nfnotainfoprocessoreferenciado);
		nfnotainfoprocessoreferenciadoResponse = getNFeBAR().fetchNFNotaInfoProcessoReferenciadoById(request);
		Assert.assertEquals(nfnotainfoprocessoreferenciado.getId(), nfnotainfoprocessoreferenciadoResponse.getId());
		getNFeBAR().deleteNFNotaInfoProcessoReferenciadoById(nfnotainfoprocessoreferenciado);
		nfnotainfoprocessoreferenciadoResponse = getNFeBAR().fetchNFNotaInfoProcessoReferenciadoById(request);
		Assert.assertEquals(nfnotainfoprocessoreferenciadoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoProcessoReferenciados()
	{
	NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado = new NFNotaInfoProcessoReferenciado();
		List<NFNotaInfoProcessoReferenciado> response = getNFeBAR().fetchAllNFNotaInfoProcessoReferenciados(nfnotainfoprocessoreferenciado).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoProcessoReferenciados()
	{
		getNFeBAR().deleteAllNFNotaInfoProcessoReferenciados();
	NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado = new NFNotaInfoProcessoReferenciado();
		List<NFNotaInfoProcessoReferenciado> response = getNFeBAR().fetchAllNFNotaInfoProcessoReferenciados(new NFNotaInfoProcessoReferenciado()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoProcessoReferenciado()
	{
		NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciado = Objects.insertNFNotaInfoProcessoReferenciado(1001, TabelaEnum.NFNOTAINFOPROCESSOREFERENCIADO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoProcessoReferenciado nfnotainfoprocessoreferenciadoResponse = getNFeBAR().fetchNFNotaInfoProcessoReferenciadoById(request);
		Assert.assertEquals(nfnotainfoprocessoreferenciadoResponse.getIdentificadorprocessoouatoconcessorio(), "identificadorProcessoOuAtoConcessorio_1");
		getNFeBAR().updateNFNotaInfoProcessoReferenciado(nfnotainfoprocessoreferenciado);
		nfnotainfoprocessoreferenciadoResponse = getNFeBAR().fetchNFNotaInfoProcessoReferenciadoById(request);
		Assert.assertEquals(nfnotainfoprocessoreferenciadoResponse.getIdentificadorprocessoouatoconcessorio(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoProcessoReferenciadosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoProcessoReferenciado> response = getNFeBAR().fetchNFNotaInfoProcessoReferenciadosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoProcessoReferenciadosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoProcessoReferenciado> response2 = getNFeBAR().fetchNFNotaInfoProcessoReferenciadosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoProcessoReferenciados();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoProcessoReferenciado> response3 = getNFeBAR().fetchNFNotaInfoProcessoReferenciadosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOEXPORTACAO ####======================================


@Test
	public void testDeleteNFNotaInfoExportacao()
	{
		NFNotaInfoExportacao nfnotainfoexportacao = Objects.insertNFNotaInfoExportacao(4, TabelaEnum.NFNOTAINFOEXPORTACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoExportacao nfnotainfoexportacaoResponse = getNFeBAR().fetchNFNotaInfoExportacaoById(request);
		Assert.assertEquals(nfnotainfoexportacaoResponse, null);
		getNFeBAR().insertNFNotaInfoExportacao(nfnotainfoexportacao);
		nfnotainfoexportacaoResponse = getNFeBAR().fetchNFNotaInfoExportacaoById(request);
		Assert.assertEquals(nfnotainfoexportacao.getId(), nfnotainfoexportacaoResponse.getId());
		getNFeBAR().deleteNFNotaInfoExportacaoById(nfnotainfoexportacao);
		nfnotainfoexportacaoResponse = getNFeBAR().fetchNFNotaInfoExportacaoById(request);
		Assert.assertEquals(nfnotainfoexportacaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoExportacaos()
	{
	NFNotaInfoExportacao nfnotainfoexportacao = new NFNotaInfoExportacao();
		List<NFNotaInfoExportacao> response = getNFeBAR().fetchAllNFNotaInfoExportacaos(nfnotainfoexportacao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoExportacaos()
	{
		getNFeBAR().deleteAllNFNotaInfoExportacaos();
	NFNotaInfoExportacao nfnotainfoexportacao = new NFNotaInfoExportacao();
		List<NFNotaInfoExportacao> response = getNFeBAR().fetchAllNFNotaInfoExportacaos(new NFNotaInfoExportacao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoExportacao()
	{
		NFNotaInfoExportacao nfnotainfoexportacao = Objects.insertNFNotaInfoExportacao(1001, TabelaEnum.NFNOTAINFOEXPORTACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoExportacao nfnotainfoexportacaoResponse = getNFeBAR().fetchNFNotaInfoExportacaoById(request);
		Assert.assertEquals(nfnotainfoexportacaoResponse.getLocaldespachoprodutos(), "localDespachoProdutos_1");
		getNFeBAR().updateNFNotaInfoExportacao(nfnotainfoexportacao);
		nfnotainfoexportacaoResponse = getNFeBAR().fetchNFNotaInfoExportacaoById(request);
		Assert.assertEquals(nfnotainfoexportacaoResponse.getLocaldespachoprodutos(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoExportacaosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoExportacao> response = getNFeBAR().fetchNFNotaInfoExportacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoExportacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoExportacao> response2 = getNFeBAR().fetchNFNotaInfoExportacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoExportacaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoExportacao> response3 = getNFeBAR().fetchNFNotaInfoExportacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOCOMPRA ####======================================


@Test
	public void testDeleteNFNotaInfoCompra()
	{
		NFNotaInfoCompra nfnotainfocompra = Objects.insertNFNotaInfoCompra(4, TabelaEnum.NFNOTAINFOCOMPRA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoCompra nfnotainfocompraResponse = getNFeBAR().fetchNFNotaInfoCompraById(request);
		Assert.assertEquals(nfnotainfocompraResponse, null);
		getNFeBAR().insertNFNotaInfoCompra(nfnotainfocompra);
		nfnotainfocompraResponse = getNFeBAR().fetchNFNotaInfoCompraById(request);
		Assert.assertEquals(nfnotainfocompra.getId(), nfnotainfocompraResponse.getId());
		getNFeBAR().deleteNFNotaInfoCompraById(nfnotainfocompra);
		nfnotainfocompraResponse = getNFeBAR().fetchNFNotaInfoCompraById(request);
		Assert.assertEquals(nfnotainfocompraResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoCompras()
	{
	NFNotaInfoCompra nfnotainfocompra = new NFNotaInfoCompra();
		List<NFNotaInfoCompra> response = getNFeBAR().fetchAllNFNotaInfoCompras(nfnotainfocompra).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoCompras()
	{
		getNFeBAR().deleteAllNFNotaInfoCompras();
	NFNotaInfoCompra nfnotainfocompra = new NFNotaInfoCompra();
		List<NFNotaInfoCompra> response = getNFeBAR().fetchAllNFNotaInfoCompras(new NFNotaInfoCompra()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoCompra()
	{
		NFNotaInfoCompra nfnotainfocompra = Objects.insertNFNotaInfoCompra(1001, TabelaEnum.NFNOTAINFOCOMPRA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoCompra nfnotainfocompraResponse = getNFeBAR().fetchNFNotaInfoCompraById(request);
		Assert.assertEquals(nfnotainfocompraResponse.getContrato(), "contrato_1");
		getNFeBAR().updateNFNotaInfoCompra(nfnotainfocompra);
		nfnotainfocompraResponse = getNFeBAR().fetchNFNotaInfoCompraById(request);
		Assert.assertEquals(nfnotainfocompraResponse.getContrato(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoComprasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoCompra> response = getNFeBAR().fetchNFNotaInfoComprasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoComprasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoCompra> response2 = getNFeBAR().fetchNFNotaInfoComprasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoCompras();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoCompra> response3 = getNFeBAR().fetchNFNotaInfoComprasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOCANA ####======================================


@Test
	public void testDeleteNFNotaInfoCana()
	{
		NFNotaInfoCana nfnotainfocana = Objects.insertNFNotaInfoCana(1400, TabelaEnum.NFNOTAINFOCANA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1400);
		NFNotaInfoCana nfnotainfocanaResponse = getNFeBAR().fetchNFNotaInfoCanaById(request);
		Assert.assertEquals(nfnotainfocanaResponse, null);
		getNFeBAR().insertNFNotaInfoCana(nfnotainfocana);
		nfnotainfocanaResponse = getNFeBAR().fetchNFNotaInfoCanaById(request);
		Assert.assertEquals(nfnotainfocana.getId(), nfnotainfocanaResponse.getId());
		nfnotainfocana = Objects.insertNFNotaInfoCana(1400, TabelaEnum.NFNOTAINFOCANA, PersistenceActionEnum.DELETE);
		getNFeBAR().deleteNFNotaInfoCanaById(nfnotainfocana);
		nfnotainfocanaResponse = getNFeBAR().fetchNFNotaInfoCanaById(request);
		Assert.assertEquals(nfnotainfocanaResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoCanas()
	{
	NFNotaInfoCana nfnotainfocana = new NFNotaInfoCana();
		List<NFNotaInfoCana> response = getNFeBAR().fetchAllNFNotaInfoCanas(nfnotainfocana).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoCanas()
	{
		getNFeBAR().deleteAllNFNotaInfoCanas();
	NFNotaInfoCana nfnotainfocana = new NFNotaInfoCana();
		List<NFNotaInfoCana> response = getNFeBAR().fetchAllNFNotaInfoCanas(new NFNotaInfoCana()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoCana()
	{
		NFNotaInfoCana nfnotainfocana = Objects.insertNFNotaInfoCana(1001, TabelaEnum.NFNOTAINFOCANA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoCana nfnotainfocanaResponse = getNFeBAR().fetchNFNotaInfoCanaById(request);
		Assert.assertEquals(nfnotainfocanaResponse.getQuantidadeTotalAnterior(), "quantidadeTotalAnterior_1");
		getNFeBAR().updateNFNotaInfoCana(nfnotainfocana);
		nfnotainfocanaResponse = getNFeBAR().fetchNFNotaInfoCanaById(request);
		Assert.assertEquals(nfnotainfocanaResponse.getQuantidadeTotalAnterior(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoCanasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoCana> response = getNFeBAR().fetchNFNotaInfoCanasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoCanasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoCana> response2 = getNFeBAR().fetchNFNotaInfoCanasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoCanas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoCana> response3 = getNFeBAR().fetchNFNotaInfoCanasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOCANAFORNECIMENTODIARIO ####======================================


@Test
	public void testDeleteNFNotaInfoCanaFornecimentoDiario()
	{
		NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario = Objects.insertNFNotaInfoCanaFornecimentoDiario(4, TabelaEnum.NFNOTAINFOCANAFORNECIMENTODIARIO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiarioResponse = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiarioById(request);
		Assert.assertEquals(nfnotainfocanafornecimentodiarioResponse, null);
		getNFeBAR().insertNFNotaInfoCanaFornecimentoDiario(nfnotainfocanafornecimentodiario);
		nfnotainfocanafornecimentodiarioResponse = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiarioById(request);
		Assert.assertEquals(nfnotainfocanafornecimentodiario.getId(), nfnotainfocanafornecimentodiarioResponse.getId());
		getNFeBAR().deleteNFNotaInfoCanaFornecimentoDiarioById(nfnotainfocanafornecimentodiario);
		nfnotainfocanafornecimentodiarioResponse = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiarioById(request);
		Assert.assertEquals(nfnotainfocanafornecimentodiarioResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoCanaFornecimentoDiarios()
	{
	NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario = new NFNotaInfoCanaFornecimentoDiario();
		List<NFNotaInfoCanaFornecimentoDiario> response = getNFeBAR().fetchAllNFNotaInfoCanaFornecimentoDiarios(nfnotainfocanafornecimentodiario).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoCanaFornecimentoDiarios()
	{
		getNFeBAR().deleteAllNFNotaInfoCanaFornecimentoDiarios();
	NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario = new NFNotaInfoCanaFornecimentoDiario();
		List<NFNotaInfoCanaFornecimentoDiario> response = getNFeBAR().fetchAllNFNotaInfoCanaFornecimentoDiarios(new NFNotaInfoCanaFornecimentoDiario()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoCanaFornecimentoDiario()
	{
		NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiario = Objects.insertNFNotaInfoCanaFornecimentoDiario(1001, TabelaEnum.NFNOTAINFOCANAFORNECIMENTODIARIO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoCanaFornecimentoDiario nfnotainfocanafornecimentodiarioResponse = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiarioById(request);
		Assert.assertEquals(nfnotainfocanafornecimentodiarioResponse.getCreateUser(), "system");
		getNFeBAR().updateNFNotaInfoCanaFornecimentoDiario(nfnotainfocanafornecimentodiario);
		nfnotainfocanafornecimentodiarioResponse = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiarioById(request);
		Assert.assertEquals(nfnotainfocanafornecimentodiarioResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNFNotaInfoCanaFornecimentoDiariosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> response = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> response2 = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiariosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoCanaFornecimentoDiarios();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoCanaFornecimentoDiario> response3 = getNFeBAR().fetchNFNotaInfoCanaFornecimentoDiariosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOCANADEDUCAO ####======================================


@Test
	public void testDeleteNFNotaInfoCanaDeducao()
	{
		NFNotaInfoCanaDeducao nfnotainfocanadeducao = Objects.insertNFNotaInfoCanaDeducao(4, TabelaEnum.NFNOTAINFOCANADEDUCAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoCanaDeducao nfnotainfocanadeducaoResponse = getNFeBAR().fetchNFNotaInfoCanaDeducaoById(request);
		Assert.assertEquals(nfnotainfocanadeducaoResponse, null);
		getNFeBAR().insertNFNotaInfoCanaDeducao(nfnotainfocanadeducao);
		nfnotainfocanadeducaoResponse = getNFeBAR().fetchNFNotaInfoCanaDeducaoById(request);
		Assert.assertEquals(nfnotainfocanadeducao.getId(), nfnotainfocanadeducaoResponse.getId());
		getNFeBAR().deleteNFNotaInfoCanaDeducaoById(nfnotainfocanadeducao);
		nfnotainfocanadeducaoResponse = getNFeBAR().fetchNFNotaInfoCanaDeducaoById(request);
		Assert.assertEquals(nfnotainfocanadeducaoResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoCanaDeducaos()
	{
	NFNotaInfoCanaDeducao nfnotainfocanadeducao = new NFNotaInfoCanaDeducao();
		List<NFNotaInfoCanaDeducao> response = getNFeBAR().fetchAllNFNotaInfoCanaDeducaos(nfnotainfocanadeducao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoCanaDeducaos()
	{
		getNFeBAR().deleteAllNFNotaInfoCanaDeducaos();
	NFNotaInfoCanaDeducao nfnotainfocanadeducao = new NFNotaInfoCanaDeducao();
		List<NFNotaInfoCanaDeducao> response = getNFeBAR().fetchAllNFNotaInfoCanaDeducaos(new NFNotaInfoCanaDeducao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoCanaDeducao()
	{
		NFNotaInfoCanaDeducao nfnotainfocanadeducao = Objects.insertNFNotaInfoCanaDeducao(1001, TabelaEnum.NFNOTAINFOCANADEDUCAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoCanaDeducao nfnotainfocanadeducaoResponse = getNFeBAR().fetchNFNotaInfoCanaDeducaoById(request);
		Assert.assertEquals(nfnotainfocanadeducaoResponse.getDescricaoDeducao(), "descricaoDeducao_1");
		getNFeBAR().updateNFNotaInfoCanaDeducao(nfnotainfocanadeducao);
		nfnotainfocanadeducaoResponse = getNFeBAR().fetchNFNotaInfoCanaDeducaoById(request);
		Assert.assertEquals(nfnotainfocanadeducaoResponse.getDescricaoDeducao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoCanaDeducaosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoCanaDeducao> response = getNFeBAR().fetchNFNotaInfoCanaDeducaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoCanaDeducaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoCanaDeducao> response2 = getNFeBAR().fetchNFNotaInfoCanaDeducaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoCanaDeducaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoCanaDeducao> response3 = getNFeBAR().fetchNFNotaInfoCanaDeducaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### NFNOTAINFOSUPLEMENTAR ####======================================


@Test
	public void testDeleteNFNotaInfoSuplementar()
	{
		NFNotaInfoSuplementar nfnotainfosuplementar = Objects.insertNFNotaInfoSuplementar(4, TabelaEnum.NFNOTAINFOSUPLEMENTAR, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		NFNotaInfoSuplementar nfnotainfosuplementarResponse = getNFeBAR().fetchNFNotaInfoSuplementarById(request);
		Assert.assertEquals(nfnotainfosuplementarResponse, null);
		getNFeBAR().insertNFNotaInfoSuplementar(nfnotainfosuplementar);
		nfnotainfosuplementarResponse = getNFeBAR().fetchNFNotaInfoSuplementarById(request);
		Assert.assertEquals(nfnotainfosuplementar.getId(), nfnotainfosuplementarResponse.getId());
		getNFeBAR().deleteNFNotaInfoSuplementarById(nfnotainfosuplementar);
		nfnotainfosuplementarResponse = getNFeBAR().fetchNFNotaInfoSuplementarById(request);
		Assert.assertEquals(nfnotainfosuplementarResponse, null);
	}

	@Test
	public void testFetchAllNFNotaInfoSuplementars()
	{
	NFNotaInfoSuplementar nfnotainfosuplementar = new NFNotaInfoSuplementar();
		List<NFNotaInfoSuplementar> response = getNFeBAR().fetchAllNFNotaInfoSuplementars(nfnotainfosuplementar).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNFNotaInfoSuplementars()
	{
		getNFeBAR().deleteAllNFNotaInfoSuplementars();
	NFNotaInfoSuplementar nfnotainfosuplementar = new NFNotaInfoSuplementar();
		List<NFNotaInfoSuplementar> response = getNFeBAR().fetchAllNFNotaInfoSuplementars(new NFNotaInfoSuplementar()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNFNotaInfoSuplementar()
	{
		NFNotaInfoSuplementar nfnotainfosuplementar = Objects.insertNFNotaInfoSuplementar(1001, TabelaEnum.NFNOTAINFOSUPLEMENTAR, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NFNotaInfoSuplementar nfnotainfosuplementarResponse = getNFeBAR().fetchNFNotaInfoSuplementarById(request);
		Assert.assertEquals(nfnotainfosuplementarResponse.getQrCode(), "qrCode_1");
		getNFeBAR().updateNFNotaInfoSuplementar(nfnotainfosuplementar);
		nfnotainfosuplementarResponse = getNFeBAR().fetchNFNotaInfoSuplementarById(request);
		Assert.assertEquals(nfnotainfosuplementarResponse.getQrCode(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNFNotaInfoSuplementarsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNotaInfoSuplementar> response = getNFeBAR().fetchNFNotaInfoSuplementarsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNFeBAR().fetchNFNotaInfoSuplementarsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNotaInfoSuplementar> response2 = getNFeBAR().fetchNFNotaInfoSuplementarsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNFeBAR().deleteAllNFNotaInfoSuplementars();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NFNotaInfoSuplementar> response3 = getNFeBAR().fetchNFNotaInfoSuplementarsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertNFNota.sql", false);
		executeSqlScript("conf/insertNFNotaInfo.sql", false);
		executeSqlScript("conf/insertNFNotaInfoIdentificacao.sql", false);
		executeSqlScript("conf/insertNFInfoModelo1Por1AReferenciada.sql", false);
		executeSqlScript("conf/insertNFInfoReferenciada.sql", false);
		executeSqlScript("conf/insertNFInfoProdutorRuralReferenciada.sql", false);
		executeSqlScript("conf/insertNFNotaInfoEmitente.sql", false);
		executeSqlScript("conf/insertNFNotaInfoAvulsa.sql", false);
		executeSqlScript("conf/insertNFNotaInfoDestinatario.sql", false);
		executeSqlScript("conf/insertNFNotaInfoLocal.sql", false);
		executeSqlScript("conf/insertNFPessoaAutorizadaDownloadNFe.sql", false);
		executeSqlScript("conf/insertNFNotaInfoTotal.sql", false);
		executeSqlScript("conf/insertNFNotaInfoICMSTotal.sql", false);
		executeSqlScript("conf/insertNFNotaInfoISSQNTotal.sql", false);
		executeSqlScript("conf/insertNFNotaInfoRetencoesTributos.sql", false);
		executeSqlScript("conf/insertNFNotaInfoTransporte.sql", false);
		executeSqlScript("conf/insertNFNotaInfoRetencaoICMSTransporte.sql", false);
		executeSqlScript("conf/insertNFNotaInfoTransportador.sql", false);
		executeSqlScript("conf/insertNFNotaInfoVeiculo.sql", false);
		executeSqlScript("conf/insertNFNotaInfoReboque.sql", false);
		executeSqlScript("conf/insertNFNotaInfoCobranca.sql", false);
		executeSqlScript("conf/insertNFNotaInfoDuplicata.sql", false);
		executeSqlScript("conf/insertNFNotaInfoFatura.sql", false);
		executeSqlScript("conf/insertNFNotaInfoCartao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoPagamento.sql", false);
		executeSqlScript("conf/insertNFNotaInfoInformacoesAdicionais.sql", false);
		executeSqlScript("conf/insertNFNotaInfoObservacao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoProcessoReferenciado.sql", false);
		executeSqlScript("conf/insertNFNotaInfoExportacao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoCompra.sql", false);
		executeSqlScript("conf/insertNFNotaInfoCana.sql", false);
		executeSqlScript("conf/insertNFNotaInfoCanaFornecimentoDiario.sql", false);
		executeSqlScript("conf/insertNFNotaInfoCanaDeducao.sql", false);
		executeSqlScript("conf/insertNFNotaInfoSuplementar.sql", false);
	}


}
