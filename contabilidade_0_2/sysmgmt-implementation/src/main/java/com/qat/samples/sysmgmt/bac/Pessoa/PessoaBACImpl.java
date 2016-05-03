/** create by system gera-java version 1.0.0 02/05/2016 21:44 : 15*/
package com.qat.samples.sysmgmt.bac.Pessoa;


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
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.bar.Pessoa.IPessoaBAR;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Pessoa leveraging the injected IPessoaBAR.
 */
@Component
public class PessoaBACImpl implements IPessoaBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_PESSOA_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_PESSOA_BAC_EXCEPTION_MSG = "sysmgmt.base.Pessoabacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PessoaBACImpl.class);

	/** The Pessoa BAR. */
	private IPessoaBAR pessoaBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Pessoa BAR.
	 *
	 * @param PessoaBAR the new Pessoa BAR
	 */
	public void setPessoaBAR(IPessoaBAR pessoaBAR)
	{
		this.pessoaBAR = pessoaBAR;
	}

	/**
	 * Gets the Pessoa BAR.
	 *
	 * @return the Pessoa BAR
	 */
	public IPessoaBAR getPessoaBAR()
	{
		return pessoaBAR;
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

//===================================### ADVOGADO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertAdvogado(com.qat.samples.sysmgmt.model.request.AdvogadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> insertAdvogado(AdvogadoMaintenanceRequest request)
{
	InternalResultsResponse<Advogado> response =
			processAdvogado(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvogadoBAC#updateAdvogado(com.qat.samples.sysmgmt.model.request.AdvogadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> updateAdvogado(AdvogadoMaintenanceRequest request)
{
	InternalResultsResponse<Advogado> response =
			processAdvogado(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvogadoBAC#deleteAdvogado(com.qat.samples.sysmgmt.model.request.AdvogadoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> deleteAdvogado(AdvogadoMaintenanceRequest request)
{
	InternalResultsResponse<Advogado> response =
			processAdvogado(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadoBAC#refreshAdvogados(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Advogado> refreshAdvogados(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getPessoaBAR().deleteAllAdvogados();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertAdvogado(new Advogado(i, "AdvogadoDesc" + i));
	}

	// Call maintain to see if we need to return the advogado list and if so whether it should be paged or not
	return maintainReturnListAdvogado(request.getReturnList(), request.getReturnListPaged(),new Advogado());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadoBAC#fetchAllAdvogados(Advogado advogado)
 */
@Override
public InternalResultsResponse<Advogado> fetchAllAdvogados(Advogado advogado)
{
	InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();
	response.getResultsList().addAll(getPessoaBAR().fetchAllAdvogados(advogado).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvogadoBAC#fetchAdvogadoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request)
{
	InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getPessoaBAR().fetchAdvogadoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvogadoBAC#fetchAdvogadosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(AdvogadoInquiryRequest request)
{
	return getPessoaBAR().fetchAdvogadosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the advogado response
 */
private InternalResultsResponse<Advogado> processAdvogado(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		AdvogadoMaintenanceRequest request)
		{
	InternalResultsResponse<Advogado> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Advogado.class.getSimpleName(), request.getAdvogado(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Advogado>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceAdvogado(request.getAdvogado(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Advogado>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the advogado list and if so whether it should be paged or
		// not
		response = maintainReturnListAdvogado(request.getReturnList(), request.getReturnListPaged(),new Advogado());

		return response;
			}

	/**
	 * Do persistenceAdvogado.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceAdvogado(Advogado advogado, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAR().insertAdvogado(advogado);

			case UPDATE:
				return getPessoaBAR().updateAdvogado(advogado);

			case DELETE:
				return getPessoaBAR().deleteAdvogadoById(advogado);
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
	private InternalResultsResponse<Advogado> maintainReturnListAdvogado(Boolean listIndicator, Boolean pageListIndicator,Advogado advogado)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				AdvogadoInquiryRequest request = new AdvogadoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchAdvogadosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllAdvogados(advogado);
			}
		}
		else
		{
			return new InternalResultsResponse<Advogado>();
		}
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
	getPessoaBAR().deleteAllClientes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertCliente(new Cliente(i, "ClienteDesc" + i));
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
	response.getResultsList().addAll(getPessoaBAR().fetchAllClientes(cliente).getResultsList());
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
		response.getResultsList().add(getPessoaBAR().fetchClienteById(request));
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
	return getPessoaBAR().fetchClientesByRequest(request);
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
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
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
				return getPessoaBAR().insertCliente(cliente);

			case UPDATE:
				return getPessoaBAR().updateCliente(cliente);

			case DELETE:
				return getPessoaBAR().deleteClienteById(cliente);
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
	getPessoaBAR().deleteAllFornecedors();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertFornecedor(new Fornecedor(i, "FornecedorDesc" + i));
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
	response.getResultsList().addAll(getPessoaBAR().fetchAllFornecedors(fornecedor).getResultsList());
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
		response.getResultsList().add(getPessoaBAR().fetchFornecedorById(request));
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
	return getPessoaBAR().fetchFornecedorsByRequest(request);
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
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
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
				return getPessoaBAR().insertFornecedor(fornecedor);

			case UPDATE:
				return getPessoaBAR().updateFornecedor(fornecedor);

			case DELETE:
				return getPessoaBAR().deleteFornecedorById(fornecedor);
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
	getPessoaBAR().deleteAllTransportadors();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertTransportador(new Transportador(i, "TransportadorDesc" + i));
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
	response.getResultsList().addAll(getPessoaBAR().fetchAllTransportadors(transportador).getResultsList());
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
		response.getResultsList().add(getPessoaBAR().fetchTransportadorById(request));
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
	return getPessoaBAR().fetchTransportadorsByRequest(request);
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
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
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
				return getPessoaBAR().insertTransportador(transportador);

			case UPDATE:
				return getPessoaBAR().updateTransportador(transportador);

			case DELETE:
				return getPessoaBAR().deleteTransportadorById(transportador);
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

//===================================### MEDICO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertMedico(com.qat.samples.sysmgmt.model.request.MedicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> insertMedico(MedicoMaintenanceRequest request)
{
	InternalResultsResponse<Medico> response =
			processMedico(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMedicoBAC#updateMedico(com.qat.samples.sysmgmt.model.request.MedicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> updateMedico(MedicoMaintenanceRequest request)
{
	InternalResultsResponse<Medico> response =
			processMedico(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMedicoBAC#deleteMedico(com.qat.samples.sysmgmt.model.request.MedicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> deleteMedico(MedicoMaintenanceRequest request)
{
	InternalResultsResponse<Medico> response =
			processMedico(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMedicoBAC#refreshMedicos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Medico> refreshMedicos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getPessoaBAR().deleteAllMedicos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertMedico(new Medico(i, "MedicoDesc" + i));
	}

	// Call maintain to see if we need to return the medico list and if so whether it should be paged or not
	return maintainReturnListMedico(request.getReturnList(), request.getReturnListPaged(),new Medico());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMedicoBAC#fetchAllMedicos(Medico medico)
 */
@Override
public InternalResultsResponse<Medico> fetchAllMedicos(Medico medico)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	response.getResultsList().addAll(getPessoaBAR().fetchAllMedicos(medico).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IMedicoBAC#fetchMedicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Medico> fetchMedicoById(FetchByIdRequest request)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getPessoaBAR().fetchMedicoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IMedicoBAC#fetchMedicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request)
{
	return getPessoaBAR().fetchMedicosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the medico response
 */
private InternalResultsResponse<Medico> processMedico(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		MedicoMaintenanceRequest request)
		{
	InternalResultsResponse<Medico> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Medico.class.getSimpleName(), request.getMedico(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Medico>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceMedico(request.getMedico(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Medico>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the medico list and if so whether it should be paged or
		// not
		response = maintainReturnListMedico(request.getReturnList(), request.getReturnListPaged(),new Medico());

		return response;
			}

	/**
	 * Do persistenceMedico.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceMedico(Medico medico, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAR().insertMedico(medico);

			case UPDATE:
				return getPessoaBAR().updateMedico(medico);

			case DELETE:
				return getPessoaBAR().deleteMedicoById(medico);
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
	private InternalResultsResponse<Medico> maintainReturnListMedico(Boolean listIndicator, Boolean pageListIndicator,Medico medico)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				MedicoInquiryRequest request = new MedicoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchMedicosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllMedicos(medico);
			}
		}
		else
		{
			return new InternalResultsResponse<Medico>();
		}
	}

//===================================### PACIENTE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertPaciente(com.qat.samples.sysmgmt.model.request.PacienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> insertPaciente(PacienteMaintenanceRequest request)
{
	InternalResultsResponse<Paciente> response =
			processPaciente(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPacienteBAC#updatePaciente(com.qat.samples.sysmgmt.model.request.PacienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> updatePaciente(PacienteMaintenanceRequest request)
{
	InternalResultsResponse<Paciente> response =
			processPaciente(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPacienteBAC#deletePaciente(com.qat.samples.sysmgmt.model.request.PacienteMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> deletePaciente(PacienteMaintenanceRequest request)
{
	InternalResultsResponse<Paciente> response =
			processPaciente(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPacienteBAC#refreshPacientes(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Paciente> refreshPacientes(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getPessoaBAR().deleteAllPacientes();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertPaciente(new Paciente(i, "PacienteDesc" + i));
	}

	// Call maintain to see if we need to return the paciente list and if so whether it should be paged or not
	return maintainReturnListPaciente(request.getReturnList(), request.getReturnListPaged(),new Paciente());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPacienteBAC#fetchAllPacientes(Paciente paciente)
 */
@Override
public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente paciente)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	response.getResultsList().addAll(getPessoaBAR().fetchAllPacientes(paciente).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IPacienteBAC#fetchPacienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Paciente> fetchPacienteById(FetchByIdRequest request)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getPessoaBAR().fetchPacienteById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IPacienteBAC#fetchPacientesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request)
{
	return getPessoaBAR().fetchPacientesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the paciente response
 */
private InternalResultsResponse<Paciente> processPaciente(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		PacienteMaintenanceRequest request)
		{
	InternalResultsResponse<Paciente> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Paciente.class.getSimpleName(), request.getPaciente(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Paciente>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistencePaciente(request.getPaciente(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Paciente>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the paciente list and if so whether it should be paged or
		// not
		response = maintainReturnListPaciente(request.getReturnList(), request.getReturnListPaged(),new Paciente());

		return response;
			}

	/**
	 * Do persistencePaciente.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistencePaciente(Paciente paciente, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAR().insertPaciente(paciente);

			case UPDATE:
				return getPessoaBAR().updatePaciente(paciente);

			case DELETE:
				return getPessoaBAR().deletePacienteById(paciente);
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
	private InternalResultsResponse<Paciente> maintainReturnListPaciente(Boolean listIndicator, Boolean pageListIndicator,Paciente paciente)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PacienteInquiryRequest request = new PacienteInquiryRequest();
				request.setPreQueryCount(true);
				return fetchPacientesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllPacientes(paciente);
			}
		}
		else
		{
			return new InternalResultsResponse<Paciente>();
		}
	}

//===================================### SINDICO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertSindico(com.qat.samples.sysmgmt.model.request.SindicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> insertSindico(SindicoMaintenanceRequest request)
{
	InternalResultsResponse<Sindico> response =
			processSindico(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISindicoBAC#updateSindico(com.qat.samples.sysmgmt.model.request.SindicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> updateSindico(SindicoMaintenanceRequest request)
{
	InternalResultsResponse<Sindico> response =
			processSindico(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISindicoBAC#deleteSindico(com.qat.samples.sysmgmt.model.request.SindicoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> deleteSindico(SindicoMaintenanceRequest request)
{
	InternalResultsResponse<Sindico> response =
			processSindico(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISindicoBAC#refreshSindicos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Sindico> refreshSindicos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getPessoaBAR().deleteAllSindicos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertSindico(new Sindico(i, "SindicoDesc" + i));
	}

	// Call maintain to see if we need to return the sindico list and if so whether it should be paged or not
	return maintainReturnListSindico(request.getReturnList(), request.getReturnListPaged(),new Sindico());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISindicoBAC#fetchAllSindicos(Sindico sindico)
 */
@Override
public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico sindico)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	response.getResultsList().addAll(getPessoaBAR().fetchAllSindicos(sindico).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ISindicoBAC#fetchSindicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Sindico> fetchSindicoById(FetchByIdRequest request)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getPessoaBAR().fetchSindicoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ISindicoBAC#fetchSindicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request)
{
	return getPessoaBAR().fetchSindicosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the sindico response
 */
private InternalResultsResponse<Sindico> processSindico(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		SindicoMaintenanceRequest request)
		{
	InternalResultsResponse<Sindico> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Sindico.class.getSimpleName(), request.getSindico(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Sindico>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceSindico(request.getSindico(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Sindico>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the sindico list and if so whether it should be paged or
		// not
		response = maintainReturnListSindico(request.getReturnList(), request.getReturnListPaged(),new Sindico());

		return response;
			}

	/**
	 * Do persistenceSindico.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceSindico(Sindico sindico, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAR().insertSindico(sindico);

			case UPDATE:
				return getPessoaBAR().updateSindico(sindico);

			case DELETE:
				return getPessoaBAR().deleteSindicoById(sindico);
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
	private InternalResultsResponse<Sindico> maintainReturnListSindico(Boolean listIndicator, Boolean pageListIndicator,Sindico sindico)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				SindicoInquiryRequest request = new SindicoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchSindicosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllSindicos(sindico);
			}
		}
		else
		{
			return new InternalResultsResponse<Sindico>();
		}
	}

//===================================### INQUILINO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertInquilino(com.qat.samples.sysmgmt.model.request.InquilinoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> insertInquilino(InquilinoMaintenanceRequest request)
{
	InternalResultsResponse<Inquilino> response =
			processInquilino(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInquilinoBAC#updateInquilino(com.qat.samples.sysmgmt.model.request.InquilinoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> updateInquilino(InquilinoMaintenanceRequest request)
{
	InternalResultsResponse<Inquilino> response =
			processInquilino(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInquilinoBAC#deleteInquilino(com.qat.samples.sysmgmt.model.request.InquilinoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> deleteInquilino(InquilinoMaintenanceRequest request)
{
	InternalResultsResponse<Inquilino> response =
			processInquilino(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInquilinoBAC#refreshInquilinos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Inquilino> refreshInquilinos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getPessoaBAR().deleteAllInquilinos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertInquilino(new Inquilino(i, "InquilinoDesc" + i));
	}

	// Call maintain to see if we need to return the inquilino list and if so whether it should be paged or not
	return maintainReturnListInquilino(request.getReturnList(), request.getReturnListPaged(),new Inquilino());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInquilinoBAC#fetchAllInquilinos(Inquilino inquilino)
 */
@Override
public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino inquilino)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	response.getResultsList().addAll(getPessoaBAR().fetchAllInquilinos(inquilino).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInquilinoBAC#fetchInquilinoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Inquilino> fetchInquilinoById(FetchByIdRequest request)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getPessoaBAR().fetchInquilinoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInquilinoBAC#fetchInquilinosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request)
{
	return getPessoaBAR().fetchInquilinosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the inquilino response
 */
private InternalResultsResponse<Inquilino> processInquilino(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		InquilinoMaintenanceRequest request)
		{
	InternalResultsResponse<Inquilino> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Inquilino.class.getSimpleName(), request.getInquilino(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Inquilino>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceInquilino(request.getInquilino(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Inquilino>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the inquilino list and if so whether it should be paged or
		// not
		response = maintainReturnListInquilino(request.getReturnList(), request.getReturnListPaged(),new Inquilino());

		return response;
			}

	/**
	 * Do persistenceInquilino.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceInquilino(Inquilino inquilino, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAR().insertInquilino(inquilino);

			case UPDATE:
				return getPessoaBAR().updateInquilino(inquilino);

			case DELETE:
				return getPessoaBAR().deleteInquilinoById(inquilino);
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
	private InternalResultsResponse<Inquilino> maintainReturnListInquilino(Boolean listIndicator, Boolean pageListIndicator,Inquilino inquilino)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				InquilinoInquiryRequest request = new InquilinoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchInquilinosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllInquilinos(inquilino);
			}
		}
		else
		{
			return new InternalResultsResponse<Inquilino>();
		}
	}

//===================================### FUNCIONARIO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertFuncionario(com.qat.samples.sysmgmt.model.request.FuncionarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request)
{
	InternalResultsResponse<Funcionario> response =
			processFuncionario(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFuncionarioBAC#updateFuncionario(com.qat.samples.sysmgmt.model.request.FuncionarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request)
{
	InternalResultsResponse<Funcionario> response =
			processFuncionario(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFuncionarioBAC#deleteFuncionario(com.qat.samples.sysmgmt.model.request.FuncionarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> deleteFuncionario(FuncionarioMaintenanceRequest request)
{
	InternalResultsResponse<Funcionario> response =
			processFuncionario(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFuncionarioBAC#refreshFuncionarios(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Funcionario> refreshFuncionarios(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getPessoaBAR().deleteAllFuncionarios();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getPessoaBAR().insertFuncionario(new Funcionario(i, "FuncionarioDesc" + i));
	}

	// Call maintain to see if we need to return the funcionario list and if so whether it should be paged or not
	return maintainReturnListFuncionario(request.getReturnList(), request.getReturnListPaged(),new Funcionario());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFuncionarioBAC#fetchAllFuncionarios(Funcionario funcionario)
 */
@Override
public InternalResultsResponse<Funcionario> fetchAllFuncionarios(Funcionario funcionario)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	response.getResultsList().addAll(getPessoaBAR().fetchAllFuncionarios(funcionario).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFuncionarioBAC#fetchFuncionarioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getPessoaBAR().fetchFuncionarioById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFuncionarioBAC#fetchFuncionariosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(FuncionarioInquiryRequest request)
{
	return getPessoaBAR().fetchFuncionariosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the funcionario response
 */
private InternalResultsResponse<Funcionario> processFuncionario(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		FuncionarioMaintenanceRequest request)
		{
	InternalResultsResponse<Funcionario> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Funcionario.class.getSimpleName(), request.getFuncionario(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Funcionario>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceFuncionario(request.getFuncionario(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Funcionario>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_PESSOA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the funcionario list and if so whether it should be paged or
		// not
		response = maintainReturnListFuncionario(request.getReturnList(), request.getReturnListPaged(),new Funcionario());

		return response;
			}

	/**
	 * Do persistenceFuncionario.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceFuncionario(Funcionario funcionario, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getPessoaBAR().insertFuncionario(funcionario);

			case UPDATE:
				return getPessoaBAR().updateFuncionario(funcionario);

			case DELETE:
				return getPessoaBAR().deleteFuncionarioById(funcionario);
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
	private InternalResultsResponse<Funcionario> maintainReturnListFuncionario(Boolean listIndicator, Boolean pageListIndicator,Funcionario funcionario)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				FuncionarioInquiryRequest request = new FuncionarioInquiryRequest();
				request.setPreQueryCount(true);
				return fetchFuncionariosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllFuncionarios(funcionario);
			}
		}
		else
		{
			return new InternalResultsResponse<Funcionario>();
		}
	}
}
