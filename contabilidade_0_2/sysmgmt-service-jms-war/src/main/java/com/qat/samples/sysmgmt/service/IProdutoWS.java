/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

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
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IProdutoBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "ProdutoService", targetNamespace = "http://qat.com/jms", portName = "ProdutoServicePort")
public interface IProdutoWS
{

//===================================### PRODUTO ####======================================

/**
	 * Rebuild a list of Produtos.
	 *
	 * @param request ProdutoRequest object containing parameter for building the list of Produto objects.
	 *
	 * @return the ProdutoResponse containing the list of Produtos built
	 */
	@WebMethod(action = "insertProdutos")
	@WebResult(name = "insertProdutosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProdutoResponse insertProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Rebuild a list of Produtos.
	 *
	 * @param request ProdutoRequest object containing parameter for building the list of Produto objects.
	 *
	 * @return the ProdutoResponse containing the list of Produtos built
	 */
	@WebMethod(action = "updateProdutos")
	@WebResult(name = "updateProdutosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProdutoResponse updateProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Rebuild a list of Produtos.
	 *
	 * @param request ProdutoRequest object containing parameter for building the list of Produto objects.
	 *
	 * @return the ProdutoResponse containing the list of Produtos built
	 */
	@WebMethod(action = "deleteProdutos")
	@WebResult(name = "deleteProdutosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProdutoResponse deleteProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Rebuild a list of Produtos.
	 *
	 * @param request ProdutoRequest object containing parameter for building the list of Produto objects.
	 *
	 * @return the ProdutoResponse containing the list of Produtos built
	 */
	@WebMethod(action = "fetchProdutoById")
	@WebResult(name = "fetchProdutoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProdutoResponse fetchProdutoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Produtos.
	 *
	 * @param request ProdutoRequest object containing parameter for building the list of Produto objects.
	 *
	 * @return the ProdutoResponse containing the list of Produtos built
	 */
	@WebMethod(action = "fetchProdutosByRequest")
	@WebResult(name = "fetchProdutosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProdutoResponse fetchProdutosByRequest(@WebParam(name = "request") ProdutoInquiryRequest request);


	/**
	 * Rebuild a list of Produtos.
	 *
	 * @param request ProdutoRequest object containing parameter for building the list of Produto objects.
	 *
	 * @return the ProdutoResponse containing the list of Produtos built
	 */
	@WebMethod(action = "refreshProdutos")
	@WebResult(name = "refreshProdutosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProdutoResponse refreshProdutos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Produtos.
	 *
	 * @param request the request
	 *
	 * @return the ProdutoResponse containing all Produto objects
	 */
	@WebMethod(action = "fetchAllProdutos")
	@WebResult(name = "fetchAllProdutosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ProdutoResponse fetchAllProdutos(@WebParam(name = "request") FetchAllRequest request);



//===================================### MARCA ####======================================

/**
	 * Rebuild a list of Marcas.
	 *
	 * @param request MarcaRequest object containing parameter for building the list of Marca objects.
	 *
	 * @return the MarcaResponse containing the list of Marcas built
	 */
	@WebMethod(action = "insertMarcas")
	@WebResult(name = "insertMarcasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MarcaResponse insertMarca(@WebParam(name = "request") MarcaMaintenanceRequest request);

	/**
	 * Rebuild a list of Marcas.
	 *
	 * @param request MarcaRequest object containing parameter for building the list of Marca objects.
	 *
	 * @return the MarcaResponse containing the list of Marcas built
	 */
	@WebMethod(action = "updateMarcas")
	@WebResult(name = "updateMarcasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MarcaResponse updateMarca(@WebParam(name = "request") MarcaMaintenanceRequest request);

	/**
	 * Rebuild a list of Marcas.
	 *
	 * @param request MarcaRequest object containing parameter for building the list of Marca objects.
	 *
	 * @return the MarcaResponse containing the list of Marcas built
	 */
	@WebMethod(action = "deleteMarcas")
	@WebResult(name = "deleteMarcasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MarcaResponse deleteMarca(@WebParam(name = "request") MarcaMaintenanceRequest request);

	/**
	 * Rebuild a list of Marcas.
	 *
	 * @param request MarcaRequest object containing parameter for building the list of Marca objects.
	 *
	 * @return the MarcaResponse containing the list of Marcas built
	 */
	@WebMethod(action = "fetchMarcaById")
	@WebResult(name = "fetchMarcaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MarcaResponse fetchMarcaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Marcas.
	 *
	 * @param request MarcaRequest object containing parameter for building the list of Marca objects.
	 *
	 * @return the MarcaResponse containing the list of Marcas built
	 */
	@WebMethod(action = "fetchMarcasByRequest")
	@WebResult(name = "fetchMarcasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MarcaResponse fetchMarcasByRequest(@WebParam(name = "request") MarcaInquiryRequest request);


	/**
	 * Rebuild a list of Marcas.
	 *
	 * @param request MarcaRequest object containing parameter for building the list of Marca objects.
	 *
	 * @return the MarcaResponse containing the list of Marcas built
	 */
	@WebMethod(action = "refreshMarcas")
	@WebResult(name = "refreshMarcasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	MarcaResponse refreshMarcas(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Marcas.
	 *
	 * @param request the request
	 *
	 * @return the MarcaResponse containing all Marca objects
	 */
	@WebMethod(action = "fetchAllMarcas")
	@WebResult(name = "fetchAllMarcasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	MarcaResponse fetchAllMarcas(@WebParam(name = "request") FetchAllRequest request);



//===================================### GRUPO ####======================================

/**
	 * Rebuild a list of Grupos.
	 *
	 * @param request GrupoRequest object containing parameter for building the list of Grupo objects.
	 *
	 * @return the GrupoResponse containing the list of Grupos built
	 */
	@WebMethod(action = "insertGrupos")
	@WebResult(name = "insertGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	GrupoResponse insertGrupo(@WebParam(name = "request") GrupoMaintenanceRequest request);

	/**
	 * Rebuild a list of Grupos.
	 *
	 * @param request GrupoRequest object containing parameter for building the list of Grupo objects.
	 *
	 * @return the GrupoResponse containing the list of Grupos built
	 */
	@WebMethod(action = "updateGrupos")
	@WebResult(name = "updateGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	GrupoResponse updateGrupo(@WebParam(name = "request") GrupoMaintenanceRequest request);

	/**
	 * Rebuild a list of Grupos.
	 *
	 * @param request GrupoRequest object containing parameter for building the list of Grupo objects.
	 *
	 * @return the GrupoResponse containing the list of Grupos built
	 */
	@WebMethod(action = "deleteGrupos")
	@WebResult(name = "deleteGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	GrupoResponse deleteGrupo(@WebParam(name = "request") GrupoMaintenanceRequest request);

	/**
	 * Rebuild a list of Grupos.
	 *
	 * @param request GrupoRequest object containing parameter for building the list of Grupo objects.
	 *
	 * @return the GrupoResponse containing the list of Grupos built
	 */
	@WebMethod(action = "fetchGrupoById")
	@WebResult(name = "fetchGrupoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	GrupoResponse fetchGrupoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Grupos.
	 *
	 * @param request GrupoRequest object containing parameter for building the list of Grupo objects.
	 *
	 * @return the GrupoResponse containing the list of Grupos built
	 */
	@WebMethod(action = "fetchGruposByRequest")
	@WebResult(name = "fetchGruposByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	GrupoResponse fetchGruposByRequest(@WebParam(name = "request") GrupoInquiryRequest request);


	/**
	 * Rebuild a list of Grupos.
	 *
	 * @param request GrupoRequest object containing parameter for building the list of Grupo objects.
	 *
	 * @return the GrupoResponse containing the list of Grupos built
	 */
	@WebMethod(action = "refreshGrupos")
	@WebResult(name = "refreshGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	GrupoResponse refreshGrupos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Grupos.
	 *
	 * @param request the request
	 *
	 * @return the GrupoResponse containing all Grupo objects
	 */
	@WebMethod(action = "fetchAllGrupos")
	@WebResult(name = "fetchAllGruposReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	GrupoResponse fetchAllGrupos(@WebParam(name = "request") FetchAllRequest request);



//===================================### SUBGRUPO ####======================================

/**
	 * Rebuild a list of SubGrupos.
	 *
	 * @param request SubGrupoRequest object containing parameter for building the list of SubGrupo objects.
	 *
	 * @return the SubGrupoResponse containing the list of SubGrupos built
	 */
	@WebMethod(action = "insertSubGrupos")
	@WebResult(name = "insertSubGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SubGrupoResponse insertSubGrupo(@WebParam(name = "request") SubGrupoMaintenanceRequest request);

	/**
	 * Rebuild a list of SubGrupos.
	 *
	 * @param request SubGrupoRequest object containing parameter for building the list of SubGrupo objects.
	 *
	 * @return the SubGrupoResponse containing the list of SubGrupos built
	 */
	@WebMethod(action = "updateSubGrupos")
	@WebResult(name = "updateSubGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SubGrupoResponse updateSubGrupo(@WebParam(name = "request") SubGrupoMaintenanceRequest request);

	/**
	 * Rebuild a list of SubGrupos.
	 *
	 * @param request SubGrupoRequest object containing parameter for building the list of SubGrupo objects.
	 *
	 * @return the SubGrupoResponse containing the list of SubGrupos built
	 */
	@WebMethod(action = "deleteSubGrupos")
	@WebResult(name = "deleteSubGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SubGrupoResponse deleteSubGrupo(@WebParam(name = "request") SubGrupoMaintenanceRequest request);

	/**
	 * Rebuild a list of SubGrupos.
	 *
	 * @param request SubGrupoRequest object containing parameter for building the list of SubGrupo objects.
	 *
	 * @return the SubGrupoResponse containing the list of SubGrupos built
	 */
	@WebMethod(action = "fetchSubGrupoById")
	@WebResult(name = "fetchSubGrupoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SubGrupoResponse fetchSubGrupoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of SubGrupos.
	 *
	 * @param request SubGrupoRequest object containing parameter for building the list of SubGrupo objects.
	 *
	 * @return the SubGrupoResponse containing the list of SubGrupos built
	 */
	@WebMethod(action = "fetchSubGruposByRequest")
	@WebResult(name = "fetchSubGruposByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SubGrupoResponse fetchSubGruposByRequest(@WebParam(name = "request") SubGrupoInquiryRequest request);


	/**
	 * Rebuild a list of SubGrupos.
	 *
	 * @param request SubGrupoRequest object containing parameter for building the list of SubGrupo objects.
	 *
	 * @return the SubGrupoResponse containing the list of SubGrupos built
	 */
	@WebMethod(action = "refreshSubGrupos")
	@WebResult(name = "refreshSubGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SubGrupoResponse refreshSubGrupos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all SubGrupos.
	 *
	 * @param request the request
	 *
	 * @return the SubGrupoResponse containing all SubGrupo objects
	 */
	@WebMethod(action = "fetchAllSubGrupos")
	@WebResult(name = "fetchAllSubGruposReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	SubGrupoResponse fetchAllSubGrupos(@WebParam(name = "request") FetchAllRequest request);



//===================================### UNIMED ####======================================

/**
	 * Rebuild a list of UniMeds.
	 *
	 * @param request UniMedRequest object containing parameter for building the list of UniMed objects.
	 *
	 * @return the UniMedResponse containing the list of UniMeds built
	 */
	@WebMethod(action = "insertUniMeds")
	@WebResult(name = "insertUniMedsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UniMedResponse insertUniMed(@WebParam(name = "request") UniMedMaintenanceRequest request);

	/**
	 * Rebuild a list of UniMeds.
	 *
	 * @param request UniMedRequest object containing parameter for building the list of UniMed objects.
	 *
	 * @return the UniMedResponse containing the list of UniMeds built
	 */
	@WebMethod(action = "updateUniMeds")
	@WebResult(name = "updateUniMedsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UniMedResponse updateUniMed(@WebParam(name = "request") UniMedMaintenanceRequest request);

	/**
	 * Rebuild a list of UniMeds.
	 *
	 * @param request UniMedRequest object containing parameter for building the list of UniMed objects.
	 *
	 * @return the UniMedResponse containing the list of UniMeds built
	 */
	@WebMethod(action = "deleteUniMeds")
	@WebResult(name = "deleteUniMedsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UniMedResponse deleteUniMed(@WebParam(name = "request") UniMedMaintenanceRequest request);

	/**
	 * Rebuild a list of UniMeds.
	 *
	 * @param request UniMedRequest object containing parameter for building the list of UniMed objects.
	 *
	 * @return the UniMedResponse containing the list of UniMeds built
	 */
	@WebMethod(action = "fetchUniMedById")
	@WebResult(name = "fetchUniMedByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UniMedResponse fetchUniMedById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of UniMeds.
	 *
	 * @param request UniMedRequest object containing parameter for building the list of UniMed objects.
	 *
	 * @return the UniMedResponse containing the list of UniMeds built
	 */
	@WebMethod(action = "fetchUniMedsByRequest")
	@WebResult(name = "fetchUniMedsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UniMedResponse fetchUniMedsByRequest(@WebParam(name = "request") UniMedInquiryRequest request);


	/**
	 * Rebuild a list of UniMeds.
	 *
	 * @param request UniMedRequest object containing parameter for building the list of UniMed objects.
	 *
	 * @return the UniMedResponse containing the list of UniMeds built
	 */
	@WebMethod(action = "refreshUniMeds")
	@WebResult(name = "refreshUniMedsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	UniMedResponse refreshUniMeds(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all UniMeds.
	 *
	 * @param request the request
	 *
	 * @return the UniMedResponse containing all UniMed objects
	 */
	@WebMethod(action = "fetchAllUniMeds")
	@WebResult(name = "fetchAllUniMedsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	UniMedResponse fetchAllUniMeds(@WebParam(name = "request") FetchAllRequest request);
}
