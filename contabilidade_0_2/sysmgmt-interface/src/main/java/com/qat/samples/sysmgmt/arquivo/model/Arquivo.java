package com.qat.samples.sysmgmt.arquivo.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
/** create by system gera-java version 1.0.0 17/09/2017 13:48 : 47*/

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class Arquivo extends ModelCosmeDamiao
{

    /** The econtabil id for the Arquivo. */
    private Integer id;

    /** The econtabil nome for the Arquivo. */
    private String nome;

    /** The econtabil local for the Arquivo. */
    private String local;

    /** The econtabil tipo for the Arquivo. */
    private String tipo;

    /** The econtabil tamanho for the Arquivo. */
    private String tamanho;



    /**
     * Default constructor.
     */
    public Arquivo()
    {
        super();
    }



	public Arquivo(int i, String string) {
		// TODO Auto-generated constructor stub
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



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getTamanho() {
		return tamanho;
	}



	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}



	@Override
	public String toString() {
		return "Arquivo [getId()=" + getId() + ", getNome()=" + getNome() + ", getLocal()=" + getLocal()
				+ ", getTipo()=" + getTipo() + ", getTamanho()=" + getTamanho() + ", toString()=" + super.toString()
				+ "]";
	}



 }
