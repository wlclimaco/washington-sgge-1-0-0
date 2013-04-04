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


package com.sensus.mlc.ClassTitulares.dac


import com.sensus.common.model.request.Request
import com.sensus.common.model.response.InternalResponse
import com.sensus.common.model.response.InternalResultsResponse
import com.sensus.mlc.ClassTitulares.model.Classtitulares
import com.sensus.mlc.ClassTitulares.model.request.ClasstitularesRequest
import com.sensus.mlc.ClassTitulares.model.request.InquiryClasstitularesRequest
import com.sensus.mlc.ClassTitulares.model.response.ClasstitularesResponse

/**
* The Interface IActionDAC.
*
* @author - QAT Brazil.
*
*/
public interface IClasstitularesDAC
{

/**
* Update ClassTitulares.
*
* @param ClassTitularesRequest the ClassTitulares request
* @return the ClassTitulares response
*/
public InternalResultsResponse<Classtitulares> updateClasstitulares(ClasstitularesRequest ClassTitularesRequest)

/**
* Delete ClassTitulares.
*
* @param ClassTitularesRequest the ClassTitulares request
* @return the ClassTitulares response
*/
public InternalResponse deleteClasstitulares(ClasstitularesRequest ClassTitularesRequest)

/**
* Fetch all ClassTitulares.
*
* @param inquiryClassTitularesRequest the inquiryClassTitulares request
* @return the inquiry ClassTitulares response
*/
public InternalResultsResponse<Classtitulares> fetchAllClasstitulares(InquiryClasstitularesRequest inquiryClassTitularesRequest)

/**
* Fetch ClassTitulares by id.
*
* @param inquiryClassTitularesRequest the inquiryClassTitulares request
* @return the internal results response
*/
public InternalResultsResponse<Classtitulares> fetchClasstitularesById(ClasstitularesRequest ClassTitularesRequest)

/**
* Generate file csv.
*
* @param inquiryClasstitularesRequest the inquiry ClassTitulares request
* @return the inquiry ClassTitulares response
*/
public InternalResponse generateFileCSV(InquiryClasstitularesRequest inquiryClasstitularesRequest)

/**
* Fetch all ClassTitulares types.
*
* @param request the request
* @return the ClassTitulares response
*/
public ClasstitularesResponse fetchAllClasstitularesTypes(Request request)

/**
* Fetch all ClassTitulares filial.
*
* @param request the request
* @return the ClassTitulares response
*/
public ClasstitularesResponse fetchAllClasstitularesFilial(Request request)

public InternalResultsResponse<Classtitulares> insertClasstitulares(ClasstitularesRequest ClassTitularesRequest)
}


