package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.embalagens.model.Embalagens;


public class EmbalagensRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Embalagens  embalagens;
}
