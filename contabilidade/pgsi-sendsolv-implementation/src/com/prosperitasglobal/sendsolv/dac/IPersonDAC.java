package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.PersonName;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IPersonDAC covers operations for Liaison/Member/Recipient.
 */
public interface IPersonDAC
{

	/**
	 * Delete person. Common method that is called to delete any {@link Person} specialization.
	 *
	 * @param person the person
	 * @return the internal response
	 */
	public InternalResponse deletePerson(Person person);

	/* *********************
	 * Liaison operations. *
	 * *********************
	 */

	/**
	 * Insert Liaison.
	 *
	 * @param liaison the liaison
	 * @return the internal results response<Liaison>
	 */
	public InternalResultsResponse<Liaison> insertLiaison(Liaison liaison);

	/**
	 * Update Liaison.
	 *
	 * @param liaison the liaison
	 * @return the internal results response<Liaison>
	 */
	public InternalResultsResponse<Liaison> updateLiaison(Liaison liaison);

	/**
	 * Fetch liaison by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Liaison> fetchLiaisonById(FetchByIdRequest request);

	/**
	 * Fetch Liaison by request.
	 *
	 * @param request the request
	 * @return the internal results response< liaison>
	 */
	public InternalResultsResponse<Liaison> fetchLiaisonByRequest(PagedInquiryRequest request);

	/* ***********************
	 * Recipient operations. *
	 * ***********************
	 */

	/**
	 * Insert recipient.
	 *
	 * @param recipient the recipient
	 * @return the internal results response< recipient>
	 */
	public InternalResultsResponse<Recipient> insertRecipient(Recipient recipient);

	/**
	 * Update recipient.
	 *
	 * @param recipient the recipient
	 * @return the internal results response< recipient>
	 */
	public InternalResultsResponse<Recipient> updateRecipient(Recipient recipient);

	/**
	 * Fetch recipient by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Recipient> fetchRecipientById(FetchByIdRequest request);

	/**
	 * Fetch recipient by request.
	 *
	 * @param request the request
	 * @return the internal results response< recipient>
	 */
	public InternalResultsResponse<Recipient> fetchRecipientByRequest(RecipientInquiryRequest request);

	/* ******************
	 * Risk operations. *
	 * ******************
	 */

	/**
	 * Update risk.
	 *
	 * @param request the request
	 * @return the internal results response< risk>
	 */
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request);

	/* ***********************
	 * Document operations. *
	 * **********************
	 */

	/**
	 * Insert person document.
	 *
	 * @param request the request
	 * @return the internal results response< document>
	 */
	public InternalResultsResponse<Document> insertPersonDocument(DocumentMaintenanceRequest request);

	/**
	 * Update person document.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse updatePersonDocument(DocumentMaintenanceRequest request);

	/**
	 * Delete person document.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deletePersonDocument(DocumentMaintenanceRequest request);

	/**
	 * Fetch country usage by id.
	 *
	 * @param id the id
	 * @return the internal results response< country usage>
	 */
	public InternalResultsResponse<CountryUsage> fetchCountryUsageById(Integer id);

	/**
	 * Fetch document by person id.
	 *
	 * @param id the id
	 * @return the internal results response< document>
	 */
	public InternalResultsResponse<Document> fetchDocumentByPersonId(Integer id);

	/* *************************
	 * PersonName operations. *
	 * ************************
	 */

	/**
	 * Fetch person name by id.
	 *
	 * @param id the id
	 * @return the internal results response< person name>
	 */
	public InternalResultsResponse<PersonName> fetchPersonNameById(Integer id);

	/**
	 * Delete person name.
	 *
	 * @param name the name
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonName(PersonName name, InternalResultsResponse<?> response);

	/* *********************
	 * Member operations. *
	 * ********************
	 */

	/**
	 * Insert member.
	 *
	 * @param member the member
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Member> insertMember(Member member);

	/**
	 * Update member.
	 *
	 * @param member the member
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Member> updateMember(Member member);

	/**
	 * Fetch member by id.
	 *
	 * @param request the request
	 * @return the internal results response
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
	 * Apply person status.
	 *
	 * @param person the person
	 * @return the internal response
	 */
	public InternalResponse applyPersonStatus(Person person);

	/**
	 * Fetch employment info by member id.
	 *
	 * @param employmentInfo the employment info
	 * @return the internal results response< employment info>
	 */
	public InternalResultsResponse<EmploymentInfo> fetchEmploymentInfoByMemberId(EmploymentInfo employmentInfo);

	/**
	 * Fetch employment info by location id.
	 *
	 * @param employmentInfoId the employment info id.
	 * @return the internal results response< employment info>
	 */
	public InternalResultsResponse<EmploymentInfo> fetchEmploymentInfoByLocationId(Integer employmentInfoId);

	/**
	 * Apply employment info status.
	 *
	 * @param employmentInfo the employment info
	 * @return the internal response
	 */
	public InternalResponse applyEmploymentInfoStatus(EmploymentInfo employmentInfo);

	/**
	 * Apply transfer setting status.
	 *
	 * @param transferSetting the transfer setting
	 * @return the internal response
	 */
	public InternalResponse applyTransferSettingStatus(TransferSetting transferSetting);
}
