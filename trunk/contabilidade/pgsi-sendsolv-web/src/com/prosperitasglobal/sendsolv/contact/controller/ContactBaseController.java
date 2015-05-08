package com.prosperitasglobal.sendsolv.contact.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ContactBaseController.
 */
public class ContactBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String COUNTRIES = "countries";

	/** The Constant RESPONSE. */
	public static final String RESPONSE = "response";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ContactBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "OrganizationBaseController";

	/** The Organization BAI. */
	private ILiaisonBAI liaisonBAI;

	/**
	 * Gets the liaison bai.
	 *
	 * @return the liaison bai
	 */
	public ILiaisonBAI getLiaisonBAI()
	{
		return liaisonBAI;
	}

	/**
	 * Sets the liaison bai.
	 *
	 * @param liaisonBAI the liaison bai
	 */
	@Resource
	public void setLiaisonBAI(ILiaisonBAI liaisonBAI)
	{
		this.liaisonBAI = liaisonBAI;
	}

	/**
	 * Location edit mav.
	 *
	 * @param liaison the liaison
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView liaisonEditMAV(FetchByIdRequest fetchByIdRequest, String returnViewName,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			modelAndView = listSelectContact(modelAndView, request);

			if (!ValidationUtil.isNullOrZero(fetchByIdRequest.getId()))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchLiaison(fetchByIdRequest)));

				return modelAndView;
			}

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
	 * Fetch all liaison.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the liaison response
	 */
	public LiaisonResponse fetchAllLiaison(PagedInquiryRequest pagedInquiryRequest)
	{

		LiaisonResponse liaisonResponse = new LiaisonResponse();
		try
		{

			liaisonResponse = getLiaisonBAI().fetchLiaisonByRequest(pagedInquiryRequest);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				liaisonResponse = null;
			}
		}

		return liaisonResponse;
	}

	/**
	 * Fetch liaison.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the liaison response
	 */
	public LiaisonResponse fetchLiaison(FetchByIdRequest fetchByIdRequest)
	{

		LiaisonResponse liaisonResponse = new LiaisonResponse();
		try
		{

			liaisonResponse = getLiaisonBAI().fetchLiaisonById(fetchByIdRequest);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				liaisonResponse = null;
			}
		}

		return liaisonResponse;
	}

	/**
	 * Edits the liaison.
	 *
	 * @param LiaisonMaintenanceRequest the liaison request
	 * @return the liaison response
	 */
	public LiaisonResponse editLiaison(LiaisonMaintenanceRequest liaisonMaintenanceRequest)
	{
		LiaisonResponse liaisonResponse = new LiaisonResponse();
		try
		{
			liaisonResponse = getLiaisonBAI().updateLiaison(liaisonMaintenanceRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			liaisonResponse = null;
		}
		return liaisonResponse;

	}

	/**
	 * Delete liaison.
	 *
	 * @param LiaisonMaintenanceRequest the liaison request
	 * @return the liaison response
	 */
	public LiaisonResponse deleteLiaison(LiaisonMaintenanceRequest liaisonMaintenanceRequest)
	{
		LiaisonResponse liaisonResponse = new LiaisonResponse();
		try
		{

			liaisonResponse = getLiaisonBAI().deleteLiaison(liaisonMaintenanceRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			liaisonResponse = null;
		}

		return liaisonResponse;

	}

	/**
	 * Insert liaison.
	 *
	 * @param LiaisonMaintenanceRequest the liaison request
	 * @return the liaison response
	 */
	public LiaisonResponse insertLiaison(LiaisonMaintenanceRequest liaisonMaintenanceRequest)
	{
		LiaisonResponse liaisonResponse = new LiaisonResponse();

		try
		{

			liaisonResponse = getLiaisonBAI().insertLiaison(liaisonMaintenanceRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			liaisonResponse = null;
		}

		return liaisonResponse;
	}
}
