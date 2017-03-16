package com.qat.samples.sysmgmt.financeiro.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;

/**
 * The Class LocationResponse.
 * 
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class BaixaTituloResponse extends InquiryResponse
{

	/** Attributes */
	private List<BaixaTitulo> baixaTituloList;

	/**
	 * The Constructor.
	 */
	public BaixaTituloResponse()
	{

	}

	
	
	public List<BaixaTitulo> getBaixaTituloList() {
		return baixaTituloList;
	}



	public void setBaixaTituloList(List<BaixaTitulo> baixaTituloList) {
		this.baixaTituloList = baixaTituloList;
	}



	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setBaixaTituloList((List<BaixaTitulo>)coll);
	}



	@Override
	public String toString() {
		return "BaixaTituloResponse [getBaixaTituloList()=" + getBaixaTituloList() + ", toString()=" + super.toString()
				+ "]";
	}


}