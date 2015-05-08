package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IMemberBAC.
 *
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 10:16:00 AM
 */
public interface IMemberBAC
{

	/**
	 * Insert member.
	 *
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Member> insertMember(MemberMaintenanceRequest request);

	/**
	 * Update member.
	 *
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Member> updateMember(MemberMaintenanceRequest request);

	/**
	 * Delete member.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteMember(MemberMaintenanceRequest request);

	/**
	 * Fetch member by id.
	 *
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Member> fetchMemberById(FetchByIdRequest request);

	/**
	 * Fetch member by request.
	 *
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Member> fetchMemberByRequest(MemberInquiryRequest request);

	/**
	 * Apply status.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse applyStatus(MemberMaintenanceRequest request);

	/**
	 * Insert document.
	 *
	 * @param request the request
	 * @return the internal results response< document>
	 */
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request);

	/**
	 * Delete document.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteDocument(DocumentMaintenanceRequest request);

}
