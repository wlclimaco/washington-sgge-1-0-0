package com.qat.samples.sysmgmt.convenio;

import java.util.List;

import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.condpag.TipoPag;
import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Convenio extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private String nome;

	private Long dataini;

	private Long dataFin;

	private Double porcentagem;

	private Double valor;

	private List<CondPag> listCondPag;

	private List<TipoPag> listTipoPag;

	/**
	 * The Constructor.
	 */
	public Convenio()
	{

	}

	public Convenio(Integer id)
	{
		super();
		this.id = id;
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
	 * @param id the id
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

	/**
	 * @return the dataini
	 */
	public Long getDataini()
	{
		return dataini;
	}

	/**
	 * @param dataini the dataini to set
	 */
	public void setDataini(Long dataini)
	{
		this.dataini = dataini;
	}

	/**
	 * @return the dataFin
	 */
	public Long getDataFin()
	{
		return dataFin;
	}

	/**
	 * @param dataFin the dataFin to set
	 */
	public void setDataFin(Long dataFin)
	{
		this.dataFin = dataFin;
	}

	/**
	 * @return the porcentagem
	 */
	public Double getPorcentagem()
	{
		return porcentagem;
	}

	/**
	 * @param porcentagem the porcentagem to set
	 */
	public void setPorcentagem(Double porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	/**
	 * @return the valor
	 */
	public Double getValor()
	{
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	/**
	 * @return the listCondPag
	 */
	public List<CondPag> getListCondPag()
	{
		return listCondPag;
	}

	/**
	 * @param listCondPag the listCondPag to set
	 */
	public void setListCondPag(List<CondPag> listCondPag)
	{
		this.listCondPag = listCondPag;
	}

	/**
	 * @return the listTipoPag
	 */
	public List<TipoPag> getListTipoPag()
	{
		return listTipoPag;
	}

	/**
	 * @param listTipoPag the listTipoPag to set
	 */
	public void setListTipoPag(List<TipoPag> listTipoPag)
	{
		this.listTipoPag = listTipoPag;
	}

	@Override
	public String toString()
	{
		return "Convenio [getId()=" + getId() + ", getNome()=" + getNome() + ", getDataini()=" + getDataini()
				+ ", getDataFin()=" + getDataFin() + ", getPorcentagem()=" + getPorcentagem() + ", getValor()="
				+ getValor() + ", getListCondPag()=" + getListCondPag() + ", getListTipoPag()=" + getListTipoPag()
				+ ", toString()=" + super.toString() + "]";
	}

}