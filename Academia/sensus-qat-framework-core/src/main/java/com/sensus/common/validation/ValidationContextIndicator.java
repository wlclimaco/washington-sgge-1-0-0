package com.sensus.common.validation;

/**
 * This enumeration is used as an indicator during the execution of validators and is used to indicate the context
 * in which a given validator should execute. It's used as an argument when invoking one of the
 * ValidationUtil.validateDomain() or ValidationUtil.validateBusiness() methods.<br/>
 * Using this enumeration eliminates the need for each business area to define their own.
 * It's also used with the new {@link ValidationController}.
 * If the business area needs additional functionality than another enumeration may need to be defined or try
 * using one of the other arguments within the Validation Context.
 */
public enum ValidationContextIndicator
{
	/** The validator is being perform in the context of a fetch. */
	FETCH,

	/** The validator is being perform in the context of an insert. */
	INSERT,

	/** The validator is being perform in the context of an update. */
	UPDATE,

	/** The validator is being perform in the context of an delete. */
	DELETE,

	/** The validator is being perform in the context of an VOID. */
	VOID,

	/** The validator is being perform in the context of some type of process. */
	PROCESS
}
