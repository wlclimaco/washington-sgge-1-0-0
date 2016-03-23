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
import com.qat.samples.sysmgmt.contato.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IContatoDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ContatosDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ContatosDACTest.class);
	private IContatoDAC contatoDAC; // injected by Spring through setter @resource

	// below

	public IContatoDAC getContatoDAC()
	{
		return contatoDAC;
	}

	@Resource
	public void setContatoDAC(IContatoDAC contatoDAC)
	{
		this.contatoDAC = contatoDAC;
	}

	@Test
	public void testupdateContato() throws Exception
	{

		Contato funcionario = new Contato();
		funcionario = insertContato(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();
		Integer a = getContatoDAC().insertContato(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Contato>();

		a = getContatoDAC().updateContato(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertContato() throws Exception
	{

		Contato funcionario = new Contato();
		funcionario = insertContato(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();

		Integer a = getContatoDAC().insertContato(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Contato();
		funcionario = insertContato(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Contato>();

		a = getContatoDAC().insertContato(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Contato> responseA =
				getContatoDAC().fetchContatoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteContato() throws Exception
	{

		Contato funcionario = new Contato();
		funcionario = insertContato(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();
		Integer a = getContatoDAC().insertContato(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Contato>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getContatoDAC().deleteContato(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Contato> responseA =
				getContatoDAC().fetchContatoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchContatoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Contato> response = getContatoDAC().fetchContatoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchContatoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Contato> response = getContatoDAC().fetchContatoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchContatoByRequest() throws Exception
	{
		// check for valid and precount
		ContatoInquiryRequest request = new ContatoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Contato> response = getContatoDAC().fetchContatoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Contato insertContato(PersistanceActionEnum action)
	{
		Contato exame = new Contato();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataContato((int)a.getTime());
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
