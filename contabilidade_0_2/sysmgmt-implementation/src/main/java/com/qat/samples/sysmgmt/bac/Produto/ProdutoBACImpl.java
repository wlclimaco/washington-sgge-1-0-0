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
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
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
	ValidationContext context = new ValidationContext(Produto.class.getSimpleName(), request.getProduto(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Produto>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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
	ValidationContext context = new ValidationContext(Marca.class.getSimpleName(), request.getMarca(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Marca>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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
	ValidationContext context = new ValidationContext(Grupo.class.getSimpleName(), request.getGrupo(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Grupo>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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
	ValidationContext context = new ValidationContext(SubGrupo.class.getSimpleName(), request.getSubGrupo(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<SubGrupo>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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
	ValidationContext context = new ValidationContext(UniMed.class.getSimpleName(), request.getUniMed(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<UniMed>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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
}
