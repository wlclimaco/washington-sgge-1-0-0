package com.qat.samples.sysmgmt.advocacia;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.clinica.model.Especialidade;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.DiasHoras;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Advogados extends Pessoa {

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
		setNome(string);
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
