package com.qat.webdaptive.controller.historico;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IHistoricoBAI;
import com.prosperitasglobal.sendsolv.model.request.AlertasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.AlertasResponse;
import com.prosperitasglobal.sendsolv.model.response.HistoricoResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class HistoricoBaseController.
 */

/**
 * @author Flavio Tosta.
 *
 */
public class HistoricoBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistoricoBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "HistoricoBaseController";

	/** The Historico BAI. */
	private IHistoricoBAI historicoBAI;

	/**
	 * Gets the historico bai.
	 *
	 * @return the historico bai
	 */
	public IHistoricoBAI getHistoricoBAI()
	{
		return historicoBAI;
	}

	/**
	 * Sets the historico bai.
	 *
	 * @param historicoBAI the historico bai
	 */
	@Resource
	public void setHistoricoBAI(IHistoricoBAI historicoBAI)
	{
		this.historicoBAI = historicoBAI;
	}

	/**
	 * Historico edit mav.
	 *
	 * @param historicoId the historico id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView historicoEditMAV(Integer historicoId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(historicoId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchHistoricoById(new FetchByIdRequest(historicoId))));

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
	 * Fetch historico by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the historico response
	 */
	public HistoricoResponse fetchHistoricoByRequest(HistoricoInquiryRequest pagedInquiryRequest)
	{

		HistoricoResponse historicoResponse = new HistoricoResponse();
		try
		{

			// historicoResponse = Mock();
			historicoResponse = getHistoricoBAI().fetchHistoricoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return historicoResponse;
	}

	public AlertasResponse fetchAllAlertas(AlertasInquiryRequest pagedInquiryRequest)
	{

		AlertasResponse historicoResponse = new AlertasResponse();
		try
		{

			// historicoResponse = Mock();
			historicoResponse = getHistoricoBAI().fetchAlertasByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return historicoResponse;
	}

	/**
	 * Fetch historico by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the historico response
	 */
	public HistoricoResponse fetchHistoricoById(FetchByIdRequest fetchByIdRequest)
	{

		HistoricoResponse historicoResponse = new HistoricoResponse();
		try
		{

			// historicoResponse = MockById();
			getHistoricoBAI().fetchHistoricoById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return historicoResponse;
	}

}
