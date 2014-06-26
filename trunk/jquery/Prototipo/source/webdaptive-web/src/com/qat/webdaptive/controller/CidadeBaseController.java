package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.cidade.bas.ICidadeBAS;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Class CountyBaseController.
 */
public class CidadeBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CidadeBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.cidade.defaultexception";

	/** The Constant PROCEDURE_RESPONSE. */
	private static final String PROCEDURE_RESPONSE = "cidadeResponse";

	/**
	 * Cidade mav.
	 * 
	 * @param request the request
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView cidadeMAV(CidadeInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(PROCEDURE_RESPONSE, mapper.writeValueAsString(cidadeFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(PROCEDURE_RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Refresh cidades.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	protected CidadeResponse refreshCidades(RefreshRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			ICidadeBAS client = (ICidadeBAS)QATAppContext.getBean("cidadeBASClientTarget");
			response = client.refreshCidades(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Maintain cidades.
	 * 
	 * @param request the request
	 * @param persistType the persist type
	 * @return the cidade response
	 */
	protected CidadeResponse maintainCidades(CidadeMaintenanceRequest request, PersistanceActionEnum persistType)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			ICidadeBAS client = (ICidadeBAS)QATAppContext.getBean("cidadeBASClientTarget");
			switch (persistType)
			{
				case INSERT:
					response = client.insertCidade(request);
					break;
				case UPDATE:
					response = client.updateCidade(request);
					break;
				case DELETE:
					response = client.deleteCidade(request);
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
	 * Cidade fetch by request.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	protected CidadeResponse cidadeFetchByRequest(CidadeInquiryRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			ICidadeBAS client = (ICidadeBAS)QATAppContext.getBean("cidadeBASClientTarget");
			response = client.fetchCidadesByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

}
