package com.prosperitasglobal.sendsolv.cliente.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IPessoaBAI;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClienteMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.ClienteResponse;
import com.qat.framework.validation.ValidationUtil;

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

			clienteResponse = getPessoaBAI().fetchClienteByRequest(pagedInquiryRequest);

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

			clienteResponse = getPessoaBAI().fetchClienteById(fetchByIdRequest);

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

	public ClienteResponse edit(ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getPessoaBAI().updateCliente(clienteRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}
		return clienteResponse;

	}

	public ClienteResponse delete(ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getPessoaBAI().deleteCliente(clienteRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}

		return clienteResponse;

	}

	public ClienteResponse insert(ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();

		try
		{

			clienteRequest.getCliente().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			clienteResponse = getPessoaBAI().insertCliente(clienteRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}

		return clienteResponse;

	}
}
