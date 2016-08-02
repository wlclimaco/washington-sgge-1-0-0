package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;

public class ConfiguracaoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Configuracao> configuracaoList;

	/**
	 * The Constructor.
	 */
	public ConfiguracaoResponse()
	{

	}

	/**
	 * @return the configuracaoList
	 */
	public List<Configuracao> getConfiguracaoList()
	{
		return configuracaoList;
	}

	/**
	 * @param configuracaoList the configuracaoList to set
	 */
	public void setConfiguracaoList(List<Configuracao> configuracaoList)
	{
		this.configuracaoList = configuracaoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setConfiguracaoList((List<Configuracao>)coll);
	}

	@Override
	public String toString()
	{
		return "ConfiguracaoResponse [getConfiguracaoList()=" + getConfiguracaoList() + ", toString()=" + super.toString() + "]";
	}

}