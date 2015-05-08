package com.prosperitasglobal.sendsolv.bac.impl;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ILocationBAC;
import com.prosperitasglobal.sendsolv.bac.IOrganizationBAC;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.OrganizationParticipantId;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Standards based implementation of a BAC for Organization leveraging the injected IOrganizationDAC.
 */
public class OrganizationBACImpl implements IOrganizationBAC
{
	/** The organization dac. */
	private IOrganizationDAC organizationDAC; // injected by Spring through setter

	/** The sdn checker bac. */
	private ISdnCheckerBAC sdnCheckerBAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The location bac. */
	private ILocationBAC locationBAC;

	/** The location bai. */
	private ILocationBAI locationBAI;

	/**
	 * Spring Sets the organization dac.
	 *
	 * @param organizationDAC the new organization dac
	 */
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
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
	 * Gets the organization dac.
	 *
	 * @return the organization dac
	 */
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
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
	 * Gets the participant id dac.
	 *
	 * @return the participant id dac
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
	}

	/**
	 * Gets the location bac.
	 *
	 * @return the locationBAC
	 */
	public ILocationBAC getLocationBAC()
	{
		return locationBAC;
	}

	/**
	 * Sets the location bac.
	 *
	 * @param locationBAC the locationBAC to set
	 */
	public void setLocationBAC(ILocationBAC locationBAC)
	{
		this.locationBAC = locationBAC;
	}

	/**
	 * @return the locationBAI
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * @param locationBAI the locationBAI to set
	 */
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#insertOrganization(com.prosperitasglobal.sendsolv.model.
	 * Organization)
	 */
	public InternalResultsResponse<Organization> insertOrganization(OrganizationMaintenanceRequest request)
	{
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();

		InternalResultsResponse<OrganizationParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextOrganizationParticipantId();
		if (participantIdResponse.isInError())
		{
			// Create a response and merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}
		else
		{
			request.getOrganization()
			.setKey(CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));

			response = getOrganizationDAC().insertOrganization(request.getOrganization());

			if (!response.isInError())
			{
				getSdnCheckerBAC().checkBusinessSdn(createSdnMatchRequest(request));
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#updateOrganization(OrganizationMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Organization> updateOrganization(OrganizationMaintenanceRequest request)
	{
		InternalResultsResponse<Organization> response =
				getOrganizationDAC().updateOrganization(request.getOrganization());

		if (!response.isInError())
		{
			getSdnCheckerBAC().checkBusinessSdn(createSdnMatchRequest(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#deleteOrganization(com.prosperitasglobal.sendsolv.model.
	 * Organization)
	 */
	@Override
	public InternalResponse deleteOrganization(OrganizationMaintenanceRequest request)
	{
		return getOrganizationDAC().deleteOrganization(request.getOrganization());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#fetchOrganizationById
	 * (com.prosperitasglobal.cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Organization> fetchOrganizationById(FetchByIdRequest request)
	{
		return getOrganizationDAC().fetchOrganizationById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#fetchOrganizationByRequest
	 * (com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Organization> fetchOrganizationByRequest(PagedInquiryRequest request)
	{
		return getOrganizationDAC().fetchOrganizationByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#insertDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request)
	{
		return getOrganizationDAC().insertDocument(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#updateDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> updateDocument(DocumentMaintenanceRequest request)
	{
		return getOrganizationDAC().updateDocument(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#deleteDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteDocument(DocumentMaintenanceRequest request)
	{
		return getOrganizationDAC().deleteDocument(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request)
	{
		return getOrganizationDAC().updateRisk(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrganizationBAC#applyOrganizationStatus(com.prosperitasglobal.sendsolv.model
	 * .request.OrganizationMaintenanceRequest)
	 */
	@Override
	public InternalResponse applyOrganizationStatus(OrganizationMaintenanceRequest request)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		Organization organization = request.getOrganization();

		applyStatusResponse = getOrganizationDAC().applyOrganizationStatus(organization);

		if (applyStatusResponse.isInError())
		{
			return applyStatusResponse;
		}
		applyStatusResponse.merge(handleStatusMessage(organization.getName()));

		PagedInquiryRequest locationRequest = new PagedInquiryRequest();
		locationRequest.setParentId(organization.getId());

		InternalResultsResponse<Location> locationResponse = getLocationBAC().fetchLocationByRequest(locationRequest);

		if (locationResponse.isInError())
		{
			applyStatusResponse.merge(locationResponse);
			return applyStatusResponse;
		}

		InternalResponse internalLocationResponse =
				processApplyLocationStatus(locationResponse.getResultsList(), organization.getStatus(),
						request.getUserContext());

		applyStatusResponse.merge(internalLocationResponse);

		if (internalLocationResponse.isInError())
		{
			return applyStatusResponse;
		}

		return applyStatusResponse;
	}

	/**
	 * Process apply location status.
	 *
	 * @param locationList the location list
	 * @param status the status
	 * @return the internal response
	 */
	private InternalResponse processApplyLocationStatus(List<Location> locationList, StatusEnum status,
			UserContext userContext)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		LocationResponse statusResponse = new LocationResponse();
		LocationMaintenanceRequest locationRequest = new LocationMaintenanceRequest();
		locationRequest.setUserContext(userContext);

		for (Location location : locationList)
		{
			location.setStatus(status);
			locationRequest.setLocation(location);
			statusResponse = getLocationBAI().applyStatus(locationRequest);

			applyStatusResponse.merge(statusResponse);
		}

		return applyStatusResponse;
	}

	/**
	 * Handle status message.
	 *
	 * @param message the message
	 * @return the internal response
	 */
	private InternalResponse handleStatusMessage(String message)
	{
		InternalResponse internalResponse = new InternalResponse();
		List<MessageInfo> currentMessage = new ArrayList<MessageInfo>();

		if (!ValidationUtil.isNullOrEmpty(message))
		{
			currentMessage.add(MessageInfo.internalMessage(String.valueOf(BusinessTypeEnum.ORGANIZATION),
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
	private SdnCheckerRequest<Business> createSdnMatchRequest(OrganizationMaintenanceRequest request)
	{
		SdnCheckerRequest<Business> sdnRequest = new SdnCheckerRequest<Business>();
		sdnRequest.setPersonOrBusiness(request.getOrganization());

		return sdnRequest;
	}
}
