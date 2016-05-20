/** create by system gera-java version 1.0.0 09/05/2016 16:51 : 47*/

package com.qat.samples.sysmgmt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Produto.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.GrupoResponse;
import com.qat.samples.sysmgmt.produto.model.response.MarcaResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.produto.model.response.SubGrupoResponse;
import com.qat.samples.sysmgmt.produto.model.response.UniMedResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class ProdutoAPIController.
 */
@Controller
@RequestMapping("/produto/api")
public class ProdutoAPIController extends BaseController {
	// /** The Constant DEFAULT_EXCEPTION_MSG. */
	// private static final String DEFAULT_EXCEPTION_MSG =
	// "sysmgmt.base.produtocontrollerrest.defaultexception";
	//
	// /** The Constant LOG. */
	// private static final Logger LOG =
	// LoggerFactory.getLogger(ProdutoAPIController.class);
	//
	// /** The produto bac. */
	// private IProdutoBAC produtoBAC; // injected by @Resource
	//
	// /**
	// * Gets the produto bac.
	// *
	// * @return the produto bac
	// */
	// public IProdutoBAC getProdutoBAC() {
	// return produtoBAC;
	// }
	//
	// /**
	// * Sets the produto bac.
	// *
	// * @param produtoBAC
	// * the new produto bac
	// */
	// @Resource
	// public void setProdutoBAC(IProdutoBAC produtoBAC) {
	// this.produtoBAC = produtoBAC;
	// }
	//
	// // ===================================### PRODUTO
	// // ####======================================
	// /**
	// * Refresh produtos.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the produto response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public ProdutoResponse refreshProdutos(@RequestParam("refreshInt")
	// Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// ProdutoResponse produtoResponse = new ProdutoResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Produto> internalResponse =
	// getProdutoBAC().refreshProdutos(request);
	// ResponseHandler.handleOperationStatusAndMessages(produtoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, produtoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return produtoResponse;
	//
	// }
	//
	// /**
	// * Fetch produto paged.
	// *
	// * @param request
	// * the request
	// * @return the produto response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public ProdutoResponse fetchProdutoPaged(@RequestBody
	// ProdutoInquiryRequest request) {
	// ProdutoResponse produtoResponse = new ProdutoResponse();
	// try {
	// InternalResultsResponse<Produto> internalResponse =
	// getProdutoBAC().fetchProdutosByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(produtoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, produtoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return produtoResponse;
	// }
	//
	// /**
	// * Insert produto.
	// *
	// * @param request
	// * the request
	// * @return the produto response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public ProdutoResponse insertProduto(@RequestBody
	// ProdutoMaintenanceRequest request) {
	// ProdutoResponse produtoResponse = new ProdutoResponse();
	// try {
	// InternalResultsResponse<Produto> internalResponse =
	// getProdutoBAC().insertProduto(request);
	// ResponseHandler.handleOperationStatusAndMessages(produtoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, produtoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return produtoResponse;
	// }
	//
	// /**
	// * Update produto.
	// *
	// * @param request
	// * the request
	// * @return the produto response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public ProdutoResponse updateProduto(@RequestBody
	// ProdutoMaintenanceRequest request) {
	// ProdutoResponse produtoResponse = new ProdutoResponse();
	// try {
	// InternalResultsResponse<Produto> internalResponse =
	// getProdutoBAC().updateProduto(request);
	// ResponseHandler.handleOperationStatusAndMessages(produtoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, produtoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return produtoResponse;
	// }
	//
	// /**
	// * Delete produto.
	// *
	// * @param request
	// * the request
	// * @return the produto response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public ProdutoResponse deleteProduto(@RequestBody
	// ProdutoMaintenanceRequest request) {
	// ProdutoResponse produtoResponse = new ProdutoResponse();
	//
	// try {
	// InternalResultsResponse<Produto> internalResponse =
	// getProdutoBAC().deleteProduto(request);
	// ResponseHandler.handleOperationStatusAndMessages(produtoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, produtoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return produtoResponse;
	//
	// }
	//
	// // ===================================### MARCA
	// // ####======================================
	// /**
	// * Refresh marcas.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the marca response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public MarcaResponse refreshMarcas(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// MarcaResponse marcaResponse = new MarcaResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Marca> internalResponse =
	// getProdutoBAC().refreshMarcas(request);
	// ResponseHandler.handleOperationStatusAndMessages(marcaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, marcaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return marcaResponse;
	//
	// }
	//
	// /**
	// * Fetch marca paged.
	// *
	// * @param request
	// * the request
	// * @return the marca response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public MarcaResponse fetchMarcaPaged(@RequestBody MarcaInquiryRequest
	// request) {
	// MarcaResponse marcaResponse = new MarcaResponse();
	// try {
	// InternalResultsResponse<Marca> internalResponse =
	// getProdutoBAC().fetchMarcasByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(marcaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, marcaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return marcaResponse;
	// }
	//
	// /**
	// * Insert marca.
	// *
	// * @param request
	// * the request
	// * @return the marca response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public MarcaResponse insertMarca(@RequestBody MarcaMaintenanceRequest
	// request) {
	// MarcaResponse marcaResponse = new MarcaResponse();
	// try {
	// InternalResultsResponse<Marca> internalResponse =
	// getProdutoBAC().insertMarca(request);
	// ResponseHandler.handleOperationStatusAndMessages(marcaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, marcaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return marcaResponse;
	// }
	//
	// /**
	// * Update marca.
	// *
	// * @param request
	// * the request
	// * @return the marca response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public MarcaResponse updateMarca(@RequestBody MarcaMaintenanceRequest
	// request) {
	// MarcaResponse marcaResponse = new MarcaResponse();
	// try {
	// InternalResultsResponse<Marca> internalResponse =
	// getProdutoBAC().updateMarca(request);
	// ResponseHandler.handleOperationStatusAndMessages(marcaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, marcaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return marcaResponse;
	// }
	//
	// /**
	// * Delete marca.
	// *
	// * @param request
	// * the request
	// * @return the marca response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public MarcaResponse deleteMarca(@RequestBody MarcaMaintenanceRequest
	// request) {
	// MarcaResponse marcaResponse = new MarcaResponse();
	//
	// try {
	// InternalResultsResponse<Marca> internalResponse =
	// getProdutoBAC().deleteMarca(request);
	// ResponseHandler.handleOperationStatusAndMessages(marcaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, marcaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return marcaResponse;
	//
	// }
	//
	// // ===================================### GRUPO
	// // ####======================================
	// /**
	// * Refresh grupos.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the grupo response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public GrupoResponse refreshGrupos(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// GrupoResponse grupoResponse = new GrupoResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<Grupo> internalResponse =
	// getProdutoBAC().refreshGrupos(request);
	// ResponseHandler.handleOperationStatusAndMessages(grupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, grupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return grupoResponse;
	//
	// }
	//
	// /**
	// * Fetch grupo paged.
	// *
	// * @param request
	// * the request
	// * @return the grupo response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public GrupoResponse fetchGrupoPaged(@RequestBody GrupoInquiryRequest
	// request) {
	// GrupoResponse grupoResponse = new GrupoResponse();
	// try {
	// InternalResultsResponse<Grupo> internalResponse =
	// getProdutoBAC().fetchGruposByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(grupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, grupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return grupoResponse;
	// }
	//
	// /**
	// * Insert grupo.
	// *
	// * @param request
	// * the request
	// * @return the grupo response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public GrupoResponse insertGrupo(@RequestBody GrupoMaintenanceRequest
	// request) {
	// GrupoResponse grupoResponse = new GrupoResponse();
	// try {
	// InternalResultsResponse<Grupo> internalResponse =
	// getProdutoBAC().insertGrupo(request);
	// ResponseHandler.handleOperationStatusAndMessages(grupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, grupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return grupoResponse;
	// }
	//
	// /**
	// * Update grupo.
	// *
	// * @param request
	// * the request
	// * @return the grupo response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public GrupoResponse updateGrupo(@RequestBody GrupoMaintenanceRequest
	// request) {
	// GrupoResponse grupoResponse = new GrupoResponse();
	// try {
	// InternalResultsResponse<Grupo> internalResponse =
	// getProdutoBAC().updateGrupo(request);
	// ResponseHandler.handleOperationStatusAndMessages(grupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, grupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return grupoResponse;
	// }
	//
	// /**
	// * Delete grupo.
	// *
	// * @param request
	// * the request
	// * @return the grupo response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public GrupoResponse deleteGrupo(@RequestBody GrupoMaintenanceRequest
	// request) {
	// GrupoResponse grupoResponse = new GrupoResponse();
	//
	// try {
	// InternalResultsResponse<Grupo> internalResponse =
	// getProdutoBAC().deleteGrupo(request);
	// ResponseHandler.handleOperationStatusAndMessages(grupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, grupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return grupoResponse;
	//
	// }
	//
	// // ===================================### SUBGRUPO
	// // ####======================================
	// /**
	// * Refresh subgrupos.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the subgrupo response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public SubGrupoResponse refreshSubGrupos(@RequestParam("refreshInt")
	// Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// SubGrupoResponse subgrupoResponse = new SubGrupoResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<SubGrupo> internalResponse =
	// getProdutoBAC().refreshSubGrupos(request);
	// ResponseHandler.handleOperationStatusAndMessages(subgrupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, subgrupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return subgrupoResponse;
	//
	// }
	//
	// /**
	// * Fetch subgrupo paged.
	// *
	// * @param request
	// * the request
	// * @return the subgrupo response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public SubGrupoResponse fetchSubGrupoPaged(@RequestBody
	// SubGrupoInquiryRequest request) {
	// SubGrupoResponse subgrupoResponse = new SubGrupoResponse();
	// try {
	// InternalResultsResponse<SubGrupo> internalResponse =
	// getProdutoBAC().fetchSubGruposByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(subgrupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, subgrupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return subgrupoResponse;
	// }
	//
	// /**
	// * Insert subgrupo.
	// *
	// * @param request
	// * the request
	// * @return the subgrupo response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public SubGrupoResponse insertSubGrupo(@RequestBody
	// SubGrupoMaintenanceRequest request) {
	// SubGrupoResponse subgrupoResponse = new SubGrupoResponse();
	// try {
	// InternalResultsResponse<SubGrupo> internalResponse =
	// getProdutoBAC().insertSubGrupo(request);
	// ResponseHandler.handleOperationStatusAndMessages(subgrupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, subgrupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return subgrupoResponse;
	// }
	//
	// /**
	// * Update subgrupo.
	// *
	// * @param request
	// * the request
	// * @return the subgrupo response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public SubGrupoResponse updateSubGrupo(@RequestBody
	// SubGrupoMaintenanceRequest request) {
	// SubGrupoResponse subgrupoResponse = new SubGrupoResponse();
	// try {
	// InternalResultsResponse<SubGrupo> internalResponse =
	// getProdutoBAC().updateSubGrupo(request);
	// ResponseHandler.handleOperationStatusAndMessages(subgrupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, subgrupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return subgrupoResponse;
	// }
	//
	// /**
	// * Delete subgrupo.
	// *
	// * @param request
	// * the request
	// * @return the subgrupo response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public SubGrupoResponse deleteSubGrupo(@RequestBody
	// SubGrupoMaintenanceRequest request) {
	// SubGrupoResponse subgrupoResponse = new SubGrupoResponse();
	//
	// try {
	// InternalResultsResponse<SubGrupo> internalResponse =
	// getProdutoBAC().deleteSubGrupo(request);
	// ResponseHandler.handleOperationStatusAndMessages(subgrupoResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, subgrupoResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return subgrupoResponse;
	//
	// }
	//
	// // ===================================### UNIMED
	// // ####======================================
	// /**
	// * Refresh unimeds.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the unimed response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public UniMedResponse refreshUniMeds(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// UniMedResponse unimedResponse = new UniMedResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<UniMed> internalResponse =
	// getProdutoBAC().refreshUniMeds(request);
	// ResponseHandler.handleOperationStatusAndMessages(unimedResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, unimedResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return unimedResponse;
	//
	// }
	//
	// /**
	// * Fetch unimed paged.
	// *
	// * @param request
	// * the request
	// * @return the unimed response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public UniMedResponse fetchUniMedPaged(@RequestBody UniMedInquiryRequest
	// request) {
	// UniMedResponse unimedResponse = new UniMedResponse();
	// try {
	// InternalResultsResponse<UniMed> internalResponse =
	// getProdutoBAC().fetchUniMedsByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(unimedResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, unimedResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return unimedResponse;
	// }
	//
	// /**
	// * Insert unimed.
	// *
	// * @param request
	// * the request
	// * @return the unimed response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public UniMedResponse insertUniMed(@RequestBody UniMedMaintenanceRequest
	// request) {
	// UniMedResponse unimedResponse = new UniMedResponse();
	// try {
	// InternalResultsResponse<UniMed> internalResponse =
	// getProdutoBAC().insertUniMed(request);
	// ResponseHandler.handleOperationStatusAndMessages(unimedResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, unimedResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return unimedResponse;
	// }
	//
	// /**
	// * Update unimed.
	// *
	// * @param request
	// * the request
	// * @return the unimed response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public UniMedResponse updateUniMed(@RequestBody UniMedMaintenanceRequest
	// request) {
	// UniMedResponse unimedResponse = new UniMedResponse();
	// try {
	// InternalResultsResponse<UniMed> internalResponse =
	// getProdutoBAC().updateUniMed(request);
	// ResponseHandler.handleOperationStatusAndMessages(unimedResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, unimedResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return unimedResponse;
	// }
	//
	// /**
	// * Delete unimed.
	// *
	// * @param request
	// * the request
	// * @return the unimed response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public UniMedResponse deleteUniMed(@RequestBody UniMedMaintenanceRequest
	// request) {
	// UniMedResponse unimedResponse = new UniMedResponse();
	//
	// try {
	// InternalResultsResponse<UniMed> internalResponse =
	// getProdutoBAC().deleteUniMed(request);
	// ResponseHandler.handleOperationStatusAndMessages(unimedResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, unimedResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return unimedResponse;
	//
	// }

}
