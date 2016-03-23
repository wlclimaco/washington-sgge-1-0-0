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
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.dac.ICnaeDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CnaeDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(CnaeDACTest.class);
	private ICnaeDAC cnaeDAC; // injected by Spring through setter @resource

	// below

	public ICnaeDAC getCnaeDAC()
	{
		return cnaeDAC;
	}

	@Resource
	public void setCnaeDAC(ICnaeDAC cnaeDAC)
	{
		this.cnaeDAC = cnaeDAC;
	}

	@Test
	public void testupdateCnae() throws Exception
	{

		Cnae funcionario = new Cnae();
		funcionario = insertCnae(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		Integer a = getCnaeDAC().insertCnae(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Cnae>();

		a = getCnaeDAC().updateCnae(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertCnae() throws Exception
	{

		Cnae funcionario = new Cnae();
		funcionario = insertCnae(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		Integer a = getCnaeDAC().insertCnae(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Cnae();
		funcionario = insertCnae(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Cnae>();

		a = getCnaeDAC().insertCnae(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Cnae> responseA = getCnaeDAC().fetchCnaeById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteCnae() throws Exception
	{

		Cnae funcionario = new Cnae();
		funcionario = insertCnae(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		Integer a = getCnaeDAC().insertCnae(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Cnae>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getCnaeDAC().deleteCnae(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Cnae> responseA =
				getCnaeDAC().fetchCnaeById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchCnaeById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Cnae> response = getCnaeDAC().fetchCnaeById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchCnaeById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Cnae> response = getCnaeDAC().fetchCnaeById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchCnaeByRequest() throws Exception
	{
		// check for valid and precount
		CnaeInquiryRequest request = new CnaeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cnae> response = getCnaeDAC().fetchCnaeByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Cnae insertCnae(PersistanceActionEnum action)
	{
		Cnae exame = new Cnae();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataCnae((int)a.getTime());
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
