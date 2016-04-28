package com.qat.samples.sysmgmt.dicionario;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Field extends ModelCosmeDamiao
{

	private Integer id;
	private String tipo;
	private Integer tamanho;
	private Boolean requerid;
	private Boolean primaryKey;
	private Boolean forenkey;
	private Boolean model;
	private Boolean xml;

	public Field()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Field(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Boolean getRequerid() {
		return requerid;
	}

	public void setRequerid(Boolean requerid) {
		this.requerid = requerid;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getForenkey() {
		return forenkey;
	}

	public void setForenkey(Boolean forenkey) {
		this.forenkey = forenkey;
	}

	public Boolean getModel() {
		return model;
	}

	public void setModel(Boolean model) {
		this.model = model;
	}

	public Boolean getXml() {
		return xml;
	}

	public void setXml(Boolean xml) {
		this.xml = xml;
	}

	@Override
	public String toString() {
		return "Field [getId()=" + getId() + ", getTipo()=" + getTipo() + ", getTamanho()=" + getTamanho()
				+ ", getRequerid()=" + getRequerid() + ", getPrimaryKey()=" + getPrimaryKey() + ", getForenkey()="
				+ getForenkey() + ", getModel()=" + getModel() + ", getXml()=" + getXml() + ", toString()="
				+ super.toString() + "]";
	}

}
