package com.sensus.mlc.tabela.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.tabela.model.Dominios;


// TODO: Auto-generated Javadoc
/**
 * The Class DominiosResponse.
 */
public class DominiosResponse extends Response
{

    /** The parent retry. */
    private Integer parentRetry;

    /** The dominioss. */
    private List<Dominios> dominioss ;

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
	 * Gets the dominioss.
	 *
	 * @return the dominioss
	 */
	public List<Dominios> getDominioss() {
		return dominioss;
	}

	/**
	 * Sets the dominioss.
	 *
	 * @param dominioss the new dominioss
	 */
	public void setDominioss(List<Dominios> dominioss) {
		this.dominioss = dominioss;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DominiosResponse [parentRetry=" + parentRetry + ", dominioss="
				+ dominioss + ", getParentRetry()=" + getParentRetry()
				+ ", getDominioss()=" + getDominioss() + "]";
	}


}
