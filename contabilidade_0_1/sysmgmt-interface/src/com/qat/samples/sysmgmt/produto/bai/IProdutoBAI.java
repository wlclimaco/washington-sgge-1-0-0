package com.qat.samples.sysmgmt.produto.bai;

import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.ClassificacaoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.response.CfopResponse;
import com.qat.samples.sysmgmt.produto.model.response.GrupoResponse;
import com.qat.samples.sysmgmt.produto.model.response.MarcaResponse;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.produto.model.response.ServicoResponse;
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

	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request);

	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request);

	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request);

	public ProdutoResponse fetchProdutoById(FetchByIdRequest request);

	public ProdutoResponse fetchProdutoByRequest(ProdutoInquiryRequest request);

	public UniMedResponse fetchUniMedByRequest(UniMedInquiryRequest request);

	public GrupoResponse fetchGrupoByRequest(GrupoInquiryRequest request);

	public SubGrupoResponse fetchSubGrupoByRequest(SubGrupoInquiryRequest request);

	public MarcaResponse fetchMarcaByRequest(MarcaInquiryRequest request);

	public TributacaoResponse fetchTributacaoByRequest(TributacaoInquiryRequest request);

	public CfopResponse fetchCfopByRequest(CfopInquiryRequest request);

	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

	// Serviço

	public ServicoResponse insertServico(ServicoMaintenanceRequest request);

	public ServicoResponse updateServico(ServicoMaintenanceRequest request);

	public ServicoResponse deleteServico(ServicoMaintenanceRequest request);

	public ServicoResponse fetchServicoById(FetchByIdRequest request);

	public ServicoResponse fetchServicoByRequest(ServicoInquiryRequest request);

	// Plano

	public PlanoResponse insertPlano(PlanoMaintenanceRequest request);

	public PlanoResponse updatePlano(PlanoMaintenanceRequest request);

	public PlanoResponse deletePlano(PlanoMaintenanceRequest request);

	public PlanoResponse fetchPlanoById(FetchByIdRequest request);

	public PlanoResponse fetchPlanoByRequest(PlanoInquiryRequest request);

}