/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IHistoricoBAC;
import com.qat.samples.sysmgmt.model.Historico;
import com.qat.samples.sysmgmt.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.service.IHistoricoWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * HistoricoWS used to provide WS interface. Delegates all calls to the IHistoricoBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class HistoricoWSImpl implements IHistoricoWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.historicojmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.historicojmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = HistoricoWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistoricoWSImpl.class);
	private IHistoricoBAC historicoBAC;
	/**
	 * @return the historicoBAC which is expected to provide the implementation.
	 */
	public IHistoricoBAC getHistoricoBAC()
	{	
		return historicoBAC;
	}

//===================================### HISTORICO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IHistoricoBAC}.
	 *
	 * @param historicoBAC the historicoBAC to set.
	 */
	public void setHistoricoBAC(IHistoricoBAC historicoBAC)
	{
		this.historicoBAC = historicoBAC;
	}
	
	/**
	 * Delegates call to {@link IHistoricoBAC}
	 *
	 * @param request a HistoricoRequest
	 * @return HistoricoResponse
	 */
	@Override
	public HistoricoResponse insertHistorico(HistoricoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoResponse response = new HistoricoResponse();
		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().insertHistorico(request);
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
	 * Delegates call to {@link IHistoricoBAC}
	 *
	 * @param request a HistoricoRequest
	 * @return HistoricoResponse
	 */
	@Override
	public HistoricoResponse updateHistorico(HistoricoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoResponse response = new HistoricoResponse();
		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().updateHistorico(request);
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
	 * Delegates call to {@link IHistoricoBAC}
	 *
	 * @param request a HistoricoRequest
	 * @return HistoricoResponse
	 */
	@Override
	public HistoricoResponse deleteHistorico(HistoricoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoResponse response = new HistoricoResponse();
		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().deleteHistorico(request);
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
	 * Delegates call to {@link IHistoricoBAC}
	 *
	 * @param request a HistoricoRequest
	 * @return HistoricoResponse
	 */
	@Override
	public HistoricoResponse fetchHistoricoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoResponse response = new HistoricoResponse();
		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchHistoricoById(request);
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
	 * Delegates call to {@link IHistoricoBAC}
	 *
	 * @param request a HistoricoRequest
	 * @return HistoricoResponse
	 */
	@Override
	public HistoricoResponse fetchHistoricosByRequest(HistoricoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoResponse response = new HistoricoResponse();
		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchHistoricosByRequest(request);
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
	 * Delegates call to {@link IHistoricoBAC}
	 *
	 * @param request a HistoricoRequest
	 * @return HistoricoResponse
	 */
	@Override
	public HistoricoResponse refreshHistoricos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoResponse response = new HistoricoResponse();
		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().refreshHistoricos(request);
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
	 * Delegates call to {@link IHistoricoBAC}
	 *
	 * @param request a HistoricoRequest
	 * @return HistoricoResponse
	 */
	@Override
	public HistoricoResponse fetchAllHistoricos(FetchAllRequest request)
	{
		HistoricoResponse response = new HistoricoResponse();
		try
		{
			InternalResultsResponse<Historico> internalResponse = getHistoricoBAC().fetchAllHistoricos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### HISTORICOITENS ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IHistoricoItensBAC}.
	 *
	 * @param historicoitensBAC the historicoitensBAC to set.
	 */
	public void setHistoricoItensBAC(IHistoricoItensBAC historicoitensBAC)
	{
		this.historicoitensBAC = historicoitensBAC;
	}
	
	/**
	 * Delegates call to {@link IHistoricoItensBAC}
	 *
	 * @param request a HistoricoItensRequest
	 * @return HistoricoItensResponse
	 */
	@Override
	public HistoricoItensResponse insertHistoricoItens(HistoricoItensMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoItensResponse response = new HistoricoItensResponse();
		try
		{
			InternalResultsResponse<HistoricoItens> internalResponse = getHistoricoItensBAC().insertHistoricoItens(request);
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
	 * Delegates call to {@link IHistoricoItensBAC}
	 *
	 * @param request a HistoricoItensRequest
	 * @return HistoricoItensResponse
	 */
	@Override
	public HistoricoItensResponse updateHistoricoItens(HistoricoItensMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoItensResponse response = new HistoricoItensResponse();
		try
		{
			InternalResultsResponse<HistoricoItens> internalResponse = getHistoricoItensBAC().updateHistoricoItens(request);
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
	 * Delegates call to {@link IHistoricoItensBAC}
	 *
	 * @param request a HistoricoItensRequest
	 * @return HistoricoItensResponse
	 */
	@Override
	public HistoricoItensResponse deleteHistoricoItens(HistoricoItensMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoItensResponse response = new HistoricoItensResponse();
		try
		{
			InternalResultsResponse<HistoricoItens> internalResponse = getHistoricoItensBAC().deleteHistoricoItens(request);
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
	 * Delegates call to {@link IHistoricoItensBAC}
	 *
	 * @param request a HistoricoItensRequest
	 * @return HistoricoItensResponse
	 */
	@Override
	public HistoricoItensResponse fetchHistoricoItensById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoItensResponse response = new HistoricoItensResponse();
		try
		{
			InternalResultsResponse<HistoricoItens> internalResponse = getHistoricoItensBAC().fetchHistoricoItensById(request);
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
	 * Delegates call to {@link IHistoricoItensBAC}
	 *
	 * @param request a HistoricoItensRequest
	 * @return HistoricoItensResponse
	 */
	@Override
	public HistoricoItensResponse fetchHistoricoItenssByRequest(HistoricoItensInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoItensResponse response = new HistoricoItensResponse();
		try
		{
			InternalResultsResponse<HistoricoItens> internalResponse = getHistoricoItensBAC().fetchHistoricoItenssByRequest(request);
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
	 * Delegates call to {@link IHistoricoItensBAC}
	 *
	 * @param request a HistoricoItensRequest
	 * @return HistoricoItensResponse
	 */
	@Override
	public HistoricoItensResponse refreshHistoricoItenss(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		HistoricoItensResponse response = new HistoricoItensResponse();
		try
		{
			InternalResultsResponse<HistoricoItens> internalResponse = getHistoricoItensBAC().refreshHistoricoItenss(request);
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
	 * Delegates call to {@link IHistoricoItensBAC}
	 *
	 * @param request a HistoricoItensRequest
	 * @return HistoricoItensResponse
	 */
	@Override
	public HistoricoItensResponse fetchAllHistoricoItenss(FetchAllRequest request)
	{
		HistoricoItensResponse response = new HistoricoItensResponse();
		try
		{
			InternalResultsResponse<HistoricoItens> internalResponse = getHistoricoItensBAC().fetchAllHistoricoItenss();
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
