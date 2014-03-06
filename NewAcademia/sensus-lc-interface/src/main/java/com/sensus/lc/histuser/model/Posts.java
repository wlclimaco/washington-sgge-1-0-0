package com.sensus.lc.histuser.model;

import java.util.List;

import com.sensus.lc.base.model.PrincipalClass;
import com.sensus.lc.foto.model.Foto;

// TODO: Auto-generated Javadoc
/**
 * The Class Academia.
 */
@SuppressWarnings("serial")
public class Posts extends PrincipalClass
{

	/** The academ. */
	private String texto;

	/** The lograd. */
	private List<Foto> fotos;

	/**
	 * Gets the texto.
	 * 
	 * @return the texto
	 */
	public String getTexto()
	{
		return texto;
	}

	/**
	 * Sets the texto.
	 * 
	 * @param texto the new texto
	 */
	public void setTexto(String texto)
	{
		this.texto = texto;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.model.PrincipalClass#toString()
	 */
	@Override
	public String toString()
	{
		return "Posts [getTexto()=" + getTexto() + ", getFotos()=" + getFotos() + ", toString()=" + super.toString()
				+ "]";
	}

}
