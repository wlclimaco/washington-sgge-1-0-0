package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Bundle", propOrder = {"id", "code", "description", "price"})
public class Produto extends QATModelOL
{

	/** The id. */
	private String id;

	/** The code. */
	private CadastroTypeEnum marca;

	/** The menu. */
	private CadastroTypeEnum menu;

	/** The submenu. */
	private CadastroTypeEnum submenu;

	/** The trimenu. */
	private CadastroTypeEnum trimenu;

	/** The unimed. */
	private CadastroTypeEnum unimed;

	/** The description. */
	private String nome;

	/** The price. */
	private String descricao;

	/** The usuario. */
	private String foto;

	/** The senha. */
	private List<Tabelapreco> precos;

	/**
	 * Instantiates a new bundle.
	 */
	public Produto()
	{

	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Gets the marca.
	 * 
	 * @return the marca
	 */
	public CadastroTypeEnum getMarca()
	{
		return marca;
	}

	/**
	 * Sets the marca.
	 * 
	 * @param marca the marca to set
	 */
	public void setMarca(CadastroTypeEnum marca)
	{
		this.marca = marca;
	}

	/**
	 * Gets the menu.
	 * 
	 * @return the menu
	 */
	public CadastroTypeEnum getMenu()
	{
		return menu;
	}

	/**
	 * Sets the menu.
	 * 
	 * @param menu the menu to set
	 */
	public void setMenu(CadastroTypeEnum menu)
	{
		this.menu = menu;
	}

	/**
	 * Gets the submenu.
	 * 
	 * @return the submenu
	 */
	public CadastroTypeEnum getSubmenu()
	{
		return submenu;
	}

	/**
	 * Sets the submenu.
	 * 
	 * @param submenu the submenu to set
	 */
	public void setSubmenu(CadastroTypeEnum submenu)
	{
		this.submenu = submenu;
	}

	/**
	 * Gets the trimenu.
	 * 
	 * @return the trimenu
	 */
	public CadastroTypeEnum getTrimenu()
	{
		return trimenu;
	}

	/**
	 * Sets the trimenu.
	 * 
	 * @param trimenu the trimenu to set
	 */
	public void setTrimenu(CadastroTypeEnum trimenu)
	{
		this.trimenu = trimenu;
	}

	/**
	 * Gets the unimed.
	 * 
	 * @return the unimed
	 */
	public CadastroTypeEnum getUnimed()
	{
		return unimed;
	}

	/**
	 * Sets the unimed.
	 * 
	 * @param unimed the unimed to set
	 */
	public void setUnimed(CadastroTypeEnum unimed)
	{
		this.unimed = unimed;
	}

	/**
	 * Gets the nome.
	 * 
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the descricao.
	 * 
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * Sets the descricao.
	 * 
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	/**
	 * Gets the foto.
	 * 
	 * @return the foto
	 */
	public String getFoto()
	{
		return foto;
	}

	/**
	 * Sets the foto.
	 * 
	 * @param foto the foto to set
	 */
	public void setFoto(String foto)
	{
		this.foto = foto;
	}

	/**
	 * Gets the precos.
	 * 
	 * @return the precos
	 */
	public List<Tabelapreco> getPrecos()
	{
		return precos;
	}

	/**
	 * Sets the precos.
	 * 
	 * @param precos the precos to set
	 */
	public void setPrecos(List<Tabelapreco> precos)
	{
		this.precos = precos;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Produto [getId()=" + getId() + ", getMarca()=" + getMarca() + ", getMenu()=" + getMenu()
				+ ", getSubmenu()=" + getSubmenu() + ", getTrimenu()=" + getTrimenu() + ", getUnimed()=" + getUnimed()
				+ ", getNome()=" + getNome() + ", getDescricao()=" + getDescricao() + ", getFoto()=" + getFoto()
				+ ", getPrecos()=" + getPrecos() + ", toString()=" + super.toString() + "]";
	}

}
