/** create by system gera-java version 1.0.0 29/08/2016 22:30 : 24*/
package com.qat.samples.sysmgmt.bac.Produto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.Categoria;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoEmpresa;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.CategoriaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.CustoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.EstoqueMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.PorcaoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoEmpresaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoEmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.RentabilidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Produto leveraging the injected IProdutoBAR.
 */
@Component
public class ProdutoBACImpl implements IProdutoBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_PRODUTO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_PRODUTO_BAC_EXCEPTION_MSG = "sysmgmt.base.Produtobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoBACImpl.class);

	/** The Produto BAR. */
	private IProdutoBAR produtoBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Produto BAR.
	 *
	 * @param ProdutoBAR the new Produto BAR
	 */
	public void setProdutoBAR(IProdutoBAR produtoBAR)
	{
		this.produtoBAR = produtoBAR;
	}

	/**
	 * Gets the Produto BAR.
	 *
	 * @return the Produto BAR
	 */
	public IProdutoBAR getProdutoBAR()
	{
		return produtoBAR;
	}

	/**
	 * Gets the validation controller
	 *
	 * @return ValidationController
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation Controller
	 *
	 * @param validationController
	 */
	public void setValidationController(ValidationController validationController)
	{
		this.validationController = validationController;
	}

//===================================### PRODUTOEMPRESA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertProdutoEmpresa(com.qat.samples.sysmgmt.model.request.ProdutoEmpresaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ProdutoEmpresa> insertProdutoEmpresa(ProdutoEmpresaMaintenanceRequest request)
{
	InternalResultsResponse<ProdutoEmpresa> response =
			processProdutoEmpresa(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProdutoEmpresaBAC#updateProdutoEmpresa(com.qat.samples.sysmgmt.model.request.ProdutoEmpresaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ProdutoEmpresa> updateProdutoEmpresa(ProdutoEmpresaMaintenanceRequest request)
{
	InternalResultsResponse<ProdutoEmpresa> response =
			processProdutoEmpresa(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProdutoEmpresaBAC#deleteProdutoEmpresa(com.qat.samples.sysmgmt.model.request.ProdutoEmpresaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ProdutoEmpresa> deleteProdutoEmpresa(ProdutoEmpresaMaintenanceRequest request)
{
	InternalResultsResponse<ProdutoEmpresa> response =
			processProdutoEmpresa(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProdutoEmpresaBAC#refreshProdutoEmpresas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ProdutoEmpresa> refreshProdutoEmpresas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllProdutoEmpresas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertProdutoEmpresa(new ProdutoEmpresa(i, "ProdutoEmpresaDesc" + i));
	}

	// Call maintain to see if we need to return the produtoempresa list and if so whether it should be paged or not
	return maintainReturnListProdutoEmpresa(request.getReturnList(), request.getReturnListPaged(),new ProdutoEmpresa());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProdutoEmpresaBAC#fetchAllProdutoEmpresas(ProdutoEmpresa produtoempresa)
 */
@Override
public InternalResultsResponse<ProdutoEmpresa> fetchAllProdutoEmpresas(ProdutoEmpresa produtoempresa)
{
	InternalResultsResponse<ProdutoEmpresa> response = new InternalResultsResponse<ProdutoEmpresa>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllProdutoEmpresas(produtoempresa).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProdutoEmpresaBAC#fetchProdutoEmpresaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ProdutoEmpresa> fetchProdutoEmpresaById(FetchByIdRequest request)
{
	InternalResultsResponse<ProdutoEmpresa> response = new InternalResultsResponse<ProdutoEmpresa>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchProdutoEmpresaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProdutoEmpresaBAC#fetchProdutoEmpresasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ProdutoEmpresa> fetchProdutoEmpresasByRequest(ProdutoEmpresaInquiryRequest request)
{
	return getProdutoBAR().fetchProdutoEmpresasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the produtoempresa response
 */
private InternalResultsResponse<ProdutoEmpresa> processProdutoEmpresa(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ProdutoEmpresaMaintenanceRequest request)
		{
	InternalResultsResponse<ProdutoEmpresa> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(ProdutoEmpresa.class.getSimpleName(), request.getProdutoEmpresa(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<ProdutoEmpresa>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceProdutoEmpresa(request.getProdutoEmpresa(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ProdutoEmpresa>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the produtoempresa list and if so whether it should be paged or
		// not
		response = maintainReturnListProdutoEmpresa(request.getReturnList(), request.getReturnListPaged(),new ProdutoEmpresa());

		return response;
			}

	/**
	 * Do persistenceProdutoEmpresa.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceProdutoEmpresa(ProdutoEmpresa produtoempresa, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertProdutoEmpresa(produtoempresa);

			case UPDATE:
				return getProdutoBAR().updateProdutoEmpresa(produtoempresa);

			case DELETE:
				return getProdutoBAR().deleteProdutoEmpresaById(produtoempresa);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<ProdutoEmpresa> maintainReturnListProdutoEmpresa(Boolean listIndicator, Boolean pageListIndicator,ProdutoEmpresa produtoempresa)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ProdutoEmpresaInquiryRequest request = new ProdutoEmpresaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchProdutoEmpresasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllProdutoEmpresas(produtoempresa);
			}
		}
		else
		{
			return new InternalResultsResponse<ProdutoEmpresa>();
		}
	}

//===================================### PRODUTO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Produto> insertProduto(ProdutoMaintenanceRequest request)
{
	InternalResultsResponse<Produto> response =
			processProduto(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProdutoBAC#updateProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Produto> updateProduto(ProdutoMaintenanceRequest request)
{
	InternalResultsResponse<Produto> response =
			processProduto(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProdutoBAC#deleteProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Produto> deleteProduto(ProdutoMaintenanceRequest request)
{
	InternalResultsResponse<Produto> response =
			processProduto(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProdutoBAC#refreshProdutos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Produto> refreshProdutos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllProdutos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertProduto(new Produto(i, "ProdutoDesc" + i));
	}

	// Call maintain to see if we need to return the produto list and if so whether it should be paged or not
	return maintainReturnListProduto(request.getReturnList(), request.getReturnListPaged(),new Produto());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProdutoBAC#fetchAllProdutos(Produto produto)
 */
@Override
public InternalResultsResponse<Produto> fetchAllProdutos(Produto produto)
{
	InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllProdutos(produto).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IProdutoBAC#fetchProdutoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
{
	InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchProdutoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IProdutoBAC#fetchProdutosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Produto> fetchProdutosByRequest(ProdutoInquiryRequest request)
{
	return getProdutoBAR().fetchProdutosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the produto response
 */
private InternalResultsResponse<Produto> processProduto(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ProdutoMaintenanceRequest request)
		{
	InternalResultsResponse<Produto> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Produto.class.getSimpleName(), request.getProduto(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Produto>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceProduto(request.getProduto(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Produto>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the produto list and if so whether it should be paged or
		// not
		response = maintainReturnListProduto(request.getReturnList(), request.getReturnListPaged(),new Produto());

		return response;
			}

	/**
	 * Do persistenceProduto.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceProduto(Produto produto, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertProduto(produto);

			case UPDATE:
				return getProdutoBAR().updateProduto(produto);

			case DELETE:
				return getProdutoBAR().deleteProdutoById(produto);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Produto> maintainReturnListProduto(Boolean listIndicator, Boolean pageListIndicator,Produto produto)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ProdutoInquiryRequest request = new ProdutoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchProdutosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllProdutos(produto);
			}
		}
		else
		{
			return new InternalResultsResponse<Produto>();
		}
	}

//===================================### CFOP ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCfop(com.qat.samples.sysmgmt.model.request.CfopMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request)
{
	InternalResultsResponse<Cfop> response =
			processCfop(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICfopBAC#updateCfop(com.qat.samples.sysmgmt.model.request.CfopMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request)
{
	InternalResultsResponse<Cfop> response =
			processCfop(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICfopBAC#deleteCfop(com.qat.samples.sysmgmt.model.request.CfopMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request)
{
	InternalResultsResponse<Cfop> response =
			processCfop(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICfopBAC#refreshCfops(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Cfop> refreshCfops(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllCfops();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertCfop(new Cfop(i, "CfopDesc" + i));
	}

	// Call maintain to see if we need to return the cfop list and if so whether it should be paged or not
	return maintainReturnListCfop(request.getReturnList(), request.getReturnListPaged(),new Cfop());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICfopBAC#fetchAllCfops(Cfop cfop)
 */
@Override
public InternalResultsResponse<Cfop> fetchAllCfops(Cfop cfop)
{
	InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllCfops(cfop).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICfopBAC#fetchCfopById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Cfop> fetchCfopById(FetchByIdRequest request)
{
	InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchCfopById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICfopBAC#fetchCfopsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request)
{
	return getProdutoBAR().fetchCfopsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the cfop response
 */
private InternalResultsResponse<Cfop> processCfop(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CfopMaintenanceRequest request)
		{
	InternalResultsResponse<Cfop> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Cfop.class.getSimpleName(), request.getCfop(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Cfop>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceCfop(request.getCfop(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Cfop>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the cfop list and if so whether it should be paged or
		// not
		response = maintainReturnListCfop(request.getReturnList(), request.getReturnListPaged(),new Cfop());

		return response;
			}

	/**
	 * Do persistenceCfop.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCfop(Cfop cfop, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertCfop(cfop);

			case UPDATE:
				return getProdutoBAR().updateCfop(cfop);

			case DELETE:
				return getProdutoBAR().deleteCfopById(cfop);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Cfop> maintainReturnListCfop(Boolean listIndicator, Boolean pageListIndicator,Cfop cfop)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CfopInquiryRequest request = new CfopInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCfopsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCfops(cfop);
			}
		}
		else
		{
			return new InternalResultsResponse<Cfop>();
		}
	}

//===================================### MARCA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertMarca(com.qat.samples.sysmgmt.model.request.MarcaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Marca> insertMarca(MarcaMaintenanceRequest request)
{
	InternalResultsResponse<Marca> response =
			processMarca(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMarcaBAC#updateMarca(com.qat.samples.sysmgmt.model.request.MarcaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Marca> updateMarca(MarcaMaintenanceRequest request)
{
	InternalResultsResponse<Marca> response =
			processMarca(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMarcaBAC#deleteMarca(com.qat.samples.sysmgmt.model.request.MarcaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Marca> deleteMarca(MarcaMaintenanceRequest request)
{
	InternalResultsResponse<Marca> response =
			processMarca(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMarcaBAC#refreshMarcas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Marca> refreshMarcas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllMarcas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertMarca(new Marca(i, "MarcaDesc" + i));
	}

	// Call maintain to see if we need to return the marca list and if so whether it should be paged or not
	return maintainReturnListMarca(request.getReturnList(), request.getReturnListPaged(),new Marca());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMarcaBAC#fetchAllMarcas(Marca marca)
 */
@Override
public InternalResultsResponse<Marca> fetchAllMarcas(Marca marca)
{
	InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllMarcas(marca).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMarcaBAC#fetchMarcaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Marca> fetchMarcaById(FetchByIdRequest request)
{
	InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchMarcaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMarcaBAC#fetchMarcasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Marca> fetchMarcasByRequest(MarcaInquiryRequest request)
{
	return getProdutoBAR().fetchMarcasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the marca response
 */
private InternalResultsResponse<Marca> processMarca(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		MarcaMaintenanceRequest request)
		{
	InternalResultsResponse<Marca> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Marca.class.getSimpleName(), request.getMarca(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Marca>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceMarca(request.getMarca(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Marca>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the marca list and if so whether it should be paged or
		// not
		response = maintainReturnListMarca(request.getReturnList(), request.getReturnListPaged(),new Marca());

		return response;
			}

	/**
	 * Do persistenceMarca.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceMarca(Marca marca, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertMarca(marca);

			case UPDATE:
				return getProdutoBAR().updateMarca(marca);

			case DELETE:
				return getProdutoBAR().deleteMarcaById(marca);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Marca> maintainReturnListMarca(Boolean listIndicator, Boolean pageListIndicator,Marca marca)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				MarcaInquiryRequest request = new MarcaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchMarcasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllMarcas(marca);
			}
		}
		else
		{
			return new InternalResultsResponse<Marca>();
		}
	}

//===================================### GRUPO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertGrupo(com.qat.samples.sysmgmt.model.request.GrupoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Grupo> insertGrupo(GrupoMaintenanceRequest request)
{
	InternalResultsResponse<Grupo> response =
			processGrupo(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IGrupoBAC#updateGrupo(com.qat.samples.sysmgmt.model.request.GrupoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Grupo> updateGrupo(GrupoMaintenanceRequest request)
{
	InternalResultsResponse<Grupo> response =
			processGrupo(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IGrupoBAC#deleteGrupo(com.qat.samples.sysmgmt.model.request.GrupoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Grupo> deleteGrupo(GrupoMaintenanceRequest request)
{
	InternalResultsResponse<Grupo> response =
			processGrupo(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IGrupoBAC#refreshGrupos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Grupo> refreshGrupos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllGrupos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertGrupo(new Grupo(i, "GrupoDesc" + i));
	}

	// Call maintain to see if we need to return the grupo list and if so whether it should be paged or not
	return maintainReturnListGrupo(request.getReturnList(), request.getReturnListPaged(),new Grupo());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IGrupoBAC#fetchAllGrupos(Grupo grupo)
 */
@Override
public InternalResultsResponse<Grupo> fetchAllGrupos(Grupo grupo)
{
	InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllGrupos(grupo).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IGrupoBAC#fetchGrupoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Grupo> fetchGrupoById(FetchByIdRequest request)
{
	InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchGrupoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IGrupoBAC#fetchGruposByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Grupo> fetchGruposByRequest(GrupoInquiryRequest request)
{
	return getProdutoBAR().fetchGruposByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the grupo response
 */
private InternalResultsResponse<Grupo> processGrupo(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		GrupoMaintenanceRequest request)
		{
	InternalResultsResponse<Grupo> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Grupo.class.getSimpleName(), request.getGrupo(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Grupo>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceGrupo(request.getGrupo(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Grupo>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the grupo list and if so whether it should be paged or
		// not
		response = maintainReturnListGrupo(request.getReturnList(), request.getReturnListPaged(),new Grupo());

		return response;
			}

	/**
	 * Do persistenceGrupo.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceGrupo(Grupo grupo, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertGrupo(grupo);

			case UPDATE:
				return getProdutoBAR().updateGrupo(grupo);

			case DELETE:
				return getProdutoBAR().deleteGrupoById(grupo);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Grupo> maintainReturnListGrupo(Boolean listIndicator, Boolean pageListIndicator,Grupo grupo)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				GrupoInquiryRequest request = new GrupoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchGruposByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllGrupos(grupo);
			}
		}
		else
		{
			return new InternalResultsResponse<Grupo>();
		}
	}

//===================================### SUBGRUPO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertSubGrupo(com.qat.samples.sysmgmt.model.request.SubGrupoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<SubGrupo> insertSubGrupo(SubGrupoMaintenanceRequest request)
{
	InternalResultsResponse<SubGrupo> response =
			processSubGrupo(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISubGrupoBAC#updateSubGrupo(com.qat.samples.sysmgmt.model.request.SubGrupoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<SubGrupo> updateSubGrupo(SubGrupoMaintenanceRequest request)
{
	InternalResultsResponse<SubGrupo> response =
			processSubGrupo(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISubGrupoBAC#deleteSubGrupo(com.qat.samples.sysmgmt.model.request.SubGrupoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<SubGrupo> deleteSubGrupo(SubGrupoMaintenanceRequest request)
{
	InternalResultsResponse<SubGrupo> response =
			processSubGrupo(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISubGrupoBAC#refreshSubGrupos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<SubGrupo> refreshSubGrupos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllSubGrupos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertSubGrupo(new SubGrupo(i, "SubGrupoDesc" + i));
	}

	// Call maintain to see if we need to return the subgrupo list and if so whether it should be paged or not
	return maintainReturnListSubGrupo(request.getReturnList(), request.getReturnListPaged(),new SubGrupo());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISubGrupoBAC#fetchAllSubGrupos(SubGrupo subgrupo)
 */
@Override
public InternalResultsResponse<SubGrupo> fetchAllSubGrupos(SubGrupo subgrupo)
{
	InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllSubGrupos(subgrupo).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISubGrupoBAC#fetchSubGrupoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<SubGrupo> fetchSubGrupoById(FetchByIdRequest request)
{
	InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchSubGrupoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISubGrupoBAC#fetchSubGruposByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<SubGrupo> fetchSubGruposByRequest(SubGrupoInquiryRequest request)
{
	return getProdutoBAR().fetchSubGruposByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the subgrupo response
 */
private InternalResultsResponse<SubGrupo> processSubGrupo(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		SubGrupoMaintenanceRequest request)
		{
	InternalResultsResponse<SubGrupo> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(SubGrupo.class.getSimpleName(), request.getSubGrupo(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<SubGrupo>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceSubGrupo(request.getSubGrupo(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<SubGrupo>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the subgrupo list and if so whether it should be paged or
		// not
		response = maintainReturnListSubGrupo(request.getReturnList(), request.getReturnListPaged(),new SubGrupo());

		return response;
			}

	/**
	 * Do persistenceSubGrupo.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceSubGrupo(SubGrupo subgrupo, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertSubGrupo(subgrupo);

			case UPDATE:
				return getProdutoBAR().updateSubGrupo(subgrupo);

			case DELETE:
				return getProdutoBAR().deleteSubGrupoById(subgrupo);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<SubGrupo> maintainReturnListSubGrupo(Boolean listIndicator, Boolean pageListIndicator,SubGrupo subgrupo)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				SubGrupoInquiryRequest request = new SubGrupoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchSubGruposByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllSubGrupos(subgrupo);
			}
		}
		else
		{
			return new InternalResultsResponse<SubGrupo>();
		}
	}

//===================================### UNIMED ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertUniMed(com.qat.samples.sysmgmt.model.request.UniMedMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<UniMed> insertUniMed(UniMedMaintenanceRequest request)
{
	InternalResultsResponse<UniMed> response =
			processUniMed(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IUniMedBAC#updateUniMed(com.qat.samples.sysmgmt.model.request.UniMedMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<UniMed> updateUniMed(UniMedMaintenanceRequest request)
{
	InternalResultsResponse<UniMed> response =
			processUniMed(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IUniMedBAC#deleteUniMed(com.qat.samples.sysmgmt.model.request.UniMedMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<UniMed> deleteUniMed(UniMedMaintenanceRequest request)
{
	InternalResultsResponse<UniMed> response =
			processUniMed(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IUniMedBAC#refreshUniMeds(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<UniMed> refreshUniMeds(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllUniMeds();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertUniMed(new UniMed(i, "UniMedDesc" + i));
	}

	// Call maintain to see if we need to return the unimed list and if so whether it should be paged or not
	return maintainReturnListUniMed(request.getReturnList(), request.getReturnListPaged(),new UniMed());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IUniMedBAC#fetchAllUniMeds(UniMed unimed)
 */
@Override
public InternalResultsResponse<UniMed> fetchAllUniMeds(UniMed unimed)
{
	InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllUniMeds(unimed).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IUniMedBAC#fetchUniMedById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<UniMed> fetchUniMedById(FetchByIdRequest request)
{
	InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchUniMedById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IUniMedBAC#fetchUniMedsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<UniMed> fetchUniMedsByRequest(UniMedInquiryRequest request)
{
	return getProdutoBAR().fetchUniMedsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the unimed response
 */
private InternalResultsResponse<UniMed> processUniMed(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		UniMedMaintenanceRequest request)
		{
	InternalResultsResponse<UniMed> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(UniMed.class.getSimpleName(), request.getUniMed(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<UniMed>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceUniMed(request.getUniMed(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<UniMed>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the unimed list and if so whether it should be paged or
		// not
		response = maintainReturnListUniMed(request.getReturnList(), request.getReturnListPaged(),new UniMed());

		return response;
			}

	/**
	 * Do persistenceUniMed.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceUniMed(UniMed unimed, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertUniMed(unimed);

			case UPDATE:
				return getProdutoBAR().updateUniMed(unimed);

			case DELETE:
				return getProdutoBAR().deleteUniMedById(unimed);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<UniMed> maintainReturnListUniMed(Boolean listIndicator, Boolean pageListIndicator,UniMed unimed)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				UniMedInquiryRequest request = new UniMedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchUniMedsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllUniMeds(unimed);
			}
		}
		else
		{
			return new InternalResultsResponse<UniMed>();
		}
	}

//===================================### TRIBUTACAO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertTributacao(com.qat.samples.sysmgmt.model.request.TributacaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Tributacao> insertTributacao(TributacaoMaintenanceRequest request)
{
	InternalResultsResponse<Tributacao> response =
			processTributacao(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITributacaoBAC#updateTributacao(com.qat.samples.sysmgmt.model.request.TributacaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Tributacao> updateTributacao(TributacaoMaintenanceRequest request)
{
	InternalResultsResponse<Tributacao> response =
			processTributacao(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITributacaoBAC#deleteTributacao(com.qat.samples.sysmgmt.model.request.TributacaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Tributacao> deleteTributacao(TributacaoMaintenanceRequest request)
{
	InternalResultsResponse<Tributacao> response =
			processTributacao(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITributacaoBAC#refreshTributacaos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Tributacao> refreshTributacaos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllTributacaos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertTributacao(new Tributacao(i, "TributacaoDesc" + i));
	}

	// Call maintain to see if we need to return the tributacao list and if so whether it should be paged or not
	return maintainReturnListTributacao(request.getReturnList(), request.getReturnListPaged(),new Tributacao());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITributacaoBAC#fetchAllTributacaos(Tributacao tributacao)
 */
@Override
public InternalResultsResponse<Tributacao> fetchAllTributacaos(Tributacao tributacao)
{
	InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllTributacaos(tributacao).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITributacaoBAC#fetchTributacaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Tributacao> fetchTributacaoById(FetchByIdRequest request)
{
	InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchTributacaoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITributacaoBAC#fetchTributacaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Tributacao> fetchTributacaosByRequest(TributacaoInquiryRequest request)
{
	return getProdutoBAR().fetchTributacaosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the tributacao response
 */
private InternalResultsResponse<Tributacao> processTributacao(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		TributacaoMaintenanceRequest request)
		{
	InternalResultsResponse<Tributacao> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Tributacao.class.getSimpleName(), request.getTributacao(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Tributacao>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceTributacao(request.getTributacao(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Tributacao>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the tributacao list and if so whether it should be paged or
		// not
		response = maintainReturnListTributacao(request.getReturnList(), request.getReturnListPaged(),new Tributacao());

		return response;
			}

	/**
	 * Do persistenceTributacao.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceTributacao(Tributacao tributacao, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertTributacao(tributacao);

			case UPDATE:
				return getProdutoBAR().updateTributacao(tributacao);

			case DELETE:
				return getProdutoBAR().deleteTributacaoById(tributacao);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Tributacao> maintainReturnListTributacao(Boolean listIndicator, Boolean pageListIndicator,Tributacao tributacao)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				TributacaoInquiryRequest request = new TributacaoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchTributacaosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllTributacaos(tributacao);
			}
		}
		else
		{
			return new InternalResultsResponse<Tributacao>();
		}
	}


//===================================### CUSTO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCusto(com.qat.samples.sysmgmt.model.request.CustoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Custo> insertCusto(CustoMaintenanceRequest request)
{
	InternalResultsResponse<Custo> response =
			processCusto(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICustoBAC#updateCusto(com.qat.samples.sysmgmt.model.request.CustoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Custo> updateCusto(CustoMaintenanceRequest request)
{
	InternalResultsResponse<Custo> response =
			processCusto(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICustoBAC#deleteCusto(com.qat.samples.sysmgmt.model.request.CustoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Custo> deleteCusto(CustoMaintenanceRequest request)
{
	InternalResultsResponse<Custo> response =
			processCusto(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICustoBAC#refreshCustos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Custo> refreshCustos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllCustos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertCusto(new Custo(i, "CustoDesc" + i));
	}

	// Call maintain to see if we need to return the custo list and if so whether it should be paged or not
	return maintainReturnListCusto(request.getReturnList(), request.getReturnListPaged(),new Custo());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICustoBAC#fetchAllCustos(Custo custo)
 */
@Override
public InternalResultsResponse<Custo> fetchAllCustos(Custo custo)
{
	InternalResultsResponse<Custo> response = new InternalResultsResponse<Custo>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllCustos(custo).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICustoBAC#fetchCustoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Custo> fetchCustoById(FetchByIdRequest request)
{
	InternalResultsResponse<Custo> response = new InternalResultsResponse<Custo>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchCustoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICustoBAC#fetchCustosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Custo> fetchCustosByRequest(PagedInquiryRequest request)
{
	return getProdutoBAR().fetchCustosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the custo response
 */
private InternalResultsResponse<Custo> processCusto(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CustoMaintenanceRequest request)
		{
	InternalResultsResponse<Custo> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Custo.class.getSimpleName(), request.getCusto(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Custo>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceCusto(request.getCusto(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Custo>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the custo list and if so whether it should be paged or
		// not
		response = maintainReturnListCusto(request.getReturnList(), request.getReturnListPaged(),new Custo());

		return response;
			}

	/**
	 * Do persistenceCusto.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCusto(Custo custo, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertCusto(custo);

			case UPDATE:
				return getProdutoBAR().updateCusto(custo);

			case DELETE:
				return getProdutoBAR().deleteCustoById(custo);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Custo> maintainReturnListCusto(Boolean listIndicator, Boolean pageListIndicator,Custo custo)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCustosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCustos(custo);
			}
		}
		else
		{
			return new InternalResultsResponse<Custo>();
		}
	}


//===================================### ESTOQUE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertEstoque(com.qat.samples.sysmgmt.model.request.EstoqueMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Estoque> insertEstoque(EstoqueMaintenanceRequest request)
{
	InternalResultsResponse<Estoque> response =
			processEstoque(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEstoqueBAC#updateEstoque(com.qat.samples.sysmgmt.model.request.EstoqueMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Estoque> updateEstoque(EstoqueMaintenanceRequest request)
{
	InternalResultsResponse<Estoque> response =
			processEstoque(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEstoqueBAC#deleteEstoque(com.qat.samples.sysmgmt.model.request.EstoqueMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Estoque> deleteEstoque(EstoqueMaintenanceRequest request)
{
	InternalResultsResponse<Estoque> response =
			processEstoque(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEstoqueBAC#refreshEstoques(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Estoque> refreshEstoques(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllEstoques();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertEstoque(new Estoque(i, "EstoqueDesc" + i));
	}

	// Call maintain to see if we need to return the estoque list and if so whether it should be paged or not
	return maintainReturnListEstoque(request.getReturnList(), request.getReturnListPaged(),new Estoque());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEstoqueBAC#fetchAllEstoques(Estoque estoque)
 */
@Override
public InternalResultsResponse<Estoque> fetchAllEstoques(Estoque estoque)
{
	InternalResultsResponse<Estoque> response = new InternalResultsResponse<Estoque>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllEstoques(estoque).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEstoqueBAC#fetchEstoqueById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Estoque> fetchEstoqueById(FetchByIdRequest request)
{
	InternalResultsResponse<Estoque> response = new InternalResultsResponse<Estoque>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchEstoqueById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEstoqueBAC#fetchEstoquesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Estoque> fetchEstoquesByRequest(PagedInquiryRequest request)
{
	return getProdutoBAR().fetchEstoquesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the estoque response
 */
private InternalResultsResponse<Estoque> processEstoque(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		EstoqueMaintenanceRequest request)
		{
	InternalResultsResponse<Estoque> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Estoque.class.getSimpleName(), request.getEstoque(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Estoque>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceEstoque(request.getEstoque(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Estoque>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the estoque list and if so whether it should be paged or
		// not
		response = maintainReturnListEstoque(request.getReturnList(), request.getReturnListPaged(),new Estoque());

		return response;
			}

	/**
	 * Do persistenceEstoque.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceEstoque(Estoque estoque, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertEstoque(estoque);

			case UPDATE:
				return getProdutoBAR().updateEstoque(estoque);

			case DELETE:
				return getProdutoBAR().deleteEstoqueById(estoque);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Estoque> maintainReturnListEstoque(Boolean listIndicator, Boolean pageListIndicator,Estoque estoque)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchEstoquesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllEstoques(estoque);
			}
		}
		else
		{
			return new InternalResultsResponse<Estoque>();
		}
	}

//===================================### PORCAO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertPorcao(com.qat.samples.sysmgmt.model.request.PorcaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Porcao> insertPorcao(PorcaoMaintenanceRequest request)
{
	InternalResultsResponse<Porcao> response =
			processPorcao(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPorcaoBAC#updatePorcao(com.qat.samples.sysmgmt.model.request.PorcaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Porcao> updatePorcao(PorcaoMaintenanceRequest request)
{
	InternalResultsResponse<Porcao> response =
			processPorcao(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPorcaoBAC#deletePorcao(com.qat.samples.sysmgmt.model.request.PorcaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Porcao> deletePorcao(PorcaoMaintenanceRequest request)
{
	InternalResultsResponse<Porcao> response =
			processPorcao(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPorcaoBAC#refreshPorcaos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Porcao> refreshPorcaos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllPorcaos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertPorcao(new Porcao(i, "PorcaoDesc" + i));
	}

	// Call maintain to see if we need to return the porcao list and if so whether it should be paged or not
	return maintainReturnListPorcao(request.getReturnList(), request.getReturnListPaged(),new Porcao());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPorcaoBAC#fetchAllPorcaos(Porcao porcao)
 */
@Override
public InternalResultsResponse<Porcao> fetchAllPorcaos(Porcao porcao)
{
	InternalResultsResponse<Porcao> response = new InternalResultsResponse<Porcao>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllPorcaos(porcao).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPorcaoBAC#fetchPorcaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Porcao> fetchPorcaoById(FetchByIdRequest request)
{
	InternalResultsResponse<Porcao> response = new InternalResultsResponse<Porcao>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchPorcaoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPorcaoBAC#fetchPorcaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Porcao> fetchPorcaosByRequest(PagedInquiryRequest request)
{
	return getProdutoBAR().fetchPorcaosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the porcao response
 */
private InternalResultsResponse<Porcao> processPorcao(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		PorcaoMaintenanceRequest request)
		{
	InternalResultsResponse<Porcao> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Porcao.class.getSimpleName(), request.getPorcao(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Porcao>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistencePorcao(request.getPorcao(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Porcao>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the porcao list and if so whether it should be paged or
		// not
		response = maintainReturnListPorcao(request.getReturnList(), request.getReturnListPaged(),new Porcao());

		return response;
			}

	/**
	 * Do persistencePorcao.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistencePorcao(Porcao porcao, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertPorcao(porcao);

			case UPDATE:
				return getProdutoBAR().updatePorcao(porcao);

			case DELETE:
				return getProdutoBAR().deletePorcaoById(porcao);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Porcao> maintainReturnListPorcao(Boolean listIndicator, Boolean pageListIndicator,Porcao porcao)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchPorcaosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllPorcaos(porcao);
			}
		}
		else
		{
			return new InternalResultsResponse<Porcao>();
		}
	}


//===================================### RENTABILIDADE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertRentabilidade(com.qat.samples.sysmgmt.model.request.RentabilidadeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Rentabilidade> insertRentabilidade(RentabilidadeMaintenanceRequest request)
{
	InternalResultsResponse<Rentabilidade> response =
			processRentabilidade(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IRentabilidadeBAC#updateRentabilidade(com.qat.samples.sysmgmt.model.request.RentabilidadeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Rentabilidade> updateRentabilidade(RentabilidadeMaintenanceRequest request)
{
	InternalResultsResponse<Rentabilidade> response =
			processRentabilidade(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IRentabilidadeBAC#deleteRentabilidade(com.qat.samples.sysmgmt.model.request.RentabilidadeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Rentabilidade> deleteRentabilidade(RentabilidadeMaintenanceRequest request)
{
	InternalResultsResponse<Rentabilidade> response =
			processRentabilidade(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IRentabilidadeBAC#refreshRentabilidades(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Rentabilidade> refreshRentabilidades(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getProdutoBAR().deleteAllRentabilidades();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getProdutoBAR().insertRentabilidade(new Rentabilidade(i, "RentabilidadeDesc" + i));
	}

	// Call maintain to see if we need to return the rentabilidade list and if so whether it should be paged or not
	return maintainReturnListRentabilidade(request.getReturnList(), request.getReturnListPaged(),new Rentabilidade());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IRentabilidadeBAC#fetchAllRentabilidades(Rentabilidade rentabilidade)
 */
@Override
public InternalResultsResponse<Rentabilidade> fetchAllRentabilidades(Rentabilidade rentabilidade)
{
	InternalResultsResponse<Rentabilidade> response = new InternalResultsResponse<Rentabilidade>();
	response.getResultsList().addAll(getProdutoBAR().fetchAllRentabilidades(rentabilidade).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IRentabilidadeBAC#fetchRentabilidadeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Rentabilidade> fetchRentabilidadeById(FetchByIdRequest request)
{
	InternalResultsResponse<Rentabilidade> response = new InternalResultsResponse<Rentabilidade>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getProdutoBAR().fetchRentabilidadeById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IRentabilidadeBAC#fetchRentabilidadesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Rentabilidade> fetchRentabilidadesByRequest(PagedInquiryRequest request)
{
	return getProdutoBAR().fetchRentabilidadesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the rentabilidade response
 */
private InternalResultsResponse<Rentabilidade> processRentabilidade(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		RentabilidadeMaintenanceRequest request)
		{
	InternalResultsResponse<Rentabilidade> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(Rentabilidade.class.getSimpleName(), request.getRentabilidade(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<Rentabilidade>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceRentabilidade(request.getRentabilidade(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Rentabilidade>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the rentabilidade list and if so whether it should be paged or
		// not
		response = maintainReturnListRentabilidade(request.getReturnList(), request.getReturnListPaged(),new Rentabilidade());

		return response;
			}

	/**
	 * Do persistenceRentabilidade.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceRentabilidade(Rentabilidade rentabilidade, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getProdutoBAR().insertRentabilidade(rentabilidade);

			case UPDATE:
				return getProdutoBAR().updateRentabilidade(rentabilidade);

			case DELETE:
				return getProdutoBAR().deleteRentabilidadeById(rentabilidade);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Rentabilidade> maintainReturnListRentabilidade(Boolean listIndicator, Boolean pageListIndicator,Rentabilidade rentabilidade)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchRentabilidadesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllRentabilidades(rentabilidade);
			}
		}
		else
		{
			return new InternalResultsResponse<Rentabilidade>();
		}
	}
	//===================================### CATEGORIA ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCategoria(com.qat.samples.sysmgmt.model.request.CategoriaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Categoria> insertCategoria(CategoriaMaintenanceRequest request)
	{
		InternalResultsResponse<Categoria> response =
				processCategoria(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICategoriaBAC#updateCategoria(com.qat.samples.sysmgmt.model.request.CategoriaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Categoria> updateCategoria(CategoriaMaintenanceRequest request)
	{
		InternalResultsResponse<Categoria> response =
				processCategoria(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICategoriaBAC#deleteCategoria(com.qat.samples.sysmgmt.model.request.CategoriaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Categoria> deleteCategoria(CategoriaMaintenanceRequest request)
	{
		InternalResultsResponse<Categoria> response =
				processCategoria(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICategoriaBAC#refreshCategorias(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Categoria> refreshCategorias(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getProdutoBAR().deleteAllCategorias();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getProdutoBAR().insertCategoria(new Categoria(i, "CategoriaDesc" + i));
		}

		// Call maintain to see if we need to return the categoria list and if so whether it should be paged or not
		return maintainReturnListCategoria(request.getReturnList(), request.getReturnListPaged(),new Categoria());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICategoriaBAC#fetchAllCategorias(Categoria categoria)
	 */
	@Override
	public InternalResultsResponse<Categoria> fetchAllCategorias(Categoria categoria)
	{
		InternalResultsResponse<Categoria> response = new InternalResultsResponse<Categoria>();
		response.getResultsList().addAll(getProdutoBAR().fetchAllCategorias(categoria).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICategoriaBAC#fetchCategoriaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Categoria> fetchCategoriaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Categoria> response = new InternalResultsResponse<Categoria>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getProdutoBAR().fetchCategoriaById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICategoriaBAC#fetchCategoriasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Categoria> fetchCategoriasByRequest(PagedInquiryRequest request)
	{
		return getProdutoBAR().fetchCategoriasByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the categoria response
	 */
	private InternalResultsResponse<Categoria> processCategoria(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			CategoriaMaintenanceRequest request)
			{
		InternalResultsResponse<Categoria> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Categoria.class.getSimpleName(), request.getCategoria(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Categoria>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceCategoria(request.getCategoria(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Categoria>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_PRODUTO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the categoria list and if so whether it should be paged or
			// not
			response = maintainReturnListCategoria(request.getReturnList(), request.getReturnListPaged(),new Categoria());

			return response;
				}

		/**
		 * Do persistenceCategoria.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceCategoria(Categoria categoria, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getProdutoBAR().insertCategoria(categoria);

				case UPDATE:
					return getProdutoBAR().updateCategoria(categoria);

				case DELETE:
					return getProdutoBAR().deleteCategoriaById(categoria);
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("updateType missing!");
					}
					break;
			}

			return null;
		}

		/**
		 * Maintain return list.
		 *
		 * @param request the request
		 * @param response the response
		 */
		private InternalResultsResponse<Categoria> maintainReturnListCategoria(Boolean listIndicator, Boolean pageListIndicator,Categoria categoria)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchCategoriasByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllCategorias(categoria);
				}
			}
			else
			{
				return new InternalResultsResponse<Categoria>();
			}
		}

}
