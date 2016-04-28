/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IFiscalBAC;
import com.qat.samples.sysmgmt.model.Fiscal;
import com.qat.samples.sysmgmt.model.response.FiscalResponse;
import com.qat.samples.sysmgmt.service.IFiscalWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * FiscalWS used to provide WS interface. Delegates all calls to the IFiscalBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class FiscalWSImpl implements IFiscalWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.fiscaljmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.fiscaljmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = FiscalWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FiscalWSImpl.class);
	private IFiscalBAC fiscalBAC;
	/**
	 * @return the fiscalBAC which is expected to provide the implementation.
	 */
	public IFiscalBAC getFiscalBAC()
	{	
		return fiscalBAC;
	}

//===================================### REGIME ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IRegimeBAC}.
	 *
	 * @param regimeBAC the regimeBAC to set.
	 */
	public void setRegimeBAC(IRegimeBAC regimeBAC)
	{
		this.regimeBAC = regimeBAC;
	}
	
	/**
	 * Delegates call to {@link IRegimeBAC}
	 *
	 * @param request a RegimeRequest
	 * @return RegimeResponse
	 */
	@Override
	public RegimeResponse insertRegime(RegimeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		RegimeResponse response = new RegimeResponse();
		try
		{
			InternalResultsResponse<Regime> internalResponse = getRegimeBAC().insertRegime(request);
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
	 * Delegates call to {@link IRegimeBAC}
	 *
	 * @param request a RegimeRequest
	 * @return RegimeResponse
	 */
	@Override
	public RegimeResponse updateRegime(RegimeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		RegimeResponse response = new RegimeResponse();
		try
		{
			InternalResultsResponse<Regime> internalResponse = getRegimeBAC().updateRegime(request);
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
	 * Delegates call to {@link IRegimeBAC}
	 *
	 * @param request a RegimeRequest
	 * @return RegimeResponse
	 */
	@Override
	public RegimeResponse deleteRegime(RegimeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		RegimeResponse response = new RegimeResponse();
		try
		{
			InternalResultsResponse<Regime> internalResponse = getRegimeBAC().deleteRegime(request);
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
	 * Delegates call to {@link IRegimeBAC}
	 *
	 * @param request a RegimeRequest
	 * @return RegimeResponse
	 */
	@Override
	public RegimeResponse fetchRegimeById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		RegimeResponse response = new RegimeResponse();
		try
		{
			InternalResultsResponse<Regime> internalResponse = getRegimeBAC().fetchRegimeById(request);
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
	 * Delegates call to {@link IRegimeBAC}
	 *
	 * @param request a RegimeRequest
	 * @return RegimeResponse
	 */
	@Override
	public RegimeResponse fetchRegimesByRequest(RegimeInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		RegimeResponse response = new RegimeResponse();
		try
		{
			InternalResultsResponse<Regime> internalResponse = getRegimeBAC().fetchRegimesByRequest(request);
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
	 * Delegates call to {@link IRegimeBAC}
	 *
	 * @param request a RegimeRequest
	 * @return RegimeResponse
	 */
	@Override
	public RegimeResponse refreshRegimes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		RegimeResponse response = new RegimeResponse();
		try
		{
			InternalResultsResponse<Regime> internalResponse = getRegimeBAC().refreshRegimes(request);
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
	 * Delegates call to {@link IRegimeBAC}
	 *
	 * @param request a RegimeRequest
	 * @return RegimeResponse
	 */
	@Override
	public RegimeResponse fetchAllRegimes(FetchAllRequest request)
	{
		RegimeResponse response = new RegimeResponse();
		try
		{
			InternalResultsResponse<Regime> internalResponse = getRegimeBAC().fetchAllRegimes();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CFOP ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link ICfopBAC}.
	 *
	 * @param cfopBAC the cfopBAC to set.
	 */
	public void setCfopBAC(ICfopBAC cfopBAC)
	{
		this.cfopBAC = cfopBAC;
	}
	
	/**
	 * Delegates call to {@link ICfopBAC}
	 *
	 * @param request a CfopRequest
	 * @return CfopResponse
	 */
	@Override
	public CfopResponse insertCfop(CfopMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CfopResponse response = new CfopResponse();
		try
		{
			InternalResultsResponse<Cfop> internalResponse = getCfopBAC().insertCfop(request);
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
	 * Delegates call to {@link ICfopBAC}
	 *
	 * @param request a CfopRequest
	 * @return CfopResponse
	 */
	@Override
	public CfopResponse updateCfop(CfopMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CfopResponse response = new CfopResponse();
		try
		{
			InternalResultsResponse<Cfop> internalResponse = getCfopBAC().updateCfop(request);
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
	 * Delegates call to {@link ICfopBAC}
	 *
	 * @param request a CfopRequest
	 * @return CfopResponse
	 */
	@Override
	public CfopResponse deleteCfop(CfopMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CfopResponse response = new CfopResponse();
		try
		{
			InternalResultsResponse<Cfop> internalResponse = getCfopBAC().deleteCfop(request);
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
	 * Delegates call to {@link ICfopBAC}
	 *
	 * @param request a CfopRequest
	 * @return CfopResponse
	 */
	@Override
	public CfopResponse fetchCfopById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CfopResponse response = new CfopResponse();
		try
		{
			InternalResultsResponse<Cfop> internalResponse = getCfopBAC().fetchCfopById(request);
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
	 * Delegates call to {@link ICfopBAC}
	 *
	 * @param request a CfopRequest
	 * @return CfopResponse
	 */
	@Override
	public CfopResponse fetchCfopsByRequest(CfopInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CfopResponse response = new CfopResponse();
		try
		{
			InternalResultsResponse<Cfop> internalResponse = getCfopBAC().fetchCfopsByRequest(request);
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
	 * Delegates call to {@link ICfopBAC}
	 *
	 * @param request a CfopRequest
	 * @return CfopResponse
	 */
	@Override
	public CfopResponse refreshCfops(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CfopResponse response = new CfopResponse();
		try
		{
			InternalResultsResponse<Cfop> internalResponse = getCfopBAC().refreshCfops(request);
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
	 * Delegates call to {@link ICfopBAC}
	 *
	 * @param request a CfopRequest
	 * @return CfopResponse
	 */
	@Override
	public CfopResponse fetchAllCfops(FetchAllRequest request)
	{
		CfopResponse response = new CfopResponse();
		try
		{
			InternalResultsResponse<Cfop> internalResponse = getCfopBAC().fetchAllCfops();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CNAE ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link ICnaeBAC}.
	 *
	 * @param cnaeBAC the cnaeBAC to set.
	 */
	public void setCnaeBAC(ICnaeBAC cnaeBAC)
	{
		this.cnaeBAC = cnaeBAC;
	}
	
	/**
	 * Delegates call to {@link ICnaeBAC}
	 *
	 * @param request a CnaeRequest
	 * @return CnaeResponse
	 */
	@Override
	public CnaeResponse insertCnae(CnaeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeResponse response = new CnaeResponse();
		try
		{
			InternalResultsResponse<Cnae> internalResponse = getCnaeBAC().insertCnae(request);
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
	 * Delegates call to {@link ICnaeBAC}
	 *
	 * @param request a CnaeRequest
	 * @return CnaeResponse
	 */
	@Override
	public CnaeResponse updateCnae(CnaeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeResponse response = new CnaeResponse();
		try
		{
			InternalResultsResponse<Cnae> internalResponse = getCnaeBAC().updateCnae(request);
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
	 * Delegates call to {@link ICnaeBAC}
	 *
	 * @param request a CnaeRequest
	 * @return CnaeResponse
	 */
	@Override
	public CnaeResponse deleteCnae(CnaeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeResponse response = new CnaeResponse();
		try
		{
			InternalResultsResponse<Cnae> internalResponse = getCnaeBAC().deleteCnae(request);
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
	 * Delegates call to {@link ICnaeBAC}
	 *
	 * @param request a CnaeRequest
	 * @return CnaeResponse
	 */
	@Override
	public CnaeResponse fetchCnaeById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeResponse response = new CnaeResponse();
		try
		{
			InternalResultsResponse<Cnae> internalResponse = getCnaeBAC().fetchCnaeById(request);
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
	 * Delegates call to {@link ICnaeBAC}
	 *
	 * @param request a CnaeRequest
	 * @return CnaeResponse
	 */
	@Override
	public CnaeResponse fetchCnaesByRequest(CnaeInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeResponse response = new CnaeResponse();
		try
		{
			InternalResultsResponse<Cnae> internalResponse = getCnaeBAC().fetchCnaesByRequest(request);
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
	 * Delegates call to {@link ICnaeBAC}
	 *
	 * @param request a CnaeRequest
	 * @return CnaeResponse
	 */
	@Override
	public CnaeResponse refreshCnaes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeResponse response = new CnaeResponse();
		try
		{
			InternalResultsResponse<Cnae> internalResponse = getCnaeBAC().refreshCnaes(request);
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
	 * Delegates call to {@link ICnaeBAC}
	 *
	 * @param request a CnaeRequest
	 * @return CnaeResponse
	 */
	@Override
	public CnaeResponse fetchAllCnaes(FetchAllRequest request)
	{
		CnaeResponse response = new CnaeResponse();
		try
		{
			InternalResultsResponse<Cnae> internalResponse = getCnaeBAC().fetchAllCnaes();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CNAEEMPRESA ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link ICnaeEmpresaBAC}.
	 *
	 * @param cnaeempresaBAC the cnaeempresaBAC to set.
	 */
	public void setCnaeEmpresaBAC(ICnaeEmpresaBAC cnaeempresaBAC)
	{
		this.cnaeempresaBAC = cnaeempresaBAC;
	}
	
	/**
	 * Delegates call to {@link ICnaeEmpresaBAC}
	 *
	 * @param request a CnaeEmpresaRequest
	 * @return CnaeEmpresaResponse
	 */
	@Override
	public CnaeEmpresaResponse insertCnaeEmpresa(CnaeEmpresaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeEmpresaResponse response = new CnaeEmpresaResponse();
		try
		{
			InternalResultsResponse<CnaeEmpresa> internalResponse = getCnaeEmpresaBAC().insertCnaeEmpresa(request);
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
	 * Delegates call to {@link ICnaeEmpresaBAC}
	 *
	 * @param request a CnaeEmpresaRequest
	 * @return CnaeEmpresaResponse
	 */
	@Override
	public CnaeEmpresaResponse updateCnaeEmpresa(CnaeEmpresaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeEmpresaResponse response = new CnaeEmpresaResponse();
		try
		{
			InternalResultsResponse<CnaeEmpresa> internalResponse = getCnaeEmpresaBAC().updateCnaeEmpresa(request);
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
	 * Delegates call to {@link ICnaeEmpresaBAC}
	 *
	 * @param request a CnaeEmpresaRequest
	 * @return CnaeEmpresaResponse
	 */
	@Override
	public CnaeEmpresaResponse deleteCnaeEmpresa(CnaeEmpresaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeEmpresaResponse response = new CnaeEmpresaResponse();
		try
		{
			InternalResultsResponse<CnaeEmpresa> internalResponse = getCnaeEmpresaBAC().deleteCnaeEmpresa(request);
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
	 * Delegates call to {@link ICnaeEmpresaBAC}
	 *
	 * @param request a CnaeEmpresaRequest
	 * @return CnaeEmpresaResponse
	 */
	@Override
	public CnaeEmpresaResponse fetchCnaeEmpresaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeEmpresaResponse response = new CnaeEmpresaResponse();
		try
		{
			InternalResultsResponse<CnaeEmpresa> internalResponse = getCnaeEmpresaBAC().fetchCnaeEmpresaById(request);
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
	 * Delegates call to {@link ICnaeEmpresaBAC}
	 *
	 * @param request a CnaeEmpresaRequest
	 * @return CnaeEmpresaResponse
	 */
	@Override
	public CnaeEmpresaResponse fetchCnaeEmpresasByRequest(CnaeEmpresaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeEmpresaResponse response = new CnaeEmpresaResponse();
		try
		{
			InternalResultsResponse<CnaeEmpresa> internalResponse = getCnaeEmpresaBAC().fetchCnaeEmpresasByRequest(request);
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
	 * Delegates call to {@link ICnaeEmpresaBAC}
	 *
	 * @param request a CnaeEmpresaRequest
	 * @return CnaeEmpresaResponse
	 */
	@Override
	public CnaeEmpresaResponse refreshCnaeEmpresas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeEmpresaResponse response = new CnaeEmpresaResponse();
		try
		{
			InternalResultsResponse<CnaeEmpresa> internalResponse = getCnaeEmpresaBAC().refreshCnaeEmpresas(request);
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
	 * Delegates call to {@link ICnaeEmpresaBAC}
	 *
	 * @param request a CnaeEmpresaRequest
	 * @return CnaeEmpresaResponse
	 */
	@Override
	public CnaeEmpresaResponse fetchAllCnaeEmpresas(FetchAllRequest request)
	{
		CnaeEmpresaResponse response = new CnaeEmpresaResponse();
		try
		{
			InternalResultsResponse<CnaeEmpresa> internalResponse = getCnaeEmpresaBAC().fetchAllCnaeEmpresas();
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
