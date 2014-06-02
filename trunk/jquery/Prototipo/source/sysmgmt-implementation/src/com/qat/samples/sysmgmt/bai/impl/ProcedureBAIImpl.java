package com.qat.samples.sysmgmt.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.bac.IProcedureBAC;
import com.qat.samples.sysmgmt.bai.IProcedureBAI;
import com.qat.samples.sysmgmt.baid.ProcedureBAID;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;

/**
 * The Class ProcedureBAIImpl.
 */
public class ProcedureBAIImpl implements IProcedureBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.procedurebaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ProcedureBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The procedure bac. */
	private IProcedureBAC procedureBAC;

	/**
	 * Gets the validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation controller.
	 * 
	 * @param countyValidationController the new validation controller
	 */
	public void setValidationController(ValidationController countyValidationController)
	{
		validationController = countyValidationController;
	}

	/**
	 * Sets the procedure bac.
	 * 
	 * @param procedureBAC the new procedure bac
	 */
	public void setProcedureBAC(IProcedureBAC procedureBAC)
	{
		this.procedureBAC = procedureBAC;
	}

	/**
	 * Gets the procedure bac.
	 * 
	 * @return the procedure bac
	 */
	public IProcedureBAC getProcedureBAC()
	{
		return procedureBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IProcedureBAI#insertProcedure(com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest)
	 */
	@Override
	public ProcedureResponse insertProcedure(ProcedureMaintenanceRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			ProcedureBAID.maintainProcedure(getProcedureBAC(), ValidationContextIndicator.INSERT, getValidationController(), PersistanceActionEnum.INSERT, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IProcedureBAI#updateProcedure(com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest)
	 */
	@Override
	public ProcedureResponse updateProcedure(ProcedureMaintenanceRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			ProcedureBAID.maintainProcedure(getProcedureBAC(), ValidationContextIndicator.UPDATE, getValidationController(), PersistanceActionEnum.UPDATE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IProcedureBAI#deleteProcedure(com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest)
	 */
	@Override
	public ProcedureResponse deleteProcedure(ProcedureMaintenanceRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			ProcedureBAID.maintainProcedure(getProcedureBAC(), ValidationContextIndicator.DELETE, getValidationController(), PersistanceActionEnum.DELETE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IProcedureBAI#refreshProcedures(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public ProcedureResponse refreshProcedures(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			ProcedureBAID.refreshProcedures(getProcedureBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IProcedureBAI#fetchAllProcedures(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public ProcedureResponse fetchAllProcedures(FetchAllRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			ProcedureBAID.fetchAllProcedures(getProcedureBAC(), response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bai.IProcedureBAI#fetchProcedureById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ProcedureResponse fetchProcedureById(FetchByIdRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			ProcedureBAID.fetchProcedureById(getProcedureBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ProcedureResponse fetchProceduresByRequest(PagedInquiryRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			ProcedureBAID.fetchProceduresPaged(getProcedureBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
