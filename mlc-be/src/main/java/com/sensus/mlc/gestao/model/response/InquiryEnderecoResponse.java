package com.sensus.mlc.endereco.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryEnderecoResponse extends InquiryResponse
{
    private List<Endereco> endereco ;


    public List<Endereco> getEndereco() {
      return endereco;
    }


    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
}



    public String toString() {
    return "InquiryEnderecoResponse [endereco=" + endereco
      + ", getEndereco()=" + getEndereco()
    + "]";
    }

}
