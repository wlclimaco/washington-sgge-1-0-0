package com.sensus.mlc.contabil.model;
public class Lffretecompra extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private Integer codfrete;
    private Integer codcompra;
    private DATE dtins;
    private Date hins;
    private String idusuins;
    private DATE dtalt;
    private Date halt;
    private String idusualt;
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
    * Gets the codfrete.
    * 
    * @return the codfrete 
    */
    	public Integer getCodfrete() 
    { 
    	return this.codfrete; 
    } 

    /** 
     * Sets the codfrete.
     *
     * @param codfrete the new codfrete 
     */
    public void setCodfrete(Integer codfrete)
    {
    	this.codfrete = codfrete; 
    }  
    /** 
    * Gets the codcompra.
    * 
    * @return the codcompra 
    */
    	public Integer getCodcompra() 
    { 
    	return this.codcompra; 
    } 

    /** 
     * Sets the codcompra.
     *
     * @param codcompra the new codcompra 
     */
    public void setCodcompra(Integer codcompra)
    {
    	this.codcompra = codcompra; 
    }  
    /** 
    * Gets the dtins.
    * 
    * @return the dtins 
    */
    	public DATE getDtins() 
    { 
    	return this.dtins; 
    } 

    /** 
     * Sets the dtins.
     *
     * @param dtins the new dtins 
     */
    public void setDtins(DATE dtins)
    {
    	this.dtins = dtins; 
    }  
    /** 
    * Gets the hins.
    * 
    * @return the hins 
    */
    	public Date getHins() 
    { 
    	return this.hins; 
    } 

    /** 
     * Sets the hins.
     *
     * @param hins the new hins 
     */
    public void setHins(Date hins)
    {
    	this.hins = hins; 
    }  
    /** 
    * Gets the idusuins.
    * 
    * @return the idusuins 
    */
    	public String getIdusuins() 
    { 
    	return this.idusuins; 
    } 

    /** 
     * Sets the idusuins.
     *
     * @param idusuins the new idusuins 
     */
    public void setIdusuins(String idusuins)
    {
    	this.idusuins = idusuins; 
    }  
    /** 
    * Gets the dtalt.
    * 
    * @return the dtalt 
    */
    	public DATE getDtalt() 
    { 
    	return this.dtalt; 
    } 

    /** 
     * Sets the dtalt.
     *
     * @param dtalt the new dtalt 
     */
    public void setDtalt(DATE dtalt)
    {
    	this.dtalt = dtalt; 
    }  
    /** 
    * Gets the halt.
    * 
    * @return the halt 
    */
    	public Date getHalt() 
    { 
    	return this.halt; 
    } 

    /** 
     * Sets the halt.
     *
     * @param halt the new halt 
     */
    public void setHalt(Date halt)
    {
    	this.halt = halt; 
    }  
    /** 
    * Gets the idusualt.
    * 
    * @return the idusualt 
    */
    	public String getIdusualt() 
    { 
    	return this.idusualt; 
    } 

    /** 
     * Sets the idusualt.
     *
     * @param idusualt the new idusualt 
     */
    public void setIdusualt(String idusualt)
    {
    	this.idusualt = idusualt; 
    }  
/* 
* (non-Javadoc) 
* @see com.sensus.common.model.SensusModel#toString() 
*/ 
@Override 
public String toString()  
{
return "Lffretecompra[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodfrete()=" + getCodfrete()+ ", getCodcompra()=" + getCodcompra()+ ", getDtins()=" + 
getDtins()+ ", getHins()=" + getHins()+ ", getIdusuins()=" + getIdusuins()+ ", getDtalt()=" + getDtalt()+ ", getHalt()=" + getHalt()+ ", getIdusualt()=" + getIdusualt()
+"]";
}
}
