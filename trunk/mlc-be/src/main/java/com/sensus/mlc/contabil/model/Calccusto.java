package com.sensus.mlc.contabil.model;
public class Calccusto extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private Integer codcalc;
    private String desccalc;
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
    * Gets the desccalc.
    * 
    * @return the desccalc 
    */
    	public String getDesccalc() 
    { 
    	return this.desccalc; 
    } 

    /** 
     * Sets the desccalc.
     *
     * @param desccalc the new desccalc 
     */
    public void setDesccalc(String desccalc)
    {
    	this.desccalc = desccalc; 
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
return "Calccusto[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodcalc()=" + getCodcalc()+ ", getDesccalc()=" + getDesccalc()+ ", getListinsalt()=" + 
getListinsalt()
+"]";
}
}
