package com.sensus.mlc.eventos.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.eventos.model.Eventos;


public class EventosResponse extends Response
{
    
    private Integer parentRetry;
    
    private List<Eventos> eventos ;
}
