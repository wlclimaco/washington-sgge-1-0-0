package com.qat.samples.sysmgmt.dp.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Organization represents a main {@link Business} that is signed up to use PGSi services. It owns one or more
 * {@link Location}
 */
@SuppressWarnings("serial")
public class ProfissaoPessoa extends ModelCosmeDamiao
{
	/** The number of locations this organization contains. */
	private Integer id;

	/** The flag that indicates if the payroll is centralized. */
	private Profissao profId;

	private Integer funcId;

	/** The dba name. */
	private Double renda;

	/** The list of documents this organization contains. */
	private Long dataAdmissao;

	/**
	 * The Constructor.
	 */
	public ProfissaoPessoa()
	{

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

	public Profissao getProfId()
	{
		return profId;
	}

	public void setProfId(Profissao profId)
	{
		this.profId = profId;
	}

	public Integer getFuncId()
	{
		return funcId;
	}

	public void setFuncId(Integer funcId)
	{
		this.funcId = funcId;
	}

	public Double getRenda()
	{
		return renda;
	}

	public void setRenda(Double renda)
	{
		this.renda = renda;
	}

	public Long getDataAdmissao()
	{
		return dataAdmissao;
	}

	public void setDataAdmissao(Long dataAdmissao)
	{
		this.dataAdmissao = dataAdmissao;
	}

	@Override
	public String toString()
	{
		return "ProfissaoPessoa [getId()=" + getId() + ", getProfId()=" + getProfId() + ", getFuncId()=" + getFuncId()
				+ ", getRenda()=" + getRenda() + ", getDataAdmissao()=" + getDataAdmissao() + ", toString()="
				+ super.toString() + "]";
	}

}