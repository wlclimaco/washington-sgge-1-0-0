package com.qat.samples.sysmgmt.unittest.dac.mybatis;

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
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.produto.dac.ICfopDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CfopDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(CfopDACTest.class);
	private ICfopDAC avisosDAC; // injected by Spring through setter @resource

	// below

	public ICfopDAC getCfopDAC()
	{
		return avisosDAC;
	}

	@Resource
	public void setCfopDAC(ICfopDAC avisosDAC)
	{
		this.avisosDAC = avisosDAC;
	}

	@Test
	public void testupdateCfop() throws Exception
	{

		Cfop funcionario = new Cfop();
		funcionario = insertCfop(PersistanceActionEnum.UPDATE);

		Integer a = getCfopDAC().updateCfop(funcionario);

	}

	@Test
	public void testinsertCfop() throws Exception
	{

		Cfop funcionario = new Cfop();
		funcionario = insertCfop(PersistanceActionEnum.INSERT);

		Integer a = getCfopDAC().insertCfop(funcionario);

	}

	@Test
	public void testdeleteCfop() throws Exception
	{

		Cfop funcionario = new Cfop();
		funcionario.setId(1);
		funcionario = insertCfop(PersistanceActionEnum.DELETE);
		Integer a = getCfopDAC().deleteCfop(funcionario);

	}

	@Test
	public void testfetchCfopById() throws Exception
	{
		// check for valid and precount
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(3);
		// InternalResultsResponse<Cfop> response = getCfopDAC().fetchCfopById(request);
		// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		// assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchCfopByRequest() throws Exception
	{
		// check for valid and precount
		CfopInquiryRequest request = new CfopInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cfop> response = getCfopDAC().fetchCfopByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Cfop insertCfop(PersistanceActionEnum action)
	{
		Cfop exame = new Cfop();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataCfop((int)a.getTime());
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
