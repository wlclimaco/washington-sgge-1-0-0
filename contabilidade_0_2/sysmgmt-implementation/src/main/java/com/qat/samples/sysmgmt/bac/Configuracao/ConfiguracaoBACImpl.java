/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.undefined;


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
import com.qat.samples.sysmgmt.bac.IConfiguracaoBAC;
import com.qat.samples.sysmgmt.bar.IComprasBAR;
import com.qat.samples.sysmgmt.model.Configuracao;
import com.qat.samples.sysmgmt.model.request.ConfiguracaoMaintenanceRequest;
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
public InternalResultsResponse<Configuracao> fetchConfiguracaosByRequest(ConfiguracaoInquiryRequest request)
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
				ConfiguracaoInquiryRequest request = new ConfiguracaoInquiryRequest();
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
public InternalResultsResponse<Boleto> fetchBoletosByRequest(BoletoInquiryRequest request)
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
				BoletoInquiryRequest request = new BoletoInquiryRequest();
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
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigcarne(com.qat.samples.sysmgmt.model.request.ConfigcarneMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configcarne> insertConfigcarne(ConfigcarneMaintenanceRequest request)
{
	InternalResultsResponse<Configcarne> response =
			processConfigcarne(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigcarneBAC#updateConfigcarne(com.qat.samples.sysmgmt.model.request.ConfigcarneMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configcarne> updateConfigcarne(ConfigcarneMaintenanceRequest request)
{
	InternalResultsResponse<Configcarne> response =
			processConfigcarne(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigcarneBAC#deleteConfigcarne(com.qat.samples.sysmgmt.model.request.ConfigcarneMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configcarne> deleteConfigcarne(ConfigcarneMaintenanceRequest request)
{
	InternalResultsResponse<Configcarne> response =
			processConfigcarne(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigcarneBAC#refreshConfigcarnes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configcarne> refreshConfigcarnes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigcarnes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigcarne(new Configcarne(i, "ConfigcarneDesc" + i));
	}

	// Call maintain to see if we need to return the configcarne list and if so whether it should be paged or not
	return maintainReturnListConfigcarne(request.getReturnList(), request.getReturnListPaged(),new Configcarne());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigcarneBAC#fetchAllConfigcarnes(Configcarne configcarne)
 */
@Override
public InternalResultsResponse<Configcarne> fetchAllConfigcarnes(Configcarne configcarne)
{
	InternalResultsResponse<Configcarne> response = new InternalResultsResponse<Configcarne>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigcarnes(configcarne).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigcarneBAC#fetchConfigcarneById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configcarne> fetchConfigcarneById(FetchByIdRequest request)
{
	InternalResultsResponse<Configcarne> response = new InternalResultsResponse<Configcarne>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigcarneById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigcarneBAC#fetchConfigcarnesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configcarne> fetchConfigcarnesByRequest(ConfigcarneInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigcarnesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configcarne response
 */
private InternalResultsResponse<Configcarne> processConfigcarne(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigcarneMaintenanceRequest request)
		{
	InternalResultsResponse<Configcarne> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configcarne.class.getSimpleName(), request.getConfigcarne(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configcarne>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigcarne(request.getConfigcarne(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configcarne>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configcarne list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigcarne(request.getReturnList(), request.getReturnListPaged(),new Configcarne());

		return response;
			}

	/**
	 * Do persistenceConfigcarne.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigcarne(Configcarne configcarne, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigcarne(configcarne);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigcarne(configcarne);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigcarneById(configcarne);
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
	private InternalResultsResponse<Configcarne> maintainReturnListConfigcarne(Boolean listIndicator, Boolean pageListIndicator,Configcarne configcarne)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfigcarneInquiryRequest request = new ConfigcarneInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigcarnesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigcarnes(configcarne);
			}
		}
		else
		{
			return new InternalResultsResponse<Configcarne>();
		}
	}

//===================================### CONFIGENTRADA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigentrada(com.qat.samples.sysmgmt.model.request.ConfigentradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configentrada> insertConfigentrada(ConfigentradaMaintenanceRequest request)
{
	InternalResultsResponse<Configentrada> response =
			processConfigentrada(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigentradaBAC#updateConfigentrada(com.qat.samples.sysmgmt.model.request.ConfigentradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configentrada> updateConfigentrada(ConfigentradaMaintenanceRequest request)
{
	InternalResultsResponse<Configentrada> response =
			processConfigentrada(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigentradaBAC#deleteConfigentrada(com.qat.samples.sysmgmt.model.request.ConfigentradaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configentrada> deleteConfigentrada(ConfigentradaMaintenanceRequest request)
{
	InternalResultsResponse<Configentrada> response =
			processConfigentrada(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigentradaBAC#refreshConfigentradas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configentrada> refreshConfigentradas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigentradas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigentrada(new Configentrada(i, "ConfigentradaDesc" + i));
	}

	// Call maintain to see if we need to return the configentrada list and if so whether it should be paged or not
	return maintainReturnListConfigentrada(request.getReturnList(), request.getReturnListPaged(),new Configentrada());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigentradaBAC#fetchAllConfigentradas(Configentrada configentrada)
 */
@Override
public InternalResultsResponse<Configentrada> fetchAllConfigentradas(Configentrada configentrada)
{
	InternalResultsResponse<Configentrada> response = new InternalResultsResponse<Configentrada>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigentradas(configentrada).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigentradaBAC#fetchConfigentradaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configentrada> fetchConfigentradaById(FetchByIdRequest request)
{
	InternalResultsResponse<Configentrada> response = new InternalResultsResponse<Configentrada>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigentradaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigentradaBAC#fetchConfigentradasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configentrada> fetchConfigentradasByRequest(ConfigentradaInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigentradasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configentrada response
 */
private InternalResultsResponse<Configentrada> processConfigentrada(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigentradaMaintenanceRequest request)
		{
	InternalResultsResponse<Configentrada> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configentrada.class.getSimpleName(), request.getConfigentrada(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configentrada>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigentrada(request.getConfigentrada(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configentrada>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configentrada list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigentrada(request.getReturnList(), request.getReturnListPaged(),new Configentrada());

		return response;
			}

	/**
	 * Do persistenceConfigentrada.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigentrada(Configentrada configentrada, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigentrada(configentrada);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigentrada(configentrada);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigentradaById(configentrada);
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
	private InternalResultsResponse<Configentrada> maintainReturnListConfigentrada(Boolean listIndicator, Boolean pageListIndicator,Configentrada configentrada)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfigentradaInquiryRequest request = new ConfigentradaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigentradasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigentradas(configentrada);
			}
		}
		else
		{
			return new InternalResultsResponse<Configentrada>();
		}
	}

//===================================### CONFIGFISCAL ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigfiscal(com.qat.samples.sysmgmt.model.request.ConfigfiscalMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configfiscal> insertConfigfiscal(ConfigfiscalMaintenanceRequest request)
{
	InternalResultsResponse<Configfiscal> response =
			processConfigfiscal(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigfiscalBAC#updateConfigfiscal(com.qat.samples.sysmgmt.model.request.ConfigfiscalMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configfiscal> updateConfigfiscal(ConfigfiscalMaintenanceRequest request)
{
	InternalResultsResponse<Configfiscal> response =
			processConfigfiscal(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigfiscalBAC#deleteConfigfiscal(com.qat.samples.sysmgmt.model.request.ConfigfiscalMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configfiscal> deleteConfigfiscal(ConfigfiscalMaintenanceRequest request)
{
	InternalResultsResponse<Configfiscal> response =
			processConfigfiscal(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigfiscalBAC#refreshConfigfiscals(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configfiscal> refreshConfigfiscals(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigfiscals();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigfiscal(new Configfiscal(i, "ConfigfiscalDesc" + i));
	}

	// Call maintain to see if we need to return the configfiscal list and if so whether it should be paged or not
	return maintainReturnListConfigfiscal(request.getReturnList(), request.getReturnListPaged(),new Configfiscal());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigfiscalBAC#fetchAllConfigfiscals(Configfiscal configfiscal)
 */
@Override
public InternalResultsResponse<Configfiscal> fetchAllConfigfiscals(Configfiscal configfiscal)
{
	InternalResultsResponse<Configfiscal> response = new InternalResultsResponse<Configfiscal>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigfiscals(configfiscal).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigfiscalBAC#fetchConfigfiscalById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configfiscal> fetchConfigfiscalById(FetchByIdRequest request)
{
	InternalResultsResponse<Configfiscal> response = new InternalResultsResponse<Configfiscal>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigfiscalById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigfiscalBAC#fetchConfigfiscalsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configfiscal> fetchConfigfiscalsByRequest(ConfigfiscalInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigfiscalsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configfiscal response
 */
private InternalResultsResponse<Configfiscal> processConfigfiscal(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigfiscalMaintenanceRequest request)
		{
	InternalResultsResponse<Configfiscal> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configfiscal.class.getSimpleName(), request.getConfigfiscal(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configfiscal>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigfiscal(request.getConfigfiscal(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configfiscal>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configfiscal list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigfiscal(request.getReturnList(), request.getReturnListPaged(),new Configfiscal());

		return response;
			}

	/**
	 * Do persistenceConfigfiscal.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigfiscal(Configfiscal configfiscal, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigfiscal(configfiscal);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigfiscal(configfiscal);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigfiscalById(configfiscal);
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
	private InternalResultsResponse<Configfiscal> maintainReturnListConfigfiscal(Boolean listIndicator, Boolean pageListIndicator,Configfiscal configfiscal)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfigfiscalInquiryRequest request = new ConfigfiscalInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigfiscalsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigfiscals(configfiscal);
			}
		}
		else
		{
			return new InternalResultsResponse<Configfiscal>();
		}
	}

//===================================### CONFIGALERTAS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigalertas(com.qat.samples.sysmgmt.model.request.ConfigalertasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configalertas> insertConfigalertas(ConfigalertasMaintenanceRequest request)
{
	InternalResultsResponse<Configalertas> response =
			processConfigalertas(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigalertasBAC#updateConfigalertas(com.qat.samples.sysmgmt.model.request.ConfigalertasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configalertas> updateConfigalertas(ConfigalertasMaintenanceRequest request)
{
	InternalResultsResponse<Configalertas> response =
			processConfigalertas(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigalertasBAC#deleteConfigalertas(com.qat.samples.sysmgmt.model.request.ConfigalertasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configalertas> deleteConfigalertas(ConfigalertasMaintenanceRequest request)
{
	InternalResultsResponse<Configalertas> response =
			processConfigalertas(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigalertasBAC#refreshConfigalertass(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configalertas> refreshConfigalertass(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigalertass();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigalertas(new Configalertas(i, "ConfigalertasDesc" + i));
	}

	// Call maintain to see if we need to return the configalertas list and if so whether it should be paged or not
	return maintainReturnListConfigalertas(request.getReturnList(), request.getReturnListPaged(),new Configalertas());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigalertasBAC#fetchAllConfigalertass(Configalertas configalertas)
 */
@Override
public InternalResultsResponse<Configalertas> fetchAllConfigalertass(Configalertas configalertas)
{
	InternalResultsResponse<Configalertas> response = new InternalResultsResponse<Configalertas>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigalertass(configalertas).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigalertasBAC#fetchConfigalertasById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configalertas> fetchConfigalertasById(FetchByIdRequest request)
{
	InternalResultsResponse<Configalertas> response = new InternalResultsResponse<Configalertas>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigalertasById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigalertasBAC#fetchConfigalertassByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configalertas> fetchConfigalertassByRequest(ConfigalertasInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigalertassByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configalertas response
 */
private InternalResultsResponse<Configalertas> processConfigalertas(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigalertasMaintenanceRequest request)
		{
	InternalResultsResponse<Configalertas> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configalertas.class.getSimpleName(), request.getConfigalertas(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configalertas>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigalertas(request.getConfigalertas(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configalertas>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configalertas list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigalertas(request.getReturnList(), request.getReturnListPaged(),new Configalertas());

		return response;
			}

	/**
	 * Do persistenceConfigalertas.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigalertas(Configalertas configalertas, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigalertas(configalertas);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigalertas(configalertas);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigalertasById(configalertas);
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
	private InternalResultsResponse<Configalertas> maintainReturnListConfigalertas(Boolean listIndicator, Boolean pageListIndicator,Configalertas configalertas)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfigalertasInquiryRequest request = new ConfigalertasInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigalertassByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigalertass(configalertas);
			}
		}
		else
		{
			return new InternalResultsResponse<Configalertas>();
		}
	}

//===================================### CONFIGGERAL ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfiggeral(com.qat.samples.sysmgmt.model.request.ConfiggeralMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configgeral> insertConfiggeral(ConfiggeralMaintenanceRequest request)
{
	InternalResultsResponse<Configgeral> response =
			processConfiggeral(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiggeralBAC#updateConfiggeral(com.qat.samples.sysmgmt.model.request.ConfiggeralMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configgeral> updateConfiggeral(ConfiggeralMaintenanceRequest request)
{
	InternalResultsResponse<Configgeral> response =
			processConfiggeral(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiggeralBAC#deleteConfiggeral(com.qat.samples.sysmgmt.model.request.ConfiggeralMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configgeral> deleteConfiggeral(ConfiggeralMaintenanceRequest request)
{
	InternalResultsResponse<Configgeral> response =
			processConfiggeral(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiggeralBAC#refreshConfiggerals(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configgeral> refreshConfiggerals(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfiggerals();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfiggeral(new Configgeral(i, "ConfiggeralDesc" + i));
	}

	// Call maintain to see if we need to return the configgeral list and if so whether it should be paged or not
	return maintainReturnListConfiggeral(request.getReturnList(), request.getReturnListPaged(),new Configgeral());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiggeralBAC#fetchAllConfiggerals(Configgeral configgeral)
 */
@Override
public InternalResultsResponse<Configgeral> fetchAllConfiggerals(Configgeral configgeral)
{
	InternalResultsResponse<Configgeral> response = new InternalResultsResponse<Configgeral>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfiggerals(configgeral).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiggeralBAC#fetchConfiggeralById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configgeral> fetchConfiggeralById(FetchByIdRequest request)
{
	InternalResultsResponse<Configgeral> response = new InternalResultsResponse<Configgeral>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfiggeralById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiggeralBAC#fetchConfiggeralsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configgeral> fetchConfiggeralsByRequest(ConfiggeralInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfiggeralsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configgeral response
 */
private InternalResultsResponse<Configgeral> processConfiggeral(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfiggeralMaintenanceRequest request)
		{
	InternalResultsResponse<Configgeral> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configgeral.class.getSimpleName(), request.getConfiggeral(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configgeral>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfiggeral(request.getConfiggeral(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configgeral>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configgeral list and if so whether it should be paged or
		// not
		response = maintainReturnListConfiggeral(request.getReturnList(), request.getReturnListPaged(),new Configgeral());

		return response;
			}

	/**
	 * Do persistenceConfiggeral.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfiggeral(Configgeral configgeral, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfiggeral(configgeral);

			case UPDATE:
				return getConfiguracaoBAR().updateConfiggeral(configgeral);

			case DELETE:
				return getConfiguracaoBAR().deleteConfiggeralById(configgeral);
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
	private InternalResultsResponse<Configgeral> maintainReturnListConfiggeral(Boolean listIndicator, Boolean pageListIndicator,Configgeral configgeral)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfiggeralInquiryRequest request = new ConfiggeralInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfiggeralsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfiggerals(configgeral);
			}
		}
		else
		{
			return new InternalResultsResponse<Configgeral>();
		}
	}

//===================================### CONFIGPRODUTO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigproduto(com.qat.samples.sysmgmt.model.request.ConfigprodutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configproduto> insertConfigproduto(ConfigprodutoMaintenanceRequest request)
{
	InternalResultsResponse<Configproduto> response =
			processConfigproduto(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigprodutoBAC#updateConfigproduto(com.qat.samples.sysmgmt.model.request.ConfigprodutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configproduto> updateConfigproduto(ConfigprodutoMaintenanceRequest request)
{
	InternalResultsResponse<Configproduto> response =
			processConfigproduto(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigprodutoBAC#deleteConfigproduto(com.qat.samples.sysmgmt.model.request.ConfigprodutoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configproduto> deleteConfigproduto(ConfigprodutoMaintenanceRequest request)
{
	InternalResultsResponse<Configproduto> response =
			processConfigproduto(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigprodutoBAC#refreshConfigprodutos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configproduto> refreshConfigprodutos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigprodutos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigproduto(new Configproduto(i, "ConfigprodutoDesc" + i));
	}

	// Call maintain to see if we need to return the configproduto list and if so whether it should be paged or not
	return maintainReturnListConfigproduto(request.getReturnList(), request.getReturnListPaged(),new Configproduto());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigprodutoBAC#fetchAllConfigprodutos(Configproduto configproduto)
 */
@Override
public InternalResultsResponse<Configproduto> fetchAllConfigprodutos(Configproduto configproduto)
{
	InternalResultsResponse<Configproduto> response = new InternalResultsResponse<Configproduto>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigprodutos(configproduto).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigprodutoBAC#fetchConfigprodutoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configproduto> fetchConfigprodutoById(FetchByIdRequest request)
{
	InternalResultsResponse<Configproduto> response = new InternalResultsResponse<Configproduto>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigprodutoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigprodutoBAC#fetchConfigprodutosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configproduto> fetchConfigprodutosByRequest(ConfigprodutoInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigprodutosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configproduto response
 */
private InternalResultsResponse<Configproduto> processConfigproduto(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigprodutoMaintenanceRequest request)
		{
	InternalResultsResponse<Configproduto> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configproduto.class.getSimpleName(), request.getConfigproduto(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configproduto>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigproduto(request.getConfigproduto(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configproduto>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configproduto list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigproduto(request.getReturnList(), request.getReturnListPaged(),new Configproduto());

		return response;
			}

	/**
	 * Do persistenceConfigproduto.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigproduto(Configproduto configproduto, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigproduto(configproduto);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigproduto(configproduto);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigprodutoById(configproduto);
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
	private InternalResultsResponse<Configproduto> maintainReturnListConfigproduto(Boolean listIndicator, Boolean pageListIndicator,Configproduto configproduto)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfigprodutoInquiryRequest request = new ConfigprodutoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigprodutosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigprodutos(configproduto);
			}
		}
		else
		{
			return new InternalResultsResponse<Configproduto>();
		}
	}

//===================================### CONFIGSMTP ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigsmtp(com.qat.samples.sysmgmt.model.request.ConfigsmtpMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configsmtp> insertConfigsmtp(ConfigsmtpMaintenanceRequest request)
{
	InternalResultsResponse<Configsmtp> response =
			processConfigsmtp(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigsmtpBAC#updateConfigsmtp(com.qat.samples.sysmgmt.model.request.ConfigsmtpMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configsmtp> updateConfigsmtp(ConfigsmtpMaintenanceRequest request)
{
	InternalResultsResponse<Configsmtp> response =
			processConfigsmtp(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigsmtpBAC#deleteConfigsmtp(com.qat.samples.sysmgmt.model.request.ConfigsmtpMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configsmtp> deleteConfigsmtp(ConfigsmtpMaintenanceRequest request)
{
	InternalResultsResponse<Configsmtp> response =
			processConfigsmtp(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigsmtpBAC#refreshConfigsmtps(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configsmtp> refreshConfigsmtps(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigsmtps();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigsmtp(new Configsmtp(i, "ConfigsmtpDesc" + i));
	}

	// Call maintain to see if we need to return the configsmtp list and if so whether it should be paged or not
	return maintainReturnListConfigsmtp(request.getReturnList(), request.getReturnListPaged(),new Configsmtp());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigsmtpBAC#fetchAllConfigsmtps(Configsmtp configsmtp)
 */
@Override
public InternalResultsResponse<Configsmtp> fetchAllConfigsmtps(Configsmtp configsmtp)
{
	InternalResultsResponse<Configsmtp> response = new InternalResultsResponse<Configsmtp>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigsmtps(configsmtp).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigsmtpBAC#fetchConfigsmtpById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configsmtp> fetchConfigsmtpById(FetchByIdRequest request)
{
	InternalResultsResponse<Configsmtp> response = new InternalResultsResponse<Configsmtp>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigsmtpById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigsmtpBAC#fetchConfigsmtpsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configsmtp> fetchConfigsmtpsByRequest(ConfigsmtpInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigsmtpsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configsmtp response
 */
private InternalResultsResponse<Configsmtp> processConfigsmtp(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigsmtpMaintenanceRequest request)
		{
	InternalResultsResponse<Configsmtp> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configsmtp.class.getSimpleName(), request.getConfigsmtp(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configsmtp>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigsmtp(request.getConfigsmtp(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configsmtp>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configsmtp list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigsmtp(request.getReturnList(), request.getReturnListPaged(),new Configsmtp());

		return response;
			}

	/**
	 * Do persistenceConfigsmtp.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigsmtp(Configsmtp configsmtp, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigsmtp(configsmtp);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigsmtp(configsmtp);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigsmtpById(configsmtp);
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
	private InternalResultsResponse<Configsmtp> maintainReturnListConfigsmtp(Boolean listIndicator, Boolean pageListIndicator,Configsmtp configsmtp)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfigsmtpInquiryRequest request = new ConfigsmtpInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigsmtpsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigsmtps(configsmtp);
			}
		}
		else
		{
			return new InternalResultsResponse<Configsmtp>();
		}
	}

//===================================### CONFIGURACAONFE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfiguracaonfe(com.qat.samples.sysmgmt.model.request.ConfiguracaonfeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracaonfe> insertConfiguracaonfe(ConfiguracaonfeMaintenanceRequest request)
{
	InternalResultsResponse<Configuracaonfe> response =
			processConfiguracaonfe(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaonfeBAC#updateConfiguracaonfe(com.qat.samples.sysmgmt.model.request.ConfiguracaonfeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracaonfe> updateConfiguracaonfe(ConfiguracaonfeMaintenanceRequest request)
{
	InternalResultsResponse<Configuracaonfe> response =
			processConfiguracaonfe(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaonfeBAC#deleteConfiguracaonfe(com.qat.samples.sysmgmt.model.request.ConfiguracaonfeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracaonfe> deleteConfiguracaonfe(ConfiguracaonfeMaintenanceRequest request)
{
	InternalResultsResponse<Configuracaonfe> response =
			processConfiguracaonfe(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaonfeBAC#refreshConfiguracaonfes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configuracaonfe> refreshConfiguracaonfes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfiguracaonfes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfiguracaonfe(new Configuracaonfe(i, "ConfiguracaonfeDesc" + i));
	}

	// Call maintain to see if we need to return the configuracaonfe list and if so whether it should be paged or not
	return maintainReturnListConfiguracaonfe(request.getReturnList(), request.getReturnListPaged(),new Configuracaonfe());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaonfeBAC#fetchAllConfiguracaonfes(Configuracaonfe configuracaonfe)
 */
@Override
public InternalResultsResponse<Configuracaonfe> fetchAllConfiguracaonfes(Configuracaonfe configuracaonfe)
{
	InternalResultsResponse<Configuracaonfe> response = new InternalResultsResponse<Configuracaonfe>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfiguracaonfes(configuracaonfe).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfiguracaonfeBAC#fetchConfiguracaonfeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configuracaonfe> fetchConfiguracaonfeById(FetchByIdRequest request)
{
	InternalResultsResponse<Configuracaonfe> response = new InternalResultsResponse<Configuracaonfe>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfiguracaonfeById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfiguracaonfeBAC#fetchConfiguracaonfesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configuracaonfe> fetchConfiguracaonfesByRequest(ConfiguracaonfeInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfiguracaonfesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configuracaonfe response
 */
private InternalResultsResponse<Configuracaonfe> processConfiguracaonfe(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfiguracaonfeMaintenanceRequest request)
		{
	InternalResultsResponse<Configuracaonfe> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configuracaonfe.class.getSimpleName(), request.getConfiguracaonfe(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configuracaonfe>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfiguracaonfe(request.getConfiguracaonfe(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configuracaonfe>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configuracaonfe list and if so whether it should be paged or
		// not
		response = maintainReturnListConfiguracaonfe(request.getReturnList(), request.getReturnListPaged(),new Configuracaonfe());

		return response;
			}

	/**
	 * Do persistenceConfiguracaonfe.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfiguracaonfe(Configuracaonfe configuracaonfe, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfiguracaonfe(configuracaonfe);

			case UPDATE:
				return getConfiguracaoBAR().updateConfiguracaonfe(configuracaonfe);

			case DELETE:
				return getConfiguracaoBAR().deleteConfiguracaonfeById(configuracaonfe);
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
	private InternalResultsResponse<Configuracaonfe> maintainReturnListConfiguracaonfe(Boolean listIndicator, Boolean pageListIndicator,Configuracaonfe configuracaonfe)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfiguracaonfeInquiryRequest request = new ConfiguracaonfeInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfiguracaonfesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfiguracaonfes(configuracaonfe);
			}
		}
		else
		{
			return new InternalResultsResponse<Configuracaonfe>();
		}
	}

//===================================### CONFIGVENDAS ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConfigvendas(com.qat.samples.sysmgmt.model.request.ConfigvendasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configvendas> insertConfigvendas(ConfigvendasMaintenanceRequest request)
{
	InternalResultsResponse<Configvendas> response =
			processConfigvendas(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigvendasBAC#updateConfigvendas(com.qat.samples.sysmgmt.model.request.ConfigvendasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configvendas> updateConfigvendas(ConfigvendasMaintenanceRequest request)
{
	InternalResultsResponse<Configvendas> response =
			processConfigvendas(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigvendasBAC#deleteConfigvendas(com.qat.samples.sysmgmt.model.request.ConfigvendasMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Configvendas> deleteConfigvendas(ConfigvendasMaintenanceRequest request)
{
	InternalResultsResponse<Configvendas> response =
			processConfigvendas(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigvendasBAC#refreshConfigvendass(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Configvendas> refreshConfigvendass(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getConfiguracaoBAR().deleteAllConfigvendass();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getConfiguracaoBAR().insertConfigvendas(new Configvendas(i, "ConfigvendasDesc" + i));
	}

	// Call maintain to see if we need to return the configvendas list and if so whether it should be paged or not
	return maintainReturnListConfigvendas(request.getReturnList(), request.getReturnListPaged(),new Configvendas());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigvendasBAC#fetchAllConfigvendass(Configvendas configvendas)
 */
@Override
public InternalResultsResponse<Configvendas> fetchAllConfigvendass(Configvendas configvendas)
{
	InternalResultsResponse<Configvendas> response = new InternalResultsResponse<Configvendas>();
	response.getResultsList().addAll(getConfiguracaoBAR().fetchAllConfigvendass(configvendas).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConfigvendasBAC#fetchConfigvendasById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Configvendas> fetchConfigvendasById(FetchByIdRequest request)
{
	InternalResultsResponse<Configvendas> response = new InternalResultsResponse<Configvendas>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getConfiguracaoBAR().fetchConfigvendasById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConfigvendasBAC#fetchConfigvendassByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Configvendas> fetchConfigvendassByRequest(ConfigvendasInquiryRequest request)
{
	return getConfiguracaoBAR().fetchConfigvendassByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the configvendas response
 */
private InternalResultsResponse<Configvendas> processConfigvendas(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConfigvendasMaintenanceRequest request)
		{
	InternalResultsResponse<Configvendas> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Configvendas.class.getSimpleName(), request.getConfigvendas(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Configvendas>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConfigvendas(request.getConfigvendas(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Configvendas>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CONFIGURACAO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the configvendas list and if so whether it should be paged or
		// not
		response = maintainReturnListConfigvendas(request.getReturnList(), request.getReturnListPaged(),new Configvendas());

		return response;
			}

	/**
	 * Do persistenceConfigvendas.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConfigvendas(Configvendas configvendas, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getConfiguracaoBAR().insertConfigvendas(configvendas);

			case UPDATE:
				return getConfiguracaoBAR().updateConfigvendas(configvendas);

			case DELETE:
				return getConfiguracaoBAR().deleteConfigvendasById(configvendas);
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
	private InternalResultsResponse<Configvendas> maintainReturnListConfigvendas(Boolean listIndicator, Boolean pageListIndicator,Configvendas configvendas)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConfigvendasInquiryRequest request = new ConfigvendasInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConfigvendassByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConfigvendass(configvendas);
			}
		}
		else
		{
			return new InternalResultsResponse<Configvendas>();
		}
	}
}
