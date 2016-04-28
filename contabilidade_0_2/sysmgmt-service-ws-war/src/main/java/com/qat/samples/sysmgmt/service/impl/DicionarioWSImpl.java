/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IDicionarioBAC;
import com.qat.samples.sysmgmt.model.Dicionario;
import com.qat.samples.sysmgmt.model.request.DicionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.DicionarioResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.DicionarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class DicionarioWSImpl implements com.qat.samples.sysmgmt.service.IDicionarioWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.dicionariowsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.dicionariowsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = DicionarioWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DicionarioWSImpl.class);

	/** The dicionario BAC. */
	private IDicionarioBAC dicionarioBAC; // injected by Spring through setter

	/**
	 * Spring Sets the dicionario BAC.
	 *
	 * @param dicionarioBAC the new dicionario BAC
	 */
	public void setDicionarioBAC(IDicionarioBAC dicionarioBAC)
	{
		this.dicionarioBAC = dicionarioBAC;
	}

	/**
	 * Gets the dicionario bac.
	 *
	 * @return the dicionario bac
	 */
	public IDicionarioBAC getDicionarioBAC()
	{
		return dicionarioBAC;
	}


//===================================### CLASSES ####======================================
	@Override
	public ClassesResponse insertClasses(ClassesMaintenanceRequest request)
	{
		ClassesResponse response = new ClassesResponse();

		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().insertClasses(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ClassesResponse updateClasses(ClassesMaintenanceRequest request)
	{
		ClassesResponse response = new ClassesResponse();

		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().updateClasses(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ClassesResponse deleteClasses(ClassesMaintenanceRequest request)
	{
		ClassesResponse response = new ClassesResponse();

		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().deleteClasses(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ClassesResponse refreshClassess(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClassesResponse response = new ClassesResponse();

		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().refreshClassess(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ClassesResponse fetchAllClassess(FetchAllRequest request)
	{
		ClassesResponse response = new ClassesResponse();

		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().fetchAllClassess(new Classes());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IClassesWS#fetchClassesById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ClassesResponse fetchClassesById(FetchByIdRequest request)
	{
		ClassesResponse response = new ClassesResponse();

		try
		{
			InternalResultsResponse<Classes> internalResponse = new InternalResultsResponse<Classes>();

			internalResponse = getDicionarioBAC().fetchClassesById(request);
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

	@Override
	public ClassesResponse fetchClassessByRequest(ClassesInquiryRequest request)
	{
		ClassesResponse response = new ClassesResponse();

		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().fetchClassessByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### INTERFACE ####======================================
	@Override
	public InterfaceResponse insertInterface(InterfaceMaintenanceRequest request)
	{
		InterfaceResponse response = new InterfaceResponse();

		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().insertInterface(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public InterfaceResponse updateInterface(InterfaceMaintenanceRequest request)
	{
		InterfaceResponse response = new InterfaceResponse();

		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().updateInterface(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public InterfaceResponse deleteInterface(InterfaceMaintenanceRequest request)
	{
		InterfaceResponse response = new InterfaceResponse();

		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().deleteInterface(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public InterfaceResponse refreshInterfaces(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InterfaceResponse response = new InterfaceResponse();

		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().refreshInterfaces(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public InterfaceResponse fetchAllInterfaces(FetchAllRequest request)
	{
		InterfaceResponse response = new InterfaceResponse();

		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().fetchAllInterfaces(new Interface());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IInterfaceWS#fetchInterfaceById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public InterfaceResponse fetchInterfaceById(FetchByIdRequest request)
	{
		InterfaceResponse response = new InterfaceResponse();

		try
		{
			InternalResultsResponse<Interface> internalResponse = new InternalResultsResponse<Interface>();

			internalResponse = getDicionarioBAC().fetchInterfaceById(request);
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

	@Override
	public InterfaceResponse fetchInterfacesByRequest(InterfaceInquiryRequest request)
	{
		InterfaceResponse response = new InterfaceResponse();

		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().fetchInterfacesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### FIELD ####======================================
	@Override
	public FieldResponse insertField(FieldMaintenanceRequest request)
	{
		FieldResponse response = new FieldResponse();

		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().insertField(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public FieldResponse updateField(FieldMaintenanceRequest request)
	{
		FieldResponse response = new FieldResponse();

		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().updateField(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public FieldResponse deleteField(FieldMaintenanceRequest request)
	{
		FieldResponse response = new FieldResponse();

		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().deleteField(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public FieldResponse refreshFields(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FieldResponse response = new FieldResponse();

		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().refreshFields(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public FieldResponse fetchAllFields(FetchAllRequest request)
	{
		FieldResponse response = new FieldResponse();

		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().fetchAllFields(new Field());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IFieldWS#fetchFieldById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public FieldResponse fetchFieldById(FetchByIdRequest request)
	{
		FieldResponse response = new FieldResponse();

		try
		{
			InternalResultsResponse<Field> internalResponse = new InternalResultsResponse<Field>();

			internalResponse = getDicionarioBAC().fetchFieldById(request);
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

	@Override
	public FieldResponse fetchFieldsByRequest(FieldInquiryRequest request)
	{
		FieldResponse response = new FieldResponse();

		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().fetchFieldsByRequest(request);
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
