package com.sensus.mlc.contabil.model;
public class Lffretevenda extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private Integer codfrete;
    private Integer codvenda;
    private Integer tipovenda;
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
    * Gets the codvenda.
    * 
    * @return the codvenda 
    */
    	public Integer getCodvenda() 
    { 
    	return this.codvenda; 
    } 

    /** 
     * Sets the codvenda.
     *
     * @param codvenda the new codvenda 
     */
    public void setCodvenda(Integer codvenda)
    {
    	this.codvenda = codvenda; 
    }  
    /** 
    * Gets the tipovenda.
    * 
    * @return the tipovenda 
    */
    	public Integer getTipovenda() 
    { 
    	return this.tipovenda; 
    } 

    /** 
     * Sets the tipovenda.
     *
     * @param tipovenda the new tipovenda 
     */
    public void setTipovenda(Integer tipovenda)
    {
    	this.tipovenda = tipovenda; 
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
return "Lffretevenda[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodfrete()=" + getCodfrete()+ ", getCodvenda()=" + getCodvenda()+ ", getTipovenda()=" 
+ getTipovenda()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
