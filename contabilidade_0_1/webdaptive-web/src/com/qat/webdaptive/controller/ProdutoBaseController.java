package com.qat.webdaptive.controller;


/**
 * The Class CountyBaseController.
 */
public class ProdutoBaseController
{

	// /** The Constant LOG. */
	// private static final Logger LOG = LoggerFactory.getLogger(ProdutoBaseController.class);
	//
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.produto.defaultexception";
	//
	// /** The Constant PROCEDURE_RESPONSE. */
	// private static final String PROCEDURE_RESPONSE = "produtoResponse";
	//
	// /**
	// * Produto mav.
	// *
	// * @param request the request
	// * @param returnViewName the return view name
	// * @return the model and view
	// */
	// protected ModelAndView produtoMAV(PagedInquiryRequest request, String returnViewName)
	// {
	// ModelAndView modelAndView = new ModelAndView(returnViewName);
	// ObjectMapper mapper = new ObjectMapper();
	// try
	// {
	// modelAndView.addObject(PROCEDURE_RESPONSE, mapper.writeValueAsString(produtoFetchByRequest(request)));
	// }
	// catch (Exception ex)
	// {
	// LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
	// modelAndView.addObject(PROCEDURE_RESPONSE, null);
	// }
	// return modelAndView;
	// }
	//
	// /**
	// * Refresh produtos.
	// *
	// * @param request the request
	// * @return the produto response
	// */
	// protected ProdutoResponse refreshProdutos(RefreshRequest request)
	// {
	// ProdutoResponse response = new ProdutoResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// response = client.refreshProdutos(request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// /**
	// * Maintain produtos.
	// *
	// * @param request the request
	// * @param persistType the persist type
	// * @return the produto response
	// */
	// protected ProdutoResponse maintainProdutos(ProdutoMaintenanceRequest request, PersistanceActionEnum persistType)
	// {
	// ProdutoResponse response = new ProdutoResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// switch (persistType)
	// {
	// case INSERT:
	// response = client.insertProduto(request);
	// break;
	// case UPDATE:
	// response = client.updateProduto(request);
	// break;
	// case DELETE:
	// response = client.deleteProduto(request);
	// break;
	// default:
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug("persistType missing! Setting Unspecified Error status.");
	// }
	// response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG);
	// break;
	// }
	//
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// protected CadastroResponse maintainCadastros(CadastroMaintenanceRequest request, PersistanceActionEnum
	// persistType)
	// {
	// CadastroResponse response = new CadastroResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// switch (persistType)
	// {
	// case INSERT:
	// response = client.insertCadastro(request);
	// break;
	// case UPDATE:
	// response = client.updateCadastro(request);
	// break;
	// case DELETE:
	// response = client.deleteCadastro(request);
	// break;
	// default:
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug("persistType missing! Setting Unspecified Error status.");
	// }
	// response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG);
	// break;
	// }
	//
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// /**
	// * Produto fetch by request.
	// *
	// * @param request the request
	// * @return the produto response
	// */
	// protected ProdutoResponse produtoFetchByRequest(PagedInquiryRequest request)
	// {
	// ProdutoResponse response = new ProdutoResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// response = client.fetchProdutosByRequest(request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }

}
