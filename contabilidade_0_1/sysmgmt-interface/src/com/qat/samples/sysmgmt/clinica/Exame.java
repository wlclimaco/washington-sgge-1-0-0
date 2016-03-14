package com.qat.samples.sysmgmt.clinica;

import java.util.List;

import com.qat.samples.sysmgmt.arquivo.Arquivo;
import com.qat.samples.sysmgmt.util.Imagem;
import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Exame extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	private Integer dataExame;

	private String medicoResponsavel;

	private String laboratorio;

	private List<Arquivo> arquivoList;

	private List<Imagem> imagemList;

	public Exame()
	{

	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Arquivo> getArquivoList()
	{
		return arquivoList;
	}

	public void setArquivoList(List<Arquivo> arquivoList)
	{
		this.arquivoList = arquivoList;
	}

	public List<Imagem> getImagemList()
	{
		return imagemList;
	}

	public void setImagemList(List<Imagem> imagemList)
	{
		this.imagemList = imagemList;
	}

	public Integer getDataExame()
	{
		return dataExame;
	}

	public void setDataExame(Integer dataExame)
	{
		this.dataExame = dataExame;
	}

	public String getMedicoResponsavel()
	{
		return medicoResponsavel;
	}

	public void setMedicoResponsavel(String medicoResponsavel)
	{
		this.medicoResponsavel = medicoResponsavel;
	}

	public String getLaboratorio()
	{
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio)
	{
		this.laboratorio = laboratorio;
	}

	@Override
	public String toString()
	{
		return "Exame [getId()=" + getId() + ", getNome()=" + getNome() + ", getArquivoList()=" + getArquivoList()
				+ ", getImagemList()=" + getImagemList() + ", getDataExame()=" + getDataExame()
				+ ", getMedicoResponsavel()=" + getMedicoResponsavel() + ", getLaboratorio()=" + getLaboratorio()
				+ ", toString()=" + super.toString() + "]";
	}

}
