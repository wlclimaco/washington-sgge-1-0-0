package com.prosperitasglobal.sendsolv.bac.impl;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ILocationBAC;
import com.prosperitasglobal.sendsolv.bac.IMemberBAC;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.LocationParticipantId;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class LocationBACImpl.
 */
public class LocationBACImpl implements ILocationBAC
{

	/** The organization dac. */
	private ILocationDAC locationDAC; // injected by Spring through setter

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The sdn checker bac. */
	private ISdnCheckerBAC sdnCheckerBAC;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The member bac. */
	private IMemberBAC memberBAC;

	/** The member bai. */
	private IMemberBAI memberBAI;

	/**
	 * Gets the location dac.
	 *
	 * @return the location dac
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
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
	 * Sets the location dac.
	 *
	 * @param locationDAC the location dac
	 */
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
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
	 * Gets the sdn checker bac.
	 *
	 * @return the sdnCheckerBAC
	 */
	public ISdnCheckerBAC getSdnCheckerBAC()
	{
		return sdnCheckerBAC;
	}

	/**
	 * Sets the sdn checker bac.
	 *
	 * @param sdnCheckerBAC the sdnCheckerBAC to set
	 */
	public void setSdnCheckerBAC(ISdnCheckerBAC sdnCheckerBAC)
	{
		this.sdnCheckerBAC = sdnCheckerBAC;
	}

	/**
	 * Gets the person dac.
	 *
	 * @return the personDAC
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * Sets the person dac.
	 *
	 * @param personDAC the personDAC to set
	 */
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * Gets the member bac.
	 *
	 * @return the memberBAC
	 */
	public IMemberBAC getMemberBAC()
	{
		return memberBAC;
	}

	/**
	 * Sets the member bac.
	 *
	 * @param memberBAC the memberBAC to set
	 */
	public void setMemberBAC(IMemberBAC memberBAC)
	{
		this.memberBAC = memberBAC;
	}

	/**
	 * @return the memberBAI
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * @param memberBAI the memberBAI to set
	 */
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ILocationBAC#insertLocation(LocationMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Location> insertLocation(LocationMaintenanceRequest request)
	{
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();

		InternalResultsResponse<LocationParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextLocationParticipantId();
		if (participantIdResponse.isInError())
		{
			// Create a response and merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}
		else
		{
			request.getLocation().setKey(CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));
			response = getLocationDAC().insertLocation(request.getLocation());

			if (!response.isInError())
			{
				getSdnCheckerBAC().checkBusinessSdn(createSdnMatchRequest(request));
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ILocationBAC#updateLocation(LocationMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Location> updateLocation(LocationMaintenanceRequest request)
	{
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();

		response = getLocationDAC().updateLocation(request.getLocation());

		if (!response.isInError())
		{
			getSdnCheckerBAC().checkBusinessSdn(createSdnMatchRequest(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ILocationBAC#deleteLocation(com.prosperitasglobal.sendsolv.model.request
	 * .LocationMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteLocation(LocationMaintenanceRequest request)
	{
		return getLocationDAC().deleteLocation(request.getLocation());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ILocationBAC#fetchLocationById(com.prosperitasglobal.sendsolv.model.request
	 * .FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Location> fetchLocationById(FetchByIdRequest request)
	{
		return getLocationDAC().fetchLocationById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ILocationBAC#fetchLocationByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Location> fetchLocationByRequest(PagedInquiryRequest request)
	{
		return getLocationDAC().fetchLocationByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ILocationBAC#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request)
	{
		return getLocationDAC().updateRisk(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ILocationBAC#applyStatus(com.prosperitasglobal.sendsolv.model.request.
	 * LocationMaintenanceRequest)
	 */
	@Override
	public InternalResponse applyStatus(LocationMaintenanceRequest request)
	{
		InternalResponse applyStatusResponse = new InternalResponse();

		Location location = request.getLocation();
		applyStatusResponse = getLocationDAC().applyLocationStatus(location);

		if (applyStatusResponse.isInError())
		{
			return applyStatusResponse;
		}
		applyStatusResponse.merge(handleStatusMessage(location.getName()));

		InternalResponse memberResponse =
				processApplyMemberStatus(location.getId(), location.getStatus(), request.getUserContext());

		applyStatusResponse.merge(memberResponse);

		return applyStatusResponse;

	}

	/**
	 * Process apply member status.
	 *
	 * @param locationId the location id
	 * @param status the status
	 * @return the internal response
	 */
	private InternalResponse processApplyMemberStatus(Integer locationId, StatusEnum status, UserContext userContext)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		InternalResultsResponse<EmploymentInfo> employmentInfoResponse =
				getPersonDAC().fetchEmploymentInfoByLocationId(locationId);

		if (employmentInfoResponse.isInError())
		{
			applyStatusResponse.merge(employmentInfoResponse);
			return applyStatusResponse;
		}
		MemberResponse statusResponse = new MemberResponse();
		InternalResultsResponse<Member> memberResponse = new InternalResultsResponse<Member>();

		for (EmploymentInfo employmentInfo : employmentInfoResponse.getResultsList())
		{
			memberResponse = getPersonDAC().fetchMemberById(new FetchByIdRequest(employmentInfo.getMemberId()));

			if (Status.NoRowsFoundError.equals(memberResponse.getStatus()))
			{
				continue;
			}

			if (memberResponse.isInError())
			{
				applyStatusResponse.merge(memberResponse);
				return applyStatusResponse;
			}

			MemberMaintenanceRequest memberRequest = new MemberMaintenanceRequest();
			memberResponse.getFirstResult().setPersonStatus(status);
			memberRequest.setMember(memberResponse.getFirstResult());
			memberRequest.setUserContext(userContext);

			statusResponse = getMemberBAI().applyStatus(memberRequest);

			applyStatusResponse.merge(statusResponse);

		}
		return applyStatusResponse;
	}

	/**
	 * Handle status message.
	 *
	 * @param activationType the activation type
	 * @param message the message
	 * @return the internal response
	 */
	private InternalResponse handleStatusMessage(String message)
	{
		InternalResponse internalResponse = new InternalResponse();
		List<MessageInfo> currentMessage = new ArrayList<MessageInfo>();

		if (!ValidationUtil.isNullOrEmpty(message))
		{
			currentMessage.add(MessageInfo.internalMessage(String.valueOf(BusinessTypeEnum.LOCATION),
					Message.MessageSeverity.Info, message));
		}

		internalResponse.addMessages(currentMessage);
		return internalResponse;
	}

	/**
	 * Creates the sdn match request.
	 *
	 * @param request the request
	 * @return the sdn match request
	 */
	private SdnCheckerRequest<Business> createSdnMatchRequest(LocationMaintenanceRequest request)
	{
		SdnCheckerRequest<Business> sdnRequest = new SdnCheckerRequest<Business>();
		sdnRequest.setPersonOrBusiness(request.getLocation());

		return sdnRequest;
	}
}
