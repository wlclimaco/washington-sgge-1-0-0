package com.prosperitasglobal.sendsolv.bai.impl;

import com.prosperitasglobal.sendsolv.bai.IMemberBAI;

/**
 * The Class MemberBAIImpl.
 */
public class MemberBAIImpl implements IMemberBAI
{
	//
	// /** The Constant CLASS_NAME. */
	// private static final String CLASS_NAME = MemberBAIImpl.class.getName();
	//
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";
	//
	// /** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED. */
	// private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED =
	// "sendsolv.base.membervalidator.id.required";
	//
	// /** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	// private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
	// "sendsolv.base.optimistic.locking.error";
	//
	// /** The Constant LOG. */
	// private static final Logger LOG = LoggerFactory.getLogger(MemberBAIImpl.class);
	//
	// /** The member bac. */
	// private IMemberBAC memberBAC;
	//
	// /** The member validation controller. */
	// private ValidationController memberValidationController;
	//
	// /** The member paged inquiry request validation controller. */
	// private ValidationController memberPagedInquiryRequestValidationController;
	//
	// /** The document validation controller. */
	// private ValidationController documentValidationController;
	//
	// /**
	// * Gets the member validation controller.
	// *
	// * @return the member validation controller
	// */
	// public ValidationController getMemberValidationController()
	// {
	// return memberValidationController;
	// }
	//
	// /**
	// * Sets the member validation controller.
	// *
	// * @param memberValidationController the member validation controller
	// */
	// public void setMemberValidationController(ValidationController memberValidationController)
	// {
	// this.memberValidationController = memberValidationController;
	// }
	//
	// /**
	// * Gets the member paged inquiry request validation controller.
	// *
	// * @return the member paged inquiry request validation controller
	// */
	// public ValidationController getMemberPagedInquiryRequestValidationController()
	// {
	// return memberPagedInquiryRequestValidationController;
	// }
	//
	// /**
	// * Sets the member paged inquiry request validation controller.
	// *
	// * @param memberPagedInquiryRequestValidationController the member paged inquiry request validation controller
	// */
	// public void setMemberPagedInquiryRequestValidationController(
	// ValidationController memberPagedInquiryRequestValidationController)
	// {
	// this.memberPagedInquiryRequestValidationController = memberPagedInquiryRequestValidationController;
	// }
	//
	// /**
	// * Gets the document validation controller.
	// *
	// * @return the document validation controller
	// */
	// public ValidationController getDocumentValidationController()
	// {
	// return documentValidationController;
	// }
	//
	// /**
	// * Sets the document validation controller.
	// *
	// * @param documentValidationController the document validation controller
	// */
	// public void setDocumentValidationController(ValidationController documentValidationController)
	// {
	// this.documentValidationController = documentValidationController;
	// }
	//
	// /**
	// * Gets the member bac.
	// *
	// * @return the member bac
	// */
	// public IMemberBAC getMemberBAC()
	// {
	// return memberBAC;
	// }
	//
	// /**
	// * Sets the member bac.
	// *
	// * @param memberBAC the member bac
	// */
	// public void setMemberBAC(IMemberBAC memberBAC)
	// {
	// this.memberBAC = memberBAC;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.IMemberBAI#insertMember
	// * (com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest)
	// */
	// @Override
	// public MemberResponse insertMember(MemberMaintenanceRequest request)
	// {
	// MemberResponse response = new MemberResponse();
	// try
	// {
	// response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.IMemberBAI#updateMember
	// * (com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest )
	// */
	// @Override
	// public MemberResponse updateMember(MemberMaintenanceRequest request)
	// {
	// MemberResponse response = new MemberResponse();
	// try
	// {
	// response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.IMemberBAI#deleteMember
	// * (com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest )
	// */
	// @Override
	// public MemberResponse deleteMember(MemberMaintenanceRequest request)
	// {
	// MemberResponse response = new MemberResponse();
	// try
	// {
	// response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// com.prosperitasglobal.sendsolv.bai.IMemberBAI#fetchMemberById(com.prosperitasglobal.sendsolv.model.request.
	// * FetchByIdRequest)
	// */
	// @Override
	// public MemberResponse fetchMemberById(FetchByIdRequest request)
	// {
	// MemberResponse response = new MemberResponse();
	// try
	// {
	// InternalResponse internalResponse = new InternalResponse();
	// // validate fetchId field
	// if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
	// {
	// internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED);
	// }
	// else
	// {
	// internalResponse = getMemberBAC().fetchMemberById(request);
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.bai.IMemberBAI#fetchMemberByRequest(com.prosperitasglobal.sendsolv.model.request
	// * .PagedInquiryRequest)
	// */
	// @Override
	// public MemberResponse fetchMemberByRequest(MemberInquiryRequest request)
	// {
	// MemberResponse response = new MemberResponse();
	// try
	// {
	// response = fetchPaged(request, response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.bai.IMemberBAI#applyStatus(com.prosperitasglobal.sendsolv.model.request.
	// * MemberMaintenanceRequest)
	// */
	// @Override
	// public MemberResponse applyStatus(MemberMaintenanceRequest request)
	// {
	// MemberResponse response = new MemberResponse();
	// InternalResponse internalResponse = null;
	// try
	// {
	//
	// // Validate.
	// ValidationContext context =
	// new ValidationContext(Member.class.getSimpleName(), request.getMember(),
	// ValidationContextIndicator.FETCH_BY_ID);
	// context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());
	//
	// if (getMemberValidationController().validate(context))
	// {
	// internalResponse = getMemberBAC().applyStatus(request);
	// }
	//
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
	//
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.bai.IMemberBAI#insertDocument(com.prosperitasglobal.sendsolv.model.request.
	// * DocumentMaintenanceRequest)
	// */
	// @Override
	// public DocumentResponse insertDocument(DocumentMaintenanceRequest request)
	// {
	// DocumentResponse response = new DocumentResponse();
	// InternalResultsResponse<Document> internalResponse = null;
	//
	// try
	// {
	// ValidationContext context = new ValidationContext(ValidationContextIndicator.INSERT);
	// context.putObjectToBeValidated(Document.class.getSimpleName(), request.getDocument());
	// context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());
	//
	// if (getDocumentValidationController().validate(context))
	// {
	// internalResponse = getMemberBAC().insertDocument(request);
	// }
	//
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.bai.IMemberBAI#deleteDocument(com.prosperitasglobal.sendsolv.model.request.
	// * DocumentMaintenanceRequest)
	// */
	// @Override
	// public DocumentResponse deleteDocument(DocumentMaintenanceRequest request)
	// {
	// DocumentResponse response = new DocumentResponse();
	// InternalResponse internalResponse = null;
	//
	// try
	// {
	// ValidationContext context = new ValidationContext(ValidationContextIndicator.DELETE);
	// context.putObjectToBeValidated(Document.class.getSimpleName(), request.getDocument());
	//
	// if (getDocumentValidationController().validate(context))
	// {
	// internalResponse = getMemberBAC().deleteDocument(request);
	// }
	//
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	//
	// return response;
	// }
	//
	// /**
	// * Process.
	// *
	// * @param indicator the indicator
	// * @param persistType the persist type
	// * @param request the request
	// * @return the member response
	// */
	// private MemberResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
	// MemberMaintenanceRequest request)
	// {
	// MemberResponse response = new MemberResponse();
	// InternalResponse internalResponse = null;
	//
	// // Validate.
	// ValidationContext context =
	// new ValidationContext(Member.class.getSimpleName(), request.getMember(), indicator);
	// context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());
	//
	// if (getMemberValidationController().validate(context))
	// {
	// // Persist
	// internalResponse = doPersistance(request, persistType);
	// }
	//
	// return (MemberResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	// }
	//
	// /**
	// * Handle return.
	// *
	// * @param response the response
	// * @param internalResponse the internal response
	// * @param messages the messages
	// * @param copyOver the copy over
	// * @return the response
	// */
	// private Response handleReturn(Response response, InternalResponse internalResponse,
	// List<MessageInfo> messages, boolean copyOver)
	// {
	// // In the case there was an Optimistic Locking error, add the specific message
	// if (!ValidationUtil.isNull(internalResponse) && !ValidationUtil.isNull(internalResponse.getStatus())
	// && Status.OptimisticLockingError.equals(internalResponse.getStatus()))
	// {
	// messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_OL_ERROR, MessageSeverity.Error,
	// MessageLevel.Object));
	// }
	//
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
	// return response;
	// }
	//
	// /**
	// * Do persistance.
	// *
	// * @param request the request
	// * @param updateType the update type
	// * @return the internal response
	// */
	// private InternalResponse doPersistance(MemberMaintenanceRequest request, PersistanceActionEnum updateType)
	// {
	// switch (updateType)
	// {
	// case INSERT:
	// return getMemberBAC().insertMember(request);
	//
	// case UPDATE:
	// return getMemberBAC().updateMember(request);
	//
	// case DELETE:
	// return getMemberBAC().deleteMember(request);
	//
	// default:
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug("updateType missing!");
	// }
	// break;
	// }
	// return null;
	// }
	//
	// /**
	// * Fetch paged.
	// *
	// * @param request the request
	// * @param response the response
	// * @return the member response
	// */
	// private MemberResponse fetchPaged(MemberInquiryRequest request, MemberResponse response)
	// {
	// InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
	//
	// ValidationContext context =
	// new ValidationContext(MemberInquiryRequest.class.getSimpleName(), request,
	// ValidationContextIndicator.FETCH);
	//
	// if (getMemberPagedInquiryRequestValidationController().validate(context))
	// {
	// internalResponse = getMemberBAC().fetchMemberByRequest(request);
	// }
	//
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// return (MemberResponse)handleReturn(response, internalResponse, context.getMessages(), true);
	// }
}
