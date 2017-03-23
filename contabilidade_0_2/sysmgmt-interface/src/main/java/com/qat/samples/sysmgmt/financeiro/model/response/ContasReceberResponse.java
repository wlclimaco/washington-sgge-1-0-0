package com.qat.samples.sysmgmt.financeiro.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;

/**
 * The Class LocationResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class ContasReceberResponse extends InquiryResponse
{

	/** Attributes */
	private List<ContasReceber> contasReceberList;

	/**
	 * The Constructor.
	 */
	public ContasReceberResponse()
	{

	}

	public List<ContasReceber> getContasReceberList() {
		return contasReceberList;
	}



	public void setContasReceberList(List<ContasReceber> contasReceberList) {
		this.contasReceberList = contasReceberList;
	}



	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setContasReceberList((List<ContasReceber>)coll);
	}

	@Override
	public String toString() {
		return "ContasReceberResponse [getContasReceberList()=" + getContasReceberList() + ", toString()="
				+ super.toString() + "]";
	}


}