package com.prosperitasglobal.sendsolv.integration.twilio.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrBAC;
import com.prosperitasglobal.sendsolv.integration.twilio.bai.IIvrBAI;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.model.response.IvrResponse;
import com.prosperitasglobal.sendsolv.integration.twilio.util.IvrCommonRoutines;
import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentTypeBAIImpl.
 */
public class IvrBAIImpl implements IIvrBAI
{

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = IvrBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG. */
	private static final String DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG =
			"sendsolve.base.commonbusinessobjects.defaultexception";

	/** The ivr bac. */
	private IIvrBAC ivrBAC; // injected by Spring through setter

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.integration.twilio.bai.IIvrBAI#verifyIdentity(com.prosperitasglobal.sendsolv.
	 * integration.twilio.model.request.IvrRequest)
	 */
	@Override
	public IvrResponse verifyIdentity(IvrRequest request)
	{
		IvrResponse response = new IvrResponse();

		return response;
	}

	/**
	 * Gets the ivr bac.
	 *
	 * @return the ivr bac
	 */
	public IIvrBAC getIvrBAC()
	{
		return ivrBAC;
	}

	/**
	 * Sets the ivr bac.
	 *
	 * @param ivrBAC the new ivr bac
	 */
	public void setIvrBAC(IIvrBAC ivrBAC)
	{
		this.ivrBAC = ivrBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.integration.twilio.bai.IIvrBAI#processAction(com.prosperitasglobal.sendsolv.
	 * integration.twilio.model.request.IvrRequest)
	 */
	@Override
	public IvrResponse processAction(IvrRequest request)
	{
		IvrResponse response = new IvrResponse();
		try
		{
			if (validRequest(request))
			{
				InternalResultsResponse<String> internalResponse = new InternalResultsResponse<String>();

				internalResponse = getIvrBAC().processIvrOption(request);

				if (internalResponse.isInError())
				{
					internalResponse.addMessage(DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG,
							Message.MessageSeverity.Error, Message.MessageLevel.Object,
							new Object[] {internalResponse.getStatus().toString()});
					response.addResults(IvrCommonRoutines.createCatchAllResponse(request, null).getResultsList());
				}

				// Handle the processing for all previous methods regardless of them failing or succeeding.
				QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
			}
			else
			{
				LOG.debug("Invalid Request");
				response.addResults(IvrCommonRoutines.createCatchAllResponse(request, null).getResultsList());
			}
		}
		catch (Exception ex)
		{
			response.addResults(IvrCommonRoutines.createCatchAllResponse(request, null).getResultsList());

			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Twilio message: " + response.getTwimlResponseList().get(0).toString());
		}

		return response;
	}

	/**
	 * Verify if {@link IvrRequest} is valid.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	private boolean validRequest(IvrRequest request)
	{
		if (!ValidationUtil.isNull(request) &&
				!ValidationUtil.isNull(request.getIvrController()) &&
				!ValidationUtil.isNull(request.getIvrIdentity()))
		{

			return true;
		}

		LOG.debug("request.getIvrController(): " + request.getIvrController());
		LOG.debug("request.getIvrIdentity(): " + request.getIvrIdentity());
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.integration.twilio.bai.IIvrBAI#processDisconnect(com.prosperitasglobal.sendsolv
	 * .integration.twilio.model.request.IvrRequest)
	 */
	@Override
	public IvrResponse processDisconnect(IvrRequest request)
	{
		IvrResponse response = new IvrResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();

			internalResponse = getIvrBAC().processDisconnect(request);

			if (internalResponse.isInError())
			{
				internalResponse.addMessage(DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG,
						Message.MessageSeverity.Error, Message.MessageLevel.Object,
						new Object[] {internalResponse.getStatus().toString()});
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
