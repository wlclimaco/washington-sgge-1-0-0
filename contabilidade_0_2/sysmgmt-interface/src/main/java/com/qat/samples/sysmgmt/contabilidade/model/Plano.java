package com.qat.samples.sysmgmt.contabilidade.model;

import java.util.List;

import com.qat.samples.sysmgmt.produto.model.PlanoByServico;
import com.qat.samples.sysmgmt.produto.model.TabPreco;
import com.qat.samples.sysmgmt.util.model.Imagem;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Plano extends ModelCosmeDamiao
{

	private Integer id;

	private Long dataInicio;

	private Long dataFinal;

	private List<TabPreco> preco;

	private Integer numeroContrato;

	private List<PlanoByServico> servicos;

	private List<Imagem> imagens;

	private String descricao;

	private String titulo;

	public Plano(Integer id)
	{
		super();
	}

	/**
	 * The Constructor.
	 */
	public Plano()
	{

	}

	public Plano(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dataInicio
	 */
	public Long getDataInicio()
	{
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Long dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFinal
	 */
	public Long getDataFinal()
	{
		return dataFinal;
	}

	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(Long dataFinal)
	{
		this.dataFinal = dataFinal;
	}

	/**
	 * @return the numeroContrato
	 */
	public Integer getNumeroContrato()
	{
		return numeroContrato;
	}

	/**
	 * @param numeroContrato the numeroContrato to set
	 */
	public void setNumeroContrato(Integer numeroContrato)
	{
		this.numeroContrato = numeroContrato;
	}

	public List<PlanoByServico> getServicos()
	{
		return servicos;
	}

	public void setServicos(List<PlanoByServico> servicos)
	{
		this.servicos = servicos;
	}

	/**
	 * @return the preco
	 */
	public List<TabPreco> getPreco()
	{
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(List<TabPreco> preco)
	{
		this.preco = preco;
	}

	public List<Imagem> getImagens()
	{
		return imagens;
	}

	public void setImagens(List<Imagem> imagens)
	{
		this.imagens = imagens;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Plano [getDataInicio()=" + getDataInicio() + ", getDataFinal()=" + getDataFinal()
				+ ", getNumeroContrato()=" + getNumeroContrato() + ", getServicos()=" + getServicos() + ", getPreco()="
				+ getPreco() + ", getImagens()=" + getImagens() + ", getDescricao()=" + getDescricao()
				+ ", getTitulo()=" + getTitulo() + ", getId()=" + getId() + ", toString()=" + super.toString() + "]";
	}

}