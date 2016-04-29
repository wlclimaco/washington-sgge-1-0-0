/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

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
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;
import com.qat.samples.sysmgmt.produto.model.response.CfopResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class FiscalWSImpl implements com.qat.samples.sysmgmt.service.IFiscalWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.fiscalwsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.fiscalwsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = FiscalWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FiscalWSImpl.class);

	/** The fiscal BAC. */
	private IFiscalBAC fiscalBAC; // injected by Spring through setter

	/**
	 * Spring Sets the fiscal BAC.
	 *
	 * @param fiscalBAC the new fiscal BAC
	 */
	public void setFiscalBAC(IFiscalBAC fiscalBAC)
	{
		this.fiscalBAC = fiscalBAC;
	}

	/**
	 * Gets the fiscal bac.
	 *
	 * @return the fiscal bac
	 */
	public IFiscalBAC getFiscalBAC()
	{
		return fiscalBAC;
	}


//===================================### REGIME ####======================================
	@Override
	public RegimeResponse insertRegime(RegimeMaintenanceRequest request)
	{
		RegimeResponse response = new RegimeResponse();

		try
		{
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().insertRegime(request);
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
	public RegimeResponse updateRegime(RegimeMaintenanceRequest request)
	{
		RegimeResponse response = new RegimeResponse();

		try
		{
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().updateRegime(request);
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
	public RegimeResponse deleteRegime(RegimeMaintenanceRequest request)
	{
		RegimeResponse response = new RegimeResponse();

		try
		{
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().deleteRegime(request);
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
	public RegimeResponse refreshRegimes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		RegimeResponse response = new RegimeResponse();

		try
		{
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().refreshRegimes(request);
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
	public RegimeResponse fetchAllRegimes(FetchAllRequest request)
	{
		RegimeResponse response = new RegimeResponse();

		try
		{
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().fetchAllRegimes(new Regime());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IRegimeWS#fetchRegimeById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public RegimeResponse fetchRegimeById(FetchByIdRequest request)
	{
		RegimeResponse response = new RegimeResponse();

		try
		{
			InternalResultsResponse<Regime> internalResponse = new InternalResultsResponse<Regime>();

			internalResponse = getFiscalBAC().fetchRegimeById(request);
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
	public RegimeResponse fetchRegimesByRequest(RegimeInquiryRequest request)
	{
		RegimeResponse response = new RegimeResponse();

		try
		{
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().fetchRegimesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CFOP ####======================================
	@Override
	public CfopResponse insertCfop(CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();

		try
		{
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().insertCfop(request);
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
	public CfopResponse updateCfop(CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();

		try
		{
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().updateCfop(request);
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
	public CfopResponse deleteCfop(CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();

		try
		{
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().deleteCfop(request);
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
	public CfopResponse refreshCfops(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CfopResponse response = new CfopResponse();

		try
		{
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().refreshCfops(request);
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
	public CfopResponse fetchAllCfops(FetchAllRequest request)
	{
		CfopResponse response = new CfopResponse();

		try
		{
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().fetchAllCfops(new Cfop());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ICfopWS#fetchCfopById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public CfopResponse fetchCfopById(FetchByIdRequest request)
	{
		CfopResponse response = new CfopResponse();

		try
		{
			InternalResultsResponse<Cfop> internalResponse = new InternalResultsResponse<Cfop>();

			internalResponse = getFiscalBAC().fetchCfopById(request);
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
	public CfopResponse fetchCfopsByRequest(CfopInquiryRequest request)
	{
		CfopResponse response = new CfopResponse();

		try
		{
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().fetchCfopsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CNAE ####======================================
	@Override
	public CnaeResponse insertCnae(CnaeMaintenanceRequest request)
	{
		CnaeResponse response = new CnaeResponse();

		try
		{
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().insertCnae(request);
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
	public CnaeResponse updateCnae(CnaeMaintenanceRequest request)
	{
		CnaeResponse response = new CnaeResponse();

		try
		{
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().updateCnae(request);
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
	public CnaeResponse deleteCnae(CnaeMaintenanceRequest request)
	{
		CnaeResponse response = new CnaeResponse();

		try
		{
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().deleteCnae(request);
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
	public CnaeResponse refreshCnaes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CnaeResponse response = new CnaeResponse();

		try
		{
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().refreshCnaes(request);
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
	public CnaeResponse fetchAllCnaes(FetchAllRequest request)
	{
		CnaeResponse response = new CnaeResponse();

		try
		{
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().fetchAllCnaes(new Cnae());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ICnaeWS#fetchCnaeById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public CnaeResponse fetchCnaeById(FetchByIdRequest request)
	{
		CnaeResponse response = new CnaeResponse();

		try
		{
			InternalResultsResponse<Cnae> internalResponse = new InternalResultsResponse<Cnae>();

			internalResponse = getFiscalBAC().fetchCnaeById(request);
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
	public CnaeResponse fetchCnaesByRequest(CnaeInquiryRequest request)
	{
		CnaeResponse response = new CnaeResponse();

		try
		{
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().fetchCnaesByRequest(request);
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
