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
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.dac.ICidadeDAC;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CidadeDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(CidadeDACTest.class);
	private ICidadeDAC cidadeDAC; // injected by Spring through setter @resource

	// below

	public ICidadeDAC getCidadeDAC()
	{
		return cidadeDAC;
	}

	@Resource
	public void setCidadeDAC(ICidadeDAC cidadeDAC)
	{
		this.cidadeDAC = cidadeDAC;
	}

	@Test
	public void testupdateCidade() throws Exception
	{

		Cidade funcionario = new Cidade();
		funcionario = insertCidade(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
		Integer a = getEntidadeDAC().insertCidade(funcionario,"", response);
		
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		response = new InternalResultsResponse<Cidade>();
		
		a = getEntidadeDAC().updateCidade(funcionario, response);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertCidade() throws Exception
	{

		Cidade funcionario = new Cidade();
		funcionario = insertCidade(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		Integer a = getCidadeDAC().insertCidade(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		
		
		Cidade funcionario = new Cidade();
		funcionario = insertCidade(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		Integer a = getEntidadeDAC().insertCidade(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	//	FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Cidade> responseA = getEntidadeDAC().fetchCidadeById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);


	}

	@Test
	public void testdeleteCidade() throws Exception
	{

		Cidade funcionario = new Cidade();
		funcionario = insertCidade(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
		Integer a = getEntidadeDAC().insertCidade(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Cidade>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEntidadeDAC().deleteCidade(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		//FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Classicacao> responseA = getEntidadeDAC().fetchCidadeById(funcionarioResponse.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchCidadeById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Cidade> response = getCidadeDAC().fetchCidadeById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchCidadeById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Cidade> response = getCidadeDAC().fetchCidadeById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchCidadeByRequest() throws Exception
	{
		// check for valid and precount
		CidadeInquiryRequest request = new CidadeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cidade> response = getCidadeDAC().fetchCidadeByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Cidade insertCidade(PersistanceActionEnum action)
	{
		Cidade exame = new Cidade();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataCidade((int)a.getTime());
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
