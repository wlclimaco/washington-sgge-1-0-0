package com.sensus.mlc.classcliente.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryClassclienteResponse extends InquiryResponse
{
    private List<Classcliente> classcliente ;


    public List<Classcliente> getClasscliente() {
      return classcliente;
    }


    public void setClasscliente(List<Classcliente> classcliente) {
        this.classcliente = classcliente;
}



    public String toString() {
    return "InquiryClassclienteResponse [classcliente=" + classcliente
      + ", getClasscliente()=" + getClasscliente()
    + "]";
    }

}
