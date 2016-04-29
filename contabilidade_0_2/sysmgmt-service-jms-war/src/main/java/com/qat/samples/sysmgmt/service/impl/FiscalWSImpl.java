/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Fiscal.IFiscalBAC;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;
import com.qat.samples.sysmgmt.produto.model.response.CfopResponse;
import com.qat.samples.sysmgmt.service.IFiscalWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
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
	/**
	 * Spring injection uses this method to inject an implementation of {@link IFiscalBAC}.
	 *
	 * @param fiscalBAC the fiscalBAC to set.
	 */
	public void setFiscalBAC(IFiscalBAC fiscalBAC)
	{
		this.fiscalBAC = fiscalBAC;
	}


//===================================### REGIME ####======================================

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
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().insertRegime(request);
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
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().updateRegime(request);
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
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().deleteRegime(request);
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
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().fetchRegimeById(request);
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
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().fetchRegimesByRequest(request);
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
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().refreshRegimes(request);
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
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().fetchAllRegimes(new Regime());
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
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().insertCfop(request);
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
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().updateCfop(request);
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
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().deleteCfop(request);
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
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().fetchCfopById(request);
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
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().fetchCfopsByRequest(request);
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
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().refreshCfops(request);
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
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().fetchAllCfops(new Cfop());
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
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().insertCnae(request);
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
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().updateCnae(request);
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
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().deleteCnae(request);
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
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().fetchCnaeById(request);
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
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().fetchCnaesByRequest(request);
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
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().refreshCnaes(request);
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
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().fetchAllCnaes(new Cnae());
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
