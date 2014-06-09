package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Produto", propOrder = {"id", "codBarra", "marca", "menu", "submenu", "unimed", "trimenu", "nome",
		"descricao",
		"foto",
		"precos"})
public class Produto extends Util
{

	/** The id. */
	private Integer id;

	/** The cod barra. */
	private String codBarra;

	/** The code. */
	private Cadastro marca;

	/** The menu. */
	private Cadastro menu;

	/** The submenu. */
	private Cadastro submenu;

	/** The trimenu. */
	private Cadastro trimenu;

	/** The unimed. */
	private Cadastro unimed;

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
	 * Instantiates a new produto.
	 * 
	 * @param id the id
	 * @param codBarra the cod barra
	 * @param marca the marca
	 * @param menu the menu
	 * @param submenu the submenu
	 * @param trimenu the trimenu
	 * @param unimed the unimed
	 * @param nome the nome
	 * @param descricao the descricao
	 * @param foto the foto
	 * @param precos the precos
	 */
	public Produto(Integer id, String codBarra, Cadastro marca, Cadastro menu, Cadastro submenu, Cadastro trimenu,
			Cadastro unimed, String nome, String descricao, String foto, List<Tabelapreco> precos)
	{
		super();
		this.id = id;
		this.codBarra = codBarra;
		this.marca = marca;
		this.menu = menu;
		this.submenu = submenu;
		this.trimenu = trimenu;
		this.unimed = unimed;
		this.nome = nome;
		this.descricao = descricao;
		this.foto = foto;
		this.precos = precos;
	}

	/**
	 * Instantiates a new produto.
	 * 
	 * @param id the id
	 * @param codBarra the cod barra
	 * @param marca the marca
	 * @param menu the menu
	 * @param submenu the submenu
	 * @param trimenu the trimenu
	 * @param unimed the unimed
	 * @param precos the precos
	 */
	public Produto(Integer id, String codBarra, Cadastro marca, Cadastro menu, Cadastro submenu, Cadastro trimenu,
			Cadastro unimed, List<Tabelapreco> precos)
	{
		super();
		this.id = id;
		this.codBarra = codBarra;
		this.marca = marca;
		this.menu = menu;
		this.submenu = submenu;
		this.trimenu = trimenu;
		this.unimed = unimed;
		this.precos = precos;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the cod barra.
	 * 
	 * @return the cod barra
	 */
	public String getCodBarra()
	{
		return codBarra;
	}

	/**
	 * Sets the cod barra.
	 * 
	 * @param codBarra the new cod barra
	 */
	public void setCodBarra(String codBarra)
	{
		this.codBarra = codBarra;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public Cadastro getMarca()
	{
		return marca;
	}

	/**
	 * Sets the code.
	 * 
	 * @param marca the new code
	 */
	public void setMarca(Cadastro marca)
	{
		this.marca = marca;
	}

	/**
	 * Gets the menu.
	 * 
	 * @return the menu
	 */
	public Cadastro getMenu()
	{
		return menu;
	}

	/**
	 * Sets the menu.
	 * 
	 * @param menu the new menu
	 */
	public void setMenu(Cadastro menu)
	{
		this.menu = menu;
	}

	/**
	 * Gets the submenu.
	 * 
	 * @return the submenu
	 */
	public Cadastro getSubmenu()
	{
		return submenu;
	}

	/**
	 * Sets the submenu.
	 * 
	 * @param submenu the new submenu
	 */
	public void setSubmenu(Cadastro submenu)
	{
		this.submenu = submenu;
	}

	/**
	 * Gets the trimenu.
	 * 
	 * @return the trimenu
	 */
	public Cadastro getTrimenu()
	{
		return trimenu;
	}

	/**
	 * Sets the trimenu.
	 * 
	 * @param trimenu the new trimenu
	 */
	public void setTrimenu(Cadastro trimenu)
	{
		this.trimenu = trimenu;
	}

	/**
	 * Gets the unimed.
	 * 
	 * @return the unimed
	 */
	public Cadastro getUnimed()
	{
		return unimed;
	}

	/**
	 * Sets the unimed.
	 * 
	 * @param unimed the new unimed
	 */
	public void setUnimed(Cadastro unimed)
	{
		this.unimed = unimed;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the description.
	 * 
	 * @param nome the new description
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * Sets the price.
	 * 
	 * @param descricao the new price
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	/**
	 * Gets the usuario.
	 * 
	 * @return the usuario
	 */
	public String getFoto()
	{
		return foto;
	}

	/**
	 * Sets the usuario.
	 * 
	 * @param foto the new usuario
	 */
	public void setFoto(String foto)
	{
		this.foto = foto;
	}

	/**
	 * Gets the senha.
	 * 
	 * @return the senha
	 */
	public List<Tabelapreco> getPrecos()
	{
		return precos;
	}

	/**
	 * Sets the senha.
	 * 
	 * @param precos the new senha
	 */
	public void setPrecos(List<Tabelapreco> precos)
	{
		this.precos = precos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#toString()
	 */
	@Override
	public String toString()
	{
		return "Produto [getId()=" + getId() + ", getCodBarra()=" + getCodBarra() + ", getMarca()=" + getMarca()
				+ ", getMenu()=" + getMenu() + ", getSubmenu()=" + getSubmenu() + ", getTrimenu()=" + getTrimenu()
				+ ", getUnimed()=" + getUnimed() + ", getNome()=" + getNome() + ", getDescricao()=" + getDescricao()
				+ ", getFoto()=" + getFoto() + ", getPrecos()=" + getPrecos() + ", toString()=" + super.toString()
				+ "]";
	}

}
