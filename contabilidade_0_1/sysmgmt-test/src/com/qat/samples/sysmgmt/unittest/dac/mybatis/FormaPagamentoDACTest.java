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
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IFormaPagamentoDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class FormaPagamentoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(FormaPagamentoDACTest.class);
	private IFormaPagamentoDAC formapagamentoDAC; // injected by Spring through setter @resource

	// below

	public IFormaPagamentoDAC getFormaPagamentoDAC()
	{
		return formapagamentoDAC;
	}

	@Resource
	public void setFormaPagamentoDAC(IFormaPagamentoDAC formapagamentoDAC)
	{
		this.formapagamentoDAC = formapagamentoDAC;
	}

	@Test
	public void testupdateFormaPagamento() throws Exception
	{

		FormaPg funcionario = new FormaPg();
		funcionario = insertFormaPagamento(PersistanceActionEnum.INSERT);
		InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
		Integer a = getFormaPagamentoDAC().insertFormaPg(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<FormaPg>();

		a = getFormaPagamentoDAC().updateFormaPg(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertFormaPagamento() throws Exception
	{

		FormaPg funcionario = new FormaPg();
		funcionario = insertFormaPagamento(PersistanceActionEnum.INSERT);

		InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();

		Integer a = getFormaPagamentoDAC().insertFormaPg(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new FormaPg();
		funcionario = insertFormaPagamento(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<FormaPg>();

		a = getFormaPagamentoDAC().insertFormaPg(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<FormaPg> responseA =
				getFormaPagamentoDAC().fetchFormaPgById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteFormaPagamento() throws Exception
	{

		FormaPg funcionario = new FormaPg();
		funcionario = insertFormaPagamento(PersistanceActionEnum.INSERT);
		InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
		Integer a = getFormaPagamentoDAC().insertFormaPg(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<FormaPg>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getFormaPagamentoDAC().deleteFormaPg(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<FormaPg> responseA =
				getFormaPagamentoDAC().fetchFormaPgById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchFormaPagamentoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<FormaPg> response = getFormaPagamentoDAC().fetchFormaPgById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchFormaPagamentoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<FormaPg> response = getFormaPagamentoDAC().fetchFormaPgById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchFormaPagamentoByRequest() throws Exception
	{
		// check for valid and precount
		FormaPgInquiryRequest request = new FormaPgInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<FormaPg> response = getFormaPagamentoDAC().fetchFormaPgByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public FormaPg insertFormaPagamento(PersistanceActionEnum action)
	{
		FormaPg exame = new FormaPg();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataFormaPagamento((int)a.getTime());
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
