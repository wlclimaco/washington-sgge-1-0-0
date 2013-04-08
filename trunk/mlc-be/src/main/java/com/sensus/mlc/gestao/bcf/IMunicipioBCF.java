package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.gestao.model.request.MunicipioRequest;
import com.sensus.mlc.gestao.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.gestao.model.response.MunicipioResponse;


/**
 * The Interface IMunicipioBCF.
 *
 * @author Washington.Costa
 */
public interface IMunicipioBCF
{

	/**
	 * Insert municipio.
	 *
	 * @param municipioRequest the municipio request
	 * @return the municipio response
	 */
	public MunicipioResponse insertMunicipio(MunicipioRequest municipioRequest);

	/**
	 * Update municipio.
	 *
	 * @param municipioRequest the municipio request
	 * @return the municipio response
	 */
	public MunicipioResponse updateMunicipio(MunicipioRequest municipioRequest);

	/**
	 * Delete municipio.
	 *
	 * @param municipioRequest the municipio request
	 * @return the municipio response
	 */
	public MunicipioResponse deleteMunicipio(MunicipioRequest municipioRequest);

	/**
	 * Fetch all municipio.
	 *
	 * @param inquiryMunicipioRequest the inquiryMunicipio request
	 * @return the inquiry municipio response
	 */
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquiryMunicipioRequest);

	/**
	 * Fetch municipio by id.
	 *
	 * @param MunicipioRequest the municipio request
	 * @return the municipio response
	 */
	public MunicipioResponse fetchMunicipioById(MunicipioRequest municipioRequest);

}
