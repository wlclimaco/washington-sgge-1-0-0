package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IDrugBAC;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.DrugResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByCodeRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class DrugWSImpl.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class DrugWSImpl implements com.qat.samples.sysmgmt.service.IDrugWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.service.drugwsimpl.defaultexception";

	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.service.drugwsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = DrugWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DrugWSImpl.class);

	/** The drug BAC. */
	private IDrugBAC drugBAC; // injected by Spring through setter

	/**
	 * Gets the drug BAC.
	 *
	 * @return the drug BAC
	 */
	public IDrugBAC getDrugBAC()
	{
		return drugBAC;
	}

	/**
	 * Sets the drug BAC.
	 *
	 * @param drugBAC the new drug BAC
	 */
	public void setDrugBAC(IDrugBAC drugBAC)
	{
		this.drugBAC = drugBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.IDrugWS#insertDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public DrugResponse insertDrug(DrugMaintenanceRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			InternalResultsResponse<Drug> internalResponse = getDrugBAC().insertDrug(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.IDrugWS#updateDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public DrugResponse updateDrug(DrugMaintenanceRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			InternalResultsResponse<Drug> internalResponse = getDrugBAC().updateDrug(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.IDrugWS#deleteDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public DrugResponse deleteDrug(DrugMaintenanceRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			InternalResultsResponse<Drug> internalResponse = getDrugBAC().deleteDrug(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.service.IDrugWS#refreshDrugs(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public DrugResponse refreshDrugs(RefreshRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			InternalResultsResponse<Drug> internalResponse = getDrugBAC().refreshDrugs(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.IDrugWS#fetchAllDrugs(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public DrugResponse fetchAllDrugs(FetchAllRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			InternalResultsResponse<Drug> internalResponse = getDrugBAC().fetchAllDrugs();
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.IDrugWS#fetchDrugByCode(com.qat.samples.sysmgmt.model.request.FetchByCodeRequest
	 * )
	 */
	@Override
	public DrugResponse fetchDrugByCode(FetchByCodeRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			InternalResultsResponse<Drug> internalResponse = new InternalResultsResponse<Drug>();

			internalResponse = getDrugBAC().fetchDrugByCode(request.getFetchCode());
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.service.IDrugWS#fetchDrugsByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest
	 * )
	 */
	@Override
	public DrugResponse fetchDrugsByRequest(PagedInquiryRequest request)
	{
		DrugResponse response = new DrugResponse();

		try
		{
			InternalResultsResponse<Drug> internalResponse = getDrugBAC().fetchDrugsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}
}
