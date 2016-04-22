package com.qat.samples.sysmgmt.convenio.model;

import java.util.List;

import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.TipoPag;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Convenio extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private String nome;

	private Integer dataini;

	private Integer dataFin;

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

	public Convenio(Integer id, String nome, Integer dataini, Integer dataFin, Double porcentagem, Double valor,
			List<CondPag> listCondPag, List<TipoPag> listTipoPag, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.dataini = dataini;
		this.dataFin = dataFin;
		this.porcentagem = porcentagem;
		this.valor = valor;
		this.listCondPag = listCondPag;
		this.listTipoPag = listTipoPag;
		setModelAction(modelAction);
	}

	public Convenio(Integer id)
	{
		super();
		this.id = id;
	}

	public Convenio(int i, String string) {
		// TODO Auto-generated constructor stub
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
	public Integer getDataini()
	{
		return dataini;
	}

	/**
	 * @param dataini the dataini to set
	 */
	public void setDataini(Integer dataini)
	{
		this.dataini = dataini;
	}

	/**
	 * @return the dataFin
	 */
	public Integer getDataFin()
	{
		return dataFin;
	}

	/**
	 * @param dataFin the dataFin to set
	 */
	public void setDataFin(Integer dataFin)
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