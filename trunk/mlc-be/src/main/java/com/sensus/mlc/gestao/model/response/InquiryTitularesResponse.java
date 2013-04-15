package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.gestao.model.Titulares;


public class InquiryTitularesResponse extends InquiryResponse
{
    private List<Titulares> titulares ;


    public List<Titulares> getTitulares() {
      return titulares;
    }


    public void setTitulares(List<Titulares> titulares) {
        this.titulares = titulares;
}



    public String toString() {
    return "InquiryTitularesResponse [titulares=" + titulares
      + ", getTitulares()=" + getTitulares()
    + "]";
    }

}
