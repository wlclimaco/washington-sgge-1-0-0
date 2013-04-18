package com.sensus.mlc.tabela.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.tabela.model.Dominios;


public class InquiryDominiosResponse extends InquiryResponse
{
    private List<Dominios> dominios ;


    public List<Dominios> getDominios() {
      return dominios;
    }


    public void setDominios(List<Dominios> dominios) {
        this.dominios = dominios;
}



    public String toString() {
    return "InquiryDominiosResponse [dominios=" + dominios
      + ", getDominios()=" + getDominios()
    + "]";
    }

}
