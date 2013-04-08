package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.pais.model.Pais;


public class PaisRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Pais  pais;
}
