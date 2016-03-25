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
import com.qat.samples.sysmgmt.produto.dac.IGrupoDAC;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class GrupoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(GrupoDACTest.class);
	private IGrupoDAC grupoDAC; // injected by Spring through setter @resource

	// below

	public IGrupoDAC getGrupoDAC()
	{
		return grupoDAC;
	}

	@Resource
	public void setGrupoDAC(IGrupoDAC grupoDAC)
	{
		this.grupoDAC = grupoDAC;
	}

	@Test
	public void testupdateGrupo() throws Exception
	{

		Grupo funcionario = new Grupo();
		funcionario = insertGrupo(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
		Integer a = getGrupoDAC().insertGrupo(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Grupo>();

		a = getGrupoDAC().updateGrupo(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertGrupo() throws Exception
	{

		Grupo funcionario = new Grupo();
		funcionario = insertGrupo(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();

		Integer a = getGrupoDAC().insertGrupo(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Grupo();
		funcionario = insertGrupo(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Grupo>();

		a = getGrupoDAC().insertGrupo(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Grupo> responseA = getGrupoDAC().fetchGrupoById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteGrupo() throws Exception
	{

		Grupo funcionario = new Grupo();
		funcionario = insertGrupo(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
		Integer a = getGrupoDAC().insertGrupo(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Grupo>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getGrupoDAC().deleteGrupo(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Grupo> responseA =
				getGrupoDAC().fetchGrupoById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchGrupoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Grupo> response = getGrupoDAC().fetchGrupoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchGrupoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Grupo> response = getGrupoDAC().fetchGrupoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchGrupoByRequest() throws Exception
	{
		// check for valid and precount
		GrupoInquiryRequest request = new GrupoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Grupo> response = getGrupoDAC().fetchGrupoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Grupo insertGrupo(PersistanceActionEnum action)
	{
		Grupo exame = new Grupo();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataGrupo((int)a.getTime());
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
