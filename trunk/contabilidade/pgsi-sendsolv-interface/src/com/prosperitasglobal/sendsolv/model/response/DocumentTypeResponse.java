package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.qat.framework.model.response.InquiryResponse;

/**
 * @author aporto
 * @version 1.0
 * @created 21-Aug-2014 04:11:00 AM
 */
public class DocumentTypeResponse extends InquiryResponse
{
	/** Attributes. */
	private List<DocumentType> documentTypeList;

	/**
	 * The Constructor.
	 */
	public DocumentTypeResponse()
	{
		documentTypeList = new ArrayList<DocumentType>();
	}

	/**
	 * Gets the document list.
	 *
	 * @return the document list
	 */
	public List<DocumentType> getDocumentTypeList()
	{
		return documentTypeList;
	}

	/**
	 * Sets the document list.
	 *
	 * @param documentList the document list
	 */
	public void setDocumentTypeList(List<DocumentType> documentList)
	{
		documentTypeList = documentList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setDocumentTypeList((List<DocumentType>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DocumentTypeResponse [getDocumentTypeList()=" + getDocumentTypeList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}
