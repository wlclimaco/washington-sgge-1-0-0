package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import com.qat.samples.sysmgmt.nf.dac.IOrdemServicoDAC;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class OrdemServicoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(OrdemServicoDACTest.class);
	private IOrdemServicoDAC ordemServicoDAC; // injected by Spring through setter @resource

	// below

	public IOrdemServicoDAC getOrdemServicoDAC()
	{
		return ordemServicoDAC;
	}

	@Resource
	public void setOrdemServicoDAC(IOrdemServicoDAC ordemServicoDAC)
	{
		this.ordemServicoDAC = ordemServicoDAC;
	}

	@Test
	public void testupdateOrdemServico() throws Exception
	{

		OrdemServico funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
		response = getOrdemServicoDAC().insertOrdemServico(funcionario);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<OrdemServico>();

		response = getOrdemServicoDAC().updateOrdemServico(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertOrdemServico() throws Exception
	{

		OrdemServico funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);

		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		response = getOrdemServicoDAC().insertOrdemServico(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<OrdemServico>();

		response = getOrdemServicoDAC().insertOrdemServico(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<OrdemServico> responseA =
				getOrdemServicoDAC().fetchOrdemServicoById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteOrdemServico() throws Exception
	{

		OrdemServico funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
		response = getOrdemServicoDAC().insertOrdemServico(funcionario);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<OrdemServico>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		InternalResponse internal = new InternalResponse();
		internal = getOrdemServicoDAC().deleteOrdemServico(funcionario);
		assertEquals(internal.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<OrdemServico> responseA =
				getOrdemServicoDAC().fetchOrdemServicoById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchOrdemServicoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<OrdemServico> response = getOrdemServicoDAC().fetchOrdemServicoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchOrdemServicoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<OrdemServico> response = getOrdemServicoDAC().fetchOrdemServicoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchOrdemServicoByRequest() throws Exception
	{
		// check for valid and precount
		OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<OrdemServico> response = getOrdemServicoDAC().fetchOrdemServicoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public OrdemServico insertOrdemServico(PersistanceActionEnum action)
	{
		OrdemServico exame = new OrdemServico();
		// Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataOrdemServico((int)a.getTime());
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
