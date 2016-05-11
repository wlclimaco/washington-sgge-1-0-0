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
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
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
		Assert.assertEquals(contaspagarResponse.getNumero(), "NATIVE INSERT");
		getFinanceiroBAR().updateContasPagar(contaspagar);
		contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagarResponse.getNumero(), "NATIVE INSERT UPDATE");
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
		Assert.assertEquals(contasreceberResponse.getNumero(), "NATIVE INSERT");
		getFinanceiroBAR().updateContasReceber(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceberResponse.getNumero(), "NATIVE INSERT UPDATE");
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
	public void testDeleteCondPag()
	{
		CondPag condpag = new CondPag(4, "CondPag_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		CondPag condpagResponse = getFinanceiroBAR().fetchCondPagById(request);
		Assert.assertEquals(condpagResponse, null);
		getFinanceiroBAR().insertCondPag(condpag);
		condpagResponse = getFinanceiroBAR().fetchCondPagById(request);
		Assert.assertEquals(condpag.getId(), condpagResponse.getId());
		getFinanceiroBAR().deleteCondPagById(condpag);
		condpagResponse = getFinanceiroBAR().fetchCondPagById(request);
		Assert.assertEquals(condpagResponse, null);
	}

	@Test
	public void testFetchAllCondPags()
	{
	CondPag condpag = new CondPag();
		List<CondPag> response = getFinanceiroBAR().fetchAllCondPags(condpag).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCondPags()
	{
		getFinanceiroBAR().deleteAllCondPags();
	CondPag condpag = new CondPag();
		List<CondPag> response = getFinanceiroBAR().fetchAllCondPags(new CondPag()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCondPag()
	{
		CondPag condpag = new CondPag(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		CondPag condpagResponse = getFinanceiroBAR().fetchCondPagById(request);
		Assert.assertEquals(condpagResponse.getNome(), "NATIVE INSERT");
		getFinanceiroBAR().updateCondPag(condpag);
		condpagResponse = getFinanceiroBAR().fetchCondPagById(request);
		Assert.assertEquals(condpagResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCondPagsByRequest() throws Exception
	{
		// check for valid and precount
		CondPagInquiryRequest request = new CondPagInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<CondPag> response = getFinanceiroBAR().fetchCondPagsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchCondPagsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CondPagInquiryRequest request2 = new CondPagInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<CondPag> response2 = getFinanceiroBAR().fetchCondPagsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllCondPags();
		CondPagInquiryRequest request3 = new CondPagInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<CondPag> response3 = getFinanceiroBAR().fetchCondPagsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### FORMAPG ####======================================


@Test
	public void testDeleteFormaPg()
	{
		FormaPg formapg = new FormaPg(4, "FormaPg_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		FormaPg formapgResponse = getFinanceiroBAR().fetchFormaPgById(request);
		Assert.assertEquals(formapgResponse, null);
		getFinanceiroBAR().insertFormaPg(formapg);
		formapgResponse = getFinanceiroBAR().fetchFormaPgById(request);
		Assert.assertEquals(formapg.getId(), formapgResponse.getId());
		getFinanceiroBAR().deleteFormaPgById(formapg);
		formapgResponse = getFinanceiroBAR().fetchFormaPgById(request);
		Assert.assertEquals(formapgResponse, null);
	}

	@Test
	public void testFetchAllFormaPgs()
	{
	FormaPg formapg = new FormaPg();
		List<FormaPg> response = getFinanceiroBAR().fetchAllFormaPgs(formapg).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllFormaPgs()
	{
		getFinanceiroBAR().deleteAllFormaPgs();
	FormaPg formapg = new FormaPg();
		List<FormaPg> response = getFinanceiroBAR().fetchAllFormaPgs(new FormaPg()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateFormaPg()
	{
		FormaPg formapg = new FormaPg(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		FormaPg formapgResponse = getFinanceiroBAR().fetchFormaPgById(request);
		Assert.assertEquals(formapgResponse.getDescricao(), "NATIVE INSERT");
		getFinanceiroBAR().updateFormaPg(formapg);
		formapgResponse = getFinanceiroBAR().fetchFormaPgById(request);
		Assert.assertEquals(formapgResponse.getDescricao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchFormaPgsByRequest() throws Exception
	{
		// check for valid and precount
		FormaPgInquiryRequest request = new FormaPgInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<FormaPg> response = getFinanceiroBAR().fetchFormaPgsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchFormaPgsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		FormaPgInquiryRequest request2 = new FormaPgInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<FormaPg> response2 = getFinanceiroBAR().fetchFormaPgsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllFormaPgs();
		FormaPgInquiryRequest request3 = new FormaPgInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<FormaPg> response3 = getFinanceiroBAR().fetchFormaPgsByRequest(request3);
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
		Assert.assertEquals(bancoResponse.getNome(), "NATIVE INSERT");
		getFinanceiroBAR().updateBanco(banco);
		bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(bancoResponse.getNome(), "NATIVE INSERT UPDATE");
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
	public void testDeleteContaCorrente()
	{
		ContaCorrente contacorrente = new ContaCorrente(4, "ContaCorrente_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ContaCorrente contacorrenteResponse = getFinanceiroBAR().fetchContaCorrenteById(request);
		Assert.assertEquals(contacorrenteResponse, null);
		getFinanceiroBAR().insertContaCorrente(contacorrente);
		contacorrenteResponse = getFinanceiroBAR().fetchContaCorrenteById(request);
		Assert.assertEquals(contacorrente.getId(), contacorrenteResponse.getId());
		getFinanceiroBAR().deleteContaCorrenteById(contacorrente);
		contacorrenteResponse = getFinanceiroBAR().fetchContaCorrenteById(request);
		Assert.assertEquals(contacorrenteResponse, null);
	}

	@Test
	public void testFetchAllContaCorrentes()
	{
	ContaCorrente contacorrente = new ContaCorrente();
		List<ContaCorrente> response = getFinanceiroBAR().fetchAllContaCorrentes(contacorrente).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllContaCorrentes()
	{
		getFinanceiroBAR().deleteAllContaCorrentes();
	ContaCorrente contacorrente = new ContaCorrente();
		List<ContaCorrente> response = getFinanceiroBAR().fetchAllContaCorrentes(new ContaCorrente()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateContaCorrente()
	{
		ContaCorrente contacorrente = new ContaCorrente(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		ContaCorrente contacorrenteResponse = getFinanceiroBAR().fetchContaCorrenteById(request);
		Assert.assertEquals(contacorrenteResponse.getNumeroConta(), "NATIVE INSERT");
		getFinanceiroBAR().updateContaCorrente(contacorrente);
		contacorrenteResponse = getFinanceiroBAR().fetchContaCorrenteById(request);
		Assert.assertEquals(contacorrenteResponse.getNumeroConta(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchContaCorrentesByRequest() throws Exception
	{
		// check for valid and precount
		ContaCorrenteInquiryRequest request = new ContaCorrenteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ContaCorrente> response = getFinanceiroBAR().fetchContaCorrentesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchContaCorrentesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ContaCorrenteInquiryRequest request2 = new ContaCorrenteInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ContaCorrente> response2 = getFinanceiroBAR().fetchContaCorrentesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllContaCorrentes();
		ContaCorrenteInquiryRequest request3 = new ContaCorrenteInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ContaCorrente> response3 = getFinanceiroBAR().fetchContaCorrentesByRequest(request3);
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
		Assert.assertEquals(caixaResponse.getCreateUser(), "NATIVE INSERT");
		getFinanceiroBAR().updateCaixa(caixa);
		caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse.getCreateUser(), "NATIVE INSERT UPDATE");
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
		executeSqlScript("conf/insertCondPag.sql", false);
		executeSqlScript("conf/insertFormaPg.sql", false);
		executeSqlScript("conf/insertBanco.sql", false);
		executeSqlScript("conf/insertContaCorrente.sql", false);
		executeSqlScript("conf/insertCaixa.sql", false);
	}

}
