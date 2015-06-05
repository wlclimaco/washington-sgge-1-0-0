package com.prosperitasglobal.sendsolv.transportador.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IPessoaBAI;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.TransportadorResponse;
import com.qat.framework.validation.ValidationUtil;

public class TransportadorBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransportadorBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "PessoaBaseController";

	/** The Pessoa BAI. */
	private IPessoaBAI pessoaBAI;

	/**
	 * Gets the pessoa bai.
	 *
	 * @return the pessoa bai
	 */
	public IPessoaBAI getPessoaBAI()
	{
		return pessoaBAI;
	}

	/**
	 * Sets the pessoa bai.
	 *
	 * @param pessoaBAI the pessoa bai
	 */
	@Resource
	public void setPessoaBAI(IPessoaBAI pessoaBAI)
	{
		this.pessoaBAI = pessoaBAI;
	}

	/**
	 * Transportador edit mav.
	 *
	 * @param pessoaId the pessoa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView transportadorEditMAV(Integer transportadorId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(transportadorId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchTransportadorById(new FetchByIdRequest(transportadorId))));

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
	 * Fetch transportador by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the transportador response
	 */
	public TransportadorResponse fetchTransportadorByRequest(TransportadorInquiryRequest pagedInquiryRequest)
	{

		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try
		{

			transportadorResponse = getPessoaBAI().fetchTransportadorByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return transportadorResponse;
	}

	/**
	 * Fetch transportador by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the transportador response
	 */
	public TransportadorResponse fetchTransportadorById(FetchByIdRequest fetchByIdRequest)
	{

		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try
		{

			transportadorResponse = getPessoaBAI().fetchTransportadorById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return transportadorResponse;
	}
}
