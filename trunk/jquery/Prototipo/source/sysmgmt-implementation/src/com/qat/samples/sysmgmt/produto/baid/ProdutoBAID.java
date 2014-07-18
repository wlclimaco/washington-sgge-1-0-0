package com.qat.samples.sysmgmt.produto.baid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.CadastroResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

/**
 * Delegate class for Produto BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ProdutoBAID
{

	/** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG = "sysmgmt.base.produtobaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private ProdutoBAID()
	{
	}

	/**
	 * Maintain produto.
	 * 
	 * @param produtoBAC the produto bac
	 * @param validationIndicator the validation indicator
	 * @param controller the controller
	 * @param persistType the persist type
	 * @param request the request
	 * @param response the response
	 */
	public static void maintainProduto(IProdutoBAC produtoBAC, ValidationContextIndicator validationIndicator,
			ValidationController controller,
			PersistanceActionEnum persistType, ProdutoMaintenanceRequest request, ProdutoResponse response)
	{

		InternalResponse internalResponse = new InternalResponse();

		// perform persistence
		switch (persistType)
		{
			case INSERT:
				internalResponse = produtoBAC.insertProduto(request.getProduto());
				break;
			case UPDATE:
				internalResponse = produtoBAC.updateProduto(request.getProduto());
				break;
			case DELETE:
				internalResponse = produtoBAC.deleteProduto(request.getProduto());
				break;
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("persistType missing! Setting Unspecified Error status.");
				}
				internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
				break;
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, null, false);
	}

	public static void maintainCadastro(IProdutoBAC produtoBAC, ValidationContextIndicator validationIndicator,
			ValidationController controller,
			PersistanceActionEnum persistType, CadastroMaintenanceRequest request, CadastroResponse response)
	{
		ValidationContext context =
				new ValidationContext(Produto.class.getSimpleName(), request.getCadastro(), validationIndicator);

		InternalResponse internalResponse = new InternalResponse();
		if (controller.validate(context))
		{
			// perform persistence
			switch (persistType)
			{
				case INSERT:
					internalResponse = produtoBAC.insertCadastro(request.getCadastro());
					break;
				case UPDATE:
					internalResponse = produtoBAC.updateCadastro(request.getCadastro());
					break;
				case DELETE:
					internalResponse = produtoBAC.deleteCadastro(request.getCadastro());
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("persistType missing! Setting Unspecified Error status.");
					}
					internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
					break;
			}
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
	}

	/**
	 * Refresh produtos.
	 * 
	 * @param produtoBAC the produto bac
	 * @param request the request
	 * @param response the response
	 */
	public static void refreshProdutos(IProdutoBAC produtoBAC, RefreshRequest request, ProdutoResponse response)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		produtoBAC.refreshProdutos(request.getRefreshInt());
		// Call maintain to see if we need to return the county list and if so whether it should be paged or not
		maintainReturnList(request.getReturnList(), request.getReturnListPaged(), response, produtoBAC, null);
	}

	/**
	 * Fetch all produtos.
	 * 
	 * @param produtoBAC the produto bac
	 * @param response the response
	 */
	public static void fetchAllProdutos(IProdutoBAC produtoBAC, ProdutoResponse response, ProdutoInquiryRequest request)
	{
		InternalResultsResponse<Produto> internalResponse = produtoBAC.fetchAllProdutos(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	public static void fetchAllCadastros(IProdutoBAC produtoBAC, CadastroResponse response,
			CadastroInquiryRequest request)
	{
		InternalResultsResponse<Cadastro> internalResponse =
				produtoBAC.fetchAllCadastros(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch produtos paged.
	 * 
	 * @param produtoBAC the produto bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchProdutosPaged(IProdutoBAC produtoBAC, PagedInquiryRequest request, ProdutoResponse response)
	{
		InternalResultsResponse<Produto> internalResponse = produtoBAC.fetchProdutosByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	public static void fetchCadastrosPaged(IProdutoBAC produtoBAC, CadastroInquiryRequest request,
			CadastroResponse response)
	{
		InternalResultsResponse<Cadastro> internalResponse = produtoBAC.fetchCadastrosByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch produto by id.
	 * 
	 * @param produtoBAC the produto bac
	 * @param request the request
	 * @param response the response
	 */
	public static void fetchProdutoById(IProdutoBAC produtoBAC, FetchByIdRequest request, ProdutoResponse response)
	{
		InternalResultsResponse<Produto> internalResponse = new InternalResultsResponse<Produto>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = produtoBAC.fetchProdutoById(request);
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	public static void fetchCadastroById(IProdutoBAC produtoBAC, FetchByIdRequest request, CadastroResponse response)
	{
		InternalResultsResponse<Cadastro> internalResponse = new InternalResultsResponse<Cadastro>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		}
		else
		{
			internalResponse = produtoBAC.fetchCadastroById(request);
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Maintain return list.
	 * 
	 * @param listIndicator the list indicator
	 * @param pageListIndicator the page list indicator
	 * @param response the response
	 * @param produtoBAC the produto bac
	 */
	private static void maintainReturnList(Boolean listIndicator, Boolean pageListIndicator, ProdutoResponse response,
			IProdutoBAC produtoBAC, ProdutoInquiryRequest request)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request1 = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				fetchProdutosPaged(produtoBAC, request1, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllProdutos(produtoBAC, response, request);
			}
		}
	}

	private static void maintainReturnListCadastro(Boolean listIndicator, Boolean pageListIndicator,
			CadastroResponse response,
			IProdutoBAC produtoBAC, CadastroInquiryRequest request)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				request.setPreQueryCount(true);
				fetchAllCadastros(produtoBAC, response, request);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllCadastros(produtoBAC, response, request);
			}
		}
	}
}
