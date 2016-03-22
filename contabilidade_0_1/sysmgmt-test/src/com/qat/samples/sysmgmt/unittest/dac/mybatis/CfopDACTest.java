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
	private ICfopDAC cfopDAC; // injected by Spring through setter @resource

	// below

	public ICfopDAC getCfopDAC()
	{
		return cfopDAC;
	}

	@Resource
	public void setCfopDAC(ICfopDAC cfopDAC)
	{
		this.cfopDAC = cfopDAC;
	}

	@Test
	public void testupdateCfop() throws Exception
	{

		Cfop funcionario = new Cfop();
		funcionario = insertCfop(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
		Integer a = getEntidadeDAC().insertCfop(funcionario,"", response);
		
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		response = new InternalResultsResponse<Cfop>();
		
		a = getEntidadeDAC().updateCfop(funcionario, response);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertCfop() throws Exception
	{

		Cfop funcionario = new Cfop();
		funcionario = insertCfop(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();

		Integer a = getCfopDAC().insertCfop(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		
		
		Cfop funcionario = new Cfop();
		funcionario = insertCfop(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();

		Integer a = getEntidadeDAC().insertCfop(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	//	FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Cfop> responseA = getEntidadeDAC().fetchCfopById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);


	}

	@Test
	public void testdeleteCfop() throws Exception
	{

		Cfop funcionario = new Cfop();
		funcionario = insertCfop(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
		Integer a = getEntidadeDAC().insertCfop(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Cfop>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEntidadeDAC().deleteCfop(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		//FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Classicacao> responseA = getEntidadeDAC().fetchCfopById(funcionarioResponse.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchCfopById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Cfop> response = getCfopDAC().fetchCfopById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchCfopById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Cfop> response = getCfopDAC().fetchCfopById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
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
