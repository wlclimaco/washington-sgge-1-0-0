package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.AuditoriaRequest;
import com.sensus.mlc.gestao.model.request.InquiryAuditoriaRequest;
import com.sensus.mlc.gestao.model.response.AuditoriaResponse;
import com.sensus.mlc.gestao.model.response.InquiryAuditoriaResponse;


/**
 * The Interface IAuditoriaBCF.
 *
 * @author Washington.Costa
 */
public interface IAuditoriaBCF
{

	/**
	 * Insert auditoria.
	 *
	 * @param auditoriaRequest the auditoria request
	 * @return the auditoria response
	 */
	public AuditoriaResponse insertAuditoria(AuditoriaRequest auditoriaRequest);

	/**
	 * Update auditoria.
	 *
	 * @param auditoriaRequest the auditoria request
	 * @return the auditoria response
	 */
	public AuditoriaResponse updateAuditoria(AuditoriaRequest auditoriaRequest);

	/**
	 * Delete auditoria.
	 *
	 * @param auditoriaRequest the auditoria request
	 * @return the auditoria response
	 */
	public AuditoriaResponse deleteAuditoria(AuditoriaRequest auditoriaRequest);

	/**
	 * Fetch all auditoria.
	 *
	 * @param inquiryAuditoriaRequest the inquiryAuditoria request
	 * @return the inquiry auditoria response
	 */
	public InquiryAuditoriaResponse fetchAllAuditoria(InquiryAuditoriaRequest inquiryAuditoriaRequest);

	/**
	 * Fetch auditoria by id.
	 *
	 * @param AuditoriaRequest the auditoria request
	 * @return the auditoria response
	 */
	public AuditoriaResponse fetchAuditoriaById(AuditoriaRequest auditoriaRequest);

}
