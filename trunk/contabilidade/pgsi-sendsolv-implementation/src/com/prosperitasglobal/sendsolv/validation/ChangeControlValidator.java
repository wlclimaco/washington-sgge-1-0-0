package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.UserContext;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ChangeControlValidator.
 * This class implements methods that validate fields that are common to all model objects in PGSi.
 * These fields control changes in the object itself: Create/Update Date, Create/Update User.
 * Notice that user is retrieved from UserContext.
 * All PGSi Validators should inherit from this abstract Validator.
 */
public abstract class ChangeControlValidator
{

	public static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_USERCONTEXT_REQUIRED =
			"sendsolv.base.businessvalidator.usercontext.required";

	/** The Constant PROSPERITASGLOBAL_BASE_USERCONTEXTVALIDATOR_USERID_REQUIRED. */
	public static final String PROSPERITASGLOBAL_BASE_USERCONTEXTVALIDATOR_USERID_REQUIRED =
			"sendsolv.base.usercontextvalidator.userid.required";

	/**
	 * Validate change control fields and adds dates.
	 *
	 * @param list the list of messages
	 * @param model the QATModel being validated
	 * @param userContext the user context
	 * @throws Exception
	 */
	protected void validateChangeControlFields(List<MessageInfo> list, QATModel model,
			ValidationContext validationContext)
	{
		UserContext userContext =
				(UserContext)validationContext.getObjectToBeValidated(UserContext.class.getSimpleName());

		// do I have UserContext ?
		if (ValidationUtil.isNull(userContext))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_USERCONTEXT_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}

		if (ValidationUtil.isNullOrEmpty(userContext.getUserId()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_USERCONTEXTVALIDATOR_USERID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		switch (model.getModelAction())
		{
			case INSERT:
				model.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
				model.setCreateUser(userContext.getUserId());
				break;
			case UPDATE:
				model.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
				model.setModifyUser(userContext.getUserId());
				break;
			case DELETE:
				break;
			default:
				break;
		}

	}
}
