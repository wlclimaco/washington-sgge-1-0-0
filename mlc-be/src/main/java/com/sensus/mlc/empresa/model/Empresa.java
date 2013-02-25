package com.sensus.mlc.empresa.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.filial.model.Filial;


// TODO: Auto-generated Javadoc
/**
 * * The Model Object Action.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class Empresa extends SensusModel
{

	/** The codemp. */
	private Integer codemp;
	
	/** The razemp. */
	private String razemp;

	/** The nomeemp. */
	private String nomeemp;

	/** The cnpjemp. */
	private String cnpjemp;

	/** The inscemp. */
	private List<Endereco> codend;
	
	/** The inscemp. */
	private String inscemp;

	/** The emdemp. */
	private String emdemp;

	/** The numemp. */
	private Integer numemp;

	/** The complemp. */
	private String complemp;

	/** The bairemp. */
	private String bairemp;

	/** The cepemp. */
	private String cepemp;

	/** The cidemp. */
	private String cidemp;

	/** The ufemp. */
	private String ufemp;

	/** The dddemp. */
	private String dddemp;

	/** The foneemp. */
	private String foneemp;

	/** The faxemp. */
	private String faxemp;

	/** The emailemp. */
	private String emailemp;

	/** The wwwemp. */
	private String wwwemp;

	/** The codeanemp. */
	private String codeanemp;

	/** The nomecontemp. */
	private String nomecontemp;

	/** The multialmoxemp. */
	private String multialmoxemp;

	/** The fotoemp. */
	private String fotoemp;

	/** The codmunic. */
	private String codmunic;

	/** The siglauf. */
	private String siglauf;

	/** The codpais. */
	private String codpais;

	/** The percissemp. */
	private Float percissemp;

	/** The codpaisemp. */
	private String codpaisemp;

	/** The dtins. */
	private Date dtins;

	/** The hins. */
	private Date hins;

	/** The idusuins. */
	private Integer idusuins;

	/** The dtalt. */
	private Date dtalt;

	/** The halt. */
	private Date halt;

	/** The idusualt. */
	private String idusualt;
	
	/** The list filial. */
	private List<Filial> listFilial;

	/**
	 * Instantiates a new action.
	 */
	public Empresa()
	{
	}


	/**
	 * Gets the codemp.
	 * 
	 * @return the codemp
	 */
	public Integer getCodemp()
	{
		return codemp;
	}

	/**
	 * Sets the codemp.
	 * 
	 * @param codemp the codemp to set
	 */
	public void setCodemp(Integer codemp)
	{
		this.codemp = codemp;
	}

	/**
	 * Gets the razemp.
	 * 
	 * @return the razemp
	 */
	public String getRazemp()
	{
		return razemp;
	}

	/**
	 * Sets the razemp.
	 * 
	 * @param razemp the razemp to set
	 */
	public void setRazemp(String razemp)
	{
		this.razemp = razemp;
	}

	/**
	 * Gets the nomeemp.
	 * 
	 * @return the nomeemp
	 */
	public String getNomeemp()
	{
		return nomeemp;
	}

	/**
	 * Sets the nomeemp.
	 * 
	 * @param nomeemp the nomeemp to set
	 */
	public void setNomeemp(String nomeemp)
	{
		this.nomeemp = nomeemp;
	}

	/**
	 * Gets the cnpjemp.
	 * 
	 * @return the cnpjemp
	 */
	public String getCnpjemp()
	{
		return cnpjemp;
	}

	/**
	 * Sets the cnpjemp.
	 * 
	 * @param cnpjemp the cnpjemp to set
	 */
	public void setCnpjemp(String cnpjemp)
	{
		this.cnpjemp = cnpjemp;
	}

	/**
	 * Gets the inscemp.
	 * 
	 * @return the inscemp
	 */
	public String getInscemp()
	{
		return inscemp;
	}

	/**
	 * Sets the inscemp.
	 * 
	 * @param inscemp the inscemp to set
	 */
	public void setInscemp(String inscemp)
	{
		this.inscemp = inscemp;
	}

	/**
	 * Gets the emdemp.
	 * 
	 * @return the emdemp
	 */
	public String getEmdemp()
	{
		return emdemp;
	}

	/**
	 * Sets the emdemp.
	 * 
	 * @param emdemp the emdemp to set
	 */
	public void setEmdemp(String emdemp)
	{
		this.emdemp = emdemp;
	}

	/**
	 * Gets the numemp.
	 * 
	 * @return the numemp
	 */
	public Integer getNumemp()
	{
		return numemp;
	}

	/**
	 * Sets the numemp.
	 * 
	 * @param numemp the numemp to set
	 */
	public void setNumemp(Integer numemp)
	{
		this.numemp = numemp;
	}

	/**
	 * Gets the complemp.
	 * 
	 * @return the complemp
	 */
	public String getComplemp()
	{
		return complemp;
	}

	/**
	 * Sets the complemp.
	 * 
	 * @param complemp the complemp to set
	 */
	public void setComplemp(String complemp)
	{
		this.complemp = complemp;
	}

	/**
	 * Gets the bairemp.
	 * 
	 * @return the bairemp
	 */
	public String getBairemp()
	{
		return bairemp;
	}

	/**
	 * Sets the bairemp.
	 * 
	 * @param bairemp the bairemp to set
	 */
	public void setBairemp(String bairemp)
	{
		this.bairemp = bairemp;
	}

	/**
	 * Gets the cepemp.
	 * 
	 * @return the cepemp
	 */
	public String getCepemp()
	{
		return cepemp;
	}

	/**
	 * Sets the cepemp.
	 * 
	 * @param cepemp the cepemp to set
	 */
	public void setCepemp(String cepemp)
	{
		this.cepemp = cepemp;
	}

	/**
	 * Gets the cidemp.
	 * 
	 * @return the cidemp
	 */
	public String getCidemp()
	{
		return cidemp;
	}

	/**
	 * Sets the cidemp.
	 * 
	 * @param cidemp the cidemp to set
	 */
	public void setCidemp(String cidemp)
	{
		this.cidemp = cidemp;
	}

	/**
	 * Gets the ufemp.
	 * 
	 * @return the ufemp
	 */
	public String getUfemp()
	{
		return ufemp;
	}

	/**
	 * Sets the ufemp.
	 * 
	 * @param ufemp the ufemp to set
	 */
	public void setUfemp(String ufemp)
	{
		this.ufemp = ufemp;
	}

	/**
	 * Gets the dddemp.
	 * 
	 * @return the dddemp
	 */
	public String getDddemp()
	{
		return dddemp;
	}

	/**
	 * Sets the dddemp.
	 * 
	 * @param dddemp the dddemp to set
	 */
	public void setDddemp(String dddemp)
	{
		this.dddemp = dddemp;
	}

	/**
	 * Gets the foneemp.
	 * 
	 * @return the foneemp
	 */
	public String getFoneemp()
	{
		return foneemp;
	}

	/**
	 * Sets the foneemp.
	 * 
	 * @param foneemp the foneemp to set
	 */
	public void setFoneemp(String foneemp)
	{
		this.foneemp = foneemp;
	}

	/**
	 * Gets the faxemp.
	 * 
	 * @return the faxemp
	 */
	public String getFaxemp()
	{
		return faxemp;
	}

	/**
	 * Sets the faxemp.
	 * 
	 * @param faxemp the faxemp to set
	 */
	public void setFaxemp(String faxemp)
	{
		this.faxemp = faxemp;
	}

	/**
	 * Gets the emailemp.
	 * 
	 * @return the emailemp
	 */
	public String getEmailemp()
	{
		return emailemp;
	}

	/**
	 * Sets the emailemp.
	 * 
	 * @param emailemp the emailemp to set
	 */
	public void setEmailemp(String emailemp)
	{
		this.emailemp = emailemp;
	}

	/**
	 * Gets the wwwemp.
	 * 
	 * @return the wwwemp
	 */
	public String getWwwemp()
	{
		return wwwemp;
	}

	/**
	 * Sets the wwwemp.
	 * 
	 * @param wwwemp the wwwemp to set
	 */
	public void setWwwemp(String wwwemp)
	{
		this.wwwemp = wwwemp;
	}

	/**
	 * Gets the codeanemp.
	 * 
	 * @return the codeanemp
	 */
	public String getCodeanemp()
	{
		return codeanemp;
	}

	/**
	 * Sets the codeanemp.
	 * 
	 * @param codeanemp the codeanemp to set
	 */
	public void setCodeanemp(String codeanemp)
	{
		this.codeanemp = codeanemp;
	}

	/**
	 * Gets the nomecontemp.
	 * 
	 * @return the nomecontemp
	 */
	public String getNomecontemp()
	{
		return nomecontemp;
	}

	/**
	 * Sets the nomecontemp.
	 * 
	 * @param nomecontemp the nomecontemp to set
	 */
	public void setNomecontemp(String nomecontemp)
	{
		this.nomecontemp = nomecontemp;
	}

	/**
	 * Gets the multialmoxemp.
	 * 
	 * @return the multialmoxemp
	 */
	public String getMultialmoxemp()
	{
		return multialmoxemp;
	}

	/**
	 * Sets the multialmoxemp.
	 * 
	 * @param multialmoxemp the multialmoxemp to set
	 */
	public void setMultialmoxemp(String multialmoxemp)
	{
		this.multialmoxemp = multialmoxemp;
	}

	/**
	 * Gets the fotoemp.
	 * 
	 * @return the fotoemp
	 */
	public String getFotoemp()
	{
		return fotoemp;
	}

	/**
	 * Sets the fotoemp.
	 * 
	 * @param fotoemp the fotoemp to set
	 */
	public void setFotoemp(String fotoemp)
	{
		this.fotoemp = fotoemp;
	}

	/**
	 * Gets the codmunic.
	 * 
	 * @return the codmunic
	 */
	public String getCodmunic()
	{
		return codmunic;
	}

	/**
	 * Sets the codmunic.
	 * 
	 * @param codmunic the codmunic to set
	 */
	public void setCodmunic(String codmunic)
	{
		this.codmunic = codmunic;
	}

	/**
	 * Gets the siglauf.
	 * 
	 * @return the siglauf
	 */
	public String getSiglauf()
	{
		return siglauf;
	}

	/**
	 * Sets the siglauf.
	 * 
	 * @param siglauf the siglauf to set
	 */
	public void setSiglauf(String siglauf)
	{
		this.siglauf = siglauf;
	}

	/**
	 * Gets the codpais.
	 * 
	 * @return the codpais
	 */
	public String getCodpais()
	{
		return codpais;
	}

	/**
	 * Sets the codpais.
	 * 
	 * @param codpais the codpais to set
	 */
	public void setCodpais(String codpais)
	{
		this.codpais = codpais;
	}

	/**
	 * Gets the percissemp.
	 * 
	 * @return the percissemp
	 */
	public Float getPercissemp()
	{
		return percissemp;
	}

	/**
	 * Sets the percissemp.
	 * 
	 * @param percissemp the percissemp to set
	 */
	public void setPercissemp(Float percissemp)
	{
		this.percissemp = percissemp;
	}

	/**
	 * Gets the codpaisemp.
	 * 
	 * @return the codpaisemp
	 */
	public String getCodpaisemp()
	{
		return codpaisemp;
	}

	/**
	 * Sets the codpaisemp.
	 * 
	 * @param codpaisemp the codpaisemp to set
	 */
	public void setCodpaisemp(String codpaisemp)
	{
		this.codpaisemp = codpaisemp;
	}

	/**
	 * Gets the dtins.
	 * 
	 * @return the dtins
	 */
	public Date getDtins()
	{
		return dtins;
	}

	/**
	 * Sets the dtins.
	 * 
	 * @param dtins the dtins to set
	 */
	public void setDtins(Date dtins)
	{
		this.dtins = dtins;
	}

	/**
	 * Gets the hins.
	 * 
	 * @return the hins
	 */
	public Date getHins()
	{
		return hins;
	}

	/**
	 * Sets the hins.
	 * 
	 * @param hins the hins to set
	 */
	public void setHins(Date hins)
	{
		this.hins = hins;
	}

	/**
	 * Gets the idusuins.
	 * 
	 * @return the idusuins
	 */
	public Integer getIdusuins()
	{
		return idusuins;
	}

	/**
	 * Sets the idusuins.
	 * 
	 * @param idusuins the idusuins to set
	 */
	public void setIdusuins(Integer idusuins)
	{
		this.idusuins = idusuins;
	}

	/**
	 * Gets the dtalt.
	 * 
	 * @return the dtalt
	 */
	public Date getDtalt()
	{
		return dtalt;
	}

	/**
	 * Sets the dtalt.
	 * 
	 * @param dtalt the dtalt to set
	 */
	public void setDtalt(Date dtalt)
	{
		this.dtalt = dtalt;
	}

	/**
	 * Gets the halt.
	 * 
	 * @return the halt
	 */
	public Date getHalt()
	{
		return halt;
	}

	/**
	 * Sets the halt.
	 * 
	 * @param halt the halt to set
	 */
	public void setHalt(Date halt)
	{
		this.halt = halt;
	}

	/**
	 * Gets the idusualt.
	 * 
	 * @return the idusualt
	 */
	public String getIdusualt()
	{
		return idusualt;
	}

	/**
	 * Sets the idusualt.
	 * 
	 * @param idusualt the idusualt to set
	 */
	public void setIdusualt(String idusualt)
	{
		this.idusualt = idusualt;
	}


	/**
	 * Gets the list filial.
	 *
	 * @return the list filial
	 */
	public List<Filial> getListFilial() {
		return listFilial;
	}




	/**
	 * Gets the codend.
	 *
	 * @return the codend
	 */
	public List<Endereco> getCodend() {
		return codend;
	}


	/**
	 * Sets the codend.
	 *
	 * @param codend the new codend
	 */
	public void setCodend(List<Endereco> codend) {
		this.codend = codend;
	}


	/**
	 * Sets the list filial.
	 *
	 * @param listFilial the new list filial
	 */
	public void setListFilial(List<Filial> listFilial) {
		this.listFilial = listFilial;
	}


	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Empresa [getCodemp()=" + getCodemp() + ", getRazemp()="
				+ getRazemp() + ", getNomeemp()=" + getNomeemp()
				+ ", getCnpjemp()=" + getCnpjemp() + ", getInscemp()="
				+ getInscemp() + ", getEmdemp()=" + getEmdemp()
				+ ", getNumemp()=" + getNumemp() + ", getComplemp()="
				+ getComplemp() + ", getBairemp()=" + getBairemp()
				+ ", getCepemp()=" + getCepemp() + ", getCidemp()="
				+ getCidemp() + ", getUfemp()=" + getUfemp() + ", getDddemp()="
				+ getDddemp() + ", getFoneemp()=" + getFoneemp()
				+ ", getFaxemp()=" + getFaxemp() + ", getEmailemp()="
				+ getEmailemp() + ", getWwwemp()=" + getWwwemp()
				+ ", getCodeanemp()=" + getCodeanemp() + ", getNomecontemp()="
				+ getNomecontemp() + ", getMultialmoxemp()="
				+ getMultialmoxemp() + ", getFotoemp()=" + getFotoemp()
				+ ", getCodmunic()=" + getCodmunic() + ", getSiglauf()="
				+ getSiglauf() + ", getCodpais()=" + getCodpais()
				+ ", getPercissemp()=" + getPercissemp() + ", getCodpaisemp()="
				+ getCodpaisemp() + ", getDtins()=" + getDtins()
				+ ", getHins()=" + getHins() + ", getIdusuins()="
				+ getIdusuins() + ", getDtalt()=" + getDtalt() + ", getHalt()="
				+ getHalt() + ", getIdusualt()=" + getIdusualt()
				+ ", getListFilial()=" + getListFilial() + ", getCodend()="
				+ getCodend() + "]";
	}


	




}
