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
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IBeneficiosDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class BeneficiosDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(BeneficiosDACTest.class);
	private IBeneficiosDAC beneficiosDAC; // injected by Spring through setter @resource

	// below

	public IBeneficiosDAC getBeneficiosDAC()
	{
		return beneficiosDAC;
	}

	@Resource
	public void setBeneficiosDAC(IBeneficiosDAC beneficiosDAC)
	{
		this.beneficiosDAC = beneficiosDAC;
	}

	@Test
	public void testupdateBeneficios() throws Exception
	{

		Beneficios funcionario = new Beneficios();
		funcionario = insertBeneficios(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
		Integer a = getBeneficiosDAC().insertBeneficios(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<Beneficios>();

		a = getBeneficiosDAC().updateBeneficios(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertBeneficios() throws Exception
	{

		Beneficios funcionario = new Beneficios();
		funcionario = insertBeneficios(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		Integer a = getBeneficiosDAC().insertBeneficios(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new Beneficios();
		funcionario = insertBeneficios(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<Beneficios>();

		a = getBeneficiosDAC().insertBeneficios(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Beneficios> responseA =
				getBeneficiosDAC().fetchBeneficiosById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteBeneficios() throws Exception
	{

		Beneficios funcionario = new Beneficios();
		funcionario = insertBeneficios(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();
		Integer a = getBeneficiosDAC().insertBeneficios(funcionario, null, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Beneficios>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getBeneficiosDAC().deleteBeneficios(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Beneficios> responseA =
				getBeneficiosDAC().fetchBeneficiosById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchBeneficiosById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Beneficios> response = getBeneficiosDAC().fetchBeneficiosById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchBeneficiosById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Beneficios> response = getBeneficiosDAC().fetchBeneficiosById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchBeneficiosByRequest() throws Exception
	{
		// check for valid and precount
		BeneficiosInquiryRequest request = new BeneficiosInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Beneficios> response = getBeneficiosDAC().fetchBeneficiosByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Beneficios insertBeneficios(PersistanceActionEnum action)
	{
		Beneficios exame = new Beneficios();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataBeneficios((int)a.getTime());
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
