package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Categoria;

public class CategoriaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Categoria> categoriaList;

	/**
	 * The Constructor.
	 */
	public CategoriaResponse()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCategoriaList((List<Categoria>)coll);
	}

	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}

	@Override
	public String toString() {
		return "CategoriaResponse [getCategoriaList()=" + getCategoriaList() + ", toString()=" + super.toString() + "]";
	}


}