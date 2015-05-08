package com.prosperitasglobal.sendsolv.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MemberBaseController.
 */
public class MemberBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MemberBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "MemberBaseController";

	/** The Organization BAI. */
	private IMemberBAI memberBAI;

	/** The filter factory. */
	private FilterFactory filterFactory;

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory()
	{
		return filterFactory;
	}

	/**
	 * @param filterFactory the filterFactory to set
	 */
	@Resource
	public void setFilterFactory(FilterFactory filterFactory)
	{
		this.filterFactory = filterFactory;
	}

	/**
	 * Gets the member bai.
	 *
	 * @return the member bai
	 */
	@Override
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the member bai
	 */
	@Override
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
	protected ModelAndView memberEditMAV(Integer memberId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectContact(modelAndView, request);
			}
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
	 * Transfer edit mav.
	 *
	 * @param transferId the transfer id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView transferEditMAV(Integer transferId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectTransfer(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(transferId))
			{
				FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(transferId);
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
	 * Fetch member by request.
	 *
	 * @param memberInquiryRequest the member inquiry request
	 * @return the member response
	 */
	public MemberResponse fetchMemberByRequest(MemberInquiryRequest memberInquiryRequest)
	{

		MemberResponse memberResponse = new MemberResponse();
		try
		{

			memberResponse =
					getMemberBAI().fetchMemberByRequest(memberInquiryRequest);

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

	/**
	 * Insert member.
	 *
	 * @param memberRequest the member request
	 * @return the member response
	 */
	public MemberResponse insertMember(MemberMaintenanceRequest memberRequest)
	{
		MemberResponse memberResponse = new MemberResponse();

		try
		{

			memberResponse = getMemberBAI().insertMember(memberRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			memberResponse = null;
		}

		return memberResponse;

	}

	/**
	 * Delete member.
	 *
	 * @param memberRequest the member request
	 * @return the member response
	 */
	public MemberResponse deleteMember(MemberMaintenanceRequest memberRequest)
	{
		MemberResponse memberResponse = new MemberResponse();
		try
		{

			memberResponse = getMemberBAI().deleteMember(memberRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			memberResponse = null;
		}

		return memberResponse;

	}

	/**
	 * Edits the member.
	 *
	 * @param memberRequest the member request
	 * @return the member response
	 */
	public MemberResponse editMember(MemberMaintenanceRequest memberRequest)
	{
		MemberResponse memberResponse = new MemberResponse();

		try
		{

			memberResponse = getMemberBAI().updateMember(memberRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			memberResponse = null;
		}
		return memberResponse;

	}

	/**
	 * Apply status.
	 *
	 * @param memberRequest the member request
	 * @return the member response
	 */
	public MemberResponse applyStatus(MemberMaintenanceRequest memberRequest)
	{
		MemberResponse memberResponse = new MemberResponse();
		try
		{

			memberResponse = getMemberBAI().applyStatus(memberRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			memberResponse = null;
		}
		return memberResponse;

	}
}
