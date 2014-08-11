package com.qat.samples.sysmgmt.util;

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
@XmlType(name = "Filter", propOrder = {"filters", "cadastros", "embalagens", "unimed", "table", "views"
		, "cidade", "cliente", "produto", "supermercado"})
public class Criteria extends Util
{

	/** The nome. */
	private List<Filter> filters;

	/** The cadastros. */
	private List<Cadastro> cadastros;

	/** The embalagens. */
	private List<Embalagem> embalagens;

	/** The unimed. */
	private List<UniMed> unimed;

	private List<Cidade> cidade;

	private List<Cliente> cliente;

	private List<Produto> produto;

	private List<Supermercado> supermercado;

	/** The table. */
	private TableTypeEnum table;

	/** The views. */
	private Boolean views;

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
	public List<UniMed> getUnimed()
	{
		return unimed;
	}

	/**
	 * Sets the unimed.
	 * 
	 * @param unimed the new unimed
	 */
	public void setUnimed(List<UniMed> unimed)
	{
		this.unimed = unimed;
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

	public List<Cidade> getCidade()
	{
		return cidade;
	}

	public void setCidade(List<Cidade> cidade)
	{
		this.cidade = cidade;
	}

	public List<Cliente> getCliente()
	{
		return cliente;
	}

	public void setCliente(List<Cliente> cliente)
	{
		this.cliente = cliente;
	}

	public List<Produto> getProduto()
	{
		return produto;
	}

	public void setProduto(List<Produto> produto)
	{
		this.produto = produto;
	}

	public List<Supermercado> getSupermercado()
	{
		return supermercado;
	}

	public void setSupermercado(List<Supermercado> supermercado)
	{
		this.supermercado = supermercado;
	}

	@Override
	public String toString()
	{
		return "Criteria [getFilters()=" + getFilters() + ", getCadastros()=" + getCadastros() + ", getEmbalagens()="
				+ getEmbalagens() + ", getUnimed()=" + getUnimed() + ", getTable()=" + getTable() + ", getViews()="
				+ getViews() + ", getCidade()=" + getCidade() + ", getCliente()=" + getCliente() + ", getProduto()="
				+ getProduto() + ", getSupermercado()=" + getSupermercado() + ", toString()=" + super.toString() + "]";
	}

}
