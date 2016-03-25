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
import com.qat.samples.sysmgmt.produto.dac.ISubGrupoDAC;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class SubGrupoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(SubGrupoDACTest.class);
	private ISubGrupoDAC subGrupoDAC; // injected by Spring through setter @resource

	// below

	public ISubGrupoDAC getSubGrupoDAC()
	{
		return subGrupoDAC;
	}

	@Resource
	public void setSubGrupoDAC(ISubGrupoDAC subGrupoDAC)
	{
		this.subGrupoDAC = subGrupoDAC;
	}

	@Test
	public void testupdateSubGrupo() throws Exception
	{

		SubGrupo funcionario = new SubGrupo();
		funcionario = insertSubGrupo(PersistanceActionEnum.INSERT);
		InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
		Integer a = getSubGrupoDAC().insertSubGrupo(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<SubGrupo>();

		a = getSubGrupoDAC().updateSubGrupo(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertSubGrupo() throws Exception
	{

		SubGrupo funcionario = new SubGrupo();
		funcionario = insertSubGrupo(PersistanceActionEnum.INSERT);

		InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();

		Integer a = getSubGrupoDAC().insertSubGrupo(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new SubGrupo();
		funcionario = insertSubGrupo(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<SubGrupo>();

		a = getSubGrupoDAC().insertSubGrupo(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<SubGrupo> responseA =
				getSubGrupoDAC().fetchSubGrupoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteSubGrupo() throws Exception
	{

		SubGrupo funcionario = new SubGrupo();
		funcionario = insertSubGrupo(PersistanceActionEnum.INSERT);
		InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
		Integer a = getSubGrupoDAC().insertSubGrupo(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<SubGrupo>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getSubGrupoDAC().deleteSubGrupo(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<SubGrupo> responseA =
				getSubGrupoDAC().fetchSubGrupoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchSubGrupoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<SubGrupo> response = getSubGrupoDAC().fetchSubGrupoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchSubGrupoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<SubGrupo> response = getSubGrupoDAC().fetchSubGrupoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchSubGrupoByRequest() throws Exception
	{
		// check for valid and precount
		SubGrupoInquiryRequest request = new SubGrupoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<SubGrupo> response = getSubGrupoDAC().fetchSubGrupoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public SubGrupo insertSubGrupo(PersistanceActionEnum action)
	{
		SubGrupo exame = new SubGrupo();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataSubGrupo((int)a.getTime());
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
