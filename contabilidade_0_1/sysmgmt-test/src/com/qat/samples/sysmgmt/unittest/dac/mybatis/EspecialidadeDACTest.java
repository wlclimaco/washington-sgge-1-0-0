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
import com.qat.samples.sysmgmt.clinica.Especialidade;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IEspecialidadeDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class EspecialidadeDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(EspecialidadeDACTest.class);
	private IEspecialidadeDAC especialidadeDAC; // injected by Spring through setter @resource

	// below

	public IEspecialidadeDAC getEspecialidadeDAC()
	{
		return especialidadeDAC;
	}

	@Resource
	public void setEspecialidadeDAC(IEspecialidadeDAC especialidadeDAC)
	{
		this.especialidadeDAC = especialidadeDAC;
	}

	@Test
	public void testupdateEspecialidade() throws Exception
	{

		Especialidade funcionario = new Especialidade();
		funcionario = insertEspecialidade(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();
		Integer a = getEspecialidadeDAC().insertEspecialidade(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Especialidade>();

		a = getEspecialidadeDAC().updateEspecialidade(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertEspecialidade() throws Exception
	{

		Especialidade funcionario = new Especialidade();
		funcionario = insertEspecialidade(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();

		Integer a = getEspecialidadeDAC().insertEspecialidade(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Especialidade();
		funcionario = insertEspecialidade(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Especialidade>();

		a = getEspecialidadeDAC().insertEspecialidade(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Especialidade> responseA =
				getEspecialidadeDAC().fetchEspecialidadeById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteEspecialidade() throws Exception
	{

		Especialidade funcionario = new Especialidade();
		funcionario = insertEspecialidade(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();
		Integer a = getEspecialidadeDAC().insertEspecialidade(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Especialidade>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEspecialidadeDAC().deleteEspecialidade(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Especialidade> responseA =
				getEspecialidadeDAC().fetchEspecialidadeById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchEspecialidadeById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Especialidade> response = getEspecialidadeDAC().fetchEspecialidadeById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchEspecialidadeById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Especialidade> response = getEspecialidadeDAC().fetchEspecialidadeById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchEspecialidadeByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Especialidade> response = getEspecialidadeDAC().fetchEspecialidadeByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Especialidade insertEspecialidade(PersistanceActionEnum action)
	{
		Especialidade exame = new Especialidade();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataEspecialidade((int)a.getTime());
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
