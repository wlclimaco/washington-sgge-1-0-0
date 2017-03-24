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
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.Conta;
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
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
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
		ContasPagar contaspagar = Objects.insertContasPagar(7, TabelaEnum.CONTASPAGAR, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(7);
		ContasPagar contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Conta conta1 = getFinanceiroBAR().fetchContaById(new FetchByIdRequest(contaspagar.getListBaixa().get(0).getConta().getId()));
		Assert.assertEquals(contaspagarResponse, null);
		getFinanceiroBAR().insertContasPagar(contaspagar);
		Conta conta = getFinanceiroBAR().fetchContaById(new FetchByIdRequest(contaspagar.getListBaixa().get(0).getConta().getId()));
		contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(new Double(conta1.getSaldo() - conta.getSaldo()), contaspagarResponse.getListBaixa().get(0).getValor());
		Assert.assertEquals(contaspagar.getId(), contaspagarResponse.getId());
		contaspagar = Objects.insertContasPagar(7, TabelaEnum.CONTASPAGAR, PersistenceActionEnum.DELETE);
		getFinanceiroBAR().deleteContasPagarById(contaspagar);
		contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		conta = getFinanceiroBAR().fetchContaById(new FetchByIdRequest(contaspagar.getListBaixa().get(0).getConta().getId()));
		Assert.assertEquals(new Double(conta1.getSaldo()), new Double(conta.getSaldo()));
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
		ContasPagar contaspagar = Objects.insertContasPagar(1001, TabelaEnum.CONTASPAGAR, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		ContasPagar contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagarResponse.getNumero(), "NATIVE INSERT");
		getFinanceiroBAR().updateContasPagar(contaspagar);
		contaspagarResponse = getFinanceiroBAR().fetchContasPagarById(request);
		Assert.assertEquals(contaspagarResponse.getNumero(), "numero_1 - UPDATE");
	}

	@Test
	public void testFetchContasPagarsByRequest() throws Exception
	{
		// check for valid and precount
		ContasPagarInquiryRequest request = new ContasPagarInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		request.setBuscaTable("id");
		request.setBuscaValue("1003");
		request.setColumnName1(1);
		request.setDirection1(com.qat.framework.model.SortExpression.Direction.Descending);
		InternalResultsResponse<ContasPagar> response = getFinanceiroBAR().fetchContasPagarsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 1);
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
		Titulo titulo = Objects.insertTitulo(7, TabelaEnum.TITULO, PersistenceActionEnum.INSERT);
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
		Titulo titulo = Objects.insertTitulo(1001, TabelaEnum.TITULO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Titulo tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse.getNumero(), "NATIVE INSERT");
		getFinanceiroBAR().updateTitulo(titulo);
		tituloResponse = getFinanceiroBAR().fetchTituloById(request);
		Assert.assertEquals(tituloResponse.getNumero(), "numero_1 - UPDATE");
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
		BaixaTitulo baixatitulo = Objects.insertBaixaTitulo(7001, TabelaEnum.BAIXARTITULO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(7001);
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
		BaixaTitulo baixatitulo = Objects.insertBaixaTitulo(1001, TabelaEnum.BAIXARTITULO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		BaixaTitulo baixatituloResponse = getFinanceiroBAR().fetchBaixaTituloById(request);
		Assert.assertEquals(baixatituloResponse.getModifyUser(), "NATIVE INSERT");
		getFinanceiroBAR().updateBaixaTitulo(baixatitulo);
		baixatituloResponse = getFinanceiroBAR().fetchBaixaTituloById(request);
		Assert.assertEquals(baixatituloResponse.getModifyUser(), "system");
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
		TipoBaixa tipobaixa = Objects.insertTipoBaixa(7, TabelaEnum.TIPOBAIXA, PersistenceActionEnum.INSERT);
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
		TipoBaixa tipobaixa = Objects.insertTipoBaixa(1, TabelaEnum.TIPOBAIXA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		TipoBaixa tipobaixaResponse = getFinanceiroBAR().fetchTipoBaixaById(request);
		Assert.assertEquals(tipobaixaResponse.getNome(), "NATIVE INSERT");
		getFinanceiroBAR().updateTipoBaixa(tipobaixa);
		tipobaixaResponse = getFinanceiroBAR().fetchTipoBaixaById(request);
		Assert.assertEquals(tipobaixaResponse.getNome(), "nome_1 - INSERT");
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
	ContasReceber contasreceber = Objects.insertContasReceber(7, TabelaEnum.CONTASRECEBER, PersistenceActionEnum.INSERT);
	FetchByIdRequest request = new FetchByIdRequest();
	request.setFetchId(7);
	ContasReceber contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
	Conta conta1 = getFinanceiroBAR().fetchContaById(new FetchByIdRequest(contasreceber.getListBaixa().get(0).getConta().getId()));
	Assert.assertEquals(contasreceberResponse, null);
	getFinanceiroBAR().insertContasReceber(contasreceber);
	Conta conta = getFinanceiroBAR().fetchContaById(new FetchByIdRequest(contasreceber.getListBaixa().get(0).getConta().getId()));
	contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
	Assert.assertEquals(new Double( conta.getSaldo()), new Double(conta1.getSaldo() +contasreceberResponse.getListBaixa().get(0).getValor()));
	Assert.assertEquals(contasreceber.getId(), contasreceberResponse.getId());
	contasreceber = Objects.insertContasReceber(7, TabelaEnum.CONTASRECEBER, PersistenceActionEnum.DELETE);
	getFinanceiroBAR().deleteContasReceberById(contasreceber);
	contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
	conta = getFinanceiroBAR().fetchContaById(new FetchByIdRequest(contasreceber.getListBaixa().get(0).getConta().getId()));
	Assert.assertEquals(new Double(conta1.getSaldo()), new Double(conta.getSaldo()));
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
		ContasReceber contasreceber = Objects.insertContasReceber(1001, TabelaEnum.CONTASRECEBER, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		ContasReceber contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceberResponse.getNumero(), "NATIVE INSERT");
		getFinanceiroBAR().updateContasReceber(contasreceber);
		contasreceberResponse = getFinanceiroBAR().fetchContasReceberById(request);
		Assert.assertEquals(contasreceberResponse.getNumero(), "numero_1 - UPDATE");
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
		CondPag condpag = Objects.insertCondPag(4, TabelaEnum.CONDPAG, PersistenceActionEnum.INSERT);
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
		CondPag condpag = Objects.insertCondPag(1, TabelaEnum.CONDPAG, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		CondPag condpagResponse = getFinanceiroBAR().fetchCondPagById(request);
		Assert.assertEquals(condpagResponse.getNome(), "NATIVE INSERT");
		getFinanceiroBAR().updateCondPag(condpag);
		condpagResponse = getFinanceiroBAR().fetchCondPagById(request);
		Assert.assertEquals(condpagResponse.getNome(), "nome_1 - INSERT");
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
		FormaPg formapg = Objects.insertFormaPg(4000, TabelaEnum.FORMAPG, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4000);
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
		FormaPg formapg = Objects.insertFormaPg(1001, TabelaEnum.FORMAPG, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		FormaPg formapgResponse = getFinanceiroBAR().fetchFormaPgById(request);
		Assert.assertEquals(formapgResponse.getDescricao(), "NATIVE INSERT");
		getFinanceiroBAR().updateFormaPg(formapg);
		formapgResponse = getFinanceiroBAR().fetchFormaPgById(request);
		Assert.assertEquals(formapgResponse.getDescricao(), "descricao_1 - UPDATE");
	}

	@Test
	public void testFetchFormaPgsByRequest() throws Exception
	{
		// check for valid and precount
		FormaPgInquiryRequest request = new FormaPgInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		request.setEmprId(4595);
		InternalResultsResponse<FormaPg> response = getFinanceiroBAR().fetchFormaPgsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getFinanceiroBAR().fetchFormaPgsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		//Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		//Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Banco banco = insertBANCO(4001, TabelaEnum.FORMAPG, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4001);
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
		Banco banco = insertBANCO(1001, TabelaEnum.FORMAPG, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
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
		ContaCorrente contacorrente = Objects.insertContaCorrente(4002, TabelaEnum.CONTACORRENTE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4002);
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
		ContaCorrente contacorrente = Objects.insertContaCorrente(1001, TabelaEnum.CONTACORRENTE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		ContaCorrente contacorrenteResponse = getFinanceiroBAR().fetchContaCorrenteById(request);
		Assert.assertEquals(contacorrenteResponse.getNumeroConta(), "NATIVE INSERT");
		getFinanceiroBAR().updateContaCorrente(contacorrente);
		contacorrenteResponse = getFinanceiroBAR().fetchContaCorrenteById(request);
		Assert.assertEquals(contacorrenteResponse.getNumeroConta(), "numeroConta_3 - UPDATE");
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


	//===================================### CONTA ####======================================


	@Test
		public void testDeleteConta()
		{
			Conta contacorrente = Objects.insertConta(4000, TabelaEnum.CONTACORRENTE, PersistenceActionEnum.INSERT);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(4000);
			Conta contacorrenteResponse = getFinanceiroBAR().fetchContaById(request);
			Assert.assertEquals(contacorrenteResponse, null);
			getFinanceiroBAR().insertConta(contacorrente);
			contacorrenteResponse = getFinanceiroBAR().fetchContaById(request);
			Assert.assertEquals(contacorrente.getId(), contacorrenteResponse.getId());
			getFinanceiroBAR().deleteContaById(contacorrente);
			contacorrenteResponse = getFinanceiroBAR().fetchContaById(request);
			Assert.assertEquals(contacorrenteResponse, null);
		}

		@Test
		public void testFetchAllContas()
		{
		Conta contacorrente = new Conta();
			List<Conta> response = getFinanceiroBAR().fetchAllContas(contacorrente).getResultsList();
			Assert.assertNotNull(response);
		}

		@Test
		public void testDeleteAllContas()
		{
			getFinanceiroBAR().deleteAllContas();
		Conta contacorrente = new Conta();
			List<Conta> response = getFinanceiroBAR().fetchAllContas(new Conta()).getResultsList();
			Assert.assertEquals(response.size(), 0);
		}

		@Test
		public void testUpdateConta()
		{
			Conta contacorrente = Objects.insertConta(1001, TabelaEnum.CONTACORRENTE, PersistenceActionEnum.UPDATE);
			FetchByIdRequest request = new FetchByIdRequest();
			request.setFetchId(1001);
			Conta contacorrenteResponse = getFinanceiroBAR().fetchContaById(request);
			Assert.assertEquals(contacorrenteResponse.getDescricao(), "codigo_1");
			getFinanceiroBAR().updateConta(contacorrente);
			contacorrenteResponse = getFinanceiroBAR().fetchContaById(request);
			Assert.assertEquals(contacorrenteResponse.getDescricao(), "NATIVE INSERT UPDATE");
		}

		@Test
		public void testFetchContasByRequest() throws Exception
		{
			// check for valid and precount
			ContaInquiryRequest request = new ContaInquiryRequest();
			request.setPreQueryCount(true);
			request.setStartPage(0);
			request.setPageSize(3);
			InternalResultsResponse<Conta> response = getFinanceiroBAR().fetchContasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
			// check for valid and precount and start 2nd page
			request.setPreQueryCount(true);
			request.setStartPage(1);
			request.setPageSize(3);
			response = getFinanceiroBAR().fetchContasByRequest(request);
			//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
			Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

			// check for valid and no precount
			ContaInquiryRequest request2 = new ContaInquiryRequest();
			request2.setPreQueryCount(false);
			InternalResultsResponse<Conta> response2 = getFinanceiroBAR().fetchContasByRequest(request2);
			Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
			Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
			// this is because we did not choose to precount
			Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

			// check for zero rows
			getFinanceiroBAR().deleteAllContas();
			ContaInquiryRequest request3 = new ContaInquiryRequest();
			request3.setPreQueryCount(true);
			InternalResultsResponse<Conta> response3 = getFinanceiroBAR().fetchContasByRequest(request3);
			Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

		}

//===================================### CAIXA ####======================================


@Test
	public void testDeleteCaixa()
	{
		Caixa caixa =  Objects.insertCaixa(4, TabelaEnum.CAIXA, PersistenceActionEnum.INSERT);
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
		Caixa caixa = Objects.insertCaixa(1, TabelaEnum.CAIXA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Caixa caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse.getCreateUser(), "NATIVE INSERT");
		getFinanceiroBAR().updateCaixa(caixa);
		caixaResponse = getFinanceiroBAR().fetchCaixaById(request);
		Assert.assertEquals(caixaResponse.getModifyUser(), "system");
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
		executeSqlScript("conf/insertTitulo.sql", false);
		executeSqlScript("conf/insertBaixaTitulo.sql", false);
		executeSqlScript("conf/insertTipoBaixa.sql", false);
		executeSqlScript("conf/insertCondPag.sql", false);
		executeSqlScript("conf/insertFormaPg.sql", false);
		executeSqlScript("conf/insertBanco.sql", false);
		executeSqlScript("conf/insertContaCorrente.sql", false);
		executeSqlScript("conf/insertConta.sql", false);
		executeSqlScript("conf/insertCaixa.sql", false);

	}




}
