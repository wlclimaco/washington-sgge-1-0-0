/** create by system gera-java version 1.0.0 10/05/2016 12:31 : 30*/
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
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class FinanceiroBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(FinanceiroBARTest.class);
private IFinanceiroBAR financeiroBAR; // injected by Spring through @Resource

@Resource
public void setFinanceiroBAR(IFinanceiroBAR financeiroBAR)
{
	this.financeiroBAR = financeiroBAR;
}

public IFinanceiroBAR getFinanceiroBAR()
{
	return financeiroBAR;
}


//===================================### CONTASPAGAR ####======================================


@Test
	public void testDeleteContasPagar()
	{
		ContasPagar contaspagar = new ContasPagar(4, "ContasPagar_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ContasPagar contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagarResponse, null);
		getFinanceiroBAR().insertContasPagar(contaspagar);
		contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagar.getId(), contaspagarResponse.getId());
		getFinanceiroBAR().deleteContasPagarById(contaspagar);
		contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagarResponse, null);
	}

	@Test
	public void testFetchAllContasPagars()
	{
	ContasPagar contaspagar = new ContasPagar();
		List<ContasPagar> response = getFinanceiroBAR().fetchAllContasPagars(contaspagar).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllContasPagars()
	{
		getFinanceiroBAR().deleteAllContasPagars();
	ContasPagar contaspagar = new ContasPagar();
		List<ContasPagar> response = getFinanceiroBAR().fetchAllContasPagars(new ContasPagar()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateContasPagar()
	{
		ContasPagar contaspagar = new ContasPagar(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		ContasPagar contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagarResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateContasPagar(contaspagar);
		contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagarResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchContasPagarsByRequest() throws Exception
	{
		// check for valid and precount
		ContasPagarInquiryRequest request = new ContasPagarInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ContasPagar> response = getFinanceiroBAR().fetchContasPagarsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchContasPagarsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ContasPagarInquiryRequest request2 = new ContasPagarInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ContasPagar> response2 = getFinanceiroBAR().fetchContasPagarsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllContasPagars();
		ContasPagarInquiryRequest request3 = new ContasPagarInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ContasPagar> response3 = getFinanceiroBAR().fetchContasPagarsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONTASRECEBER ####======================================


@Test
	public void testDeleteContasReceber()
	{
		ContasReceber contasreceber = new ContasReceber(4, "ContasReceber_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ContasReceber contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceberResponse, null);
		getFinanceiroBAR().insertContasReceber(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceber.getId(), contasreceberResponse.getId());
		getFinanceiroBAR().deleteContasReceberById(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceberResponse, null);
	}

	@Test
	public void testFetchAllContasRecebers()
	{
	ContasReceber contasreceber = new ContasReceber();
		List<ContasReceber> response = getFinanceiroBAR().fetchAllContasRecebers(contasreceber).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllContasRecebers()
	{
		getFinanceiroBAR().deleteAllContasRecebers();
	ContasReceber contasreceber = new ContasReceber();
		List<ContasReceber> response = getFinanceiroBAR().fetchAllContasRecebers(new ContasReceber()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateContasReceber()
	{
		ContasReceber contasreceber = new ContasReceber(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		ContasReceber contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceberResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateContasReceber(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceberResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchContasRecebersByRequest() throws Exception
	{
		// check for valid and precount
		ContasReceberInquiryRequest request = new ContasReceberInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ContasReceber> response = getFinanceiroBAR().fetchContasRecebersByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchContasRecebersByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ContasReceberInquiryRequest request2 = new ContasReceberInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ContasReceber> response2 = getFinanceiroBAR().fetchContasRecebersByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllContasRecebers();
		ContasReceberInquiryRequest request3 = new ContasReceberInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ContasReceber> response3 = getFinanceiroBAR().fetchContasRecebersByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONDPAG ####======================================


@Test
	public void testDeleteCondpag()
	{
		Condpag condpag = new Condpag(4, "Condpag_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Condpag condpagResponse = getFinanceiroBAR().fetchCondpagById(request);
		Assert.assertEquals(condpagResponse, null);
		getFinanceiroBAR().insertCondpag(condpag);
		condpagResponse = getFinanceiroBAR().fetchCondpagById(request);
		Assert.assertEquals(condpag.getId(), condpagResponse.getId());
		getFinanceiroBAR().deleteCondpagById(condpag);
		condpagResponse = getFinanceiroBAR().fetchCondpagById(request);
		Assert.assertEquals(condpagResponse, null);
	}

	@Test
	public void testFetchAllCondpags()
	{
	Condpag condpag = new Condpag();
		List<Condpag> response = getFinanceiroBAR().fetchAllCondpags(condpag).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCondpags()
	{
		getFinanceiroBAR().deleteAllCondpags();
	Condpag condpag = new Condpag();
		List<Condpag> response = getFinanceiroBAR().fetchAllCondpags(new Condpag()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCondpag()
	{
		Condpag condpag = new Condpag(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Condpag condpagResponse = getFinanceiroBAR().fetchCondpagById(request);
		Assert.assertEquals(condpagResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateCondpag(condpag);
		condpagResponse = getFinanceiroBAR().fetchCondpagById(request);
		Assert.assertEquals(condpagResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCondpagsByRequest() throws Exception
	{
		// check for valid and precount
		CondpagInquiryRequest request = new CondpagInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Condpag> response = getFinanceiroBAR().fetchCondpagsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchCondpagsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CondpagInquiryRequest request2 = new CondpagInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Condpag> response2 = getFinanceiroBAR().fetchCondpagsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllCondpags();
		CondpagInquiryRequest request3 = new CondpagInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Condpag> response3 = getFinanceiroBAR().fetchCondpagsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### FORMAPG ####======================================


@Test
	public void testDeleteFormapg()
	{
		Formapg formapg = new Formapg(4, "Formapg_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Formapg formapgResponse = getFinanceiroBAR().fetchFormapgById(request);
		Assert.assertEquals(formapgResponse, null);
		getFinanceiroBAR().insertFormapg(formapg);
		formapgResponse = getFinanceiroBAR().fetchFormapgById(request);
		Assert.assertEquals(formapg.getId(), formapgResponse.getId());
		getFinanceiroBAR().deleteFormapgById(formapg);
		formapgResponse = getFinanceiroBAR().fetchFormapgById(request);
		Assert.assertEquals(formapgResponse, null);
	}

	@Test
	public void testFetchAllFormapgs()
	{
	Formapg formapg = new Formapg();
		List<Formapg> response = getFinanceiroBAR().fetchAllFormapgs(formapg).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllFormapgs()
	{
		getFinanceiroBAR().deleteAllFormapgs();
	Formapg formapg = new Formapg();
		List<Formapg> response = getFinanceiroBAR().fetchAllFormapgs(new Formapg()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateFormapg()
	{
		Formapg formapg = new Formapg(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Formapg formapgResponse = getFinanceiroBAR().fetchFormapgById(request);
		Assert.assertEquals(formapgResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateFormapg(formapg);
		formapgResponse = getFinanceiroBAR().fetchFormapgById(request);
		Assert.assertEquals(formapgResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchFormapgsByRequest() throws Exception
	{
		// check for valid and precount
		FormapgInquiryRequest request = new FormapgInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Formapg> response = getFinanceiroBAR().fetchFormapgsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchFormapgsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		FormapgInquiryRequest request2 = new FormapgInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Formapg> response2 = getFinanceiroBAR().fetchFormapgsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllFormapgs();
		FormapgInquiryRequest request3 = new FormapgInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Formapg> response3 = getFinanceiroBAR().fetchFormapgsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### BANCO ####======================================


@Test
	public void testDeleteBanco()
	{
		Banco banco = new Banco(4, "Banco_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Banco bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(bancoResponse, null);
		getFinanceiroBAR().insertBanco(banco);
		bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(banco.getId(), bancoResponse.getId());
		getFinanceiroBAR().deleteBancoById(banco);
		bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(bancoResponse, null);
	}

	@Test
	public void testFetchAllBancos()
	{
	Banco banco = new Banco();
		List<Banco> response = getFinanceiroBAR().fetchAllBancos(banco).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllBancos()
	{
		getFinanceiroBAR().deleteAllBancos();
	Banco banco = new Banco();
		List<Banco> response = getFinanceiroBAR().fetchAllBancos(new Banco()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateBanco()
	{
		Banco banco = new Banco(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Banco bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(bancoResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateBanco(banco);
		bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(bancoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchBancosByRequest() throws Exception
	{
		// check for valid and precount
		BancoInquiryRequest request = new BancoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Banco> response = getFinanceiroBAR().fetchBancosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchBancosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		BancoInquiryRequest request2 = new BancoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Banco> response2 = getFinanceiroBAR().fetchBancosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllBancos();
		BancoInquiryRequest request3 = new BancoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Banco> response3 = getFinanceiroBAR().fetchBancosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONTACORRENTE ####======================================


@Test
	public void testDeleteContacorrente()
	{
		Contacorrente contacorrente = new Contacorrente(4, "Contacorrente_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Contacorrente contacorrenteResponse = getFinanceiroBAR().fetchContacorrenteById(request);
		Assert.assertEquals(contacorrenteResponse, null);
		getFinanceiroBAR().insertContacorrente(contacorrente);
		contacorrenteResponse = getFinanceiroBAR().fetchContacorrenteById(request);
		Assert.assertEquals(contacorrente.getId(), contacorrenteResponse.getId());
		getFinanceiroBAR().deleteContacorrenteById(contacorrente);
		contacorrenteResponse = getFinanceiroBAR().fetchContacorrenteById(request);
		Assert.assertEquals(contacorrenteResponse, null);
	}

	@Test
	public void testFetchAllContacorrentes()
	{
	Contacorrente contacorrente = new Contacorrente();
		List<Contacorrente> response = getFinanceiroBAR().fetchAllContacorrentes(contacorrente).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllContacorrentes()
	{
		getFinanceiroBAR().deleteAllContacorrentes();
	Contacorrente contacorrente = new Contacorrente();
		List<Contacorrente> response = getFinanceiroBAR().fetchAllContacorrentes(new Contacorrente()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateContacorrente()
	{
		Contacorrente contacorrente = new Contacorrente(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Contacorrente contacorrenteResponse = getFinanceiroBAR().fetchContacorrenteById(request);
		Assert.assertEquals(contacorrenteResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateContacorrente(contacorrente);
		contacorrenteResponse = getFinanceiroBAR().fetchContacorrenteById(request);
		Assert.assertEquals(contacorrenteResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchContacorrentesByRequest() throws Exception
	{
		// check for valid and precount
		ContacorrenteInquiryRequest request = new ContacorrenteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Contacorrente> response = getFinanceiroBAR().fetchContacorrentesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchContacorrentesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ContacorrenteInquiryRequest request2 = new ContacorrenteInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Contacorrente> response2 = getFinanceiroBAR().fetchContacorrentesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllContacorrentes();
		ContacorrenteInquiryRequest request3 = new ContacorrenteInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Contacorrente> response3 = getFinanceiroBAR().fetchContacorrentesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CAIXA ####======================================


@Test
	public void testDeleteCaixa()
	{
		Caixa caixa = new Caixa(4, "Caixa_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Caixa caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse, null);
		getFinanceiroBAR().insertCaixa(caixa);
		caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixa.getId(), caixaResponse.getId());
		getFinanceiroBAR().deleteCaixaById(caixa);
		caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse, null);
	}

	@Test
	public void testFetchAllCaixas()
	{
	Caixa caixa = new Caixa();
		List<Caixa> response = getFinanceiroBAR().fetchAllCaixas(caixa).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCaixas()
	{
		getFinanceiroBAR().deleteAllCaixas();
	Caixa caixa = new Caixa();
		List<Caixa> response = getFinanceiroBAR().fetchAllCaixas(new Caixa()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCaixa()
	{
		Caixa caixa = new Caixa(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Caixa caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateCaixa(caixa);
		caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCaixasByRequest() throws Exception
	{
		// check for valid and precount
		CaixaInquiryRequest request = new CaixaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Caixa> response = getFinanceiroBAR().fetchCaixasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchCaixasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CaixaInquiryRequest request2 = new CaixaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Caixa> response2 = getFinanceiroBAR().fetchCaixasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllCaixas();
		CaixaInquiryRequest request3 = new CaixaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Caixa> response3 = getFinanceiroBAR().fetchCaixasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertContasPagar.sql", false);
		executeSqlScript("conf/insertContasReceber.sql", false);
		executeSqlScript("conf/insertCondpag.sql", false);
		executeSqlScript("conf/insertFormapg.sql", false);
		executeSqlScript("conf/insertBanco.sql", false);
		executeSqlScript("conf/insertContacorrente.sql", false);
		executeSqlScript("conf/insertCaixa.sql", false);
	}

}
