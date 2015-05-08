package com.prosperitasglobal.sendsolv.bac.impl;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.bac.IProdutoBAC;
import com.prosperitasglobal.sendsolv.bac.IRecipientBAC;
import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;

/**
 * The Class ProdutoBACImpl.
 */
public class ProdutoBACImpl implements IProdutoBAC
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
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#insertProduto(ProdutoRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> insertProduto(ProdutoMaintenanceRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		InternalResultsResponse<ProdutoParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextProdutoParticipantId();

		if (participantIdResponse.isInError())
		{
			// Create a response and merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}
		else
		{
			request.getProduto().setParticipantId(
					CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));
			request.getProduto().setPinNumber(CommonRoutines.generatePinIdentifier(getPinNumberLength()));

			maintainProdutoTransferSettingKey(request, response);

			response = getPersonDAC().insertProduto(request.getProduto());

			if (!response.isInError())
			{
				getSdnCheckerBAC().checkProdutoSdn(createSdnMatchRequest(request));
			}
		}
		return response;
	}

	/**
	 * Maintain produto transfer setting key.
	 *
	 * @param request the request
	 * @param response the response
	 */
	public void maintainProdutoTransferSettingKey(ProdutoMaintenanceRequest request,
			InternalResultsResponse<Produto> response)
	{
		if (request.getProduto().getTransferSettingList().isEmpty())
		{
			return;
		}
		else
		{
			for (TransferSetting result : request.getProduto().getTransferSettingList())
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
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#updateProduto(ProdutoRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> updateProduto(ProdutoMaintenanceRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		/*
		 * If the produto pin number is null, it means that a new one must be generated. Generate a new pin number and
		 * assign it to the produto before we update the datastore.
		 */
		if (ValidationUtil.isNull(request.getProduto().getPinNumber()))
		{
			request.getProduto().setPinNumber(CommonRoutines.generatePinIdentifier(getPinNumberLength()));
		}

		maintainProdutoTransferSettingKey(request, response);

		response = getPersonDAC().updateProduto(request.getProduto());

		if (!response.isInError() && !PersistanceActionEnum.NONE.equals(request.getProduto().getModelAction()))
		{
			getSdnCheckerBAC().checkProdutoSdn(createSdnMatchRequest(request));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IProdutoBAC#deleteProduto
	 * (com.prosperitasglobal.sendsolv.model.request.ProdutoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteProduto(ProdutoMaintenanceRequest request)
	{
		return getPersonDAC().deletePerson(request.getProduto());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#fetchProdutoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
	{
		return getPersonDAC().fetchProdutoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IProdutoBAC#fetchProdutoByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .ProdutoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoByRequest(ProdutoInquiryRequest request)
	{
		return getPersonDAC().fetchProdutoByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#applyStatus(com.prosperitasglobal.sendsolv.model.request.
	 * ProdutoMaintenanceRequest)
	 */
	@Override
	public InternalResponse applyStatus(ProdutoMaintenanceRequest request)
	{
		InternalResponse applyStatusResponse = new InternalResponse();

		Produto produto = request.getProduto();
		applyStatusResponse = getPersonDAC().applyPersonStatus(produto);
		if (applyStatusResponse.isInError())
		{
			return applyStatusResponse;
		}
		applyStatusResponse.merge(handleStatusMessage(MEMBER, produto.getFirstName() + SPACE
				+ produto.getParticipantId()));

		InternalResponse assignCardResponse = assignCallingCard(produto);
		if (assignCardResponse.isInError() && !Status.NoRowsFoundError.equals(assignCardResponse.getStatus()))
		{
			return assignCardResponse;
		}

		InternalResponse transferSettingResponse =
				processApplyTransferSettingStatus(produto.getTransferSettingList(), produto.getPersonStatus(),
						request.getUserContext());

		applyStatusResponse.merge(transferSettingResponse);
		if (transferSettingResponse.isInError())
		{
			return applyStatusResponse;
		}

		InternalResponse employmentInfoResponse =
				processApplyEmploymentStatus(produto.getEmploymentInfoList(), request.getProduto().getPersonStatus());

		applyStatusResponse.merge(employmentInfoResponse);

		return applyStatusResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#insertDocument(com.prosperitasglobal.sendsolv.model.request.
	 * DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request)
	{
		return getPersonDAC().insertPersonDocument(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#deleteDocument(com.prosperitasglobal.sendsolv.model.request.
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
	private SdnCheckerRequest<Produto> createSdnMatchRequest(ProdutoMaintenanceRequest request)
	{
		SdnCheckerRequest<Produto> sdnRequest = new SdnCheckerRequest<Produto>();
		sdnRequest.setPersonOrBusiness(request.getProduto());

		return sdnRequest;
	}

	/**
	 * Assign calling card.
	 *
	 * @param produto the produto
	 * @return the internal response
	 */
	private InternalResponse assignCallingCard(Produto produto)
	{
		InternalResponse response = new InternalResponse();
		if (!StatusEnum.ACTIVE.equals(produto.getPersonStatus()) ||
				!ValidationUtil.isNull(produto.getCallingCardInfo()))
		{
			return response;
		}

		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(produto.getId());
		request.setCallingCardInfo(callingCardInfo);

		response = getCallingCardBAC().assignCard(request);

		return response;
	}
}
