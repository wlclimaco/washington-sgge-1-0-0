package com.sensus.mlc.wui.empresa.action;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.empresa.bcf.IEmpresaBCF;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.util.ResultUtil;


/**
 * The Class SmartpointPageAction.
 * 
 * @author Phelipe Momesso
 */
@SuppressWarnings("serial")
public class EmpresaPageAction extends LayoutBase
{

	/** ************************ CONSTANTS ***********************. */

	/** The key for the default "loading error" droplist prompt. */
	public static final String DEFAULT_ERROR_KEY = "widgets.combobox.errorprompt2";

	/** The default "empty" value for dropLists. */
	private static final String DEFAULT_VALUE = "0";

	/** The Constant GROUP_LIST_ERROR. */
	private static final String GROUP_LIST_ERROR = "Error loading Group List";

	/** The logger for this class. */
	private static final Log LOG = LogFactory.getLog(EmpresaPageAction.class);

	/** The Constant TAG_LIST_ERROR. */
	private static final String TAG_LIST_ERROR = "Error loading Tag List";

	/** ************************ PROPERTIES ***********************. */

	private IEmpresaBCF empresaBCF;
	
	/** The codemp. */
	private Integer codemp;
	
	private Integer id;

	/** The razemp. */
	private String razemp;

	/** The nomeemp. */
	private String nomeemp;

	/** The cnpjemp. */
	private String cnpjemp;

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



	public String editEmpresa()
	{
		try
		{
			if (!ValidationUtil.isNull(getId()))
			{
				EmpresaRequest empresaRequest = new EmpresaRequest();

				// ADD user context to request
//				BERequestContextUtil.enableContext(empresaRequest);

				List<Empresa> empresas = new ArrayList<Empresa>();

				Empresa empresa = new Empresa();
				empresa.setCodemp(getCodemp());
				empresa.setModelAction(PersistanceActionEnum.FETCHBYID);
				empresas.add(empresa);
				empresaRequest.setEmpresa(empresa);
				EmpresaResponse empresaResponse = getEmpresaBCF().fetchEmpresaById(empresaRequest);

					empresas = empresaResponse.getEmpresa();

					if (!(ValidationUtil.isNull(empresas)) && (empresas.size() > 0))
					{
						setCodemp(empresas.get(0).getCodemp());
						setRazemp(empresas.get(0).getRazemp());
						setNomeemp(empresas.get(0).getNomeemp());
						setCnpjemp(empresas.get(0).getCnpjemp());
						setInscemp(empresas.get(0).getInscemp());
						setEmdemp(empresas.get(0).getEmdemp());
						setNumemp(empresas.get(0).getNumemp());
						setComplemp(empresas.get(0).getCepemp());
						setBairemp(empresas.get(0).getBairemp());
						setCepemp(empresas.get(0).getCepemp());
						setCidemp(empresas.get(0).getCidemp());
						setUfemp(empresas.get(0).getUfemp());
						setDddemp(empresas.get(0).getDddemp());
						setFoneemp(empresas.get(0).getFoneemp());
						setFaxemp(empresas.get(0).getFaxemp());
						setEmailemp(empresas.get(0).getEmailemp());
						setWwwemp(empresas.get(0).getWwwemp());
						setCodeanemp(empresas.get(0).getCodeanemp());
						setNomecontemp(empresas.get(0).getNomecontemp());
						setMultialmoxemp(empresas.get(0).getMultialmoxemp());
						setFotoemp(empresas.get(0).getFotoemp());
						setCodmunic(empresas.get(0).getCodmunic());
						setSiglauf(empresas.get(0).getSiglauf());
						setCodpais(empresas.get(0).getCodpais());
						setPercissemp(empresas.get(0).getPercissemp());
						setCodpaisemp(empresas.get(0).getCodpaisemp());

					}

				}
		}
		catch (Exception e)
		{
			LOG.error("Error searching for group", e);
		}
		return SUCCESS;
	}



	public Integer getCodemp() {
		return codemp;
	}



	public void setCodemp(Integer codemp) {
		this.codemp = codemp;
	}



	public String getRazemp() {
		return razemp;
	}



	public void setRazemp(String razemp) {
		this.razemp = razemp;
	}



	public String getNomeemp() {
		return nomeemp;
	}



	public void setNomeemp(String nomeemp) {
		this.nomeemp = nomeemp;
	}



	public String getCnpjemp() {
		return cnpjemp;
	}



	public void setCnpjemp(String cnpjemp) {
		this.cnpjemp = cnpjemp;
	}



	public String getInscemp() {
		return inscemp;
	}



	public void setInscemp(String inscemp) {
		this.inscemp = inscemp;
	}



	public String getEmdemp() {
		return emdemp;
	}



	public void setEmdemp(String emdemp) {
		this.emdemp = emdemp;
	}



	public Integer getNumemp() {
		return numemp;
	}



	public void setNumemp(Integer numemp) {
		this.numemp = numemp;
	}



	public String getComplemp() {
		return complemp;
	}



	public void setComplemp(String complemp) {
		this.complemp = complemp;
	}



	public String getBairemp() {
		return bairemp;
	}



	public void setBairemp(String bairemp) {
		this.bairemp = bairemp;
	}



	public String getCepemp() {
		return cepemp;
	}



	public void setCepemp(String cepemp) {
		this.cepemp = cepemp;
	}



	public String getCidemp() {
		return cidemp;
	}



	public void setCidemp(String cidemp) {
		this.cidemp = cidemp;
	}



	public String getUfemp() {
		return ufemp;
	}



	public void setUfemp(String ufemp) {
		this.ufemp = ufemp;
	}



	public String getDddemp() {
		return dddemp;
	}



	public void setDddemp(String dddemp) {
		this.dddemp = dddemp;
	}



	public String getFoneemp() {
		return foneemp;
	}



	public void setFoneemp(String foneemp) {
		this.foneemp = foneemp;
	}



	public String getFaxemp() {
		return faxemp;
	}



	public void setFaxemp(String faxemp) {
		this.faxemp = faxemp;
	}



	public String getEmailemp() {
		return emailemp;
	}



	public void setEmailemp(String emailemp) {
		this.emailemp = emailemp;
	}



	public String getWwwemp() {
		return wwwemp;
	}



	public void setWwwemp(String wwwemp) {
		this.wwwemp = wwwemp;
	}



	public String getCodeanemp() {
		return codeanemp;
	}



	public void setCodeanemp(String codeanemp) {
		this.codeanemp = codeanemp;
	}



	public String getNomecontemp() {
		return nomecontemp;
	}



	public void setNomecontemp(String nomecontemp) {
		this.nomecontemp = nomecontemp;
	}



	public String getMultialmoxemp() {
		return multialmoxemp;
	}



	public void setMultialmoxemp(String multialmoxemp) {
		this.multialmoxemp = multialmoxemp;
	}



	public String getFotoemp() {
		return fotoemp;
	}



	public void setFotoemp(String fotoemp) {
		this.fotoemp = fotoemp;
	}



	public String getCodmunic() {
		return codmunic;
	}



	public void setCodmunic(String codmunic) {
		this.codmunic = codmunic;
	}



	public String getSiglauf() {
		return siglauf;
	}



	public void setSiglauf(String siglauf) {
		this.siglauf = siglauf;
	}



	public String getCodpais() {
		return codpais;
	}



	public void setCodpais(String codpais) {
		this.codpais = codpais;
	}



	public Float getPercissemp() {
		return percissemp;
	}



	public void setPercissemp(Float percissemp) {
		this.percissemp = percissemp;
	}



	public String getCodpaisemp() {
		return codpaisemp;
	}



	public void setCodpaisemp(String codpaisemp) {
		this.codpaisemp = codpaisemp;
	}



	public Date getDtins() {
		return dtins;
	}



	public void setDtins(Date dtins) {
		this.dtins = dtins;
	}



	public Date getHins() {
		return hins;
	}



	public void setHins(Date hins) {
		this.hins = hins;
	}



	public Integer getIdusuins() {
		return idusuins;
	}



	public void setIdusuins(Integer idusuins) {
		this.idusuins = idusuins;
	}



	public Date getDtalt() {
		return dtalt;
	}



	public void setDtalt(Date dtalt) {
		this.dtalt = dtalt;
	}



	public Date getHalt() {
		return halt;
	}



	public void setHalt(Date halt) {
		this.halt = halt;
	}



	public String getIdusualt() {
		return idusualt;
	}



	public void setIdusualt(String idusualt) {
		this.idusualt = idusualt;
	}



	public IEmpresaBCF getEmpresaBCF() {
		return empresaBCF;
	}



	public void setEmpresaBCF(IEmpresaBCF empresaBCF) {
		this.empresaBCF = empresaBCF;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



}