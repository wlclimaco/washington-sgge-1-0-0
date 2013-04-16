package com.sensus.mlc.auditoria.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.request.AuditoriaRequest;
import com.sensus.mlc.gestao.model.request.InquiryAuditoriaRequest;
import com.sensus.mlc.gestao.model.response.AuditoriaResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IAuditoriaDAC;
{

/**
* Update auditoria.
*
* @param auditoriaRequest the auditoria request
* @return the auditoria response
*/
public InternalResultsResponse<Auditoria> updateAuditoria(AuditoriaRequest auditoriaRequest);

/**
* Delete auditoria.
*
* @param auditoriaRequest the auditoria request
* @return the auditoria response
*/
public InternalResponse deleteAuditoria(AuditoriaRequest auditoriaRequest);

/**
* Fetch all auditoria.
*
* @param inquiryauditoriaRequest the inquiryauditoria request
* @return the inquiry auditoria response
*/
public InternalResultsResponse<Auditoria> fetchAllAuditoria(InquiryAuditoriaRequest inquiryauditoriaRequest);

/**
* Fetch auditoria by id.
*
* @param inquiryauditoriaRequest the inquiryauditoria request
* @return the internal results response
*/
public InternalResultsResponse<Auditoria> fetchAuditoriaById(AuditoriaRequest auditoriaRequest);

/**
* Generate file csv.
*
* @param inquiryAuditoriaRequest the inquiry auditoria request
* @return the inquiry auditoria response
*/
public InternalResponse generateFileCSV(InquiryAuditoriaRequest inquiryAuditoriaRequest);

/**
* Fetch all auditoria types.
*
* @param request the request
* @return the auditoria response
*/
public AuditoriaResponse fetchAllAuditoriaTypes(Request request);

/**
* Fetch all auditoria filial.
*
* @param request the request
* @return the auditoria response
*/
public AuditoriaResponse fetchAllAuditoriaFilial(Request request);

public InternalResultsResponse<Auditoria> insertAuditoria(AuditoriaRequest auditoriaRequest);
}


