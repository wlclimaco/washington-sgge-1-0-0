package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.gestao.model.Pais;


public class InquiryPaisResponse extends InquiryResponse
{
    private List<Pais> pais ;


    public List<Pais> getPais() {
      return pais;
    }


    public void setPais(List<Pais> pais) {
        this.pais = pais;
}



    public String toString() {
    return "InquiryPaisResponse [pais=" + pais
      + ", getPais()=" + getPais()
    + "]";
    }

}
