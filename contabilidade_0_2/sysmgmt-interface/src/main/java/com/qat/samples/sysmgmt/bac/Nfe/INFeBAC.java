/** create by system gera-java version 1.0.0 28/09/2016 16:26 : 41*/
package com.qat.samples.sysmgmt.bac.Nfe;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nfe.model.NFNota;
import com.qat.samples.sysmgmt.nfe.request.NFNotaInquiryRequest;
import com.qat.samples.sysmgmt.nfe.request.NFNotaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface INFeBAC. (Business Area Component - BAC)
 */
public interface INFeBAC
{



//===================================### NFNOTA ####======================================
	/**

	/**
	 * Insert nfnota.
	 *
* @param request the nfnota maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> insertNFNota(NFNotaMaintenanceRequest request);

	/**
* Update nfnota.
*
* @param request the nfnota maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> updateNFNota(NFNotaMaintenanceRequest request);

	/**
* Delete nfnota.
*
* @param request the nfnota maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> deleteNFNota(NFNotaMaintenanceRequest request);


	/**
* Fetch nfnota by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> fetchNFNotaById(FetchByIdRequest request);

	/**
* Fetch all nfnotas.
*
* @return the internal results response< nfnota>
*/
	public InternalResultsResponse<NFNota> fetchAllNFNotas(NFNota  nfnota);

	/**
* Fetch nfnotas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<NFNota> fetchNFNotasByRequest(NFNotaInquiryRequest request);

}
