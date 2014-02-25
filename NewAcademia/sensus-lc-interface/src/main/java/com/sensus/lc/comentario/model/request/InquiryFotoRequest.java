package com.sensus.lc.comentario.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.foto.model.Foto;

/**
 * The Class InquiryFotoRequest.
 * 
 * @author Washington
 */
public class InquiryFotoRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The fotos. */
	private List<Foto> fotos;

	/**
	 * Instantiates a new inquiry foto request.
	 */
	public InquiryFotoRequest()
	{

	}

	/**
	 * Instantiates a new inquiry foto request.
	 * 
	 * @param foto the foto
	 */
	public InquiryFotoRequest(Foto foto)
	{
		addFoto(foto);
	}

	/**
	 * Gets the fotos.
	 * 
	 * @return the fotos
	 */
	public List<Foto> getFotos()
	{
		return fotos;
	}

	/**
	 * Sets the fotos.
	 * 
	 * @param fotos the new fotos
	 */
	public void setFotos(List<Foto> fotos)
	{
		this.fotos = fotos;
	}

	/**
	 * Gets the first foto.
	 * 
	 * @return the first foto
	 */
	public Foto getFirstFoto()
	{
		if ((getFotos() != null) && !getFotos().isEmpty())
		{
			return getFotos().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the foto.
	 * 
	 * @param foto the foto
	 */
	public void addFoto(Foto foto)
	{
		if (getFotos() == null)
		{
			setFotos(new ArrayList<Foto>());
		}

		getFotos().add(foto);
	}

	@Override
	public String toString()
	{
		return "InquiryFotoRequest [getFotos()=" + getFotos() + ", getFirstFoto()=" + getFirstFoto() + ", toString()="
				+ super.toString() + "]";
	}

}
