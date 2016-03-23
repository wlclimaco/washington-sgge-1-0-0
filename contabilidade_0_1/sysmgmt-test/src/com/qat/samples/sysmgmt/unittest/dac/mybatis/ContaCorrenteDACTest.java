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
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.conta.ContaCorrente;
import com.qat.samples.sysmgmt.entidade.dac.IContaCorrenteDAC;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ContaCorrenteDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ContaCorrenteDACTest.class);
	private IContaCorrenteDAC contaCorrenteDAC; // injected by Spring through setter @resource

	// below

	public IContaCorrenteDAC getContaCorrenteDAC()
	{
		return contaCorrenteDAC;
	}

	@Resource
	public void setContaCorrenteDAC(IContaCorrenteDAC contaCorrenteDAC)
	{
		this.contaCorrenteDAC = contaCorrenteDAC;
	}

	@Test
	public void testupdateContaCorrente() throws Exception
	{

		ContaCorrente funcionario = new ContaCorrente();
		funcionario = insertContaCorrente(PersistanceActionEnum.INSERT);
		InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
		Integer a = getContaCorrenteDAC().insertContaCorrente(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<ContaCorrente>();

		a = getContaCorrenteDAC().updateContaCorrente(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertContaCorrente() throws Exception
	{

		ContaCorrente funcionario = new ContaCorrente();
		funcionario = insertContaCorrente(PersistanceActionEnum.INSERT);

		InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();

		Integer a = getContaCorrenteDAC().insertContaCorrente(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new ContaCorrente();
		funcionario = insertContaCorrente(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<ContaCorrente>();

		a = getContaCorrenteDAC().insertContaCorrente(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<ContaCorrente> responseA =
				getContaCorrenteDAC().fetchContaCorrenteById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteContaCorrente() throws Exception
	{

		ContaCorrente funcionario = new ContaCorrente();
		funcionario = insertContaCorrente(PersistanceActionEnum.INSERT);
		InternalResultsResponse<ContaCorrente> response = new InternalResultsResponse<ContaCorrente>();
		Integer a = getContaCorrenteDAC().insertContaCorrente(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<ContaCorrente>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getContaCorrenteDAC().deleteContaCorrente(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<ContaCorrente> responseA =
				getContaCorrenteDAC().fetchContaCorrenteById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchContaCorrenteById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<ContaCorrente> response = getContaCorrenteDAC().fetchContaCorrenteById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchContaCorrenteById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<ContaCorrente> response = getContaCorrenteDAC().fetchContaCorrenteById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchContaCorrenteByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<ContaCorrente> response = getContaCorrenteDAC().fetchContaCorrenteByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public ContaCorrente insertContaCorrente(PersistanceActionEnum action)
	{
		ContaCorrente exame = new ContaCorrente();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataContaCorrente((int)a.getTime());
		// exame.setMedicoResponsavel("Resposnsavel");
		// exame.setLaboratorio("Laboratorio");

		return exame;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBanco.sql", false);
	}
}
