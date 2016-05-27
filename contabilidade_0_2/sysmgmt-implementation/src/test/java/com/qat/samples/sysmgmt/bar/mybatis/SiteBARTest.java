/** create by system gera-java version 1.0.0 07/05/2016 18:36 : 29*/
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
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoByServico;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoBySite;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.ContatoItens;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Preco;
import com.qat.samples.sysmgmt.produto.model.PrecoTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.criteria.SiteCriteria;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
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
public class SiteBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(SiteBARTest.class);
private ISiteBAR siteBAR; // injected by Spring through @Resource

@Resource
public void setSiteBAR(ISiteBAR siteBAR)
{
	this.siteBAR = siteBAR;
}

public ISiteBAR getSiteBAR()
{
	return siteBAR;
}


//===================================### SERVICO ####======================================


//@Test
//	public void testDeleteServico()
//	{
//		Servico servico = insertServico(4,TabelaEnum.SERVICO,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		Servico servicoResponse = getSiteBAR().fetchServicoById(request);
//		Assert.assertEquals(servicoResponse, null);
//		getSiteBAR().insertServico(servico);
//		servicoResponse = getSiteBAR().fetchServicoById(request);
//		Assert.assertEquals(servico.getId(), servicoResponse.getId());
//		Assert.assertEquals(servicoResponse.getPreco().size(), 1);
//		getSiteBAR().deleteServicoById(servico);
//		servicoResponse = getSiteBAR().fetchServicoById(request);
//		Assert.assertEquals(servicoResponse, null);
//	}
//
//	@Test
//	public void testFetchAllServicos()
//	{
//	Servico servico = new Servico();
//		List<Servico> response = getSiteBAR().fetchAllServicos(servico).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllServicos()
//	{
//		getSiteBAR().deleteAllServicos();
//		Servico servico = new Servico();
//		List<Servico> response = getSiteBAR().fetchAllServicos(new Servico()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateServico()
//	{
//		Servico servico = insertServico(1,TabelaEnum.SERVICO,PersistenceActionEnum.UPDATE);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		Servico servicoResponse = getSiteBAR().fetchServicoById(request);
//		Assert.assertEquals(servicoResponse.getNome(), "nome");
//		getSiteBAR().updateServico(servico);
//		servicoResponse = getSiteBAR().fetchServicoById(request);
//		Assert.assertEquals(servicoResponse.getNome(), "NATIVE INSERT UPDATE");
//		Assert.assertEquals(servicoResponse.getPreco().size(), 1);
//	}
//
//	@Test
//	public void testFetchServicosByRequest() throws Exception
//	{
//		// check for valid and precount
//		ServicoInquiryRequest request = new ServicoInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Servico> response = getSiteBAR().fetchServicosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getSiteBAR().fetchServicosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		ServicoInquiryRequest request2 = new ServicoInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Servico> response2 = getSiteBAR().fetchServicosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getSiteBAR().deleteAllServicos();
//		ServicoInquiryRequest request3 = new ServicoInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Servico> response3 = getSiteBAR().fetchServicosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
////===================================### SITE ####======================================
//
//
//@Test
//	public void testDeleteSite()
//	{
//		Site site = insertSite(4,TabelaEnum.SERVICO,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		Site siteResponse = getSiteBAR().fetchSiteById(request);
//		Assert.assertEquals(siteResponse, null);
//		getSiteBAR().insertSite(site);
//		siteResponse = getSiteBAR().fetchSiteById(request);
//		Assert.assertEquals(site.getId(), siteResponse.getId());
//		getSiteBAR().deleteSiteById(site);
//		siteResponse = getSiteBAR().fetchSiteById(request);
//	//	Assert.assertEquals(siteResponse, siteResponse.getId());
//	}
//
//	@Test
//	public void testFetchAllSites()
//	{
//	Site site = new Site();
//		List<Site> response = getSiteBAR().fetchAllSites(site).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllSites()
//	{
//		getSiteBAR().deleteAllSites();
//		Site site = new Site();
//		List<Site> response = getSiteBAR().fetchAllSites(new Site()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateSite()
//	{
//		Site site = insertSite(1,TabelaEnum.SITE,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		Site siteResponse = getSiteBAR().fetchSiteById(request);
//		Assert.assertEquals(siteResponse.getNome(), "nome_1");
//		getSiteBAR().updateSite(site);
//		siteResponse = getSiteBAR().fetchSiteById(request);
//		Assert.assertEquals(siteResponse.getNome(), "NATIVE INSERT UPDATE");
//	}

	@Test
	public void testFetchSitesByRequest() throws Exception
	{
		// check for valid and precount
		SiteInquiryRequest request = new SiteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(1);
		request.setUrl("http://localhost:8080/webSite/");
		InternalResultsResponse<Site> response = getSiteBAR().fetchSitesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		Assert.assertTrue(response.getResultsList().size() == 1);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSiteBAR().fetchSitesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		SiteInquiryRequest request2 = new SiteInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Site> response2 = getSiteBAR().fetchSitesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSiteBAR().deleteAllSites();
		SiteInquiryRequest request3 = new SiteInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Site> response3 = getSiteBAR().fetchSitesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONTATO ####======================================
//
//
@Test
	public void testDeleteContato()
	{
		Contato contato = insertContato(4,TabelaEnum.CONTATO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Contato contatoResponse = getSiteBAR().fetchContatoById(request);
		Assert.assertEquals(contatoResponse, null);
		getSiteBAR().insertContato(contato);
		contatoResponse = getSiteBAR().fetchContatoById(request);
		Assert.assertEquals(contato.getId(), contatoResponse.getId());
		getSiteBAR().deleteContatoById(contato);
		contatoResponse = getSiteBAR().fetchContatoById(request);
		Assert.assertEquals(contatoResponse, null);
	}
//
//	@Test
//	public void testFetchAllContatos()
//	{
//	Contato contato = new Contato();
//		List<Contato> response = getSiteBAR().fetchAllContatos(contato).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllContatos()
//	{
//		getSiteBAR().deleteAllContatos();
//	Contato contato = new Contato();
//		List<Contato> response = getSiteBAR().fetchAllContatos(new Contato()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateContato()
//	{
//		Contato contato = insertContato(1,TabelaEnum.CONTATO,PersistenceActionEnum.UPDATE);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		Contato contatoResponse = getSiteBAR().fetchContatoById(request);
//		Assert.assertEquals(contatoResponse.getNome(), "nome_3");
//		getSiteBAR().updateContato(contato);
//		contatoResponse = getSiteBAR().fetchContatoById(request);
//		Assert.assertEquals(contatoResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchContatosByRequest() throws Exception
//	{
//		// check for valid and precount
//		ContatoInquiryRequest request = new ContatoInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Contato> response = getSiteBAR().fetchContatosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getSiteBAR().fetchContatosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		ContatoInquiryRequest request2 = new ContatoInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Contato> response2 = getSiteBAR().fetchContatosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getSiteBAR().deleteAllContatos();
//		ContatoInquiryRequest request3 = new ContatoInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Contato> response3 = getSiteBAR().fetchContatosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
////===================================### CONTATOITENS ####======================================
//
//
//@Test
//	public void testDeleteContatoItens()
//	{
//		ContatoItens contatoitens = insertContatoItens(4,TabelaEnum.CONTATOITENS,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		ContatoItens contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
//		Assert.assertEquals(contatoitensResponse, null);
//		getSiteBAR().insertContatoItens(contatoitens);
//		contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
//		Assert.assertEquals(contatoitens.getId(), contatoitensResponse.getId());
//		getSiteBAR().deleteContatoItensById(contatoitens);
//		contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
//		Assert.assertEquals(contatoitensResponse, null);
//	}
//
//	@Test
//	public void testFetchAllContatoItenss()
//	{
//	ContatoItens contatoitens = new ContatoItens();
//		List<ContatoItens> response = getSiteBAR().fetchAllContatoItenss(contatoitens).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllContatoItenss()
//	{
//		getSiteBAR().deleteAllContatoItenss();
//	ContatoItens contatoitens = new ContatoItens();
//		List<ContatoItens> response = getSiteBAR().fetchAllContatoItenss(new ContatoItens()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateContatoItens()
//	{
//		ContatoItens contatoitens = insertContatoItens(1,TabelaEnum.CONTATOITENS,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		ContatoItens contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
//		Assert.assertEquals(contatoitensResponse.getTexto(), "texto_2");
//		getSiteBAR().updateContatoItens(contatoitens);
//		contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
//		Assert.assertEquals(contatoitensResponse.getTexto(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchContatoItenssByRequest() throws Exception
//	{
//		// check for valid and precount
//		PagedInquiryRequest request = new PagedInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<ContatoItens> response = getSiteBAR().fetchContatoItenssByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getSiteBAR().fetchContatoItenssByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		PagedInquiryRequest request2 = new PagedInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<ContatoItens> response2 = getSiteBAR().fetchContatoItenssByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getSiteBAR().deleteAllContatoItenss();
//		PagedInquiryRequest request3 = new PagedInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<ContatoItens> response3 = getSiteBAR().fetchContatoItenssByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
////===================================### ORDEMSERVICO ####======================================
//
//
//@Test
//	public void testDeleteOrdemServico()
//	{
//		OrdemServico ordemservico = insertOrdemServico(4,TabelaEnum.ORDEMSERVICO,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		OrdemServico ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
//		Assert.assertEquals(ordemservicoResponse, null);
//		getSiteBAR().insertOrdemServico(ordemservico);
//		ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
//		Assert.assertEquals(ordemservico.getId(), ordemservicoResponse.getId());
//		getSiteBAR().deleteOrdemServicoById(ordemservico);
//		ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
//		Assert.assertEquals(ordemservicoResponse, null);
//	}
//
//	@Test
//	public void testFetchAllOrdemServicos()
//	{
//	OrdemServico ordemservico = new OrdemServico();
//		List<OrdemServico> response = getSiteBAR().fetchAllOrdemServicos(ordemservico).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllOrdemServicos()
//	{
//		getSiteBAR().deleteAllOrdemServicos();
//	OrdemServico ordemservico = new OrdemServico();
//		List<OrdemServico> response = getSiteBAR().fetchAllOrdemServicos(new OrdemServico()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateOrdemServico()
//	{
//		OrdemServico ordemservico = insertOrdemServico(1,TabelaEnum.ORDEMSERVICO,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		OrdemServico ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
//		Assert.assertEquals(ordemservicoResponse.getNome(), "nome_3");
//		getSiteBAR().updateOrdemServico(ordemservico);
//		ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
//		Assert.assertEquals(ordemservicoResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchOrdemServicosByRequest() throws Exception
//	{
//		// check for valid and precount
//		OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<OrdemServico> response = getSiteBAR().fetchOrdemServicosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getSiteBAR().fetchOrdemServicosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		OrdemServicoInquiryRequest request2 = new OrdemServicoInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<OrdemServico> response2 = getSiteBAR().fetchOrdemServicosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getSiteBAR().deleteAllOrdemServicos();
//		OrdemServicoInquiryRequest request3 = new OrdemServicoInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<OrdemServico> response3 = getSiteBAR().fetchOrdemServicosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
////===================================### ORDEMSERVICOITENS ####======================================
//
//
//@Test
//	public void testDeleteOrdemServicoItens()
//	{
//		OrdemServicoItens ordemservicoitens = insertOrdemServicoItens(4,TabelaEnum.ORDEMSERVICOITENS,PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		OrdemServicoItens ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
//		Assert.assertEquals(ordemservicoitensResponse, null);
//		getSiteBAR().insertOrdemServicoItens(ordemservicoitens);
//		ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
//		Assert.assertEquals(ordemservicoitens.getId(), ordemservicoitensResponse.getId());
//		getSiteBAR().deleteOrdemServicoItensById(ordemservicoitens);
//		ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
//		Assert.assertEquals(ordemservicoitensResponse, null);
//	}
//
//	@Test
//	public void testFetchAllOrdemServicoItenss()
//	{
//	OrdemServicoItens ordemservicoitens = new OrdemServicoItens();
//		List<OrdemServicoItens> response = getSiteBAR().fetchAllOrdemServicoItenss(ordemservicoitens).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllOrdemServicoItenss()
//	{
//		getSiteBAR().deleteAllOrdemServicoItenss();
//	OrdemServicoItens ordemservicoitens = new OrdemServicoItens();
//		List<OrdemServicoItens> response = getSiteBAR().fetchAllOrdemServicoItenss(new OrdemServicoItens()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateOrdemServicoItens()
//	{
//		OrdemServicoItens ordemservicoitens = insertOrdemServicoItens(100001,TabelaEnum.ORDEMSERVICOITENS,PersistenceActionEnum.UPDATE);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(100001);
//		OrdemServicoItens ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
//		Assert.assertEquals(ordemservicoitensResponse.getTexto(), "texto_2");
//		getSiteBAR().updateOrdemServicoItens(ordemservicoitens);
//		ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
//		Assert.assertEquals(ordemservicoitensResponse.getTexto(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchOrdemServicoItenssByRequest() throws Exception
//	{
//		// check for valid and precount
//		PagedInquiryRequest request = new PagedInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<OrdemServicoItens> response = getSiteBAR().fetchOrdemServicoItenssByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getSiteBAR().fetchOrdemServicoItenssByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		PagedInquiryRequest request2 = new PagedInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<OrdemServicoItens> response2 = getSiteBAR().fetchOrdemServicoItenssByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getSiteBAR().deleteAllOrdemServicoItenss();
//		PagedInquiryRequest request3 = new PagedInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<OrdemServicoItens> response3 = getSiteBAR().fetchOrdemServicoItenssByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
////===================================### PLANO ####======================================
//
//
//@Test
//	public void testDeletePlano()
//	{
//		Plano plano = insertPlano(4, TabelaEnum.PLANO, PersistenceActionEnum.INSERT);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(4);
//		Plano planoResponse = getSiteBAR().fetchPlanoById(request);
//		Assert.assertEquals(planoResponse, null);
//		getSiteBAR().insertPlano(plano);
//		planoResponse = getSiteBAR().fetchPlanoById(request);
//		Assert.assertEquals(plano.getId(), planoResponse.getId());
//		getSiteBAR().deletePlanoById(plano);
//		planoResponse = getSiteBAR().fetchPlanoById(request);
//		Assert.assertEquals(planoResponse.getStatusList().get(planoResponse.getStatusList().size() -1).getStatus(), CdStatusTypeEnum.DELETADO);
//	}
//
//	@Test
//	public void testFetchAllPlanos()
//	{
//	Plano plano = new Plano();
//		List<Plano> response = getSiteBAR().fetchAllPlanos(plano).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllPlanos()
//	{
//		getSiteBAR().deleteAllPlanos();
//	Plano plano = new Plano();
//		List<Plano> response = getSiteBAR().fetchAllPlanos(new Plano()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdatePlano()
//	{
//		Plano plano = insertPlano(1, TabelaEnum.PLANO, PersistenceActionEnum.UPDATE);
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		Plano planoResponse = getSiteBAR().fetchPlanoById(request);
//		Assert.assertEquals(planoResponse.getTitulo(), "titulo_5");
//		getSiteBAR().updatePlano(plano);
//		planoResponse = getSiteBAR().fetchPlanoById(request);
//		Assert.assertEquals(planoResponse.getTitulo(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchPlanosByRequest() throws Exception
//	{
//		// check for valid and precount
//		PlanoInquiryRequest request = new PlanoInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		SiteCriteria criteria = new SiteCriteria();
//		criteria.setUrl("URL 2");
//	//	request.setCriteria(criteria);
//		InternalResultsResponse<Plano> response = getSiteBAR().fetchPlanosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getSiteBAR().fetchPlanosByRequest(request);
//		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		PlanoInquiryRequest request2 = new PlanoInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Plano> response2 = getSiteBAR().fetchPlanosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getSiteBAR().deleteAllPlanos();
//		PlanoInquiryRequest request3 = new PlanoInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Plano> response3 = getSiteBAR().fetchPlanosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}

//	@Before
//	public void setup()
//	{
//		executeSqlScript("conf/insertServico.sql", false);
//		executeSqlScript("conf/insertSite.sql", false);
//		executeSqlScript("conf/insertContato.sql", false);
//		executeSqlScript("conf/insertContatoItens.sql", false);
//		executeSqlScript("conf/insertOrdemServico.sql", false);
//		executeSqlScript("conf/insertOrdemServicoItens.sql", false);
//		executeSqlScript("conf/insertPlano.sql", false);
//	}

	public Servico insertServico(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Servico servico = new Servico();
		Date a = new Date();
		if(id != null){
			servico.setId(id);
		}

		servico.setNome("NATIVE INSERT UPDATE");
		servico.setDescricao("descricao");
		servico.setPreco(new ArrayList<Preco>());
		servico.getPreco().add(insertPreco(id, TabelaEnum.SERVICO, action));
		servico.setParentId(id);
		servico.setEmprId(1);
		servico.setModifyDateUTC(a.getTime());
		servico.setCreateDateUTC(a.getTime());
		servico.setCreateUser("system");
		servico.setModifyUser("system");
		servico.setProcessId(1);
		servico.setModelAction(action);

		return servico;
	}

	public Preco insertPreco(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Preco servico = new Preco();
		Date a = new Date();

		servico.setDataMarcacao(a.getTime());
		servico.setParentId(id);
		servico.setTabelaEnum(tabela);
		servico.setPrecoTypeEnum(PrecoTypeEnum.COMPRA);
		servico.setValor(new Double(1.99));
		servico.setDataProInicial(a.getTime());
		servico.setDataProFinal(a.getTime());
		servico.setMaxVendProd(10);
		servico.setEmprId(1);
		servico.setModifyDateUTC(a.getTime());
		servico.setCreateDateUTC(a.getTime());
		servico.setCreateUser("system");
		servico.setModifyUser("system");
		servico.setProcessId(1);
		servico.setModelAction(action);

		return servico;
	}

public PlanoByServico insertServicoByPlano(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
	PlanoByServico servicobyplano = new PlanoByServico();
		Date a = new Date();

		servicobyplano.setServico(insertServico(id, tabela, action));
		servicobyplano.setParentId(id);
		servicobyplano.setEmprId(1);
		servicobyplano.setModifyDateUTC(a.getTime());
		servicobyplano.setCreateDateUTC(a.getTime());
		servicobyplano.setCreateUser("system");
		servicobyplano.setModifyUser("system");
		servicobyplano.setProcessId(1);
		servicobyplano.setModelAction(action);

		return servicobyplano;
	}


public Site insertSite(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Site site = new Site();
		Date a = new Date();
site.setId(id);
site.setNome("NATIVE INSERT UPDATE");
site.setUrl("url");
site.setQuemSomos("quemSomos");
site.setMissao("missao");
site.setVisao("visao");
site.setTitulo("titulo");
site.setLogo("logo");
site.setAtorization(Boolean.TRUE);
site.setSiteTypeEnumValue(1);
site.setEmpresa(new Empresa());
site.setServicoList(new ArrayList<Servico>());
site.setPlanoList(new ArrayList<Plano>());
		site.setParentId(id);
		site.setEmprId(1);
		site.setModifyDateUTC(a.getTime());
		site.setCreateDateUTC(a.getTime());
		site.setCreateUser("system");
		site.setModifyUser("system");
		site.setProcessId(1);
		site.setModelAction(action);

		return site;
	}


public Contato insertContato(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Contato contato = new Contato();
		Date a = new Date();
contato.setId(id);
contato.setParentId(id);
contato.setDataContato(a.getTime());
contato.setNome("NATIVE INSERT UPDATE");
contato.setMotivoValue(100);
contato.setContatoItensList(new ArrayList<ContatoItens>());
		contato.setParentId(id);
		contato.setEmprId(1);
		contato.setModifyDateUTC(a.getTime());
		contato.setCreateDateUTC(a.getTime());
		contato.setCreateUser("system");
		contato.setModifyUser("system");
		contato.setProcessId(1);
		contato.setModelAction(action);

		return contato;
	}


public ContatoItens insertContatoItens(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		ContatoItens contatoitens = new ContatoItens();
		Date a = new Date();
contatoitens.setId(id);
contatoitens.setDataAlt(a.getTime());
contatoitens.setTexto("NATIVE INSERT UPDATE");
contatoitens.setTitulo("NATIVE INSERT UPDATE");
		contatoitens.setParentId(id);
		contatoitens.setEmprId(1);
		contatoitens.setModifyDateUTC(a.getTime());
		contatoitens.setCreateDateUTC(a.getTime());
		contatoitens.setCreateUser("system");
		contatoitens.setModifyUser("system");
		contatoitens.setProcessId(1);
		contatoitens.setModelAction(action);

		return contatoitens;
	}


public OrdemServico insertOrdemServico(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		OrdemServico ordemservico = new OrdemServico();
		Date a = new Date();
ordemservico.setId(id);
ordemservico.setEmprId(4);
ordemservico.setUserId("100");
ordemservico.setNome("NATIVE INSERT UPDATE");
ordemservico.setData(a.getTime());
ordemservico.setAssunto("assunto");
ordemservico.setStatusValue(100);
ordemservico.setOrdemServicoItensList(new ArrayList<OrdemServicoItens>());
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


public OrdemServicoItens insertOrdemServicoItens(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		OrdemServicoItens ordemservicoitens = new OrdemServicoItens();
		Date a = new Date();
ordemservicoitens.setId(id);
ordemservicoitens.setData(a.getTime());
ordemservicoitens.setTexto("NATIVE INSERT UPDATE");
		ordemservicoitens.setParentId(id);
		ordemservicoitens.setEmprId(1);
		ordemservicoitens.setModifyDateUTC(a.getTime());
		ordemservicoitens.setCreateDateUTC(a.getTime());
		ordemservicoitens.setCreateUser("system");
		ordemservicoitens.setModifyUser("system");
		ordemservicoitens.setProcessId(1);
		ordemservicoitens.setModelAction(action);

		return ordemservicoitens;
	}


public Plano insertPlano(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Plano plano = new Plano();
		Date a = new Date();
plano.setId(id);
plano.setDataInicio(a.getTime());
plano.setDataFinal(a.getTime());
plano.setNumeroContrato(100);
plano.setDescricao("descricao");
plano.setTitulo("NATIVE INSERT UPDATE");
plano.setPrecoList(new ArrayList<Preco>());
plano.getPrecoList().add(insertPreco(id, TabelaEnum.PLANO, action));
plano.setServicoList(new ArrayList<PlanoByServico>());
plano.getServicoList().add(insertServicoByPlano(id, TabelaEnum.PLANO, action));
		plano.setParentId(id);
		plano.setEmprId(1);
		plano.setModifyDateUTC(a.getTime());
		plano.setCreateDateUTC(a.getTime());
		plano.setCreateUser("system");
		plano.setModifyUser("system");
		plano.setProcessId(1);
		plano.setModelAction(action);

		return plano;
	}


}
