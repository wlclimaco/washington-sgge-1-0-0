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
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.bai.IProdutoBAI;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/produtobaiimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class ProdutoBAIImplTest extends AbstractJUnit4SpringContextTests
{

	private IProdutoBAI produtoBAI;

	private static final String ID_REQUIRED = "sysmgmt.base.produtovalidator.id.required";
	private static final String BUNDLEDESC_REQUIRED = "sysmgmt.base.produtovalidator.produtodesc.required";
	private static final String BUNDLECODE_REQUIRED = "sysmgmt.base.produtovalidator.produtocode.required";
	private static final String DESCRIPTION_VALUE = "description";
	private static final String CODE_VALUE = "code";
	private static final String FETCH_FAILURE = "fetchFailure";
	private static final String FAILURE = "failure";
	private static final String BAC_KEY = "sysmgmt.base.validator.id.required";
	private static final String BAID_KEY = "sysmgmt.base.produtobaidimpl.defaultexception";
	private static final String BAI_KEY = "sysmgmt.base.produtobaiimpl.defaultexception";
	private static final String EXCEPTION = "exception";

	public IProdutoBAI getProdutoBAI()
	{
		return produtoBAI;
	}

	@Resource
	public void setProdutoBAI(IProdutoBAI produtoBAI)
	{
		this.produtoBAI = produtoBAI;
	}

	@Test
	public void testInsert()
	{
		Produto produto = new Produto();
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, true, true);
		ProdutoResponse response = getProdutoBAI().insertProduto(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchAll()
	{
		ProdutoResponse response = getProdutoBAI().fetchAllProdutos(new FetchAllRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchByRequest()
	{
		ProdutoResponse response = getProdutoBAI().fetchProdutosByRequest(new PagedInquiryRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testRefresh()
	{
		RefreshRequest request = new RefreshRequest(4, false, false);
		ProdutoResponse response = getProdutoBAI().refreshProdutos(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Produto produto = new Produto();
		produto.setId(1);
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, true, false);
		ProdutoResponse response = getProdutoBAI().updateProduto(request);
		assertTrue(response.getMessageList().isEmpty());
		assertFalse(response.getProdutos().isEmpty());
	}

	@Test
	public void testInsertNoProdutoCode()
	{
		Produto produto = new Produto();
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().insertProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLECODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertNoProdutoDescription()
	{
		Produto produto = new Produto();
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().insertProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLEDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoProdutoId()
	{
		Produto produto = new Produto();
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().updateProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteNoProdutoId()
	{
		Produto produto = new Produto();
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().deleteProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdNoProdutoId()
	{
		new FetchByIdRequest();
	}

	@Test
	public void testUpdateNoProdutoCode()
	{
		Produto produto = new Produto();
		produto.setId(1);
		produto.setDescricao(CODE_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().updateProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLECODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoProdutoDescription()
	{
		Produto produto = new Produto();
		produto.setId(1);
		produto.setNome(DESCRIPTION_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().updateProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLEDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertFailure()
	{
		Produto produto = new Produto();
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		MockProdutoBAC.setReturnResult(FAILURE);
		MockProdutoBAC.setFailureMessageCode(BAC_KEY);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().insertProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteFailure()
	{
		Produto produto = new Produto();
		produto.setId(1);
		MockProdutoBAC.setReturnResult(FAILURE);
		MockProdutoBAC.setFailureMessageCode(BAC_KEY);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().deleteProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdFailure()
	{

		FetchByIdRequest request = new FetchByIdRequest(new Integer(1));
		MockProdutoBAC.setReturnResult(FETCH_FAILURE);
		MockProdutoBAC.setFailureMessageCode(BAC_KEY);
		ProdutoResponse response = getProdutoBAI().fetchProdutoById(request);
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
		Produto produto = new Produto();
		produto.setId(1);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().deleteProduto(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertException()
	{
		Produto produto = new Produto();
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		MockProdutoBAC.setReturnResult(EXCEPTION);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().insertProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteException()
	{
		Produto produto = new Produto();
		produto.setId(1);
		MockProdutoBAC.setReturnResult(EXCEPTION);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().deleteProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);
		MockProdutoBAC.setReturnResult(EXCEPTION);
		ProdutoResponse response = getProdutoBAI().fetchProdutoById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllException()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockProdutoBAC.setReturnResult(EXCEPTION);
		ProdutoResponse response = getProdutoBAI().fetchAllProdutos(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchProdutosByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockProdutoBAC.setReturnResult(EXCEPTION);
		ProdutoResponse response = getProdutoBAI().fetchProdutosByRequest(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testRefreshException()
	{
		RefreshRequest request = new RefreshRequest(4, true, false);
		MockProdutoBAC.setReturnResult(EXCEPTION);
		ProdutoResponse response = getProdutoBAI().refreshProdutos(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchProdutosByRequestFailure()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockProdutoBAC.setReturnResult(FETCH_FAILURE);
		ProdutoResponse response = getProdutoBAI().fetchProdutosByRequest(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllFailure()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockProdutoBAC.setReturnResult(FETCH_FAILURE);
		ProdutoResponse response = getProdutoBAI().fetchAllProdutos(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateFailure()
	{
		Produto produto = new Produto();
		produto.setId(1);
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		MockProdutoBAC.setReturnResult(FAILURE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		ProdutoResponse response = getProdutoBAI().updateProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateException()
	{
		Produto produto = new Produto();
		produto.setId(1);
		produto.setDescricao(CODE_VALUE);
		produto.setNome(DESCRIPTION_VALUE);
		ProdutoMaintenanceRequest request = new ProdutoMaintenanceRequest(produto, false, false);
		MockProdutoBAC.setReturnResult(EXCEPTION);
		ProdutoResponse response = getProdutoBAI().updateProduto(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Before
	public void setUp()
	{
		MockProdutoBAC.setReturnResult(null);
	}

	public static class MockProdutoBAC implements IProdutoBAC
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
			MockProdutoBAC.failureMessageCode = failureMessageCode;
		}

		@Override
		public InternalResponse deleteProduto(Produto produto)
		{
			return createInternalResponse(produto);
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
				response.addMessage(getFailureMessageCode(), MessageSeverity.Error, MessageLevel.Other);
				response.setStatus(Status.UnspecifiedError);
			}
			return response;
		}

		@Override
		public InternalResultsResponse<Produto> fetchAllProdutos()
		{
			return createFetchResponse();
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
				response.addMessage(getFailureMessageCode(), MessageSeverity.Error, MessageLevel.Other);
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Produto());
			return response;
		}

		@Override
		public InternalResultsResponse<Produto> fetchProdutosByRequest(PagedInquiryRequest request)
		{
			return createFetchResponse();
		}

		@Override
		public InternalResponse insertProduto(Produto produto)
		{
			return createInternalResponse(produto);
		}

		@Override
		public void refreshProdutos(Integer count)
		{

		}

		@Override
		public InternalResponse updateProduto(Produto produto)
		{
			return createInternalResponse(produto);
		}

		@Override
		public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
		{
			return createFetchResponse();
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
