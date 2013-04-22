package com.sensus.mlc.contabil.model;
public class Lfcsosn extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String csosn;
    private String desccsosn;
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
    * Gets the csosn.
    * 
    * @return the csosn 
    */
    	public String getCsosn() 
    { 
    	return this.csosn; 
    } 

    /** 
     * Sets the csosn.
     *
     * @param csosn the new csosn 
     */
    public void setCsosn(String csosn)
    {
    	this.csosn = csosn; 
    }  
    /** 
    * Gets the desccsosn.
    * 
    * @return the desccsosn 
    */
    	public String getDesccsosn() 
    { 
    	return this.desccsosn; 
    } 

    /** 
     * Sets the desccsosn.
     *
     * @param desccsosn the new desccsosn 
     */
    public void setDesccsosn(String desccsosn)
    {
    	this.desccsosn = desccsosn; 
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
return "Lfcsosn[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCsosn()=" + getCsosn()+ ", getDesccsosn()=" + getDesccsosn()+ ", getListinsalt()=" + 
getListinsalt()
+"]";
}
}
