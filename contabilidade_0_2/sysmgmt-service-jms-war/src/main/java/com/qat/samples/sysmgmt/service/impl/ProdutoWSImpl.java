/** create by system gera-java version 1.0.0 28/04/2016 16:21 : 34*/
package com.qat.samples.sysmgmt.service.impl;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.model.Produto;
import com.qat.samples.sysmgmt.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.service.IProdutoWS;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
/**
 * ProdutoWS used to provide WS interface. Delegates all calls to the IProdutoBAC.
 *
 */
@Service
@WebService(targetNamespace = "http://qat.com/jms")
public class ProdutoWSImpl implements IProdutoWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.produtojmswsimpl.defaultexception";
	/** The Constant DEFAULT_ERROR_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.produtojmswsimpl.defaulterror";
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ProdutoWSImpl.class.getName();
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoWSImpl.class);
	private IProdutoBAC produtoBAC;
	/**
	 * @return the produtoBAC which is expected to provide the implementation.
	 */
	public IProdutoBAC getProdutoBAC()
	{	
		return produtoBAC;
	}

//===================================### PRODUTO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IProdutoBAC}.
	 *
	 * @param produtoBAC the produtoBAC to set.
	 */
	public void setProdutoBAC(IProdutoBAC produtoBAC)
	{
		this.produtoBAC = produtoBAC;
	}
	
	/**
	 * Delegates call to {@link IProdutoBAC}
	 *
	 * @param request a ProdutoRequest
	 * @return ProdutoResponse
	 */
	@Override
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().insertProduto(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IProdutoBAC}
	 *
	 * @param request a ProdutoRequest
	 * @return ProdutoResponse
	 */
	@Override
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().updateProduto(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IProdutoBAC}
	 *
	 * @param request a ProdutoRequest
	 * @return ProdutoResponse
	 */
	@Override
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().deleteProduto(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IProdutoBAC}
	 *
	 * @param request a ProdutoRequest
	 * @return ProdutoResponse
	 */
	@Override
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().fetchProdutoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IProdutoBAC}
	 *
	 * @param request a ProdutoRequest
	 * @return ProdutoResponse
	 */
	@Override
	public ProdutoResponse fetchProdutosByRequest(ProdutoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().fetchProdutosByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IProdutoBAC}
	 *
	 * @param request a ProdutoRequest
	 * @return ProdutoResponse
	 */
	@Override
	public ProdutoResponse refreshProdutos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().refreshProdutos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IProdutoBAC}
	 *
	 * @param request a ProdutoRequest
	 * @return ProdutoResponse
	 */
	@Override
	public ProdutoResponse fetchAllProdutos(FetchAllRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().fetchAllProdutos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### MARCA ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IMarcaBAC}.
	 *
	 * @param marcaBAC the marcaBAC to set.
	 */
	public void setMarcaBAC(IMarcaBAC marcaBAC)
	{
		this.marcaBAC = marcaBAC;
	}
	
	/**
	 * Delegates call to {@link IMarcaBAC}
	 *
	 * @param request a MarcaRequest
	 * @return MarcaResponse
	 */
	@Override
	public MarcaResponse insertMarca(MarcaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MarcaResponse response = new MarcaResponse();
		try
		{
			InternalResultsResponse<Marca> internalResponse = getMarcaBAC().insertMarca(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMarcaBAC}
	 *
	 * @param request a MarcaRequest
	 * @return MarcaResponse
	 */
	@Override
	public MarcaResponse updateMarca(MarcaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MarcaResponse response = new MarcaResponse();
		try
		{
			InternalResultsResponse<Marca> internalResponse = getMarcaBAC().updateMarca(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMarcaBAC}
	 *
	 * @param request a MarcaRequest
	 * @return MarcaResponse
	 */
	@Override
	public MarcaResponse deleteMarca(MarcaMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MarcaResponse response = new MarcaResponse();
		try
		{
			InternalResultsResponse<Marca> internalResponse = getMarcaBAC().deleteMarca(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMarcaBAC}
	 *
	 * @param request a MarcaRequest
	 * @return MarcaResponse
	 */
	@Override
	public MarcaResponse fetchMarcaById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MarcaResponse response = new MarcaResponse();
		try
		{
			InternalResultsResponse<Marca> internalResponse = getMarcaBAC().fetchMarcaById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IMarcaBAC}
	 *
	 * @param request a MarcaRequest
	 * @return MarcaResponse
	 */
	@Override
	public MarcaResponse fetchMarcasByRequest(MarcaInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MarcaResponse response = new MarcaResponse();
		try
		{
			InternalResultsResponse<Marca> internalResponse = getMarcaBAC().fetchMarcasByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IMarcaBAC}
	 *
	 * @param request a MarcaRequest
	 * @return MarcaResponse
	 */
	@Override
	public MarcaResponse refreshMarcas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MarcaResponse response = new MarcaResponse();
		try
		{
			InternalResultsResponse<Marca> internalResponse = getMarcaBAC().refreshMarcas(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IMarcaBAC}
	 *
	 * @param request a MarcaRequest
	 * @return MarcaResponse
	 */
	@Override
	public MarcaResponse fetchAllMarcas(FetchAllRequest request)
	{
		MarcaResponse response = new MarcaResponse();
		try
		{
			InternalResultsResponse<Marca> internalResponse = getMarcaBAC().fetchAllMarcas();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### GRUPO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IGrupoBAC}.
	 *
	 * @param grupoBAC the grupoBAC to set.
	 */
	public void setGrupoBAC(IGrupoBAC grupoBAC)
	{
		this.grupoBAC = grupoBAC;
	}
	
	/**
	 * Delegates call to {@link IGrupoBAC}
	 *
	 * @param request a GrupoRequest
	 * @return GrupoResponse
	 */
	@Override
	public GrupoResponse insertGrupo(GrupoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		GrupoResponse response = new GrupoResponse();
		try
		{
			InternalResultsResponse<Grupo> internalResponse = getGrupoBAC().insertGrupo(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IGrupoBAC}
	 *
	 * @param request a GrupoRequest
	 * @return GrupoResponse
	 */
	@Override
	public GrupoResponse updateGrupo(GrupoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		GrupoResponse response = new GrupoResponse();
		try
		{
			InternalResultsResponse<Grupo> internalResponse = getGrupoBAC().updateGrupo(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IGrupoBAC}
	 *
	 * @param request a GrupoRequest
	 * @return GrupoResponse
	 */
	@Override
	public GrupoResponse deleteGrupo(GrupoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		GrupoResponse response = new GrupoResponse();
		try
		{
			InternalResultsResponse<Grupo> internalResponse = getGrupoBAC().deleteGrupo(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IGrupoBAC}
	 *
	 * @param request a GrupoRequest
	 * @return GrupoResponse
	 */
	@Override
	public GrupoResponse fetchGrupoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		GrupoResponse response = new GrupoResponse();
		try
		{
			InternalResultsResponse<Grupo> internalResponse = getGrupoBAC().fetchGrupoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IGrupoBAC}
	 *
	 * @param request a GrupoRequest
	 * @return GrupoResponse
	 */
	@Override
	public GrupoResponse fetchGruposByRequest(GrupoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		GrupoResponse response = new GrupoResponse();
		try
		{
			InternalResultsResponse<Grupo> internalResponse = getGrupoBAC().fetchGruposByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IGrupoBAC}
	 *
	 * @param request a GrupoRequest
	 * @return GrupoResponse
	 */
	@Override
	public GrupoResponse refreshGrupos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		GrupoResponse response = new GrupoResponse();
		try
		{
			InternalResultsResponse<Grupo> internalResponse = getGrupoBAC().refreshGrupos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IGrupoBAC}
	 *
	 * @param request a GrupoRequest
	 * @return GrupoResponse
	 */
	@Override
	public GrupoResponse fetchAllGrupos(FetchAllRequest request)
	{
		GrupoResponse response = new GrupoResponse();
		try
		{
			InternalResultsResponse<Grupo> internalResponse = getGrupoBAC().fetchAllGrupos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### SUBGRUPO ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link ISubGrupoBAC}.
	 *
	 * @param subgrupoBAC the subgrupoBAC to set.
	 */
	public void setSubGrupoBAC(ISubGrupoBAC subgrupoBAC)
	{
		this.subgrupoBAC = subgrupoBAC;
	}
	
	/**
	 * Delegates call to {@link ISubGrupoBAC}
	 *
	 * @param request a SubGrupoRequest
	 * @return SubGrupoResponse
	 */
	@Override
	public SubGrupoResponse insertSubGrupo(SubGrupoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SubGrupoResponse response = new SubGrupoResponse();
		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getSubGrupoBAC().insertSubGrupo(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISubGrupoBAC}
	 *
	 * @param request a SubGrupoRequest
	 * @return SubGrupoResponse
	 */
	@Override
	public SubGrupoResponse updateSubGrupo(SubGrupoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SubGrupoResponse response = new SubGrupoResponse();
		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getSubGrupoBAC().updateSubGrupo(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISubGrupoBAC}
	 *
	 * @param request a SubGrupoRequest
	 * @return SubGrupoResponse
	 */
	@Override
	public SubGrupoResponse deleteSubGrupo(SubGrupoMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SubGrupoResponse response = new SubGrupoResponse();
		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getSubGrupoBAC().deleteSubGrupo(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISubGrupoBAC}
	 *
	 * @param request a SubGrupoRequest
	 * @return SubGrupoResponse
	 */
	@Override
	public SubGrupoResponse fetchSubGrupoById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SubGrupoResponse response = new SubGrupoResponse();
		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getSubGrupoBAC().fetchSubGrupoById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link ISubGrupoBAC}
	 *
	 * @param request a SubGrupoRequest
	 * @return SubGrupoResponse
	 */
	@Override
	public SubGrupoResponse fetchSubGruposByRequest(SubGrupoInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SubGrupoResponse response = new SubGrupoResponse();
		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getSubGrupoBAC().fetchSubGruposByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link ISubGrupoBAC}
	 *
	 * @param request a SubGrupoRequest
	 * @return SubGrupoResponse
	 */
	@Override
	public SubGrupoResponse refreshSubGrupos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SubGrupoResponse response = new SubGrupoResponse();
		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getSubGrupoBAC().refreshSubGrupos(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link ISubGrupoBAC}
	 *
	 * @param request a SubGrupoRequest
	 * @return SubGrupoResponse
	 */
	@Override
	public SubGrupoResponse fetchAllSubGrupos(FetchAllRequest request)
	{
		SubGrupoResponse response = new SubGrupoResponse();
		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getSubGrupoBAC().fetchAllSubGrupos();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

//===================================### UNIMED ####======================================

	/**
	 * Spring injection uses this method to inject an implementation of {@link IUniMedBAC}.
	 *
	 * @param unimedBAC the unimedBAC to set.
	 */
	public void setUniMedBAC(IUniMedBAC unimedBAC)
	{
		this.unimedBAC = unimedBAC;
	}
	
	/**
	 * Delegates call to {@link IUniMedBAC}
	 *
	 * @param request a UniMedRequest
	 * @return UniMedResponse
	 */
	@Override
	public UniMedResponse insertUniMed(UniMedMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UniMedResponse response = new UniMedResponse();
		try
		{
			InternalResultsResponse<UniMed> internalResponse = getUniMedBAC().insertUniMed(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IUniMedBAC}
	 *
	 * @param request a UniMedRequest
	 * @return UniMedResponse
	 */
	@Override
	public UniMedResponse updateUniMed(UniMedMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UniMedResponse response = new UniMedResponse();
		try
		{
			InternalResultsResponse<UniMed> internalResponse = getUniMedBAC().updateUniMed(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IUniMedBAC}
	 *
	 * @param request a UniMedRequest
	 * @return UniMedResponse
	 */
	@Override
	public UniMedResponse deleteUniMed(UniMedMaintenanceRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UniMedResponse response = new UniMedResponse();
		try
		{
			InternalResultsResponse<UniMed> internalResponse = getUniMedBAC().deleteUniMed(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IUniMedBAC}
	 *
	 * @param request a UniMedRequest
	 * @return UniMedResponse
	 */
	@Override
	public UniMedResponse fetchUniMedById(FetchByIdRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UniMedResponse response = new UniMedResponse();
		try
		{
			InternalResultsResponse<UniMed> internalResponse = getUniMedBAC().fetchUniMedById(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	
	/**
	 * Delegates call to {@link IUniMedBAC}
	 *
	 * @param request a UniMedRequest
	 * @return UniMedResponse
	 */
	@Override
	public UniMedResponse fetchUniMedsByRequest(UniMedInquiryRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UniMedResponse response = new UniMedResponse();
		try
		{
			InternalResultsResponse<UniMed> internalResponse = getUniMedBAC().fetchUniMedsByRequest(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IUniMedBAC}
	 *
	 * @param request a UniMedRequest
	 * @return UniMedResponse
	 */
	@Override
	public UniMedResponse refreshUniMeds(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UniMedResponse response = new UniMedResponse();
		try
		{
			InternalResultsResponse<UniMed> internalResponse = getUniMedBAC().refreshUniMeds(request);
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
	/**
	 * Delegates call to {@link IUniMedBAC}
	 *
	 * @param request a UniMedRequest
	 * @return UniMedResponse
	 */
	@Override
	public UniMedResponse fetchAllUniMeds(FetchAllRequest request)
	{
		UniMedResponse response = new UniMedResponse();
		try
		{
			InternalResultsResponse<UniMed> internalResponse = getUniMedBAC().fetchAllUniMeds();
			ResponseHandler
					.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
