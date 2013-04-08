package com.sensus.mlc.gestao.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Embalagens;
import com.sensus.mlc.gestao.model.request.EmbalagensRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmbalagensRequest;
import com.sensus.mlc.gestao.model.response.EmbalagensResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IEmbalagensDAC
{

/**
* Update embalagens.
*
* @param embalagensRequest the embalagens request
* @return the embalagens response
*/
public InternalResultsResponse<Embalagens> updateEmbalagens(EmbalagensRequest embalagensRequest);

/**
* Delete embalagens.
*
* @param embalagensRequest the embalagens request
* @return the embalagens response
*/
public InternalResponse deleteEmbalagens(EmbalagensRequest embalagensRequest);

/**
* Fetch all embalagens.
*
* @param inquiryembalagensRequest the inquiryembalagens request
* @return the inquiry embalagens response
*/
public InternalResultsResponse<Embalagens> fetchAllEmbalagens(InquiryEmbalagensRequest inquiryembalagensRequest);

/**
* Fetch embalagens by id.
*
* @param inquiryembalagensRequest the inquiryembalagens request
* @return the internal results response
*/
public InternalResultsResponse<Embalagens> fetchEmbalagensById(EmbalagensRequest embalagensRequest);

/**
* Generate file csv.
*
* @param inquiryEmbalagensRequest the inquiry embalagens request
* @return the inquiry embalagens response
*/
public InternalResponse generateFileCSV(InquiryEmbalagensRequest inquiryEmbalagensRequest);

/**
* Fetch all embalagens types.
*
* @param request the request
* @return the embalagens response
*/
public EmbalagensResponse fetchAllEmbalagensTypes(Request request);

/**
* Fetch all embalagens filial.
*
* @param request the request
* @return the embalagens response
*/
public EmbalagensResponse fetchAllEmbalagensFilial(Request request);

public InternalResultsResponse<Embalagens> insertEmbalagens(EmbalagensRequest embalagensRequest);
}


