package com.qat.samples.sysmgmt.advocacia;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.clinica.model.Especialidade;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.util.model.DiasHoras;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Advogado extends Pessoa
{
	private List<DiasHoras> horasTrabalho;
	private Long tempoAtendimento;
	private List<Especialidade> especialidade;
	public Advogado()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Advogado(int i, String string) {
		setId(i);
		setNome(string);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public Advogado(String string, String string2, Object object) {
		// TODO Auto-generated constructor stub
	}

	public List<DiasHoras> getHorasTrabalho() {
		return horasTrabalho;
	}

	public void setHorasTrabalho(List<DiasHoras> horasTrabalho) {
		this.horasTrabalho = horasTrabalho;
	}

	public Long getTempoAtendimento() {
		return tempoAtendimento;
	}

	public void setTempoAtendimento(Long tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
	}

	public List<Especialidade> getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(List<Especialidade> especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return "Advogado [getHorasTrabalho()=" + getHorasTrabalho() + ", getTempoAtendimento()=" + getTempoAtendimento()
				+ ", getEspecialidade()=" + getEspecialidade() + ", toString()=" + super.toString() + "]";
	}


}
