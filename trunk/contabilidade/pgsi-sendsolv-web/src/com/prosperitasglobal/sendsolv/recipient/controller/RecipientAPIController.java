package com.prosperitasglobal.sendsolv.recipient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The RecipientAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/recipient")
public class RecipientAPIController extends RecipientBaseController
{
	//
	// /** The URL mapping constants. */
	// private static final String APPLY_STATUS = "applyStatus";
	//
	// /** The Constant DELETE_RECIPIENT. */
	// private static final String DELETE_RECIPIENT = "/delete";
	//
	// /** The Constant EDIT_RECIPIENT. */
	// private static final String EDIT_RECIPIENT = "/update";
	//
	// /** The Constant FETCH_ALL. */
	// private static final String FETCH_ALL = "/fetchall";
	//
	// /** The Constant INSERT_RECIPIENT. */
	// private static final String INSERT_RECIPIENT = "/insert";
	//
	// /** The Constant FETCH. */
	// private static final String FETCH = "/fetch";
	//
	// /**
	// * Fetch all Recipients.
	// *
	// * @param recipientInquiryRequest the recipient inquiry request
	// * @return the response
	// */
	// @RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	// @ResponseBody
	// public RecipientResponse fetchAll(@RequestBody RecipientInquiryRequest recipientInquiryRequest)
	// {
	//
	// return fetchRecipientByRequest(recipientInquiryRequest);
	//
	// }
	//
	// /**
	// * Fetch.
	// *
	// * @param fetchByIdRequest the fetch by id request
	// * @return the recipient response
	// */
	// @RequestMapping(value = FETCH, method = RequestMethod.POST)
	// @ResponseBody
	// public RecipientResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	// {
	//
	// return fetchRecipientById(fetchByIdRequest);
	//
	// }
	//
	// /**
	// * Edit one recipient.
	// *
	// * @param recipientRequest the recipient request
	// * @return the response
	// */
	// @RequestMapping(value = EDIT_RECIPIENT, method = RequestMethod.POST)
	// @ResponseBody
	// public RecipientResponse edit(@RequestBody RecipientMaintenanceRequest recipientRequest)
	// {
	// return editRecipient(recipientRequest);
	//
	// }
	//
	// /**
	// * Delete one recipient.
	// *
	// * @param recipientRequest the recipient request
	// * @return the response
	// */
	// @RequestMapping(value = DELETE_RECIPIENT, method = RequestMethod.POST)
	// @ResponseBody
	// public RecipientResponse delete(@RequestBody RecipientMaintenanceRequest recipientRequest)
	// {
	//
	// return deleteRecipient(recipientRequest);
	//
	// }
	//
	// /**
	// * Insert one recipient.
	// *
	// * @param recipientRequest the recipient request
	// * @return the response
	// */
	// @RequestMapping(value = INSERT_RECIPIENT, method = RequestMethod.POST)
	// @ResponseBody
	// public RecipientResponse insert(@RequestBody RecipientMaintenanceRequest recipientRequest)
	// {
	//
	// return insertRecipient(recipientRequest);
	//
	// }
	//
	// /**
	// * Apply.
	// *
	// * @param recipientRequest the recipient request
	// * @return the recipient response
	// */
	// @RequestMapping(value = APPLY_STATUS, method = RequestMethod.POST)
	// @ResponseBody
	// public RecipientResponse apply(@RequestBody RecipientMaintenanceRequest recipientRequest)
	// {
	//
	// return applyStatus(recipientRequest);
	//
	// }

}
