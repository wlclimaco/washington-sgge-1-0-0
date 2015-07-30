package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IPessoaBAC;
import com.prosperitasglobal.sendsolv.bai.IPessoaBAI;
import com.prosperitasglobal.sendsolv.model.Agencia;
import com.prosperitasglobal.sendsolv.model.Banco;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Cliente;
import com.prosperitasglobal.sendsolv.model.Conta;
import com.prosperitasglobal.sendsolv.model.Contador;
import com.prosperitasglobal.sendsolv.model.Contato;
import com.prosperitasglobal.sendsolv.model.Convenio;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.FormaPg;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.HorarioFunc;
import com.prosperitasglobal.sendsolv.model.Profissao;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.request.AgenciaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BancoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.ContaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContadorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.ContatoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ConvenioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EventoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FormaPgInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.HoraFuncInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProfissaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.AgenciaResponse;
import com.prosperitasglobal.sendsolv.model.response.BancoResponse;
import com.prosperitasglobal.sendsolv.model.response.BeneficiosResponse;
import com.prosperitasglobal.sendsolv.model.response.CidadeResponse;
import com.prosperitasglobal.sendsolv.model.response.ClienteResponse;
import com.prosperitasglobal.sendsolv.model.response.ContaResponse;
import com.prosperitasglobal.sendsolv.model.response.ContadorResponse;
import com.prosperitasglobal.sendsolv.model.response.ContatoResponse;
import com.prosperitasglobal.sendsolv.model.response.ConvenioResponse;
import com.prosperitasglobal.sendsolv.model.response.EstadoResponse;
import com.prosperitasglobal.sendsolv.model.response.EventoResponse;
import com.prosperitasglobal.sendsolv.model.response.FormaPgResponse;
import com.prosperitasglobal.sendsolv.model.response.FornecedorResponse;
import com.prosperitasglobal.sendsolv.model.response.HorarioFuncResponse;
import com.prosperitasglobal.sendsolv.model.response.ProfissaoResponse;
import com.prosperitasglobal.sendsolv.model.response.TransportadorResponse;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class PessoaBAIImpl.
 */
public class PessoaBAIImpl implements IPessoaBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = PessoaBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PessoaBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.empresavalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The empresa bac. */
	private IPessoaBAC empresaBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get empresa validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the empresa validation controller.
	 *
	 * @param empresaValidationController the new validation controller
	 */
	public void setValidationController(ValidationController empresaValidationController)
	{
		validationController = empresaValidationController;
	}

	/**
	 * Spring Sets the empresa bac.
	 *
	 * @param empresaBAC the new empresa bac
	 */
	public void setPessoaBAC(IPessoaBAC empresaBAC)
	{
		this.empresaBAC = empresaBAC;
	}

	/**
	 * Gets the empresa bac.
	 *
	 * @return the empresa bac
	 */
	public IPessoaBAC getPessoaBAC()
	{
		return empresaBAC;
	}

	/**
	 * Insert cliente.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	@Override
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			response = processCliente(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/** The Cliente response. */
	@Override
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			response = processCliente(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delete cliente.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	@Override
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			response = processCliente(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch cliente by id.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	@Override
	public ClienteResponse fetchClienteById(FetchByIdRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getPessoaBAC().fetchClienteById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch cliente by request.
	 *
	 * @param request the request
	 * @return the cliente response
	 */
	@Override
	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			fetchCliente(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Fetch cliente.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchCliente(ClienteInquiryRequest request, ClienteResponse response)
	{
		InternalResultsResponse<Cliente> internalResponse = new InternalResultsResponse<Cliente>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchClienteByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the empresa response
	 */
	private ClienteResponse processCliente(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			ClienteMaintenanceRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceCliente(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnCliente(response, internalResponse, null, true);
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private ClienteResponse handleReturnCliente(ClienteResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response,
				internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceCliente(ClienteMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAC().insertCliente(request);

			case UPDATE:
				return getPessoaBAC().updateCliente(request);

			case DELETE:
				return getPessoaBAC().deleteCliente(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// =========================== Fornercedor====================
	/**
	 * Insert fornecedor.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	@Override
	public FornecedorResponse insertFornecedor(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			response = processFornecedor(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Update fornecedor.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	@Override
	public FornecedorResponse updateFornecedor(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			response = processFornecedor(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delete fornecedor.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	@Override
	public FornecedorResponse deleteFornecedor(FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			response = processFornecedor(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch fornecedor by id.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	@Override
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getPessoaBAC().fetchFornecedorById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch fornecedor by request.
	 *
	 * @param request the request
	 * @return the fornecedor response
	 */
	@Override
	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		try
		{
			fetchFornecedor(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Fetch fornecedor.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchFornecedor(FornecedorInquiryRequest request, FornecedorResponse response)
	{
		InternalResultsResponse<Fornecedor> internalResponse = new InternalResultsResponse<Fornecedor>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchFornecedorByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the empresa response
	 */
	private FornecedorResponse processFornecedor(ValidationContextIndicator indicator,
			PersistanceActionEnum persistType,
			FornecedorMaintenanceRequest request)
	{
		FornecedorResponse response = new FornecedorResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceFornecedor(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnFornecedor(response, internalResponse, null, true);
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private FornecedorResponse handleReturnFornecedor(FornecedorResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response,
				internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceFornecedor(FornecedorMaintenanceRequest request,
			PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAC().insertFornecedor(request);

			case UPDATE:
				return getPessoaBAC().updateFornecedor(request);

			case DELETE:
				return getPessoaBAC().deleteFornecedor(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// =================================Transportador==========================
	/**
	 * Insert transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public TransportadorResponse insertTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			response = processTransportador(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Update transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public TransportadorResponse updateTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			response = processTransportador(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delete transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public TransportadorResponse deleteTransportador(TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			response = processTransportador(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch transportador by id.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getPessoaBAC().fetchTransportadorById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch transportador by request.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		try
		{
			fetchTransportador(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Fetch transportador.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchTransportador(TransportadorInquiryRequest request, TransportadorResponse response)
	{
		InternalResultsResponse<Transportador> internalResponse = new InternalResultsResponse<Transportador>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchTransportadorByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the empresa response
	 */
	private TransportadorResponse processTransportador(ValidationContextIndicator indicator,
			PersistanceActionEnum persistType,
			TransportadorMaintenanceRequest request)
	{
		TransportadorResponse response = new TransportadorResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceTransportador(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, null, true);
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private TransportadorResponse handleReturn(TransportadorResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response,
				internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceTransportador(TransportadorMaintenanceRequest request,
			PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAC().insertTransportador(request);

			case UPDATE:
				return getPessoaBAC().updateTransportador(request);

			case DELETE:
				return getPessoaBAC().deleteTransportador(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	@Override
	public ProfissaoResponse fetchProfissaoByRequest(ProfissaoInquiryRequest request)
	{
		ProfissaoResponse response = new ProfissaoResponse();
		try
		{
			fetchProfissao(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchProfissao(ProfissaoInquiryRequest request, ProfissaoResponse response)
	{
		InternalResultsResponse<Profissao> internalResponse = new InternalResultsResponse<Profissao>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchProfissaoByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public ConvenioResponse fetchConvenioByRequest(ConvenioInquiryRequest request)
	{
		ConvenioResponse response = new ConvenioResponse();
		try
		{
			fetchConvenio(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchConvenio(ConvenioInquiryRequest request, ConvenioResponse response)
	{
		InternalResultsResponse<Convenio> internalResponse = new InternalResultsResponse<Convenio>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchConvenioByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request)
	{
		ContatoResponse response = new ContatoResponse();
		try
		{
			fetchContato(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchContato(ContatoInquiryRequest request, ContatoResponse response)
	{
		InternalResultsResponse<Contato> internalResponse = new InternalResultsResponse<Contato>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchContatoByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public BancoResponse fetchBancoByRequest(BancoInquiryRequest request)
	{
		BancoResponse response = new BancoResponse();
		try
		{
			fetchBanco(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchBanco(BancoInquiryRequest request, BancoResponse response)
	{
		InternalResultsResponse<Banco> internalResponse = new InternalResultsResponse<Banco>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchBancoByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public FormaPgResponse fetchFormaPgByRequest(FormaPgInquiryRequest request)
	{
		FormaPgResponse response = new FormaPgResponse();
		try
		{
			fetchFormaPg(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchFormaPg(FormaPgInquiryRequest request, FormaPgResponse response)
	{
		InternalResultsResponse<FormaPg> internalResponse = new InternalResultsResponse<FormaPg>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchFormaPgByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request)
	{
		AgenciaResponse response = new AgenciaResponse();
		try
		{
			fetchAgencia(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchAgencia(AgenciaInquiryRequest request, AgenciaResponse response)
	{
		InternalResultsResponse<Agencia> internalResponse = new InternalResultsResponse<Agencia>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchAgenciaByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public ContaResponse fetchContaByRequest(ContaInquiryRequest request)
	{
		ContaResponse response = new ContaResponse();
		try
		{
			fetchConta(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchConta(ContaInquiryRequest request, ContaResponse response)
	{
		InternalResultsResponse<Conta> internalResponse = new InternalResultsResponse<Conta>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchContaByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		EstadoResponse response = new EstadoResponse();
		try
		{
			fetchEstado(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchEstado(EstadoInquiryRequest request, EstadoResponse response)
	{
		InternalResultsResponse<Estado> internalResponse = new InternalResultsResponse<Estado>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchEstadoByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public CidadeResponse fetchCidadeRequest(CidadeInquiryRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			fetchCidade(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchCidade(CidadeInquiryRequest request, CidadeResponse response)
	{
		InternalResultsResponse<Cidade> internalResponse = new InternalResultsResponse<Cidade>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchCidadeRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public EventoResponse fetchEventosRequest(EventoInquiryRequest request)
	{
		EventoResponse response = new EventoResponse();
		try
		{
			fetchEventos(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchEventos(EventoInquiryRequest request, EventoResponse response)
	{
		InternalResultsResponse<Eventos> internalResponse = new InternalResultsResponse<Eventos>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchEventosRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public BeneficiosResponse fetchBeneficiosRequest(BeneficiosInquiryRequest request)
	{
		BeneficiosResponse response = new BeneficiosResponse();
		try
		{
			fetchBeneficios(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchBeneficios(BeneficiosInquiryRequest request, BeneficiosResponse response)
	{
		InternalResultsResponse<Beneficios> internalResponse = new InternalResultsResponse<Beneficios>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchBeneficiosRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	@Override
	public HorarioFuncResponse fetchHorarioFuncsRequest(HoraFuncInquiryRequest request)
	{
		HorarioFuncResponse response = new HorarioFuncResponse();
		try
		{
			fetchHorarioFunc(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	private void fetchHorarioFunc(HoraFuncInquiryRequest request, HorarioFuncResponse response)
	{
		InternalResultsResponse<HorarioFunc> internalResponse = new InternalResultsResponse<HorarioFunc>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchHorarioFuncsRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	// =======Contador
	// =================================Contador==========================
	/**
	 * Insert transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public ContadorResponse insertContador(ContadorMaintenanceRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			response = processContador(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Update transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public ContadorResponse updateContador(ContadorMaintenanceRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			response = processContador(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Delete transportador.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public ContadorResponse deleteContador(ContadorMaintenanceRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			response = processContador(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch transportador by id.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public ContadorResponse fetchContadorById(FetchByIdRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getPessoaBAC().fetchContadorById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/**
	 * Fetch transportador by request.
	 *
	 * @param request the request
	 * @return the transportador response
	 */
	@Override
	public ContadorResponse fetchContadorByRequest(ContadorInquiryRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		try
		{
			fetchContador(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Fetch transportador.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchContador(ContadorInquiryRequest request, ContadorResponse response)
	{
		InternalResultsResponse<Contador> internalResponse = new InternalResultsResponse<Contador>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getPessoaBAC().fetchContadorByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the empresa response
	 */
	private ContadorResponse processContador(ValidationContextIndicator indicator,
			PersistanceActionEnum persistType,
			ContadorMaintenanceRequest request)
	{
		ContadorResponse response = new ContadorResponse();
		InternalResponse internalResponse = null;

		// Persist
		internalResponse = doPersistanceContador(request, persistType);

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, null, true);
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private ContadorResponse handleReturn(ContadorResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response,
				internalResponse, messages, copyOver);
		return response;
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceContador(ContadorMaintenanceRequest request,
			PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAC().insertContador(request);

			case UPDATE:
				return getPessoaBAC().updateContador(request);

			case DELETE:
				return getPessoaBAC().deleteContador(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}
}
