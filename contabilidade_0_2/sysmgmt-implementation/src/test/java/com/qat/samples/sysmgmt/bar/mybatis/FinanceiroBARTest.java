/** create by system gera-java version 1.0.0 11/05/2016 23:5 : 10*/
package com.qat.samples.sysmgmt.bar.mybatis;


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
import com.qat.samples.sysmgmt.agencia.model.Agencia;
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
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
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
		ContasPagar contaspagar = insertContasPagar(7, TabelaEnum.CONTASPAGAR, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(7);
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
		Titulo titulo = insertTitulo(7, TabelaEnum.TITULO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(7);
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
		Titulo titulo = insertTitulo(1, TabelaEnum.TITULO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Titulo tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse.getNumero(), "NATIVE INSERT");
		getFinanceiroBAR().updateTitulo(titulo);
		tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse.getNumero(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchTitulosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
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
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Titulo> response2 = getFinanceiroBAR().fetchTitulosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllTitulos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Titulo> response3 = getFinanceiroBAR().fetchTitulosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### BAIXATITULO ####======================================


@Test
	public void testDeleteBaixaTitulo()
	{
		BaixaTitulo baixatitulo = insertBaixaTitulo(7, TabelaEnum.BAIXARTITULO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(7);
		BaixaTitulo baixatituloResponse = getFinanceiroBAR().fetchBaixaTituloById(request);
		Assert.assertEquals(baixatituloResponse, null);
		getFinanceiroBAR().insertBaixaTitulo(baixatitulo);
		baixatituloResponse = getFinanceiroBAR().fetchBaixaTituloById(request);
		Assert.assertEquals(baixatitulo.getId(), baixatituloResponse.getId());
		getFinanceiroBAR().deleteBaixaTituloById(baixatitulo);
		baixatituloResponse = getFinanceiroBAR().fetchBaixaTituloById(request);
		Assert.assertEquals(baixatituloResponse, null);
	}

	@Test
	public void testFetchAllBaixaTitulos()
	{
	BaixaTitulo baixatitulo = new BaixaTitulo();
		List<BaixaTitulo> response = getFinanceiroBAR().fetchAllBaixaTitulos(baixatitulo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllBaixaTitulos()
	{
		getFinanceiroBAR().deleteAllBaixaTitulos();
	BaixaTitulo baixatitulo = new BaixaTitulo();
		List<BaixaTitulo> response = getFinanceiroBAR().fetchAllBaixaTitulos(new BaixaTitulo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateBaixaTitulo()
	{
		BaixaTitulo baixatitulo = insertBaixaTitulo(1, TabelaEnum.BAIXARTITULO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		BaixaTitulo baixatituloResponse = getFinanceiroBAR().fetchBaixaTituloById(request);
		Assert.assertEquals(baixatituloResponse.getModifyUser(), "NATIVE INSERT");
		getFinanceiroBAR().updateBaixaTitulo(baixatitulo);
		baixatituloResponse = getFinanceiroBAR().fetchBaixaTituloById(request);
		Assert.assertEquals(baixatituloResponse.getModifyUser(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchBaixaTitulosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<BaixaTitulo> response = getFinanceiroBAR().fetchBaixaTitulosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchBaixaTitulosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<BaixaTitulo> response2 = getFinanceiroBAR().fetchBaixaTitulosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllBaixaTitulos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<BaixaTitulo> response3 = getFinanceiroBAR().fetchBaixaTitulosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### TIPOBAIXA ####======================================


@Test
	public void testDeleteTipoBaixa()
	{
		TipoBaixa tipobaixa = insertTipoBaixa(7, TabelaEnum.TIPOBAIXA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(7);
		TipoBaixa tipobaixaResponse = getFinanceiroBAR().fetchTipoBaixaById(request);
		Assert.assertEquals(tipobaixaResponse, null);
		getFinanceiroBAR().insertTipoBaixa(tipobaixa);
		tipobaixaResponse = getFinanceiroBAR().fetchTipoBaixaById(request);
		Assert.assertEquals(tipobaixa.getId(), tipobaixaResponse.getId());
		getFinanceiroBAR().deleteTipoBaixaById(tipobaixa);
		tipobaixaResponse = getFinanceiroBAR().fetchTipoBaixaById(request);
		Assert.assertEquals(tipobaixaResponse, null);
	}

	@Test
	public void testFetchAllTipoBaixas()
	{
	TipoBaixa tipobaixa = new TipoBaixa();
		List<TipoBaixa> response = getFinanceiroBAR().fetchAllTipoBaixas(tipobaixa).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTipoBaixas()
	{
		getFinanceiroBAR().deleteAllTipoBaixas();
	TipoBaixa tipobaixa = new TipoBaixa();
		List<TipoBaixa> response = getFinanceiroBAR().fetchAllTipoBaixas(new TipoBaixa()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateTipoBaixa()
	{
		TipoBaixa tipobaixa = insertTipoBaixa(1, TabelaEnum.TIPOBAIXA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		TipoBaixa tipobaixaResponse = getFinanceiroBAR().fetchTipoBaixaById(request);
		Assert.assertEquals(tipobaixaResponse.getNome(), "NATIVE INSERT");
		getFinanceiroBAR().updateTipoBaixa(tipobaixa);
		tipobaixaResponse = getFinanceiroBAR().fetchTipoBaixaById(request);
		Assert.assertEquals(tipobaixaResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchTipoBaixasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<TipoBaixa> response = getFinanceiroBAR().fetchTipoBaixasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchTipoBaixasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<TipoBaixa> response2 = getFinanceiroBAR().fetchTipoBaixasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getFinanceiroBAR().deleteAllTipoBaixas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<TipoBaixa> response3 = getFinanceiroBAR().fetchTipoBaixasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONTASRECEBER ####======================================


@Test
	public void testDeleteContasReceber()
	{
		ContasReceber contasreceber = insertContasReceber(7, TabelaEnum.CONTASRECEBER, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(7);
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
		ContasReceber contasreceber = insertContasReceber(1, TabelaEnum.CONTASRECEBER, PersistenceActionEnum.UPDATE);
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
		CondPag condpag = insertCondPag(4, TabelaEnum.CONDPAG, PersistenceActionEnum.INSERT);
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
		CondPag condpag = insertCondPag(1, TabelaEnum.CONDPAG, PersistenceActionEnum.INSERT);
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
		FormaPg formapg = insertFormaPg(4, TabelaEnum.FORMAPG, PersistenceActionEnum.INSERT);
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
		FormaPg formapg = insertFormaPg(1, TabelaEnum.FORMAPG, PersistenceActionEnum.UPDATE);
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
		Banco banco = insertBANCO(4, TabelaEnum.FORMAPG, PersistenceActionEnum.INSERT);
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

	private Banco insertBANCO(int i, TabelaEnum formapg, PersistenceActionEnum insert) {
		Banco condpag = new Banco();
		Date a = new Date();
		condpag.setId(i);
		condpag.setNome("NATIVE INSERT");
		condpag.setParentId(i);
		condpag.setEmprId(1);
		condpag.setTabelaEnum(formapg);
		condpag.setModifyDateUTC(a.getTime());
		condpag.setCreateDateUTC(a.getTime());
		condpag.setCreateUser("system");
		condpag.setModifyUser("system");
		condpag.setProcessId(1);
		condpag.setModelAction(insert);

		return condpag;
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
		Banco banco = insertBANCO(1, TabelaEnum.FORMAPG, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Banco bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(bancoResponse.getNome(), "nome_1");
		getFinanceiroBAR().updateBanco(banco);
		bancoResponse = getFinanceiroBAR().fetchBancoById(request);
		Assert.assertEquals(bancoResponse.getNome(), "NATIVE INSERT");
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
		ContaCorrente contacorrente = insertContaCorrente(4, TabelaEnum.CONTACORRENTE, PersistenceActionEnum.INSERT);
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
		ContaCorrente contacorrente = insertContaCorrente(1, TabelaEnum.CONTACORRENTE, PersistenceActionEnum.UPDATE);
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
		Caixa caixa =  insertCaixa(4, TabelaEnum.CAIXA, PersistenceActionEnum.INSERT);
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
		Caixa caixa = insertCaixa(1, TabelaEnum.CAIXA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Caixa caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse.getCreateUser(), "NATIVE INSERT");
		getFinanceiroBAR().updateCaixa(caixa);
		caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse.getModifyUser(), "NATIVE INSERT UPDATE");
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
		executeSqlScript("conf/insertTitulo.sql", false);
		executeSqlScript("conf/insertBaixaTitulo.sql", false);
		executeSqlScript("conf/insertTipoBaixa.sql", false);
		executeSqlScript("conf/insertCondPag.sql", false);
		executeSqlScript("conf/insertFormaPg.sql", false);
		executeSqlScript("conf/insertBanco.sql", false);
		executeSqlScript("conf/insertContaCorrente.sql", false);
		executeSqlScript("conf/insertCaixa.sql", false);
	}


	public ContasPagar insertContasPagar(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ContasPagar contaspagar = new ContasPagar();
			Date a = new Date();
			contaspagar.setId(id);
			contaspagar.setParentId(id);
			contaspagar.setEmprId(1);
			contaspagar.setNumero("NATIVE INSERT UPDATE");
			contaspagar.setModifyDateUTC(a.getTime());
			contaspagar.setCreateDateUTC(a.getTime());
			contaspagar.setCreateUser("system");
			contaspagar.setModifyUser("system");
			contaspagar.setFornecedor(new Fornecedor());
			contaspagar.setProcessId(1);
			contaspagar.setModelAction(action);

			return contaspagar;
		}


	public Titulo insertTitulo(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Titulo titulo = new Titulo();
			Date a = new Date();
			titulo.setId(id);
			titulo.setNumero("NATIVE INSERT UPDATE");

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
			baixatitulo.setId(id);
			baixatitulo.setDataBaixa(a.getTime());
			baixatitulo.setObservacao("NATIVE INSERT UPDATE");
			baixatitulo.setValor(new Double(1.99));
			baixatitulo.setJuros(new Double(1.99));
			baixatitulo.setMulta(new Double(1.99));
			baixatitulo.setDesconto(new Double(1.99));
			baixatitulo.setParentId(id);
			baixatitulo.setEmprId(1);
			baixatitulo.setModifyDateUTC(a.getTime());
			baixatitulo.setCreateDateUTC(a.getTime());
			baixatitulo.setCreateUser("NATIVE INSERT UPDATE");
			baixatitulo.setModifyUser("NATIVE INSERT UPDATE");
			baixatitulo.setProcessId(1);
			baixatitulo.setModelAction(action);

			return baixatitulo;
		}


	public TipoBaixa insertTipoBaixa(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			TipoBaixa tipobaixa = new TipoBaixa();
			Date a = new Date();
			tipobaixa.setId(id);
			tipobaixa.setNome("NATIVE INSERT UPDATE");
			tipobaixa.setDescricao("NATIVE INSERT UPDATE");
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
			contasreceber.setId(id);
			contasreceber.setParentId(id);
			contasreceber.setEmprId(1);
			contasreceber.setModifyDateUTC(a.getTime());
			contasreceber.setCreateDateUTC(a.getTime());
			contasreceber.setCreateUser("system");
			contasreceber.setModifyUser("system");
			contasreceber.setCliente(new Cliente());
			contasreceber.setNumero("NATIVE INSERT UPDATE");
			contasreceber.setProcessId(1);
			contasreceber.setModelAction(action);

			return contasreceber;
		}


	public CondPag insertCondPag(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			CondPag condpag = new CondPag();
			Date a = new Date();
			condpag.setId(id);
			condpag.setParentId(id);
			condpag.setEmprId(1);
			condpag.setNome("NATIVE INSERT UPDATE");
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
			formapg.setId(id);
			formapg.setParentId(id);
			formapg.setEmprId(1);
			formapg.setDescricao("NATIVE INSERT UPDATE");
			formapg.setModifyDateUTC(a.getTime());
			formapg.setCreateDateUTC(a.getTime());
			formapg.setCreateUser("NATIVE INSERT UPDATE");
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
			contacorrente.setId(id);
			contacorrente.setSaldo(new Double(1.99));
			contacorrente.setNumeroConta("NATIVE INSERT UPDATE");
			contacorrente.setParentId(id);
			contacorrente.setEmprId(1);
			contacorrente.setAgencia(new Agencia());
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
			caixa.setId(id);
			caixa.setParentId(id);
			caixa.setEmprId(1);
			caixa.setModifyDateUTC(a.getTime());
			caixa.setCreateDateUTC(a.getTime());
			caixa.setCreateUser("NATIVE INSERT UPDATE");
			caixa.setModifyUser("NATIVE INSERT UPDATE");
			caixa.setProcessId(1);
			caixa.setModelAction(action);

			return caixa;
		}


}
