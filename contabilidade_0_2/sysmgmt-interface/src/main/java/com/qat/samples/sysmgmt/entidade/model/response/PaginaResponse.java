package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Pagina;

public class PaginaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Pagina> paginaList;

	/**
	 * The Constructor.
	 */
	public PaginaResponse()
	{

	}

	/**
	 * @return the paginaList
	 */
	public List<Pagina> getPaginaList()
	{
		return paginaList;
	}

	/**
	 * @param paginaList the paginaList to set
	 */
	public void setPaginaList(List<Pagina> paginaList)
	{
		this.paginaList = paginaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPaginaList((List<Pagina>)coll);
	}

	@Override
	public String toString()
	{
		return "PaginaResponse [getPaginaList()=" + getPaginaList() + ", toString()=" + super.toString() + "]";
	}

}