package com.sensus.mlc.gestao.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Eventos;
import com.sensus.mlc.gestao.model.request.EventosRequest;
import com.sensus.mlc.gestao.model.request.InquiryEventosRequest;
import com.sensus.mlc.gestao.model.response.EventosResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IEventosDAC
{

/**
* Update eventos.
*
* @param eventosRequest the eventos request
* @return the eventos response
*/
public InternalResultsResponse<Eventos> updateEventos(EventosRequest eventosRequest);

/**
* Delete eventos.
*
* @param eventosRequest the eventos request
* @return the eventos response
*/
public InternalResponse deleteEventos(EventosRequest eventosRequest);

/**
* Fetch all eventos.
*
* @param inquiryeventosRequest the inquiryeventos request
* @return the inquiry eventos response
*/
public InternalResultsResponse<Eventos> fetchAllEventos(InquiryEventosRequest inquiryeventosRequest);

/**
* Fetch eventos by id.
*
* @param inquiryeventosRequest the inquiryeventos request
* @return the internal results response
*/
public InternalResultsResponse<Eventos> fetchEventosById(EventosRequest eventosRequest);

/**
* Generate file csv.
*
* @param inquiryEventosRequest the inquiry eventos request
* @return the inquiry eventos response
*/
public InternalResponse generateFileCSV(InquiryEventosRequest inquiryEventosRequest);

/**
* Fetch all eventos types.
*
* @param request the request
* @return the eventos response
*/
public EventosResponse fetchAllEventosTypes(Request request);

/**
* Fetch all eventos filial.
*
* @param request the request
* @return the eventos response
*/
public EventosResponse fetchAllEventosFilial(Request request);

public InternalResultsResponse<Eventos> insertEventos(EventosRequest eventosRequest);
}


