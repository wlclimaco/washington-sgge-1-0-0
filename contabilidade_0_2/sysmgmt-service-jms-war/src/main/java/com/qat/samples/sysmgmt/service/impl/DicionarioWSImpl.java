/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IDicionarioBAC;
import com.qat.samples.sysmgmt.model.Dicionario;
import com.qat.samples.sysmgmt.model.response.DicionarioResponse;
import com.qat.samples.sysmgmt.service.IDicionarioWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
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

//===================================### CLASSES ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IClassesBAC}.
	 *
	 * @param classesBAC the classesBAC to set.
	 */
	public void setClassesBAC(IClassesBAC classesBAC)
	{
		this.classesBAC = classesBAC;
	}
	
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
			InternalResultsResponse<Classes> internalResponse = getClassesBAC().insertClasses(request);
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
			InternalResultsResponse<Classes> internalResponse = getClassesBAC().updateClasses(request);
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
			InternalResultsResponse<Classes> internalResponse = getClassesBAC().deleteClasses(request);
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
			InternalResultsResponse<Classes> internalResponse = getClassesBAC().fetchClassesById(request);
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
			InternalResultsResponse<Classes> internalResponse = getClassesBAC().fetchClassessByRequest(request);
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
			InternalResultsResponse<Classes> internalResponse = getClassesBAC().refreshClassess(request);
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
			InternalResultsResponse<Classes> internalResponse = getClassesBAC().fetchAllClassess();
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
	 * Spring injection uses this method to inject an implementation of {@link IInterfaceBAC}.
	 *
	 * @param interfaceBAC the interfaceBAC to set.
	 */
	public void setInterfaceBAC(IInterfaceBAC interfaceBAC)
	{
		this.interfaceBAC = interfaceBAC;
	}
	
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
			InternalResultsResponse<Interface> internalResponse = getInterfaceBAC().insertInterface(request);
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
			InternalResultsResponse<Interface> internalResponse = getInterfaceBAC().updateInterface(request);
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
			InternalResultsResponse<Interface> internalResponse = getInterfaceBAC().deleteInterface(request);
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
			InternalResultsResponse<Interface> internalResponse = getInterfaceBAC().fetchInterfaceById(request);
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
			InternalResultsResponse<Interface> internalResponse = getInterfaceBAC().fetchInterfacesByRequest(request);
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
			InternalResultsResponse<Interface> internalResponse = getInterfaceBAC().refreshInterfaces(request);
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
			InternalResultsResponse<Interface> internalResponse = getInterfaceBAC().fetchAllInterfaces();
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
	 * Spring injection uses this method to inject an implementation of {@link IFieldBAC}.
	 *
	 * @param fieldBAC the fieldBAC to set.
	 */
	public void setFieldBAC(IFieldBAC fieldBAC)
	{
		this.fieldBAC = fieldBAC;
	}
	
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
			InternalResultsResponse<Field> internalResponse = getFieldBAC().insertField(request);
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
			InternalResultsResponse<Field> internalResponse = getFieldBAC().updateField(request);
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
			InternalResultsResponse<Field> internalResponse = getFieldBAC().deleteField(request);
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
			InternalResultsResponse<Field> internalResponse = getFieldBAC().fetchFieldById(request);
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
			InternalResultsResponse<Field> internalResponse = getFieldBAC().fetchFieldsByRequest(request);
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
			InternalResultsResponse<Field> internalResponse = getFieldBAC().refreshFields(request);
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
			InternalResultsResponse<Field> internalResponse = getFieldBAC().fetchAllFields();
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
