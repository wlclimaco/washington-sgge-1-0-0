package com.qat.samples.sysmgmt.unittest.bai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.bai.IProcedureBAI;
import com.qat.samples.sysmgmt.model.Procedure;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/procedurebaiimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class ProcedureBAIImplTest extends AbstractJUnit4SpringContextTests
{

	private IProcedureBAI procedureBAI;

	private static final String ID_REQUIRED = "sysmgmt.base.procedurevalidator.id.required";
	private static final String PROCDESC_REQUIRED = "sysmgmt.base.procedurevalidator.procdesc.required";
	private static final String PROCCODE_REQUIRED = "sysmgmt.base.procedurevalidator.proccode.required";
	private static final String DESCRIPTION_VALUE = "description";
	private static final String CODE_VALUE = "code";
	private static final String FETCH_FAILURE = "fetchFailure";
	private static final String FAILURE = "failure";
	private static final String BAC_KEY = "sysmgmt.base.validator.id.required";
	private static final String BAID_KEY = "sysmgmt.base.procedurebaidimpl.defaultexception";
	private static final String BAI_KEY = "sysmgmt.base.procedurebaiimpl.defaultexception";
	private static final String EXCEPTION = "exception";

	public IProcedureBAI getProcedureBAI()
	{
		return procedureBAI;
	}

	@Resource
	public void setProcedureBAI(IProcedureBAI procedureBAI)
	{
		this.procedureBAI = procedureBAI;
	}

	@Test
	public void testInsert()
	{
		Procedure procedure = new Procedure();
		procedure.setCode(CODE_VALUE);
		procedure.setDescription(DESCRIPTION_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, true, true);
		ProcedureResponse response = getProcedureBAI().insertProcedure(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchAll()
	{
		ProcedureResponse response = getProcedureBAI().fetchAllProcedures(new FetchAllRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchByRequest()
	{
		ProcedureResponse response = getProcedureBAI().fetchProceduresByRequest(new PagedInquiryRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testRefresh()
	{
		RefreshRequest request = new RefreshRequest(4, false, false);
		ProcedureResponse response = getProcedureBAI().refreshProcedures(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		procedure.setCode(CODE_VALUE);
		procedure.setDescription(DESCRIPTION_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, true, false);
		ProcedureResponse response = getProcedureBAI().updateProcedure(request);
		assertTrue(response.getMessageList().isEmpty());
		assertFalse(response.getProcedures().isEmpty());
	}

	@Test
	public void testInsertNoProcedureCode()
	{
		Procedure procedure = new Procedure();
		procedure.setDescription(DESCRIPTION_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().insertProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(PROCCODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertNoProcedureDescription()
	{
		Procedure procedure = new Procedure();
		procedure.setCode(CODE_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().insertProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(PROCDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoProcedureId()
	{
		Procedure procedure = new Procedure();
		procedure.setCode(CODE_VALUE);
		procedure.setDescription(DESCRIPTION_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().updateProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteNoProcedureId()
	{
		Procedure procedure = new Procedure();
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().deleteProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdNoProcedureId()
	{
		FetchByIdRequest request = new FetchByIdRequest();
		ProcedureResponse response = getProcedureBAI().fetchProcedureById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoProcedureCode()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		procedure.setDescription(DESCRIPTION_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().updateProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(PROCCODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoProcedureDescription()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		procedure.setCode(CODE_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().updateProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(PROCDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertFailure()
	{
		Procedure procedure = new Procedure();
		procedure.setCode(CODE_VALUE);
		procedure.setDescription(DESCRIPTION_VALUE);
		MockProcedureBAC.setReturnResult(FAILURE);
		MockProcedureBAC.setFailureMessageCode(BAC_KEY);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().insertProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteFailure()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		MockProcedureBAC.setReturnResult(FAILURE);
		MockProcedureBAC.setFailureMessageCode(BAC_KEY);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().deleteProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdFailure()
	{
		FetchByIdRequest request = new FetchByIdRequest(new Integer(1));
		MockProcedureBAC.setReturnResult(FETCH_FAILURE);
		MockProcedureBAC.setFailureMessageCode(BAC_KEY);
		ProcedureResponse response = getProcedureBAI().fetchProcedureById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchById()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);
		ProcedureResponse response = getProcedureBAI().fetchProcedureById(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testDelete()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().deleteProcedure(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertException()
	{
		Procedure procedure = new Procedure();
		procedure.setCode(CODE_VALUE);
		procedure.setDescription(DESCRIPTION_VALUE);
		MockProcedureBAC.setReturnResult(EXCEPTION);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().insertProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteException()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		MockProcedureBAC.setReturnResult(EXCEPTION);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().deleteProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);
		MockProcedureBAC.setReturnResult(EXCEPTION);
		ProcedureResponse response = getProcedureBAI().fetchProcedureById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllException()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockProcedureBAC.setReturnResult(EXCEPTION);
		ProcedureResponse response = getProcedureBAI().fetchAllProcedures(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchProceduresByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockProcedureBAC.setReturnResult(EXCEPTION);
		ProcedureResponse response = getProcedureBAI().fetchProceduresByRequest(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testRefreshException()
	{
		RefreshRequest request = new RefreshRequest(4, true, false);
		MockProcedureBAC.setReturnResult(EXCEPTION);
		ProcedureResponse response = getProcedureBAI().refreshProcedures(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchProceduresByRequestFailure()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockProcedureBAC.setReturnResult(FETCH_FAILURE);
		ProcedureResponse response = getProcedureBAI().fetchProceduresByRequest(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllFailure()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockProcedureBAC.setReturnResult(FETCH_FAILURE);
		ProcedureResponse response = getProcedureBAI().fetchAllProcedures(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateFailure()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		procedure.setCode(CODE_VALUE);
		procedure.setDescription(DESCRIPTION_VALUE);
		MockProcedureBAC.setReturnResult(FAILURE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		ProcedureResponse response = getProcedureBAI().updateProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateException()
	{
		Procedure procedure = new Procedure();
		procedure.setId(1);
		procedure.setCode(CODE_VALUE);
		procedure.setDescription(DESCRIPTION_VALUE);
		ProcedureMaintenanceRequest request = new ProcedureMaintenanceRequest(procedure, false, false);
		MockProcedureBAC.setReturnResult(EXCEPTION);
		ProcedureResponse response = getProcedureBAI().updateProcedure(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Before
	public void setUp()
	{
		MockProcedureBAC.setReturnResult(null);
	}

	public static class MockProcedureBAC implements IProcedureBAC
	{
		private static String returnResult;
		private static String failureMessageCode;

		public static String getReturnResult()
		{
			return returnResult;
		}

		public static void setReturnResult(String newValue)
		{
			returnResult = newValue;
		}

		public static String getFailureMessageCode()
		{
			return failureMessageCode;
		}

		public static void setFailureMessageCode(String failureMessageCode)
		{
			MockProcedureBAC.failureMessageCode = failureMessageCode;
		}

		@Override
		public InternalResponse deleteProcedure(Procedure procedure)
		{
			return createInternalResponse(procedure);
		}

		private InternalResponse createInternalResponse(Procedure procedure)
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResponse response = new InternalResponse();
			if (FAILURE.equals(getReturnResult()))
			{
				response.addMessage(getFailureMessageCode(), MessageSeverity.Error, MessageLevel.Other);
				response.setStatus(Status.UnspecifiedError);
			}
			return response;
		}

		@Override
		public InternalResultsResponse<Procedure> fetchAllProcedures()
		{
			return createFetchResponse();
		}

		private InternalResultsResponse<Procedure> createFetchResponse()
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Procedure> response = new InternalResultsResponse<Procedure>();
			if (FETCH_FAILURE.equals(getReturnResult()))
			{
				response.addMessage(getFailureMessageCode(), MessageSeverity.Error, MessageLevel.Other);
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Procedure());
			return response;
		}

		@Override
		public InternalResultsResponse<Procedure> fetchProcedureById(FetchByIdRequest request)
		{
			return createFetchResponse();
		}

		@Override
		public InternalResultsResponse<Procedure> fetchProceduresByRequest(PagedInquiryRequest request)
		{
			return createFetchResponse();
		}

		@Override
		public InternalResponse insertProcedure(Procedure procedure)
		{
			return createInternalResponse(procedure);
		}

		@Override
		public void refreshProcedures(Integer count)
		{

		}

		@Override
		public InternalResponse updateProcedure(Procedure procedure)
		{
			return createInternalResponse(procedure);
		}

	}
}
