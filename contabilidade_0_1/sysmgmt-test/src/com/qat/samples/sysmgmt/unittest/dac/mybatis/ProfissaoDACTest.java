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
import com.qat.samples.sysmgmt.dp.Profissao;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IProfissaoDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ProfissaoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ProfissaoDACTest.class);
	private IProfissaoDAC profissaoDAC; // injected by Spring through setter @resource

	// below

	public IProfissaoDAC getProfissaoDAC()
	{
		return profissaoDAC;
	}

	@Resource
	public void setProfissaoDAC(IProfissaoDAC profissaoDAC)
	{
		this.profissaoDAC = profissaoDAC;
	}

	@Test
	public void testupdateProfissao() throws Exception
	{

		Profissao funcionario = new Profissao();
		funcionario = insertProfissao(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Profissao> response = new InternalResultsResponse<Profissao>();
		Integer a = getProfissaoDAC().insertProfissao(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Profissao>();

		a = getProfissaoDAC().updateProfissao(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertProfissao() throws Exception
	{

		Profissao funcionario = new Profissao();
		funcionario = insertProfissao(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Profissao> response = new InternalResultsResponse<Profissao>();

		Integer a = getProfissaoDAC().insertProfissao(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Profissao();
		funcionario = insertProfissao(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Profissao>();

		a = getProfissaoDAC().insertProfissao(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Profissao> responseA =
				getProfissaoDAC().fetchProfissaoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteProfissao() throws Exception
	{

		Profissao funcionario = new Profissao();
		funcionario = insertProfissao(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Profissao> response = new InternalResultsResponse<Profissao>();
		Integer a = getProfissaoDAC().insertProfissao(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Profissao>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getProfissaoDAC().deleteProfissao(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Profissao> responseA =
				getProfissaoDAC().fetchProfissaoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchProfissaoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Profissao> response = getProfissaoDAC().fetchProfissaoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchProfissaoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Profissao> response = getProfissaoDAC().fetchProfissaoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchProfissaoByRequest() throws Exception
	{
		// check for valid and precount
		ProfissaoInquiryRequest request = new ProfissaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Profissao> response = getProfissaoDAC().fetchProfissaoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Profissao insertProfissao(PersistanceActionEnum action)
	{
		Profissao exame = new Profissao();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataProfissao((int)a.getTime());
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
