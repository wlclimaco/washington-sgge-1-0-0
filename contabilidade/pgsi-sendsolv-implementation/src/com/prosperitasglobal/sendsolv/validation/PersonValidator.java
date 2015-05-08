package com.prosperitasglobal.sendsolv.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.PersonTypeEnum;
import com.prosperitasglobal.sendsolv.model.RiskLevelEnum;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PersonValidator.
 */
public class PersonValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_RISK_NOTE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_RISK_NOTE_REQUIRED =
			"sendsolv.base.personvalidator.risk.note.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PARTICIPANT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PARTICIPANT_ID_REQUIRED =
			"sendsolv.base.personvalidator.participant.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_GENDER_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_GENDER_REQUIRED =
			"sendsolv.base.personvalidator.gender.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED =
			"sendsolv.base.personvalidator.last.name.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED =
			"sendsolv.base.personvalidator.first.name.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG =
			"sendsolv.base.base.personvalidator.risklevelnote.toobig";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED =
			"sendsolv.base.personvalidator.risklevel.required";

	/** The Constant TWO_HUNDRED_FIFTY_FIVE. */
	private static final int TWO_HUNDRED_FIFTY_FIVE = 255;

	/** The Constant FORTY. */
	private static final Integer FORTY = 40;

	/** The Constant CONTACT_LIST_KEY. */
	protected static final String CONTACT_LIST_KEY = "ContactList";

	/** The Constant NOTE_LIST_KEY. */
	private static final String NOTE_LIST_KEY = "NoteList";

	/** The Constant NAME_LIST_KEY. */
	private static final String NAME_LIST_KEY = "NameList";

	/** The Constant DOCUMENT_LIST_KEY. */
	private static final String DOCUMENT_LIST_KEY = "DocumentList";

	/** The Constant SSN. */
	private static final String SSN = "^[0-9]{9}$";

	/** The Constant SSN_NAME. */
	private static final String SSN_NAME = "Social Security Number ";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_DATE_OF_BIRTH_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_DATE_OF_BIRTH_REQUIRED =
			"sendsolv.base.personvalidator.date.of.birth.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.last.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.middle.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.first.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_PERSON_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_PERSON_REQUIRED =
			"sendsolv.base.personvalidator.person.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_TYPE_REQUIRED =
			"sendsolv.base.personvalidator.persontype.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.personvalidator.personid.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PEP_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PEP_STATUS_REQUIRED =
			"sendsolv.base.personvalidator.pepstatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED =
			"sendsolv.base.personvalidator.personstatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED =
			"sendsolv.base.personvalidator.contactlist.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED =
			"sendsolv.base.personvalidator.risk.required";

	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_WRONG_SSN =
			"sendsolv.base.personvalidator.wrong.ssn";

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
	@Override
	public void validate(ValidationContext validationContext)
	{
		Person person =
				(Person)validationContext.getObjectToBeValidated(Business.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(person))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_PERSON_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}
		performValidation(validationContext, person);
	}

	/**
	 * Perform validation.
	 *
	 * @param validationContext the validation context
	 * @param person the person
	 */
	protected void performValidation(ValidationContext validationContext, Person person)
	{
		validatePersonType(validationContext.getMessages(), person);

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateId(validationContext.getMessages(), person);
				break;
			case INSERT:
				validateAll(validationContext.getMessages(), person);
				break;
			case UPDATE:
				validateId(validationContext.getMessages(), person);
				validateAll(validationContext.getMessages(), person);
				break;
			case FETCH_BY_ID:
				validateApplyStatus(validationContext.getMessages(), person);
				break;
			default:
				validateAll(validationContext.getMessages(), person);
				break;
		}

		if (ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validationContext.setStopProcessing(true);
			return;
		}

		validateChangeControlFields(validationContext.getMessages(), person, validationContext);

		if (!ValidationUtil.isNullOrEmpty(person.getContactList()))
		{
			validationContext.getObjectsToBeValidated().put(CONTACT_LIST_KEY,
					person.getContactList());
		}

		if (!ValidationUtil.isNullOrEmpty(person.getNoteList()))
		{
			validationContext.getObjectsToBeValidated().put(NOTE_LIST_KEY,
					person.getNoteList());
		}

		if (!ValidationUtil.isNullOrEmpty(person.getNameList()))
		{
			validationContext.getObjectsToBeValidated().put(NAME_LIST_KEY,
					person.getNameList());
		}

		if (!ValidationUtil.isNullOrEmpty(person.getDocumentList()))
		{
			validationContext.getObjectsToBeValidated().put(DOCUMENT_LIST_KEY,
					person.getDocumentList());
		}
	}

	/**
	 * Validate apply status.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	private void validateApplyStatus(List<MessageInfo> messages, Person person)
	{
		validateParticipantId(messages, person);
		validatePersonStatus(messages, person);
		validateSSN(messages, person);
		validateRisk(messages, person);
		validateDateOfBirth(messages, person);
		validateFirstName(messages, person);
		validateLastName(messages, person);
		validateContactList(messages, person);
	}

	/**
	 * Validate all.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	private void validateAll(List<MessageInfo> messages, Person person)
	{
		if (!PersistanceActionEnum.NONE.equals(person.getModelAction()))
		{
			validatePersonStatus(messages, person);
			validatePepStatus(messages, person);
			validateContactList(messages, person);
			validateDateOfBirth(messages, person);
			validateFirstName(messages, person);
			validateMiddleName(messages, person);
			validateLastName(messages, person);
			validateGender(messages, person);
			validateSSN(messages, person);
		}

		validateRisk(messages, person);
	}

	/**
	 * Validate contact list.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	private void validateContactList(List<MessageInfo> messages, Person person)
	{
		if (ValidationUtil.isNullOrEmpty(person.getContactList()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
		}
	}

	/**
	 * Validate pep status.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	private void validatePepStatus(List<MessageInfo> messages, Person person)
	{
		ValidationUtil.isNullOrZero(person.getPepStatusValue(),
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PEP_STATUS_REQUIRED, messages);

	}

	/**
	 * Validate person status.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	private void validatePersonStatus(List<MessageInfo> messages, Person person)
	{
		if (ValidationUtil.isNullOrZero(person.getPersonStatusValue()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {validateValueToBeReturn(person)}));
		}
	}

	/**
	 * Validate person type.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validatePersonType(List<MessageInfo> messages, Person person)
	{
		ValidationUtil.isNullOrZero(person.getPersonTypeValue(),
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_TYPE_REQUIRED, messages);
	}

	/**
	 * Validate id.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateId(List<MessageInfo> messages, Person person)
	{
		ValidationUtil.isNullOrZero(person.getId(),
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_ID_REQUIRED, messages);
	}

	/**
	 * Validate date of birth.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateDateOfBirth(List<MessageInfo> messages, Person person)
	{
		if (ValidationUtil.isNull(person.getPersonType())
				|| !PersonTypeEnum.MEMBER.equals(person.getPersonType()))
		{
			return;
		}

		if (ValidationUtil.isNullOrZero(person.getDateOfBirth()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_DATE_OF_BIRTH_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
		}
	}

	/**
	 * Validate first name.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateFirstName(List<MessageInfo> messages, Person person)
	{
		if (ValidationUtil.isNullOrEmpty(person.getFirstName()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
			return;
		}

		if (person.getFirstName().length() > FORTY)
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
		}

	}

	/**
	 * Validate middle name.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateMiddleName(List<MessageInfo> messages, Person person)
	{
		if (!ValidationUtil.isNullOrEmpty(person.getMiddleName()))
		{
			if (person.getMiddleName().length() > FORTY)
			{
				messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG,
						Message.MessageSeverity.Error,
						Message.MessageLevel.Field));
			}
		}
	}

	/**
	 * Validate last name.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateLastName(List<MessageInfo> messages, Person person)
	{
		if (ValidationUtil.isNullOrEmpty(person.getLastName()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
			return;
		}

		if (person.getLastName().length() > FORTY)
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
		}

	}

	/**
	 * Validate risk.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateRisk(List<MessageInfo> messages, Person person)
	{
		if (ValidationUtil.isNull(person.getRisk()))
		{
			messages.add(new MessageInfo(SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
			return;
		}

		if (!PersistanceActionEnum.NONE.equals(person.getRisk().getModelAction()))
		{
			validateRiskLevel(messages, person);
			validateRiskLevelNote(messages, person);

			person.setModelAction(person.getRisk().getModelAction());
		}

	}

	/**
	 * Validate gender.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateGender(List<MessageInfo> messages, Person person)
	{
		if (ValidationUtil.isNull(person.getGenderValue()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_GENDER_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}
	}

	/**
	 * Validate ssn.
	 *
	 * @param messages the messages
	 * @param person the person
	 */
	protected void validateSSN(List<MessageInfo> messages, Person person)
	{

		if (ValidationUtil.isNullOrEmpty(person.getDocumentList()))
		{
			return;
		}

		for (Document document : person.getDocumentList())
		{
			if (!ValidationUtil.isNull(document.getDocumentType())
					&& !ValidationUtil.isNull(document.getDocumentType().getName())
					&& document.getDocumentType().getName().matches(SSN_NAME))
			{
				Pattern pattern = Pattern.compile(SSN);
				Matcher matcher = pattern.matcher(document.getValue());

				if (!matcher.matches())
				{
					messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_WRONG_SSN,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field,
							new Object[] {validateValueToBeReturn(person)}));
				}
				return;
			}
		}
	}

	private void validateRiskLevel(List<MessageInfo> list, Person person)
	{

		if (ValidationUtil.isNull(person.getRisk().getRiskLevel()))
		{
			list.add(new MessageInfo(SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
		}
	}

	/**
	 * Validate risk level note.
	 *
	 * @param list the list
	 * @param risk the risk
	 */
	private void validateRiskLevelNote(List<MessageInfo> list, Person person)
	{
		if (RiskLevelEnum.UNKNOWN.equals(person.getRisk().getRiskLevel()))
		{
			return;
		}
		if (ValidationUtil.isNull(person.getRisk().getRiskLevelNote()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_RISK_NOTE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
			return;
		}

		if (person.getRisk().getRiskLevelNote().length() > TWO_HUNDRED_FIFTY_FIVE)
		{
			list.add(new MessageInfo(SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(person)}));
		}

	}

	/**
	 * Validate participant id.
	 *
	 * @param list the list
	 * @param person the person
	 */
	private void validateParticipantId(List<MessageInfo> list, Person person)
	{
		if (ValidationUtil.isNullOrEmpty(person.getParticipantId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PARTICIPANT_ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {validateValueToBeReturn(person)}));
		}
	}

	/**
	 * Validate value to be return.
	 *
	 * @param person the person
	 * @return the string
	 */
	protected String validateValueToBeReturn(Person person)
	{
		if (!ValidationUtil.isNullOrEmpty(person.getParticipantId()))
		{
			return person.getParticipantId();
		}

		if (!ValidationUtil.isNullOrEmpty(person.getFirstName()))
		{
			return person.getFirstName();
		}

		if (ValidationUtil.isNullOrEmpty(person.getParticipantId())
				&& ValidationUtil.isNullOrEmpty(person.getFirstName()))
		{
			return person.getPersonType() + " " + String.valueOf(person.getId());
		}
		return null;
	}

}
