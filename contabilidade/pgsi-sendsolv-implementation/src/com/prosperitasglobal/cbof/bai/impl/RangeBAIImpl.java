package com.prosperitasglobal.cbof.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.IRangeBAI;
import com.prosperitasglobal.cbof.dac.IRangeDAC;
import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.cbof.model.request.RangeRequest;
import com.prosperitasglobal.cbof.model.response.RangeResponse;
import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class RangeBAIImpl.
 */
public class RangeBAIImpl implements IRangeBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = RangeBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RangeBAIImpl.class);

	/** The Constant PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED =
			"cbof.base.organizationvalidator.code.required";

	/** The Constant DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG. */
	private static final String DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG =
			"sendsolve.base.commonbusinessobjects.defaultexception";

	/** The range dac. */
	private IRangeDAC rangeDAC;

	/**
	 * Gets the range dac.
	 *
	 * @return the range dac
	 */
	public IRangeDAC getRangeDAC()
	{
		return rangeDAC;
	}

	/**
	 * Sets the range dac.
	 *
	 * @param rangeDAC the range dac
	 */
	public void setRangeDAC(IRangeDAC rangeDAC)
	{
		this.rangeDAC = rangeDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.bai.IRangeBAI#fetchAllRange(com.prosperitasglobal.cbof.model.request.RangeRequest)
	 */
	@Override
	public RangeResponse fetchAllRange(RangeRequest request)
	{
		RangeResponse response = new RangeResponse();
		try
		{
			InternalResultsResponse<Range> internalResponse = getRangeDAC().fetchAllRanges(request);
			verifyError(internalResponse);

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
	 * com.prosperitasglobal.cbof.bai.IRangeBAI#fetchRangeById(com.prosperitasglobal.cbof.model.request.RangeRequest)
	 */
	@Override
	public RangeResponse fetchRangeById(RangeRequest request)
	{
		RangeResponse response = new RangeResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNullOrZero(request.getId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_CBOFVALIDATOR_CODE_REQUIRED);
			}
			else
			{
				internalResponse = getRangeDAC().fetchRangeById(request);
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
