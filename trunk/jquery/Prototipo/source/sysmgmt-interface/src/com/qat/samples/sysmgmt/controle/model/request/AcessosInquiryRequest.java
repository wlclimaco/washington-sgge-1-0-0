package com.qat.samples.sysmgmt.controle.model.request;

import java.util.List;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Model Object PagedInquiryRequest.
 */
public class AcessosInquiryRequest extends InquiryRequest
{

	/** The acessos. */
	List<ControleAcess> acessos;

	/**
	 * Gets the acessos.
	 * 
	 * @return the acessos
	 */
	public List<ControleAcess> getAcessos()
	{
		return acessos;
	}

	/**
	 * Sets the acessos.
	 * 
	 * @param acessos the new acessos
	 */
	public void setAcessos(List<ControleAcess> acessos)
	{
		this.acessos = acessos;
	}

	/**
	 * Instantiates a new acessos inquiry request.
	 */
	public AcessosInquiryRequest()
	{

	}

	/**
	 * Instantiates a new acessos inquiry request.
	 * 
	 * @param acessos the acessos
	 */
	public AcessosInquiryRequest(List<ControleAcess> acessos)
	{
		super();
		this.acessos = acessos;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AcessosInquiryRequest [getAcessos()=" + getAcessos() + ", toString()=" + super.toString() + "]";
	}

}
