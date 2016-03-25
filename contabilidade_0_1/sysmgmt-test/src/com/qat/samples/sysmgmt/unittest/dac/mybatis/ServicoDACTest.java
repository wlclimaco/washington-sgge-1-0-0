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
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.dac.IServicoDAC;
import com.qat.samples.sysmgmt.produto.model.PlanoByServico;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ServicoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ServicoDACTest.class);
	private IServicoDAC servicoDAC; // injected by Spring through setter @resource

	// below

	public IServicoDAC getServicoDAC()
	{
		return servicoDAC;
	}

	@Resource
	public void setServicoDAC(IServicoDAC servicoDAC)
	{
		this.servicoDAC = servicoDAC;
	}

	@Test
	public void testupdateServico() throws Exception
	{

		PlanoByServico funcionario = new PlanoByServico();
		funcionario = insertServico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<PlanoByServico> response = new InternalResultsResponse<PlanoByServico>();
		Integer a = getServicoDAC().insertServico(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<PlanoByServico>();

		a = getServicoDAC().updateServico(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertServico() throws Exception
	{

		PlanoByServico funcionario = new PlanoByServico();
		funcionario = insertServico(PersistanceActionEnum.INSERT);

		InternalResultsResponse<PlanoByServico> response = new InternalResultsResponse<PlanoByServico>();

		Integer a = getServicoDAC().insertServico(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new PlanoByServico();
		funcionario = insertServico(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<PlanoByServico>();

		a = getServicoDAC().insertServico(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<PlanoByServico> responseA =
				getServicoDAC().fetchServicoById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteServico() throws Exception
	{

		PlanoByServico funcionario = new PlanoByServico();
		funcionario = insertServico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<PlanoByServico> response = new InternalResultsResponse<PlanoByServico>();
		Integer a = getServicoDAC().insertServico(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<PlanoByServico>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getServicoDAC().deleteServico(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<PlanoByServico> responseA =
				getServicoDAC().fetchServicoById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchServicoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<PlanoByServico> response = getServicoDAC().fetchServicoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchServicoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<PlanoByServico> response = getServicoDAC().fetchServicoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchServicoByRequest() throws Exception
	{
		// check for valid and precount
		ServicoInquiryRequest request = new ServicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<PlanoByServico> response = getServicoDAC().fetchServicoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public PlanoByServico insertServico(PersistanceActionEnum action)
	{
		PlanoByServico exame = new PlanoByServico();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataServico((int)a.getTime());
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
