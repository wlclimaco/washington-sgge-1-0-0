package com.prosperitasglobal.sendsolv.contact.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;

/**
 * The ContactViewController Class.
 *
 * @author Flavio Tosta
 *
 */
@Controller
@RequestMapping("/contact")
public class ContactViewController extends ContactBaseController
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "";
	private static final String FETCH_ADD = "/insert";
	private static final String FETCH_EDIT = "/update";

	/** The view mapping constants . */
	private static final String VIEW_CONTACT_MAIN = "/contact/contact_main";
	private static final String VIEW_CONTACT_ADD = "/contact/contact_dialog_create";
	private static final String CONTACT_ID = "contactId";

	/**
	 * Load list views.
	 *
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_CONTACT_MAIN);
		isInitialLoad(request, modelAndView);

		return modelAndView;
	}

	/**
	 * Load add/edit view.
	 *
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_ADD, FETCH_EDIT}, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(value = CONTACT_ID, required = false) Integer contactId,
			HttpServletRequest request)
	{
		FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(contactId);
		fetchByIdRequest.setUserContext(fetchUserContext(request));

		return liaisonEditMAV(fetchByIdRequest, VIEW_CONTACT_ADD, request);

	}
}
