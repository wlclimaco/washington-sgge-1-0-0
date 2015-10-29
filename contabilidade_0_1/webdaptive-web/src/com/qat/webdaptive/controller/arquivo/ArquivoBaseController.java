package com.qat.webdaptive.controller.arquivo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IArquivoBAI;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.ArquivoResponse;
import com.qat.framework.validation.ValidationUtil;

public class ArquivoBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ArquivoBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ArquivoBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Arquivo BAI. */
	private IArquivoBAI arquivoBAI;

	/**
	 * Gets the arquivo bai.
	 *
	 * @return the arquivo bai
	 */
	public IArquivoBAI getArquivoBAI()
	{
		return arquivoBAI;
	}

	/**
	 * Sets the arquivo bai.
	 *
	 * @param arquivoBAI the arquivo bai
	 */
	@Resource
	public void setArquivoBAI(IArquivoBAI arquivoBAI)
	{
		this.arquivoBAI = arquivoBAI;
	}

	/**
	 * Arquivo edit mav.
	 *
	 * @param arquivoId the arquivo id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView arquivoEditMAV(Integer arquivoId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(arquivoId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchArquivoById(new FetchByIdRequest(arquivoId))));

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
	 * Fetch arquivo by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the arquivo response
	 */
	public ArquivoResponse fetchArquivoByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		ArquivoResponse arquivoResponse = new ArquivoResponse();
		try
		{

			// arquivoResponse = getArquivoBAI().fetchArquivoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return arquivoResponse;
	}

	/**
	 * Fetch arquivo by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the arquivo response
	 */
	public ArquivoResponse fetchArquivoById(FetchByIdRequest fetchByIdRequest)
	{

		ArquivoResponse arquivoResponse = new ArquivoResponse();
		try
		{

			arquivoResponse = getArquivoBAI().fetchArquivoById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return arquivoResponse;
	}

}
