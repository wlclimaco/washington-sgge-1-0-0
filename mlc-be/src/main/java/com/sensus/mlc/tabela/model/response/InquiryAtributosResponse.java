package com.sensus.mlc.tabela.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.tabela.model.Atributos;


public class InquiryAtributosResponse extends InquiryResponse
{
    private List<Atributos> atributos ;


    public List<Atributos> getAtributos() {
      return atributos;
    }


    public void setAtributos(List<Atributos> atributos) {
        this.atributos = atributos;
}



    public String toString() {
    return "InquiryAtributosResponse [atributos=" + atributos
      + ", getAtributos()=" + getAtributos()
    + "]";
    }

}
