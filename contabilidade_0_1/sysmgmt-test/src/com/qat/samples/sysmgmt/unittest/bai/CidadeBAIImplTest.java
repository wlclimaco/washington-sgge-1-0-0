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
import com.qat.samples.sysmgmt.cidade.bac.ICidadeBAC;
import com.qat.samples.sysmgmt.cidade.bai.ICidadeBAI;
import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/cidadebaiimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class CidadeBAIImplTest extends AbstractJUnit4SpringContextTests
{

	private ICidadeBAI cidadeBAI;

	private static final String ID_REQUIRED = "sysmgmt.base.cidadevalidator.id.required";
	private static final String BUNDLEDESC_REQUIRED = "sysmgmt.base.cidadevalidator.cidadedesc.required";
	private static final String BUNDLECODE_REQUIRED = "sysmgmt.base.cidadevalidator.cidadecode.required";
	private static final String DESCRIPTION_VALUE = "description";
	private static final String CODE_VALUE = "code";
	private static final String FETCH_FAILURE = "fetchFailure";
	private static final String FAILURE = "failure";
	private static final String BAC_KEY = "sysmgmt.base.validator.id.required";
	private static final String BAID_KEY = "sysmgmt.base.cidadebaidimpl.defaultexception";
	private static final String BAI_KEY = "sysmgmt.base.cidadebaiimpl.defaultexception";
	private static final String EXCEPTION = "exception";

	public ICidadeBAI getCidadeBAI()
	{
		return cidadeBAI;
	}

	@Resource
	public void setCidadeBAI(ICidadeBAI cidadeBAI)
	{
		this.cidadeBAI = cidadeBAI;
	}

	@Test
	public void testInsert()
	{
		Cidade cidade = new Cidade();
		cidade.setEstado(CODE_VALUE);
		cidade.setCidade(DESCRIPTION_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, true, true);
		CidadeResponse response = getCidadeBAI().insertCidade(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchAll()
	{
		CidadeResponse response = getCidadeBAI().fetchAllCidades(new FetchAllRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchByRequest()
	{
		CidadeResponse response = getCidadeBAI().fetchCidadesByRequest(new PagedInquiryRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testRefresh()
	{
		RefreshRequest request = new RefreshRequest(4, false, false);
		CidadeResponse response = getCidadeBAI().refreshCidades(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setEstado(CODE_VALUE);
		cidade.setCidade(DESCRIPTION_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, true, false);
		CidadeResponse response = getCidadeBAI().updateCidade(request);
		assertTrue(response.getMessageList().isEmpty());
		assertFalse(response.getCidades().isEmpty());
	}

	@Test
	public void testInsertNoCidadeCode()
	{
		Cidade cidade = new Cidade();
		cidade.setEstado(CODE_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().insertCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLECODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertNoCidadeDescription()
	{
		Cidade cidade = new Cidade();
		cidade.setCidade(DESCRIPTION_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().insertCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLEDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoCidadeId()
	{
		Cidade cidade = new Cidade();
		cidade.setEstado(CODE_VALUE);
		cidade.setCidade(DESCRIPTION_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().updateCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteNoCidadeId()
	{
		Cidade cidade = new Cidade();
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().deleteCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdNoCidadeId()
	{
		new FetchByIdRequest();
	}

	@Test
	public void testUpdateNoCidadeCode()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setCidade(DESCRIPTION_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().updateCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLECODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoCidadeDescription()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setEstado(CODE_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().updateCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLEDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertFailure()
	{
		Cidade cidade = new Cidade();
		cidade.setEstado(CODE_VALUE);
		cidade.setCidade(DESCRIPTION_VALUE);
		MockCidadeBAC.setReturnResult(FAILURE);
		MockCidadeBAC.setFailureMessageCode(BAC_KEY);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().insertCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteFailure()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		MockCidadeBAC.setReturnResult(FAILURE);
		MockCidadeBAC.setFailureMessageCode(BAC_KEY);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().deleteCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdFailure()
	{

		FetchByIdRequest request = new FetchByIdRequest(new Integer(1));
		MockCidadeBAC.setReturnResult(FETCH_FAILURE);
		MockCidadeBAC.setFailureMessageCode(BAC_KEY);
		CidadeResponse response = getCidadeBAI().fetchCidadeById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchById()
	{
		new FetchByIdRequest(1);
	}

	@Test
	public void testDelete()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().deleteCidade(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertException()
	{
		Cidade cidade = new Cidade();
		cidade.setEstado(CODE_VALUE);
		cidade.setCidade(DESCRIPTION_VALUE);
		MockCidadeBAC.setReturnResult(EXCEPTION);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().insertCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteException()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		MockCidadeBAC.setReturnResult(EXCEPTION);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().deleteCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);
		MockCidadeBAC.setReturnResult(EXCEPTION);
		CidadeResponse response = getCidadeBAI().fetchCidadeById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllException()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockCidadeBAC.setReturnResult(EXCEPTION);
		CidadeResponse response = getCidadeBAI().fetchAllCidades(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchCidadesByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockCidadeBAC.setReturnResult(EXCEPTION);
		CidadeResponse response = getCidadeBAI().fetchCidadesByRequest(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testRefreshException()
	{
		RefreshRequest request = new RefreshRequest(4, true, false);
		MockCidadeBAC.setReturnResult(EXCEPTION);
		CidadeResponse response = getCidadeBAI().refreshCidades(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchCidadesByRequestFailure()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockCidadeBAC.setReturnResult(FETCH_FAILURE);
		CidadeResponse response = getCidadeBAI().fetchCidadesByRequest(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllFailure()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockCidadeBAC.setReturnResult(FETCH_FAILURE);
		CidadeResponse response = getCidadeBAI().fetchAllCidades(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateFailure()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setEstado(CODE_VALUE);
		cidade.setCidade(DESCRIPTION_VALUE);
		MockCidadeBAC.setReturnResult(FAILURE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAI().updateCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateException()
	{
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setEstado(CODE_VALUE);
		cidade.setCidade(DESCRIPTION_VALUE);
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		MockCidadeBAC.setReturnResult(EXCEPTION);
		CidadeResponse response = getCidadeBAI().updateCidade(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Before
	public void setUp()
	{
		MockCidadeBAC.setReturnResult(null);
	}

	public static class MockCidadeBAC implements ICidadeBAC
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
			MockCidadeBAC.failureMessageCode = failureMessageCode;
		}

		@Override
		public InternalResponse deleteCidade(Cidade cidade)
		{
			return createInternalResponse(cidade);
		}

		private InternalResponse createInternalResponse(Cidade cidade)
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
		public InternalResultsResponse<Cidade> fetchAllCidades()
		{
			return createFetchResponse();
		}

		private InternalResultsResponse<Cidade> createFetchResponse()
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
			if (FETCH_FAILURE.equals(getReturnResult()))
			{
				response.addMessage(getFailureMessageCode(), MessageSeverity.Error, MessageLevel.Other);
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Cidade());
			return response;
		}

		@Override
		public InternalResultsResponse<Cidade> fetchCidadesByRequest(PagedInquiryRequest request)
		{
			return createFetchResponse();
		}

		@Override
		public InternalResponse insertCidade(Cidade cidade)
		{
			return createInternalResponse(cidade);
		}

		@Override
		public void refreshCidades(Integer count)
		{

		}

		@Override
		public InternalResponse updateCidade(Cidade cidade)
		{
			return createInternalResponse(cidade);
		}

		@Override
		public InternalResultsResponse<Cidade> fetchCidadeById(FetchByIdRequest request)
		{
			return createFetchResponse();
		}

	}
}
