package com.sensus.mlc.contabil.model;
public class Lfitcalccusto extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private Integer codcalc;
    private Integer seqitcalc;
    private String siglacalc;
    private String operacaocalc;
    private List<Alterinse> listinsalt;
    /** 
    * Gets the codemp.
    * 
    * @return the codemp 
    */
    	public Integer getCodemp() 
    { 
    	return this.codemp; 
    } 

    /** 
     * Sets the codemp.
     *
     * @param codemp the new codemp 
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
    	return this.codfilial; 
    } 

    /** 
     * Sets the codfilial.
     *
     * @param codfilial the new codfilial 
     */
    public void setCodfilial(Integer codfilial)
    {
    	this.codfilial = codfilial; 
    }  
    /** 
    * Gets the codcalc.
    * 
    * @return the codcalc 
    */
    	public Integer getCodcalc() 
    { 
    	return this.codcalc; 
    } 

    /** 
     * Sets the codcalc.
     *
     * @param codcalc the new codcalc 
     */
    public void setCodcalc(Integer codcalc)
    {
    	this.codcalc = codcalc; 
    }  
    /** 
    * Gets the seqitcalc.
    * 
    * @return the seqitcalc 
    */
    	public Integer getSeqitcalc() 
    { 
    	return this.seqitcalc; 
    } 

    /** 
     * Sets the seqitcalc.
     *
     * @param seqitcalc the new seqitcalc 
     */
    public void setSeqitcalc(Integer seqitcalc)
    {
    	this.seqitcalc = seqitcalc; 
    }  
    /** 
    * Gets the siglacalc.
    * 
    * @return the siglacalc 
    */
    	public String getSiglacalc() 
    { 
    	return this.siglacalc; 
    } 

    /** 
     * Sets the siglacalc.
     *
     * @param siglacalc the new siglacalc 
     */
    public void setSiglacalc(String siglacalc)
    {
    	this.siglacalc = siglacalc; 
    }  
    /** 
    * Gets the operacaocalc.
    * 
    * @return the operacaocalc 
    */
    	public String getOperacaocalc() 
    { 
    	return this.operacaocalc; 
    } 

    /** 
     * Sets the operacaocalc.
     *
     * @param operacaocalc the new operacaocalc 
     */
    public void setOperacaocalc(String operacaocalc)
    {
    	this.operacaocalc = operacaocalc; 
    }  
    /** 
    * Gets the listinsalt.
    * 
    * @return the listinsalt 
    */
    	public List<Alterinse> getListinsalt() 
    { 
    	return this.listinsalt; 
    } 

    /** 
     * Sets the listinsalt.
     *
     * @param listinsalt the new listinsalt 
     */
    public void setListinsalt(List<Alterinse> listinsalt)
    {
    	this.listinsalt = listinsalt; 
    }  
/* 
* (non-Javadoc) 
* @see com.sensus.common.model.SensusModel#toString() 
*/ 
@Override 
public String toString()  
{
return "Lfitcalccusto[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodcalc()=" + getCodcalc()+ ", getSeqitcalc()=" + getSeqitcalc()+ ", getSiglacalc()=" + 
getSiglacalc()+ ", getOperacaocalc()=" + getOperacaocalc()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
