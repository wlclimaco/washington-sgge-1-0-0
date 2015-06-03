package com.prosperitasglobal.sendsolv.fornecedor.controller;

import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

public class FornecedorBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FornecedorBaseController.class);

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
	 * Fornecedor edit mav.
	 *
	 * @param pessoaId the pessoa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView fornecedorEditMAV(Integer fornecedorId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(fornecedorId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchFornecedorById(new FetchByIdRequest(fornecedorId))));

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
	 * Fetch fornecedor by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the fornecedor response
	 */
	public FornecedorResponse fetchFornecedorByRequest(FornecedorInquiryRequest pagedInquiryRequest)
	{

		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try
		{

			fornecedorResponse = getFornecedorBAI().fetchFornecedorByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return fornecedorResponse;
	}

	/**
	 * Fetch fornecedor by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the fornecedor response
	 */
	public FornecedorResponse fetchFornecedorById(FetchByIdRequest fetchByIdRequest)
	{

		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try
		{

			fornecedorResponse = getFornecedorBAI().fetchFornecedorById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return fornecedorResponse;
	}
}
