package com.qat.samples.sysmgmt.util.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class DiasHoras extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	/** The dias semanas. */
	private DoisValores diasSemanas;

	/** The hora inicio. */
	private Long horaInicio;

	/** The hora final. */
	private Long horaFinal;

	/** The diario. */
	private Integer diario;

	/** The semanal. */
	private Integer semanal;

	/** The quinzenal. */
	private Integer quinzenal;

	/** The mensal. */
	private Integer mensal;

	/** The anual. */
	private Integer anual;

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

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the dias semanas.
	 *
	 * @return the dias semanas
	 */
	public DoisValores getDiasSemanas() {
		return diasSemanas;
	}

	/**
	 * Sets the dias semanas.
	 *
	 * @param diasSemanas
	 *            the new dias semanas
	 */
	public void setDiasSemanas(DoisValores diasSemanas) {
		this.diasSemanas = diasSemanas;
	}

	/**
	 * Gets the hora inicio.
	 *
	 * @return the hora inicio
	 */
	public Long getHoraInicio() {
		return horaInicio;
	}

	/**
	 * Sets the hora inicio.
	 *
	 * @param horaInicio
	 *            the new hora inicio
	 */
	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * Gets the hora final.
	 *
	 * @return the hora final
	 */
	public Long getHoraFinal() {
		return horaFinal;
	}

	/**
	 * Sets the hora final.
	 *
	 * @param horaFinal
	 *            the new hora final
	 */
	public void setHoraFinal(Long horaFinal) {
		this.horaFinal = horaFinal;
	}

	/**
	 * Gets the diario.
	 *
	 * @return the diario
	 */
	public Integer getDiario() {
		return diario;
	}

	/**
	 * Sets the diario.
	 *
	 * @param diario
	 *            the new diario
	 */
	public void setDiario(Integer diario) {
		this.diario = diario;
	}

	/**
	 * Gets the semanal.
	 *
	 * @return the semanal
	 */
	public Integer getSemanal() {
		return semanal;
	}

	/**
	 * Sets the semanal.
	 *
	 * @param semanal
	 *            the new semanal
	 */
	public void setSemanal(Integer semanal) {
		this.semanal = semanal;
	}

	/**
	 * Gets the quinzenal.
	 *
	 * @return the quinzenal
	 */
	public Integer getQuinzenal() {
		return quinzenal;
	}

	/**
	 * Sets the quinzenal.
	 *
	 * @param quinzenal
	 *            the new quinzenal
	 */
	public void setQuinzenal(Integer quinzenal) {
		this.quinzenal = quinzenal;
	}

	/**
	 * Gets the mensal.
	 *
	 * @return the mensal
	 */
	public Integer getMensal() {
		return mensal;
	}

	/**
	 * Sets the mensal.
	 *
	 * @param mensal
	 *            the new mensal
	 */
	public void setMensal(Integer mensal) {
		this.mensal = mensal;
	}

	/**
	 * Gets the anual.
	 *
	 * @return the anual
	 */
	public Integer getAnual() {
		return anual;
	}

	/**
	 * Sets the anual.
	 *
	 * @param anual
	 *            the new anual
	 */
	public void setAnual(Integer anual) {
		this.anual = anual;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "DiasHoras [getId()=" + getId() + ", getDiasSemanas()=" + getDiasSemanas() + ", getHoraInicio()="
				+ getHoraInicio() + ", getHoraFinal()=" + getHoraFinal() + ", getDiario()=" + getDiario()
				+ ", getSemanal()=" + getSemanal() + ", getQuinzenal()=" + getQuinzenal() + ", getMensal()="
				+ getMensal() + ", getAnual()=" + getAnual() + ", toString()=" + super.toString() + "]";
	}

}
