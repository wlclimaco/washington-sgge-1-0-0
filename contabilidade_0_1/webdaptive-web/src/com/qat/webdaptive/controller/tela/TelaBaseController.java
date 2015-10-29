package com.qat.webdaptive.controller.tela;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.ITelaBAI;
import com.prosperitasglobal.sendsolv.model.request.TelaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.TelaResponse;
import com.qat.framework.validation.ValidationUtil;

public class TelaBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TelaBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TelaBaseController";

	/** The Tela BAI. */
	private ITelaBAI telaBAI;

	/**
	 * Gets the tela bai.
	 *
	 * @return the tela bai
	 */
	public ITelaBAI getTelaBAI()
	{
		return telaBAI;
	}

	/**
	 * Sets the tela bai.
	 *
	 * @param telaBAI the tela bai
	 */
	@Resource
	public void setTelaBAI(ITelaBAI telaBAI)
	{
		this.telaBAI = telaBAI;
	}

	/**
	 * Tela edit mav.
	 *
	 * @param telaId the tela id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView telaEditMAV(Integer telaId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(telaId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchTelaById(new FetchByIdRequest(telaId))));

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
	 * Fetch tela by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the tela response
	 */
	public TelaResponse fetchTelaByRequest(TelaInquiryRequest pagedInquiryRequest)
	{

		TelaResponse telaResponse = new TelaResponse();
		try
		{

			telaResponse = getTelaBAI().fetchTelaByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return telaResponse;
	}

	/**
	 * Fetch tela by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the tela response
	 */
	public TelaResponse fetchTelaById(FetchByIdRequest fetchByIdRequest)
	{

		TelaResponse telaResponse = new TelaResponse();
		try
		{

			telaResponse = getTelaBAI().fetchTelaById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return telaResponse;
	}

}
