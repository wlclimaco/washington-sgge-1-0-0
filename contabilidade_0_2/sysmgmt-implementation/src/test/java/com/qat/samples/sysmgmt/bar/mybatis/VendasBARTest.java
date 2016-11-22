/** create by system gera-java version 1.0.0 27/09/2016 12:44 : 26*/
package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.ArrayList;
import java.util.Date;
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
import com.qat.samples.sysmgmt.bar.Vendas.IVendasBAR;
import com.qat.samples.sysmgmt.nf.model.ConhecimentoTransporte;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalItens;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
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
public class VendasBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(VendasBARTest.class);
private IVendasBAR vendasBAR; // injected by Spring through @Resource

@Resource
public void setVendasBAR(IVendasBAR vendasBAR)
{
	this.vendasBAR = vendasBAR;
}

public IVendasBAR getVendasBAR()
{
	return vendasBAR;
}


//===================================### NOTAFISCALSAIDA ####======================================


@Test
	public void testDeleteNotaFiscalSaida()
	{
		NFNota notafiscalsaida = Objects.insertNFNota(4001, TabelaEnum.NOTAFISCALSAIDA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4001);
		NFNota notafiscalsaidaResponse = getVendasBAR().fetchNotaFiscalSaidaById(request);
		Assert.assertEquals(notafiscalsaidaResponse, null);
		getVendasBAR().insertNotaFiscalSaida(notafiscalsaida);
		notafiscalsaidaResponse = getVendasBAR().fetchNotaFiscalSaidaById(request);
		Assert.assertEquals(notafiscalsaida.getId(), notafiscalsaidaResponse.getId());
		notafiscalsaida = Objects.insertNFNota(4001, TabelaEnum.NOTAFISCALSAIDA, PersistenceActionEnum.DELETE);
		getVendasBAR().deleteNotaFiscalSaidaById(notafiscalsaida);
		notafiscalsaidaResponse = getVendasBAR().fetchNotaFiscalSaidaById(request);
		Assert.assertEquals(notafiscalsaidaResponse, null);
	}

	@Test
	public void testFetchAllNotaFiscalSaidas()
	{
		NFNota notafiscalsaida = new NFNota();
		List<NFNota> response = getVendasBAR().fetchAllNotaFiscalSaidas(notafiscalsaida).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNotaFiscalSaidas()
	{
		getVendasBAR().deleteAllNotaFiscalSaidas();
		NFNota notafiscalsaida = new NFNota();
		List<NFNota> response = getVendasBAR().fetchAllNotaFiscalSaidas(new NFNota()).getResultsList();
		Assert.assertEquals(response.size(), 7);
	}

	@Test
	public void testUpdateNotaFiscalSaida()
	{
		NFNota notafiscalsaida = Objects.insertNFNota(1000, TabelaEnum.NOTAFISCALSAIDA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		NFNota notafiscalsaidaResponse = getVendasBAR().fetchNotaFiscalSaidaById(request);
		Assert.assertEquals(notafiscalsaidaResponse.getCreateUser(), "system");
		getVendasBAR().updateNotaFiscalSaida(notafiscalsaida);
		notafiscalsaidaResponse = getVendasBAR().fetchNotaFiscalSaidaById(request);
		Assert.assertEquals(notafiscalsaidaResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchNotaFiscalSaidasByRequest() throws Exception
	{
		// check for valid and precount
		NFNotaInquiryRequest request = new NFNotaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NFNota> response = getVendasBAR().fetchNotaFiscalSaidasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getVendasBAR().fetchNotaFiscalSaidasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		NFNotaInquiryRequest request2 = new NFNotaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NFNota> response2 = getVendasBAR().fetchNotaFiscalSaidasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
//		getVendasBAR().deleteAllNotaFiscalSaidas();
//		NFNotaInquiryRequest request3 = new NFNotaInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<NFNota> response3 = getVendasBAR().fetchNotaFiscalSaidasByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ORDEMSERVICO ####======================================


@Test
	public void testDeleteOrdemServico()
	{
		OrdemServico ordemservico = insertOrdemServico(4, TabelaEnum.ORDEMSERVICO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		OrdemServico ordemservicoResponse = getVendasBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse, null);
		getVendasBAR().insertOrdemServico(ordemservico);
		ordemservicoResponse = getVendasBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservico.getId(), ordemservicoResponse.getId());
		getVendasBAR().deleteOrdemServicoById(ordemservico);
		ordemservicoResponse = getVendasBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse, null);
	}

	@Test
	public void testFetchAllOrdemServicos()
	{
	OrdemServico ordemservico = new OrdemServico();
		List<OrdemServico> response = getVendasBAR().fetchAllOrdemServicos(ordemservico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllOrdemServicos()
	{
		getVendasBAR().deleteAllOrdemServicos();
	OrdemServico ordemservico = new OrdemServico();
		List<OrdemServico> response = getVendasBAR().fetchAllOrdemServicos(new OrdemServico()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateOrdemServico()
	{
		OrdemServico ordemservico = insertOrdemServico(1001, TabelaEnum.ORDEMSERVICO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		OrdemServico ordemservicoResponse = getVendasBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse.getAssunto(), "assunto_1");
		getVendasBAR().updateOrdemServico(ordemservico);
		ordemservicoResponse = getVendasBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse.getAssunto(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchOrdemServicosByRequest() throws Exception
	{
		// check for valid and precount
		OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<OrdemServico> response = getVendasBAR().fetchOrdemServicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getVendasBAR().fetchOrdemServicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		OrdemServicoInquiryRequest request2 = new OrdemServicoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<OrdemServico> response2 = getVendasBAR().fetchOrdemServicosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getVendasBAR().deleteAllOrdemServicos();
		OrdemServicoInquiryRequest request3 = new OrdemServicoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<OrdemServico> response3 = getVendasBAR().fetchOrdemServicosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}


//===================================### NOTAFISCALITENS ####======================================


@Test
	public void testDeleteNotaFiscalItens()
	{
		NotaFiscalItens notafiscalitens = Objects.insertNotaFiscalItens(40001, TabelaEnum.NOTAFISCALITENS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(40001);
		NotaFiscalItens notafiscalitensResponse = getVendasBAR().fetchNotaFiscalItensById(request);
		Assert.assertEquals(notafiscalitensResponse, null);
		getVendasBAR().insertNotaFiscalItens(notafiscalitens);
		notafiscalitensResponse = getVendasBAR().fetchNotaFiscalItensById(request);
		Assert.assertEquals(notafiscalitens.getId(), notafiscalitensResponse.getId());
		getVendasBAR().deleteNotaFiscalItensById(notafiscalitens);
		notafiscalitensResponse = getVendasBAR().fetchNotaFiscalItensById(request);
		Assert.assertEquals(notafiscalitensResponse, null);
	}

	@Test
	public void testFetchAllNotaFiscalItenss()
	{
	NotaFiscalItens notafiscalitens = new NotaFiscalItens();
		List<NotaFiscalItens> response = getVendasBAR().fetchAllNotaFiscalItenss(notafiscalitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNotaFiscalItenss()
	{
		getVendasBAR().deleteAllNotaFiscalItenss();
	NotaFiscalItens notafiscalitens = new NotaFiscalItens();
		List<NotaFiscalItens> response = getVendasBAR().fetchAllNotaFiscalItenss(new NotaFiscalItens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNotaFiscalItens()
	{
		NotaFiscalItens notafiscalitens = Objects.insertNotaFiscalItens(1001, TabelaEnum.NOTAFISCALITENS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		NotaFiscalItens notafiscalitensResponse = getVendasBAR().fetchNotaFiscalItensById(request);
		Assert.assertEquals(notafiscalitensResponse.getCnpj(), "cnpj_1");
		getVendasBAR().updateNotaFiscalItens(notafiscalitens);
		notafiscalitensResponse = getVendasBAR().fetchNotaFiscalItensById(request);
		Assert.assertEquals(notafiscalitensResponse.getCnpj(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNotaFiscalItenssByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<NotaFiscalItens> response = getVendasBAR().fetchNotaFiscalItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getVendasBAR().fetchNotaFiscalItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<NotaFiscalItens> response2 = getVendasBAR().fetchNotaFiscalItenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getVendasBAR().deleteAllNotaFiscalItenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<NotaFiscalItens> response3 = getVendasBAR().fetchNotaFiscalItenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertNotaFiscalSaida.sql", false);
		executeSqlScript("conf/insertOrcamento.sql", false);
		executeSqlScript("conf/insertOrdemServico.sql", false);
		executeSqlScript("conf/insertConhecimentoTransporte.sql", false);
		executeSqlScript("conf/insertNotaFiscalItens.sql", false);
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


	public NotaFiscalSaida insertNotaFiscalSaida(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			NotaFiscalSaida notafiscalsaida = new NotaFiscalSaida();
			Date a = new Date();
			notafiscalsaida.setId(id);
			notafiscalsaida.setParentId(id);
			notafiscalsaida.setEmprId(1);
			notafiscalsaida.setSerie("NATIVE INSERT UPDATE");
			notafiscalsaida.setModifyDateUTC(a.getTime());
			notafiscalsaida.setCreateDateUTC(a.getTime());
			notafiscalsaida.setCreateUser("system");
			notafiscalsaida.setModifyUser("system");
			notafiscalsaida.setProcessId(1);
			notafiscalsaida.setModelAction(action);

			return notafiscalsaida;
		}


	public Orcamento insertOrcamento(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Orcamento orcamento = new Orcamento();
			Date a = new Date();
			orcamento.setId(id);
			orcamento.setParentId(id);
			orcamento.setEmprId(1);
			orcamento.setTipo(2);
			orcamento.setSerie("NATIVE INSERT UPDATE");
			orcamento.setModifyDateUTC(a.getTime());
			orcamento.setCreateDateUTC(a.getTime());
			orcamento.setCreateUser("system");
			orcamento.setModifyUser("system");
			orcamento.setProcessId(1);
			orcamento.setModelAction(action);

			return orcamento;
		}


	public OrdemServico insertOrdemServico(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			OrdemServico ordemservico = new OrdemServico();
			Date a = new Date();
			ordemservico.setId(id);
			ordemservico.setUserId("NATIVE INSERT UPDATE");
			ordemservico.setNome("NATIVE INSERT UPDATE");
			ordemservico.setData(a.getTime());
			ordemservico.setAssunto("NATIVE INSERT UPDATE");
			ordemservico.setStatusValue(100);
			ordemservico.setOrdemServicoItensList(new ArrayList<OrdemServicoItens>());
			ordemservico.getOrdemServicoItensList().add(new OrdemServicoItens());
			ordemservico.setParentId(id);
			ordemservico.setEmprId(1);
			ordemservico.setModifyDateUTC(a.getTime());
			ordemservico.setCreateDateUTC(a.getTime());
			ordemservico.setCreateUser("system");
			ordemservico.setModifyUser("system");
			ordemservico.setProcessId(1);
			ordemservico.setModelAction(action);

			return ordemservico;
		}


	public ConhecimentoTransporte insertConhecimentoTransporte(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConhecimentoTransporte conhecimentotransporte = new ConhecimentoTransporte();
			Date a = new Date();
			conhecimentotransporte.setId(id);
			conhecimentotransporte.setIdNota(100);
			conhecimentotransporte.setRemetente(100);
			conhecimentotransporte.setVrTotalMercadorias(new Double(10.00));
			conhecimentotransporte.setApCreIcms(100);
			conhecimentotransporte.setFretePorConta(100);
			conhecimentotransporte.setPlaca("NATIVE INSERT UPDATE");
			conhecimentotransporte.setEspecie(100);
			conhecimentotransporte.setVolume(new Double(10.00));
			conhecimentotransporte.setPesoLiquido(new Double(10.00));
			conhecimentotransporte.setPesoBruto(new Double(10.00));
	//		conhecimentotransporte.setTransportador(10000);
		//	conhecimentotransporte.setMarca(10000);
		//	conhecimentotransporte.setEstado(10000);
			conhecimentotransporte.setRegistroNacionalTransportadorCarga("NATIVE INSERT UPDATE");
			conhecimentotransporte.setVagao("NATIVE INSERT UPDATE");
			conhecimentotransporte.setBalsa("NATIVE INSERT UPDATE");
			conhecimentotransporte.setParentId(id);
			conhecimentotransporte.setEmprId(1);
			conhecimentotransporte.setModifyDateUTC(a.getTime());
			conhecimentotransporte.setCreateDateUTC(a.getTime());
			conhecimentotransporte.setCreateUser("system");
			conhecimentotransporte.setModifyUser("system");
			conhecimentotransporte.setProcessId(1);
			conhecimentotransporte.setModelAction(action);

			return conhecimentotransporte;
		}





}
