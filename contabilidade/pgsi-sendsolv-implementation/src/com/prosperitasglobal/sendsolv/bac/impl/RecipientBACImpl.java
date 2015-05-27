package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.bac.IRecipientBAC;

/**
 * The Class MemberBACImpl.
 */
public class RecipientBACImpl implements IRecipientBAC
{

	// /** The Constant SPACE. */
	// private static final String SPACE = " ";
	//
	// /** The person dac. */
	// private IPersonDAC personDAC;
	//
	// /** The participant id dac. */
	// private IParticipantIdDAC participantIdDAC;
	//
	// /** The sdn checker bac. */
	// private ISdnCheckerBAC sdnCheckerBAC;
	//
	// /**
	// * Gets the person dac.
	// *
	// * @return the person dac
	// */
	// public IPersonDAC getPersonDAC()
	// {
	// return personDAC;
	// }
	//
	// /**
	// * Gets the participant id dac.
	// *
	// * @return the participant id dac
	// */
	// public IParticipantIdDAC getParticipantIdDAC()
	// {
	// return participantIdDAC;
	// }
	//
	// /**
	// * Sets the person dac.
	// *
	// * @param personDAC the person dac
	// */
	// public void setPersonDAC(IPersonDAC personDAC)
	// {
	// this.personDAC = personDAC;
	// }
	//
	// /**
	// * Sets the participant id dac.
	// *
	// * @param participantIdDAC the participant id dac
	// */
	// public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	// {
	// this.participantIdDAC = participantIdDAC;
	// }
	//
	// /**
	// * @return the sdnCheckerBAC
	// */
	// public ISdnCheckerBAC getSdnCheckerBAC()
	// {
	// return sdnCheckerBAC;
	// }
	//
	// /**
	// * @param sdnCheckerBAC the sdnCheckerBAC to set
	// */
	// public void setSdnCheckerBAC(ISdnCheckerBAC sdnCheckerBAC)
	// {
	// this.sdnCheckerBAC = sdnCheckerBAC;
	// }
	//
	// /**
	// * Insert recipient.
	// *
	// * @param request the request
	// * @return the internal results response< recipient>
	// */
	// @Override
	// public InternalResultsResponse<Recipient> insertRecipient(RecipientMaintenanceRequest request)
	// {
	// InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
	//
	// InternalResultsResponse<RecipientParticipantId> participantIdResponse =
	// getParticipantIdDAC().fetchNextRecipientParticipantId();
	//
	// if (participantIdResponse.isInError())
	// {
	// // Create a response and merge in the errors for fetching the next participant id.
	// response.merge(participantIdResponse);
	// response.setStatus(participantIdResponse.getStatus());
	// return response;
	// }
	//
	// request.getRecipient().setParticipantId(
	// CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));
	// response = getPersonDAC().insertRecipient(request.getRecipient());
	//
	// if (!response.isInError())
	// {
	// getSdnCheckerBAC().checkRecipientSdn(createSdnMatchRequest(request));
	// }
	//
	// return response;
	//
	// }
	//
	// /**
	// * Update recipient.
	// *
	// * @param request the request
	// * @return the internal results response< recipient>
	// */
	// @Override
	// public InternalResultsResponse<Recipient> updateRecipient(RecipientMaintenanceRequest request)
	// {
	// InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
	//
	// response = getPersonDAC().updateRecipient(request.getRecipient());
	//
	// if (!response.isInError())
	// {
	// getSdnCheckerBAC().checkRecipientSdn(createSdnMatchRequest(request));
	// }
	//
	// return response;
	// }
	//
	// /**
	// * Delete recipient.
	// *
	// * @param request the request
	// * @return the internal response
	// */
	// @Override
	// public InternalResponse deleteRecipient(RecipientMaintenanceRequest request)
	// {
	// return getPersonDAC().deletePerson(request.getRecipient());
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bac.IRecipientBAC#fetchRecipientById(com.prosperitasglobal.cbof.model.request.
	// * FetchByIdRequest)
	// */
	// @Override
	// public InternalResultsResponse<Recipient> fetchRecipientById(FetchByIdRequest request)
	// {
	// return getPersonDAC().fetchRecipientById(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bac.IRecipientBAC#fetchRecipientByRequest(com.prosperitasglobal.sendsolv.model
	// * .request.RecipientInquiryRequest)
	// */
	// @Override
	// public InternalResultsResponse<Recipient> fetchRecipientByRequest(RecipientInquiryRequest request)
	// {
	// return getPersonDAC().fetchRecipientByRequest(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.bac.IRecipientBAC#applyStatus(com.prosperitasglobal.sendsolv.model.request.
	// * RecipientMaintenanceRequest)
	// */
	// @Override
	// public InternalResponse applyStatus(RecipientMaintenanceRequest request)
	// {
	// InternalResponse applyStatusResponse = new InternalResponse();
	//
	// Recipient recipient = request.getRecipient();
	// applyStatusResponse = getPersonDAC().applyPersonStatus(recipient);
	//
	// if (applyStatusResponse.isInError())
	// {
	// return applyStatusResponse;
	// }
	//
	// return handleStatusMessage(recipient.getFirstName() + SPACE + recipient.getParticipantId());
	// }
	//
	// /**
	// * Handle status message.
	// *
	// * @param messages the messages
	// * @return the internal response
	// */
	// private InternalResponse handleStatusMessage(String message)
	// {
	// InternalResponse internalResponse = new InternalResponse();
	// List<MessageInfo> currentMessage = new ArrayList<MessageInfo>();
	//
	// if (!ValidationUtil.isNullOrEmpty(message))
	// {
	// currentMessage.add(MessageInfo.internalMessage(String.valueOf(PersonTypeEnum.RECIPIENT),
	// Message.MessageSeverity.Info, message));
	// }
	// internalResponse.addMessages(currentMessage);
	// return internalResponse;
	// }
	//
	// /**
	// * Creates the sdn match request.
	// *
	// * @param request the request
	// * @return the sdn match request
	// */
	// private SdnCheckerRequest<Recipient> createSdnMatchRequest(RecipientMaintenanceRequest request)
	// {
	// SdnCheckerRequest<Recipient> sdnRequest = new SdnCheckerRequest<Recipient>();
	// sdnRequest.setPersonOrBusiness(request.getRecipient());
	//
	// return sdnRequest;
	// }
	//
}
