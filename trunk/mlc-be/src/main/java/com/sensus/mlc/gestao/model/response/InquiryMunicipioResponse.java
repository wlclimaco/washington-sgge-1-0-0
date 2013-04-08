package com.sensus.mlc.municipio.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryMunicipioResponse extends InquiryResponse
{
    private List<Municipio> municipio ;


    public List<Municipio> getMunicipio() {
      return municipio;
    }


    public void setMunicipio(List<Municipio> municipio) {
        this.municipio = municipio;
}



    public String toString() {
    return "InquiryMunicipioResponse [municipio=" + municipio
      + ", getMunicipio()=" + getMunicipio()
    + "]";
    }

}
