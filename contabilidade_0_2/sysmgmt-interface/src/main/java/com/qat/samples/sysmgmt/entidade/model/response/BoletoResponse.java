package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Boleto;

public class BoletoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Boleto> boletoList;

	/**
	 * The Constructor.
	 */
	public BoletoResponse()
	{

	}

	/**
	 * @return the boletoList
	 */
	public List<Boleto> getBoletoList()
	{
		return boletoList;
	}

	/**
	 * @param boletoList the boletoList to set
	 */
	public void setBoletoList(List<Boleto> boletoList)
	{
		this.boletoList = boletoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setBoletoList((List<Boleto>)coll);
	}

	@Override
	public String toString()
	{
		return "BoletoResponse [getBoletoList()=" + getBoletoList() + ", toString()=" + super.toString() + "]";
	}

}