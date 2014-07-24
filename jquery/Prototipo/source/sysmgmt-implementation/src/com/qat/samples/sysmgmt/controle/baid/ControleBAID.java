package com.qat.samples.sysmgmt.controle.baid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.controle.bac.IControleBAC;
import com.qat.samples.sysmgmt.controle.model.Controle;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.response.AcessosResponse;
import com.qat.samples.sysmgmt.controle.model.response.ControleResponse;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * Delegate class for Cidade BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ControleBAID
{

	/** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG = "sysmgmt.base.cidadebaidimpl.defaultexception";

	/** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ControleBAID.class);

	/**
	 * Required for final classes to insure no one tries to instantiate it.
	 */
	private ControleBAID()
	{
	}

	/**
	 * Fetch all cidades.
	 * 
	 * @param cidadeBAC the cidade bac
	 * @param response the response
	 */
	public static void fetchAllControles(IControleBAC procedureBAC, ControleResponse response,
			ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> internalResponse = procedureBAC.fetchAllControles(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	public static void fetchAllAcessos(IControleBAC procedureBAC, AcessosResponse response,
			AcessosInquiryRequest request)
	{
		InternalResultsResponse<ControleAcess> internalResponse = procedureBAC.fetchAllAcessos(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	public static void fetchControleByPage(IControleBAC procedureBAC, ControleResponse response,
			ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> internalResponse = procedureBAC.fetchControleByPage(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	public static void fetchControlesByRequest(IControleBAC procedureBAC, ControleResponse response,
			ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> internalResponse = procedureBAC.fetchControlesByRequest(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	public static void fetchControleByAction(IControleBAC procedureBAC, ControleResponse response,
			ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> internalResponse = procedureBAC.fetchControleByAction(request);
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
					.getStatus().toString()});
		}
		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

}
