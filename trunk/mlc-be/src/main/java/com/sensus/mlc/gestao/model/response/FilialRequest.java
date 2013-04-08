package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.filial.model.Filial;


public class FilialRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Filial  filial;
}
