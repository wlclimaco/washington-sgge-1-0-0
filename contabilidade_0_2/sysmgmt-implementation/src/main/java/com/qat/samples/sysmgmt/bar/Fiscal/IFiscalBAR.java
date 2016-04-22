package com.qat.samples.sysmgmt.bar.Fiscal;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.CnaeEmpresa;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface FiscalBAR.. (Data Access Component - DAC)
 */
public interface IFiscalBAR
{

	/**
	 * Fetch regime by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Regime fetchRegimeById(FetchByIdRequest request);

	/**
* Insert regime.
*
* @param regime the regime
*
* @return the internal response
*/
	public InternalResponse insertRegime(Regime regime);

	/**
* Update regime.
*
* @param regime the regime
*
* @return the internal response
*/
	public InternalResponse updateRegime(Regime regime);

	/**
* Delete regime.
*
* @param regime the regime
*
* @return the internal response
*/
	public InternalResponse deleteRegimeById(Regime regime);

	/**
* Delete all regimes.
*
* @return the internal response
*/
	public InternalResponse deleteAllRegimes();

	/**
* Fetch all regimes.
*
* @return the list< regime>
*/
	public InternalResultsResponse<Regime> fetchAllRegimes(Regime  regime);

	/**
* Fetch regimes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Regime> fetchRegimesByRequest(RegimeInquiryRequest request);

	/**
	 * Fetch cfop by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Cfop fetchCfopById(FetchByIdRequest request);

	/**
* Insert cfop.
*
* @param cfop the cfop
*
* @return the internal response
*/
	public InternalResponse insertCfop(Cfop cfop);

	/**
* Update cfop.
*
* @param cfop the cfop
*
* @return the internal response
*/
	public InternalResponse updateCfop(Cfop cfop);

	/**
* Delete cfop.
*
* @param cfop the cfop
*
* @return the internal response
*/
	public InternalResponse deleteCfopById(Cfop cfop);

	/**
* Delete all cfops.
*
* @return the internal response
*/
	public InternalResponse deleteAllCfops();

	/**
* Fetch all cfops.
*
* @return the list< cfop>
*/
	public InternalResultsResponse<Cfop> fetchAllCfops(Cfop  cfop);

	/**
* Fetch cfops by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request);

	/**
	 * Fetch cnae by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Cnae fetchCnaeById(FetchByIdRequest request);

	/**
* Insert cnae.
*
* @param cnae the cnae
*
* @return the internal response
*/
	public InternalResponse insertCnae(Cnae cnae);

	/**
* Update cnae.
*
* @param cnae the cnae
*
* @return the internal response
*/
	public InternalResponse updateCnae(Cnae cnae);

	/**
* Delete cnae.
*
* @param cnae the cnae
*
* @return the internal response
*/
	public InternalResponse deleteCnaeById(Cnae cnae);

	/**
* Delete all cnaes.
*
* @return the internal response
*/
	public InternalResponse deleteAllCnaes();

	/**
* Fetch all cnaes.
*
* @return the list< cnae>
*/
	public InternalResultsResponse<Cnae> fetchAllCnaes(Cnae  cnae);

	/**
* Fetch cnaes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cnae> fetchCnaesByRequest(CnaeInquiryRequest request);

	/**
	 * Fetch cnaeempresa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public CnaeEmpresa fetchCnaeEmpresaById(FetchByIdRequest request);

	/**
* Insert cnaeempresa.
*
* @param cnaeempresa the cnaeempresa
*
* @return the internal response
*/
	public InternalResponse insertCnaeEmpresa(CnaeEmpresa cnaeempresa);

	/**
* Update cnaeempresa.
*
* @param cnaeempresa the cnaeempresa
*
* @return the internal response
*/
	public InternalResponse updateCnaeEmpresa(CnaeEmpresa cnaeempresa);

	/**
* Delete cnaeempresa.
*
* @param cnaeempresa the cnaeempresa
*
* @return the internal response
*/
	public InternalResponse deleteCnaeEmpresaById(CnaeEmpresa cnaeempresa);

	/**
* Delete all cnaeempresas.
*
* @return the internal response
*/
	public InternalResponse deleteAllCnaeEmpresas();

	/**
* Fetch all cnaeempresas.
*
* @return the list< cnaeempresa>
*/
	public InternalResultsResponse<CnaeEmpresa> fetchAllCnaeEmpresas(CnaeEmpresa  cnaeempresa);

	/**
* Fetch cnaeempresas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CnaeEmpresa> fetchCnaeEmpresasByRequest(PagedInquiryRequest request);

}
