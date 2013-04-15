package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Municipio;


// TODO: Auto-generated Javadoc
/**
 * The Class MunicipioResponse.
 */
public class MunicipioResponse extends Response
{

    /** The parent retry. */
    private Integer parentRetry;

    /** The municipio. */
    private List<Municipio> municipio ;

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
	 * Gets the municipio.
	 *
	 * @return the municipio
	 */
	public List<Municipio> getMunicipio() {
		return municipio;
	}

	/**
	 * Sets the municipio.
	 *
	 * @param municipio the new municipio
	 */
	public void setMunicipio(List<Municipio> municipio) {
		this.municipio = municipio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MunicipioResponse [parentRetry=" + parentRetry + ", municipio="
				+ municipio + ", getParentRetry()=" + getParentRetry()
				+ ", getMunicipio()=" + getMunicipio() + "]";
	}


}
