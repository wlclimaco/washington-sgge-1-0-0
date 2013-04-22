package com.sensus.mlc.contabil.model;
public class Lfmodnota extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private Integer codmodnota;
    private String descmodnota;
    private String tipomodnota;
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
    * Gets the codmodnota.
    * 
    * @return the codmodnota 
    */
    	public Integer getCodmodnota() 
    { 
    	return this.codmodnota; 
    } 

    /** 
     * Sets the codmodnota.
     *
     * @param codmodnota the new codmodnota 
     */
    public void setCodmodnota(Integer codmodnota)
    {
    	this.codmodnota = codmodnota; 
    }  
    /** 
    * Gets the descmodnota.
    * 
    * @return the descmodnota 
    */
    	public String getDescmodnota() 
    { 
    	return this.descmodnota; 
    } 

    /** 
     * Sets the descmodnota.
     *
     * @param descmodnota the new descmodnota 
     */
    public void setDescmodnota(String descmodnota)
    {
    	this.descmodnota = descmodnota; 
    }  
    /** 
    * Gets the tipomodnota.
    * 
    * @return the tipomodnota 
    */
    	public String getTipomodnota() 
    { 
    	return this.tipomodnota; 
    } 

    /** 
     * Sets the tipomodnota.
     *
     * @param tipomodnota the new tipomodnota 
     */
    public void setTipomodnota(String tipomodnota)
    {
    	this.tipomodnota = tipomodnota; 
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
return "Lfmodnota[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodmodnota()=" + getCodmodnota()+ ", getDescmodnota()=" + getDescmodnota()+ ", 
getTipomodnota()=" + getTipomodnota()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
