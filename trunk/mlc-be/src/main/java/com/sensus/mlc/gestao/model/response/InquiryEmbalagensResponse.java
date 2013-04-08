package com.sensus.mlc.embalagens.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryEmbalagensResponse extends InquiryResponse
{
    private List<Embalagens> embalagens ;


    public List<Embalagens> getEmbalagens() {
      return embalagens;
    }


    public void setEmbalagens(List<Embalagens> embalagens) {
        this.embalagens = embalagens;
}



    public String toString() {
    return "InquiryEmbalagensResponse [embalagens=" + embalagens
      + ", getEmbalagens()=" + getEmbalagens()
    + "]";
    }

}
