package com.prosperitasglobal.cbof.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IContactDAC.
 */
public interface IContactDAC
{

	/**
	 * Update contact.
	 *
	 * @param contact the contact
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateContact(Contact contact, InternalResultsResponse<?> response);

	/**
	 * Insert contact.
	 *
	 * @param contact the contact
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertContact(Contact contact, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business contact.
	 *
	 * @param contact the contact
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessContact(Contact contact, InternalResultsResponse<?> response);

	/**
	 * Delete person contact.
	 *
	 * @param contact the contact
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonContact(Contact contact, InternalResultsResponse<?> response);

	/**
	 * Fetch contact by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< contact>
	 */
	public InternalResultsResponse<Contact> fetchContactByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch contact by id.
	 *
	 * @param id the id
	 * @return the internal results response< contact>
	 */
	public InternalResultsResponse<Contact> fetchContactById(Integer id);

	/**
	 * Maintain contact associations.
	 *
	 * @param contactList the contact list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainContactAssociations(List<Contact> contactList, Integer parentId, String associateStatement,
			InternalResultsResponse<?> response);
}
