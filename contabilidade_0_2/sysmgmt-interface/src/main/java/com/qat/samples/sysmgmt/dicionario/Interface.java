package com.qat.samples.sysmgmt.dicionario;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Interface extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private String local;

	private List<Classes> classesList;

	public Interface()
	{
		super();

	}



	public Interface(Integer id, String nome, String local) {
		super();
		this.id = id;
		this.nome = nome;
		this.local = local;
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public List<Classes> getClassesList() {
		return classesList;
	}

	public void setClassesList(List<Classes> classesList) {
		this.classesList = classesList;
	}

	@Override
	public String toString() {
		return "Interface [getId()=" + getId() + ", getNome()=" + getNome() + ", getLocal()=" + getLocal()
				+ ", getClassesList()=" + getClassesList() + ", toString()=" + super.toString() + "]";
	}

}
