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
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.bas.IProdutoBAS;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.unittest.dac.mybatis.ProdutoDACTest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/produtobasimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class ProdutoBASImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoDACTest.class);

	private static final String EXCEPTION = "exception";
	private static final String FAILURE = "failure";
	private static final String FETCH_FAILURE = "fetchFailure";

	private IProdutoBAS produtoBAS;

	public IProdutoBAS getProdutoBAS()
	{
		return produtoBAS;
	}

	@Resource
	public void setProdutoBAS(IProdutoBAS produtoBAS)
	{
		this.produtoBAS = produtoBAS;
	}

	@Test
	public void testInsert()
	{
		Produto produto = new Produto();
		// ProdutoPrice produtoPrice = new ProdutoPrice("T123456", "F", new BigDecimal(10.00), new Date());
		// produto.setProdutoPrices(Arrays.asList(produtoPrice));
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		request.setProduto(produto);

		ProdutoResponse response = getProdutoBAS().insertProduto(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Produto produto = new Produto();
		// ProdutoPrice produtoPrice = new ProdutoPrice("T123456", "F", new BigDecimal(10.00), new Date());
		// produto.setProdutoPrices(Arrays.asList(produtoPrice));
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAS().updateProduto(request);
		request.setProduto(produto);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertWithValidationError()
	{
		Produto produto = new Produto();
		// ProdutoPrice produtoPrice = new ProdutoPrice("T123456", "F", new BigDecimal(10.00), new Date());
		// produto.setProdutoPrices(Arrays.asList(produtoPrice));
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);

		ProdutoResponse response = getProdutoBAS().insertProduto(request);
		assertTrue(response.getMessageList().size() == 1);
	}

	public static class MockProdutoBAC implements IProdutoBAC
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

		private InternalResponse createInternalResponse(Produto produto)
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

		private InternalResultsResponse<Produto> createInternalResultsResponse(Produto produto)
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
			if (produto != null)
			{
				response.addResult(produto);
			}

			if (FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
			}

			return response;
		}

		@Override
		public InternalResponse insertProduto(Produto produto)
		{
			return createInternalResponse(produto);
		}

		@Override
		public InternalResponse updateProduto(Produto produto)
		{
			return createInternalResponse(produto);
		}

		@Override
		public InternalResponse deleteProduto(Produto produto)
		{
			return createInternalResponse(produto);
		}

		@Override
		public void refreshProdutos(Integer refreshNumber)
		{

		}

		@Override
		public InternalResultsResponse<Produto> fetchAllProdutos()
		{
			return createFetchResponse();
		}

		@Override
		public InternalResultsResponse<Produto> fetchProdutosByRequest(PagedInquiryRequest request)
		{
			// TODO Auto-generated method stub
			return null;
		}

		private InternalResultsResponse<Produto> createFetchResponse()
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
			if (FETCH_FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Produto());
			return response;
		}

		@Override
		public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public InternalResponse insertCadastro(Cadastro procedure)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public InternalResponse updateCadastro(Cadastro procedure)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public InternalResponse deleteCadastro(Cadastro procedure)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void refreshCadastros(Integer refreshNumber)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public InternalResultsResponse<Cadastro> fetchCadastroById(FetchByIdRequest request)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public InternalResultsResponse<Cadastro> fetchAllCadastros()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public InternalResultsResponse<Cadastro> fetchCadastrosByRequest(PagedInquiryRequest request)
		{
			// TODO Auto-generated method stub
			return null;
		}

	}
}