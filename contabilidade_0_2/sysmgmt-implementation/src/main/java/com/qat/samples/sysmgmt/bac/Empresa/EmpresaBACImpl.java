package com.qat.samples.sysmgmt.bac.Empresa;
/** create by system gera-java version 1.0.0 04/06/2016 19:35 : 11*/



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaMaintenanceRequest;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.bar.Util.IDoisValorBAR;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.TransactionInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.Ajuda;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Field;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Menu;
import com.qat.samples.sysmgmt.entidade.model.Message;
import com.qat.samples.sysmgmt.entidade.model.Pagina;
import com.qat.samples.sysmgmt.entidade.model.Role;
import com.qat.samples.sysmgmt.entidade.model.Transaction;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.Validacao;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.MessageInquiryRequest;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.request.AjudaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.MenuMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.MessageMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.NoteMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.PaginaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.RoleMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.StatusMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.UserRolesMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.ValidacaoMaintenanceRequest;

/**
 * Standards based implementation of a BAC for Empresa leveraging the injected IEmpresaBAR.
 */
@Component
public class EmpresaBACImpl implements IEmpresaBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_EMPRESA_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BAC_EXCEPTION_MSG = "sysmgmt.base.Empresabacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaBACImpl.class);

	/** The Empresa BAR. */
	private IEmpresaBAR empresaBAR; // injected by Spring through setter

	private IDoisValorBAR doisValorBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Empresa BAR.
	 *
	 * @param EmpresaBAR the new Empresa BAR
	 */
	public void setEmpresaBAR(IEmpresaBAR empresaBAR)
	{
		this.empresaBAR = empresaBAR;
	}

	/**
	 * Gets the Empresa BAR.
	 *
	 * @return the Empresa BAR
	 */
	public IEmpresaBAR getEmpresaBAR()
	{
		return empresaBAR;
	}

	public IDoisValorBAR getDoisValorBAR() {
		return doisValorBAR;
	}

	public void setDoisValorBAR(IDoisValorBAR doisValorBAR) {
		this.doisValorBAR = doisValorBAR;
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

//===================================### EMPRESA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertEmpresa(com.qat.samples.sysmgmt.model.request.EmpresaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Empresa> insertEmpresa(EmpresaMaintenanceRequest request)
{
	InternalResultsResponse<Empresa> response =
			processEmpresa(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEmpresaBAC#updateEmpresa(com.qat.samples.sysmgmt.model.request.EmpresaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Empresa> updateEmpresa(EmpresaMaintenanceRequest request)
{
	InternalResultsResponse<Empresa> response =
			processEmpresa(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEmpresaBAC#deleteEmpresa(com.qat.samples.sysmgmt.model.request.EmpresaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Empresa> deleteEmpresa(EmpresaMaintenanceRequest request)
{
	InternalResultsResponse<Empresa> response =
			processEmpresa(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEmpresaBAC#refreshEmpresas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Empresa> refreshEmpresas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getEmpresaBAR().deleteAllEmpresas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getEmpresaBAR().insertEmpresa(new Empresa(i, "EmpresaDesc" + i));
	}

	// Call maintain to see if we need to return the empresa list and if so whether it should be paged or not
	return maintainReturnListEmpresa(request.getReturnList(), request.getReturnListPaged(),new Empresa());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEmpresaBAC#fetchAllEmpresas(Empresa empresa)
 */
@Override
public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa empresa)
{
	InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
	response.getResultsList().addAll(getEmpresaBAR().fetchAllEmpresas(empresa).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IEmpresaBAC#fetchEmpresaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request)
{
	InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getEmpresaBAR().fetchEmpresaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IEmpresaBAC#fetchEmpresasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request)
{
	return getEmpresaBAR().fetchEmpresasByRequest(request);
}



@Override
public InternalResultsResponse<Empresa> fetchAllEmpresasByUser(EmpresaInquiryRequest request)
{
	return getEmpresaBAR().fetchAllEmpresasByUser(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the empresa response
 */
private InternalResultsResponse<Empresa> processEmpresa(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		EmpresaMaintenanceRequest request)
		{
	InternalResultsResponse<Empresa> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceEmpresa(request.getEmpresa(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Empresa>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the empresa list and if so whether it should be paged or
		// not
		response = maintainReturnListEmpresa(request.getReturnList(), request.getReturnListPaged(),new Empresa());

		return response;
			}
private InternalResultsResponse<Message> processMessage(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		MessageMaintenanceRequest request)
		{
	InternalResultsResponse<Message> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceMessage(request.getMessage(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Message>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the empresa list and if so whether it should be paged or
		// not
		response = maintainReturnListMessage(request.getReturnList(), request.getReturnListPaged(),new Message());

		return response;
			}
	/**
	 * Do persistenceEmpresa.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceEmpresa(Empresa empresa, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertEmpresa(empresa);

			case UPDATE:
				return getEmpresaBAR().updateEmpresa(empresa);

			case DELETE:
				return getEmpresaBAR().deleteEmpresaById(empresa);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	private InternalResponse doPersistenceMessage(Message empresa, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertMessage(empresa);

			case UPDATE:
				return getEmpresaBAR().updateMessage(empresa);

			case DELETE:
				return getEmpresaBAR().deleteMessageById(empresa);
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
	private InternalResultsResponse<Empresa> maintainReturnListEmpresa(Boolean listIndicator, Boolean pageListIndicator,Empresa empresa)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				EmpresaInquiryRequest request = new EmpresaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchEmpresasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllEmpresas(empresa);
			}
		}
		else
		{
			return new InternalResultsResponse<Empresa>();
		}
	}

	private InternalResultsResponse<Message> maintainReturnListMessage(Boolean listIndicator, Boolean pageListIndicator,Message empresa)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				MessageInquiryRequest request = new MessageInquiryRequest();
				request.setPreQueryCount(true);
				return fetchMessagesByRequest(request);
			}
		}
		else
		{
			return new InternalResultsResponse<Message>();
		}
		return null;
	}

//===================================### FILIAL ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertFilial(com.qat.samples.sysmgmt.model.request.FilialMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Filial> insertFilial(FilialMaintenanceRequest request)
{
	InternalResultsResponse<Filial> response =
			processFilial(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFilialBAC#updateFilial(com.qat.samples.sysmgmt.model.request.FilialMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Filial> updateFilial(FilialMaintenanceRequest request)
{
	InternalResultsResponse<Filial> response =
			processFilial(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFilialBAC#deleteFilial(com.qat.samples.sysmgmt.model.request.FilialMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Filial> deleteFilial(FilialMaintenanceRequest request)
{
	InternalResultsResponse<Filial> response =
			processFilial(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFilialBAC#refreshFilials(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Filial> refreshFilials(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getEmpresaBAR().deleteAllFilials();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getEmpresaBAR().insertFilial(new Filial(i, "FilialDesc" + i));
	}

	// Call maintain to see if we need to return the filial list and if so whether it should be paged or not
	return maintainReturnListFilial(request.getReturnList(), request.getReturnListPaged(),new Filial());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFilialBAC#fetchAllFilials(Filial filial)
 */
@Override
public InternalResultsResponse<Filial> fetchAllFilials(Filial filial)
{
	InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
	response.getResultsList().addAll(getEmpresaBAR().fetchAllFilials(filial).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IFilialBAC#fetchFilialById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request)
{
	InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getEmpresaBAR().fetchFilialById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IFilialBAC#fetchFilialsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request)
{
	return getEmpresaBAR().fetchFilialsByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the filial response
 */
private InternalResultsResponse<Filial> processFilial(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		FilialMaintenanceRequest request)
		{
	InternalResultsResponse<Filial> response = null;


		// Persist
		InternalResponse internalResponse = doPersistenceFilial(request.getFilial(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Filial>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the filial list and if so whether it should be paged or
		// not
		response = maintainReturnListFilial(request.getReturnList(), request.getReturnListPaged(),new Filial());

		return response;
			}

	/**
	 * Do persistenceFilial.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceFilial(Filial filial, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertFilial(filial);

			case UPDATE:
				return getEmpresaBAR().updateFilial(filial);

			case DELETE:
				return getEmpresaBAR().deleteFilialById(filial);
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
	private InternalResultsResponse<Filial> maintainReturnListFilial(Boolean listIndicator, Boolean pageListIndicator,Filial filial)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				FilialInquiryRequest request = new FilialInquiryRequest();
				request.setPreQueryCount(true);
				return fetchFilialsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllFilials(filial);
			}
		}
		else
		{
			return new InternalResultsResponse<Filial>();
		}
	}

//===================================### DEPOSITO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertDeposito(com.qat.samples.sysmgmt.model.request.DepositoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Deposito> insertDeposito(DepositoMaintenanceRequest request)
{
	InternalResultsResponse<Deposito> response =
			processDeposito(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDepositoBAC#updateDeposito(com.qat.samples.sysmgmt.model.request.DepositoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Deposito> updateDeposito(DepositoMaintenanceRequest request)
{
	InternalResultsResponse<Deposito> response =
			processDeposito(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDepositoBAC#deleteDeposito(com.qat.samples.sysmgmt.model.request.DepositoMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Deposito> deleteDeposito(DepositoMaintenanceRequest request)
{
	InternalResultsResponse<Deposito> response =
			processDeposito(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDepositoBAC#refreshDepositos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Deposito> refreshDepositos(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getEmpresaBAR().deleteAllDepositos();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getEmpresaBAR().insertDeposito(new Deposito(i, "DepositoDesc" + i));
	}

	// Call maintain to see if we need to return the deposito list and if so whether it should be paged or not
	return maintainReturnListDeposito(request.getReturnList(), request.getReturnListPaged(),new Deposito());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDepositoBAC#fetchAllDepositos(Deposito deposito)
 */
@Override
public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito deposito)
{
	InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
	response.getResultsList().addAll(getEmpresaBAR().fetchAllDepositos(deposito).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IDepositoBAC#fetchDepositoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request)
{
	InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getEmpresaBAR().fetchDepositoById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IDepositoBAC#fetchDepositosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request)
{
	return getEmpresaBAR().fetchDepositosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the deposito response
 */
private InternalResultsResponse<Deposito> processDeposito(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		DepositoMaintenanceRequest request)
		{
	InternalResultsResponse<Deposito> response = null;


		// Persist
		InternalResponse internalResponse = doPersistenceDeposito(request.getDeposito(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Deposito>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the deposito list and if so whether it should be paged or
		// not
		response = maintainReturnListDeposito(request.getReturnList(), request.getReturnListPaged(),new Deposito());

		return response;
			}

	/**
	 * Do persistenceDeposito.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceDeposito(Deposito deposito, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertDeposito(deposito);

			case UPDATE:
				return getEmpresaBAR().updateDeposito(deposito);

			case DELETE:
				return getEmpresaBAR().deleteDepositoById(deposito);
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
	private InternalResultsResponse<Deposito> maintainReturnListDeposito(Boolean listIndicator, Boolean pageListIndicator,Deposito deposito)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				DepositoInquiryRequest request = new DepositoInquiryRequest();
				request.setPreQueryCount(true);
				return fetchDepositosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllDepositos(deposito);
			}
		}
		else
		{
			return new InternalResultsResponse<Deposito>();
		}
	}

//===================================### USUARIO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertUsuario(com.qat.samples.sysmgmt.model.request.UsuarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Usuario> insertUsuario(UsuarioMaintenanceRequest request)
{
	InternalResultsResponse<Usuario> response =
			processUsuario(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IUsuarioBAC#updateUsuario(com.qat.samples.sysmgmt.model.request.UsuarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Usuario> updateUsuario(UsuarioMaintenanceRequest request)
{
	InternalResultsResponse<Usuario> response =
			processUsuario(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IUsuarioBAC#deleteUsuario(com.qat.samples.sysmgmt.model.request.UsuarioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Usuario> deleteUsuario(UsuarioMaintenanceRequest request)
{
	InternalResultsResponse<Usuario> response =
			processUsuario(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IUsuarioBAC#refreshUsuarios(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Usuario> refreshUsuarios(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getEmpresaBAR().deleteAllUsuarios();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getEmpresaBAR().insertUsuario(new Usuario(i, "UsuarioDesc" + i));
	}

	// Call maintain to see if we need to return the usuario list and if so whether it should be paged or not
	return maintainReturnListUsuario(request.getReturnList(), request.getReturnListPaged(),new Usuario());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IUsuarioBAC#fetchAllUsuarios(Usuario usuario)
 */
@Override
public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario usuario)
{
	InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
	response.getResultsList().addAll(getEmpresaBAR().fetchAllUsuarios(usuario).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IUsuarioBAC#fetchUsuarioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Usuario> fetchUsuarioById(FetchByIdRequest request)
{
	InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getEmpresaBAR().fetchUsuarioById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IUsuarioBAC#fetchUsuariosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request)
{
	return getEmpresaBAR().fetchUsuariosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the usuario response
 */
private InternalResultsResponse<Usuario> processUsuario(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		UsuarioMaintenanceRequest request)
		{
	InternalResultsResponse<Usuario> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceUsuario(request.getUsuario(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Usuario>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the usuario list and if so whether it should be paged or
		// not
		response = maintainReturnListUsuario(request.getReturnList(), request.getReturnListPaged(),new Usuario());

		return response;
			}

	/**
	 * Do persistenceUsuario.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceUsuario(Usuario usuario, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertUsuario(usuario);

			case UPDATE:
				return getEmpresaBAR().updateUsuario(usuario);

			case DELETE:
				return getEmpresaBAR().deleteUsuarioById(usuario);
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
	private InternalResultsResponse<Usuario> maintainReturnListUsuario(Boolean listIndicator, Boolean pageListIndicator,Usuario usuario)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				UsuarioInquiryRequest request = new UsuarioInquiryRequest();
				request.setPreQueryCount(true);
				return fetchUsuariosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllUsuarios(usuario);
			}
		}
		else
		{
			return new InternalResultsResponse<Usuario>();
		}
	}

//===================================### ADVOCACIA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertAdvocacia(com.qat.samples.sysmgmt.model.request.AdvocaciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advocacia> insertAdvocacia(AdvocaciaMaintenanceRequest request)
{
	InternalResultsResponse<Advocacia> response =
			processAdvocacia(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvocaciaBAC#updateAdvocacia(com.qat.samples.sysmgmt.model.request.AdvocaciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advocacia> updateAdvocacia(AdvocaciaMaintenanceRequest request)
{
	InternalResultsResponse<Advocacia> response =
			processAdvocacia(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvocaciaBAC#deleteAdvocacia(com.qat.samples.sysmgmt.model.request.AdvocaciaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Advocacia> deleteAdvocacia(AdvocaciaMaintenanceRequest request)
{
	InternalResultsResponse<Advocacia> response =
			processAdvocacia(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvocaciaBAC#refreshAdvocacias(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Advocacia> refreshAdvocacias(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getEmpresaBAR().deleteAllAdvocacias();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getEmpresaBAR().insertAdvocacia(new Advocacia(i, "AdvocaciaDesc" + i));
	}

	// Call maintain to see if we need to return the advocacia list and if so whether it should be paged or not
	return maintainReturnListAdvocacia(request.getReturnList(), request.getReturnListPaged(),new Advocacia());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvocaciaBAC#fetchAllAdvocacias(Advocacia advocacia)
 */
@Override
public InternalResultsResponse<Advocacia> fetchAllAdvocacias(Advocacia advocacia)
{
	InternalResultsResponse<Advocacia> response = new InternalResultsResponse<Advocacia>();
	response.getResultsList().addAll(getEmpresaBAR().fetchAllAdvocacias(advocacia).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IAdvocaciaBAC#fetchAdvocaciaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Advocacia> fetchAdvocaciaById(FetchByIdRequest request)
{
	InternalResultsResponse<Advocacia> response = new InternalResultsResponse<Advocacia>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getEmpresaBAR().fetchAdvocaciaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IAdvocaciaBAC#fetchAdvocaciasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Advocacia> fetchAdvocaciasByRequest(AdvocaciaInquiryRequest request)
{
	return getEmpresaBAR().fetchAdvocaciasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the advocacia response
 */
private InternalResultsResponse<Advocacia> processAdvocacia(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		AdvocaciaMaintenanceRequest request)
		{
	InternalResultsResponse<Advocacia> response = null;


		// Persist
		InternalResponse internalResponse = doPersistenceAdvocacia(request.getAdvocacia(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Advocacia>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the advocacia list and if so whether it should be paged or
		// not
		response = maintainReturnListAdvocacia(request.getReturnList(), request.getReturnListPaged(),new Advocacia());

		return response;
			}

	/**
	 * Do persistenceAdvocacia.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceAdvocacia(Advocacia advocacia, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertAdvocacia(advocacia);

			case UPDATE:
				return getEmpresaBAR().updateAdvocacia(advocacia);

			case DELETE:
				return getEmpresaBAR().deleteAdvocaciaById(advocacia);
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
	private InternalResultsResponse<Advocacia> maintainReturnListAdvocacia(Boolean listIndicator, Boolean pageListIndicator,Advocacia advocacia)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				AdvocaciaInquiryRequest request = new AdvocaciaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchAdvocaciasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllAdvocacias(advocacia);
			}
		}
		else
		{
			return new InternalResultsResponse<Advocacia>();
		}
	}

//===================================### CLINICA ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertClinica(com.qat.samples.sysmgmt.model.request.ClinicaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Clinica> insertClinica(ClinicaMaintenanceRequest request)
{
	InternalResultsResponse<Clinica> response =
			processClinica(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClinicaBAC#updateClinica(com.qat.samples.sysmgmt.model.request.ClinicaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Clinica> updateClinica(ClinicaMaintenanceRequest request)
{
	InternalResultsResponse<Clinica> response =
			processClinica(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClinicaBAC#deleteClinica(com.qat.samples.sysmgmt.model.request.ClinicaMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Clinica> deleteClinica(ClinicaMaintenanceRequest request)
{
	InternalResultsResponse<Clinica> response =
			processClinica(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClinicaBAC#refreshClinicas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Clinica> refreshClinicas(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getEmpresaBAR().deleteAllClinicas();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getEmpresaBAR().insertClinica(new Clinica(i, "ClinicaDesc" + i));
	}

	// Call maintain to see if we need to return the clinica list and if so whether it should be paged or not
	return maintainReturnListClinica(request.getReturnList(), request.getReturnListPaged(),new Clinica());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClinicaBAC#fetchAllClinicas(Clinica clinica)
 */
@Override
public InternalResultsResponse<Clinica> fetchAllClinicas(Clinica clinica)
{
	InternalResultsResponse<Clinica> response = new InternalResultsResponse<Clinica>();
	response.getResultsList().addAll(getEmpresaBAR().fetchAllClinicas(clinica).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClinicaBAC#fetchClinicaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Clinica> fetchClinicaById(FetchByIdRequest request)
{
	InternalResultsResponse<Clinica> response = new InternalResultsResponse<Clinica>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getEmpresaBAR().fetchClinicaById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClinicaBAC#fetchClinicasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Clinica> fetchClinicasByRequest(ClinicaInquiryRequest request)
{
	return getEmpresaBAR().fetchClinicasByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the clinica response
 */
private InternalResultsResponse<Clinica> processClinica(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ClinicaMaintenanceRequest request)
		{
	InternalResultsResponse<Clinica> response = null;

		// Persist
		InternalResponse internalResponse = doPersistenceClinica(request.getClinica(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Clinica>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the clinica list and if so whether it should be paged or
		// not
		response = maintainReturnListClinica(request.getReturnList(), request.getReturnListPaged(),new Clinica());

		return response;
			}

	/**
	 * Do persistenceClinica.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceClinica(Clinica clinica, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertClinica(clinica);

			case UPDATE:
				return getEmpresaBAR().updateClinica(clinica);

			case DELETE:
				return getEmpresaBAR().deleteClinicaById(clinica);
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
	private InternalResultsResponse<Clinica> maintainReturnListClinica(Boolean listIndicator, Boolean pageListIndicator,Clinica clinica)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ClinicaInquiryRequest request = new ClinicaInquiryRequest();
				request.setPreQueryCount(true);
				return fetchClinicasByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllClinicas(clinica);
			}
		}
		else
		{
			return new InternalResultsResponse<Clinica>();
		}
	}

//===================================### CONDOMINIO ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertCondominio(com.qat.samples.sysmgmt.model.request.CondominioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Condominio> insertCondominio(CondominioMaintenanceRequest request)
{
	InternalResultsResponse<Condominio> response =
			processCondominio(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICondominioBAC#updateCondominio(com.qat.samples.sysmgmt.model.request.CondominioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Condominio> updateCondominio(CondominioMaintenanceRequest request)
{
	InternalResultsResponse<Condominio> response =
			processCondominio(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICondominioBAC#deleteCondominio(com.qat.samples.sysmgmt.model.request.CondominioMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Condominio> deleteCondominio(CondominioMaintenanceRequest request)
{
	InternalResultsResponse<Condominio> response =
			processCondominio(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICondominioBAC#refreshCondominios(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Condominio> refreshCondominios(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getEmpresaBAR().deleteAllCondominios();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getEmpresaBAR().insertCondominio(new Condominio(i, "CondominioDesc" + i));
	}

	// Call maintain to see if we need to return the condominio list and if so whether it should be paged or not
	return maintainReturnListCondominio(request.getReturnList(), request.getReturnListPaged(),new Condominio());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICondominioBAC#fetchAllCondominios(Condominio condominio)
 */
@Override
public InternalResultsResponse<Condominio> fetchAllCondominios(Condominio condominio)
{
	InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();
	response.getResultsList().addAll(getEmpresaBAR().fetchAllCondominios(condominio).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICondominioBAC#fetchCondominioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Condominio> fetchCondominioById(FetchByIdRequest request)
{
	InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getEmpresaBAR().fetchCondominioById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.ICondominioBAC#fetchCondominiosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Condominio> fetchCondominiosByRequest(CondominioInquiryRequest request)
{
	return getEmpresaBAR().fetchCondominiosByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the condominio response
 */
private InternalResultsResponse<Condominio> processCondominio(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		CondominioMaintenanceRequest request)
		{
	InternalResultsResponse<Condominio> response = null;


		// Persist
		InternalResponse internalResponse = doPersistenceCondominio(request.getCondominio(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Condominio>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the condominio list and if so whether it should be paged or
		// not
		response = maintainReturnListCondominio(request.getReturnList(), request.getReturnListPaged(),new Condominio());

		return response;
			}

	/**
	 * Do persistenceCondominio.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceCondominio(Condominio condominio, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getEmpresaBAR().insertCondominio(condominio);

			case UPDATE:
				return getEmpresaBAR().updateCondominio(condominio);

			case DELETE:
				return getEmpresaBAR().deleteCondominioById(condominio);
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
	private InternalResultsResponse<Condominio> maintainReturnListCondominio(Boolean listIndicator, Boolean pageListIndicator,Condominio condominio)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				CondominioInquiryRequest request = new CondominioInquiryRequest();
				request.setPreQueryCount(true);
				return fetchCondominiosByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllCondominios(condominio);
			}
		}
		else
		{
			return new InternalResultsResponse<Condominio>();
		}
	}

	@Override
	public InternalResultsResponse<Transaction> fetchTransactionById(TransactionInquiryRequest request) {
		return getEmpresaBAR().fetchTransactionById(request);
	}

	//===================================### USERROLES ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertUserRoles(com.qat.samples.sysmgmt.model.request.UserRolesMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<UserRoles> insertUserRoles(UserRolesMaintenanceRequest request)
	{
		InternalResultsResponse<UserRoles> response =
				processUserRoles(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IUserRolesBAC#updateUserRoles(com.qat.samples.sysmgmt.model.request.UserRolesMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<UserRoles> updateUserRoles(UserRolesMaintenanceRequest request)
	{
		InternalResultsResponse<UserRoles> response =
				processUserRoles(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IUserRolesBAC#deleteUserRoles(com.qat.samples.sysmgmt.model.request.UserRolesMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<UserRoles> deleteUserRoles(UserRolesMaintenanceRequest request)
	{
		InternalResultsResponse<UserRoles> response =
				processUserRoles(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IUserRolesBAC#refreshUserRoless(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<UserRoles> refreshUserRoless(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getEmpresaBAR().deleteAllUserRoless();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getEmpresaBAR().insertUserRoles(new UserRoles(i, "UserRolesDesc" + i));
		}

		// Call maintain to see if we need to return the userroles list and if so whether it should be paged or not
		return maintainReturnListUserRoles(request.getReturnList(), request.getReturnListPaged(),new UserRoles());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IUserRolesBAC#fetchAllUserRoless(UserRoles userroles)
	 */
	@Override
	public InternalResultsResponse<UserRoles> fetchAllUserRoless(UserRoles userroles)
	{
		InternalResultsResponse<UserRoles> response = new InternalResultsResponse<UserRoles>();
		response.getResultsList().addAll(getEmpresaBAR().fetchAllUserRoless(userroles).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IUserRolesBAC#fetchUserRolesById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<UserRoles> fetchUserRolesById(FetchByIdRequest request)
	{
		InternalResultsResponse<UserRoles> response = new InternalResultsResponse<UserRoles>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getEmpresaBAR().fetchUserRolesById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IUserRolesBAC#fetchUserRolessByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<UserRoles> fetchUserRolessByRequest(PagedInquiryRequest request)
	{
		return getEmpresaBAR().fetchUserRolessByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the userroles response
	 */
	private InternalResultsResponse<UserRoles> processUserRoles(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			UserRolesMaintenanceRequest request)
			{
		InternalResultsResponse<UserRoles> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(UserRoles.class.getSimpleName(), request.getUserRoles(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<UserRoles>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceUserRoles(request.getUserRole(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<UserRoles>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the userroles list and if so whether it should be paged or
			// not
			response = maintainReturnListUserRoles(request.getReturnList(), request.getReturnListPaged(),new UserRoles());

			return response;
				}

		/**
		 * Do persistenceUserRoles.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceUserRoles(UserRoles userroles, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getEmpresaBAR().insertUserRoles(userroles);

				case UPDATE:
					return getEmpresaBAR().updateUserRoles(userroles);

				case DELETE:
					return getEmpresaBAR().deleteUserRolesById(userroles);
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
		private InternalResultsResponse<UserRoles> maintainReturnListUserRoles(Boolean listIndicator, Boolean pageListIndicator,UserRoles userroles)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchUserRolessByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllUserRoless(userroles);
				}
			}
			else
			{
				return new InternalResultsResponse<UserRoles>();
			}
		}

	//===================================### ROLE ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertRole(com.qat.samples.sysmgmt.model.request.RoleMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Role> insertRole(RoleMaintenanceRequest request)
	{
		InternalResultsResponse<Role> response =
				processRole(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IRoleBAC#updateRole(com.qat.samples.sysmgmt.model.request.RoleMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Role> updateRole(RoleMaintenanceRequest request)
	{
		InternalResultsResponse<Role> response =
				processRole(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IRoleBAC#deleteRole(com.qat.samples.sysmgmt.model.request.RoleMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Role> deleteRole(RoleMaintenanceRequest request)
	{
		InternalResultsResponse<Role> response =
				processRole(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IRoleBAC#refreshRoles(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Role> refreshRoles(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getEmpresaBAR().deleteAllRoles();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getEmpresaBAR().insertRole(new Role(i, "RoleDesc" + i));
		}

		// Call maintain to see if we need to return the role list and if so whether it should be paged or not
		return maintainReturnListRole(request.getReturnList(), request.getReturnListPaged(),new Role());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IRoleBAC#fetchAllRoles(Role role)
	 */
	@Override
	public InternalResultsResponse<Role> fetchAllRoles(Role role)
	{
		InternalResultsResponse<Role> response = new InternalResultsResponse<Role>();
		response.getResultsList().addAll(getEmpresaBAR().fetchAllRoles(role).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IRoleBAC#fetchRoleById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Role> fetchRoleById(FetchByIdRequest request)
	{
		InternalResultsResponse<Role> response = new InternalResultsResponse<Role>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getEmpresaBAR().fetchRoleById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IRoleBAC#fetchRolesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Role> fetchRolesByRequest(PagedInquiryRequest request)
	{
		return getEmpresaBAR().fetchRolesByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the role response
	 */
	private InternalResultsResponse<Role> processRole(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			RoleMaintenanceRequest request)
			{
		InternalResultsResponse<Role> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Role.class.getSimpleName(), request.getRole(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Role>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceRole(request.getRole(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Role>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the role list and if so whether it should be paged or
			// not
			response = maintainReturnListRole(request.getReturnList(), request.getReturnListPaged(),new Role());

			return response;
				}

		/**
		 * Do persistenceRole.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceRole(Role role, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getEmpresaBAR().insertRole(role);

				case UPDATE:
					return getEmpresaBAR().updateRole(role);

				case DELETE:
					return getEmpresaBAR().deleteRoleById(role);
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
		private InternalResultsResponse<Role> maintainReturnListRole(Boolean listIndicator, Boolean pageListIndicator,Role role)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchRolesByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllRoles(role);
				}
			}
			else
			{
				return new InternalResultsResponse<Role>();
			}
		}

	//===================================### PAGINA ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertPagina(com.qat.samples.sysmgmt.model.request.PaginaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Pagina> insertPagina(PaginaMaintenanceRequest request)
	{
		InternalResultsResponse<Pagina> response =
				processPagina(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IPaginaBAC#updatePagina(com.qat.samples.sysmgmt.model.request.PaginaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Pagina> updatePagina(PaginaMaintenanceRequest request)
	{
		InternalResultsResponse<Pagina> response =
				processPagina(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IPaginaBAC#deletePagina(com.qat.samples.sysmgmt.model.request.PaginaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Pagina> deletePagina(PaginaMaintenanceRequest request)
	{
		InternalResultsResponse<Pagina> response =
				processPagina(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IPaginaBAC#refreshPaginas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Pagina> refreshPaginas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getEmpresaBAR().deleteAllPaginas();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getEmpresaBAR().insertPagina(new Pagina(i, "PaginaDesc" + i));
		}

		// Call maintain to see if we need to return the pagina list and if so whether it should be paged or not
		return maintainReturnListPagina(request.getReturnList(), request.getReturnListPaged(),new Pagina());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IPaginaBAC#fetchAllPaginas(Pagina pagina)
	 */
	@Override
	public InternalResultsResponse<Pagina> fetchAllPaginas(Pagina pagina)
	{
		InternalResultsResponse<Pagina> response = new InternalResultsResponse<Pagina>();
		response.getResultsList().addAll(getEmpresaBAR().fetchAllPaginas(pagina).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IPaginaBAC#fetchPaginaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Pagina> fetchPaginaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Pagina> response = new InternalResultsResponse<Pagina>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getEmpresaBAR().fetchPaginaById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IPaginaBAC#fetchPaginasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Pagina> fetchPaginasByRequest(PagedInquiryRequest request)
	{
		return getEmpresaBAR().fetchPaginasByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the pagina response
	 */
	private InternalResultsResponse<Pagina> processPagina(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			PaginaMaintenanceRequest request)
			{
		InternalResultsResponse<Pagina> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Pagina.class.getSimpleName(), request.getPagina(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Pagina>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistencePagina(request.getPagina(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Pagina>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the pagina list and if so whether it should be paged or
			// not
			response = maintainReturnListPagina(request.getReturnList(), request.getReturnListPaged(),new Pagina());

			return response;
				}

		/**
		 * Do persistencePagina.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistencePagina(Pagina pagina, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getEmpresaBAR().insertPagina(pagina);

				case UPDATE:
					return getEmpresaBAR().updatePagina(pagina);

				case DELETE:
					return getEmpresaBAR().deletePaginaById(pagina);
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
		private InternalResultsResponse<Pagina> maintainReturnListPagina(Boolean listIndicator, Boolean pageListIndicator,Pagina pagina)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchPaginasByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllPaginas(pagina);
				}
			}
			else
			{
				return new InternalResultsResponse<Pagina>();
			}
		}

	//===================================### VALIDACAO ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertValidacao(com.qat.samples.sysmgmt.model.request.ValidacaoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Validacao> insertValidacao(ValidacaoMaintenanceRequest request)
	{
		InternalResultsResponse<Validacao> response =
				processValidacao(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IValidacaoBAC#updateValidacao(com.qat.samples.sysmgmt.model.request.ValidacaoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Validacao> updateValidacao(ValidacaoMaintenanceRequest request)
	{
		InternalResultsResponse<Validacao> response =
				processValidacao(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IValidacaoBAC#deleteValidacao(com.qat.samples.sysmgmt.model.request.ValidacaoMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Validacao> deleteValidacao(ValidacaoMaintenanceRequest request)
	{
		InternalResultsResponse<Validacao> response =
				processValidacao(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IValidacaoBAC#refreshValidacaos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Validacao> refreshValidacaos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getEmpresaBAR().deleteAllValidacaos();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getEmpresaBAR().insertValidacao(new Validacao(i, "ValidacaoDesc" + i));
		}

		// Call maintain to see if we need to return the validacao list and if so whether it should be paged or not
		return maintainReturnListValidacao(request.getReturnList(), request.getReturnListPaged(),new Validacao());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IValidacaoBAC#fetchAllValidacaos(Validacao validacao)
	 */
	@Override
	public InternalResultsResponse<Validacao> fetchAllValidacaos(Validacao validacao)
	{
		InternalResultsResponse<Validacao> response = new InternalResultsResponse<Validacao>();
		response.getResultsList().addAll(getEmpresaBAR().fetchAllValidacaos(validacao).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IValidacaoBAC#fetchValidacaoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Validacao> fetchValidacaoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Validacao> response = new InternalResultsResponse<Validacao>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getEmpresaBAR().fetchValidacaoById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IValidacaoBAC#fetchValidacaosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Validacao> fetchValidacaosByRequest(PagedInquiryRequest request)
	{
		return getEmpresaBAR().fetchValidacaosByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the validacao response
	 */
	private InternalResultsResponse<Validacao> processValidacao(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			ValidacaoMaintenanceRequest request)
			{
		InternalResultsResponse<Validacao> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Validacao.class.getSimpleName(), request.getValidacao(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Validacao>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceValidacao(request.getValidacao(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Validacao>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the validacao list and if so whether it should be paged or
			// not
			response = maintainReturnListValidacao(request.getReturnList(), request.getReturnListPaged(),new Validacao());

			return response;
				}

		/**
		 * Do persistenceValidacao.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceValidacao(Validacao validacao, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getEmpresaBAR().insertValidacao(validacao);

				case UPDATE:
					return getEmpresaBAR().updateValidacao(validacao);

				case DELETE:
					return getEmpresaBAR().deleteValidacaoById(validacao);
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
		private InternalResultsResponse<Validacao> maintainReturnListValidacao(Boolean listIndicator, Boolean pageListIndicator,Validacao validacao)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchValidacaosByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllValidacaos(validacao);
				}
			}
			else
			{
				return new InternalResultsResponse<Validacao>();
			}
		}

	//===================================### FIELD ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertField(com.qat.samples.sysmgmt.model.request.FieldMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Field> insertField(FieldMaintenanceRequest request)
	{
		InternalResultsResponse<Field> response =
				processField(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IFieldBAC#updateField(com.qat.samples.sysmgmt.model.request.FieldMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Field> updateField(FieldMaintenanceRequest request)
	{
		InternalResultsResponse<Field> response =
				processField(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IFieldBAC#deleteField(com.qat.samples.sysmgmt.model.request.FieldMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Field> deleteField(FieldMaintenanceRequest request)
	{
		InternalResultsResponse<Field> response =
				processField(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IFieldBAC#refreshFields(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Field> refreshFields(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getEmpresaBAR().deleteAllFields();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getEmpresaBAR().insertField(new Field(i, "FieldDesc" + i));
		}

		// Call maintain to see if we need to return the field list and if so whether it should be paged or not
		return maintainReturnListField(request.getReturnList(), request.getReturnListPaged(),new Field());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IFieldBAC#fetchAllFields(Field field)
	 */
	@Override
	public InternalResultsResponse<Field> fetchAllFields(Field field)
	{
		InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();
		response.getResultsList().addAll(getEmpresaBAR().fetchAllFields(field).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IFieldBAC#fetchFieldById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Field> fetchFieldById(FetchByIdRequest request)
	{
		InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getEmpresaBAR().fetchFieldById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IFieldBAC#fetchFieldsByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Field> fetchFieldsByRequest(FieldInquiryRequest request)
	{
		return getEmpresaBAR().fetchFieldsByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the field response
	 */
	private InternalResultsResponse<Field> processField(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			FieldMaintenanceRequest request)
			{
		InternalResultsResponse<Field> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Field.class.getSimpleName(), request.getField(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Field>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceField(request.getField(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Field>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the field list and if so whether it should be paged or
			// not
			response = maintainReturnListField(request.getReturnList(), request.getReturnListPaged(),new Field());

			return response;
				}

		/**
		 * Do persistenceField.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceField(Field field, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getEmpresaBAR().insertField(field);

				case UPDATE:
					return getEmpresaBAR().updateField(field);

				case DELETE:
					return getEmpresaBAR().deleteFieldById(field);
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
		private InternalResultsResponse<Field> maintainReturnListField(Boolean listIndicator, Boolean pageListIndicator,Field field)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					FieldInquiryRequest request = new FieldInquiryRequest();
					request.setPreQueryCount(true);
					return fetchFieldsByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllFields(field);
				}
			}
			else
			{
				return new InternalResultsResponse<Field>();
			}
		}

	//===================================### AJUDA ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertAjuda(com.qat.samples.sysmgmt.model.request.AjudaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Ajuda> insertAjuda(AjudaMaintenanceRequest request)
	{
		InternalResultsResponse<Ajuda> response =
				processAjuda(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IAjudaBAC#updateAjuda(com.qat.samples.sysmgmt.model.request.AjudaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Ajuda> updateAjuda(AjudaMaintenanceRequest request)
	{
		InternalResultsResponse<Ajuda> response =
				processAjuda(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IAjudaBAC#deleteAjuda(com.qat.samples.sysmgmt.model.request.AjudaMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Ajuda> deleteAjuda(AjudaMaintenanceRequest request)
	{
		InternalResultsResponse<Ajuda> response =
				processAjuda(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IAjudaBAC#refreshAjudas(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Ajuda> refreshAjudas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		getEmpresaBAR().deleteAllAjudas();
		int refreshNumber = request.getRefreshInt();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
		getEmpresaBAR().insertAjuda(new Ajuda(i, "AjudaDesc" + i));
		}

		// Call maintain to see if we need to return the ajuda list and if so whether it should be paged or not
		return maintainReturnListAjuda(request.getReturnList(), request.getReturnListPaged(),new Ajuda());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IAjudaBAC#fetchAllAjudas(Ajuda ajuda)
	 */
	@Override
	public InternalResultsResponse<Ajuda> fetchAllAjudas(Ajuda ajuda)
	{
		InternalResultsResponse<Ajuda> response = new InternalResultsResponse<Ajuda>();
		response.getResultsList().addAll(getEmpresaBAR().fetchAllAjudas(ajuda).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IAjudaBAC#fetchAjudaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Ajuda> fetchAjudaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Ajuda> response = new InternalResultsResponse<Ajuda>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getEmpresaBAR().fetchAjudaById(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IAjudaBAC#fetchAjudasByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Ajuda> fetchAjudasByRequest(PagedInquiryRequest request)
	{
		return getEmpresaBAR().fetchAjudasByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the ajuda response
	 */
	private InternalResultsResponse<Ajuda> processAjuda(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			AjudaMaintenanceRequest request)
			{
		InternalResultsResponse<Ajuda> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Ajuda.class.getSimpleName(), request.getAjuda(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Ajuda>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceAjuda(request.getAjuda(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Ajuda>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the ajuda list and if so whether it should be paged or
			// not
			response = maintainReturnListAjuda(request.getReturnList(), request.getReturnListPaged(),new Ajuda());

			return response;
				}

		/**
		 * Do persistenceAjuda.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceAjuda(Ajuda ajuda, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getEmpresaBAR().insertAjuda(ajuda);

				case UPDATE:
					return getEmpresaBAR().updateAjuda(ajuda);

				case DELETE:
					return getEmpresaBAR().deleteAjudaById(ajuda);
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
		private InternalResultsResponse<Ajuda> maintainReturnListAjuda(Boolean listIndicator, Boolean pageListIndicator,Ajuda ajuda)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchAjudasByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllAjudas(ajuda);
				}
			}
			else
			{
				return new InternalResultsResponse<Ajuda>();
			}
		}

	//===================================### NOTE ####======================================
		/**
	/*
	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertMenu(com.qat.samples.sysmgmt.model.request.MenuMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Note> insertNote(NoteMaintenanceRequest request)
	{
		InternalResultsResponse<Note> response =
				processNote(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.INoteBAC#updateNote(com.qat.samples.sysmgmt.model.request.NoteMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Note> updateNote(NoteMaintenanceRequest request)
	{
		InternalResultsResponse<Note> response =
				processNote(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.INoteBAC#deleteNote(com.qat.samples.sysmgmt.model.request.NoteMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Note> deleteNote(NoteMaintenanceRequest request)
	{
		InternalResultsResponse<Note> response =
				processNote(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.INoteBAC#fetchAllNotes(Note menu)
	 */
	@Override
	public InternalResultsResponse<Note> fetchAllNotes(Note menu)
	{
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();
		response.getResultsList().addAll(getEmpresaBAR().fetchAllNotes(menu).getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.INoteBAC#fetchNotesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Note> fetchNotesByRequest(PagedInquiryRequest request)
	{
		return getEmpresaBAR().fetchNotesByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the menu response
	 */
	private InternalResultsResponse<Note> processNote(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			NoteMaintenanceRequest request)
			{
		InternalResultsResponse<Note> response = null;

		// Validate
		//ValidationContext context = new ValidationContext(Note.class.getSimpleName(), request.getNote(), indicator);
		//if (!getValidationController().validate(context))
		//{
		//	response = new InternalResultsResponse<Note>();
		//	response.setStatus(SystemErrorCategory.SystemValidation);
		//	response.addMessages(context.getMessages());
		//	return response;
		//}

			// Persist
			InternalResponse internalResponse = doPersistenceNote(request.getNote(), persistType);
			if (internalResponse.isInError())
			{
				response = new InternalResultsResponse<Note>();
				response.setStatus(internalResponse.getError());
				response.addMessages(internalResponse.getMessageInfoList());
				response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
						MessageLevel.Object, new Object[] {internalResponse.errorToString()});

				return response;
			}

			// Call maintainReurnList to see if we need to return the menu list and if so whether it should be paged or
			// not
			response = maintainReturnListNote(request.getReturnList(), request.getReturnListPaged(),new Note());

			return response;
				}

		/**
		 * Do persistenceNote.
		 *
		 * @param request the request
		 * @param updateType the update type
		 * @return the internal response
		 */
		private InternalResponse doPersistenceNote(Note menu, PersistenceActionEnum updateType)
		{
			switch (updateType)
			{
				case INSERT:
					return getEmpresaBAR().insertNote(menu);

				case UPDATE:
					return getEmpresaBAR().updateNote(menu);

				case DELETE:
					return getEmpresaBAR().deleteNoteById(menu);
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
		private InternalResultsResponse<Note> maintainReturnListNote(Boolean listIndicator, Boolean pageListIndicator,Note menu)
		{
			// Fetch again if requested.
			if (listIndicator)
			{
				// Fetch Paged is requested.
				if (pageListIndicator)
				{
					PagedInquiryRequest request = new PagedInquiryRequest();
					request.setPreQueryCount(true);
					return fetchNotesByRequest(request);
				}
				else
				{
					// otherwise return all rows not paged
					return fetchAllNotes(menu);
				}
			}
			else
			{
				return new InternalResultsResponse<Note>();
			}
		}

		@Override
		public InternalResultsResponse<Endereco> fetchEnderecosByRequest(EmpresaInquiryRequest request) {
			return getEmpresaBAR().fetchEnderecosByRequest(request);
		}

















		//===================================### FIELD ####======================================
				/**
			/*
			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertField(com.qat.samples.sysmgmt.model.request.FieldMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<DoisValores> insertDoisValores(DoisValoresMaintenanceRequest request)
			{
				InternalResultsResponse<DoisValores> response =
						processDoisValores(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.IDoisValoresBAC#updateDoisValores(com.qat.samples.sysmgmt.model.request.DoisValoresMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<DoisValores> updateDoisValores(DoisValoresMaintenanceRequest request)
			{
				InternalResultsResponse<DoisValores> response =
						processDoisValores(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.IDoisValoresBAC#deleteDoisValores(com.qat.samples.sysmgmt.model.request.DoisValoresMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<DoisValores> deleteDoisValoresById(DoisValoresMaintenanceRequest request)
			{
				InternalResultsResponse<DoisValores> response =
						processDoisValores(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see com.qat.samples.sysmgmt.bac.IDoisValoresBAC#fetchAllDoisValoress(DoisValores field)
			 */
			@Override
			public InternalResultsResponse<DoisValores> fetchAllDoisValoress(DoisValores field)
			{
				InternalResultsResponse<DoisValores> response = new InternalResultsResponse<DoisValores>();
				response.getResultsList().addAll(getDoisValorBAR().fetchAllDoisValoress(field).getResultsList());
				return response;
			}


			/*
			 * (non-Javadoc)
			 * @see com.qat.samples.sysmgmt.bac.IDoisValoresBAC#fetchDoisValoressByRequest(com.qat.samples.sysmgmt.model.request.
			 * PagedInquiryRequest)
			 */
			@Override
			public InternalResultsResponse<DoisValores> fetchDoisValoressByRequest(DoisValoresInquiryRequest request)
			{
				return getDoisValorBAR().fetchDoisValoressByRequest(request);
			}

			/**
			 * Process.
			 *
			 * @param indicator the indicator
			 * @param persistType the persist type
			 * @param request the request
			 * @return the field response
			 */
			private InternalResultsResponse<DoisValores> processDoisValores(ValidationContextIndicator indicator,
					PersistenceActionEnum persistType,
					DoisValoresMaintenanceRequest request)
					{
				InternalResultsResponse<DoisValores> response = null;

				// Validate
				//ValidationContext context = new ValidationContext(DoisValores.class.getSimpleName(), request.getDoisValores(), indicator);
				//if (!getValidationController().validate(context))
				//{
				//	response = new InternalResultsResponse<DoisValores>();
				//	response.setStatus(SystemErrorCategory.SystemValidation);
				//	response.addMessages(context.getMessages());
				//	return response;
				//}

					// Persist
					InternalResponse internalResponse = doPersistenceDoisValores(request.getDoisValor(), persistType);
					if (internalResponse.isInError())
					{
						response = new InternalResultsResponse<DoisValores>();
						response.setStatus(internalResponse.getError());
						response.addMessages(internalResponse.getMessageInfoList());
						response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
								MessageLevel.Object, new Object[] {internalResponse.errorToString()});

						return response;
					}

					// Call maintainReurnList to see if we need to return the field list and if so whether it should be paged or
					// not
					response = maintainReturnListDoisValores(request.getReturnList(), request.getReturnListPaged(),new DoisValores());

					return response;
						}

				/**
				 * Do persistenceDoisValores.
				 *
				 * @param request the request
				 * @param updateType the update type
				 * @return the internal response
				 */
				private InternalResponse doPersistenceDoisValores(DoisValores field, PersistenceActionEnum updateType)
				{
					switch (updateType)
					{
						case INSERT:
							return getDoisValorBAR().insertDoisValores(field);

						case UPDATE:
							return getDoisValorBAR().updateDoisValores(field);

						case DELETE:
							return getDoisValorBAR().deleteDoisValoresById(field);
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
				private InternalResultsResponse<DoisValores> maintainReturnListDoisValores(Boolean listIndicator, Boolean pageListIndicator,DoisValores field)
				{
					// Fetch again if requested.
					if (listIndicator)
					{
						// Fetch Paged is requested.
						if (pageListIndicator)
						{
							DoisValoresInquiryRequest request = new DoisValoresInquiryRequest();
							request.setPreQueryCount(true);
							return fetchDoisValoressByRequest(request);
						}
						else
						{
							// otherwise return all rows not paged
							return fetchAllDoisValoress(field);
						}
					}
					else
					{
						return new InternalResultsResponse<DoisValores>();
					}
				}

				@Override
				public DoisValores fetchDoisValoresById(FetchByIdRequest request) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public InternalResponse deleteAllDoisValoress() {
					// TODO Auto-generated method stub
					return null;
				}

				//===================================### MENU ####======================================
				/**
			/*
			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertNote(com.qat.samples.sysmgmt.model.request.MenuMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<Status> insertStatus(StatusMaintenanceRequest request)
			{
				InternalResultsResponse<Status> response =
						processStatus(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.IStatusBAC#updateStatus(com.qat.samples.sysmgmt.model.request.StatusMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<Status> updateStatus(StatusMaintenanceRequest request)
			{
				InternalResultsResponse<Status> response =
						processStatus(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.IStatusBAC#deleteStatus(com.qat.samples.sysmgmt.model.request.StatusMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<Status> deleteStatus(StatusMaintenanceRequest request)
			{
				InternalResultsResponse<Status> response =
						processStatus(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see com.qat.samples.sysmgmt.bac.IStatusBAC#fetchAllStatuss(Status menu)
			 */
			@Override
			public InternalResultsResponse<Status> fetchAllStatuss(Status menu)
			{
				InternalResultsResponse<Status> response = new InternalResultsResponse<Status>();
				response.getResultsList().addAll(getEmpresaBAR().fetchAllStatuss(menu).getResultsList());
				return response;
			}



			/*
			 * (non-Javadoc)
			 * @see com.qat.samples.sysmgmt.bac.IStatusBAC#fetchStatussByRequest(com.qat.samples.sysmgmt.model.request.
			 * PagedInquiryRequest)
			 */
			@Override
			public InternalResultsResponse<Status> fetchStatussByRequest(PagedInquiryRequest request)
			{
				return getEmpresaBAR().fetchStatussByRequest(request);
			}

			/**
			 * Process.
			 *
			 * @param indicator the indicator
			 * @param persistType the persist type
			 * @param request the request
			 * @return the menu response
			 */
			private InternalResultsResponse<Status> processStatus(ValidationContextIndicator indicator,
					PersistenceActionEnum persistType,
					StatusMaintenanceRequest request)
					{
				InternalResultsResponse<Status> response = null;

				// Validate
				//ValidationContext context = new ValidationContext(Status.class.getSimpleName(), request.getStatus(), indicator);
				//if (!getValidationController().validate(context))
				//{
				//	response = new InternalResultsResponse<Status>();
				//	response.setStatus(SystemErrorCategory.SystemValidation);
				//	response.addMessages(context.getMessages());
				//	return response;
				//}

					// Persist
					InternalResponse internalResponse = doPersistenceStatus(request.getStatus(), persistType);
					if (internalResponse.isInError())
					{
						response = new InternalResultsResponse<Status>();
						response.setStatus(internalResponse.getError());
						response.addMessages(internalResponse.getMessageInfoList());
						response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
								MessageLevel.Object, new Object[] {internalResponse.errorToString()});

						return response;
					}

					// Call maintainReurnList to see if we need to return the menu list and if so whether it should be paged or
					// not
					response = maintainReturnListStatus(request.getReturnList(), request.getReturnListPaged(),new Status());

					return response;
						}

				/**
				 * Do persistenceStatus.
				 *
				 * @param request the request
				 * @param updateType the update type
				 * @return the internal response
				 */
				private InternalResponse doPersistenceStatus(Status menu, PersistenceActionEnum updateType)
				{
					switch (updateType)
					{
						case INSERT:
							return getEmpresaBAR().insertStatus(menu);

						case UPDATE:
							return getEmpresaBAR().updateStatus(menu);

						case DELETE:
							return getEmpresaBAR().deleteStatusById(menu);
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
				private InternalResultsResponse<Status> maintainReturnListStatus(Boolean listIndicator, Boolean pageListIndicator,Status menu)
				{
					// Fetch again if requested.
					if (listIndicator)
					{
						// Fetch Paged is requested.
						if (pageListIndicator)
						{
							PagedInquiryRequest request = new PagedInquiryRequest();
							request.setPreQueryCount(true);
							return fetchStatussByRequest(request);
						}
						else
						{
							// otherwise return all rows not paged
							return fetchAllStatuss(menu);
						}
					}
					else
					{
						return new InternalResultsResponse<Status>();
					}
				}


				//===================================### MENU ####======================================
				/**
			/*
			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertMenu(com.qat.samples.sysmgmt.model.request.MenuMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<Menu> insertMenu(MenuMaintenanceRequest request)
			{
				InternalResultsResponse<Menu> response =
						processMenu(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.IMenuBAC#updateMenu(com.qat.samples.sysmgmt.model.request.MenuMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<Menu> updateMenu(MenuMaintenanceRequest request)
			{
				InternalResultsResponse<Menu> response =
						processMenu(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.IMenuBAC#deleteMenu(com.qat.samples.sysmgmt.model.request.MenuMaintenanceRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<Menu> deleteMenu(MenuMaintenanceRequest request)
			{
				InternalResultsResponse<Menu> response =
						processMenu(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see com.qat.samples.sysmgmt.bac.IMenuBAC#refreshMenus(com.qat.samples.sysmgmt.model.request.RefreshRequest)
			 */
			@Override
			public InternalResultsResponse<Menu> refreshMenus(RefreshRequest request)
			{
				// This method is demo code only. Do not view this as a QAT Global Standard.
				getEmpresaBAR().deleteAllMenus();
				int refreshNumber = request.getRefreshInt();
				refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

				for (int i = 1; i <= refreshNumber; i++)
				{
				getEmpresaBAR().insertMenu(new Menu(i, "MenuDesc" + i));
				}

				// Call maintain to see if we need to return the menu list and if so whether it should be paged or not
				return maintainReturnListMenu(request.getReturnList(), request.getReturnListPaged(),new Menu());
			}

			/*
			 * (non-Javadoc)
			 * @see com.qat.samples.sysmgmt.bac.IMenuBAC#fetchAllMenus(Menu menu)
			 */
			@Override
			public InternalResultsResponse<Menu> fetchAllMenus(Menu menu)
			{
				InternalResultsResponse<Menu> response = new InternalResultsResponse<Menu>();
				response.getResultsList().addAll(getEmpresaBAR().fetchAllMenus(menu).getResultsList());
				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see
			 * com.qat.samples.sysmgmt.bac.IMenuBAC#fetchMenuById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
			 * )
			 */
			@Override
			public InternalResultsResponse<Menu> fetchMenuById(FetchByIdRequest request)
			{
				InternalResultsResponse<Menu> response = new InternalResultsResponse<Menu>();
				// validate fetchId field
				if (ValidationUtil.isNull(request.getFetchId()))
				{
					response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
					response.setStatus(SystemErrorCategory.SystemValidation);
				}
				else
				{
					response.getResultsList().add(getEmpresaBAR().fetchMenuById(request));
				}

				return response;
			}

			/*
			 * (non-Javadoc)
			 * @see com.qat.samples.sysmgmt.bac.IMenuBAC#fetchMenusByRequest(com.qat.samples.sysmgmt.model.request.
			 * PagedInquiryRequest)
			 */
			@Override
			public InternalResultsResponse<Menu> fetchMenusByRequest(PagedInquiryRequest request)
			{
				return getEmpresaBAR().fetchMenusByRequest(request);
			}

			/**
			 * Process.
			 *
			 * @param indicator the indicator
			 * @param persistType the persist type
			 * @param request the request
			 * @return the menu response
			 */
			private InternalResultsResponse<Menu> processMenu(ValidationContextIndicator indicator,
					PersistenceActionEnum persistType,
					MenuMaintenanceRequest request)
					{
				InternalResultsResponse<Menu> response = null;

				// Validate
				//ValidationContext context = new ValidationContext(Menu.class.getSimpleName(), request.getMenu(), indicator);
				//if (!getValidationController().validate(context))
				//{
				//	response = new InternalResultsResponse<Menu>();
				//	response.setStatus(SystemErrorCategory.SystemValidation);
				//	response.addMessages(context.getMessages());
				//	return response;
				//}

					// Persist
					InternalResponse internalResponse = doPersistenceMenu(request.getMenu(), persistType);
					if (internalResponse.isInError())
					{
						response = new InternalResultsResponse<Menu>();
						response.setStatus(internalResponse.getError());
						response.addMessages(internalResponse.getMessageInfoList());
						response.addMessage(DEFAULT_EMPRESA_BAC_EXCEPTION_MSG, MessageSeverity.Error,
								MessageLevel.Object, new Object[] {internalResponse.errorToString()});

						return response;
					}

					// Call maintainReurnList to see if we need to return the menu list and if so whether it should be paged or
					// not
					response = maintainReturnListMenu(request.getReturnList(), request.getReturnListPaged(),new Menu());

					return response;
						}

				/**
				 * Do persistenceMenu.
				 *
				 * @param request the request
				 * @param updateType the update type
				 * @return the internal response
				 */
				private InternalResponse doPersistenceMenu(Menu menu, PersistenceActionEnum updateType)
				{
					switch (updateType)
					{
						case INSERT:
							return getEmpresaBAR().insertMenu(menu);

						case UPDATE:
							return getEmpresaBAR().updateMenu(menu);

						case DELETE:
							return getEmpresaBAR().deleteMenuById(menu);
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
				private InternalResultsResponse<Menu> maintainReturnListMenu(Boolean listIndicator, Boolean pageListIndicator,Menu menu)
				{
					// Fetch again if requested.
					if (listIndicator)
					{
						// Fetch Paged is requested.
						if (pageListIndicator)
						{
							PagedInquiryRequest request = new PagedInquiryRequest();
							request.setPreQueryCount(true);
							return fetchMenusByRequest(request);
						}
						else
						{
							// otherwise return all rows not paged
							return fetchAllMenus(menu);
						}
					}
					else
					{
						return new InternalResultsResponse<Menu>();
					}
				}

				@Override
				public InternalResultsResponse<Message> insertMessage(MessageMaintenanceRequest request) {
					InternalResultsResponse<Message> response =
							processMessage(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
					return response;
				}

				@Override
				public InternalResultsResponse<Message> updateMessage(MessageMaintenanceRequest request) {
					InternalResultsResponse<Message> response =
							processMessage(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
					return response;
				}

				@Override
				public InternalResultsResponse<Message> deleteMessage(MessageMaintenanceRequest request) {
					InternalResultsResponse<Message> response =
							processMessage(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
					return response;
				}

				@Override
				public InternalResultsResponse<Message> fetchMessagesByRequest(MessageInquiryRequest request) {
					return getEmpresaBAR().fetchMessagesByRequest(request);
				}

}
