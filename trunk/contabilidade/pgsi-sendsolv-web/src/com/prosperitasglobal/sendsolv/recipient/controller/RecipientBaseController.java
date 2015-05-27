package com.prosperitasglobal.sendsolv.recipient.controller;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

/**
 * The Class RecipientBaseController.
 */
public class RecipientBaseController extends UtilControllerD
{

	// /** The Response constants. */
	// public static final String RESPONSE = "response";
	//
	// /** The Constant LOG. */
	// private static final Logger LOG = LoggerFactory.getLogger(RecipientBaseController.class);
	//
	// /** The Constant CONTROLLER_EXCEPTION_MSG. */
	// private static final String CONTROLLER_EXCEPTION_MSG = "RecipientBaseController";
	//
	// /** The Organization BAI. */
	// private IRecipientBAI recipientBAI;
	//
	// /**
	// * Gets the recipient bai.
	// *
	// * @return the recipient bai
	// */
	// public IRecipientBAI getRecipientBAI()
	// {
	// return recipientBAI;
	// }
	//
	// /**
	// * Sets the recipient bai.
	// *
	// * @param recipientBAI the recipient bai
	// */
	// @Resource
	// public void setRecipientBAI(IRecipientBAI recipientBAI)
	// {
	// this.recipientBAI = recipientBAI;
	// }
	//
	// /**
	// * Recipient edit mav.
	// *
	// * @param recipientId the recipient id
	// * @param returnViewName the return view name
	// * @param isSelect the is select
	// * @return the model and view
	// */
	// protected ModelAndView recipientEditMAV(Integer recipientId, String returnViewName, Boolean isSelect,
	// HttpServletRequest request)
	// {
	// ModelAndView modelAndView = new ModelAndView(returnViewName);
	//
	// try
	// {
	//
	// if (isSelect)
	// {
	// modelAndView = listSelectContact(modelAndView, request);
	// }
	// if (!ValidationUtil.isNullOrZero(recipientId))
	// {
	// FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(recipientId);
	// fetchByIdRequest.setUserContext(fetchUserContext(request));
	//
	// modelAndView.addObject(RESPONSE,
	// getMapper().writeValueAsString(fetchRecipientById(fetchByIdRequest)));
	//
	// return modelAndView;
	// }
	//
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// modelAndView.addObject(RESPONSE, null);
	// }
	// }
	//
	// return modelAndView;
	// }
	//
	// /**
	// * Fetch recipient by request.
	// *
	// * @param recipientInquiryRequest the recipient inquiry request
	// * @return the recipient response
	// */
	// public RecipientResponse fetchRecipientByRequest(RecipientInquiryRequest recipientInquiryRequest)
	// {
	//
	// RecipientResponse recipientResponse = new RecipientResponse();
	// try
	// {
	//
	// recipientResponse =
	// getRecipientBAI().fetchRecipientByRequest(recipientInquiryRequest);
	//
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// }
	// }
	//
	// return recipientResponse;
	// }
	//
	// /**
	// * Fetch recipient by id.
	// *
	// * @param fetchByIdRequest the fetch by id request
	// * @return the recipient response
	// */
	// public RecipientResponse fetchRecipientById(FetchByIdRequest fetchByIdRequest)
	// {
	//
	// RecipientResponse recipientResponse = new RecipientResponse();
	// try
	// {
	//
	// recipientResponse =
	// getRecipientBAI().fetchRecipientById(fetchByIdRequest);
	//
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// }
	// }
	//
	// return recipientResponse;
	// }
	//
	// /**
	// * Insert recipient.
	// *
	// * @param recipientRequest the recipient request
	// * @return the recipient response
	// */
	// public RecipientResponse insertRecipient(RecipientMaintenanceRequest recipientRequest)
	// {
	// RecipientResponse recipientResponse = new RecipientResponse();
	//
	// try
	// {
	//
	// recipientResponse = getRecipientBAI().insertRecipient(recipientRequest);
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// recipientResponse = null;
	// }
	//
	// return recipientResponse;
	//
	// }
	//
	// /**
	// * Delete recipient.
	// *
	// * @param recipientRequest the recipient request
	// * @return the recipient response
	// */
	// public RecipientResponse deleteRecipient(RecipientMaintenanceRequest recipientRequest)
	// {
	// RecipientResponse recipientResponse = new RecipientResponse();
	// try
	// {
	//
	// recipientResponse = getRecipientBAI().deleteRecipient(recipientRequest);
	//
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// recipientResponse = null;
	// }
	//
	// return recipientResponse;
	//
	// }
	//
	// /**
	// * Edits the recipient.
	// *
	// * @param recipientRequest the recipient request
	// * @return the recipient response
	// */
	// public RecipientResponse editRecipient(RecipientMaintenanceRequest recipientRequest)
	// {
	// RecipientResponse recipientResponse = new RecipientResponse();
	// try
	// {
	//
	// recipientResponse = getRecipientBAI().updateRecipient(recipientRequest);
	//
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// recipientResponse = null;
	// }
	// return recipientResponse;
	//
	// }
	//
	// /**
	// * Apply status.
	// *
	// * @param recipientRequest the recipient request
	// * @return the recipient response
	// */
	// public RecipientResponse applyStatus(RecipientMaintenanceRequest recipientRequest)
	// {
	// RecipientResponse recipientResponse = new RecipientResponse();
	// try
	// {
	//
	// recipientResponse = getRecipientBAI().applyStatus(recipientRequest);
	//
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// recipientResponse = null;
	// }
	// return recipientResponse;
	//
	// }

}
