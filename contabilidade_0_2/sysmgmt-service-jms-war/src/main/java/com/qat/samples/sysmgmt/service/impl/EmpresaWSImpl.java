/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IEmpresaBAC;
import com.qat.samples.sysmgmt.model.Empresa;
import com.qat.samples.sysmgmt.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.service.IEmpresaWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * EmpresaWS used to provide WS interface. Delegates all calls to the IEmpresaBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class EmpresaWSImpl implements IEmpresaWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.empresajmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.empresajmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = EmpresaWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaWSImpl.class);
	private IEmpresaBAC empresaBAC;
	/**
	 * @return the empresaBAC which is expected to provide the implementation.
	 */
	public IEmpresaBAC getEmpresaBAC()
	{	
		return empresaBAC;
	}

//===================================### EMPRESA ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IEmpresaBAC}.
	 *
	 * @param empresaBAC the empresaBAC to set.
	 */
	public void setEmpresaBAC(IEmpresaBAC empresaBAC)
	{
		this.empresaBAC = empresaBAC;
	}
	
	/**
	 * Delegates call to {@link IEmpresaBAC}
	 *
	 * @param request a EmpresaRequest
	 * @return EmpresaResponse
	 */
	@Override
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().insertEmpresa(request);
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
	 * Delegates call to {@link IEmpresaBAC}
	 *
	 * @param request a EmpresaRequest
	 * @return EmpresaResponse
	 */
	@Override
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().updateEmpresa(request);
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
	 * Delegates call to {@link IEmpresaBAC}
	 *
	 * @param request a EmpresaRequest
	 * @return EmpresaResponse
	 */
	@Override
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().deleteEmpresa(request);
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
	 * Delegates call to {@link IEmpresaBAC}
	 *
	 * @param request a EmpresaRequest
	 * @return EmpresaResponse
	 */
	@Override
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().fetchEmpresaById(request);
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
	 * Delegates call to {@link IEmpresaBAC}
	 *
	 * @param request a EmpresaRequest
	 * @return EmpresaResponse
	 */
	@Override
	public EmpresaResponse fetchEmpresasByRequest(EmpresaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().fetchEmpresasByRequest(request);
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
	 * Delegates call to {@link IEmpresaBAC}
	 *
	 * @param request a EmpresaRequest
	 * @return EmpresaResponse
	 */
	@Override
	public EmpresaResponse refreshEmpresas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().refreshEmpresas(request);
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
	 * Delegates call to {@link IEmpresaBAC}
	 *
	 * @param request a EmpresaRequest
	 * @return EmpresaResponse
	 */
	@Override
	public EmpresaResponse fetchAllEmpresas(FetchAllRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();
		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().fetchAllEmpresas();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### FILIAL ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IFilialBAC}.
	 *
	 * @param filialBAC the filialBAC to set.
	 */
	public void setFilialBAC(IFilialBAC filialBAC)
	{
		this.filialBAC = filialBAC;
	}
	
	/**
	 * Delegates call to {@link IFilialBAC}
	 *
	 * @param request a FilialRequest
	 * @return FilialResponse
	 */
	@Override
	public FilialResponse insertFilial(FilialMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FilialResponse response = new FilialResponse();
		try
		{
			InternalResultsResponse<Filial> internalResponse = getFilialBAC().insertFilial(request);
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
	 * Delegates call to {@link IFilialBAC}
	 *
	 * @param request a FilialRequest
	 * @return FilialResponse
	 */
	@Override
	public FilialResponse updateFilial(FilialMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FilialResponse response = new FilialResponse();
		try
		{
			InternalResultsResponse<Filial> internalResponse = getFilialBAC().updateFilial(request);
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
	 * Delegates call to {@link IFilialBAC}
	 *
	 * @param request a FilialRequest
	 * @return FilialResponse
	 */
	@Override
	public FilialResponse deleteFilial(FilialMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FilialResponse response = new FilialResponse();
		try
		{
			InternalResultsResponse<Filial> internalResponse = getFilialBAC().deleteFilial(request);
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
	 * Delegates call to {@link IFilialBAC}
	 *
	 * @param request a FilialRequest
	 * @return FilialResponse
	 */
	@Override
	public FilialResponse fetchFilialById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FilialResponse response = new FilialResponse();
		try
		{
			InternalResultsResponse<Filial> internalResponse = getFilialBAC().fetchFilialById(request);
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
	 * Delegates call to {@link IFilialBAC}
	 *
	 * @param request a FilialRequest
	 * @return FilialResponse
	 */
	@Override
	public FilialResponse fetchFilialsByRequest(FilialInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FilialResponse response = new FilialResponse();
		try
		{
			InternalResultsResponse<Filial> internalResponse = getFilialBAC().fetchFilialsByRequest(request);
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
	 * Delegates call to {@link IFilialBAC}
	 *
	 * @param request a FilialRequest
	 * @return FilialResponse
	 */
	@Override
	public FilialResponse refreshFilials(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FilialResponse response = new FilialResponse();
		try
		{
			InternalResultsResponse<Filial> internalResponse = getFilialBAC().refreshFilials(request);
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
	 * Delegates call to {@link IFilialBAC}
	 *
	 * @param request a FilialRequest
	 * @return FilialResponse
	 */
	@Override
	public FilialResponse fetchAllFilials(FetchAllRequest request)
	{
		FilialResponse response = new FilialResponse();
		try
		{
			InternalResultsResponse<Filial> internalResponse = getFilialBAC().fetchAllFilials();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### DEPOSITO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IDepositoBAC}.
	 *
	 * @param depositoBAC the depositoBAC to set.
	 */
	public void setDepositoBAC(IDepositoBAC depositoBAC)
	{
		this.depositoBAC = depositoBAC;
	}
	
	/**
	 * Delegates call to {@link IDepositoBAC}
	 *
	 * @param request a DepositoRequest
	 * @return DepositoResponse
	 */
	@Override
	public DepositoResponse insertDeposito(DepositoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DepositoResponse response = new DepositoResponse();
		try
		{
			InternalResultsResponse<Deposito> internalResponse = getDepositoBAC().insertDeposito(request);
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
	 * Delegates call to {@link IDepositoBAC}
	 *
	 * @param request a DepositoRequest
	 * @return DepositoResponse
	 */
	@Override
	public DepositoResponse updateDeposito(DepositoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DepositoResponse response = new DepositoResponse();
		try
		{
			InternalResultsResponse<Deposito> internalResponse = getDepositoBAC().updateDeposito(request);
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
	 * Delegates call to {@link IDepositoBAC}
	 *
	 * @param request a DepositoRequest
	 * @return DepositoResponse
	 */
	@Override
	public DepositoResponse deleteDeposito(DepositoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DepositoResponse response = new DepositoResponse();
		try
		{
			InternalResultsResponse<Deposito> internalResponse = getDepositoBAC().deleteDeposito(request);
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
	 * Delegates call to {@link IDepositoBAC}
	 *
	 * @param request a DepositoRequest
	 * @return DepositoResponse
	 */
	@Override
	public DepositoResponse fetchDepositoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DepositoResponse response = new DepositoResponse();
		try
		{
			InternalResultsResponse<Deposito> internalResponse = getDepositoBAC().fetchDepositoById(request);
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
	 * Delegates call to {@link IDepositoBAC}
	 *
	 * @param request a DepositoRequest
	 * @return DepositoResponse
	 */
	@Override
	public DepositoResponse fetchDepositosByRequest(DepositoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DepositoResponse response = new DepositoResponse();
		try
		{
			InternalResultsResponse<Deposito> internalResponse = getDepositoBAC().fetchDepositosByRequest(request);
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
	 * Delegates call to {@link IDepositoBAC}
	 *
	 * @param request a DepositoRequest
	 * @return DepositoResponse
	 */
	@Override
	public DepositoResponse refreshDepositos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DepositoResponse response = new DepositoResponse();
		try
		{
			InternalResultsResponse<Deposito> internalResponse = getDepositoBAC().refreshDepositos(request);
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
	 * Delegates call to {@link IDepositoBAC}
	 *
	 * @param request a DepositoRequest
	 * @return DepositoResponse
	 */
	@Override
	public DepositoResponse fetchAllDepositos(FetchAllRequest request)
	{
		DepositoResponse response = new DepositoResponse();
		try
		{
			InternalResultsResponse<Deposito> internalResponse = getDepositoBAC().fetchAllDepositos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### USUARIO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IUsuarioBAC}.
	 *
	 * @param usuarioBAC the usuarioBAC to set.
	 */
	public void setUsuarioBAC(IUsuarioBAC usuarioBAC)
	{
		this.usuarioBAC = usuarioBAC;
	}
	
	/**
	 * Delegates call to {@link IUsuarioBAC}
	 *
	 * @param request a UsuarioRequest
	 * @return UsuarioResponse
	 */
	@Override
	public UsuarioResponse insertUsuario(UsuarioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UsuarioResponse response = new UsuarioResponse();
		try
		{
			InternalResultsResponse<Usuario> internalResponse = getUsuarioBAC().insertUsuario(request);
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
	 * Delegates call to {@link IUsuarioBAC}
	 *
	 * @param request a UsuarioRequest
	 * @return UsuarioResponse
	 */
	@Override
	public UsuarioResponse updateUsuario(UsuarioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UsuarioResponse response = new UsuarioResponse();
		try
		{
			InternalResultsResponse<Usuario> internalResponse = getUsuarioBAC().updateUsuario(request);
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
	 * Delegates call to {@link IUsuarioBAC}
	 *
	 * @param request a UsuarioRequest
	 * @return UsuarioResponse
	 */
	@Override
	public UsuarioResponse deleteUsuario(UsuarioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UsuarioResponse response = new UsuarioResponse();
		try
		{
			InternalResultsResponse<Usuario> internalResponse = getUsuarioBAC().deleteUsuario(request);
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
	 * Delegates call to {@link IUsuarioBAC}
	 *
	 * @param request a UsuarioRequest
	 * @return UsuarioResponse
	 */
	@Override
	public UsuarioResponse fetchUsuarioById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UsuarioResponse response = new UsuarioResponse();
		try
		{
			InternalResultsResponse<Usuario> internalResponse = getUsuarioBAC().fetchUsuarioById(request);
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
	 * Delegates call to {@link IUsuarioBAC}
	 *
	 * @param request a UsuarioRequest
	 * @return UsuarioResponse
	 */
	@Override
	public UsuarioResponse fetchUsuariosByRequest(UsuarioInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UsuarioResponse response = new UsuarioResponse();
		try
		{
			InternalResultsResponse<Usuario> internalResponse = getUsuarioBAC().fetchUsuariosByRequest(request);
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
	 * Delegates call to {@link IUsuarioBAC}
	 *
	 * @param request a UsuarioRequest
	 * @return UsuarioResponse
	 */
	@Override
	public UsuarioResponse refreshUsuarios(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UsuarioResponse response = new UsuarioResponse();
		try
		{
			InternalResultsResponse<Usuario> internalResponse = getUsuarioBAC().refreshUsuarios(request);
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
	 * Delegates call to {@link IUsuarioBAC}
	 *
	 * @param request a UsuarioRequest
	 * @return UsuarioResponse
	 */
	@Override
	public UsuarioResponse fetchAllUsuarios(FetchAllRequest request)
	{
		UsuarioResponse response = new UsuarioResponse();
		try
		{
			InternalResultsResponse<Usuario> internalResponse = getUsuarioBAC().fetchAllUsuarios();
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
