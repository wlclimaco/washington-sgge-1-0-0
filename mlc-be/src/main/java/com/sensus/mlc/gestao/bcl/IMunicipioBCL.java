package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Municipio;
import com.sensus.mlc.gestao.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.gestao.model.request.MunicipioRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IMunicipioBCL
{

	/**
	 * Insert municipio.
	 *
	 * @param municipioRequest the municipio request
	 * @return the municipio response
	 */
	public InternalResultsResponse<Municipio> insertMunicipio(MunicipioRequest municipioRequest);

	/**
	 * Update municipio.
	 *
	 * @param municipioRequest the municipio request
	 * @return the municipio response
	 */
	public InternalResultsResponse<Municipio> updateMunicipio(MunicipioRequest municipioRequest);

	/**
	 * Delete municipio.
	 *
	 * @param municipioRequest the municipio request
	 * @return the municipio response
	 */
	public InternalResponse deleteMunicipio(MunicipioRequest municipioRequest);

	/**
	 * Fetch all municipio.
	 *
	 * @param inquirymunicipioRequest the inquirymunicipio request
	 * @return the inquiry municipio response
	 */
	public InternalResultsResponse<Municipio> fetchAllMunicipio(InquiryMunicipioRequest inquirymunicipioRequest);

	/**
	 * Fetch municipio by id.
	 *
	 * @param inquirymunicipioRequest the inquirymunicipio request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Municipio> fetchMunicipioById(MunicipioRequest municipioRequest);

	/**
	 * Fetch all municipio types.
	 *
	 * @param request the request
	 * @return the municipio response
	 */
	public InternalResultsResponse<Municipio> fetchAllMunicipioTypes(InquiryMunicipioRequest inquirymunicipioRequest);

	/**
	 * Fetch all municipio filial.
	 *
	 * @param request the request
	 * @return the municipio response
	 */
	public InternalResultsResponse<Municipio> fetchAllMunicipioFilial(MunicipioRequest municipioRequest);

}
