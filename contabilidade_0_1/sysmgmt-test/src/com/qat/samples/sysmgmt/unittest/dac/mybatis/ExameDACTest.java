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
import com.qat.samples.sysmgmt.clinica.Exame;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IExameDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ExameDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ExameDACTest.class);
	private IExameDAC enderecoDAC; // injected by Spring through setter @resource

	// below

	public IExameDAC getExameDAC()
	{
		return enderecoDAC;
	}

	@Resource
	public void setExameDAC(IExameDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	@Test
	public void testupdateExame() throws Exception
	{

		Exame funcionario = new Exame();
		funcionario = insertExame(PersistanceActionEnum.UPDATE);

		Integer a = getExameDAC().updateExame(funcionario);

	}

	@Test
	public void testinsertExame() throws Exception
	{

		Exame funcionario = new Exame();
		funcionario = insertExame(PersistanceActionEnum.INSERT);

		Integer a = getExameDAC().insertExame(funcionario);

	}

	@Test
	public void testdeleteExame() throws Exception
	{

		Exame funcionario = new Exame();
		funcionario.setId(1);
		funcionario = insertExame(PersistanceActionEnum.DELETE);
		Integer a = getExameDAC().deleteExame(funcionario);

	}

	@Test
	public void testfetchExameById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Exame> response = getExameDAC().fetchExameById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchExameByRequest() throws Exception
	{
		// check for valid and precount
		ExameInquiryRequest request = new ExameInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Exame> response = getExameDAC().fetchExameByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Exame insertExame(PersistanceActionEnum action)
	{
		Exame exame = new Exame();
		Date a = new Date();
		exame.setId(1);
		exame.setNome("Nome");
		// exame.setDataExame((int)a.getTime());
		exame.setMedicoResponsavel("Resposnsavel");
		exame.setLaboratorio("Laboratorio");

		return exame;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertExame.sql", false);
	}
}
