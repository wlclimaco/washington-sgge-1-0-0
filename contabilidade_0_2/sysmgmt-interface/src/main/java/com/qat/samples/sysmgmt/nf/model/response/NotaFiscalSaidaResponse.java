package com.qat.samples.sysmgmt.nf.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.nfe.model.NFNota;

public class NotaFiscalSaidaResponse extends InquiryResponse
{

	/** Attributes */
	private List<NFNota> nfnotaList;

	/**
	 * The Constructor.
	 */
	public NotaFiscalSaidaResponse()
	{

	}

	public List<NFNota> getNfnotaList() {
		return nfnotaList;
	}

	public void setNfnotaList(List<NFNota> nfnotaList) {
		this.nfnotaList = nfnotaList;
	}



	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setNfnotaList((List<NFNota>)coll);
	}

	@Override
	public String toString() {
		return "NotaFiscalSaidaResponse [getNfnotaList()=" + getNfnotaList() + ", toString()=" + super.toString() + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

}