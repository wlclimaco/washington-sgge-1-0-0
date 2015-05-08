package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class EmploymentInfoListValidator.
 */
public class EmploymentInfoListValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmploymentInfoListValidator.class);

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENT_ALREADY_EXIST. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENT_ALREADY_EXIST =
			"sendsolv.base.employmentinfovalidator.employment.already.exist";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENTINFOSTATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENTINFOSTATUS_REQUIRED =
			"sendsolv.base.employmentinfovalidator.employmentinfostatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYEE_ID_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYEE_ID_TOO_BIG =
			"sendsolv.base.employmentinfovalidator.employee.id.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_JOBTITLE_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_JOBTITLE_TOO_BIG =
			"sendsolv.base.employmentinfovalidator.jobtitle.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED =
			"sendsolv.base.membervalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_LOCATION_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_LOCATION_ID_REQUIRED =
			"sendsolv.base.employmentinfovalidator.location.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED =
			"sendsolv.base.employmentinfovalidator.id.required";

	/** The Constant EMPLOYMENT_INFO_LIST_KEY. */
	private static final String EMPLOYMENT_INFO_LIST_KEY = "EmploymentInfoList";

	/** The Constant FIFTY. */
	private static final Integer FIFTY = 50;

	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The person dac. */
	private IPersonDAC personDAC;

	/**
	 * Gets the person dac.
	 *
	 * @return the person dac
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * Sets the person dac.
	 *
	 * @param personDAC the person dac
	 */
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<EmploymentInfo> employmentInfoList =
				(List<EmploymentInfo>)validationContext.getObjectToBeValidated(EMPLOYMENT_INFO_LIST_KEY);

		if (ValidationUtil.isNullOrEmpty(employmentInfoList))
		{
			return;
		}

		for (EmploymentInfo employmentInfo : employmentInfoList)
		{
			validateEmploymentInfo(employmentInfo, validationContext);

			if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
			{
				validateChangeControlFields(validationContext.getMessages(), employmentInfo, validationContext);
			}
		}
	}

	/**
	 * Validate employment info.
	 *
	 * @param employmentInfo the employment info
	 * @param context the context
	 */
	private void validateEmploymentInfo(EmploymentInfo employmentInfo, ValidationContext context)
	{
		switch (employmentInfo.getModelAction())
		{
			case INSERT:
				validateAll(context.getMessages(), employmentInfo);
				break;
			case UPDATE:
				validateId(context.getMessages(), employmentInfo);
				validateAll(context.getMessages(), employmentInfo);
				break;
			case DELETE:
				validateId(context.getMessages(), employmentInfo);
				break;
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Model action for Employment info Validator missing!");
				}
				break;
		}
	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param employmentInfo the employment info
	 */
	private void validateAll(List<MessageInfo> list, EmploymentInfo employmentInfo)
	{
		validateLocationId(list, employmentInfo);
		validateEmploymentInfoStatus(list, employmentInfo);
		validateJobTitle(list, employmentInfo);
		validateEmployeeId(list, employmentInfo);

		if (!PersistanceActionEnum.INSERT.equals(employmentInfo.getModelAction()))
		{
			validateMemberId(list, employmentInfo);

			if (!ValidationUtil.isNullOrEmpty(list))
			{
				return;
			}

			validateEmploymentInformation(employmentInfo, list);
		}
	}

	/**
	 * Validate employment information.
	 *
	 * @param employmentInfo the employment info
	 * @param list the list
	 */
	private void validateEmploymentInformation(EmploymentInfo employmentInfo, List<MessageInfo> list)
	{

		InternalResultsResponse<EmploymentInfo> response = getPersonDAC().fetchEmploymentInfoByMemberId(employmentInfo);

		if (!response.hasResults())
		{
			return;
		}

		if (PersistanceActionEnum.INSERT.equals(employmentInfo.getModelAction())
				|| !(employmentInfo.getId().equals(response.getFirstResult().getId())))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENT_ALREADY_EXIST,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}

	}

	/**
	 * Validate id.
	 *
	 * @param list the list
	 * @param employmentInfo the employment info
	 */
	private void validateId(List<MessageInfo> list, EmploymentInfo employmentInfo)
	{
		if (ValidationUtil.isNullOrZero(employmentInfo.getId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate location id.
	 *
	 * @param list the list
	 * @param employmentInfo the employment info
	 */
	private void validateLocationId(List<MessageInfo> list, EmploymentInfo employmentInfo)
	{
		if (ValidationUtil.isNullOrZero(employmentInfo.getLocationId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_LOCATION_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate member id.
	 *
	 * @param list the list
	 * @param employmentInfo the employment info
	 */
	private void validateMemberId(List<MessageInfo> list, EmploymentInfo employmentInfo)
	{
		if (ValidationUtil.isNullOrZero(employmentInfo.getMemberId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate employment info status.
	 *
	 * @param list the list
	 * @param employmentInfo the employment info
	 */
	private void validateEmploymentInfoStatus(List<MessageInfo> list, EmploymentInfo employmentInfo)
	{
		ValidationUtil.isNullOrZero(employmentInfo.getEmploymentInfoStatusValue(),
				PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENTINFOSTATUS_REQUIRED, list);
	}

	/**
	 * Validate job title.
	 *
	 * @param messages the messages
	 * @param employmentInfo the employment info
	 */
	private void validateJobTitle(List<MessageInfo> messages, EmploymentInfo employmentInfo)
	{
		if (!ValidationUtil.isNull(employmentInfo.getJobTitle()) &&
				(employmentInfo.getJobTitle().length() > FIFTY))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_JOBTITLE_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}

	}

	/**
	 * Validate employee id.
	 *
	 * @param messages the messages
	 * @param employmentInfo the employment info
	 */
	private void validateEmployeeId(List<MessageInfo> messages, EmploymentInfo employmentInfo)
	{
		if (!ValidationUtil.isNull(employmentInfo.getEmployeeId()) &&
				(employmentInfo.getEmployeeId().length() > TWENTY))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYEE_ID_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}
}
