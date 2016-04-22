package com.qat.samples.sysmgmt.bac.Fiscal;


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
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Fiscal leveraging the injected IFiscalBAR.
 */
@Component
public class FiscalBACImpl implements IFiscalBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_FISCAL_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_FISCAL_BAC_EXCEPTION_MSG = "sysmgmt.base.Fiscalbacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FiscalBACImpl.class);

	/** The Fiscal BAR. */
	private IFiscalBAR fiscalBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Fiscal BAR.
	 *
	 * @param FiscalBAR the new Fiscal BAR
	 */
	public void setFiscalBAR(IFiscalBAR fiscalBAR)
	{
		this.fiscalBAR = fiscalBAR;
	}

	/**
	 * Gets the Fiscal BAR.
	 *
	 * @return the Fiscal BAR
	 */
	public IFiscalBAR getFiscalBAR()
	{
		return fiscalBAR;
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

//===================================### REGIME ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertRegime(com.qat.samples.sysmgmt.model.request.RegimeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Regime> insertRegime(RegimeMaintenanceRequest request)
{
	InternalResultsResponse<Regime> response =
			processRegime(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IRegimeBAC#updateRegime(com.qat.samples.sysmgmt.model.request.RegimeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Regime> updateRegime(RegimeMaintenanceRequest request)
{
	InternalResultsResponse<Regime> response =
			processRegime(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IRegimeBAC#deleteRegime(com.qat.samples.sysmgmt.model.request.RegimeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Regime> deleteRegime(RegimeMaintenanceRequest request)
{
	InternalResultsResponse<Regime> response =
			processRegime(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IRegimeBAC#refreshRegimes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Regime> refreshRegimes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFiscalBAR().deleteAllRegimes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFiscalBAR().insertRegime(new Regime(i, "RegimeDesc" + i));
	}

	// Call maintain to see if we need to return the regime list and if so whether it should be paged or not
	return maintainReturnListRegime(request.getReturnList(), request.getReturnListPaged(),new Regime());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IRegimeBAC#fetchAllRegimes(Regime regime)
 */
@Override
public InternalResultsResponse<Regime> fetchAllRegimes(Regime regime)
{
	InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();
	response.getResultsList().addAll(getFiscalBAR().fetchAllRegimes(regime).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IRegimeBAC#fetchRegimeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Regime> fetchRegimeById(FetchByIdRequest request)
{
	InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFiscalBAR().fetchRegimeById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IRegimeBAC#fetchRegimesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Regime> fetchRegimesByRequest(RegimeInquiryRequest request)
{
	return getFiscalBAR().fetchRegimesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the regime response
 */
private InternalResultsResponse<Regime> processRegime(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		RegimeMaintenanceRequest request)
		{
	InternalResultsResponse<Regime> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Regime.class.getSimpleName(), request.getRegime(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Regime>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceRegime(request.getRegime(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Regime>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FISCAL_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the regime list and if so whether it should be paged or
		// not
		response = maintainReturnListRegime(request.getReturnList(), request.getReturnListPaged(),new Regime());

		return response;
			}

	/**
	 * Do persistenceRegime.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceRegime(Regime regime, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFiscalBAR().insertRegime(regime);

			case UPDATE:
				return getFiscalBAR().updateRegime(regime);

			case DELETE:
				return getFiscalBAR().deleteRegimeById(regime);
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
	private InternalResultsResponse<Regime> maintainReturnListRegime(Boolean listIndicator, Boolean pageListIndicator,Regime regime)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				RegimeInquiryRequest request = new RegimeInquiryRequest();
				request.setPreQueryCount(true);
				return fetchRegimesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllRegimes(regime);
			}
		}
		else
		{
			return new InternalResultsResponse<Regime>();
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
	getFiscalBAR().deleteAllCfops();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFiscalBAR().insertCfop(new Cfop(i, "CfopDesc" + i));
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
	response.getResultsList().addAll(getFiscalBAR().fetchAllCfops(cfop).getResultsList());
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
		response.getResultsList().add(getFiscalBAR().fetchCfopById(request));
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
	return getFiscalBAR().fetchCfopsByRequest(request);
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
	ValidationContext context = new ValidationContext(Cfop.class.getSimpleName(), request.getCfop(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Cfop>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceCfop(request.getCfop(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Cfop>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FISCAL_BAC_EXCEPTION_MSG, MessageSeverity.Error,
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
				return getFiscalBAR().insertCfop(cfop);

			case UPDATE:
				return getFiscalBAR().updateCfop(cfop);

			case DELETE:
				return getFiscalBAR().deleteCfopById(cfop);
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

//===================================### CNAE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCnae(com.qat.samples.sysmgmt.model.request.CnaeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request)
{
	InternalResultsResponse<Cnae> response =
			processCnae(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICnaeBAC#updateCnae(com.qat.samples.sysmgmt.model.request.CnaeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request)
{
	InternalResultsResponse<Cnae> response =
			processCnae(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICnaeBAC#deleteCnae(com.qat.samples.sysmgmt.model.request.CnaeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cnae> deleteCnae(CnaeMaintenanceRequest request)
{
	InternalResultsResponse<Cnae> response =
			processCnae(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICnaeBAC#refreshCnaes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Cnae> refreshCnaes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getFiscalBAR().deleteAllCnaes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getFiscalBAR().insertCnae(new Cnae(i, "CnaeDesc" + i));
	}

	// Call maintain to see if we need to return the cnae list and if so whether it should be paged or not
	return maintainReturnListCnae(request.getReturnList(), request.getReturnListPaged(),new Cnae());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICnaeBAC#fetchAllCnaes(Cnae cnae)
 */
@Override
public InternalResultsResponse<Cnae> fetchAllCnaes(Cnae cnae)
{
	InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
	response.getResultsList().addAll(getFiscalBAR().fetchAllCnaes(cnae).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICnaeBAC#fetchCnaeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Cnae> fetchCnaeById(FetchByIdRequest request)
{
	InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getFiscalBAR().fetchCnaeById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICnaeBAC#fetchCnaesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cnae> fetchCnaesByRequest(CnaeInquiryRequest request)
{
	return getFiscalBAR().fetchCnaesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the cnae response
 */
private InternalResultsResponse<Cnae> processCnae(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CnaeMaintenanceRequest request)
		{
	InternalResultsResponse<Cnae> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Cnae.class.getSimpleName(), request.getCnae(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Cnae>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceCnae(request.getCnae(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Cnae>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_FISCAL_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the cnae list and if so whether it should be paged or
		// not
		response = maintainReturnListCnae(request.getReturnList(), request.getReturnListPaged(),new Cnae());

		return response;
			}

	/**
	 * Do persistenceCnae.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCnae(Cnae cnae, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getFiscalBAR().insertCnae(cnae);

			case UPDATE:
				return getFiscalBAR().updateCnae(cnae);

			case DELETE:
				return getFiscalBAR().deleteCnaeById(cnae);
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
	private InternalResultsResponse<Cnae> maintainReturnListCnae(Boolean listIndicator, Boolean pageListIndicator,Cnae cnae)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CnaeInquiryRequest request = new CnaeInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCnaesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCnaes(cnae);
			}
		}
		else
		{
			return new InternalResultsResponse<Cnae>();
		}
	}
}
