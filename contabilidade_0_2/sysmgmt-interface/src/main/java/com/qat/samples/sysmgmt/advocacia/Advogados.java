package com.qat.samples.sysmgmt.advocacia;

import java.util.Date;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Advogados extends ModelCosmeDamiao {

	private Integer id;
	/** The horas trabalhos. */
	private Advogado advogado;

	/** The tempo atendimento. */
	private Integer advogadoPrincipal;



	/**
	 * Instantiates a new advogado.
	 */
	public Advogados() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new advogado.
	 *
	 * @param i the i
	 * @param string the string
	 */
	public Advogados(int i, String string) {
		setId(i);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	/**
	 * Instantiates a new advogado.
	 *
	 * @param string the string
	 * @param string2 the string 2
	 * @param object the object
	 */
	public Advogados(String string, String string2, Object object) {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public Integer getAdvogadoPrincipal() {
		return advogadoPrincipal;
	}

	public void setAdvogadoPrincipal(Integer advogadoPrincipal) {
		this.advogadoPrincipal = advogadoPrincipal;
	}

	@Override
	public String toString() {
		return "Advogados [getAdvogado()=" + getAdvogado() + ", getAdvogadoPrincipal()=" + getAdvogadoPrincipal()
				+ ", toString()=" + super.toString() + "]";
	}


}
