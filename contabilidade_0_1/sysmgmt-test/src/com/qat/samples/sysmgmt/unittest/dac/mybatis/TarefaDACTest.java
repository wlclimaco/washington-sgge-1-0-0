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
import com.qat.samples.sysmgmt.entidade.dac.ITarefaDAC;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Tarefa;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class TarefaDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(TarefaDACTest.class);
	private ITarefaDAC tarefaDAC; // injected by Spring through setter @resource

	// below

	public ITarefaDAC getTarefaDAC()
	{
		return tarefaDAC;
	}

	@Resource
	public void setTarefaDAC(ITarefaDAC tarefaDAC)
	{
		this.tarefaDAC = tarefaDAC;
	}

	@Test
	public void testupdateTarefa() throws Exception
	{

		Tarefa funcionario = new Tarefa();
		funcionario = insertTarefa(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
		Integer a = getTarefaDAC().insertTarefa(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Tarefa>();

		a = getTarefaDAC().updateTarefa(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertTarefa() throws Exception
	{

		Tarefa funcionario = new Tarefa();
		funcionario = insertTarefa(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();

		Integer a = getTarefaDAC().insertTarefa(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Tarefa();
		funcionario = insertTarefa(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Tarefa>();

		a = getTarefaDAC().insertTarefa(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Tarefa> responseA = getTarefaDAC().fetchTarefaById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteTarefa() throws Exception
	{

		Tarefa funcionario = new Tarefa();
		funcionario = insertTarefa(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
		Integer a = getTarefaDAC().insertTarefa(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Tarefa>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getTarefaDAC().deleteTarefa(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Tarefa> responseA =
				getTarefaDAC().fetchTarefaById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchTarefaById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Tarefa> response = getTarefaDAC().fetchTarefaById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchTarefaById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Tarefa> response = getTarefaDAC().fetchTarefaById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchTarefaByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Tarefa> response = getTarefaDAC().fetchTarefaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Tarefa insertTarefa(PersistanceActionEnum action)
	{
		Tarefa exame = new Tarefa();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataTarefa((int)a.getTime());
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
