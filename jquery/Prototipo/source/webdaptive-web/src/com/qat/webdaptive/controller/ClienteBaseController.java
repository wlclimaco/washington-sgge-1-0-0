package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.cliente.bas.IClienteBAS;
import com.qat.samples.sysmgmt.cliente.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Class CountyBaseController.
 */
public class ClienteBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClienteBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.cliente.defaultexception";

	/** The Constant CLIENTE_RESPONSE. */
	private static final String CLIENTE_RESPONSE = "clienteResponse";

	/**
	 * Cliente mav.
	 * 
	 * @param request the request
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView clienteMAV(PagedInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(CLIENTE_RESPONSE, mapper.writeValueAsString(clienteFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(CLIENTE_RESPONSE, null);
		}
		return modelAndView;
	}

	protected ModelAndView cadastroMAV(PagedInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(CLIENTE_RESPONSE, null);
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(CLIENTE_RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Refresh clientes.
	 * 
	 * @param request the request
	 * @return the cliente response
	 */
	protected ClienteResponse refreshClientes(RefreshRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IClienteBAS client = (IClienteBAS)QATAppContext.getBean("clienteBASClientTarget");
			response = client.refreshClientes(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Maintain clientes.
	 * 
	 * @param request the request
	 * @param persistType the persist type
	 * @return the cliente response
	 */
	protected ClienteResponse maintainClientes(ClienteMaintenanceRequest request,
			PersistanceActionEnum persistType)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IClienteBAS client = (IClienteBAS)QATAppContext.getBean("clienteBASClientTarget");
			switch (persistType)
			{
				case INSERT:
					response = client.insertCliente(request);
					break;
				case UPDATE:
					response = client.updateCliente(request);
					break;
				case DELETE:
					response = client.deleteCliente(request);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("persistType missing! Setting Unspecified Error status.");
					}
					response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG);
					break;
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Cliente fetch by request.
	 * 
	 * @param request the request
	 * @return the cliente response
	 */
	protected ClienteResponse clienteFetchByRequest(PagedInquiryRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IClienteBAS client = (IClienteBAS)QATAppContext.getBean("clienteBASClientTarget");
			response = client.fetchClientesByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected ClienteResponse clienteBEFetchAll(boolean useBAI, FetchAllRequest request)
	{
		ClienteResponse response = new ClienteResponse();
		try
		{
			IClienteBAS client = (IClienteBAS)QATAppContext.getBean("clienteBASClientTarget");
			response = client.fetchAllClientes(request);

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

}
