package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;

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
import com.qat.samples.sysmgmt.nf.dac.IConhecimentoTransporteDAC;
import com.qat.samples.sysmgmt.nf.model.ConhecimentoTransporte;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ConhecimentoTransporteDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ConhecimentoTransporteDACTest.class);
	private IConhecimentoTransporteDAC condPagDAC; // injected by Spring through setter @resource

	// below

	public IConhecimentoTransporteDAC getConhecimentoTransporteDAC()
	{
		return condPagDAC;
	}

	@Resource
	public void setConhecimentoTransporteDAC(IConhecimentoTransporteDAC condPagDAC)
	{
		this.condPagDAC = condPagDAC;
	}

	@Test
	public void testupdateConhecimentoTransporte() throws Exception
	{

		ConhecimentoTransporte funcionario = new ConhecimentoTransporte();
		funcionario = insertConhecimentoTransporte(PersistanceActionEnum.UPDATE);

		InternalResultsResponse<ConhecimentoTransporte> funcionarioResponse =
				new InternalResultsResponse<ConhecimentoTransporte>();
		getConhecimentoTransporteDAC().updateConhecimentoTransporte(funcionario, funcionarioResponse);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertConhecimentoTransporte() throws Exception
	{

		// ConhecimentoTransporte funcionario = new ConhecimentoTransporte();
		// funcionario = insertConhecimentoTransporte(PersistanceActionEnum.INSERT);
		//
		// InternalResultsResponse<ConhecimentoTransporte> funcionarioResponse =
		// getConhecimentoTransporteDAC().insertConhecimentoTransporte(funcionario);
		// assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(22);
		// InternalResultsResponse<ConhecimentoTransporte> responseA =
		// getConhecimentoTransporteDAC().fetchConhecimentoTransporteById(request);
		// assertTrue(responseA.getResultsList().size() == 1);
		// assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == StatusEnum.ANALIZANDO);

	}

	@Test
	public void testdeleteConhecimentoTransporte() throws Exception
	{

		// ConhecimentoTransporte funcionario = new ConhecimentoTransporte();
		// funcionario.setId(1);
		// funcionario = insertConhecimentoTransporte(PersistanceActionEnum.DELETE);
		// InternalResponse funcionarioResponse =
		// getConhecimentoTransporteDAC().deleteConhecimentoTransporte(funcionario);
		// assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchConhecimentoTransporteById() throws Exception
	{
		// check for valid and precount
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(3);
		// InternalResultsResponse<ConhecimentoTransporte> response =
		// getConhecimentoTransporteDAC().fetchConhecimentoTransporteById(request);
		// assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		// assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchConhecimentoTransporteByRequest() throws Exception
	{
		// check for valid and precount
		// ConhecimentoTransporteInquiryRequest request = new ConhecimentoTransporteInquiryRequest();
		// request.setPreQueryCount(true);
		// request.setStartPage(0);
		// request.setPageSize(4);
		// InternalResultsResponse<ConhecimentoTransporte> response =
		// getConhecimentoTransporteDAC().fetchConhecimentoTransporteByRequest(request);
		// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertConhecimentoTransporte.sql", false);
	}

	public ConhecimentoTransporte insertConhecimentoTransporte(PersistanceActionEnum action)
	{
		ConhecimentoTransporte exame = new ConhecimentoTransporte();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataConhecimentoTransporte((int)a.getTime());
		// exame.setMedicoResponsavel("Resposnsavel");
		// exame.setLaboratorio("Laboratorio");

		return exame;
	}
}
