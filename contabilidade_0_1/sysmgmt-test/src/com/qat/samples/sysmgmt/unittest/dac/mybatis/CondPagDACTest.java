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
import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.ICondPagDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CondPagDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(CondPagDACTest.class);
	private ICondPagDAC avisosDAC; // injected by Spring through setter @resource

	// below

	public ICondPagDAC getCondPagDAC()
	{
		return avisosDAC;
	}

	@Resource
	public void setCondPagDAC(ICondPagDAC avisosDAC)
	{
		this.avisosDAC = avisosDAC;
	}

	@Test
	public void testupdateCondPag() throws Exception
	{

		CondPag funcionario = new CondPag();
		funcionario = insertCondPag(PersistanceActionEnum.UPDATE);

		Integer a = getCondPagDAC().updateCondPag(funcionario);

	}

	@Test
	public void testinsertCondPag() throws Exception
	{

		CondPag funcionario = new CondPag();
		funcionario = insertCondPag(PersistanceActionEnum.INSERT);

		Integer a = getCondPagDAC().insertCondPag(funcionario);

	}

	@Test
	public void testdeleteCondPag() throws Exception
	{

		CondPag funcionario = new CondPag();
		funcionario.setId(1);
		funcionario = insertCondPag(PersistanceActionEnum.DELETE);
		Integer a = getCondPagDAC().deleteCondPag(funcionario);

	}

	@Test
	public void testfetchCondPagById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<CondPag> response = getCondPagDAC().fetchCondPagById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchCondPagByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<CondPag> response = getCondPagDAC().fetchCondPagByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public CondPag insertCondPag(PersistanceActionEnum action)
	{
		CondPag exame = new CondPag();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataCondPag((int)a.getTime());
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
