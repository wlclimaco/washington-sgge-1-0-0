package com.qat.samples.sysmgmt.site.model;

import java.util.List;

import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Site extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private String url;

	private List<Divs> divs;
	private List<Images> images;
	private List<Links> links;
	private List<Servico> servicos;

	private List<Texts> texts;

	private QuemSomos gerente;

	public Site()
	{

	}

	public Site(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public List<Divs> getDivs()
	{
		return divs;
	}

	public void setDivs(List<Divs> divs)
	{
		this.divs = divs;
	}

	public List<Images> getImages()
	{
		return images;
	}

	public void setImages(List<Images> images)
	{
		this.images = images;
	}

	public List<Links> getLinks()
	{
		return links;
	}

	public void setLinks(List<Links> links)
	{
		this.links = links;
	}

	public List<Servico> getServicos()
	{
		return servicos;
	}

	public void setServicos(List<Servico> servicos)
	{
		this.servicos = servicos;
	}

	public List<Texts> getTexts()
	{
		return texts;
	}

	public void setTexts(List<Texts> texts)
	{
		this.texts = texts;
	}

	public QuemSomos getGerente()
	{
		return gerente;
	}

	public void setGerente(QuemSomos gerente)
	{
		this.gerente = gerente;
	}

	@Override
	public String toString()
	{
		return "Site [getId()=" + getId() + ", getNome()=" + getNome() + ", getUrl()=" + getUrl() + ", getDivs()="
				+ getDivs() + ", getImages()=" + getImages() + ", getLinks()=" + getLinks() + ", getServicos()="
				+ getServicos() + ", getTexts()=" + getTexts() + ", getGerente()=" + getGerente() + ", getParentId()="
				+ getParentId() + ", toString()=" + super.toString() + "]";
	}

}
