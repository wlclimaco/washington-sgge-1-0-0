package com.prosperitasglobal.sendsolv.document.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentResponse;
import com.prosperitasglobal.sendsolv.model.response.DocumentTypeResponse;

/**
 * The OrganizationAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/api/document")
public class DocumentAPIController extends BaseController
{
	/** The URL mapping constants. */
	private static final String DELETE = "/delete";

	/** The Constant EDIT. */
	private static final String EDIT = "/edit";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/** The Constant FETCH_TYPE. */
	private static final String FETCH_TYPE = "/type";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DocumentAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "DocumentAPIController";

	/** The organization bai. */
	private IOrganizationBAI organizationBAI;

	/** The document type bai. */
	private IDocumentTypeBAI documentTypeBAI;

	/** The Member BAI. */
	private IMemberBAI memberBAI;

	/**
	 * Gets the member bai.
	 *
	 * @return the member bai
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Gets the document type bai.
	 *
	 * @return the document type bai
	 */
	public IDocumentTypeBAI getDocumentTypeBAI()
	{
		return documentTypeBAI;
	}

	/**
	 * Sets the document type bai.
	 *
	 * @param documentTypeBAI the document type bai
	 */
	@Resource
	public void setDocumentTypeBAI(IDocumentTypeBAI documentTypeBAI)
	{
		this.documentTypeBAI = documentTypeBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the member bai
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/**
	 * Gets the organization bai.
	 *
	 * @return the organization bai
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * Sets the organization bai.
	 *
	 * @param organizationBAI the new organization bai
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	/**
	 * Edit one document.
	 *
	 * @param documentRequest the document request
	 * @return the response
	 */
	@RequestMapping(value = EDIT, method = RequestMethod.POST)
	@ResponseBody
	public DocumentResponse edit(@RequestBody DocumentMaintenanceRequest documentRequest)
	{
		DocumentResponse documentResponse = new DocumentResponse();
		try
		{

			documentResponse = getOrganizationBAI().updateDocument(documentRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			documentResponse = null;
		}
		return documentResponse;

	}

	/**
	 * Delete one document.
	 *
	 * @param documentRequest the document request
	 * @return the response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public DocumentResponse delete(@RequestBody DocumentMaintenanceRequest documentRequest)
	{
		DocumentResponse documentResponse = new DocumentResponse();
		try
		{

			if (documentRequest.getDocument().getParentKeyValue() == BusinessTypeEnum.ORGANIZATION.getValue())
			{
				documentResponse = getOrganizationBAI().deleteDocument(documentRequest);
			}

			else if (documentRequest.getDocument().getParentKeyValue() == BusinessTypeEnum.MEMBER.getValue())
			{
				documentResponse = getMemberBAI().deleteDocument(documentRequest);
			}

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			documentResponse = null;
		}
		return documentResponse;

	}

	/**
	 * Insert one Document.
	 *
	 * @param documentRequest the document request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public DocumentResponse insert(@RequestBody DocumentMaintenanceRequest documentRequest)
	{
		DocumentResponse documentResponse = new DocumentResponse();
		try
		{

			if (documentRequest.getDocument().getParentKeyValue() == BusinessTypeEnum.ORGANIZATION.getValue())
			{
				documentResponse = getOrganizationBAI().insertDocument(documentRequest);
			}

			else if (documentRequest.getDocument().getParentKeyValue() == BusinessTypeEnum.MEMBER.getValue())
			{
				documentResponse = getMemberBAI().insertDocument(documentRequest);
			}

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			documentResponse = null;
		}
		return documentResponse;
	}

	/**
	 * Fetch document type.
	 *
	 * @param documentTypeRequest the document type request
	 * @return the document type response
	 */
	@RequestMapping(value = FETCH_TYPE, method = RequestMethod.POST)
	@ResponseBody
	public DocumentTypeResponse fetchDocumentType(@RequestBody DocumentTypeRequest documentTypeRequest)
	{

		DocumentTypeResponse documentTypeResponse = new DocumentTypeResponse();

		try
		{

			documentTypeResponse = documentTypeBAI.fetchDocumentTypeByRequest(documentTypeRequest);
		}

		catch (Exception e)
		{
			documentTypeResponse = null;
		}

		return documentTypeResponse;
	}

}
