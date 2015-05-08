package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;

/**
 * The Interface IMemberBAI.
 *
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 09:51:30 AM
 */
public interface IMemberBAI
{

	/**
	 * Insert member.
	 *
	 * @param request the request
	 * @return the member response
	 */
	public MemberResponse insertMember(MemberMaintenanceRequest request);

	/**
	 * Update member.
	 *
	 * @param request the request
	 * @return the member response
	 */
	public MemberResponse updateMember(MemberMaintenanceRequest request);

	/**
	 * Delete member.
	 *
	 * @param request the request
	 * @return the member response
	 */
	public MemberResponse deleteMember(MemberMaintenanceRequest request);

	/**
	 * Fetch member by id.
	 *
	 * @param request the request
	 * @return the member response
	 */
	public MemberResponse fetchMemberById(FetchByIdRequest request);

	/**
	 * Fetch member by request.
	 *
	 * @param request the request
	 * @return the member response
	 */
	public MemberResponse fetchMemberByRequest(MemberInquiryRequest request);

	/**
	 * Apply status.
	 *
	 * @param request the request
	 * @return the member response
	 */
	public MemberResponse applyStatus(MemberMaintenanceRequest request);

	/**
	 * Insert document.
	 *
	 * @param request the request
	 * @return the document response
	 */
	public DocumentResponse insertDocument(DocumentMaintenanceRequest request);

	/**
	 * Delete document.
	 *
	 * @param request the request
	 * @return the document response
	 */
	public DocumentResponse deleteDocument(DocumentMaintenanceRequest request);

}
