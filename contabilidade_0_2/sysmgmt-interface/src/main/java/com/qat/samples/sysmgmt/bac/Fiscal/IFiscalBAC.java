package com.qat.samples.sysmgmt.bac.Fiscal;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IFiscalBAC. (Business Area Component - BAC)
 */
public interface IFiscalBAC
{



//===================================### REGIME ####======================================
	/**

	/**
	 * Insert regime.
	 *
* @param request the regime maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Regime> insertRegime(RegimeMaintenanceRequest request);

	/**
* Update regime.
*
* @param request the regime maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Regime> updateRegime(RegimeMaintenanceRequest request);

	/**
* Delete regime.
*
* @param request the regime maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Regime> deleteRegime(RegimeMaintenanceRequest request);

	/**
* Refresh regimes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Regime> refreshRegimes(RefreshRequest request);

	/**
* Fetch regime by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Regime> fetchRegimeById(FetchByIdRequest request);

	/**
* Fetch all regimes.
*
* @return the internal results response< regime>
*/
	public InternalResultsResponse<Regime> fetchAllRegimes(Regime  regime);

	/**
* Fetch regimes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Regime> fetchRegimesByRequest(RegimeInquiryRequest request);


//===================================### CFOP ####======================================
	/**

	/**
	 * Insert cfop.
	 *
* @param request the cfop maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request);

	/**
* Update cfop.
*
* @param request the cfop maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request);

	/**
* Delete cfop.
*
* @param request the cfop maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request);

	/**
* Refresh cfops.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Cfop> refreshCfops(RefreshRequest request);

	/**
* Fetch cfop by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> fetchCfopById(FetchByIdRequest request);

	/**
* Fetch all cfops.
*
* @return the internal results response< cfop>
*/
	public InternalResultsResponse<Cfop> fetchAllCfops(Cfop  cfop);

	/**
* Fetch cfops by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request);


//===================================### CNAE ####======================================
	/**

	/**
	 * Insert cnae.
	 *
* @param request the cnae maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request);

	/**
* Update cnae.
*
* @param request the cnae maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request);

	/**
* Delete cnae.
*
* @param request the cnae maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cnae> deleteCnae(CnaeMaintenanceRequest request);

	/**
* Refresh cnaes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Cnae> refreshCnaes(RefreshRequest request);

	/**
* Fetch cnae by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cnae> fetchCnaeById(FetchByIdRequest request);

	/**
* Fetch all cnaes.
*
* @return the internal results response< cnae>
*/
	public InternalResultsResponse<Cnae> fetchAllCnaes(Cnae  cnae);

	/**
* Fetch cnaes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cnae> fetchCnaesByRequest(CnaeInquiryRequest request);

}
