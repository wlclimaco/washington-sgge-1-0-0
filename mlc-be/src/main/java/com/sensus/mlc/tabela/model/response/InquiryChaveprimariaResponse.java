package com.sensus.mlc.chaveprimaria.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryChaveprimariaResponse extends InquiryResponse
{
    private List<Chaveprimaria> chaveprimaria ;


    public List<Chaveprimaria> getChaveprimaria() {
      return chaveprimaria;
    }


    public void setChaveprimaria(List<Chaveprimaria> chaveprimaria) {
        this.chaveprimaria = chaveprimaria;
}



    public String toString() {
    return "InquiryChaveprimariaResponse [chaveprimaria=" + chaveprimaria
      + ", getChaveprimaria()=" + getChaveprimaria()
    + "]";
    }

}
