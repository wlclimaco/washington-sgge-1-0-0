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
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaMaintenanceRequest;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;

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

	// Validate
	ValidationContext context = new ValidationContext(Filial.class.getSimpleName(), request.getFilial(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Filial>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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

	// Validate
	ValidationContext context = new ValidationContext(Deposito.class.getSimpleName(), request.getDeposito(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Deposito>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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

	// Validate
	ValidationContext context = new ValidationContext(Usuario.class.getSimpleName(), request.getUsuario(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Usuario>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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

	// Validate
	ValidationContext context = new ValidationContext(Advocacia.class.getSimpleName(), request.getAdvocacia(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Advocacia>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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

	// Validate
	ValidationContext context = new ValidationContext(Clinica.class.getSimpleName(), request.getClinica(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Clinica>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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

	// Validate
	ValidationContext context = new ValidationContext(Condominio.class.getSimpleName(), request.getCondominio(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Condominio>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

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
}
