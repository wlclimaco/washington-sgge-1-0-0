package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.produto.bas.IProdutoBAS;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.site.bas.ISiteBAS;

/**
 * The Class CountyBaseController.
 */
public class SiteBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CadastroBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.supermercado.defaultexception";

	private static final String PROCEDURE_RESPONSE = null;

	protected ModelAndView ordemServicoMAV(OrdemServicoInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(PROCEDURE_RESPONSE, mapper.writeValueAsString(ordemServicoFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(PROCEDURE_RESPONSE, null);
		}
		return modelAndView;
	}

	protected ModelAndView contatoMAV(ContatoInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject("contatoList", mapper.writeValueAsString(contatoFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(PROCEDURE_RESPONSE, null);
		}
		return modelAndView;
	}

	protected ContatoResponse contatoFetchByRequest(ContatoInquiryRequest request)
	{
		ContatoResponse response = new ContatoResponse();
		try
		{
			ISiteBAS client = (ISiteBAS)QATAppContext.getBean("siteBASClientTarget");
			response = client.fetchContatosByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected OrdemServicoResponse ordemServicoFetchByRequest(OrdemServicoInquiryRequest request)
	{
		OrdemServicoResponse response = new OrdemServicoResponse();
		try
		{
			ISiteBAS client = (ISiteBAS)QATAppContext.getBean("siteBASClientTarget");
			response = client.fetchOrdemServicosByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	protected PlanoResponse planoFetchByRequest(PlanoInquiryRequest request)
	{
		PlanoResponse response = new PlanoResponse();
		try
		{
			IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
			response = client.fetchPlanosByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

}
