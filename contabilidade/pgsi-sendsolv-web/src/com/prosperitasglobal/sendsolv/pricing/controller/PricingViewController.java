package com.prosperitasglobal.sendsolv.pricing.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.common.controller.BaseViewController;
import com.prosperitasglobal.sendsolv.model.criteria.InquiryCriteria;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;

/**
 * The Class PricingViewController.
 */
@Controller
@RequestMapping("/pricing")
public class PricingViewController extends BaseViewController
{

	/** The Constant FETCH_LIST. */
	private static final String FETCH_LIST = "/profile_list";

	/** The Constant VIEW_MAIN. */
	private static final String VIEW_MAIN = "/pricing/pricing_main";

	/** The Constant START_PAGE_NUMBER. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PricingViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "PricingViewController";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW = "/profile_view";

	/** The Constant VIEW_PROFILE. */
	private static final String VIEW_PROFILE = "/pricing/pricing_view";

	/** The Constant LOCATION_ID. */
	private static final String LOCATION_ID = "locationId";

	/** The product plan bai. */
	private ILocationBAI locationBAI;

	/**
	 * Gets the location bai.
	 *
	 * @return the locationBAI
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the locationBAI to set
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Load list.
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		LocationResponse locationResponse = null;
		PagedInquiryRequest inquiryRequest = new PagedInquiryRequest();
		inquiryRequest.setStartPage(START_PAGE_NUMBER);
		inquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		InquiryCriteria inquiryCriteria = new InquiryCriteria();
		inquiryCriteria.setHasProductPlan(true);
		inquiryRequest.setInquiryCriteria(inquiryCriteria);

		try
		{

			locationResponse = locationBAI.fetchLocationByRequest(inquiryRequest);
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(locationResponse));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	/**
	 * Load view.
	 *
	 * @param locationId the location id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_VIEW, method = RequestMethod.GET)
	public ModelAndView loadView(@RequestParam(value = LOCATION_ID, required = true) Integer locationId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_PROFILE);

		try
		{
			modelAndView.addObject(RESPONSE,
					getMapper()
					.writeValueAsString(getLocationBAI().fetchLocationById(new FetchByIdRequest(locationId))));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}
}
