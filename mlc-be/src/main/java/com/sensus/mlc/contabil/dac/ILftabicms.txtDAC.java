package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txt
import com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.response.Lftabicms.txtResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILftabicms.txtDAC
{

/**
* Update lftabicms.txt.
*
* @param lftabicms.txtRequest the lftabicms.txt request
* @return the lftabicms.txt response
*/
public InternalResultsResponse<Lftabicms.txt> updateLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest);

/**
* Delete lftabicms.txt.
*
* @param lftabicms.txtRequest the lftabicms.txt request
* @return the lftabicms.txt response
*/
public InternalResponse deleteLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest);

/**
* Fetch all lftabicms.txt.
*
* @param inquirylftabicms.txtRequest the inquirylftabicms.txt request
* @return the inquiry lftabicms.txt response
*/
public InternalResultsResponse<Lftabicms.txt> fetchAllLftabicms.txt(InquiryLftabicms.txtRequest inquirylftabicms.txtRequest);

/**
* Fetch lftabicms.txt by id.
*
* @param inquirylftabicms.txtRequest the inquirylftabicms.txt request
* @return the internal results response
*/
public InternalResultsResponse<Lftabicms.txt> fetchLftabicms.txtById(Lftabicms.txtRequest lftabicms.txtRequest);

/**
* Generate file csv.
*
* @param inquiryLftabicms.txtRequest the inquiry lftabicms.txt request
* @return the inquiry lftabicms.txt response
*/
public InternalResponse generateFileCSV(InquiryLftabicms.txtRequest inquiryLftabicms.txtRequest);

/**
* Fetch all lftabicms.txt types.
*
* @param request the request
* @return the lftabicms.txt response
*/
public Lftabicms.txtResponse fetchAllLftabicms.txtTypes(Request request);

/**
* Fetch all lftabicms.txt filial.
*
* @param request the request
* @return the lftabicms.txt response
*/
public Lftabicms.txtResponse fetchAllLftabicms.txtFilial(Request request);

public InternalResultsResponse<Lftabicms.txt> insertLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest);
}


