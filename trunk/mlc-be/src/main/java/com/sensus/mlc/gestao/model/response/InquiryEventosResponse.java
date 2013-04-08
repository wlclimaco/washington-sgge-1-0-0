package com.sensus.mlc.eventos.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryEventosResponse extends InquiryResponse
{
    private List<Eventos> eventos ;


    public List<Eventos> getEventos() {
      return eventos;
    }


    public void setEventos(List<Eventos> eventos) {
        this.eventos = eventos;
}



    public String toString() {
    return "InquiryEventosResponse [eventos=" + eventos
      + ", getEventos()=" + getEventos()
    + "]";
    }

}
