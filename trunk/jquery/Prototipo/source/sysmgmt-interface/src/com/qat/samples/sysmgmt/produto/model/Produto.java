package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.supermercado.model.Supermercado;
import com.qat.samples.sysmgmt.util.Imagem;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Produto", propOrder = {"id", "codBarra", "marca", "menu", "unimed",
		"nome",
		"descricao",
		"foto",
		"precos",
		"imagens"})
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

	private List<Imagem> imagens;

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
		this.unimed = unimed;
		this.precos = precos;
	}

	/**
	 * Instantiates a new produto.
	 * 
	 * @param id the id
	 * @param supermercadoid the supermercadoid
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
	public Produto(Integer id, Supermercado supermercadoid, String codBarra, Cadastro marca, Cadastro menu,
			Cadastro submenu, Cadastro trimenu, Cadastro unimed, String nome, String descricao, String foto,
			List<Tabelapreco> precos)
	{
		super();
		this.id = id;
		this.codBarra = codBarra;
		this.marca = marca;
		this.menu = menu;
		this.unimed = unimed;
		this.nome = nome;
		this.descricao = descricao;
		this.foto = foto;
		this.precos = precos;
	}

	/**
	 * Gets the supermercadoid.
	 * 
	 * @return the supermercadoid
	 */

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

	public List<Imagem> getImagens()
	{
		return imagens;
	}

	public void setImagens(List<Imagem> imagens)
	{
		this.imagens = imagens;
	}

	@Override
	public String toString()
	{
		return "Produto [getId()=" + getId() + ", getCodBarra()=" + getCodBarra() + ", getMarca()=" + getMarca()
				+ ", getMenu()=" + getMenu() + ", getUnimed()=" + getUnimed() + ", getNome()=" + getNome()
				+ ", getDescricao()=" + getDescricao() + ", getFoto()=" + getFoto() + ", getPrecos()=" + getPrecos()
				+ ", getImagens()=" + getImagens() + ", toString()=" + super.toString() + "]";
	}

}
