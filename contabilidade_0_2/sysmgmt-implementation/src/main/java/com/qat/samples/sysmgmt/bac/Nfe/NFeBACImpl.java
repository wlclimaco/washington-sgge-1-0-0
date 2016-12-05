/** create by system gera-java version 1.0.0 28/09/2016 16:26 : 42*/
package com.qat.samples.sysmgmt.bac.Nfe;


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
import com.qat.samples.sysmgmt.bar.Nfe.INFeBAR;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
import com.qat.samples.sysmgmt.nfe.request.NFNotaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * Standards based implementation of a BAC for NFe leveraging the injected INFeBAR.
 */
@Component
public class NFeBACImpl implements INFeBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_NFE_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_NFE_BAC_EXCEPTION_MSG = "sysmgmt.base.NFebacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NFeBACImpl.class);

	/** The NFe BAR. */
	private INFeBAR nfeBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the NFe BAR.
	 *
	 * @param NFeBAR the new NFe BAR
	 */
	public void setNFeBAR(INFeBAR nfeBAR)
	{
		this.nfeBAR = nfeBAR;
	}

	/**
	 * Gets the NFe BAR.
	 *
	 * @return the NFe BAR
	 */
	public INFeBAR getNFeBAR()
	{
		return nfeBAR;
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

//===================================### NFNOTA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertNFNota(com.qat.samples.sysmgmt.model.request.NFNotaItensMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NFNota> insertNFNota(NFNotaMaintenanceRequest request)
{
	InternalResultsResponse<NFNota> response =
			processNFNota(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INFNotaBAC#updateNFNota(com.qat.samples.sysmgmt.model.request.NFNotaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NFNota> updateNFNota(NFNotaMaintenanceRequest request)
{
	InternalResultsResponse<NFNota> response =
			processNFNota(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INFNotaBAC#deleteNFNota(com.qat.samples.sysmgmt.model.request.NFNotaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<NFNota> deleteNFNota(NFNotaMaintenanceRequest request)
{
	InternalResultsResponse<NFNota> response =
			processNFNota(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}


/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INFNotaBAC#fetchAllNFNotas(NFNota nfnota)
 */
@Override
public InternalResultsResponse<NFNota> fetchAllNFNotas(NFNota nfnota)
{
	InternalResultsResponse<NFNota> response = new InternalResultsResponse<NFNota>();
	response.getResultsList().addAll(getNFeBAR().fetchAllNFNotas(nfnota).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.INFNotaBAC#fetchNFNotaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<NFNota> fetchNFNotaById(FetchByIdRequest request)
{
	InternalResultsResponse<NFNota> response = new InternalResultsResponse<NFNota>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getNFeBAR().fetchNFNotaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.INFNotaBAC#fetchNFNotasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<NFNota> fetchNFNotasByRequest(NFNotaInquiryRequest request)
{
	return getNFeBAR().fetchNFNotasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the nfnota response
 */
private InternalResultsResponse<NFNota> processNFNota(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		NFNotaMaintenanceRequest request)
		{
	InternalResultsResponse<NFNota> response = null;

	// Validate
	//ValidationContext context = new ValidationContext(NFNota.class.getSimpleName(), request.getNFNota(), indicator);
	//if (!getValidationController().validate(context))
	//{
	//	response = new InternalResultsResponse<NFNota>();
	//	response.setStatus(SystemErrorCategory.SystemValidation);
	//	response.addMessages(context.getMessages());
	//	return response;
	//}

		// Persist
		InternalResponse internalResponse = doPersistenceNFNota(request.getNFNota(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<NFNota>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_NFE_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the nfnota list and if so whether it should be paged or
		// not
		response = maintainReturnListNFNota(request.getReturnList(), request.getReturnListPaged(),new NFNota());

		return response;
			}

	/**
	 * Do persistenceNFNota.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceNFNota(NFNota nfnota, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getNFeBAR().insertNFNota(nfnota);

			case UPDATE:
				return getNFeBAR().updateNFNota(nfnota);

			case DELETE:
				return getNFeBAR().deleteNFNotaById(nfnota);
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
	private InternalResultsResponse<NFNota> maintainReturnListNFNota(Boolean listIndicator, Boolean pageListIndicator,NFNota nfnota)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				NFNotaInquiryRequest request = new NFNotaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchNFNotasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllNFNotas(nfnota);
			}
		}
		else
		{
			return new InternalResultsResponse<NFNota>();
		}
	}


}
