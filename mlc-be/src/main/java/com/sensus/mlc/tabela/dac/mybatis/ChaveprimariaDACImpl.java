package com.sensus.mlc.chaveprimaria.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.chaveprimaria.model.Chaveprimaria
import com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.response.ChaveprimariaResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IChaveprimariaDAC;
{

/**
* Update chaveprimaria.
*
* @param chaveprimariaRequest the chaveprimaria request
* @return the chaveprimaria response
*/
public InternalResultsResponse<Chaveprimaria> updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest);

/**
* Delete chaveprimaria.
*
* @param chaveprimariaRequest the chaveprimaria request
* @return the chaveprimaria response
*/
public InternalResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest);

/**
* Fetch all chaveprimaria.
*
* @param inquirychaveprimariaRequest the inquirychaveprimaria request
* @return the inquiry chaveprimaria response
*/
public InternalResultsResponse<Chaveprimaria> fetchAllChaveprimaria(InquiryChaveprimariaRequest inquirychaveprimariaRequest);

/**
* Fetch chaveprimaria by id.
*
* @param inquirychaveprimariaRequest the inquirychaveprimaria request
* @return the internal results response
*/
public InternalResultsResponse<Chaveprimaria> fetchChaveprimariaById(ChaveprimariaRequest chaveprimariaRequest);

/**
* Generate file csv.
*
* @param inquiryChaveprimariaRequest the inquiry chaveprimaria request
* @return the inquiry chaveprimaria response
*/
public InternalResponse generateFileCSV(InquiryChaveprimariaRequest inquiryChaveprimariaRequest);

/**
* Fetch all chaveprimaria types.
*
* @param request the request
* @return the chaveprimaria response
*/
public ChaveprimariaResponse fetchAllChaveprimariaTypes(Request request);

/**
* Fetch all chaveprimaria filial.
*
* @param request the request
* @return the chaveprimaria response
*/
public ChaveprimariaResponse fetchAllChaveprimariaFilial(Request request);

public InternalResultsResponse<Chaveprimaria> insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest);
}


