/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IFinanceiroBAC;
import com.qat.samples.sysmgmt.model.Financeiro;
import com.qat.samples.sysmgmt.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.service.IFinanceiroWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
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

//===================================### CONTASPAGAR ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IContasPagarBAC}.
	 *
	 * @param contaspagarBAC the contaspagarBAC to set.
	 */
	public void setContasPagarBAC(IContasPagarBAC contaspagarBAC)
	{
		this.contaspagarBAC = contaspagarBAC;
	}
	
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
			InternalResultsResponse<ContasPagar> internalResponse = getContasPagarBAC().insertContasPagar(request);
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
			InternalResultsResponse<ContasPagar> internalResponse = getContasPagarBAC().updateContasPagar(request);
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
			InternalResultsResponse<ContasPagar> internalResponse = getContasPagarBAC().deleteContasPagar(request);
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
			InternalResultsResponse<ContasPagar> internalResponse = getContasPagarBAC().fetchContasPagarById(request);
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
			InternalResultsResponse<ContasPagar> internalResponse = getContasPagarBAC().fetchContasPagarsByRequest(request);
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
			InternalResultsResponse<ContasPagar> internalResponse = getContasPagarBAC().refreshContasPagars(request);
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
			InternalResultsResponse<ContasPagar> internalResponse = getContasPagarBAC().fetchAllContasPagars();
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
	 * Spring injection uses this method to inject an implementation of {@link IContasReceberBAC}.
	 *
	 * @param contasreceberBAC the contasreceberBAC to set.
	 */
	public void setContasReceberBAC(IContasReceberBAC contasreceberBAC)
	{
		this.contasreceberBAC = contasreceberBAC;
	}
	
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
			InternalResultsResponse<ContasReceber> internalResponse = getContasReceberBAC().insertContasReceber(request);
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
			InternalResultsResponse<ContasReceber> internalResponse = getContasReceberBAC().updateContasReceber(request);
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
			InternalResultsResponse<ContasReceber> internalResponse = getContasReceberBAC().deleteContasReceber(request);
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
			InternalResultsResponse<ContasReceber> internalResponse = getContasReceberBAC().fetchContasReceberById(request);
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
			InternalResultsResponse<ContasReceber> internalResponse = getContasReceberBAC().fetchContasRecebersByRequest(request);
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
			InternalResultsResponse<ContasReceber> internalResponse = getContasReceberBAC().refreshContasRecebers(request);
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
			InternalResultsResponse<ContasReceber> internalResponse = getContasReceberBAC().fetchAllContasRecebers();
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
	 * Spring injection uses this method to inject an implementation of {@link ICondPagBAC}.
	 *
	 * @param condpagBAC the condpagBAC to set.
	 */
	public void setCondPagBAC(ICondPagBAC condpagBAC)
	{
		this.condpagBAC = condpagBAC;
	}
	
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
			InternalResultsResponse<CondPag> internalResponse = getCondPagBAC().insertCondPag(request);
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
			InternalResultsResponse<CondPag> internalResponse = getCondPagBAC().updateCondPag(request);
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
			InternalResultsResponse<CondPag> internalResponse = getCondPagBAC().deleteCondPag(request);
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
			InternalResultsResponse<CondPag> internalResponse = getCondPagBAC().fetchCondPagById(request);
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
			InternalResultsResponse<CondPag> internalResponse = getCondPagBAC().fetchCondPagsByRequest(request);
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
			InternalResultsResponse<CondPag> internalResponse = getCondPagBAC().refreshCondPags(request);
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
			InternalResultsResponse<CondPag> internalResponse = getCondPagBAC().fetchAllCondPags();
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
	 * Spring injection uses this method to inject an implementation of {@link IFormaPgBAC}.
	 *
	 * @param formapgBAC the formapgBAC to set.
	 */
	public void setFormaPgBAC(IFormaPgBAC formapgBAC)
	{
		this.formapgBAC = formapgBAC;
	}
	
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
			InternalResultsResponse<FormaPg> internalResponse = getFormaPgBAC().insertFormaPg(request);
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
			InternalResultsResponse<FormaPg> internalResponse = getFormaPgBAC().updateFormaPg(request);
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
			InternalResultsResponse<FormaPg> internalResponse = getFormaPgBAC().deleteFormaPg(request);
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
			InternalResultsResponse<FormaPg> internalResponse = getFormaPgBAC().fetchFormaPgById(request);
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
			InternalResultsResponse<FormaPg> internalResponse = getFormaPgBAC().fetchFormaPgsByRequest(request);
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
			InternalResultsResponse<FormaPg> internalResponse = getFormaPgBAC().refreshFormaPgs(request);
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
			InternalResultsResponse<FormaPg> internalResponse = getFormaPgBAC().fetchAllFormaPgs();
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
	 * Spring injection uses this method to inject an implementation of {@link IBancoBAC}.
	 *
	 * @param bancoBAC the bancoBAC to set.
	 */
	public void setBancoBAC(IBancoBAC bancoBAC)
	{
		this.bancoBAC = bancoBAC;
	}
	
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
			InternalResultsResponse<Banco> internalResponse = getBancoBAC().insertBanco(request);
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
			InternalResultsResponse<Banco> internalResponse = getBancoBAC().updateBanco(request);
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
			InternalResultsResponse<Banco> internalResponse = getBancoBAC().deleteBanco(request);
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
			InternalResultsResponse<Banco> internalResponse = getBancoBAC().fetchBancoById(request);
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
			InternalResultsResponse<Banco> internalResponse = getBancoBAC().fetchBancosByRequest(request);
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
			InternalResultsResponse<Banco> internalResponse = getBancoBAC().refreshBancos(request);
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
			InternalResultsResponse<Banco> internalResponse = getBancoBAC().fetchAllBancos();
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
	 * Spring injection uses this method to inject an implementation of {@link IContaCorrenteBAC}.
	 *
	 * @param contacorrenteBAC the contacorrenteBAC to set.
	 */
	public void setContaCorrenteBAC(IContaCorrenteBAC contacorrenteBAC)
	{
		this.contacorrenteBAC = contacorrenteBAC;
	}
	
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
			InternalResultsResponse<ContaCorrente> internalResponse = getContaCorrenteBAC().insertContaCorrente(request);
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
			InternalResultsResponse<ContaCorrente> internalResponse = getContaCorrenteBAC().updateContaCorrente(request);
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
			InternalResultsResponse<ContaCorrente> internalResponse = getContaCorrenteBAC().deleteContaCorrente(request);
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
			InternalResultsResponse<ContaCorrente> internalResponse = getContaCorrenteBAC().fetchContaCorrenteById(request);
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
			InternalResultsResponse<ContaCorrente> internalResponse = getContaCorrenteBAC().fetchContaCorrentesByRequest(request);
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
			InternalResultsResponse<ContaCorrente> internalResponse = getContaCorrenteBAC().refreshContaCorrentes(request);
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
			InternalResultsResponse<ContaCorrente> internalResponse = getContaCorrenteBAC().fetchAllContaCorrentes();
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
	 * Spring injection uses this method to inject an implementation of {@link ICaixaBAC}.
	 *
	 * @param caixaBAC the caixaBAC to set.
	 */
	public void setCaixaBAC(ICaixaBAC caixaBAC)
	{
		this.caixaBAC = caixaBAC;
	}
	
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
			InternalResultsResponse<Caixa> internalResponse = getCaixaBAC().insertCaixa(request);
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
			InternalResultsResponse<Caixa> internalResponse = getCaixaBAC().updateCaixa(request);
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
			InternalResultsResponse<Caixa> internalResponse = getCaixaBAC().deleteCaixa(request);
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
			InternalResultsResponse<Caixa> internalResponse = getCaixaBAC().fetchCaixaById(request);
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
			InternalResultsResponse<Caixa> internalResponse = getCaixaBAC().fetchCaixasByRequest(request);
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
			InternalResultsResponse<Caixa> internalResponse = getCaixaBAC().refreshCaixas(request);
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
			InternalResultsResponse<Caixa> internalResponse = getCaixaBAC().fetchAllCaixas();
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
