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
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IBeneficiosDAC;

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
	private IBeneficiosDAC avisosDAC; // injected by Spring through setter @resource

	// below

	public IBeneficiosDAC getBeneficiosDAC()
	{
		return avisosDAC;
	}

	@Resource
	public void setBeneficiosDAC(IBeneficiosDAC avisosDAC)
	{
		this.avisosDAC = avisosDAC;
	}

	@Test
	public void testupdateBeneficios() throws Exception
	{

		Beneficios funcionario = new Beneficios();
		funcionario = insertBeneficios(PersistanceActionEnum.UPDATE);

		Integer a = getBeneficiosDAC().updateBeneficios(funcionario);

	}

	@Test
	public void testinsertBeneficios() throws Exception
	{

		Beneficios funcionario = new Beneficios();
		funcionario = insertBeneficios(PersistanceActionEnum.INSERT);

		Integer a = getBeneficiosDAC().insertBeneficios(funcionario);

	}

	@Test
	public void testdeleteBeneficios() throws Exception
	{

		Beneficios funcionario = new Beneficios();
		funcionario.setId(1);
		funcionario = insertBeneficios(PersistanceActionEnum.DELETE);
		Integer a = getBeneficiosDAC().deleteBeneficios(funcionario);

	}

	@Test
	public void testfetchBeneficiosById() throws Exception
	{
		// check for valid and precount
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(3);
		// InternalResultsResponse<Beneficios> response = getBeneficiosDAC().fetchBeneficiosByRequest(request)
		// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		// assertEquals(response.getStatus(), Status.OperationSuccess);
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
