package com.qat.samples.sysmgmt.site.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.contato.model.ContatoItens;

/**
 * The Model Object ContatoItensResponse.
 */
public class ContatoItensResponse extends InquiryResponse
{

	/** The contatoItenList. */
	@XmlElement(nillable = true)
	private List<ContatoItens> contatoItenList;


	public List<ContatoItens> getContatoItenList() {
		return contatoItenList;
	}

	public void setContatoItenList(List<ContatoItens> contatoItenList) {
		this.contatoItenList = contatoItenList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setContatoItenList((List<ContatoItens>)coll);
	}

	@Override
	public String toString() {
		return "ContatoItensResponse [getContatoItenList()=" + getContatoItenList() + ", toString()=" + super.toString()
				+ "]";
	}



}
