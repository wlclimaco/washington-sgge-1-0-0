/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.Configuracao;


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
import com.qat.samples.sysmgmt.bar.Configuracao.IConfiguracaoBAR;
import com.qat.samples.sysmgmt.entidade.model.Boleto;
import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;
import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.entidade.model.request.BoletoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigAlertasMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigCarneMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigFiscalMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigGeralMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigSMTPMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfigVendasMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfiguracaoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.ConfiguracaoNFeMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Configuracao leveraging the injected IConfiguracaoBAR.
 */
@Component
public class ConfiguracaoBACImpl implements IConfiguracaoBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG = "sysmgmt.base.Configuracaobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ConfiguracaoBACImpl.class);

	/** The Configuracao BAR. */
	private IConfiguracaoBAR configuracaoBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Configuracao BAR.
	 *
	 * @param ConfiguracaoBAR the new Configuracao BAR
	 */
	public void setConfiguracaoBAR(IConfiguracaoBAR configuracaoBAR)
	{
		this.configuracaoBAR = configuracaoBAR;
	}

	/**
	 * Gets the Configuracao BAR.
	 *
	 * @return the Configuracao BAR
	 */
	public IConfiguracaoBAR getConfiguracaoBAR()
	{
		return configuracaoBAR;
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

//===================================### CONFIGURACAO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfiguracao(com.qat.samples.sysmgmt.model.request.ConfiguracaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracao> insertConfiguracao(ConfiguracaoMaintenanceRequest request)
{
	InternalResultsResponse<Configuracao> response =
			processConfiguracao(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaoBAC#updateConfiguracao(com.qat.samples.sysmgmt.model.request.ConfiguracaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracao> updateConfiguracao(ConfiguracaoMaintenanceRequest request)
{
	InternalResultsResponse<Configuracao> response =
			processConfiguracao(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaoBAC#deleteConfiguracao(com.qat.samples.sysmgmt.model.request.ConfiguracaoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracao> deleteConfiguracao(ConfiguracaoMaintenanceRequest request)
{
	InternalResultsResponse<Configuracao> response =
			processConfiguracao(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaoBAC#refreshConfiguracaos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configuracao> refreshConfiguracaos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfiguracaos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfiguracao(new Configuracao(i, "ConfiguracaoDesc" + i));
	}

	// Call maintain to see if we need to return the configuracao list and if so whether it should be paged or not
	return maintainReturnListConfiguracao(request.getReturnList(), request.getReturnListPaged(),new Configuracao());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaoBAC#fetchAllConfiguracaos(Configuracao configuracao)
 */
@Override
public InternalResultsResponse<Configuracao> fetchAllConfiguracaos(Configuracao configuracao)
{
	InternalResultsResponse<Configuracao> response = new InternalResultsResponse<Configuracao>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfiguracaos(configuracao).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaoBAC#fetchConfiguracaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracao> fetchConfiguracaoById(FetchByIdRequest request)
{
	InternalResultsResponse<Configuracao> response = new InternalResultsResponse<Configuracao>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfiguracaoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaoBAC#fetchConfiguracaosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configuracao> fetchConfiguracaosByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfiguracaosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configuracao response
 */
private InternalResultsResponse<Configuracao> processConfiguracao(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfiguracaoMaintenanceRequest request)
		{
	InternalResultsResponse<Configuracao> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configuracao.class.getSimpleName(), request.getConfiguracao(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configuracao>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfiguracao(request.getConfiguracao(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configuracao>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configuracao list and if so whether it should be paged or
		// not
		response = maintainReturnListConfiguracao(request.getReturnList(), request.getReturnListPaged(),new Configuracao());

		return response;
			}

	/**
	 * Do persistenceConfiguracao.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfiguracao(Configuracao configuracao, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfiguracao(configuracao);

			case UPDATE:
				return getConfiguracaoBAR().updateConfiguracao(configuracao);

			case DELETE:
				return getConfiguracaoBAR().deleteConfiguracaoById(configuracao);
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
	private InternalResultsResponse<Configuracao> maintainReturnListConfiguracao(Boolean listIndicator, Boolean pageListIndicator,Configuracao configuracao)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfiguracaosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfiguracaos(configuracao);
			}
		}
		else
		{
			return new InternalResultsResponse<Configuracao>();
		}
	}

//===================================### BOLETO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertBoleto(com.qat.samples.sysmgmt.model.request.BoletoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Boleto> insertBoleto(BoletoMaintenanceRequest request)
{
	InternalResultsResponse<Boleto> response =
			processBoleto(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBoletoBAC#updateBoleto(com.qat.samples.sysmgmt.model.request.BoletoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Boleto> updateBoleto(BoletoMaintenanceRequest request)
{
	InternalResultsResponse<Boleto> response =
			processBoleto(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBoletoBAC#deleteBoleto(com.qat.samples.sysmgmt.model.request.BoletoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Boleto> deleteBoleto(BoletoMaintenanceRequest request)
{
	InternalResultsResponse<Boleto> response =
			processBoleto(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBoletoBAC#refreshBoletos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Boleto> refreshBoletos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllBoletos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertBoleto(new Boleto(i, "BoletoDesc" + i));
	}

	// Call maintain to see if we need to return the boleto list and if so whether it should be paged or not
	return maintainReturnListBoleto(request.getReturnList(), request.getReturnListPaged(),new Boleto());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBoletoBAC#fetchAllBoletos(Boleto boleto)
 */
@Override
public InternalResultsResponse<Boleto> fetchAllBoletos(Boleto boleto)
{
	InternalResultsResponse<Boleto> response = new InternalResultsResponse<Boleto>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllBoletos(boleto).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IBoletoBAC#fetchBoletoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Boleto> fetchBoletoById(FetchByIdRequest request)
{
	InternalResultsResponse<Boleto> response = new InternalResultsResponse<Boleto>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchBoletoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IBoletoBAC#fetchBoletosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Boleto> fetchBoletosByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchBoletosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the boleto response
 */
private InternalResultsResponse<Boleto> processBoleto(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		BoletoMaintenanceRequest request)
		{
	InternalResultsResponse<Boleto> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Boleto.class.getSimpleName(), request.getBoleto(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Boleto>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceBoleto(request.getBoleto(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Boleto>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the boleto list and if so whether it should be paged or
		// not
		response = maintainReturnListBoleto(request.getReturnList(), request.getReturnListPaged(),new Boleto());

		return response;
			}

	/**
	 * Do persistenceBoleto.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceBoleto(Boleto boleto, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertBoleto(boleto);

			case UPDATE:
				return getConfiguracaoBAR().updateBoleto(boleto);

			case DELETE:
				return getConfiguracaoBAR().deleteBoletoById(boleto);
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
	private InternalResultsResponse<Boleto> maintainReturnListBoleto(Boolean listIndicator, Boolean pageListIndicator,Boleto boleto)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchBoletosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllBoletos(boleto);
			}
		}
		else
		{
			return new InternalResultsResponse<Boleto>();
		}
	}

//===================================### CONFIGCARNE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigCarne(com.qat.samples.sysmgmt.model.request.ConfigCarneMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigCarne> insertConfigCarne(ConfigCarneMaintenanceRequest request)
{
	InternalResultsResponse<ConfigCarne> response =
			processConfigCarne(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigCarneBAC#updateConfigCarne(com.qat.samples.sysmgmt.model.request.ConfigCarneMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigCarne> updateConfigCarne(ConfigCarneMaintenanceRequest request)
{
	InternalResultsResponse<ConfigCarne> response =
			processConfigCarne(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigCarneBAC#deleteConfigCarne(com.qat.samples.sysmgmt.model.request.ConfigCarneMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigCarne> deleteConfigCarne(ConfigCarneMaintenanceRequest request)
{
	InternalResultsResponse<ConfigCarne> response =
			processConfigCarne(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigCarneBAC#refreshConfigCarnes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigCarne> refreshConfigCarnes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigCarnes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigCarne(new ConfigCarne(i, "ConfigCarneDesc" + i));
	}

	// Call maintain to see if we need to return the configcarne list and if so whether it should be paged or not
	return maintainReturnListConfigCarne(request.getReturnList(), request.getReturnListPaged(),new ConfigCarne());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigCarneBAC#fetchAllConfigCarnes(ConfigCarne configcarne)
 */
@Override
public InternalResultsResponse<ConfigCarne> fetchAllConfigCarnes(ConfigCarne configcarne)
{
	InternalResultsResponse<ConfigCarne> response = new InternalResultsResponse<ConfigCarne>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigCarnes(configcarne).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigCarneBAC#fetchConfigCarneById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigCarne> fetchConfigCarneById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigCarne> response = new InternalResultsResponse<ConfigCarne>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigCarneById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigCarneBAC#fetchConfigCarnesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigCarne> fetchConfigCarnesByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigCarnesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configcarne response
 */
private InternalResultsResponse<ConfigCarne> processConfigCarne(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigCarneMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigCarne> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigCarne.class.getSimpleName(), request.getConfigCarne(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigCarne>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigCarne(request.getConfigCarne(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigCarne>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configcarne list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigCarne(request.getReturnList(), request.getReturnListPaged(),new ConfigCarne());

		return response;
			}

	/**
	 * Do persistenceConfigCarne.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigCarne(ConfigCarne configcarne, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigCarne(configcarne);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigCarne(configcarne);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigCarneById(configcarne);
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
	private InternalResultsResponse<ConfigCarne> maintainReturnListConfigCarne(Boolean listIndicator, Boolean pageListIndicator,ConfigCarne configcarne)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigCarnesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigCarnes(configcarne);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigCarne>();
		}
	}

//===================================### CONFIGENTRADA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigEntrada(com.qat.samples.sysmgmt.model.request.ConfigEntradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigEntrada> insertConfigEntrada(ConfigEntradaMaintenanceRequest request)
{
	InternalResultsResponse<ConfigEntrada> response =
			processConfigEntrada(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigEntradaBAC#updateConfigEntrada(com.qat.samples.sysmgmt.model.request.ConfigEntradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigEntrada> updateConfigEntrada(ConfigEntradaMaintenanceRequest request)
{
	InternalResultsResponse<ConfigEntrada> response =
			processConfigEntrada(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigEntradaBAC#deleteConfigEntrada(com.qat.samples.sysmgmt.model.request.ConfigEntradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigEntrada> deleteConfigEntrada(ConfigEntradaMaintenanceRequest request)
{
	InternalResultsResponse<ConfigEntrada> response =
			processConfigEntrada(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigEntradaBAC#refreshConfigEntradas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigEntrada> refreshConfigEntradas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigEntradas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigEntrada(new ConfigEntrada(i, "ConfigEntradaDesc" + i));
	}

	// Call maintain to see if we need to return the configentrada list and if so whether it should be paged or not
	return maintainReturnListConfigEntrada(request.getReturnList(), request.getReturnListPaged(),new ConfigEntrada());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigEntradaBAC#fetchAllConfigEntradas(ConfigEntrada configentrada)
 */
@Override
public InternalResultsResponse<ConfigEntrada> fetchAllConfigEntradas(ConfigEntrada configentrada)
{
	InternalResultsResponse<ConfigEntrada> response = new InternalResultsResponse<ConfigEntrada>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigEntradas(configentrada).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigEntradaBAC#fetchConfigEntradaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigEntrada> fetchConfigEntradaById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigEntrada> response = new InternalResultsResponse<ConfigEntrada>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigEntradaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigEntradaBAC#fetchConfigEntradasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigEntrada> fetchConfigEntradasByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigEntradasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configentrada response
 */
private InternalResultsResponse<ConfigEntrada> processConfigEntrada(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigEntradaMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigEntrada> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigEntrada.class.getSimpleName(), request.getConfigEntrada(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigEntrada>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigEntrada(request.getConfigEntrada(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigEntrada>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configentrada list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigEntrada(request.getReturnList(), request.getReturnListPaged(),new ConfigEntrada());

		return response;
			}

	/**
	 * Do persistenceConfigEntrada.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigEntrada(ConfigEntrada configentrada, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigEntrada(configentrada);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigEntrada(configentrada);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigEntradaById(configentrada);
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
	private InternalResultsResponse<ConfigEntrada> maintainReturnListConfigEntrada(Boolean listIndicator, Boolean pageListIndicator,ConfigEntrada configentrada)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigEntradasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigEntradas(configentrada);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigEntrada>();
		}
	}

//===================================### CONFIGFISCAL ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigFiscal(com.qat.samples.sysmgmt.model.request.ConfigFiscalMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigFiscal> insertConfigFiscal(ConfigFiscalMaintenanceRequest request)
{
	InternalResultsResponse<ConfigFiscal> response =
			processConfigFiscal(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigFiscalBAC#updateConfigFiscal(com.qat.samples.sysmgmt.model.request.ConfigFiscalMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigFiscal> updateConfigFiscal(ConfigFiscalMaintenanceRequest request)
{
	InternalResultsResponse<ConfigFiscal> response =
			processConfigFiscal(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigFiscalBAC#deleteConfigFiscal(com.qat.samples.sysmgmt.model.request.ConfigFiscalMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigFiscal> deleteConfigFiscal(ConfigFiscalMaintenanceRequest request)
{
	InternalResultsResponse<ConfigFiscal> response =
			processConfigFiscal(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigFiscalBAC#refreshConfigFiscals(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigFiscal> refreshConfigFiscals(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigFiscals();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigFiscal(new ConfigFiscal(i, "ConfigFiscalDesc" + i));
	}

	// Call maintain to see if we need to return the configfiscal list and if so whether it should be paged or not
	return maintainReturnListConfigFiscal(request.getReturnList(), request.getReturnListPaged(),new ConfigFiscal());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigFiscalBAC#fetchAllConfigFiscals(ConfigFiscal configfiscal)
 */
@Override
public InternalResultsResponse<ConfigFiscal> fetchAllConfigFiscals(ConfigFiscal configfiscal)
{
	InternalResultsResponse<ConfigFiscal> response = new InternalResultsResponse<ConfigFiscal>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigFiscals(configfiscal).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigFiscalBAC#fetchConfigFiscalById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigFiscal> fetchConfigFiscalById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigFiscal> response = new InternalResultsResponse<ConfigFiscal>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigFiscalById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigFiscalBAC#fetchConfigFiscalsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigFiscal> fetchConfigFiscalsByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigFiscalsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configfiscal response
 */
private InternalResultsResponse<ConfigFiscal> processConfigFiscal(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigFiscalMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigFiscal> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigFiscal.class.getSimpleName(), request.getConfigFiscal(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigFiscal>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigFiscal(request.getConfigFiscal(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigFiscal>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configfiscal list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigFiscal(request.getReturnList(), request.getReturnListPaged(),new ConfigFiscal());

		return response;
			}

	/**
	 * Do persistenceConfigFiscal.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigFiscal(ConfigFiscal configfiscal, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigFiscal(configfiscal);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigFiscal(configfiscal);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigFiscalById(configfiscal);
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
	private InternalResultsResponse<ConfigFiscal> maintainReturnListConfigFiscal(Boolean listIndicator, Boolean pageListIndicator,ConfigFiscal configfiscal)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigFiscalsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigFiscals(configfiscal);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigFiscal>();
		}
	}

//===================================### CONFIGALERTAS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigAlertas(com.qat.samples.sysmgmt.model.request.ConfigAlertasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigAlertas> insertConfigAlertas(ConfigAlertasMaintenanceRequest request)
{
	InternalResultsResponse<ConfigAlertas> response =
			processConfigAlertas(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigAlertasBAC#updateConfigAlertas(com.qat.samples.sysmgmt.model.request.ConfigAlertasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigAlertas> updateConfigAlertas(ConfigAlertasMaintenanceRequest request)
{
	InternalResultsResponse<ConfigAlertas> response =
			processConfigAlertas(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigAlertasBAC#deleteConfigAlertas(com.qat.samples.sysmgmt.model.request.ConfigAlertasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigAlertas> deleteConfigAlertas(ConfigAlertasMaintenanceRequest request)
{
	InternalResultsResponse<ConfigAlertas> response =
			processConfigAlertas(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigAlertasBAC#refreshConfigAlertass(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigAlertas> refreshConfigAlertass(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigAlertass();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigAlertas(new ConfigAlertas(i, "ConfigAlertasDesc" + i));
	}

	// Call maintain to see if we need to return the configalertas list and if so whether it should be paged or not
	return maintainReturnListConfigAlertas(request.getReturnList(), request.getReturnListPaged(),new ConfigAlertas());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigAlertasBAC#fetchAllConfigAlertass(ConfigAlertas configalertas)
 */
@Override
public InternalResultsResponse<ConfigAlertas> fetchAllConfigAlertass(ConfigAlertas configalertas)
{
	InternalResultsResponse<ConfigAlertas> response = new InternalResultsResponse<ConfigAlertas>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigAlertass(configalertas).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigAlertasBAC#fetchConfigAlertasById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigAlertas> fetchConfigAlertasById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigAlertas> response = new InternalResultsResponse<ConfigAlertas>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigAlertasById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigAlertasBAC#fetchConfigAlertassByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigAlertas> fetchConfigAlertassByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigAlertassByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configalertas response
 */
private InternalResultsResponse<ConfigAlertas> processConfigAlertas(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigAlertasMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigAlertas> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigAlertas.class.getSimpleName(), request.getConfigAlertas(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigAlertas>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigAlertas(request.getConfigAlertas(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigAlertas>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configalertas list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigAlertas(request.getReturnList(), request.getReturnListPaged(),new ConfigAlertas());

		return response;
			}

	/**
	 * Do persistenceConfigAlertas.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigAlertas(ConfigAlertas configalertas, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigAlertas(configalertas);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigAlertas(configalertas);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigAlertasById(configalertas);
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
	private InternalResultsResponse<ConfigAlertas> maintainReturnListConfigAlertas(Boolean listIndicator, Boolean pageListIndicator,ConfigAlertas configalertas)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigAlertassByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigAlertass(configalertas);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigAlertas>();
		}
	}

//===================================### CONFIGGERAL ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigGeral(com.qat.samples.sysmgmt.model.request.ConfigGeralMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigGeral> insertConfigGeral(ConfigGeralMaintenanceRequest request)
{
	InternalResultsResponse<ConfigGeral> response =
			processConfigGeral(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigGeralBAC#updateConfigGeral(com.qat.samples.sysmgmt.model.request.ConfigGeralMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigGeral> updateConfigGeral(ConfigGeralMaintenanceRequest request)
{
	InternalResultsResponse<ConfigGeral> response =
			processConfigGeral(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigGeralBAC#deleteConfigGeral(com.qat.samples.sysmgmt.model.request.ConfigGeralMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigGeral> deleteConfigGeral(ConfigGeralMaintenanceRequest request)
{
	InternalResultsResponse<ConfigGeral> response =
			processConfigGeral(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigGeralBAC#refreshConfigGerals(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigGeral> refreshConfigGerals(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigGerals();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigGeral(new ConfigGeral(i, "ConfigGeralDesc" + i));
	}

	// Call maintain to see if we need to return the configgeral list and if so whether it should be paged or not
	return maintainReturnListConfigGeral(request.getReturnList(), request.getReturnListPaged(),new ConfigGeral());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigGeralBAC#fetchAllConfigGerals(ConfigGeral configgeral)
 */
@Override
public InternalResultsResponse<ConfigGeral> fetchAllConfigGerals(ConfigGeral configgeral)
{
	InternalResultsResponse<ConfigGeral> response = new InternalResultsResponse<ConfigGeral>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigGerals(configgeral).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigGeralBAC#fetchConfigGeralById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigGeral> fetchConfigGeralById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigGeral> response = new InternalResultsResponse<ConfigGeral>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigGeralById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigGeralBAC#fetchConfigGeralsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigGeral> fetchConfigGeralsByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigGeralsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configgeral response
 */
private InternalResultsResponse<ConfigGeral> processConfigGeral(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigGeralMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigGeral> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigGeral.class.getSimpleName(), request.getConfigGeral(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigGeral>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigGeral(request.getConfigGeral(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigGeral>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configgeral list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigGeral(request.getReturnList(), request.getReturnListPaged(),new ConfigGeral());

		return response;
			}

	/**
	 * Do persistenceConfigGeral.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigGeral(ConfigGeral configgeral, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigGeral(configgeral);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigGeral(configgeral);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigGeralById(configgeral);
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
	private InternalResultsResponse<ConfigGeral> maintainReturnListConfigGeral(Boolean listIndicator, Boolean pageListIndicator,ConfigGeral configgeral)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigGeralsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigGerals(configgeral);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigGeral>();
		}
	}

//===================================### CONFIGPRODUTO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigProduto(com.qat.samples.sysmgmt.model.request.ConfigProdutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigProduto> insertConfigProduto(ConfigProdutoMaintenanceRequest request)
{
	InternalResultsResponse<ConfigProduto> response =
			processConfigProduto(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigProdutoBAC#updateConfigProduto(com.qat.samples.sysmgmt.model.request.ConfigProdutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigProduto> updateConfigProduto(ConfigProdutoMaintenanceRequest request)
{
	InternalResultsResponse<ConfigProduto> response =
			processConfigProduto(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigProdutoBAC#deleteConfigProduto(com.qat.samples.sysmgmt.model.request.ConfigProdutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigProduto> deleteConfigProduto(ConfigProdutoMaintenanceRequest request)
{
	InternalResultsResponse<ConfigProduto> response =
			processConfigProduto(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigProdutoBAC#refreshConfigProdutos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigProduto> refreshConfigProdutos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigProdutos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigProduto(new ConfigProduto(i, "ConfigProdutoDesc" + i));
	}

	// Call maintain to see if we need to return the configproduto list and if so whether it should be paged or not
	return maintainReturnListConfigProduto(request.getReturnList(), request.getReturnListPaged(),new ConfigProduto());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigProdutoBAC#fetchAllConfigProdutos(ConfigProduto configproduto)
 */
@Override
public InternalResultsResponse<ConfigProduto> fetchAllConfigProdutos(ConfigProduto configproduto)
{
	InternalResultsResponse<ConfigProduto> response = new InternalResultsResponse<ConfigProduto>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigProdutos(configproduto).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigProdutoBAC#fetchConfigProdutoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigProduto> fetchConfigProdutoById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigProduto> response = new InternalResultsResponse<ConfigProduto>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigProdutoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigProdutoBAC#fetchConfigProdutosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigProduto> fetchConfigProdutosByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigProdutosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configproduto response
 */
private InternalResultsResponse<ConfigProduto> processConfigProduto(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigProdutoMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigProduto> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigProduto.class.getSimpleName(), request.getConfigProduto(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigProduto>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigProduto(request.getConfigProduto(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigProduto>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configproduto list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigProduto(request.getReturnList(), request.getReturnListPaged(),new ConfigProduto());

		return response;
			}

	/**
	 * Do persistenceConfigProduto.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigProduto(ConfigProduto configproduto, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigProduto(configproduto);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigProduto(configproduto);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigProdutoById(configproduto);
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
	private InternalResultsResponse<ConfigProduto> maintainReturnListConfigProduto(Boolean listIndicator, Boolean pageListIndicator,ConfigProduto configproduto)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigProdutosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigProdutos(configproduto);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigProduto>();
		}
	}

//===================================### CONFIGSMTP ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigSMTP(com.qat.samples.sysmgmt.model.request.ConfigSMTPMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigSMTP> insertConfigSMTP(ConfigSMTPMaintenanceRequest request)
{
	InternalResultsResponse<ConfigSMTP> response =
			processConfigSMTP(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigSMTPBAC#updateConfigSMTP(com.qat.samples.sysmgmt.model.request.ConfigSMTPMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigSMTP> updateConfigSMTP(ConfigSMTPMaintenanceRequest request)
{
	InternalResultsResponse<ConfigSMTP> response =
			processConfigSMTP(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigSMTPBAC#deleteConfigSMTP(com.qat.samples.sysmgmt.model.request.ConfigSMTPMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigSMTP> deleteConfigSMTP(ConfigSMTPMaintenanceRequest request)
{
	InternalResultsResponse<ConfigSMTP> response =
			processConfigSMTP(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigSMTPBAC#refreshConfigSMTPs(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigSMTP> refreshConfigSMTPs(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigSMTPs();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigSMTP(new ConfigSMTP(i, "ConfigSMTPDesc" + i));
	}

	// Call maintain to see if we need to return the configsmtp list and if so whether it should be paged or not
	return maintainReturnListConfigSMTP(request.getReturnList(), request.getReturnListPaged(),new ConfigSMTP());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigSMTPBAC#fetchAllConfigSMTPs(ConfigSMTP configsmtp)
 */
@Override
public InternalResultsResponse<ConfigSMTP> fetchAllConfigSMTPs(ConfigSMTP configsmtp)
{
	InternalResultsResponse<ConfigSMTP> response = new InternalResultsResponse<ConfigSMTP>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigSMTPs(configsmtp).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigSMTPBAC#fetchConfigSMTPById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigSMTP> fetchConfigSMTPById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigSMTP> response = new InternalResultsResponse<ConfigSMTP>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigSMTPById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigSMTPBAC#fetchConfigSMTPsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigSMTP> fetchConfigSMTPsByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigSMTPsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configsmtp response
 */
private InternalResultsResponse<ConfigSMTP> processConfigSMTP(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigSMTPMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigSMTP> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigSMTP.class.getSimpleName(), request.getConfigSMTP(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigSMTP>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigSMTP(request.getConfigSMTP(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigSMTP>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configsmtp list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigSMTP(request.getReturnList(), request.getReturnListPaged(),new ConfigSMTP());

		return response;
			}

	/**
	 * Do persistenceConfigSMTP.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigSMTP(ConfigSMTP configsmtp, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigSMTP(configsmtp);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigSMTP(configsmtp);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigSMTPById(configsmtp);
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
	private InternalResultsResponse<ConfigSMTP> maintainReturnListConfigSMTP(Boolean listIndicator, Boolean pageListIndicator,ConfigSMTP configsmtp)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigSMTPsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigSMTPs(configsmtp);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigSMTP>();
		}
	}

//===================================### CONFIGURACAONFE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfiguracaoNFe(com.qat.samples.sysmgmt.model.request.ConfiguracaoNFeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfiguracaoNFe> insertConfiguracaoNFe(ConfiguracaoNFeMaintenanceRequest request)
{
	InternalResultsResponse<ConfiguracaoNFe> response =
			processConfiguracaoNFe(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaoNFeBAC#updateConfiguracaoNFe(com.qat.samples.sysmgmt.model.request.ConfiguracaoNFeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfiguracaoNFe> updateConfiguracaoNFe(ConfiguracaoNFeMaintenanceRequest request)
{
	InternalResultsResponse<ConfiguracaoNFe> response =
			processConfiguracaoNFe(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaoNFeBAC#deleteConfiguracaoNFe(com.qat.samples.sysmgmt.model.request.ConfiguracaoNFeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfiguracaoNFe> deleteConfiguracaoNFe(ConfiguracaoNFeMaintenanceRequest request)
{
	InternalResultsResponse<ConfiguracaoNFe> response =
			processConfiguracaoNFe(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaoNFeBAC#refreshConfiguracaoNFes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfiguracaoNFe> refreshConfiguracaoNFes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfiguracaoNFes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfiguracaoNFe(new ConfiguracaoNFe(i, "ConfiguracaoNFeDesc" + i));
	}

	// Call maintain to see if we need to return the configuracaonfe list and if so whether it should be paged or not
	return maintainReturnListConfiguracaoNFe(request.getReturnList(), request.getReturnListPaged(),new ConfiguracaoNFe());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaoNFeBAC#fetchAllConfiguracaoNFes(ConfiguracaoNFe configuracaonfe)
 */
@Override
public InternalResultsResponse<ConfiguracaoNFe> fetchAllConfiguracaoNFes(ConfiguracaoNFe configuracaonfe)
{
	InternalResultsResponse<ConfiguracaoNFe> response = new InternalResultsResponse<ConfiguracaoNFe>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfiguracaoNFes(configuracaonfe).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaoNFeBAC#fetchConfiguracaoNFeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfiguracaoNFe> fetchConfiguracaoNFeById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfiguracaoNFe> response = new InternalResultsResponse<ConfiguracaoNFe>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfiguracaoNFeById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaoNFeBAC#fetchConfiguracaoNFesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfiguracaoNFe> fetchConfiguracaoNFesByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfiguracaoNFesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configuracaonfe response
 */
private InternalResultsResponse<ConfiguracaoNFe> processConfiguracaoNFe(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfiguracaoNFeMaintenanceRequest request)
		{
	InternalResultsResponse<ConfiguracaoNFe> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfiguracaoNFe.class.getSimpleName(), request.getConfiguracaoNFe(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfiguracaoNFe>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfiguracaoNFe(request.getConfiguracaoNFe(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfiguracaoNFe>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configuracaonfe list and if so whether it should be paged or
		// not
		response = maintainReturnListConfiguracaoNFe(request.getReturnList(), request.getReturnListPaged(),new ConfiguracaoNFe());

		return response;
			}

	/**
	 * Do persistenceConfiguracaoNFe.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfiguracaoNFe(ConfiguracaoNFe configuracaonfe, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfiguracaoNFe(configuracaonfe);

			case UPDATE:
				return getConfiguracaoBAR().updateConfiguracaoNFe(configuracaonfe);

			case DELETE:
				return getConfiguracaoBAR().deleteConfiguracaoNFeById(configuracaonfe);
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
	private InternalResultsResponse<ConfiguracaoNFe> maintainReturnListConfiguracaoNFe(Boolean listIndicator, Boolean pageListIndicator,ConfiguracaoNFe configuracaonfe)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfiguracaoNFesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfiguracaoNFes(configuracaonfe);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfiguracaoNFe>();
		}
	}

//===================================### CONFIGVENDAS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigVendas(com.qat.samples.sysmgmt.model.request.ConfigVendasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigVendas> insertConfigVendas(ConfigVendasMaintenanceRequest request)
{
	InternalResultsResponse<ConfigVendas> response =
			processConfigVendas(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigVendasBAC#updateConfigVendas(com.qat.samples.sysmgmt.model.request.ConfigVendasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigVendas> updateConfigVendas(ConfigVendasMaintenanceRequest request)
{
	InternalResultsResponse<ConfigVendas> response =
			processConfigVendas(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigVendasBAC#deleteConfigVendas(com.qat.samples.sysmgmt.model.request.ConfigVendasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigVendas> deleteConfigVendas(ConfigVendasMaintenanceRequest request)
{
	InternalResultsResponse<ConfigVendas> response =
			processConfigVendas(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigVendasBAC#refreshConfigVendass(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<ConfigVendas> refreshConfigVendass(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigVendass();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigVendas(new ConfigVendas(i, "ConfigVendasDesc" + i));
	}

	// Call maintain to see if we need to return the configvendas list and if so whether it should be paged or not
	return maintainReturnListConfigVendas(request.getReturnList(), request.getReturnListPaged(),new ConfigVendas());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigVendasBAC#fetchAllConfigVendass(ConfigVendas configvendas)
 */
@Override
public InternalResultsResponse<ConfigVendas> fetchAllConfigVendass(ConfigVendas configvendas)
{
	InternalResultsResponse<ConfigVendas> response = new InternalResultsResponse<ConfigVendas>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigVendass(configvendas).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigVendasBAC#fetchConfigVendasById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<ConfigVendas> fetchConfigVendasById(FetchByIdRequest request)
{
	InternalResultsResponse<ConfigVendas> response = new InternalResultsResponse<ConfigVendas>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigVendasById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigVendasBAC#fetchConfigVendassByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<ConfigVendas> fetchConfigVendassByRequest(PagedInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigVendassByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configvendas response
 */
private InternalResultsResponse<ConfigVendas> processConfigVendas(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigVendasMaintenanceRequest request)
		{
	InternalResultsResponse<ConfigVendas> response = null;

	// Validate
	ValidationContext context = new ValidationContext(ConfigVendas.class.getSimpleName(), request.getConfigVendas(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<ConfigVendas>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigVendas(request.getConfigVendas(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<ConfigVendas>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configvendas list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigVendas(request.getReturnList(), request.getReturnListPaged(),new ConfigVendas());

		return response;
			}

	/**
	 * Do persistenceConfigVendas.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigVendas(ConfigVendas configvendas, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigVendas(configvendas);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigVendas(configvendas);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigVendasById(configvendas);
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
	private InternalResultsResponse<ConfigVendas> maintainReturnListConfigVendas(Boolean listIndicator, Boolean pageListIndicator,ConfigVendas configvendas)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigVendassByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigVendass(configvendas);
			}
		}
		else
		{
			return new InternalResultsResponse<ConfigVendas>();
		}
	}
}
