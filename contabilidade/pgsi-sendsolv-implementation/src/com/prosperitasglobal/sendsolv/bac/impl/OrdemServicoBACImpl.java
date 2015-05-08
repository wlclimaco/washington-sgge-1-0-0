package com.prosperitasglobal.sendsolv.bac.impl;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC;
import com.prosperitasglobal.sendsolv.bac.IRecipientBAC;
import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;

/**
 * The Class OrdemServicoBACImpl.
 */
public class OrdemServicoBACImpl implements IOrdemServicoBAC
{

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant MEMBER. */
	private static final String MEMBER = "MEMBER";

	/** The Constant TRANSFER_SETTING. */
	private static final String TRANSFER_SETTING = "TRANSFER_SETTING";

	/** The Constant EMPLOYMENT_INFO. */
	private static final String EMPLOYMENT_INFO = "EMPLOYMENT_INFO";

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The length of the pin number. */
	private Integer pinNumberLength;

	/** The sdn checker bac. */
	private ISdnCheckerBAC sdnCheckerBAC;

	/** The recipient bac. */
	private IRecipientBAC recipientBAC;

	/** The recipient bai. */
	private IRecipientBAI recipientBAI;

	/** The calling card bac. */
	private ICallingCardBAC callingCardBAC;

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
	 * Gets the pin number length. Value will be injected by Spring.
	 *
	 * @return The pin number length.
	 */
	public Integer getPinNumberLength()
	{
		return pinNumberLength;
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
	 * Sets the pin number length. Value will be injected by Spring.
	 *
	 * @param pinNumberLength The pin number length.
	 */
	public void setPinNumberLength(Integer pinNumberLength)
	{
		this.pinNumberLength = pinNumberLength;
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
	 * Gets the recipient bac.
	 *
	 * @return the recipientBAC
	 */
	public IRecipientBAC getRecipientBAC()
	{
		return recipientBAC;
	}

	/**
	 * Sets the recipient bac.
	 *
	 * @param recipientBAC the recipientBAC to set
	 */
	public void setRecipientBAC(IRecipientBAC recipientBAC)
	{
		this.recipientBAC = recipientBAC;
	}

	/**
	 * @return the recipientBAI
	 */
	public IRecipientBAI getRecipientBAI()
	{
		return recipientBAI;
	}

	/**
	 * @param recipientBAI the recipientBAI to set
	 */
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
	}

	/**
	 * @return the callingCardBAC
	 */
	public ICallingCardBAC getCallingCardBAC()
	{
		return callingCardBAC;
	}

	/**
	 * @param callingCardBAC the callingCardBAC to set
	 */
	public void setCallingCardBAC(ICallingCardBAC callingCardBAC)
	{
		this.callingCardBAC = callingCardBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#insertOrdemServico(OrdemServicoRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		InternalResultsResponse<OrdemServicoParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextOrdemServicoParticipantId();

		if (participantIdResponse.isInError())
		{
			// Create a response and merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}
		else
		{
			request.getOrdemServico().setParticipantId(
					CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));
			request.getOrdemServico().setPinNumber(CommonRoutines.generatePinIdentifier(getPinNumberLength()));

			maintainOrdemServicoTransferSettingKey(request, response);

			response = getPersonDAC().insertOrdemServico(request.getOrdemServico());

			if (!response.isInError())
			{
				getSdnCheckerBAC().checkOrdemServicoSdn(createSdnMatchRequest(request));
			}
		}
		return response;
	}

	/**
	 * Maintain ordemServico transfer setting key.
	 *
	 * @param request the request
	 * @param response the response
	 */
	public void maintainOrdemServicoTransferSettingKey(OrdemServicoMaintenanceRequest request,
			InternalResultsResponse<OrdemServico> response)
	{
		if (request.getOrdemServico().getTransferSettingList().isEmpty())
		{
			return;
		}
		else
		{
			for (TransferSetting result : request.getOrdemServico().getTransferSettingList())
			{
				if (ValidationUtil.isNull(result.getKey()))
				{
					InternalResultsResponse<TransferSettingParticipantId> transferparticipantIdResponse =
							getParticipantIdDAC().fetchNextTransferSettingParticipantId();
					if (transferparticipantIdResponse.isInError())
					{
						// Create a response and merge in the errors for fetching the next participant id.
						response.merge(transferparticipantIdResponse);
						response.setStatus(transferparticipantIdResponse.getStatus());
						return;
					}
					result.setKey(CommonRoutines.formatParticipantId(transferparticipantIdResponse.getFirstResult()));
				}
			}
		}
		return;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#updateOrdemServico(OrdemServicoRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		/*
		 * If the ordemServico pin number is null, it means that a new one must be generated. Generate a new pin number
		 * and
		 * assign it to the ordemServico before we update the datastore.
		 */
		if (ValidationUtil.isNull(request.getOrdemServico().getPinNumber()))
		{
			request.getOrdemServico().setPinNumber(CommonRoutines.generatePinIdentifier(getPinNumberLength()));
		}

		maintainOrdemServicoTransferSettingKey(request, response);

		response = getPersonDAC().updateOrdemServico(request.getOrdemServico());

		if (!response.isInError() && !PersistanceActionEnum.NONE.equals(request.getOrdemServico().getModelAction()))
		{
			getSdnCheckerBAC().checkOrdemServicoSdn(createSdnMatchRequest(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#deleteOrdemServico
	 * (com.prosperitasglobal.sendsolv.model.request.OrdemServicoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		return getPersonDAC().deletePerson(request.getOrdemServico());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#fetchOrdemServicoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
	{
		return getPersonDAC().fetchOrdemServicoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#fetchOrdemServicoByRequest(com.prosperitasglobal.sendsolv.model
	 * .request
	 * .OrdemServicoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request)
	{
		return getPersonDAC().fetchOrdemServicoByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#applyStatus(com.prosperitasglobal.sendsolv.model.request.
	 * OrdemServicoMaintenanceRequest)
	 */
	@Override
	public InternalResponse applyStatus(OrdemServicoMaintenanceRequest request)
	{
		InternalResponse applyStatusResponse = new InternalResponse();

		OrdemServico ordemServico = request.getOrdemServico();
		applyStatusResponse = getPersonDAC().applyPersonStatus(ordemServico);
		if (applyStatusResponse.isInError())
		{
			return applyStatusResponse;
		}
		applyStatusResponse.merge(handleStatusMessage(MEMBER, ordemServico.getFirstName() + SPACE
				+ ordemServico.getParticipantId()));

		InternalResponse assignCardResponse = assignCallingCard(ordemServico);
		if (assignCardResponse.isInError() && !Status.NoRowsFoundError.equals(assignCardResponse.getStatus()))
		{
			return assignCardResponse;
		}

		InternalResponse transferSettingResponse =
				processApplyTransferSettingStatus(ordemServico.getTransferSettingList(),
						ordemServico.getPersonStatus(),
						request.getUserContext());

		applyStatusResponse.merge(transferSettingResponse);
		if (transferSettingResponse.isInError())
		{
			return applyStatusResponse;
		}

		InternalResponse employmentInfoResponse =
				processApplyEmploymentStatus(ordemServico.getEmploymentInfoList(), request.getOrdemServico()
						.getPersonStatus());

		applyStatusResponse.merge(employmentInfoResponse);

		return applyStatusResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#insertDocument(com.prosperitasglobal.sendsolv.model.request.
	 * DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request)
	{
		return getPersonDAC().insertPersonDocument(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#deleteDocument(com.prosperitasglobal.sendsolv.model.request.
	 * DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteDocument(DocumentMaintenanceRequest request)
	{
		return getPersonDAC().deletePersonDocument(request);
	}

	/**
	 * Process apply transfer setting status.
	 *
	 * @param transferSettingList the transfer setting list
	 * @param status the status
	 * @return the internal response
	 */
	private InternalResponse processApplyTransferSettingStatus(List<TransferSetting> transferSettingList,
			StatusEnum status, UserContext userContext)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		RecipientResponse statusResponse = new RecipientResponse();
		InternalResponse personResponse = new InternalResponse();

		for (TransferSetting transferSetting : transferSettingList)
		{
			InternalResultsResponse<Recipient> recipientResponse = new InternalResultsResponse<Recipient>();

			recipientResponse =
					getPersonDAC().fetchRecipientById(new FetchByIdRequest(transferSetting.getRecipientId()));

			if (Status.NoRowsFoundError.equals(recipientResponse.getStatus()))
			{
				continue;
			}

			if (recipientResponse.isInError())
			{
				applyStatusResponse.merge(recipientResponse);
				return applyStatusResponse;
			}

			RecipientMaintenanceRequest recipientRequest = new RecipientMaintenanceRequest();
			recipientResponse.getFirstResult().setPersonStatus(status);
			recipientRequest.setRecipient(recipientResponse.getFirstResult());
			recipientRequest.setUserContext(userContext);

			statusResponse = getRecipientBAI().applyStatus(recipientRequest);

			applyStatusResponse.merge(statusResponse);

			transferSetting.setStatus(status);
			personResponse = getPersonDAC().applyTransferSettingStatus(transferSetting);

			if (personResponse.isInError())
			{
				applyStatusResponse.merge(personResponse);
				return applyStatusResponse;
			}
			applyStatusResponse.merge(handleStatusMessage(TRANSFER_SETTING,
					String.valueOf(transferSetting.getId())));
		}
		return applyStatusResponse;
	}

	/**
	 * Handle status message.
	 *
	 * @param activationType the activation type (MEMBER, TRANSFER_SETTING or EMPLOYMENT_INFO)
	 * @param message the message
	 * @return the internal response
	 */
	private InternalResponse handleStatusMessage(String activationType, String message)
	{
		InternalResponse internalResponse = new InternalResponse();
		List<MessageInfo> currentMessage = new ArrayList<MessageInfo>();

		if (!ValidationUtil.isNullOrEmpty(message))
		{
			currentMessage.add(MessageInfo.internalMessage(activationType,
					Message.MessageSeverity.Info, message));
		}

		internalResponse.addMessages(currentMessage);
		return internalResponse;
	}

	/**
	 * Process apply employment status.
	 *
	 * @param employmentInfoList the employment info list
	 * @param status the status
	 * @return the internal response
	 */
	private InternalResponse processApplyEmploymentStatus(List<EmploymentInfo> employmentInfoList, StatusEnum status)
	{
		InternalResponse applyStatusResponse = new InternalResponse();
		InternalResponse statusResponse = new InternalResponse();
		for (EmploymentInfo employmentInfo : employmentInfoList)
		{
			employmentInfo.setEmploymentInfoStatus(status);
			statusResponse = getPersonDAC().applyEmploymentInfoStatus(employmentInfo);

			if (statusResponse.isInError())
			{
				return statusResponse;
			}
			applyStatusResponse.merge(handleStatusMessage(EMPLOYMENT_INFO,
					String.valueOf(employmentInfo.getId())));
		}
		return applyStatusResponse;
	}

	/**
	 * Creates the sdn match request.
	 *
	 * @param request the request
	 * @return the sdn match request
	 */
	private SdnCheckerRequest<OrdemServico> createSdnMatchRequest(OrdemServicoMaintenanceRequest request)
	{
		SdnCheckerRequest<OrdemServico> sdnRequest = new SdnCheckerRequest<OrdemServico>();
		sdnRequest.setPersonOrBusiness(request.getOrdemServico());

		return sdnRequest;
	}

	/**
	 * Assign calling card.
	 *
	 * @param ordemServico the ordemServico
	 * @return the internal response
	 */
	private InternalResponse assignCallingCard(OrdemServico ordemServico)
	{
		InternalResponse response = new InternalResponse();
		if (!StatusEnum.ACTIVE.equals(ordemServico.getPersonStatus()) ||
				!ValidationUtil.isNull(ordemServico.getCallingCardInfo()))
		{
			return response;
		}

		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(ordemServico.getId());
		request.setCallingCardInfo(callingCardInfo);

		response = getCallingCardBAC().assignCard(request);

		return response;
	}
}
