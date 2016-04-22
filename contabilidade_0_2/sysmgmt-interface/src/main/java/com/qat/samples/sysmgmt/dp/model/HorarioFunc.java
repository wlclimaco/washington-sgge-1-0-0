package com.qat.samples.sysmgmt.dp.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

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
	private Long data;

	/** The description. */
	private Long horarioEntr;

	/** The horario sair. */
	private Long horarioSair;

	/** The tipo. */
	private String tipo;

	/**
	 * Default constructor.
	 */
	public HorarioFunc()
	{
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
	public HorarioFunc(Integer id, Long data, Long horarioEntr, Long horarioSair, String tipo,
			PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.data = data;
		this.horarioEntr = horarioEntr;
		this.horarioSair = horarioSair;
		this.tipo = tipo;
		setModelAction(modelAction);
	}

	public HorarioFunc(int i, String string) {
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

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * @return the horarioEntr
	 */
	public Long getHorarioEntr()
	{
		return horarioEntr;
	}

	/**
	 * @param horarioEntr the horarioEntr to set
	 */
	public void setHorarioEntr(Long horarioEntr)
	{
		this.horarioEntr = horarioEntr;
	}

	/**
	 * @return the horarioSair
	 */
	public Long getHorarioSair()
	{
		return horarioSair;
	}

	/**
	 * @param horarioSair the horarioSair to set
	 */
	public void setHorarioSair(Long horarioSair)
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

	@Override
	public String toString()
	{
		return "HorarioFunc [getId()=" + getId() + ", getData()=" + getData() + ", getHorarioEntr()="
				+ getHorarioEntr() + ", getHorarioSair()=" + getHorarioSair() + ", getTipo()=" + getTipo()
				+ ", toString()=" + super.toString() + "]";
	}

}
