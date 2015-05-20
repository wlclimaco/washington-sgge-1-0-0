package com.prosperitasglobal.sendsolv.empresa.controller;

import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

public class EmpresaBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "EmpresaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Empresa BAI. */
	private IEmpresaBAI empresaBAI

	/**
	 * Gets the empresa bai.
	 *
	 * @return the empresa bai
	 */
	public IEmpresaBAI getEmpresaBAI()
	{
		return empresaBAI;
	}

	/**
	 * Sets the empresa bai.
	 *
	 * @param empresaBAI the empresa bai
	 */
	@Resource
	public void setEmpresaBAI(IEmpresaBAI empresaBAI)
	{
		this.empresaBAI = empresaBAI;
	}

	/**
	 * Empresa edit mav.
	 *
	 * @param empresaId the empresa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView empresaEditMAV(Integer empresaId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(empresaId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchEmpresaById(new FetchByIdRequest(empresaId))));

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
	 * @param empresaId the empresa id
	 * @return the integer
	 */
	private Integer fetchEnrolledMembers(Integer empresaId, HttpServletRequest request)
	{
		MemberResponse memberResponse = fetchMembersEnrolledMember(empresaId, BusinessTypeEnum.LOCATION, request);

		if (memberResponse.getMemberList() != null)
		{
			return memberResponse.getMemberList().size();
		}

		return 0;
	}

	/**
	 * Fetch empresa by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		EmpresaResponse empresaResponse = new EmpresaResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchEmpresaByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

	/**
	 * Fetch empresa by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest fetchByIdRequest)
	{

		EmpresaResponse empresaResponse = new EmpresaResponse();
		try
		{

			empresaResponse = getEmpresaBAI().fetchEmpresaById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return empresaResponse;
	}

}
