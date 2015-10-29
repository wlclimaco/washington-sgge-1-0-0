package com.qat.samples.sysmgmt.produto.baid;


/**
 * Delegate class for Produto BAI. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class ProdutoBAID
{

	// /** The Constant DEFAULT_BUNDLE_BAID_EXCEPTION_MSG. */
	// private static final String DEFAULT_BUNDLE_BAID_EXCEPTION_MSG = "sysmgmt.base.produtobaidimpl.defaultexception";
	//
	// /** The Constant SYSMGMT_BASE_ID_REQUIRED. */
	// private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";
	// /** The Constant LOG. */
	// private static final Logger LOG = LoggerFactory.getLogger(ProdutoBAID.class);
	//
	// /**
	// * Required for final classes to insure no one tries to instantiate it.
	// */
	// private ProdutoBAID()
	// {
	// }
	//
	// /**
	// * Maintain produto.
	// *
	// * @param produtoBAC the produto bac
	// * @param validationIndicator the validation indicator
	// * @param controller the controller
	// * @param persistType the persist type
	// * @param request the request
	// * @param response the response
	// */
	// public static void maintainProduto(IProdutoBAC produtoBAC, ValidationContextIndicator validationIndicator,
	// ValidationController controller,
	// PersistanceActionEnum persistType, ProdutoMaintenanceRequest request, ProdutoResponse response)
	// {
	//
	// InternalResponse internalResponse = new InternalResponse();
	//
	// // perform persistence
	// switch (persistType)
	// {
	// case INSERT:
	// internalResponse = produtoBAC.insertProduto(request.getProduto());
	// break;
	// case UPDATE:
	// if (!ValidationUtil.isNull(request.getProdutos()))
	// {
	// for (Integer i = 0; i < request.getProdutos().size(); i++)
	// {
	//
	// InternalResultsResponse<Produto> produto = new InternalResultsResponse<Produto>();
	// produto =
	// produtoBAC.fetchProdutoById(new FetchByIdRequest(request.getProdutos().get(i)
	// .getId()));
	// if (produto.getStatus().equals(Status.OperationSuccess))
	// {
	//
	// Produto produtos = new Produto();
	// produtos = produto.getFirstResult();
	// Supermercado supermercado = new Supermercado();
	// supermercado = produtos.getPrecos().get(0).getSupermercadoid();
	// supermercado.setSuperId(request.getProdutos().get(i).getPrecos().get(0)
	// .getSupermercadoid().getSuperId());
	// produtos.getPrecos().get(0).setSupermercadoid(supermercado);
	// if (request.getProdutos().get(i).getTabela().equals(TableTypeEnum.PRODUTO))
	// {
	// produtos.setTabela(TableTypeEnum.PRODUTO);
	// internalResponse = produtoBAC.insertProduto(produtos);
	// }
	//
	// else if (request.getProdutos().get(i).getTabela().equals(TableTypeEnum.MARCA))
	// {
	// produtos.setTabela(TableTypeEnum.PRODUTO);
	// internalResponse = produtoBAC.deletePreco(produtos);
	// }
	// else if (request.getProdutos().get(i).getTabela().equals(TableTypeEnum.MENU))
	// {
	// produtos.setTabela(TableTypeEnum.PRODUTO);
	// produtos.getPrecos().get(0).setPromocao(1);
	// internalResponse = produtoBAC.updateProduto(produtos);
	// }
	// else if (request.getProdutos().get(i).getTabela().equals(TableTypeEnum.SUBMENU))
	// {
	// produtos.setTabela(TableTypeEnum.PRODUTO);
	// produtos.getPrecos().get(0).setPromocao(2);
	// internalResponse = produtoBAC.updateProduto(produtos);
	// }
	// else
	// {
	// internalResponse = produtoBAC.updateProduto(request.getProduto());
	// }
	// }
	// }
	//
	// }
	// else
	// {
	// internalResponse = produtoBAC.updateProduto(request.getProduto());
	// }
	// break;
	// case DELETE:
	// internalResponse = produtoBAC.deleteProduto(request.getProduto());
	// break;
	// default:
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug("persistType missing! Setting Unspecified Error status.");
	// }
	// internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
	// break;
	// }
	//
	// if (internalResponse.getStatus() == Status.OperationSuccess)
	// {
	// // Call maintain to see if we need to return the county list and if so whether it should be paged or not
	// ProdutoInquiryRequest requestCadastro = new ProdutoInquiryRequest();
	// Produto cadastro = new Produto();
	// cadastro.setTabela(TableTypeEnum.PRODUTO);
	// requestCadastro.setProduto(cadastro);
	// maintainReturnListProduto(request.getReturnList(), request.getReturnListPaged(), response, produtoBAC,
	// requestCadastro);
	// }
	//
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, null, false);
	// }
	//
	// public static void maintainCadastro(IProdutoBAC produtoBAC, ValidationContextIndicator validationIndicator,
	// ValidationController controller,
	// PersistanceActionEnum persistType, CadastroMaintenanceRequest request, CadastroResponse response)
	// {
	// ValidationContext context =
	// new ValidationContext(Produto.class.getSimpleName(), request.getCadastro(), validationIndicator);
	//
	// InternalResponse internalResponse = new InternalResponse();
	// if (controller.validate(context))
	// {
	// // perform persistence
	// switch (persistType)
	// {
	// case INSERT:
	// internalResponse = produtoBAC.insertCadastro(request.getCadastro());
	// break;
	// case UPDATE:
	// internalResponse = produtoBAC.updateCadastro(request.getCadastro());
	// break;
	// case DELETE:
	// internalResponse = produtoBAC.deleteCadastro(request.getCadastro());
	// break;
	// default:
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug("persistType missing! Setting Unspecified Error status.");
	// }
	// internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
	// break;
	// }
	// }
	// if (internalResponse.getStatus() == Status.OperationSuccess)
	// {
	// // Call maintain to see if we need to return the county list and if so whether it should be paged or not
	// CadastroInquiryRequest requestCadastro = new CadastroInquiryRequest();
	// Cadastro cadastro = new Cadastro(0, request.getCadastro().getType());
	// requestCadastro.setCadastro(cadastro);
	// maintainReturnListCadastro(request.getReturnList(), request.getReturnListPaged(), response, produtoBAC,
	// requestCadastro);
	// }
	//
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
	// }
	//
	// /**
	// * Refresh produtos.
	// *
	// * @param produtoBAC the produto bac
	// * @param request the request
	// * @param response the response
	// */
	// public static void refreshProdutos(IProdutoBAC produtoBAC, RefreshRequest request, ProdutoResponse response)
	// {
	// // This method is demo code only. Do not view this as a QAT Standard.
	// produtoBAC.refreshProdutos(request.getRefreshInt());
	// // Call maintain to see if we need to return the county list and if so whether it should be paged or not
	//
	// }
	//
	// /**
	// * Fetch all produtos.
	// *
	// * @param produtoBAC the produto bac
	// * @param response the response
	// */
	// public static void fetchAllProdutos(IProdutoBAC produtoBAC, ProdutoResponse response, ProdutoInquiryRequest
	// request)
	// {
	// InternalResultsResponse<Produto> internalResponse = produtoBAC.fetchAllProdutos(request);
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// public static void fetchAllEmbalagens(IProdutoBAC produtoBAC, EmbalagemResponse response,
	// EmbalagemInquiryRequest request)
	// {
	// InternalResultsResponse<Embalagem> internalResponse = produtoBAC.fetchAllEmbalagems(request);
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// public static void fetchAllUniMeds(IProdutoBAC produtoBAC, EmbalagemResponse response,
	// EmbalagemInquiryRequest request)
	// {
	// InternalResultsResponse<UniMed> internalResponse = produtoBAC.fetchAllUniMeds(request);
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// public static void fetchAllCadastros(IProdutoBAC produtoBAC, CadastroResponse response,
	// CadastroInquiryRequest request)
	// {
	// InternalResultsResponse<Cadastro> internalResponse =
	// produtoBAC.fetchAllCadastros(request);
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// /**
	// * Fetch produtos paged.
	// *
	// * @param produtoBAC the produto bac
	// * @param request the request
	// * @param response the response
	// */
	// public static void fetchProdutosPaged(IProdutoBAC produtoBAC, PagedInquiryRequest request, ProdutoResponse
	// response)
	// {
	// InternalResultsResponse<Produto> internalResponse = produtoBAC.fetchProdutosByRequest(request);
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// public static void fetchCadastrosPaged(IProdutoBAC produtoBAC, CadastroInquiryRequest request,
	// CadastroResponse response)
	// {
	// InternalResultsResponse<Cadastro> internalResponse = produtoBAC.fetchCadastrosByRequest(request);
	// if (internalResponse.getStatus() != Status.OperationSuccess)
	// {
	// response.addOperationFailedMessage(DEFAULT_BUNDLE_BAID_EXCEPTION_MSG, new Object[] {internalResponse
	// .getStatus().toString()});
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// /**
	// * Fetch produto by id.
	// *
	// * @param produtoBAC the produto bac
	// * @param request the request
	// * @param response the response
	// */
	// public static void fetchProdutoById(IProdutoBAC produtoBAC, FetchByIdRequest request, ProdutoResponse response)
	// {
	// InternalResultsResponse<Produto> internalResponse = new InternalResultsResponse<Produto>();
	// // validate fetchId field
	// if (ValidationUtil.isNull(request.getFetchId()))
	// {
	// internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
	// }
	// else
	// {
	// internalResponse = produtoBAC.fetchProdutoById(request);
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// public static void fetchCadastroById(IProdutoBAC produtoBAC, FetchByIdRequest request, CadastroResponse response)
	// {
	// InternalResultsResponse<Cadastro> internalResponse = new InternalResultsResponse<Cadastro>();
	// // validate fetchId field
	// if (ValidationUtil.isNull(request.getFetchId()))
	// {
	// internalResponse.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
	// }
	// else
	// {
	// internalResponse = produtoBAC.fetchCadastroById(request);
	// }
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	// }
	//
	// public static void maintainEmbalagem(IProdutoBAC produtoBAC, ValidationContextIndicator validationIndicator,
	// ValidationController controller,
	// PersistanceActionEnum persistType, EmbalagemMaintenanceRequest request, EmbalagemResponse response)
	// {
	// InternalResponse internalResponse = new InternalResponse();
	// switch (persistType)
	// {
	// case INSERT:
	// internalResponse = produtoBAC.insertEmbalagem(request.getEmbalagem());
	// break;
	// case UPDATE:
	// internalResponse = produtoBAC.updateEmbalagem(request.getEmbalagem());
	// break;
	// case DELETE:
	// internalResponse = produtoBAC.deleteEmbalagem(request.getEmbalagem());
	// break;
	// default:
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug("persistType missing! Setting Unspecified Error status.");
	// }
	// internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
	// break;
	// }
	// if (internalResponse.getStatus() == Status.OperationSuccess)
	// {
	// // Call maintain to see if we need to return the county list and if so whether it should be paged or not
	// EmbalagemInquiryRequest requestCadastro = new EmbalagemInquiryRequest();
	// maintainReturnListEmbalagem(request.getReturnList(), request.getReturnListPaged(), response, produtoBAC,
	// requestCadastro);
	// }
	//
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, null, false);
	// }
	//
	// public static void maintainUniMed(IProdutoBAC produtoBAC, ValidationContextIndicator validationIndicator,
	// ValidationController controller,
	// PersistanceActionEnum persistType, EmbalagemMaintenanceRequest request, EmbalagemResponse response)
	// {
	// InternalResponse internalResponse = new InternalResponse();
	// switch (persistType)
	// {
	// case INSERT:
	// internalResponse = produtoBAC.insertUniMed(request.getEmbalagem());
	// break;
	// case UPDATE:
	// internalResponse = produtoBAC.updateUniMed(request.getEmbalagem());
	// break;
	// case DELETE:
	// internalResponse = produtoBAC.deleteUniMed(request.getEmbalagem());
	// break;
	// default:
	// if (LOG.isDebugEnabled())
	// {
	// LOG.debug("persistType missing! Setting Unspecified Error status.");
	// }
	// internalResponse.setStatus(InternalResponse.Status.UnspecifiedError);
	// break;
	// }
	// if (internalResponse.getStatus() == Status.OperationSuccess)
	// {
	// // Call maintain to see if we need to return the county list and if so whether it should be paged or not
	// EmbalagemInquiryRequest requestCadastro = new EmbalagemInquiryRequest();
	// Embalagem cadastro = new Embalagem();
	// requestCadastro.setEmbalagem(cadastro);
	// maintainReturnListUniMed(request.getReturnList(), request.getReturnListPaged(), response, produtoBAC,
	// requestCadastro);
	// }
	//
	// // Handle the processing for all previous methods regardless of them failing or succeeding.
	// QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, null, false);
	// }
	//
	// /**
	// * Maintain return list.
	// *
	// * @param listIndicator the list indicator
	// * @param pageListIndicator the page list indicator
	// * @param response the response
	// * @param produtoBAC the produto bac
	// */
	// private static void maintainReturnListEmbalagem(Boolean listIndicator, Boolean pageListIndicator,
	// EmbalagemResponse response,
	// IProdutoBAC produtoBAC, EmbalagemInquiryRequest request)
	// {
	// // Fetch again if requested.
	// if (listIndicator)
	// {
	// // Fetch Paged is requested.
	// if (pageListIndicator)
	// {
	// request.setPreQueryCount(true);
	// fetchAllEmbalagens(produtoBAC, response, request);
	// }
	// else
	// {
	// // otherwise return all rows not paged
	// fetchAllEmbalagens(produtoBAC, response, request);
	// }
	// }
	// }
	//
	// private static void maintainReturnListUniMed(Boolean listIndicator, Boolean pageListIndicator,
	// EmbalagemResponse response,
	// IProdutoBAC produtoBAC, EmbalagemInquiryRequest request)
	// {
	// // Fetch again if requested.
	// if (listIndicator)
	// {
	// // Fetch Paged is requested.
	// if (pageListIndicator)
	// {
	// request.setPreQueryCount(true);
	// fetchAllUniMeds(produtoBAC, response, request);
	// }
	// else
	// {
	// // otherwise return all rows not paged
	// fetchAllUniMeds(produtoBAC, response, request);
	// }
	// }
	// }
	//
	// private static void maintainReturnListCadastro(Boolean listIndicator, Boolean pageListIndicator,
	// CadastroResponse response,
	// IProdutoBAC produtoBAC, CadastroInquiryRequest request)
	// {
	// // Fetch again if requested.
	// if (listIndicator)
	// {
	// // Fetch Paged is requested.
	// if (pageListIndicator)
	// {
	// request.setPreQueryCount(true);
	// fetchAllCadastros(produtoBAC, response, request);
	// }
	// else
	// {
	// // otherwise return all rows not paged
	// fetchAllCadastros(produtoBAC, response, request);
	// }
	// }
	// }
	//
	// private static void maintainReturnListProduto(Boolean listIndicator, Boolean pageListIndicator,
	// ProdutoResponse response,
	// IProdutoBAC produtoBAC, ProdutoInquiryRequest request)
	// {
	// // Fetch again if requested.
	// if (listIndicator)
	// {
	// // Fetch Paged is requested.
	// if (pageListIndicator)
	// {
	// request.setPreQueryCount(true);
	// fetchAllProdutos(produtoBAC, response, request);
	// }
	// else
	// {
	// // otherwise return all rows not paged
	// fetchAllProdutos(produtoBAC, response, request);
	// }
	// }
	// }
}
