/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IEmpresaBAC;
import com.qat.samples.sysmgmt.model.Empresa;
import com.qat.samples.sysmgmt.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class EmpresaWSImpl implements com.qat.samples.sysmgmt.service.IEmpresaWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.empresawsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.empresawsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = EmpresaWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaWSImpl.class);

	/** The empresa BAC. */
	private IEmpresaBAC empresaBAC; // injected by Spring through setter

	/**
	 * Spring Sets the empresa BAC.
	 *
	 * @param empresaBAC the new empresa BAC
	 */
	public void setEmpresaBAC(IEmpresaBAC empresaBAC)
	{
		this.empresaBAC = empresaBAC;
	}

	/**
	 * Gets the empresa bac.
	 *
	 * @return the empresa bac
	 */
	public IEmpresaBAC getEmpresaBAC()
	{
		return empresaBAC;
	}


//===================================### EMPRESA ####======================================
	@Override
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().insertEmpresa(request);
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
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().updateEmpresa(request);
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
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().deleteEmpresa(request);
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
	public EmpresaResponse refreshEmpresas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EmpresaResponse response = new EmpresaResponse();

		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().refreshEmpresas(request);
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
	public EmpresaResponse fetchAllEmpresas(FetchAllRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().fetchAllEmpresas(new Empresa());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IEmpresaWS#fetchEmpresaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{
			InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

			internalResponse = getEmpresaBAC().fetchEmpresaById(request);
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
	public EmpresaResponse fetchEmpresasByRequest(EmpresaInquiryRequest request)
	{
		EmpresaResponse response = new EmpresaResponse();

		try
		{
			InternalResultsResponse<Empresa> internalResponse = getEmpresaBAC().fetchEmpresasByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### FILIAL ####======================================
	@Override
	public FilialResponse insertFilial(FilialMaintenanceRequest request)
	{
		FilialResponse response = new FilialResponse();

		try
		{
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().insertFilial(request);
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
	public FilialResponse updateFilial(FilialMaintenanceRequest request)
	{
		FilialResponse response = new FilialResponse();

		try
		{
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().updateFilial(request);
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
	public FilialResponse deleteFilial(FilialMaintenanceRequest request)
	{
		FilialResponse response = new FilialResponse();

		try
		{
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().deleteFilial(request);
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
	public FilialResponse refreshFilials(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FilialResponse response = new FilialResponse();

		try
		{
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().refreshFilials(request);
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
	public FilialResponse fetchAllFilials(FetchAllRequest request)
	{
		FilialResponse response = new FilialResponse();

		try
		{
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().fetchAllFilials(new Filial());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IFilialWS#fetchFilialById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public FilialResponse fetchFilialById(FetchByIdRequest request)
	{
		FilialResponse response = new FilialResponse();

		try
		{
			InternalResultsResponse<Filial> internalResponse = new InternalResultsResponse<Filial>();

			internalResponse = getEmpresaBAC().fetchFilialById(request);
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
	public FilialResponse fetchFilialsByRequest(FilialInquiryRequest request)
	{
		FilialResponse response = new FilialResponse();

		try
		{
			InternalResultsResponse<Filial> internalResponse = getEmpresaBAC().fetchFilialsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### DEPOSITO ####======================================
	@Override
	public DepositoResponse insertDeposito(DepositoMaintenanceRequest request)
	{
		DepositoResponse response = new DepositoResponse();

		try
		{
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().insertDeposito(request);
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
	public DepositoResponse updateDeposito(DepositoMaintenanceRequest request)
	{
		DepositoResponse response = new DepositoResponse();

		try
		{
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().updateDeposito(request);
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
	public DepositoResponse deleteDeposito(DepositoMaintenanceRequest request)
	{
		DepositoResponse response = new DepositoResponse();

		try
		{
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().deleteDeposito(request);
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
	public DepositoResponse refreshDepositos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		DepositoResponse response = new DepositoResponse();

		try
		{
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().refreshDepositos(request);
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
	public DepositoResponse fetchAllDepositos(FetchAllRequest request)
	{
		DepositoResponse response = new DepositoResponse();

		try
		{
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().fetchAllDepositos(new Deposito());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IDepositoWS#fetchDepositoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public DepositoResponse fetchDepositoById(FetchByIdRequest request)
	{
		DepositoResponse response = new DepositoResponse();

		try
		{
			InternalResultsResponse<Deposito> internalResponse = new InternalResultsResponse<Deposito>();

			internalResponse = getEmpresaBAC().fetchDepositoById(request);
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
	public DepositoResponse fetchDepositosByRequest(DepositoInquiryRequest request)
	{
		DepositoResponse response = new DepositoResponse();

		try
		{
			InternalResultsResponse<Deposito> internalResponse = getEmpresaBAC().fetchDepositosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### USUARIO ####======================================
	@Override
	public UsuarioResponse insertUsuario(UsuarioMaintenanceRequest request)
	{
		UsuarioResponse response = new UsuarioResponse();

		try
		{
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().insertUsuario(request);
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
	public UsuarioResponse updateUsuario(UsuarioMaintenanceRequest request)
	{
		UsuarioResponse response = new UsuarioResponse();

		try
		{
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().updateUsuario(request);
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
	public UsuarioResponse deleteUsuario(UsuarioMaintenanceRequest request)
	{
		UsuarioResponse response = new UsuarioResponse();

		try
		{
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().deleteUsuario(request);
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
	public UsuarioResponse refreshUsuarios(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UsuarioResponse response = new UsuarioResponse();

		try
		{
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().refreshUsuarios(request);
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
	public UsuarioResponse fetchAllUsuarios(FetchAllRequest request)
	{
		UsuarioResponse response = new UsuarioResponse();

		try
		{
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().fetchAllUsuarios(new Usuario());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IUsuarioWS#fetchUsuarioById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public UsuarioResponse fetchUsuarioById(FetchByIdRequest request)
	{
		UsuarioResponse response = new UsuarioResponse();

		try
		{
			InternalResultsResponse<Usuario> internalResponse = new InternalResultsResponse<Usuario>();

			internalResponse = getEmpresaBAC().fetchUsuarioById(request);
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
	public UsuarioResponse fetchUsuariosByRequest(UsuarioInquiryRequest request)
	{
		UsuarioResponse response = new UsuarioResponse();

		try
		{
			InternalResultsResponse<Usuario> internalResponse = getEmpresaBAC().fetchUsuariosByRequest(request);
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
