/** create by system gera-java version 1.0.0 11/05/2016 23:5 : 10*/
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
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.TipoBaixa;
import com.qat.samples.sysmgmt.financeiro.model.Titulo;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
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
		ContasPagar contaspagar = insertContasPagar(1, TabelaEnum.CONTASPAGAR, PersistenceActionEnum.UPDATE);
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

//===================================### TITULO ####======================================


@Test
	public void testDeleteTitulo()
	{
		Titulo titulo = new Titulo(4, "Titulo_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Titulo tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse, null);
		getFinanceiroBAR().insertTitulo(titulo);
		tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(titulo.getId(), tituloResponse.getId());
		getFinanceiroBAR().deleteTituloById(titulo);
		tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse, null);
	}

	@Test
	public void testFetchAllTitulos()
	{
	Titulo titulo = new Titulo();
		List<Titulo> response = getFinanceiroBAR().fetchAllTitulos(titulo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTitulos()
	{
		getFinanceiroBAR().deleteAllTitulos();
	Titulo titulo = new Titulo();
		List<Titulo> response = getFinanceiroBAR().fetchAllTitulos(new Titulo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateTitulo()
	{
		Titulo titulo = new Titulo(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Titulo tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateTitulo(titulo);
		tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchTitulosByRequest() throws Exception
	{
		// check for valid and precount
		TituloInquiryRequest request = new TituloInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Titulo> response = getFinanceiroBAR().fetchTitulosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchTitulosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		TituloInquiryRequest request2 = new TituloInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Titulo> response2 = getFinanceiroBAR().fetchTitulosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllTitulos();
		TituloInquiryRequest request3 = new TituloInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Titulo> response3 = getFinanceiroBAR().fetchTitulosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### BAIXATITULO ####======================================


@Test
	public void testDeleteBaixatitulo()
	{
		Baixatitulo baixatitulo = new Baixatitulo(4, "Baixatitulo_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Baixatitulo baixatituloResponse = getFinanceiroBAR().fetchBaixatituloById(request);
		Assert.assertEquals(baixatituloResponse, null);
		getFinanceiroBAR().insertBaixatitulo(baixatitulo);
		baixatituloResponse = getFinanceiroBAR().fetchBaixatituloById(request);
		Assert.assertEquals(baixatitulo.getId(), baixatituloResponse.getId());
		getFinanceiroBAR().deleteBaixatituloById(baixatitulo);
		baixatituloResponse = getFinanceiroBAR().fetchBaixatituloById(request);
		Assert.assertEquals(baixatituloResponse, null);
	}

	@Test
	public void testFetchAllBaixatitulos()
	{
	Baixatitulo baixatitulo = new Baixatitulo();
		List<Baixatitulo> response = getFinanceiroBAR().fetchAllBaixatitulos(baixatitulo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllBaixatitulos()
	{
		getFinanceiroBAR().deleteAllBaixatitulos();
	Baixatitulo baixatitulo = new Baixatitulo();
		List<Baixatitulo> response = getFinanceiroBAR().fetchAllBaixatitulos(new Baixatitulo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateBaixatitulo()
	{
		Baixatitulo baixatitulo = new Baixatitulo(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Baixatitulo baixatituloResponse = getFinanceiroBAR().fetchBaixatituloById(request);
		Assert.assertEquals(baixatituloResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateBaixatitulo(baixatitulo);
		baixatituloResponse = getFinanceiroBAR().fetchBaixatituloById(request);
		Assert.assertEquals(baixatituloResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchBaixatitulosByRequest() throws Exception
	{
		// check for valid and precount
		BaixatituloInquiryRequest request = new BaixatituloInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Baixatitulo> response = getFinanceiroBAR().fetchBaixatitulosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchBaixatitulosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		BaixatituloInquiryRequest request2 = new BaixatituloInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Baixatitulo> response2 = getFinanceiroBAR().fetchBaixatitulosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllBaixatitulos();
		BaixatituloInquiryRequest request3 = new BaixatituloInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Baixatitulo> response3 = getFinanceiroBAR().fetchBaixatitulosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### TIPOBAIXA ####======================================


@Test
	public void testDeleteTipobaixa()
	{
		Tipobaixa tipobaixa = new Tipobaixa(4, "Tipobaixa_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Tipobaixa tipobaixaResponse = getFinanceiroBAR().fetchTipobaixaById(request);
		Assert.assertEquals(tipobaixaResponse, null);
		getFinanceiroBAR().insertTipobaixa(tipobaixa);
		tipobaixaResponse = getFinanceiroBAR().fetchTipobaixaById(request);
		Assert.assertEquals(tipobaixa.getId(), tipobaixaResponse.getId());
		getFinanceiroBAR().deleteTipobaixaById(tipobaixa);
		tipobaixaResponse = getFinanceiroBAR().fetchTipobaixaById(request);
		Assert.assertEquals(tipobaixaResponse, null);
	}

	@Test
	public void testFetchAllTipobaixas()
	{
	Tipobaixa tipobaixa = new Tipobaixa();
		List<Tipobaixa> response = getFinanceiroBAR().fetchAllTipobaixas(tipobaixa).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTipobaixas()
	{
		getFinanceiroBAR().deleteAllTipobaixas();
	Tipobaixa tipobaixa = new Tipobaixa();
		List<Tipobaixa> response = getFinanceiroBAR().fetchAllTipobaixas(new Tipobaixa()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateTipobaixa()
	{
		Tipobaixa tipobaixa = new Tipobaixa(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Tipobaixa tipobaixaResponse = getFinanceiroBAR().fetchTipobaixaById(request);
		Assert.assertEquals(tipobaixaResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateTipobaixa(tipobaixa);
		tipobaixaResponse = getFinanceiroBAR().fetchTipobaixaById(request);
		Assert.assertEquals(tipobaixaResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchTipobaixasByRequest() throws Exception
	{
		// check for valid and precount
		TipobaixaInquiryRequest request = new TipobaixaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Tipobaixa> response = getFinanceiroBAR().fetchTipobaixasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchTipobaixasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		TipobaixaInquiryRequest request2 = new TipobaixaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Tipobaixa> response2 = getFinanceiroBAR().fetchTipobaixasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllTipobaixas();
		TipobaixaInquiryRequest request3 = new TipobaixaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Tipobaixa> response3 = getFinanceiroBAR().fetchTipobaixasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONTASRECEBER ####======================================


@Test
	public void testDeleteContasreceber()
	{
		Contasreceber contasreceber = new Contasreceber(4, "Contasreceber_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Contasreceber contasreceberResponse = getFinanceiroBAR().fetchContasreceberById(request);
		Assert.assertEquals(contasreceberResponse, null);
		getFinanceiroBAR().insertContasreceber(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasreceberById(request);
		Assert.assertEquals(contasreceber.getId(), contasreceberResponse.getId());
		getFinanceiroBAR().deleteContasreceberById(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasreceberById(request);
		Assert.assertEquals(contasreceberResponse, null);
	}

	@Test
	public void testFetchAllContasrecebers()
	{
	Contasreceber contasreceber = new Contasreceber();
		List<Contasreceber> response = getFinanceiroBAR().fetchAllContasrecebers(contasreceber).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllContasrecebers()
	{
		getFinanceiroBAR().deleteAllContasrecebers();
	Contasreceber contasreceber = new Contasreceber();
		List<Contasreceber> response = getFinanceiroBAR().fetchAllContasrecebers(new Contasreceber()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateContasreceber()
	{
		Contasreceber contasreceber = new Contasreceber(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Contasreceber contasreceberResponse = getFinanceiroBAR().fetchContasreceberById(request);
		Assert.assertEquals(contasreceberResponse.getDescription(), "NATIVE INSERT");
		getFinanceiroBAR().updateContasreceber(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasreceberById(request);
		Assert.assertEquals(contasreceberResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchContasrecebersByRequest() throws Exception
	{
		// check for valid and precount
		ContasreceberInquiryRequest request = new ContasreceberInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Contasreceber> response = getFinanceiroBAR().fetchContasrecebersByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchContasrecebersByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ContasreceberInquiryRequest request2 = new ContasreceberInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Contasreceber> response2 = getFinanceiroBAR().fetchContasrecebersByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllContasrecebers();
		ContasreceberInquiryRequest request3 = new ContasreceberInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Contasreceber> response3 = getFinanceiroBAR().fetchContasrecebersByRequest(request3);
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
		executeSqlScript("conf/insertTitulo.sql", false);
		executeSqlScript("conf/insertBaixatitulo.sql", false);
		executeSqlScript("conf/insertTipobaixa.sql", false);
		executeSqlScript("conf/insertContasreceber.sql", false);
		executeSqlScript("conf/insertCondpag.sql", false);
		executeSqlScript("conf/insertFormapg.sql", false);
		executeSqlScript("conf/insertBanco.sql", false);
		executeSqlScript("conf/insertContacorrente.sql", false);
		executeSqlScript("conf/insertCaixa.sql", false);
	}


	public ContasPagar insertContasPagar(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ContasPagar contaspagar = new ContasPagar();
			Date a = new Date();
			contaspagar.setId(100);
			contaspagar.setDataBaixa(a.getTime());
			contaspagar.setObservacao("observacao");
			contaspagar.setValor(new Double(1.99));
			contaspagar.setJuros(new Double(1.99));
			contaspagar.setMulta(new Double(1.99));
			contaspagar.setDesconto(new Double(1.99));
			contaspagar.setTipoBaixaList(new ArrayList<BaixaTitulo>());
			contaspagar.getBaixaTitulo().add(insertBaixaTitulo(id,TabelaEnum.CONTASPAGAR,action));
			contaspagar.setFornecedor(new ArrayList<undefined>());
			contaspagar.setParentId(id);
			contaspagar.setEmprId(1);
			contaspagar.setModifyDateUTC(a.getTime());
			contaspagar.setCreateDateUTC(a.getTime());
			contaspagar.setCreateUser("system");
			contaspagar.setModifyUser("system");
			contaspagar.setProcessId(1);
			contaspagar.setModelAction(action);

			return contaspagar;
		}


	public Titulo insertTitulo(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Titulo titulo = new Titulo();
			Date a = new Date();
			titulo.setId(100);
			titulo.setNumero("numero");
			titulo.setFornecedor(100);
			titulo.setCliente(100);
			titulo.setDataEmissao(a.getTime());
			titulo.setDataVencimento(a.getTime());
			titulo.setDocId(100);
			titulo.setObservacao("observacao");
			titulo.setFinanceiroEnumValue(100);
			titulo.setListBaixa(new ArrayList<BaixaTitulo>());
			titulo.getBaixaTitulo().add(insertBaixaTitulo(id,TabelaEnum.TITULO,action));
			titulo.setParentId(id);
			titulo.setEmprId(1);
			titulo.setModifyDateUTC(a.getTime());
			titulo.setCreateDateUTC(a.getTime());
			titulo.setCreateUser("system");
			titulo.setModifyUser("system");
			titulo.setProcessId(1);
			titulo.setModelAction(action);

			return titulo;
		}


	public BaixaTitulo insertBaixaTitulo(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			BaixaTitulo baixatitulo = new BaixaTitulo();
			Date a = new Date();
			baixatitulo.setId(100);
			baixatitulo.setDataBaixa(a.getTime());
			baixatitulo.setObservacao("observacao");
			baixatitulo.setValor(new Double(1.99));
			baixatitulo.setJuros(new Double(1.99));
			baixatitulo.setMulta(new Double(1.99));
			baixatitulo.setDesconto(new Double(1.99));
			baixatitulo.setTipoBaixaList(new ArrayList<BaixaTitulo>());
			baixatitulo.getBaixaTitulo().add(insertBaixaTitulo(id,TabelaEnum.BAIXATITULO,action));
			baixatitulo.setParentId(id);
			baixatitulo.setEmprId(1);
			baixatitulo.setModifyDateUTC(a.getTime());
			baixatitulo.setCreateDateUTC(a.getTime());
			baixatitulo.setCreateUser("system");
			baixatitulo.setModifyUser("system");
			baixatitulo.setProcessId(1);
			baixatitulo.setModelAction(action);

			return baixatitulo;
		}


	public TipoBaixa insertTipoBaixa(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			TipoBaixa tipobaixa = new TipoBaixa();
			Date a = new Date();
			tipobaixa.setId(100);
			tipobaixa.setNome("nome");
			tipobaixa.setDescricao(new ArrayList<undefined>());
			tipobaixa.getundefined().add(insertundefined(id,TabelaEnum.TIPOBAIXA,action));
			tipobaixa.setParentId(id);
			tipobaixa.setEmprId(1);
			tipobaixa.setModifyDateUTC(a.getTime());
			tipobaixa.setCreateDateUTC(a.getTime());
			tipobaixa.setCreateUser("system");
			tipobaixa.setModifyUser("system");
			tipobaixa.setProcessId(1);
			tipobaixa.setModelAction(action);

			return tipobaixa;
		}


	public ContasReceber insertContasReceber(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ContasReceber contasreceber = new ContasReceber();
			Date a = new Date();
			contasreceber.setId(100);
			contasreceber.setDataBaixa(a.getTime());
			contasreceber.setObservacao("observacao");
			contasreceber.setValor(new Double(1.99));
			contasreceber.setJuros(new Double(1.99));
			contasreceber.setMulta(new Double(1.99));
			contasreceber.setDesconto(new Double(1.99));
			contasreceber.setTipoBaixaList(new ArrayList<BaixaTitulo>());
			contasreceber.getBaixaTitulo().add(insertBaixaTitulo(id,TabelaEnum.CONTASRECEBER,action));
			contasreceber.setCliente(new ArrayList<undefined>());
			contasreceber.setParentId(id);
			contasreceber.setEmprId(1);
			contasreceber.setModifyDateUTC(a.getTime());
			contasreceber.setCreateDateUTC(a.getTime());
			contasreceber.setCreateUser("system");
			contasreceber.setModifyUser("system");
			contasreceber.setProcessId(1);
			contasreceber.setModelAction(action);

			return contasreceber;
		}


	public CondPag insertCondPag(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			CondPag condpag = new CondPag();
			Date a = new Date();
			condpag.setId(100);
			condpag.setParentId(id);
			condpag.setEmprId(1);
			condpag.setModifyDateUTC(a.getTime());
			condpag.setCreateDateUTC(a.getTime());
			condpag.setCreateUser("system");
			condpag.setModifyUser("system");
			condpag.setProcessId(1);
			condpag.setModelAction(action);

			return condpag;
		}


	public FormaPg insertFormaPg(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			FormaPg formapg = new FormaPg();
			Date a = new Date();
			formapg.setId(100);
			formapg.setParentId(id);
			formapg.setEmprId(1);
			formapg.setModifyDateUTC(a.getTime());
			formapg.setCreateDateUTC(a.getTime());
			formapg.setCreateUser("system");
			formapg.setModifyUser("system");
			formapg.setProcessId(1);
			formapg.setModelAction(action);

			return formapg;
		}


	public Banco insertBanco(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Banco banco = new Banco();
			Date a = new Date();
			banco.setId(100);
			banco.setParentId(id);
			banco.setEmprId(1);
			banco.setModifyDateUTC(a.getTime());
			banco.setCreateDateUTC(a.getTime());
			banco.setCreateUser("system");
			banco.setModifyUser("system");
			banco.setProcessId(1);
			banco.setModelAction(action);

			return banco;
		}


	public ContaCorrente insertContaCorrente(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ContaCorrente contacorrente = new ContaCorrente();
			Date a = new Date();
			contacorrente.setId(100);
			contacorrente.setNome("nome");
			contacorrente.setSaldo(new Double(1.99));
			contacorrente.setDescricao("descricao");
			contacorrente.setParentId(id);
			contacorrente.setEmprId(1);
			contacorrente.setModifyDateUTC(a.getTime());
			contacorrente.setCreateDateUTC(a.getTime());
			contacorrente.setCreateUser("system");
			contacorrente.setModifyUser("system");
			contacorrente.setProcessId(1);
			contacorrente.setModelAction(action);

			return contacorrente;
		}


	public Caixa insertCaixa(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Caixa caixa = new Caixa();
			Date a = new Date();
			caixa.setId(100);
			caixa.setParentId(id);
			caixa.setEmprId(1);
			caixa.setModifyDateUTC(a.getTime());
			caixa.setCreateDateUTC(a.getTime());
			caixa.setCreateUser("system");
			caixa.setModifyUser("system");
			caixa.setProcessId(1);
			caixa.setModelAction(action);

			return caixa;
		}


}
