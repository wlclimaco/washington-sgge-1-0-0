package com.prosperitasglobal.sendsolv.cliente.controller;

import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

public class ClienteBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClienteBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "PessoaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

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
	 * Cliente edit mav.
	 *
	 * @param pessoaId the pessoa id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView clienteEditMAV(Integer clienteId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(clienteId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchClienteById(new FetchByIdRequest(clienteId))));

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
	 * Fetch cliente by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the cliente response
	 */
	public ClienteResponse fetchClienteByRequest(ClienteInquiryRequest pagedInquiryRequest)
	{

		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getClienteBAI().fetchClienteByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return clienteResponse;
	}

	/**
	 * Fetch cliente by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the cliente response
	 */
	public ClienteResponse fetchClienteById(FetchByIdRequest fetchByIdRequest)
	{

		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getClienteBAI().fetchClienteById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return clienteResponse;
	}
}
