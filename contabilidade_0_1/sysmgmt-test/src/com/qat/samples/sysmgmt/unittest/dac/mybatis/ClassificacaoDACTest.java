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
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.dac.IClassificacaoDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ClassificacaoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ClassificacaoDACTest.class);
	private IClassificacaoDAC classificacaoDAC; // injected by Spring through setter @resource

	// below

	public IClassificacaoDAC getClassificacaoDAC()
	{
		return classificacaoDAC;
	}

	@Resource
	public void setClassificacaoDAC(IClassificacaoDAC classificacaoDAC)
	{
		this.classificacaoDAC = classificacaoDAC;
	}

	@Test
	public void testupdateClassificacao() throws Exception
	{

		Classificacao funcionario = new Classificacao();
		funcionario = insertClassificacao(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Classificacao> response = new InternalResultsResponse<Classificacao>();
		Integer a = getClassificacaoDAC().insertClassificacao(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Classificacao>();

		a = getClassificacaoDAC().updateClassificacao(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertClassificacao() throws Exception
	{

		Classificacao funcionario = new Classificacao();
		funcionario = insertClassificacao(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Classificacao> response = new InternalResultsResponse<Classificacao>();

		Integer a = getClassificacaoDAC().insertClassificacao(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Classificacao();
		funcionario = insertClassificacao(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Classificacao>();

		a = getClassificacaoDAC().insertClassificacao(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Classificacao> responseA =
				getClassificacaoDAC().fetchClassificacaoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteClassificacao() throws Exception
	{

		Classificacao funcionario = new Classificacao();
		funcionario = insertClassificacao(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Classificacao> response = new InternalResultsResponse<Classificacao>();
		Integer a = getClassificacaoDAC().insertClassificacao(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Classificacao>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getClassificacaoDAC().deleteClassificacao(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Classificacao> responseA =
				getClassificacaoDAC().fetchClassificacaoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchClassificacaoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Classificacao> response = getClassificacaoDAC().fetchClassificacaoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchClassificacaoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Classificacao> response = getClassificacaoDAC().fetchClassificacaoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchClassificacaoByRequest() throws Exception
	{
		// check for valid and precount
		ClassificacaoInquiryRequest request = new ClassificacaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Classificacao> response = getClassificacaoDAC().fetchClassificacaoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Classificacao insertClassificacao(PersistanceActionEnum action)
	{
		Classificacao exame = new Classificacao();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataClassificacao((int)a.getTime());
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
