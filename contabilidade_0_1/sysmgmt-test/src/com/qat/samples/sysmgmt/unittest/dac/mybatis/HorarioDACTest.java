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
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IHoraFuncDAC;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class HorarioDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(HorarioDACTest.class);
	private IHoraFuncDAC horarioDAC; // injected by Spring through setter @resource

	// below

	public IHoraFuncDAC getHorarioFuncDAC()
	{
		return horarioDAC;
	}

	@Resource
	public void setHorarioFuncDAC(IHoraFuncDAC horarioDAC)
	{
		this.horarioDAC = horarioDAC;
	}

	@Test
	public void testupdateHorarioFunc() throws Exception
	{

		HorarioFunc funcionario = new HorarioFunc();
		funcionario = insertHorarioFunc(PersistanceActionEnum.INSERT);
		InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();
		Integer a = getHorarioFuncDAC().insertHorarioFunc(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<HorarioFunc>();

		a = getHorarioFuncDAC().updateHorarioFunc(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertHorarioFunc() throws Exception
	{

		HorarioFunc funcionario = new HorarioFunc();
		funcionario = insertHorarioFunc(PersistanceActionEnum.INSERT);

		InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();

		Integer a = getHorarioFuncDAC().insertHorarioFunc(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new HorarioFunc();
		funcionario = insertHorarioFunc(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<HorarioFunc>();

		a = getHorarioFuncDAC().insertHorarioFunc(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<HorarioFunc> responseA =
				getHorarioFuncDAC().fetchHorarioFuncById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteHorarioFunc() throws Exception
	{

		HorarioFunc funcionario = new HorarioFunc();
		funcionario = insertHorarioFunc(PersistanceActionEnum.INSERT);
		InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();
		Integer a = getHorarioFuncDAC().insertHorarioFunc(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<HorarioFunc>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getHorarioFuncDAC().deleteHorarioFunc(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<HorarioFunc> responseA =
				getHorarioFuncDAC().fetchHorarioFuncById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchHorarioFuncById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<HorarioFunc> response = getHorarioFuncDAC().fetchHorarioFuncById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchHorarioFuncById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<HorarioFunc> response = getHorarioFuncDAC().fetchHorarioFuncById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchHorarioFuncByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<HorarioFunc> response = getHorarioFuncDAC().fetchHorarioFuncByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public HorarioFunc insertHorarioFunc(PersistanceActionEnum action)
	{
		HorarioFunc exame = new HorarioFunc();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataHorarioFunc((int)a.getTime());
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
