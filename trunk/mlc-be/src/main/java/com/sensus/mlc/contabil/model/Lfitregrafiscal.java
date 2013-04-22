package com.sensus.mlc.contabil.model;
public class Lfitregrafiscal extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private Integer codregra;
    private String codnat;
    private String noufitrf;
    private String cvitrf;
    private Integer codemptm;
    private Integer codfilialtm;
    private Integer codtipomov;
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
    * Gets the codregra.
    * 
    * @return the codregra 
    */
    	public Integer getCodregra() 
    { 
    	return this.codregra; 
    } 

    /** 
     * Sets the codregra.
     *
     * @param codregra the new codregra 
     */
    public void setCodregra(Integer codregra)
    {
    	this.codregra = codregra; 
    }  
    /** 
    * Gets the codnat.
    * 
    * @return the codnat 
    */
    	public String getCodnat() 
    { 
    	return this.codnat; 
    } 

    /** 
     * Sets the codnat.
     *
     * @param codnat the new codnat 
     */
    public void setCodnat(String codnat)
    {
    	this.codnat = codnat; 
    }  
    /** 
    * Gets the noufitrf.
    * 
    * @return the noufitrf 
    */
    	public String getNoufitrf() 
    { 
    	return this.noufitrf; 
    } 

    /** 
     * Sets the noufitrf.
     *
     * @param noufitrf the new noufitrf 
     */
    public void setNoufitrf(String noufitrf)
    {
    	this.noufitrf = noufitrf; 
    }  
    /** 
    * Gets the cvitrf.
    * 
    * @return the cvitrf 
    */
    	public String getCvitrf() 
    { 
    	return this.cvitrf; 
    } 

    /** 
     * Sets the cvitrf.
     *
     * @param cvitrf the new cvitrf 
     */
    public void setCvitrf(String cvitrf)
    {
    	this.cvitrf = cvitrf; 
    }  
    /** 
    * Gets the codemptm.
    * 
    * @return the codemptm 
    */
    	public Integer getCodemptm() 
    { 
    	return this.codemptm; 
    } 

    /** 
     * Sets the codemptm.
     *
     * @param codemptm the new codemptm 
     */
    public void setCodemptm(Integer codemptm)
    {
    	this.codemptm = codemptm; 
    }  
    /** 
    * Gets the codfilialtm.
    * 
    * @return the codfilialtm 
    */
    	public Integer getCodfilialtm() 
    { 
    	return this.codfilialtm; 
    } 

    /** 
     * Sets the codfilialtm.
     *
     * @param codfilialtm the new codfilialtm 
     */
    public void setCodfilialtm(Integer codfilialtm)
    {
    	this.codfilialtm = codfilialtm; 
    }  
    /** 
    * Gets the codtipomov.
    * 
    * @return the codtipomov 
    */
    	public Integer getCodtipomov() 
    { 
    	return this.codtipomov; 
    } 

    /** 
     * Sets the codtipomov.
     *
     * @param codtipomov the new codtipomov 
     */
    public void setCodtipomov(Integer codtipomov)
    {
    	this.codtipomov = codtipomov; 
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
return "Lfitregrafiscal[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodregra()=" + getCodregra()+ ", getCodnat()=" + getCodnat()+ ", getNoufitrf()=" + 
getNoufitrf()+ ", getCvitrf()=" + getCvitrf()+ ", getCodemptm()=" + getCodemptm()+ ", getCodfilialtm()=" + getCodfilialtm()+ ", getCodtipomov()=" + getCodtipomov()+ ", getListinsalt()=" 
+ getListinsalt()
+"]";
}
}
