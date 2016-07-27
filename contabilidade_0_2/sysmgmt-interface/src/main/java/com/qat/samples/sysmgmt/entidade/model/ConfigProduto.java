package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigProduto extends ModelCosmeDamiao
{
	private Integer id;
	private Cfop cfop;
	private DoisValores icmsSitTrib;
	private DoisValores icmsOrigem;
	private DoisValores icmsModalidadeBC;
	private Double icmsRedBaseCalc;
	private Double icmsAliq;
	
	private DoisValores icmsMotDesoneracao;
	private DoisValores icmsModBCST;
	private Double icmsMargValAdic;
	private Double icmsRedBaseCalcST;
	private Double icmsPrecoUnitPautaST;
	private Double icmsAliqST;
	
	private DoisValores ipiSitTrib;
	private Double ipiClasCigarroBebida;
	private String ipiCNPJProd;
	private String ipiCodSeloCont;
	private Double ipiQtdSelo;
	private Integer ipiCodEnquad;
	private DoisValores ipiTipCalc;
	private Double ipiAliq;
	
	private DoisValores pisSitTrib;
	private Double pisAliq;
	private Double pisValUnidtrib;
	
	private DoisValores pistipoCalcSubstTrib;
	private Double pisAliqST;
	private Double pisValorAliqST;
	
	private DoisValores cofinsSubstTrib;
	private Double cofinsAliq;
	private Double cofinsValorAliq;
	private DoisValores cofinsTipoCalcSubstTrib;
	private Double cofinsAliqST;
	private Double cofinsValorAliqST;
	
	public ConfigProduto()
	{
		super();
	}

	public ConfigProduto(Integer id)
	{
		setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}

	public DoisValores getIcmsSitTrib() {
		return icmsSitTrib;
	}

	public void setIcmsSitTrib(DoisValores icmsSitTrib) {
		this.icmsSitTrib = icmsSitTrib;
	}

	public DoisValores getIcmsOrigem() {
		return icmsOrigem;
	}

	public void setIcmsOrigem(DoisValores icmsOrigem) {
		this.icmsOrigem = icmsOrigem;
	}

	public DoisValores getIcmsModalidadeBC() {
		return icmsModalidadeBC;
	}

	public void setIcmsModalidadeBC(DoisValores icmsModalidadeBC) {
		this.icmsModalidadeBC = icmsModalidadeBC;
	}

	public Double getIcmsRedBaseCalc() {
		return icmsRedBaseCalc;
	}

	public void setIcmsRedBaseCalc(Double icmsRedBaseCalc) {
		this.icmsRedBaseCalc = icmsRedBaseCalc;
	}

	public Double getIcmsAliq() {
		return icmsAliq;
	}

	public void setIcmsAliq(Double icmsAliq) {
		this.icmsAliq = icmsAliq;
	}

	public DoisValores getIcmsMotDesoneracao() {
		return icmsMotDesoneracao;
	}

	public void setIcmsMotDesoneracao(DoisValores icmsMotDesoneracao) {
		this.icmsMotDesoneracao = icmsMotDesoneracao;
	}

	public DoisValores getIcmsModBCST() {
		return icmsModBCST;
	}

	public void setIcmsModBCST(DoisValores icmsModBCST) {
		this.icmsModBCST = icmsModBCST;
	}

	public Double getIcmsMargValAdic() {
		return icmsMargValAdic;
	}

	public void setIcmsMargValAdic(Double icmsMargValAdic) {
		this.icmsMargValAdic = icmsMargValAdic;
	}

	public Double getIcmsRedBaseCalcST() {
		return icmsRedBaseCalcST;
	}

	public void setIcmsRedBaseCalcST(Double icmsRedBaseCalcST) {
		this.icmsRedBaseCalcST = icmsRedBaseCalcST;
	}

	public Double getIcmsPrecoUnitPautaST() {
		return icmsPrecoUnitPautaST;
	}

	public void setIcmsPrecoUnitPautaST(Double icmsPrecoUnitPautaST) {
		this.icmsPrecoUnitPautaST = icmsPrecoUnitPautaST;
	}

	public Double getIcmsAliqST() {
		return icmsAliqST;
	}

	public void setIcmsAliqST(Double icmsAliqST) {
		this.icmsAliqST = icmsAliqST;
	}

	public DoisValores getIpiSitTrib() {
		return ipiSitTrib;
	}

	public void setIpiSitTrib(DoisValores ipiSitTrib) {
		this.ipiSitTrib = ipiSitTrib;
	}

	public Double getIpiClasCigarroBebida() {
		return ipiClasCigarroBebida;
	}

	public void setIpiClasCigarroBebida(Double ipiClasCigarroBebida) {
		this.ipiClasCigarroBebida = ipiClasCigarroBebida;
	}

	public String getIpiCNPJProd() {
		return ipiCNPJProd;
	}

	public void setIpiCNPJProd(String ipiCNPJProd) {
		this.ipiCNPJProd = ipiCNPJProd;
	}

	public String getIpiCodSeloCont() {
		return ipiCodSeloCont;
	}

	public void setIpiCodSeloCont(String ipiCodSeloCont) {
		this.ipiCodSeloCont = ipiCodSeloCont;
	}

	public Double getIpiQtdSelo() {
		return ipiQtdSelo;
	}

	public void setIpiQtdSelo(Double ipiQtdSelo) {
		this.ipiQtdSelo = ipiQtdSelo;
	}

	public Integer getIpiCodEnquad() {
		return ipiCodEnquad;
	}

	public void setIpiCodEnquad(Integer ipiCodEnquad) {
		this.ipiCodEnquad = ipiCodEnquad;
	}

	public DoisValores getIpiTipCalc() {
		return ipiTipCalc;
	}

	public void setIpiTipCalc(DoisValores ipiTipCalc) {
		this.ipiTipCalc = ipiTipCalc;
	}

	public Double getIpiAliq() {
		return ipiAliq;
	}

	public void setIpiAliq(Double ipiAliq) {
		this.ipiAliq = ipiAliq;
	}

	public DoisValores getPisSitTrib() {
		return pisSitTrib;
	}

	public void setPisSitTrib(DoisValores pisSitTrib) {
		this.pisSitTrib = pisSitTrib;
	}

	public Double getPisAliq() {
		return pisAliq;
	}

	public void setPisAliq(Double pisAliq) {
		this.pisAliq = pisAliq;
	}

	public Double getPisValUnidtrib() {
		return pisValUnidtrib;
	}

	public void setPisValUnidtrib(Double pisValUnidtrib) {
		this.pisValUnidtrib = pisValUnidtrib;
	}

	public DoisValores getPistipoCalcSubstTrib() {
		return pistipoCalcSubstTrib;
	}

	public void setPistipoCalcSubstTrib(DoisValores pistipoCalcSubstTrib) {
		this.pistipoCalcSubstTrib = pistipoCalcSubstTrib;
	}

	public Double getPisAliqST() {
		return pisAliqST;
	}

	public void setPisAliqST(Double pisAliqST) {
		this.pisAliqST = pisAliqST;
	}

	public Double getPisValorAliqST() {
		return pisValorAliqST;
	}

	public void setPisValorAliqST(Double pisValorAliqST) {
		this.pisValorAliqST = pisValorAliqST;
	}

	public DoisValores getCofinsSubstTrib() {
		return cofinsSubstTrib;
	}

	public void setCofinsSubstTrib(DoisValores cofinsSubstTrib) {
		this.cofinsSubstTrib = cofinsSubstTrib;
	}

	public Double getCofinsAliq() {
		return cofinsAliq;
	}

	public void setCofinsAliq(Double cofinsAliq) {
		this.cofinsAliq = cofinsAliq;
	}

	public Double getCofinsValorAliq() {
		return cofinsValorAliq;
	}

	public void setCofinsValorAliq(Double cofinsValorAliq) {
		this.cofinsValorAliq = cofinsValorAliq;
	}

	public DoisValores getCofinsTipoCalcSubstTrib() {
		return cofinsTipoCalcSubstTrib;
	}

	public void setCofinsTipoCalcSubstTrib(DoisValores cofinsTipoCalcSubstTrib) {
		this.cofinsTipoCalcSubstTrib = cofinsTipoCalcSubstTrib;
	}

	public Double getCofinsAliqST() {
		return cofinsAliqST;
	}

	public void setCofinsAliqST(Double cofinsAliqST) {
		this.cofinsAliqST = cofinsAliqST;
	}

	public Double getCofinsValorAliqST() {
		return cofinsValorAliqST;
	}

	public void setCofinsValorAliqST(Double cofinsValorAliqST) {
		this.cofinsValorAliqST = cofinsValorAliqST;
	}

	@Override
	public String toString() {
		return "ConfigProduto [getId()=" + getId() + ", getCfop()=" + getCfop() + ", getIcmsSitTrib()="
				+ getIcmsSitTrib() + ", getIcmsOrigem()=" + getIcmsOrigem() + ", getIcmsModalidadeBC()="
				+ getIcmsModalidadeBC() + ", getIcmsRedBaseCalc()=" + getIcmsRedBaseCalc() + ", getIcmsAliq()="
				+ getIcmsAliq() + ", getIcmsMotDesoneracao()=" + getIcmsMotDesoneracao() + ", getIcmsModBCST()="
				+ getIcmsModBCST() + ", getIcmsMargValAdic()=" + getIcmsMargValAdic() + ", getIcmsRedBaseCalcST()="
				+ getIcmsRedBaseCalcST() + ", getIcmsPrecoUnitPautaST()=" + getIcmsPrecoUnitPautaST()
				+ ", getIcmsAliqST()=" + getIcmsAliqST() + ", getIpiSitTrib()=" + getIpiSitTrib()
				+ ", getIpiClasCigarroBebida()=" + getIpiClasCigarroBebida() + ", getIpiCNPJProd()=" + getIpiCNPJProd()
				+ ", getIpiCodSeloCont()=" + getIpiCodSeloCont() + ", getIpiQtdSelo()=" + getIpiQtdSelo()
				+ ", getIpiCodEnquad()=" + getIpiCodEnquad() + ", getIpiTipCalc()=" + getIpiTipCalc()
				+ ", getIpiAliq()=" + getIpiAliq() + ", getPisSitTrib()=" + getPisSitTrib() + ", getPisAliq()="
				+ getPisAliq() + ", getPisValUnidtrib()=" + getPisValUnidtrib() + ", getPistipoCalcSubstTrib()="
				+ getPistipoCalcSubstTrib() + ", getPisAliqST()=" + getPisAliqST() + ", getPisValorAliqST()="
				+ getPisValorAliqST() + ", getCofinsSubstTrib()=" + getCofinsSubstTrib() + ", getCofinsAliq()="
				+ getCofinsAliq() + ", getCofinsValorAliq()=" + getCofinsValorAliq() + ", getCofinsTipoCalcSubstTrib()="
				+ getCofinsTipoCalcSubstTrib() + ", getCofinsAliqST()=" + getCofinsAliqST()
				+ ", getCofinsValorAliqST()=" + getCofinsValorAliqST() + ", toString()=" + super.toString() + "]";
	}

}
