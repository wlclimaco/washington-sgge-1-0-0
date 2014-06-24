package com.qat.samples.sysmgmt.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.util.Imagem;

/**
 * The Model Object ProcedureResponse.
 */
public class FotoResponse extends InquiryResponse
{

	/** The bundles. */
	@XmlElement(nillable = true)
	private List<Imagem> imagens;

	public List<Imagem> getImagens()
	{
		return imagens;
	}

	public void setImagens(List<Imagem> imagens)
	{
		this.imagens = imagens;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setImagens((List<Imagem>)coll);
	}

	@Override
	public String toString()
	{
		return "FotoResponse [getImagens()=" + getImagens() + ", toString()=" + super.toString() + "]";
	}

}
