/** create by system gera-java version 1.0.0 27/04/2016*/


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
 * The Interface IProdutoWS.
 */
@WebService(serviceName = "ProdutoService", targetNamespace = "http://qat.com/sysmgmt", portName = "ProdutoServicePort")
public interface IProdutoWS
{

//===================================### PRODUTO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertProduto")
	@WebResult(name = "insertProdutoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ProdutoResponse insertProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateProduto")
	@WebResult(name = "updateProdutoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ProdutoResponse updateProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteProduto")
	@WebResult(name = "deleteProdutoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ProdutoResponse deleteProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshProdutos")
	@WebResult(name = "refreshProdutosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ProdutoResponse refreshProdutos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllProdutos")
	@WebResult(name = "fetchAllProdutosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ProdutoResponse fetchAllProdutos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchProdutoById")
	@WebResult(name = "fetchProdutoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ProdutoResponse fetchProdutoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchProdutosByRequest")
	@WebResult(name = "fetchProdutosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ProdutoResponse fetchProdutosByRequest(@WebParam(name = "request") ProdutoInquiryRequest request);


//===================================### MARCA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertMarca")
	@WebResult(name = "insertMarcaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public MarcaResponse insertMarca(@WebParam(name = "request") MarcaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateMarca")
	@WebResult(name = "updateMarcaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public MarcaResponse updateMarca(@WebParam(name = "request") MarcaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteMarca")
	@WebResult(name = "deleteMarcaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public MarcaResponse deleteMarca(@WebParam(name = "request") MarcaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshMarcas")
	@WebResult(name = "refreshMarcasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public MarcaResponse refreshMarcas(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllMarcas")
	@WebResult(name = "fetchAllMarcasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public MarcaResponse fetchAllMarcas(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchMarcaById")
	@WebResult(name = "fetchMarcaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public MarcaResponse fetchMarcaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchMarcasByRequest")
	@WebResult(name = "fetchMarcasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public MarcaResponse fetchMarcasByRequest(@WebParam(name = "request") MarcaInquiryRequest request);


//===================================### GRUPO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertGrupo")
	@WebResult(name = "insertGrupoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public GrupoResponse insertGrupo(@WebParam(name = "request") GrupoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateGrupo")
	@WebResult(name = "updateGrupoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public GrupoResponse updateGrupo(@WebParam(name = "request") GrupoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteGrupo")
	@WebResult(name = "deleteGrupoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public GrupoResponse deleteGrupo(@WebParam(name = "request") GrupoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshGrupos")
	@WebResult(name = "refreshGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public GrupoResponse refreshGrupos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllGrupos")
	@WebResult(name = "fetchAllGruposReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public GrupoResponse fetchAllGrupos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchGrupoById")
	@WebResult(name = "fetchGrupoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public GrupoResponse fetchGrupoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchGruposByRequest")
	@WebResult(name = "fetchGruposByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public GrupoResponse fetchGruposByRequest(@WebParam(name = "request") GrupoInquiryRequest request);


//===================================### SUBGRUPO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertSubGrupo")
	@WebResult(name = "insertSubGrupoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public SubGrupoResponse insertSubGrupo(@WebParam(name = "request") SubGrupoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateSubGrupo")
	@WebResult(name = "updateSubGrupoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public SubGrupoResponse updateSubGrupo(@WebParam(name = "request") SubGrupoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteSubGrupo")
	@WebResult(name = "deleteSubGrupoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public SubGrupoResponse deleteSubGrupo(@WebParam(name = "request") SubGrupoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshSubGrupos")
	@WebResult(name = "refreshSubGruposReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public SubGrupoResponse refreshSubGrupos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllSubGrupos")
	@WebResult(name = "fetchAllSubGruposReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public SubGrupoResponse fetchAllSubGrupos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchSubGrupoById")
	@WebResult(name = "fetchSubGrupoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public SubGrupoResponse fetchSubGrupoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchSubGruposByRequest")
	@WebResult(name = "fetchSubGruposByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public SubGrupoResponse fetchSubGruposByRequest(@WebParam(name = "request") SubGrupoInquiryRequest request);


//===================================### UNIMED ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertUniMed")
	@WebResult(name = "insertUniMedReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public UniMedResponse insertUniMed(@WebParam(name = "request") UniMedMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateUniMed")
	@WebResult(name = "updateUniMedReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public UniMedResponse updateUniMed(@WebParam(name = "request") UniMedMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteUniMed")
	@WebResult(name = "deleteUniMedReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public UniMedResponse deleteUniMed(@WebParam(name = "request") UniMedMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshUniMeds")
	@WebResult(name = "refreshUniMedsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public UniMedResponse refreshUniMeds(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllUniMeds")
	@WebResult(name = "fetchAllUniMedsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public UniMedResponse fetchAllUniMeds(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchUniMedById")
	@WebResult(name = "fetchUniMedByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public UniMedResponse fetchUniMedById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchUniMedsByRequest")
	@WebResult(name = "fetchUniMedsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public UniMedResponse fetchUniMedsByRequest(@WebParam(name = "request") UniMedInquiryRequest request);

}

