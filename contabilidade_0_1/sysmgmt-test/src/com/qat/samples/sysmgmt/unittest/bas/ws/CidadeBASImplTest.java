package com.qat.samples.sysmgmt.unittest.bas.ws;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cidade.bac.ICidadeBAC;
import com.qat.samples.sysmgmt.cidade.bas.ICidadeBAS;
import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.unittest.dac.mybatis.CidadeDACTest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/cidadebasimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class CidadeBASImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(CidadeDACTest.class);

	private static final String EXCEPTION = "exception";
	private static final String FAILURE = "failure";
	private static final String FETCH_FAILURE = "fetchFailure";

	private ICidadeBAS cidadeBAS;

	public ICidadeBAS getCidadeBAS()
	{
		return cidadeBAS;
	}

	@Resource
	public void setCidadeBAS(ICidadeBAS cidadeBAS)
	{
		this.cidadeBAS = cidadeBAS;
	}

	@Test
	public void testInsert()
	{
		Cidade cidade = new Cidade("T123456", "1");
		// CidadePrice cidadePrice = new CidadePrice("T123456", "F", new BigDecimal(10.00), new Date());
		// cidade.setCidadePrices(Arrays.asList(cidadePrice));
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		request.setCidade(cidade);

		CidadeResponse response = getCidadeBAS().insertCidade(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Cidade cidade = new Cidade("T123456", "1");
		// CidadePrice cidadePrice = new CidadePrice("T123456", "F", new BigDecimal(10.00), new Date());
		// cidade.setCidadePrices(Arrays.asList(cidadePrice));
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);
		CidadeResponse response = getCidadeBAS().updateCidade(request);
		request.setCidade(cidade);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertWithValidationError()
	{
		Cidade cidade = new Cidade("T123456", "1");
		// CidadePrice cidadePrice = new CidadePrice("T123456", "F", new BigDecimal(10.00), new Date());
		// cidade.setCidadePrices(Arrays.asList(cidadePrice));
		CidadeMaintenanceRequest request = new CidadeMaintenanceRequest(cidade, false, false);

		CidadeResponse response = getCidadeBAS().insertCidade(request);
		assertTrue(response.getMessageList().size() == 1);
	}

	public static class MockCidadeBAC implements ICidadeBAC
	{
		private static String returnResult;

		public static String getReturnResult()
		{
			return returnResult;
		}

		public static void setReturnResult(String newValue)
		{
			returnResult = newValue;
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
				response.setStatus(Status.UnspecifiedError);
			}

			return response;
		}

		private InternalResultsResponse<Cidade> createInternalResultsResponse(Cidade cidade)
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
			if (cidade != null)
			{
				response.addResult(cidade);
			}

			if (FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
			}

			return response;
		}

		@Override
		public InternalResponse insertCidade(Cidade cidade)
		{
			return createInternalResponse(cidade);
		}

		@Override
		public InternalResponse updateCidade(Cidade cidade)
		{
			return createInternalResponse(cidade);
		}

		@Override
		public InternalResponse deleteCidade(Cidade cidade)
		{
			return createInternalResponse(cidade);
		}

		@Override
		public void refreshCidades(Integer refreshNumber)
		{

		}

		@Override
		public InternalResultsResponse<Cidade> fetchAllCidades()
		{
			return createFetchResponse();
		}

		@Override
		public InternalResultsResponse<Cidade> fetchCidadesByRequest(PagedInquiryRequest request)
		{
			// TODO Auto-generated method stub
			return null;
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
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Cidade());
			return response;
		}

		@Override
		public InternalResultsResponse<Cidade> fetchCidadeById(FetchByIdRequest request)
		{
			// TODO Auto-generated method stub
			return null;
		}

	}
}