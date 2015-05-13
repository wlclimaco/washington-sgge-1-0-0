package com.prosperitasglobal.sendsolv.financeiro.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IFinanceiroBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.model.request.FinanceiroInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.FinanceiroResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.qat.framework.validation.ValidationUtil;

public class FinanceiroBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FinanceiroBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "FinanceiroBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Financeiro BAI. */
	private IFinanceiroBAI financeiroBAI;

	/** The Member BAI. */
	private IMemberBAI memberBAI;

	/**
	 * Gets the member BAI.
	 *
	 * @return the member BAI
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
	 * Gets the financeiro bai.
	 *
	 * @return the financeiro bai
	 */
	public IFinanceiroBAI getFinanceiroBAI()
	{
		return financeiroBAI;
	}

	/**
	 * Sets the financeiro bai.
	 *
	 * @param financeiroBAI the financeiro bai
	 */
	@Resource
	public void setFinanceiroBAI(IFinanceiroBAI financeiroBAI)
	{
		this.financeiroBAI = financeiroBAI;
	}

	/**
	 * Financeiro edit mav.
	 *
	 * @param financeiroId the financeiro id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView financeiroEditMAV(Integer financeiroId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(financeiroId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchFinanceiroById(new FetchByIdRequest(financeiroId))));

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
	 * Fetch enrolled members.
	 *
	 * @param financeiroId the financeiro id
	 * @return the integer
	 */
	private Integer fetchEnrolledMembers(Integer financeiroId, HttpServletRequest request)
	{
		MemberResponse memberResponse = fetchMembersEnrolledMember(financeiroId, BusinessTypeEnum.LOCATION, request);

		if (memberResponse.getMemberList() != null)
		{
			return memberResponse.getMemberList().size();
		}

		return 0;
	}

	/**
	 * Fetch financeiro by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the financeiro response
	 */
	public FinanceiroResponse fetchFinanceiroByRequest(FinanceiroInquiryRequest pagedInquiryRequest)
	{

		FinanceiroResponse financeiroResponse = new FinanceiroResponse();
		try
		{

			financeiroResponse = getFinanceiroBAI().fetchFinanceiroByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return financeiroResponse;
	}

	/**
	 * Fetch financeiro by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the financeiro response
	 */
	public FinanceiroResponse fetchFinanceiroById(FetchByIdRequest fetchByIdRequest)
	{

		FinanceiroResponse financeiroResponse = new FinanceiroResponse();
		try
		{

			financeiroResponse = getFinanceiroBAI().fetchFinanceiroById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return financeiroResponse;
	}

}
