package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.bas.ISupermercadoBAS;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * The Class CountyBaseController.
 */
public class SupermercadoBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SupermercadoBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.supermercado.defaultexception";

	/** The Constant SUPERMERCADO_RESPONSE. */
	private static final String SUPERMERCADO_RESPONSE = "supermercadoResponse";

	/**
	 * Supermercado mav.
	 * 
	 * @param request the request
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView supermercadoMAV(PagedInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(SUPERMERCADO_RESPONSE,
					mapper.writeValueAsString(supermercadoFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(SUPERMERCADO_RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Refresh supermercados.
	 * 
	 * @param request the request
	 * @return the supermercado response
	 */
	protected SupermercadoResponse refreshSupermercado(RefreshRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			ISupermercadoBAS client = (ISupermercadoBAS)QATAppContext.getBean("supermercadoBASClientTarget");
			response = client.refreshSupermercados(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Maintain supermercados.
	 * 
	 * @param request the request
	 * @param persistType the persist type
	 * @return the supermercado response
	 */
	protected SupermercadoResponse maintainSupermercado(SupermercadoMaintenanceRequest request,
			PersistanceActionEnum persistType)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			ISupermercadoBAS client = (ISupermercadoBAS)QATAppContext.getBean("supermercadoBASClientTarget");
			switch (persistType)
			{
				case INSERT:
					response = client.insertSupermercado(request);
					break;
				case UPDATE:
					response = client.updateSupermercado(request);
					break;
				case DELETE:
					response = client.deleteSupermercado(request);
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
	 * Supermercado fetch by request.
	 * 
	 * @param request the request
	 * @return the supermercado response
	 */
	protected SupermercadoResponse supermercadoFetchByRequest(PagedInquiryRequest request)
	{
		SupermercadoResponse response = new SupermercadoResponse();
		try
		{
			ISupermercadoBAS client = (ISupermercadoBAS)QATAppContext.getBean("supermercadoBASClientTarget");
			response = client.fetchSupermercadosByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

}
