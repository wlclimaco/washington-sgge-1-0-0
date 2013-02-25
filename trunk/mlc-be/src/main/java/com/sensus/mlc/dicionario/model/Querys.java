package com.sensus.mlc.dicionario.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Querys.
 */
public class Querys {


	  /** The Cod query. */
  	public Integer codQuery;

	  /** The Ds query. */
  	public String dsQuery;

	  /** The Query. */
  	public String query;

	  /** The Tipo query. */
  	public String tipoQuery;

	/**
	 * Gets the cod query.
	 *
	 * @return the cod query
	 */
	public Integer getCodQuery() {
		return codQuery;
	}

	/**
	 * Sets the cod query.
	 *
	 * @param codQuery the new cod query
	 */
	public void setCodQuery(Integer codQuery) {
		this.codQuery = codQuery;
	}

	/**
	 * Gets the ds query.
	 *
	 * @return the ds query
	 */
	public String getDsQuery() {
		return dsQuery;
	}

	/**
	 * Sets the ds query.
	 *
	 * @param dsQuery the new ds query
	 */
	public void setDsQuery(String dsQuery) {
		this.dsQuery = dsQuery;
	}

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Sets the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Gets the tipo query.
	 *
	 * @return the tipo query
	 */
	public String getTipoQuery() {
		return tipoQuery;
	}

	/**
	 * Sets the tipo query.
	 *
	 * @param tipoQuery the new tipo query
	 */
	public void setTipoQuery(String tipoQuery) {
		this.tipoQuery = tipoQuery;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Querys [codQuery=" + codQuery + ", dsQuery=" + dsQuery
				+ ", query=" + query + ", tipoQuery=" + tipoQuery
				+ ", getCodQuery()=" + getCodQuery() + ", getDsQuery()="
				+ getDsQuery() + ", getQuery()=" + getQuery()
				+ ", getTipoQuery()=" + getTipoQuery() + "]";
	}


}
