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
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IBancoDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class BancoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(BancoDACTest.class);
	private IBancoDAC bancoDAC; // injected by Spring through setter @resource

	// below

	public IBancoDAC getBancoDAC()
	{
		return bancoDAC;
	}

	@Resource
	public void setBancoDAC(IBancoDAC bancoDAC)
	{
		this.bancoDAC = bancoDAC;
	}

	@Test
	public void testupdateBanco() throws Exception
	{

		Banco funcionario = new Banco();
		funcionario = insertBanco(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
		Integer a = getEntidadeDAC().insertBanco(funcionario,"", response);
		
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		response = new InternalResultsResponse<Banco>();
		
		a = getEntidadeDAC().updateBanco(funcionario, response);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertBanco() throws Exception
	{

		Banco funcionario = new Banco();
		funcionario = insertBanco(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		Integer a = getBancoDAC().insertBanco(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		
		
		Banco funcionario = new Banco();
		funcionario = insertBanco(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		Integer a = getEntidadeDAC().insertBanco(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	//	FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Banco> responseA = getEntidadeDAC().fetchBancoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);


	}

	@Test
	public void testdeleteBanco() throws Exception
	{

		Banco funcionario = new Banco();
		funcionario = insertBanco(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();
		Integer a = getEntidadeDAC().insertBanco(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Banco>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEntidadeDAC().deleteBanco(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		//FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Classicacao> responseA = getEntidadeDAC().fetchBancoById(funcionarioResponse.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchBancoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Banco> response = getBancoDAC().fetchBancoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchBancoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Banco> response = getBancoDAC().fetchBancoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchBancoByRequest() throws Exception
	{
		// check for valid and precount
		BancoInquiryRequest request = new BancoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Banco> response = getBancoDAC().fetchBancoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Banco insertBanco(PersistanceActionEnum action)
	{
		Banco exame = new Banco();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataBanco((int)a.getTime());
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
