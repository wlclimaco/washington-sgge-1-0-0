package com.sensus.mlc.contabil.model;
public class Lfmoddocfisc extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String codmoddocfisc;
    private String descmoddocfisc;
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
    * Gets the codmoddocfisc.
    * 
    * @return the codmoddocfisc 
    */
    	public String getCodmoddocfisc() 
    { 
    	return this.codmoddocfisc; 
    } 

    /** 
     * Sets the codmoddocfisc.
     *
     * @param codmoddocfisc the new codmoddocfisc 
     */
    public void setCodmoddocfisc(String codmoddocfisc)
    {
    	this.codmoddocfisc = codmoddocfisc; 
    }  
    /** 
    * Gets the descmoddocfisc.
    * 
    * @return the descmoddocfisc 
    */
    	public String getDescmoddocfisc() 
    { 
    	return this.descmoddocfisc; 
    } 

    /** 
     * Sets the descmoddocfisc.
     *
     * @param descmoddocfisc the new descmoddocfisc 
     */
    public void setDescmoddocfisc(String descmoddocfisc)
    {
    	this.descmoddocfisc = descmoddocfisc; 
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
return "Lfmoddocfisc[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodmoddocfisc()=" + getCodmoddocfisc()+ ", getDescmoddocfisc()=" + 
getDescmoddocfisc()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
