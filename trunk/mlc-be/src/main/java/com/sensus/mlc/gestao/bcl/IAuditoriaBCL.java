package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.request.AuditoriaRequest;
import com.sensus.mlc.gestao.model.request.InquiryAuditoriaRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IAuditoriaBCL
{

	/**
	 * Insert auditoria.
	 *
	 * @param auditoriaRequest the auditoria request
	 * @return the auditoria response
	 */
	public InternalResultsResponse<Auditoria> insertAuditoria(AuditoriaRequest auditoriaRequest);

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
	 * Fetch all auditoria types.
	 *
	 * @param request the request
	 * @return the auditoria response
	 */
	public InternalResultsResponse<Auditoria> fetchAllAuditoriaTypes(InquiryAuditoriaRequest inquiryauditoriaRequest);

	/**
	 * Fetch all auditoria filial.
	 *
	 * @param request the request
	 * @return the auditoria response
	 */
	public InternalResultsResponse<Auditoria> fetchAllAuditoriaFilial(AuditoriaRequest auditoriaRequest);

}
