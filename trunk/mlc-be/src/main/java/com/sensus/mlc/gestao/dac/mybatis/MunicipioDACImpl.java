package com.sensus.mlc.municipio.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.municipio.model.Municipio
import com.sensus.mlc.municipio.model.request.MunicipioRequest;
import com.sensus.mlc.municipio.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.municipio.model.response.MunicipioResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IMunicipioDAC;
{

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
* Generate file csv.
*
* @param inquiryMunicipioRequest the inquiry municipio request
* @return the inquiry municipio response
*/
public InternalResponse generateFileCSV(InquiryMunicipioRequest inquiryMunicipioRequest);

/**
* Fetch all municipio types.
*
* @param request the request
* @return the municipio response
*/
public MunicipioResponse fetchAllMunicipioTypes(Request request);

/**
* Fetch all municipio filial.
*
* @param request the request
* @return the municipio response
*/
public MunicipioResponse fetchAllMunicipioFilial(Request request);

public InternalResultsResponse<Municipio> insertMunicipio(MunicipioRequest municipioRequest);
}


