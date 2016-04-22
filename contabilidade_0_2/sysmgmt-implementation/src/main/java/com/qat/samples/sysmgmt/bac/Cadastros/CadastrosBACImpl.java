package com.qat.samples.sysmgmt.bac.Cadastros;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
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
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.ConvenioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaMaintenanceRequest;

/**
 * Standards based implementation of a BAC for Cadastros leveraging the injected ICadastrosBAR.
 */
@Component
public class CadastrosBACImpl implements ICadastrosBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_CADASTROS_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_CADASTROS_BAC_EXCEPTION_MSG = "sysmgmt.base.Cadastrosbacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CadastrosBACImpl.class);

	/** The Cadastros BAR. */
	private ICadastrosBAR cadastrosBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Cadastros BAR.
	 *
	 * @param CadastrosBAR the new Cadastros BAR
	 */
	public void setCadastrosBAR(ICadastrosBAR cadastrosBAR)
	{
		this.cadastrosBAR = cadastrosBAR;
	}

	/**
	 * Gets the Cadastros BAR.
	 *
	 * @return the Cadastros BAR
	 */
	public ICadastrosBAR getCadastrosBAR()
	{
		return cadastrosBAR;
	}

	/**
	 * Gets the validation controller
	 *
	 * @return ValidationController
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation Controller
	 *
	 * @param validationController
	 */
	public void setValidationController(ValidationController validationController)
	{
		this.validationController = validationController;
	}

//===================================### CLIENTE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cliente> insertCliente(ClienteMaintenanceRequest request)
{
	InternalResultsResponse<Cliente> response =
			processCliente(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClienteBAC#updateCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cliente> updateCliente(ClienteMaintenanceRequest request)
{
	InternalResultsResponse<Cliente> response =
			processCliente(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClienteBAC#deleteCliente(com.qat.samples.sysmgmt.model.request.ClienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cliente> deleteCliente(ClienteMaintenanceRequest request)
{
	InternalResultsResponse<Cliente> response =
			processCliente(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClienteBAC#refreshClientes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Cliente> refreshClientes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCadastrosBAR().deleteAllClientes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCadastrosBAR().insertCliente(new Cliente(i, "ClienteDesc" + i));
	}

	// Call maintain to see if we need to return the cliente list and if so whether it should be paged or not
	return maintainReturnListCliente(request.getReturnList(), request.getReturnListPaged(),new Cliente());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClienteBAC#fetchAllClientes(Cliente cliente)
 */
@Override
public InternalResultsResponse<Cliente> fetchAllClientes(Cliente cliente)
{
	InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
	response.getResultsList().addAll(getCadastrosBAR().fetchAllClientes(cliente).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClienteBAC#fetchClienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request)
{
	InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCadastrosBAR().fetchClienteById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClienteBAC#fetchClientesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cliente> fetchClientesByRequest(ClienteInquiryRequest request)
{
	return getCadastrosBAR().fetchClientesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the cliente response
 */
private InternalResultsResponse<Cliente> processCliente(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ClienteMaintenanceRequest request)
		{
	InternalResultsResponse<Cliente> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Cliente.class.getSimpleName(), request.getCliente(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Cliente>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceCliente(request.getCliente(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Cliente>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CADASTROS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the cliente list and if so whether it should be paged or
		// not
		response = maintainReturnListCliente(request.getReturnList(), request.getReturnListPaged(),new Cliente());

		return response;
			}

	/**
	 * Do persistenceCliente.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCliente(Cliente cliente, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastrosBAR().insertCliente(cliente);

			case UPDATE:
				return getCadastrosBAR().updateCliente(cliente);

			case DELETE:
				return getCadastrosBAR().deleteClienteById(cliente);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Cliente> maintainReturnListCliente(Boolean listIndicator, Boolean pageListIndicator,Cliente cliente)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ClienteInquiryRequest request = new ClienteInquiryRequest();
				request.setPreQueryCount(true);
				return fetchClientesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllClientes(cliente);
			}
		}
		else
		{
			return new InternalResultsResponse<Cliente>();
		}
	}

//===================================### FORNECEDOR ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertFornecedor(com.qat.samples.sysmgmt.model.request.FornecedorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Fornecedor> insertFornecedor(FornecedorMaintenanceRequest request)
{
	InternalResultsResponse<Fornecedor> response =
			processFornecedor(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFornecedorBAC#updateFornecedor(com.qat.samples.sysmgmt.model.request.FornecedorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Fornecedor> updateFornecedor(FornecedorMaintenanceRequest request)
{
	InternalResultsResponse<Fornecedor> response =
			processFornecedor(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFornecedorBAC#deleteFornecedor(com.qat.samples.sysmgmt.model.request.FornecedorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Fornecedor> deleteFornecedor(FornecedorMaintenanceRequest request)
{
	InternalResultsResponse<Fornecedor> response =
			processFornecedor(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFornecedorBAC#refreshFornecedors(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Fornecedor> refreshFornecedors(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCadastrosBAR().deleteAllFornecedors();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCadastrosBAR().insertFornecedor(new Fornecedor(i, "FornecedorDesc" + i));
	}

	// Call maintain to see if we need to return the fornecedor list and if so whether it should be paged or not
	return maintainReturnListFornecedor(request.getReturnList(), request.getReturnListPaged(),new Fornecedor());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFornecedorBAC#fetchAllFornecedors(Fornecedor fornecedor)
 */
@Override
public InternalResultsResponse<Fornecedor> fetchAllFornecedors(Fornecedor fornecedor)
{
	InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
	response.getResultsList().addAll(getCadastrosBAR().fetchAllFornecedors(fornecedor).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFornecedorBAC#fetchFornecedorById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request)
{
	InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCadastrosBAR().fetchFornecedorById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFornecedorBAC#fetchFornecedorsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Fornecedor> fetchFornecedorsByRequest(FornecedorInquiryRequest request)
{
	return getCadastrosBAR().fetchFornecedorsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the fornecedor response
 */
private InternalResultsResponse<Fornecedor> processFornecedor(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		FornecedorMaintenanceRequest request)
		{
	InternalResultsResponse<Fornecedor> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Fornecedor.class.getSimpleName(), request.getFornecedor(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Fornecedor>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceFornecedor(request.getFornecedor(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Fornecedor>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CADASTROS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the fornecedor list and if so whether it should be paged or
		// not
		response = maintainReturnListFornecedor(request.getReturnList(), request.getReturnListPaged(),new Fornecedor());

		return response;
			}

	/**
	 * Do persistenceFornecedor.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceFornecedor(Fornecedor fornecedor, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastrosBAR().insertFornecedor(fornecedor);

			case UPDATE:
				return getCadastrosBAR().updateFornecedor(fornecedor);

			case DELETE:
				return getCadastrosBAR().deleteFornecedorById(fornecedor);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Fornecedor> maintainReturnListFornecedor(Boolean listIndicator, Boolean pageListIndicator,Fornecedor fornecedor)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				FornecedorInquiryRequest request = new FornecedorInquiryRequest();
				request.setPreQueryCount(true);
				return fetchFornecedorsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllFornecedors(fornecedor);
			}
		}
		else
		{
			return new InternalResultsResponse<Fornecedor>();
		}
	}

//===================================### TRANSPORTADOR ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertTransportador(com.qat.samples.sysmgmt.model.request.TransportadorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Transportador> insertTransportador(TransportadorMaintenanceRequest request)
{
	InternalResultsResponse<Transportador> response =
			processTransportador(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITransportadorBAC#updateTransportador(com.qat.samples.sysmgmt.model.request.TransportadorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Transportador> updateTransportador(TransportadorMaintenanceRequest request)
{
	InternalResultsResponse<Transportador> response =
			processTransportador(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITransportadorBAC#deleteTransportador(com.qat.samples.sysmgmt.model.request.TransportadorMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Transportador> deleteTransportador(TransportadorMaintenanceRequest request)
{
	InternalResultsResponse<Transportador> response =
			processTransportador(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITransportadorBAC#refreshTransportadors(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Transportador> refreshTransportadors(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCadastrosBAR().deleteAllTransportadors();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCadastrosBAR().insertTransportador(new Transportador(i, "TransportadorDesc" + i));
	}

	// Call maintain to see if we need to return the transportador list and if so whether it should be paged or not
	return maintainReturnListTransportador(request.getReturnList(), request.getReturnListPaged(),new Transportador());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITransportadorBAC#fetchAllTransportadors(Transportador transportador)
 */
@Override
public InternalResultsResponse<Transportador> fetchAllTransportadors(Transportador transportador)
{
	InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
	response.getResultsList().addAll(getCadastrosBAR().fetchAllTransportadors(transportador).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITransportadorBAC#fetchTransportadorById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request)
{
	InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCadastrosBAR().fetchTransportadorById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITransportadorBAC#fetchTransportadorsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Transportador> fetchTransportadorsByRequest(TransportadorInquiryRequest request)
{
	return getCadastrosBAR().fetchTransportadorsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the transportador response
 */
private InternalResultsResponse<Transportador> processTransportador(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		TransportadorMaintenanceRequest request)
		{
	InternalResultsResponse<Transportador> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Transportador.class.getSimpleName(), request.getTransportador(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Transportador>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceTransportador(request.getTransportador(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Transportador>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CADASTROS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the transportador list and if so whether it should be paged or
		// not
		response = maintainReturnListTransportador(request.getReturnList(), request.getReturnListPaged(),new Transportador());

		return response;
			}

	/**
	 * Do persistenceTransportador.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceTransportador(Transportador transportador, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastrosBAR().insertTransportador(transportador);

			case UPDATE:
				return getCadastrosBAR().updateTransportador(transportador);

			case DELETE:
				return getCadastrosBAR().deleteTransportadorById(transportador);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Transportador> maintainReturnListTransportador(Boolean listIndicator, Boolean pageListIndicator,Transportador transportador)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				TransportadorInquiryRequest request = new TransportadorInquiryRequest();
				request.setPreQueryCount(true);
				return fetchTransportadorsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllTransportadors(transportador);
			}
		}
		else
		{
			return new InternalResultsResponse<Transportador>();
		}
	}

//===================================### CONVENIO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertConvenio(com.qat.samples.sysmgmt.model.request.ConvenioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Convenio> insertConvenio(ConvenioMaintenanceRequest request)
{
	InternalResultsResponse<Convenio> response =
			processConvenio(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConvenioBAC#updateConvenio(com.qat.samples.sysmgmt.model.request.ConvenioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Convenio> updateConvenio(ConvenioMaintenanceRequest request)
{
	InternalResultsResponse<Convenio> response =
			processConvenio(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConvenioBAC#deleteConvenio(com.qat.samples.sysmgmt.model.request.ConvenioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Convenio> deleteConvenio(ConvenioMaintenanceRequest request)
{
	InternalResultsResponse<Convenio> response =
			processConvenio(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConvenioBAC#refreshConvenios(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Convenio> refreshConvenios(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCadastrosBAR().deleteAllConvenios();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCadastrosBAR().insertConvenio(new Convenio(i, "ConvenioDesc" + i));
	}

	// Call maintain to see if we need to return the convenio list and if so whether it should be paged or not
	return maintainReturnListConvenio(request.getReturnList(), request.getReturnListPaged(),new Convenio());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConvenioBAC#fetchAllConvenios(Convenio convenio)
 */
@Override
public InternalResultsResponse<Convenio> fetchAllConvenios(Convenio convenio)
{
	InternalResultsResponse<Convenio> response = new InternalResultsResponse<Convenio>();
	response.getResultsList().addAll(getCadastrosBAR().fetchAllConvenios(convenio).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IConvenioBAC#fetchConvenioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Convenio> fetchConvenioById(FetchByIdRequest request)
{
	InternalResultsResponse<Convenio> response = new InternalResultsResponse<Convenio>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCadastrosBAR().fetchConvenioById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IConvenioBAC#fetchConveniosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Convenio> fetchConveniosByRequest(ConvenioInquiryRequest request)
{
	return getCadastrosBAR().fetchConveniosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the convenio response
 */
private InternalResultsResponse<Convenio> processConvenio(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ConvenioMaintenanceRequest request)
		{
	InternalResultsResponse<Convenio> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Convenio.class.getSimpleName(), request.getConvenio(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Convenio>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceConvenio(request.getConvenio(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Convenio>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CADASTROS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the convenio list and if so whether it should be paged or
		// not
		response = maintainReturnListConvenio(request.getReturnList(), request.getReturnListPaged(),new Convenio());

		return response;
			}

	/**
	 * Do persistenceConvenio.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceConvenio(Convenio convenio, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastrosBAR().insertConvenio(convenio);

			case UPDATE:
				return getCadastrosBAR().updateConvenio(convenio);

			case DELETE:
				return getCadastrosBAR().deleteConvenioById(convenio);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Convenio> maintainReturnListConvenio(Boolean listIndicator, Boolean pageListIndicator,Convenio convenio)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ConvenioInquiryRequest request = new ConvenioInquiryRequest();
				request.setPreQueryCount(true);
				return fetchConveniosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllConvenios(convenio);
			}
		}
		else
		{
			return new InternalResultsResponse<Convenio>();
		}
	}

//===================================### CIDADE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCidade(com.qat.samples.sysmgmt.model.request.CidadeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request)
{
	InternalResultsResponse<Cidade> response =
			processCidade(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICidadeBAC#updateCidade(com.qat.samples.sysmgmt.model.request.CidadeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request)
{
	InternalResultsResponse<Cidade> response =
			processCidade(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICidadeBAC#deleteCidade(com.qat.samples.sysmgmt.model.request.CidadeMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Cidade> deleteCidade(CidadeMaintenanceRequest request)
{
	InternalResultsResponse<Cidade> response =
			processCidade(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICidadeBAC#refreshCidades(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Cidade> refreshCidades(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCadastrosBAR().deleteAllCidades();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCadastrosBAR().insertCidade(new Cidade(i, "CidadeDesc" + i));
	}

	// Call maintain to see if we need to return the cidade list and if so whether it should be paged or not
	return maintainReturnListCidade(request.getReturnList(), request.getReturnListPaged(),new Cidade());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICidadeBAC#fetchAllCidades(Cidade cidade)
 */
@Override
public InternalResultsResponse<Cidade> fetchAllCidades(Cidade cidade)
{
	InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
	response.getResultsList().addAll(getCadastrosBAR().fetchAllCidades(cidade).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICidadeBAC#fetchCidadeById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Cidade> fetchCidadeById(FetchByIdRequest request)
{
	InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCadastrosBAR().fetchCidadeById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICidadeBAC#fetchCidadesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cidade> fetchCidadesByRequest(CidadeInquiryRequest request)
{
	return getCadastrosBAR().fetchCidadesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the cidade response
 */
private InternalResultsResponse<Cidade> processCidade(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CidadeMaintenanceRequest request)
		{
	InternalResultsResponse<Cidade> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Cidade.class.getSimpleName(), request.getCidade(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Cidade>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceCidade(request.getCidade(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Cidade>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CADASTROS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the cidade list and if so whether it should be paged or
		// not
		response = maintainReturnListCidade(request.getReturnList(), request.getReturnListPaged(),new Cidade());

		return response;
			}

	/**
	 * Do persistenceCidade.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCidade(Cidade cidade, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastrosBAR().insertCidade(cidade);

			case UPDATE:
				return getCadastrosBAR().updateCidade(cidade);

			case DELETE:
				return getCadastrosBAR().deleteCidadeById(cidade);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Cidade> maintainReturnListCidade(Boolean listIndicator, Boolean pageListIndicator,Cidade cidade)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CidadeInquiryRequest request = new CidadeInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCidadesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCidades(cidade);
			}
		}
		else
		{
			return new InternalResultsResponse<Cidade>();
		}
	}

//===================================### ESTADO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertEstado(com.qat.samples.sysmgmt.model.request.EstadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Estado> insertEstado(EstadoMaintenanceRequest request)
{
	InternalResultsResponse<Estado> response =
			processEstado(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEstadoBAC#updateEstado(com.qat.samples.sysmgmt.model.request.EstadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Estado> updateEstado(EstadoMaintenanceRequest request)
{
	InternalResultsResponse<Estado> response =
			processEstado(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEstadoBAC#deleteEstado(com.qat.samples.sysmgmt.model.request.EstadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Estado> deleteEstado(EstadoMaintenanceRequest request)
{
	InternalResultsResponse<Estado> response =
			processEstado(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEstadoBAC#refreshEstados(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Estado> refreshEstados(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCadastrosBAR().deleteAllEstados();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCadastrosBAR().insertEstado(new Estado(i, "EstadoDesc" + i));
	}

	// Call maintain to see if we need to return the estado list and if so whether it should be paged or not
	return maintainReturnListEstado(request.getReturnList(), request.getReturnListPaged(),new Estado());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEstadoBAC#fetchAllEstados(Estado estado)
 */
@Override
public InternalResultsResponse<Estado> fetchAllEstados(Estado estado)
{
	InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();
	response.getResultsList().addAll(getCadastrosBAR().fetchAllEstados(estado).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEstadoBAC#fetchEstadoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Estado> fetchEstadoById(FetchByIdRequest request)
{
	InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCadastrosBAR().fetchEstadoById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEstadoBAC#fetchEstadosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Estado> fetchEstadosByRequest(EstadoInquiryRequest request)
{
	return getCadastrosBAR().fetchEstadosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the estado response
 */
private InternalResultsResponse<Estado> processEstado(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		EstadoMaintenanceRequest request)
		{
	InternalResultsResponse<Estado> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Estado.class.getSimpleName(), request.getEstado(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Estado>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceEstado(request.getEstado(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Estado>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CADASTROS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the estado list and if so whether it should be paged or
		// not
		response = maintainReturnListEstado(request.getReturnList(), request.getReturnListPaged(),new Estado());

		return response;
			}

	/**
	 * Do persistenceEstado.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceEstado(Estado estado, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastrosBAR().insertEstado(estado);

			case UPDATE:
				return getCadastrosBAR().updateEstado(estado);

			case DELETE:
				return getCadastrosBAR().deleteEstadoById(estado);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Estado> maintainReturnListEstado(Boolean listIndicator, Boolean pageListIndicator,Estado estado)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				EstadoInquiryRequest request = new EstadoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchEstadosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllEstados(estado);
			}
		}
		else
		{
			return new InternalResultsResponse<Estado>();
		}
	}

//===================================### TAREFA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertTarefa(com.qat.samples.sysmgmt.model.request.TarefaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Tarefa> insertTarefa(TarefaMaintenanceRequest request)
{
	InternalResultsResponse<Tarefa> response =
			processTarefa(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITarefaBAC#updateTarefa(com.qat.samples.sysmgmt.model.request.TarefaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Tarefa> updateTarefa(TarefaMaintenanceRequest request)
{
	InternalResultsResponse<Tarefa> response =
			processTarefa(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITarefaBAC#deleteTarefa(com.qat.samples.sysmgmt.model.request.TarefaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Tarefa> deleteTarefa(TarefaMaintenanceRequest request)
{
	InternalResultsResponse<Tarefa> response =
			processTarefa(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITarefaBAC#refreshTarefas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Tarefa> refreshTarefas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getCadastrosBAR().deleteAllTarefas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getCadastrosBAR().insertTarefa(new Tarefa(i, "TarefaDesc" + i));
	}

	// Call maintain to see if we need to return the tarefa list and if so whether it should be paged or not
	return maintainReturnListTarefa(request.getReturnList(), request.getReturnListPaged(),new Tarefa());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITarefaBAC#fetchAllTarefas(Tarefa tarefa)
 */
@Override
public InternalResultsResponse<Tarefa> fetchAllTarefas(Tarefa tarefa)
{
	InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
	response.getResultsList().addAll(getCadastrosBAR().fetchAllTarefas(tarefa).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ITarefaBAC#fetchTarefaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Tarefa> fetchTarefaById(FetchByIdRequest request)
{
	InternalResultsResponse<Tarefa> response = new InternalResultsResponse<Tarefa>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getCadastrosBAR().fetchTarefaById(request).getFirstResult());
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ITarefaBAC#fetchTarefasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Tarefa> fetchTarefasByRequest(TarefaInquiryRequest request)
{
	return getCadastrosBAR().fetchTarefasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the tarefa response
 */
private InternalResultsResponse<Tarefa> processTarefa(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		TarefaMaintenanceRequest request)
		{
	InternalResultsResponse<Tarefa> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Tarefa.class.getSimpleName(), request.getTarefa(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Tarefa>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceTarefa(request.getTarefa(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Tarefa>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_CADASTROS_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the tarefa list and if so whether it should be paged or
		// not
		response = maintainReturnListTarefa(request.getReturnList(), request.getReturnListPaged(),new Tarefa());

		return response;
			}

	/**
	 * Do persistenceTarefa.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceTarefa(Tarefa tarefa, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastrosBAR().insertTarefa(tarefa);

			case UPDATE:
				return getCadastrosBAR().updateTarefa(tarefa);

			case DELETE:
				return getCadastrosBAR().deleteTarefaById(tarefa);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Tarefa> maintainReturnListTarefa(Boolean listIndicator, Boolean pageListIndicator,Tarefa tarefa)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				TarefaInquiryRequest request = new TarefaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchTarefasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllTarefas(tarefa);
			}
		}
		else
		{
			return new InternalResultsResponse<Tarefa>();
		}
	}
}
