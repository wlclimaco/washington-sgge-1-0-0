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
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.dac.ITabPrecoDAC;
import com.qat.samples.sysmgmt.produto.model.TabPreco;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class PrecoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(PrecoDACTest.class);
	private ITabPrecoDAC precoDAC; // injected by Spring through setter @resource

	// below

	public ITabPrecoDAC getTabPrecoDAC()
	{
		return precoDAC;
	}

	@Resource
	public void setTabPrecoDAC(ITabPrecoDAC precoDAC)
	{
		this.precoDAC = precoDAC;
	}

	@Test
	public void testupdateTabPreco() throws Exception
	{

		TabPreco funcionario = new TabPreco();
		funcionario = insertTabPreco(PersistanceActionEnum.INSERT);
		InternalResultsResponse<TabPreco> response = new InternalResultsResponse<TabPreco>();
		Integer a = getTabPrecoDAC().insertTabPreco(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<TabPreco>();

		a = getTabPrecoDAC().updateTabPreco(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertTabPreco() throws Exception
	{

		TabPreco funcionario = new TabPreco();
		funcionario = insertTabPreco(PersistanceActionEnum.INSERT);

		InternalResultsResponse<TabPreco> response = new InternalResultsResponse<TabPreco>();

		Integer a = getTabPrecoDAC().insertTabPreco(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = new TabPreco();
		funcionario = insertTabPreco(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<TabPreco>();

		a = getTabPrecoDAC().insertTabPreco(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<TabPreco> responseA =
				getTabPrecoDAC().fetchTabPrecoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteTabPreco() throws Exception
	{

		TabPreco funcionario = new TabPreco();
		funcionario = insertTabPreco(PersistanceActionEnum.INSERT);
		InternalResultsResponse<TabPreco> response = new InternalResultsResponse<TabPreco>();
		Integer a = getTabPrecoDAC().insertTabPreco(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<TabPreco>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getTabPrecoDAC().deleteTabPreco(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		// FetchByIdRequest request = new FetchByIdRequest();
		// request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<TabPreco> responseA =
				getTabPrecoDAC().fetchTabPrecoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchTabPrecoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<TabPreco> response = getTabPrecoDAC().fetchTabPrecoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchTabPrecoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<TabPreco> response = getTabPrecoDAC().fetchTabPrecoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchTabPrecoByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<TabPreco> response = getTabPrecoDAC().fetchTabPrecoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public TabPreco insertTabPreco(PersistanceActionEnum action)
	{
		TabPreco exame = new TabPreco();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataTabPreco((int)a.getTime());
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
