/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Dicionario.IDicionarioBAC;
import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.dicionario.Field;
import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.ClassesMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.response.ClassesResponse;
import com.qat.samples.sysmgmt.dicionario.response.FieldResponse;
import com.qat.samples.sysmgmt.dicionario.response.InterfaceResponse;
import com.qat.samples.sysmgmt.service.IDicionarioWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * DicionarioWS used to provide WS interface. Delegates all calls to the IDicionarioBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class DicionarioWSImpl implements IDicionarioWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.dicionariojmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.dicionariojmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = DicionarioWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DicionarioWSImpl.class);
	private IDicionarioBAC dicionarioBAC;
	/**
	 * @return the dicionarioBAC which is expected to provide the implementation.
	 */
	public IDicionarioBAC getDicionarioBAC()
	{
		return dicionarioBAC;
	}
	/**
	 * Spring injection uses this method to inject an implementation of {@link IDicionarioBAC}.
	 *
	 * @param dicionarioBAC the dicionarioBAC to set.
	 */
	public void setDicionarioBAC(IDicionarioBAC dicionarioBAC)
	{
		this.dicionarioBAC = dicionarioBAC;
	}


//===================================### CLASSES ####======================================

	/**
	 * Delegates call to {@link IClassesBAC}
	 *
	 * @param request a ClassesRequest
	 * @return ClassesResponse
	 */
	@Override
	public ClassesResponse insertClasses(ClassesMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClassesResponse response = new ClassesResponse();
		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().insertClasses(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IClassesBAC}
	 *
	 * @param request a ClassesRequest
	 * @return ClassesResponse
	 */
	@Override
	public ClassesResponse updateClasses(ClassesMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClassesResponse response = new ClassesResponse();
		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().updateClasses(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IClassesBAC}
	 *
	 * @param request a ClassesRequest
	 * @return ClassesResponse
	 */
	@Override
	public ClassesResponse deleteClasses(ClassesMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClassesResponse response = new ClassesResponse();
		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().deleteClasses(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IClassesBAC}
	 *
	 * @param request a ClassesRequest
	 * @return ClassesResponse
	 */
	@Override
	public ClassesResponse fetchClassesById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClassesResponse response = new ClassesResponse();
		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().fetchClassesById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IClassesBAC}
	 *
	 * @param request a ClassesRequest
	 * @return ClassesResponse
	 */
	@Override
	public ClassesResponse fetchClassessByRequest(ClassesInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClassesResponse response = new ClassesResponse();
		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().fetchClassessByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IClassesBAC}
	 *
	 * @param request a ClassesRequest
	 * @return ClassesResponse
	 */
	@Override
	public ClassesResponse refreshClassess(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClassesResponse response = new ClassesResponse();
		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().refreshClassess(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IClassesBAC}
	 *
	 * @param request a ClassesRequest
	 * @return ClassesResponse
	 */
	@Override
	public ClassesResponse fetchAllClassess(FetchAllRequest request)
	{
		ClassesResponse response = new ClassesResponse();
		try
		{
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().fetchAllClassess(new Classes());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### INTERFACE ####======================================

	/**
	 * Delegates call to {@link IInterfaceBAC}
	 *
	 * @param request a InterfaceRequest
	 * @return InterfaceResponse
	 */
	@Override
	public InterfaceResponse insertInterface(InterfaceMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InterfaceResponse response = new InterfaceResponse();
		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().insertInterface(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInterfaceBAC}
	 *
	 * @param request a InterfaceRequest
	 * @return InterfaceResponse
	 */
	@Override
	public InterfaceResponse updateInterface(InterfaceMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InterfaceResponse response = new InterfaceResponse();
		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().updateInterface(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInterfaceBAC}
	 *
	 * @param request a InterfaceRequest
	 * @return InterfaceResponse
	 */
	@Override
	public InterfaceResponse deleteInterface(InterfaceMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InterfaceResponse response = new InterfaceResponse();
		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().deleteInterface(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInterfaceBAC}
	 *
	 * @param request a InterfaceRequest
	 * @return InterfaceResponse
	 */
	@Override
	public InterfaceResponse fetchInterfaceById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InterfaceResponse response = new InterfaceResponse();
		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().fetchInterfaceById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IInterfaceBAC}
	 *
	 * @param request a InterfaceRequest
	 * @return InterfaceResponse
	 */
	@Override
	public InterfaceResponse fetchInterfacesByRequest(InterfaceInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InterfaceResponse response = new InterfaceResponse();
		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().fetchInterfacesByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IInterfaceBAC}
	 *
	 * @param request a InterfaceRequest
	 * @return InterfaceResponse
	 */
	@Override
	public InterfaceResponse refreshInterfaces(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		InterfaceResponse response = new InterfaceResponse();
		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().refreshInterfaces(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IInterfaceBAC}
	 *
	 * @param request a InterfaceRequest
	 * @return InterfaceResponse
	 */
	@Override
	public InterfaceResponse fetchAllInterfaces(FetchAllRequest request)
	{
		InterfaceResponse response = new InterfaceResponse();
		try
		{
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().fetchAllInterfaces(new Interface());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### FIELD ####======================================

	/**
	 * Delegates call to {@link IFieldBAC}
	 *
	 * @param request a FieldRequest
	 * @return FieldResponse
	 */
	@Override
	public FieldResponse insertField(FieldMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FieldResponse response = new FieldResponse();
		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().insertField(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IFieldBAC}
	 *
	 * @param request a FieldRequest
	 * @return FieldResponse
	 */
	@Override
	public FieldResponse updateField(FieldMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FieldResponse response = new FieldResponse();
		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().updateField(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IFieldBAC}
	 *
	 * @param request a FieldRequest
	 * @return FieldResponse
	 */
	@Override
	public FieldResponse deleteField(FieldMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FieldResponse response = new FieldResponse();
		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().deleteField(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IFieldBAC}
	 *
	 * @param request a FieldRequest
	 * @return FieldResponse
	 */
	@Override
	public FieldResponse fetchFieldById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FieldResponse response = new FieldResponse();
		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().fetchFieldById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Delegates call to {@link IFieldBAC}
	 *
	 * @param request a FieldRequest
	 * @return FieldResponse
	 */
	@Override
	public FieldResponse fetchFieldsByRequest(FieldInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FieldResponse response = new FieldResponse();
		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().fetchFieldsByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IFieldBAC}
	 *
	 * @param request a FieldRequest
	 * @return FieldResponse
	 */
	@Override
	public FieldResponse refreshFields(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FieldResponse response = new FieldResponse();
		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().refreshFields(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IFieldBAC}
	 *
	 * @param request a FieldRequest
	 * @return FieldResponse
	 */
	@Override
	public FieldResponse fetchAllFields(FetchAllRequest request)
	{
		FieldResponse response = new FieldResponse();
		try
		{
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().fetchAllFields(new Field());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
