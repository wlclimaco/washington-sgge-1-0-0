/** create by system gera-java version 1.0.0 09/11/2016 19:59 : 12*/
package com.qat.samples.sysmgmt.produto.model;


import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class ICMSOpInter extends ModelCosmeDamiao
{

    /** The econtabil id for the ICMSOpInter. */
    private Integer id;
    
    private Integer prodId;

    /** The econtabil perICMSUFDest for the ICMSOpInter. */
    private Double perICMSUFDest;

    /** The econtabil vrBaseCalcUFDest for the ICMSOpInter. */
    private Double vrBaseCalcUFDest;

    /** The econtabil aliqIntUFDest for the ICMSOpInter. */
    private Double aliqIntUFDest;

    /** The econtabil aliqInterestadual for the ICMSOpInter. */
    private DoisValores aliqInterestadual;

    /** The econtabil percProvPart for the ICMSOpInter. */
    private DoisValores percProvPart;

    /** The econtabil vrICMSPartUFDest for the ICMSOpInter. */
    private Double vrICMSPartUFDest;

    /** The econtabil vrICMSPartUFReme for the ICMSOpInter. */
    private Double vrICMSPartUFReme;

    /** The econtabil vrICMSPartUFRemet for the ICMSOpInter. */
    private Double vrICMSRelFCPUFDest;



    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Double getPerICMSUFDest() {
		return perICMSUFDest;
	}



	public void setPerICMSUFDest(Double perICMSUFDest) {
		this.perICMSUFDest = perICMSUFDest;
	}



	public Double getVrBaseCalcUFDest() {
		return vrBaseCalcUFDest;
	}



	public void setVrBaseCalcUFDest(Double vrBaseCalcUFDest) {
		this.vrBaseCalcUFDest = vrBaseCalcUFDest;
	}



	public Double getAliqIntUFDest() {
		return aliqIntUFDest;
	}



	public void setAliqIntUFDest(Double aliqIntUFDest) {
		this.aliqIntUFDest = aliqIntUFDest;
	}



	public DoisValores getAliqInterestadual() {
		return aliqInterestadual;
	}



	public void setAliqInterestadual(DoisValores aliqInterestadual) {
		this.aliqInterestadual = aliqInterestadual;
	}



	public DoisValores getPercProvPart() {
		return percProvPart;
	}



	public void setPercProvPart(DoisValores percProvPart) {
		this.percProvPart = percProvPart;
	}



	public Double getVrICMSPartUFDest() {
		return vrICMSPartUFDest;
	}



	public void setVrICMSPartUFDest(Double vrICMSPartUFDest) {
		this.vrICMSPartUFDest = vrICMSPartUFDest;
	}



	public Double getVrICMSPartUFReme() {
		return vrICMSPartUFReme;
	}



	public void setVrICMSPartUFReme(Double vrICMSPartUFReme) {
		this.vrICMSPartUFReme = vrICMSPartUFReme;
	}



	


	public Double getVrICMSRelFCPUFDest() {
		return vrICMSRelFCPUFDest;
	}



	public void setVrICMSRelFCPUFDest(Double vrICMSRelFCPUFDest) {
		this.vrICMSRelFCPUFDest = vrICMSRelFCPUFDest;
	}



	public Integer getProdId() {
		return prodId;
	}



	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}



	/**
     * Default constructor.
     */
    public ICMSOpInter()
    {
        super();
    }



	@Override
	public String toString() {
		return "ICMSOpInter [getId()=" + getId() + ", getPerICMSUFDest()=" + getPerICMSUFDest()
				+ ", getVrBaseCalcUFDest()=" + getVrBaseCalcUFDest() + ", getAliqIntUFDest()=" + getAliqIntUFDest()
				+ ", getAliqInterestadual()=" + getAliqInterestadual() + ", getPercProvPart()=" + getPercProvPart()
				+ ", getVrICMSPartUFDest()=" + getVrICMSPartUFDest() + ", getVrICMSPartUFReme()="
				+ getVrICMSPartUFReme() + ", getVrICMSRelFCPUFDest()=" + getVrICMSRelFCPUFDest() + ", getProdId()="
				+ getProdId() + ", toString()=" + super.toString() + "]";
	}

}
