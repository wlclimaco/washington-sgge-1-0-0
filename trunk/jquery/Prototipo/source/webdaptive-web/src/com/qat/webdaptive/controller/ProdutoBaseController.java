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
import com.qat.samples.sysmgmt.produto.bas.IProdutoBAS;
import com.qat.samples.sysmgmt.produto.model.request.CadastroMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.CadastroResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

/**
 * The Class CountyBaseController.
 */
public class ProdutoBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.produto.defaultexception";

	/** The Constant PROCEDURE_RESPONSE. */
	private static final String PROCEDURE_RESPONSE = "produtoResponse";

	/**
	 * Produto mav.
	 * 
	 * @param request the request
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView produtoMAV(PagedInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(PROCEDURE_RESPONSE, mapper.writeValueAsString(produtoFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(PROCEDURE_RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Refresh produtos.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	protected ProdutoResponse refreshProdutos(RefreshRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
			response = client.refreshProdutos(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Maintain produtos.
	 * 
	 * @param request the request
	 * @param persistType the persist type
	 * @return the produto response
	 */
	protected ProdutoResponse maintainProdutos(ProdutoMaintenanceRequest request, PersistanceActionEnum persistType)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
			switch (persistType)
			{
				case INSERT:
					response = client.insertProduto(request);
					break;
				case UPDATE:
					response = client.updateProduto(request);
					break;
				case DELETE:
					response = client.deleteProduto(request);
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

	protected CadastroResponse maintainCadastros(CadastroMaintenanceRequest request, PersistanceActionEnum persistType)
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
	 * Produto fetch by request.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	protected ProdutoResponse produtoFetchByRequest(PagedInquiryRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
			response = client.fetchProdutosByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

}
