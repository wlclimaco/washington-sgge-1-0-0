package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.calccusto.model.Calccusto
import com.sensus.mlc.calccusto.model.request.CalccustoRequest;
import com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest;
import com.sensus.mlc.calccusto.model.response.CalccustoResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ICalccustoDAC
{

/**
* Update calccusto.
*
* @param calccustoRequest the calccusto request
* @return the calccusto response
*/
public InternalResultsResponse<Calccusto> updateCalccusto(CalccustoRequest calccustoRequest);

/**
* Delete calccusto.
*
* @param calccustoRequest the calccusto request
* @return the calccusto response
*/
public InternalResponse deleteCalccusto(CalccustoRequest calccustoRequest);

/**
* Fetch all calccusto.
*
* @param inquirycalccustoRequest the inquirycalccusto request
* @return the inquiry calccusto response
*/
public InternalResultsResponse<Calccusto> fetchAllCalccusto(InquiryCalccustoRequest inquirycalccustoRequest);

/**
* Fetch calccusto by id.
*
* @param inquirycalccustoRequest the inquirycalccusto request
* @return the internal results response
*/
public InternalResultsResponse<Calccusto> fetchCalccustoById(CalccustoRequest calccustoRequest);

/**
* Generate file csv.
*
* @param inquiryCalccustoRequest the inquiry calccusto request
* @return the inquiry calccusto response
*/
public InternalResponse generateFileCSV(InquiryCalccustoRequest inquiryCalccustoRequest);

/**
* Fetch all calccusto types.
*
* @param request the request
* @return the calccusto response
*/
public CalccustoResponse fetchAllCalccustoTypes(Request request);

/**
* Fetch all calccusto filial.
*
* @param request the request
* @return the calccusto response
*/
public CalccustoResponse fetchAllCalccustoFilial(Request request);

public InternalResultsResponse<Calccusto> insertCalccusto(CalccustoRequest calccustoRequest);
}


