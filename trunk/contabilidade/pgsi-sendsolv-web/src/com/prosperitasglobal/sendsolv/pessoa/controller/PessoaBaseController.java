package com.prosperitasglobal.sendsolv.pessoa.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IEmpresaBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.EmpresaResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.qat.framework.validation.ValidationUtil;

public class PessoaBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PessoaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "EmpresaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Empresa BAI. */
	private IEmpresaBAI locationBAI;

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
	public IEmpresaBAI getEmpresaBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setEmpresaBAI(IEmpresaBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Empresa edit mav.
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
						getMapper().writeValueAsString(fetchEmpresaById(new FetchByIdRequest(locationId))));

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
	public EmpresaResponse fetchEmpresaByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			// locationResponse = Mock();
			// getEmpresaBAI().fetchEmpresaByRequest(pagedInquiryRequest);

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
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest fetchByIdRequest)
	{

		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			// locationResponse = MockById();
			// getEmpresaBAI().fetchEmpresaById(fetchByIdRequest);

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
