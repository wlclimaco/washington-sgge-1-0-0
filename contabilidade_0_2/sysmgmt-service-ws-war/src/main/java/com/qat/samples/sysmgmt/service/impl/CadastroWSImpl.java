/** create by system gera-java version 1.0.0 28/04/2016 14:29 : 20*/

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
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class CadastroWSImpl implements com.qat.samples.sysmgmt.service.ICadastrosWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cadastroswsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.cadastroswsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CadastroWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CadastroWSImpl.class);

	/** The cadastros BAC. */
	private ICadastrosBAC cadastrosBAC; // injected by Spring through setter

	/**
	 * Spring Sets the cadastros BAC.
	 *
	 * @param cadastrosBAC the new cadastros BAC
	 */
	public void setCadastrosBAC(ICadastrosBAC cadastrosBAC)
	{
		this.cadastrosBAC = cadastrosBAC;
	}

	/**
	 * Gets the cadastros bac.
	 *
	 * @return the cadastros bac
	 */
	public ICadastrosBAC getCadastrosBAC()
	{
		return cadastrosBAC;
	}


//===================================### CLIENTE ####======================================
	@Override
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();

		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().insertCliente(request);
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
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();

		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().updateCliente(request);
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
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();

		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().deleteCliente(request);
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
	public ClienteResponse refreshClientes(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ClienteResponse response = new ClienteResponse();

		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().refreshClientes(request);
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
	public ClienteResponse fetchAllClientes(FetchAllRequest request)
	{
		ClienteResponse response = new ClienteResponse();

		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().fetchAllClientes(new Cliente());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IClienteWS#fetchClienteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		ClienteResponse response = new ClienteResponse();

		try
		{
			InternalResultsResponse<Cliente> internalResponse = new InternalResultsResponse<Cliente>();

			internalResponse = getCadastrosBAC().fetchClienteById(request);
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
	public ClienteResponse fetchClientesByRequest(ClienteInquiryRequest request)
	{
		ClienteResponse response = new ClienteResponse();

		try
		{
			InternalResultsResponse<Cliente> internalResponse = getCadastrosBAC().fetchClientesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### FORNECEDOR ####======================================
	@Override
	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();

		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().insertFornecedor(request);
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
	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();

		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().updateFornecedor(request);
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
	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();

		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().deleteFornecedor(request);
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
	public FornecedorResponse refreshFornecedors(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		FornecedorResponse response = new FornecedorResponse();

		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().refreshFornecedors(request);
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
	public FornecedorResponse fetchAllFornecedors(FetchAllRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();

		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().fetchAllFornecedors(new Fornecedor());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IFornecedorWS#fetchFornecedorById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();

		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = new InternalResultsResponse<Fornecedor>();

			internalResponse = getCadastrosBAC().fetchFornecedorById(request);
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
	public FornecedorResponse fetchFornecedorsByRequest(FornecedorInquiryRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();

		try
		{
			InternalResultsResponse<Fornecedor> internalResponse = getCadastrosBAC().fetchFornecedorsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### TRANSPORTADOR ####======================================
	@Override
	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();

		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().insertTransportador(request);
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
	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();

		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().updateTransportador(request);
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
	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();

		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().deleteTransportador(request);
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
	public TransportadorResponse refreshTransportadors(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TransportadorResponse response = new TransportadorResponse();

		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().refreshTransportadors(request);
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
	public TransportadorResponse fetchAllTransportadors(FetchAllRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();

		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().fetchAllTransportadors(new Transportador());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ITransportadorWS#fetchTransportadorById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();

		try
		{
			InternalResultsResponse<Transportador> internalResponse = new InternalResultsResponse<Transportador>();

			internalResponse = getCadastrosBAC().fetchTransportadorById(request);
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
	public TransportadorResponse fetchTransportadorsByRequest(TransportadorInquiryRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();

		try
		{
			InternalResultsResponse<Transportador> internalResponse = getCadastrosBAC().fetchTransportadorsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CONVENIO ####======================================
	@Override
	public ConvenioResponse insertConvenio(ConvenioMaintenanceRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();

		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().insertConvenio(request);
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
	public ConvenioResponse updateConvenio(ConvenioMaintenanceRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();

		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().updateConvenio(request);
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
	public ConvenioResponse deleteConvenio(ConvenioMaintenanceRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();

		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().deleteConvenio(request);
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
	public ConvenioResponse refreshConvenios(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ConvenioResponse response = new ConvenioResponse();

		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().refreshConvenios(request);
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
	public ConvenioResponse fetchAllConvenios(FetchAllRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();

		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().fetchAllConvenios(new Convenio());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IConvenioWS#fetchConvenioById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ConvenioResponse fetchConvenioById(FetchByIdRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();

		try
		{
			InternalResultsResponse<Convenio> internalResponse = new InternalResultsResponse<Convenio>();

			internalResponse = getCadastrosBAC().fetchConvenioById(request);
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
	public ConvenioResponse fetchConveniosByRequest(ConvenioInquiryRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();

		try
		{
			InternalResultsResponse<Convenio> internalResponse = getCadastrosBAC().fetchConveniosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### CIDADE ####======================================
	@Override
	public CidadeResponse insertCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();

		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().insertCidade(request);
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
	public CidadeResponse updateCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();

		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().updateCidade(request);
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
	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();

		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().deleteCidade(request);
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
	public CidadeResponse refreshCidades(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		CidadeResponse response = new CidadeResponse();

		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().refreshCidades(request);
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
	public CidadeResponse fetchAllCidades(FetchAllRequest request)
	{
		CidadeResponse response = new CidadeResponse();

		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().fetchAllCidades(new Cidade());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ICidadeWS#fetchCidadeById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public CidadeResponse fetchCidadeById(FetchByIdRequest request)
	{
		CidadeResponse response = new CidadeResponse();

		try
		{
			InternalResultsResponse<Cidade> internalResponse = new InternalResultsResponse<Cidade>();

			internalResponse = getCadastrosBAC().fetchCidadeById(request);
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
	public CidadeResponse fetchCidadesByRequest(CidadeInquiryRequest request)
	{
		CidadeResponse response = new CidadeResponse();

		try
		{
			InternalResultsResponse<Cidade> internalResponse = getCadastrosBAC().fetchCidadesByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### ESTADO ####======================================
	@Override
	public EstadoResponse insertEstado(EstadoMaintenanceRequest request)
	{
		EstadoResponse response = new EstadoResponse();

		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().insertEstado(request);
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
	public EstadoResponse updateEstado(EstadoMaintenanceRequest request)
	{
		EstadoResponse response = new EstadoResponse();

		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().updateEstado(request);
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
	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request)
	{
		EstadoResponse response = new EstadoResponse();

		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().deleteEstado(request);
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
	public EstadoResponse refreshEstados(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		EstadoResponse response = new EstadoResponse();

		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().refreshEstados(request);
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
	public EstadoResponse fetchAllEstados(FetchAllRequest request)
	{
		EstadoResponse response = new EstadoResponse();

		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().fetchAllEstados(new Estado());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.IEstadoWS#fetchEstadoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public EstadoResponse fetchEstadoById(FetchByIdRequest request)
	{
		EstadoResponse response = new EstadoResponse();

		try
		{
			InternalResultsResponse<Estado> internalResponse = new InternalResultsResponse<Estado>();

			internalResponse = getCadastrosBAC().fetchEstadoById(request);
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
	public EstadoResponse fetchEstadosByRequest(EstadoInquiryRequest request)
	{
		EstadoResponse response = new EstadoResponse();

		try
		{
			InternalResultsResponse<Estado> internalResponse = getCadastrosBAC().fetchEstadosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### TAREFA ####======================================
	@Override
	public TarefaResponse insertTarefa(TarefaMaintenanceRequest request)
	{
		TarefaResponse response = new TarefaResponse();

		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().insertTarefa(request);
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
	public TarefaResponse updateTarefa(TarefaMaintenanceRequest request)
	{
		TarefaResponse response = new TarefaResponse();

		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().updateTarefa(request);
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
	public TarefaResponse deleteTarefa(TarefaMaintenanceRequest request)
	{
		TarefaResponse response = new TarefaResponse();

		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().deleteTarefa(request);
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
	public TarefaResponse refreshTarefas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		TarefaResponse response = new TarefaResponse();

		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().refreshTarefas(request);
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
	public TarefaResponse fetchAllTarefas(FetchAllRequest request)
	{
		TarefaResponse response = new TarefaResponse();

		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().fetchAllTarefas(new Tarefa());
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
	 * @see com.qat.samples.sysmgmt.dapi.impl.ITarefaWS#fetchTarefaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public TarefaResponse fetchTarefaById(FetchByIdRequest request)
	{
		TarefaResponse response = new TarefaResponse();

		try
		{
			InternalResultsResponse<Tarefa> internalResponse = new InternalResultsResponse<Tarefa>();

			internalResponse = getCadastrosBAC().fetchTarefaById(request);
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
	public TarefaResponse fetchTarefasByRequest(TarefaInquiryRequest request)
	{
		TarefaResponse response = new TarefaResponse();

		try
		{
			InternalResultsResponse<Tarefa> internalResponse = getCadastrosBAC().fetchTarefasByRequest(request);
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
