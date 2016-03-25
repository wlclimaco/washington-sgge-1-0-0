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
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.dac.INotaFiscalDAC;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class NotaFiscalDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalDACTest.class);
	private INotaFiscalDAC notaFiscalDAC; // injected by Spring through setter @resource

	// below

	public INotaFiscalDAC getNotaFiscalSaidaDAC()
	{
		return notaFiscalDAC;
	}

	@Resource
	public void setNotaFiscalSaidaDAC(INotaFiscalDAC notaFiscalDAC)
	{
		this.notaFiscalDAC = notaFiscalDAC;
	}

	@Test
	public void testupdateNotaFiscalSaida() throws Exception
	{

		NotaFiscalSaida funcionario = new NotaFiscalSaida();
		funcionario = insertNotaFiscalSaida(PersistanceActionEnum.INSERT);
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
		response = getNotaFiscalSaidaDAC().insertNotaFiscalSaida(funcionario);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<NotaFiscalSaida>();

		response = getNotaFiscalSaidaDAC().updateNotaFiscalSaida(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertNotaFiscalSaida() throws Exception
	{

		NotaFiscalSaida funcionario = new NotaFiscalSaida();
		funcionario = insertNotaFiscalSaida(PersistanceActionEnum.INSERT);

		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();

		response = getNotaFiscalSaidaDAC().insertNotaFiscalSaida(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new NotaFiscalSaida();
		funcionario = insertNotaFiscalSaida(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<NotaFiscalSaida>();

		response = getNotaFiscalSaidaDAC().insertNotaFiscalSaida(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<NotaFiscalSaida> responseA =
				getNotaFiscalSaidaDAC().fetchNotaFiscalSaidaById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteNotaFiscalSaida() throws Exception
	{

		NotaFiscalSaida funcionario = new NotaFiscalSaida();
		funcionario = insertNotaFiscalSaida(PersistanceActionEnum.INSERT);
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
		response = getNotaFiscalSaidaDAC().insertNotaFiscalSaida(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<NotaFiscalSaida>();
		InternalResponse internal = new InternalResponse();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		internal = getNotaFiscalSaidaDAC().deleteNotaFiscalSaida(funcionario);
		assertEquals(internal.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<NotaFiscalSaida> responseA =
				getNotaFiscalSaidaDAC().fetchNotaFiscalSaidaById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchNotaFiscalSaidaById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<NotaFiscalSaida> response = getNotaFiscalSaidaDAC().fetchNotaFiscalSaidaById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchNotaFiscalSaidaById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<NotaFiscalSaida> response = getNotaFiscalSaidaDAC().fetchNotaFiscalSaidaById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchNotaFiscalSaidaByRequest() throws Exception
	{
		// check for valid and precount
		NotaFiscalInquiryRequest request = new NotaFiscalInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<NotaFiscalSaida> response =
				getNotaFiscalSaidaDAC().fetchNotaFiscalSaidaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public NotaFiscalSaida insertNotaFiscalSaida(PersistanceActionEnum action)
	{
		NotaFiscalSaida exame = new NotaFiscalSaida();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataNotaFiscalSaida((int)a.getTime());
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
