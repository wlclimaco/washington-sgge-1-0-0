package com.qat.samples.sysmgmt.dicionario;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Classes extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private List<Field> fieldList;

	public Classes()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Classes(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

	public List<Field> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	@Override
	public String toString() {
		return "Classes [getId()=" + getId() + ", getNome()=" + getNome() + ", getFieldList()=" + getFieldList()
				+ ", toString()=" + super.toString() + "]";
	}


}
