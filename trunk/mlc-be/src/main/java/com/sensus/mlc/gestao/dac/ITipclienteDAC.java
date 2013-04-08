package com.sensus.mlc.gestao.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Tipcliente;
import com.sensus.mlc.gestao.model.request.InquiryTipclienteRequest;
import com.sensus.mlc.gestao.model.request.TipclienteRequest;
import com.sensus.mlc.gestao.model.response.TipclienteResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ITipclienteDAC
{

/**
* Update tipcliente.
*
* @param tipclienteRequest the tipcliente request
* @return the tipcliente response
*/
public InternalResultsResponse<Tipcliente> updateTipcliente(TipclienteRequest tipclienteRequest);

/**
* Delete tipcliente.
*
* @param tipclienteRequest the tipcliente request
* @return the tipcliente response
*/
public InternalResponse deleteTipcliente(TipclienteRequest tipclienteRequest);

/**
* Fetch all tipcliente.
*
* @param inquirytipclienteRequest the inquirytipcliente request
* @return the inquiry tipcliente response
*/
public InternalResultsResponse<Tipcliente> fetchAllTipcliente(InquiryTipclienteRequest inquirytipclienteRequest);

/**
* Fetch tipcliente by id.
*
* @param inquirytipclienteRequest the inquirytipcliente request
* @return the internal results response
*/
public InternalResultsResponse<Tipcliente> fetchTipclienteById(TipclienteRequest tipclienteRequest);

/**
* Generate file csv.
*
* @param inquiryTipclienteRequest the inquiry tipcliente request
* @return the inquiry tipcliente response
*/
public InternalResponse generateFileCSV(InquiryTipclienteRequest inquiryTipclienteRequest);

/**
* Fetch all tipcliente types.
*
* @param request the request
* @return the tipcliente response
*/
public TipclienteResponse fetchAllTipclienteTypes(Request request);

/**
* Fetch all tipcliente filial.
*
* @param request the request
* @return the tipcliente response
*/
public TipclienteResponse fetchAllTipclienteFilial(Request request);

public InternalResultsResponse<Tipcliente> insertTipcliente(TipclienteRequest tipclienteRequest);
}


