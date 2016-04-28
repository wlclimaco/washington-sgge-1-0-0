/** create by system gera-java version 1.0.0 28/04/2016 14:31 : 5*/

package com.qat.samples.sysmgmt.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.model.Produto;
import com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standard implementation of a web service where the operations are delegated to a BAC.
 * Note the BAC is injected by Spring.
 */
@Service
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class ProdutoWSImpl implements com.qat.samples.sysmgmt.service.IProdutoWS
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.produtowsimpl.defaultexception";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_ERROR_MSG = "sysmgmt.base.produtowsimpl.defaulterror";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ProdutoWSImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoWSImpl.class);

	/** The produto BAC. */
	private IProdutoBAC produtoBAC; // injected by Spring through setter

	/**
	 * Spring Sets the produto BAC.
	 *
	 * @param produtoBAC the new produto BAC
	 */
	public void setProdutoBAC(IProdutoBAC produtoBAC)
	{
		this.produtoBAC = produtoBAC;
	}

	/**
	 * Gets the produto bac.
	 *
	 * @return the produto bac
	 */
	public IProdutoBAC getProdutoBAC()
	{
		return produtoBAC;
	}


//===================================### PRODUTO ####======================================
	@Override
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();

		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().insertProduto(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();

		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().updateProduto(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();

		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().deleteProduto(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProdutoResponse refreshProdutos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		ProdutoResponse response = new ProdutoResponse();

		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().refreshProdutos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProdutoResponse fetchAllProdutos(FetchAllRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();

		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().fetchAllProdutos(new Produto());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IProdutoWS#fetchProdutoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();

		try
		{
			InternalResultsResponse<Produto> internalResponse = new InternalResultsResponse<Produto>();

			internalResponse = getProdutoBAC().fetchProdutoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public ProdutoResponse fetchProdutosByRequest(ProdutoInquiryRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();

		try
		{
			InternalResultsResponse<Produto> internalResponse = getProdutoBAC().fetchProdutosByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### MARCA ####======================================
	@Override
	public MarcaResponse insertMarca(MarcaMaintenanceRequest request)
	{
		MarcaResponse response = new MarcaResponse();

		try
		{
			InternalResultsResponse<Marca> internalResponse = getProdutoBAC().insertMarca(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MarcaResponse updateMarca(MarcaMaintenanceRequest request)
	{
		MarcaResponse response = new MarcaResponse();

		try
		{
			InternalResultsResponse<Marca> internalResponse = getProdutoBAC().updateMarca(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MarcaResponse deleteMarca(MarcaMaintenanceRequest request)
	{
		MarcaResponse response = new MarcaResponse();

		try
		{
			InternalResultsResponse<Marca> internalResponse = getProdutoBAC().deleteMarca(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MarcaResponse refreshMarcas(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		MarcaResponse response = new MarcaResponse();

		try
		{
			InternalResultsResponse<Marca> internalResponse = getProdutoBAC().refreshMarcas(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MarcaResponse fetchAllMarcas(FetchAllRequest request)
	{
		MarcaResponse response = new MarcaResponse();

		try
		{
			InternalResultsResponse<Marca> internalResponse = getProdutoBAC().fetchAllMarcas(new Marca());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IMarcaWS#fetchMarcaById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public MarcaResponse fetchMarcaById(FetchByIdRequest request)
	{
		MarcaResponse response = new MarcaResponse();

		try
		{
			InternalResultsResponse<Marca> internalResponse = new InternalResultsResponse<Marca>();

			internalResponse = getProdutoBAC().fetchMarcaById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public MarcaResponse fetchMarcasByRequest(MarcaInquiryRequest request)
	{
		MarcaResponse response = new MarcaResponse();

		try
		{
			InternalResultsResponse<Marca> internalResponse = getProdutoBAC().fetchMarcasByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### GRUPO ####======================================
	@Override
	public GrupoResponse insertGrupo(GrupoMaintenanceRequest request)
	{
		GrupoResponse response = new GrupoResponse();

		try
		{
			InternalResultsResponse<Grupo> internalResponse = getProdutoBAC().insertGrupo(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public GrupoResponse updateGrupo(GrupoMaintenanceRequest request)
	{
		GrupoResponse response = new GrupoResponse();

		try
		{
			InternalResultsResponse<Grupo> internalResponse = getProdutoBAC().updateGrupo(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public GrupoResponse deleteGrupo(GrupoMaintenanceRequest request)
	{
		GrupoResponse response = new GrupoResponse();

		try
		{
			InternalResultsResponse<Grupo> internalResponse = getProdutoBAC().deleteGrupo(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public GrupoResponse refreshGrupos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		GrupoResponse response = new GrupoResponse();

		try
		{
			InternalResultsResponse<Grupo> internalResponse = getProdutoBAC().refreshGrupos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public GrupoResponse fetchAllGrupos(FetchAllRequest request)
	{
		GrupoResponse response = new GrupoResponse();

		try
		{
			InternalResultsResponse<Grupo> internalResponse = getProdutoBAC().fetchAllGrupos(new Grupo());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IGrupoWS#fetchGrupoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public GrupoResponse fetchGrupoById(FetchByIdRequest request)
	{
		GrupoResponse response = new GrupoResponse();

		try
		{
			InternalResultsResponse<Grupo> internalResponse = new InternalResultsResponse<Grupo>();

			internalResponse = getProdutoBAC().fetchGrupoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public GrupoResponse fetchGruposByRequest(GrupoInquiryRequest request)
	{
		GrupoResponse response = new GrupoResponse();

		try
		{
			InternalResultsResponse<Grupo> internalResponse = getProdutoBAC().fetchGruposByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### SUBGRUPO ####======================================
	@Override
	public SubGrupoResponse insertSubGrupo(SubGrupoMaintenanceRequest request)
	{
		SubGrupoResponse response = new SubGrupoResponse();

		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getProdutoBAC().insertSubGrupo(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SubGrupoResponse updateSubGrupo(SubGrupoMaintenanceRequest request)
	{
		SubGrupoResponse response = new SubGrupoResponse();

		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getProdutoBAC().updateSubGrupo(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SubGrupoResponse deleteSubGrupo(SubGrupoMaintenanceRequest request)
	{
		SubGrupoResponse response = new SubGrupoResponse();

		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getProdutoBAC().deleteSubGrupo(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SubGrupoResponse refreshSubGrupos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		SubGrupoResponse response = new SubGrupoResponse();

		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getProdutoBAC().refreshSubGrupos(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SubGrupoResponse fetchAllSubGrupos(FetchAllRequest request)
	{
		SubGrupoResponse response = new SubGrupoResponse();

		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getProdutoBAC().fetchAllSubGrupos(new SubGrupo());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.ISubGrupoWS#fetchSubGrupoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public SubGrupoResponse fetchSubGrupoById(FetchByIdRequest request)
	{
		SubGrupoResponse response = new SubGrupoResponse();

		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = new InternalResultsResponse<SubGrupo>();

			internalResponse = getProdutoBAC().fetchSubGrupoById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public SubGrupoResponse fetchSubGruposByRequest(SubGrupoInquiryRequest request)
	{
		SubGrupoResponse response = new SubGrupoResponse();

		try
		{
			InternalResultsResponse<SubGrupo> internalResponse = getProdutoBAC().fetchSubGruposByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

//===================================### UNIMED ####======================================
	@Override
	public UniMedResponse insertUniMed(UniMedMaintenanceRequest request)
	{
		UniMedResponse response = new UniMedResponse();

		try
		{
			InternalResultsResponse<UniMed> internalResponse = getProdutoBAC().insertUniMed(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public UniMedResponse updateUniMed(UniMedMaintenanceRequest request)
	{
		UniMedResponse response = new UniMedResponse();

		try
		{
			InternalResultsResponse<UniMed> internalResponse = getProdutoBAC().updateUniMed(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public UniMedResponse deleteUniMed(UniMedMaintenanceRequest request)
	{
		UniMedResponse response = new UniMedResponse();

		try
		{
			InternalResultsResponse<UniMed> internalResponse = getProdutoBAC().deleteUniMed(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public UniMedResponse refreshUniMeds(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		UniMedResponse response = new UniMedResponse();

		try
		{
			InternalResultsResponse<UniMed> internalResponse = getProdutoBAC().refreshUniMeds(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public UniMedResponse fetchAllUniMeds(FetchAllRequest request)
	{
		UniMedResponse response = new UniMedResponse();

		try
		{
			InternalResultsResponse<UniMed> internalResponse = getProdutoBAC().fetchAllUniMeds(new UniMed());
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.dapi.impl.IUniMedWS#fetchUniMedById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public UniMedResponse fetchUniMedById(FetchByIdRequest request)
	{
		UniMedResponse response = new UniMedResponse();

		try
		{
			InternalResultsResponse<UniMed> internalResponse = new InternalResultsResponse<UniMed>();

			internalResponse = getProdutoBAC().fetchUniMedById(request);
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			ResponseHandler.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}

	@Override
	public UniMedResponse fetchUniMedsByRequest(UniMedInquiryRequest request)
	{
		UniMedResponse response = new UniMedResponse();

		try
		{
			InternalResultsResponse<UniMed> internalResponse = getProdutoBAC().fetchUniMedsByRequest(request);
			ResponseHandler.populateResponse(response, internalResponse, DEFAULT_ERROR_MSG, request.getRequestContext());
		}
		catch (Exception ex)
		{
			ResponseHandler.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME},
					request.getRequestContext());
		}

		return response;
	}
}
