/** create by system gera-java version 1.0.0 28/04/2016 20:5 : 32*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Cadastros.ICadastrosBAC;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.dp.model.response.ConvenioResponse;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.FornecedorResponse;
import com.qat.samples.sysmgmt.pessoa.model.response.TransportadorResponse;
import com.qat.samples.sysmgmt.service.ICadastrosWS;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.ConvenioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.util.model.response.TarefaResponse;
/**
 * CadastrosWS used to provide WS interface. Delegates all calls to the ICadastrosBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class CadastroWSImpl implements ICadastrosWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cadastrosjmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.cadastrosjmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CadastroWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CadastroWSImpl.class);
	private ICadastrosBAC cadastrosBAC;
	/**
	 * @return the cadastrosBAC which is expected to provide the implementation.
	 */
	public ICadastrosBAC getCadastrosBAC()
	{
		return cadastrosBAC;
	}
	/**
	 * Spring injection uses this method to inject an implementation of {@link ICadastrosBAC}.
	 *
	 * @param cadastrosBAC the cadastrosBAC to set.
	 */
	public void setCadastrosBAC(ICadastrosBAC cadastrosBAC)
	{
		this.cadastrosBAC = cadastrosBAC;
	}


//===================================### CLIENTE ####======================================

	/**
	 * Delegates call to {@link IClienteBAC}
	 *
	 * @param request a ClienteRequest
	 * @return ClienteResponse
	 */
	@Override
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().insertCliente(request);
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
	 * Delegates call to {@link IClienteBAC}
	 *
	 * @param request a ClienteRequest
	 * @return ClienteResponse
	 */
	@Override
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().updateCliente(request);
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
	 * Delegates call to {@link IClienteBAC}
	 *
	 * @param request a ClienteRequest
	 * @return ClienteResponse
	 */
	@Override
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().deleteCliente(request);
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
	 * Delegates call to {@link IClienteBAC}
	 *
	 * @param request a ClienteRequest
	 * @return ClienteResponse
	 */
	@Override
	public ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().fetchClienteById(request);
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
	 * Delegates call to {@link IClienteBAC}
	 *
	 * @param request a ClienteRequest
	 * @return ClienteResponse
	 */
	@Override
	public ClienteResponse fetchClientesByRequest(ClienteInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().fetchClientesByRequest(request);
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
	 * Delegates call to {@link IClienteBAC}
	 *
	 * @param request a ClienteRequest
	 * @return ClienteResponse
	 */
	@Override
	public ClienteResponse refreshClientes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().refreshClientes(request);
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
	 * Delegates call to {@link IClienteBAC}
	 *
	 * @param request a ClienteRequest
	 * @return ClienteResponse
	 */
	@Override
	public ClienteResponse fetchAllClientes(FetchAllRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().fetchAllClientes(new Cliente());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### FORNECEDOR ####======================================

	/**
	 * Delegates call to {@link IFornecedorBAC}
	 *
	 * @param request a FornecedorRequest
	 * @return FornecedorResponse
	 */
	@Override
	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().insertFornecedor(request);
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
	 * Delegates call to {@link IFornecedorBAC}
	 *
	 * @param request a FornecedorRequest
	 * @return FornecedorResponse
	 */
	@Override
	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().updateFornecedor(request);
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
	 * Delegates call to {@link IFornecedorBAC}
	 *
	 * @param request a FornecedorRequest
	 * @return FornecedorResponse
	 */
	@Override
	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().deleteFornecedor(request);
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
	 * Delegates call to {@link IFornecedorBAC}
	 *
	 * @param request a FornecedorRequest
	 * @return FornecedorResponse
	 */
	@Override
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().fetchFornecedorById(request);
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
	 * Delegates call to {@link IFornecedorBAC}
	 *
	 * @param request a FornecedorRequest
	 * @return FornecedorResponse
	 */
	@Override
	public FornecedorResponse fetchFornecedorsByRequest(FornecedorInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().fetchFornecedorsByRequest(request);
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
	 * Delegates call to {@link IFornecedorBAC}
	 *
	 * @param request a FornecedorRequest
	 * @return FornecedorResponse
	 */
	@Override
	public FornecedorResponse refreshFornecedors(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().refreshFornecedors(request);
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
	 * Delegates call to {@link IFornecedorBAC}
	 *
	 * @param request a FornecedorRequest
	 * @return FornecedorResponse
	 */
	@Override
	public FornecedorResponse fetchAllFornecedors(FetchAllRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().fetchAllFornecedors(new Fornecedor());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### TRANSPORTADOR ####======================================

	/**
	 * Delegates call to {@link ITransportadorBAC}
	 *
	 * @param request a TransportadorRequest
	 * @return TransportadorResponse
	 */
	@Override
	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().insertTransportador(request);
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
	 * Delegates call to {@link ITransportadorBAC}
	 *
	 * @param request a TransportadorRequest
	 * @return TransportadorResponse
	 */
	@Override
	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().updateTransportador(request);
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
	 * Delegates call to {@link ITransportadorBAC}
	 *
	 * @param request a TransportadorRequest
	 * @return TransportadorResponse
	 */
	@Override
	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().deleteTransportador(request);
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
	 * Delegates call to {@link ITransportadorBAC}
	 *
	 * @param request a TransportadorRequest
	 * @return TransportadorResponse
	 */
	@Override
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().fetchTransportadorById(request);
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
	 * Delegates call to {@link ITransportadorBAC}
	 *
	 * @param request a TransportadorRequest
	 * @return TransportadorResponse
	 */
	@Override
	public TransportadorResponse fetchTransportadorsByRequest(TransportadorInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().fetchTransportadorsByRequest(request);
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
	 * Delegates call to {@link ITransportadorBAC}
	 *
	 * @param request a TransportadorRequest
	 * @return TransportadorResponse
	 */
	@Override
	public TransportadorResponse refreshTransportadors(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().refreshTransportadors(request);
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
	 * Delegates call to {@link ITransportadorBAC}
	 *
	 * @param request a TransportadorRequest
	 * @return TransportadorResponse
	 */
	@Override
	public TransportadorResponse fetchAllTransportadors(FetchAllRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().fetchAllTransportadors(new Transportador());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CONVENIO ####======================================

	/**
	 * Delegates call to {@link IConvenioBAC}
	 *
	 * @param request a ConvenioRequest
	 * @return ConvenioResponse
	 */
	@Override
	public ConvenioResponse insertConvenio(ConvenioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().insertConvenio(request);
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
	 * Delegates call to {@link IConvenioBAC}
	 *
	 * @param request a ConvenioRequest
	 * @return ConvenioResponse
	 */
	@Override
	public ConvenioResponse updateConvenio(ConvenioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().updateConvenio(request);
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
	 * Delegates call to {@link IConvenioBAC}
	 *
	 * @param request a ConvenioRequest
	 * @return ConvenioResponse
	 */
	@Override
	public ConvenioResponse deleteConvenio(ConvenioMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().deleteConvenio(request);
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
	 * Delegates call to {@link IConvenioBAC}
	 *
	 * @param request a ConvenioRequest
	 * @return ConvenioResponse
	 */
	@Override
	public ConvenioResponse fetchConvenioById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().fetchConvenioById(request);
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
	 * Delegates call to {@link IConvenioBAC}
	 *
	 * @param request a ConvenioRequest
	 * @return ConvenioResponse
	 */
	@Override
	public ConvenioResponse fetchConveniosByRequest(ConvenioInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().fetchConveniosByRequest(request);
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
	 * Delegates call to {@link IConvenioBAC}
	 *
	 * @param request a ConvenioRequest
	 * @return ConvenioResponse
	 */
	@Override
	public ConvenioResponse refreshConvenios(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().refreshConvenios(request);
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
	 * Delegates call to {@link IConvenioBAC}
	 *
	 * @param request a ConvenioRequest
	 * @return ConvenioResponse
	 */
	@Override
	public ConvenioResponse fetchAllConvenios(FetchAllRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().fetchAllConvenios(new Convenio());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### CIDADE ####======================================

	/**
	 * Delegates call to {@link ICidadeBAC}
	 *
	 * @param request a CidadeRequest
	 * @return CidadeResponse
	 */
	@Override
	public CidadeResponse insertCidade(CidadeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CidadeResponse response = new CidadeResponse();
		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().insertCidade(request);
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
	 * Delegates call to {@link ICidadeBAC}
	 *
	 * @param request a CidadeRequest
	 * @return CidadeResponse
	 */
	@Override
	public CidadeResponse updateCidade(CidadeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CidadeResponse response = new CidadeResponse();
		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().updateCidade(request);
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
	 * Delegates call to {@link ICidadeBAC}
	 *
	 * @param request a CidadeRequest
	 * @return CidadeResponse
	 */
	@Override
	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CidadeResponse response = new CidadeResponse();
		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().deleteCidade(request);
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
	 * Delegates call to {@link ICidadeBAC}
	 *
	 * @param request a CidadeRequest
	 * @return CidadeResponse
	 */
	@Override
	public CidadeResponse fetchCidadeById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CidadeResponse response = new CidadeResponse();
		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().fetchCidadeById(request);
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
	 * Delegates call to {@link ICidadeBAC}
	 *
	 * @param request a CidadeRequest
	 * @return CidadeResponse
	 */
	@Override
	public CidadeResponse fetchCidadesByRequest(CidadeInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CidadeResponse response = new CidadeResponse();
		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().fetchCidadesByRequest(request);
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
	 * Delegates call to {@link ICidadeBAC}
	 *
	 * @param request a CidadeRequest
	 * @return CidadeResponse
	 */
	@Override
	public CidadeResponse refreshCidades(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CidadeResponse response = new CidadeResponse();
		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().refreshCidades(request);
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
	 * Delegates call to {@link ICidadeBAC}
	 *
	 * @param request a CidadeRequest
	 * @return CidadeResponse
	 */
	@Override
	public CidadeResponse fetchAllCidades(FetchAllRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().fetchAllCidades(new Cidade());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### ESTADO ####======================================

	/**
	 * Delegates call to {@link IEstadoBAC}
	 *
	 * @param request a EstadoRequest
	 * @return EstadoResponse
	 */
	@Override
	public EstadoResponse insertEstado(EstadoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EstadoResponse response = new EstadoResponse();
		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().insertEstado(request);
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
	 * Delegates call to {@link IEstadoBAC}
	 *
	 * @param request a EstadoRequest
	 * @return EstadoResponse
	 */
	@Override
	public EstadoResponse updateEstado(EstadoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EstadoResponse response = new EstadoResponse();
		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().updateEstado(request);
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
	 * Delegates call to {@link IEstadoBAC}
	 *
	 * @param request a EstadoRequest
	 * @return EstadoResponse
	 */
	@Override
	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EstadoResponse response = new EstadoResponse();
		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().deleteEstado(request);
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
	 * Delegates call to {@link IEstadoBAC}
	 *
	 * @param request a EstadoRequest
	 * @return EstadoResponse
	 */
	@Override
	public EstadoResponse fetchEstadoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EstadoResponse response = new EstadoResponse();
		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().fetchEstadoById(request);
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
	 * Delegates call to {@link IEstadoBAC}
	 *
	 * @param request a EstadoRequest
	 * @return EstadoResponse
	 */
	@Override
	public EstadoResponse fetchEstadosByRequest(EstadoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EstadoResponse response = new EstadoResponse();
		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().fetchEstadosByRequest(request);
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
	 * Delegates call to {@link IEstadoBAC}
	 *
	 * @param request a EstadoRequest
	 * @return EstadoResponse
	 */
	@Override
	public EstadoResponse refreshEstados(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EstadoResponse response = new EstadoResponse();
		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().refreshEstados(request);
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
	 * Delegates call to {@link IEstadoBAC}
	 *
	 * @param request a EstadoRequest
	 * @return EstadoResponse
	 */
	@Override
	public EstadoResponse fetchAllEstados(FetchAllRequest request)
	{
		EstadoResponse response = new EstadoResponse();
		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().fetchAllEstados(new Estado());
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### TAREFA ####======================================

	/**
	 * Delegates call to {@link ITarefaBAC}
	 *
	 * @param request a TarefaRequest
	 * @return TarefaResponse
	 */
	@Override
	public TarefaResponse insertTarefa(TarefaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TarefaResponse response = new TarefaResponse();
		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().insertTarefa(request);
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
	 * Delegates call to {@link ITarefaBAC}
	 *
	 * @param request a TarefaRequest
	 * @return TarefaResponse
	 */
	@Override
	public TarefaResponse updateTarefa(TarefaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TarefaResponse response = new TarefaResponse();
		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().updateTarefa(request);
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
	 * Delegates call to {@link ITarefaBAC}
	 *
	 * @param request a TarefaRequest
	 * @return TarefaResponse
	 */
	@Override
	public TarefaResponse deleteTarefa(TarefaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TarefaResponse response = new TarefaResponse();
		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().deleteTarefa(request);
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
	 * Delegates call to {@link ITarefaBAC}
	 *
	 * @param request a TarefaRequest
	 * @return TarefaResponse
	 */
	@Override
	public TarefaResponse fetchTarefaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TarefaResponse response = new TarefaResponse();
		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().fetchTarefaById(request);
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
	 * Delegates call to {@link ITarefaBAC}
	 *
	 * @param request a TarefaRequest
	 * @return TarefaResponse
	 */
	@Override
	public TarefaResponse fetchTarefasByRequest(TarefaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TarefaResponse response = new TarefaResponse();
		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().fetchTarefasByRequest(request);
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
	 * Delegates call to {@link ITarefaBAC}
	 *
	 * @param request a TarefaRequest
	 * @return TarefaResponse
	 */
	@Override
	public TarefaResponse refreshTarefas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TarefaResponse response = new TarefaResponse();
		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().refreshTarefas(request);
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
	 * Delegates call to {@link ITarefaBAC}
	 *
	 * @param request a TarefaRequest
	 * @return TarefaResponse
	 */
	@Override
	public TarefaResponse fetchAllTarefas(FetchAllRequest request)
	{
		TarefaResponse response = new TarefaResponse();
		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().fetchAllTarefas(new Tarefa());
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
