package com.qat.samples.sysmgmt.bac.Site;


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
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.site.model.request.SiteInquiryRequest;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Site leveraging the injected ISiteBAR.
 */
@Component
public class SiteBACImpl implements ISiteBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_SITE_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_SITE_BAC_EXCEPTION_MSG = "sysmgmt.base.Sitebacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SiteBACImpl.class);

	/** The Site BAR. */
	private ISiteBAR siteBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Site BAR.
	 *
	 * @param SiteBAR the new Site BAR
	 */
	public void setSiteBAR(ISiteBAR siteBAR)
	{
		this.siteBAR = siteBAR;
	}

	/**
	 * Gets the Site BAR.
	 *
	 * @return the Site BAR
	 */
	public ISiteBAR getSiteBAR()
	{
		return siteBAR;
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

//===================================### SITE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertSite(com.qat.samples.sysmgmt.model.request.SiteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Site> insertSite(SiteMaintenanceRequest request)
{
	InternalResultsResponse<Site> response =
			processSite(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISiteBAC#updateSite(com.qat.samples.sysmgmt.model.request.SiteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Site> updateSite(SiteMaintenanceRequest request)
{
	InternalResultsResponse<Site> response =
			processSite(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISiteBAC#deleteSite(com.qat.samples.sysmgmt.model.request.SiteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Site> deleteSite(SiteMaintenanceRequest request)
{
	InternalResultsResponse<Site> response =
			processSite(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISiteBAC#refreshSites(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Site> refreshSites(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getSiteBAR().deleteAllSites();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getSiteBAR().insertSite(new Site(i, "SiteDesc" + i));
	}

	// Call maintain to see if we need to return the site list and if so whether it should be paged or not
	return maintainReturnListSite(request.getReturnList(), request.getReturnListPaged(),new Site());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISiteBAC#fetchAllSites(Site site)
 */
@Override
public InternalResultsResponse<Site> fetchAllSites(Site site)
{
	InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	response.getResultsList().addAll(getSiteBAR().fetchAllSites(site).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISiteBAC#fetchSiteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request)
{
	InternalResultsResponse<Site> response = new InternalResultsResponse<Site>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getSiteBAR().fetchSiteById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISiteBAC#fetchSitesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Site> fetchSitesByRequest(SiteInquiryRequest request)
{
	return getSiteBAR().fetchSitesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the site response
 */
private InternalResultsResponse<Site> processSite(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		SiteMaintenanceRequest request)
		{
	InternalResultsResponse<Site> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Site.class.getSimpleName(), request.getSite(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Site>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceSite(request.getSite(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Site>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_SITE_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the site list and if so whether it should be paged or
		// not
		response = maintainReturnListSite(request.getReturnList(), request.getReturnListPaged(),new Site());

		return response;
			}

	/**
	 * Do persistenceSite.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceSite(Site site, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getSiteBAR().insertSite(site);

			case UPDATE:
				return getSiteBAR().updateSite(site);

			case DELETE:
				return getSiteBAR().deleteSiteById(site);
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
	private InternalResultsResponse<Site> maintainReturnListSite(Boolean listIndicator, Boolean pageListIndicator,Site site)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				SiteInquiryRequest request = new SiteInquiryRequest();
				request.setPreQueryCount(true);
				return fetchSitesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllSites(site);
			}
		}
		else
		{
			return new InternalResultsResponse<Site>();
		}
	}

//===================================### CONTATO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertContato(com.qat.samples.sysmgmt.model.request.ContatoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Contato> insertContato(ContatoMaintenanceRequest request)
{
	InternalResultsResponse<Contato> response =
			processContato(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContatoBAC#updateContato(com.qat.samples.sysmgmt.model.request.ContatoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Contato> updateContato(ContatoMaintenanceRequest request)
{
	InternalResultsResponse<Contato> response =
			processContato(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContatoBAC#deleteContato(com.qat.samples.sysmgmt.model.request.ContatoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Contato> deleteContato(ContatoMaintenanceRequest request)
{
	InternalResultsResponse<Contato> response =
			processContato(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContatoBAC#refreshContatos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Contato> refreshContatos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getSiteBAR().deleteAllContatos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getSiteBAR().insertContato(new Contato(i, "ContatoDesc" + i));
	}

	// Call maintain to see if we need to return the contato list and if so whether it should be paged or not
	return maintainReturnListContato(request.getReturnList(), request.getReturnListPaged(),new Contato());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContatoBAC#fetchAllContatos(Contato contato)
 */
@Override
public InternalResultsResponse<Contato> fetchAllContatos(Contato contato)
{
	InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();
	response.getResultsList().addAll(getSiteBAR().fetchAllContatos(contato).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IContatoBAC#fetchContatoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Contato> fetchContatoById(FetchByIdRequest request)
{
	InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getSiteBAR().fetchContatoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IContatoBAC#fetchContatosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Contato> fetchContatosByRequest(ContatoInquiryRequest request)
{
	return getSiteBAR().fetchContatosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the contato response
 */
private InternalResultsResponse<Contato> processContato(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ContatoMaintenanceRequest request)
		{
	InternalResultsResponse<Contato> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Contato.class.getSimpleName(), request.getContato(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Contato>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceContato(request.getContato(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Contato>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_SITE_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the contato list and if so whether it should be paged or
		// not
		response = maintainReturnListContato(request.getReturnList(), request.getReturnListPaged(),new Contato());

		return response;
			}

	/**
	 * Do persistenceContato.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceContato(Contato contato, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getSiteBAR().insertContato(contato);

			case UPDATE:
				return getSiteBAR().updateContato(contato);

			case DELETE:
				return getSiteBAR().deleteContatoById(contato);
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
	private InternalResultsResponse<Contato> maintainReturnListContato(Boolean listIndicator, Boolean pageListIndicator,Contato contato)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ContatoInquiryRequest request = new ContatoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchContatosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllContatos(contato);
			}
		}
		else
		{
			return new InternalResultsResponse<Contato>();
		}
	}

//===================================### ORDEMSERVICO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertOrdemServico(com.qat.samples.sysmgmt.model.request.OrdemServicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request)
{
	InternalResultsResponse<OrdemServico> response =
			processOrdemServico(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#updateOrdemServico(com.qat.samples.sysmgmt.model.request.OrdemServicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request)
{
	InternalResultsResponse<OrdemServico> response =
			processOrdemServico(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#deleteOrdemServico(com.qat.samples.sysmgmt.model.request.OrdemServicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> deleteOrdemServico(OrdemServicoMaintenanceRequest request)
{
	InternalResultsResponse<OrdemServico> response =
			processOrdemServico(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#refreshOrdemServicos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> refreshOrdemServicos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getSiteBAR().deleteAllOrdemServicos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getSiteBAR().insertOrdemServico(new OrdemServico(i, "OrdemServicoDesc" + i));
	}

	// Call maintain to see if we need to return the ordemservico list and if so whether it should be paged or not
	return maintainReturnListOrdemServico(request.getReturnList(), request.getReturnListPaged(),new OrdemServico());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#fetchAllOrdemServicos(OrdemServico ordemservico)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos(OrdemServico ordemservico)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	response.getResultsList().addAll(getSiteBAR().fetchAllOrdemServicos(ordemservico).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#fetchOrdemServicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
{
	InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getSiteBAR().fetchOrdemServicoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IOrdemServicoBAC#fetchOrdemServicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<OrdemServico> fetchOrdemServicosByRequest(OrdemServicoInquiryRequest request)
{
	return getSiteBAR().fetchOrdemServicosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the ordemservico response
 */
private InternalResultsResponse<OrdemServico> processOrdemServico(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		OrdemServicoMaintenanceRequest request)
		{
	InternalResultsResponse<OrdemServico> response = null;

	// Validate
	ValidationContext context = new ValidationContext(OrdemServico.class.getSimpleName(), request.getOrdemServico(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<OrdemServico>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceOrdemServico(request.getOrdemServico(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<OrdemServico>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_SITE_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the ordemservico list and if so whether it should be paged or
		// not
		response = maintainReturnListOrdemServico(request.getReturnList(), request.getReturnListPaged(),new OrdemServico());

		return response;
			}

	/**
	 * Do persistenceOrdemServico.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceOrdemServico(OrdemServico ordemservico, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getSiteBAR().insertOrdemServico(ordemservico);

			case UPDATE:
				return getSiteBAR().updateOrdemServico(ordemservico);

			case DELETE:
				return getSiteBAR().deleteOrdemServicoById(ordemservico);
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
	private InternalResultsResponse<OrdemServico> maintainReturnListOrdemServico(Boolean listIndicator, Boolean pageListIndicator,OrdemServico ordemservico)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchOrdemServicosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllOrdemServicos(ordemservico);
			}
		}
		else
		{
			return new InternalResultsResponse<OrdemServico>();
		}
	}

//===================================### PLANO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertPlano(com.qat.samples.sysmgmt.model.request.PlanoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Plano> insertPlano(PlanoMaintenanceRequest request)
{
	InternalResultsResponse<Plano> response =
			processPlano(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPlanoBAC#updatePlano(com.qat.samples.sysmgmt.model.request.PlanoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Plano> updatePlano(PlanoMaintenanceRequest request)
{
	InternalResultsResponse<Plano> response =
			processPlano(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPlanoBAC#deletePlano(com.qat.samples.sysmgmt.model.request.PlanoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Plano> deletePlano(PlanoMaintenanceRequest request)
{
	InternalResultsResponse<Plano> response =
			processPlano(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPlanoBAC#refreshPlanos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Plano> refreshPlanos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getSiteBAR().deleteAllPlanos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getSiteBAR().insertPlano(new Plano(i, "PlanoDesc" + i));
	}

	// Call maintain to see if we need to return the plano list and if so whether it should be paged or not
	return maintainReturnListPlano(request.getReturnList(), request.getReturnListPaged(),new Plano());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPlanoBAC#fetchAllPlanos(Plano plano)
 */
@Override
public InternalResultsResponse<Plano> fetchAllPlanos(Plano plano)
{
	InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();
	response.getResultsList().addAll(getSiteBAR().fetchAllPlanos(plano).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPlanoBAC#fetchPlanoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Plano> fetchPlanoById(FetchByIdRequest request)
{
	InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getSiteBAR().fetchPlanoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPlanoBAC#fetchPlanosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Plano> fetchPlanosByRequest(PlanoInquiryRequest request)
{
	return getSiteBAR().fetchPlanosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the plano response
 */
private InternalResultsResponse<Plano> processPlano(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		PlanoMaintenanceRequest request)
		{
	InternalResultsResponse<Plano> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Plano.class.getSimpleName(), request.getPlano(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Plano>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistencePlano(request.getPlano(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Plano>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_SITE_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the plano list and if so whether it should be paged or
		// not
		response = maintainReturnListPlano(request.getReturnList(), request.getReturnListPaged(),new Plano());

		return response;
			}

	/**
	 * Do persistencePlano.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistencePlano(Plano plano, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getSiteBAR().insertPlano(plano);

			case UPDATE:
				return getSiteBAR().updatePlano(plano);

			case DELETE:
				return getSiteBAR().deletePlanoById(plano);
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
	private InternalResultsResponse<Plano> maintainReturnListPlano(Boolean listIndicator, Boolean pageListIndicator,Plano plano)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PlanoInquiryRequest request = new PlanoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchPlanosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllPlanos(plano);
			}
		}
		else
		{
			return new InternalResultsResponse<Plano>();
		}
	}

	//===================================### SERVICO ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertServico(com.qat.samples.sysmgmt.model.request.ServicoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Servico> insertServico(ServicoMaintenanceRequest request)
	{
		InternalResultsResponse<Servico> response =
				processServico(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IServicoBAC#updateServico(com.qat.samples.sysmgmt.model.request.ServicoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Servico> updateServico(ServicoMaintenanceRequest request)
	{
		InternalResultsResponse<Servico> response =
				processServico(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IServicoBAC#deleteServico(com.qat.samples.sysmgmt.model.request.ServicoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Servico> deleteServico(ServicoMaintenanceRequest request)
	{
		InternalResultsResponse<Servico> response =
				processServico(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IServicoBAC#refreshServicos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Servico> refreshServicos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getSiteBAR().deleteAllServicos();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getSiteBAR().insertServico(new Servico(i, "ServicoDesc" + i));
		}

		// Call maintain to see if we need to return the servico list and if so whether it should be paged or not
		return maintainReturnListServico(request.getReturnList(), request.getReturnListPaged(),new Servico());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IServicoBAC#fetchAllServicos(Servico servico)
	 */
	@Override
	public InternalResultsResponse<Servico> fetchAllServicos(Servico servico)
	{
		InternalResultsResponse<Servico> response = new InternalResultsResponse<Servico>();
		response.getResultsList().addAll(getSiteBAR().fetchAllServicos(servico).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IServicoBAC#fetchServicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Servico> fetchServicoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Servico> response = new InternalResultsResponse<Servico>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getSiteBAR().fetchServicoById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IServicoBAC#fetchServicosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Servico> fetchServicosByRequest(ServicoInquiryRequest request)
	{
		return getSiteBAR().fetchServicosByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the servico response
	 */
	private InternalResultsResponse<Servico> processServico(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			ServicoMaintenanceRequest request)
			{
		InternalResultsResponse<Servico> response = null;

		// Validate
		ValidationContext context = new ValidationContext(Servico.class.getSimpleName(), request.getServico(), indicator);
		if (!getValidationController().validate(context))
		{
			response = new InternalResultsResponse<Servico>();
			response.setStatus(SystemErrorCategory.SystemValidation);
			response.addMessages(context.getMessages());
			return response;
		}

			// Persist
			InternalResponse internalResponse = doPersistenceServico(request.getServico(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Servico>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_SITE_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the servico list and if so whether it should be paged or
			// not
			response = maintainReturnListServico(request.getReturnList(), request.getReturnListPaged(),new Servico());

			return response;
				}

		/**
		 * Do persistenceServico.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceServico(Servico servico, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getSiteBAR().insertServico(servico);

				case UPDATE:
					return getSiteBAR().updateServico(servico);

				case DELETE:
					return getSiteBAR().deleteServicoById(servico);
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
		private InternalResultsResponse<Servico> maintainReturnListServico(Boolean listIndicator, Boolean pageListIndicator,Servico servico)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					ServicoInquiryRequest request = new ServicoInquiryRequest();
					request.setPreQueryCount(true);
					return fetchServicosByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllServicos(servico);
				}
			}
			else
			{
				return new InternalResultsResponse<Servico>();
			}
		}

}
