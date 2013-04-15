package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.gestao.model.Bairro;


public class InquiryBairroResponse extends InquiryResponse
{
    private List<Bairro> bairro ;


    public List<Bairro> getBairro() {
      return bairro;
    }


    public void setBairro(List<Bairro> bairro) {
        this.bairro = bairro;
}



    public String toString() {
    return "InquiryBairroResponse [bairro=" + bairro
      + ", getBairro()=" + getBairro()
    + "]";
    }

}
