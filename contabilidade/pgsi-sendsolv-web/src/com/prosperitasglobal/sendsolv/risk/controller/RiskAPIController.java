package com.prosperitasglobal.sendsolv.risk.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.RiskResponse;

/**
 * The OrganizationAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/api/risk")
public class RiskAPIController extends BaseController
{
	/** The URL mapping constants. */
	private static final String EDIT = "/edit";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RiskAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "RiskAPIController";

	/** The organization bai. */
	private IOrganizationBAI organizationBAI;

	/**
	 * Gets the organization bai.
	 *
	 * @return the organization bai
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * Sets the organization bai.
	 *
	 * @param organizationBAI the organization bai
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	/**
	 * Edit one Organization.
	 *
	 * @param riskRequest the risk request
	 * @return the response
	 */
	@RequestMapping(value = EDIT, method = RequestMethod.POST)
	@ResponseBody
	public RiskResponse edit(@RequestBody RiskMaintenanceRequest riskRequest)
	{
		RiskResponse riskResponse = new RiskResponse();
		try
		{

			riskResponse = getOrganizationBAI().updateRisk(riskRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			riskResponse = null;
		}
		return riskResponse;

	}
}
