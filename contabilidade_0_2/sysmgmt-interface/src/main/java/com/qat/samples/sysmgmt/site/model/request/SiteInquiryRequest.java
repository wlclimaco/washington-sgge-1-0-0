package com.qat.samples.sysmgmt.site.model.request;

import com.qat.samples.sysmgmt.site.model.criteria.SiteCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class SiteInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
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

	@Override
	public String toString() {
		return "SiteInquiryRequest [getUrl()=" + getUrl() + ", getNome()=" + getNome() + ", toString()="
				+ super.toString() + "]";
	}



}
