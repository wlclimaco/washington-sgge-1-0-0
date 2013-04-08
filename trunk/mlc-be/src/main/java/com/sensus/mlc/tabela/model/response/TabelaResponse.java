package com.sensus.mlc.tabela.model.response;

import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.tabela.model.Tabela;

// TODO: Auto-generated Javadoc
/**
 * The Class TabelaResponse.
 */
public class TabelaResponse extends Response {

	/** The parent retry. */
	private Integer parentRetry;

	/** The tabelas. */
	private List<Tabela> tabelas;

	/**
	 * Gets the parent retry.
	 *
	 * @return the parent retry
	 */
	public Integer getParentRetry() {
		return parentRetry;
	}

	/**
	 * Sets the parent retry.
	 *
	 * @param parentRetry the new parent retry
	 */
	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	/**
	 * Gets the tabelas.
	 *
	 * @return the tabelas
	 */
	public List<Tabela> getTabelas() {
		return tabelas;
	}

	/**
	 * Sets the tabelas.
	 *
	 * @param tabelas the new tabelas
	 */
	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TabelaResponse [parentRetry=" + parentRetry + ", tabelas="
				+ tabelas + ", getParentRetry()=" + getParentRetry()
				+ ", getTabelas()=" + getTabelas() + "]";
	}

}