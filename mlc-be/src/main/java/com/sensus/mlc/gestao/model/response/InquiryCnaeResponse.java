package com.sensus.mlc.cnae.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryCnaeResponse extends InquiryResponse
{
    private List<Cnae> cnae ;


    public List<Cnae> getCnae() {
      return cnae;
    }


    public void setCnae(List<Cnae> cnae) {
        this.cnae = cnae;
}



    public String toString() {
    return "InquiryCnaeResponse [cnae=" + cnae
      + ", getCnae()=" + getCnae()
    + "]";
    }

}
