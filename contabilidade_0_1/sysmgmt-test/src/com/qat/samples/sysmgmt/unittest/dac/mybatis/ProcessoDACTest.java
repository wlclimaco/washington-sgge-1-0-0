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
import com.qat.samples.sysmgmt.advocacia.model.Processo;
import com.qat.samples.sysmgmt.advocacia.model.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IProcessoDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ProcessoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ProcessoDACTest.class);
	private IProcessoDAC processoDAC; // injected by Spring through setter @resource

	// below

	public IProcessoDAC getProcessoDAC()
	{
		return processoDAC;
	}

	@Resource
	public void setProcessoDAC(IProcessoDAC processoDAC)
	{
		this.processoDAC = processoDAC;
	}

	@Test
	public void testupdateProcesso() throws Exception
	{

		Processo funcionario = new Processo();
		funcionario = insertProcesso(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
		Integer a = getProcessoDAC().insertProcesso(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Processo>();

		a = getProcessoDAC().updateProcesso(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertProcesso() throws Exception
	{

		Processo funcionario = new Processo();
		funcionario = insertProcesso(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();

		Integer a = getProcessoDAC().insertProcesso(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Processo();
		funcionario = insertProcesso(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Processo>();

		a = getProcessoDAC().insertProcesso(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Processo> responseA =
				getProcessoDAC().fetchProcessoById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteProcesso() throws Exception
	{

		Processo funcionario = new Processo();
		funcionario = insertProcesso(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
		Integer a = getProcessoDAC().insertProcesso(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Processo>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getProcessoDAC().deleteProcesso(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Processo> responseA =
				getProcessoDAC().fetchProcessoById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchProcessoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Processo> response = getProcessoDAC().fetchProcessoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchProcessoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Processo> response = getProcessoDAC().fetchProcessoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchProcessoByRequest() throws Exception
	{
		// check for valid and precount
		ProcessoInquiryRequest request = new ProcessoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Processo> response = getProcessoDAC().fetchProcessoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Processo insertProcesso(PersistanceActionEnum action)
	{
		Processo exame = new Processo();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataProcesso((int)a.getTime());
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
