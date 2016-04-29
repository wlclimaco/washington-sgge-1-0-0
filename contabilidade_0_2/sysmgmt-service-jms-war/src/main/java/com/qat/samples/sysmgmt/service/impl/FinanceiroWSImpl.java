/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Financeiro.IFinanceiroBAC;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.banco.model.request.BancoMaintenanceRequest;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.dp.model.response.BancoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FormaPgResponse;
import com.qat.samples.sysmgmt.financeiro.model.Caixa;
import com.qat.samples.sysmgmt.financeiro.model.ContasPagar;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CaixaMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.CondPagMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContaCorrenteMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasPagarMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.ContasReceberMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FormaPgMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.CondPagResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContaCorrenteResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContasPagarResponse;
import com.qat.samples.sysmgmt.financeiro.model.response.ContasReceberResponse;
import com.qat.samples.sysmgmt.nf.model.response.CaixaResponse;
import com.qat.samples.sysmgmt.service.IFinanceiroWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * FinanceiroWS used to provide WS interface. Delegates all calls to the IFinanceiroBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class FinanceiroWSImpl implements IFinanceiroWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.financeirojmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.financeirojmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = FinanceiroWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FinanceiroWSImpl.class);
	private IFinanceiroBAC financeiroBAC;
	/**
	 * @return the financeiroBAC which is expected to provide the implementation.
	 */
	public IFinanceiroBAC getFinanceiroBAC()
	{
		return financeiroBAC;
	}
	/**
	 * Spring injection uses this method to inject an implementation of {@link IFinanceiroBAC}.
	 *
	 * @param financeiroBAC the financeiroBAC to set.
	 */
	public void setFinanceiroBAC(IFinanceiroBAC financeiroBAC)
	{
		this.financeiroBAC = financeiroBAC;
	}


//===================================### CONTASPAGAR ####======================================

	/**
	 * Delegates call to {@link IContasPagarBAC}
	 *
	 * @param request a ContasPagarRequest
	 * @return ContasPagarResponse
	 */
	@Override
	public ContasPagarResponse insertContasPagar(ContasPagarMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasPagarResponse response = new ContasPagarResponse();
		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().insertContasPagar(request);
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
	 * Delegates call to {@link IContasPagarBAC}
	 *
	 * @param request a ContasPagarRequest
	 * @return ContasPagarResponse
	 */
	@Override
	public ContasPagarResponse updateContasPagar(ContasPagarMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasPagarResponse response = new ContasPagarResponse();
		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().updateContasPagar(request);
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
	 * Delegates call to {@link IContasPagarBAC}
	 *
	 * @param request a ContasPagarRequest
	 * @return ContasPagarResponse
	 */
	@Override
	public ContasPagarResponse deleteContasPagar(ContasPagarMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasPagarResponse response = new ContasPagarResponse();
		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().deleteContasPagar(request);
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
	 * Delegates call to {@link IContasPagarBAC}
	 *
	 * @param request a ContasPagarRequest
	 * @return ContasPagarResponse
	 */
	@Override
	public ContasPagarResponse fetchContasPagarById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasPagarResponse response = new ContasPagarResponse();
		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().fetchContasPagarById(request);
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
	 * Delegates call to {@link IContasPagarBAC}
	 *
	 * @param request a ContasPagarRequest
	 * @return ContasPagarResponse
	 */
	@Override
	public ContasPagarResponse fetchContasPagarsByRequest(ContasPagarInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasPagarResponse response = new ContasPagarResponse();
		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().fetchContasPagarsByRequest(request);
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
	 * Delegates call to {@link IContasPagarBAC}
	 *
	 * @param request a ContasPagarRequest
	 * @return ContasPagarResponse
	 */
	@Override
	public ContasPagarResponse refreshContasPagars(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasPagarResponse response = new ContasPagarResponse();
		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().refreshContasPagars(request);
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
	 * Delegates call to {@link IContasPagarBAC}
	 *
	 * @param request a ContasPagarRequest
	 * @return ContasPagarResponse
	 */
	@Override
	public ContasPagarResponse fetchAllContasPagars(FetchAllRequest request)
	{
		ContasPagarResponse response = new ContasPagarResponse();
		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().fetchAllContasPagars(new ContasPagar());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CONTASRECEBER ####======================================

	/**
	 * Delegates call to {@link IContasReceberBAC}
	 *
	 * @param request a ContasReceberRequest
	 * @return ContasReceberResponse
	 */
	@Override
	public ContasReceberResponse insertContasReceber(ContasReceberMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasReceberResponse response = new ContasReceberResponse();
		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().insertContasReceber(request);
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
	 * Delegates call to {@link IContasReceberBAC}
	 *
	 * @param request a ContasReceberRequest
	 * @return ContasReceberResponse
	 */
	@Override
	public ContasReceberResponse updateContasReceber(ContasReceberMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasReceberResponse response = new ContasReceberResponse();
		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().updateContasReceber(request);
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
	 * Delegates call to {@link IContasReceberBAC}
	 *
	 * @param request a ContasReceberRequest
	 * @return ContasReceberResponse
	 */
	@Override
	public ContasReceberResponse deleteContasReceber(ContasReceberMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasReceberResponse response = new ContasReceberResponse();
		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().deleteContasReceber(request);
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
	 * Delegates call to {@link IContasReceberBAC}
	 *
	 * @param request a ContasReceberRequest
	 * @return ContasReceberResponse
	 */
	@Override
	public ContasReceberResponse fetchContasReceberById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasReceberResponse response = new ContasReceberResponse();
		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().fetchContasReceberById(request);
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
	 * Delegates call to {@link IContasReceberBAC}
	 *
	 * @param request a ContasReceberRequest
	 * @return ContasReceberResponse
	 */
	@Override
	public ContasReceberResponse fetchContasRecebersByRequest(ContasReceberInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasReceberResponse response = new ContasReceberResponse();
		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().fetchContasRecebersByRequest(request);
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
	 * Delegates call to {@link IContasReceberBAC}
	 *
	 * @param request a ContasReceberRequest
	 * @return ContasReceberResponse
	 */
	@Override
	public ContasReceberResponse refreshContasRecebers(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasReceberResponse response = new ContasReceberResponse();
		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().refreshContasRecebers(request);
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
	 * Delegates call to {@link IContasReceberBAC}
	 *
	 * @param request a ContasReceberRequest
	 * @return ContasReceberResponse
	 */
	@Override
	public ContasReceberResponse fetchAllContasRecebers(FetchAllRequest request)
	{
		ContasReceberResponse response = new ContasReceberResponse();
		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().fetchAllContasRecebers(new ContasReceber());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CONDPAG ####======================================

	/**
	 * Delegates call to {@link ICondPagBAC}
	 *
	 * @param request a CondPagRequest
	 * @return CondPagResponse
	 */
	@Override
	public CondPagResponse insertCondPag(CondPagMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CondPagResponse response = new CondPagResponse();
		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().insertCondPag(request);
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
	 * Delegates call to {@link ICondPagBAC}
	 *
	 * @param request a CondPagRequest
	 * @return CondPagResponse
	 */
	@Override
	public CondPagResponse updateCondPag(CondPagMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CondPagResponse response = new CondPagResponse();
		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().updateCondPag(request);
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
	 * Delegates call to {@link ICondPagBAC}
	 *
	 * @param request a CondPagRequest
	 * @return CondPagResponse
	 */
	@Override
	public CondPagResponse deleteCondPag(CondPagMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CondPagResponse response = new CondPagResponse();
		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().deleteCondPag(request);
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
	 * Delegates call to {@link ICondPagBAC}
	 *
	 * @param request a CondPagRequest
	 * @return CondPagResponse
	 */
	@Override
	public CondPagResponse fetchCondPagById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CondPagResponse response = new CondPagResponse();
		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().fetchCondPagById(request);
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
	 * Delegates call to {@link ICondPagBAC}
	 *
	 * @param request a CondPagRequest
	 * @return CondPagResponse
	 */
	@Override
	public CondPagResponse fetchCondPagsByRequest(CondPagInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CondPagResponse response = new CondPagResponse();
		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().fetchCondPagsByRequest(request);
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
	 * Delegates call to {@link ICondPagBAC}
	 *
	 * @param request a CondPagRequest
	 * @return CondPagResponse
	 */
	@Override
	public CondPagResponse refreshCondPags(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CondPagResponse response = new CondPagResponse();
		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().refreshCondPags(request);
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
	 * Delegates call to {@link ICondPagBAC}
	 *
	 * @param request a CondPagRequest
	 * @return CondPagResponse
	 */
	@Override
	public CondPagResponse fetchAllCondPags(FetchAllRequest request)
	{
		CondPagResponse response = new CondPagResponse();
		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().fetchAllCondPags(new CondPag());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### FORMAPG ####======================================

	/**
	 * Delegates call to {@link IFormaPgBAC}
	 *
	 * @param request a FormaPgRequest
	 * @return FormaPgResponse
	 */
	@Override
	public FormaPgResponse insertFormaPg(FormaPgMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().insertFormaPg(request);
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
	 * Delegates call to {@link IFormaPgBAC}
	 *
	 * @param request a FormaPgRequest
	 * @return FormaPgResponse
	 */
	@Override
	public FormaPgResponse updateFormaPg(FormaPgMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().updateFormaPg(request);
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
	 * Delegates call to {@link IFormaPgBAC}
	 *
	 * @param request a FormaPgRequest
	 * @return FormaPgResponse
	 */
	@Override
	public FormaPgResponse deleteFormaPg(FormaPgMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().deleteFormaPg(request);
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
	 * Delegates call to {@link IFormaPgBAC}
	 *
	 * @param request a FormaPgRequest
	 * @return FormaPgResponse
	 */
	@Override
	public FormaPgResponse fetchFormaPgById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().fetchFormaPgById(request);
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
	 * Delegates call to {@link IFormaPgBAC}
	 *
	 * @param request a FormaPgRequest
	 * @return FormaPgResponse
	 */
	@Override
	public FormaPgResponse fetchFormaPgsByRequest(FormaPgInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().fetchFormaPgsByRequest(request);
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
	 * Delegates call to {@link IFormaPgBAC}
	 *
	 * @param request a FormaPgRequest
	 * @return FormaPgResponse
	 */
	@Override
	public FormaPgResponse refreshFormaPgs(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().refreshFormaPgs(request);
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
	 * Delegates call to {@link IFormaPgBAC}
	 *
	 * @param request a FormaPgRequest
	 * @return FormaPgResponse
	 */
	@Override
	public FormaPgResponse fetchAllFormaPgs(FetchAllRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().fetchAllFormaPgs(new FormaPg());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### BANCO ####======================================

	/**
	 * Delegates call to {@link IBancoBAC}
	 *
	 * @param request a BancoRequest
	 * @return BancoResponse
	 */
	@Override
	public BancoResponse insertBanco(BancoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BancoResponse response = new BancoResponse();
		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().insertBanco(request);
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
	 * Delegates call to {@link IBancoBAC}
	 *
	 * @param request a BancoRequest
	 * @return BancoResponse
	 */
	@Override
	public BancoResponse updateBanco(BancoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BancoResponse response = new BancoResponse();
		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().updateBanco(request);
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
	 * Delegates call to {@link IBancoBAC}
	 *
	 * @param request a BancoRequest
	 * @return BancoResponse
	 */
	@Override
	public BancoResponse deleteBanco(BancoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BancoResponse response = new BancoResponse();
		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().deleteBanco(request);
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
	 * Delegates call to {@link IBancoBAC}
	 *
	 * @param request a BancoRequest
	 * @return BancoResponse
	 */
	@Override
	public BancoResponse fetchBancoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BancoResponse response = new BancoResponse();
		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().fetchBancoById(request);
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
	 * Delegates call to {@link IBancoBAC}
	 *
	 * @param request a BancoRequest
	 * @return BancoResponse
	 */
	@Override
	public BancoResponse fetchBancosByRequest(BancoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BancoResponse response = new BancoResponse();
		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().fetchBancosByRequest(request);
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
	 * Delegates call to {@link IBancoBAC}
	 *
	 * @param request a BancoRequest
	 * @return BancoResponse
	 */
	@Override
	public BancoResponse refreshBancos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BancoResponse response = new BancoResponse();
		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().refreshBancos(request);
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
	 * Delegates call to {@link IBancoBAC}
	 *
	 * @param request a BancoRequest
	 * @return BancoResponse
	 */
	@Override
	public BancoResponse fetchAllBancos(FetchAllRequest request)
	{
		BancoResponse response = new BancoResponse();
		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().fetchAllBancos(new Banco());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CONTACORRENTE ####======================================

	/**
	 * Delegates call to {@link IContaCorrenteBAC}
	 *
	 * @param request a ContaCorrenteRequest
	 * @return ContaCorrenteResponse
	 */
	@Override
	public ContaCorrenteResponse insertContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContaCorrenteResponse response = new ContaCorrenteResponse();
		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().insertContaCorrente(request);
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
	 * Delegates call to {@link IContaCorrenteBAC}
	 *
	 * @param request a ContaCorrenteRequest
	 * @return ContaCorrenteResponse
	 */
	@Override
	public ContaCorrenteResponse updateContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContaCorrenteResponse response = new ContaCorrenteResponse();
		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().updateContaCorrente(request);
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
	 * Delegates call to {@link IContaCorrenteBAC}
	 *
	 * @param request a ContaCorrenteRequest
	 * @return ContaCorrenteResponse
	 */
	@Override
	public ContaCorrenteResponse deleteContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContaCorrenteResponse response = new ContaCorrenteResponse();
		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().deleteContaCorrente(request);
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
	 * Delegates call to {@link IContaCorrenteBAC}
	 *
	 * @param request a ContaCorrenteRequest
	 * @return ContaCorrenteResponse
	 */
	@Override
	public ContaCorrenteResponse fetchContaCorrenteById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContaCorrenteResponse response = new ContaCorrenteResponse();
		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().fetchContaCorrenteById(request);
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
	 * Delegates call to {@link IContaCorrenteBAC}
	 *
	 * @param request a ContaCorrenteRequest
	 * @return ContaCorrenteResponse
	 */
	@Override
	public ContaCorrenteResponse fetchContaCorrentesByRequest(ContaCorrenteInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContaCorrenteResponse response = new ContaCorrenteResponse();
		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().fetchContaCorrentesByRequest(request);
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
	 * Delegates call to {@link IContaCorrenteBAC}
	 *
	 * @param request a ContaCorrenteRequest
	 * @return ContaCorrenteResponse
	 */
	@Override
	public ContaCorrenteResponse refreshContaCorrentes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContaCorrenteResponse response = new ContaCorrenteResponse();
		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().refreshContaCorrentes(request);
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
	 * Delegates call to {@link IContaCorrenteBAC}
	 *
	 * @param request a ContaCorrenteRequest
	 * @return ContaCorrenteResponse
	 */
	@Override
	public ContaCorrenteResponse fetchAllContaCorrentes(FetchAllRequest request)
	{
		ContaCorrenteResponse response = new ContaCorrenteResponse();
		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().fetchAllContaCorrentes(new ContaCorrente());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CAIXA ####======================================

	/**
	 * Delegates call to {@link ICaixaBAC}
	 *
	 * @param request a CaixaRequest
	 * @return CaixaResponse
	 */
	@Override
	public CaixaResponse insertCaixa(CaixaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CaixaResponse response = new CaixaResponse();
		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().insertCaixa(request);
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
	 * Delegates call to {@link ICaixaBAC}
	 *
	 * @param request a CaixaRequest
	 * @return CaixaResponse
	 */
	@Override
	public CaixaResponse updateCaixa(CaixaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CaixaResponse response = new CaixaResponse();
		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().updateCaixa(request);
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
	 * Delegates call to {@link ICaixaBAC}
	 *
	 * @param request a CaixaRequest
	 * @return CaixaResponse
	 */
	@Override
	public CaixaResponse deleteCaixa(CaixaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CaixaResponse response = new CaixaResponse();
		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().deleteCaixa(request);
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
	 * Delegates call to {@link ICaixaBAC}
	 *
	 * @param request a CaixaRequest
	 * @return CaixaResponse
	 */
	@Override
	public CaixaResponse fetchCaixaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CaixaResponse response = new CaixaResponse();
		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().fetchCaixaById(request);
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
	 * Delegates call to {@link ICaixaBAC}
	 *
	 * @param request a CaixaRequest
	 * @return CaixaResponse
	 */
	@Override
	public CaixaResponse fetchCaixasByRequest(CaixaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CaixaResponse response = new CaixaResponse();
		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().fetchCaixasByRequest(request);
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
	 * Delegates call to {@link ICaixaBAC}
	 *
	 * @param request a CaixaRequest
	 * @return CaixaResponse
	 */
	@Override
	public CaixaResponse refreshCaixas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CaixaResponse response = new CaixaResponse();
		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().refreshCaixas(request);
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
	 * Delegates call to {@link ICaixaBAC}
	 *
	 * @param request a CaixaRequest
	 * @return CaixaResponse
	 */
	@Override
	public CaixaResponse fetchAllCaixas(FetchAllRequest request)
	{
		CaixaResponse response = new CaixaResponse();
		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().fetchAllCaixas(new Caixa());
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
