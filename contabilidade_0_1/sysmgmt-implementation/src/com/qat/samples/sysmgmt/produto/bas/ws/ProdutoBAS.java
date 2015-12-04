package com.qat.samples.sysmgmt.produto.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.bai.IProdutoBAI;
import com.qat.samples.sysmgmt.produto.bas.IProdutoBAS;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.produto.model.response.ServicoResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class ProdutoBAS implements IProdutoBAS
{

	/** The produto bai. */
	private IProdutoBAI produtoBAI; // injected by Spring through setter

	/**
	 * Spring Sets the produto bai.
	 * 
	 * @param produtoBAI the new produto bai
	 */
	public void setProdutoBAI(IProdutoBAI produtoBAI)
	{
		this.produtoBAI = produtoBAI;
	}

	/**
	 * Gets the produto bac.
	 * 
	 * @return the produto bac
	 */
	public IProdutoBAI getProdutoBAI()
	{
		return produtoBAI;
	}

	@Override
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request)
	{
		return getProdutoBAI().insertProduto(request);
	}

	@Override
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request)
	{
		return getProdutoBAI().updateProduto(request);

	}

	@Override
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request)
	{
		return getProdutoBAI().deleteProduto(request);
	}

	@Override
	public ProdutoResponse fetchAllProdutos(FetchAllRequest request)
	{
		return getProdutoBAI().fetchAllProdutos(request);
	}

	@Override
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request)
	{
		return getProdutoBAI().fetchProdutoById(request);

	}

	@Override
	public ProdutoResponse refreshProdutos(RefreshRequest request)
	{
		return null;

	}

	@Override
	public ProdutoResponse fetchProdutosByRequest(ProdutoInquiryRequest request)
	{
		return getProdutoBAI().fetchProdutoByRequest(request);
	}

	@Override
	public ServicoResponse insertServico(ServicoMaintenanceRequest request)
	{
		return getProdutoBAI().insertServico(request);
	}

	@Override
	public ServicoResponse updateServico(ServicoMaintenanceRequest request)
	{
		return getProdutoBAI().updateServico(request);
	}

	@Override
	public ServicoResponse deleteServico(ServicoMaintenanceRequest request)
	{
		return getProdutoBAI().deleteServico(request);
	}

	@Override
	public ServicoResponse fetchServicosByRequest(ServicoInquiryRequest request)
	{
		return getProdutoBAI().fetchServicoByRequest(request);
	}

	@Override
	public PlanoResponse insertPlano(PlanoMaintenanceRequest request)
	{
		return getProdutoBAI().insertPlano(request);
	}

	@Override
	public PlanoResponse updatePlano(PlanoMaintenanceRequest request)
	{
		return getProdutoBAI().updatePlano(request);
	}

	@Override
	public PlanoResponse deletePlano(PlanoMaintenanceRequest request)
	{
		return getProdutoBAI().deletePlano(request);
	}

	@Override
	public PlanoResponse fetchPlanosByRequest(PlanoInquiryRequest request)
	{
		return getProdutoBAI().fetchPlanoByRequest(request);
	}

	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.bas.IProdutoBAS#insertProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
	// * )
	// */
	// @Override
	// public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request)
	// {
	// return getProdutoBAI().insertProduto(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.bas.IProdutoBAS#updateProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
	// * )
	// */
	// @Override
	// public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request)
	// {
	// return getProdutoBAI().updateProduto(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.bas.IProdutoBAS#deleteProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
	// * )
	// */
	// @Override
	// public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request)
	// {
	// return getProdutoBAI().deleteProduto(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bas.IProdutoBAS#refreshProdutos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	// */
	// @Override
	// public ProdutoResponse refreshProdutos(RefreshRequest request)
	// {
	// // This method is demo code only. Do not view this as a QAT Global Standard.
	// return getProdutoBAI().refreshProdutos(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bas.IProdutoBAS#fetchAllProdutos(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	// */
	// @Override
	// public ProdutoResponse fetchAllProdutos(ProdutoInquiryRequest request)
	// {
	// return getProdutoBAI().fetchAllProdutos(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// *
	// com.qat.samples.sysmgmt.bas.IProdutoBAS#fetchProdutoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	// */
	// @Override
	// public ProdutoResponse fetchProdutoById(FetchByIdRequest request)
	// {
	// return getProdutoBAI().fetchProdutoById(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.qat.samples.sysmgmt.bas.IProdutoBAS#fetchProdutosByRequest(com.qat.samples.sysmgmt.model.request.
	// * PagedInquiryRequest)
	// */
	// @Override
	// public ProdutoResponse fetchProdutosByRequest(PagedInquiryRequest request)
	// {
	// return getProdutoBAI().fetchProdutosByRequest(request);
	// }
	//
	// @Override
	// public CadastroResponse insertCadastro(CadastroMaintenanceRequest request)
	// {
	// return getProdutoBAI().insertCadastro(request);
	// }
	//
	// @Override
	// public CadastroResponse updateCadastro(CadastroMaintenanceRequest request)
	// {
	// return getProdutoBAI().updateCadastro(request);
	// }
	//
	// @Override
	// public CadastroResponse deleteCadastro(CadastroMaintenanceRequest request)
	// {
	// return getProdutoBAI().deleteCadastro(request);
	// }
	//
	// @Override
	// public CadastroResponse fetchAllCadastros(CadastroInquiryRequest request)
	// {
	// return getProdutoBAI().fetchAllCadastros(request);
	// }
	//
	// @Override
	// public CadastroResponse fetchCadastroById(FetchByIdRequest request)
	// {
	// return getProdutoBAI().fetchCadastroById(request);
	// }
	//
	// @Override
	// public CadastroResponse refreshCadastros(RefreshRequest request)
	// {
	// return getProdutoBAI().refreshCadastros(request);
	// }
	//
	// @Override
	// public CadastroResponse fetchCadastrosByRequest(CadastroInquiryRequest request)
	// {
	// return getProdutoBAI().fetchCadastrosByRequest(request);
	// }
	//
	// @Override
	// public EmbalagemResponse insertEmbalagem(EmbalagemMaintenanceRequest request)
	// {
	// return getProdutoBAI().insertEmbalagem(request);
	// }
	//
	// @Override
	// public EmbalagemResponse updateEmbalagem(EmbalagemMaintenanceRequest request)
	// {
	// return getProdutoBAI().updateEmbalagem(request);
	// }
	//
	// @Override
	// public EmbalagemResponse deleteEmbalagem(EmbalagemMaintenanceRequest request)
	// {
	// return getProdutoBAI().deleteEmbalagem(request);
	// }
	//
	// @Override
	// public EmbalagemResponse fetchAllEmbalagems(EmbalagemInquiryRequest request)
	// {
	// return getProdutoBAI().fetchAllEmbalagems(request);
	// }
	//
	// @Override
	// public EmbalagemResponse insertUniMed(EmbalagemMaintenanceRequest request)
	// {
	// return getProdutoBAI().insertUniMed(request);
	// }
	//
	// @Override
	// public EmbalagemResponse updateUniMed(EmbalagemMaintenanceRequest request)
	// {
	// return getProdutoBAI().updateUniMed(request);
	// }
	//
	// @Override
	// public EmbalagemResponse deleteUniMed(EmbalagemMaintenanceRequest request)
	// {
	// return getProdutoBAI().deleteUniMed(request);
	// }
	//
	// @Override
	// public EmbalagemResponse fetchAllUniMeds(EmbalagemInquiryRequest request)
	// {
	// return getProdutoBAI().fetchAllUniMeds(request);
	// }

}
