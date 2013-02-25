package com.sensus.mlc.wui.filial.action;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

import com.sensus.common.model.Response;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.filial.bcf.IFilialBCF;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.request.FilialRequest;
import com.sensus.mlc.filial.model.response.FilialResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.wui.base.action.ActionBase;



// TODO: Auto-generated Javadoc
/**
 * Action for searching saved.
 *
 * @author Weslley Silva
 */

/**
 * @author QATEmployee
 *
 */
@SuppressWarnings("serial")
public class FilialAjaxAction extends ActionBase
{


	/** The filial bcf. */
	private  IFilialBCF filialBCF;
	/** The response. */
	private Response response;

	/** The emdemp. */
	private String endereco;

	/** The numemp. */
	private Integer numero;

	/** The complemp. */
	private String complemento;

	/** The bairemp. */
	private String bairro;

	/** The cepemp. */
	private String cep;

	/** The cidemp. */
	private String cidade;

	/** The ufemp. */
	private String uf;

	/** The dddemp. */
	private String ddd;

	/** The foneemp. */
	private String fone1;

	/** The faxemp. */
	private String fone2;

	/** The emailemp. */
	private String email;

	/** The wwwemp. */
	private String site;

	/** The codeanemp. */
	private String cel1;

	/** The nomecontemp. */
	private String cel2;

	/** The multialmoxemp. */
	private String codpais;

	/** The codmunic. */
	private String codmunic;

	/** The siglauf. */
	private String siglauf;

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
	private Integer codend;

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

	/** The nomecontemp. */
	private String nomecontemp;

	/**
	 * Valid endereco.
	 *
	 * @return the endereco
	 */
	public Endereco validEndereco()
	{
		Endereco endereco = new Endereco();

		if(!ValidationUtil.isNullOrEmpty(getEndereco())){
			endereco.setEndereco(getEndereco());
		}else{
			endereco.setEndereco("");
		}

		if(!ValidationUtil.isNull(getNumero())){
			endereco.setNumero(getNumero());
		}else{
			endereco.setNumero(0);
		}

		if(!ValidationUtil.isNullOrEmpty(getComplemento())){
			endereco.setComplemento(getComplemento());
		}else{
			endereco.setComplemento("");
		}

		if(!ValidationUtil.isNullOrEmpty(getBairro())){
			endereco.setBairro(getBairro());
		}else{
			endereco.setBairro("");
		}

		if(!ValidationUtil.isNullOrEmpty(getCep())){
			endereco.setCep(getCep());
		}else{
			endereco.setCep("");
		}


		if(!ValidationUtil.isNullOrEmpty(getUf())){
			endereco.setUf(getUf());
		}else{
			endereco.setUf("");
		}

		if(!ValidationUtil.isNullOrEmpty(getDdd())){
			endereco.setDdd(getDdd());
		}else{
			endereco.setDdd("");
		}

		if(!ValidationUtil.isNullOrEmpty(getFone1())){
			endereco.setFone1(getFone1());
		}else{
			endereco.setFone1("");
		}
		if(!ValidationUtil.isNullOrEmpty(getFone2())){
			endereco.setFone2(getFone2());
		}else{
			endereco.setFone2("");
		}

		if(!ValidationUtil.isNullOrEmpty(getEmail())){
			endereco.setEmail(getEmail());
		}else{
			endereco.setEmail("");
		}
		if(!ValidationUtil.isNullOrEmpty(getSite())){
			endereco.setSite(getSite());
		}else{
			endereco.setSite("");
		}
		if(!ValidationUtil.isNullOrEmpty(getNomecontemp())){
			endereco.setNomecontemp(getNomecontemp());
		}else{
			endereco.setNomecontemp("");
		}
		if(!ValidationUtil.isNullOrEmpty(getCodmunic())){
			endereco.setCodmunic(getCodmunic());
		}else{
			endereco.setCodmunic("");
		}
		if(!ValidationUtil.isNullOrEmpty(getCodpais())){
			endereco.setCodpais(getCodpais());
		}else{
			endereco.setCodpais("");
		}
		return endereco;
	}

	/**
	 * Valid filial.
	 *
	 * @return the filial
	 */
	public Filial validFilial()
	{
		Filial filial = new Filial();

		if(!ValidationUtil.isNull(getCodemp())){
			filial.setCodemp(getCodemp());
		}

		if(!ValidationUtil.isNull(getCodfilial())){
			filial.setCodfilial(getCodfilial());
		}

		if(!ValidationUtil.isNullOrEmpty(getRazfilial())){
			filial.setRazfilial(getRazfilial());
		}else{
			filial.setRazfilial("");
		}

		if(!ValidationUtil.isNullOrEmpty(getNomefilial())){
			filial.setNomefilial(getNomefilial());
		}else{
			filial.setNomefilial("");
		}
		if(!ValidationUtil.isNullOrEmpty(getMzfilial())){
			filial.setMzfilial(getMzfilial());
		}else{
			filial.setMzfilial("");
		}
		if(!ValidationUtil.isNullOrEmpty(getCnpjfilial())){
			filial.setCnpjfilial(getCnpjfilial());
		}else{
			filial.setCnpjfilial("");
		}
		if(!ValidationUtil.isNullOrEmpty(getInscfilial())){
			filial.setInscfilial(getInscfilial());
		}else{
			filial.setInscfilial("");
		}

		if(!ValidationUtil.isNullOrEmpty(getCoddistfilial())){
			filial.setCoddistfilial(getCoddistfilial());
		}else{
			filial.setCoddistfilial("");
		}
		if(!ValidationUtil.isNull(getPercpisfilial())){
			filial.setPercpisfilial(getPercpisfilial());
		}else{
			filial.setPercpisfilial(new Float(0));
		}

		if(!ValidationUtil.isNull(getPerccofinsfilial())){
			filial.setPerccofinsfilial(getPerccofinsfilial());
		}else{
			filial.setPerccofinsfilial(new Float(0));
		}

		if(!ValidationUtil.isNull(getPercirfilial())){
			filial.setPercirfilial(getPercirfilial());
		}else{
			filial.setPercirfilial(new Float(0));
		}

		if(!ValidationUtil.isNull(getPerccsocialfilial())){
			filial.setPerccsocialfilial(getPerccsocialfilial());
		}else{
			filial.setPerccsocialfilial(new Float(0));
		}

		if(!ValidationUtil.isNullOrEmpty(getSimplesfilial())){
			filial.setSimplesfilial(getSimplesfilial());
		}else{
			filial.setSimplesfilial("");
		}

		if(!ValidationUtil.isNull(getPercsimplesfilial())){
			filial.setPercsimplesfilial(getPercsimplesfilial());
		}else{
			filial.setPercsimplesfilial(new Float(0));
		}

		if(!ValidationUtil.isNull(getCodempuc())){
			filial.setCodempuc(getCodempuc());
		}else{
			filial.setCodempuc(0);
		}

		if(!ValidationUtil.isNull(getCodfilialuc())){
			filial.setCodfilialuc(getCodfilialuc());
		}else{
			filial.setCodfilialuc(0);
		}

		if(!ValidationUtil.isNull(getCodunifcod())){
			filial.setCodunifcod(getCodunifcod());
		}else{
			filial.setCodunifcod(0);
		}

		if(!ValidationUtil.isNullOrEmpty(getInscmunfilial())){
			filial.setInscmunfilial(getInscmunfilial());
		}else{
			filial.setInscmunfilial("");
		}
		if(!ValidationUtil.isNullOrEmpty(getCnaefilial())){
			filial.setCnaefilial(getCnaefilial());
		}else{
			filial.setCnaefilial("");
		}

		if(!ValidationUtil.isNull(getPercissfilial())){
			filial.setPercissfilial(getPercissfilial());
		}else{
			filial.setPercissfilial(new Float(0));
		}

		if(!ValidationUtil.isNullOrEmpty(getContribipifilial())){
			filial.setContribipifilial(getContribipifilial());
		}else{
			filial.setContribipifilial("");
		}
		if(!ValidationUtil.isNull(getTimbrefilial())){
			filial.setTimbrefilial(getTimbrefilial());
		}else{
			filial.setTimbrefilial("");
		}
		if(!ValidationUtil.isNullOrEmpty(getPerfilfilial())){
			filial.setPerfilfilial(getPerfilfilial());
		}else{
			filial.setPerfilfilial("");
		}

		if(!ValidationUtil.isNullOrEmpty(getIndativfilial())){
			filial.setIndativfilial(getIndativfilial());
		}else{
			filial.setIndativfilial("");
		}

		if(!ValidationUtil.isNull(getDtins())){
			filial.setDtins(getDtins());
		}
		if(!ValidationUtil.isNull(getHins())){
			filial.setHins(getHins());
		}

		if(!ValidationUtil.isNull(getIdusuins())){
			filial.setIdusuins(getIdusuins());
		}

		if(!ValidationUtil.isNull(getDtalt())){
			filial.setDtalt(getDtalt());
		}

		if(!ValidationUtil.isNull(getHalt())){
			filial.setHalt(getHalt());
		}

		if(!ValidationUtil.isNullOrEmpty(getIdusualt())){
			filial.setIdusualt(getIdusualt());
		}else{
			filial.setIdusualt("");
		}

		if(!ValidationUtil.isNull(getCodempco())){
			filial.setCodempco(getCodempco());
		}else{
			filial.setCodempco(0);
		}
		if(!ValidationUtil.isNull(getCodfilialco())){
			filial.setCodfilialco(getCodfilialco());
		}else{
			filial.setCodfilialco(0);
		}
		if(!ValidationUtil.isNull(getCodfor())){
			filial.setCodfor(getCodfor());
		}else{
			filial.setCodfor(0);
		}
		if(!ValidationUtil.isNull(getSuframa())){
			filial.setSuframa(getSuframa());
		}else{
			filial.setSuframa(0);
		}
		if(!ValidationUtil.isNull(getSedeMatriz())){
			filial.setSedeMatriz(getSedeMatriz());
		}else{
			filial.setSedeMatriz(new Boolean(false));
		}

		filial.setCodend(validEndereco());

		return filial;
	}

	/**
	 * Insert filial.
	 *
	 * @return the string
	 */
	public String insertFilial()
	{
		try
		{



		FilialRequest filialRequest = new FilialRequest();
		filialRequest.setFilial(validFilial());



		UserContext contest= new UserContext();
		contest.setUserId("rod");
		contest.setTenant(new Tenant(1));
	//	getInquiryEmpresaRequest().setUserContext(getUserContext());
	//	BERequestContextUtil.enableContext(inquiryempresaRequest);
		filialRequest.setUserContext(contest);
		FilialResponse filialResponse = getFilialBCF().insertFilial(filialRequest);
		setResponse(filialResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not search SmartPoints", ex);
			}
		}


		return SUCCESS;
	}

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 *
	 * @param response the new response
	 */
	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * Gets the endereco.
	 *
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Sets the endereco.
	 *
	 * @param endereco the new endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Gets the complemento.
	 *
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Sets the complemento.
	 *
	 * @param complemento the new complemento
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Sets the bairro.
	 *
	 * @param bairro the new bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Gets the cep.
	 *
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Sets the cep.
	 *
	 * @param cep the new cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Gets the cidade.
	 *
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Sets the cidade.
	 *
	 * @param cidade the new cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Gets the uf.
	 *
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Sets the uf.
	 *
	 * @param uf the new uf
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Gets the ddd.
	 *
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * Sets the ddd.
	 *
	 * @param ddd the new ddd
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * Gets the fone1.
	 *
	 * @return the fone1
	 */
	public String getFone1() {
		return fone1;
	}

	/**
	 * Sets the fone1.
	 *
	 * @param fone1 the new fone1
	 */
	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	/**
	 * Gets the fone2.
	 *
	 * @return the fone2
	 */
	public String getFone2() {
		return fone2;
	}

	/**
	 * Sets the fone2.
	 *
	 * @param fone2 the new fone2
	 */
	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Gets the cel1.
	 *
	 * @return the cel1
	 */
	public String getCel1() {
		return cel1;
	}

	/**
	 * Sets the cel1.
	 *
	 * @param cel1 the new cel1
	 */
	public void setCel1(String cel1) {
		this.cel1 = cel1;
	}

	/**
	 * Gets the cel2.
	 *
	 * @return the cel2
	 */
	public String getCel2() {
		return cel2;
	}

	/**
	 * Sets the cel2.
	 *
	 * @param cel2 the new cel2
	 */
	public void setCel2(String cel2) {
		this.cel2 = cel2;
	}

	/**
	 * Gets the codpais.
	 *
	 * @return the codpais
	 */
	public String getCodpais() {
		return codpais;
	}

	/**
	 * Sets the codpais.
	 *
	 * @param codpais the new codpais
	 */
	public void setCodpais(String codpais) {
		this.codpais = codpais;
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
	 * Gets the codemp.
	 *
	 * @return the codemp
	 */
	public Integer getCodemp() {
		return codemp;
	}

	/**
	 * Sets the codemp.
	 *
	 * @param codemp the new codemp
	 */
	public void setCodemp(Integer codemp) {
		this.codemp = codemp;
	}

	/**
	 * Gets the codfilial.
	 *
	 * @return the codfilial
	 */
	public Integer getCodfilial() {
		return codfilial;
	}

	/**
	 * Sets the codfilial.
	 *
	 * @param codfilial the new codfilial
	 */
	public void setCodfilial(Integer codfilial) {
		this.codfilial = codfilial;
	}

	/**
	 * Gets the razfilial.
	 *
	 * @return the razfilial
	 */
	public String getRazfilial() {
		return razfilial;
	}

	/**
	 * Sets the razfilial.
	 *
	 * @param razfilial the new razfilial
	 */
	public void setRazfilial(String razfilial) {
		this.razfilial = razfilial;
	}

	/**
	 * Gets the nomefilial.
	 *
	 * @return the nomefilial
	 */
	public String getNomefilial() {
		return nomefilial;
	}

	/**
	 * Sets the nomefilial.
	 *
	 * @param nomefilial the new nomefilial
	 */
	public void setNomefilial(String nomefilial) {
		this.nomefilial = nomefilial;
	}

	/**
	 * Gets the mzfilial.
	 *
	 * @return the mzfilial
	 */
	public String getMzfilial() {
		return mzfilial;
	}

	/**
	 * Sets the mzfilial.
	 *
	 * @param mzfilial the new mzfilial
	 */
	public void setMzfilial(String mzfilial) {
		this.mzfilial = mzfilial;
	}

	/**
	 * Gets the cnpjfilial.
	 *
	 * @return the cnpjfilial
	 */
	public String getCnpjfilial() {
		return cnpjfilial;
	}

	/**
	 * Sets the cnpjfilial.
	 *
	 * @param cnpjfilial the new cnpjfilial
	 */
	public void setCnpjfilial(String cnpjfilial) {
		this.cnpjfilial = cnpjfilial;
	}

	/**
	 * Gets the inscfilial.
	 *
	 * @return the inscfilial
	 */
	public String getInscfilial() {
		return inscfilial;
	}

	/**
	 * Sets the inscfilial.
	 *
	 * @param inscfilial the new inscfilial
	 */
	public void setInscfilial(String inscfilial) {
		this.inscfilial = inscfilial;
	}

	/**
	 * Gets the codend.
	 *
	 * @return the codend
	 */
	public Integer getCodend() {
		return codend;
	}

	/**
	 * Sets the codend.
	 *
	 * @param codend the new codend
	 */
	public void setCodend(Integer codend) {
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
	 * Gets the filial bcf.
	 *
	 * @return the filial bcf
	 */
	public IFilialBCF getFilialBCF() {
		return filialBCF;
	}

	/**
	 * Sets the filial bcf.
	 *
	 * @param filialBCF the new filial bcf
	 */
	public void setFilialBCF(IFilialBCF filialBCF) {
		this.filialBCF = filialBCF;
	}

	/**
	 * Gets the nomecontemp.
	 *
	 * @return the nomecontemp
	 */
	public String getNomecontemp() {
		return nomecontemp;
	}

	/**
	 * Sets the nomecontemp.
	 *
	 * @param nomecontemp the new nomecontemp
	 */
	public void setNomecontemp(String nomecontemp) {
		this.nomecontemp = nomecontemp;
	}

}