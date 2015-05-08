package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Document;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class DocumentValidator.
 *
 * @author aporto
 * @version 1.0
 * @created 22-Aug-2014 10:00:00 AM
 */
public class DocumentValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant OTHER. */
	private static final String OTHER = "OTHER";

	/** The Constant EIGHTY. */
	private static final Integer EIGTHY = 80;

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILED_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILING_STATUS_REQUIRED =
			"sendsolv.base.documentvalidator.filed.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_NOTE_TEXT. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_NOTE_TEXT_TOO_BIG =
			"sendsolv.base.documentvalidator.note.text.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED =
			"sendsolv.base.documentvalidator.document.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED =
			"sendsolv.base.documentvalidator.parent.key.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED =
			"sendsolv.base.documentvalidator.parent.key.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED =
			"sendsolv.base.documentvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_REQUIRED =
			"sendsolv.base.documentvalidator.document.required";

	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_KEYWORD_TEXT_TOO_BIG =
			"sendsolv.base.documentvalidator.keyword.text.too.big";

	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_OTHER_NEED_NOTE_TEXT =
			"sendsolv.base.documentvalidator.type.other.text.required";;

			/*
			 * (non-Javadoc)
			 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
			 */
			@Override
			public void validate(ValidationContext validationContext)
			{
				Document document =
						(Document)validationContext.getObjectToBeValidated(Document.class.getSimpleName());

				// Do the basic check first: do I even have an Object to validate ?
				if (ValidationUtil.isNull(document))
				{
					validationContext.getMessages().add(
							new MessageInfo(PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_REQUIRED,
									Message.MessageSeverity.Error,
									Message.MessageLevel.Field));
					return;
				}

				switch (validationContext.getValidationContextIndicator())
				{
					case INSERT:
						validateAll(validationContext.getMessages(), document);
						break;
					case UPDATE:
						validateId(validationContext.getMessages(), document);
						validateAll(validationContext.getMessages(), document);
						break;
					case DELETE:
						validateId(validationContext.getMessages(), document);
						break;
					default:
						validateAll(validationContext.getMessages(), document);
						break;
				}

				if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
				{
					validateChangeControlFields(validationContext.getMessages(), document, validationContext);
				}

			}

			/**
			 * Validate all.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateAll(List<MessageInfo> list, Document document)
			{
				validateParentKey(list, document);
				validateParentKeyType(list, document);
				validateDocumentType(list, document);
				validateNoteText(list, document);
				validateTypeOther(list, document);
				validateKeywordText(list, document);
				validateFilingStatus(list, document);
			}

			/**
			 * Validate id.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateId(List<MessageInfo> list, Document document)
			{
				if (ValidationUtil.isNullOrZero(document.getId()))
				{
					list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
				}
			}

			/**
			 * Validate parent key.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateParentKey(List<MessageInfo> list, Document document)
			{
				ValidationUtil.isNullOrZero(document.getParentKey(),
						PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED,
						list);
			}

			/**
			 * Validate parent key type.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateParentKeyType(List<MessageInfo> list, Document document)
			{
				ValidationUtil.isNull(document.getParentKeyType(),
						PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED,
						list);
			}

			/**
			 * Validate document type.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateDocumentType(List<MessageInfo> list, Document document)
			{
				ValidationUtil.isNull(document.getDocumentType(),
						PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED, list);
			}

			/**
			 * Validate note text.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateNoteText(List<MessageInfo> list, Document document)
			{
				if (ValidationUtil.isNull(document.getNoteText()))
				{
					return;
				}
				if (document.getNoteText().length() > EIGTHY)
				{
					list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_NOTE_TEXT_TOO_BIG,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
				}
			}

			/**
			 * Validate type other.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateTypeOther(List<MessageInfo> list, Document document)
			{
				if (ValidationUtil.isNull(document.getDocumentType())
				|| ValidationUtil.isNull(document.getDocumentType().getName()))
				{
					return;
				}

				if (document.getDocumentType().getName().equalsIgnoreCase(OTHER)
						&& ValidationUtil.isNull(document.getNoteText()))
				{
					list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_OTHER_NEED_NOTE_TEXT,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
				}
			}

			/**
			 * Validate keyword text.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateKeywordText(List<MessageInfo> list, Document document)
			{
				if (ValidationUtil.isNull(document.getKeywordText()))
				{
					return;
				}
				if (document.getKeywordText().length() > EIGTHY)
				{
					list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_KEYWORD_TEXT_TOO_BIG,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
				}
			}

			/**
			 * Validate filing status.
			 *
			 * @param list the list
			 * @param document the document
			 */
			private void validateFilingStatus(List<MessageInfo> list, Document document)
			{
				if (BusinessTypeEnum.MEMBER.equals(document.getParentKeyType()))
				{
					return;
				}

				ValidationUtil.isNull(document.getFilingStatus(),
						PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILING_STATUS_REQUIRED, list);
			}
}
