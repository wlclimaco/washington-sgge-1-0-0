package com.prosperitasglobal.cbof.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.IIndustryClassificationBAI;
import com.prosperitasglobal.cbof.dac.ICodeValueDAC;
import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;
import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class IndustryClassificationBAIImpl.
 */
public class IndustryClassificationBAIImpl implements IIndustryClassificationBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = IndustryClassificationBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IndustryClassificationBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED =
			"cbof.base.organizationvalidator.code.required";

	/** The Constant DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG. */
	private static final String DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG =
			"sendsolve.base.commonbusinessobjects.defaultexception";

	/** The industry classification dac. */
	private IIndustryClassificationDAC industryClassificationDAC; // injected by Spring through setter

	/** The code value dac. */
	private ICodeValueDAC codeValueDAC; // injected by Spring through setter

	/**
	 * Gets the industry classification dac.
	 *
	 * @return the industry classification dac
	 */
	public IIndustryClassificationDAC getIndustryClassificationDAC()
	{
		return industryClassificationDAC;
	}

	/**
	 * Sets the industry classification dac.
	 *
	 * @param industryClassificationDAC the industry classification dac
	 */
	public void setIndustryClassificationDAC(IIndustryClassificationDAC industryClassificationDAC)
	{
		this.industryClassificationDAC = industryClassificationDAC;
	}

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
	 * com.prosperitasglobal.cbof.bai.IIndustryClassification#fetchAllNAICS(com.prosperitasglobal.cbof.model.request
	 * .CodeValueRequest)
	 */
	@Override
	public CodeValueResponse fetchAllNAICS(CodeValueRequest request)
	{
		CodeValueResponse response = new CodeValueResponse();

		try
		{
			InternalResultsResponse<CodeValue> internalResponse = getCodeValueDAC().fetchAllCodeValueByType(request);

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.bai.IIndustryClassification#fetchAllSIC(com.prosperitasglobal.cbof.model.request.
	 * CodeValueRequest)
	 */
	@Override
	public CodeValueResponse fetchAllSIC(CodeValueRequest request)
	{
		CodeValueResponse response = new CodeValueResponse();

		try
		{
			InternalResultsResponse<CodeValue> internalResponse = getCodeValueDAC().fetchAllCodeValueByType(request);

			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.bai.IIndustryClassification#fetchSICByCode(com.prosperitasglobal.cbof.model.request
	 * .CodeValueRequest)
	 */
	@Override
	public CodeValueResponse fetchSICByCode(CodeValueRequest request)
	{
		CodeValueResponse response = new CodeValueResponse();

		try
		{
			InternalResultsResponse<CodeValue> internalResponse = new InternalResultsResponse<CodeValue>();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getCode()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED);
			}
			else
			{
				internalResponse = getIndustryClassificationDAC().fetchCodeValueByCode(request);
				verifyError(internalResponse);
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.bai.IIndustryClassification#fetchNAICSByCode(com.prosperitasglobal.cbof.model.request
	 * .CodeValueRequest)
	 */
	@Override
	public CodeValueResponse fetchNAICSByCode(CodeValueRequest request)
	{
		CodeValueResponse response = new CodeValueResponse();

		try
		{
			InternalResultsResponse<CodeValue> internalResponse = new InternalResultsResponse<CodeValue>();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getCode()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED);
			}
			else
			{
				internalResponse = getIndustryClassificationDAC().fetchCodeValueByCode(request);
				verifyError(internalResponse);
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

	/**
	 * Verify error.
	 *
	 * @param internalResponse the internal response
	 */
	private void verifyError(InternalResponse internalResponse)
	{
		if (internalResponse.isInError())
		{
			internalResponse.addMessage(DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse.getStatus().toString()});
		}
	}

}
