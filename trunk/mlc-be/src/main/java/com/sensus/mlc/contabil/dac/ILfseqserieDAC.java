package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfseqserie.model.Lfseqserie
import com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.response.LfseqserieResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfseqserieDAC
{

/**
* Update lfseqserie.
*
* @param lfseqserieRequest the lfseqserie request
* @return the lfseqserie response
*/
public InternalResultsResponse<Lfseqserie> updateLfseqserie(LfseqserieRequest lfseqserieRequest);

/**
* Delete lfseqserie.
*
* @param lfseqserieRequest the lfseqserie request
* @return the lfseqserie response
*/
public InternalResponse deleteLfseqserie(LfseqserieRequest lfseqserieRequest);

/**
* Fetch all lfseqserie.
*
* @param inquirylfseqserieRequest the inquirylfseqserie request
* @return the inquiry lfseqserie response
*/
public InternalResultsResponse<Lfseqserie> fetchAllLfseqserie(InquiryLfseqserieRequest inquirylfseqserieRequest);

/**
* Fetch lfseqserie by id.
*
* @param inquirylfseqserieRequest the inquirylfseqserie request
* @return the internal results response
*/
public InternalResultsResponse<Lfseqserie> fetchLfseqserieById(LfseqserieRequest lfseqserieRequest);

/**
* Generate file csv.
*
* @param inquiryLfseqserieRequest the inquiry lfseqserie request
* @return the inquiry lfseqserie response
*/
public InternalResponse generateFileCSV(InquiryLfseqserieRequest inquiryLfseqserieRequest);

/**
* Fetch all lfseqserie types.
*
* @param request the request
* @return the lfseqserie response
*/
public LfseqserieResponse fetchAllLfseqserieTypes(Request request);

/**
* Fetch all lfseqserie filial.
*
* @param request the request
* @return the lfseqserie response
*/
public LfseqserieResponse fetchAllLfseqserieFilial(Request request);

public InternalResultsResponse<Lfseqserie> insertLfseqserie(LfseqserieRequest lfseqserieRequest);
}


