package com.sensus.mlc.tabela.model.request;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Tabela;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryTagRequest.
 *
 * @author - Washington
 */

public class TabelaRequest extends LightSelectionRequest
{

    /** The parent retry. */
    private Integer parentRetry;

    /** The tabela. */
    private Tabela  tabela;

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
	 * Gets the tabela.
	 *
	 * @return the tabela
	 */
	public Tabela getTabela() {
		return tabela;
	}

	/**
	 * Sets the tabela.
	 *
	 * @param tabela the new tabela
	 */
	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString() {
		return "TabelaRequest [parentRetry=" + parentRetry + ", tabela="
				+ tabela + ", getParentRetry()=" + getParentRetry()
				+ ", getTabela()=" + getTabela() + "]";
	}


}
