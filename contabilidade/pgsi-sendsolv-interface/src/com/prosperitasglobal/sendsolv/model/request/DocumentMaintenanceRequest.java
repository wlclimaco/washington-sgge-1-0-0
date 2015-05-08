package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Document;
import com.qat.framework.model.request.MaintenanceRequest;

/**
 * @author aporto
 * @version 1.0
 * @created 21-Aug-2014 04:00:00 AM
 */
public class DocumentMaintenanceRequest extends MaintenanceRequest
{
	/** Attributes. */
	private Document document;

	/**
	 * The Constructor.
	 */
	public DocumentMaintenanceRequest()
	{
	}

	/**
	 * Gets the document.
	 *
	 * @return the document
	 */
	public Document getDocument()
	{
		return document;
	}

	/**
	 * Sets the document.
	 *
	 * @param document the document
	 */
	public void setDocument(Document document)
	{
		this.document = document;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DocumentMaintenanceRequest [getDocument()=" + getDocument() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}
