package com.qat.samples.sysmgmt.produto.bai;

import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.ClassificacaoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.response.CfopResponse;
import com.qat.samples.sysmgmt.produto.model.response.GrupoResponse;
import com.qat.samples.sysmgmt.produto.model.response.MarcaResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.produto.model.response.SubGrupoResponse;
import com.qat.samples.sysmgmt.produto.model.response.TributacaoResponse;
import com.qat.samples.sysmgmt.produto.model.response.UniMedResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IProdutoBAI.
 * 
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface IProdutoBAI
{

	/**
	 * Insert location.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request);

	/**
	 * Update location.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request);

	/**
	 * Delete location.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ProdutoResponse fetchProdutoByRequest(ProdutoInquiryRequest request);

	/**
	 * Fetch uni med by request.
	 * 
	 * @param request the request
	 * @return the uni med response
	 */
	public UniMedResponse fetchUniMedByRequest(UniMedInquiryRequest request);

	/**
	 * Fetch grupo by request.
	 * 
	 * @param request the request
	 * @return the grupo response
	 */
	public GrupoResponse fetchGrupoByRequest(GrupoInquiryRequest request);

	/**
	 * Fetch sub grupo by request.
	 * 
	 * @param request the request
	 * @return the sub grupo response
	 */
	public SubGrupoResponse fetchSubGrupoByRequest(SubGrupoInquiryRequest request);

	/**
	 * Fetch marca by request.
	 * 
	 * @param request the request
	 * @return the marca response
	 */
	public MarcaResponse fetchMarcaByRequest(MarcaInquiryRequest request);

	/**
	 * Fetch tributacao by request.
	 * 
	 * @param request the request
	 * @return the tributacao response
	 */
	public TributacaoResponse fetchTributacaoByRequest(TributacaoInquiryRequest request);

	/**
	 * Fetch cfop by request.
	 * 
	 * @param request the request
	 * @return the cfop response
	 */
	public CfopResponse fetchCfopByRequest(CfopInquiryRequest request);

	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

}