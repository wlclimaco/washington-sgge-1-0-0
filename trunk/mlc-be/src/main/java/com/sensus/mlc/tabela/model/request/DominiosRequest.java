package com.sensus.mlc.tabela.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Dominios;

// TODO: Auto-generated Javadoc
/**
 * The Class DominiosRequest.
 */
public class DominiosRequest extends LightSelectionRequest
{

    /** The parent retry. */
    private Integer parentRetry;

    /** The dominios. */
    private Dominios  dominios;

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
	 * Gets the dominios.
	 *
	 * @return the dominios
	 */
	public Dominios getDominios() {
		return dominios;
	}

	/**
	 * Sets the dominios.
	 *
	 * @param dominios the new dominios
	 */
	public void setDominios(Dominios dominios) {
		this.dominios = dominios;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString() {
		return "DominiosRequest [parentRetry=" + parentRetry + ", dominios="
				+ dominios + ", getParentRetry()=" + getParentRetry()
				+ ", getDominios()=" + getDominios() + "]";
	}

}
