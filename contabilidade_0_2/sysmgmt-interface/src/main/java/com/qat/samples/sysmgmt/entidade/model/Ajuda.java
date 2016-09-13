package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Ajuda extends ModelCosmeDamiao
{


	private Integer id;

	private String titulo;
    private Integer status;
    private String texto;

	public Ajuda(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Ajuda() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Ajuda [getId()=" + getId() + ", getTitulo()=" + getTitulo() + ", getStatus()=" + getStatus()
				+ ", getTexto()=" + getTexto() + ", toString()=" + super.toString() + "]";
	}

}
