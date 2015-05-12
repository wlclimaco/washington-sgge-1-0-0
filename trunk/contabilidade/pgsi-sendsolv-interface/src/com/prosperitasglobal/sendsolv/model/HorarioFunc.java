package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class HorarioFunc extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer data;

	/** The description. */
	private String horarioEntr;

	/** The horario sair. */
	private String horarioSair;

	/** The tipo. */
	private String tipo;

	/**
	 * Default constructor.
	 */
	public HorarioFunc()
	{
		super();
	}

	/**
	 * Instantiates a new horario func.
	 *
	 * @param id the id
	 * @param data the data
	 * @param horarioEntr the horario entr
	 * @param horarioSair the horario sair
	 * @param tipo the tipo
	 */
	public HorarioFunc(Integer id, Integer data, String horarioEntr, String horarioSair, String tipo)
	{
		super();
		this.id = id;
		this.data = data;
		this.horarioEntr = horarioEntr;
		this.horarioSair = horarioSair;
		this.tipo = tipo;
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

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Integer getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data to set
	 */
	public void setData(Integer data)
	{
		this.data = data;
	}

	/**
	 * Gets the horario entr.
	 *
	 * @return the horarioEntr
	 */
	public String getHorarioEntr()
	{
		return horarioEntr;
	}

	/**
	 * Sets the horario entr.
	 *
	 * @param horarioEntr the horarioEntr to set
	 */
	public void setHorarioEntr(String horarioEntr)
	{
		this.horarioEntr = horarioEntr;
	}

	/**
	 * Gets the horario sair.
	 *
	 * @return the horarioSair
	 */
	public String getHorarioSair()
	{
		return horarioSair;
	}

	/**
	 * Sets the horario sair.
	 *
	 * @param horarioSair the horarioSair to set
	 */
	public void setHorarioSair(String horarioSair)
	{
		this.horarioSair = horarioSair;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo()
	{
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "HorarioFunc [getId()=" + getId() + ", getData()=" + getData() + ", getHorarioEntr()="
				+ getHorarioEntr() + ", getHorarioSair()=" + getHorarioSair() + ", getTipo()=" + getTipo()
				+ ", toString()=" + super.toString() + "]";
	}

}
