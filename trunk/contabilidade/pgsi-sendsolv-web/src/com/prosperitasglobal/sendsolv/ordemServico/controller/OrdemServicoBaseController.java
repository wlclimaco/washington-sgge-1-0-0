package com.prosperitasglobal.sendsolv.ordemServico.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IOrdemServicoBAI;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.OrdemServicoResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class OrdemServicoBaseController.
 */

/**
 * @author Flavio Tosta.
 *
 */
public class OrdemServicoBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrdemServicoBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "OrdemServicoBaseController";

	/** The OrdemServico BAI. */
	private IOrdemServicoBAI ordemServicoBAI;

	/**
	 * Gets the ordemServico bai.
	 *
	 * @return the ordemServico bai
	 */
	public IOrdemServicoBAI getOrdemServicoBAI()
	{
		return ordemServicoBAI;
	}

	/**
	 * Sets the ordemServico bai.
	 *
	 * @param ordemServicoBAI the ordemServico bai
	 */
	@Resource
	public void setOrdemServicoBAI(IOrdemServicoBAI ordemServicoBAI)
	{
		this.ordemServicoBAI = ordemServicoBAI;
	}

	/**
	 * OrdemServico edit mav.
	 *
	 * @param ordemServicoId the ordemServico id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView ordemServicoEditMAV(Integer ordemServicoId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(ordemServicoId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchOrdemServicoById(new FetchByIdRequest(ordemServicoId))));

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
	 * Fetch ordemServico by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the ordemServico response
	 */
	public OrdemServicoResponse fetchOrdemServicoByRequest(OrdemServicoInquiryRequest pagedInquiryRequest)
	{

		OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();
		try
		{

			// ordemServicoResponse = Mock();
			getOrdemServicoBAI().fetchOrdemServicoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return ordemServicoResponse;
	}

	/**
	 * Fetch ordemServico by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the ordemServico response
	 */
	public OrdemServicoResponse fetchOrdemServicoById(FetchByIdRequest fetchByIdRequest)
	{

		OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();
		try
		{

			// ordemServicoResponse = MockById();
			getOrdemServicoBAI().fetchOrdemServicoById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return ordemServicoResponse;
	}

}
