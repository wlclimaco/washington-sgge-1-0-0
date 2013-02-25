package com.sensus.mlc.dicionario.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Tipos.
 */
public class Tipos {

	  /** The cod tipo. */
  	public Integer codTipo;

	  /** The tipo. */
  	public String tipo;

	/**
	 * Gets the cod tipo.
	 *
	 * @return the cod tipo
	 */
	public Integer getCodTipo() {
		return codTipo;
	}

	/**
	 * Sets the cod tipo.
	 *
	 * @param codTipo the new cod tipo
	 */
	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tipos [codTipo=" + codTipo + ", tipo=" + tipo
				+ ", getCodTipo()=" + getCodTipo() + ", getTipo()=" + getTipo()
				+ "]";
	}



}
