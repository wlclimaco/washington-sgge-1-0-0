package com.sensus.mlc.dicionario.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Exports.
 */
public class Exports {

	  /** The cod exp. */
  	public Integer codExp;

	  /** The cod atrib. */
  	public List<Atributos> codAtrib;

	  /** The cod query. */
  	public Querys codQuery;

	  /** The posicao. */
  	public Integer posicao;

	  /** The Ds export. */
  	public String DsExport;

	/**
	 * Gets the cod exp.
	 *
	 * @return the cod exp
	 */
	public Integer getCodExp() {
		return codExp;
	}

	/**
	 * Sets the cod exp.
	 *
	 * @param codExp the new cod exp
	 */
	public void setCodExp(Integer codExp) {
		this.codExp = codExp;
	}

	/**
	 * Gets the cod atrib.
	 *
	 * @return the cod atrib
	 */
	public List<Atributos> getCodAtrib() {
		return codAtrib;
	}

	/**
	 * Sets the cod atrib.
	 *
	 * @param codAtrib the new cod atrib
	 */
	public void setCodAtrib(List<Atributos> codAtrib) {
		this.codAtrib = codAtrib;
	}

	/**
	 * Gets the cod query.
	 *
	 * @return the cod query
	 */
	public Querys getCodQuery() {
		return codQuery;
	}

	/**
	 * Sets the cod query.
	 *
	 * @param codQuery the new cod query
	 */
	public void setCodQuery(Querys codQuery) {
		this.codQuery = codQuery;
	}

	/**
	 * Gets the posicao.
	 *
	 * @return the posicao
	 */
	public Integer getPosicao() {
		return posicao;
	}

	/**
	 * Sets the posicao.
	 *
	 * @param posicao the new posicao
	 */
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	/**
	 * Gets the ds export.
	 *
	 * @return the ds export
	 */
	public String getDsExport() {
		return DsExport;
	}

	/**
	 * Sets the ds export.
	 *
	 * @param dsExport the new ds export
	 */
	public void setDsExport(String dsExport) {
		DsExport = dsExport;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exports [codExp=" + codExp + ", codAtrib=" + codAtrib
				+ ", codQuery=" + codQuery + ", posicao=" + posicao
				+ ", DsExport=" + DsExport + ", getCodExp()=" + getCodExp()
				+ ", getCodAtrib()=" + getCodAtrib() + ", getCodQuery()="
				+ getCodQuery() + ", getPosicao()=" + getPosicao()
				+ ", getDsExport()=" + getDsExport() + "]";
	}


}
