package com.prosperitasglobal.sendsolv.sar.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.member.controller.MemberViewController;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

/**
 * The Class MemberViewController.
 *
 * @author Flavio Tosta, Washington Costa
 */
@Controller
@RequestMapping("/sar")
public class SarViewController extends SarBaseController
{
	private static final String RESPONSE_AUX = "responseAux";

	private static final String LIAISON = "liaison";

	private static final String SAR_SAR_VIEW = "/sar/sar_view";

	private static final String VIEW = "view";

	private static final String SAR_SAR_MAIN = "/sar/sar_main";

	private static final String LIST = "";

	private static final String SAR_SAR_DIALOG = "/sar/sar_dialog";

	private static final String RECIPIENT = "recipient";

	private static final String MEMBER = "member";

	private static final String SAR_DIALOG = "sar_dialog";

	private static final String ID2 = "id";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MemberViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "MemberViewController";

	/**
	 * Load add.
	 *
	 * @param memberId the member id
	 * @return the model and view
	 */
	@RequestMapping(value = {SAR_DIALOG}, method = RequestMethod.GET)
	public ModelAndView loadAdd(@RequestParam(value = ID2, required = false) Integer id, String type,
			HttpServletRequest request)
	{
		if ((type.equalsIgnoreCase(MEMBER)) || (type.equalsIgnoreCase(RECIPIENT)))
		{
			return memberEditMAV(id, SAR_SAR_DIALOG, request);
		}
		else
		{
			return new ModelAndView(SAR_SAR_DIALOG);
		}
	}

	@RequestMapping(value = LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(SAR_SAR_MAIN);

		// Check whether has initial load or not
		SarInquiryRequest sarInquiryRequest = new SarInquiryRequest();
		sarInquiryRequest.setStartPage(0);
		sarInquiryRequest.setPageSize(25);
		sarInquiryRequest.setPreQueryCount(true);
		sarInquiryRequest.addSortExpressions(new SortExpression(ID2, Direction.Ascending));

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchSuspiciousActivityByRequest(sarInquiryRequest)));
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

	@RequestMapping(value = {VIEW}, method = RequestMethod.GET)
	public ModelAndView sarView(@RequestParam(value = ID2, required = false) String id, Integer parentKey,
			String type,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(SAR_SAR_VIEW);
		try
		{
			if ((type.equalsIgnoreCase(MEMBER)) || (type.equalsIgnoreCase(RECIPIENT))
					|| (type.equalsIgnoreCase(LIAISON)))
			{
				if (type.equalsIgnoreCase(MEMBER))
				{
					modelAndView.addObject(RESPONSE_AUX, getMapper()
							.writeValueAsString(fetchMemberById(new FetchByIdRequest(parentKey))));
				}
				else if (type.equalsIgnoreCase(RECIPIENT))
				{
					modelAndView.addObject(RESPONSE_AUX, getMapper()
							.writeValueAsString(fetchRecipientByID(new FetchByIdRequest(parentKey))));
				}
				else if (type.equalsIgnoreCase(LIAISON))
				{
					modelAndView.addObject(RESPONSE_AUX, getMapper()
							.writeValueAsString(fetchLiaisonByID(new FetchByIdRequest(parentKey))));
				}
				modelAndView.addObject(RESPONSE, getMapper()
						.writeValueAsString(fetchSuspiciousActivityByIdRequest(new FetchByIdRequest(id))));
			}
			else
			{
				if (type.equalsIgnoreCase("LOCATION"))
				{
					modelAndView.addObject(RESPONSE_AUX, getMapper()
							.writeValueAsString(fetchLocationById(new FetchByIdRequest(parentKey))));
				}
				else if (type.equalsIgnoreCase("ORGANIZATION"))
				{
					modelAndView.addObject(RESPONSE_AUX, getMapper()
							.writeValueAsString(fetchOrganizationById(new FetchByIdRequest(parentKey))));
				}

				modelAndView.addObject(RESPONSE, getMapper()
						.writeValueAsString(fetchBusinessSuspiciousActivityByIdRequest(new FetchByIdRequest(id))));
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

}
