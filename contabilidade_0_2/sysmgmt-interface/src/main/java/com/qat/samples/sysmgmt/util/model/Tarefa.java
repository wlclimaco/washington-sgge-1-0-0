package com.qat.samples.sysmgmt.util.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Tarefa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String tarefa;

	private Double porcento;

	/**
	 * Default constructor.
	 */
	public Tarefa()
	{
		super();
	}

	public Tarefa(int i, String string) {
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTarefa()
	{
		return tarefa;
	}

	public void setTarefa(String tarefa)
	{
		this.tarefa = tarefa;
	}

	public Double getPorcento()
	{
		return porcento;
	}

	public void setPorcento(Double porcento)
	{
		this.porcento = porcento;
	}

	@Override
	public String toString()
	{
		return "Tarefa [getId()=" + getId() + ", getTarefa()=" + getTarefa() + ", getPorcento()=" + getPorcento()
				+ ", toString()=" + super.toString() + "]";
	}

}
