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
import com.qat.samples.sysmgmt.nf.dac.INFStatusDAC;
import com.qat.samples.sysmgmt.nf.model.NFStatus;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class NFStatusDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(NFStatusDACTest.class);
	private INFStatusDAC nfStatusDAC; // injected by Spring through setter @resource

	// below

	public INFStatusDAC getNFStatusDAC()
	{
		return nfStatusDAC;
	}

	@Resource
	public void setNFStatusDAC(INFStatusDAC nfStatusDAC)
	{
		this.nfStatusDAC = nfStatusDAC;
	}

	@Test
	public void testupdateNFStatus() throws Exception
	{

		NFStatus funcionario = new NFStatus();
		funcionario = insertNFStatus(PersistanceActionEnum.INSERT);
		InternalResultsResponse<NFStatus> response = new InternalResultsResponse<NFStatus>();
		Integer a = getNFStatusDAC().insertNFStatus(funcionario, "", response);

		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(response.getFirstResult().getId());
		response = new InternalResultsResponse<NFStatus>();

		a = getNFStatusDAC().updateNFStatus(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertNFStatus() throws Exception
	{

		NFStatus funcionario = new NFStatus();
		funcionario = insertNFStatus(PersistanceActionEnum.INSERT);

		InternalResultsResponse<NFStatus> response = new InternalResultsResponse<NFStatus>();

		Integer a = getNFStatusDAC().insertNFStatus(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);

		funcionario = new NFStatus();
		funcionario = insertNFStatus(PersistanceActionEnum.INSERT);
		response = new InternalResultsResponse<NFStatus>();

		a = getNFStatusDAC().insertNFStatus(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<NFStatus> responseA =
				getNFStatusDAC().fetchNFStatusById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteNFStatus() throws Exception
	{

		NFStatus funcionario = new NFStatus();
		funcionario = insertNFStatus(PersistanceActionEnum.INSERT);
		InternalResultsResponse<NFStatus> response = new InternalResultsResponse<NFStatus>();
		Integer a = getNFStatusDAC().insertNFStatus(funcionario, "", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<NFStatus>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getNFStatusDAC().deleteNFStatus(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<NFStatus> responseA =
				getNFStatusDAC().fetchNFStatusById(request);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchNFStatusById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<NFStatus> response = getNFStatusDAC().fetchNFStatusById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchNFStatusById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<NFStatus> response = getNFStatusDAC().fetchNFStatusById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchNFStatusByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<NFStatus> response = getNFStatusDAC().fetchNFStatusByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public NFStatus insertNFStatus(PersistanceActionEnum action)
	{
		NFStatus exame = new NFStatus();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataNFStatus((int)a.getTime());
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
