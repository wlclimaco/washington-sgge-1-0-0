package com.qat.webdaptive.controller;


/**
 * The Class CountyBaseController.
 */
public class EntidadeBaseController
{

	// /** The Constant LOG. */
	// private static final Logger LOG = LoggerFactory.getLogger(CadastroBaseController.class);
	//
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.supermercado.defaultexception";
	//
	// /** The Constant SUPERMERCADO_RESPONSE. */
	// private static final String SUPERMERCADO_RESPONSE = "cadastroResponse";
	//
	// /** The county bai. */
	// private IProdutoBAI produtoBAI; // injected by @Resource
	//
	// public IProdutoBAI getProdutoBAI()
	// {
	// return produtoBAI;
	// }
	//
	// public void setProdutoBAI(IProdutoBAI produtoBAI)
	// {
	// this.produtoBAI = produtoBAI;
	// }
	//
	// /**
	// * Cadastro mav.
	// *
	// * @param request the request
	// * @param returnViewName the return view name
	// * @return the model and view
	// */
	// protected ModelAndView cadastroMAV(CadastroInquiryRequest request, String returnViewName)
	// {
	// ModelAndView modelAndView = new ModelAndView(returnViewName);
	// ObjectMapper mapper = new ObjectMapper();
	// try
	// {
	// modelAndView.addObject(SUPERMERCADO_RESPONSE,
	// mapper.writeValueAsString(cadastroFetchByRequest(request)));
	// }
	// catch (Exception ex)
	// {
	// LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
	// modelAndView.addObject(SUPERMERCADO_RESPONSE, null);
	// }
	// return modelAndView;
	// }
	//
	// /**
	// * Refresh cadastros.
	// *
	// * @param request the request
	// * @return the cadastro response
	// */
	// protected CadastroResponse refreshCadastro(RefreshRequest request)
	// {
	// CadastroResponse response = new CadastroResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// response = client.refreshCadastros(request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// /**
	// * Maintain supermercados.
	// *
	// * @param request the request
	// * @param persistType the persist type
	// * @return the supermercado response
	// */
	// protected CadastroResponse maintainCadastro(CadastroMaintenanceRequest request,
	// PersistanceActionEnum persistType)
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
	// * Cadastro fetch by request.
	// *
	// * @param request the request
	// * @return the supermercado response
	// */
	// protected CadastroResponse cadastroFetchByRequest(CadastroInquiryRequest request)
	// {
	// CadastroResponse response = new CadastroResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// response = client.fetchCadastrosByRequest(request);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// protected CadastroResponse cadastroBEFetchAll(boolean useBAI, CadastroInquiryRequest request)
	// {
	// CadastroResponse response = new CadastroResponse();
	// try
	// {
	// // IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// // response = client.fetchAllCadastros(request);
	//
	// response = getProdutoBAI().fetchAllCadastros(request);
	//
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// protected EmbalagemResponse uniMedBEFetchAll(boolean useBAI, EmbalagemInquiryRequest request)
	// {
	// EmbalagemResponse response = new EmbalagemResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// response = client.fetchAllUniMeds(request);
	//
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// protected EmbalagemResponse embalagemBEFetchAll(boolean useBAI, EmbalagemInquiryRequest request)
	// {
	// EmbalagemResponse response = new EmbalagemResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// response = client.fetchAllEmbalagems(request);
	//
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
	//
	// protected ProdutoResponse produtoBEFetchAll(boolean useBAI, ProdutoInquiryRequest request)
	// {
	// ProdutoResponse response = new ProdutoResponse();
	// try
	// {
	// IProdutoBAS client = (IProdutoBAS)QATAppContext.getBean("produtoBASClientTarget");
	// response = client.fetchAllProdutos(request);
	//
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
	// }
	// return response;
	// }
}
