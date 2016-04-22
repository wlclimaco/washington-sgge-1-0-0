package com.qat.samples.sysmgmt.bar.Socios;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface SociosBAR.. (Data Access Component - DAC)
 */
public interface ISociosBAR
{

	/**
	 * Fetch socio by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Socio fetchSocioById(FetchByIdRequest request);

	/**
* Insert socio.
*
* @param socio the socio
*
* @return the internal response
*/
	public InternalResponse insertSocio(Socio socio);

	/**
* Update socio.
*
* @param socio the socio
*
* @return the internal response
*/
	public InternalResponse updateSocio(Socio socio);

	/**
* Delete socio.
*
* @param socio the socio
*
* @return the internal response
*/
	public InternalResponse deleteSocioById(Socio socio);

	/**
* Delete all socios.
*
* @return the internal response
*/
	public InternalResponse deleteAllSocios();

	/**
* Fetch all socios.
*
* @return the list< socio>
*/
	public InternalResultsResponse<Socio> fetchAllSocios(Socio  socio);

	/**
* Fetch socios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Socio> fetchSociosByRequest(PagedInquiryRequest request);

}
