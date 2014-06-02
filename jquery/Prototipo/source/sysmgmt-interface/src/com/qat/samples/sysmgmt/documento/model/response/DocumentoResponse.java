package com.qat.samples.sysmgmt.documento.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.documento.model.Documento;

/**
 * The Model Object DocumentoResponse.
 */
public class DocumentoResponse extends InquiryResponse
{

	/** The documentos. */
	@XmlElement(nillable = true)
	private List<Documento> documentos;

	/**
	 * Gets the documentos.
	 * 
	 * @return the documentos
	 */
	public List<Documento> getDocumentos()
	{
		return documentos;
	}

	/**
	 * Sets the documentos.
	 * 
	 * @param documentos the new documentos
	 */
	public void setDocumentos(List<Documento> documentos)
	{
		this.documentos = documentos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setDocumentos((List<Documento>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "DocumentoResponse [getDocumentos()=" + getDocumentos() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
