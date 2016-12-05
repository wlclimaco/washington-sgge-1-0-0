package com.qat.samples.sysmgmt.nfeItens.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.nfe.model.NFNota;

/**
 * The Model Object ProcedureResponse.
 */
public class NFNotaItensResponse extends InquiryResponse
{

	/** The procedures. */
	@XmlElement(nillable = true)
	private List<NFNota> notas;

	/**
	 * Gets the procedures.
	 * 
	 * @return the procedures
	 */
	public List<NFNota> getProcedures()
	{
		return notas;
	}

	/**
	 * Sets the procedures.
	 * 
	 * @param procedures the new procedures
	 */
	public void setProcedures(List<NFNota> procedures)
	{
		this.notas = procedures;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setProcedures((List<NFNota>)coll);
	}

	@Override
	public String toString() {
		return "NFNotaResponse [getProcedures()=" + getProcedures() + ", toString()=" + super.toString() + "]";
	}

	

}
