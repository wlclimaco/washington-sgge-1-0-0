package com.qat.samples.sysmgmt.unittest.bac;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.cidade.bac.ICidadeBAC;
import com.qat.samples.sysmgmt.cidade.dac.ICidadeDAC;
import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/cidadebacimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class CidadeBACImplTest extends AbstractJUnit4SpringContextTests
{

	private ICidadeBAC cidadeBAC;

	public ICidadeBAC getCidadeBAC()
	{
		return cidadeBAC;
	}

	@Resource
	public void setCidadeBAC(ICidadeBAC cidadeBAC)
	{
		this.cidadeBAC = cidadeBAC;
	}

	@Test
	public void testInsertCidade()
	{
		Cidade cidade = new Cidade(1, "pc88", "desc88");
		InternalResponse response = getCidadeBAC().insertCidade(cidade);
		assertNotNull(response);
		assertNotNull(cidade.getId());
	}

	@Test
	public void testUpdateCidade()
	{
		assertNotNull(getCidadeBAC().updateCidade(new Cidade(1, "pc89", "desc89")));
	}

	@Test
	public void testUpdateCidadeError()
	{
		assertNotNull(getCidadeBAC().updateCidade(new Cidade(1, null, "desc89")));
	}

	@Test
	public void testDeleteCidade()
	{
		assertNotNull(getCidadeBAC().deleteCidade(new Cidade()));
	}

	@Test
	public void testFetchAllCidade()
	{
		InternalResultsResponse<Cidade> results = getCidadeBAC().fetchAllCidades();
		assertNotNull(results);
		assertNotNull(results.getResultsList());
	}

	@Test
	public void testFetchCidadesByRequest()
	{
		InternalResultsResponse<Cidade> results = getCidadeBAC().fetchCidadesByRequest(new PagedInquiryRequest());
		assertNotNull(results);
		assertNotNull(results.getResultsList());
	}

	@Test
	public void testFetchCidadeById()
	{
		assertNotNull(getCidadeBAC().fetchCidadeById(new FetchByIdRequest()));
	}

	@Test
	public void testRefreshCidades()
	{
		getCidadeBAC().refreshCidades(4);

	}

	@Test
	public void testRefreshCidadesLessThanOne()
	{
		getCidadeBAC().refreshCidades(0);
	}

	public static class MockCidadeDAC implements ICidadeDAC
	{
		@Override
		public InternalResponse deleteAllCidades()
		{
			return new InternalResponse();
		}

		@Override
		public InternalResponse deleteCidade(Cidade cidade)
		{
			return new InternalResponse();
		}

		@Override
		public List<Cidade> fetchAllCidades()
		{
			List<Cidade> results = new ArrayList<Cidade>();
			results.add(new Cidade());
			results.add(new Cidade());
			results.add(new Cidade());
			results.add(new Cidade());
			return results;
		}

		@Override
		public InternalResponse insertCidade(Cidade cidade)
		{
			cidade.setId(100);
			return new InternalResponse();
		}

		@Override
		public InternalResponse updateCidade(Cidade cidade)
		{
			if (ValidationUtil.isNullOrEmpty(cidade.getEstado()))
			{
				return new InternalResponse(Status.ExternalError);
			}
			else
			{
				return new InternalResponse();
			}
		}

		@Override
		public InternalResultsResponse<Cidade> fetchCidadesByRequest(PagedInquiryRequest request)
		{
			InternalResultsResponse<Cidade> results = new InternalResultsResponse<Cidade>();
			results.addResults(fetchAllCidades());
			return results;
		}

		@Override
		public Cidade fetchCidadeById(FetchByIdRequest request)
		{
			Cidade cidade = new Cidade();
			return cidade;
		}

	}
}
