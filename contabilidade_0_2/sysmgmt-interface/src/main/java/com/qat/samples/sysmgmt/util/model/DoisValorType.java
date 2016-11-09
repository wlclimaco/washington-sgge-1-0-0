package com.qat.samples.sysmgmt.util.model;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class DoisValorType extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String tipo;

	/** The description. */
	private String descricao;

	/**
	 * Default constructor.
	 */
	public DoisValorType()
	{
		super();
	}

	public DoisValorType(int i, String string) {
		// TODO Auto-generated constructor stub
	}


	public DoisValorType(Integer id) {
		super();
		this.id = id;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "DoisValorType [getId()=" + getId() + ", getTipo()=" + getTipo() + ", getDescricao()=" + getDescricao()
				+ ", toString()=" + super.toString() + "]";
	}
}
