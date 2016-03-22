package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.financeiro.dac.IFinanceiroDAC;
import com.qat.samples.sysmgmt.financeiro.model.Financeiro;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class BaixarContaDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(BaixarContaDACTest.class);
	private IFinanceiroDAC financeiroDAC; // injected by Spring through setter @resource

	// below

	public IFinanceiroDAC getFinanceiroDAC()
	{
		return financeiroDAC;
	}

	@Resource
	public void setFinanceiroDAC(IFinanceiroDAC financeiroDAC)
	{
		this.financeiroDAC = financeiroDAC;
	}

	@Test
	public void testupdateFinanceiro() throws Exception
	{

		Financeiro funcionario = new Financeiro();
		funcionario = insertFinanceiro(PersistanceActionEnum.UPDATE);

		InternalResultsResponse<Financeiro> funcionarioResponse = getFinanceiroDAC().updateFinanceiro(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertFinanceiro() throws Exception
	{

		Financeiro funcionario = new Financeiro();
		funcionario = insertFinanceiro(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Financeiro> funcionarioResponse = getFinanceiroDAC().insertFinanceiro(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(22);
		InternalResultsResponse<Financeiro> responseA = getFinanceiroDAC().fetchFinanceiroById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		// assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == StatusEnum.ANALIZANDO);

	}

	@Test
	public void testdeleteFinanceiro() throws Exception
	{

		Financeiro funcionario = new Financeiro();
		funcionario.setId(1);
		funcionario = insertFinanceiro(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getFinanceiroDAC().deleteFinanceiro(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchFinanceiroById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Financeiro> response = getFinanceiroDAC().fetchFinanceiroById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchFinanceiroByRequest() throws Exception
	{
		// check for valid and precount
		FinanceiroInquiryRequest request = new FinanceiroInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Financeiro> response = getFinanceiroDAC().fetchFinanceiroByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertFinanceiro.sql", false);
	}

	public Financeiro insertFinanceiro(PersistanceActionEnum action)
	{
		Financeiro exame = new Financeiro();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataAviso((int)a.getTime());
		// exame.setMedicoResponsavel("Resposnsavel");
		// exame.setLaboratorio("Laboratorio");

		return exame;
	}
}
