package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.bas.IProdutoBAS;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.CadastroResponse;

/**
 * The Class CountyBaseController.
 */
public class CadastroBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CadastroBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.supermercado.defaultexception";

	/** The Constant SUPERMERCADO_RESPONSE. */
	private static final String SUPERMERCADO_RESPONSE = "cadastroResponse";

	/**
	 * Cadastro mav.
	 * 
	 * @param request the request
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView cadastroMAV(CadastroInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(SUPERMERCADO_RESPONSE,
					mapper.writeValueAsString(cadastroFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(SUPERMERCADO_RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Refresh cadastros.
	 * 
	 * @param request the request
	 * @return the cadastro response
	 */
	protected CadastroResponse refreshCadastro(RefreshRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
			response = client.refreshCadastros(request);
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
	protected CadastroResponse maintainCadastro(CadastroMaintenanceRequest request,
			PersistanceActionEnum persistType)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
			switch (persistType)
			{
				case INSERT:
					response = client.insertCadastro(request);
					break;
				case UPDATE:
					response = client.updateCadastro(request);
					break;
				case DELETE:
					response = client.deleteCadastro(request);
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
	 * Cadastro fetch by request.
	 * 
	 * @param request the request
	 * @return the supermercado response
	 */
	protected CadastroResponse cadastroFetchByRequest(CadastroInquiryRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
			response = client.fetchCadastrosByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

}
