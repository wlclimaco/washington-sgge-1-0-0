/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.ICadastrosBAC;
import com.qat.samples.sysmgmt.model.Cadastros;
import com.qat.samples.sysmgmt.model.response.CadastrosResponse;
import com.qat.samples.sysmgmt.service.ICadastrosWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * CadastrosWS used to provide WS interface. Delegates all calls to the ICadastrosBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class CadastrosWSImpl implements ICadastrosWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cadastrosjmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.cadastrosjmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CadastrosWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CadastrosWSImpl.class);
	private ICadastrosBAC cadastrosBAC;
	/**
	 * @return the cadastrosBAC which is expected to provide the implementation.
	 */
	public ICadastrosBAC getCadastrosBAC()
	{	
		return cadastrosBAC;
	}

//===================================### CLIENTE ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IClienteBAC}.
	 *
	 * @param clienteBAC the clienteBAC to set.
	 */
	public void setClienteBAC(IClienteBAC clienteBAC)
	{
		this.clienteBAC = clienteBAC;
	}
	
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
			InternalResultsResponse<Cliente> internalResponse = getClienteBAC().insertCliente(request);
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
			InternalResultsResponse<Cliente> internalResponse = getClienteBAC().updateCliente(request);
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
			InternalResultsResponse<Cliente> internalResponse = getClienteBAC().deleteCliente(request);
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
			InternalResultsResponse<Cliente> internalResponse = getClienteBAC().fetchClienteById(request);
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
			InternalResultsResponse<Cliente> internalResponse = getClienteBAC().fetchClientesByRequest(request);
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
			InternalResultsResponse<Cliente> internalResponse = getClienteBAC().refreshClientes(request);
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
			InternalResultsResponse<Cliente> internalResponse = getClienteBAC().fetchAllClientes();
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
	 * Spring injection uses this method to inject an implementation of {@link IFornecedorBAC}.
	 *
	 * @param fornecedorBAC the fornecedorBAC to set.
	 */
	public void setFornecedorBAC(IFornecedorBAC fornecedorBAC)
	{
		this.fornecedorBAC = fornecedorBAC;
	}
	
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
			InternalResultsResponse<Fornecedor> internalResponse = getFornecedorBAC().insertFornecedor(request);
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
			InternalResultsResponse<Fornecedor> internalResponse = getFornecedorBAC().updateFornecedor(request);
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
			InternalResultsResponse<Fornecedor> internalResponse = getFornecedorBAC().deleteFornecedor(request);
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
			InternalResultsResponse<Fornecedor> internalResponse = getFornecedorBAC().fetchFornecedorById(request);
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
			InternalResultsResponse<Fornecedor> internalResponse = getFornecedorBAC().fetchFornecedorsByRequest(request);
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
			InternalResultsResponse<Fornecedor> internalResponse = getFornecedorBAC().refreshFornecedors(request);
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
			InternalResultsResponse<Fornecedor> internalResponse = getFornecedorBAC().fetchAllFornecedors();
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
	 * Spring injection uses this method to inject an implementation of {@link ITransportadorBAC}.
	 *
	 * @param transportadorBAC the transportadorBAC to set.
	 */
	public void setTransportadorBAC(ITransportadorBAC transportadorBAC)
	{
		this.transportadorBAC = transportadorBAC;
	}
	
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
			InternalResultsResponse<Transportador> internalResponse = getTransportadorBAC().insertTransportador(request);
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
			InternalResultsResponse<Transportador> internalResponse = getTransportadorBAC().updateTransportador(request);
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
			InternalResultsResponse<Transportador> internalResponse = getTransportadorBAC().deleteTransportador(request);
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
			InternalResultsResponse<Transportador> internalResponse = getTransportadorBAC().fetchTransportadorById(request);
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
			InternalResultsResponse<Transportador> internalResponse = getTransportadorBAC().fetchTransportadorsByRequest(request);
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
			InternalResultsResponse<Transportador> internalResponse = getTransportadorBAC().refreshTransportadors(request);
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
			InternalResultsResponse<Transportador> internalResponse = getTransportadorBAC().fetchAllTransportadors();
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
	 * Spring injection uses this method to inject an implementation of {@link IConvenioBAC}.
	 *
	 * @param convenioBAC the convenioBAC to set.
	 */
	public void setConvenioBAC(IConvenioBAC convenioBAC)
	{
		this.convenioBAC = convenioBAC;
	}
	
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
			InternalResultsResponse<Convenio> internalResponse = getConvenioBAC().insertConvenio(request);
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
			InternalResultsResponse<Convenio> internalResponse = getConvenioBAC().updateConvenio(request);
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
			InternalResultsResponse<Convenio> internalResponse = getConvenioBAC().deleteConvenio(request);
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
			InternalResultsResponse<Convenio> internalResponse = getConvenioBAC().fetchConvenioById(request);
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
			InternalResultsResponse<Convenio> internalResponse = getConvenioBAC().fetchConveniosByRequest(request);
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
			InternalResultsResponse<Convenio> internalResponse = getConvenioBAC().refreshConvenios(request);
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
			InternalResultsResponse<Convenio> internalResponse = getConvenioBAC().fetchAllConvenios();
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
	 * Spring injection uses this method to inject an implementation of {@link ICidadeBAC}.
	 *
	 * @param cidadeBAC the cidadeBAC to set.
	 */
	public void setCidadeBAC(ICidadeBAC cidadeBAC)
	{
		this.cidadeBAC = cidadeBAC;
	}
	
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
			InternalResultsResponse<Cidade> internalResponse = getCidadeBAC().insertCidade(request);
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
			InternalResultsResponse<Cidade> internalResponse = getCidadeBAC().updateCidade(request);
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
			InternalResultsResponse<Cidade> internalResponse = getCidadeBAC().deleteCidade(request);
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
			InternalResultsResponse<Cidade> internalResponse = getCidadeBAC().fetchCidadeById(request);
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
			InternalResultsResponse<Cidade> internalResponse = getCidadeBAC().fetchCidadesByRequest(request);
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
			InternalResultsResponse<Cidade> internalResponse = getCidadeBAC().refreshCidades(request);
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
			InternalResultsResponse<Cidade> internalResponse = getCidadeBAC().fetchAllCidades();
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
	 * Spring injection uses this method to inject an implementation of {@link IEstadoBAC}.
	 *
	 * @param estadoBAC the estadoBAC to set.
	 */
	public void setEstadoBAC(IEstadoBAC estadoBAC)
	{
		this.estadoBAC = estadoBAC;
	}
	
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
			InternalResultsResponse<Estado> internalResponse = getEstadoBAC().insertEstado(request);
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
			InternalResultsResponse<Estado> internalResponse = getEstadoBAC().updateEstado(request);
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
			InternalResultsResponse<Estado> internalResponse = getEstadoBAC().deleteEstado(request);
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
			InternalResultsResponse<Estado> internalResponse = getEstadoBAC().fetchEstadoById(request);
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
			InternalResultsResponse<Estado> internalResponse = getEstadoBAC().fetchEstadosByRequest(request);
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
			InternalResultsResponse<Estado> internalResponse = getEstadoBAC().refreshEstados(request);
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
			InternalResultsResponse<Estado> internalResponse = getEstadoBAC().fetchAllEstados();
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
	 * Spring injection uses this method to inject an implementation of {@link ITarefaBAC}.
	 *
	 * @param tarefaBAC the tarefaBAC to set.
	 */
	public void setTarefaBAC(ITarefaBAC tarefaBAC)
	{
		this.tarefaBAC = tarefaBAC;
	}
	
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
			InternalResultsResponse<Tarefa> internalResponse = getTarefaBAC().insertTarefa(request);
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
			InternalResultsResponse<Tarefa> internalResponse = getTarefaBAC().updateTarefa(request);
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
			InternalResultsResponse<Tarefa> internalResponse = getTarefaBAC().deleteTarefa(request);
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
			InternalResultsResponse<Tarefa> internalResponse = getTarefaBAC().fetchTarefaById(request);
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
			InternalResultsResponse<Tarefa> internalResponse = getTarefaBAC().fetchTarefasByRequest(request);
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
			InternalResultsResponse<Tarefa> internalResponse = getTarefaBAC().refreshTarefas(request);
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
			InternalResultsResponse<Tarefa> internalResponse = getTarefaBAC().fetchAllTarefas();
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
