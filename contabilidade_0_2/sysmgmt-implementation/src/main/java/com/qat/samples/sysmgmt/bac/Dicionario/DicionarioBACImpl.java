package com.qat.samples.sysmgmt.bac.Dicionario;


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
import com.qat.samples.sysmgmt.bar.Dicionario.IDicionarioBAR;
import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.dicionario.Field;
import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.ClassesMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Dicionario leveraging the injected IDicionarioBAR.
 */
@Component
public class DicionarioBACImpl implements IDicionarioBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_DICIONARIO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_DICIONARIO_BAC_EXCEPTION_MSG = "sysmgmt.base.Dicionariobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DicionarioBACImpl.class);

	/** The Dicionario BAR. */
	private IDicionarioBAR dicionarioBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Spring Sets the Dicionario BAR.
	 *
	 * @param DicionarioBAR the new Dicionario BAR
	 */
	public void setDicionarioBAR(IDicionarioBAR dicionarioBAR)
	{
		this.dicionarioBAR = dicionarioBAR;
	}

	/**
	 * Gets the Dicionario BAR.
	 *
	 * @return the Dicionario BAR
	 */
	public IDicionarioBAR getDicionarioBAR()
	{
		return dicionarioBAR;
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

//===================================### CLASSES ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertClasses(com.qat.samples.sysmgmt.model.request.ClassesMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Classes> insertClasses(ClassesMaintenanceRequest request)
{
	InternalResultsResponse<Classes> response =
			processClasses(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClassesBAC#updateClasses(com.qat.samples.sysmgmt.model.request.ClassesMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Classes> updateClasses(ClassesMaintenanceRequest request)
{
	InternalResultsResponse<Classes> response =
			processClasses(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClassesBAC#deleteClasses(com.qat.samples.sysmgmt.model.request.ClassesMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Classes> deleteClasses(ClassesMaintenanceRequest request)
{
	InternalResultsResponse<Classes> response =
			processClasses(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClassesBAC#refreshClassess(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Classes> refreshClassess(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDicionarioBAR().deleteAllClassess();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDicionarioBAR().insertClasses(new Classes(i, "ClassesDesc" + i));
	}

	// Call maintain to see if we need to return the classes list and if so whether it should be paged or not
	return maintainReturnListClasses(request.getReturnList(), request.getReturnListPaged(),new Classes());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClassesBAC#fetchAllClassess(Classes classes)
 */
@Override
public InternalResultsResponse<Classes> fetchAllClassess(Classes classes)
{
	InternalResultsResponse<Classes> response = new InternalResultsResponse<Classes>();
	response.getResultsList().addAll(getDicionarioBAR().fetchAllClassess(classes).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IClassesBAC#fetchClassesById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Classes> fetchClassesById(FetchByIdRequest request)
{
	InternalResultsResponse<Classes> response = new InternalResultsResponse<Classes>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDicionarioBAR().fetchClassesById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IClassesBAC#fetchClassessByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Classes> fetchClassessByRequest(ClassesInquiryRequest request)
{
	return getDicionarioBAR().fetchClassessByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the classes response
 */
private InternalResultsResponse<Classes> processClasses(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		ClassesMaintenanceRequest request)
		{
	InternalResultsResponse<Classes> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Classes.class.getSimpleName(), request.getClasses(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Classes>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceClasses(request.getClasses(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Classes>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DICIONARIO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the classes list and if so whether it should be paged or
		// not
		response = maintainReturnListClasses(request.getReturnList(), request.getReturnListPaged(),new Classes());

		return response;
			}

	/**
	 * Do persistenceClasses.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceClasses(Classes classes, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDicionarioBAR().insertClasses(classes);

			case UPDATE:
				return getDicionarioBAR().updateClasses(classes);

			case DELETE:
				return getDicionarioBAR().deleteClassesById(classes);
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
	private InternalResultsResponse<Classes> maintainReturnListClasses(Boolean listIndicator, Boolean pageListIndicator,Classes classes)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				ClassesInquiryRequest request = new ClassesInquiryRequest();
				request.setPreQueryCount(true);
				return fetchClassessByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllClassess(classes);
			}
		}
		else
		{
			return new InternalResultsResponse<Classes>();
		}
	}

//===================================### INTERFACE ####======================================
	/**
/*
/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.ICountyBAC#insertInterface(com.qat.samples.sysmgmt.model.request.InterfaceMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Interface> insertInterface(InterfaceMaintenanceRequest request)
{
	InternalResultsResponse<Interface> response =
			processInterface(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInterfaceBAC#updateInterface(com.qat.samples.sysmgmt.model.request.InterfaceMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Interface> updateInterface(InterfaceMaintenanceRequest request)
{
	InternalResultsResponse<Interface> response =
			processInterface(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInterfaceBAC#deleteInterface(com.qat.samples.sysmgmt.model.request.InterfaceMaintenanceRequest
 * )
 */
@Override
public InternalResultsResponse<Interface> deleteInterface(InterfaceMaintenanceRequest request)
{
	InternalResultsResponse<Interface> response =
			processInterface(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInterfaceBAC#refreshInterfaces(com.qat.samples.sysmgmt.model.request.RefreshRequest)
 */
@Override
public InternalResultsResponse<Interface> refreshInterfaces(RefreshRequest request)
{
	// This method is demo code only. Do not view this as a QAT Global Standard.
	getDicionarioBAR().deleteAllInterfaces();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDicionarioBAR().insertInterface(new Interface(i, "InterfaceDesc" + i,"dd"));
	}

	// Call maintain to see if we need to return the interface list and if so whether it should be paged or not
	return maintainReturnListInterface(request.getReturnList(), request.getReturnListPaged(),new Interface());
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInterfaceBAC#fetchAllInterfaces(Interface interface)
 */
@Override
public InternalResultsResponse<Interface> fetchAllInterfaces(Interface interfaces)
{
	InternalResultsResponse<Interface> response = new InternalResultsResponse<Interface>();
	response.getResultsList().addAll(getDicionarioBAR().fetchAllInterfaces(interfaces).getResultsList());
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bac.IInterfaceBAC#fetchInterfaceById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest
 * )
 */
@Override
public InternalResultsResponse<Interface> fetchInterfaceById(FetchByIdRequest request)
{
	InternalResultsResponse<Interface> response = new InternalResultsResponse<Interface>();
	// validate fetchId field
	if (ValidationUtil.isNull(request.getFetchId()))
	{
		response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
		response.setStatus(SystemErrorCategory.SystemValidation);
	}
	else
	{
		response.getResultsList().add(getDicionarioBAR().fetchInterfaceById(request));
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bac.IInterfaceBAC#fetchInterfacesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Interface> fetchInterfacesByRequest(InterfaceInquiryRequest request)
{
	return getDicionarioBAR().fetchInterfacesByRequest(request);
}

/**
 * Process.
 *
 * @param indicator the indicator
 * @param persistType the persist type
 * @param request the request
 * @return the interface response
 */
private InternalResultsResponse<Interface> processInterface(ValidationContextIndicator indicator,
		PersistenceActionEnum persistType,
		InterfaceMaintenanceRequest request)
		{
	InternalResultsResponse<Interface> response = null;

	// Validate
	ValidationContext context = new ValidationContext(Interface.class.getSimpleName(), request.getInterface(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Interface>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceInterface(request.getInterface(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Interface>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DICIONARIO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object, new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the interface list and if so whether it should be paged or
		// not
		response = maintainReturnListInterface(request.getReturnList(), request.getReturnListPaged(),new Interface());

		return response;
			}

	/**
	 * Do persistenceInterface.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistenceInterface(Interface interfaces, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDicionarioBAR().insertInterface(interfaces);

			case UPDATE:
				return getDicionarioBAR().updateInterface(interfaces);

			case DELETE:
				return getDicionarioBAR().deleteInterfaceById(interfaces);
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
	private InternalResultsResponse<Interface> maintainReturnListInterface(Boolean listIndicator, Boolean pageListIndicator,Interface interfaces)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				InterfaceInquiryRequest request = new InterfaceInquiryRequest();
				request.setPreQueryCount(true);
				return fetchInterfacesByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllInterfaces(interfaces);
			}
		}
		else
		{
			return new InternalResultsResponse<Interface>();
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
	getDicionarioBAR().deleteAllFields();
	int refreshNumber = request.getRefreshInt();
	refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

	for (int i = 1; i <= refreshNumber; i++)
	{
	getDicionarioBAR().insertField(new Field());
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
	response.getResultsList().addAll(getDicionarioBAR().fetchAllFields(field).getResultsList());
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
		response.getResultsList().add(getDicionarioBAR().fetchFieldById(request));
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
	return getDicionarioBAR().fetchFieldsByRequest(request);
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
	ValidationContext context = new ValidationContext(Field.class.getSimpleName(), request.getField(), indicator);
	if (!getValidationController().validate(context))
	{
		response = new InternalResultsResponse<Field>();
		response.setStatus(SystemErrorCategory.SystemValidation);
		response.addMessages(context.getMessages());
		return response;
	}

		// Persist
		InternalResponse internalResponse = doPersistenceField(request.getField(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Field>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DICIONARIO_BAC_EXCEPTION_MSG, MessageSeverity.Error,
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
				return getDicionarioBAR().insertField(field);

			case UPDATE:
				return getDicionarioBAR().updateField(field);

			case DELETE:
				return getDicionarioBAR().deleteFieldById(field);
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
}
