package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ILiaisonBAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.ContactParticipantId;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LiaisonBACImpl.
 */
public class LiaisonBACImpl implements ILiaisonBAC
{

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The sdn checker bac. */
	private ISdnCheckerBAC sdnCheckerBAC;

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
	 * Gets the participant id dac.
	 *
	 * @return the participant id dac
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
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

	/**
	 * Sets the participant id dac.
	 *
	 * @param participantIdDAC the participant id dac
	 */
	public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	{
		this.participantIdDAC = participantIdDAC;
	}

	/**
	 * @return the sdnCheckerBAC
	 */
	public ISdnCheckerBAC getSdnCheckerBAC()
	{
		return sdnCheckerBAC;
	}

	/**
	 * @param sdnCheckerBAC the sdnCheckerBAC to set
	 */
	public void setSdnCheckerBAC(ISdnCheckerBAC sdnCheckerBAC)
	{
		this.sdnCheckerBAC = sdnCheckerBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ILiaisonBAC#insertLiaison(com.prosperitasglobal.sendsolv.model.request.
	 * LiaisonMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Liaison> insertLiaison(LiaisonMaintenanceRequest request)
	{
		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();

		InternalResultsResponse<ContactParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextContactParticipantId();

		if (participantIdResponse.isInError())
		{
			// Create a response and merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}
		request.getLiaison().setParticipantId(
				CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));
		response = getPersonDAC().insertLiaison(request.getLiaison());

		if (!response.isInError())
		{
			getSdnCheckerBAC().checkLiaisonSdn(createSdnMatchRequest(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ILiaisonBAC#updateLiaison(com.prosperitasglobal.sendsolv.model.request.
	 * LiaisonMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Liaison> updateLiaison(LiaisonMaintenanceRequest request)
	{
		InternalResultsResponse<Liaison> response = getPersonDAC().updateLiaison(request.getLiaison());

		if (!response.isInError())
		{
			getSdnCheckerBAC().checkLiaisonSdn(createSdnMatchRequest(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ILiaisonBAC#deleteLiaison(com.prosperitasglobal.sendsolv.model.request.
	 * LiaisonMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteLiaison(LiaisonMaintenanceRequest request)
	{
		return getPersonDAC().deletePerson(request.getLiaison());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ILiaisonBAC#fetchLiaisonById(com.prosperitasglobal.sendsolv.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Liaison> fetchLiaisonById(FetchByIdRequest request)
	{
		return getPersonDAC().fetchLiaisonById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ILiaisonBAC#fetchLiaisonByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Liaison> fetchLiaisonByRequest(PagedInquiryRequest request)
	{
		return getPersonDAC().fetchLiaisonByRequest(request);
	}

	/**
	 * Creates the sdn match request.
	 *
	 * @param request the request
	 * @return the sdn match request
	 */
	private SdnCheckerRequest<Liaison> createSdnMatchRequest(LiaisonMaintenanceRequest request)
	{
		SdnCheckerRequest<Liaison> sdnRequest = new SdnCheckerRequest<Liaison>();
		sdnRequest.setPersonOrBusiness(request.getLiaison());

		return sdnRequest;
	}
}
