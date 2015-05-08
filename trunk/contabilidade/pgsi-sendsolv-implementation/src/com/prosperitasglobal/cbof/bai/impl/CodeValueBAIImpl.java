package com.prosperitasglobal.cbof.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.ICodeValueBAI;
import com.prosperitasglobal.cbof.dac.ICodeValueDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;
import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CodeValueBAIImpl.
 */
public class CodeValueBAIImpl implements ICodeValueBAI
{

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CodeValueBAIImpl.class.getName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED =
			"cbof.base.organizationvalidator.code.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CodeValueBAIImpl.class);

	/** The code value dac. */
	private ICodeValueDAC codeValueDAC; // injected by Spring through setter

	/**
	 * Gets the code value dac.
	 *
	 * @return the code value dac
	 */
	public ICodeValueDAC getCodeValueDAC()
	{
		return codeValueDAC;
	}

	/**
	 * Sets the code value dac.
	 *
	 * @param codeValueDAC the code value dac
	 */
	public void setCodeValueDAC(ICodeValueDAC codeValueDAC)
	{
		this.codeValueDAC = codeValueDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.bai.ICodeValueBAI#fetchAllCodeValueByType(com.prosperitasglobal.cbof.model.request
	 * .CodeValueRequest)
	 */
	@Override
	public CodeValueResponse fetchAllCodeValueByType(CodeValueRequest request)
	{
		CodeValueResponse response = new CodeValueResponse();

		try
		{
			InternalResultsResponse<CodeValue> internalResponse = new InternalResultsResponse<CodeValue>();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getCodeValueType()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED);
			}
			else
			{
				internalResponse = getCodeValueDAC().fetchAllCodeValueByType(request);

				if (internalResponse.isInError())
				{
					internalResponse.addMessage(DEFAULT_EXCEPTION_MSG,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Object, new Object[] {internalResponse.getStatus().toString()});
				}
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

}
