package com.prosperitasglobal.sendsolv.tabela.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.ITabelaBAI;
import com.prosperitasglobal.sendsolv.model.request.TabelaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.model.response.TabelaResponse;
import com.qat.framework.validation.ValidationUtil;

public class TabelaBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TabelaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TabelaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Tabela BAI. */
	private ITabelaBAI locationBAI;

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
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ITabelaBAI getTabelaBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setTabelaBAI(ITabelaBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Tabela edit mav.
	 *
	 * @param locationId the location id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView locationEditMAV(Integer locationId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(locationId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchTabelaById(new FetchByIdRequest(locationId))));

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
	 * @param locationId the location id
	 * @return the integer
	 */
	private Integer fetchEnrolledMembers(Integer locationId, HttpServletRequest request)
	{
		MemberResponse memberResponse = fetchMembersEnrolledMember(locationId, BusinessTypeEnum.LOCATION, request);

		if (memberResponse.getMemberList() != null)
		{
			return memberResponse.getMemberList().size();
		}

		return 0;
	}

	/**
	 * Fetch location by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the location response
	 */
	public TabelaResponse fetchTabelaByRequest(TabelaInquiryRequest pagedInquiryRequest)
	{

		TabelaResponse locationResponse = new TabelaResponse();
		try
		{

			locationResponse = getTabelaBAI().fetchTabelaByRequest(pagedInquiryRequest);

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
	 * Fetch location by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	public TabelaResponse fetchTabelaById(FetchByIdRequest fetchByIdRequest)
	{

		TabelaResponse locationResponse = new TabelaResponse();
		try
		{

			locationResponse = getTabelaBAI().fetchTabelaById(fetchByIdRequest);

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
}
