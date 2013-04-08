package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.eventos.model.Eventos;


public class EventosRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Eventos  eventos;
}
