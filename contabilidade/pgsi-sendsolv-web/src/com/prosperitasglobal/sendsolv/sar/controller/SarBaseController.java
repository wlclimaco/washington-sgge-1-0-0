package com.prosperitasglobal.sendsolv.sar.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;
import com.prosperitasglobal.sendsolv.model.response.SarResponse;
import com.qat.framework.model.response.MaintenanceResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MemberBaseController.
 */
public class SarBaseController extends BaseController
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SarBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "MemberBaseController";

	/** The Organization BAI. */
	private IMemberBAI memberBAI;

	/** The Organization BAI. */
	private IOrganizationBAI organizationBAI;

	private IRecipientBAI recipientBAI;

	private ILiaisonBAI liaisonBAI;

	/** The Organization BAI. */
	private ILocationBAI locationBAI;

	private ISuspiciousActivityBAI suspiciousActivityBAI;

	/**
	 * @return the organizationBAI
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * @param organizationBAI the organizationBAI to set
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
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
	@Resource
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
	}

	/**
	 * @return the liaisonBAI
	 */
	public ILiaisonBAI getLiaisonBAI()
	{
		return liaisonBAI;
	}

	/**
	 * @param liaisonBAI the liaisonBAI to set
	 */
	@Resource
	public void setLiaisonBAI(ILiaisonBAI liaisonBAI)
	{
		this.liaisonBAI = liaisonBAI;
	}

	/**
	 * @return the locationBAI
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * @param locationBAI the locationBAI to set
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * @return the suspiciousActivityBAI
	 */
	public ISuspiciousActivityBAI getSuspiciousActivityBAI()
	{
		return suspiciousActivityBAI;
	}

	/**
	 * @param suspiciousActivityBAI the suspiciousActivityBAI to set
	 */
	@Resource
	public void setSuspiciousActivityBAI(ISuspiciousActivityBAI suspiciousActivityBAI)
	{
		this.suspiciousActivityBAI = suspiciousActivityBAI;
	}

	/**
	 * Gets the member bai.
	 *
	 * @return the member bai
	 */

	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the member bai
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/**
	 * Member edit mav.
	 *
	 * @param memberId the member id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView memberEditMAV(Integer memberId, String returnViewName, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (!ValidationUtil.isNullOrZero(memberId))
			{
				FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(memberId);
				fetchByIdRequest.setUserContext(fetchUserContext(request));
				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchMemberById(fetchByIdRequest)));

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
	 * Fetch member by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the member response
	 */
	public MemberResponse fetchMemberById(FetchByIdRequest fetchByIdRequest)
	{

		MemberResponse memberResponse = new MemberResponse();
		try
		{

			memberResponse = getMemberBAI().fetchMemberById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return memberResponse;
	}

	public LocationResponse fetchLocationById(FetchByIdRequest fetchByIdRequest)
	{

		LocationResponse locationResponse = new LocationResponse();
		try
		{

			locationResponse = getLocationBAI().fetchLocationById(fetchByIdRequest);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	/**
	 * Insert member.
	 *
	 * @param memberRequest the member request
	 * @return the member response
	 */
	public MaintenanceResponse insertSuspiciousActivity(SarMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();

		try
		{

			response = getSuspiciousActivityBAI().insertSuspiciousActivity(request);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			response = null;
		}

		return response;

	}

	public MaintenanceResponse updateSuspiciousActivity(SarMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();

		try
		{

			response = getSuspiciousActivityBAI().updateSuspiciousActivity(request);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			response = null;
		}

		return response;

	}

	public SarResponse fetchSuspiciousActivityByRequest(SarInquiryRequest request)
	{

		SarResponse response = new SarResponse();
		try
		{

			response = getSuspiciousActivityBAI().fetchSuspiciousActivityByRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public SarResponse fetchSuspiciousActivityByIdRequest(FetchByIdRequest request)
	{

		SarResponse response = new SarResponse();
		try
		{

			response = getSuspiciousActivityBAI().fetchSuspiciousActivityByIdRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public SarResponse fetchBusinessSuspiciousActivityByIdRequest(FetchByIdRequest request)
	{

		SarResponse response = new SarResponse();
		try
		{

			response = getSuspiciousActivityBAI().fetchBusinessSuspiciousActivityByIdRequest(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public Object fetchMemberByID(FetchByIdRequest request)
	{

		MemberResponse response = new MemberResponse();
		try
		{

			response = getMemberBAI().fetchMemberById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public Object fetchLocationBy(FetchByIdRequest request)
	{

		LocationResponse response = new LocationResponse();
		try
		{

			response = getLocationBAI().fetchLocationById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public Object fetchOrganizationById(FetchByIdRequest request)
	{

		OrganizationResponse response = new OrganizationResponse();
		try
		{

			response = getOrganizationBAI().fetchOrganizationById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public Object fetchRecipientByID(FetchByIdRequest request)
	{

		RecipientResponse response = new RecipientResponse();
		try
		{

			response = getRecipientBAI().fetchRecipientById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}

	public Object fetchLiaisonByID(FetchByIdRequest request)
	{

		LiaisonResponse response = new LiaisonResponse();
		try
		{

			response = getLiaisonBAI().fetchLiaisonById(request);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return response;
	}
}
