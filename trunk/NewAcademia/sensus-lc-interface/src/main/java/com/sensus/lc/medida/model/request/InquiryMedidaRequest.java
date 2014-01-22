package com.sensus.lc.medida.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.medida.model.Medida;

/**
 * The Class InquiryMedidaRequest.
 * 
 * @author Washington
 */
public class InquiryMedidaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The medidas. */
	private List<Medida> medidas;

	/**
	 * Instantiates a new inquiry medida request.
	 */
	public InquiryMedidaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry medida request.
	 * 
	 * @param medida the medida
	 */
	public InquiryMedidaRequest(Medida medida)
	{
		addMedida(medida);
	}

	/**
	 * Gets the medidas.
	 * 
	 * @return the medidas
	 */
	public List<Medida> getMedidas()
	{
		return medidas;
	}

	/**
	 * Sets the medidas.
	 * 
	 * @param medidas the new medidas
	 */
	public void setMedidas(List<Medida> medidas)
	{
		this.medidas = medidas;
	}

	/**
	 * Gets the first medida.
	 * 
	 * @return the first medida
	 */
	public Medida getFirstMedida()
	{
		if ((getMedidas() != null) && !getMedidas().isEmpty())
		{
			return getMedidas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the medida.
	 * 
	 * @param medida the medida
	 */
	public void addMedida(Medida medida)
	{
		if (getMedidas() == null)
		{
			setMedidas(new ArrayList<Medida>());
		}

		getMedidas().add(medida);
	}

	@Override
	public String toString()
	{
		return "InquiryMedidaRequest [getMedidas()=" + getMedidas() + ", getFirstMedida()=" + getFirstMedida()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
