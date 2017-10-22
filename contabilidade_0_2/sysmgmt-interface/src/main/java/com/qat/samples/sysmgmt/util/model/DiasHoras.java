package com.qat.samples.sysmgmt.util.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class DiasHoras extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;


	/** The hora inicio. */
	private Long horaInicio;

	/** The hora final. */
	private Long horaFinal;

	/** The dias semanas. */
	private List<DoisValores> diasSemanas;

	/**
	 * Default constructor.
	 */
	public DiasHoras() {
		super();
	}

	/**
	 * Instantiates a new dias horas.
	 *
	 * @param i
	 *            the i
	 * @param string
	 *            the string
	 */
	public DiasHoras(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Long horaFinal) {
		this.horaFinal = horaFinal;
	}

	public List<DoisValores> getDiasSemanas() {
		return diasSemanas;
	}

	public void setDiasSemanas(List<DoisValores> diasSemanas) {
		this.diasSemanas = diasSemanas;
	}

	@Override
	public String toString() {
		return "DiasHoras [getId()=" + getId() + ", getHoraInicio()=" + getHoraInicio() + ", getHoraFinal()="
				+ getHoraFinal() + ", getDiasSemanas()=" + getDiasSemanas() + ", toString()=" + super.toString() + "]";
	}

}
