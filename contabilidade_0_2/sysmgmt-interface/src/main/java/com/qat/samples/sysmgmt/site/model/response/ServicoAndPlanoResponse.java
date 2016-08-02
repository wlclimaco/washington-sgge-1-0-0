package com.qat.samples.sysmgmt.site.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.site.model.ServicoAndPlano;

/**
 * The Model Object ServicoAndPlanoResponse.
 */
public class ServicoAndPlanoResponse extends InquiryResponse
{

	/** The servicoAndPlanoList. */
	@XmlElement(nillable = true)
	private List<ServicoAndPlano> servicoAndPlanoList;


	public List<ServicoAndPlano> getContatoItenList() {
		return servicoAndPlanoList;
	}

	public void setContatoItenList(List<ServicoAndPlano> servicoAndPlanoList) {
		this.servicoAndPlanoList = servicoAndPlanoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setContatoItenList((List<ServicoAndPlano>)coll);
	}

	@Override
	public String toString() {
		return "ServicoAndPlanoResponse [getContatoItenList()=" + getContatoItenList() + ", toString()=" + super.toString()
				+ "]";
	}



}
