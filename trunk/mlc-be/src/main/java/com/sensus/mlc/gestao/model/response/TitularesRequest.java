package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.titulares.model.Titulares;


public class TitularesRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Titulares  titulares;
}
