package com.qat.samples.sysmgmt.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bac.IDrugBAC;
import com.qat.samples.sysmgmt.bai.IDrugBAI;
import com.qat.samples.sysmgmt.bas.ws.DrugBAS;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByCodeRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.DrugResponse;

/**
 * Implementation of a BAI, well formed with lots of modularization.
 */
public class DrugBAIImpl extends BaseBAI implements IDrugBAI
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DrugBAS.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.drugbaiimpl.defaultexception";
	private static final String SYSMGMT_BASE_CODE_REQUIRED = "sysmgmt.base.validator.code.required";

	/** The drug bac. */
	private IDrugBAC drugBAC; // injected by Spring through setter

	/**
	 * Spring Sets the drug bac.
	 * 
	 * @param drugBAC the new drug bac
	 */
	public void setDrugBAC(IDrugBAC drugBAC)
	{
		this.drugBAC = drugBAC;
	}

	/**
	 * Gets the drug bac.
	 * 
	 * @return the drug bac
	 */
	public IDrugBAC getDrugBAC()
	{
		return drugBAC;
	}

	@Override
	public DrugResponse insertDrug(DrugMaintenanceRequest request)
	{
		DrugResponse response = new DrugResponse();
		try
		{
			response = (DrugResponse)process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request,
					response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public DrugResponse updateDrug(DrugMaintenanceRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			response = (DrugResponse)process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request,
					response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public DrugResponse deleteDrug(DrugMaintenanceRequest request)
	{
		DrugResponse response = new DrugResponse();
		try
		{
			response = (DrugResponse)process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request,
					response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public DrugResponse refreshDrugs(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DrugResponse response = new DrugResponse();
		try
		{
			getDrugBAC().refreshDrugs(request.getRefreshInt());
			// Call maintainReturnList to see if we need to return the drug list and if so whether it should be paged or
			// not
			DrugMaintenanceRequest requestRefresh = new DrugMaintenanceRequest();
			requestRefresh.setReturnList(request.getReturnList());
			requestRefresh.setReturnListPaged(request.getReturnListPaged());
			maintainReturnList(requestRefresh, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public DrugResponse fetchAllDrugs(FetchAllRequest request)
	{
		DrugResponse response = new DrugResponse();
		try
		{
			fetchAllNDCs(response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IDrugBAI#fetchDrugById(com.qat.samples.sysmgmt.model.request.DrugRequest)
	 */
	@Override
	public DrugResponse fetchDrugByCode(FetchByCodeRequest request)
	{
		DrugResponse response = new DrugResponse();
		try
		{

			InternalResultsResponse<Drug> internalResponse = new InternalResultsResponse<Drug>();
			// validate fetchCode field
			if (ValidationUtil.isNullOrEmpty(request.getFetchCode()))
			{
				internalResponse.addFieldErrorMessage(SYSMGMT_BASE_CODE_REQUIRED);
			}
			else
			{
				internalResponse = getDrugBAC().fetchDrugByCode(request.getFetchCode());
			}

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public DrugResponse fetchDrugsByRequest(PagedInquiryRequest request)
	{
		DrugResponse response = new DrugResponse();
		try
		{
			fetchPagedNDCs(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Do persistence. Called by the underlying abstract class when it needs to persist the object.
	 * 
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	@Override
	protected InternalResponse doPersistance(Request request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDrugBAC().insertDrug((Drug)getObjectToBeValidated(request));

			case UPDATE:
				return getDrugBAC().updateDrug((Drug)getObjectToBeValidated(request));

			case DELETE:
				return getDrugBAC().deleteDrug((Drug)getObjectToBeValidated(request));
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	@Override
	protected void maintainReturnList(Object requestObject, Object responseObject)
	{
		DrugMaintenanceRequest request = (DrugMaintenanceRequest)requestObject;
		DrugResponse response = (DrugResponse)responseObject;
		// Fetch again if requested.
		if (request.getReturnList())
		{
			// Fetch Paged is requested.
			if (request.getReturnListPaged())
			{
				PagedInquiryRequest requestPaged = new PagedInquiryRequest();
				requestPaged.setPreQueryCount(true);
				fetchPagedNDCs(requestPaged, response);
			}
			else
			{
				// otherwise return all rows not paged
				fetchAllNDCs(response);
			}
		}

	}

	@Override
	protected Object getObjectToBeValidated(Request request)
	{
		return ((DrugMaintenanceRequest)request).getDrug();
	}

	/**
	 * Fetch all ndcs.
	 * 
	 * @param response the response
	 */
	private void fetchAllNDCs(DrugResponse response)
	{
		InternalResultsResponse<Drug> internalResponse = getDrugBAC().fetchAllDrugs();
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Fetch paged ndcs.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPagedNDCs(PagedInquiryRequest request, DrugResponse response)
	{
		InternalResultsResponse<Drug> internalResponse = getDrugBAC().fetchDrugsByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}
}
