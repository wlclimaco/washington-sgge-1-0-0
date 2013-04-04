Memo1
package com.sensus.mlc.UnidMed.dac


import com.sensus.common.model.request.Request
import com.sensus.common.model.response.InternalResponse
import com.sensus.common.model.response.InternalResultsResponse
import com.sensus.mlc.UnidMed.model.Unidmed
import com.sensus.mlc.UnidMed.model.request.UnidmedRequest
import com.sensus.mlc.UnidMed.model.request.InquiryUnidmedRequest
import com.sensus.mlc.UnidMed.model.response.UnidmedResponse

/**
* The Interface IActionDAC.
*
* @author - QAT Brazil.
*
*/
public interface IUnidmedDAC
{

/**
* Update UnidMed.
*
* @param UnidMedRequest the UnidMed request
* @return the UnidMed response
*/
public InternalResultsResponse<Unidmed> updateUnidmed(UnidmedRequest UnidMedRequest)

/**
* Delete UnidMed.
*
* @param UnidMedRequest the UnidMed request
* @return the UnidMed response
*/
public InternalResponse deleteUnidmed(UnidmedRequest UnidMedRequest)

/**
* Fetch all UnidMed.
*
* @param inquiryUnidMedRequest the inquiryUnidMed request
* @return the inquiry UnidMed response
*/
public InternalResultsResponse<Unidmed> fetchAllUnidmed(InquiryUnidmedRequest inquiryUnidMedRequest)

/**
* Fetch UnidMed by id.
*
* @param inquiryUnidMedRequest the inquiryUnidMed request
* @return the internal results response
*/
public InternalResultsResponse<Unidmed> fetchUnidmedById(UnidmedRequest UnidMedRequest)

/**
* Generate file csv.
*
* @param inquiryUnidmedRequest the inquiry UnidMed request
* @return the inquiry UnidMed response
*/
public InternalResponse generateFileCSV(InquiryUnidmedRequest inquiryUnidmedRequest)

/**
* Fetch all UnidMed types.
*
* @param request the request
* @return the UnidMed response
*/
public UnidmedResponse fetchAllUnidmedTypes(Request request)

/**
* Fetch all UnidMed filial.
*
* @param request the request
* @return the UnidMed response
*/
public UnidmedResponse fetchAllUnidmedFilial(Request request)

public InternalResultsResponse<Unidmed> insertUnidmed(UnidmedRequest UnidMedRequest)
}


