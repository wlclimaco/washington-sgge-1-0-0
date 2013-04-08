package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Uf;


// TODO: Auto-generated Javadoc
/**
 * The Class UfRequest.
 */
public class UfRequest extends LightSelectionRequest
{

    /** The parent retry. */
    private Integer parentRetry;

    /** The uf. */
    private Uf  uf;

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
	 * Gets the uf.
	 *
	 * @return the uf
	 */
	public Uf getUf() {
		return uf;
	}

	/**
	 * Sets the uf.
	 *
	 * @param uf the new uf
	 */
	public void setUf(Uf uf) {
		this.uf = uf;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString() {
		return "UfRequest [parentRetry=" + parentRetry + ", uf=" + uf
				+ ", getParentRetry()=" + getParentRetry() + ", getUf()="
				+ getUf() + "]";
	}


}
