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
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IEventosDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class EventosDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(EventosDACTest.class);
	private IEventosDAC eventosDAC; // injected by Spring through setter @resource

	// below

	public IEventosDAC getEventosDAC()
	{
		return eventosDAC;
	}

	@Resource
	public void setEventosDAC(IEventosDAC eventosDAC)
	{
		this.eventosDAC = eventosDAC;
	}

	@Test
	public void testupdateEventos() throws Exception
	{

		Eventos funcionario = new Eventos();
		funcionario = insertEventos(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		Integer a = getEventosDAC().insertEvento(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Eventos>();

		a = getEventosDAC().updateEvento(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertEventos() throws Exception
	{

		Eventos funcionario = new Eventos();
		funcionario = insertEventos(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();

		Integer a = getEventosDAC().insertEvento(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Eventos();
		funcionario = insertEventos(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Eventos>();

		a = getEventosDAC().insertEvento(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Eventos> responseA =
				getEventosDAC().fetchEventosById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteEventos() throws Exception
	{

		Eventos funcionario = new Eventos();
		funcionario = insertEventos(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();
		Integer a = getEventosDAC().insertEvento(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Eventos>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEventosDAC().deleteEvento(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Eventos> responseA =
				getEventosDAC().fetchEventosById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchEventosById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Eventos> response = getEventosDAC().fetchEventosById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchEventosById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Eventos> response = getEventosDAC().fetchEventosById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchEventosByRequest() throws Exception
	{
		// check for valid and precount
		EventoInquiryRequest request = new EventoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Eventos> response = getEventosDAC().fetchEventosByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Eventos insertEventos(PersistanceActionEnum action)
	{
		Eventos exame = new Eventos();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataEventos((int)a.getTime());
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
