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
public class Advogado extends Pessoa {

	/** The horas trabalhos. */
	private List<DiasHoras> horasTrabalhos;

	/** The tempo atendimento. */
	private Long tempoAtendimento;

	/** The especialidades. */
	private List<Especialidade> especialidades;

	/** The compromissos. */
	private List<Compromisso> compromissos;

	/** The processos. */
	private List<Processo> processos;

	/**
	 * Instantiates a new advogado.
	 */
	public Advogado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new advogado.
	 *
	 * @param i the i
	 * @param string the string
	 */
	public Advogado(int i, String string) {
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
	public Advogado(String string, String string2, Object object) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the horas trabalhos.
	 *
	 * @return the horas trabalhos
	 */
	public List<DiasHoras> getHorasTrabalhos() {
		return horasTrabalhos;
	}

	/**
	 * Sets the horas trabalhos.
	 *
	 * @param horasTrabalhos the new horas trabalhos
	 */
	public void setHorasTrabalhos(List<DiasHoras> horasTrabalhos) {
		this.horasTrabalhos = horasTrabalhos;
	}

	/**
	 * Gets the tempo atendimento.
	 *
	 * @return the tempo atendimento
	 */
	public Long getTempoAtendimento() {
		return tempoAtendimento;
	}

	/**
	 * Sets the tempo atendimento.
	 *
	 * @param tempoAtendimento the new tempo atendimento
	 */
	public void setTempoAtendimento(Long tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
	}

	/**
	 * Gets the especialidades.
	 *
	 * @return the especialidades
	 */
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	/**
	 * Sets the especialidades.
	 *
	 * @param especialidades the new especialidades
	 */
	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	/**
	 * Gets the compromissos.
	 *
	 * @return the compromissos
	 */
	public List<Compromisso> getCompromissos() {
		return compromissos;
	}

	/**
	 * Sets the compromissos.
	 *
	 * @param compromissos the new compromissos
	 */
	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}

	/**
	 * Gets the processos.
	 *
	 * @return the processos
	 */
	public List<Processo> getProcessos() {
		return processos;
	}

	/**
	 * Sets the processos.
	 *
	 * @param processos the new processos
	 */
	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	/* (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.pessoa.model.Pessoa#toString()
	 */
	@Override
	public String toString() {
		return "Advogado [getHorasTrabalhos()=" + getHorasTrabalhos() + ", getTempoAtendimento()="
				+ getTempoAtendimento() + ", getEspecialidades()=" + getEspecialidades() + ", getCompromissos()="
				+ getCompromissos() + ", getProcessos()=" + getProcessos() + ", toString()=" + super.toString() + "]";
	}

}
