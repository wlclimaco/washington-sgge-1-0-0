package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivityParticipantId;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SuspiciousActivityBACImpl.
 */
public class SuspiciousActivityBACImpl implements ISuspiciousActivityBAC
{

	/** The suspicious activity dac. */
	private ISuspiciousActivityDAC suspiciousActivityDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/**
	 * Gets the suspicious activity dac.
	 *
	 * @return the suspiciousActivityDAC
	 */
	public ISuspiciousActivityDAC getSuspiciousActivityDAC()
	{
		return suspiciousActivityDAC;
	}

	/**
	 * Sets the suspicious activity dac.
	 *
	 * @param suspiciousActivityDAC the suspiciousActivityDAC to set
	 */
	public void setSuspiciousActivityDAC(ISuspiciousActivityDAC suspiciousActivityDAC)
	{
		this.suspiciousActivityDAC = suspiciousActivityDAC;
	}

	/**
	 * Gets the participant id dac.
	 *
	 * @return the participantIdDAC
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
	}

	/**
	 * Sets the participant id dac.
	 *
	 * @param participantIdDAC the participantIdDAC to set
	 */
	public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	{
		this.participantIdDAC = participantIdDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC#insertSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertSuspiciousActivity(SarMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		InternalResultsResponse<SuspiciousActivityParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextSuspiciousActivityParticipantId();

		if (participantIdResponse.isInError())
		{
			// Create a response and merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}

		request.getSuspiciousActivity().setBusinessKey(
				CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));

		response = getSuspiciousActivityDAC().insertSuspiciousActivity(request);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC#updateSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateSuspiciousActivity(SarMaintenanceRequest request)
	{
		return getSuspiciousActivityDAC().insertSuspiciousActivity(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC#deleteSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteSuspiciousActivity(SarMaintenanceRequest request)
	{
		InternalResponse response = getSuspiciousActivityDAC().deleteSuspiciousActivity(request);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC#fetchSuspiciousActivityByRequest(com.prosperitasglobal
	 * .sendsolv.model.request.SarInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<SuspiciousActivity> fetchSuspiciousActivityByRequest(SarInquiryRequest request)
	{
		InternalResultsResponse<SuspiciousActivity> response =
				getSuspiciousActivityDAC().fetchSuspiciousActivityByRequest(request);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC#fetchSuspiciousActivityByIdRequest
	 * (com.prosperitasglobal.cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<SuspiciousActivity> fetchSuspiciousActivityByIdRequest(FetchByIdRequest request)
	{
		InternalResultsResponse<SuspiciousActivity> response =
				getSuspiciousActivityDAC().fetchSuspiciousActivityByIdRequest(request);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC#fetchBusinessSuspiciousActivityByIdRequest(com.
	 * prosperitasglobal.cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<SuspiciousActivity> fetchBusinessSuspiciousActivityByIdRequest(
			FetchByIdRequest request)
	{
		InternalResultsResponse<SuspiciousActivity> response =
				getSuspiciousActivityDAC().fetchBusinessSuspiciousActivityByIdRequest(request);

		return response;
	}
}
