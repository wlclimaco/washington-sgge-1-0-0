/** create by system gera-java version 1.0.0 07/05/2016 18:36 : 29*/
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

import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.ContatoItens;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServicoItens;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
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


@Test
	public void testDeleteServico()
	{
		Servico servico = new Servico(4, "Servico_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Servico servicoResponse = getSiteBAR().fetchServicoById(request);
		Assert.assertEquals(servicoResponse, null);
		getSiteBAR().insertServico(servico);
		servicoResponse = getSiteBAR().fetchServicoById(request);
		Assert.assertEquals(servico.getId(), servicoResponse.getId());
		getSiteBAR().deleteServicoById(servico);
		servicoResponse = getSiteBAR().fetchServicoById(request);
		Assert.assertEquals(servicoResponse, null);
	}

	@Test
	public void testFetchAllServicos()
	{
	Servico servico = new Servico();
		List<Servico> response = getSiteBAR().fetchAllServicos(servico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllServicos()
	{
		getSiteBAR().deleteAllServicos();
	Servico servico = new Servico();
		List<Servico> response = getSiteBAR().fetchAllServicos(new Servico()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateServico()
	{
		Servico servico = new Servico(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Servico servicoResponse = getSiteBAR().fetchServicoById(request);
		Assert.assertEquals(servicoResponse.getNome(), "NATIVE INSERT");
		getSiteBAR().updateServico(servico);
		servicoResponse = getSiteBAR().fetchServicoById(request);
		Assert.assertEquals(servicoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchServicosByRequest() throws Exception
	{
		// check for valid and precount
		ServicoInquiryRequest request = new ServicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Servico> response = getSiteBAR().fetchServicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSiteBAR().fetchServicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ServicoInquiryRequest request2 = new ServicoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Servico> response2 = getSiteBAR().fetchServicosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSiteBAR().deleteAllServicos();
		ServicoInquiryRequest request3 = new ServicoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Servico> response3 = getSiteBAR().fetchServicosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### SITE ####======================================


@Test
	public void testDeleteSite()
	{
		Site site = new Site(4, "Site_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Site siteResponse = getSiteBAR().fetchSiteById(request);
		Assert.assertEquals(siteResponse, null);
		getSiteBAR().insertSite(site);
		siteResponse = getSiteBAR().fetchSiteById(request);
		Assert.assertEquals(site.getId(), siteResponse.getId());
		getSiteBAR().deleteSiteById(site);
		siteResponse = getSiteBAR().fetchSiteById(request);
		Assert.assertEquals(siteResponse, null);
	}

	@Test
	public void testFetchAllSites()
	{
	Site site = new Site();
		List<Site> response = getSiteBAR().fetchAllSites(site).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllSites()
	{
		getSiteBAR().deleteAllSites();
	Site site = new Site();
		List<Site> response = getSiteBAR().fetchAllSites(new Site()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateSite()
	{
		Site site = new Site(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Site siteResponse = getSiteBAR().fetchSiteById(request);
		Assert.assertEquals(siteResponse.getNome(), "NATIVE INSERT");
		getSiteBAR().updateSite(site);
		siteResponse = getSiteBAR().fetchSiteById(request);
		Assert.assertEquals(siteResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchSitesByRequest() throws Exception
	{
		// check for valid and precount
		SiteInquiryRequest request = new SiteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Site> response = getSiteBAR().fetchSitesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
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


@Test
	public void testDeleteContato()
	{
		Contato contato = new Contato(4, "Contato_999");
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

	@Test
	public void testFetchAllContatos()
	{
	Contato contato = new Contato();
		List<Contato> response = getSiteBAR().fetchAllContatos(contato).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllContatos()
	{
		getSiteBAR().deleteAllContatos();
	Contato contato = new Contato();
		List<Contato> response = getSiteBAR().fetchAllContatos(new Contato()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateContato()
	{
		Contato contato = new Contato(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Contato contatoResponse = getSiteBAR().fetchContatoById(request);
		Assert.assertEquals(contatoResponse.getNome(), "NATIVE INSERT");
		getSiteBAR().updateContato(contato);
		contatoResponse = getSiteBAR().fetchContatoById(request);
		Assert.assertEquals(contatoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchContatosByRequest() throws Exception
	{
		// check for valid and precount
		ContatoInquiryRequest request = new ContatoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Contato> response = getSiteBAR().fetchContatosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSiteBAR().fetchContatosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ContatoInquiryRequest request2 = new ContatoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Contato> response2 = getSiteBAR().fetchContatosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSiteBAR().deleteAllContatos();
		ContatoInquiryRequest request3 = new ContatoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Contato> response3 = getSiteBAR().fetchContatosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONTATOITENS ####======================================


@Test
	public void testDeleteContatoItens()
	{
		ContatoItens contatoitens = new ContatoItens(4, "ContatoItens_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ContatoItens contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
		Assert.assertEquals(contatoitensResponse, null);
		getSiteBAR().insertContatoItens(contatoitens);
		contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
		Assert.assertEquals(contatoitens.getId(), contatoitensResponse.getId());
		getSiteBAR().deleteContatoItensById(contatoitens);
		contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
		Assert.assertEquals(contatoitensResponse, null);
	}

	@Test
	public void testFetchAllContatoItenss()
	{
	ContatoItens contatoitens = new ContatoItens();
		List<ContatoItens> response = getSiteBAR().fetchAllContatoItenss(contatoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllContatoItenss()
	{
		getSiteBAR().deleteAllContatoItenss();
	ContatoItens contatoitens = new ContatoItens();
		List<ContatoItens> response = getSiteBAR().fetchAllContatoItenss(new ContatoItens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateContatoItens()
	{
		ContatoItens contatoitens = new ContatoItens(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		ContatoItens contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
		Assert.assertEquals(contatoitensResponse.getTexto(), "NATIVE INSERT");
		getSiteBAR().updateContatoItens(contatoitens);
		contatoitensResponse = getSiteBAR().fetchContatoItensById(request);
		Assert.assertEquals(contatoitensResponse.getTexto(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchContatoItenssByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ContatoItens> response = getSiteBAR().fetchContatoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSiteBAR().fetchContatoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ContatoItens> response2 = getSiteBAR().fetchContatoItenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSiteBAR().deleteAllContatoItenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ContatoItens> response3 = getSiteBAR().fetchContatoItenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ORDEMSERVICO ####======================================


@Test
	public void testDeleteOrdemServico()
	{
		OrdemServico ordemservico = new OrdemServico(4, "OrdemServico_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		OrdemServico ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse, null);
		getSiteBAR().insertOrdemServico(ordemservico);
		ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservico.getId(), ordemservicoResponse.getId());
		getSiteBAR().deleteOrdemServicoById(ordemservico);
		ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse, null);
	}

	@Test
	public void testFetchAllOrdemServicos()
	{
	OrdemServico ordemservico = new OrdemServico();
		List<OrdemServico> response = getSiteBAR().fetchAllOrdemServicos(ordemservico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllOrdemServicos()
	{
		getSiteBAR().deleteAllOrdemServicos();
	OrdemServico ordemservico = new OrdemServico();
		List<OrdemServico> response = getSiteBAR().fetchAllOrdemServicos(new OrdemServico()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateOrdemServico()
	{
		OrdemServico ordemservico = new OrdemServico(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		OrdemServico ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse.getNome(), "NATIVE INSERT");
		getSiteBAR().updateOrdemServico(ordemservico);
		ordemservicoResponse = getSiteBAR().fetchOrdemServicoById(request);
		Assert.assertEquals(ordemservicoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchOrdemServicosByRequest() throws Exception
	{
		// check for valid and precount
		OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<OrdemServico> response = getSiteBAR().fetchOrdemServicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSiteBAR().fetchOrdemServicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		OrdemServicoInquiryRequest request2 = new OrdemServicoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<OrdemServico> response2 = getSiteBAR().fetchOrdemServicosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSiteBAR().deleteAllOrdemServicos();
		OrdemServicoInquiryRequest request3 = new OrdemServicoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<OrdemServico> response3 = getSiteBAR().fetchOrdemServicosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ORDEMSERVICOITENS ####======================================


@Test
	public void testDeleteOrdemServicoItens()
	{
		OrdemServicoItens ordemservicoitens = new OrdemServicoItens(4, "OrdemServicoItens_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		OrdemServicoItens ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
		Assert.assertEquals(ordemservicoitensResponse, null);
		getSiteBAR().insertOrdemServicoItens(ordemservicoitens);
		ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
		Assert.assertEquals(ordemservicoitens.getId(), ordemservicoitensResponse.getId());
		getSiteBAR().deleteOrdemServicoItensById(ordemservicoitens);
		ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
		Assert.assertEquals(ordemservicoitensResponse, null);
	}

	@Test
	public void testFetchAllOrdemServicoItenss()
	{
	OrdemServicoItens ordemservicoitens = new OrdemServicoItens();
		List<OrdemServicoItens> response = getSiteBAR().fetchAllOrdemServicoItenss(ordemservicoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllOrdemServicoItenss()
	{
		getSiteBAR().deleteAllOrdemServicoItenss();
	OrdemServicoItens ordemservicoitens = new OrdemServicoItens();
		List<OrdemServicoItens> response = getSiteBAR().fetchAllOrdemServicoItenss(new OrdemServicoItens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateOrdemServicoItens()
	{
		OrdemServicoItens ordemservicoitens = new OrdemServicoItens(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		OrdemServicoItens ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
		Assert.assertEquals(ordemservicoitensResponse.getTexto(), "NATIVE INSERT");
		getSiteBAR().updateOrdemServicoItens(ordemservicoitens);
		ordemservicoitensResponse = getSiteBAR().fetchOrdemServicoItensById(request);
		Assert.assertEquals(ordemservicoitensResponse.getTexto(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchOrdemServicoItenssByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<OrdemServicoItens> response = getSiteBAR().fetchOrdemServicoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSiteBAR().fetchOrdemServicoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<OrdemServicoItens> response2 = getSiteBAR().fetchOrdemServicoItenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSiteBAR().deleteAllOrdemServicoItenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<OrdemServicoItens> response3 = getSiteBAR().fetchOrdemServicoItenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### PLANO ####======================================


@Test
	public void testDeletePlano()
	{
		Plano plano = new Plano(4, "Plano_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Plano planoResponse = getSiteBAR().fetchPlanoById(request);
		Assert.assertEquals(planoResponse, null);
		getSiteBAR().insertPlano(plano);
		planoResponse = getSiteBAR().fetchPlanoById(request);
		Assert.assertEquals(plano.getId(), planoResponse.getId());
		getSiteBAR().deletePlanoById(plano);
		planoResponse = getSiteBAR().fetchPlanoById(request);
		Assert.assertEquals(planoResponse, null);
	}

	@Test
	public void testFetchAllPlanos()
	{
	Plano plano = new Plano();
		List<Plano> response = getSiteBAR().fetchAllPlanos(plano).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllPlanos()
	{
		getSiteBAR().deleteAllPlanos();
	Plano plano = new Plano();
		List<Plano> response = getSiteBAR().fetchAllPlanos(new Plano()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdatePlano()
	{
		Plano plano = new Plano(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Plano planoResponse = getSiteBAR().fetchPlanoById(request);
		Assert.assertEquals(planoResponse.getTitulo(), "NATIVE INSERT");
		getSiteBAR().updatePlano(plano);
		planoResponse = getSiteBAR().fetchPlanoById(request);
		Assert.assertEquals(planoResponse.getTitulo(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchPlanosByRequest() throws Exception
	{
		// check for valid and precount
		PlanoInquiryRequest request = new PlanoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Plano> response = getSiteBAR().fetchPlanosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSiteBAR().fetchPlanosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PlanoInquiryRequest request2 = new PlanoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Plano> response2 = getSiteBAR().fetchPlanosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSiteBAR().deleteAllPlanos();
		PlanoInquiryRequest request3 = new PlanoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Plano> response3 = getSiteBAR().fetchPlanosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertServico.sql", false);
		executeSqlScript("conf/insertSite.sql", false);
		executeSqlScript("conf/insertContato.sql", false);
		executeSqlScript("conf/insertContatoItens.sql", false);
		executeSqlScript("conf/insertOrdemServico.sql", false);
		executeSqlScript("conf/insertOrdemServicoItens.sql", false);
		executeSqlScript("conf/insertPlano.sql", false);
	}

}
