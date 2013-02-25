package com.sensus.mlc.filial.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.almoxarifado.model.Almoxarifado;
import com.sensus.mlc.endereco.model.Endereco;


// TODO: Auto-generated Javadoc
/**
 * * The Model Object Action.
 *
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class Filial extends SensusModel
{

	/** The codemp. */
	private Integer codemp;

	/** The codfilial. */
	private Integer codfilial;

	/** The razemp. */
	private String razfilial;

	/** The nomeemp. */
	private String nomefilial;

	/** The cnpjemp. */
	private String mzfilial;

	/** The inscemp. */
	private String cnpjfilial;

	/** The emdemp. */
	private String inscfilial;

	/** The endfilial. */
	private Endereco codend;

	/** The coddistfilial. */
	private String coddistfilial;

	/** The percpisfilial. */
	private Float percpisfilial;

	/** The perccofinsfilial. */
	private Float perccofinsfilial;

	/** The percirfilial. */
	private Float percirfilial;

	/** The perccsocialfilial. */
	private Float perccsocialfilial;

	/** The codeanemp. */
	private String simplesfilial;

	/** The percsimplesfilial. */
	private Float percsimplesfilial;

	/** The codmunic. */
	private String codmunic;

	/** The multialmoxemp. */
	private String siglauf;

	/** The codpais. */
	private Integer codpais;

	/** The codempuc. */
	private Integer codempuc;

	/** The codfilialuc. */
	private Integer codfilialuc;

	/** The codunifcod. */
	private Integer codunifcod;

	/** The inscmunfilial. */
	private String inscmunfilial;

	/** The cnaefilial. */
	private String cnaefilial;

	/** The percissfilial. */
	private Float percissfilial;

	/** The contribipifilial. */
	private String contribipifilial;

	/** The fotoemp. */
	private String timbrefilial;

	/** The codmunic. */
	private String perfilfilial;

	/** The siglauf. */
	private String indativfilial;

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

	/** The codempco. */
	private Integer codempco;

	/** The codfilialco. */
	private Integer codfilialco;

	/** The codfor. */
	private Integer codfor;

	/** The suframa. */
	private Integer suframa;

	/** The sede matriz. */
	private Boolean sedeMatriz;

	/** The list almoxarifados. */
	private List<Almoxarifado> listAlmoxarifados;

	/**
	 * Instantiates a new action.
	 */
	public Filial()
	{
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
	 * Gets the codfilial.
	 *
	 * @return the codfilial
	 */
	public Integer getCodfilial()
	{
		return codfilial;
	}

	/**
	 * Sets the codfilial.
	 *
	 * @param codfilial the codfilial to set
	 */
	public void setCodfilial(Integer codfilial)
	{
		this.codfilial = codfilial;
	}

	/**
	 * Gets the razfilial.
	 *
	 * @return the razfilial
	 */
	public String getRazfilial()
	{
		return razfilial;
	}

	/**
	 * Sets the razfilial.
	 *
	 * @param razfilial the razfilial to set
	 */
	public void setRazfilial(String razfilial)
	{
		this.razfilial = razfilial;
	}

	/**
	 * Gets the nomefilial.
	 *
	 * @return the nomefilial
	 */
	public String getNomefilial()
	{
		return nomefilial;
	}

	/**
	 * Sets the nomefilial.
	 *
	 * @param nomefilial the nomefilial to set
	 */
	public void setNomefilial(String nomefilial)
	{
		this.nomefilial = nomefilial;
	}

	/**
	 * Gets the mzfilial.
	 *
	 * @return the mzfilial
	 */
	public String getMzfilial()
	{
		return mzfilial;
	}

	/**
	 * Sets the mzfilial.
	 *
	 * @param mzfilial the mzfilial to set
	 */
	public void setMzfilial(String mzfilial)
	{
		this.mzfilial = mzfilial;
	}

	/**
	 * Gets the cnpjfilial.
	 *
	 * @return the cnpjfilial
	 */
	public String getCnpjfilial()
	{
		return cnpjfilial;
	}

	/**
	 * Sets the cnpjfilial.
	 *
	 * @param cnpjfilial the cnpjfilial to set
	 */
	public void setCnpjfilial(String cnpjfilial)
	{
		this.cnpjfilial = cnpjfilial;
	}

	/**
	 * Gets the inscfilial.
	 *
	 * @return the inscfilial
	 */
	public String getInscfilial()
	{
		return inscfilial;
	}

	/**
	 * Sets the inscfilial.
	 *
	 * @param inscfilial the inscfilial to set
	 */
	public void setInscfilial(String inscfilial)
	{
		this.inscfilial = inscfilial;
	}


	public Endereco getCodend() {
		return codend;
	}


	public void setCodend(Endereco codend) {
		this.codend = codend;
	}


	/**
	 * Gets the coddistfilial.
	 *
	 * @return the coddistfilial
	 */
	public String getCoddistfilial() {
		return coddistfilial;
	}


	/**
	 * Sets the coddistfilial.
	 *
	 * @param coddistfilial the new coddistfilial
	 */
	public void setCoddistfilial(String coddistfilial) {
		this.coddistfilial = coddistfilial;
	}


	/**
	 * Gets the percpisfilial.
	 *
	 * @return the percpisfilial
	 */
	public Float getPercpisfilial() {
		return percpisfilial;
	}


	/**
	 * Sets the percpisfilial.
	 *
	 * @param percpisfilial the new percpisfilial
	 */
	public void setPercpisfilial(Float percpisfilial) {
		this.percpisfilial = percpisfilial;
	}


	/**
	 * Gets the perccofinsfilial.
	 *
	 * @return the perccofinsfilial
	 */
	public Float getPerccofinsfilial() {
		return perccofinsfilial;
	}


	/**
	 * Sets the perccofinsfilial.
	 *
	 * @param perccofinsfilial the new perccofinsfilial
	 */
	public void setPerccofinsfilial(Float perccofinsfilial) {
		this.perccofinsfilial = perccofinsfilial;
	}


	/**
	 * Gets the percirfilial.
	 *
	 * @return the percirfilial
	 */
	public Float getPercirfilial() {
		return percirfilial;
	}


	/**
	 * Sets the percirfilial.
	 *
	 * @param percirfilial the new percirfilial
	 */
	public void setPercirfilial(Float percirfilial) {
		this.percirfilial = percirfilial;
	}


	/**
	 * Gets the perccsocialfilial.
	 *
	 * @return the perccsocialfilial
	 */
	public Float getPerccsocialfilial() {
		return perccsocialfilial;
	}


	/**
	 * Sets the perccsocialfilial.
	 *
	 * @param perccsocialfilial the new perccsocialfilial
	 */
	public void setPerccsocialfilial(Float perccsocialfilial) {
		this.perccsocialfilial = perccsocialfilial;
	}


	/**
	 * Gets the simplesfilial.
	 *
	 * @return the simplesfilial
	 */
	public String getSimplesfilial() {
		return simplesfilial;
	}


	/**
	 * Sets the simplesfilial.
	 *
	 * @param simplesfilial the new simplesfilial
	 */
	public void setSimplesfilial(String simplesfilial) {
		this.simplesfilial = simplesfilial;
	}


	/**
	 * Gets the percsimplesfilial.
	 *
	 * @return the percsimplesfilial
	 */
	public Float getPercsimplesfilial() {
		return percsimplesfilial;
	}


	/**
	 * Sets the percsimplesfilial.
	 *
	 * @param percsimplesfilial the new percsimplesfilial
	 */
	public void setPercsimplesfilial(Float percsimplesfilial) {
		this.percsimplesfilial = percsimplesfilial;
	}


	/**
	 * Gets the codmunic.
	 *
	 * @return the codmunic
	 */
	public String getCodmunic() {
		return codmunic;
	}


	/**
	 * Sets the codmunic.
	 *
	 * @param codmunic the new codmunic
	 */
	public void setCodmunic(String codmunic) {
		this.codmunic = codmunic;
	}


	/**
	 * Gets the siglauf.
	 *
	 * @return the siglauf
	 */
	public String getSiglauf() {
		return siglauf;
	}


	/**
	 * Sets the siglauf.
	 *
	 * @param siglauf the new siglauf
	 */
	public void setSiglauf(String siglauf) {
		this.siglauf = siglauf;
	}


	/**
	 * Gets the codpais.
	 *
	 * @return the codpais
	 */
	public Integer getCodpais() {
		return codpais;
	}


	/**
	 * Sets the codpais.
	 *
	 * @param codpais the new codpais
	 */
	public void setCodpais(Integer codpais) {
		this.codpais = codpais;
	}


	/**
	 * Gets the codempuc.
	 *
	 * @return the codempuc
	 */
	public Integer getCodempuc() {
		return codempuc;
	}


	/**
	 * Sets the codempuc.
	 *
	 * @param codempuc the new codempuc
	 */
	public void setCodempuc(Integer codempuc) {
		this.codempuc = codempuc;
	}


	/**
	 * Gets the codfilialuc.
	 *
	 * @return the codfilialuc
	 */
	public Integer getCodfilialuc() {
		return codfilialuc;
	}


	/**
	 * Sets the codfilialuc.
	 *
	 * @param codfilialuc the new codfilialuc
	 */
	public void setCodfilialuc(Integer codfilialuc) {
		this.codfilialuc = codfilialuc;
	}


	/**
	 * Gets the codunifcod.
	 *
	 * @return the codunifcod
	 */
	public Integer getCodunifcod() {
		return codunifcod;
	}


	/**
	 * Sets the codunifcod.
	 *
	 * @param codunifcod the new codunifcod
	 */
	public void setCodunifcod(Integer codunifcod) {
		this.codunifcod = codunifcod;
	}


	/**
	 * Gets the inscmunfilial.
	 *
	 * @return the inscmunfilial
	 */
	public String getInscmunfilial() {
		return inscmunfilial;
	}


	/**
	 * Sets the inscmunfilial.
	 *
	 * @param inscmunfilial the new inscmunfilial
	 */
	public void setInscmunfilial(String inscmunfilial) {
		this.inscmunfilial = inscmunfilial;
	}


	/**
	 * Gets the cnaefilial.
	 *
	 * @return the cnaefilial
	 */
	public String getCnaefilial() {
		return cnaefilial;
	}


	/**
	 * Sets the cnaefilial.
	 *
	 * @param cnaefilial the new cnaefilial
	 */
	public void setCnaefilial(String cnaefilial) {
		this.cnaefilial = cnaefilial;
	}


	/**
	 * Gets the percissfilial.
	 *
	 * @return the percissfilial
	 */
	public Float getPercissfilial() {
		return percissfilial;
	}


	/**
	 * Sets the percissfilial.
	 *
	 * @param percissfilial the new percissfilial
	 */
	public void setPercissfilial(Float percissfilial) {
		this.percissfilial = percissfilial;
	}


	/**
	 * Gets the contribipifilial.
	 *
	 * @return the contribipifilial
	 */
	public String getContribipifilial() {
		return contribipifilial;
	}


	/**
	 * Sets the contribipifilial.
	 *
	 * @param contribipifilial the new contribipifilial
	 */
	public void setContribipifilial(String contribipifilial) {
		this.contribipifilial = contribipifilial;
	}


	/**
	 * Gets the timbrefilial.
	 *
	 * @return the timbrefilial
	 */
	public String getTimbrefilial() {
		return timbrefilial;
	}


	/**
	 * Sets the timbrefilial.
	 *
	 * @param timbrefilial the new timbrefilial
	 */
	public void setTimbrefilial(String timbrefilial) {
		this.timbrefilial = timbrefilial;
	}


	/**
	 * Gets the perfilfilial.
	 *
	 * @return the perfilfilial
	 */
	public String getPerfilfilial() {
		return perfilfilial;
	}


	/**
	 * Sets the perfilfilial.
	 *
	 * @param perfilfilial the new perfilfilial
	 */
	public void setPerfilfilial(String perfilfilial) {
		this.perfilfilial = perfilfilial;
	}


	/**
	 * Gets the indativfilial.
	 *
	 * @return the indativfilial
	 */
	public String getIndativfilial() {
		return indativfilial;
	}


	/**
	 * Sets the indativfilial.
	 *
	 * @param indativfilial the new indativfilial
	 */
	public void setIndativfilial(String indativfilial) {
		this.indativfilial = indativfilial;
	}


	/**
	 * Gets the dtins.
	 *
	 * @return the dtins
	 */
	public Date getDtins() {
		return dtins;
	}


	/**
	 * Sets the dtins.
	 *
	 * @param dtins the new dtins
	 */
	public void setDtins(Date dtins) {
		this.dtins = dtins;
	}


	/**
	 * Gets the hins.
	 *
	 * @return the hins
	 */
	public Date getHins() {
		return hins;
	}


	/**
	 * Sets the hins.
	 *
	 * @param hins the new hins
	 */
	public void setHins(Date hins) {
		this.hins = hins;
	}


	/**
	 * Gets the idusuins.
	 *
	 * @return the idusuins
	 */
	public Integer getIdusuins() {
		return idusuins;
	}


	/**
	 * Sets the idusuins.
	 *
	 * @param idusuins the new idusuins
	 */
	public void setIdusuins(Integer idusuins) {
		this.idusuins = idusuins;
	}


	/**
	 * Gets the dtalt.
	 *
	 * @return the dtalt
	 */
	public Date getDtalt() {
		return dtalt;
	}


	/**
	 * Sets the dtalt.
	 *
	 * @param dtalt the new dtalt
	 */
	public void setDtalt(Date dtalt) {
		this.dtalt = dtalt;
	}


	/**
	 * Gets the halt.
	 *
	 * @return the halt
	 */
	public Date getHalt() {
		return halt;
	}


	/**
	 * Sets the halt.
	 *
	 * @param halt the new halt
	 */
	public void setHalt(Date halt) {
		this.halt = halt;
	}


	/**
	 * Gets the idusualt.
	 *
	 * @return the idusualt
	 */
	public String getIdusualt() {
		return idusualt;
	}


	/**
	 * Sets the idusualt.
	 *
	 * @param idusualt the new idusualt
	 */
	public void setIdusualt(String idusualt) {
		this.idusualt = idusualt;
	}


	/**
	 * Gets the codempco.
	 *
	 * @return the codempco
	 */
	public Integer getCodempco() {
		return codempco;
	}


	/**
	 * Sets the codempco.
	 *
	 * @param codempco the new codempco
	 */
	public void setCodempco(Integer codempco) {
		this.codempco = codempco;
	}


	/**
	 * Gets the codfilialco.
	 *
	 * @return the codfilialco
	 */
	public Integer getCodfilialco() {
		return codfilialco;
	}


	/**
	 * Sets the codfilialco.
	 *
	 * @param codfilialco the new codfilialco
	 */
	public void setCodfilialco(Integer codfilialco) {
		this.codfilialco = codfilialco;
	}


	/**
	 * Gets the codfor.
	 *
	 * @return the codfor
	 */
	public Integer getCodfor() {
		return codfor;
	}


	/**
	 * Sets the codfor.
	 *
	 * @param codfor the new codfor
	 */
	public void setCodfor(Integer codfor) {
		this.codfor = codfor;
	}


	/**
	 * Gets the suframa.
	 *
	 * @return the suframa
	 */
	public Integer getSuframa() {
		return suframa;
	}


	/**
	 * Sets the suframa.
	 *
	 * @param suframa the new suframa
	 */
	public void setSuframa(Integer suframa) {
		this.suframa = suframa;
	}


	/**
	 * Gets the sede matriz.
	 *
	 * @return the sede matriz
	 */
	public Boolean getSedeMatriz() {
		return sedeMatriz;
	}


	/**
	 * Sets the sede matriz.
	 *
	 * @param sedeMatriz the new sede matriz
	 */
	public void setSedeMatriz(Boolean sedeMatriz) {
		this.sedeMatriz = sedeMatriz;
	}


	/**
	 * Gets the list almoxarifados.
	 *
	 * @return the list almoxarifados
	 */
	public List<Almoxarifado> getListAlmoxarifados() {
		return listAlmoxarifados;
	}


	/**
	 * Sets the list almoxarifados.
	 *
	 * @param listAlmoxarifados the new list almoxarifados
	 */
	public void setListAlmoxarifados(List<Almoxarifado> listAlmoxarifados) {
		this.listAlmoxarifados = listAlmoxarifados;
	}


	/**
	 * Gets the codemp.
	 *
	 * @return the codemp
	 */
	public Integer getCodemp() {
		return codemp;
	}


	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Filial [codemp=" + codemp + ", codfilial=" + codfilial
				+ ", razfilial=" + razfilial + ", nomefilial=" + nomefilial
				+ ", mzfilial=" + mzfilial + ", cnpjfilial=" + cnpjfilial
				+ ", inscfilial=" + inscfilial + ", codend=" + codend
				+ ", coddistfilial=" + coddistfilial + ", percpisfilial="
				+ percpisfilial + ", perccofinsfilial=" + perccofinsfilial
				+ ", percirfilial=" + percirfilial + ", perccsocialfilial="
				+ perccsocialfilial + ", simplesfilial=" + simplesfilial
				+ ", percsimplesfilial=" + percsimplesfilial + ", codmunic="
				+ codmunic + ", siglauf=" + siglauf + ", codpais=" + codpais
				+ ", codempuc=" + codempuc + ", codfilialuc=" + codfilialuc
				+ ", codunifcod=" + codunifcod + ", inscmunfilial="
				+ inscmunfilial + ", cnaefilial=" + cnaefilial
				+ ", percissfilial=" + percissfilial + ", contribipifilial="
				+ contribipifilial + ", timbrefilial=" + timbrefilial
				+ ", perfilfilial=" + perfilfilial + ", indativfilial="
				+ indativfilial + ", dtins=" + dtins + ", hins=" + hins
				+ ", idusuins=" + idusuins + ", dtalt=" + dtalt + ", halt="
				+ halt + ", idusualt=" + idusualt + ", codempco=" + codempco
				+ ", codfilialco=" + codfilialco + ", codfor=" + codfor
				+ ", suframa=" + suframa + ", sedeMatriz=" + sedeMatriz
				+ ", listAlmoxarifados=" + listAlmoxarifados
				+ ", getCodfilial()=" + getCodfilial() + ", getRazfilial()="
				+ getRazfilial() + ", getNomefilial()=" + getNomefilial()
				+ ", getMzfilial()=" + getMzfilial() + ", getCnpjfilial()="
				+ getCnpjfilial() + ", getInscfilial()=" + getInscfilial()
				+ ", getCodend()=" + getCodend() + ", getCoddistfilial()="
				+ getCoddistfilial() + ", getPercpisfilial()="
				+ getPercpisfilial() + ", getPerccofinsfilial()="
				+ getPerccofinsfilial() + ", getPercirfilial()="
				+ getPercirfilial() + ", getPerccsocialfilial()="
				+ getPerccsocialfilial() + ", getSimplesfilial()="
				+ getSimplesfilial() + ", getPercsimplesfilial()="
				+ getPercsimplesfilial() + ", getCodmunic()=" + getCodmunic()
				+ ", getSiglauf()=" + getSiglauf() + ", getCodpais()="
				+ getCodpais() + ", getCodempuc()=" + getCodempuc()
				+ ", getCodfilialuc()=" + getCodfilialuc()
				+ ", getCodunifcod()=" + getCodunifcod()
				+ ", getInscmunfilial()=" + getInscmunfilial()
				+ ", getCnaefilial()=" + getCnaefilial()
				+ ", getPercissfilial()=" + getPercissfilial()
				+ ", getContribipifilial()=" + getContribipifilial()
				+ ", getTimbrefilial()=" + getTimbrefilial()
				+ ", getPerfilfilial()=" + getPerfilfilial()
				+ ", getIndativfilial()=" + getIndativfilial()
				+ ", getDtins()=" + getDtins() + ", getHins()=" + getHins()
				+ ", getIdusuins()=" + getIdusuins() + ", getDtalt()="
				+ getDtalt() + ", getHalt()=" + getHalt() + ", getIdusualt()="
				+ getIdusualt() + ", getCodempco()=" + getCodempco()
				+ ", getCodfilialco()=" + getCodfilialco() + ", getCodfor()="
				+ getCodfor() + ", getSuframa()=" + getSuframa()
				+ ", getSedeMatriz()=" + getSedeMatriz()
				+ ", getListAlmoxarifados()=" + getListAlmoxarifados()
				+ ", getCodemp()=" + getCodemp() + "]";
	}



}
