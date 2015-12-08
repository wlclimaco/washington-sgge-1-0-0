package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Servico;

/**
 * The Class ProdutoResponse.
 * 
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 09:39:00 AM
 */
public class ServicoResponse extends InquiryResponse
{

	/** Attributes. */
	private List<Servico> servicoList;

	public List<Servico> getServicoList()
	{
		return servicoList;
	}

	public void setServicoList(List<Servico> servicoList)
	{
		this.servicoList = servicoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setServicoList((List<Servico>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoResponse [getProdutoList()=" + getServicoList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
