package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfmensagem.model.Lfmensagem
import com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.response.LfmensagemResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfmensagemDAC
{

/**
* Update lfmensagem.
*
* @param lfmensagemRequest the lfmensagem request
* @return the lfmensagem response
*/
public InternalResultsResponse<Lfmensagem> updateLfmensagem(LfmensagemRequest lfmensagemRequest);

/**
* Delete lfmensagem.
*
* @param lfmensagemRequest the lfmensagem request
* @return the lfmensagem response
*/
public InternalResponse deleteLfmensagem(LfmensagemRequest lfmensagemRequest);

/**
* Fetch all lfmensagem.
*
* @param inquirylfmensagemRequest the inquirylfmensagem request
* @return the inquiry lfmensagem response
*/
public InternalResultsResponse<Lfmensagem> fetchAllLfmensagem(InquiryLfmensagemRequest inquirylfmensagemRequest);

/**
* Fetch lfmensagem by id.
*
* @param inquirylfmensagemRequest the inquirylfmensagem request
* @return the internal results response
*/
public InternalResultsResponse<Lfmensagem> fetchLfmensagemById(LfmensagemRequest lfmensagemRequest);

/**
* Generate file csv.
*
* @param inquiryLfmensagemRequest the inquiry lfmensagem request
* @return the inquiry lfmensagem response
*/
public InternalResponse generateFileCSV(InquiryLfmensagemRequest inquiryLfmensagemRequest);

/**
* Fetch all lfmensagem types.
*
* @param request the request
* @return the lfmensagem response
*/
public LfmensagemResponse fetchAllLfmensagemTypes(Request request);

/**
* Fetch all lfmensagem filial.
*
* @param request the request
* @return the lfmensagem response
*/
public LfmensagemResponse fetchAllLfmensagemFilial(Request request);

public InternalResultsResponse<Lfmensagem> insertLfmensagem(LfmensagemRequest lfmensagemRequest);
}


