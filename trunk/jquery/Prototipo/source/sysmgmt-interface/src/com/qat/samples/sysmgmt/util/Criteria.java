package com.qat.samples.sysmgmt.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.cliente.model.Cliente;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Embalagem;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Criteria", propOrder = {"filters", "cadastros", "embalagens", "unimeds", "table", "views"
		, "cidades", "clientes", "produtos", "supermercados",
		"produto", "cadastro", "embalagem", "supermercado", "menu"})
public class Criteria extends Util
{

	/** The nome. */
	private List<Filter> filters = new ArrayList<Filter>();

	/** The cadastros. */
	private List<Cadastro> cadastros = new ArrayList<Cadastro>();

	/** The embalagens. */
	private List<Embalagem> embalagens = new ArrayList<Embalagem>();

	/** The unimed. */
	private List<UniMed> unimeds = new ArrayList<UniMed>();

	/** The cidades. */
	private List<Cidade> cidades = new ArrayList<Cidade>();

	/** The clientes. */
	private List<Cliente> clientes = new ArrayList<Cliente>();

	/** The produtos. */
	private List<Produto> produtos = new ArrayList<Produto>();

	/** The supermercados. */
	private List<Supermercado> supermercados = new ArrayList<Supermercado>();

	/** The table. */
	private TableTypeEnum table;

	/** The views. */
	private Boolean views;

	/** The produto. */
	private Produto produto;

	/** The cadastro. */
	private Cadastro cadastro;

	private Cadastro menu;

	/** The embalagem. */
	private Embalagem embalagem;

	/** The supermercado. */
	private Supermercado supermercado;

	/**
	 * Instantiates a new imagem.
	 */
	public Criteria()
	{

	}

	/**
	 * Instantiates a new criteria.
	 * 
	 * @param filters the filters
	 */
	public Criteria(List<Filter> filters)
	{
		super();
		this.filters = filters;
	}

	/**
	 * Instantiates a new criteria.
	 * 
	 * @param cadastros the cadastros
	 * @param embalagens the embalagens
	 */
	public Criteria(List<Cadastro> cadastros, List<Embalagem> embalagens)
	{
		super();
		this.cadastros = cadastros;
		this.embalagens = embalagens;
	}

	/**
	 * Gets the nome.
	 * 
	 * @return the nome
	 */
	public List<Filter> getFilters()
	{
		return filters;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param filters the new nome
	 */
	public void setFilters(List<Filter> filters)
	{
		this.filters = filters;
	}

	/**
	 * Gets the cadastros.
	 * 
	 * @return the cadastros
	 */
	public List<Cadastro> getCadastros()
	{
		return cadastros;
	}

	/**
	 * Sets the cadastros.
	 * 
	 * @param cadastros the new cadastros
	 */
	public void setCadastros(List<Cadastro> cadastros)
	{
		this.cadastros = cadastros;
	}

	/**
	 * Gets the embalagens.
	 * 
	 * @return the embalagens
	 */
	public List<Embalagem> getEmbalagens()
	{
		return embalagens;
	}

	/**
	 * Sets the embalagens.
	 * 
	 * @param embalagens the new embalagens
	 */
	public void setEmbalagens(List<Embalagem> embalagens)
	{
		this.embalagens = embalagens;
	}

	/**
	 * Gets the unimed.
	 * 
	 * @return the unimed
	 */
	public List<UniMed> getUnimeds()
	{
		return unimeds;
	}

	/**
	 * Sets the unimed.
	 * 
	 * @param unimeds the new unimed
	 */
	public void setUnimeds(List<UniMed> unimeds)
	{
		this.unimeds = unimeds;
	}

	/**
	 * Gets the cidades.
	 * 
	 * @return the cidades
	 */
	public List<Cidade> getCidades()
	{
		return cidades;
	}

	/**
	 * Sets the cidades.
	 * 
	 * @param cidades the new cidades
	 */
	public void setCidades(List<Cidade> cidades)
	{
		this.cidades = cidades;
	}

	/**
	 * Gets the clientes.
	 * 
	 * @return the clientes
	 */
	public List<Cliente> getClientes()
	{
		return clientes;
	}

	/**
	 * Sets the clientes.
	 * 
	 * @param clientes the new clientes
	 */
	public void setClientes(List<Cliente> clientes)
	{
		this.clientes = clientes;
	}

	/**
	 * Gets the produtos.
	 * 
	 * @return the produtos
	 */
	public List<Produto> getProdutos()
	{
		return produtos;
	}

	/**
	 * Sets the produtos.
	 * 
	 * @param produtos the new produtos
	 */
	public void setProdutos(List<Produto> produtos)
	{
		this.produtos = produtos;
	}

	/**
	 * Gets the supermercados.
	 * 
	 * @return the supermercados
	 */
	public List<Supermercado> getSupermercados()
	{
		return supermercados;
	}

	/**
	 * Sets the supermercados.
	 * 
	 * @param supermercados the new supermercados
	 */
	public void setSupermercados(List<Supermercado> supermercados)
	{
		this.supermercados = supermercados;
	}

	/**
	 * Gets the table.
	 * 
	 * @return the table
	 */
	public TableTypeEnum getTable()
	{
		return table;
	}

	/**
	 * Sets the table.
	 * 
	 * @param table the new table
	 */
	public void setTable(TableTypeEnum table)
	{
		this.table = table;
	}

	/**
	 * Gets the views.
	 * 
	 * @return the views
	 */
	public Boolean getViews()
	{
		return views;
	}

	/**
	 * Sets the views.
	 * 
	 * @param views the new views
	 */
	public void setViews(Boolean views)
	{
		this.views = views;
	}

	/**
	 * Gets the produto.
	 * 
	 * @return the produto
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * Sets the produto.
	 * 
	 * @param produto the new produto
	 */
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	/**
	 * Gets the cadastro.
	 * 
	 * @return the cadastro
	 */
	public Cadastro getCadastro()
	{
		return cadastro;
	}

	/**
	 * Sets the cadastro.
	 * 
	 * @param cadastro the new cadastro
	 */
	public void setCadastro(Cadastro cadastro)
	{
		this.cadastro = cadastro;
	}

	/**
	 * Gets the embalagem.
	 * 
	 * @return the embalagem
	 */
	public Embalagem getEmbalagem()
	{
		return embalagem;
	}

	/**
	 * Sets the embalagem.
	 * 
	 * @param embalagem the new embalagem
	 */
	public void setEmbalagem(Embalagem embalagem)
	{
		this.embalagem = embalagem;
	}

	/**
	 * Gets the supermercado.
	 * 
	 * @return the supermercado
	 */
	public Supermercado getSupermercado()
	{
		return supermercado;
	}

	/**
	 * Sets the supermercado.
	 * 
	 * @param supermercado the new supermercado
	 */
	public void setSupermercado(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}

	public Cadastro getMenu()
	{
		return menu;
	}

	public void setMenu(Cadastro menu)
	{
		this.menu = menu;
	}

	@Override
	public String toString()
	{
		return "Criteria [getFilters()=" + getFilters() + ", getCadastros()=" + getCadastros() + ", getEmbalagens()="
				+ getEmbalagens() + ", getUnimeds()=" + getUnimeds() + ", getCidades()=" + getCidades()
				+ ", getClientes()=" + getClientes() + ", getProdutos()=" + getProdutos() + ", getSupermercados()="
				+ getSupermercados() + ", getTable()=" + getTable() + ", getViews()=" + getViews() + ", getProduto()="
				+ getProduto() + ", getCadastro()=" + getCadastro() + ", getEmbalagem()=" + getEmbalagem()
				+ ", getSupermercado()=" + getSupermercado() + ", getMenu()=" + getMenu() + ", toString()="
				+ super.toString() + "]";
	}

}
