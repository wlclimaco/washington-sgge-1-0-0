package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Document;
import com.qat.framework.model.response.InquiryResponse;

/**
 * @author aporto
 * @version 1.0
 * @created 21-Aug-2014 04:11:00 AM
 */
public class DocumentResponse extends InquiryResponse
{
	/** Attributes. */
	private List<Document> documentList;

	/**
	 * The Constructor.
	 */
	public DocumentResponse()
	{
		documentList = new ArrayList<Document>();
	}

	/**
	 * Gets the document list.
	 *
	 * @return the document list
	 */
	public List<Document> getDocumentList()
	{
		return documentList;
	}

	/**
	 * Sets the document list.
	 *
	 * @param documentList the document list
	 */
	public void setDocumentList(List<Document> documentList)
	{
		this.documentList = documentList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setDocumentList((List<Document>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DocumentResponse [getDocumentList()=" + getDocumentList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}
