package com.sensus.mlc.dicionario.model;

import java.util.List;

public class Imports {
	  /** The cod exp. */
	public Integer codImp;

	  /** The cod atrib. */
	public List<Atributos> codAtrib;

	  /** The cod query. */
	public Querys codQuery;

	  /** The posicao. */
	public Integer posicao;

	  /** The Ds export. */
	public String DsExport;



	public Integer getCodImp() {
		return codImp;
	}

	public void setCodImp(Integer codImp) {
		this.codImp = codImp;
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

	@Override
	public String toString() {
		return "Imports [codImp=" + codImp + ", codAtrib=" + codAtrib
				+ ", codQuery=" + codQuery + ", posicao=" + posicao
				+ ", DsExport=" + DsExport + ", getCodImp()=" + getCodImp()
				+ ", getCodAtrib()=" + getCodAtrib() + ", getCodQuery()="
				+ getCodQuery() + ", getPosicao()=" + getPosicao()
				+ ", getDsExport()=" + getDsExport() + "]";
	}

}
