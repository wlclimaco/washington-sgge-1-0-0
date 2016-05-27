package com.qat.samples.sysmgmt.site.model.criteria;

import java.io.Serializable;

import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class SiteCriteria implements Serializable
{

	private String url;
	private String nome;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * The Constructor.
	 */
	public SiteCriteria()
	{
		super();
	}

	@Override
	public String toString() {
		return "SiteCriteria [getUrl()=" + getUrl() + ", getNome()=" + getNome() + ", toString()=" + super.toString()
				+ "]";
	}



}
