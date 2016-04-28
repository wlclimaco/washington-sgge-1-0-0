/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IFinanceiroBAC;
import com.qat.samples.sysmgmt.model.Financeiro;
import com.qat.samples.sysmgmt.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class FinanceiroWSImpl implements com.qat.samples.sysmgmt.service.IFinanceiroWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.financeirowsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.financeirowsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = FinanceiroWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FinanceiroWSImpl.class);

	/** The financeiro BAC. */
	private IFinanceiroBAC financeiroBAC; // injected by Spring through setter

	/**
	 * Spring Sets the financeiro BAC.
	 *
	 * @param financeiroBAC the new financeiro BAC
	 */
	public void setFinanceiroBAC(IFinanceiroBAC financeiroBAC)
	{
		this.financeiroBAC = financeiroBAC;
	}

	/**
	 * Gets the financeiro bac.
	 *
	 * @return the financeiro bac
	 */
	public IFinanceiroBAC getFinanceiroBAC()
	{
		return financeiroBAC;
	}


//===================================### CONTASPAGAR ####======================================
	@Override
	public ContasPagarResponse insertContasPagar(ContasPagarMaintenanceRequest request)
	{
		ContasPagarResponse response = new ContasPagarResponse();

		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().insertContasPagar(request);
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
	public ContasPagarResponse updateContasPagar(ContasPagarMaintenanceRequest request)
	{
		ContasPagarResponse response = new ContasPagarResponse();

		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().updateContasPagar(request);
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
	public ContasPagarResponse deleteContasPagar(ContasPagarMaintenanceRequest request)
	{
		ContasPagarResponse response = new ContasPagarResponse();

		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().deleteContasPagar(request);
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
	public ContasPagarResponse refreshContasPagars(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasPagarResponse response = new ContasPagarResponse();

		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().refreshContasPagars(request);
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
	public ContasPagarResponse fetchAllContasPagars(FetchAllRequest request)
	{
		ContasPagarResponse response = new ContasPagarResponse();

		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().fetchAllContasPagars(new ContasPagar());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IContasPagarWS#fetchContasPagarById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ContasPagarResponse fetchContasPagarById(FetchByIdRequest request)
	{
		ContasPagarResponse response = new ContasPagarResponse();

		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = new InternalResultsResponse<ContasPagar>();

			internalResponse = getFinanceiroBAC().fetchContasPagarById(request);
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
	public ContasPagarResponse fetchContasPagarsByRequest(ContasPagarInquiryRequest request)
	{
		ContasPagarResponse response = new ContasPagarResponse();

		try
		{
			InternalResultsResponse<ContasPagar> internalResponse = getFinanceiroBAC().fetchContasPagarsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CONTASRECEBER ####======================================
	@Override
	public ContasReceberResponse insertContasReceber(ContasReceberMaintenanceRequest request)
	{
		ContasReceberResponse response = new ContasReceberResponse();

		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().insertContasReceber(request);
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
	public ContasReceberResponse updateContasReceber(ContasReceberMaintenanceRequest request)
	{
		ContasReceberResponse response = new ContasReceberResponse();

		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().updateContasReceber(request);
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
	public ContasReceberResponse deleteContasReceber(ContasReceberMaintenanceRequest request)
	{
		ContasReceberResponse response = new ContasReceberResponse();

		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().deleteContasReceber(request);
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
	public ContasReceberResponse refreshContasRecebers(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContasReceberResponse response = new ContasReceberResponse();

		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().refreshContasRecebers(request);
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
	public ContasReceberResponse fetchAllContasRecebers(FetchAllRequest request)
	{
		ContasReceberResponse response = new ContasReceberResponse();

		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().fetchAllContasRecebers(new ContasReceber());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IContasReceberWS#fetchContasReceberById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ContasReceberResponse fetchContasReceberById(FetchByIdRequest request)
	{
		ContasReceberResponse response = new ContasReceberResponse();

		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = new InternalResultsResponse<ContasReceber>();

			internalResponse = getFinanceiroBAC().fetchContasReceberById(request);
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
	public ContasReceberResponse fetchContasRecebersByRequest(ContasReceberInquiryRequest request)
	{
		ContasReceberResponse response = new ContasReceberResponse();

		try
		{
			InternalResultsResponse<ContasReceber> internalResponse = getFinanceiroBAC().fetchContasRecebersByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CONDPAG ####======================================
	@Override
	public CondPagResponse insertCondPag(CondPagMaintenanceRequest request)
	{
		CondPagResponse response = new CondPagResponse();

		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().insertCondPag(request);
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
	public CondPagResponse updateCondPag(CondPagMaintenanceRequest request)
	{
		CondPagResponse response = new CondPagResponse();

		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().updateCondPag(request);
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
	public CondPagResponse deleteCondPag(CondPagMaintenanceRequest request)
	{
		CondPagResponse response = new CondPagResponse();

		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().deleteCondPag(request);
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
	public CondPagResponse refreshCondPags(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CondPagResponse response = new CondPagResponse();

		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().refreshCondPags(request);
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
	public CondPagResponse fetchAllCondPags(FetchAllRequest request)
	{
		CondPagResponse response = new CondPagResponse();

		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().fetchAllCondPags(new CondPag());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ICondPagWS#fetchCondPagById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public CondPagResponse fetchCondPagById(FetchByIdRequest request)
	{
		CondPagResponse response = new CondPagResponse();

		try
		{
			InternalResultsResponse<CondPag> internalResponse = new InternalResultsResponse<CondPag>();

			internalResponse = getFinanceiroBAC().fetchCondPagById(request);
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
	public CondPagResponse fetchCondPagsByRequest(CondPagInquiryRequest request)
	{
		CondPagResponse response = new CondPagResponse();

		try
		{
			InternalResultsResponse<CondPag> internalResponse = getFinanceiroBAC().fetchCondPagsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### FORMAPG ####======================================
	@Override
	public FormaPgResponse insertFormaPg(FormaPgMaintenanceRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();

		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().insertFormaPg(request);
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
	public FormaPgResponse updateFormaPg(FormaPgMaintenanceRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();

		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().updateFormaPg(request);
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
	public FormaPgResponse deleteFormaPg(FormaPgMaintenanceRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();

		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().deleteFormaPg(request);
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
	public FormaPgResponse refreshFormaPgs(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FormaPgResponse response = new FormaPgResponse();

		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().refreshFormaPgs(request);
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
	public FormaPgResponse fetchAllFormaPgs(FetchAllRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();

		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().fetchAllFormaPgs(new FormaPg());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IFormaPgWS#fetchFormaPgById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public FormaPgResponse fetchFormaPgById(FetchByIdRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();

		try
		{
			InternalResultsResponse<FormaPg> internalResponse = new InternalResultsResponse<FormaPg>();

			internalResponse = getFinanceiroBAC().fetchFormaPgById(request);
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
	public FormaPgResponse fetchFormaPgsByRequest(FormaPgInquiryRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();

		try
		{
			InternalResultsResponse<FormaPg> internalResponse = getFinanceiroBAC().fetchFormaPgsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### BANCO ####======================================
	@Override
	public BancoResponse insertBanco(BancoMaintenanceRequest request)
	{
		BancoResponse response = new BancoResponse();

		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().insertBanco(request);
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
	public BancoResponse updateBanco(BancoMaintenanceRequest request)
	{
		BancoResponse response = new BancoResponse();

		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().updateBanco(request);
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
	public BancoResponse deleteBanco(BancoMaintenanceRequest request)
	{
		BancoResponse response = new BancoResponse();

		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().deleteBanco(request);
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
	public BancoResponse refreshBancos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		BancoResponse response = new BancoResponse();

		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().refreshBancos(request);
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
	public BancoResponse fetchAllBancos(FetchAllRequest request)
	{
		BancoResponse response = new BancoResponse();

		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().fetchAllBancos(new Banco());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IBancoWS#fetchBancoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public BancoResponse fetchBancoById(FetchByIdRequest request)
	{
		BancoResponse response = new BancoResponse();

		try
		{
			InternalResultsResponse<Banco> internalResponse = new InternalResultsResponse<Banco>();

			internalResponse = getFinanceiroBAC().fetchBancoById(request);
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
	public BancoResponse fetchBancosByRequest(BancoInquiryRequest request)
	{
		BancoResponse response = new BancoResponse();

		try
		{
			InternalResultsResponse<Banco> internalResponse = getFinanceiroBAC().fetchBancosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CONTACORRENTE ####======================================
	@Override
	public ContaCorrenteResponse insertContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		ContaCorrenteResponse response = new ContaCorrenteResponse();

		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().insertContaCorrente(request);
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
	public ContaCorrenteResponse updateContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		ContaCorrenteResponse response = new ContaCorrenteResponse();

		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().updateContaCorrente(request);
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
	public ContaCorrenteResponse deleteContaCorrente(ContaCorrenteMaintenanceRequest request)
	{
		ContaCorrenteResponse response = new ContaCorrenteResponse();

		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().deleteContaCorrente(request);
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
	public ContaCorrenteResponse refreshContaCorrentes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ContaCorrenteResponse response = new ContaCorrenteResponse();

		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().refreshContaCorrentes(request);
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
	public ContaCorrenteResponse fetchAllContaCorrentes(FetchAllRequest request)
	{
		ContaCorrenteResponse response = new ContaCorrenteResponse();

		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().fetchAllContaCorrentes(new ContaCorrente());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IContaCorrenteWS#fetchContaCorrenteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ContaCorrenteResponse fetchContaCorrenteById(FetchByIdRequest request)
	{
		ContaCorrenteResponse response = new ContaCorrenteResponse();

		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = new InternalResultsResponse<ContaCorrente>();

			internalResponse = getFinanceiroBAC().fetchContaCorrenteById(request);
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
	public ContaCorrenteResponse fetchContaCorrentesByRequest(ContaCorrenteInquiryRequest request)
	{
		ContaCorrenteResponse response = new ContaCorrenteResponse();

		try
		{
			InternalResultsResponse<ContaCorrente> internalResponse = getFinanceiroBAC().fetchContaCorrentesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CAIXA ####======================================
	@Override
	public CaixaResponse insertCaixa(CaixaMaintenanceRequest request)
	{
		CaixaResponse response = new CaixaResponse();

		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().insertCaixa(request);
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
	public CaixaResponse updateCaixa(CaixaMaintenanceRequest request)
	{
		CaixaResponse response = new CaixaResponse();

		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().updateCaixa(request);
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
	public CaixaResponse deleteCaixa(CaixaMaintenanceRequest request)
	{
		CaixaResponse response = new CaixaResponse();

		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().deleteCaixa(request);
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
	public CaixaResponse refreshCaixas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CaixaResponse response = new CaixaResponse();

		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().refreshCaixas(request);
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
	public CaixaResponse fetchAllCaixas(FetchAllRequest request)
	{
		CaixaResponse response = new CaixaResponse();

		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().fetchAllCaixas(new Caixa());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ICaixaWS#fetchCaixaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public CaixaResponse fetchCaixaById(FetchByIdRequest request)
	{
		CaixaResponse response = new CaixaResponse();

		try
		{
			InternalResultsResponse<Caixa> internalResponse = new InternalResultsResponse<Caixa>();

			internalResponse = getFinanceiroBAC().fetchCaixaById(request);
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
	public CaixaResponse fetchCaixasByRequest(CaixaInquiryRequest request)
	{
		CaixaResponse response = new CaixaResponse();

		try
		{
			InternalResultsResponse<Caixa> internalResponse = getFinanceiroBAC().fetchCaixasByRequest(request);
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
