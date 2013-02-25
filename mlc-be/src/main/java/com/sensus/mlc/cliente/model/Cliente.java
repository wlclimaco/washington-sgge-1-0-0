package com.sensus.mlc.cliente.model;

import java.sql.Blob;
import java.sql.Date;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * * The Model Object Action.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class Cliente extends SensusModel
{

	/** The codemp. */
	private Integer codemp;

	/** The codfilial. */
	private Integer codfilial;
	
	private Integer codcli;
	
	private Integer codempcc;
	
	private Integer codfilialcc;
	
	private Integer codclascli;
	
	private Integer codempvd;
	
	private Integer codfilialvd;
	
	private Integer codvend;
	
	private Integer codemptc;
	
	private Integer codfilialtc;
	
	private Integer codtipocob;
	
	private Integer codemppg;
	
	private Integer codfilialpg;
	
	private Integer codplanopag;
	
	private Integer codemptn;
	
	private Integer codfilialtn;
	
	private Integer codtran;
	
	private Integer codempbo;
	
	private Integer codfilialbo;
	
	private String razcli;
	
	private String codbanco;
	
	private Integer codempsr;
	
	private Integer codfilialsr;
	
	private Integer codsetor;
	
	
	private String nomecli;
	
	private Integer codempti;
	
	private Integer codfilialti;
	
	
	private Integer codtipocli;
	
	
	private Date datacli;
	
	
	private String pessoacli;
	
	private String ativocli;
	
	
	private String cnpjcli;
	
	private String insccli;
	
	private String cpfcli;
	
	private String rgcli;
	
	private String sspcli;
	
	private String endcli;
	
	private Integer numcli;
	
	private String complcli;
	
	private String baircli;
	
	private String cidcli;
	
	private String ufcli;
	
	private String cepcli;
	
	private String dddcli;
	
	private String fonecli;
	
	private String ramalcli;
	
	private String dddfaxcli;
	
	private String faxcli;
	
	private String emailcli;
	
	private String contcli;
	
	private String endcob;
	
	private Integer numcob;
	
	private String complcob;
	
	private String baircob;
	
	private String cidcob;
	
	private String ufcob;
	
	private String cepcob;
	
	private String dddfonecob;
	
	private String fonecob;
	
	private String dddfaxcob;
	
	private String faxcob;
	
	private String endent;
	
	private Integer nument;
	
	private String complent;
	
	private String bairent;
	
	private String cident;
	
	private String ufent;
	
	private String cepent;
	
	private String dddfoneent;
	
	private String foneent;
	
	private String dddfaxent;
	
	private String faxent;
	
	private String obscli;
	
	private String agenciacli;
	
	private Integer codemppq;
	
	private Integer codfilialpq;
	
	private Integer codpesq;
	
	private String incracli;
	
	private Integer codtpcred;
	
	private Integer codfilialtr;
	
	private Integer codemptr;
	
	private Date dtinitr;
	
	private Date dtvenctotr;
	
	private String nirfcli;
	
	private Integer codfisccli;
	
	private Integer codempfc;
	
	private Integer codfilialfc;
	
	private String natcli;
	
	private String ufnatcli;
	
	private String temporescli;
	
	private Integer codpais;
	
	private String apelidocli;
	
	private Integer codempec;
	
	private Integer codfilialec;
	
	private Integer codtbec;
	
	private Integer codittbec;
	
	private Date dtins;
	
	private Date idusuins;
	
	private Date dtalt;
	
	private Date idusualt;
	
	private Date hins;
	
	private Date halt;
	
	private String sitecli;
	
	private Integer codcontdeb;
	
	private Integer codcontcred;

	public Integer getCodemp() {
		return codemp;
	}

	public void setCodemp(Integer codemp) {
		this.codemp = codemp;
	}

	public Integer getCodfilial() {
		return codfilial;
	}

	public void setCodfilial(Integer codfilial) {
		this.codfilial = codfilial;
	}

	public Integer getCodcli() {
		return codcli;
	}

	public void setCodcli(Integer codcli) {
		this.codcli = codcli;
	}

	public Integer getCodempcc() {
		return codempcc;
	}

	public void setCodempcc(Integer codempcc) {
		this.codempcc = codempcc;
	}

	public Integer getCodfilialcc() {
		return codfilialcc;
	}

	public void setCodfilialcc(Integer codfilialcc) {
		this.codfilialcc = codfilialcc;
	}

	public Integer getCodclascli() {
		return codclascli;
	}

	public void setCodclascli(Integer codclascli) {
		this.codclascli = codclascli;
	}

	public Integer getCodempvd() {
		return codempvd;
	}

	public void setCodempvd(Integer codempvd) {
		this.codempvd = codempvd;
	}

	public Integer getCodfilialvd() {
		return codfilialvd;
	}

	public void setCodfilialvd(Integer codfilialvd) {
		this.codfilialvd = codfilialvd;
	}

	public Integer getCodvend() {
		return codvend;
	}

	public void setCodvend(Integer codvend) {
		this.codvend = codvend;
	}

	public Integer getCodemptc() {
		return codemptc;
	}

	public void setCodemptc(Integer codemptc) {
		this.codemptc = codemptc;
	}

	public Integer getCodfilialtc() {
		return codfilialtc;
	}

	public void setCodfilialtc(Integer codfilialtc) {
		this.codfilialtc = codfilialtc;
	}

	public Integer getCodtipocob() {
		return codtipocob;
	}

	public void setCodtipocob(Integer codtipocob) {
		this.codtipocob = codtipocob;
	}

	public Integer getCodemppg() {
		return codemppg;
	}

	public void setCodemppg(Integer codemppg) {
		this.codemppg = codemppg;
	}

	public Integer getCodfilialpg() {
		return codfilialpg;
	}

	public void setCodfilialpg(Integer codfilialpg) {
		this.codfilialpg = codfilialpg;
	}

	public Integer getCodplanopag() {
		return codplanopag;
	}

	public void setCodplanopag(Integer codplanopag) {
		this.codplanopag = codplanopag;
	}

	public Integer getCodemptn() {
		return codemptn;
	}

	public void setCodemptn(Integer codemptn) {
		this.codemptn = codemptn;
	}

	public Integer getCodfilialtn() {
		return codfilialtn;
	}

	public void setCodfilialtn(Integer codfilialtn) {
		this.codfilialtn = codfilialtn;
	}

	public Integer getCodtran() {
		return codtran;
	}

	public void setCodtran(Integer codtran) {
		this.codtran = codtran;
	}

	public Integer getCodempbo() {
		return codempbo;
	}

	public void setCodempbo(Integer codempbo) {
		this.codempbo = codempbo;
	}

	public Integer getCodfilialbo() {
		return codfilialbo;
	}

	public void setCodfilialbo(Integer codfilialbo) {
		this.codfilialbo = codfilialbo;
	}

	public String getRazcli() {
		return razcli;
	}

	public void setRazcli(String razcli) {
		this.razcli = razcli;
	}

	public String getCodbanco() {
		return codbanco;
	}

	public void setCodbanco(String codbanco) {
		this.codbanco = codbanco;
	}

	public Integer getCodempsr() {
		return codempsr;
	}

	public void setCodempsr(Integer codempsr) {
		this.codempsr = codempsr;
	}

	public Integer getCodfilialsr() {
		return codfilialsr;
	}

	public void setCodfilialsr(Integer codfilialsr) {
		this.codfilialsr = codfilialsr;
	}

	public Integer getCodsetor() {
		return codsetor;
	}

	public void setCodsetor(Integer codsetor) {
		this.codsetor = codsetor;
	}

	public String getNomecli() {
		return nomecli;
	}

	public void setNomecli(String nomecli) {
		this.nomecli = nomecli;
	}

	public Integer getCodempti() {
		return codempti;
	}

	public void setCodempti(Integer codempti) {
		this.codempti = codempti;
	}

	public Integer getCodfilialti() {
		return codfilialti;
	}

	public void setCodfilialti(Integer codfilialti) {
		this.codfilialti = codfilialti;
	}

	public Integer getCodtipocli() {
		return codtipocli;
	}

	public void setCodtipocli(Integer codtipocli) {
		this.codtipocli = codtipocli;
	}

	public Date getDatacli() {
		return datacli;
	}

	public void setDatacli(Date datacli) {
		this.datacli = datacli;
	}

	public String getPessoacli() {
		return pessoacli;
	}

	public void setPessoacli(String pessoacli) {
		this.pessoacli = pessoacli;
	}

	public String getAtivocli() {
		return ativocli;
	}

	public void setAtivocli(String ativocli) {
		this.ativocli = ativocli;
	}

	public String getCnpjcli() {
		return cnpjcli;
	}

	public void setCnpjcli(String cnpjcli) {
		this.cnpjcli = cnpjcli;
	}

	public String getInsccli() {
		return insccli;
	}

	public void setInsccli(String insccli) {
		this.insccli = insccli;
	}

	public String getCpfcli() {
		return cpfcli;
	}

	public void setCpfcli(String cpfcli) {
		this.cpfcli = cpfcli;
	}

	public String getRgcli() {
		return rgcli;
	}

	public void setRgcli(String rgcli) {
		this.rgcli = rgcli;
	}

	public String getSspcli() {
		return sspcli;
	}

	public void setSspcli(String sspcli) {
		this.sspcli = sspcli;
	}

	public String getEndcli() {
		return endcli;
	}

	public void setEndcli(String endcli) {
		this.endcli = endcli;
	}

	public Integer getNumcli() {
		return numcli;
	}

	public void setNumcli(Integer numcli) {
		this.numcli = numcli;
	}

	public String getComplcli() {
		return complcli;
	}

	public void setComplcli(String complcli) {
		this.complcli = complcli;
	}

	public String getBaircli() {
		return baircli;
	}

	public void setBaircli(String baircli) {
		this.baircli = baircli;
	}

	public String getCidcli() {
		return cidcli;
	}

	public void setCidcli(String cidcli) {
		this.cidcli = cidcli;
	}

	public String getUfcli() {
		return ufcli;
	}

	public void setUfcli(String ufcli) {
		this.ufcli = ufcli;
	}

	public String getCepcli() {
		return cepcli;
	}

	public void setCepcli(String cepcli) {
		this.cepcli = cepcli;
	}

	public String getDddcli() {
		return dddcli;
	}

	public void setDddcli(String dddcli) {
		this.dddcli = dddcli;
	}

	public String getFonecli() {
		return fonecli;
	}

	public void setFonecli(String fonecli) {
		this.fonecli = fonecli;
	}

	public String getRamalcli() {
		return ramalcli;
	}

	public void setRamalcli(String ramalcli) {
		this.ramalcli = ramalcli;
	}

	public String getDddfaxcli() {
		return dddfaxcli;
	}

	public void setDddfaxcli(String dddfaxcli) {
		this.dddfaxcli = dddfaxcli;
	}

	public String getFaxcli() {
		return faxcli;
	}

	public void setFaxcli(String faxcli) {
		this.faxcli = faxcli;
	}

	public String getEmailcli() {
		return emailcli;
	}

	public void setEmailcli(String emailcli) {
		this.emailcli = emailcli;
	}

	public String getContcli() {
		return contcli;
	}

	public void setContcli(String contcli) {
		this.contcli = contcli;
	}

	public String getEndcob() {
		return endcob;
	}

	public void setEndcob(String endcob) {
		this.endcob = endcob;
	}

	public Integer getNumcob() {
		return numcob;
	}

	public void setNumcob(Integer numcob) {
		this.numcob = numcob;
	}

	public String getComplcob() {
		return complcob;
	}

	public void setComplcob(String complcob) {
		this.complcob = complcob;
	}

	public String getBaircob() {
		return baircob;
	}

	public void setBaircob(String baircob) {
		this.baircob = baircob;
	}

	public String getCidcob() {
		return cidcob;
	}

	public void setCidcob(String cidcob) {
		this.cidcob = cidcob;
	}

	public String getUfcob() {
		return ufcob;
	}

	public void setUfcob(String ufcob) {
		this.ufcob = ufcob;
	}

	public String getCepcob() {
		return cepcob;
	}

	public void setCepcob(String cepcob) {
		this.cepcob = cepcob;
	}

	public String getDddfonecob() {
		return dddfonecob;
	}

	public void setDddfonecob(String dddfonecob) {
		this.dddfonecob = dddfonecob;
	}

	public String getFonecob() {
		return fonecob;
	}

	public void setFonecob(String fonecob) {
		this.fonecob = fonecob;
	}

	public String getDddfaxcob() {
		return dddfaxcob;
	}

	public void setDddfaxcob(String dddfaxcob) {
		this.dddfaxcob = dddfaxcob;
	}

	public String getFaxcob() {
		return faxcob;
	}

	public void setFaxcob(String faxcob) {
		this.faxcob = faxcob;
	}

	public String getEndent() {
		return endent;
	}

	public void setEndent(String endent) {
		this.endent = endent;
	}

	public Integer getNument() {
		return nument;
	}

	public void setNument(Integer nument) {
		this.nument = nument;
	}

	public String getComplent() {
		return complent;
	}

	public void setComplent(String complent) {
		this.complent = complent;
	}

	public String getBairent() {
		return bairent;
	}

	public void setBairent(String bairent) {
		this.bairent = bairent;
	}

	public String getCident() {
		return cident;
	}

	public void setCident(String cident) {
		this.cident = cident;
	}

	public String getUfent() {
		return ufent;
	}

	public void setUfent(String ufent) {
		this.ufent = ufent;
	}

	public String getCepent() {
		return cepent;
	}

	public void setCepent(String cepent) {
		this.cepent = cepent;
	}

	public String getDddfoneent() {
		return dddfoneent;
	}

	public void setDddfoneent(String dddfoneent) {
		this.dddfoneent = dddfoneent;
	}

	public String getFoneent() {
		return foneent;
	}

	public void setFoneent(String foneent) {
		this.foneent = foneent;
	}

	public String getDddfaxent() {
		return dddfaxent;
	}

	public void setDddfaxent(String dddfaxent) {
		this.dddfaxent = dddfaxent;
	}

	public String getFaxent() {
		return faxent;
	}

	public void setFaxent(String faxent) {
		this.faxent = faxent;
	}

	public String getObscli() {
		return obscli;
	}

	public void setObscli(String obscli) {
		this.obscli = obscli;
	}

	public String getAgenciacli() {
		return agenciacli;
	}

	public void setAgenciacli(String agenciacli) {
		this.agenciacli = agenciacli;
	}

	public Integer getCodemppq() {
		return codemppq;
	}

	public void setCodemppq(Integer codemppq) {
		this.codemppq = codemppq;
	}

	public Integer getCodfilialpq() {
		return codfilialpq;
	}

	public void setCodfilialpq(Integer codfilialpq) {
		this.codfilialpq = codfilialpq;
	}

	public Integer getCodpesq() {
		return codpesq;
	}

	public void setCodpesq(Integer codpesq) {
		this.codpesq = codpesq;
	}

	public String getIncracli() {
		return incracli;
	}

	public void setIncracli(String incracli) {
		this.incracli = incracli;
	}

	public Integer getCodtpcred() {
		return codtpcred;
	}

	public void setCodtpcred(Integer codtpcred) {
		this.codtpcred = codtpcred;
	}

	public Integer getCodfilialtr() {
		return codfilialtr;
	}

	public void setCodfilialtr(Integer codfilialtr) {
		this.codfilialtr = codfilialtr;
	}

	public Integer getCodemptr() {
		return codemptr;
	}

	public void setCodemptr(Integer codemptr) {
		this.codemptr = codemptr;
	}

	public Date getDtinitr() {
		return dtinitr;
	}

	public void setDtinitr(Date dtinitr) {
		this.dtinitr = dtinitr;
	}

	public Date getDtvenctotr() {
		return dtvenctotr;
	}

	public void setDtvenctotr(Date dtvenctotr) {
		this.dtvenctotr = dtvenctotr;
	}

	public String getNirfcli() {
		return nirfcli;
	}

	public void setNirfcli(String nirfcli) {
		this.nirfcli = nirfcli;
	}

	public Integer getCodfisccli() {
		return codfisccli;
	}

	public void setCodfisccli(Integer codfisccli) {
		this.codfisccli = codfisccli;
	}

	public Integer getCodempfc() {
		return codempfc;
	}

	public void setCodempfc(Integer codempfc) {
		this.codempfc = codempfc;
	}

	public Integer getCodfilialfc() {
		return codfilialfc;
	}

	public void setCodfilialfc(Integer codfilialfc) {
		this.codfilialfc = codfilialfc;
	}

	public String getNatcli() {
		return natcli;
	}

	public void setNatcli(String natcli) {
		this.natcli = natcli;
	}

	public String getUfnatcli() {
		return ufnatcli;
	}

	public void setUfnatcli(String ufnatcli) {
		this.ufnatcli = ufnatcli;
	}

	public String getTemporescli() {
		return temporescli;
	}

	public void setTemporescli(String temporescli) {
		this.temporescli = temporescli;
	}

	public Integer getCodpais() {
		return codpais;
	}

	public void setCodpais(Integer codpais) {
		this.codpais = codpais;
	}

	public String getApelidocli() {
		return apelidocli;
	}

	public void setApelidocli(String apelidocli) {
		this.apelidocli = apelidocli;
	}

	public Integer getCodempec() {
		return codempec;
	}

	public void setCodempec(Integer codempec) {
		this.codempec = codempec;
	}

	public Integer getCodfilialec() {
		return codfilialec;
	}

	public void setCodfilialec(Integer codfilialec) {
		this.codfilialec = codfilialec;
	}

	public Integer getCodtbec() {
		return codtbec;
	}

	public void setCodtbec(Integer codtbec) {
		this.codtbec = codtbec;
	}

	public Integer getCodittbec() {
		return codittbec;
	}

	public void setCodittbec(Integer codittbec) {
		this.codittbec = codittbec;
	}

	public Date getDtins() {
		return dtins;
	}

	public void setDtins(Date dtins) {
		this.dtins = dtins;
	}

	public Date getIdusuins() {
		return idusuins;
	}

	public void setIdusuins(Date idusuins) {
		this.idusuins = idusuins;
	}

	public Date getDtalt() {
		return dtalt;
	}

	public void setDtalt(Date dtalt) {
		this.dtalt = dtalt;
	}

	public Date getIdusualt() {
		return idusualt;
	}

	public void setIdusualt(Date idusualt) {
		this.idusualt = idusualt;
	}

	public Date getHins() {
		return hins;
	}

	public void setHins(Date hins) {
		this.hins = hins;
	}

	public Date getHalt() {
		return halt;
	}

	public void setHalt(Date halt) {
		this.halt = halt;
	}

	public String getSitecli() {
		return sitecli;
	}

	public void setSitecli(String sitecli) {
		this.sitecli = sitecli;
	}

	public Integer getCodcontdeb() {
		return codcontdeb;
	}

	public void setCodcontdeb(Integer codcontdeb) {
		this.codcontdeb = codcontdeb;
	}

	public Integer getCodcontcred() {
		return codcontcred;
	}

	public void setCodcontcred(Integer codcontcred) {
		this.codcontcred = codcontcred;
	}

	@Override
	public String toString() {
		return "Cliente [codemp=" + codemp + ", codfilial=" + codfilial
				+ ", codcli=" + codcli + ", codempcc=" + codempcc
				+ ", codfilialcc=" + codfilialcc + ", codclascli=" + codclascli
				+ ", codempvd=" + codempvd + ", codfilialvd=" + codfilialvd
				+ ", codvend=" + codvend + ", codemptc=" + codemptc
				+ ", codfilialtc=" + codfilialtc + ", codtipocob=" + codtipocob
				+ ", codemppg=" + codemppg + ", codfilialpg=" + codfilialpg
				+ ", codplanopag=" + codplanopag + ", codemptn=" + codemptn
				+ ", codfilialtn=" + codfilialtn + ", codtran=" + codtran
				+ ", codempbo=" + codempbo + ", codfilialbo=" + codfilialbo
				+ ", razcli=" + razcli + ", codbanco=" + codbanco
				+ ", codempsr=" + codempsr + ", codfilialsr=" + codfilialsr
				+ ", codsetor=" + codsetor + ", nomecli=" + nomecli
				+ ", codempti=" + codempti + ", codfilialti=" + codfilialti
				+ ", codtipocli=" + codtipocli + ", datacli=" + datacli
				+ ", pessoacli=" + pessoacli + ", ativocli=" + ativocli
				+ ", cnpjcli=" + cnpjcli + ", insccli=" + insccli + ", cpfcli="
				+ cpfcli + ", rgcli=" + rgcli + ", sspcli=" + sspcli
				+ ", endcli=" + endcli + ", numcli=" + numcli + ", complcli="
				+ complcli + ", baircli=" + baircli + ", cidcli=" + cidcli
				+ ", ufcli=" + ufcli + ", cepcli=" + cepcli + ", dddcli="
				+ dddcli + ", fonecli=" + fonecli + ", ramalcli=" + ramalcli
				+ ", dddfaxcli=" + dddfaxcli + ", faxcli=" + faxcli
				+ ", emailcli=" + emailcli + ", contcli=" + contcli
				+ ", endcob=" + endcob + ", numcob=" + numcob + ", complcob="
				+ complcob + ", baircob=" + baircob + ", cidcob=" + cidcob
				+ ", ufcob=" + ufcob + ", cepcob=" + cepcob + ", dddfonecob="
				+ dddfonecob + ", fonecob=" + fonecob + ", dddfaxcob="
				+ dddfaxcob + ", faxcob=" + faxcob + ", endent=" + endent
				+ ", nument=" + nument + ", complent=" + complent
				+ ", bairent=" + bairent + ", cident=" + cident + ", ufent="
				+ ufent + ", cepent=" + cepent + ", dddfoneent=" + dddfoneent
				+ ", foneent=" + foneent + ", dddfaxent=" + dddfaxent
				+ ", faxent=" + faxent + ", obscli=" + obscli + ", agenciacli="
				+ agenciacli + ", codemppq=" + codemppq + ", codfilialpq="
				+ codfilialpq + ", codpesq=" + codpesq + ", incracli="
				+ incracli + ", codtpcred=" + codtpcred + ", codfilialtr="
				+ codfilialtr + ", codemptr=" + codemptr + ", dtinitr="
				+ dtinitr + ", dtvenctotr=" + dtvenctotr + ", nirfcli="
				+ nirfcli + ", codfisccli=" + codfisccli + ", codempfc="
				+ codempfc + ", codfilialfc=" + codfilialfc + ", natcli="
				+ natcli + ", ufnatcli=" + ufnatcli + ", temporescli="
				+ temporescli + ", codpais=" + codpais + ", apelidocli="
				+ apelidocli + ", codempec=" + codempec + ", codfilialec="
				+ codfilialec + ", codtbec=" + codtbec + ", codittbec="
				+ codittbec + ", dtins=" + dtins + ", idusuins=" + idusuins
				+ ", dtalt=" + dtalt + ", idusualt=" + idusualt + ", hins="
				+ hins + ", halt=" + halt + ", sitecli=" + sitecli
				+ ", codcontdeb=" + codcontdeb + ", codcontcred=" + codcontcred
				+ "]";
	}

}
